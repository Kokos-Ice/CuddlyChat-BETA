package game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.DiceCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.query;

public class Freidiffen
{
  private static String[] wuerfelfarben = { "blue", "brightgreen", "darkblue", "green", "orange", "pink", "red" };

  public static void make(String arg, Client client, Channel channel)
  {
    Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
    int dran = 0;
    String next = "";

    int publics = 0;

    String vons = "";
    String gegners = "";
    String knuddel = "0";
    String rosen = "0";
    String aktuplayer = "";

    int maxwurf = 0;
    int anzahl = 0;
    String wurf = "";

    PoolConnection pconss = ConnectionPool.getConnection();
    PreparedStatement psss = null;
    try
    {
      Connection conss = pconss.connect();
      psss = conss.prepareStatement("SELECT * FROM game_freidiffen where channel = '" + channel.getName() + "' and aktuplayer = '" + client.getName() + "'");
      ResultSet rsss = psss.executeQuery();
      while (rsss.next()) {
        dran = 1;

        publics = rsss.getInt("public");

        knuddel = rsss.getString("einsatz_knuddel");
        rosen = rsss.getString("einsatz_rosen");
        gegners = rsss.getString("gegner");
        vons = rsss.getString("von");
        maxwurf = rsss.getInt("maxwurf");
        anzahl = rsss.getInt("anzahl");
        wurf = rsss.getString("wurf");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (psss != null) try { psss.close(); } catch (SQLException e) {  } pconss.close();
    }
    if (dran == 1) {
      if (client.getName().equals(vons))
        next = gegners;
      else {
        next = vons;
      }
      String variablen = DiceCreator.Würfeln(arg, 0);
      String[] teil = variablen.split("\\|");
      if (!arg.trim().equals(wurf)) {
        client.sendButlerMessage(channel.getName(), String.format("Du musst mit _" + wurf + "_ würfeln.", new Object[0]));
        return;
      }
      if (!teil[5].equals("0")) {
        client.sendButlerMessage(channel.getName(), String.format("%s", new Object[] { teil[2] }));
        return;
      }
      Client target1 = Server.get().getClient(next);
      boolean online = true; if (target1 == null) { online = false; target1 = new Client(null); target1.loadStats(next);
      }

      channel.broadcastAction("°BB°", String.format("> %s rollt %s Würfel%s...#%s: %s = _%s_", new Object[] { client.getName(), teil[0], teil[1], teil[2], teil[3], teil[4] }));

      if (maxwurf == 0)
      {
        client.sendButlerMessage(channel.getName(), "Aufgepasst! " + next + " wird nun versuchen besser zu würfeln als du.");
        target1.sendButlerMessage(channel.getName(), "Würfele nun mit _°BB>_2/dice " + wurf + "|/dice " + wurf + "<°_§ und hol dir den Sieg.");
        query.update("update game_freidiffen set start='" + time + "',maxwurf='" + teil[4] + "', aktuplayer='" + next + "' where aktuplayer='" + client.getName() + "'");
      } else {
        Random zufall = new Random();
        String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];

        String einsatz = "";
        if ((!knuddel.equals("0")) && (rosen.equals("0")))
          einsatz = Integer.parseInt(knuddel) + " Knuddel";
        else if ((!rosen.equals("0")) && (knuddel.equals("0"))) {
          einsatz = Integer.parseInt(rosen) + " Rosen";
        }
        String einsatz2 = "";
        if ((!knuddel.equals("0")) && (rosen.equals("0")))
          einsatz2 = Integer.parseInt(knuddel) + Integer.parseInt(knuddel) + " Knuddel";
        else if ((!rosen.equals("0")) && (knuddel.equals("0"))) {
          einsatz2 = Integer.parseInt(rosen) + Integer.parseInt(rosen) + " Rosen";
        }

        if (maxwurf > Integer.parseInt(teil[4]))
        {
          channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Freidiffen gewann " + next + ". Der Gewinn in Höhe von _" + einsatz2 + "_ wurde soeben ausgezahlt.", new Object[0]));
          target1.increaseKnuddels(target1.getKnuddels() + Integer.parseInt(knuddel) + Integer.parseInt(knuddel));
          target1.setRosensperre(String.valueOf(Integer.parseInt(target1.getRosensperre()) + Integer.parseInt(rosen) + Integer.parseInt(rosen)));
        }
        else if (maxwurf < Integer.parseInt(teil[4])) {
          channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Freidiffen gewann " + client.getName() + ". Der Gewinn in Höhe von _" + einsatz2 + "_ wurde soeben ausgezahlt.", new Object[0]));
          client.increaseKnuddels(client.getKnuddels() + Integer.parseInt(knuddel) + Integer.parseInt(knuddel));
          client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) + Integer.parseInt(rosen) + Integer.parseInt(rosen)));
        }
        else {
          channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Freidiffen zwischen " + client.getName() + " und " + next + " endete unentschieden, Aus diesem Grund wurde der Einsatz (" + einsatz + ") zurückgezahlt.", new Object[0]));
          client.increaseKnuddels(client.getKnuddels() + Integer.parseInt(knuddel));
          client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) + Integer.parseInt(rosen)));
          target1.increaseKnuddels(target1.getKnuddels() + Integer.parseInt(knuddel));
          target1.setRosensperre(String.valueOf(Integer.parseInt(target1.getRosensperre()) + Integer.parseInt(rosen)));
        }

        client.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");
        target1.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");

        query.insert("insert into game_freidiffen_log set savetime='" + time + "', privi='" + publics + "', knuddels='" + knuddel + "', rosen='" + rosen + "', gegner='" + gegners + "', anzahl='" + anzahl + "', wurf='" + wurf + "', user='" + vons + "'");

        query.delete("delete from game_freidiffen where aktuplayer='" + client.getName() + "'");
      }
    }
  }
}
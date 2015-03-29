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

public class Darten
{
  private static String[] wuerfelfarben = { "blue", "brightgreen", "darkblue", "green", "orange", "pink", "red" };

  public static void make(String arg, Client client, Channel channel)
  {
    Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
    int dran = 0;
    String next = "";

    int offen = 0;
    int publics = 0;
    int nachwurf = 0;
    int myzahl = 0;
    int nachwurfaktiv = 0;
    String vons = "";
    String gegners = "";
    int startzahl = 0;
    String knuddel = "0";
    String rosen = "0";
    String aktuplayer = "";
    String feld = "";
    int otherzahl = 0;
    PoolConnection pconss = ConnectionPool.getConnection();
    PreparedStatement psss = null;
    try
    {
      Connection conss = pconss.connect();
      psss = conss.prepareStatement("SELECT * FROM game_darten where channel = '" + channel.getName() + "' and aktuplayer = '" + client.getName() + "'");
      ResultSet rsss = psss.executeQuery();
      while (rsss.next()) {
        dran = 1;

        if (rsss.getString("aktuplayer").equals(rsss.getString("von"))) {
          myzahl = rsss.getInt("startplayer1");
          otherzahl = rsss.getInt("startplayer2");
          feld = "startplayer1";
        } else {
          myzahl = rsss.getInt("startplayer2");
          otherzahl = rsss.getInt("startplayer1");
          feld = "startplayer2";
        }
        offen = rsss.getInt("offen");
        startzahl = rsss.getInt("startzahl");
        nachwurfaktiv = rsss.getInt("nachwurfaktiv");
        knuddel = rsss.getString("einsatz_knuddel");
        rosen = rsss.getString("einsatz_rosen");
        nachwurf = rsss.getInt("nachwurf");
        gegners = rsss.getString("gegner");
        vons = rsss.getString("von");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (psss != null) try { psss.close(); } catch (SQLException e) {  } pconss.close();
    }

    if (dran == 1) {
      if (client.getName().equals(vons))
        next = gegners;
      else {
        next = vons;
      }

      String variablen = DiceCreator.Würfeln(arg, offen);
      String[] teil = variablen.split("\\|");
      if ((!arg.trim().equals("1w" + myzahl)) && (!arg.trim().equals("" + myzahl))) {
        client.sendButlerMessage(channel.getName(), String.format("Du musst mit _1w" + myzahl + "_ würfeln.", new Object[0]));
        return;
      }
      if (!teil[5].equals("0")) {
        client.sendButlerMessage(channel.getName(), String.format("%s", new Object[] { teil[2] }));
        return;
      }
      Client target1 = Server.get().getClient(next);
      boolean online = true; if (target1 == null) { online = false; target1 = new Client(null); target1.loadStats(next);
      }
      Client target2 = Server.get().getClient(gegners);
      boolean online2 = true; if (target2 == null) { online2 = false; target2 = new Client(null); target2.loadStats(gegners);
      }
      Client target4 = Server.get().getClient(vons);
      boolean online4 = true; if (target4 == null) { online4 = false; target4 = new Client(null); target4.loadStats(vons);
      }
      
      channel.broadcastAction("°BB°", String.format("> %s rollt %s Würfel%s...#%s: %s = _%s_", new Object[] { client.getName(), teil[0], teil[1], teil[2], teil[3], teil[4] }));

      if (nachwurfaktiv == 1) {
        if (teil[4].equals("1")) {
          Random zufall = new Random();
          String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];
          String einsatz2 = "";
          if ((!knuddel.equals("0")) && (rosen.equals("0")))
            einsatz2 = Integer.parseInt(knuddel) + " Knuddel";
          else if ((!rosen.equals("0")) && (knuddel.equals("0")))
            einsatz2 = Integer.parseInt(rosen) + " Rosen";
          else {
            einsatz2 = Integer.parseInt(knuddel) + " Knuddel und " + Integer.parseInt(rosen) + Integer.parseInt(rosen) + " Rosen";
          }
          channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Darten zwischen " + vons + " und " + gegners + " endete unentschieden. Aus diesem Grund wurde der Einsatz (" + einsatz2 + ") zurückgezahlt.", new Object[0]));
          target4.setRosensperre(String.valueOf(Integer.parseInt(target4.getRosensperre()) + Integer.parseInt(rosen)));
          target4.increaseKnuddels(target4.getKnuddels() + Integer.parseInt(knuddel));
          target2.setRosensperre(String.valueOf(Integer.parseInt(target2.getRosensperre()) + Integer.parseInt(rosen)));
          target2.increaseKnuddels(target2.getKnuddels() + Integer.parseInt(knuddel));
          client.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");
          target1.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");

          query.insert("insert into game_darten_log set savetime='" + time + "', privi='" + publics + "', offen='" + offen + "', knuddels='" + knuddel + "', rosen='" + rosen + "', gegner='" + gegners + "', startzahl='" + startzahl + "', nachwurf='" + nachwurf + "', user='" + vons + "'");
          query.delete("delete from game_darten where aktuplayer='" + client.getName() + "'");
        }
        else
        {
          Random zufall = new Random();
          String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];

          String einsatz = "";
          if ((!knuddel.equals("0")) && (rosen.equals("0")))
            einsatz = Integer.parseInt(knuddel) + Integer.parseInt(knuddel) + " Knuddel";
          else if ((!rosen.equals("0")) && (knuddel.equals("0")))
            einsatz = Integer.parseInt(rosen) + Integer.parseInt(rosen) + " Rosen";
          else {
            einsatz = Integer.parseInt(knuddel) + Integer.parseInt(knuddel) + " Knuddel und " + Integer.parseInt(rosen) + Integer.parseInt(rosen) + " Rosen";
          }

          channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Darten gewann " + next + ". Der Gewinn in Höhe von _" + einsatz + "_ wurde soeben ausgezahlt.", new Object[0]));
          Client winners = Server.get().getClient(next);
          boolean online3 = true; if (winners == null) { online3 = false; winners = new Client(null); winners.loadStats(next);
          }

          winners.setRosensperre(String.valueOf(Integer.parseInt(winners.getRosensperre()) + Integer.parseInt(rosen) + Integer.parseInt(rosen)));
          winners.increaseKnuddels(winners.getKnuddels() + Integer.parseInt(knuddel) + Integer.parseInt(knuddel));

          client.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");
          winners.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");

          query.insert("insert into game_darten_log set savetime='" + time + "', privi='" + publics + "', offen='" + offen + "', knuddels='" + knuddel + "', rosen='" + rosen + "', gegner='" + gegners + "', startzahl='" + startzahl + "', nachwurf='" + nachwurf + "', user='" + vons + "'");

          query.delete("delete from game_darten where aktuplayer='" + client.getName() + "'");
        }

        return;
      }

      if ((teil[4].equals("1")) && (nachwurf == 1) && (client.getName().equals(vons))) {
        Client winners = Server.get().getClient(next);
        boolean online3 = true; if (winners == null) { online3 = false; winners = new Client(null); winners.loadStats(next);
        }

        client.sendButlerMessage(channel.getName(), "Unter Druck: " + next + " muss nun _die Eins würfeln_, um ein Unentschieden zu erreichen.");
        if (offen == 0)
          winners.sendButlerMessage(channel.getName(), "Unter Druck: Du musst nun _die Eins würfeln_, um ein Untenscheiden zu erreichen: _°BB>_2/dice 1w" + otherzahl + "|/dice 1w" + otherzahl + "<°");
        else {
          winners.sendButlerMessage(channel.getName(), "Unter Druck: Du musst nun _die Eins würfeln_, um ein Untenscheiden zu erreichen: _°BB>_2/diceo 1w" + otherzahl + "|/diceo 1w" + otherzahl + "<°");
        }

        query.update("update game_darten set nachwurfaktiv='1', aktuplayer='" + next + "' where aktuplayer='" + client.getName() + "'");
        return;
      }if (((teil[4].equals("1")) && (nachwurf == 0)) || ((teil[4].equals("1")) && (nachwurf == 1) && (client.getName().equals(gegners)))) {
        Random zufall = new Random();
        String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];

        String einsatz = "";
        if ((!knuddel.equals("0")) && (rosen.equals("0")))
          einsatz = Integer.parseInt(knuddel) + Integer.parseInt(knuddel) + " Knuddel";
        else if ((!rosen.equals("0")) && (knuddel.equals("0")))
          einsatz = Integer.parseInt(rosen) + Integer.parseInt(rosen) + " Rosen";
        else {
          einsatz = Integer.parseInt(knuddel) + Integer.parseInt(knuddel) + " Knuddel und " + Integer.parseInt(rosen) + Integer.parseInt(rosen) + " Rosen";
        }

        channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Darten gewann " + client.getName() + ". Der Gewinn in Höhe von _" + einsatz + "_ wurde soeben ausgezahlt.", new Object[0]));
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) + Integer.parseInt(rosen) + Integer.parseInt(rosen)));
        client.increaseKnuddels(client.getKnuddels() + Integer.parseInt(knuddel) + Integer.parseInt(knuddel));
        Client winners = Server.get().getClient(next);
        boolean online3 = true; if (winners == null) { online3 = false; winners = new Client(null); winners.loadStats(next);
        }
        client.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");
        winners.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");

        query.insert("insert into game_darten_log set savetime='" + time + "', privi='" + publics + "', offen='" + offen + "', knuddels='" + knuddel + "', rosen='" + rosen + "', gegner='" + gegners + "', startzahl='" + startzahl + "', nachwurf='" + nachwurf + "', user='" + vons + "'");
        query.delete("delete from game_darten where aktuplayer='" + client.getName() + "'");

        return;
      }
      client.sendButlerMessage(channel.getName(), "Du hast eine " + teil[4] + " gewürfelt. " + next + " wir nun mit einem " + otherzahl + "-seitingen Würfel würfeln.");
      if (offen == 0)
        target1.sendButlerMessage(channel.getName(), client.getName() + " hat gerade eine " + teil[4] + " gewürfelt. Du bist nun an der Reihe _°BB>_2/dice 1w" + otherzahl + "|/dice 1w" + otherzahl + "<°_§ zu würfeln.");
      else {
        target1.sendButlerMessage(channel.getName(), client.getName() + " hat gerade eine " + teil[4] + " gewürfelt. Du bist nun an der Reihe _°BB>_2/diceo 1w" + otherzahl + "|/diceo 1w" + otherzahl + "<°_§ zu würfeln.");
      }
      query.update("update game_darten set " + feld + "='" + teil[4] + "', aktuplayer='" + next + "' where aktuplayer='" + client.getName() + "'");
    }
  }
}
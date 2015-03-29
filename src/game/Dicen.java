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

public class Dicen
{
  private static String[] wuerfelfarben = { "blue", "brightgreen", "darkblue", "green", "orange", "pink", "red" };

  public static void make(String arg, Client client, Channel channel)
  {
    Long time = Long.valueOf(System.currentTimeMillis() / 1000L);

    int offen = 0;
    String maxwurf = "";
    int startrunde = 1;
    int runden = 1;
    String aktuwurf = "";
    int dran = 0;
    String punkte = "";
    int vorsprung = 0;
    String vons = "";
    String publics = "";
    String gegners = "";
    String knuddel = "0";
    String rosen = "0";
    PoolConnection pconss = ConnectionPool.getConnection();
    PreparedStatement psss = null;
    try
    {
      Connection conss = pconss.connect();
      psss = conss.prepareStatement("SELECT * FROM game_dicen where channel = '" + channel.getName() + "' and aktuplayer = '" + client.getName() + "' and maxwurf != '0'");
      ResultSet rsss = psss.executeQuery();
      while (rsss.next()) {
        dran = 1;
        punkte = rsss.getString("punkte");
        publics = rsss.getString("public");
        vorsprung = rsss.getInt("vorsprung");
        startrunde = rsss.getInt("startrunde");
        maxwurf = rsss.getString("maxwurf");
        offen = rsss.getInt("offen");
        runden = rsss.getInt("runden");
        knuddel = rsss.getString("einsatz_knuddel");
        rosen = rsss.getString("einsatz_rosen");
        aktuwurf = rsss.getString("aktuwurf");
        gegners = rsss.getString("gegner");
        vons = rsss.getString("von");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (psss != null) try { psss.close(); } catch (SQLException e) {  } pconss.close();
    }

    if (dran == 1) {
      String[] punktes = punkte.split("\\|", 2);
      int punktevon = Integer.parseInt(punktes[0]);
      int punktegegner = Integer.parseInt(punktes[1]);
      String variablen = DiceCreator.Würfeln(arg, offen);
      String[] teil = variablen.split("\\|");
      if (!arg.trim().equals(aktuwurf.trim())) {
        client.sendButlerMessage(channel.getName(), String.format("Du musst mit _" + aktuwurf + "_ würfeln.", new Object[0]));
        return;
      }
      if (!teil[5].equals("0")) {
        client.sendButlerMessage(channel.getName(), String.format("%s", new Object[] { teil[2] }));
        return;
      }

     
      channel.broadcastAction("°BB°", String.format("> %s rollt %s Würfel%s...#%s: %s = _%s_", new Object[] { client.getName(), teil[0], teil[1], teil[2], teil[3], teil[4] }));
      String[] oldwurf = maxwurf.split("\\|", 2);
      int oldwurfzahl = Integer.parseInt(oldwurf[1]);
      int neuwurfzahl = Integer.parseInt(teil[4]);
      String winner = "";
      String winwurf = "";

      if (oldwurfzahl > neuwurfzahl) {
        winner = oldwurf[0];
        winwurf = oldwurf[1];
      } else if (oldwurfzahl < neuwurfzahl) {
        if (oldwurf[0].equals(vons))
          winner = vons;
        else {
          winner = gegners;
        }
        winwurf = teil[4];
      } else {
        query.update("update game_dicen set startrunde=startrunde-'1'  where von='" + client.getName() + "' or gegner ='" + client.getName() + "'");
        startrunde -= 1;
      }

      if (winner.equals(vons))
        punktevon++;
      else if (winner.equals(gegners)) {
        punktegegner++;
      }

      int diff = punktevon - punktegegner * -1;
      Client target1 = Server.get().getClient(vons);
      boolean online = true; if (target1 == null) { online = false; target1 = new Client(null); target1.loadStats(vons); }
      Client target2 = Server.get().getClient(gegners);
      boolean online2 = true; if (target2 == null) { online2 = false; target2 = new Client(null); target2.loadStats(gegners); }
      if ((startrunde >= runden) && (diff >= vorsprung))
      {
        target1.sendButlerMessage(channel.getName(), "Mit dem Wurf (" + winwurf + ") hat " + winner + " diese Runde und damit das ganze Spiel für sich entschieden. _Endstand:_ " + vons + " (" + punktevon + ") vs. " + gegners + " (" + punktegegner + ")");
        target2.sendButlerMessage(channel.getName(), "Mit dem Wurf (" + winwurf + ") hat " + winner + " diese Runde und damit das ganze Spiel für sich entschieden. _Endstand:_ " + vons + " (" + punktevon + ") vs. " + gegners + " (" + punktegegner + ")");

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

        channel.broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> Das Spiel Dicen gewann " + winner + ". Der Gewinn in Höhe von _" + einsatz + "_ wurde soeben ausgezahlt.", new Object[0]));

        Client winners = Server.get().getClient(winner);
        boolean online3 = true; if (winners == null) { online3 = false; winners = new Client(null); winners.loadStats(winner);
        }
        winners.setRosensperre(String.valueOf(Integer.parseInt(winners.getRosensperre()) + Integer.parseInt(rosen) + Integer.parseInt(rosen)));
        winners.increaseKnuddels(winners.getKnuddels() + Integer.parseInt(knuddel) + Integer.parseInt(knuddel));

        target1.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");
        target2.sendButlerMessage(channel.getName(), "Möchtest du noch eine Runde spielen? Biete eine neue Runde mit _°BB>_2/game again|/game again<°_§ oder eine ähnliche Runde mit _°BB>_2/game lastconfig|/game lastconfig<°_§ an.");

        query.insert("insert into game_dicen_log set savetime='" + time + "', privi='" + publics + "', offen='" + offen + "', knuddels='" + knuddel + "', rosen='" + rosen + "', gegner='" + gegners + "', siegrunden='" + runden + "', vorsprung='" + vorsprung + "', user='" + vons + "'");
        query.delete("delete from game_dicen where von='" + client.getName() + "' or gegner ='" + client.getName() + "'");
      }
      else
      {
        if (winner.isEmpty()) {
          target1.sendButlerMessage(channel.getName(), "Diese Runde endet unentschieden. _Aktueller Stand:_ " + vons + " (" + punktevon + ") vs. " + gegners + " (" + punktegegner + ")");
          target2.sendButlerMessage(channel.getName(), "Diese Runde endet unentschieden. _Aktueller Stand:_ " + vons + " (" + punktevon + ") vs. " + gegners + " (" + punktegegner + ")");
        }
        else
        {
          target1.sendButlerMessage(channel.getName(), "In dieser Runde hat sich " + winner + " mit dem Wurf (" + winwurf + ") als Gewinner hervor getan. _Aktueller Stand:_ " + vons + " (" + punktevon + ") vs. " + gegners + " (" + punktegegner + ")");
          target2.sendButlerMessage(channel.getName(), "In dieser Runde hat sich " + winner + " mit dem Wurf (" + winwurf + ") als Gewinner hervor getan. _Aktueller Stand:_ " + vons + " (" + punktevon + ") vs. " + gegners + " (" + punktegegner + ")");
        }
        client.sendButlerMessage(channel.getName(), "Sage jetzt mit welchen Würfeln gewürfelt werden soll (Beispiel: _°BB>_2/game " + aktuwurf + "|/game " + aktuwurf + "<°_§)");
        query.update("update game_dicen set start='" + time + "',punkte='" + punktevon + "|" + punktegegner + "',startrunde=startrunde+'1', maxwurf='0' where von='" + client.getName() + "' or gegner ='" + client.getName() + "'");
      }
      return;
    }
  }
}
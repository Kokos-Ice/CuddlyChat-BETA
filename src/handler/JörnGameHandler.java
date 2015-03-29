package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.KCodeParser;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.query;

public class JörnGameHandler
{
  private static String[] wuerfelfarben = { "blue", "brightgreen", "darkblue", "green", "orange", "pink", "red" };

  private static boolean isInteger(String s) { try { Integer.parseInt(s); return true; } catch (NumberFormatException e) {  } return false;
  }

  public static void handle(String[] tokens, Client client)
  {
    int dabei = 0;
    PoolConnection pconss1 = ConnectionPool.getConnection(); PreparedStatement psss1 = null;
    try { Connection conss1 = pconss1.connect();
      psss1 = conss1.prepareStatement("SELECT * FROM game_dicen where von='" + client.getName() + "' or gegner='" + client.getName() + "'");
      for (ResultSet rsss1 = psss1.executeQuery(); rsss1.next(); )
        dabei = 1; } catch (SQLException e) {
      e.printStackTrace(); } finally { if (psss1 != null) try { psss1.close(); } catch (SQLException e) {  } pconss1.close();
    }
    PoolConnection pconss2 = ConnectionPool.getConnection(); PreparedStatement psss2 = null;
    try { Connection conss2 = pconss2.connect();
      psss2 = conss2.prepareStatement("SELECT * FROM game_darten where von='" + client.getName() + "' or gegner='" + client.getName() + "'");
      for (ResultSet rsss2 = psss2.executeQuery(); rsss2.next(); )
        dabei = 1; } catch (SQLException e) {
      e.printStackTrace(); } finally { if (psss2 != null) try { psss2.close(); } catch (SQLException e) {  } pconss2.close();
    }
    PoolConnection pconss3 = ConnectionPool.getConnection(); PreparedStatement psss3 = null;
    try { Connection conss3 = pconss3.connect();
      psss3 = conss3.prepareStatement("SELECT * FROM game_freidiffen where von='" + client.getName() + "' or gegner='" + client.getName() + "'");
      for (ResultSet rsss3 = psss3.executeQuery(); rsss3.next(); )
        dabei = 1; } catch (SQLException e) {
      e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
    }
    if (dabei == 1)
    {
      client.sendButlerMessage(client.getChannel().getName(), "Du bietest momentan ein Spiel an und kannst deshalb kein weiteres Spiel starten.");

      return;
    }

    if (tokens[1].equals("dicen"))
    {
      if ((!isInteger(tokens[6])) || (!isInteger(tokens[5])) || ((Integer.parseInt(tokens[6]) == 0) && (Integer.parseInt(tokens[5]) == 0))) {
        client.sendButlerMessage(client.getChannel().getName(), "Du musst Rosen oder Knuddel setzen, um das Spiel zu starten.");
        return;
      }if (client.getKnuddels() / 2 < Integer.parseInt(tokens[5])) {
        client.sendButlerMessage(client.getChannel().getName(), "Du verfügst nicht über genug Knuddel, um dieses Spiel zu starten. (max. 50% der eigenen Knuddel)");
        return;
      }if ((!isInteger(tokens[8])) || (Integer.parseInt(tokens[8]) == 0)) {
        client.sendButlerMessage(client.getChannel().getName(), "Die Anzahl der Siegrunden muss mindestens 1 sein.");
        return;
      }if ((!isInteger(tokens[9])) || (Integer.parseInt(tokens[9]) == 0)) {
        client.sendButlerMessage(client.getChannel().getName(), "Der Siegvorsprung muss mindestens 1 sein.");
        return;
      }if (Integer.parseInt(tokens[8]) >= 21) {
        client.sendButlerMessage(client.getChannel().getName(), "Die Anzahl der Siegrunden darf höchstens 20 sein.");
        return;
      }if (Integer.parseInt(tokens[9]) >= 6) {
        client.sendButlerMessage(client.getChannel().getName(), "Der Siegvorsprung darf höchstens 5 sein.");
        return;
      }if (Integer.parseInt(client.getRosensperre()) < Integer.parseInt(tokens[6])) {
        client.sendButlerMessage(client.getChannel().getName(), "Du verfügst nicht über genug Rosen, um dieses Spiel zu starten.");
        return;
      }

      String einsatz = "";
      if ((!tokens[5].equals("0")) && (tokens[6].equals("0"))) {
        einsatz = tokens[5] + " Knuddel";
        client.increaseKnuddels(client.getKnuddels() - Integer.parseInt(tokens[5]));
      }
      else if ((!tokens[6].equals("0")) && (tokens[5].equals("0"))) {
        einsatz = tokens[6] + " Rosen";
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - Integer.parseInt(tokens[6])));
      }
      else {
        einsatz = tokens[5] + " Knuddel und " + tokens[6] + " Rosen";
        client.increaseKnuddels(client.getKnuddels() - Integer.parseInt(tokens[5]));
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - Integer.parseInt(tokens[6])));
      }

      if (!tokens[7].trim().isEmpty()) {
        String nicknamen = KCodeParser.escape(tokens[7]);

        boolean online = true;
        Client target = Server.get().getClient(nicknamen);
        if (target == null) {
          online = false;
          target = new Client(null);
          target.loadStats(nicknamen);
        }

        nicknamen = target.getName();

        if (client == target) {
          client.sendButlerMessage(client.getChannel().getName(), "Du kannst doch nicht mit dir selbst spielen.");
          return;
        }
        if ((!client.getChannel().getClients().contains(target)) || (!online)) {
          client.sendButlerMessage(client.getChannel().getName(), "Der von dir gewählte Gegner " + tokens[7] + " ist momentan nicht im Channel online.");
          return;
        }
        if (Integer.parseInt(target.getRosensperre()) < Integer.parseInt(tokens[6])) {
          client.sendButlerMessage(client.getChannel().getName(), tokens[7] + " verfügt nicht über genug Rosen, um mit dir zu spielen.");
          return;
        }

        if (target.getKnuddels() / 2 < Integer.parseInt(tokens[5])) {
          client.sendButlerMessage(client.getChannel().getName(), tokens[7] + " verfügt nicht über genügend Knuddels, um mit dir zu spielen.");
          return;
        }

        target.sendButlerMessage(client.getChannel().getName(), "°>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<° bietet dir eine Runde °RR22°_Dicen_ °r°an. _°>{button}Mitspielen||call|/game join:" + client.getName() + "<°_ oder  _°>ablehnen|/game deny:" + client.getName() + "<°_. (_°>" + tokens[8] + " Runden, Einsatz: " + einsatz + "|/game details:" + client.getName() + "<°_)°°");
        client.sendButlerMessage(client.getChannel().getName(), "Ich habe " + target.getName() + " soeben dein Spielangebot unterbreitet.");

        if (tokens[3].equals("Ja"))
          tokens[3] = "1";
        else {
          tokens[3] = "0";
        }
        if (tokens[4].equals("Ja"))
          tokens[4] = "1";
        else {
          tokens[4] = "0";
        }

        Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
        query.insert("insert into game_dicen set ip='" + client.getIPAddress() + "',channel='" + client.getChannel().getName() + "',start='" + time + "', von='" + client.getName() + "', einsatz_knuddel='" + tokens[5] + "', aktuplayer='', einsatz_rosen='" + tokens[6] + "', runden='" + tokens[8] + "', vorsprung='" + tokens[8] + "', public='" + tokens[3] + "', offen='" + tokens[4] + "', gegner='" + target.getName() + "', akzept='0'");

        return;
      }
      Random zufall = new Random();
      String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];

      if (tokens[3].equals("Ja"))
        tokens[3] = "1";
      else {
        tokens[3] = "0";
      }
      if (tokens[4].equals("Ja"))
        tokens[4] = "1";
      else {
        tokens[4] = "0";
      }

      client.getChannel().broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> °>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<° bietet eine Runde °RR22°_Dicen_§°BB° an. °>{button}Mitspielen||call|/game join:" + client.getName() + "<° (_°>" + tokens[8] + " Runden, Einsatz: " + einsatz + "|/game details:" + client.getName() + "<°_)", new Object[0]));
      Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
      query.insert("insert into game_dicen set ip='" + client.getIPAddress() + "',aktuplayer='',channel='" + client.getChannel().getName() + "',start='" + time + "', von='" + client.getName() + "', einsatz_knuddel='" + tokens[5] + "', einsatz_rosen='" + tokens[6] + "', runden='" + tokens[8] + "', vorsprung='" + tokens[8] + "', public='" + tokens[3] + "', offen='" + tokens[4] + "', gegner='', akzept='0'");
    }

    if (tokens[1].equals("darten"))
    {
      if ((!isInteger(tokens[6])) || (!isInteger(tokens[7])) || ((Integer.parseInt(tokens[6]) == 0) && (Integer.parseInt(tokens[7]) == 0))) {
        client.sendButlerMessage(client.getChannel().getName(), "Du musst Rosen oder Knuddel setzen, um das Spiel zu starten.");
        return;
      }if (client.getKnuddels() / 2 < Integer.parseInt(tokens[6])) {
        client.sendButlerMessage(client.getChannel().getName(), "Du verfügst nicht über genug Knuddel, um dieses Spiel zu starten. (max. 50% der eigenen Knuddel)");
        return;
      }if (Integer.parseInt(tokens[9]) >= 100001) {
        client.sendButlerMessage(client.getChannel().getName(), "Die Start-Zahl darf höchstens 100.000 sein.");
        return;
      }if (Integer.parseInt(tokens[9]) <= 99) {
        client.sendButlerMessage(client.getChannel().getName(), "Die Start-Zahl muss mindestens 100 sein.");
        return;
      }if (Integer.parseInt(client.getRosensperre()) < Integer.parseInt(tokens[7])) {
        client.sendButlerMessage(client.getChannel().getName(), "Du verfügst nicht über genug Rosen, um dieses Spiel zu starten.");
        return;
      }

      String einsatz = "";
      if ((!tokens[6].equals("0")) && (tokens[7].equals("0"))) {
        einsatz = tokens[6] + " Knuddel";
        client.increaseKnuddels(client.getKnuddels() - Integer.parseInt(tokens[6]));
      }
      else if ((!tokens[7].equals("0")) && (tokens[6].equals("0"))) {
        einsatz = tokens[7] + " Rosen";
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - Integer.parseInt(tokens[6])));
      }
      else {
        einsatz = tokens[6] + " Knuddel und " + tokens[7] + " Rosen";
        client.increaseKnuddels(client.getKnuddels() - Integer.parseInt(tokens[6]));
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - Integer.parseInt(tokens[7])));
      }

      if (!tokens[8].trim().isEmpty()) {
        String nicknamen = KCodeParser.escape(tokens[8]);

        boolean online = true;
        Client target = Server.get().getClient(nicknamen);
        if (target == null) {
          online = false;
          target = new Client(null);
          target.loadStats(nicknamen);
        }

        nicknamen = target.getName();

        if (client == target) {
          client.sendButlerMessage(client.getChannel().getName(), "Du kannst doch nicht mit dir selbst spielen.");
          return;
        }
        if ((!client.getChannel().getClients().contains(target)) || (!online)) {
          client.sendButlerMessage(client.getChannel().getName(), "Der von dir gewählte Gegner " + tokens[8] + " ist momentan nicht im Channel online.");
          return;
        }
        if (Integer.parseInt(target.getRosensperre()) < Integer.parseInt(tokens[6])) {
          client.sendButlerMessage(client.getChannel().getName(), tokens[8] + " verfügt nicht über genug Rosen, um mit dir zu spielen.");
          return;
        }

        if (target.getKnuddels() / 2 < Integer.parseInt(tokens[5])) {
          client.sendButlerMessage(client.getChannel().getName(), tokens[8] + " verfügt nicht über genügend Knuddels, um mit dir zu spielen.");
          return;
        }
        String nachwurf = "";
        if (tokens[5].equals("1")) {
          nachwurf = " Nachwurf aktiv,";
        }
        target.sendButlerMessage(client.getChannel().getName(), "°>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<° bietet dir eine Runde °RR22°_Darten_ °r°an. _°>{button}Mitspielen||call|/game join:" + client.getName() + "<°_ oder  _°>ablehnen|/game deny:" + client.getName() + "<°_. (_°>Startzahl: " + tokens[9] + "," + nachwurf + " Einsatz: " + einsatz + "|/game details:" + client.getName() + "<°_)°°");
        client.sendButlerMessage(client.getChannel().getName(), "Ich habe " + target.getName() + " soeben dein Spielangebot unterbreitet.");

        if (tokens[3].equals("Ja"))
          tokens[3] = "1";
        else {
          tokens[3] = "0";
        }
        if (tokens[4].equals("Ja"))
          tokens[4] = "1";
        else {
          tokens[4] = "0";
        }
        if (tokens[5].equals("Ja"))
          tokens[5] = "1";
        else {
          tokens[5] = "0";
        }

        Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
        query.insert("insert into game_darten set ip='" + client.getIPAddress() + "',channel='" + client.getChannel().getName() + "',start='" + time + "', von='" + client.getName() + "', einsatz_knuddel='" + tokens[6] + "', aktuplayer='', einsatz_rosen='" + tokens[7] + "',`public`='" + tokens[3] + "', offen='" + tokens[4] + "',nachwurf='" + tokens[5] + "', gegner='" + target.getName() + "', akzept='0', startzahl='" + tokens[9] + "', startplayer1='" + tokens[9] + "', startplayer2='" + tokens[9] + "'");

        return;
      }
      Random zufall = new Random();
      String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];

      if (tokens[3].equals("Ja"))
        tokens[3] = "1";
      else {
        tokens[3] = "0";
      }
      if (tokens[4].equals("Ja"))
        tokens[4] = "1";
      else {
        tokens[4] = "0";
      }
      if (tokens[5].equals("Ja"))
        tokens[5] = "1";
      else {
        tokens[5] = "0";
      }
      String nachwurf = "";
      if (tokens[5].equals("1")) {
        nachwurf = " Nachwurf aktiv,";
      }
      client.getChannel().broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> °>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<° bietet eine Runde °RR22°_Darten_§°BB° an. °>{button}Mitspielen||call|/game join:" + client.getName() + "<° (_°>Startzahl: " + tokens[9] + "," + nachwurf + " Einsatz: " + einsatz + "|/game details:" + client.getName() + "<°_)", new Object[0]));
      Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
      query.insert("insert into game_darten set ip='" + client.getIPAddress() + "',channel='" + client.getChannel().getName() + "',start='" + time + "', von='" + client.getName() + "', einsatz_knuddel='" + tokens[6] + "', aktuplayer='', einsatz_rosen='" + tokens[7] + "',public='" + tokens[3] + "', offen='" + tokens[4] + "',nachwurf='" + tokens[5] + "', gegner='', akzept='0', startzahl='" + tokens[9] + "', startplayer1='" + tokens[9] + "', startplayer2='" + tokens[9] + "'");
    }

    if (tokens[1].equals("freidiffen"))
    {
      if ((!isInteger(tokens[4])) || (!isInteger(tokens[5])) || ((Integer.parseInt(tokens[4]) == 0) && (Integer.parseInt(tokens[5]) == 0))) {
        client.sendButlerMessage(client.getChannel().getName(), "Du musst Rosen oder Knuddel setzen, um das Spiel zu starten.");
        return;
      }if (client.getKnuddels() / 2 < Integer.parseInt(tokens[4])) {
        client.sendButlerMessage(client.getChannel().getName(), "Du verfügst nicht über genug Knuddel, um dieses Spiel zu starten. (max. 50% der eigenen Knuddel)");
        return;
      }if ((!isInteger(tokens[7])) || (Integer.parseInt(tokens[7]) <= 0)) {
        client.sendButlerMessage(client.getChannel().getName(), "Du musst mindestens einen Würfel auswählen.");
        return;
      }if (Integer.parseInt(tokens[7]) >= 26) {
        client.sendButlerMessage(client.getChannel().getName(), "Du kannst maximal 25 Würfel auswählen.");
        return;
      }if (Integer.parseInt(client.getRosensperre()) < Integer.parseInt(tokens[5])) {
        client.sendButlerMessage(client.getChannel().getName(), "Du verfügst nicht über genug Rosen, um dieses Spiel zu starten.");
        return;
      }if ((Integer.parseInt(tokens[4]) >= 1) && (Integer.parseInt(tokens[5]) >= 1)) {
        client.sendButlerMessage(client.getChannel().getName(), "Beim Spiel Freidiffen kannst du nur um Knuddels _oder_ Rosen spielen.");
        return;
      }if (((Integer.parseInt(tokens[4]) >= 1) && (Integer.parseInt(tokens[4]) % Integer.parseInt(tokens[7]) != 0)) || (Integer.parseInt(tokens[4]) == 1)) {
        client.sendButlerMessage(client.getChannel().getName(), "Die Anzahl der Knuddels muss durch die Anzahl der ausgewählten Würfel teilbar sein. (Beispiel: 10 Knuddels => 5 Würfel, 2 Würfel oder 1 Würfel)");
        return;
      }if (((Integer.parseInt(tokens[5]) >= 1) && (Integer.parseInt(tokens[5]) % Integer.parseInt(tokens[7]) != 0)) || (Integer.parseInt(tokens[5]) == 1)) {
        client.sendButlerMessage(client.getChannel().getName(), "Die Anzahl der Rosen muss durch die Anzahl der ausgewählten Würfel teilbar sein. (Beispiel: 10 Rosen => 5 Würfel, 2 Würfel oder 1 Würfel)");
        return;
      }
      String einsatz = "";
      if ((!tokens[4].equals("0")) && (tokens[5].equals("0"))) {
        einsatz = tokens[4] + " Knuddel";
        client.increaseKnuddels(client.getKnuddels() - Integer.parseInt(tokens[4]));
      }
      else if ((!tokens[5].equals("0")) && (tokens[4].equals("0"))) {
        einsatz = tokens[5] + " Rosen";
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - Integer.parseInt(tokens[5])));
      }
      else {
        einsatz = tokens[4] + " Knuddel und " + tokens[5] + " Rosen";
        client.increaseKnuddels(client.getKnuddels() - Integer.parseInt(tokens[4]));
        client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - Integer.parseInt(tokens[5])));
      }

      if (!tokens[6].trim().isEmpty()) {
        String nicknamen = KCodeParser.escape(tokens[6]);

        boolean online = true;
        Client target = Server.get().getClient(nicknamen);
        if (target == null) {
          online = false;
          target = new Client(null);
          target.loadStats(nicknamen);
        }

        nicknamen = target.getName();

        if (client == target) {
          client.sendButlerMessage(client.getChannel().getName(), "Du kannst doch nicht mit dir selbst spielen.");
          return;
        }
        if ((!client.getChannel().getClients().contains(target)) || (!online)) {
          client.sendButlerMessage(client.getChannel().getName(), "Der von dir gewählte Gegner " + tokens[6] + " ist momentan nicht im Channel online.");
          return;
        }
        if (Integer.parseInt(target.getRosensperre()) < Integer.parseInt(tokens[5])) {
          client.sendButlerMessage(client.getChannel().getName(), tokens[6] + " verfügt nicht über genug Rosen, um mit dir zu spielen.");
          return;
        }

        if (target.getKnuddels() / 2 < Integer.parseInt(tokens[4])) {
          client.sendButlerMessage(client.getChannel().getName(), tokens[6] + " verfügt nicht über genügend Knuddels, um mit dir zu spielen.");
          return;
        }

        int würfel = 0;
        if (!tokens[4].equals("0"))
          würfel = Integer.parseInt(tokens[4]) / Integer.parseInt(tokens[7]);
        else {
          würfel = Integer.parseInt(tokens[5]) / Integer.parseInt(tokens[7]);
        }

        target.sendButlerMessage(client.getChannel().getName(), "°>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<° bietet dir eine Runde °RR22°_Freidiffen_ °r°an. _°>{button}Mitspielen||call|/game join:" + client.getName() + "<°_ oder  _°>ablehnen|/game deny:" + client.getName() + "<°_. (_°>" + tokens[7] + "w" + würfel + "," + einsatz + "|/game details:" + client.getName() + "<°_)°°");
        client.sendButlerMessage(client.getChannel().getName(), "Ich habe " + target.getName() + " soeben dein Spielangebot unterbreitet.");

        if (tokens[3].equals("Ja"))
          tokens[3] = "1";
        else {
          tokens[3] = "0";
        }

        Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
        query.insert("insert into game_freidiffen set ip='" + client.getIPAddress() + "',channel='" + client.getChannel().getName() + "',start='" + time + "', von='" + client.getName() + "', einsatz_knuddel='" + tokens[4] + "', aktuplayer='', einsatz_rosen='" + tokens[5] + "',`public`='" + tokens[3] + "',gegner='" + target.getName() + "', akzept='0', anzahl='" + tokens[7] + "', wurf='" + tokens[7] + "w" + würfel + "'");

        return;
      }
      Random zufall = new Random();
      String farbe = wuerfelfarben[zufall.nextInt(wuerfelfarben.length)];

      if (tokens[3].equals("Ja"))
        tokens[3] = "1";
      else {
        tokens[3] = "0";
      }
      int würfel = 0;
      if (!tokens[4].equals("0"))
        würfel = Integer.parseInt(tokens[4]) / Integer.parseInt(tokens[7]);
      else {
        würfel = Integer.parseInt(tokens[5]) / Integer.parseInt(tokens[7]);
      }
      client.getChannel().broadcastAction(String.format("°>mychannel/cubes_prefix_%s...h_0.mx_-0.png<BB°", new Object[] { farbe }), String.format("> °>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<° bietet eine Runde °RR22°_Freidiffen_§°BB° an. °>{button}Mitspielen||call|/game join:" + client.getName() + "<° (_°>" + tokens[7] + "w" + würfel + ", " + einsatz + "|/game details:" + client.getName() + "<°_)", new Object[0]));
      Long time = Long.valueOf(System.currentTimeMillis() / 1000L);
      query.insert("insert into game_freidiffen set ip='" + client.getIPAddress() + "',channel='" + client.getChannel().getName() + "',start='" + time + "', von='" + client.getName() + "', einsatz_knuddel='" + tokens[4] + "', aktuplayer='', einsatz_rosen='" + tokens[5] + "',public='" + tokens[3] + "', gegner='', akzept='0', anzahl='" + tokens[7] + "', wurf='" + tokens[7] + "w" + würfel + "'");
    }
  }
}
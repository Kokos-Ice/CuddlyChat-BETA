package fifty;

import fifty.free.fiftyfree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.DiceCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.query;

public class fiftywuerfeln
{
  private static int countChars(String input, String toCount)
  {
    int counter = 0;
    input = input.toUpperCase();
    input = input.replace("2W4", "4+4");
    input = input.replace("3W4", "4+4+4");
    input = input.replace("2W6", "6+6");
    input = input.replace("2W8", "8+8");
    input = input.replace("1W4", "4");
    input = input.replace("1W6", "6");
    input = input.replace("1W8", "8");
    input = input.replace("W", "");
    String[] lol = input.split("\\+");
    for (String item : lol) {
      if (item.equals(toCount)) {
        counter++;
      }

    }

    return counter;
  }
  private static int countChars2(String input, String toCount) { int counter = 0;
    String[] lol = input.split(",");
    for (String item : lol) {
      if (item.equals(toCount)) {
        counter++;
      }

    }

    return counter;
  }

  public static void make(String arg, Client client, Channel channel, String spielart)
  {
    String dabei = "0";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fifty" + spielart + "dabei` where user = ? and ingame='1'");
      ps3.setString(1, client.getName());
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        dabei = "1";
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps3 != null)
        try {
          ps3.close();
        }
        catch (SQLException e)
        {
        }
      pcon3.close();
    }

    if (dabei.equals("0")) {
      client.sendButlerMessage(channel.getName(), "Du nimmst nicht an _Fifty!_ teil.");
      return;
    }
    if ((!fiftyfree.activ4) && (!fiftyfree.aktuuser.equals(client.getName()))) {
      client.sendButlerMessage(channel.getName(), "Du kannst noch nicht würfeln.");
      return;
    }

    List params4 = query.select("gewurfelt,user", "fiftyfreedabei", "user='" + client.getName() + "'");
    if ((((String)params4.get(0)).equals("1")) && (!fiftyfree.aktuuser.equals(client.getName()))) {
      client.sendButlerMessage(channel.getName(), "Du hast in dieser Runde bereits die Würfel geworfen.");
      return;
    }

    int fours = countChars(arg, "4");
    int sixs = countChars(arg, "6");
    int eights = countChars(arg, "8");
    int tens = countChars(arg, "10");
    int zws = countChars(arg, "12");
    int zwas = countChars(arg, "20");

    List paramsa = query.select("wurfel,wurfgesamt,user", "fifty" + spielart + "dabei", "user='" + client.getName() + "'");
    String haben = (String)paramsa.get(0);

    int fours2 = countChars2(haben, "4");
    int sixs2 = countChars2(haben, "6");
    int eights2 = countChars2(haben, "8");
    int tens2 = countChars2(haben, "10");
    int zws2 = countChars2(haben, "12");
    int zwas2 = countChars2(haben, "20");

    String args = arg.toUpperCase();
    args = args.replace("4W4", "LOL");
    args = args.replace("6W6", "LOL");
    args = args.replace("8W8", "LOL");
    args = args.replace("10W10", "LOL");
    args = args.replace("12W12", "LOL");
    args = args.replace("20W20", "LOL");
    args = args.replace("2W4", "");
    args = args.replace("3W4", "");
    args = args.replace("2W6", "");
    args = args.replace("2W8", "");
    args = args.replace("1W4", "");
    args = args.replace("1W6", "");
    args = args.replace("1W8", "");
    args = args.replace("1W10", "");
    args = args.replace("1W12", "");
    args = args.replace("1W20", "");
    args = args.replace("W", "");
    args = args.replace("4", "");
    args = args.replace("6", "");
    args = args.replace("8", "");
    args = args.replace("10", "");
    args = args.replace("12", "");
    args = args.replace("20", "");
    args = args.replace("+", "");
    if ((!args.isEmpty()) || (fours > fours2) || (sixs > sixs2) || (eights > eights2) || (tens > tens2) || (zws > zws2) || (zwas > zwas2)) {
      client.sendButlerMessage(channel.getName(), "Du hast mit " + arg.toUpperCase() + " zuviele Würfel geworfen (Zur Verfügung stehende Würfel: " + settings.getWuerfel(client.getName(), "free") + ").");
      return;
    }

    String variablen = DiceCreator.Würfeln(arg, 1);
    String[] teil = variablen.split("\\|");
    if (teil[5].equals("0")) {
      int gesamt = Integer.parseInt((String)paramsa.get(1)) + Integer.parseInt(teil[4]);
      String vt = "";
      int aktuwurf = 0;
      int stechenwurf = 0;

      if (spielart.equals("free")) {
        if (fiftyfree.runde == 1)
          aktuwurf = fiftyfree.wurf1;
        else if (fiftyfree.runde == 2)
          aktuwurf = fiftyfree.wurf2;
        else if (fiftyfree.runde == 3) {
          aktuwurf = fiftyfree.wurf3;
        }

        if (fiftyfree.stechzahl != 0) {
          stechenwurf = 1;
          aktuwurf = fiftyfree.stechzahl;
        }
      }

      if (Integer.parseInt(teil[4]) == aktuwurf) {
        vt = "(°BB°_Volltreffer!_§) ";
      }
      String out = "0";
      String more = "";
      if (Integer.parseInt(teil[4]) > aktuwurf) {
        more = ", überschreitet die Grenze " + aktuwurf + " und _°RR°scheidet kläglich aus_§.";
        query.update("update fifty" + spielart + "dabei set ingame='0' where user='" + client.getName() + "'");
        out = "1";
      } else {
        query.update("update fifty" + spielart + "dabei set wurfgesamt=wurfgesamt+'" + teil[4] + "' where user='" + client.getName() + "'");

        int platz = 0;
        int placesi = 0;
        PoolConnection pcon2 = ConnectionPool.getConnection();
        PreparedStatement ps2 = null;
        try
        {
          Connection con2 = pcon2.connect();
          ps2 = con2.prepareStatement("SELECT * FROM fifty" + spielart + "dabei where ingame = '1' order by wurfgesamt desc");
          ResultSet rs2 = ps2.executeQuery();
          while (rs2.next()) {
            platz++;
            if (rs2.getString("user").equals(client.getName()))
              placesi = platz;
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        } finally {
          if (ps2 != null)
            try {
              ps2.close();
            }
            catch (SQLException e)
            {
            }
          pcon2.close();
        }

        if (placesi == 1) {
          more = " " + vt + "und kommt damit insgesamt auf_ " + gesamt + "_. (°BB°_" + placesi + "_°r°. Platz)";
        }
        else {
          more = " " + vt + "und kommt damit insgesamt auf_ " + gesamt + "_. (" + placesi + ". Platz)";
        }
      }
      if (stechenwurf == 1)
      {
        String more2 = "";
        if (channel.getName().equals("Fifty!")) {
          if (Integer.parseInt(teil[4]) <= fiftyfree.stechenwurf) {
            more2 = ", hätte aber mind " + fiftyfree.stechenwurf + " werden müssen ^^";
            fiftyfree.WurfOut(client.getName(), channel, client);
          } else if (Integer.parseInt(teil[4]) < fiftyfree.stechzahl) {
            more2 = ", das ist die °BB°_Führung!_";
            fiftyfree.stechenwurf = Integer.parseInt(teil[4]);

            if (client.getName().equals(fiftyfree.getLastUser()))
            {
              fiftyfree.WinStech(client.getName(), client, channel);
            }
            else fiftyfree.NextPlayer(client, channel);

            fiftyfree.stechenwurf = Integer.parseInt(teil[4]);
          } else if (Integer.parseInt(teil[4]) == fiftyfree.stechzahl) {
            more2 = ", augenblicklich geschafft.";
            fiftyfree.WinStech(client.getName(), client, channel);
          }

        }

        channel.broadcastAction(client.getName(), String.format("würfelt %s: %s = _%s_" + more2, new Object[] { teil[2], teil[3], teil[4] }));
      } else {
        channel.broadcastAction(client.getName(), String.format("würfelt %s: %s = _%s_" + more, new Object[] { teil[2], teil[3], teil[4] }));
      }

      if (fiftyfree.getPlaces().trim().isEmpty()) {
        settings.Ende("free", channel);
        return;
      }
      query.update("update fifty" + spielart + "dabei set gewurfelt='1' where user='" + client.getName() + "'");

      if ((fiftyfree.runde == 3) && (gesamt == 50))
      {
        Object[] lol = settings.getFiftyPoints("free", fiftyfree.getDabei());
        String place1 = lol[0].toString();
        String place2 = lol[1].toString();
        String place3 = lol[2].toString();
        int rofl1 = Integer.parseInt(place1.replace(".0", ""));
        int rofl2 = Integer.parseInt(place2.replace(".0", ""));
        int rofl3 = Integer.parseInt(place3.replace(".0", ""));
        int gesamts = rofl1 + rofl2 + rofl3;
        int alle = gesamts + settings.getJP(spielart);
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°BB26°_" + client.getName() + " hat es geschafft und hat den Jackpot geknackt!!!_°r°§###Das Spiel endet sofort und _" + client.getName() + "_ erhält _" + alle + " Fifty! Punkte_ (" + gesamts + " Fifty! Punkte aus dem Spiel und " + settings.getJP(spielart) + " Fifty-Punkte aus dem Jackpot).", new Object[0]));

        client.increaseFiftyPoints(alle);
        settings.Ende2("free", channel);
      }

      settings.Check("free", channel);
    }
    else {
      client.sendButlerMessage(channel.getName(), String.format("%s", new Object[] { teil[2] }));
    }

    if (fours == 1) {
      if (fours2 == 3) {
        haben = haben.replace("4,4,4", "4,4");
      }
      if (fours2 == 2) {
        haben = haben.replace("4,4", "4");
      }
      if (fours2 == 1)
        haben = haben.replace("4", "");
    }
    else if (fours == 2) {
      if (fours2 == 3) {
        haben = haben.replace("4,4,4", "4");
      }
      if (fours2 == 2)
        haben = haben.replace("4,4", "");
    }
    else if ((fours == 3) && (fours2 == 3))
    {
      haben = haben.replace("4,4,4", "");
    }

    if (sixs == 1) {
      if (sixs2 == 2) {
        haben = haben.replace("6,6", "6");
      }
      if (sixs2 == 1)
        haben = haben.replace("6", "");
    }
    else if ((sixs == 2) && (sixs2 == 2))
    {
      haben = haben.replace("6,6", "");
    }

    if (eights == 1) {
      if (eights2 == 2) {
        haben = haben.replace("8,8", "8");
      }
      if (eights2 == 1)
        haben = haben.replace("8", "");
    }
    else if ((eights == 2) && (eights2 == 2))
    {
      haben = haben.replace("8,8", "");
    }

    if (tens == 1) {
      haben = haben.replace("10", "");
    }
    if (zws == 1) {
      haben = haben.replace("12", "");
    }
    if (zwas == 1) {
      haben = haben.replace("20", "");
    }

    query.update("update fifty" + spielart + "dabei set wurfel='" + haben + "' where user='" + client.getName() + "'");
  }
}
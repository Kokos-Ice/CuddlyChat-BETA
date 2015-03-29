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
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.query;

public class settings
{
  private static int countChars(String input, String toCount)
  {
    int counter = 0;

    String[] lol = input.split(",");
    for (String item : lol) {
      if (item.equals(toCount)) {
        counter++;
      }
    }

    return counter;
  }

  public static void Check(String spielart, Channel channel) {
    int alle = 1;
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fifty" + spielart + "dabei` where gewurfelt='0' and ingame='1'");
      ResultSet rs3 = ps3.executeQuery();

      while (rs3.next())
      {
        alle = 0;
      }
    }
    catch (SQLException e) {
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

    if (alle == 1) {
      fiftyfree.activ5 = false;
      fiftyfree.activ6 = true;
    }
  }

  public static void Ende(String spielart, Channel channel) {
    if (spielart.contains("free")) {
      Object[] lol = getFiftyPoints("free", fiftyfree.getDabei());
      String place1 = lol[0].toString();
      String place2 = lol[1].toString();
      String place3 = lol[2].toString();
      int rofl1 = Integer.parseInt(place1.replace(".0", ""));
      int rofl2 = Integer.parseInt(place2.replace(".0", ""));
      int rofl3 = Integer.parseInt(place3.replace(".0", ""));

      int gesamt = rofl1 + rofl2 + rofl3;
      query.count("update fiftyjackpots set free=free+'" + gesamt + "'");
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format(" °RR20°_Fifty! endet ohne Sieger!_#§°r°Alle Spieler sind vorab ausgeschieden, deshalb wandern " + gesamt + " Fifty! Punkte in den Jackpot.", new Object[0]));

      fiftyfree.runde = 1;
      fiftyfree.wurf1 = 0;
      fiftyfree.wurf2 = 0;
      fiftyfree.wurf3 = 0;
      fiftyfree.activ = false;
      fiftyfree.activ2 = false;
      fiftyfree.activ3 = false;
      fiftyfree.activ4 = false;
      fiftyfree.activ5 = false;
      fiftyfree.activ6 = false;
      fiftyfree.activ7 = false;
      query.truncate("truncate table fiftyfreedabei");
    }
  }

  public static void Ende2(String spielart, Channel channel) {
    if (spielart.contains("free"))
    {
      query.count("update fiftyjackpots set free=free+'0'");

      fiftyfree.runde = 1;
      fiftyfree.wurf1 = 0;
      fiftyfree.wurf2 = 0;
      fiftyfree.wurf3 = 0;
      fiftyfree.activ = false;
      fiftyfree.activ2 = false;
      fiftyfree.activ3 = false;
      fiftyfree.activ4 = false;
      fiftyfree.activ5 = false;
      fiftyfree.activ6 = false;
      fiftyfree.activ7 = false;
      query.truncate("truncate table fiftyfreedabei");
    }
  }

  public int getMinPoints(String spielart)
  {
    int min = 0;

    if (spielart.equals("free"))
      min = 0;
    else if (spielart.equals("normal"))
      min = 0;
    else if (spielart.equals("pro"))
      min = 2500;
    else if (spielart.equals("bonzen"))
      min = 5000;
    else if (spielart.equals("big")) {
      min = 0;
    }

    return min;
  }

  public static int getJP(String spielart)
  {
    int jp = 0;
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyjackpots`");
      ResultSet rs3 = ps3.executeQuery();

      while (rs3.next())
      {
        jp = rs3.getInt(spielart);
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
    return jp;
  }
  public int getEinsatz(String spielart) {
    int einsatz = 0;

    if (spielart.equals("free"))
      einsatz = 0;
    else if (spielart.equals("normal"))
      einsatz = 1;
    else if (spielart.equals("pro"))
      einsatz = 5;
    else if (spielart.equals("bonzen"))
      einsatz = 25;
    else if (spielart.equals("big")) {
      einsatz = 101;
    }
    return einsatz;
  }
  public static String getWuerfel(String name, String spielart) {
    String wuerfel = "";
    if (spielart.equals("free")) {
      List params = query.select("wurfel,user", "fiftyfreedabei", "user='" + name + "'");

      String haben = (String)params.get(0);

      int vierer = countChars(haben, "4");
      int secher = countChars(haben, "6");
      int achter = countChars(haben, "8");
      int zehner = countChars(haben, "10");
      int zwolfer = countChars(haben, "12");
      int zwaner = countChars(haben, "20");
      if (vierer == 3) {
        wuerfel = wuerfel + "3W4 + ";
      }
      if (vierer == 2) {
        wuerfel = wuerfel + "2W4 + ";
      }
      if (vierer == 1) {
        wuerfel = wuerfel + "W4 + ";
      }
      if (secher == 2) {
        wuerfel = wuerfel + "2W6 + ";
      }
      if (secher == 1) {
        wuerfel = wuerfel + "W6 + ";
      }
      if (achter == 1) {
        wuerfel = wuerfel + "W8 + ";
      }
      if (achter == 2) {
        wuerfel = wuerfel + "2W8 + ";
      }
      if (zehner == 1) {
        wuerfel = wuerfel + "W10 + ";
      }
      if (zwolfer == 1) {
        wuerfel = wuerfel + "W12 + ";
      }
      if (zwaner == 1) {
        wuerfel = wuerfel + "W20 + ";
      }
    }

    if (!wuerfel.isEmpty())
      wuerfel = wuerfel.substring(0, wuerfel.length() - 3);
    else {
      wuerfel = "°r°";
    }
    return wuerfel;
  }

  public static Object[] getFiftyPoints(String spielart, double player) {
    Object[] data = new Object[3];

    double first = 0.0D;
    double second = 0.0D;
    double third = 0.0D;

    if (spielart.equals("free")) {
      first = Math.ceil(player / 2.0D);

      second = Math.ceil(first / 2.0D + 1.0D);

      if (second == first) {
        second -= 1.0D;
      }
      third = player - first - second;

      data[0] = Double.valueOf(first);
      data[1] = Double.valueOf(second);
      data[2] = Double.valueOf(third);
    }

    return data;
  }
}
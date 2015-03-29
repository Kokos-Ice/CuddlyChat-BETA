package fifty;

import fifty.free.fiftyfree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import starlight.Channel;
import starlight.Client;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.query;

public class fiftyanmeldung
{
  public static void make(Client client, Channel channel)
  {
    if ((channel.getName().equals("Fifty!")) && (fiftyfree.activ == true))
    {
      if (fiftyfree.activ2 == true)
      {
        client.sendButlerMessage(channel.getName(), "Leider ist die _Anmeldezeit vorbei_, warte aufs nächste Spiel.");

        return;
      }
    /*  if (client.checkGl() == 1) {
        client.sendButlerMessage(channel.getName(), "Du bist noch bis zum _" + client.Glbis() + "_ für alle Spiele gesperrt.");
        return;
      }*/

      String dabeiuser = "";
      String dabei2 = "0";
      PoolConnection pcon3a = ConnectionPool.getConnection();
      PreparedStatement ps3a = null;
      try
      {
        Connection cona = pcon3a.connect();
        ps3a = cona.prepareStatement("SELECT * FROM `fiftyfreedabei` where ip = ?");
        ps3a.setString(1, client.getIPAddress());
        ResultSet rs3a = ps3a.executeQuery();
        while (rs3a.next()) {
          dabeiuser = rs3a.getString("user");
          dabei2 = "1";
        }

      }
      catch (SQLException e)
      {
        e.printStackTrace();
      } finally {
        if (ps3a != null)
          try {
            ps3a.close();
          }
          catch (SQLException e)
          {
          }
        pcon3a.close();
      }

      String dabei = "0";
      PoolConnection pcon3 = ConnectionPool.getConnection();
      PreparedStatement ps3 = null;
      try
      {
        Connection con = pcon3.connect();
        ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where user = ?");
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

      if (dabei.equals("1")) {
        client.sendButlerMessage(channel.getName(), "Du bist bereits bei Fifty! angemeldet!");

        return;
      }

      if (dabei2.equals("1")) {
        client.sendButlerMessage(channel.getName(), String.format("_Teilnahme leider nicht möglich_, da °>_h%s|/pp %s|/w %s<° die selbe IP hat.", new Object[] { dabeiuser, dabeiuser, dabeiuser }));
        return;
      }

      client.sendButlerMessage(channel.getName(), "Du bist nun _für Fifty! angemeldet_.");
      query.insert("insert into fiftyfreedabei set user='" + client.getName() + "', ip='" + client.getIPAddress() + "',wurfel='4,4,4,8,8,10,12,20'");
    }
    else
    {
      client.sendButlerMessage(channel.getName(), String.format("In diesem Channel läuft aktuell _kein Fifty!_", new Object[0]));
    }
  }
}
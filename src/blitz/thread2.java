package blitz;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.KCodeParser;
import tools.Toolbar;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class thread2 extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
    for (int i = 0; i < 70; i++) {
      try {
        if (blitz.activ == true)
          sleep(50L);
        else {
          return;
        }

      }
      catch (InterruptedException e)
      {
      }

    }

    String userid = "";
    String bonususer = "";
    String runde = "";
    String zahl = "";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `blitzsettings`");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        userid = rs3.getString("aktuell");
        runde = rs3.getString("runde");

        zahl = rs3.getString("zahl");
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
    String user = "";
    PoolConnection pcon4 = ConnectionPool.getConnection();
    PreparedStatement ps4 = null;
    try
    {
      Connection con4 = pcon4.connect();
      ps4 = con4.prepareStatement("SELECT * FROM `blitzdabei` where id = ?");
      ps4.setString(1, userid);
      ResultSet rs4 = ps4.executeQuery();
      while (rs4.next())
      {
        user = rs4.getString("user");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps4 != null)
        try {
          ps4.close();
        }
        catch (SQLException e)
        {
        }
      pcon4.close();
    }

    Random randombo = new Random();
    int bonus = randombo.nextInt(3);

    if ((runde.equals("2")) && (bonus == 1))
    {
      Random randombozahl = new Random();
      int zahl3 = randombozahl.nextInt(10);
      zahl3++;
      String userbonus = "";

      PoolConnection pconbon = ConnectionPool.getConnection();
      PreparedStatement psbon = null;
      try
      {
        Connection conbon = pconbon.connect();
        psbon = conbon.prepareStatement("SELECT * FROM `blitzdabei` order by rand();");
        ResultSet rsbon = psbon.executeQuery();
        while (rsbon.next())
        {
          userbonus = rsbon.getString("user");
        }

      }
      catch (SQLException e)
      {
        e.printStackTrace();
      } finally {
        if (psbon != null)
          try {
            psbon.close();
          }
          catch (SQLException e)
          {
          }
        pconbon.close();
      }

      String nickname2 = KCodeParser.escape(userbonus);

      boolean online2 = true;
      Client target2 = Server.get().getClient(nickname2);
      if (target2 == null) {
        online2 = false;
        target2 = new Client(null);
        target2.loadStats(nickname2);
      }
      
      
      Server.get().query("UPDATE `blitzsettings` SET `bonus` = '"+target2.getName()+"', `zahl3` = '"+zahl3+"'");

     

      blitz.bonuswurf = true;
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°BB18°_+++ BONUSRUNDE +++_##%s erhält nun die Möglichkeit in dieser Bonusrunde bis zu 1000 Knuddels zu gewinnen!##_§Es gilt die Zahl -°18RR° %s_§ - zu treffen!", new Object[] { target2.getName(), Integer.valueOf(zahl3) }));
      target2.sendButlerMessage(channel.getName(), "Sie haben für Ihren Wurf maximal 20 Sekunden Zeit! Gewürfelt wird mit _°BB°°>_h/blitz wurf|/blitz wurf<°_°r°.");

      for (int i = 0; i < 70; i++) {
        try {
          if (blitz.activ == true)
            sleep(200L);
          else {
            return;
          }
        }
        catch (InterruptedException e)
        {
        }
      }
    }

    PoolConnection pcon3df = ConnectionPool.getConnection();
    PreparedStatement ps3df = null;
    try
    {
      Connection condf = pcon3df.connect();
      ps3df = condf.prepareStatement("SELECT * FROM `blitzsettings`");
      ResultSet rs3df = ps3df.executeQuery();
      while (rs3df.next())
      {
        bonususer = rs3df.getString("bonus");
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps3df != null)
        try {
          ps3df.close();
        }
        catch (SQLException e)
        {
        }
      pcon3df.close();
    }

    if (!bonususer.isEmpty()) {
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°18BB°_%s_§ hat die Chance auf den Bonus leider verschlafen.", new Object[] { bonususer }));
      blitz.bonuswurf = false;
    }

    Server.get().query("UPDATE `blitzsettings` SET `bonus` = '', zahl3 = ''");
   
    String text = "";
    String text2 = "";
    String text3 = "";
    Random random = new Random();
    int texta = random.nextInt(3);
    int textb = random.nextInt(3);
    int textc = random.nextInt(4);
    blitz.wurfeln = true;
    if (textb == 0)
      text = String.format("°BB18°_Runde %s_§ beginnt...#Wer die Zahl -_ °18°°RR°%s_§ - würfelt, scheidet aus!", new Object[] { runde, zahl });
    else if (textb == 1)
      text = String.format("°BB18°_Runde %s_§ beginnt...#Die Zahl -_ °18°°RR°%s_§ - darf nicht gewürfelt werden.", new Object[] { runde, zahl });
    else if (textb == 2) {
      text = String.format("°BB18°_Runde %s_§ beginnt...#-_ °18°°RR°%s_§ - gilt es nicht zu treffen!", new Object[] { runde, zahl });
    }

     for(Client c : channel.getClients()) {
                List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Würfeln", "/sfc "+channel.getName()+":/blitz wurf", "", true));  
            c.send(Toolbar.ToolbarString(channel.getName(), buttons));
           c.send(Toolbar.BingoStyle(channel.getName()));
           }
    
    if (texta == 0)
      text2 = String.format("_°BB°°18°%s_§, du beginnst das Spiel!",  user );
    else if (texta == 1)
      text2 = String.format("Den ersten Zug hat _°BB°°18°%s_§!",  user );
    else if (texta == 2) {
      text2 = String.format("Es kann los gehen, _°BB°°18°%s_§!",  user);
    }

    if (textc == 0)
      text3 = String.format("Sie sind am Zug, °BB°°18°_%s_§.", new Object[] { user });
    else if (textc == 1)
      text3 = String.format("It's your turn, _°BB°°18°%s_§!", new Object[] { user });
    else if (textc == 2)
      text3 = String.format("Weiter gehts mit _°BB°°18°%s_§.", new Object[] { user });
    else if (textc == 3) {
      text3 = String.format("_°BB°°18°%s_§, du bist an der Reihe.", new Object[] { user });
    }

    if (runde.equals("1"))
      text = String.format("%s##%s", new Object[] { text, text2 });
    else {
      text = String.format("%s##%s", new Object[] { text, text3 });
    }

    Long zeit = Long.valueOf(System.currentTimeMillis() / 1000L);
    PoolConnection pcon2E = ConnectionPool.getConnection();
    PreparedStatement ps2E = null;
    try
    {
      Connection con2E = pcon2E.connect();
      ps2E = con2E.prepareStatement("UPDATE `blitzsettings` SET next = ?");
      ps2E.setLong(1, zeit.longValue());
      ps2E.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps2E != null)
        try {
          ps2E.close();
        }
        catch (SQLException e)
        {
        }
      pcon2E.close();
    }

  Server.get().query("truncate table blitzzahlen");
     // kürzer xD
     

    String nickname = KCodeParser.escape(user);

    boolean online = true;
    Client target = Server.get().getClient(nickname);
    if (target == null) {
      online = false;
      target = new Client(null);
      target.loadStats(nickname);
    }

    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { text }));
    target.sendButlerMessage(channel.getName(), "Sie haben für Ihren Wurf maximal 20 Sekunden Zeit! Gewürfelt wird mit _°BB°°>_h/blitz wurf|/blitz wurf<°_°r°.");

    blitz.activ2 = true;
  }
}
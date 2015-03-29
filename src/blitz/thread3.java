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
import tools.PacketCreator;
import tools.Toolbar;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class thread3 extends Thread
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
    String userfi = "";
    PoolConnection pcon4 = ConnectionPool.getConnection();
    PreparedStatement ps4 = null;
    try
    {
      Connection con4 = pcon4.connect();
      ps4 = con4.prepareStatement("SELECT * FROM `blitzdabei` where id = ?");
      ps4.setString(1, "1");
      ResultSet rs4 = ps4.executeQuery();
      while (rs4.next())
      {
        userfi = rs4.getString("user");
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
    Random random = new Random();
    int randomInt = random.nextInt(75);
    int zahl1 = randomInt + 1;
    int randomInt2 = random.nextInt(75);
    int zahl2 = randomInt2 + 1;

    String nickname = KCodeParser.escape(userfi);

    boolean online = true;
    Client target = Server.get().getClient(nickname);
    if (target == null) {
      online = false;
      target = new Client(null);
      target.loadStats(nickname);
    }

    Server.get().query("UPDATE `blitzsettings` SET zahl = '"+zahl1+"', zahl2 ='"+zahl2+"'");
   
    blitz.wurfeln = true;
    blitz.activ3 = true;
    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:",  Server.get().getButler().getName()), String.format("Damit sind wir schon beim_°BB°°18° Finale_°r°! Unser heutiger Finalist ist_°BB°°18° " + userfi + "_°r°.#Die Zahl -_°BB°°18° %s _§- gilt es zu treffen.#Dafür haben Sie _maximal 3 Versuche_ und eine _Zeitbegrenzung von einer Minute_.#Der Jackpot beträgt momentan °18RR°_" + blitz.getBlitzjp() + "_§ Blitz Punkte!#Bei der Zahl -_ °20°°BB°%s _°r°- gewinnst du einen _exklusiven_ Smiley  (°>ct/sm_ct_ehren-smiley-ani.gif<°).#_Viel Glück_!",  Integer.valueOf(zahl1), Integer.valueOf(zahl2)));
    target.sendButlerMessage(channel.getName(), "Sie haben für Ihre Würfe maximal 60 Sekunden Zeit! Gewürfelt wird mit _°BB°°>_h/blitz wurf|/blitz wurf<°_°r°.");

    for (int i = 0; i < 70; i++) {
      try {
        if (blitz.activ == true)
          sleep(600L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }
    }

    Random random2 = new Random();
    int randomInt3 = random2.nextInt(40);
    int jp = randomInt3 + 11;

    blitz.activ = false;
    blitz.activ2 = false;
    blitz.activ3 = false;
    for (Client c : channel.getClients()) {
      c.send(PacketCreator.removePrefixIcons(channel.getName()));
      
       List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/sfc "+channel.getName()+":/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            c.send(Toolbar.ToolbarString(channel.getName(), buttons));
           c.send(Toolbar.BingoStyle(channel.getName()));
    }
    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", Server.get().getButler().getName()), String.format("_°BB°°18°%s_§ hat sich zulange Zeit gelassen. °BB°°18°_%s Punkte_§ wandern in den Jackpot",  userfi, Integer.valueOf(jp)));
   
    Server.get().query("truncate table blitzdabei");
    Server.get().query("truncate table blitzzahlen");
    int neuerjp = blitz.getBlitzjp() + jp;
    Server.get().query("UPDATE `blitzsettings` SET runde='1', aktuell='1', next='', jackpot = '"+neuerjp+"'");
    
   
  }
}
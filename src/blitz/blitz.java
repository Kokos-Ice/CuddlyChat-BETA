package blitz;

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

public class blitz
{
  public static boolean activ = false;
  public static boolean activ2 = false;
  public static boolean activ3 = false;
  public static int dabei = 0;
  public static boolean bonuswurf = false;
  public static boolean wurfeln = false;

  public static void nextPlayer(String ausgeschieden, Channel channel, Client client) {
    int nextid = 0;
    String nextplayer = "";
    int aktuid = 0;
    int runde = 0;
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `blitzsettings`");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next()) {
        aktuid = rs3.getInt("aktuell");
        runde = rs3.getInt("runde");
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

    nextid = aktuid + 1;
    int nextuserid = 0;
    PoolConnection pcon4 = ConnectionPool.getConnection();
    PreparedStatement ps4 = null;
    try
    {
      Connection con4 = pcon4.connect();
      ps4 = con4.prepareStatement("SELECT * FROM `blitzdabei` where id = ?");
      ps4.setInt(1, nextid);
      ResultSet rs4 = ps4.executeQuery();
      while (rs4.next()) {
        nextplayer = rs4.getString("user");
        nextuserid = rs4.getInt("id");
      }
    }
    catch (SQLException e) {
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

    String aktus = "";
    PoolConnection pconrt = ConnectionPool.getConnection();
    PreparedStatement psrt = null;
    try
    {
      Connection conrt = pconrt.connect();
      psrt = conrt.prepareStatement("SELECT * FROM `blitzdabei` where id = ?");
      psrt.setInt(1, aktuid);
      ResultSet rsrt = psrt.executeQuery();
      while (rsrt.next()) {
        aktus = rsrt.getString("user");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (psrt != null)
        try {
          psrt.close();
        }
        catch (SQLException e)
        {
        }
      pconrt.close();
    }

    String firstplayer = "";
    PoolConnection pcon5 = ConnectionPool.getConnection();
    PreparedStatement ps5 = null;
    try
    {
      Connection con5 = pcon5.connect();
      ps5 = con5.prepareStatement("SELECT * FROM `blitzdabei` order by id asc limit 1");

      ResultSet rs5 = ps5.executeQuery();
      while (rs5.next())
        firstplayer = rs5.getString("user");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps5 != null)
        try {
          ps5.close();
        }
        catch (SQLException e)
        {
        }
      pcon5.close();
    }

    if (ausgeschieden.equals("0")) {
      String nun = "";
      int neuaktuell = 0;
      Long aktu = Long.valueOf(System.currentTimeMillis() / 1000L);
      if (nextuserid == 0) {
        nun = firstplayer;
        neuaktuell = 1;
      } else {
        nun = nextplayer;
        neuaktuell = nextid;
      }
      PoolConnection pcon2E = ConnectionPool.getConnection();
      PreparedStatement ps2E = null;
      try
      {
        Connection con2E = pcon2E.connect();
        ps2E = con2E.prepareStatement("UPDATE `blitzsettings` SET aktuell= ?, next = ?");
        ps2E.setInt(1, neuaktuell);
        ps2E.setLong(2, aktu.longValue());
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

      String nickname = KCodeParser.escape(nun);

      boolean online = true;
      Client target = Server.get().getClient(nickname);
      if (target == null) {
        online = false;
        target = new Client(null);
        target.loadStats(nickname);
      }

      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°RR°°18°_" + nun + "_§, du bist nun an der Reihe.", new Object[0]));
      target.sendButlerMessage(channel.getName(), "Sie haben für Ihren Wurf maximal 20 Sekunden Zeit! Gewürfelt wird mit _°BB°°>_h/blitz wurf|/blitz wurf<°_°r°.");
    }
    else
    {
      int dabei = 0;
      PoolConnection pconw = ConnectionPool.getConnection();
      PreparedStatement psw = null;
      try
      {
        Connection conw = pconw.connect();
        psw = conw.prepareStatement("SELECT COUNT(user) AS summe FROM `blitzdabei`");
        ResultSet rsw = psw.executeQuery();
        while (rsw.next())
        {
          dabei = rsw.getInt("summe");
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      } finally {
        if (psw != null)
          try {
            psw.close();
          }
          catch (SQLException e)
          {
          }
        pconw.close();
      }

      Random randomzu = new Random();
      int randomInt = randomzu.nextInt(15);
      int neuezahls = randomInt + 1;
      int neuerunde = runde + 1;
      PoolConnection pcon2E = ConnectionPool.getConnection();
      PreparedStatement ps2E = null;
      try {
        Connection con2E = pcon2E.connect();
        ps2E = con2E.prepareStatement("UPDATE `blitzsettings` SET `zahl` = ?, `next` = '', runde = ?");
        ps2E.setInt(1, neuezahls);
        ps2E.setInt(2, neuerunde);
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

      wurfeln = false;

      for (Client c : channel.getClients()) {
        List prefix = new ArrayList();
        prefix.add(aktus + "~");
        c.send(PacketCreator.showPrefixIcons(channel.getName(), prefix,true));
      }
      
      Server.get().query("delete from blitzdabei where user='"+aktus+"'");
    Server.get().query("ALTER TABLE blitzdabei DROP id");
Server.get().query("ALTER TABLE `blitzdabei` ADD `id` INT( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST");
   
Server.get().query("UPDATE `blitzsettings` set aktuell='1'");

    

      if (dabei == 2) {
        activ3 = true;
        thread3.client = client;
        thread3.channel = channel;
        Thread mixThread = new thread3();
        mixThread.start();
      }
      else {
        activ2 = true;
        thread2.client = client;
        thread2.channel = channel;
        Thread mixThread = new thread2();
        mixThread.start();
      }
    }
  }

  public static void startBlitz()
  {
    Random random = new Random();
    int randomInt = random.nextInt(14);

    randomInt++;
Server.get().query("UPDATE `blitzsettings` SET `zahl` = '"+randomInt+"'");
   
  }

  public static int getBlitzjp()
  {
    int jp = 0;

    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `blitzsettings`");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        jp = rs3.getInt("jackpot");
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
  public static int getDabei() {
    int dabei = 0;
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT COUNT(user) AS summe FROM `blitzdabei`");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        dabei = rs3.getInt("summe");
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
    return dabei;
  }

  public static String getPlayer() {
    String player = "";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `blitzdabei` order by user");
      ResultSet rs3 = ps3.executeQuery();
      int cC = 1;
      while (rs3.next()) {
        cC++;
        if (cC != 2) {
          player = player + ", ";
        }
        player = player + rs3.getString("user");
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
    return player;
  }

  
  
  public static void start(String message, Client client, Channel channel)
  {
      
    if (message.toLowerCase().contains(Server.get().getButler().getName().toLowerCase() + " blitz"))
    {
      if (!channel.getName().equals("Blitz!")) {
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), "Tut mir furchtbar leid, in diesem Channel gibt es kein _Blitz_, " + client.getName() + ".");

        return;
      }

      if ((activ == true) || (activ2 == true) || (activ3 == true))
      {
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("Blitz läuft bereits.", new Object[0]));
      }
      else
      {
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°RR°°20°_Zeit für eine neue Runde °BB°Blitz!_§ °>ct/sm_ct_ehren-smiley-ani.gif<°##Wer bei diesem Game teilnehmen möchte, gibt innerhalb 30 Sekunden _°BB>_h/blitz join|/blitz join<°_ °r°ein.", new Object[0]));
      
        activ = true;
        
for(Client a : channel.getClients()) {
         List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Anmelden", "/sfc "+channel.getName()+":/blitz join", "", true));  
            a.send(Toolbar.ToolbarString(channel.getName(), buttons));
            a.send(Toolbar.BingoStyle(channel.getName()));
}
        thread.client = client;
        thread.channel = channel;
        Thread mixThread = new thread();
        mixThread.start();
         
      }
    }
  }
}

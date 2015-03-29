package fifty.free;

import fifty.fiftywuerfeln;
import fifty.settings;
import java.io.PrintStream;
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

public class fiftyfree
{
  public static boolean activ = false;
  public static int runde = 1;
  public static int wurf1 = 0;
  public static int wurf2 = 0;
  public static int wurf3 = 0;
  public static boolean activ2 = false;
  public static boolean activ3 = false;
  public static boolean activ4 = false;
  public static boolean activ5 = false;
  public static boolean activ6 = false;
  public static boolean activ7 = false;
  public static boolean activ8 = false;
  public static boolean activ9 = false;
  public static int stechplace = 0;
  public static int stechzahl = 0;
  public static int stechuser = 0;
  public static int instechen = 0;
  public static boolean nextuser = false;
  public static String aktuuser = "";
  public static int stechenwurf = 1;

  private static int countChars2(String input, String toCount)
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

  public static void WinStech(String names, Client client, Channel channel)
  {
    query.update("update fiftyfreedabei set wurfel=''");
    query.update("update fiftyfreedabei set place=place+'1' where user != '" + names + "' and aus = '0' and place >= '" + stechplace + "'");

    activ9 = false;
    nextuser = false;
    activ7 = true;
    thread4.client = client;
    thread4.channel = channel;
    Thread mixThread = new thread4();
    mixThread.start();
  }

  public static void NextPlayer(Client client, Channel channel)
  {
    nextuser = true;
    activ8 = true;
    thread5.client = client;
    thread5.channel = channel;
    Thread mixThread = new thread5();
    mixThread.start();
  }

  public static void WurfOut(String namens, Channel channel, Client client) {
    System.out.print(namens);
    query.update("update fiftyfreedabei set place=place+'1', aus='1' where user='" + namens + "'");
    query.update("update fiftyfreedabei set wurfel=''");

    if (instechen == 3) {
      List params = query.select("user", "fiftyfreedabei", "place='" + stechplace + "' and ingame='1'");
      String name = (String)params.get(0);

      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_" + name + "_ gewinnt das Stechen um Platz " + stechplace + "!", new Object[0]));
      query.update("update fiftyfreedabei set wurfel=''");
      query.update("update fiftyfreedabei set place=place+'1' where user != '" + name + "' and aus = '0' and place >= '" + stechplace + "'");

      activ9 = false;
      nextuser = false;
      activ7 = true;
      thread4.client = client;
      thread4.channel = channel;
      Thread mixThread = new thread4();
      mixThread.start();
    }
    else {
      stechuser -= 1;
      instechen -= 1;

      nextuser = true;
      activ8 = true;
      thread5.client = client;
      thread5.channel = channel;
      Thread mixThread = new thread5();
      mixThread.start();
    }
  }

  public static String getLastUser()
  {
    String player = "";

    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where place='" + stechplace + "' and ingame='1' order by id");
      ResultSet rs3 = ps3.executeQuery();
      int cC = 0;
      while (rs3.next())
      {
        player = rs3.getString("user");

        cC++;
      }
    } catch (SQLException e) {
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
  public static String getAktuPlayer(int id) {
    String player = "";

    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where place='" + stechplace + "' and ingame='1'");
      ResultSet rs3 = ps3.executeQuery();
      int cC = 0;
      while (rs3.next())
      {
        if (id == cC) {
          player = rs3.getString("user");
        }
        cC++;
      }
    } catch (SQLException e) {
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
  public static String getPlayer() {
    String player = "";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` order by user");
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

  public static String getStechenUser()
  {
    String player = "";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where place='" + stechplace + "' and ingame='1'");
      ResultSet rs3 = ps3.executeQuery();
      int cC = 1;
      while (rs3.next()) {
        cC++;
        if (cC != 2) {
          player = player + ", ";
        }
        player = player + rs3.getString("user");
        instechen = cC;
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

    return player;
  }

  public static void restwuerfe() {
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where ingame = '1' and gewurfelt = '0' order by user");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        List paramsa = query.select("wurfel,wurfgesamt,user", "fiftyfreedabei", "user='" + rs3.getString("user") + "'");
        String haben = (String)paramsa.get(0);
        int zahl = 0;
        int fours2 = countChars2(haben, "4");
        int sixs2 = countChars2(haben, "6");
        int eights2 = countChars2(haben, "8");
        int tens2 = countChars2(haben, "10");
        int zws2 = countChars2(haben, "12");
        int zwas2 = countChars2(haben, "20");

        if (fours2 >= 1)
          zahl = 4;
        else if (sixs2 >= 1)
          zahl = 6;
        else if (eights2 >= 1)
          zahl = 8;
        else if (tens2 >= 1)
          zahl = 10;
        else if (zws2 >= 1)
          zahl = 12;
        else if (zwas2 >= 1) {
          zahl = 20;
        }
        Channel channel = Server.get().getChannel(KCodeParser.escape("Fifty! Free"));

        String nickname = KCodeParser.escape(rs3.getString("user"));

        boolean online = true;
        Client target = Server.get().getClient(nickname);
        if (target == null) {
          online = false;
          target = new Client(null);
          target.loadStats(nickname);
        }

        nickname = target.getName();

        fiftywuerfeln.make("" + zahl + "", target, channel, "free");
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
  }

  public static void sentWuerfel() {
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where ingame = '1' order by wurfgesamt desc");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        Client target = Server.get().getClient(rs3.getString("user"));

        if (target != null) {
          Channel channel = Server.get().getChannel(KCodeParser.escape("Fifty!"));

          target.sendButlerMessage(channel.getName(), "Deine Würfel: _" + settings.getWuerfel(target.getName(), "free") + "_ (Würfeln mit z.B. /d 4+20)");
        }
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
  }

  public static void sentPlace()
  {
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where ingame = '1' order by wurfgesamt desc limit 10");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next())
      {
        Client target = Server.get().getClient(rs3.getString("user"));
        if (target != null) {
          Channel channel = Server.get().getChannel(KCodeParser.escape("Fifty! Free"));

          if (rs3.getInt("place") <= 3) {
            target.sendButlerMessage(channel.getName(), "Du bist aktuell auf _°BB°Platz " + rs3.getString("place") + "_°r° (mit " + rs3.getString("wurfgesamt") + ").");
          }
          else
            target.sendButlerMessage(channel.getName(), "Du bist aktuell auf _Platz " + rs3.getString("place") + "_ (mit " + rs3.getString("wurfgesamt") + ").");
        }
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
  }

  public static void sentEnd(int winner)
  {
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where ingame = '1' order by place");
      ResultSet rs3 = ps3.executeQuery();
      int place = 0;
      while (rs3.next()) {
        place++;

        Client target = Server.get().getClient(rs3.getString("user"));
        if (target != null)
        {
          Channel channel = Server.get().getChannel(KCodeParser.escape("Fifty! Free"));

          if (place <= winner) {
            target.sendButlerMessage(channel.getName(), "Endergebnis: _Platz " + place + "_ (_°BB°Gewonnen!_°r°§) mit " + rs3.getString("wurfgesamt") + ".");
          }
          else {
            target.sendButlerMessage(channel.getName(), "Endergebnis: _Platz " + place + "_ mit " + rs3.getString("wurfgesamt") + ". Ich drück' dir die Daumen fürs nächste Spiel!");
          }
        }
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
  }

  public static String getPlaces()
  {
    String player = "";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where ingame = '1' order by wurfgesamt desc limit 10");
      ResultSet rs3 = ps3.executeQuery();
      int cC = 0;
      int lastpoints = 999;
      int lastplace = 0;
      while (rs3.next()) {
        if (lastpoints != rs3.getInt("wurfgesamt")) {
          cC++;
          lastpoints = rs3.getInt("wurfgesamt");
          player = player + cC + ".";
          lastplace = cC;
        }
        player = player + "°%%06°" + rs3.getString("user") + " (" + rs3.getString("wurfgesamt") + ")";
        if ((runde == 2) && (rs3.getInt("wurfgesamt") == wurf1 + wurf2))
        {
          player = player + " - °BB°_Jackpot-Chance!!!_°r°";
        }

        player = player + "°%%00°#";
        query.update("update fiftyfreedabei set place='" + lastplace + "' where user='" + rs3.getString("user") + "'");
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
    return player;
  }

  public static String getPlaces2()
  {
    String player = "";
    PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fiftyfreedabei` where ingame = '1' order by wurfgesamt desc");
      ResultSet rs3 = ps3.executeQuery();
      int cC = 0;
      int lastpoints = 999;
      while (rs3.next())
      {
        if (lastpoints != rs3.getInt("wurfgesamt")) {
          cC++;
          lastpoints = rs3.getInt("wurfgesamt");
          player = player + cC + ".";
        }
        player = player + "°%%06°" + rs3.getString("user") + " (" + rs3.getString("wurfgesamt") + ")°%%00°#";
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

  public static void setStechen()
  {
    Random randomzu = new Random();
    int randomInt = randomzu.nextInt(7);
    if (randomInt == 0)
      stechzahl = 5;
    else if (randomInt == 1)
      stechzahl = 10;
    else if (randomInt == 3)
      stechzahl = 40;
    else if (randomInt == 4)
      stechzahl = 20;
    else if (randomInt == 5)
      stechzahl = 25;
    else
      stechzahl = 8;
  }

  public static void setNumbers() {
    Random randomzu = new Random();
    int randomInt = randomzu.nextInt(9);
    if (randomInt == 0) {
      wurf1 = 5;
      wurf2 = 10;
      wurf3 = 35;
    } else if (randomInt == 1) {
      wurf1 = 4;
      wurf2 = 12;
      wurf3 = 34;
    } else if (randomInt == 2) {
      wurf1 = 20;
      wurf2 = 10;
      wurf3 = 20;
    } else if (randomInt == 3) {
      wurf1 = 10;
      wurf2 = 12;
      wurf3 = 28;
    } else if (randomInt == 4) {
      wurf1 = 14;
      wurf2 = 30;
      wurf3 = 6;
    } else if (randomInt == 5) {
      wurf1 = 5;
      wurf2 = 40;
      wurf3 = 5;
    } else if (randomInt == 6) {
      wurf1 = 7;
      wurf2 = 20;
      wurf3 = 23;
    } else if (randomInt == 7) {
      wurf1 = 12;
      wurf2 = 8;
      wurf3 = 30;
    } else if (randomInt == 8) {
      wurf1 = 18;
      wurf2 = 12;
      wurf3 = 20;
    } else if (randomInt == 9) {
      wurf1 = 30;
      wurf2 = 7;
      wurf3 = 13;
    }
  }

  public static int getDabei()
  {
    int dabei = query.count("select count(id) as a from fiftyfreedabei");
    return dabei;
  }

  public static int getDabei2()
  {
    int dabei = query.count("select count(id) as a from fiftyfreedabei where ingame='1'");
    return dabei;
  }

  public static void start(String message, Client client, Channel channel)
  {
    if (message.toLowerCase().contains(Server.get().getButler().getName().toLowerCase() + " fifty"))
    {
      if (!channel.getName().equals("Fifty!")) {
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), "Tut mir furchtbar leid, in diesem Channel gibt es kein _Fifty!_, " + client.getName() + ".");

        return;
      }

      if (activ == true)
      {
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("Fifty! läuft bereits.", new Object[0]));
      }
      else {
        setNumbers();
        setStechen();

        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("_°20RR°Die Anmeldung für Fifty! läuft... _°r>sm_dice.b.gif<°##Wer bei unserem Würfelklassiker _Fifty!_ (5 - 20 Spieler) mitspielen möchte, gibt bitte jetzt _°BB>_2/d +|/d +<°_§ ein.", new Object[0]));
        activ = true;
        thread.client = client;
        thread.channel = channel;
        Thread mixThread = new thread();
        mixThread.start();
      }
    }
  }
}
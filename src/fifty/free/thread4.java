package fifty.free;

import fifty.settings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.KCodeParser;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.query;

class thread4 extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ7 == true)
          sleep(50L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }

    }

    Object[] lol = settings.getFiftyPoints("free", fiftyfree.getDabei());
    String place1 = lol[0].toString();
    String place2 = lol[1].toString();
    String place3 = lol[2].toString();

    int first = 1;
    int stechen = 0;
    int second = 1;
    int exist2 = 0;
    int third = 1;
    int exist3 = 0;
    if ((!place1.equals("0.0")) && (fiftyfree.getDabei2() > 1)) {
      first = query.count("select count(id) as a from fiftyfreedabei where place='1' and ingame='1'");
    }
    if ((!place2.equals("0.0")) && (fiftyfree.getDabei2() > 2)) {
      second = query.count("select count(id) as a from fiftyfreedabei where place='2' and ingame='1'");
      exist2 = 1;
    }
    if ((!place3.equals("0.0")) && (fiftyfree.getDabei2() > 3)) {
      third = query.count("select count(id) as a from fiftyfreedabei where place='3' and ingame='1'");
      exist3 = 1;
    }

    if (first >= 2)
    {
      stechen = 1;

      fiftyfree.stechplace = 1;
    } else if ((second >= 2) && (exist2 == 1)) {
      stechen = 1;
      fiftyfree.stechplace = 2;
    } else if ((third >= 2) && (exist3 == 1)) {
      stechen = 1;
      fiftyfree.stechplace = 3;
    }
    if (stechen == 1) {
      fiftyfree.setStechen();

      fiftyfree.activ6 = false;
      fiftyfree.activ4 = false;
      fiftyfree.activ8 = true;
      thread5.client = client;
      thread5.channel = channel;
      Thread mixThread = new thread5();
      mixThread.start();
      return;
    }

    int winnerzahl = 2;
    String nick1 = "";
    String nick2 = "";
    String nick3 = "";
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
        if (place == 1) {
          nick1 = rs3.getString("user");
        }
        if (place == 2) {
          nick2 = rs3.getString("user");
        }
        if (place == 3)
          nick3 = rs3.getString("user");
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

    String winner = "1. " + nick1 + ": " + place1.replace(".0", "") + " Fifty! Punkte#";
    int rofl = Integer.parseInt(place1.replace(".0", ""));

    String nickname = KCodeParser.escape(nick1);

    boolean online = true;
    Client nickwin1 = Server.get().getClient(nickname);
    if (nickwin1 == null) {
      online = false;
      nickwin1 = new Client(null);
      nickwin1.loadStats(nickname);
    }

    nickname = nickwin1.getName();

    nickwin1.increaseFiftyPoints(rofl);
    if (!nick2.isEmpty()) {
      winner = winner + "2. " + nick2 + ": " + place2.replace(".0", "") + " Fifty! Punkte#";
      int rofl2 = Integer.parseInt(place2.replace(".0", ""));
      String nickname2 = KCodeParser.escape(nick2);

      boolean online2 = true;
      Client nickwin2 = Server.get().getClient(nickname2);
      if (nickwin2 == null) {
        online2 = false;
        nickwin2 = new Client(null);
        nickwin2.loadStats(nickname);
      }

      nickname2 = nickwin2.getName();
      nickwin2.increaseFiftyPoints(rofl2);
    }
    if ((!place3.equals("0.0")) && (!nick3.isEmpty())) {
      winner = winner + "3. " + nick3 + ": " + place3.replace(".0", "") + " Fifty! Punkte";
      int rofl3 = Integer.parseInt(place3.replace(".0", ""));

      String nickname3 = KCodeParser.escape(nick3);

      boolean online3 = true;
      Client nickwin3 = Server.get().getClient(nickname3);
      if (nickwin3 == null) {
        online3 = false;
        nickwin3 = new Client(null);
        nickwin3.loadStats(nickname);
      }

      nickname3 = nickwin3.getName();

      nickwin3.increaseFiftyPoints(rofl3);
      winnerzahl = 3;
    }
    int jp = 0;
    if (nick2.isEmpty()) {
      int lala = Integer.parseInt(place2.replace(".0", ""));
      jp += lala;
    }
    if (nick3.isEmpty()) {
      int lala = Integer.parseInt(place3.replace(".0", ""));
      jp += lala;
    }

    if (jp != 0) {
      winner = winner + "#(" + jp + " Fifty! Punkte wandern in den Jackpot)";
      query.update("update fiftyjackpots set free=free+'" + jp + "'");
    }
    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°RR20°_Fifty! endet somit mit folgenden Siegern:_°r°§##%s", new Object[] { winner }));
    fiftyfree.sentEnd(winnerzahl);

    fiftyfree.activ = false;
    fiftyfree.runde = 1;
    fiftyfree.wurf1 = 0;
    fiftyfree.wurf2 = 0;
    fiftyfree.wurf3 = 0;
    fiftyfree.activ2 = false;
    fiftyfree.activ3 = false;
    fiftyfree.activ4 = false;
    fiftyfree.activ5 = false;
    fiftyfree.activ6 = false;
    fiftyfree.activ7 = false;
  }
}
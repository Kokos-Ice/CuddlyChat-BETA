package blitz;

import static blitz.thread3.channel;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.PacketCreator;
import tools.Toolbar;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

class thread extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
      funktionen.funktion("blitz join",client, channel);
    for (int i = 0; i < 70; i++) {
      try {
        if (blitz.activ == true)
          sleep(300L);
        else
          return;
      }
      catch (InterruptedException e)
      {
      }
    }
    if (blitz.getDabei() < 2) {
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°18°°BB°_Blitz_§ konnte nicht gestartet werden. Es müssen mindestens 2 Spieler dabei sein!", new Object[0]));

      client.send(PacketCreator.removePrefixIcons(channel.getName()));

       for (Client c : channel.getClients()) {
      
       List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/sfc "+channel.getName()+":/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            c.send(Toolbar.ToolbarString(channel.getName(), buttons));
           c.send(Toolbar.BingoStyle(channel.getName()));
    }
      PoolConnection pcon8 = ConnectionPool.getConnection();
      PreparedStatement ps8 = null;
      try
      {
        Connection con8 = pcon8.connect();
        ps8 = con8.prepareStatement("truncate table blitzdabei");
        ps8.execute();
      }
      catch (SQLException e) {
        e.printStackTrace();
      } finally {
        if (ps8 != null)
          try {
            ps8.close();
          }
          catch (SQLException e)
          {
          }
        pcon8.close();
      }

      PoolConnection pcon2E = ConnectionPool.getConnection();
      PreparedStatement ps2E = null;
      try
      {
        Connection con2E = pcon2E.connect();
        ps2E = con2E.prepareStatement("UPDATE `blitzsettings` SET runde='1', aktuell='1'");
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

      PoolConnection pcon9 = ConnectionPool.getConnection();
      PreparedStatement ps9 = null;
      try
      {
        Connection con9 = pcon9.connect();
        ps9 = con9.prepareStatement("truncate table blitzdabei");
        ps9.execute();
      }
      catch (SQLException e) {
        e.printStackTrace();
      } finally {
        if (ps9 != null)
          try {
            ps9.close();
          }
          catch (SQLException e)
          {
          }
        pcon9.close();
      }

      blitz.activ = false;
    } else {
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("Die °RR°°21°_Anmeldung_§ für °RR°°21°_Blitz_§ ist beendet!§##Folgende Spieler sind dabei: %s (%s Spieler) würfeln um den Jackpot mit °BB°°21°_%s Blitz Punkten!_", new Object[] { blitz.getPlayer(), Integer.valueOf(blitz.getDabei()), Integer.valueOf(blitz.getBlitzjp()) }));
      blitz.wurfeln = false;
      blitz.bonuswurf = false;
      blitz.startBlitz();

      blitz.activ2 = true;
      thread2.client = client;
      thread2.channel = channel;
      Thread mixThread = new thread2();
      mixThread.start();
    }
  }
}

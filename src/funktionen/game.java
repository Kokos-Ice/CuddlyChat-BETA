package funktionen;

import game.shourtDarten;
import game.shourtDicen;
import game.shourtFreidiffen;
import handler.JörnGameHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.ReceiveOpcode;
import starlight.Server;
import tools.DiceCreator;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Choice;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.TextField;
import tools.query;

public class game
{
  public static void make(String arg, Client client, Channel channel)
  {
    Long time = Long.valueOf(System.currentTimeMillis() / 1000L);

    if (arg.equals("tie")) {
      client.sendButlerMessage(channel.getName(), "Diese Funktion ist derzeit nicht verfügbar.");
      return;
    }

    if (arg.equals("again"))
    {
      String last = client.getLastGame();
      if (last.isEmpty()) {
        client.sendButlerMessage(channel.getName(), "Die Einstellungen deines letzten Spiels kenne ich leider nicht mehr.");
        return;
      }
      String[] haha = last.split("\\|");
      String lol = "";
      String dazu = "";

      if (haha[0].equals("dicen")) {
        PoolConnection pconss3 = ConnectionPool.getConnection(); 
        PreparedStatement psss3 = null;
        try { 
            Connection conss3 = pconss3.connect();
          psss3 = conss3.prepareStatement("SELECT * FROM game_dicen_log where id='" + haha[1] + "'");
          ResultSet rsss3 = psss3.executeQuery();
          for (rsss3 = psss3.executeQuery(); rsss3.next(); ) {
            String gegner = "";
            if (rsss3.getString("gegner").equals(client.getName()))
              gegner = rsss3.getString("user");
            else {
              gegner = rsss3.getString("gegner");
            }

            dazu = "||" + rsss3.getString("privi") + "|" + rsss3.getString("offen") + "|" + rsss3.getString("knuddels") + "|" + rsss3.getString("rosen") + "|" + gegner + "|" + rsss3.getString("siegrunde") + "|" + rsss3.getString("vorsprung");
          }
        }
        catch (SQLException e)
        {
          
          e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
        }
      }
      if (haha[0].equals("darten")) {
        PoolConnection pconss3 = ConnectionPool.getConnection(); PreparedStatement psss3 = null;
        try { Connection conss3 = pconss3.connect();
          psss3 = conss3.prepareStatement("SELECT * FROM game_darten_log where id='" + haha[1] + "'");
          ResultSet rsss3 = psss3.executeQuery();
          for (rsss3 = psss3.executeQuery(); rsss3.next(); ) {
            String gegner = "";
            if (rsss3.getString("gegner").equals(client.getName()))
              gegner = rsss3.getString("user");
            else {
              gegner = rsss3.getString("gegner");
            }

            dazu = "||" + rsss3.getString("privi") + "|" + rsss3.getString("offen") + "|" + rsss3.getString("nachwurf") + "|" + rsss3.getString("knuddels") + "|" + rsss3.getString("rosen") + "|" + gegner + "|" + rsss3.getString("startzahl");
          }
        }
        catch (SQLException e)
        {
        
          e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
        }

      }

      if (haha[0].equals("freidiffen")) {
        PoolConnection pconss3 = ConnectionPool.getConnection(); PreparedStatement psss3 = null;
        try { Connection conss3 = pconss3.connect();
          psss3 = conss3.prepareStatement("SELECT * FROM game_freidiffen_log where id='" + haha[1] + "'");
          ResultSet rsss3 = psss3.executeQuery();
          for (rsss3 = psss3.executeQuery(); rsss3.next(); ) {
            String gegner = "";
            if (rsss3.getString("gegner").equals(client.getName()))
              gegner = rsss3.getString("user");
            else {
              gegner = rsss3.getString("gegner");
            }

            dazu = "||" + rsss3.getString("privi") + "|" + rsss3.getString("knuddels") + "|" + rsss3.getString("rosen") + "|" + gegner + "|" + rsss3.getString("anzahl");
          }
        }
        catch (SQLException e)
        {
        
          e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
        }
      }

      lol = "FAIL|" + haha[0] + dazu;
      String[] tokens = lol.split("\\|");
      JörnGameHandler.handle(tokens, client);
      return;
    }

    int lastconfig = 0;
    if (arg.equals("lastconfig")) {
      String last = client.getLastGame();
      if (last.isEmpty()) {
        client.sendButlerMessage(channel.getName(), "Die Einstellungen deines letzten Spiels kenne ich leider nicht mehr.");
        return;
      }
      String[] haha = last.split("\\|");
      arg = "settings:" + haha[0];
      lastconfig = Integer.parseInt(haha[1]);
    }

    if (arg.equals("?"))
    {
      String text = "In diesem Channel kannst du diese Spiele starten:";
/*
      if (channel.getFreidiffen() == 1) text = text + "#°>bullet.b.png<° Freidiffen °>mychannel/question-mark.png<>--<>|/h Freidiffen<°";
      if (channel.getDiffen() == 1) text = text + "#°>bullet.b.png<° Diffen °>mychannel/question-mark.png<>--<>|/h Diffen<°";
      if (channel.getDarten() == 1) text = text + "#°>bullet.b.png<° Darten °>mychannel/question-mark.png<>--<>|/h Darten<°";
      if (channel.getDicen() == 1) text = text + "#°>bullet.b.png<° Dicen °>mychannel/question-mark.png<>--<>|/h Dicen<°";
      client.sendButlerMessage(channel.getName(), text);
        */
      return;
    }
/*
    if ((channel.getDicen() == 0) && (channel.getDarten() == 0) && (channel.getDiffen() == 0) && (channel.getFreidiffen() == 0)) {
      client.sendButlerMessage(channel.getName(), "In diesem Channel kann man momentan keine Spiele starten.");

      return;
    }
*/
    int dran = 0;
    String gegners = "";
    String vons = "";
    int offen = 0;
    PoolConnection pconss = ConnectionPool.getConnection(); PreparedStatement psss = null;
    try {
      Connection conss = pconss.connect();
      psss = conss.prepareStatement("SELECT * FROM game_dicen where channel = '" + channel.getName() + "' and aktuplayer = '" + client.getName() + "' and maxwurf = '0'");
      ResultSet rsss = psss.executeQuery();
      while (rsss.next()) {
        dran = 1;
        offen = rsss.getInt("offen");
        gegners = rsss.getString("gegner");
        vons = rsss.getString("von");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (psss != null) try { psss.close(); } catch (SQLException e) {  } pconss.close();
    }
    if (dran == 1)
    {
      String variablen = DiceCreator.Würfeln(arg, offen);
      String[] teil = variablen.split("\\|");
      if (!teil[5].equals("0")) {
        client.sendButlerMessage(channel.getName(), String.format("%s", new Object[] { teil[2] }));
        return;
      }

      String nicks = "";
      Client target = null;
      if (gegners.equals(client.getName())) {
        
        nicks = vons;
      } else {
        target = Server.get().getClient(gegners);
        nicks = gegners;
      }

      boolean online = true; 
      if (target == null) { online = false; target = new Client(null); target.loadStats(nicks); }
      target.sendButlerMessage(channel.getName(), client.getName() + " würfelt jetzt mit _" + arg + "_.");
      channel.broadcastAction("°BB°", String.format("> %s rollt %s Würfel%s...#%s: %s = _%s_", new Object[] { client.getName(), teil[0], teil[1], teil[2], teil[3], teil[4] }));
      client.sendButlerMessage(channel.getName(), target.getName() + " würfelt jetzt mit _" + arg + "_.");
      if (offen == 0)
        target.sendButlerMessage(channel.getName(), "Die Waffen wurden gewählt. Bitte würfele nun diesen Würfel: _°BB>_2/dice " + arg + "|/dice " + arg + "<°_§.");
      else {
        target.sendButlerMessage(channel.getName(), "Die Waffen wurden gewählt. Bitte würfele nun diesen Würfel: _°BB>_2/diceo " + arg + "|/diceo " + arg + "<°_§.");
      }
      query.update("update game_dicen set start='" + time + "',aktuplayer='" + target.getName() + "', aktuwurf='" + arg + "',maxwurf='" + client.getName() + "|" + teil[4] + "' where von='" + client.getName() + "' or gegner = '" + client.getName() + "'");
    }

    if (arg.equals("stop")) {
      String gamehave = "";
      PoolConnection pcon = ConnectionPool.getConnection(); 
      PreparedStatement ps = null;
      try { Connection con = pcon.connect();
        ps = con.prepareStatement("SELECT * FROM game_dicen where gegner = '' and channel = '" + channel.getName() + "' and von = '" + client.getName() + "'");
        ResultSet rs = ps.executeQuery();
        for (rs = ps.executeQuery(); rs.next(); ) {
          gamehave = "Dicen";
          client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) + Integer.parseInt(rs.getString("einsatz_rosen"))));
          client.increaseKnuddels(client.getKnuddels() + Integer.parseInt(rs.getString("einsatz_knuddel")));
        }
      }
      catch (SQLException e)
      {
        ResultSet rs;
        e.printStackTrace(); } finally { if (ps != null) try { ps.close(); } catch (SQLException e) {  } pcon.close();
      }
      PoolConnection pcon1 = ConnectionPool.getConnection(); PreparedStatement ps1 = null;
      try { Connection con1 = pcon1.connect();
        ps1 = con1.prepareStatement("SELECT * FROM game_darten where gegner = '' and channel = '" + channel.getName() + "' and von = '" + client.getName() + "'");
        ResultSet rs1 = ps1.executeQuery();
        for (rs1 = ps1.executeQuery(); rs1.next(); ) {
          gamehave = "Darten";
          client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) + Integer.parseInt(rs1.getString("einsatz_rosen"))));
          client.increaseKnuddels(client.getKnuddels() + Integer.parseInt(rs1.getString("einsatz_knuddel")));
        }
      }
      catch (SQLException e)
      {
        ResultSet rs1;
        e.printStackTrace(); } finally { if (ps1 != null) try { ps1.close(); } catch (SQLException e) {  } pcon1.close();
      }

      PoolConnection pcon2 = ConnectionPool.getConnection(); PreparedStatement ps2 = null;
      try { Connection con2 = pcon2.connect();
        ps2 = con2.prepareStatement("SELECT * FROM game_freidiffen where gegner = '' and channel = '" + channel.getName() + "' and von = '" + client.getName() + "'");
        ResultSet rs2 = ps2.executeQuery();
        for (rs2 = ps2.executeQuery(); rs2.next(); ) {
          gamehave = "Freidiffen";
          client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) + Integer.parseInt(rs2.getString("einsatz_rosen"))));
          client.increaseKnuddels(client.getKnuddels() + Integer.parseInt(rs2.getString("einsatz_knuddel")));
        }
      }
      catch (SQLException e)
      {
   
        e.printStackTrace(); } finally { if (ps2 != null) try { ps2.close(); } catch (SQLException e) {  } pcon2.close();
      }

      if (!gamehave.isEmpty()) {
        client.sendButlerMessage(channel.getName(), "Dein Angebot zum Spielen von _" + gamehave + "_ wurde soeben gestoppt. ");
        query.delete("delete from game_dicen where von='" + client.getName() + "'");
        query.delete("delete from game_darten where von='" + client.getName() + "'");
        query.delete("delete from game_freidiffen where von='" + client.getName() + "'");
      }
      else {
        client.sendButlerMessage(channel.getName(), "Du hattest kein offenes Spielangebot.");
      }
      return;
    }
    if (arg.equals("list")) {
      String text = "";
      String text2 = "";
      PoolConnection pcon = ConnectionPool.getConnection(); PreparedStatement ps = null;
      try {
        Connection con = pcon.connect();
        ps = con.prepareStatement("SELECT * FROM game_dicen where gegner = '' and channel = '" + channel.getName() + "'  order by start");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          String einsatz = "";
          if ((!rs.getString("einsatz_knuddel").equals("0")) && (rs.getString("einsatz_rosen").equals("0")))
            einsatz = rs.getString("einsatz_knuddel") + " Knuddel";
          else if ((!rs.getString("einsatz_rosen").equals("0")) && (rs.getString("einsatz_knuddel").equals("0")))
            einsatz = rs.getString("einsatz_rosen") + " Rosen";
          else {
            einsatz = rs.getString("einsatz_knuddel") + " Knuddel und " + rs.getString("einsatz_rosen") + " Rosen";
          }

          text2 = text2 + "#°>bullet.b.png<° " + rs.getString("von") + " bietet eine Runde °22RR°_Dicen_§ an. °BB>{button}Mitspielen||call|/game join:" + rs.getString("von") + "<° (_°>" + rs.getString("runden") + " Runden, Einsatz: " + einsatz + "|/game details:" + rs.getString("von") + "<°_)§";
        } } catch (SQLException e) { e.printStackTrace(); } finally { if (ps != null) try { ps.close(); } catch (SQLException e) {  } pcon.close();
      }

      PoolConnection pcon1 = ConnectionPool.getConnection(); PreparedStatement ps1 = null;
      try {
        Connection con1 = pcon1.connect();
        ps1 = con1.prepareStatement("SELECT * FROM game_darten where gegner = '' and channel = '" + channel.getName() + "'  order by start");
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
          String einsatz = "";
          if ((!rs1.getString("einsatz_knuddel").equals("0")) && (rs1.getString("einsatz_rosen").equals("0")))
            einsatz = rs1.getString("einsatz_knuddel") + " Knuddel";
          else if ((!rs1.getString("einsatz_rosen").equals("0")) && (rs1.getString("einsatz_knuddel").equals("0")))
            einsatz = rs1.getString("einsatz_rosen") + " Rosen";
          else {
            einsatz = rs1.getString("einsatz_knuddel") + " Knuddel und " + rs1.getString("einsatz_rosen") + " Rosen";
          }
          String nachwurf = "";
          if (rs1.getString("nachwurf").equals("1")) {
            nachwurf = " Nachwurf aktiv,";
          }
          text2 = text2 + "#°>bullet.b.png<° " + rs1.getString("von") + " bietet eine Runde °22RR°_Dicen_§ an. °BB>{button}Mitspielen||call|/game join:" + rs1.getString("von") + "<° (_°>Startzahl: " + rs1.getString("startzahl") + "," + nachwurf + " Einsatz: " + einsatz + "|/game details:" + rs1.getString("von") + "<°_)§";
        } } catch (SQLException e) { e.printStackTrace(); } finally { if (ps1 != null) try { ps1.close(); } catch (SQLException e) {  } pcon1.close();
      }

      PoolConnection pcon2 = ConnectionPool.getConnection(); PreparedStatement ps2 = null;
      try {
        Connection con2 = pcon2.connect();
        ps2 = con2.prepareStatement("SELECT * FROM game_freidiffen where gegner = '' and channel = '" + channel.getName() + "'  order by start");
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
          String einsatz = "";
          if ((!rs2.getString("einsatz_knuddel").equals("0")) && (rs2.getString("einsatz_rosen").equals("0")))
            einsatz = rs2.getString("einsatz_knuddel") + " Knuddel";
          else if ((!rs2.getString("einsatz_rosen").equals("0")) && (rs2.getString("einsatz_knuddel").equals("0")))
            einsatz = rs2.getString("einsatz_rosen") + " Rosen";
          else {
            einsatz = rs2.getString("einsatz_knuddel") + " Knuddel und " + rs2.getString("einsatz_rosen") + " Rosen";
          }

          text2 = text2 + "#°>bullet.b.png<° " + rs2.getString("von") + " bietet eine Runde °22RR°_Freidiffen_§ an. °BB>{button}Mitspielen||call|/game join:" + rs2.getString("von") + "<° (_°>" + rs2.getString("wurf") + ", " + einsatz + "|/game details:" + rs2.getString("von") + "<°_)§";
        } } catch (SQLException e) { e.printStackTrace(); } finally { if (ps2 != null) try { ps2.close(); } catch (SQLException e) {  } pcon2.close();
      }

      if (!text2.isEmpty()) {
        text = "Folgende Spiele werden momentan angeboten:" + text2;
      }
      if (text.isEmpty()) {
        text = "Momentan gibt es kein offenes Spielangebot. °>finger.b.gif<° _°BB>_2Eröffne ein Spiel!|/game<°_";
      }

      client.sendButlerMessage(channel.getName(), text);
      return;
    }

    if (!arg.isEmpty()) {
      if (arg.indexOf(":") < 0) {
        return;
      }
      String[] aktion = arg.split(":", 2);
      if (aktion[0].equals("diffen")) {
        client.sendButlerMessage(channel.getName(), "Diese Funktion ist derzeit nicht verfügbar.");
        return;
      }if (aktion[0].equals("darten")) {
        shourtDarten.make(aktion[1], client, channel);
        return;
      }if (aktion[0].equals("dicen")) {
        shourtDicen.make(aktion[1], client, channel);
        return;
      }if (aktion[0].equals("freidiffen")) {
        shourtFreidiffen.make(aktion[1], client, channel);
        return;
      }if (aktion[0].equals("details")) {
        String nickname = KCodeParser.escape(aktion[1]);
        boolean online = true;
        Client target = Server.get().getClient(nickname);
        if (target == null) {
          online = false;
          target = new Client(null);
          target.loadStats(nickname);
        }

        nickname = target.getName();
        if (nickname == null) {
          client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(aktion[1]));
          return;
        }
        String art = "";
        String einsatz = "";

        PoolConnection pcon = ConnectionPool.getConnection(); PreparedStatement ps = null;
        try { Connection con = pcon.connect();
          ps = con.prepareStatement("SELECT * FROM game_dicen where channel = '" + channel.getName() + "' and von='" + aktion[1] + "'");
         ResultSet rs = ps.executeQuery();
          for (rs = ps.executeQuery(); rs.next(); ) {
            art = "dicen";
            if ((!rs.getString("einsatz_knuddel").equals("0")) && (rs.getString("einsatz_rosen").equals("0")))
              einsatz = rs.getString("einsatz_knuddel") + " Knuddel";
            else if ((!rs.getString("einsatz_rosen").equals("0")) && (rs.getString("einsatz_knuddel").equals("0")))
              einsatz = rs.getString("einsatz_rosen") + " Rosen";
            else
              einsatz = rs.getString("einsatz_knuddel") + " Knuddel und " + rs.getString("einsatz_rosen") + " Rosen";
          }
        }
        catch (SQLException e)
        {
          ResultSet rs;
          e.printStackTrace(); } finally { if (ps != null) try { ps.close(); } catch (SQLException e) {  } pcon.close();
        }
        PoolConnection pcon1 = ConnectionPool.getConnection(); PreparedStatement ps1 = null;
        try { Connection con1 = pcon1.connect();
          ps1 = con1.prepareStatement("SELECT * FROM game_darten where channel = '" + channel.getName() + "' and von='" + aktion[1] + "'");
          ResultSet rs1 = ps1.executeQuery();
          for (rs1 = ps1.executeQuery(); rs1.next(); ) {
            art = "darten";
            if ((!rs1.getString("einsatz_knuddel").equals("0")) && (rs1.getString("einsatz_rosen").equals("0")))
              einsatz = rs1.getString("einsatz_knuddel") + " Knuddel";
            else if ((!rs1.getString("einsatz_rosen").equals("0")) && (rs1.getString("einsatz_knuddel").equals("0")))
              einsatz = rs1.getString("einsatz_rosen") + " Rosen";
            else
              einsatz = rs1.getString("einsatz_knuddel") + " Knuddel und " + rs1.getString("einsatz_rosen") + " Rosen";
          }
        }
        catch (SQLException e)
        {
          ResultSet rs1;
          e.printStackTrace(); } finally { if (ps1 != null) try { ps1.close(); } catch (SQLException e) {  } pcon1.close();
        }
        PoolConnection pcon2 = ConnectionPool.getConnection(); PreparedStatement ps2 = null;
        try { Connection con2 = pcon2.connect();
          ps2 = con2.prepareStatement("SELECT * FROM game_freidiffen where channel = '" + channel.getName() + "' and von='" + aktion[1] + "'");
          ResultSet rs2 = ps2.executeQuery();
          for (rs2 = ps2.executeQuery(); rs2.next(); ) {
            art = "freidiffen";
            if ((!rs2.getString("einsatz_knuddel").equals("0")) && (rs2.getString("einsatz_rosen").equals("0")))
              einsatz = rs2.getString("einsatz_knuddel") + " Knuddel";
            else if ((!rs2.getString("einsatz_rosen").equals("0")) && (rs2.getString("einsatz_knuddel").equals("0")))
              einsatz = rs2.getString("einsatz_rosen") + " Rosen";
            else
              einsatz = rs2.getString("einsatz_knuddel") + " Knuddel und " + rs2.getString("einsatz_rosen") + " Rosen";
          }
        }
        catch (SQLException e)
        {
          ResultSet rs2;
          e.printStackTrace(); } finally { if (ps2 != null) try { ps2.close(); } catch (SQLException e) {  } pcon2.close(); }
        if (art.isEmpty()) {
          client.sendButlerMessage(channel.getName(), target.getName() + " hat kein offenes Spielangebot.");
          return;
        }
        
        if (art.equals("dicen"))
          PacketCreator.createWindowDetailsDicen(target, einsatz);
        else if (art.equals("darten"))
          PacketCreator.createWindowDetailsDarten(target, einsatz);
        else {
          PacketCreator.createWindowDetailsFreidiffen(target, einsatz);
        }

        return;
      }

      if (aktion[0].equals("join")) {
        int exist = 0;
        String gegner = "";
        String table = "";
        String aktuwurf = "";
        String von = "";
        int offens = 0;
        String wurf = "";
        int knuddels = 0;
        String ip = "";
        int rosen = 0;
        int anzahl = 0;
        int akzept = 0;
        int startzahl = 0;

        PoolConnection pcon = ConnectionPool.getConnection(); PreparedStatement ps = null;
        try { Connection con = pcon.connect();
          ps = con.prepareStatement("SELECT * FROM game_dicen where channel = '" + channel.getName() + "' and von='" + aktion[1] + "'");
          ResultSet rs = ps.executeQuery();
          for (rs = ps.executeQuery(); rs.next(); ) {
            gegner = rs.getString("gegner");
            exist = 1;
            ip = rs.getString("ip");
            offens = rs.getInt("offen");
            rosen = rs.getInt("einsatz_rosen");
            knuddels = rs.getInt("einsatz_knuddel");
            table = "game_dicen";
            aktuwurf = rs.getString("aktuwurf");
            von = rs.getString("von");
            akzept = rs.getInt("akzept");
          }
        }
        catch (SQLException e)
        {
          ResultSet rs;
          e.printStackTrace(); } finally { if (ps != null) try { ps.close(); } catch (SQLException e) {  } pcon.close();
        }
        PoolConnection pcon1 = ConnectionPool.getConnection(); PreparedStatement ps1 = null;
        try { Connection con1 = pcon1.connect();
          ps1 = con1.prepareStatement("SELECT * FROM game_darten where channel = '" + channel.getName() + "' and von='" + aktion[1] + "'");
          ResultSet rs1 = ps1.executeQuery();
          for (rs1 = ps1.executeQuery(); rs1.next(); ) {
            gegner = rs1.getString("gegner");
            exist = 1;
            ip = rs1.getString("ip");
            offens = rs1.getInt("offen");
            startzahl = rs1.getInt("startzahl");
            rosen = rs1.getInt("einsatz_rosen");
            knuddels = rs1.getInt("einsatz_knuddel");
            table = "game_darten";
            von = rs1.getString("von");
            akzept = rs1.getInt("akzept");
          }
        }
        catch (SQLException e)
        {
          ResultSet rs1;
          e.printStackTrace(); } finally { if (ps1 != null) try { ps1.close(); } catch (SQLException e) {  } pcon1.close();
        }

        PoolConnection pcon2 = ConnectionPool.getConnection(); PreparedStatement ps2 = null;
        try { Connection con2 = pcon1.connect();
          ps2 = con2.prepareStatement("SELECT * FROM game_freidiffen where channel = '" + channel.getName() + "' and von='" + aktion[1] + "'");
          ResultSet rs2 = ps2.executeQuery();
          for (rs2 = ps2.executeQuery(); rs2.next(); ) {
            gegner = rs2.getString("gegner");
            exist = 1;
            ip = rs2.getString("ip");
            anzahl = rs2.getInt("anzahl");
            rosen = rs2.getInt("einsatz_rosen");
            knuddels = rs2.getInt("einsatz_knuddel");
            table = "game_freidiffen";
            von = rs2.getString("von");
            wurf = rs2.getString("wurf");
            akzept = rs2.getInt("akzept");
          }
        }
        catch (SQLException e)
        {
          ResultSet rs2;
          e.printStackTrace(); } finally { if (ps2 != null) try { ps2.close(); } catch (SQLException e) {  } pcon2.close();
        }

        if (exist == 0) {
          client.sendButlerMessage(channel.getName(), "Das Angebot für dieses Spiel besteht leider nicht mehr.");
        } else if (((!gegner.isEmpty()) && (!gegner.equals(client.getName()))) || (akzept == 1)) {
          client.sendButlerMessage(channel.getName(), "Das Spiel hat bereits einen Gegenspieler gefunden.");
        }
        else {
         /* if (client.getIPAddress().equals(ip)) {
            client.sendButlerMessage(channel.getName(), "Du kannst diesem Spiel nicht beitreten, weil du dich im selben Netzwerk befindest, wie der Herausforderder.");
            return;
          }*/
          if (rosen > Integer.parseInt(client.getRosensperre())) {
            client.sendButlerMessage(channel.getName(), "Du verfügst nicht über genug Rosen, um diesem Spiel beizutreten.");
            return;
          }
          if (knuddels > client.getKnuddels() / 2) {
            client.sendButlerMessage(channel.getName(), "Du verfügst nicht über genug Knuddels, um diesem Spiel beizutreten. (max. 50% der eigenen Knuddel)");
            return;
          }

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

          if (dabei == 1) {
            client.sendButlerMessage(channel.getName(), "Du bietest momentan ein Spiel an und kannst deshalb kein weiteres Spiel spielen.");
            return;
          }

          client.setRosensperre(String.valueOf(Integer.parseInt(client.getRosensperre()) - rosen));
          client.increaseKnuddels(client.getKnuddels() - knuddels);

          if (table.equals("game_dicen")) {
            client.sendButlerMessage(channel.getName(), "Du bist dem Spiel beibeitreten. " + von + " entscheidet nun welche Würfel geworfen werden.");
            Client target = Server.get().getClient(von);
            boolean online = true; if (target == null) { online = false; target = new Client(null); target.loadStats(von);
            }
            target.sendButlerMessage(channel.getName(), client.getName() + " hat deine Herausforderungn angenommen. Nun entscheidest du, welche Würfel geworfen werden: (Vorschlag: _°BB>_2/game " + aktuwurf + "|/game " + aktuwurf + "<°_§)");
            Random random = new Random();
            int randomInt = random.nextInt(24);
            randomInt += 1;
            int randomInt2 = random.nextInt(99999);
            randomInt2 += 1;

            query.update("update " + table + " set start='" + time + "',aktuplayer='" + von + "', aktuwurf='" + randomInt + "w" + randomInt2 + "', gegner='" + client.getName() + "', akzept='1' where von='" + von + "'");
          } else if (table.equals("game_darten")) {
            client.sendButlerMessage(channel.getName(), "Du bist dem Spiel von " + von + " beigetreten. " + von + " wird nun mit _1w" + startzahl + "_ beginnen.");
            Client target = Server.get().getClient(von);
            boolean online = true; if (target == null) { online = false; target = new Client(null); target.loadStats(von); }
            if (offens == 0)
              target.sendButlerMessage(channel.getName(), client.getName() + " hat deine Herausforderung angenommen. Du beginnst mit _°BB>/dice 1w" + startzahl + "|/dice 1w" + startzahl + "<°_§");
            else {
              target.sendButlerMessage(channel.getName(), client.getName() + " hat deine Herausforderung angenommen. Du beginnst mit _°BB>/diceo 1w" + startzahl + "|/diceo 1w" + startzahl + "<°_§");
            }

            query.update("update " + table + " set start='" + time + "',aktuplayer='" + von + "', gegner='" + client.getName() + "', akzept='1' where von='" + von + "'");
          } else if (table.equals("game_freidiffen"))
          {
            client.sendButlerMessage(channel.getName(), "Das Spiel °BB°_Freidiffen_§ startet nun. " + von + " beginnt mit dem ersten Wurf (" + wurf + ").");
            Client target = Server.get().getClient(von);
            boolean online = true; if (target == null) { online = false; target = new Client(null); target.loadStats(von); }
            target.sendButlerMessage(channel.getName(), client.getName() + " spielt nun gegen dich. Würfele nun mit _°BB>_2/dice " + wurf + "|/dice " + wurf + "<°_§ und hol dir den Sieg.");

            query.update("update " + table + " set start='" + time + "',aktuplayer='" + von + "', gegner='" + client.getName() + "', akzept='1' where von='" + von + "'");
          }

        }

        return;
      }if (aktion[0].equals("deny")) {
        String von = "";
        PoolConnection pcon = ConnectionPool.getConnection(); PreparedStatement ps = null;
        try { Connection con = pcon.connect();
          ps = con.prepareStatement("SELECT * FROM game_dicen where gegner = '" + client.getName() + "' and channel = '" + channel.getName() + "' and akzept='0'");
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            von = rs.getString("von");
            Client targets = Server.get().getClient(von);
            boolean online = true; if (targets == null) { online = false; targets = new Client(null); targets.loadStats(von); }
            targets.setRosensperre(String.valueOf(Integer.parseInt(targets.getRosensperre()) + Integer.parseInt(rs.getString("einsatz_rosen"))));
            targets.increaseKnuddels(targets.getKnuddels() + Integer.parseInt(rs.getString("einsatz_knuddel")));
          } } catch (SQLException e) {
          e.printStackTrace(); } finally { if (ps != null) try { ps.close(); } catch (SQLException e) {  } pcon.close();
        }

        PoolConnection pcon1 = ConnectionPool.getConnection(); PreparedStatement ps1 = null;
        try { Connection con1 = pcon1.connect();
          ps1 = con1.prepareStatement("SELECT * FROM game_darten where gegner = '" + client.getName() + "' and channel = '" + channel.getName() + "' and akzept='0'");
          ResultSet rs1 = ps1.executeQuery();
          while (rs1.next()) {
            von = rs1.getString("von");
            Client targets = Server.get().getClient(von);
            boolean online = true; if (targets == null) { online = false; targets = new Client(null); targets.loadStats(von); }
            targets.setRosensperre(String.valueOf(Integer.parseInt(targets.getRosensperre()) + Integer.parseInt(rs1.getString("einsatz_rosen"))));
            targets.increaseKnuddels(targets.getKnuddels() + Integer.parseInt(rs1.getString("einsatz_knuddel")));
          } } catch (SQLException e) {
          e.printStackTrace(); } finally { if (ps1 != null) try { ps1.close(); } catch (SQLException e) {  } pcon1.close();
        }

        if (!von.trim().isEmpty()) {
          Client target = Server.get().getClient(von);
          client.sendButlerMessage(channel.getName(), "Du hast gerade das Spielangebot von " + von + " abgelehnt.");
          target.sendButlerMessage(channel.getName(), client.getName() + " hat gerade dein Spielangebot abgelehnt.");
          query.delete("delete from game_darten where gegner='" + client.getName() + "'");
          query.delete("delete from game_dicen where gegner='" + client.getName() + "'");
        } else {
          client.sendButlerMessage(channel.getName(), "Das Angebot für dieses Spiel besteht leider nicht mehr.");
        }
        return;
      }if (aktion[0].equals("settings")) {
        if (aktion[1].equals("dicen")) {
          /*if (channel.getDicen() == 0) {
            client.sendButlerMessage(channel.getName(), "In diesem Channel kann man momentan kein Dicen starten.");
            return;
          }*/
          Popup popup = new Popup("Spiel konfigurieren - Dicen", "Spiel konfigurieren - Dicen", "",  300, 250);

          String privi = "Nein";
          String offens = "Nein";
          String knuddel = "0";
          String rosen = "0";
          String gegner = "";
          String runden = "3";
          String vorsprung = "1";
          if (lastconfig != 0) {
            PoolConnection pconss3 = ConnectionPool.getConnection(); PreparedStatement psss3 = null;
            try { Connection conss3 = pconss3.connect();
              psss3 = conss3.prepareStatement("SELECT * FROM game_dicen_log where id='" + lastconfig + "'");
              ResultSet rsss3 = psss3.executeQuery();
              for (rsss3 = psss3.executeQuery(); rsss3.next(); ) {
                if (rsss3.getString("privi").equals("1")) privi = "Ja"; else privi = "Nein";
                if (rsss3.getString("offen").equals("1")) offens = "Ja"; else offens = "Nein";
                knuddel = rsss3.getString("knuddels");
                rosen = rsss3.getString("rosen");
                if (rsss3.getString("gegner").equals(client.getName())) gegner = rsss3.getString("user"); else gegner = rsss3.getString("gegner");
                runden = rsss3.getString("siegrunden");
                vorsprung = rsss3.getString("vorsprung");
              }
            }
            catch (SQLException e)
            {
              ResultSet rsss3;
              e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
            }

          }

          Panel gay3 = new Panel();
          gay3.addComponent(new Label("Privates Würfeln"));
          Choice whoisgay3 = new Choice(new String[] { "Nein", "Ja" }, privi);
          gay3.addComponent(whoisgay3);

          popup.addPanel(gay3);

          Panel gay4 = new Panel();
          gay4.addComponent(new Label("Offene Würfe"));
          Choice whoisgay4 = new Choice(new String[] { "Nein", "Ja" }, offens);
          gay4.addComponent(whoisgay4);

          popup.addPanel(gay4);

          Panel panel0 = new Panel();
          panel0.addComponent(new Label("Knuddels"));
          panel0.addComponent(new TextField(10, knuddel));
          popup.addPanel(panel0);

          Panel panel00 = new Panel();
          panel00.addComponent(new Label("Rosen"));
          panel00.addComponent(new TextField(10, rosen));
          popup.addPanel(panel00);

          Panel panel000 = new Panel();
          panel000.addComponent(new Label("Gegenspieler"));
          panel000.addComponent(new TextField(10, gegner));
          popup.addPanel(panel000);

          Panel panel0000 = new Panel();
          panel0000.addComponent(new Label("Siegrunden"));
          panel0000.addComponent(new TextField(10, runden));
          popup.addPanel(panel0000);

          Panel panel00000 = new Panel();
          panel00000.addComponent(new Label("Siegvorsprung"));
          panel00000.addComponent(new TextField(10, vorsprung));
          popup.addPanel(panel00000);

          Panel panel20 = new Panel();
          Button button = new Button("Speichern");
          Button button2 = new Button("Abbrechen");
          panel20.addComponent(button);
          panel20.addComponent(button2);
          button.enableAction();

          popup.addPanel(panel20);
          popup.setOpcode(ReceiveOpcode.GAME.getValue(), "dicen");

          client.send(popup.toString());
        }
        else if (aktion[1].equals("darten")) {
         /* if (channel.getDarten() == 0) {
            client.sendButlerMessage(channel.getName(), "In diesem Channel kann man momentan kein Darten starten.");
            return;
          }*/
          Popup popup = new Popup("Spiel konfigurieren - Darten", "Spiel konfigurieren - Darten", "", 300, 250);

          String privi = "Nein";
          String offens = "Nein";
          String knuddel = "0";
          String rosen = "0";
          String gegner = "";
          String startzahl = "100";
          String nachwurf = "Ja";
          if (lastconfig != 0) {
            PoolConnection pconss3 = ConnectionPool.getConnection(); PreparedStatement psss3 = null;
            try { Connection conss3 = pconss3.connect();
              psss3 = conss3.prepareStatement("SELECT * FROM game_darten_log where id='" + lastconfig + "'");
              ResultSet rsss3 = psss3.executeQuery();
              for (rsss3 = psss3.executeQuery(); rsss3.next(); ) {
                if (rsss3.getString("privi").equals("1")) privi = "Ja"; else privi = "Nein";
                if (rsss3.getString("offen").equals("1")) offens = "Ja"; else offens = "Nein";
                knuddel = rsss3.getString("knuddels");
                rosen = rsss3.getString("rosen");
                if (rsss3.getString("gegner").equals(client.getName())) gegner = rsss3.getString("user"); else gegner = rsss3.getString("gegner");
                startzahl = rsss3.getString("startzahl");
                if (rsss3.getString("nachwurf").equals("1")) nachwurf = "Ja"; else nachwurf = "Nein";
              }
            }
            catch (SQLException e)
            {
              ResultSet rsss3;
              e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
            }
          }

          Panel gay3 = new Panel();
          gay3.addComponent(new Label("Privates Würfeln"));
          Choice whoisgay3 = new Choice(new String[] { "Nein", "Ja" }, privi);
          gay3.addComponent(whoisgay3);

          popup.addPanel(gay3);

          Panel gay4 = new Panel();
          gay4.addComponent(new Label("Offene Würfe"));
          Choice whoisgay4 = new Choice(new String[] { "Nein", "Ja" }, offens);
          gay4.addComponent(whoisgay4);

          popup.addPanel(gay4);

          Panel gay5 = new Panel();
          gay5.addComponent(new Label("Nachwurf"));
          Choice whoisgay5 = new Choice(new String[] { "Nein", "Ja" }, nachwurf);
          gay5.addComponent(whoisgay5);

          popup.addPanel(gay5);

          Panel panel0 = new Panel();
          panel0.addComponent(new Label("Knuddels"));
          panel0.addComponent(new TextField(10, knuddel));
          popup.addPanel(panel0);

          Panel panel00 = new Panel();
          panel00.addComponent(new Label("Rosen"));
          panel00.addComponent(new TextField(10, rosen));
          popup.addPanel(panel00);

          Panel panel000 = new Panel();
          panel000.addComponent(new Label("Gegenspieler"));
          panel000.addComponent(new TextField(10, gegner));
          popup.addPanel(panel000);

          Panel panel0000 = new Panel();
          panel0000.addComponent(new Label("Start-Zahl"));
          panel0000.addComponent(new TextField(10, startzahl));
          popup.addPanel(panel0000);

          Panel panel20 = new Panel();
          Button button = new Button("Speichern");
          Button button2 = new Button("Abbrechen");
          panel20.addComponent(button);
          panel20.addComponent(button2);
          button.enableAction();

          popup.addPanel(panel20);
          popup.setOpcode(ReceiveOpcode.GAME.getValue(), "darten");

          client.send(popup.toString());
        }
        else if (aktion[1].equals("freidiffen")) {
         /* if (channel.getFreidiffen() == 0) {
            client.sendButlerMessage(channel.getName(), "In diesem Channel kann man momentan kein Freidiffen starten.");
            return;
          }*/
          Popup popup = new Popup("Spiel konfigurieren - Freidiffen", "Spiel konfigurieren - Freidiffen", "", 300, 250);

          String privi = "Nein";

          String knuddel = "0";
          String rosen = "0";
          String gegner = "";
          String anzahl = "1";

          if (lastconfig != 0) {
            PoolConnection pconss3 = ConnectionPool.getConnection(); PreparedStatement psss3 = null;
            try { Connection conss3 = pconss3.connect();
              psss3 = conss3.prepareStatement("SELECT * FROM game_freidiffen_log where id='" + lastconfig + "'");
              ResultSet rsss3 = psss3.executeQuery();
              for (rsss3 = psss3.executeQuery(); rsss3.next(); ) {
                if (rsss3.getString("privi").equals("1")) privi = "Ja"; else privi = "Nein";
                knuddel = rsss3.getString("knuddels");
                rosen = rsss3.getString("rosen");
                if (rsss3.getString("gegner").equals(client.getName())) gegner = rsss3.getString("user"); else gegner = rsss3.getString("gegner");
                anzahl = rsss3.getString("anzahl");
              }
            }
            catch (SQLException e)
            {
              ResultSet rsss3;
              e.printStackTrace(); } finally { if (psss3 != null) try { psss3.close(); } catch (SQLException e) {  } pconss3.close();
            }

          }

          Panel gay3 = new Panel();
          gay3.addComponent(new Label("Privates Würfeln"));
          Choice whoisgay3 = new Choice(new String[] { "Nein", "Ja" }, privi);
          gay3.addComponent(whoisgay3);

          popup.addPanel(gay3);

          Panel panel0 = new Panel();
          panel0.addComponent(new Label("Knuddels"));
          panel0.addComponent(new TextField(10, knuddel));
          popup.addPanel(panel0);

          Panel panel00 = new Panel();
          panel00.addComponent(new Label("Rosen"));
          panel00.addComponent(new TextField(10, rosen));
          popup.addPanel(panel00);

          Panel panel000 = new Panel();
          panel000.addComponent(new Label("Gegenspieler"));
          panel000.addComponent(new TextField(10, gegner));
          popup.addPanel(panel000);

          Panel panel0000 = new Panel();
          panel0000.addComponent(new Label("Anz. Würfel"));
          panel0000.addComponent(new TextField(10, anzahl));
          popup.addPanel(panel0000);

          Panel panel20 = new Panel();
          Button button = new Button("Speichern");
          Button button2 = new Button("Abbrechen");
          panel20.addComponent(button);
          panel20.addComponent(button2);
          button.enableAction();

          popup.addPanel(panel20);
          popup.setOpcode(ReceiveOpcode.GAME.getValue(), "freidiffen");

          client.send(popup.toString());
        }
        else if (aktion[1].equals("diffen")) {
          /*if (channel.getDiffen() == 0) {
            client.sendButlerMessage(channel.getName(), "In diesem Channel kann man momentan kein Diffen starten.");
            return;
          }*/
          client.sendButlerMessage(channel.getName(), "Diffen ist momentan nicht verfügbar.");
        }

        return;
      }

    }

    Popup popup = new Popup("Spiele - Übersicht", "Spiele - Übersicht", "", 300, 250);
    //if (channel.getDicen() == 1) {
      Panel panel2 = new Panel();
      Button buttonMessage2 = new Button("         Dicen         ");
      buttonMessage2.setCommand(String.format("/game settings:dicen", new Object[0]));
      panel2.addComponent(buttonMessage2);
      popup.addPanel(panel2);
    //}
    //if (channel.getDarten() == 1) {
      Panel panel3 = new Panel();
      Button buttonMessage3 = new Button("        Darten        ");
      buttonMessage3.setCommand(String.format("/game settings:darten", new Object[0]));
      panel3.addComponent(buttonMessage3);
      popup.addPanel(panel3);
   // }
   // if (channel.getDiffen() == 1) {
      Panel panel4 = new Panel();
      Button buttonMessage4 = new Button("         Diffen         ");
      buttonMessage4.setCommand(String.format("/game settings:diffen", new Object[0]));
      panel4.addComponent(buttonMessage4);
      popup.addPanel(panel4);
   // }
    //if (channel.getFreidiffen() == 1) {
      Panel panel5 = new Panel();
      Button buttonMessage5 = new Button("      Freidiffen      ");
      buttonMessage5.setCommand(String.format("/game settings:freidiffen", new Object[0]));
      panel5.addComponent(buttonMessage5);
      popup.addPanel(panel5);
   // }
    Panel panel = new Panel();
    panel.addComponent(new Button("      Schließen      "));
    popup.addPanel(panel);

    client.send(popup.toString());
  }
}
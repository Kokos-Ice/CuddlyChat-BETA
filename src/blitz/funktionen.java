package blitz;

import static funktionen.f.time;
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
import starlight.Smiley;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.Toolbar;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class funktionen
{
  private static boolean isInteger(String s)
  {
    try
    {
      Integer.parseInt(s);
      return true;
    }
    catch (NumberFormatException e)
    {
    }

    return false;
  }

  public static void funktion(String message, Client client, Channel channel)
  {
    String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
    String cmd = command.toLowerCase();
    String arg = "";
    if (message.length() > cmd.length() + 1) {
      arg = message.substring(message.indexOf(' ') + 1);
    }

    if (cmd.equals("blitz"))
    {
      if (arg.equals("join")) {
        String dabei = "";
        PoolConnection pcon3 = ConnectionPool.getConnection();
        PreparedStatement ps3 = null;
        try
        {
          Connection con = pcon3.connect();
          ps3 = con.prepareStatement("SELECT * FROM `blitzdabei` where user = ?");
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
        if ((!blitz.activ) || (blitz.activ2 == true) || (blitz.activ3 == true)) {
          client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir leider nicht zur Verfügung.");
        } else if (dabei.equals("1")) {
          client.sendButlerMessage(channel.getName(), "Du hast dich bereits angemeldet.");
        } else {
      /*    if (client.checkGl() == 1) {
            client.sendButlerMessage(channel.getName(), "Du bist noch bis zum _" + client.Glbis() + "_ für alle Spiele gesperrt.");
            return;
          }*/
          client.sendButlerMessage(channel.getName(), "Du hast dich erfolgreich angemeldet.");

          List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Anmeldung läuft...", "/sfc "+channel.getName()+":/blitz join", "", true));  
            client.send(Toolbar.ToolbarString(channel.getName(), buttons));
            client.send(Toolbar.BingoStyle(channel.getName()));
          
          
          List prefix = new ArrayList();
          prefix.add(client.getName() + "ct/sm_ct_ehren-smiley-ani.gif");
          //prefix.add(client.getName() + "~nicklist_blitz");
         
          for (Client c : channel.getClients()) {
            c.send(PacketCreator.showPrefixIcons(channel.getName(), prefix,true));
          }
          
          Server.get().query("insert into blitzdabei set user = '"+client.getName()+"'");

         
        }
      }
      else if (arg.equals("wurf"))
      {
        String userid = "";
        int zahl = 0;
        int zahl2 = 0;
        String bonus = "";
        int zahl3 = 0;
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
            zahl = rs3.getInt("zahl");
            zahl2 = rs3.getInt("zahl2");
            zahl3 = rs3.getInt("zahl3");
            bonus = rs3.getString("bonus");
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

        int wurfels = 0;
        String user = "";
        PoolConnection pcon4 = ConnectionPool.getConnection();
        PreparedStatement ps4 = null;
        try
        {
          Connection con4 = pcon4.connect();
          ps4 = con4.prepareStatement("SELECT * FROM `blitzdabei` where `id` = ?");
          ps4.setString(1, userid);
          ResultSet rs4 = ps4.executeQuery();
          while (rs4.next())
          {
            user = rs4.getString("user");
            wurfels = rs4.getInt("wurfel");
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

        if (!blitz.activ) {
          client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        } else if ((!user.equals(client.getName())) && (!blitz.bonuswurf)) {
          client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        }
        else {
          Random random = new Random();
          int randomInt = random.nextInt(15);
          int wurf = randomInt + 1;
          int randomIntii = random.nextInt(10);
          int wurf2 = randomIntii + 1;
          if ((bonus.isEmpty()) && (!blitz.wurfeln)) {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            return;
          }

          if (!bonus.isEmpty()) {
            if (blitz.bonuswurf == true) {
              if (bonus.equals(client.getName())) {
                if (zahl3 == wurf2)
                {
                  int randomIntiii = random.nextInt(8);
                  int kndl = randomIntiii + 1;
                  int gewinn = 0;
                  if (kndl == 1)
                    gewinn = 1;
                  else if (kndl == 2)
                    gewinn = 5;
                  else if (kndl == 3)
                    gewinn = 10;
                  else if (kndl == 4)
                    gewinn = 25;
                  else if (kndl == 5)
                    gewinn = 50;
                  else if (kndl == 6)
                    gewinn = 100;
                  else if (kndl == 7)
                    gewinn = 500;
                  else if (kndl == 8) {
                    gewinn = 1000;
                  }

                  channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°18BB°_%s_§ freut sich, als W10 = _%s_ ergab. -°BB18°BONUS!_§ -", new Object[] { client.getName(), Integer.valueOf(wurf2) }));
                  channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°18BB°_%s_ §hat die Chance auf einen Bonus sinnvoll genutzt und somit _%s Knuddels_ gewonnen!", new Object[] { client.getName(), Integer.valueOf(gewinn) }));
                  client.increaseKnuddels(gewinn);
                  // Jörns Knuddels-Gewinn Abfrage abgeschaltet weil verdoppelt sonst Profil-Knuddels
                  // client.increaseKnuddels(client.getKnuddels()+ gewinn);
                }
                else
                {
                  channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°18BB°%s_§ ärgert sich wie wild, als W10 = _%s_ ergab. _Kein Bonus!_", new Object[] { client.getName(), Integer.valueOf(wurf2) }));
                }

                blitz.bonuswurf = false;
             Server.get().query("UPDATE `blitzsettings` SET `bonus` = '', `zahl3` = ''");
               
              }
              else {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
              }
            }
            else {
              client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            }

          }
          else if ((blitz.activ2 == true) && (!blitz.activ3))
          {
            String bereits = "0";
            String users = "";
            PoolConnection pcon40 = ConnectionPool.getConnection();
            PreparedStatement ps40 = null;
            try
            {
              Connection con40 = pcon40.connect();
              ps40 = con40.prepareStatement("SELECT * FROM `blitzzahlen` where zahl = ?");
              ps40.setInt(1, wurf);
              ResultSet rs40 = ps40.executeQuery();
              while (rs40.next()) {
                bereits = "1";
                users = rs40.getString("user");
              }
            }
            catch (SQLException e)
            {
              e.printStackTrace();
            } finally {
              if (ps40 != null)
                try {
                  ps40.close();
                }
                catch (SQLException e)
                {
                }
              pcon40.close();
            }
            String messages = "";
            if (bereits.equals("1")) {
              Random randomp = new Random();
              int texti = randomp.nextInt(6);
              if (texti == 0)
                messages = String.format("_°18RR°%s_§ kann es nicht begreifen, als W15 = _°20°%s_§ bereits von %s gewürfelt wurde. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf), users });
              else if (texti == 1)
                messages = String.format("_°18RR°%s_§ ärgert sich tierisch, als W15 = _°20°%s_§ bereits von %s gewürfelt wurde. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf), users });
              else if (texti == 2)
                messages = String.format("_°18RR°%s_§ ist genervt, als W15 = _°20°%s_§ bereits von %s gewürfelt wurde. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf), users });
              else if (texti == 3)
                messages = String.format("_°18RR°%s_§ ist wütend, als W15 = _°20°%s_§ bereits von %s gewürfelt wurde. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf), users });
              else if (texti == 4)
                messages = String.format("_°18RR°%s_§ glaubt seinen Augen kaum, als W15 = _°20°%s_§ bereits von %s gewürfelt wurde. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf), users });
              else if (texti == 5) {
                messages = String.format("_°18RR°%s_§ ist fassungslos, als man Erfahren hat, dass W15 = _°20°%s_§ bereits von %s gewürfelt wurde. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf), users });
              }
              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { messages }));
              blitz.nextPlayer("1", channel, client);
            } else if (wurf == zahl) {
              Random randomp = new Random();
              int texti = randomp.nextInt(6);
              if (texti == 0)
                messages = String.format("_°18RR°%s_§ kann es nicht begreifen, als W15 = _°20°%s_§ ergab. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf) });
              else if (texti == 1)
                messages = String.format("_°18RR°%s_§ ärgert sich tierisch, als W15 = _°20°%s_§ ergab. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf) });
              else if (texti == 2)
                messages = String.format("_°18RR°%s_§ ist genervt, als W15 = _°20°%s_§ ergab. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf) });
              else if (texti == 3)
                messages = String.format("_°18RR°%s_§ ist wütend, als W15 = _°20°%s_§ ergab. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf) });
              else if (texti == 4)
                messages = String.format("_°18RR°%s_§ glaubt seinen Augen kaum, als W15 = _°20°%s_§ ergab. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf) });
              else if (texti == 5) {
                messages = String.format("_°18RR°%s_§ ist fassungslos, als man Erfahren hat, dass W15 = _°20°%s_§ ergab. _Ausgeschieden!_", new Object[] { client.getName(), Integer.valueOf(wurf) });
              }
              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { messages }));
              blitz.nextPlayer("1", channel, client);
            } else {
              String mehr = "0";
              if (wurf == zahl + 1)
                mehr = "1";
              else if (wurf == zahl - 1)
                mehr = "1";
              else {
                mehr = "0";
              }
              if (mehr.equals("1")) {
                Random randomt = new Random();
                int textz = randomt.nextInt(4);
                if (textz == 0)
                  mehr = " - °RR°°18°_Puh!_§ -";
                else if (textz == 1)
                  mehr = " - °RR°°18°_Beinahe!_§ -";
                else if (textz == 2)
                  mehr = " - °RR°°18°_Knapp!_§ -";
                else if (textz == 3)
                  mehr = " - °RR°°18°_Glück gehabt!_§ -";
              }
              else
              {
                mehr = "";
              }
              Random randomtt = new Random();
              int texty = randomtt.nextInt(3);
              if (texty == 0)
                messages = String.format("_°BB18°%s_§ ist überglücklich, als W15 = _°BB18°%s_§ ergab.%s ", new Object[] { client.getName(), Integer.valueOf(wurf), mehr });
              else if (texty == 1)
                messages = String.format("_°BB18°%s_§ freut sich, als W15 = _°BB18°%s_§ ergab.%s ", new Object[] { client.getName(), Integer.valueOf(wurf), mehr });
              else if (texty == 2) {
                messages = String.format("_°BB18°%s_§ hüpft vor Freunde, als W15 = _°BB18°%s_§ ergab.%s ", new Object[] { client.getName(), Integer.valueOf(wurf), mehr });
              }

              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { messages }));
              blitz.nextPlayer("0", channel, client);
            Server.get().query("insert into blitzzahlen set user = '"+client.getName()+"', zahl = '"+wurf+"'");
            

            }

          }
          else if ((blitz.activ2 == true) && (blitz.activ3 == true))
          {
              // das müsste finale sein
            Random random5 = new Random();
            int randomInt6 = random5.nextInt(75);
            int wurffinale = randomInt6 + 1;
            String mess = "";
            int knack = 0;
            int ende = 0;
            Random random2 = new Random();
            int randomInt3 = random2.nextInt(40);
            int punkte = randomInt3 + 11;
            if (wurffinale == zahl) {
              mess = "_°RR18°" + client.getName() + "_§ würfelt W75 = _°RR18°" + wurffinale + "°r° °18BB°und gewinnt augenblicklich den Blitz Jackpot mit " + blitz.getBlitzjp() + " Blitz Punkten!";
              knack = 1;
              client.increaseBlitzPoints(blitz.getBlitzjp());
              
              // HIER PRÜFEN für Highlight-Eintrag
              
              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { mess }));
          for(Client c : channel.getClients()) {
                List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/sfc "+channel.getName()+":/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            c.send(Toolbar.ToolbarString(channel.getName(), buttons));
           c.send(Toolbar.BingoStyle(channel.getName()));
           }
            }
            else if (wurffinale == zahl2) {
              mess = "°RR18°_" + client.getName() + "_§ würfelt W75 = _°RR18°" + wurffinale + "°r° °18BB°und gewinnt augenblicklich den Smiley °>ct/sm_ct_ehren-smiley-ani.gif<°!";
              knack = 2;
              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { mess }));
          String id = "";
               for(Smiley sm : Server.get().getSmileys()) {
            if (sm.getName2().equals("Special-Blitzsmiley")) {
                id = String.valueOf(sm.getID());
            }
        }
               if (!id.isEmpty()) {
          client.setSmiley(id);   
          client.setCodeE(client.getCodeE()+1);
          client.setHighlights(String.format("%s|%s Exklusiven Smiley gewonnen im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));             
         
               }
              
              
           for(Client c : channel.getClients()) {
                List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/sfc "+channel.getName()+":/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            c.send(Toolbar.ToolbarString(channel.getName(), buttons));
           c.send(Toolbar.BingoStyle(channel.getName()));
           }
            }
            else
            {
              mess = "_°RR18°" + client.getName() + "_§ würfelt W75 = _°RR18°" + wurffinale + "_§!";
              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("%s", new Object[] { mess }));
            }
            

            if (wurfels <= 1) {
              if (knack == 0) {
                channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("Der °BB°°20°_Jackpot_§ und der °BB°°20°_Smiley_§ wurden nicht gewonnen. °BB°°20°_" + client.getName() + "_§ erhält " + punkte + " Blitz Punkte, zusätzlich wandern " + punkte + " Punkte in den Jackpot!", new Object[0]));
             for(Client c : channel.getClients()) {
                List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/sfc "+channel.getName()+":/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            c.send(Toolbar.ToolbarString(channel.getName(), buttons));
           c.send(Toolbar.BingoStyle(channel.getName()));
           }
              }
              ende = 1;
              client.increaseBlitzPoints(punkte);
              
              // Hier Prüfen für Highlight-Eintrag
              
              
              if (client.getBlitzPoints() > 99 && client.getBlitzrank() ==0) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Neuling im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)1);
                 }
    if (client.getBlitzPoints() > 299 && client.getBlitzrank() ==1) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Anfänger im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)2);
                 }
    if (client.getBlitzPoints() > 499 && client.getBlitzrank() ==2) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Lehrling im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)3);
                 }
    if (client.getBlitzPoints() > 999 && client.getBlitzrank() ==3) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Hüttchenspieler im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)4);
                 }
    if (client.getBlitzPoints() > 1999 && client.getBlitzrank() ==4) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Fensterpolierer im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)5);
                 }
              
    if (client.getBlitzPoints() > 4999 && client.getBlitzrank() ==5) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Fugenschaber im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)6);
                 }
              
    if (client.getBlitzPoints() > 9999 && client.getBlitzrank() ==6) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Dieb im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)7);
                 }
               if (client.getBlitzPoints() > 14999 && client.getBlitzrank() ==7) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Schnorrer im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)8);
                 }
               
               if (client.getBlitzPoints() > 19999 && client.getBlitzrank() ==8) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Joker im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)9);
                 }
               
               if (client.getBlitzPoints() > 29999 && client.getBlitzrank() ==9) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Elektriker im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)10);
                 }
               if (client.getBlitzPoints() > 49999 && client.getBlitzrank() ==10) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Gutachter im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)11);
                 }
                               
               if (client.getBlitzPoints() > 99999 && client.getBlitzrank() ==11) {
                     client.setHighlights(String.format("%s|%s Erreichte den Rang Blitzableiter im Spiel Blitz!|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setBlitzrank((byte)12);
                 }
              
              
              
              
             int neuerjp = blitz.getBlitzjp() + punkte;
              Server.get().query("UPDATE `blitzsettings` SET runde='1', aktuell='1', next='', jackpot = '"+neuerjp+"'");
          
            } else {
                client.sendButlerMessage(channel.getName(),"Du kannst noch _°BB>_h"+(wurfels-1)+"x würfeln|/blitz wurf<r°_.");
            }
            if (knack != 0) {
              int neuerjp = 0;
              channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("Das Spiel endet sofort!", new Object[0]));
              ende = 1;
              if (knack == 1)
                neuerjp = 0;
              else {
                neuerjp = blitz.getBlitzjp();
              }
              Server.get().query("UPDATE `blitzsettings` SET runde='1', aktuell='1', next='', jackpot = '"+neuerjp+"'");
             
            }
      int neuerunden = wurfels - 1;
            Server.get().query("UPDATE `blitzdabei` SET wurfel = '"+neuerunden+"' where user = '"+client.getName()+"'");
          

            if (ende == 1) {
              blitz.activ = false;
              blitz.activ2 = false;
              blitz.activ3 = false;
              for (Client c : channel.getClients()) {
                c.send(PacketCreator.removePrefixIcons(channel.getName()));
              }
Server.get().query("truncate table blitzdabei");
Server.get().query("truncate table blitzzahlen");              
            
            }

          }
          else
          {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
          }

        }

      }
      else
      {
        client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
      }
    }
  }
}
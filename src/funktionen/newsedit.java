package funktionen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import starlight .*;
import java.util.List;
import java.util.ArrayList;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;

public class newsedit{
  public static void functionMake(Client client, Channel channel,String arg)  {

       String[] art = arg.split(":");
     if (art[0].equals("edit")) {
        PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
         String id = "";

       String an = "";
       String text = "";
        try {
           Connection con = pcon.connect();
           ps = con.prepareStatement("SELECT * FROM `news` where id = ?");
          ps.setString(1, art[1]);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            id = rs.getString("id");
            an = rs.getString("an");
            text = rs.getString("text");


          }
        }
        catch (SQLException e) {
         e.printStackTrace();
       } finally {
          if (ps != null)
            try {
             ps.close();
            }
            catch (SQLException e)
            {
            }
           pcon.close();
        }

        Popup popup = new Popup("News-Editor / News bearbeiten", "News-Editor / News bearbeiten", "", 400, 0);

        Panel panel7 = new Panel();
            TextArea a = new TextArea(15, 50);
            a.setText(text);
            panel7.addComponent(a);
            popup.addPanel(panel7);
            
       Panel panel7a = new Panel();
        panel7a.addComponent(new Label("News geht an Mitglieder ab dem Status:"));
        Choice lol2 = new Choice(new String[] { "0","1","2","3","4","5","6","7","8","9","10","11","12" }, "");
        lol2.setSelected(an);
        panel7a.addComponent(lol2);
        popup.addPanel(panel7a);
  
        
        
         Panel panel20 = new Panel();
        Button button = new Button("Speichern");
         Button button2 = new Button("Abbrechen");
         panel20.addComponent(button);
        panel20.addComponent(button2);
         button.enableAction();
        popup.addPanel(panel20);
         popup.setOpcode(ReceiveOpcode.NEWSEDIT.getValue(), "newsedit°" + id);
         client.send(popup.toString());
      }
       else if (art[0].equals("del")) {
           
           
        PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null; 
       

       String von = "";
       String an = "";
    String text = "";
    String uhrzeit = "";
        try {
           Connection con = pcon.connect();
           ps = con.prepareStatement("SELECT * FROM `news` where id = ?");
          ps.setString(1, art[1]);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
           
             von = rs.getString("von");
            an = rs.getString("an");
           text = rs.getString("text");
            uhrzeit = rs.getString("uhrzeit");

          }
        } catch (SQLException e) {  e.printStackTrace();   } finally { if (ps != null)    try { ps.close(); }  catch (SQLException e)  {  }  pcon.close();    }

        
          Server.get().query("delete from news where id = '"+art[1]+"'");
         
   Server.news.clear();
   // news neu laden
     PoolConnection pcon2 = ConnectionPool.getConnection();
        PreparedStatement ps2 = null; 
      
        try {
           Connection con2 = pcon2.connect();
           ps2 = con2.prepareStatement("SELECT * FROM `news");

          ResultSet rs2 = ps2.executeQuery();
          while (rs2.next()) {
              	Server.news.add(new String[] {rs2.getString("von"), rs2.getString("an"), rs2.getString("text"), rs2.getString("uhrzeit")});
			
                }
        } catch (SQLException e) {  e.printStackTrace();   } finally { if (ps2 != null)    try { ps2.close(); }  catch (SQLException e)  {  }  pcon2.close();    }

   

      PopupNewStyle popup = new PopupNewStyle("Erfolgreich", "Erfolgreich", "##Die News wurde erfolgreich gelöscht.", 400, 275);
         Panel panel = new Panel();
         panel.addComponent(new Button("   OK   "));
         popup.addPanel(panel);
         client.send(popup.toString());   
      }
     else if (arg.equals("neu"))
      {
        Popup popup = new Popup("News-Editor / Neue News", "News-Editor / Neue News", "Trage hier die neue News ein.##°>gg.png<° °12°_Bitte beachte die Recht-, Groß- und Kleinschreibung!_", 400, 150);

          Panel panel7 = new Panel();
          TextArea a = new TextArea(15, 50);
          panel7.addComponent(a);
          popup.addPanel(panel7);
            
        Panel panel7a = new Panel();
        panel7a.addComponent(new Label("News geht an Mitglieder ab dem Status:"));
        Choice lol2 = new Choice(new String[] { "0","1","2","3","4","5","6","7","8","9","10","11","12" }, "0");
        panel7a.addComponent(lol2);
        popup.addPanel(panel7a);
        
        
         Panel panel20 = new Panel();
         Button button = new Button("Speichern");
         Button button2 = new Button("Abbrechen");
        panel20.addComponent(button);
        panel20.addComponent(button2);
        button.enableAction();

       popup.addPanel(panel20);

         popup.setOpcode(ReceiveOpcode.NEWSEDIT.getValue(), "newsneu");
        client.send(popup.toString());
      }
      else
      {
         String text = "Willkommen im News-Editor _"+client.getName()+"_!#Hier kannst du neue News erstellen oder bereits vorhandene News bearbeiten und löschen.##°-°#";
         text = text + "°>finger.b.gif<° _°BB>Neue News erstellen|/newsedit neu<°_°r°##";
         PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
         int Zahl = 0;
        try {
           Connection con = pcon.connect();
          ps = con.prepareStatement("SELECT * FROM `news`");
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
            Zahl++;
            text = text + Zahl + ". °>_2" + rs.getString("uhrzeit") + " ("+rs.getString("von")+")|/newsedit edit:" + rs.getString("id") + "<° °>redcross.png<>--<>|/newsedit del:" + rs.getString("id") + "<°#";
         }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
          if (ps != null)
           try {
              ps.close();
            }
            catch (SQLException e)
            {
            }
          pcon.close();
       }
         Popup popup = new Popup("News-Editor", "News-Editor", text, 500, 400);
         Panel panel = new Panel();
         panel.addComponent(new Button("   OK   "));
         popup.addPanel(panel);
         popup.setNicknotexist(1);
         client.send(popup.toString());
      }

  }
}
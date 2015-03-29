package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import starlight.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;

public class NewsEditHandler {
  public static void handle(String[] tokens, Client client)   {
   if (tokens[1].contains("newsedit")) {
       String text = tokens[3].trim();
       String an = tokens[4].trim();
      
      String[] id = tokens[1].split("°", 2);
       String error = "";

      if (text.isEmpty()) {
         error = "Alle Felder müssen ausgefüllt werden.";
      }

    if (error.isEmpty()) {
       //  String popup = Popup.create("Gespeichert", "Gespeichert","Die Änderungen an dieser News wurden gespeichert.", 400, 200);
       //  client.send(popup);
         PopupNewStyle popup = new PopupNewStyle("Gespeichert", "Gespeichert", "##Die Änderungen an dieser News wurden gespeichert.", 400, 275);
         Panel panel = new Panel();
         panel.addComponent(new Button("   OK   "));
         popup.addPanel(panel);
         client.send(popup.toString());
        
         String von2 = "";
         String an2 = "";
         String text2 = "";
         String uhrzeit2 = "";
             PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        try {
           Connection con = pcon.connect();
           ps = con.prepareStatement("SELECT * FROM `news` where id = ?");
          ps.setString(1, id[1]);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
           
           von2 = rs.getString("von");
            an2 = rs.getString("an");
           text2 = rs.getString("text");
            uhrzeit2 = rs.getString("uhrzeit");

          }
        } catch (SQLException e) {  e.printStackTrace();   } finally { if (ps != null)    try { ps.close(); }  catch (SQLException e)  {  }  pcon.close();    }

  
        Server.get().query("update news set an='"+an+"',text='"+text+"' where id='"+id[1]+"'");
          Server.news.clear();

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

     
 

       

      }
      else
      {
      //  String popup = Popup.create("Problem", "Problem",String.format("%s", error), 400, 320);
      //   client.send(popup);
         PopupNewStyle popup = new PopupNewStyle("Problem", "Problem",String.format("##Bei der Änderung der News sind folgende#_Probleme_ aufgetreten:##"+error.toString()), 400, 275);
         Panel panel = new Panel();
         panel.addComponent(new Button("   OK   "));
         popup.addPanel(panel);
         client.send(popup.toString());  
      }
    } else if (tokens[1].equals("newsneu")) {
       String text = tokens[3].trim();
       String an = tokens[4].trim();
      
       String error = "";

 
 if (text.isEmpty()) {
         error = "Alle Felder müssen ausgefüllt werden.";
      }
 

       if (error.isEmpty()) {
        // String popup = Popup.create("Erfolgreich", "Erfolgreich","Die News wurde erfolgreich gespeichert.", 400, 50);
        // client.send(popup);
         PopupNewStyle popup = new PopupNewStyle("Erfolgreich", "Erfolgreich", "##Die News wurde erfolgreich erstellt.", 400, 275);
         Panel panel = new Panel();
         panel.addComponent(new Button("   OK   "));
         popup.addPanel(panel);
         client.send(popup.toString());
       
         
         long timestamp = System.currentTimeMillis()/1000;
       String uhrzeit = Server.get().timeStampToDate(timestamp)+" "+Server.get().timeStampToDateLong(timestamp);
             Server.news.add(new String[] {client.getName(),an,text,uhrzeit});
Server.get().query("insert into news set von='"+client.getName()+"',an='"+an+"',text='"+text+"', uhrzeit='"+uhrzeit+"'");

// accounts updat newsperrre xDe
int anrank = Integer.parseInt(an);
 PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT rank,name,newssperre FROM `accounts`");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next()) {
     if (rs3.getInt("rank") >= anrank) {
          Client target = Server.get().getClient(rs3.getString("name"));
      
          if (target != null) {
              target.setNewssperre((byte)0);
          } else {
              Server.get().query("update accounts set newssperre='0' where name='"+rs3.getString("name")+"'");
          }
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
      else
       {
       //  String popup2 = Popup.create("Problem", "Problem",String.format("%s", error), 400, 100);
      //  client.send(popup2);
         PopupNewStyle popup = new PopupNewStyle("Problem", "Problem",String.format("##Bei der Änderung der News sind folgende#_Probleme_ aufgetreten:##"+error.toString()), 400, 275);
         Panel panel = new Panel();
         panel.addComponent(new Button("   OK   "));
         popup.addPanel(panel);
         client.send(popup.toString());   
              }
   
   
   
  }}
}

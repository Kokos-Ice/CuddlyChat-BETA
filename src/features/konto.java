package features;

import static features.hero.timeStampToDate;
import static funktionen.f.time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import starlight.*;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;


public class konto {
    
    
    public static void KontoVermehrung() {
        String tag = new SimpleDateFormat("dd").format(new Date());
        if (tag.equals("01")) {
       PoolConnection pcon2 = ConnectionPool.getConnection();
        PreparedStatement ps2 = null;
      
        try {
           Connection con = pcon2.connect();
           ps2 = con.prepareStatement("SELECT * FROM `accounts`");
          ResultSet rs = ps2.executeQuery();
          while (rs.next()) {
             String nickname = KCodeParser.escape(rs.getString("name"));
      boolean online = true;
       Client target = Server.get().getClient(nickname);
       if (target == null) {
         online = false;
        target = new Client(null);
        target.loadStats(nickname);
      }   
       if (target.getName() != null) {
       
        String[] l = target.getFeature("Schweizer-Konto");
        if (l[0].equals("2")) {
            if (target.getKontoKnuddels() >= 1) {
                double pw = (double)target.getKontoKnuddels()*10/100;
             
        int neu = (int)pw;
                
        
        if (neu >= 1) {
         
            
           Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Zinsen, Zinsen, Zinsen...", String.format("Zahltag auf deinem _Schweizer Knuddelskonto_:##Deine Knuddel haben sich dadurch _um °R°"+neu+" °r°Knuddel vermehrt_!##Auf dein Konto kannst du jederzeit mit _°BB>_h/konto|/konto<°_°r° zugreifen.##Dein %s", Server.get().getButler().getName()), time);         
            target.setKontoKnuddels(target.getKontoKnuddels()+neu);
            target.saveStats();
        }
        
                
            }
            
            
        }
        
        }}
        }
        catch (SQLException e) {
         e.printStackTrace();
       } finally {
          if (ps2 != null)
            try {
             ps2.close();
            }
            catch (SQLException e)
            {
            }
           pcon2.close();
        } 
    }
    }
    
    
    public static void functionMake(Client client,Channel channel, String arg) {
   String[] l = client.getFeature("Schweizer-Konto");
 Feature ft = Server.get().getFeature("Schweizer-Konto");
 
 if (ft == null) {
     return;
 }
 
 if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   } 
        	
        	if(client.getKontoPassword().isEmpty()) {
        		client.send(PacketCreator.kontoPw(client.getName()));
        		return;
        	}

    		client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
               
          
      }}
package features;

import static features.hero.timeStampToDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;


public class fanifybutler {
      public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
      
      
      
        String[] l = client.getFeature("Butler-Fan");
 Feature ft = Server.get().getFeature("Butler-Fan");
 
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
 
 
 boolean on = true;
 Client target = Server.get().getClient(arg);
 if (target == null) {
     target = new Client(null);
     on = false;
     target.loadStats(arg);
 }
 
 if (arg.isEmpty()) {
  client.sendButlerMessage(channel.getName(),"Verwende _/fanifybutler NICK_ um James Fan von Nick werden zu lassen.");
     return;
 }
    if(target.getName() == null) {
        	client.sendButlerMessage(channel.getName(), unknownUser(arg));
                return;
                }
            
        	
        if(!on) {
        	client.sendButlerMessage(channel.getName(), userIsOffline(target));
        	return;
            }

 
 if (target == client) {
   client.sendButlerMessage(channel.getName(),"Mit dir selbst geht das aber nicht.");
     return;
 }
 if (target == Server.get().getButler()) {
      client.sendButlerMessage(channel.getName(),String.format(Server.get().getButler().getName()+" kannst du nicht auswählen."));
     return;
 }

 boolean isFan = false;
  PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `fans` where von='"+Server.get().getButler().getName()+"' and an='"+target.getName()+"'");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next()) {
       isFan = true;
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
    
   if (isFan) {
       client.sendButlerMessage(channel.getName(),Server.get().getButler().getName()+" ist bereits ein Fan von "+target.getName());
       return;
   } 
    String text = "Ich bin hocherfreut, dass "+client.getName()+" uns einander vorgestellt hat und ich deine Bekanntschaft machen durfte. Ich hoffe, wir führen noch viele interessante Konversationen.\n\nHochachtungsvoll, dein "+Server.get().getButler().getName();
 Server.get().query("insert into fans set von='"+Server.get().getButler().getName()+"', an='"+target.getName()+"', time='"+(System.currentTimeMillis()/1000)+"',typ='foto',text='"+text+"'");
 target.setFans(target.getFans()+1);
 target.saveStats();
 target.sendButlerMessage(channel.getName(),"Soeben ist "+Server.get().getButler().getName()+" _°BB°°>_hdein Fan|http://heaven24.zapto.org/index.php?page=photo_fans&n="+target.getName()+"&photo<°°r°_ geworden");   
 Server.get().newMessage(Server.get().getButler().getName(), target.getName(),"", "Hallo "+target.getName()+",##soeben ist "+Server.get().getButler().getName()+" _°BB°°>_hdein Fan|http://heaven24.zapto.org/index.php?page=photo_fans&n="+target.getName()+"&photo<°°r°_ geworden und hat dir außerdem folgende Nachricht hinterlassen:##''Ich bin hocherfreut, dass "+client.getName()+ "uns einander vorgestellt hat und ich deine Bekanntschaft machen durfte. Ich hoffe, wir führen noch viele interessante Konversationen.##Hochachtungsvoll, dein "+Server.get().getButler().getName()+"''", (System.currentTimeMillis()/1000));      
 client.sendButlerMessage(channel.getName(),Server.get().getButler().getName()+" ist ab sofort ein Fan von "+target.getName());
 
 
      }
      }
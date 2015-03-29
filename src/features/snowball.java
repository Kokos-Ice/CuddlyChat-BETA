package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class snowball {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
           // wichtig            
  String[] l = client.getFeature("Snowball");
 Feature ft = Server.get().getFeature("Snowball");
 
 if (ft == null) {
     // kick vermeiden
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
              // ende    
        	
        	
        	
        	arg = KCodeParser.escape(arg);
        	Client target = Server.get().getClient(arg);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}
        	
        	if(target == client || target == Server.get().getButler()) {
        		client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht mit einen Schneeball bewerfen.", target.getName()));
        		return;
        	}

            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }  
               client.setSnowball(true);
               channel.broadcastButlerMessage(String.format("°>_h%s|/serverpp \"|/w \"<° bewirft °>_h%s|/serverpp \"|/w \"<° mit einen Schneeball.°>{sprite}type:snowball|nick:%s<°", client.getName(), target.getName(), target.getName()));
              ft.setBan(l[1],l[3],l[4],client); // setz sperre

                
              
          
      }}
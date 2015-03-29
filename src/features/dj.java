package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class dj {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
          	   // wichtig            
  String[] l = client.getFeature("DJ");
 Feature ft = Server.get().getFeature("DJ");
 
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
        	
        	if(arg.equals("stop")) {
        		client.sendButlerMessage(channel.getName(), "Der Song wurde angehalten.");
        		client.send(PacketCreator.playMp3(null));
        		return;
        	}
          
      }
      }
package funktionen;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class topic {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   
          if(!client.hasPermission("cmd.topic") && !channel.checkHz(client.getName())) { 
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/topic TEXT oder !#(Ändert das Channelthema in TEXT oder löscht es.)");
        		return;
        	}
        	
            if (arg.equals("!")) {
                client.sendButlerMessage(channel.getName(), "Das Thema wurde gelöscht.");
                channel.setTopic(null);
                return;
            }
            
            arg = Server.get().parseSmileys(client, arg);
            channel.setTopic(arg);
      
          
      }}
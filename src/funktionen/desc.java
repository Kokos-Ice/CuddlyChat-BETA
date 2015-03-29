package funktionen;

import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class desc {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   
          if(!client.hasPermission("cmd.desc")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
            arg = KCodeParser.parse(client, arg, 0, 10, 20);
            
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Deine _Beschreibung_ wurde _entfernt_.");
        		client.setDesc(null);
        		return;
        	}
        	
        	if(arg.length() > 200) {
        		client.sendButlerMessage(channel.getName(), "Deine Beschreibung darf maximal 200 Zeichen betragen.");
        		return;
        	}
        	
        	arg = Server.get().parseSmileys(client, arg);
        			
        	client.sendButlerMessage(channel.getName(), String.format("_Beschreibung hinzugefügt_: %s#Du kannst sie mit _°BB>/fotowhois %s|\"<r°_ abrufen.", arg, client.getName().replace("<", "\\<")));
        	client.setDesc(arg);
          
      }}
package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class action {


    public static void functionMake(Client client,Channel channel, String arg) {
        
   if(!client.hasPermission("cmd.action")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/action TEXT#(Manipuliert eine Aktion mit dem vorgegebenen TEXT im aktuellen Channel.)");
        		return; 
        	}
        	Server.get().newSysLogEntry(client.getName(), String.format("Broadcast-Action: (%s) ausgeführt", arg, client.getName()));       
        	channel.broadcastAction(">", Server.get().parseSmileys(client, arg));

    
    }
    }
package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class clearcomments {


    public static void functionMake(Client client,Channel channel, String arg) {
    if(!client.hasPermission("cmd.clearcomments")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/clearcomments NICK#(Alle Adminkommentare von NICK löschen.)");
        		return;
        	}
        	
        	Client target = Server.get().getClient(arg);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(arg));
        			return;
        		}
        	}
        	
        	if(target.getComments().isEmpty()) {
        		client.sendButlerMessage(channel.getName(), String.format("%s verfügt über keinerlei Adminkommentare.", target.getName()));
        		return;
        	}
        	
        	client.sendButlerMessage(channel.getName(), String.format("Alle Adminkommentare von %s wurden gelöscht.", target.getName()));
        	
        	if(online) {
        		target.setComments("");
        	} else {
        		Server.get().query(String.format("UPDATE `accounts` SET `comments` = '' WHERE `name` = '%s'", target.getName()));
        	}
        	
        	Server.get().newSysLogEntry(client.getName(), String.format("Admininfo von %s gelöscht", target.getName()));
    
    }
    }
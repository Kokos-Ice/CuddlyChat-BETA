package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class clearsyscomments {


    public static void functionMake(Client client,Channel channel, String arg) {
    if(!client.hasPermission("cmd.clearsyscomments")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/clearsyscomments NICK#(Alle SysAdminkommentare von NICK löschen.)");
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
        	
        	if(target.getSyscomments().isEmpty()) {
        		client.sendButlerMessage(channel.getName(), String.format("%s verfügt über keinerlei SysAdminkommentare.", target.getName()));
        		return;
        	}
        	
        	client.sendButlerMessage(channel.getName(), String.format("Alle SysAdminkommentare von %s wurden gelöscht.", target.getName()));
        	
        	if(online) {
        		target.setSyscomments("");
        	} else {
        		Server.get().query(String.format("UPDATE `accounts` SET `syscomments` = '' WHERE `name` = '%s'", target.getName()));
        	}
        	
        	Server.get().newSysLogEntry(client.getName(), String.format("SysAdmininfo von %s gelöscht", target.getName()));
    
    }
    }
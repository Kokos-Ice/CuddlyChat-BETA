package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class auth {


    public static void functionMake(Client client,Channel channel, String arg) {




if(!client.hasPermission("cmd.auth")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String oldPw = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/auth Alter Sicherheitscode:Neuer Sicherheitscode";
            
            if (arg.length() > oldPw.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(oldPw.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), info);
            	return;
            }
            
            if(oldPw.equals("+")) {
            	client.sendButlerMessage(channel.getName(), "Die Sicherheitsabfrage wurde _aktiviert_.");
            	client.setAuthActive((byte)1);
                Server.get().newSysLogEntry(client.getName(), String.format("Sicherheitsabfrage ist jetzt aktiviert", client.getName()));
            	return;
            }
            
            if(oldPw.equals("-")) {
            	client.sendButlerMessage(channel.getName(), "Die Sicherheitsabfrage wurde _deaktiviert_.");
            	client.setAuthActive((byte)0);
                 Server.get().newSysLogEntry(client.getName(), String.format("Sicherheitsabfrage ist jetzt deaktiviert", client.getName()));
            	return;
            }

            if(msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), info);
            	return;
            }
            
            if(!HexTool.hash("SHA1", oldPw).equals(client.getAuthPassword())) {
            	client.sendButlerMessage(channel.getName(), "Dein aktueller Sicherheitscode Password ist falsch.");
            	return;
            }
            
        	client.sendButlerMessage(channel.getName(), "Dein Sicherheitscode wurde _geändert_.");
        	client.setAuthPassword(HexTool.hash("SHA1", msg));
}
}
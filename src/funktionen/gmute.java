
package funktionen;

import static funktionen.his.time;
import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class gmute {


    public static void functionMake(Client client,Channel channel, String arg) {




 if(!client.hasPermission("cmd.set.gmute")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
            }
        	
            String[] pieces = arg.split(":", 3);
            String nick = "", comment = "", tage = "";
            boolean entsperren = false;
            
            try {
            	nick = pieces[0];
            	comment = pieces[1];
            	tage = pieces[2];
            } catch(Exception ex) {
            }
            
            if(nick.isEmpty() || comment.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/gmute NICK:COMMENT:#(Setzt Nick bis zum Serverneustart auf die Globale-Muteliste.");
            	return;
            }
            if(nick.startsWith("!")) {
            	entsperren = true;
            	nick = nick.replace("!", "");
            }
            
            Client target = Server.get().getClient(nick);
            boolean online = true;
            
            if(target == null) {
            	online = false;
            	target = new Client(null);
            	target.loadStats(nick);
            	
            	if(target.getName() == null) {
            		client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nick));
            		return;
            	}
            }
            
            comment = KCodeParser.parse(client, comment, 5, 10, 20);
            comment = Server.get().parseSmileys(client, comment);
            
            if(entsperren) {
            	if(target.getGmute() == 0) {
            		client.sendButlerMessage(channel.getName(), String.format("%s hat momentan keinen Gamelock.", target.getName()));
            		return;
            	}
            	
            	client.sendButlerMessage(channel.getName(), String.format("%s wurde für alle Spiele _entsperrt_.", target.getName()));
            	Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Globalmute aufgehoben", String.format("%s hat deine Globalmute soeben aufgehoben.", client.getName()), time);
            	
            	if(online) {
            		target.setGmute(0);
            	} else {
            		Server.get().query(String.format("update accounts set gmute='0' where name='%s'", target.getName()));
            	}
            	
            	target.setComment(time, null, "Global-Entmutet!", client.getName(), comment);
            	return;
            }
            
            if(target == Server.get().getButler() || target == client || target.getRank() > 4) {
            	client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht Globalmuten.", target.getName()));
            	return;
            }

            if(target.getGmute() != 0) {
            	client.sendButlerMessage(channel.getName(), String.format("%s ist bereits auf der Globalmuteliste.", target.getName()));
            	return;
            }
            
            if(tage.isEmpty()) {
            	tage = "1";
            }
            
            try {
            	Integer.parseInt(tage);
            } catch(Exception ex) {
            	client.sendButlerMessage(channel.getName(), "Du musst eine Zahl angeben.");
            	return;
            }
            
            int days = Integer.parseInt(tage);
            long when = (int) (time+(days*86400));
            
            if(days < 1) {
            	client.sendButlerMessage(channel.getName(), "Du musst eine Zahl >= 1 angeben.");
            }
            
            client.sendButlerMessage(channel.getName(), String.format("%s wurde soeben auf der _Globalen Muteliste_ gesetzt.", target.getName(), days));
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Globalmute", String.format("%s hat dich bis morgen früh Global-Gemutet.", client.getName(), Server.get().timeStampToDate(when)), time);
        	
        	if(online) {
        		target.setGmute(when);
        	} else {
        		Server.get().query(String.format("update accounts set gmute='%s' where name='%s'", when, target.getName()));
        	}
        	
        	target.setComment(time, null, String.format("Globalmute!", days), client.getName(), comment);
    }
}
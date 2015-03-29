package funktionen;

import static funktionen.his.time;
import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class gamelock {


    public static void functionMake(Client client,Channel channel, String arg) {
 
     if(!client.hasPermission("cmd.gamelock")) {
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
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/gamelock NICK:COMMENT:TAGE#(Sperrt NICK für TAGE für alle Chatspiele. COMMENT wird als Adminkommentar bei NICK eingetragen. Ist TAGE nicht angegeben, wird NICK für 3 Tage gesperrt. Mit !NICK:COMMENT entsperrt man NICK wieder.)");
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
            	if(target.getSpielsperre() == 0) {
            		client.sendButlerMessage(channel.getName(), String.format("%s hat momentan keinen Gamelock.", target.getName()));
            		return;
            	}
            	
            	client.sendButlerMessage(channel.getName(), String.format("%s wurde für alle Spiele _entsperrt_.", target.getName()));
            	Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Gamelock aufgehoben", String.format("Du wurdest von %s wieder _für alle Spiele entsperrt_.", client.getName()), time);
            	
            	if(online) {
            		target.setSpielsperre(0);
            	} else {
            		Server.get().query(String.format("update accounts set spielsperre='0' where name='%s'", target.getName()));
            	}
            	
            	target.setComment(time, null, "°B°\"Gamelock\" aufgehoben!", client.getName(), comment);
            	return;
            }
            
            if(target == Server.get().getButler() || target == client || target.getRank() > 4) {
            	client.sendButlerMessage(channel.getName(), String.format("Du kannst %s keinen Gamelock geben.", target.getName()));
            	return;
            }

            if(target.getSpielsperre() != 0) {
            	client.sendButlerMessage(channel.getName(), String.format("%s ist bereits für alle Spiele gesperrt.", target.getName()));
            	return;
            }
            
            if(tage.isEmpty()) {
            	tage = "3";
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
            
            client.sendButlerMessage(channel.getName(), String.format("%s wurde soeben für _%s Tage für alle Spiele gesperrt_.", target.getName(), days));
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Gamelock", String.format("Du wurdest von %s bis zum _%s für alle Spiele gesperrt_.##Grund:#%s", client.getName(), Server.get().timeStampToDate(when), comment), time);
        	
        	if(online) {
        		target.setSpielsperre(when);
        	} else {
        		Server.get().query(String.format("update accounts set spielsperre='%s' where name='%s'", when, target.getName()));
        	}
        	
        	target.setComment(time, null, String.format("°B°\"(°R°%s Tage°B°)\" Gamelock!", days), client.getName(), comment);
        
        
                
                
    
    
    }
   
    }
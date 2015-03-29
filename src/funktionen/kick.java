package funktionen;

import static funktionen.his.time;
import java.text.*;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.parse;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class kick {


    public static void functionMake(Client client,Channel channel, String arg) {
     




if(!client.hasPermission("cmd.kick")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            String recipients = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/kick NICK:TEXT#(Wirft NICK aus allen Channels. TEXT wird als Kommentar in der Admininfo vermerkt. Zusätzlich wird die IP von NICK bei der Registrierung und im Chat gesperrt.)";
            
            if (arg.length() > recipients.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if (recipients.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            String nickname = recipients;
            Client target = Server.get().getClient(recipients);
            boolean online = true;
            
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(!online) {
            	client.sendButlerMessage(channel.getName(), userIsOffline(target));
            	return;
            }
            
            if(msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            if(target == client || target.getRank() >= 4) {
                client.sendButlerMessage(channel.getName(), String.format("%s kann nicht gekickt werden.", target.getName())); 
                return;
            }
            
            if(msg.length() > 350) {
                client.sendButlerMessage(channel.getName(), "Der Kommentar darf höchstens 350 Zeichen lang sein!"); 
                return;
            }
            

            client.sendButlerMessage(channel.getName(), String.format("%s wurde aus allen Channels geworfen.", target.getName()));
            
            parse("/h knigge", target, target.getChannel(), false);
            parse("/h agb", target, target.getChannel(), false);
            target.send(PacketCreator.openURL(String.format("%sagb", Server.get().getURL()), "_blank"));
            target.setKicks(target.getKicks()+1);
            target.logout("Sie wurden rausgeworfen.");
            target.setComment(time, null, "Kick!", client.getName(), msg);
        	Settings.setBan(String.format("%s|%s|", Settings.getBan(), target.getIPAddress()));
            Server.get().query(String.format("insert into register set ip='%s'", target.getIPAddress()));
       
        
    }
}

        	
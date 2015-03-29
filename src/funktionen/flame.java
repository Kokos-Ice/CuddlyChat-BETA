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

public class flame {


    public static void functionMake(Client client,Channel channel, String arg) {
     


if(!client.hasPermission("cmd.flame")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            String recipients = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/flame NICK:TEXT#(Schickt NICK eine Androhung rechtlicher Konsequenzen. TEXT wird als Kommentar in der Admininfo von NICK hinterlassen.)";
            
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
            
            if(target == client || target.getRank() >= client.getRank()) {
                client.sendButlerMessage(channel.getName(), String.format("Du kannst %s keine Androhung schicken!", target.getName())); 
                return;
            }
            
            if(msg.length() > 350) {
                client.sendButlerMessage(channel.getName(), "Der Kommentar darf höchstens 350 Zeichen lang sein!"); 
                return;
            }
            

            client.sendButlerMessage(channel.getName(), String.format("Die Androhung wurde an %s gesendet.", target.getName()));
            String text = String.format("#°R°_Ihre IP-Adresse ist %s.#Ich werde morgen früh eine Abusemail mit den Logfiles und Androhung rechtlicher Konsequenten an Ihren Provider schicken, wenn Sie nicht augenblicklich Ihre Störungen dieses Chats einstellen!##Letzte WARNUNG!", target.getIPAddress());
        	target.setComment(time, null, "Flame!", client.getName(), msg);
        	Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Abmahnung Ihres Missbrauchs", text, time);
        	
            parse("/h knigge", target, target.getChannel(), false);
            parse("/h agb", target, target.getChannel(), false);
            String title = ">>> ACHTUNG <<<";
            target.setFlames(target.getFlames()+1);
            
   
             Popup popup = new Popup(title, title, text.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setGullideckel(1);
                 target.send(popup.toString());
                 return;
            
    }
}
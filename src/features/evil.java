package features;

import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;






public class evil {
    private static Random zufall = new Random();
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Evil-Message");
    Feature ft = Server.get().getFeature("Evil-Message");
 
      // hier annehmen ablehnen
    
    
     

            if(client.getWeckMessage() == 1) {
            	client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature momentan nicht benutzen.");
            	return;
            }
            
        	String nick = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nick.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(nick.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/evil +NICK:TEXT#(NICK eine Teufelsnachricht mit der Nachricht TEXT übermitteln.)");
            	return;
            }
            
            if(nick.startsWith("+")) {
            	nick = nick.substring(1);
            }
            
            Client target = Server.get().getClient(nick);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(nick);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(nick));
        			return;
        		}
        	}

            if(target == client) {
                client.sendButlerMessage(channel.getName(), "Du kannst dir selbst keine Teufelsnachricht schicken.");
                return;
            }
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}

        	client.sendButlerMessage(channel.getName(), String.format("Deine bei _°BB>_h%s|/serverpp \"|/w \"<r°_ Teufelsnachricht startet sofort....°>|{backgroundad|features/mephistos_laugh/ft_11-10_mephisto_bg_bright.jpg|features/mephistos_laugh/ft_11-10_mephisto_bg_bright.jpg|features/mephistos_laugh/ft_11-10_mephisto_bg_dark.jpg|features/mephistos_laugh/ft_11-10_mephisto_bg_dark.jpg|0|0|0|0|1|10|x|%s|255,180,0|255,180,0|30|0|0<°°>pics/features/mephistos_laugh/laugh_002.mp<°", target.getName().replace("<", "\\<"), msg));
        	
        	for(Channel s : target.getChannels()) {
            	target.sendButlerMessage(s.getName(), String.format("°>pics/features/mephistos_laugh/laugh_002.mp<°_°BB>_h%s|/serverpp \"|/w \"<r°_ sendet dir eine Teufelsnachricht...°>|{backgroundad|features/mephistos_laugh/ft_11-10_mephisto_bg_bright.jpg|features/mephistos_laugh/ft_11-10_mephisto_bg_bright.jpg|features/mephistos_laugh/ft_11-10_mephisto_bg_dark.jpg|features/mephistos_laugh/ft_11-10_mephisto_bg_dark.jpg|0|0|0|0|1|10|x|%s|255,180,0|255,180,0|30|0|0<°°>pics/features/mephistos_laugh/laugh_003.mp<°", client.getName(), msg));
        	}             
             
      }      
  } 

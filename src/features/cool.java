package features;

import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import starlight.*;

import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class cool {
    
      private static List<Pair<String, Integer>> icons;

    public static void addIcon(String icon, int size, Client nick, Channel channel) {
        Pair<String, Integer> pair = new Pair<String, Integer>(icon, size);

        icons = new ArrayList<Pair<String, Integer>>();
        icons.add(pair);
        
        if(channel == null) {
        	for (Channel channelz : nick.getChannels()) {
            	for (Client target : channelz.getClients()) {
            		if(target != Server.get().getButler()) {
            			target.send(PacketCreator.addIcon(channelz.getName(), nick.getName(), pair));
            		}
            	}
        	}
        } else {
            for (Client target : channel.getClients()) {
            	if(target != Server.get().getButler()) {
            		target.send(PacketCreator.addIcon(channel.getName(), nick.getName(), pair));
            	}
            }
        }
    }
    
    public static void removeIcon(String icon, String name, Channel channel) {
    	Client nick = Server.get().getClient(name);
    	
    	if(channel == null) {
    		for (Channel channelz : nick.getChannels()) {
    			for (Client target : channelz.getClients()) {
    				if(target != Server.get().getButler()) {
            			target.send(PacketCreator.removeIcon(channelz.getName(), nick.getName(), icon));
            		}
            	}
        	}
        } else {
    		for (Client target : channel.getClients()) {
    			if(target != Server.get().getButler() && nick != null) {
            		target.send(PacketCreator.removeIcon(channel.getName(), nick.getName(), icon));
            	}
        	}
        }
    }
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
          
             // wichtig            
  String[] l = client.getFeature("Cool");
 Feature ft = Server.get().getFeature("Cool");
 
 if (ft == null) {
     // kick vermeiden
     return;
 }
 
 if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   } 
              // ende    
        	
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/cool NICK:TEXT";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(nickname.isEmpty() || msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), info);
            	return;
            }
            
            
            
        	Client lol = Server.get().getClient(nickname);
        	boolean online = true;
        	
        	if(lol == null) {
        		online = false;
        		lol = new Client(null);
        		lol.loadStats(nickname);
        		
        		if(lol.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}

        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(lol));
				return;
			}
            
            if (!channel.getClients().contains(lol)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", lol.getName()));
                return;
            }
        	
        	if(lol == client) {
        		client.sendButlerMessage(channel.getName(), "Du kannst dich selbst nicht cool finden.");
				return;
        	}
        	
        	msg = KCodeParser.parse(client, msg, 5, 10, 20);
            msg = Server.get().parseSmileys(client, msg);
            
        	channel.broadcastAction(Server.get().getButler().getName(), String.format("findet °>_h%s|/serverpp \"|/w \"<° heute °BB°_extreeem cooool_%s°>sounds/sexy.mp<° und gibt die Nachricht mit: \"\"%s\"\".", lol.getName().replace("<", "\\<"), lol.getPhoto().isEmpty()?"°r°":String.format(" °BB>photos/photo/getPicture.php?m&img=%s...center_140.shadow_4.border_3.jpg<>--<>|%sindex.php?page=photo_user&n=%s&photo<r°°>abo/cool_photo-overlay...b.mx_-60.my_30.w_10.png<°", lol.getPhoto(), Server.get().getURL(), lol.getName().replace("<", "\\<")), msg));
        	icons = new ArrayList<Pair<String, Integer>>();
        	addIcon("pics/cool.gif", 29, lol, null);
        	Server.cool.add(lol.getName());
        	ft.setBan(l[1],l[3],l[4],client); // setz sperre
                  
                
                
      }
      }
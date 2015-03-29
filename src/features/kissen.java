package features;

import java.util.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;

public class kissen {
  
    
    public static void functionMake(Client client,Channel channel, String arg) {
  Long time = System.currentTimeMillis()/1000; 
   
        if (!client.checkCode(165) && !client.checkCode(166) && !client.hasPermission("cmd.kissen") && !client.hasPermission("cmd.kissen.global") && !client.hasPermission("cmd.kissen.channel")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		return;
        	}
        	
        	if(arg.equals("+global")) {
        		if (!client.checkCode(166) && !client.hasPermission("cmd.kissen.global")) {
            		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            		return;
            	}
        		
            	client.sendButlerMessage(channel.getName(), "Eine °RR°_globale°r°_ Kissenschlacht wurde _gestartet_.");
        		
            	for(Channel c : Server.get().getChannels()) {
            		if(c.countClients() > 1) {
            			if(!c.getPfActive() && c.getGameName().isEmpty()) {
            				c.setPfActive(true);
            				c.setPfStarter(client.getName());
            			}
            		}
            	}
            	
            	return;
        	}

        	if(arg.equals("+channel")) {
        		if (!client.checkCode(165) && !client.hasPermission("cmd.kissen.channel")) {
            		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            		return;
            	}
        		
        		if(!channel.getGameName().isEmpty()) {
        			client.sendButlerMessage(channel.getName(), "In diesem Channel kann keine Kissenschlacht gestartet werden.");
        			return;
        		}
        		
        		if(channel.getPfActive()) {
                	client.sendButlerMessage(channel.getName(), "In diesem Channel findet momentan bereits eine Kissenschlacht statt.");
        			return;
        		}
        		
            	client.sendButlerMessage(channel.getName(), "Eine Kissenschlacht wurde _gestartet_.");
            	channel.setPfActive(true);
    			channel.setPfStarter(client.getName());
        		return;
        	}

        	if (!client.checkCode(165) && !client.checkCode(166) && !client.hasPermission("cmd.kissen") && !client.hasPermission("cmd.kissen.global") && !client.hasPermission("cmd.kissen.channel")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	if(arg.equals("+join")) {
        		if(!channel.getPfActive()) {
        			client.sendButlerMessage(channel.getName(), "In diesem Channel findet momentan keine Kissenschlacht statt.");
        		} else if(channel.getPfSpieler().contains(String.format("|%s|", client.getName()))) {
        			client.sendButlerMessage(channel.getName(), "Du bist bereits angemeldet.");
                                
                         } else if(client.getSpielsperre() != 0) {       
                                client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
                                                      
        		} else if(channel.getPfStatus() != 1) {
        			client.sendButlerMessage(channel.getName(), "Eine Anmeldung ist nicht mehr möglich.");
        		} else {
        			client.sendButlerMessage(channel.getName(), "Du hast dich erfolgreich angemeldet.");
        			
        			for(Client c : channel.getClients()) {
        				List<String> prefix = new ArrayList<String>();
        				prefix.add(String.format("%s~nf.gif~nf.gif~nf.gif", client.getName()));
        				c.send(PacketCreator.showPrefixIcons(channel.getName(), prefix,true));
        			}
        			
        			channel.setPfSpieler(String.format("%s|%s|", channel.getPfSpieler(), client.getName()));
        		}
        		
        		return;
        	}
        	
        	if(!channel.getPfActive()) {
    			client.sendButlerMessage(channel.getName(), "In diesem Channel findet momentan keine Kissenschlacht statt.");
    			return;
        	}
        	
        	if(client.getPfGeworfen() != 0 && (client.getPfGeworfen()+5)-time > 0 || channel.getPfStatus() != 3 || !channel.getPfSpieler().contains(String.format("|%s|", client.getName()))) {
    			client.sendButlerMessage(channel.getName(), "Du kannst momentan kein Kissen werfen.");
    			return;
        	}
        	
        	Client target = Server.get().getClient(arg);
        	
        	if(target == null) {
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}
        	
        	if(target == client) {
        		client.sendButlerMessage(channel.getName(), "Du kannst dich nicht selbst abwerfen!");
        		return;
        	}
        	
        	if(!channel.getPfSpieler().contains(String.format("|%s|", target.getName()))) {
    			client.sendButlerMessage(channel.getName(), String.format("%s ist kein Mitspieler!", target.getName()));
    			return;
    		}
        	
        	target.setPfLeben((byte) (target.getPfLeben()-1));
        	channel.broadcastAction(client.getName(), String.format("hat %s mit einem großen Kissen mitten ins Gesicht getroffen! °>pf.gif<° %s", target.getName(), target.getPfLeben()==0?"_Ausgeschieden!":""));
        	client.sendButlerMessage(channel.getName(), String.format("Du hast _%s getroffen_! %s %s", target.getName(), target.getGenderLabel(), target.getPfLeben()==0?"ist _ausgeschieden_.":String.format("hat noch _%s Leben_.", target.getPfLeben())));
        	target.sendButlerMessage(channel.getName(), String.format("Du wurdest von _%s getroffen_! Du %s", client.getName(), target.getPfLeben()==0?"bist _ausgeschieden_.":String.format("hast noch _%s Leben_.", target.getPfLeben())));
        	client.setPfGeworfen(time);
        	
        	if(target.getPfLeben() == 2) {
    			for(Client c : channel.getClients()) {
    				List<String> prefix = new ArrayList<String>();
    				prefix.add(String.format("%s~nf.gif~nf.gif", target.getName()));
    				c.send(PacketCreator.showPrefixIcons(channel.getName(), prefix,true));
    			}
        	} else if(target.getPfLeben() == 1) {
    			for(Client c : channel.getClients()) {
    				List<String> prefix = new ArrayList<String>();
    				prefix.add(String.format("%s~nf.gif", target.getName()));
    				c.send(PacketCreator.showPrefixIcons(channel.getName(), prefix,true));
    			}
        	} else {
    			for(Client c : channel.getClients()) {
    				List<String> prefix = new ArrayList<String>();
    				prefix.add(String.format("%s~", target.getName()));
    				c.send(PacketCreator.showPrefixIcons(channel.getName(), prefix,true));
    			}
    			
        		channel.setPfSpieler(channel.getPfSpieler().replace(String.format("|%s|", target.getName()), ""));
        	}
        	
        	if(Server.countChars(channel.getPfSpieler(), '|') == 2) {
        		String l = channel.getPfSpieler().replace("|", "");
        		Client gewinner = Server.get().getClient(l);
        		
        		if(gewinner == null) {
        			gewinner = new Client(null);
        			gewinner.loadStats(l);
        		}
        		
        		channel.broadcastButlerMessage(String.format("Die Kissenschlacht ist _vorbei_! _Ein °>s/24.gif<°Knuddel_ hat der tapfere _Gewinner °BB>_h%s|/serverpp \"|/w \"<r° gewonnen_!", l.replace("<", "\\<")));
        		gewinner.increaseKnuddels(1);
        		channel.setPfActive(false);
    			channel.setPfSpieler("");
    			
    			for(Client c : channel.getClients()) {
    				c.send(PacketCreator.removePrefixIcons(channel.getName()));
    			}
    			
    			channel.setPfStatus(0);
        	}
                
        
    }}
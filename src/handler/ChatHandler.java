/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (C) 2011 - 2012 Flav <http://banana-coding.com>
 * 
 * Diese Dateien unterliegen dem Coprytight von Banana-Coding und
 * darf verändert, aber weder in andere Projekte eingefügt noch
 * reproduziert werden.
 * 
 * Der Emulator dient - sofern der Client nicht aus Eigenproduktion
 * stammt - nur zu Lernzwecken, das Hosten des originalen Knuddels Clients
 * ist auf eigene Gefahr und verstößt möglicherweise gegen Schutzrechte
 * der Knuddels.de GmbH & Co KG
 * 
 * Autoren: Flav (Grundversion), Localhost (Erweiterte Version), Kokos-Ice (Erweiterte Version)
 */



package handler;

import java.io.IOException;
import java.net.MalformedURLException;
import tools.KCodeParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import starlight.Butler;
import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.CommandParser2;
import starlight.FunctionParser;
import starlight.Server;

public class ChatHandler {
    
	public static void handle(String[] tokens, Client client) throws MalformedURLException, IOException {
        Channel channel = Server.get().getChannel(tokens[1]);
        String arg = "";
        Random zufall = new Random();
        Long systemtime = System.currentTimeMillis()/1000;
        
        if (channel == null || !channel.getClients().contains(client)) {
            return;
        }

        if (client.isAway()) {
            client.setAway(false);
        }
        
        client.setTimeOut(systemtime);

        String message = tokens[2].trim();
        
        String command = message.substring(1).split(" ")[0].toLowerCase();

         if (message.charAt(0) != '/') {
             
               
             
        	if (client.checkSpam(command.equals("p")?true:false, channel)) {
           		return;
        	}
         }
        
        if (message.isEmpty()) {
            return;
        }

    	if(message.contains(client.getuPassword())) {
    		client.sendButlerMessage(channel.getName(), "Deine letzte Nachricht wurde ignoriert, da sie _dein Passwort_ enthielt. Um sämtlichen Missbrauch zu verhindern: _Gib bitte niemals dein Passwort an irgendjemanden weiter!_#Möchtest du dein Passwort ändern, so kannst du dies durch Eingabe von °>/e|\"<° tun.");
    		Server.get().newSysLogEntry(client.getName(), String.format("Nachricht ignoriert (Passwort enthalten).", client.getName()));
                return;
    	}
        
        if (message.length() > command.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1).trim();
        }
        
        if(Server.get().checkCcm(client.getName(), channel, 2)) {
        	message = KCodeParser.noKCode(message);
        } else {
        	arg = KCodeParser.parse(client, arg, 0, 10, 20);
            arg = Server.get().parseSmileys(client, arg);
        }

        client.CheckNewTut(message.toLowerCase(), channel, client.getAktuTut());
        
        if (message.charAt(0) == '/') {
            String cmd = command.toLowerCase();
            if (cmd.equals("blitz")) {
              blitz.funktionen.funktion(message, client, channel);  
                return;
     
            } else if (cmd.equals("fifty")) {
              fifty.fiftyanmeldung.make(client, channel);  
                return;
            }
             if(channel.getFeatures() != null && !channel.getFeatures().parseCommand(message, client,null)) {
                    return;
                }
            
        	if(!channel.getBannedFunctions().isEmpty()) {
        		if(channel.getBannedFunctions().contains(String.format("|%s|", command))) {
        		             client.sendButlerMessage(channel.getName(), String.format("Die Funktion /%s ist in diesem Channel leider deaktiviert.", command));
        			return;
        		}
        	}
        	
            String[] commands = {"auth","bugs","tut","stat","uptime","cm", "img", "server","clearcomments", "clearcmcomments", "w", "ww", "rights", "syslog", "blinddate", "whois", "go", "cc","m", "info", "lock", "gamelock"};
            
            for(String x : commands) {
            	if(command.equals(x)) {
            		FunctionParser.parse(message, client, channel);
                	return;
                }
            }
         	
        	for(String macro : Server.macros.keySet()) {
        		if(macro.equals(command)) {
					String texts = Server.macros.get(macro);
                    Client c = Server.get().getClient(arg);
                    List<String> texte = new ArrayList<String>();
                    
                    for(String t : texts.split("\\|")) {
                    	if(!t.isEmpty()) {
                    		texte.add(t);
                    	}
                    }
                    
                    String[] bla = texte.get(new Random().nextInt(texte.size())).split("~");
                    int me = Integer.parseInt(bla[1]);
                    String text = bla[0]
                    		.replace("[TEXT]", String.format("%s§",arg.isEmpty()?"sich":c==null?arg:String.format("°>_h%s|/serverpp \"|/w \"<°",c.getName().replace("<", "\\<"))))
            				.replace("[NICK]", client.getName()
            						);
                    
                    if(me == 1) {
                    	channel.broadcastAction(client.getName(), text);
                    } else{
                    	channel.broadcastMessage(text, client, true);
                    }
                    
                    return;
        		}
        	}
        	CommandParser2.parse2(message, client, channel, false);
    		CommandParser.parse(message, client, channel, false);
            return;
        }
            
    	if(Server.get().checkCcm(client.getName(), channel, 3)) {
    		return;
    	}

		if(message.length() > 2000) {
        	client.sendButlerMessage(channel.getName(), "Deine Nachricht enthielt _zuviele Zeichen_, deshalb wurde sie ignoriert.");
        	Server.get().newSysLogEntry(client.getName(), String.format("Nachricht ignoriert (zu viele Zeichen).", client.getName()));
                return;
    	}
		
    	if(!client.hasPermission("use.badwords")) {
    		if(client.getOnlineTime() < 6000000 && !channel.checkCm(client.getName())) {
    			String messageClearKCode = KCodeParser.clearKCode(message.toLowerCase());
    			
    			for(String lala : Server.badwords.keySet()) {
    				if(messageClearKCode.contains(lala)) {
    					String nachricht = "";
    					int juschu = Server.badwords.get(lala);
            		
    					try {
    						nachricht = message.toLowerCase().split(lala)[0];
    					}catch(Exception ex) {
    					}
            		
    					channel.broadcastMessage(String.format("%s %s...", nachricht, lala.substring(0, 1)), client, false);
    					channel.broadcastAction(Server.get().getButler().getName(), String.format("konnte %s gerade noch den Mund zuhalten.", client.getName()));
            		                Server.get().newSysLogEntry(client.getName(), String.format("Nachricht ignoriert (BadWords).", client.getName())); 
    					if(juschu == 1) {
    						if(channel.getJuSchu() == 1) {
    							client.sendButlerMessage(channel.getName(), String.format("Du wurdest soeben von °>_h%s|/serverpp \"|/w \"<° im Channel %s ge_°BB>mutet|/h mute<r°_.#Begründung: Jugendschutz", Server.get().getButler().getName(), channel.getName()));
    							channel.setMutes(String.format("%s|%s|", channel.getMutes(), client.getName()));
    							
    							if(channel.isVisible()) {
    								client.setComment(systemtime, channel.getName(), "Mute!", Server.get().getButler().getName(), "Jugendschutz");
    							}
    							
    							client.setWarnings((byte)0);
    						}
    					} else {
    						if(client.getWarnings() == 5) {
    							client.sendButlerMessage(channel.getName(), String.format("Du wurdest soeben von °>_h%s|/serverpp \"|/w \"<° im Channel %s ge_°BB>mutet|/h mute<r°_.", Server.get().getButler().getName(), channel.getName()));
    							channel.setMutes(String.format("%s|%s|", channel.getMutes(), client.getName()));
    							
    							if(channel.isVisible()) {
    								client.setComment(systemtime, channel.getName(), "Mute!", Server.get().getButler().getName(), "");
    							}
    							
    							client.setWarnings((byte)0);
    						} else {
    							client.sendButlerMessage(channel.getName(), "Bitte benehmen Sie sich anständig!");
    						Server.get().newSysLogEntry(client.getName(), String.format("Verwarnung von "+Server.get().getButler().getName()+" erhalten (Jugendschutz / Spamming).", client.getName()));
                                                }
    					}
            		
    					client.increaseWarnings();
    					return;
    				}
            	}
            }
        }
    	
    	if(channel.isModerated()) {
    		if(!channel.checkVip(client.getName()) && !channel.checkMod(client.getName())) {
    			if(client.getRank() < 5) {
    				client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
    				return;
    			}
    		} else {
    			if(!client.getColor().isEmpty()) {
    				message = String.format("°[%s]18°%s", client.getColor(), message);
    			} else if(channel.checkMod(client.getName())) {
        			message = String.format("°RR18°%s", message);
    			} else if(channel.checkVip(client.getName())) {
        			message = String.format("°BB16°%s", message);
    			}
    		}
    	}
        
     
        
        if(channel.getName().equals("Private") || channel.getName().equals("Private 15-17") || channel.getName().equals("Psssst")) {
            if(client.getRank() < 5) {
    				client.sendButlerMessage(channel.getName(), "In diesem Channel kannst du nicht öffentlich sprechen. Klick einen Nicknamen in der Anwesenheitsliste an und beginne einfach ein Privatgespräch.");
    				return;
        }
        }   
        if(client.getGmute() != 0) {
    				client.sendButlerMessage(channel.getName(), "Du bist derzeit Globalgemutet und kannst deßhalb nicht öffentlich schreiben.");
    				return;
        }
        
        
        if(client.getGrippeStatus() > 0 && zufall.nextInt(24) == 10) {
        	channel.broadcastMessage(Server.get().textToIll(message), client, false);
        } else {
        	channel.broadcastMessage(message, client, false);
        }
        
        
        if(!channel.butlerMuted()) {
        	if(channel.countClients() == 2 || message.toLowerCase().contains(Server.get().getButler().getName().toLowerCase())) {
        	     if (channel.getGameName().trim().isEmpty()) {  		
                    Butler.parse(client, channel, arg);
                  }
        	}
        }

        client.setZeichen(client.getZeichen()+message.length());
        client.setLevelInfo("zeichen",message.length());
    }
}
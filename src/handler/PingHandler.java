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

import tools.PacketCreator;
import java.util.Random;

import starlight.Channel;
import starlight.Client;
import starlight.FunctionParser;
import starlight.Server;

public class PingHandler {
    public static void handle(String[] tokens, Client client) {
    	String what = tokens[1];
    	
        if (what.equals("-")) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

            client.send(PacketCreator.pong());
            
            if(client.getName() == null) {
            	return;
            }
            
            if(client.getTimeOut() != 0) { // Online
                long time = System.currentTimeMillis()/1000;
                Random zufall = new Random();
                int zufallInt = zufall.nextInt(150);
                
        
                if(client.getKnuddelscentLock() == 0) {
                	int actualLevel = client.getKnuddelscentLevel();
                	int nextLevel = actualLevel+1;
                	
                	for(Channel c : client.getChannels()) {
                		client.sendButlerMessage(c.getName(), String.format("Für den _Tageslogin_ erhältst du _°BB>_h5 Knuddels|/h knuddelscent<r°_. °>loginknuddelscent/loginbar-20_%sto%s...alwayscopy.gif<° °>loginknuddelscent/knuddel_small.gif<°", actualLevel, nextLevel));
                		
                             if(client.hasPermission("cmd.tageslogin.bonus")) {
                                client.sendButlerMessage(c.getName(), String.format("°RR°°16°_AUFGEPASST!_ - °r°Jemand Unbekanntes hat dir einen Bonus eingeräumt. Für den _Tageslogin_ erhältst du nochmals _°BB>_h100 Knuddels|/h knuddelscent<r°_ °>loginknuddelscent/knuddel_small.gif<°. Wer das nur gewesen sein mag?", actualLevel, nextLevel));
                		client.increaseKnuddels((float) 100.00);
                                }
                                
                               
                                 
                		if(actualLevel == 19) {
                    		client.sendButlerMessage(c.getName(), "_Glückwunsch_, mit dem heutigen _Tageslogin_, hast du jetzt _einen ganzen Knuddel_ °>loginknuddelscent/knuddel_small.gif<° gesammelt!");
                    	}
                	}
                	
                	client.setKnuddelscentLevel((byte) (actualLevel == 19 ? 0 : nextLevel));
                	client.setKnuddelscentLock((byte) 1);
                	client.increaseKnuddels((float) 5.00);
                }
        
 
                     
                     
                if ((client.getTutOpen() == 1) && (client.getOnlineTime() / 60 >= 10) && (client.getTutAktiv() == 1) && (client.getAktuTut() == 1)) {
                	FunctionParser.parse("/tut", client, client.getChannel());
                	client.setTutOpen((byte)0);
           		}
           	    
                int mins = 0;
           		int rang = 0;
           		 
           		for(int tutorial : Server.tutorials.keySet()) {
           			String[] more = Server.tutorials.get(tutorial);
           		 						
           		 	if(tutorial <= client.getAktuTut()) {
           		 		rang = Integer.parseInt(more[5]);
           		 		mins = more[6].isEmpty()?0:Integer.parseInt(more[6]);
           		 	}
           		 						
           		 	if ((client.getTutOpen() == 1) && (mins <= client.getOnlineTime() / 60) && (rang <= client.getRank()) && (client.getTutAktiv() == 1)) {
           		 		FunctionParser.parse("/tut", client, client.getChannel());
           		 		client.setTutOpen((byte)0);
           		 	}
           		}
                
            	if(time >= client.getTimeOut() && !client.hasPermission("cmd.timeout.disable")) {
               
                    client.disconnect();
             }
                    
            	
            	if(client.getBotkontrolleAn() != null) {
                    Long rest = client.getBotkontrolleZeit()+600;
                    Long rest2 = rest-time;
       
                    if (rest2 <= 0) {
                    	Client an = Server.get().getClient(client.getBotkontrolleAn());
                		
                    	if(an != null) {
                    		Server.get().newMessage(Server.get().getButler().getName(), an.getBotkontrolleVon(), String.format("Bestätigt: Botkontrolle %s", an.getName()), String.format("%s hat nicht auf eine Kontroll-Nachricht reagiert, damit hat sich der _Bot-Verdacht bestätigt_. %s wurde aus dem Chat gekickt und ein Eintrag in die Admininfo wurde getätigt.", an.getName(), an.getName()), time);
                        	an.setComment(time, null, null,Server.get().getButler().getName(), String.format("Botkontrolle durch %s _positiv_.", client.getName()));
                    		an.setBotkontrolleVon(null);
                    		an.disconnect();
                    	} else {
                        	for(Channel s : client.getChannels()) {
                        		client.sendButlerMessage(s.getName(), String.format("%s hat die Botkontrolle _nicht beantwortet_, weil %s mittlerweile _offline_ ist.", client.getBotkontrolleAn(), client.getBotkontrolleAn()));
                        	}
                    	}
                    	
                    	client.setBotkontrolleAn(null);
                    	client.setBotkontrolleZeit(0);
                    }
           	 	}
            	
            	if(zufallInt == 50) {
            		try {
            			Channel channel = client.getChannel();
                	
            			if(channel.checkCm(client.getName())) {
            				channel.broadcastAction("°r°", String.format("Für den ehrenamtlichen Einsatz als Channelmoderator (°>cm.png<°) erhält _°BB>_h%s|/serverpp \"|/w \"<r°_ als kleines Dankeschön einen Knuddel!", client.getName().replace("<", "\\<")));
            				client.increaseKnuddels(1);
            			}
            		} catch(Exception ex) {
            		}
            	}
            } else {
            	//Offline
            	
            }
        } else {
        	String infos = tokens[1].trim();
        	System.out.println(infos);
        }
    }
}

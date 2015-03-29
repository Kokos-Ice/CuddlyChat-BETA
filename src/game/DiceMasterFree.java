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



package game;

import java.util.List;
import java.util.Random;

import tools.KCodeParser;
import starlight.Channel;
import starlight.Client;
import starlight.Server;

public class DiceMasterFree implements Game {
    private Channel channel;
    private static String[] dmNoWin = {"Leider kein Gewinn!", "Das war wohl nichts! Versuch es gleich nochmal...", "Nichts! Das Pech verfolgt dich...", "Du hast einen stinkenden Socken gewonnen, jedoch keine Knuddels!", "Leider nichts! Kopf hoch, das nächste Mal wird besser!"};
    private static String[] dmWin = {"%s Knuddels gehen auf dein Konto!", "Du darfst dich freuen, denn du erhälst %s Knuddels!", "Voll ins Schwarze getroffen! %s Knuddels!"};
    
    public DiceMasterFree(Channel channel) {
        this.channel = channel;
    }

    public boolean parsePublicMessage(String message, Client client) {
		return false;
    }

    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
		return false;
    }

    public void onLeave(Client client) {
    }

	public void onJoin(Client client) {
	}

	public boolean parseCommand(String message, Client client) {
        String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
        String cmd = command.toLowerCase();
        String arg = "";
        Random zufall = new Random();
        
        if (message.length() > cmd.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1);
        }

        int Anzahlspe = Server.countChars(arg, '°');
        
        if (Anzahlspe == 1) {
        	String[] argus = arg.split("°", 2);
        	arg = argus[0];
        }
        
        if(!cmd.equals("dm")) {
        	return true;
        }
        
        if(!client.hasPermission("cmd.dm")) {
    		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
    		return false;
    	} else if(client.getSpielsperre() != 0) {
    		client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
    		return false;
    	} else {
    		arg = KCodeParser.escape(arg);
        	int zahl = zufall.nextInt(500)+1;
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Falsche Anwendung.");
        		return false;
        	} else {
        		if(arg.equals("dice")) {
            		if(client.getDm() < 1) {
            			client.sendButlerMessage(channel.getName(), "Du kannst momentan nicht würfeln.");
            		} else {
            			channel.broadcastAction(">", String.format("%s rollt einen Würfel...#W500: %s = _%s_", client.getName(), zahl, zahl));
            			client.setDm(client.getDm()-1);
                	
                		if(zahl == 250) {
                			channel.broadcastButlerMessage(String.format("°18RR°_%s", String.format(dmWin[zufall.nextInt(dmWin.length)], 50)));
                			client.increaseKnuddels(50);
                			client.setDmWin(client.getDmWin()+50);
                		} else if(zahl == 410 || zahl == 420 || zahl == 430 || zahl == 440 || zahl == 460 || zahl == 470 || zahl == 480 || zahl == 490) {
                			channel.broadcastButlerMessage(String.format("°18RR°_%s", String.format(dmWin[zufall.nextInt(dmWin.length)], 25)));
                			client.increaseKnuddels(25);
                			client.setDmWin(client.getDmWin()+25);
                		} else if(zahl == 210 || zahl == 220 || zahl == 230 || zahl == 240 || zahl == 260 || zahl == 270 || zahl == 280 || zahl == 290) {
                			channel.broadcastButlerMessage(String.format("°18RR°_%s", String.format(dmWin[zufall.nextInt(dmWin.length)], 15)));
                			client.increaseKnuddels(15);
                			client.setDmWin(client.getDmWin()+15);
                		} else if(zahl == 111 || zahl == 222 || zahl == 333 || zahl == 444) {
                			channel.broadcastButlerMessage(String.format("°18RR°_%s", String.format(dmWin[zufall.nextInt(dmWin.length)], 10)));
                			client.increaseKnuddels(10);
                			client.setDmWin(client.getDmWin()+10);
                		} else if(zahl == 10 || zahl == 20 || zahl == 30 || zahl == 40 || zahl == 60 || zahl == 70 || zahl == 80 || zahl == 90) {
                			channel.broadcastButlerMessage(String.format("°18RR°_%s", String.format(dmWin[zufall.nextInt(dmWin.length)], 5)));
                			client.increaseKnuddels(5);
                			client.setDmWin(client.getDmWin()+5);
                		} else if(zahl == 110 || zahl == 120 || zahl == 130 || zahl == 140 || zahl == 160 || zahl == 170 || zahl == 180 || zahl == 190 || zahl == 310 || zahl == 320 || zahl == 330 || zahl == 340 || zahl == 360 || zahl == 370 || zahl == 380 || zahl == 390) {
                			channel.broadcastButlerMessage("°18RR°_Freiwurf! Du darfst nochmal würfeln.");
                			client.setDm(client.getDm()+1);
                		} else {
                			channel.broadcastButlerMessage(String.format("°18RR°_%s °>2.gif<°", dmNoWin[zufall.nextInt(dmNoWin.length)]));
                		}
                	
                		if(client.getDm() == 0) {
                			channel.broadcastButlerMessage(String.format("°18RR°_Somit hast du alle Würfel aufgebraucht, %s.", client.getName()));

                			if(client.getDmStartKnuddels() > client.getDmWin()) {
                				client.sendButlerMessage(channel.getName(), String.format("Du hast einen °RR°_Verlust von %s Knuddels°r°_ gemacht.", client.getDmStartKnuddels()-client.getDmWin()));
                			} else {
                				int gewinn = client.getDmWin()-client.getDmStartKnuddels();
                			
                				if(gewinn > 1) {	
                					client.sendButlerMessage(channel.getName(), String.format("Du hast einen °G°_Gewinn von %s Knuddels°r°_ gemacht.", gewinn));
                				}
                			}
                		
                			client.setDmStartKnuddels(0);
                			client.setDmWin(0);
                		}
                	}
            		
            		return false;
            	} else {
            		if(!Server.get().isInteger(arg)) {
                		client.sendButlerMessage(channel.getName(), "Bitte gib eine Rundenanzahl an: /dm RUNDEN");
                	} else {
	                	int runden = Integer.parseInt(arg);
	                	int knuddels = runden*0;
                	
	                	if(runden < 1 || runden > 15) {
	                		client.sendButlerMessage(channel.getName(), "Bitte gib mindestens 1 und maximal 15 Runden an.");
	                	} else if(knuddels > client.getKnuddels()) {
	                		client.sendButlerMessage(channel.getName(), String.format("Du benötigst mindestens %s Knuddels um %s Runden spielen zu können.", knuddels, runden));
	                	} else if(client.getDm() > 0) {
	                		client.sendButlerMessage(channel.getName(), String.format("Du hast noch _%s offene Würfe_. °>{button}Würfeln||call|/dm dice<°", client.getDm()));
	                	} else {
	                		channel.broadcastButlerMessage(String.format("°18RR°_Dein%s nun freigegeben, %s.", runden==1?" Würfel ist":String.format("e %s Würfel sind", runden), client.getName()));
	                		client.sendButlerMessage(channel.getName(), String.format("Dir wurden _%s Knuddels_ abgezogen. Würfeln kannst du mit _°BB>/dm dice|\"<r°_. Viel Glück!", knuddels));
	                		client.setDm(runden);
	                		client.setDmStartKnuddels(knuddels);
	                		client.deseaseKnuddels(knuddels);
                		}
                	}
                	return false;
            	}
        	}
    	}
	}
}
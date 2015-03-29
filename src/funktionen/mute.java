package funktionen;

import static features.hero.timeStampToDate;
import game.WordMixRecord;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.moderated;
import static starlight.CommandParser.muted;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class mute {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg,String cmd) {
     	int typ;
        	
        	if(cmd.equals("mute")) {
        		typ = 3;
        	} else {
        		typ = 2;
        	}

        	if(!client.hasPermission("cmd.mute") && !client.checkTeam("Spiele") && !client.checkTeam("Jugendschutz") && !channel.checkCm(client.getName())  && !channel.checkMcm(client.getName()) && !channel.checkHz(client.getName())) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}        	
        	
        	
        	String nick = KCodeParser.escape(arg).split(":", 2)[0].trim();
        	String msg = "";

        	if(nick.isEmpty()) {
                String mutedNicks = channel.getMutes().replace("||", ", ").replace("|", "");
                String cmutedNicks = channel.getCmutes().replace("||", ", ").replace("|", "");
                
                client.sendButlerMessage(channel.getName(), String.format("Folgende Bösewichte sind zwangsweise eingeschränkt (Mute & Colormute):#_Lokal_:%s%s", mutedNicks.isEmpty()?"":String.format("#_Muted_:#%s", mutedNicks), cmutedNicks.isEmpty()?"":String.format("#_Colormuted_:#%s", cmutedNicks)));
        		return;
        	}
        	
        	if (arg.length() > nick.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
        	
        	if (!nick.isEmpty() && arg.charAt(0) == '!') {
                nick = nick.substring(1).trim();
            }
        	
        	Client target = Server.get().getClient(nick);
        	boolean online = true;
        	
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(nick);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nick));
                    return;
                }
            }
            
            if(arg.charAt(0) != '!' && !online) {
            	client.sendButlerMessage(channel.getName(), userIsOffline(target));
            	return;
            }
        	
            if(arg.charAt(0) == '!') {
				if(Server.get().checkCcm(target.getName(), channel, typ)) {
					for(Channel lala : target.getChannels()) {
						target.sendButlerMessage(lala.getName(), String.format("Du wurdest soeben von %s im Channel %s ent%s.", client.getName(), channel.getName(), cmd.equals("mute")?"mutet und kannst dort ab jetzt wieder öffentliche Nachrichten schreiben":"colormutet und kannst dort ab jetzt wieder Farben und Formatierungen in öffentlichen Nachrichten verwenden"));
					}
					
            		target.setComment(time, channel.getName(), String.format("Ent%smutet!", typ==3?"":"color"), client.getName(), "");
            		client.sendButlerMessage(channel.getName(), String.format("%s kann nun wieder weiter im Channel %s %s.", target.getName(), channel.getName(), cmd.equals("mute")?"nerven":"farbig schreiben und Smileys verwenden"));
            		
            		if(typ == 3) {
            			channel.setMutes(channel.getMutes().replace(String.format("|%s|", target.getName()), ""));
            		} else {
            			channel.setCmutes(channel.getMutes().replace(String.format("|%s|", target.getName()), ""));
            		}
            	} else {
            		client.sendButlerMessage(channel.getName(), String.format("%s steht nicht auf der %suteliste (%s).", target.getName(), cmd.equals("mute")?"M":"Colorm", channel.getName())); 
            	}
            	
            	return;
            }
            
            if(!target.getChannels().contains(channel)) {
            	client.sendButlerMessage(channel.getName(), String.format("%s hält sich in einem _anderen Channel_ auf.", target.getName())); 
            	return;
            }
            
            if(target.getRank() > 4) {
            	client.sendButlerMessage(channel.getName(), String.format("Sie haben nicht genügend Rechte, um %s zu muten.", target.getName()));
            	return;
            }
            
            if(msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/mute NICK:COMMENT oder !NICK oder nur /mute#(/mute NICK setzt den betreffenden Nick auf die Muteliste, !NICK entfernt diesen wieder. Nur /mute zeigt eine Liste der aktuell gemuteten Personen an.)");
            	return;
            }

            if(msg.length() < 10) {
            	client.sendButlerMessage(channel.getName(), "Ihre Begründung muss mindestens 10 Zeichen lang sein.");
            	return;
            }
           
            if(msg.length() > 350) {
            	client.sendButlerMessage(channel.getName(), "Ihre Begründung darf höchstens 350 Zeichen lang sein.");
            	return;
            }
            
            if(Server.get().checkCcm(target.getName(), channel, typ)) {
            	client.sendButlerMessage(channel.getName(), String.format("%s ist bereits in diesem Channel ge%smutet.", target.getName(), cmd.equals("mute") ? "":"color"));
            	return;
            }
            
            msg = KCodeParser.parse(client, msg, 5, 10, 20);
            msg = Server.get().parseSmileys(client, msg);
            
            target.setComment(time, channel.getName(), String.format("%sute!", cmd.equals("mute")?"M":"Colorm"), client.getName(), msg);
            if(cmd.equals("mute")) {
            	target.setMutes(target.getMutes()+1);
                target.sendButlerMessage(channel.getName(), String.format("Du wurdest soeben von %s im Channel %s _°BB>_hgemutet|/h mute<°§.#Begründung: %s", client.getName(), channel.getName(), msg));
            } else {
                target.setCmutes(target.getCmutes()+1);
                target.sendButlerMessage(channel.getName(), String.format("Du wurdest soeben von %s im Channel %s _°BB>_hgecolormutet|/h colormute<°§.#Begründung: %s", client.getName(), channel.getName(), msg));
            }

            client.sendButlerMessage(channel.getName(), String.format("%s wird jetzt erstmal im Channel %s %s.", target.getName(), channel.getName(), cmd.equals("mute")?"still sein":"keine Farben und Smileys mehr verwenden"));
            if(typ == 3) { // mute
            	channel.setMutes(String.format("%s|%s|", channel.getMutes(), target.getName()));
            } else {
            	channel.setCmutes(String.format("%s|%s|", channel.getMutes(), target.getName()));
            }
          
      }}
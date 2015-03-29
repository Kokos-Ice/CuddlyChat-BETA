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
import static starlight.CommandParser.countChars;
import static starlight.CommandParser.image;
import static starlight.CommandParser.moderated;
import static starlight.CommandParser.muted;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class ig {
  public static Long time = System.currentTimeMillis()/1000; 
  
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
 if(!client.hasPermission("cmd.ignore")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
    		
    		if(arg.isEmpty()) {
    			if(client.getIgnoredNicks().isEmpty()) {
    				client.sendButlerMessage(channel.getName(), "Momentan ignorierst du niemanden.");
    			} else {
    				StringBuilder ignoredNicks = new StringBuilder();
    				int countNicks = 1;
    				
    				for(String nick : client.getIgnoredNicks().split("\\|")) {
    					if(!nick.isEmpty()) {
    						String name = nick.replace("<", "\\<");
    						
    						if(countNicks != 1) {
    							ignoredNicks.append(", ");
    						}
    						
    						ignoredNicks.append("°>_h").append(name).append("|/serverpp \"|/w \"<°");
    						countNicks++;
    					}
    				}
    				
    				client.sendButlerMessage(channel.getName(), String.format("Folgende Mitglieder werden momentan von dir ignoriert:#%s", ignoredNicks.toString()));
    			}
    			
    			return;
    		}

    		Client target = Server.get().getClient(arg);
    		boolean remove = false;
    		
    		if(arg.startsWith("!")) {
    			remove = true;
    			arg = arg.substring(1);
    		}
    		
    		if(target == null) {
    			target = new Client(null);
    			target.loadStats(arg);
    			
    			if(target.getName() == null) {
    				client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", arg));
    				return;
    			}
    		}
    		
    		String nick = target.getName();
    		String name = nick.replace("<", "\\<");
    		
    		if(remove) {
    			if(!client.checkIgnored(target.getName())) {
                                
    				client.sendButlerMessage(channel.getName(), String.format("Du hast °>_h%s|/serverpp \"|/w \"<° niemals ignoriert!", name));
        			return;
    			}
                        
			Logger.handle(null,String.format("%s wird ab sofort nicht mehr von %s ignoriert", target.getName(), client.getName()));
    			Server.get().newSysLogEntry(client.getName(), String.format("%s wird ab sofort nicht mehr ignoriert", name));
    			client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wird von nun an nicht mehr von dir ignoriert!", name));
				client.removeIgnoredNick(target.getName());
    		} else {
    			int maxIgnoredNicks = client.getRank()*5+10;
    			int currentIgnoredNicks = countChars(client.getIgnoredNicks(), '|')/2;
    			String lastIgnoredNick = "";
    			
    			if(!client.getIgnoredNicks().isEmpty()) {
    				lastIgnoredNick = client.getIgnoredNicks().substring(1).split("\\|")[0];
    			}
    			
    			if(target == client) {
    				client.sendButlerMessage(channel.getName(), "Jeder hat mal Probleme, aber deshalb musst du dich doch nicht selbst ignorieren?!");
    				return;
    			}
    			
    			if(target.hasPermission("notignorable")) {
    				client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° kann nicht ignoriert werden.", name));
    				return;
    			}
    			
    			if(client.checkIgnored(target.getName())) {
    				client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° steht bereits auf ihrer Ignoreliste.", name));
    				return;
    			}
    			Server.get().newSysLogEntry(client.getName(), String.format("%s wird fortan ignoriert", name));
                        
			Logger.handle(null,String.format("%s wird fortan von %s ignoriert", target.getName(), client.getName()));
    			client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wird von nun an von dir _ignoriert_, was du mit _°>/ig !%s|\"<°_ rückgängig machen kannst.%s##°>{button}Notruf senden und über %s beschweren||call|/admincall complaint:%s<°", name, nick, currentIgnoredNicks == maxIgnoredNicks ? String.format("#Da deine Ignoreliste voll war, wurde %s wieder von deiner Liste entfernt.", lastIgnoredNick) : "", name, name));
    			
    			if(currentIgnoredNicks == maxIgnoredNicks) {
        			client.removeIgnoredNick(lastIgnoredNick);
    			}
    			
    			if(client.checkFriend(target.getName())) {
                	client.sendButlerMessage(channel.getName(), String.format("Da du %s nun ignorierst wurde %s von deiner Freundesliste entfernt.", target.getName(), target.getName()));	
                    client.setFriendlist(client.getFriendlist().replace(String.format("|%s|", target.getName()), ""));
                }
    			
    			client.addIgnoredNick(target.getName());
    		}
          
      }}
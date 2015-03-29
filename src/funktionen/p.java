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
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class p {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
  if(!client.hasPermission("cmd.p")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            int split = arg.indexOf(':');
            String info = "Bitte die Funktion folgendermaßen benutzen:#/p NICK:TEXT#(flüstert den TEXT an NICK, wobei auch mehrere Nicks mit Kommas getrennt werden können)";

            if (split < 1 || split == arg.length() - 1) {
                client.sendButlerMessage(channel.getName(), info);
                return;
            }

            String recipient = arg.substring(0, split);
            String[] recipients = recipient.split(",");

            if (recipients.length > 5 && client.getRank() < 5) {
                return;
            }

            List<Client> targets = new ArrayList<Client>();

            for (String rcp : recipients) {
                rcp = KCodeParser.escape(rcp);

                if (rcp.isEmpty()) {
                    continue;
                }

                Client target = Server.get().getClient(rcp);
                boolean online = true;
                
                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(rcp);
                    
                    if(target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), unknownUser(rcp));
                        return;
                    }
                } 
                
                if(!online) {
                    client.sendButlerMessage(channel.getName(), userIsOffline(target));
                    return;
                }
                
                if(target.checkIgnored(client.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("Du wirst von %s ignoriert.", target.getName()));
                    return;
                }
                
                if(target.getSilence() == 1 && client.getRank() < 5) {
                	client.sendButlerMessage(channel.getName(), String.format("%s können derzeit keine Nachrichten hinterlassen werden.", target.getName()));
                	return;
                }
                
                if (!targets.contains(target)) {
                    targets.add(target);
                }
                
                if(client.checkIgnored(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("_%s steht auf deiner Ignore-Liste_ und wird deshalb nicht antworten können. Mit _°BB>/ig !%s|\"<r°_ kannst du %s wieder von der Ignore-Liste löschen.", target.getName(), target.getName().replace("<", "\\<"), target.getName()));
                }
                
                if(target.getAge() > 0 && target.getAge() < 14 && client.getAge() > 17) {
                	if(client.getChildJuSchuMessage() == 0) {
                		client.sendButlerMessage(channel.getName(), String.format("Hallo %s, bitte beachte, dass dein Gesprächspartner %s noch ein Kind ist. Für Gespräche mit Kindern gelten besondere Regeln, die du unbedingt beachten solltest. Die wichtigsten Regeln haben wir für dich auf einer _°BB>_hInformationsseite|<r°_ zusammengefasst.", client.getName(), target.getName()));
                		client.setChildJuSchuMessage((byte)1);
                	}
                }
            }

            if (targets.isEmpty()) {
                return;
            }

            String msg = arg.substring(split + 1);
            
            if(client.getGrippeStatus() > 0 && new Random().nextInt(10) == 5) {
            	msg = Server.get().textToIll(msg);
            }
            
        	client.sendPrivateMessage(targets, msg, channel, false);
        	
        	if(targets.contains(Server.get().getButler())) {
            	if(channel.getGame() == null) {
            		client.sendButlerMessage(channel.getName(), String.format(Butler.butlerT[new Random().nextInt(Butler.butlerT.length)], client.getName()));
            	}
            }
        	
        	if(targets.toString().contains(client.getMentor())) {
        		if(client.getMpChat() == 0) {
        			client.setMpChat((byte)1);
        		}
       
                }
          
      }}
package funktionen;

import static features.hero.timeStampToDate;
import static funktionen.kiss.time;
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


public class heart {
  public static Long time = System.currentTimeMillis()/1000; 
  
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }
public static boolean haveLoveLight(Client client,boolean setban) {
     String[] l = client.getFeature("Lovelight");
 Feature ft = Server.get().getFeature("Lovelight");
 
 if (ft == null) {
     return false;
 }
 if (l[0].equals("2")) {
     if (setban) {
         	ft.setBan(l[1],l[3],l[4],client); // setz sperre
     }
     return true;
 }
 
    return false;
}
      public static void functionMake(Client client,Channel channel, String arg) {
       	if(!client.checkCode(70) && !client.checkCode(55) && !client.hasPermission("feature.trashheart")) {
        		if(client.getRank() < 1) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
            }
            
            String re = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > re.length()) {
                msg = arg.substring(arg.indexOf(':', 2) + 1).trim();
            }
            
            String herz = client.getHeart();
            int herzsperre = client.getHeartsperre();
            
            if (arg.isEmpty()) {  
        		if(client.getRank() < 1) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
        		
                if(herzsperre == 1 && herz.isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Obwohl du dein Herz im Moment nicht vergeben hast, kannst du es erst im nächsten Monat wieder vergeben.");
                    return;
                }
                
                if(herzsperre == 0 && herz.isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Du hast dein Herz derzeit nicht vergeben.");
                    return;
                }
                    
                client.sendButlerMessage(channel.getName(), String.format("Du hast dein Herz derzeit an °>_h%s|/serverpp \"|/w \"<° vergeben%s.", herz.replace("<", "\\<"), (herzsperre == 1 ? " und kannst es erst im kommenden Monat an eine andere Person vergeben" : "")));
                return;
            }
            
            if (arg.equals("!")) {
        		if(client.getRank() < 1) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
        		
                if(herz.isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Du hast dein Herz derzeit an niemanden vergeben.");
                }else{
                    client.sendButlerMessage(channel.getName(), String.format("Du hast dein Herz an °>_h%s|/serverpp \"|/w \"<° wieder entzogen.", herz.replace("<", "\\<")));
                    Server.get().newMessage(Server.get().getButler().getName(), herz, "Herz entzogen", String.format("%s hat dir das Herz wieder entzogen.", client.getName()), time);
                    client.setHeart("");
                    Client z = Server.get().getClient(herz);
                    if (z == null) {z = new Client(null); z.loadStats(herz); }
                    if (z.getName() != null) {
                    z.receivedHearts.remove(client.getName());
                    }   
                }
                
                return;
            }
            boolean giveBack = false;
            if(re.startsWith("-")) {
            	re = re.substring(1);
            	giveBack = true;
            }
            boolean herzbotschaft = false;
            if(re.startsWith("+")) {
            	re = re.substring(1);
            	herzbotschaft = true;
            }
            
            Client target = Server.get().getClient(re);
            boolean online = true;

            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(re);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(re));
                    return;
                }
            }
            
            msg = KCodeParser.parse(client, msg, 5, 10, 20);
            msg = Server.get().parseSmileys(client, msg);
            
            if(giveBack) {
            	 // wichtig            
  String[] l = client.getFeature("Trash-Heart");
 Feature ft = Server.get().getFeature("Trash-Heart");
 
 if (ft == null) {
     // kick vermeiden
     return;
 }
 
            	
            	if(!target.getHeart().equals(client.getName())) {
            		client.sendButlerMessage(channel.getName(), String.format("Du hast kein Herz von %s erhalten.", target.getName()));
            		return;
            	}
            	
            	client.sendButlerMessage(channel.getName(), String.format("Du hast dein Herz %s zurückgegeben.", target.getName()));
            	Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Herz zurückgegeben", String.format("%s hat dir dein Herz zurückgegeben.", client.getName()), time);
            	if(online) {
            		target.setHeart("");
            		target.receivedHearts.remove(client.getName());
            	} else {
            		Server.get().query(String.format("update accounts set heart='' where name='%s'", target.getName()));
            	}
            	return;
            }
            
            if(!online) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }
            
            if(herzbotschaft) {
            	 // wichtig            
  String[] l = client.getFeature("Sprayer-Heart");
 Feature ft = Server.get().getFeature("Sprayer-Heart");
 
 if (ft == null) {
     // kick vermeiden
     return;
 }
 

                if(target == client) {
                    client.sendButlerMessage(channel.getName(), "Du kannst dir selbst keine Herzbotschaft schicken.");
                    return;
                }

               
                
            	client.sendButlerMessage(channel.getName(), String.format("Du hast _°BB>_h%s|/serverpp \"|/w \"<r°_ jetzt folgende °>fullheart.png<°-Botschaft gesendet...°>|{backgroundad|heartAd_bright.jpg|heartAd_bright.jpg|heartAd_dark.jpg|heartAd_dark.jpg|0|0|0|0|5|20|http://www.knuddels.de| %s|255,255,255|255,255,255|28|0|-22<°", target.getName().replace("<", "\\<"), msg));
            	
            	for(Channel s : target.getChannels()) {
                	target.sendButlerMessage(s.getName(), String.format("_°BB>_h%s|/serverpp \"|/w \"<r°_ sendet dir diese °>fullheart.png<°-Botschaft...°>|{backgroundad|heartAd_bright.jpg|heartAd_bright.jpg|heartAd_dark.jpg|heartAd_dark.jpg|0|0|0|0|5|20|http://www.knuddels.de| %s|255,255,255|255,255,255|28|0|-22<°", client.getName().replace("<", "\\<"), msg));
            	}
            	
            	ft.setBan(l[1],l[3],l[4],client); // setz sperre
            	return;
            }
            
    		if(client.getRank() < 1) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
            
            if(target == client) {
                client.sendButlerMessage(channel.getName(), "Du kannst dein eigenes Herz nicht an dich selbst vergeben, Eigenliebe ist nicht angesagt.");
                return;
            }
            
            if(target == Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), String.format("Du kannst dein Herz nicht an %s geben.", Server.get().getButler().getName()));
                return;
            }

            
            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName()));
                return;
            }
            
            if(herz.equals(target.getName())) {
                client.sendButlerMessage(channel.getName(), String.format("Du hast dein Herz bereits an %s vergeben.", herz));
                return; 
            }
            
            
            
            
            if(herzsperre == 1 && !haveLoveLight(client,false)) {
                client.sendButlerMessage(channel.getName(), String.format("Du kannst dein Herz nur einmal im Monat vergeben. %s", herz.isEmpty()?"":String.format("Mit _/heart !_ kannst du dein Herz %s wieder entziehen.", herz)));
                return;
            }
            
            channel.broadcastPicAction(">>>", String.format("%s nimmt all seinen Mut zusammen und schenkt %s das eigene °>fullheart.png<°%s", client.getName(), target.getName(), msg.isEmpty() ? "." : String.format(" mit folgendem Text:#%s", msg)), String.format("actHeart_%s.png", image(client, target))); 
              haveLoveLight(client,true);
            for(Channel p : target.getChannels()) {
            	target.sendButlerMessage(p.getName(), String.format("_%s_ hat dir soeben das °>fullheart.png<° geschenkt. Wenn du das Herz von %s nicht haben möchtest, kannst du es in den nächsten Minuten mit _°BB>/deny|\"<r°_ zurückgeben.", client.getName(), client.getName()));
                target.setHighlights(String.format("%s|%s _Herz_ erhalten von °BB°_%s_°12°|", target.getHighlights(), Server.get().timeStampToDate(time), client.getName()));
                client.setHighlights(String.format("%s|%s _Herz_ verschenkt an °BB°_%s_°12°|", client.getHighlights(), Server.get().timeStampToDate(time), target.getName()));
   
            }
            
            if(!herz.isEmpty()) {
            	Server.get().newMessage(Server.get().getButler().getName(), herz, "Herz entzogen", String.format("%s hat dir das Herz wieder entzogen.", client.getName()), time);
            }
            
            client.setHeart(target.getName());
           target.receivedHearts.add(client.getName());
            
            target.setDeny(client.getName());
           
            if(client.getRank() < 8) {
            	client.setHeartsperre((byte)1);
            }
          
      }}
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


public class knuddelz {
  public static Long time = System.currentTimeMillis()/1000; 
    private static String[] knuddelz = {"[C] °RR°knuddelt°BB° mal ganz spontan [T] durch... WOW!","[T] werden von [C] so richtig °RR°durchgeknuddelt°BB°.", "[C] nimmt [T] in den Arm und °RR°knuddelt°BB° [T] mal so richtig.","Es wird wieder °RR°geknuddelt°BB°! [C] scheint [T] sehr gerne zu haben...", "[T] werden Opfer einer °RR°Massenknuddelichung°BB° von [C].", "[C] gibt eine ganze Runde °RR°Knuddels°BB° an [T].","[C] °RR°knuddelt°BB° [T] mal so richtig durch."};
    private static String[] noKnuddels = {"Willst Du mich eigentlich verarschen?, Du hast doch gar keine Knuddels!", "Du Träumer, knuddeln ohne Knuddels. Dass erzähl mal deinen Psychiater!", "Wer arbeiten geht, der kann auch knuddeln.", "Pech gehabt! Letztes mal hast du mir nicht mal einen Knuddel gegeben, und jetzt hast du gar keine Knuddels mehr! ;)", "Ich bin zwar erst 15, aber in Mathematik bist du wohl nicht auf meiner Höhe!", "Auf welcher Schule warst du nochmal? Es muss die Sonderschule gewesen sein."};
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
    if(!client.hasPermission("cmd.knuddelz")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            if(muted(client, channel)) { 
            	return;
            }
            
            if(moderated(client, channel)) {
            	client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
            	return;
            }
            
            if(arg.startsWith("+")) {
            	arg = arg.substring(1);	
            }

            
        	String image = "";
        
        	if(client.getGender() == 1) {
        		image = "m";
        	} else if(client.getGender() == 2) {
        		image = "f";
        	}
        	
        	Channel lol = Server.get().getChannel(arg);
        	
            if(arg.isEmpty() || lol != null && !lol.checkHz(client.getName())) {
            	if(client.getKnuddels() < channel.countClients()-1) {
            		client.sendButlerMessage(channel.getName(), noKnuddels[new Random().nextInt(noKnuddels.length)]);
            		return;
            	} 
            
            	StringBuilder nicks = new StringBuilder();
            	int lala = 1;
            
            	for(Client c : channel.getClients()) {
            		if(c != client) {
            			nicks.append(lala!=1?", ":"").append("°>_h").append(c.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
            			c.increaseKnuddels(1);
            			lala++;
            		}
            	}
            	
            	String text = knuddelz[new Random().nextInt(knuddelz.length)].replace("[C]", client.getName()).replace("[T]", nicks.toString());
            
            	channel.broadcastPicAction(">>>", text, String.format("actKnuddelz_%s.png", image));
            	client.deseaseKnuddels(channel.countClients()-1);
            	return;
            }
            
            Channel ch = Server.get().getChannel(arg);

            if(ch == null) {
            	client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", arg));
            	return;
            }
            
            if(!ch.getClients().contains(client)) {
            	client.sendButlerMessage(channel.getName(), String.format("Du musst im Channel %s online sein um deine CMs knuddeln zu können.", ch.getName()));
            	return;
            }
            
            if(channel.getCmk() == 1) {
            	client.sendButlerMessage(channel.getName(), String.format("Diese Woche wurden die CMs im Channel %s bereits geknuddelt.", ch.getName()));
            	return;
            }
            
            int x = 0;
            StringBuilder cmk = new StringBuilder();
            for(Client c : ch.getClients()) {
        		if(c != client) {
        			if(ch.checkCm(c.getName())) {
        				cmk.append(x!=0?", ":"").append("°>_h").append(c.getName()).append("|/serverpp \"|/w \"<°");
        				x++;
        			}
        		}
            }
            
            if(x < 1) {
            	client.sendButlerMessage(channel.getName(), "Es muss mindestens ein CM im Channel online sein.");
            	return;
            }

        	ch.broadcastPicAction(">>>", String.format("HZ%s %s °RR°knuddelt°BB° %s CMs %s mal so richtig durch.", client.getRank() > 4 ? "A":"E", client.getName(), client.getGender() == 1 ? "seine":"ihre", cmk.toString()), String.format("actKnuddelz_%s.png", image));
            channel.setCmk(1);
            for(Client c : ch.getClients()) {
        		if(c != client) {
        			if(ch.checkCm(c.getName())) {
        				c.increaseKnuddels(1);
        			}
        		}
            }
      }}
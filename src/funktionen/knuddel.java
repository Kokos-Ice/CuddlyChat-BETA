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
import static starlight.CommandParser.image;
import static starlight.CommandParser.moderated;
import static starlight.CommandParser.muted;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class knuddel {
  public static Long time = System.currentTimeMillis()/1000; 
    private static String[] noKnuddels = {"Willst Du mich eigentlich verarschen?, Du hast doch gar keine Knuddels!", "Du Träumer, knuddeln ohne Knuddels. Dass erzähl mal deinen Psychiater!", "Wer arbeiten geht, der kann auch knuddeln.", "Pech gehabt! Letztes mal hast du mir nicht mal einen Knuddel gegeben, und jetzt hast du gar keine Knuddels mehr! ;)", "Ich bin zwar erst 15, aber in Mathematik bist du wohl nicht auf meiner Höhe!", "Auf welcher Schule warst du nochmal? Es muss die Sonderschule gewesen sein."};
    private static String[] knuddel = {"[C] °RR°knuddelt°BB° [T] sehr herzlich."};
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
  
            if(muted(client, channel)) { 
            	return;
            }
            
            if(moderated(client, channel)) {
            	client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
            	return;
            }
            
            if(!client.hasPermission("cmd.knuddel")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
        	String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            int anzahl = 0;
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(!msg.isEmpty() && msg.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")) {
            	anzahl = Integer.parseInt(msg);
            }
            
            String mesg = knuddel[new Random().nextInt(knuddel.length)];
            mesg = mesg.replace("[C]", String.format("°>_h%s|/serverpp \"|/w \"<°", client.getName().replace("<", "\\<")));
            
            if (nickname.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Ja, wen willst du denn knuddeln???");
                return;
            }

            if (nickname.equalsIgnoreCase(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du kannst dich doch nicht selbst knuddeln!");
                return;
            }

            Client target = Server.get().getClient(nickname);
            boolean online = true;
            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nickname);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            mesg = mesg.replace("[T]", String.format("°>_h%s|/serverpp \"|/w \"<°", target.getName().replace("<", "\\<")));
            mesg = mesg.replace("[K]", String.valueOf(nf.format(target.getKnuddels()+1)));
            
            if (!online && msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }
            
            if(!msg.isEmpty()) {
                if(client.getKnuddels() < anzahl) {
                    client.sendButlerMessage(channel.getName(), noKnuddels[new Random().nextInt(noKnuddels.length)]);
                    return;
                }
            
                if(anzahl > 0) {
                    Popup popup = new Popup(String.format("Knuddels Überweisung an %s durchführen?", target.getName()), String.format("Knuddels Überweisung an %s durchführen?", target.getName()), String.format("#Bitte bestätige, dass du die Überweisung von##_%s an %s_##durchführen möchtest.", anzahl == 1 ? "einen Knuddel" : String.format("%s Knuddels", anzahl), target.getName()), 400, 250);
                    Panel panel2 = new Panel();
                    Button button = new Button("Bestätigen");
                    button.setStyled(true);
                    button.enableAction();
                    panel2.addComponent(button);
                    Button button3 = new Button("Ablehnen");
                    button3.setStyled(true);
                    panel2.addComponent(button3);
                    popup.addPanel(panel2);    

                    popup.setOpcode(ReceiveOpcode.KNUDDEL.getValue(), String.format("%s|%s", target.getName(), anzahl));
                    client.send(popup.toString());
                    return;
                }
            }
            
            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            } 
            
            if (client.getKnuddels() < 1) {
                client.sendButlerMessage(channel.getName(), noKnuddels[new Random().nextInt(noKnuddels.length)]);
                return;
            }
             client.setLevelInfo("knuddel",1);
            channel.broadcastPicAction(">", mesg, String.format("actKnuddel_%s.png", image(client, target)));  
            client.deseaseKnuddels(1);
            target.increaseKnuddels(1);    
            
          
      }}
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


public class lotto {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
       if(arg.isEmpty()) {
            	if(Server.lotto.size() == 0) {
            		client.sendButlerMessage(channel.getName(), "Derzeit hat noch niemand bei Lotto gewonnen.");
            		return;
            	}
            	
                StringBuilder lotto = new StringBuilder("Hilfe, wie Lotto funktioniert, erhälst Du durch Eingabe von _/h lotto_. Hier die Liste der letzten Gewinner von der wöchentlichen Lottoausspielung:##(Nick, Gewonnene Knuddels, Gewinnzahl)");

                for(String datum : Server.lotto.keySet()) {
                	String[] split = Server.lotto.get(datum);
                	String name = split[0].replace("<", "\\<");
                	String zahl = split[1];
                	int knuddels = Integer.parseInt(split[2]);
                	
                	lotto.append("#").append(datum).append(": °>_h").append(name).append("|/serverpp \"|/w \"<° (").append(nf.format(knuddels)).append(" Knuddels, Zahl ").append(zahl).append(")");
                }

                 Popup popup = new Popup("Lotto", "Lotto", lotto.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
            }
            
            if(!client.hasPermission("cmd.lotto")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}

            if(!Server.get().isInteger(arg)) {
            	client.sendButlerMessage(channel.getName(), "Bitte gib eine Zahl an.");
                return;
            }
            
            int zahl = Integer.parseInt(arg);
            
            if(zahl < 0 || zahl > 99) {
                client.sendButlerMessage(channel.getName(), "Bitte gib eine Zahl von 0-100 an.");
                return;
            }
        
            if(client.getKnuddels() < 2) {
                client.sendButlerMessage(channel.getName(), "Du hast leider weniger als die 2 benötigten Knuddels.");
                return;
            }
            
            if(client.getLotto() != 0) {
                client.sendButlerMessage(channel.getName(), String.format("Du hast bereits auf die Zahl %s gesetzt.", client.getLotto()));
                return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Dein Einsatz auf die Zahl %s wurde registriert. 2 Knuddels wurden dir dafür abgezogen. Derzeit sind bereits %s Knuddels insgesamt gesetzt.", arg, Server.get().getLottoJackpot() * 2 + 2));
            client.deseaseKnuddels(2);
            client.setLotto(zahl);
            Server.get().increaseLottoJackpot(2);
          
      }}
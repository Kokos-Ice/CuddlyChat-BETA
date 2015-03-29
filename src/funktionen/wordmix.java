package funktionen;

import static features.hero.timeStampToDate;
import game.WordMixRecord;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import starlight.*;
import tools.*;
import tools.popup.*;


public class wordmix {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   	
          if(!client.hasPermission("cmd.wordmix")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(WordMixRecord.getPoints() == 0) {
        		
                    Popup popup = new Popup("Bestleistung bei WordMix", "Bestleistung bei WordMix", "Bisher hat niemand den Tagesrekord aufgestellt.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
        	}
        	
            String nickname = String.format("°20B>_h%s|/serverpp \"|/w \"<r°", WordMixRecord.getNick().replace("<", "\\<"));
            float nPoints = WordMixRecord.getPoints();
            String nSentence = WordMixRecord.getSentence();
            float nSeconds = WordMixRecord.getSeconds();
            String showString = String.format("°28°_WordMix Bestleistung des Tages:_°15°###_%s_ °15° erreichte _°20°%s Punkte°r°_°15° mit folgendem Satz:##°20°_\"\"%s_\"\"°15°##(in _%s_ Sekunden)##", nickname, df.format(nPoints), nSentence, df.format(nSeconds));

            if (nPoints > 0.00f) {
             Popup popup = new Popup("Bestleistung bei WordMix", "Bestleistung bei WordMix", showString, 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setNewspopup(1);
                        client.send(popup.toString());
                        
            } else {
             Popup popup = new Popup("Bestleistung bei WordMix", "Bestleistung bei WordMix", "Bisher hat niemand den Tagesrekord aufgestellt.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
            }
      }}
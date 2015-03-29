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


public class statistik {
  public static Long time = System.currentTimeMillis()/1000; 
  
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   if(!client.hasPermission("cmd.statistik")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            StringBuilder s = new StringBuilder();
            
        	for(String question : Server.statistik.keySet()) {
        		String[] split = Server.statistik.get(question);
        		String uhrzeit = split[2];
        		String stimmen = split[1];
        		String text = split[0];
        		
                s.append(uhrzeit).append("#").append(stimmen).append(" Stimmen abgegeben#_").append(question).append("_#");
                
                for(String x : text.split("\\|")) {
                	s.append("#").append(x);
                }
                
                s.append("##");
        	}
        	
            Popup popup = new Popup("Vergangene Umfragen", "Vergangene Umfragen", s.toString(), 400, 250);
        	         Panel panel = new Panel();
                         Button buttonMessage3 = new Button("   OK   ");
                         buttonMessage3.setStyled(true);
                         panel.addComponent(buttonMessage3);
                         popup.addPanel(panel);
                         popup.setModern(1);
                         client.send(popup.toString());
                       
            
          
      }}
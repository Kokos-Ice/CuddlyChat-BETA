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
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;


public class points {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   if(!client.hasPermission("cmd.points")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	Client target = arg.isEmpty() || client.getRank() < 10 && !client.checkTeam("Spiele") ? client:Server.get().getClient(arg);
        	
        	if(target == null) {
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}
        	
            StringBuilder points = new StringBuilder();
            String title = String.format("Spiele-Punkte - Übersicht %s", client != target ? String.format(" (%s)", target.getName()):""); 
            points.append("#Bad6:°%30°").append(nf.format(target.getBad6())).append("°%00°");
            points.append("#Blitz!:°%30°").append(nf.format(target.getBlitzPoints())).append("°%00°");
            points.append("#High or Low:°%30°").append(nf.format(target.getHol())).append("°%00°");
            points.append("#Jumpo:°%30°").append(nf.format(target.getJumpopunkte())).append("°%00°");
            points.append("#Mathe:°%30°").append(nf.format((int)target.getMathePoints())).append("°%00°"); 
            points.append("#Mix:°%30°").append(nf.format((int)target.getMixPoints())).append("°%00°"); 
            points.append("#Quess:°%30°").append(nf.format((int)target.getQuessPoints())).append("°%00°");  
            points.append("#Quiz:°%30°").append(nf.format((int)target.getQuizPoints())).append("°%00°");  
            points.append("#ThiefGame:°%30°").append(nf.format(target.getThiefGame())).append("°%00°");
            points.append("#Translate:°%30°").append(nf.format((int)target.getTranslatePoints())).append("°%00°");  
            points.append("#WordMix:°%30°").append(nf.format((int)target.getWordMixPoints())).append("°%00°");
            points.append("#Knuddels:°%30°").append(target.getKnuddels()).append("°%00°");
            Popup popup = new Popup(title, title, points.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
        
          
      }}
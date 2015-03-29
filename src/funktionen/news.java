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


public class news {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
          
          if(!client.hasPermission("cmd.news")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
          
         
            StringBuilder news = new StringBuilder();
            if (arg.equals("all")) {
          
            int id = 1;
            
            for(String[] infos : Server.news) {
            	int an = Integer.parseInt(infos[1]);
            	String uhrzeit = infos[3];
            	String text = infos[2];
            	String von = infos[0];
                String a = "Mitglieder";
            
                if(an == 1) a = "Familymitglieder";
                else if(an == 2) a = "Stammis";
                else if(an == 3) a = "Ehrenmitglieder";
                else if(an > 4 && an < 8) a = "Admins";
                else if(an > 8) a = "Sysadmins";
                
                if(client.getRank() >= an) {
                   String old = news.toString();
                      news.setLength(0);
                    news.append("#_News (").append(id).append(") von °>_h").append(von).append("|/serverpp \"|/w \"<° an alle °BB°").append(a).append("§##").append(text).append("§##geschrieben am ").append(uhrzeit).append("#"+old);
                }
                
                id++;
            }
            } else {
                
                 int id = 1;
            
            for(String[] infos : Server.news) {
            	int an = Integer.parseInt(infos[1]);
            	String uhrzeit = infos[3];
            	String text = infos[2];
            	String von = infos[0];
                String a = "Mitglieder";
            
                if(an == 1) a = "Familymitglieder";
                else if(an == 2) a = "Stammis";
                else if(an == 3) a = "Ehrenmitglieder";
                else if(an > 4 && an < 8) a = "Admins";
                else if(an > 8) a = "Sysadmins";
                
                if(client.getRank() >= an) {
                    news.setLength(0);
                    news.append("#_News (").append(id).append(") von °>_h").append(von).append("|/serverpp \"|/w \"<° an alle °BB°").append(a).append("§##").append(text).append("§##geschrieben am ").append(uhrzeit).append("#");
                }
                
                id++;
            }
                
            }
            
            String title = "+++ "+Server.get().getSettings("CHAT_NAME")+" News Ticker +++";
            Popup popup = new Popup(title, title, news.toString(), 575, 380);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setNewspopup(1);
                 client.send(popup.toString());
                 
      }
      }
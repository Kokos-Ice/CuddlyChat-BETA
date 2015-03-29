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


public class readmehis {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   	if(!client.hasPermission("cmd.readmehis")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            Client target = nickname.isEmpty()?client:Server.get().getClient(nickname);
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            
            if(nickname.equals("del") && !msg.isEmpty()) {
                String title = String.format("Readme%s gelöscht", !msg.equals("all")?"":"-History");
                 Popup popup = new Popup(title, title, String.format("#%s wurde gelöscht.", msg.equals("all")?"Die gesamte History der Readmes":"Der Readme-Text"), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        
                Server.get().query(String.format("DELETE FROM `readmes` WHERE `name` = '%s'%s", client.getID(), msg.equals("all")?"":String.format(" AND `time` = '%s'", msg)));

                if(msg.equals("all")) {
                	client.readmes.clear();
                    client.setReadme(null);
                } else {
                	client.readmes.remove(Long.parseLong(msg));
                }
                
                return;
            }
            
            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            StringBuilder readmehis = new StringBuilder().append("History von ").append(target.getName());
            StringBuilder texte = new StringBuilder();
        	
            String title = String.format("Readme History von %s", target.getName());
           	
            
            List<Long> sortedList = new ArrayList<Long>();
            sortedList.addAll(target.readmes.keySet());
            Collections.sort(sortedList);
            Iterator<Long> iter = sortedList.iterator();

            while (iter.hasNext()) {
            	Long uhrzeit = iter.next();
            	String text = target.readmes.get(uhrzeit);

                texte.append("##\"\"").append(text).append("§\"\"#°10°").append(Server.get().timeStampToDate(uhrzeit)).append(" ").append(Server.get().timeStampToTime(uhrzeit)).append("°r°");
            	
                if(target == client) {
                	texte.append("   °B>Löschen|/readmehis del:").append(uhrzeit).append("<r°");
                }
            }
            
            readmehis.append(texte.toString());
            Server.get();
			int readmeZahl = target.readmes.size();
            
            if(texte.toString().isEmpty() && readmeZahl < 2) {
            	client.sendButlerMessage(channel.getName(), String.format("%s hat keine Readme-History.", target.getName()));
            	return;
            }
        	
            if(target == client) {
            	readmehis.append("###°BB>History komplett löschen|/readmehis del:all<°");
            }
            
         
             Popup popup = new Popup(title, title, readmehis.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setReadmehis(1);
                        client.send(popup.toString());
                     
          
      }}
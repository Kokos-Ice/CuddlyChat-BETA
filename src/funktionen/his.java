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
import static starlight.CommandParser.countChars;
import static starlight.CommandParser.image;
import static starlight.CommandParser.moderated;
import static starlight.CommandParser.muted;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class his {
  public static Long time = System.currentTimeMillis()/1000; 
  
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
         if(!client.hasPermission("cmd.his") && !channel.checkCm(client.getName()) && !channel.checkMcm(client.getName()) && !channel.checkHz(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
        	}
        	
        	
                
               String nickname = KCodeParser.escape(arg).split(":", 2)[0].toLowerCase();
        	
            String msg = "";
             
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            Channel target = arg.isEmpty() || nickname.equals("-")?channel:Server.get().getChannel(nickname);

            if(target == null) {
            	client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", nickname));
            	return;
            }
            
            if(!msg.isEmpty()) {
            	Client nick = Server.get().getClient(msg);
            	
            	if(nick == null) {
            		nick = new Client(null);
            		nick.loadStats(msg);
            		
            		if(nick.getName() == null) {
            			client.sendButlerMessage(channel.getName(), unknownUser(msg));
            			return;
            		}
            	}
            }
            
            StringBuilder his = new StringBuilder();
            int anzahl = target.history.size();
            
            if(anzahl == 0) {
            	client.sendButlerMessage(channel.getName(), String.format("Für den Channel %s sind keine öffentlichen Nachrichten mehr gespeichert.", target.getName()));
                return;
            }
            
            
            
            List<Long> sortedList = new ArrayList<Long>();
            sortedList.addAll(target.history.keySet());
            Collections.sort(sortedList);
            Iterator<Long> iter = sortedList.iterator();

            while (iter.hasNext()) {
            	Long key = iter.next();
            	String[] split = target.history.get(key);
            	String name = split[0].replace("<", "\\<");
            	String text = split[1];
         	 	String uhrzeit = Server.get().timeStampToTime(key);

                his.append("##_");
                
                if(name.toLowerCase().equals(msg.toLowerCase())) {
                	his.append("°R°-->>°r° ");
                }
                
                his.append(name).append(":_ ").append(text).append(" §°10°(").append(uhrzeit).append(")°r°");
            }
            Logger.handle(null,String.format("%s oeffnet die Channel-History von %s", client.getName(), target.getName()));
            Server.get().newSysLogEntry(client.getName(), String.format("Channel History von %s geöffnet", target.getName()));
            String title = String.format("History %s (%s Msgs)", target.getName(), anzahl);
    
            Popup popup = new Popup(title, title, his.toString(), 700, 500);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setChhistory(1);
                 client.send(popup.toString());
                
             
      }
      }
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


public class iplist {
  public static Long time = System.currentTimeMillis()/1000; 
  
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
 
          	if(!client.hasPermission("cmd.iplist")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            String nickname = KCodeParser.escape(arg);
            Client target;
            
            if(arg.isEmpty()) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);
            }
            
            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", nickname));
                    return;
                }
            }
            
            StringBuilder lol = new StringBuilder();
            
            if(client.hasPermission("cmd.iplist") && target.getRegisterIP() != null) {
            	lol.append("_Register-IP_:°%30°").append(target.getRegisterIP()).append("°%00°##");
            }
            
        	lol.append("_Uhrzeit_°%50°_IP-Adresse_°%00°##");
            
        	for(String[] infos : target.logins) {
                lol.append(infos[0]).append("°%50°").append(infos[1]).append("°%00°#");
        	}
            
            lol.append("#_Hinweis_: Mehrere, _zeitlich naheliegende Logins_ werden zu einem einzigen Login _zusammengefasst_.");
            
                 Popup popup = new Popup(String.format("IP List %s", target.getName()), String.format("IP List %s", target.getName()), lol.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setModern(1);
                 client.send(popup.toString());
             
      }}
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
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class ping {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
  	if(!client.hasPermission("cmd.ping")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
                
         
       
                arg = KCodeParser.escape(arg);
        	
        	Client target = arg.isEmpty() ? client:Server.get().getClient(arg);
            boolean online = true;
                     
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(arg);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }
            }

            if (!online) {
            	client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }
                 
            client.sendButlerMessage(channel.getName(), String.format("Ping %s...", target.getName()));
       
               
          
      }}
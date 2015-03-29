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


public class me {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
          if(!client.hasPermission("cmd.me")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            if(muted(client, channel)) { 
            	return;
            }
            
            if(moderated(client, channel)) {
            	client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
            	return;
            }
            
            arg = KCodeParser.parse(client, arg, 5, 10, 20);
            arg = Server.get().parseSmileys(client, arg);

            if (!arg.isEmpty()) {
                channel.broadcastAction(client.getName(), arg);
            }
          
      }}
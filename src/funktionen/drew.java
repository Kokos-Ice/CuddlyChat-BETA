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


public class drew {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
     
        	if(!client.hasPermission("cmd.drew")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            String re = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            Client target = Server.get().getClient(re);
            boolean online = true;
            
            if (arg.length() > re.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            msg = KCodeParser.escape(msg);
            
            if(re.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaÃŸen benutzen:#/move NICK:CHANNEL#(Schiebt NICK in den CHANNEL.)");
                return;
            }
           
            if(target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(re);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(re));
                    return;
                }
            }
            
            if(!online) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }
            
            if(target.getName().equals(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du kannst dich nicht selbst verschieben!");
                return;
            }
            
            if(target.getName().equals(Server.get().getButler().getName())) {
                client.sendButlerMessage(channel.getName(), String.format("%s kann nicht verschoben werden.", Server.get().getButler().getName()));
                return;
            }
            
            if(target.getRank() > client.getRank()) {
                client.sendButlerMessage(channel.getName(), String.format("Du hast nicht die benötigten Rechte, %s zu verschieben.", target.getName()));
                return;
            }
            
            Channel channelTo = Server.get().getChannel(msg);
            
            if(channelTo == null) {
                client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", msg));
                return;
            }
            
            if(target.getChannels().contains(channelTo)) {
                client.sendButlerMessage(channel.getName(), String.format("%s ist bereits im Channel %s!", target.getName(), channelTo.getName()));
                return;
            }
            
           
            
            client.sendButlerMessage(channel.getName(), String.format("Du hast %s in den Channel %s verschoben.", target.getName(), channelTo.getName()));
            target.joinChannel(channelTo);
            target.send(PacketCreator.channelFrame(channelTo, target.getName(), target.newMessages.size()));
            channelTo.join(target);
            target.sendButlerMessage(channelTo.getName(), String.format("Du wurdest von %s in den Channel %s verschoben.", client.getName(), channelTo.getName()));
        
      }}
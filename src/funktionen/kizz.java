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


public class kizz {
 
  
        private static String[] kizzsend = {"Deine Lippen bewegen sich fordernd auf [T] zu..."}; 
    private static String[] kizzbecome = {"[T]'s Lippen bewegen sich auf deine zu... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "[T] hofft sehr, dass du dich zu einem zärtlichen Zungenkuss hinreißen lässt... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "[T] nähert sich dir mit feuchten Lippen... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "[T] hätte gerade richig Lust auf einen Zungenkuss mit dir... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "Jetzt könnte es zärtlich werden [T]'s Lippen kommen auf deine zu... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "[T] bewegt sich langsam mit feuchten Lippen auf dich zu... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "[T] kommt auf dich zu und setzt mit den Lippen zu einem Zungenkuss an... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_", "[T] würde dich gerade nur zu gerne leidenschaftlich küssen... °>finger.b.gif<° _°BB>_hZungenkuss erwidern|/kiss [T]<°_"};
   
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
  
          if(!client.hasPermission("cmd.kizz")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String nickname = KCodeParser.escape(arg);

            if (nickname.isEmpty()) {
                return;
            }

            if (nickname.equalsIgnoreCase(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du kannst dich doch nicht selbst küssen!");
                return;
            }

            
            Client target = Server.get().getClient(nickname);

            if (target == null) {
                client.sendButlerMessage(channel.getName(), unknownUser(nickname));
            } else if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° is currently online _in a different channel_.", target.getName().replace("<", "\\<")));
            } else {
        
              // Original  client.sendButlerMessage(channel.getName(), String.format("Your lips are approaching °>_h%s|/serverpp \"|/w \"<°...", target.getName().replace("<", "\\<")));  
            
                 if (target == Server.get().getButler()) {
                 client.sendButlerMessage(channel.getName(),"Unser Butler ist leider bereits zu Alt dafür!");
                 return;
             }
     
                String text = kizzsend[new Random().nextInt(kizzsend.length)];
                text = text.replace("[T]",target.getName());
                
             client.sendButlerMessage(channel.getName(), text);
             
             
                String text2 = kizzbecome[new Random().nextInt(kizzbecome.length)];
                text2 = text2.replace("[T]",client.getName());
      target.sendButlerMessage(channel.getName(),text2);
            // Original    target.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° comes closer to you, and sets up his lips for a french kiss... °>finger.b.gif<° _°BB>_hkiss back|/kiss %s<°_", client.getName().replace("<", "\\<"), client.getName().replace("<", "\\<")));
                Server.actions.put((System.currentTimeMillis()/1000), new String[] {"kizz", client.getName(), target.getName(), channel.getName(), "30"});
            
            }
      }}
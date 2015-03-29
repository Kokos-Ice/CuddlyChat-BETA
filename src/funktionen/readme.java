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


public class readme {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   
        
         	if(!client.hasPermission("cmd.readme")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	int length;
        	
        	if(!client.hasPermission("cmd.profil.kcode.use")) {
        	
              String[] l = client.getFeature("Pimped-Readme");
              Feature ft = Server.get().getFeature("Pimped-Readme");
                      if (ft == null) { return; }
                      
                    if (l[0].equals("2")) {
                      arg = KCodeParser.parse(client, arg, 0, 16, 16);
                  } else {
                    arg = KCodeParser.noKCode(arg);
                  }
        		
        		length = 60;
        	} else {
                    
                    
        		arg = KCodeParser.parse(client, arg, 0, 16, 16);
        		length = 120;
        	}
                
                
              String[] l = client.getFeature("Readme-Verlängerung");
              Feature ft = Server.get().getFeature("Readme-Verlängerung");
 
                if (ft == null) { return; }
               if (l[0].equals("2")) {
                   length+=50;
               }
        	
            if(arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/readme TEXT oder /readme !#(TEXT ist von allen einsehbar, auf deren Friendliste du stehst oder die dein Profil anschauen. Dieser TEXT bleibt bestehen, bis du ihn mit /readme ! löschst oder einen neuen speicherst.)");
            	return;
            }
        	
            if(arg.equals("!")) {
            	client.sendButlerMessage(channel.getName(), "Readme gelöscht.");
            	client.setReadme(null);
            	return;
            }
            
            
            if(arg.length() > length && !client.hasPermission("unlimitedchars")) {
                arg = arg.substring(0, length);
            }
        
            
            arg = Server.get().parseSmileys(client, arg);
        	
            int readmeZahl = client.readmes.size();
            client.sendButlerMessage(channel.getName(), String.format("%sAlle deine Freunde können nun folgenden Text in deinem Profil lesen: \\\"%s§\\\"%s", arg.length() > length ? "Da dein Text über "+length+" Zeichen enthielt, musste er gekürzt werden.#" : "", arg, readmeZahl>1?"  _°>_h[His]|/readmehis<°_":""));
            Server.get().query(String.format("INSERT INTO `readmes` SET `name` = '%s', `text` = '%s', `time` = '%s'", client.getID(), arg.replace("'", "\\'"), time));
            client.setReadme(arg);
             client.setLevelInfo("readme",1);
            client.readmes.put(time, arg);
        
      }}
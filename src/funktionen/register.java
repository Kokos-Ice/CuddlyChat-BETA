package funktionen;

import static features.hero.timeStampToDate;
import game.WordMixRecord;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;


public class register {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   	
          	if(!client.hasPermission("cmd.register")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
        	arg = arg.toLowerCase();
        	String text = "Bitte die Funktion folgendermaßen benutzen:#/register on/off#(Die Registration ein- oder ausschalten.)";
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), text);
        		return;
        	}
             
        	if(arg.equals("on") || arg.equals("off")) {
        		client.sendButlerMessage(channel.getName(), String.format("Die Registration wurde %sgeschaltet.", arg.equals("on") ? "ein" : "aus"));
        		Settings.setRegistration(arg.equals("on")?1:0);
        		Server.get().newSysLogEntry(client.getName(), String.format("Registration %sgeschaltet", arg.equals("on")?"ein":"aus"));
        		return;
        	}
             
        	client.sendButlerMessage(channel.getName(), text);
      
      }}
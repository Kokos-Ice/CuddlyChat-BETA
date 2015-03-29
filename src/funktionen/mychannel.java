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


public class mychannel {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
  
          if(arg.toLowerCase().equals("cmchannels")) {
        		client.sendButlerMessage(channel.getName(), "Du bist in keinem MyChannel CM.");
        		return;
        	}
        	
        	if(!client.hasPermission("cmd.mychannel")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	PacketCreator.createMyChannelWindow(client);
                
                
                
      }}
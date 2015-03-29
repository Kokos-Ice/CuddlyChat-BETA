package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class diceo {


    public static void functionMake(Client client,Channel channel, String arg) {
     
        if(!client.hasPermission("cmd.diceo")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String variablen = tools.DiceCreator.dice(arg,1);
            String[] teil = variablen.split("\\|");
        
            if (teil[5].equals("0")) {
                channel.broadcastAction("°BB°", String.format("> %s rollt %s Würfel%s...#%s:%s = _%s_",client.getName(),teil[0],teil[1],teil[2],teil[3],teil[4]));
            } else {
                client.sendButlerMessage(channel.getName(), String.format("%s", teil[2]));
            }        
    }
}
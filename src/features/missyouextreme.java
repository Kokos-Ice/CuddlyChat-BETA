package features;

import starlight.*;
import tools.*;
import tools.popup.*;
import java.util.*;

public class missyouextreme {
  
    public static void functionMake(Client client,Channel channel, String arg) {
 
        
         if (arg.isEmpty()) {
         client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/missyouextreme NICK#(NICK hat dir den Kopf verdreht.)");
            return;
         }
        
        if (arg.startsWith("-") && !arg.equals("-")) {
            
            if (client.getTurnedHeadTo().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Du vermisst niemanden extreem.");
             return;
        }
        
        
        
        
        
        }
    }
}

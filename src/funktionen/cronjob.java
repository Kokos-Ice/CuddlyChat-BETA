package funktionen;

import starlight.*;
import tools.Logger;


public class cronjob {
    
      public static void functionMake(Client client,Channel channel, String arg) {
     
          
          
         if(!client.hasPermission("cmd.cronjob")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
          
          
           Logger.handle(null,String.format("Update wird manuell gestartet von "+client.getName()+"!"));
           tools.Cronjob.execute(client);
          
      }
}

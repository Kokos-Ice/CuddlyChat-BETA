package funktionen;

import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class sound {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg,String cmd) {
   
                         if(!client.hasPermission(String.format("cmd.sound", cmd))) {
                         client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                         return;
                  }
        	
                         for(Client c : channel.getClients()) {
                             if (arg.endsWith(".mp3")) {
                              c.send(PacketCreator.playMp3(arg));
                             } else {
                         c.send(PacketCreator.playSound(arg));
                             }
    
                  }
     
                
                
          
      }}
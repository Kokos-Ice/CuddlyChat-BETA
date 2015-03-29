package funktionen;

import starlight.*;
import tools.*;


public class fotoalbum {
  
     public static void functionMake(Client client,Channel channel, String arg) {
 
      if (!arg.isEmpty()) {
             client.send(PacketCreator.openURL(String.format("%sindex.php?page=photo_album&id=%s&photo", Server.get().getURL(), arg), "_blank"));    
         
      }   
         
         
         
}
}

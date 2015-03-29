

package tools;

import java.util.Collections;
import java.util.List;
import starlight.*;

public class ButlerTipps {
   
    
    public static void sendTipps() {
        List texte = Server.get().getButlerTipps();
        Collections.shuffle(texte);
     for(Channel x : Server.get().getChannels()) {
         
         String text = (String) texte.get(0);
         x.broadcastButlerMessage(text);
         
         
     }   
        
        
    }
    
}

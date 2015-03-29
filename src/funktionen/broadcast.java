package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class broadcast {


    public static void functionMake(Client client,Channel channel, String arg) {
     

if(!client.hasPermission("cmd.broadcast")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            arg = Server.get().parseSmileys(client, arg);
            
            if (!arg.isEmpty()) {
                for (Channel ch : Server.get().getChannels()) {
                    if(ch.countClients() > 1) {
                        ch.broadcastAction("°BB°", String.format("Durchsage von °>_h%s|/serverpp \"|/w \"<°: _°20°%s", client.getName().replace("<", "\\<"), arg));
                    }
                }
            }
    }
}
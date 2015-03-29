package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class gb {


    public static void functionMake(Client client,Channel channel, String arg) {
     
        
        if(!client.hasPermission("cmd.gb")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            arg = KCodeParser.escape(arg);

            if (arg.isEmpty()) {
                client.send(PacketCreator.openURL(String.format("%sindex.php?page=gb_edit&hp&accesscookie="+Functions.saveLoginCookie(client), Server.get().getURL()), "_blank"));
                return;
            }
            
            Client target = Server.get().getClient(arg);

            if (target == null) {
                target = new Client(null);
                target.loadStats(arg);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }         
            } 
            
            if (target.getHP() == 0) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hat derzeit keine Homepage angelegt.", target.getName().replace("<", "\\<")));
                return;
            }
        
            client.send(PacketCreator.openURL(String.format("%sindex.php?page=gb_user&n=%s&hp", Server.get().getURL(), target.getName()), "_blank"));    
 
    }
}
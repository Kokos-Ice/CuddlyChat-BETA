package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class forum {


    public static void functionMake(Client client,Channel channel, String arg) {
     
    
        if(!client.hasPermission("cmd.forum")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	client.send(PacketCreator.openURL(String.format("%sindex.php?page=forum&accesscookie="+Functions.saveLoginCookie(client), Server.get().getURL()), "_blank"));
    }
}
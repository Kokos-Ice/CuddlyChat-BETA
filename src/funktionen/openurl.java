package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class openurl {


    public static void functionMake(Client client,Channel channel, String arg) {
     
        if(!client.hasPermission("cmd.openurl")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	client.send(PacketCreator.openURL(arg, "_blank"));
    }
}
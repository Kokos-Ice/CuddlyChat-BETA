package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class l {


    public static void functionMake(Client client,Channel channel, String arg) {
     
        if(!client.hasPermission("cmd.l")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		return;
        	}

        	client.sendButlerMessage(channel.getName(), "Der Song wird gestartet... Du kannst den Song jederzeit mit _°BB>/dj stop|\"<r°_ stoppen.");
        	client.send(PacketCreator.playMp3(null));
                
        	client.send(PacketCreator.playMp3(String.format("%s%s", Server.get().getURL(), arg)));
                
    }
}
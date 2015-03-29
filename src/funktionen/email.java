package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class email {


    public static void functionMake(Client client,Channel channel, String arg) {
        if(!client.hasPermission("cmd.email")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}

            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            client.send(PacketCreator.createEmailWindow(nickname, msg));
    }
}
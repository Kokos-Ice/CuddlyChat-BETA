package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class hz {


    public static void functionMake(Client client,Channel channel, String arg) {
    


if(!client.hasPermission("cmd.hz")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		StringBuilder hze = new StringBuilder("°B°_");
        		String title = "HZ-Editor - Übersicht";
        		
        		for(Channel c : Server.get().getChannels()) {
        			if(c.getTemp() == 0) {
        				hze.append("°>").append(c.getName()).append("|/hz \"<°#");
        			}
        		}
        		
        		
                        Popup popup = new Popup(title, title, hze.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setModern(1);
                        client.send(popup.toString());
                        return;
                 
        		
        	}
        	
        	Channel target = Server.get().getChannel(arg);
        	
        	if(target == null) {
        		client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", arg));
        		return;
        	}
        	
        	client.send(PacketCreator.createHZEditorWindow(target.getName(), target.getHZ().replace("||", ", ").replace("|", "")));
       
            
            
    }
}

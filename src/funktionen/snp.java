package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class snp {


    public static void functionMake(Client client,Channel channel, String arg) {
if(!client.hasPermission("cmd.snp")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(KCodeParser.escape(arg).equals("list")) {
        		if(Server.snp.size() == 0) {
        			client.sendButlerMessage(channel.getName(), "Es sind keine Passwortneusetzungen gespeichert.");
        		} else {
            		StringBuilder list = new StringBuilder("_Nick°%40°Neugesetzt von°%75°Datum°%00°_#");
            		
            		for(long zeit : Server.snp.keySet()) {
            			String[] split = Server.snp.get(zeit);
            			String name = split[0];
                    	String von = split[1];
                    	String datum = Server.get().timeStampToDate(zeit);
                    	
                    	list.append(name).append("°%40°").append(von).append("°%75°").append(datum).append("°%00°#");
            		}
            		
            		PopupNewStyle popup = new PopupNewStyle("Kürzlich geänderte Passwörter", "Kürzlich geänderte Passwörter", list.toString(), 450, 375);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Kürzlich geänderte Passwörter abgerufen", client.getName()));
                        return;    

                       
                        }
            } else {
            	client.send(PacketCreator.createSnpWindow(client.getName(), "", Password.generateRandom(8)));
            }
    }
}
       
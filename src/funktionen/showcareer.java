package funktionen;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;




public class showcareer {
    
   
    
    public static void functionMake(Client client,Channel channel, String arg) {
  
        	if(!client.hasPermission("cmd.showcareer")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String nickname = KCodeParser.escape(arg);
            
            if(nickname.isEmpty()) {
                nickname = client.getName();
            }
            
            Client target = Server.get().getClient(nickname);
            
            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            StringBuilder career = new StringBuilder();
            String title = String.format("Laufbahn von %s", target.getName());
            career.append("#°%04°").append(target == Server.get().getButler() ? "21.05.1935" : target.getRegistrationDate()).append("°%34°Registration°%00°");
              
            if(!target.getCareer().isEmpty()) {
            	for(String out : target.getCareer().split("\\|")) {
            		if(!out.isEmpty()) {
            			String datum = out.split(" ")[0];
            			String text = out.split(" ", 2)[1];
    			
            			career.append("#°%04°").append(datum).append("°%34°").append(text).append("°%00°");
            		}
            	}
    		}
                    
                 Popup popup = new Popup(title, title, career.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString());
              
    }}
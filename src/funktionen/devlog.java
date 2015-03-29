package funktionen;

import static funktionen.f.time;
import handler.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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




public class devlog {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
        
           if(!client.hasPermission("cmd.devlog")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	StringBuffer data = new StringBuffer();
         data.append("°B°_Hier findest du alle Developer-Logfiles_°K°#");
               
                   
                   PoolConnection pcon = ConnectionPool.getConnection();
		Statement stmt = null;

		try {
			Connection con = pcon.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM `dev_log` ORDER BY `id` DESC LIMIT 15");
			
			while (rs.next()) {
				//try {
                                    String color = "°K°";
                                    String type = rs.getString("type");
                                    if(type.equals("IN")) {
                                        color = "°[0,80,0]°";
                                    } else if(type.equals("OUT")) {
                                        color = "°[80,0,0]°";
                                    }
                                    data.append(color);
                                    data.append(KCodeParser.escape(rs.getString("data")));
                                    data.append("#");
                                    /*Panel pan = new Panel();
                                    TextArea tx = new TextArea(1, 1, rs.getString("data"));
                                    pan.addComponent(new Label(rs.getInt("id") + ", " + rs.getString("type")));
                                    pan.addComponent(tx);
                                    popup.addPanel(pan);*/
                                    //channelStyles.put(rs.getInt("id"), new ChannelStyle(rs));
				/*}catch(Exception ex) {
					
				}*/
			}
			rs.close();
                       
                   } catch(SQLException e) {
                   e.printStackTrace();   
                   }
                 Popup popup = new Popup("DEV Log", null, data.toString(), 800, 600);
                   popup.setDesign(2);
                
        Panel panel2 = new Panel();
        Button button = new Button("Leeren (TRUNCATE)");
        button.enableAction();
        panel2.addComponent(button);
        panel2.addComponent(new Button("OK"));
        popup.addPanel(panel2);
        popup.setOpcode(ReceiveOpcode.DL.getValue(), "truncate");
        client.send(popup.toString());
                
        
    }}
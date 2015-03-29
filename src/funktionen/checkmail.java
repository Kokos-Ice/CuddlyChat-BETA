package funktionen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class checkmail {


    public static void functionMake(Client client,Channel channel, String arg) {


 	if(!client.hasPermission("cmd.checkmail")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/checkmail E-Mail-Adresse#(Prüft welche User sich mit dieser E-Mail eingeloggt haben.)");
        		return;
        	}
        	
        	StringBuilder email = new StringBuilder();
        	int treffer = 0;
        	PoolConnection pcon = ConnectionPool.getConnection();
            Statement ps = null;

            try {
                Connection con = pcon.connect();
                ps = con.createStatement();
                ResultSet rs = ps.executeQuery(String.format("SELECT `name`, `registration` FROM `accounts` WHERE `email` = '%s' ORDER BY `registration` DESC", arg.replace("-", ".")));
                
                while(rs.next()) {
                	String name = rs.getString("name");
                	long registration = rs.getLong("registration");
                	String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(registration), Server.get().timeStampToTime(registration));
                	Client c = Server.get().getClient(name);
                	
                	if(c != null) {
                		email.append("_°B");
                	} else {
                		email.append("°");
                	}
                	
                	email.append(">_h").append(name.replace("<", "\\<")).append("|/serverpp \"|/w \"<%40");
                	
                	if(c != null) {
                		email.append("r°_");
                	} else {
                		email.append("°");
                	}
                	
                	email.append("(").append(uhrzeit).append(", E-Mail)°%00°#");
                	treffer++;
                }
                rs.close();
                rs = ps.executeQuery(String.format("SELECT `name`, `uhrzeit` FROM `loginlist` WHERE `ip` = '%s' ORDER BY `uhrzeit` DESC", arg.replace("-", ".")));
                
                while(rs.next()) {
                	String name = rs.getString("name");
                	String uhrzeit = rs.getString("uhrzeit");
                	Client c = Server.get().getClient(name);

                	if(c != null) {
                		email.append("_°B");
                	} else {
                		email.append("°");
                	}
                	
                	email.append(">_h").append(name.replace("<", "\\<")).append("|/serverpp \"|/w \"<%40");

                	if(c != null) {
                		email.append("r°_");
                	} else {
                		email.append("°");
                	}
                	
                	email.append("(").append(uhrzeit).append(")°%00°#");
                	treffer++;
                }
                rs.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }

                pcon.close();
            }
            
            if(email.toString().isEmpty()) {
            	client.sendButlerMessage(channel.getName(), String.format("Keine Übereinstimmungen mit der _E-Mail %s_ gefunden.", arg));
            	return;
            }
            Server.get().newSysLogEntry(client.getName(), String.format("Suche nach Nicknames mit der E-Mail-Adresse: (%s)", arg, treffer));
            String title = String.format("%s - %s Treffer", arg, treffer);
            
             Popup popup = new Popup(title, title, email.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;    
        
    
    }
}
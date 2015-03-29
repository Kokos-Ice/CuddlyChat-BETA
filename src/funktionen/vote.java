package funktionen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;


public class vote {
    
       public static void functionMake(Client client,Channel channel, String arg) {
 if(!client.hasPermission("cmd.vote")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
                if(client.getWahlsperre() != 0) {
                   client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Wahlen gesperrt.");
                   return;
                }
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		int number = 1;
        		StringBuilder wahlen = new StringBuilder();
        		
        		for(String election : Server.elections.keySet()) {
        			int aktiv = Integer.parseInt(Server.elections.get(election)[0]);
        			
        			if(aktiv == 2) {
                		if(number != 1) {
                			wahlen.append(", ");
                		}
                		
                		wahlen.append(election);
                		number++;
        			}
        		}
                
                if(wahlen.toString().isEmpty()) {
            		client.sendButlerMessage(channel.getName(), "Derzeit laufen keine Wahlen.");
                	return;
                }
                
        		client.sendButlerMessage(channel.getName(), String.format("Folgende Wahlen laufen derzeit:#%s", wahlen.toString()));
        		return;
        	}

            List<String> nicks = new ArrayList<String>();
            
        	PoolConnection pcon = ConnectionPool.getConnection();
            Statement ps = null;

            try {
                Connection con = pcon.connect();
                ps = con.createStatement();
                ResultSet rs = ps.executeQuery(String.format("SELECT `name`, `aktiv`, `nominated` FROM `wahlen` WHERE `name` = '%s'", arg));
                
                if (!rs.next()) {
                    client.sendButlerMessage(channel.getName(), String.format("Es gibt im Moment keine %s-Wahlen.", arg));
                	return;
                }
                
                
                
                if(client.getWahlsperre() == 1) {
                   client.sendButlerMessage(channel.getName(), String.format("Du bist derzeit für alle Wahlen gesperrt."));
                   return;
                   }

                int aktiv = rs.getInt("aktiv");
                String nominated = rs.getString("nominated");
                
                
              if(aktiv == 1) {
                	client.sendButlerMessage(channel.getName(), "Momentan läuft die Nominierungsphase.");
                	return;
                }
              
                
                if(aktiv == 3 || aktiv == 0) {
                	client.sendButlerMessage(channel.getName(), String.format("Die %s-Wahl ist leider schon vorüber.", arg));
                	return;
                }
/*
                 if (client.getRank() < 10) {
                if (client.getLC().isEmpty() || client.getLcmonths() == 0) {
                 // man darf net weil man kein lc hat  oder kein monat lc  
              return;
                }
                 }
                */
                
                
                
                if (nominated.trim().isEmpty()) {
                 client.sendButlerMessage(channel.getName(),"Keiner nomiert-Error. :D");
                 return;
                    }
                
                for(String x : nominated.split("\\|")) {
                	if(!x.isEmpty()) {
                		String nick = x.split("~")[0].trim();
                		String stimmen = x.split("~")[1];
                		
                		nicks.add(String.format("%s~nom. von %s", nick, stimmen));
                	}
                }
                
                rs.close();
                rs = ps.executeQuery(String.format("SELECT `name` FROM `wahlenvoted` WHERE `name` = '%s' and `wahl` = '%s'", client.getName(), arg));
                if(rs.next()) {
                	client.sendButlerMessage(channel.getName(), "Sie haben bereits Ihre Stimme abgegeben.");
                	return;
                }
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
        	
        	client.send(PacketCreator.votePopup(client.getName(),arg, nicks));
        	
        	if(arg.equals("admin")) {
        	        Popup popup = new Popup("Admin", "Admin", "Bei Infected werden die Admins aus den Familymitgliedern und den Stammis gewählt.##_Wichtig:_#bitte fair bleiben und _nur mit Deinem Hauptnick_, mit dem Du eventuell auch selbst nominiert bist, _wählen_. Andernfalls werden _alle_ Deine Nicks gesperrt und Du erhältst eine Wahlsperre für die kommenden Chatwahlen!#Um einem nominierten Kandidaten Plus- oder Minusstimmen zu geben, gehe folgendermaßen vor##1. Gewünschten Nick im Textfeld unter'Kandidaten'eingeben.#2. Aus der unteren Liste passenden Nick auswählen.#3. Mit dem Plus-Button Positiv- und dem Minus-Button Negativstimmen vergeben.#4. Genauso mit allen weiteren Nicks verfahren.#5. Du kannst maximal fünf Positiv- und fünf Negativstimmen (maximal 2 pro Nick) vergeben.##Überlege Deine Entscheidung gut, und wähle vor allem die Chatter, die sich immer freundlich und hilfsbereit (vor allem den Neuen gegenüber) verhalten haben und die nötige Reife besitzen.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                
                }
       }}
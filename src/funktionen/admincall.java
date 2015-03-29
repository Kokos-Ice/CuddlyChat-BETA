package funktionen;

import static funktionen.f.time;
import handler.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import starlight.*;
import static starlight.CommandParser.parse;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;




public class admincall {
        private static NumberFormat nf;
    private static DecimalFormat df;
   
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }
     
    public static void functionMake(Client client,Channel channel, String arg,String cmd) {
        if(!client.hasPermission(String.format("cmd.%s", cmd))) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	if(arg.equals("-") || arg.isEmpty()) {
            	client.send(PacketCreator.createAdminCallWindow());
        		return;
        	}
        	
        	if(arg.equals("help")) {
        		parse("/h", client, channel, false);
        		return;
        	}
        	
        	String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
             
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(msg.isEmpty() || nickname.isEmpty()) {
            	return;
            }
            
            if(nickname.equals("complaint")) {
        		client.send(PacketCreator.createAdmincallWindow(msg, "", ""));
            	return;
            }
            
            if(nickname.equals("info")) {
            	PoolConnection pcon = ConnectionPool.getConnection();
                Statement ps = null;

                try {
                    Connection con = pcon.connect();
                    ps = con.createStatement();
                    ResultSet rs = ps.executeQuery(String.format("SELECT `id`, `ergebnis`, `bewertet`, `channel`, `abgesetzt`, `angenommen`, `bearbeiter`, `beschuldigter`, `beschwerdeführer`, `verstoss`, `begründung` FROM `admincalls` WHERE `id` = '%s'", msg));
                    
                    if(!rs.next()) {
                    	client.sendButlerMessage(channel.getName(), String.format("Es existiert kein Notruf mit der ID %s!", msg));
                    } else {
                    	String beschuldigter = rs.getString("beschuldigter");
                    	String beschwerdeführer = rs.getString("beschwerdeführer");
                    	String verstoß = rs.getString("verstoss");
                    	String begründung = rs.getString("begründung");
                    	String bearbeiter = rs.getString("bearbeiter");
                    	String channe = rs.getString("channel");
                    	long abgesetzt = rs.getLong("abgesetzt");
                    	String ergebnis = rs.getString("ergebnis");
                    	long bewertet = rs.getLong("bewertet");
                    	long angenommen = rs.getLong("angenommen");
                    	
                    	if(!client.getName().equals(beschuldigter) && !client.getName().equals(bearbeiter) && !client.hasPermission("seeadmincalls")) {
                    		client.sendButlerMessage(channel.getName(), "Du kannst diesen Notruf nicht einsehen!");
                    		return;
                    	}

                    	String title = String.format("Informationen zum Notruf *%s", msg);
                    	StringBuilder xd = new StringBuilder();
                    	Client bf = Server.get().getClient(beschwerdeführer);
                    	Client b = Server.get().getClient(beschuldigter);
                    	StringBuilder ereignislog = new StringBuilder();
                    	boolean bfo = true;
                    	boolean bo = true;
                    	
                    	if(bf == null) {
                    		bfo = false;
                    		bf = new Client(null);
                    		bf.loadStats(beschwerdeführer);
                    	}
                    	
                    	if(b == null) {
                    		bo = false;
                    		b = new Client(null);
                    		b.loadStats(beschuldigter);
                    	}

                    	xd.append("_ID_:°%40°").append(msg).append("°%00°#");
                    	xd.append("_Verstoß_:°%40°").append(verstoß).append("°%00°#");
                    	
                    	if(!ergebnis.isEmpty()) {
                        	xd.append("_Bewertet als_:°%40°").append(ergebnis).append("°%00°#");
                    	}
                    	
                    	xd.append("_Channel_:_°BB%40>_h").append(channe).append("|/go \"|/go +\"<r%00°_#");
                    	xd.append("_Beschwerdeführer_:_°%40BB>_h").append(bf.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_ (").append(bf.getAge()).append(")");
                    	
                    	if(bf.getGender() == 1) {
                    		xd.append("°>male.png<°");
                    	} else {
                    		xd.append("°>female.png<°");
                    	}

                    	if(bearbeiter.equals(client.getName())) {
                    		xd.append("#(").append(bf.getRankLabel(bf.getRank())).append(", ").append(nf.format(bf.getOnlineTime()/60)).append(" Minuten (Reg.: ").append(bf.getRegistrationDate()).append(" ").append(bf.getRegistrationTime()).append("), ").append(bfo ? "_Online_" : "Offline").append(")");
                    	}
                    	
                    	xd.append("°%00°#_Beschuldigter_:_°%40BB>_h").append(b.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_ (").append(b.getAge()).append(")");
                    	
                    	if(b.getGender() == 1) {
                    		xd.append("°>male.png<°");
                    	} else {
                    		xd.append("°>female.png<°");
                    	}
                    	
                    	if(bearbeiter.equals(client.getName())) {
                    		xd.append("#(").append(b.getRankLabel(b.getRank())).append(", ").append(nf.format(b.getOnlineTime()/60)).append(" Minuten (Reg.: ").append(b.getRegistrationDate()).append(" ").append(b.getRegistrationTime()).append("), ").append(bo ? "_Online_" : "Offline").append(")");
                    	}
                    	
                    	xd.append("°%00#°");
                    	
                    	ereignislog.append("#_").append(Server.get().timeStampToTime(abgesetzt)).append("_: Notruf abgesetzt");
                    	
                    	if(!bearbeiter.isEmpty()) {
                    		xd.append("_Zugewiesen_:_°BB%40>_h").append(bearbeiter.replace("<", "\\<")).append("|/go \"|/go +\"<r%00°_#°%00°");
                    		
                    		ereignislog.append("#_").append(Server.get().timeStampToTime(angenommen)).append("_: Notruf zugewiesen");
                    	}
                    	
                    	if(!ergebnis.isEmpty()) {
                    		ereignislog.append("#_").append(Server.get().timeStampToTime(bewertet)).append("_: Fall abgeschlossen");
                    	}

                    	xd.append("#_Ereignislog_:°%40°_").append(Server.get().timeStampToDate(abgesetzt)).append("_").append(ereignislog.toString().isEmpty() ? "" : String.format("#%s", ereignislog.toString())).append("°%00°#");
                    	
                    	xd.append("_Begründung_:##").append(begründung);
                    	
                        Popup popup = new Popup(title, title, xd.toString(), 550, 350);
                        
                        if(bearbeiter.equals(client.getName()) && ergebnis.isEmpty()) {
                        	Panel panel2 = new Panel();
                        	String[] lala = {"Notruf berechtigt", "Notruf unberechtigt", "Notruf-Missbrauch"};
                        	panel2.addComponent(new Label(String.format("Bewerten als:")));
                        	panel2.addComponent(new Choice(lala, verstoß));
                        	popup.addPanel(panel2);
                        }
                        
                        Panel panel5 = new Panel();
                        Button button = new Button("   OK   ");
                        button.setStyled(true);
                        
                        if(bearbeiter.equals(client.getName()) && ergebnis.isEmpty()) {
                            button.enableAction();
                        	popup.setOpcode(ReceiveOpcode.ADMINCALL.getValue(), String.format("lala~%s", msg));
                        }
                        panel5.addComponent(button);
                        popup.addPanel(panel5);
                        
                        client.send(popup.toString());
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
                
            	return;
            }
            
            if(nickname.equals("ok")) {
            	PoolConnection pcon = ConnectionPool.getConnection();
                Statement ps = null;

                try {
                    Connection con = pcon.connect();
                    ps = con.createStatement();
                    ResultSet rs = ps.executeQuery(String.format("SELECT `id`, `bearbeiter`, `beschwerdeführer`, `beschuldigter` FROM `admincalls` WHERE `id` = '%s'", msg));
                    
                    if(!rs.next()) {
                    	client.sendButlerMessage(channel.getName(), String.format("Es existiert kein Notruf mit der ID %s!", msg));
                    } else {
                		Client nrs = Server.get().getNRS();
                		if(nrs == null) {
                			nrs = new Client(null);
                			nrs.loadStats("Notruf-System");
                		}
                		
                    	String bearbeiter = rs.getString("bearbeiter");
                    	String beschwerdeführer = rs.getString("beschwerdeführer");
                    	String beschuldigter = rs.getString("beschuldigter");
                    	
                    	if(!bearbeiter.isEmpty()) {
                    		client.sendButlerMessage(channel.getName(), String.format("%s den Notruf bereits angenommen. %s", bearbeiter.equals(client.getName()) ? "Du hast" : String.format("%s hat", bearbeiter), bearbeiter.equals(client.getName()) ? String.format("(_°BB>Informationen|/admincall info:%s<r°_)", msg) : ""));
                    		return;
                    	}

                    	client.sendButlerMessage(channel.getName(), String.format("Du hast den _Notruf angenommen_. (_°BB>Informationen|/admincall info:%s<r°_)", msg));
                    	Server.get().query(String.format("UPDATE `admincalls` SET `bearbeiter` = '%s', angenommen = '%s' WHERE `id` = '%s'", client.getName(), System.currentTimeMillis()/1000, msg));
                    	parse(String.format("/admincall info:%s", msg), client, channel, false);
                    	Server.get().newMessage(nrs.getName(), beschuldigter, "", String.format("Die Bearbeitung der über Sie eingegangenen _Beschwerde °BB>_h*%s|/admincall info:%s<r°_ wurde soeben an _den Admin °BB>_h%s|/serverpp \"/w \"<r°_ zur Bearbeitung übergeben.##_°BB>_h%s|/serverpp \"/w \"<r°_ wird den Notruf nun prüfen und entscheiden.", msg, msg, bearbeiter.isEmpty()?client.getName():bearbeiter, bearbeiter.isEmpty()?client.getName():bearbeiter), System.currentTimeMillis()/1000);
                    	Server.get().newMessage(nrs.getName(), beschwerdeführer, "", String.format("Die Bearbeitung des von Ihnen gemeldeten _°BB>_hNotrufs *%s|/admincall info:%s<r°_ wurde soeben an _den Admin °BB>_h%s|/serverpp \"|/w \"<r°_ zur Bearbeitung übergeben.##_°BB>_h%s|/serverpp \"|/w \"<r°_ wird den Notruf nun prüfen und entscheiden.##Sie werden nach Abschluss der Bearbeitung über die Entscheidung informiert werden. Unter dem obigen Notruf-Link können Sie den aktuellen Bearbeitungszustand verfolgen.",msg, msg, bearbeiter.isEmpty()?client.getName():bearbeiter,bearbeiter.isEmpty()?client.getName():bearbeiter), System.currentTimeMillis()/1000);
                		
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
                
            	return;
            }
            
    }
    }
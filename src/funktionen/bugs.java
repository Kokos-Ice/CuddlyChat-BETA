package funktionen;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class bugs {


    public static void functionMake(Client client,Channel channel, String arg) {






	if(!client.hasPermission("cmd.bugs")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
            arg = KCodeParser.escape(arg);
            String[] split = arg.split(":");
            String one = "";
            String two = "";
            
            try {
            	one = split[0].trim();
            	two = split[1].trim();
            } catch(Exception ex) {
            }
            
            /*if(typ.equalsIgnoreCase("mail")) {
            	if(msg.isEmpty() || msg.length() < 20) {
            		client.sendButlerMessage(channel.getName(), "Bitte gib eine detaillierte Beschreibung des Bugs mit mind. 20 Zeichen wieder.");
            		return;
            	}
            	
            	client.sendButlerMessage(channel.getName(), "Deine Beschreibung zu einem Bug wurde _an das Team Bugs weitergeleitet_, welches sich schnellstmöglich darum kümmert. Vielen Dank! °>1.gif<°");
            	
            	PoolConnection pcon = ConnectionPool.getConnection();
                Statement ps = null;

                try {
                    Connection con = pcon.connect();
                    ps = con.createStatement();
                    ResultSet rs = ps.executeQuery("SELECT `name`,`teams` FROM `accounts` WHERE `teams` != ''");
                    
                    while(rs.next()) {
                    	String name = rs.getString("name");
                    	String teams = rs.getString("teams");
                    	
                    	if(teams.contains("|Bugs~")) {
                    		Server.get().newMessage(Server.get().getButler().getName(), name, "Bug gemeldet", String.format("Soeben wurde _ein Bug von °>_h%s|/serverpp \"|/w \"<°_ (%s, %s, %s) _gemeldet_.##_Beschreibung_:#%s##Bitte trage den Fall wenn möglich in unser _°BB>BugTrackingSystem|%s/bts/<r°_ ein.", client.getName().replace("<", "\\<"), client.getGender() == 1 ? "männl.":"weibl.", client.getAge(), client.getRankLabel(client.getRank()), msg, Server.get().getURL()), time);
                    	}
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
            */
            
            if(one.isEmpty()) {
            	return;
            }
            
            if(one.equals("?")) {
            	StringBuilder bugs = new StringBuilder();
            	
            	bugs.append("Bitte die Funktion folgendermaßen benutzen:#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs ? - Diese Hilfe öffnen°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs ticket:ID, /bugs #ID - Ein bestimmtes Ticket aufrufen°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs bts - Die BTS-Startseite aufrufen°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs verify - Tickets anzeigen, die aktuell zu verifizieren sind°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs myBugs - offene, einem selbst zugewiesene Tickets anzeigen (für Entwickler nützlich)°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs find:TEXT, /bugs search:TEXT - Das BTS nach TEXT durchsuchen°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs new - Einen euen Bug eintragen°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs appletversion:NICK - Appletversion eines beliebigen Users anzeigen (muss online sein)°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs experimente:NICK - zeigt die aktiven Experimente bei NICK in einer Liste mit Erklärung an°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs moreInfo - Tickets anzeigen, die aktuell weitere Informationen benötigen°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/fa bugs - Online-Teamler ansehen°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs flags:[NICK] - User-Flags abfragen°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs flags:[NICK]=[+flag1 -flag2 ...] - User-Flags setzen°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs clearMessageHistory - Eigene interne Message History löschen (für bestimmte Tests erforderlich)°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs clearMessageHistory:[NICK] - Interne Message History von NICK löschen (für bestimmte Tests erforderlich)°%00°");
            	bugs.append("##SmileyProject#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs clearcodes - Löscht die eigenen Codes°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs smileyfeatures - Zeige die Übersicht aller Features°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs generateCodes - Listet Befehle zum generieren neuer Codes für alle verfügbaren Serien.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs generateCodes:SERIE - Generiert EINEN unregistrierten Code der SERIE und gibt ihn aus.°%00°#SERIE kann sein: Serien-Name, Teil des Namen (wenn eindeutig), Serien-Id (wie bei \"/bugs generateCodes\").°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs generateCodes:SERIE:ANZAHL - Generiert ANZAHL unregistrierte Codes der SERIE und gibt sie aus.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs receive - Du empfängst sofort EIN Geschenk, das für dich hinterlegt wurde.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs receive:<nick> - <nick> empfängt sofort EIN Geschenk, das für ihn hinterlegt wurde (sogar wenn er offline ist).°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs receiveAll - Du empfängst sofort ALLE Geschenke, die für dich hinterlegt wurden.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs receiveAll:<nick> - <nick> empfängt sofort ALLE Geschenk, die für ihn hinterlegt wurden (sogar wenn er offline ist).°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs receiveChannel - Jeder User im Channel empfängt sofort EIN Geschenk, das für ihn hinterlegt wurde.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs receiveAllChannel - Jeder User im Channel empfängt sofort ALLE Geschenke, die für ihn hinterlegt wurden.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/code <code> - Einen Code aktivieren.°%00°#");
            	//bugs.append("°>cc/bullet_blue_outlined.png<%05°/code serie[:<seriesName>] = /code series = /code serien - Zeigt die Übersicht aller Serien oder sucht nach Serien die <seriesName> enthalten.°%00°#");
            	bugs.append("#Heart#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs settrash - trash heart°%00°#");
            	bugs.append("°>cc/bullet_blue_outlined.png<%05°/bugs renivetrash - trash heart°%00°");
            	
            	client.sendButlerMessage(channel.getName(), bugs.toString());
            	return;
            }
            
            if(one.equalsIgnoreCase("clearMessageHistory")) {
            	Client target = two.isEmpty() ? client : Server.get().getClient(two);
            	boolean online = true;
            	
            	if(target == null) {
            		online = false;
            		target = new Client(null);
            		target.loadStats(two);
            		
            		if(target.getName() == null) {
            			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(two));
            			return;
            		}
            	}
            	
            	if(!online) {
            		client.sendButlerMessage(channel.getName(), CommandParser.userIsOffline(target));
            		return;
            	}
            	
            	client.sendButlerMessage(channel.getName(), String.format("Die interne MessageHistory von %s wurde _gelöscht_.", target.getName()));
            	return;
            }
            
            if(one.equalsIgnoreCase("generateCodes")) {
            	StringBuilder gc = new StringBuilder("Funktionen zum Generieren neuer Codes:#");
            	
            	//gc.append("°>cc/bullet_blue_outlined.png<%05°/bugs generateCodes:SERIE - Generiert EINEN unregistrierten Code der SERIE und gibt ihn aus.°%00°#SERIE kann sein: Serien-Name, Teil des Namen (wenn eindeutig), Serien-Id (wie bei \"/bugs generateCodes\").°%00°#");
            	//gc.append("°>cc/bullet_blue_outlined.png<%05°/bugs generateCodes:SERIE:ANZAHL - Generiert ANZAHL unregistrierte Codes der SERIE und gibt sie aus.°%00°#");
            	gc.append("Noch keine! :D");
            	client.sendButlerMessage(channel.getName(), gc.toString());
            	return;
            }
            
          
            
            if(one.equalsIgnoreCase("clearcodes")) {
                Client target = two.isEmpty() ? client : Server.get().getClient(two);
            	boolean online = true;
                
                
                if(target == null) {
            		online = false;
            		target = new Client(null);
            		target.loadStats(two);
            		
            		if(target.getName() == null) {
            			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(two));
            			return;
            		}
            	
            	}
     
            	client.sendButlerMessage(channel.getName(), "Die Codes wurden _gelöscht_.");
            	target.setSmileys("");
            	target.setCodeE(0);
            	return;
            }
            
            if(one.equalsIgnoreCase("bts")) {
            	client.send(PacketCreator.openURL("http://31.17.109.157/", "_blank"));
            	return;
            }
            
            if(one.equalsIgnoreCase("settrash")) {
        		if(client.hasPermission("feature.trashheart")) {
            		client.sendButlerMessage(channel.getName(), "Du hast dir bereits das Feature _trashheart_ gesetzt.");
            		return;
            	}
            		
            	client.sendButlerMessage(channel.getName(), "Du hast dir das Feature _trashheart_ gesetzt.");
            	String bez = Server.permissions.get("feature.trashheart");
            	Server.permissions.put("feature.trashheart", String.format("%s|N%s|", bez, client.getName()));
            	Server.get().query(String.format("UPDATE `permissions` SET `rights`='%s' WHERE `permission` = 'feature.trashheart'", String.format("%s|N%s|", bez, client.getName())));
            }
            
            if(one.equalsIgnoreCase("renivetrash")) {
            	if(!client.hasPermission("feature.trashheart")) {
                	client.sendButlerMessage(channel.getName(), "Du besitzt das Feature _trashheart_ nicht.");
            		return;
            	}

            	client.sendButlerMessage(channel.getName(), "Du hast dir das Feature _trashheart_ entzogen.");
            	String bez = Server.permissions.get("feature.trashheart");
            	Server.permissions.put("feature.trashheart", bez.replace(String.format("|N%s|", client.getName()), ""));
            	Server.get().query(String.format("UPDATE `permissions` SET `rights`='%s' WHERE `permission` = 'feature.trashheart'", bez.replace(String.format("|N%s|", client.getName()), "")));
            	return;
            }
            
            if(two.isEmpty()) {
            	return;
            }
            
            if(one.equalsIgnoreCase("notfall")) {
            	if(two.isEmpty()) {
            		client.sendButlerMessage(channel.getName(), "Bitte gib den Text der SMS an.");
            		return;
            	}
            	
            	try {
            		String[] handynummern = {"00491734459606", "00491633527236", "004369911087378"};
            	
            		for(String handynummer : handynummern) {
            			String test = URLEncoder.encode(String.format("%s meldet folgenden Vorfall: %s", client.getName(), two), "UTF-8");
            			String site = String.format("http://www.innosend.de/gateway/sms.php?id=EddieDU&pw=bipdebop7505&text=%s&type=4&absender="+Server.get().getSettings("CHAT_NAME")+"&empfaenger=%s", test, handynummer);
            			URL url = new URL(site);
                        URLConnection connection = url.openConnection();
                        InputStream is = connection.getInputStream();  
                        Scanner scanner = new Scanner(is);
                        System.out.println( "SMS: "+scanner.useDelimiter( "\\Z" ).next() );
                        is.close();
                        scanner.close();
            		}
            	
            		client.sendButlerMessage(channel.getName(), String.format("Die SMS wurde an %s Empfänger versendet.", handynummern.length));
            	} catch(Exception ex) {
            		client.sendButlerMessage(channel.getName(), "Die SMS konnten nicht gesendet werden!");
            	}
            	
            	return;
            }
            
            if(one.equalsIgnoreCase("appletversion")) {
            	Client target = Server.get().getClient(two);
            	boolean online = true;
            	
            	if(target == null) {
            		online = false;
            		target = new Client(null);
            		target.loadStats(two);
            		
            		if(target.getName() == null) {
            			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(two));
            			return;
            		}
            	}
            	
            	if(!online) {
            		client.sendButlerMessage(channel.getName(), CommandParser.userIsOffline(target));
            		return;
            	}
            	
            	client.sendButlerMessage(channel.getName(), String.format("%s ist über die _Appletversion %s_ im Chat eingeloggt.", target.getName(), target.getAppletVersion()));
            	return;
            }
            
            if(one.equalsIgnoreCase("experimente")) {
            	Client target = Server.get().getClient(two);
            	
            	if(target == null) {
            		target = new Client(null);
            		target.loadStats(two);
            		
            		if(target.getName() == null) {
            			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(two));
            			return;
            		}
            	}
            	
            	client.sendButlerMessage(channel.getName(), String.format("%s wirkt momentan bei keinen Experimenten mit.", target.getName()));
            	return;
            }
}
}
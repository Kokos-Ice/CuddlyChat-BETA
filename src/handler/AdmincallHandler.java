/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (C) 2011 - 2012 Flav <http://banana-coding.com>
 * 
 * Diese Dateien unterliegen dem Coprytight von Banana-Coding und
 * darf verändert, aber weder in andere Projekte eingefügt noch
 * reproduziert werden.
 * 
 * Der Emulator dient - sofern der Client nicht aus Eigenproduktion
 * stammt - nur zu Lernzwecken, das Hosten des originalen Knuddels Clients
 * ist auf eigene Gefahr und verstößt möglicherweise gegen Schutzrechte
 * der Knuddels.de GmbH & Co KG
 * 
 * Autoren: Flav (Grundversion), Localhost (Erweiterte Version), Kokos-Ice (Erweiterte Version)
 */



package handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.Server;
import tools.PacketCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class AdmincallHandler {

	public static void handle(String[] tokens, Client client) {
		Client nrs = Server.get().getNRS();
		
		if(nrs == null) {
			nrs = new Client(null);
			nrs.loadStats("Notruf-System");
		}

		String a = tokens[1].trim();
		String b = tokens[2].trim();
		String d = "";
		
		try {
			d = tokens[3].trim();
		} catch(Exception ex) {
			
		}
 		
		if(b.equals("?") || b.equals("Abbrechen")) {
			return;
		}
		
		if(d.equals("- Auswählen -")) {
        	client.send(PacketCreator.createAdminCallWindow());
        	return;
		}
		
		if(d.equals("Allgemeine Frage / Problem")) {
			CommandParser.parse("/h index", client, client.getChannel(), false);
			return;
		}

		if(d.equals("Beschwerde über andere Chat-Teilnehmer")) {
			client.send(PacketCreator.createAdmincallWindow("", "", ""));
			return;
		}

		if(a.contains("lala")) {
			String notruf = tokens[1].trim().split("~")[1];
			String typ = tokens[3].trim();
			
			client.send(PopupNewStyle.create("Hinweis", "Hinweis", String.format("Der Notruf wurde als _%s_ bewertet.%s", typ, typ.equals("Notruf-Missbrauch")?"##Der Beschwerdeführer wurde _3 Tage_ für das Notruf-System _gesperrt_.":""), 450, 275));
			Server.get().query(String.format("UPDATE `admincalls` SET `bewertet` = '%s', ergebnis = '%s' WHERE `id` = '%s'", System.currentTimeMillis()/1000, typ, notruf));

			
			PoolConnection pcon = ConnectionPool.getConnection();
            Statement ps = null;

            try {
                Connection con = pcon.connect();
                ps = con.createStatement();
                ResultSet rs = ps.executeQuery(String.format("SELECT `id`, `bearbeiter`, `beschwerdeführer`, `beschuldigter` FROM `admincalls` WHERE `id` = '%s'", notruf));
                
                if(rs.next()) {
                	String beschuldigter = rs.getString("beschuldigter");
                	String bearbeiter = rs.getString("bearbeiter");
                	String beschwerdeführer = rs.getString("beschwerdeführer");
                	
                	Client t = Server.get().getClient(beschwerdeführer);
                	boolean online = true;
                	if(t == null) {
                		online = false;
                		t = new Client(null);
                		t.loadStats(beschwerdeführer);
                	}
                	
                	Server.get().newMessage(nrs.getName(), beschwerdeführer, "", String.format("Der _°BB>_hNotruf *%s|/admincall info:%s<r°_ wurde gerade durch _den Admin °BB>_h%s|/serverpp \"|/w \"<r°_ entschieden.##Sie waren in diesem Fall der _Beschwerdeführer_.##Die getroffene Entscheidung lautet:##_Beschuldigter:_#%s: _Vorwürfe %szutreffend_##_Beschwerdeführer:_#%s: _%s_##Weitere Maßnahmen im Sinne dieser Entscheidung werden gegebenfalls durch _°BB>_h%s|/serverpp \"|/w \"<r°_ getroffen werden.", notruf, notruf, bearbeiter,beschuldigter, typ.equals("Notruf berechtigt")?"":"un", beschwerdeführer, typ.equals("Notruf berechtigt")?"Notruf berechtigt":typ.equals("Notruf unberechtigt")?"Notruf unberechtigt":"Notruf unberechtigt (Missbrauch)", bearbeiter), System.currentTimeMillis()/1000);
                	Server.get().newMessage(nrs.getName(), beschuldigter, "", String.format("Der _°BB>_hNotruf *%s|/admincall info:%s<r°_ wurde gerade durch _den Admin °BB>_h%s|/serverpp \"|/w \"<r°_ entschieden.##Sie waren in diesem Fall der _Beschuldigte_.##Es wurde entschieden, dass die durch den Beschwerdeführer geäußerten _Vorwürfe gegen Sie %sgerechtfertigt_ waren. Information betreffend das weitere diesbezügliche Vorgehen gegen Sie haben oder werden Sie noch von _°BB>_h%s|/serverpp \"|/w \"<r°_ erhalten.", notruf, notruf, bearbeiter, typ.equals("Notruf berechtigt") ? "": "un", bearbeiter), System.currentTimeMillis()/1000);

        			long time = (System.currentTimeMillis()/1000)+259200;
        			
                	if(!typ.equals("Notruf-Missbrauch")) {
                		if(online) {
                			t.increaseAdmincallSecond();
                		} else {
                			Server.get().query(String.format("update accounts set admincallSecond=admincallSecond+'1' where name='%s'", t.getName()));
                		}
                	} else {
                    	if(online) {
                    		t.increaseAdmincallSecond();
                    		t.setNotrufsperre(time);
                    	} else {
                    		Server.get().query(String.format("update accounts set notrufsperre='%s', admincallThird=admincallThird+'1' where name='%s'", time, t.getName()));
                    	}
                    	
                    	Server.get().newMessage(Server.get().getButler().getName(), t.getName(), "Notruf-System gesperrt", String.format("Du wurdest für das Notrufsystem bis zum %s gesperrt, da du das _Notrufsystem missbraucht_ hast.##Das Notrufsystem ist ausschließlich für Notfälle gedacht. Durch Missbrauch des Notrufsystems können wichtige Notrufe nicht mehr schnell genug bearbeitet werden.#Beachte, dass Du bei erneutem Missbrauch für die verursachten Kosten in Anspruch genommen werden kannst.", Server.get().timeStampToDate(time)), System.currentTimeMillis()/1000);
                    	t.setComment(time, null, "Notrufsperre!", client.getName(), String.format("Automatische Notrufsperre (°R°_3 Tage°B°_) durch °>_h*%s|/admincall info:%s<°", notruf, notruf));
                    	
                    	if(typ.equals("Notruf berechtigt")) {
                    		String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(time), Server.get().timeStampToTime(time));
                    		
                    		if(online) {
                    			t.admincalls.add(new String[] {typ, notruf, uhrzeit});
                    		}
                    	}
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
		
		int admincallID = Integer.parseInt(tokens[1].trim());
		String beschuldigter = tokens[3].trim();
		String verstoß = tokens[4].trim();
		String begründung = tokens[5].trim();
		
		if(client.getNotrufsperre() != 0) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Sie sind momentan für das Notrufsystem _gesperrt_.", 450, 275));
			return;	
		}
		
		if(client.getRank() > 4) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Du kannst als Admin keine Notrufe absetzen.", 450, 275));
			return;	
		}
		
		if(beschuldigter.isEmpty()) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Bitte geben Sie unter _Beschuldigter_ den Nicknamen an, über den Sie sich beschweren möchten.", 450, 275));
			return;	
		}
		
		if(begründung.isEmpty()) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem","Bitte _geben Sie eine Begründung an_. Die Begründung sollte ausführlich sein und alle, zur Beurteilung ihrer Beschwerde notwendigen, Hintergrundinformationen enthalten.", 450, 275));
			return;
		}
		
		Client target = Server.get().getClient(beschuldigter);
		boolean online = true;
		
		if(target == null) {
			online = false;
			target = new Client(null);
			target.loadStats(beschuldigter);
			
			if(target.getName() == null) {
				client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
				client.send(PopupNewStyle.create("Problem", "Problem", String.format("Der Nick %s existiert nicht!", beschuldigter), 450, 275));
				return;
			}
		}
		
		if(begründung.length() < 50) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", String.format("Ihre _Begründung war zu kurz_ und zu unpräzise. Bitte begründen Sie Ihre Anschuldigung gegenüber %s ausführlicher und verständlicher.", target.getName()), 450, 275));
			return;
		}
		
		if(begründung.length() > 1000) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Deine Begründung ist _zu lang_.", 450, 275));
		}
		
		if(target == client) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Wenn Sie sich über sich selbst beschweren möchten, wenden Sie sich bitte an Ihre Eltern!", 450, 275));
			return;
		}
		
		if(target == Server.get().getButler() || target.getRank() >= client.getRank() && target.getRank() > 6) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", String.format("Sie können _über den Nick %s keine Notrufe_ absetzen, weil es sich um einen System-Nick handelt.", target.getName()), 450, 275));
			return;
		}
		
		if(verstoß.equals("- Auswählen -")) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Klicken Sie auf die untere Menüliste und _wählen Sie Typ des Verstoßes aus_.", 450, 275));
			return;
		}
		
		int lala = 0;

        for (Object x : Server.get().getClients().toArray()) {
       	 	Client c = ((Client) x);
       	 	
       	 	if(c != Server.get().getButler() && c != target) {
       	 		if(c.getRank() > 4) {
       	 			lala++;
       	 		}
       	 	}
        }
		
		if(lala < 1) {
			client.send(PacketCreator.createAdmincallWindow(beschuldigter, verstoß, begründung));
			client.send(PopupNewStyle.create("Problem", "Problem", "Momentan ist kein Admin online der dein Notruf entgegennehmen kann. Bitte probiere es später erneut.", 450, 275));
			return;
		}
		
		client.send(PopupNewStyle.create("Notruf", "Notruf gespeichert", "Dein Notruf wurde gespeichert und wird schnellstmöglich von einem anwesenden Admin bearbeitet.", 450, 275));
		
		
		String nrstext = String.format("#°RR°_-----   ACHTUNG -----_°r°##Es ist eine _Beschwerde (%s) über Sie_ eingegangen. Bitte _°BB>klicken Sie jetzt unbedingt hier|/admincall info:%s<r°_, um die Vorwürfe anzuschauen und den Vorwurf einzugestehen oder Widerspruch zu erheben.##°RR°_-----   ACHTUNG -----_°r°", verstoß, admincallID);
		
		Server.get().newMessage(nrs.getName(), target.getName(), "Beschwerde über Sie!", nrstext, System.currentTimeMillis()/1000);
		if(online) {
			for(Channel i : target.getChannels()) {
				target.sendButlerMessage(i.getName(), nrstext);
			}
		}


        for (Object x : Server.get().getClients().toArray()) {
       	 	Client c = ((Client) x);
       	 	
       	 	if(c != Server.get().getButler() && c != target) {
       	 		if(c.getRank() > 4) {
       	 			for(Channel p : c.getChannels()) {
       	 				c.sendButlerMessage(p.getName(), new StringBuilder().append("Deine °>sounds/ding.mp<°Hilfe ist gefragt, antworte schnell!°#%-1°#_°BB>Notruf annehmen|/admincall ok:").append(admincallID).append("<°").toString());
       	 			}
       	 		}
       	 	}
        }
		
		Server.get().query(String.format("INSERT INTO `admincalls` SET `id` = '%s', `verstoss` = '%s', `beschuldigter` = '%s', `begründung` = '%s', `beschwerdeführer` = '%s', `abgesetzt` = '%s', `channel` = '%s', `bearbeiter` = '', `ergebnis` = ''", admincallID, verstoß, beschuldigter, begründung.replace("'", "\'"), client.getName(), System.currentTimeMillis()/1000, client.getChannel().getName()));
		client.increaseAdmincallFirst();
	}
}
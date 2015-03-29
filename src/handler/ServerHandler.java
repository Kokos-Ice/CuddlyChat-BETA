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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import starlight.Client;
import starlight.Server;
import starlight.Settings;
import tools.PacketCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class ServerHandler {

	public static void handle(String[] tokens, Client client) {
		String module = tokens[1].trim();
		boolean edit = false;
		String more = "";
		
		if(module.startsWith("edit")) {
			module = module.substring(4);
			String[] xy = module.split("~~~");
			module = xy[1];
			more = xy[0];
			edit = true;
		}
            
             
		
		String title = String.format("Server - %s", module);

		if(module.equalsIgnoreCase("wahlen")) {
			String wahlname = tokens[3].trim().replace("'", "\\'");
			String aktiv = tokens[4].trim();
			String nomiPhase = tokens[5].trim().replace("'", "\\'");
			String wahlPhase = tokens[6].trim().replace("'", "\\'");
			String endPhase = tokens[7].trim().replace("'", "\\'");
			byte aktivByte = 0;
			
			if(aktiv.equals("Aktiviert")) {
				aktivByte = 1;
			}
			
			if(wahlname.isEmpty() || nomiPhase.isEmpty() || wahlPhase.isEmpty() || endPhase.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { wahlname, aktiv, nomiPhase, wahlPhase, endPhase }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib alle benötigten Informationen an.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(!edit && Server.elections.containsKey(wahlname)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { wahlname, aktiv, nomiPhase, wahlPhase, endPhase }));
				PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Die Wahl %s existiert bereits.", wahlname), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                return;
			}
			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Die Wahl %s wurde geändet.", more), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
                                
				String[] x  = Server.elections.get(more);
                                Server.elections.remove(more);
				Server.elections.put(wahlname, new String[] {String.valueOf(aktivByte), nomiPhase, wahlPhase, endPhase," ",x[5]," "} );
				Server.get().query(String.format("UPDATE `wahlen` SET `name` = '%s', `aktiv` = '%s', `nomiPhase` = '%s',`wahlPhase`='%s',`nominated` = ' ', `suglist` = ' ', `endPhase` = '%s' WHERE `name` = '%s'", wahlname, aktivByte, nomiPhase, wahlPhase, endPhase, more));
			  Server.get().query("delete from `wahlenvoted` where wahl='"+wahlname+"'");
                        } else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Die Wahl %s wurde hinzugefügt.", wahlname), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
				Server.elections.put(wahlname, new String[] {String.valueOf(aktivByte), nomiPhase, wahlPhase, endPhase," "," "," "} );
				Server.get().query(String.format("INSERT INTO `wahlen` SET `name` = '%s', `aktiv` = '%s',`wahl` = ' ', `nomiPhase` = '%s',`wahlPhase`='%s', `suglist` = ' ', `nominated` = ' ', `endPhase` = '%s'", wahlname, aktivByte, nomiPhase, wahlPhase, endPhase));
			  Server.get().query("delete from `wahlenvoted` where wahl='"+wahlname+"'");
                        }
		}
		
		if(module.equalsIgnoreCase("toplisten")) {
			String name = tokens[3].trim().replace("'", "\\'");
			String beschreibung = tokens[4].trim().replace("'", "\\'");
			String datenbank = tokens[5].trim().replace("'", "\\'");
			
			if(name.isEmpty() || beschreibung.isEmpty() || datenbank.isEmpty()) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib alle benötigten Informationen an.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(!edit && Server.toplisten.containsKey(name)) {
				PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Die Topliste %s existiert bereits.", name), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                return;
			}
			
			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Die Topliste %s wurde geändert.", name), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
				Server.toplisten.remove(more);
				Server.toplisten.put(name, new String[] { datenbank, beschreibung});
				Server.get().query(String.format("UPDATE `toplisten` SET `name` = '%s', `text` = '%s', `word` = '%s' WHERE `name` = '%s'", datenbank, beschreibung, name, more));
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Die Topliste %s wurde hinzugefügt.", name), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
                                
                                
				Server.toplisten.put(name, new String[] { datenbank, beschreibung});
				Server.get().query(String.format("INSERT INTO `toplisten` SET `name` = '%s', `text` = '%s', `word` = '%s'", datenbank, beschreibung, name));
			}
		} else if(module.equalsIgnoreCase("wordmix")) {
			String sentence = tokens[3].trim().replace("'", "\\'");
			String[] split = sentence.split(" ");
			
			if(sentence.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib einen Satz ein.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());      
				return;
			}
			
			if(split.length < 3 || split.length > 10) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence }));
				PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte achte auf die Länge der Wörter: Mindestens 3 und maximal 10 Wörter.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());      
				return;
			}
			
			if(!edit && Server.wordmix.contains(sentence)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Der Satz ist bereits vorhanden.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());   
				return;
			}
			
			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Der Satz wurde geändert.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
				Server.wordmix.remove(more);
				Server.wordmix.add(sentence);
				Server.get().query(String.format("UPDATE `wordmix` SET `SENTENCE` = '%s' WHERE `sentence` = '%s'", sentence, more));
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Der Satz wurde hinzugefügt.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
                                
				Server.wordmix.add(sentence);
				Server.get().query(String.format("INSERT INTO `wordmix` SET `SENTENCE` = '%s'", sentence.replace("'", "\\'")));
			}
            
                        
                        } else if(module.equalsIgnoreCase("mix")) {
			String sentence = tokens[3].trim().replace("'", "\\'");
			String[] split = sentence.split(" ");
			
			if(sentence.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib ein Wort ein.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());       
                                return;
			}
			
			
			
			if(!edit && Server.mix.contains(sentence)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence }));
				PopupNewStyle popup = new PopupNewStyle(title, title, "##Dieses Wort ist bereits vorhanden.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());       
                                return;
			}
			
			if(edit) {
				client.send(PopupNewStyle.create(title, title, "Das Wort wurde geändert.", 450, 275));
				Server.mix.remove(more);
				Server.mix.add(sentence);
				Server.get().query(String.format("UPDATE `game_mix` SET `SENTENCE` = '%s' WHERE `sentence` = '%s'", sentence, more));
			} else {
				client.send(PopupNewStyle.create(title, title, "Das Wort wurde hinzugefügt.", 450, 275));
				Server.mix.add(sentence);
				Server.get().query(String.format("INSERT INTO `game_mix` SET `SENTENCE` = '%s'", sentence.replace("'", "\\'")));
			}
                        
                        
                        
		} else if(module.equalsIgnoreCase("hilfe")) {
			String word = tokens[3].trim().replace("'", "\\'").toLowerCase();
			String header = tokens[4].trim().replace("'", "\\'");
			String text = tokens[5].trim().replace("'", "\\'");
			
			if(word.isEmpty() || header.isEmpty() || text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { word, header, text }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib alle benötigten Informationen an.", 450, 275));
				return;
			}
			
			if(!edit && Server.help.containsKey(word)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { word, header, text }));
				client.send(PopupNewStyle.create(title, title, "Das Wort ist bereits vorhanden.", 450, 275));
				return;
			}
			
			if(!edit) {
				client.send(PopupNewStyle.create(title, title, "Das Wort wurde hinzugefügt.", 450, 275));
				
				Server.help.put(word, new String[]{header, text});
				Server.get().query(String.format("INSERT INTO `help` SET `word` = '%s', `title` = '%s', `text` = '%s'",word.replace("'", "\\'"), header.replace("'", "\\'"), text.replace("'", "\\'")));
			} else {
				client.send(PopupNewStyle.create(title, title, "Das Wort wurde geändert.", 450, 275));
				
				Server.help.remove(more);
				Server.get().query(String.format("UPDATE `help` SET `word` = '%s', `title` = '%s', `text` = '%s' WHERE `word` = '%s'",word.replace("'", "\\'"), title.replace("'", "\\'"), text.replace("'", "\\'"), more.replace("'", "\\'")));
				Server.help.put(word, new String[]{header, text});
			}
                        
                        
                        
                        
                        
                        } else if(module.equalsIgnoreCase("syslog")) {
			String time = tokens[3].trim().replace("'", "\\'").toLowerCase();
			String name = tokens[4].trim().replace("'", "\\'");
			String text = tokens[5].trim().replace("'", "\\'");
			
			if(time.isEmpty() || name.isEmpty() || text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { time, name, text }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib alle benötigten Informationen an.", 450, 275));
				return;
			}
			
			if(!edit && Server.syslog.containsKey(time)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { time, name, text }));
				client.send(PopupNewStyle.create(title, title, "Das Wort ist bereits vorhanden.", 450, 275));
				return;
			}
			
			if(!edit) {
				client.send(PopupNewStyle.create(title, title, "Das Wort wurde hinzugefügt.", 450, 275));
				
				Server.syslog.put(time, new String[]{name, text});
				Server.get().query(String.format("INSERT INTO `syslog` SET `time` = '%s', `name` = '%s', `text` = '%s'",time.replace("'", "\\'"), name.replace("'", "\\'"), text.replace("'", "\\'")));
			} else {
				client.send(PopupNewStyle.create(title, title, "Das Wort wurde geändert.", 450, 275));
				
				Server.syslog.remove(more);
				Server.get().query(String.format("UPDATE `syslog` SET `time` = '%s', `name` = '%s', `text` = '%s' WHERE `time` = '%s'",time.replace("'", "\\'"), title.replace("'", "\\'"), text.replace("'", "\\'"), more.replace("'", "\\'")));
				Server.syslog.put(time, new String[]{name, text});
			}
                        
                        
                        
                          } else if(module.equalsIgnoreCase("wahlenvoted")) {
			String time = tokens[3].trim().replace("'", "\\'").toLowerCase();
			String name = tokens[4].trim().replace("'", "\\'");
			String text = tokens[5].trim().replace("'", "\\'");
			
			if(time.isEmpty() || name.isEmpty() || text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { time, name, text }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib alle benötigten Informationen an.", 450, 275));
				return;
			}
			
			
                        
		} else if(module.equalsIgnoreCase("todo")) {
			String text = tokens[3].trim().replace("'", "\\'");
			
			if(text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] {  text }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib den Text an.", 450, 275));
				return;
			}
			
			if(!edit && Server.todo.containsKey(text)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { text }));
				client.send(PopupNewStyle.create(title, title, "Der Text ist bereits vorhanden.", 450, 275));
				return;
			}
			
			if(!edit) {
				client.send(PopupNewStyle.create(title, title, "Der Text wurde hinzugefügt.", 450, 275));
				
				Server.todo.remove(more);
				Server.todo.put(text, client.getName());
				Server.get().query(String.format("INSERT INTO `todo` SET `text` = '%s', `von` = '%s'",text, client.getName()));
			} else {
				client.send(PopupNewStyle.create(title, title, "Der Text wurde geändert.", 450, 275));

				String von = Server.todo.get(more);
				Server.get().query(String.format("UPDATE `todo` SET `text` = '%s', `von` = '%s' WHERE `text` = '%s'",text, von,more));
				Server.todo.put(text, von);
			}
		} else if(module.equalsIgnoreCase("teams")) {
			String team = tokens[3].trim().replace("'", "\\'");
			
			if(team.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { team }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib ein Teamname an.", 450, 275));
				return;
			}
			
			if(!edit) {
				client.send(PopupNewStyle.create(title, title, "Das Team wurde hinzugefügt.", 450, 275));
				
				Settings.addTeam(team);
			} else {
				client.send(PopupNewStyle.create(title, title, "Das Team wurde geändert.", 450, 275));
				
				String newTeams = String.format("%s|%s|", Settings.teams.replace(String.format("|%s|", more), ""), team);
				Settings.teams = newTeams;
				Server.get().query(String.format("UPDATE `settings` SET `teams` = '%s'",newTeams));
			}
		} else if(module.equalsIgnoreCase("werbung")) {
			String text = tokens[3].trim();
		
                    
			if(text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { more,text }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib ein Werbetext an.", 450, 275));
				return;
			}

			if(!edit && Server.werbung.containsValue(text)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { more,text }));
				client.send(PopupNewStyle.create(title, title, "Der Werbetext existiert bereits", 450, 275));
				return;
			}
			
			if(!edit) {
				client.send(PopupNewStyle.create(title, title, "Der Werbetext wurde hinzugefügt.", 450, 275));
				
				
		Server.get().query(String.format("INSERT INTO `werbung` SET `text` = '%s'", text));
                                
                         int id = 0;       
         PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        try {
           Connection con = pcon.connect();
           ps = con.prepareStatement("SELECT * FROM `werbung` where text = ?");
          ps.setString(1, text);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
           
           id = rs.getInt("id");

          }
        } catch (SQLException e) {  e.printStackTrace();   } finally { if (ps != null)    try { ps.close(); }  catch (SQLException e)  {  }  pcon.close();    }
Server.werbung.put(id,text);
                                
			} else {
				client.send(PopupNewStyle.create(title, title, "Der Werbetext wurde geändert.", 450, 275));
				
				Server.werbung.remove(Integer.parseInt(more));
				Server.werbung.put(Integer.parseInt(more),text);
				Server.get().query(String.format("UPDATE `werbung` SET `text` = '%s' WHERE `id` = '%s'",text, more));
		
                        }
                        
                        
                        } else if(module.equalsIgnoreCase("butlertipps")) {
			String text = tokens[3].trim();
		
                    
			if(text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { more,text }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib ein Werbetext an.", 450, 275));
				return;
			}

			if(!edit && Server.butlertips.contains(text)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { more,text }));
				client.send(PopupNewStyle.create(title, title, "Der Butler-Tipp existiert bereits", 450, 275));
				return;
			}
			
			if(!edit) {
				client.send(PopupNewStyle.create(title, title, "Der Butler-Tipp wurde hinzugefügt.", 450, 275));
				
				
		Server.get().query(String.format("INSERT INTO `butler_tipps` SET `text` = '%s'", text));
                                
                         int id = 0;       
         PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        try {
           Connection con = pcon.connect();
           ps = con.prepareStatement("SELECT * FROM `butler_tipps` where text = ?");
          ps.setString(1, text);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
           
           id = rs.getInt("id");

          }
        } catch (SQLException e) {  e.printStackTrace();   } finally { if (ps != null)    try { ps.close(); }  catch (SQLException e)  {  }  pcon.close();    }
Server.butlertips.add(text);
                                
			} else {
				client.send(PopupNewStyle.create(title, title, "Der Werbetext wurde geändert.", 450, 275));
				
				Server.butlertips.remove(text);
				Server.butlertips.add(text);
				Server.get().query(String.format("UPDATE `butler_tipps` SET `text` = '%s' WHERE `id` = '%s'",text, more));
		
                        }
                        
                        
                        
		} else if(module.equalsIgnoreCase("funktionen")) {
			String function = tokens[3].trim().replace("'", "\\'");
			String description = tokens[4].trim().replace("'", "\\'");
			String rankLabel = tokens[5].trim().replace("'", "\\'");
			byte rank = 0;
			
			if(rankLabel.equals("Familymitglied")) {
				rank = 1;
			} else if(rankLabel.equals("Stammi")) {
				rank = 2;
			} else if(rankLabel.equals("Ehrenmitglied")) {
				rank = 3;
			} else if(rankLabel.equals("Ehrenmitglied")) {
				rank = 4;
			} else if(rankLabel.equals("inoffizieller Admin")) {
				rank = 5;
			} else if(rankLabel.equals("Admin Status 6")) {
				rank = 6;
			} else if(rankLabel.equals("Admin Status 7")) {
				rank = 7;
			} else if(rankLabel.equals("inoffizieller Sysadmin")) {
				rank = 8;
			} else if(rankLabel.equals("Sysadmin Status 9")) {
				rank = 9;
			} else if(rankLabel.equals("Sysadmin Status 10")) {
				rank = 10;
			} else if(rankLabel.equals("Sysadmin Status 11")) {
				rank = 11;
			}
			
			if(function.isEmpty() || description.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { function, description, rankLabel }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib alle benötigten Informationen an.", 450, 275));
				return;
			}
			
			if(!edit && Server.functions.containsKey(function)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { function,description,rankLabel }));
				client.send(PopupNewStyle.create(title, title, "Es gibt bereits eine Beschreibung zu der Funktion.", 450, 275));
				return;
			}
			
			if(edit) {
				client.send(PopupNewStyle.create(title, title, "Die Funktion wurde geändert.", 450, 275));
				
				String[] fm = new String[] {String.valueOf(rank), description};
				Server.functions.put(function, fm);
				Server.get().query(String.format("UPDATE `functions` SET `description` = '%s', `rank` = '%s', `function` = '%s' WHERE `function` = '%s'", description.replace("'", "\\'"), rank, function.replace("'", "\\'"), more));
			} else {
				client.send(PopupNewStyle.create(title, title, "Die Funktion wurde hinzugefügt.", 450, 275));
			
				String[] fm = new String[] {String.valueOf(rank), description};
				Server.functions.put(function, fm);
				Server.get().query(String.format("INSERT INTO `functions` SET `description` = '%s', `rank` = '%s', `function` = '%s'", description.replace("'", "\\'"), rank, function.replace("'", "\\'")));
			}
		} else if(module.equalsIgnoreCase("rechte")) {
			String recht = tokens[3].trim().replace("'", "\\'");
			String bezeichnungen = tokens[4].trim().replace("'", "\\'");
			
			if(recht.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { recht, bezeichnungen }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib den Name des neuen Rechts an.", 450, 275));
				return;
			}
			
			if(!edit && Server.permissions.containsKey(recht)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { recht, bezeichnungen }));
				client.send(PopupNewStyle.create(title, title, "Das Recht ist bereits vorhanden.", 450, 275));
				return;
			}
			
			if(edit) {
				client.send(PopupNewStyle.create(title, title, "Das Recht wurde geändert.", 450, 275));
				Server.permissions.remove(more);
				Server.permissions.put(recht, bezeichnungen);
				Server.get().query(String.format("UPDATE `permissions` SET `permission` = '%s', `rights` = '%s' WHERE `permission`= '%s'", recht, bezeichnungen, more));
			} else {
				client.send(PopupNewStyle.create(title, title, "Das Recht wurde hinzugefügt.", 450, 275));
				Server.permissions.put(recht, bezeichnungen);
				Server.get().query(String.format("INSERT INTO `permissions` SET `permission` = '%s', `rights` = '%s'", recht.replace("'", "\\'"), bezeichnungen.replace("'", "\\'")));
			}
		}else if(module.equalsIgnoreCase("mathe")) {
			String sentence = tokens[3].trim().replace("'", "\\'");
			String loesung = tokens[4].trim().replace("'", "\\'");
			
			if(sentence.isEmpty() || loesung.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence,loesung }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib den Name der neuen Umfrage an.", 450, 275));
				return;
			}
			
			if(!edit && Server.mathe.containsKey(sentence)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence,loesung }));
				client.send(PopupNewStyle.create(title, title, "Die Frage ist bereits vorhanden.", 450, 275));
				return;
			}
			
			if(edit) {
				client.send(PopupNewStyle.create(title, title, "Die Umfrage wurde geändert.", 450, 275));
				Server.mathe.remove(more);
				Server.mathe.put(sentence,loesung);
				Server.get().query(String.format("UPDATE `mathe` SET `sentence` = '%s', `loesung` = '%s' WHERE `sentence`= '%s'", sentence,loesung, more));
			} else {
				client.send(PopupNewStyle.create(title, title, "Die Frage wurde hinzugefügt.", 450, 275));
				Server.mathe.put(sentence,loesung);
				Server.get().query(String.format("INSERT INTO `mathe` SET `sentence` = '%s', `loesung` = '%s'", sentence,loesung));
			}
                        }else if(module.equalsIgnoreCase("translate")) {
			String sentence = tokens[3].trim().replace("'", "\\'");
			String loesung = tokens[4].trim().replace("'", "\\'");
			
			if(sentence.isEmpty() || loesung.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence,loesung }));
				client.send(PopupNewStyle.create(title, title, "Bitte gib den Name der neuen Umfrage an.", 450, 275));
				return;
			}
			
			if(!edit && Server.translate.containsKey(sentence)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence,loesung }));
				client.send(PopupNewStyle.create(title, title, "Die Frage ist bereits vorhanden.", 450, 275));
				return;
			}
			
			if(edit) {
				client.send(PopupNewStyle.create(title, title, "Die Umfrage wurde geändert.", 450, 275));
				Server.translate.remove(more);
				Server.translate.put(sentence,loesung);
				Server.get().query(String.format("UPDATE `translate` SET `sentence` = '%s', `loesung` = '%s' WHERE `sentence`= '%s'", sentence,loesung, more));
			} else {
				client.send(PopupNewStyle.create(title, title, "Die Frage wurde hinzugefügt.", 450, 275));
				Server.translate.put(sentence,loesung);
				Server.get().query(String.format("INSERT INTO `translate` SET `sentence` = '%s', `loesung` = '%s'", sentence,loesung));
			}
                        }else if(module.equalsIgnoreCase("quiz")) {
			String sentence = tokens[3].trim().replace("'", "\\'");
			String loesung = tokens[4].trim().replace("'", "\\'");
			
			if(sentence.isEmpty() || loesung.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence,loesung }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib den Name der neuen Umfrage an.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(!edit && Server.quiz.containsKey(sentence)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { sentence,loesung }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Die Frage ist bereits vorhanden.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Die Umfrage wurde geändert.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
				Server.quiz.remove(more);
				Server.quiz.put(sentence,loesung);
				Server.get().query(String.format("UPDATE `quiz` SET `sentence` = '%s', `loesung` = '%s' WHERE `sentence`= '%s'", sentence,loesung, more));
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Die Frage wurde hinzugefügt.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
                                
				Server.quiz.put(sentence,loesung);
				Server.get().query(String.format("INSERT INTO `quiz` SET `sentence` = '%s', `loesung` = '%s'", sentence,loesung));
			}
                        }else if(module.equalsIgnoreCase("umfragen")) {
			String frage = tokens[3].trim().replace("'", "\\'");
			String antworten = tokens[4].trim().replace("'", "\\'");
			
			if(frage.isEmpty() || antworten.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { frage,antworten }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib den Name der neuen Umfrage an.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(!edit && Server.umfragen.containsKey(frage)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { frage,antworten }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Die Umfrage ist bereits vorhanden.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Die Umfrage wurde geändert.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
                                
                                
				Server.umfragen.remove(more);
				Server.umfragen.put(frage,antworten);
				Server.get().query(String.format("UPDATE `umfragen` SET `frage` = '%s', `antworten` = '%s' WHERE `frage`= '%s'", frage,antworten, more));
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Die Umfrage wurde hinzugefügt.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
                                
				Server.umfragen.put(frage,antworten);
				Server.get().query(String.format("INSERT INTO `umfragen` SET `frage` = '%s', `antworten` = '%s'", frage,antworten));
			}
		} else if(module.equalsIgnoreCase("badwords")) {
			String badword = tokens[3].trim().replace("'", "\\'");
			String jugendschutz = tokens[4].trim().replace("'", "\\'");
			int juschu = 0;
			
			if(jugendschutz.equals("Ja")) {
				juschu = 1;
			}
			
			if(badword.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { badword, jugendschutz }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib ein Badword ein.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(!edit && Server.badwords.containsKey(badword)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { badword, jugendschutz }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Badword ist bereits vorhanden.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das BadWord wurde geändert.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
				Server.badwords.remove(more);
				Server.get().query(String.format("UPDATE `badword` SET `text` = '%s', `juschu` = '%s' WHERE `text` = '%s'",badword.replace("'", "\\'"), juschu, more.replace("'", "\\'")));
				Server.badwords.put(badword, juschu);
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Badword wurde hinzugefügt.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
				Server.badwords.put(badword, juschu);
				Server.get().query(String.format("INSERT INTO `badword` SET `text` = '%s', `juschu` = '%s'", badword.replace("'", "\\'"), juschu));
			}
		} else if(module.equalsIgnoreCase("butler")) {
			String satz = tokens[3].trim().toLowerCase().replace("'", "\\'");
			String antwort = tokens[4].trim().replace("'", "\\'");
			
			if(satz.isEmpty() || antwort.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { satz, antwort }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib den Satz und die Antwort ein.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}

			if(!edit && Server.james.containsKey(satz)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { satz,antwort }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Wort existiert bereits.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}

			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Wort wurde geändert.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
				Server.james.remove(more);
				Server.get().query(String.format("UPDATE `butler` SET `word` = '%s', `text` = '%s' WHERE `word` = '%s'", satz,antwort, more));
				Server.james.put(satz,antwort);
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Wort wurde hinzugefügt.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
				Server.james.put(satz,antwort);
				Server.get().query(String.format("INSERT INTO `butler` SET `word` = '%s', `text` = '%s'", satz,antwort));
			}
		
		} else if(module.equalsIgnoreCase("makros")) {
			String makro = tokens[3].trim().toLowerCase().replace("'", "\\'");
			String text = tokens[4].trim().replace("'", "\\'");

			if(makro.isEmpty() || text.isEmpty()) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { makro, text }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Bitte gib das Makro und den Text ein.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}
			
			if(!edit && Server.macros.containsKey(makro)) {
				client.send(PacketCreator.createServerPopup(edit, module, new String[] { makro, text }));
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Makro existiert bereits.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				return;
			}

			if(edit) {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Makro wurde geändert.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                                
                                
				Server.macros.remove(more);
				Server.get().query(String.format("UPDATE `macros` SET `macro` = '%s', `texts` = '%s' WHERE `macro` = '%s'", makro, text, more));
				Server.macros.put(makro, text);
			} else {
                                PopupNewStyle popup = new PopupNewStyle(title, title, "##Das Makro wurde hinzugefügt.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
				
                                
                                
                                
				Server.macros.put(makro, text);
				Server.get().query(String.format("INSERT INTO `macros` SET `texts` = '%s', `macro` = '%s'", text, makro));
			}
		}
	}
}
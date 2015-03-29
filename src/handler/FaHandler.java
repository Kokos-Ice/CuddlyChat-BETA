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
import starlight.ReceiveOpcode;
import starlight.Server;
import starlight.Settings;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Choice;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;
import tools.popup.TextArea;
import tools.popup.TextField;

public class FaHandler {

	
	public static void handle(String[] tokens, Client client) {
    	String teamRight = tokens[1].trim();
    	String button = tokens[2].trim();
    	String drei = "";
    	String vier = "";
    	
    	try {
    		drei = tokens[3].trim();
    		vier = tokens[4].trim();
    	}catch(Exception ex) {
    		
    	}
    	
    	if(button.equals("Mitglieder")) {
            Popup popup = new Popup(String.format("Mitglieder Team %s bearbeiten", teamRight), String.format("Mitglieder Team %s bearbeiten", teamRight), "", 0, 0);
    	    Panel panel7 = new Panel();
            panel7.addComponent(new Label("Nickname:"));
            TextField a = new TextField(15);
            a.setText(drei);
            panel7.addComponent(a);
            popup.addPanel(panel7);
            Panel panel8 = new Panel();
            panel8.addComponent(new Label("Rang:"));
            Choice b = new Choice(new String[] {"Mitglied", "Teamleiter", "Teamnick"}, vier);
            panel8.addComponent(b);
            popup.addPanel(panel8);
            Panel panel2 = new Panel();
            Button button2 = new Button("Hinzufügen");
            button2.enableAction();
            button2.setStyled(true);
            panel2.addComponent(button2);
            Button button3 = new Button("Entfernen");
            button3.enableAction();
            button3.setStyled(true);
            panel2.addComponent(button3);
            Button button4 = new Button("Abbrechen");
            button4.setStyled(true);
            panel2.addComponent(button4);
            popup.addPanel(panel2);
            popup.setOpcode(ReceiveOpcode.FA.getValue(), teamRight);
            client.send(popup.toString());
    	} else if(button.equals("RundMail")) {
    		Popup popup = new Popup(String.format("RundMail Team %s schreiben", teamRight), String.format("RundMail Team %s schreiben", teamRight), "", 0, 0);
            Panel panel7 = new Panel();
            TextArea a = new TextArea(15, 50);
            a.setText(drei);
            panel7.addComponent(a);
            popup.addPanel(panel7);
            Panel panel2 = new Panel();
            Button button2 = new Button("Absenden");
            button2.enableAction();
            button2.setStyled(true);
            panel2.addComponent(button2);
            Button button3 = new Button("Abbrechen");
            button3.setStyled(true);
            panel2.addComponent(button3);
            popup.addPanel(panel2);
            popup.setOpcode(ReceiveOpcode.FA.getValue(), teamRight);
            client.send(popup.toString());
    	} else if(button.equals("Hinzufügen") || button.equals("Entfernen")) {
    		String nick = drei;
    		String rank = vier;
    		
    		byte rankID = 0;
    		
    		if(rank.equals("Teamleiter")) {
    			rankID = 1;
    		}
                
                if(rank.equals("Teamnick")) {
    			rankID = 6;
    		}
    		
    		Client target = Server.get().getClient(nick);
    		boolean online = true;
    		
    		if(target == null) {
    			online = false;
    			target = new Client(null);
    			target.loadStats(drei);
    			
    			if(target.getName() == null) {
                        PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", "##Der Nick existiert nicht.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
    				
    			}
    		}
    		
    		if(button.equals("Hinzufügen")) {
    			if(target.checkTeam(teamRight, rankID)) {
                                PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", String.format("##%s ist bereits %s im Team %s.", target.getName(), rank, teamRight), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
    				return;
    			}
    			
                                PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", String.format("##%s wurde als %s dem Team %s hinzugefügt.", target.getName(), rank, teamRight), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                        
                        
                        
                        
    			String newTeams = String.format("%s|%s~%s|", target.getTeams(), teamRight, rankID);
    	                Long time = System.currentTimeMillis()/1000; 
                        Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("%s-Team", teamRight), String.format("Hallo %s,##Du wurdest soeben dem _Team %s als %s hinzugefügt_.##Liebe Grüße,#%s", target.getName(), teamRight, rank, Server.get().getButler().getName()), time);
			Server.get().newSysLogEntry(client.getName(), String.format("%s wurde dem %s-Team hinzugefügt", target.getName(), teamRight));
           
    			if(online) {
    				target.setTeams(newTeams);
                                target.setCareer2(String.format("%s|%s Aufnahme ins _%s_-Team|", target.getCareer2(), Server.get().timeStampToDate(time) , teamRight));
                                target.saveStats();
    			} else {
    				Server.get().query(String.format("UPDATE `accounts` SET `teams` = '%s' WHERE `name` = '%s'", newTeams, target.getName()));
    			        Server.get().query(String.format("update accounts set career2='%s|%s Aufnahme ins _%s_-Team|' where name='%s'", target.getCareer2(), Server.get().timeStampToDate(time), teamRight, target.getName()));
            
                        }
    		}else {
    			if(!target.checkTeam(teamRight, rankID)) {
                                PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", String.format("##%s ist nicht %s im Team %s.", target.getName(), rank, teamRight), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
    				return;
    			}
    			
                        PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", String.format("##%s wurde als %s aus dem Team %s entfernt.", target.getName(), rank, teamRight), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
			Long time = System.currentTimeMillis()/1000;
                        Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("%s-Team", teamRight), String.format("Hallo %s,##Du wurdest soeben _aus dem Team %s als %s entfernt_.##Liebe Grüße,#%s", target.getName(), teamRight, rank, Server.get().getButler().getName()), time);
		        Server.get().newSysLogEntry(client.getName(), String.format("%s wurde aus dem %s-Team entfernt", target.getName(), teamRight));
    			String newTeams = target.getTeams().replace(String.format("|%s~%s|", teamRight, rankID), "");
    			     
    			if(online) {
    				target.setTeams(newTeams);
                                target.setCareer2(String.format("%s|%s Das _%s_-Team verlassen|", target.getCareer2(), Server.get().timeStampToDate(time) , teamRight));
                                target.saveStats();
    			} else {
    				Server.get().query(String.format("UPDATE `accounts` SET `teams` = '%s' WHERE `name` = '%s'", newTeams, target.getName()));
    			        Server.get().query(String.format("update accounts set career2='%s|%s Das _%s_-Team verlassen|' where name='%s'", target.getCareer2(), Server.get().timeStampToDate(time), teamRight, target.getName()));
            
                    
                        }
    		}
		} else if(button.equals("Absenden")) { 
    		if(drei.isEmpty()) {
                        PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", "##Bitte gib den Text an.", 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
    			        return;
    		}

                   PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", String.format("##Eine RundMail an das Team %s wurde _gesendet_.", teamRight), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
    		

			PoolConnection pcona = ConnectionPool.getConnection();
            PreparedStatement psa = null;
        
            try {
                Connection cona = pcona.connect();
                psa = cona.prepareStatement("SELECT `teams`,`name` FROM `accounts` WHERE `teams` != ''");
                ResultSet rsa = psa.executeQuery();
            
                while(rsa.next()) {
                    String tea = rsa.getString("teams").toLowerCase();
                    String name = rsa.getString("name");
                    
                    if(tea.contains(String.format("|%s~", teamRight.toLowerCase()))) {
                    	Server.get().newMessage(Server.get().getButler().getName(), name, String.format("Rundmail an das %s-Team (von %s)", teamRight.replace("-Team", ""), client.getName()).replace("von %s", ""), String.format("Die Teamleiter des Teams _%s_ haben dir folgende Nachricht hinterlassen:##%s", teamRight, drei), System.currentTimeMillis()/1000);
                    }
                }    
            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (psa != null) {
                    try {
                        psa.close();
                    } catch (SQLException e) {
                    }
                }

                pcona.close();
            }
    	} else if(button.equals("Teaminfo")) {
            String team = Server.help.get(String.format("%s-team", teamRight.toLowerCase()))[1];
            
            Popup popup = new Popup(String.format("Teaminfo %s bearbeiten", teamRight), String.format("Teaminfo %s bearbeiten", teamRight), "", 0, 0);
            Panel panel7 = new Panel();
            TextArea a = new TextArea(15, 50);
            a.setText(team);
            panel7.addComponent(a);
            popup.addPanel(panel7);
            Panel panel2 = new Panel();
            Button button2 = new Button("Speichern");
            button2.enableAction();
            button2.setStyled(true);
            panel2.addComponent(button2); 
            Button button3 = new Button("Abbrechen");
            button3.setStyled(true);
            panel2.addComponent(button3);
            popup.addPanel(panel2);
            popup.setOpcode(ReceiveOpcode.FA.getValue(), teamRight);
            client.send(popup.toString());
    	} else if(button.equals("Speichern")) {
    		String info = tokens[3].trim();
    		String teamT = teamRight.toLowerCase()+"-team";

            if(!Server.help.containsKey(teamT)) {
            	String title = String.format("Hilfe - %s-Team", teamRight);
            	Server.help.put(teamT, new String[] {title, ""});
            	Server.get().query(String.format("INSERT INTO `help` SET `word` = '%s', `text` = '', `title` = '%s'", teamT, title));
            }
           
                
                   PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", String.format("##Die Teaminfo des Teams %s wurde _geändert_.", teamRight), 400, 275);
                                Panel panel = new Panel();
                                Button buttonMessage3 = new Button("   OK   ");
                                buttonMessage3.setStyled(true);
                                panel.addComponent(buttonMessage3);
                                popup.addPanel(panel);
                                client.send(popup.toString());
                
    		String title = Server.help.get(teamT)[0];
    		Server.help.put(teamT, new String[] {title, info});
    		Server.get().query(String.format("UPDATE `help` SET `text` = '%s' WHERE `word` = '%s'", info.replace("'", "\\'"), teamT));
    	}
	}
}

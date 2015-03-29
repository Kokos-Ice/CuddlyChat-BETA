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

import starlight.Client;
import starlight.Server;
import tools.HexTool;
import tools.PacketCreator;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class SetNewPasswordHandler {
	
	public static void handle(String tokens[], Client client) {
		String actualNick = tokens[1].trim();
		String nick = tokens[3].trim();
		String password = tokens[4].trim();
		long time = System.currentTimeMillis()/1000;
        
        if(!client.getName().equals(actualNick)) {
          //  client.send(Popup.create("Problem", "Nicht eingeloggt", String.format("#Du bist _nicht_ mit ndem Nick %s _eingeloggt_.", actualNick), 300, 200));
            Popup popup = new Popup("Problem", "Problem", String.format("Du bist _nicht_ mit dem Nick %s _eingeloggt_.", actualNick), 450, 350);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
        }
        
        if(client.getSnp() > 4) {
            //client.send(Popup.create("Problem", "Problem", "Du kannst nur 5 Passwörter pro Tag neu setzen.", 450, 275));
            Popup popup = new Popup("Problem", "Problem", "Du kannst nur 5 Passwörter pro Tag neu setzen.", 450, 350);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
        }
        
        if(nick.isEmpty() || password.isEmpty()) {
        	client.send(PacketCreator.createSnpWindow(client.getName(), nick, password));
    		//client.send(Popup.create("Problem", "Problem", "Bitte füll alle Felder aus.", 450, 275));
    		Popup popup = new Popup("Problem", "Problem", "Bitte füll alle Felder aus.", 450, 350);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
        }
        
        Client target = Server.get().getClient(nick);
        boolean online = true;
        
        if(target == null) {
        	online = false;
        	target = new Client(null);
        	target.loadStats(nick);
        	
        	if(target.getName() == null) {
            	client.send(PacketCreator.createSnpWindow(client.getName(), nick, password));
        		//client.send(Popup.create("Problem", "Problem", String.format("Der Nick _%s existiert nicht_.", nick), 450, 275));
        		Popup popup = new Popup("Problem", "Problem", String.format("Der Nick _%s existiert nicht_.", nick), 450, 350);
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
        
        if(online) {
        	client.send(PacketCreator.createSnpWindow(client.getName(), nick, password));
        	//client.send(Popup.create("Problem", "Problem", String.format("Der Nick %s muss offline sein um ein neues Passwort setzen zu können.", nick), 450, 275));
    		Popup popup = new Popup("Problem", "Problem", String.format("Der Nick %s muss offline sein um ein neues Passwort setzen zu können", nick), 450, 350);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;    
        }
        
        if(target == Server.get().getButler() || target.getRank() >= client.getRank()) {
        	client.send(PacketCreator.createSnpWindow(client.getName(), nick, password));
        	//client.send(Popup.create("Problem", "Problem", String.format("Du kannst das Passwort von %s nicht ändern.", nick), 450, 275));
    		Popup popup = new Popup("Problem", "Problem", String.format("Du kannst das Passwort von %s nicht ändern.", nick), 450, 350);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;    
        }
        
        client.send(Popup.create("Passwort gesetzt", "Passwort gesetzt", String.format("Das Passwort von _%s_ wurde auf _%s_ gesetzt.", nick, password), 450, 275));
        client.setSnp((byte) (client.getSnp()+1));
        Server.get().query(String.format("INSERT INTO `snp` SET `von` = '%s', `nick` = '%s', `wann` = '%s'", actualNick, target.getName(), time));
        Server.get().query(String.format("UPDATE `accounts` SET `password` = '%s' WHERE `name` = '%s'", HexTool.hash("sha1", password), nick));
        Server.snp.put(time, new String[] {target.getName(), client.getName()});
        
        target.setComment(time, null, null, Server.get().getButler().getName(), String.format("Passwort von %s neugesetzt", actualNick));
	}
}

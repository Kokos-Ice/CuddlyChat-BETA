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

import java.util.Random;

import starlight.Channel;
import starlight.Client;
import starlight.Server;

import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class InviteHandler {

	public static void handle(String[] tokens, Client client) {
		String nick = tokens[1].trim().replace("<", "\\<");
		String separeeName = tokens[3].trim();
		String separeeTopic = tokens[4].trim();
		String design = tokens[5].trim();
		String test = tokens[6].trim();
		String butler = tokens[7].trim();
		Client target = Server.get().getClient(nick);
		
		if(separeeName.isEmpty()) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Du musst ein Name für das Separee angeben.", 450, 275));
			return;
		}
		
		if(separeeName.length() > 20) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Der Name des Separees ist zu lang.", 450, 275));
			return;
		}
		
		if(separeeTopic.isEmpty()) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Du musst ein Thema für das Separee angeben.", 450, 275));
			return;
		}
		
		if(separeeTopic.length() > 250) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Das Thema des Separees ist zu lang.", 450, 275));
			return;
		}
		
		if(target == null) {
			client.send(PopupNewStyle.create("Problem", "Problem", String.format("%s ist mittlerweile offline.", nick), 450, 275));
			return;
		}
		
		int randomInt = new Random().nextInt(99999)+1;
		
		for(Channel c : client.getChannels()) {
			client.sendButlerMessage(c.getName(), String.format("Ich habe °>_h%s|/serverpp \"|/w \"<° benachrichtigt.", nick));
		}
		
		for(Channel s : target.getChannels()) {
			target.sendButlerMessage(s.getName(), String.format("%s hat dich in ein Separee eingeladen. °>finger.b.gif<° _°BB>Folgen|/ok %s<°", client.getName(), randomInt));
		}
		
		Server.invite.put(randomInt, new String[] {nick,client.getName(), design, test, butler, separeeName, separeeTopic});
	}
}

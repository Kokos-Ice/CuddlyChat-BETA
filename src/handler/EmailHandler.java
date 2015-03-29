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
import tools.KCodeParser;
import tools.PacketCreator;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class EmailHandler {
	public static void handle(String[] tokens, Client client) {
		String nick = KCodeParser.noKCode(tokens[3].trim());
		String text = KCodeParser.noKCode(tokens[4].trim().replace("#", "<br>"));
		Client target = Server.get().getClient(nick);
		
		if(nick.isEmpty()) {
			client.send(PacketCreator.createEmailWindow(nick, text));
			client.send(PopupNewStyle.create("E-Mail Problem", "E-Mail Problem", "Bitte gib einen Empf�nger an.", 450, 275));
			return;
		}
		
		if(text.isEmpty()) {
			client.send(PacketCreator.createEmailWindow(nick, text));
			client.send(PopupNewStyle.create("E-Mail Problem", "E-Mail Problem", "Bitte gib einen Text ein.", 450, 275));
			return;
		}
		
		if(text.length() > 2000) {
			client.send(PacketCreator.createEmailWindow(nick, text));
			client.send(PopupNewStyle.create("E-Mail Problem", "E-Mail Problem", "Deine Nachricht ist zu lang. Du darfst _maximal 2.000 Zeichen_ benutzen.", 450, 275));
			return;
		}
		
		if(target == null) {
			target = new Client(null);
			target.loadStats(nick);
			
			if(target.getName() == null) {
				client.send(PacketCreator.createEmailWindow(nick, text));
				client.send(PopupNewStyle.create("E-Mail Problem", "E-Mail Problem", String.format("Der Nick _%s existiert nicht_!", nick), 450, 275));
				return;
			}
		}
		
		if(target.getEmail().isEmpty()) {
			client.send(PacketCreator.createEmailWindow(nick, text));
			client.send(PopupNewStyle.create("E-Mail Problem", "E-Mail Problem", String.format("%s hat keine E-Mail angegeben.", nick), 450, 275));
			return;
		}
		
		if(target.getEmails() == 0) {
			client.send(PacketCreator.createEmailWindow(nick, text));
			client.send(PopupNewStyle.create("E-Mail Problem", "E-Mail Problem", String.format("%s m�chte keine E-Mails �ber /email empfangen!", target.getName()), 450, 275));
			return;
		}
		
		client.send(PopupNewStyle.create("E-Mail versendet", "E-Mail versendet", String.format("Deine Mail an %s wurde gesendet.", target.getName()), 450, 275));
	}
}

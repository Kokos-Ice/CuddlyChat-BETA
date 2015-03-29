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

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class RequestUserListHandler {

	public static void handle(String[] tokens, Client client) {
		String ch = tokens[1].trim();
		Channel channel = Server.get().getChannel(ch);
		StringBuilder nicks = new StringBuilder();
		String title = String.format("%s (%s)", channel.getName(), channel.countClients());
		int cC = 1;

    	for (Client nick : channel.getClients()) {
        	nicks.append(cC!=1?", ":"").append("°>_h").append(nick.getName()).append("|/serverpp \"|/w \"<°");
        	cC++;
    	}
    
    	client.send(PopupNewStyle.create(title, title, nicks.toString(), 400, 200));
	}
}
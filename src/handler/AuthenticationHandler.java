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
import tools.HexTool;
import tools.PacketCreator;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class AuthenticationHandler {

	public static void handle(String[] tokens, Client client) {
		String channel = tokens[1].trim();
		String authPassword = tokens[3].trim();
		Channel channelTo = Server.get().getChannel(channel);
		
		if(!HexTool.hash("SHA1", authPassword).equals(client.getAuthPassword())) {
			client.send(PopupNewStyle.create("Sicherheitscode falsch", "Sicherheitscode falsch", "#°RR°_Der Sicherheitscode ist falsch!", 450, 275));
			return;
		}
		
        client.joinChannel(channelTo);
        client.send(PacketCreator.channelFrame(channelTo, client.getName(), client.newMessages.size()));
        channelTo.join(client);
	}
}

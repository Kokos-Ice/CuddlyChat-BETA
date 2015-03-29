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
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class VoteHandler {

	public static void handle(String[] tokens, Client client) {
		String wahl = tokens[1].trim();
		StringBuilder vh = new StringBuilder();
		int x = 1;
		
		for(String lol : tokens) {
			if(x > 2) {
				vh.append(lol).append(";;;");
			}
			
			if(x%2 == 0) {
				vh.append("|");
			}
			
			x++;
		}
		
		
    	client.send(PopupNewStyle.create("Wahl gezählt", "Wahl gezählt", "Deine Wahl wurde registriert und gezählt.", 450, 275));
    	Server.get().query(String.format("INSERT INTO `wahlenvoted` SET `name` = '%s', wahl = '%s', what = '%s'", client.getName(), wahl, vh.toString()));
	}
}
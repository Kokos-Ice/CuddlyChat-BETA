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

import java.text.SimpleDateFormat;
import java.util.Date;

import starlight.Channel;
import starlight.Client;
import starlight.Server;

import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class InfoHandler {

	public static void handle(String[] tokens, Client client) {
		Channel channel = Server.get().getChannel(tokens[1].trim());
		int cms = Math.round(channel.getLcStammis()/5);
		StringBuilder text = new StringBuilder();
		int monat = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
		int jahr = Integer.parseInt(new SimpleDateFormat("yy").format(new Date()));
		String title = String.format("Moderatorenwahlen - %s", channel.getName());
		
		text.append("#In diesem Channel werden Channelmoderatoren gewählt.##Die nächste _Nominierungsphase_ beginnt am 01.");
		
		if(monat == 12) {
			text.append("01.20").append(jahr+1);
		} else {
			monat++;
			
			if(monat < 10) {
				text.append("0");
			}
			
			text.append(monat);
		}
		
		text.append(".20").append(jahr).append(" (");
		
		if(cms < 1) {
			text.append("momentan würde die Wahl entfallen, da noch zu wenige Stammis diesen Channel zum Lieblingschannel gewählt haben");
		} else {
			text.append("insgesamt würden momentan ").append(cms).append(" Channelmoderatoren gewählt werden");
		}
		
		text.append(").");
		
		client.send(PopupNewStyle.create(title, title, text.toString(), 450, 275));
		return;
	}
}
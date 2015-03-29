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

public class MentorHandler {
	public static void handle(String[] tokens, Client client) {
		Client schützling = Server.get().getClient(tokens[1].trim());
		String typ = tokens[2].trim();
		
		if(schützling == null) {
			client.send(PopupNewStyle.create("Problem", "Verschwunden", String.format("%s ist in der Zwischenzeit leider schon verschwunden.##ist in der Zwischenzeit leider schon verschwunden.", tokens[1].trim()), 450, 275));
			return;
		}
		
		if(typ.equals("Ja")) {
			if(!schützling.getMentor().isEmpty()) {
				client.send(PopupNewStyle.create("Problem", "Schlafmütze", "Du hast dir zu lange Zeit gelassen, um auf das letzte Fenster zu reagieren.", 450, 275));
				return;
			}
		
			client.send(PopupNewStyle.create("Mentorschaft", "Mentorschaft", String.format("Nun trägst du die Verantwortung für _°>_h%s|/serverpp \"|/w \"<°_.##Bitte begebe Dich in den _°BB°Channel °>_h%s|/go \"|/go +\"<r°_ und fange mit Deinem Schützling ein Gespräch an, es sollte die Chance für eine _Freundschaft_ bestehen.#_Beachte folgendes:_##- Es ist immer am besten, wenn alles wie ein _spontanes Gespräch_ ausschaut, erwähne nicht das Mentorenprogramm#- %s ist ganz neu dabei, jede Art der Hilfe ist willkommen#- Wenn %s Familymitglied wird, wirst du benachrichtigt und erhältst ganze _20 Mentorenpunkte_#- Du erhältst Mentorenpunkte _nur_, wenn du dich auch wirklich um deinen Schützling kümmerst#- Mit _/mentor_ kannst du deine letzten Schützlinge nachschauen.", schützling.getName().replace("<", "\\<"), schützling.getChannel().getName(), schützling.getName().replace("<", "\\<"), schützling.getName().replace("<", "\\<")), 395, 365));
			schützling.setMentor(client.getName());
			client.setSchuetzlinge(String.format("%s|%s|", client.getSchuetzlinge(), schützling.getName()));
		}
	}
}
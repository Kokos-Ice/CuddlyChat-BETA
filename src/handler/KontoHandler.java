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
import starlight.CommandParser;
import starlight.Server;
import tools.HexTool;
import tools.PacketCreator;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class KontoHandler {

	public static void handle(String[] tokens, Client client) {
		String nick = tokens[1].trim();
		String what = tokens[2].trim();
		
		
		if(!nick.equals(client.getName())) {
			client.send(PopupNewStyle.create("Problem", "Nicht eingeloggt", String.format("#Du bist _nicht_ mit ndem Nick %s _eingeloggt_.", nick), 300, 200));
        	return;
		}
		
		if(what.equals("Kontopasswort speichern")) {
			String password = tokens[3].trim();
			String password2 = tokens[4].trim();
			
			if(password.isEmpty()) {
        		client.send(PacketCreator.kontoPw(client.getName()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Geben sie unter Passwort ihr neues _Passwort_ für das Knuddelskonto ein.", 450, 275));
	        	return;
			}
			
			if(!password.equals(password2)) {
        		client.send(PacketCreator.kontoPw(client.getName()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Die angegebenen _Passwörter waren nicht identisch_.", 450, 275));
	        	return;
			}
			
			client.setKontoPassword(HexTool.hash("sha1", password));
			CommandParser.parse("/konto", client, client.getChannel(), false);
			client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Passwort gespeichert", "Schweizer Knuddelskonto - Passwort gespeichert", "#Das Passwort für Ihr Knuddelskonto wurde gespeichert.##_°R°Notieren Sie sich Ihr Passwort unbedingt§, da es bei Verlust nicht neu gesetzt werden kann!", 450, 275));
			return;
		}
		
		if(what.equals("Kontopasswort setzen")) {
			String oldPw = tokens[3].trim();
			String password = tokens[4].trim();
			String password2 = tokens[5].trim();
			
			if(!HexTool.hash("sha1", oldPw).equals(client.getKontoPassword())) {
        		client.send(PacketCreator.kontoPwNew(client.getName()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Das von Ihnen angegebene _alte Kontopasswort ist falsch_.", 450, 275));
	        	return;
			}
			
			if(password.isEmpty()) {
        		client.send(PacketCreator.kontoPwNew(client.getName()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Geben sie unter Passwort ihr neues _Passwort_ für das Knuddelskonto ein.", 450, 275));
	        	return;
			}
			
			if(!password.equals(password2)) {
        		client.send(PacketCreator.kontoPwNew(client.getName()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Die angegebenen _neuen Passwörter waren nicht identisch_.", 450, 275));
	        	return;
			}
			
			client.setKontoPassword(HexTool.hash("sha1", password));
			CommandParser.parse("/konto", client, client.getChannel(), false);
			client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Passwort gespeichert", "Schweizer Knuddelskonto - Passwort gespeichert", "#Das Passwort für Ihr Knuddelskonto wurde gespeichert.##_°R°Notieren Sie sich Ihr Passwort unbedingt§, da es bei Verlust nicht neu gesetzt werden kann!", 450, 275));
			return;
		}
		
		if(what.equals("Kontopasswort ändern")) {
    		client.send(PacketCreator.kontoPwNew(client.getName()));
		}
		
		if(what.equals("OK")) {
			String knuddels = tokens[3].trim();
			String aktion = tokens[4].trim();
			String kontopw = tokens[5].trim();
			
			if(knuddels.isEmpty()) {
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Geben Sie bei _Knuddels die Anzahl der Knuddels an_.", 450, 275));
				return;
			}
			
			try {
				Integer.parseInt(knuddels);
			} catch(Exception ex) {
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Geben Sie bei _Knuddels die Anzahl der Knuddels an_.", 450, 275));
				return;
			}
			
			int kn = Integer.parseInt(knuddels);
			if(kn < 1) {
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Geben Sie bei _Knuddels mindestens einen Knuddel an_.", 450, 275));
				return;
			}
			
			if(aktion.equals("Aktion wählen...")) {
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Bitte _wählen Sie eine Aktion aus_.", 450, 275));
				return;
			}
			
			if(!HexTool.hash("sha1", kontopw).equals(client.getKontoPassword())) {
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", "#Das von Ihnen angegebene _Kontopasswort ist falsch_.", 450, 275));
				return;
			}
			
			if(aktion.equals("Abheben")) {
				if(client.getKontoKnuddels() < kn) {
					client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
					client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", String.format("Sie können maximal %s abheben, mehr ist momentan nicht auf Ihrem Konto.", client.getKontoKnuddels()), 450, 275));
					return;
				}

				client.setKontoKnuddels(client.getKontoKnuddels()-kn);
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Knuddels abgehoben", "Schweizer Knuddelskonto - Knuddels abgehoben", String.format("Sie haben soeben _%s Knuddels_ von ihrem Schweizer Knuddelskonto abgehoben.", kn), 450, 275));
				client.increaseKnuddels(kn);
				Server.get().deseaseAllKontoKnuddels(kn);
				return;
			}
			
			if(aktion.equals("Einzahlen")) {
				if(client.getKnuddels() < kn) {
					client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
					client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Problem", "Schweizer Knuddelskonto - Problem", String.format("Sie können maximal %s einzahlen, mehr besitzen Sie nicht.", client.getKnuddels()), 450, 275));
					return;
				}

				client.setKontoKnuddels(client.getKontoKnuddels()+kn);
				client.send(PacketCreator.konto(client.getName(), client.getKontoKnuddels()));
				client.send(PopupNewStyle.create("Schweizer Knuddelskonto - Knuddels eingezahlt", "Schweizer Knuddelskonto - Knuddels eingezahlt", String.format("Sie haben soeben _%s Knuddels_ auf ihr Schweizer Knuddelskonto eingezahlt.", kn), 450, 275));
				client.deseaseKnuddels(kn);
				Server.get().increaseAllKontoKnuddels(kn);
				return;
			}
			
			return;
		}
	}
}

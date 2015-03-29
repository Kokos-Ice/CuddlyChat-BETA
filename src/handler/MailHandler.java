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

import java.util.regex.Pattern;

import starlight.Client;
import starlight.Server;
import tools.PacketCreator;
import tools.Password;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class MailHandler {
	
	private static Pattern emailPattern;
    private static final String validEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,5})$";
    
    static {
        emailPattern = Pattern.compile(validEmail);
    }
    
	public static void handle(String[] tokens, Client client) {
		String email = tokens[3].trim();
		long time = System.currentTimeMillis()/1000;
		
		if(!emailPattern.matcher(email).matches()) {
			client.send(PacketCreator.createVerifyWindow(client.getName(), email, 2));
		} else {
			String okcode = Password.generateRandom(15);
			String cancelcode = Password.generateRandom(15);
			
			client.send(PopupNewStyle.create("E-Mail-Verifizierung gestartet", "E-Mail-Verifizierung gestartet", String.format("Die angegebene E-Mail-Adresse %s wird nun auf Gültigkeit überprüft. In einigen Sekunden wirst du eine Verifizierungs-E-Mail von uns erhalten. Bitte _schaue in deinem Postfach nach_.##Wenn du gerade im Chat eingeloggt bist, erhältst du _eine Nachricht per /m, sobald die Verifizierungs-E-Mail versendet wurde_.", email), 450, 275));
			client.setEmailVerify((byte) 1);
			
			if(!client.getEmail().equalsIgnoreCase(email)) {
				client.setEmail(email);
			}
			
			//Email prüfen
			
			
			if(client != null) {
				Server.get().newMessage(Server.get().getButler().getName(), client.getName(), "Verifizierungs-E-Mail gesendet", String.format("An die von dir angegebene E-Mail-Adresse %s wird in den nächsten Minuten eine Verifizierungs-E-Mail gesendet.##Bitte ruf diese E-Mail ab und bestätige sie durch einen Klick auf den enthaltenen Verifizierungs-Link.##Um diesen Änderungsvorgang abzubrechen, °>klick hier|/e description cancelver<°.", email), time);
			}
			
			Server.get().query(String.format("INSERT INTO `emailverify` SET `name` = '%s', `ok` = '%s', `cancel` = '%s'", client.getName(), okcode, cancelcode));
		}
	}
}

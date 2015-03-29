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
import tools.popup.Popup;
import tools.Birthday;
import tools.popup.PopupNewStyle;

public class VerifyHandler {
	
	public static boolean checkPattern(String aPattern, String aCode) {
		return aCode.matches(aPattern);
	}
		
	public static boolean checkID(String aID) {
		if (!checkPattern("[\\d]{10}[<]{1}[\\d]{7}[<]{1}[\\d]{7}[<]{1}[\\d]{1}", aID))
		    return false;
		
		String[] subs = aID.split("<");
		int[] wgt = { 7, 3, 1 };
		int tmp = 0, com = 0, pos = 0;

		for (int a = 0; a < subs.length - 1; a++, tmp = 0) {
			for (int i = 0; i < subs[a].length() - 1; i++) {
			      tmp += ((subs[a].charAt(i) - 48) * wgt[i % 3]) % 10;
			      com += ((subs[a].charAt(i) - 48) * wgt[pos++ % 3]) % 10;    
			}
			 
			if (tmp % 10 != subs[a].charAt(subs[a].length() - 1) - 48)
			    return false;	
			    com += ((tmp % 10) * wgt[pos++ % 3]) % 10;
		}

		if (com % 10 != subs[subs.length - 1].charAt(subs[subs.length - 1].length() - 1) - 48)
			return false;
			  
		return true;

	}
	
	public static void handle(String[] tokens, Client client) {
		String eins = tokens[3].trim();
		String zwei = tokens[4].trim();
		String drei = tokens[5].trim();
		String vier = tokens[6].trim();

		if (client.getVerify() == 1) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Dein Alter ist bereits verifiziert und lässt sich nun nicht mehr ändern.", 450, 275));
			return;
		}
		
		if (!eins.isEmpty() && eins.length() >= 10) {
			eins = eins.substring(0, 10);
		}
	
		String code = String.format("%s<%s<%s<%s", eins, zwei, drei, vier);
		String tag = "";
		String monat = "";
		String jahr2 = "";
		String falschrum = "";
		
		if(client.getPersofalsch() > 2) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Du hast bereits drei mal eine falsche Nummer eingegeben. Versuche es später noch einmal.", 450, 275));
	        return;
		}
		
		if (eins.length() != 10 || eins.isEmpty()) {
	        client.send(PopupNewStyle.create("Problem", "Problem", "Die _erste Zahl_ hat eine falsche Länge, bitte überprüfe sie nochmal anhand des Personalausweises.", 450, 275));
	        client.increasePersofalsch();
	        return;
		} else if (zwei.length() != 7 || zwei.isEmpty()) {
	        client.send(PopupNewStyle.create("Problem", "Problem", "Die _zweite Zahl_ hat eine falsche Länge, bitte überprüfe sie nochmal anhand des Personalausweises.", 450, 275));
	        client.increasePersofalsch();
	        return;
		} else if (drei.length() != 7 || drei.isEmpty()) {
	        client.send(PopupNewStyle.create("Problem", "Problem", "Die _dritte Zahl_ hat eine falsche Länge, bitte überprüfe sie nochmal anhand des Personalausweises.", 450, 275));
	        client.increasePersofalsch();
	        return;
		} else if (vier.length() != 1 || vier.isEmpty()) {
	        client.send(PopupNewStyle.create("Problem", "Problem", "Die _vierte Zahl_ hat eine falsche Länge, bitte überprüfe sie nochmal anhand des Personalausweises.", 450, 275));
	        client.increasePersofalsch();
	        return;
		} else if(checkID(code) == false) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Die Zahlen enthalten einen Fehler. Bitte überprüfe sie nochmal anhand des Personalausweises.", 450, 275));
			client.increasePersofalsch();
			return;
		} 
		
		if (!zwei.isEmpty()) {
			tag = zwei.substring(4, 6);
			monat = zwei.substring(2, 4);
			String jahr = zwei.substring(0, 2);

			if (Integer.parseInt(jahr) >= 50) {
				jahr2 = String.format("19%s", jahr);
			} else {
				jahr2 = String.format("20%s", jahr);
			}
			
			falschrum = String.format("%s.%s.%s", jahr2, monat, tag);
		}
		
		int age = client.getAge();
		
		try {
			age = Birthday.toAge(falschrum);
		} catch (Exception e) {
		}
		
		if(age < 16) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Du kannst dein Alter erst ab 16 verifizieren.", 450, 275));
			return;
		}
		
		if(age > client.getAge() || age < client.getAge()) {
			client.send(PopupNewStyle.create("Problem", "Problem", "Dein Alter konnte nicht geändert werden. Deine Verifizierung weicht zu stark von dem Alter ab, dass Du zuvor angegeben hast.", 450, 275));
			return;
		}
		
		client.send(PopupNewStyle.create("Erfolg", "Erfolg", String.format("Dein Geburtsdatum wurde verifiziert. Änderungen am Alter oder Geburtsdatum sind nun nicht mehr möglich.%s", age != client.getAge() ? "##Dein Alter wurde aus deinem Geburtsdatum automatisch berechnet.": ""), 450, 275));
		client.setVerify((byte)1);
		client.setAge((byte)age);
		client.setBirthday(String.format("%s.%s.%s", tag, monat, jahr2));
    }
}
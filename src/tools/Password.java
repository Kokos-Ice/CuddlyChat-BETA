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



package tools;

import java.util.ArrayList;
import java.util.Collection;

public class Password {
	
	public static String generateRandom(int length) {
		Collection<Character> characters = new ArrayList<Character>();
		StringBuilder chars = new StringBuilder();
		StringBuilder password = new StringBuilder();
		
		for(int i = 48; i <= 57; i++) { // Zahlen
			characters.add((char)i);
		}
		
		for(int i = 65; i <= 90; i++) { //Großbuchstaben
			characters.add((char)i);
		}
		
		for (Character c : characters) {
			chars.append(c);
		}
		
		char [] pwChars = chars.toString().toCharArray();
		int characterAmount = characters.size();
		
		for (int i = 1 ; i <= length; i++){
			password.append(pwChars[(int)(Math.random()* ((characterAmount - 1) + 1) + 1) - 1]);
		}
		
		return password.toString();
	}
}

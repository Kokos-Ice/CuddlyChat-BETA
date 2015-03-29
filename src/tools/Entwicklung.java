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

import java.util.Random;

public class Entwicklung {

	private static Random random = new Random();
	
	public static int getNumber() {
		int x = 2;
		
		while(true) {
			int y = random.nextInt(2)+1;
			
			if(x == 11 || y != 2) {
				return x;
			}

			x++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Smiley: "+getNumber());
	}
}

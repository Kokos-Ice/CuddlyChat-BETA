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

public class Zodiac { 
	
    public static String getZodiacSign(String birthday) {
        String[] datesplit = birthday.split("\\.");
        int D = Integer.parseInt(datesplit[0]);
        int M =  Integer.parseInt(datesplit[1]);
       
        if ((M == 12 && D >= 22 && D <= 31) || (M ==  1 && D >= 1 && D <= 19)) {
            return "Steinbock";
        } else if ((M ==  1 && D >= 20 && D <= 31) || (M ==  2 && D >= 1 && D <= 17)) {
           return "Wassermann";
        } else if ((M ==  2 && D >= 18 && D <= 29) || (M ==  3 && D >= 1 && D <= 19)) {
           return "Fische";
        } else if ((M ==  3 && D >= 20 && D <= 31) || (M ==  4 && D >= 1 && D <= 19)) {
            return "Widder";
        } else if ((M ==  4 && D >= 20 && D <= 30) || (M ==  5 && D >= 1 && D <= 20)) {
            return "Stier";
        } else if ((M ==  5 && D >= 21 && D <= 31) || (M ==  6 && D >= 1 && D <= 20)) {
            return "Zwillinge";
        } else if ((M ==  6 && D >= 21 && D <= 30) || (M ==  7 && D >= 1 && D <= 22)) {
            return "Krebs";
        } else if ((M ==  7 && D >= 23 && D <= 31) || (M ==  8 && D >= 1 && D <= 22)) {
            return "Löwe";
        } else if ((M ==  8 && D >= 23 && D <= 31) || (M ==  9 && D >= 1 && D <= 22)) {
            return "Jungfrau";
        } else if ((M ==  9 && D >= 23 && D <= 30) || (M == 10 && D >= 1 && D <= 22)) {
            return "Waage";
        } else if ((M == 10 && D >= 23 && D <= 31) || (M == 11 && D >= 1 && D <= 21)) {
            return "Skorpion";
        } else if ((M == 11 && D >= 22 && D <= 30) || (M == 12 && D >= 1 && D <= 21)) {
            return "Schütze";
        }

        return null;
    }
}

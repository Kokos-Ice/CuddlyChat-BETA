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

import java.security.MessageDigest;

public class HexTool {
    private static final char[] hexDigit = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    private static String toHex(byte b) {
        int signed = b & 0xFF;
        char[] hex = new char[] { hexDigit[signed >> 4], hexDigit[signed & 0x0F] };
        return String.valueOf(hex);
    }

    public static String hash(String algorithm, String str) {
        StringBuilder ret = new StringBuilder();
        byte[] bytes = null;

        try {
            MessageDigest hash = MessageDigest.getInstance(algorithm);
            bytes = hash.digest(str.getBytes("UTF8"));
        } catch (Exception e) {
        }

        for (byte b : bytes) {
            ret.append(toHex(b));
        }

        return ret.toString();
    }
}

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Source {

    public static String get(String websiteURL) {
        try {
            String sourceLine;
            URL url = new URL(websiteURL);
            URLConnection con = url.openConnection();
            con.setRequestProperty("User-Agent", "");
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder source = new StringBuilder();
            while ((sourceLine = bufferReader.readLine()) != null) {
                source.append(sourceLine);
            }
            return source.toString();
        } catch (Exception x) {
            x.printStackTrace();
            return null;
        }
    }
}

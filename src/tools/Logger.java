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

import starlight.Server;

public class Logger {

    public static void handle(String sender, String text) {
        StringBuilder handle = new StringBuilder();
        Long time = System.currentTimeMillis()/1000;
        handle.append("<").append(Server.get().timeStampToTime(time)).append("> ");
        
        if(sender != null) {
            handle.append(sender).append(": ");
        }
        
        handle.append(text);
        System.out.println(handle.toString());
    }
}
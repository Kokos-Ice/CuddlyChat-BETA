/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (c) 2011 - 2012 Flav <http://banana-coding.com>
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
import starlight.ReceiveOpcode;

public class DlHandler {

	
	public static void handle(String[] tokens, Client client) {
            if(tokens[0].equals("truncate")) {
                if(!client.hasPermission("cmd.devlog")) {
        		client.sendButlerMessage(client.getChannel().getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
                
                Server.get().query("TRUNCATE `dev_log`");
                client.sendButlerMessage(client.getChannel().getName(), "Die Dev-Logs wurden geleert!");
            } else {
                    
                client.sendButlerMessage(client.getChannel().getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                }
	}
}

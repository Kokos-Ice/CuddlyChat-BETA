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

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;

public class ChannelrulesHandler {
    public static void handle(String[] tokens, Client client) {
    	String channel = tokens[1].trim();
    	String info = tokens[3].trim();
    	Channel ch = Server.get().getChannel(channel);
    	
    		Popup popup = new Popup("Hinweis", "Hinweis", String.format("#Die Channelinfo des Channels %s wurde _gespeichert_.", ch.getName()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        
        ch.setInfo(info);
    }
}
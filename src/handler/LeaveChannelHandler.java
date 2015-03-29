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

public class LeaveChannelHandler {
    public static void handle(String[] tokens, Client client) {
        Channel channel = Server.get().getChannel(tokens[1]);

        if (channel == null || !channel.getClients().contains(client)) {
            return;
        }

        channel.leave(client);
        client.leaveChannel(channel);

        if (tokens.length >= 4) {
            //client.updatePosition(tokens[2], tokens[3]);

            if (tokens.length >= 6) {
                //client.updateSize(tokens[4], tokens[5]);

                if (tokens.length >= 7) {
                    client.setScrollspeed(Integer.parseInt(tokens[6]));
                }
            }
        }
    }
}
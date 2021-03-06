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

import java.util.Collection;

import starlight.Channel;
import starlight.Client;
import starlight.Server;

import tools.PacketCreator;

public class CategoryHandler {

	public static void handle(String[] tokens, Client client) {
		client.setLoginCategory(Byte.parseByte(tokens[1]));
		
		Collection<Channel> channelList = Server.get().getChannels();
	    client.send(PacketCreator.channelList(channelList, client.getLoginCategory()));	
	    client.send(PacketCreator.hello(((Channel) channelList.toArray()[0]).getName()));
	}
}
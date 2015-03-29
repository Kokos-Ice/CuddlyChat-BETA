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



package update;

import java.io.*;
import java.net.*;
import java.util.*;
import starlight.Server;

public class UpdateServer implements Runnable {
	protected ServerSocket listen;
	protected Vector<UpdateConnect> connections;
	Thread connect;

	public UpdateServer() {
		try {
			listen = new ServerSocket(1339);
		} catch (IOException e) {
		}
		
		connections = new Vector<UpdateConnect>();
		connect = new Thread(this);
		connect.start();
		
		System.out.println("UpdateServer ("+Server.get().getSettings("CHAT_NAME")+") Port 1339 started!");
	}

	public void run() {
		try {
			while(true) {
				Socket client=listen.accept();

				UpdateConnect c = new UpdateConnect(this, client);
				connections.addElement(c);
			}
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		new UpdateServer();
	}
}

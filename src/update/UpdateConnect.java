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

import java.net.*;
import java.io.*;

class UpdateConnect extends Thread {
	protected Socket client;
	protected DataInputStream in;
	protected PrintStream out;
	protected UpdateServer server;

	public UpdateConnect(UpdateServer server, Socket client) {
		this.server=server;
		this.client=client;

		try {
			in = new DataInputStream(client.getInputStream());
			out = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			try {
				client.close();
			} catch (IOException e2) {
			}
			
			return;
		}

		this.start();
	}


	@SuppressWarnings("deprecation")
	public void run() {
		String line;

		try {
			while(true) {
				line=in.readLine();
				
				if(line!=null) {
					if(line.contains("UPDATE~")) {
                                           int sekwait = Integer.parseInt(line.split("~")[1]);
						System.out.println("Update! "+(30+sekwait)+" Sekunden warten...");
						try {
							Thread.sleep(((30+sekwait)*1000));
							
						} catch(InterruptedException ex) {
						}
						
						Runtime rt = Runtime.getRuntime();
						System.out.println("Chatserver neustarten...");
						
						@SuppressWarnings("unused")
						Process proc = rt.exec("java -cp dist/heaven24.jar:lib/* starlight.Server 1338pause"); 
						
						System.out.println("Chatserver neugestartet!");
					}
				}
			}
		} catch (IOException e) {
		}
	}
}


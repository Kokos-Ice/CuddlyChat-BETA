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

import starlight.Client;
import starlight.Server;
import tools.popup.Popup;
import tools.PacketCreator;
import tools.popup.Button;
import tools.popup.Panel;

public class HandshakeHandler {
    public static void handle(String[] tokens, Client client) {
    	String applet = tokens[1].trim();
    	String url = tokens[2].trim();
        
    	String category = tokens[3].trim();
    	String java = tokens[4].trim();

        
      if(!url.startsWith("http://heaven24.de") && !url.startsWith("http://www.heaven24.de/index.php?page=chat&v=90biw2&c=0") && !url.startsWith("http://www.heaven24.de/") && !url.startsWith("http://localhost") && !url.startsWith("http://89.163.224.189") && !url.startsWith("http://cuddlychat.de") && !url.startsWith("http://heaven24.zapto.org") && !url.startsWith(Server.get().getURL()) && !url.startsWith("http://ms188.moonshot.fastwebserver.de")) {
    		Popup popup = new Popup("Illegal", "Illegal", String.format("Diese Webseite verwendet ein Chatapplet der Webseite _"+Server.get().getSettings("CHAT_NAME")+".de_, welches ohne eine Lizenz betrieben wird!##Dies wurde von unserem Server registriert. Eine Stellungsnahme des Seitenbetreibers wird erwartet, rechtliche Schritte behalten wir uns vor.##", Server.get().getURL().replace("http://", "").replace("/", ""), Server.get().getURL().replace("http://", "").replace("/", "")), 300, 200);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        
            client.send(PacketCreator.openURL(Server.get().getURL(), "_blank"));
    		return;
   		}
    	
        
        client.send(PacketCreator.butler());
        client.send(PacketCreator.changeProtocol());
    	client.setLoginCategory(Byte.parseByte(category));
        client.sendHello();
        client.setAppletVersion(applet);
        client.setJavaVersion(java);
        client.setLoginUrl(url);
        



    }
}

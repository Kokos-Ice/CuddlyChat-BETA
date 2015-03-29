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

import starlight.*;
import tools.PacketCreator;
import tools.popup.*;

public class PasswordHandler {
    public static void handle(String[] tokens, Client client) {
        String token = tokens[1].trim();
        String password = tokens[3].trim();
        
        String[] split = token.split(":", 2);
        String[] splitz = split[1].split(":");
             
        Channel channel = Server.get().getChannel(splitz[0]);
        Channel channelTo = Server.get().getChannel(split[0]);
        
        if(!channelTo.getPassword().equals(password)) {
            Popup popup = new Popup("Passwort notwendig", "Passwort notwendig", String.format("Für den Channel %s ist ein Passwort notwendig.", channelTo.getName()), 350, 130);
            Panel panel8 = new Panel();
            panel8.addComponent(new Label(String.format("Passwort: ")));
            panel8.addComponent(new TextField(20));
            popup.addPanel(panel8);
            Panel panel2 = new Panel();
            Button button = new Button("   OK   ");
            button.setStyled(true);
            panel2.addComponent(new Label("  ")); 
            button.enableAction();
            panel2.addComponent(button);
            Button button3 = new Button("Abbrechen");
            button3.setStyled(true);
            panel2.addComponent(button3);
            popup.addPanel(panel2);
                
            popup.setOpcode(ReceiveOpcode.PASSWORD.getValue(), String.format("%s:%s:%s", channelTo.getName(), channel.getName(), splitz[1]));
            client.send(popup.toString());
                
            client.send(PopupNewStyle.create("Falsches Passwort", "Falsches Passwort", String.format("#Das von ihnen verwendete _Passwort_ für den Channel _%s_ ist _nicht korrekt_.", channelTo.getName()), 450, 275));
            return;
        }
        
        client.joinChannel(channelTo);

        if (splitz[1].equals("no")) {
            channel.leave(client);
            client.leaveChannel(channel);
            client.send(PacketCreator.switchChannel(channel.getName(), channelTo.getName()));
            client.send(PacketCreator.updateChannelSettings(channelTo));
            client.send(PacketCreator.updateChannelBackground(channelTo));
        } else {
            client.send(PacketCreator.channelFrame(channelTo, client.getName(), client.newMessages.size()));
        }

        channelTo.join(client);
    }
}
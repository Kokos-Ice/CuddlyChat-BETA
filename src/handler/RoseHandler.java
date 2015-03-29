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

public class RoseHandler {
    public static void handle(String[] tokens, Client client) {
        if (tokens[1].equals("rose")) { 
            String name = tokens[3].trim();
            String text = tokens[4].trim();
            
            if(client.getRosesSend() == 0) {
            	client.send(PacketCreator.createRoseWindow(name, text));
               
                Popup popup = new Popup("Problem", "Problem", "#Du hast in dieser Woche bereits eine Rose versandt, mehr ist nicht möglich.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
            }
            
            if(name.equalsIgnoreCase(client.getName())) {
            	client.send(PacketCreator.createRoseWindow(name, text));
               
                Popup popup = new Popup("Problem", "Problem", "#Du kannst dir nicht selbst eine Rose schicken!", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
            }
            
            Client target = Server.get().getClient(name);
            boolean online = true;

            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(name);

                if(target.getName() == null) {
                	client.send(PacketCreator.createRoseWindow(name, text));
                    
                        Popup popup = new Popup("Problem", "Problem", String.format("#Den Nicknamen '%s' gibt es nicht.", name), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
                        
               }
            }
            
            if(online == true) {
            	client.send(PacketCreator.createRoseWindow(name, text));
              
                Popup popup = new Popup("Problem", "Problem", String.format("#%s ist im Chat online, Rosen können nur an Chatter vergeben werden, die offline sind.", target.getName()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
            }
            
            if(text.isEmpty()) {
            	client.send(PacketCreator.createRoseWindow(name, text));
               
                Popup popup = new Popup("Problem", "Problem", "#Ein Text für die Rose muss angegeben werden!.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
            }
            
            if(text.length() > 2000) {
            	client.send(PacketCreator.createRoseWindow(name, text));
             
                Popup popup = new Popup("Zu viele Zeichen", "Zu viele Zeichen", "#Deine Nachricht enthielt _zu viele Zeichen_, bitte kürze deine Rosennachricht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
            }
            
             Popup popup = new Popup("Rose ist auf dem Weg", "Rose ist auf dem Weg", String.format("Deine Rose wird %s schnellstens überreicht.", target.getName()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        
            
            
            
            client.setRosesSend((byte) (client.getRosesSend() - 1));
            Server.get().newRose(client, target.getName(), text, System.currentTimeMillis()/1000);
        }
    }        
}
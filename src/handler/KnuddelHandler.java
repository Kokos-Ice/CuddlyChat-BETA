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
import tools.popup.Button;
import tools.popup.Panel;
    import tools.popup.Popup;
import tools.popup.PopupNewStyle;
     
    public class KnuddelHandler {
        public static void handle(String[] tokens, Client client) {
            String[] lol = tokens[1].split("\\|");
            int knuddels = Integer.parseInt(lol[1]);
            String name = lol[0];
           
     
     
        Client target2;
                boolean online2 = true;
                    target2 = Server.get().getClient(client.getName());
                    if (target2 == null) {
                        online2 = false;
     
    }
    if(online2==false)
    {
    return;
    }
     
            if(client.getKnuddels() >= knuddels) {
               // client.send(Popup.create("Überweisung ausgeführt", "Überweisung ausgeführt", String.format("Du hast %s soeben erfolgreich %s überwiesen. ", lol[0], (knuddels == 1 ? "einen Knuddel" : String.format("%s Knuddels", knuddels))), 450, 275));  
                
                PopupNewStyle popup = new PopupNewStyle("Überweisung ausgeführt", "Überweisung ausgeführt", String.format("Du hast %s soeben erfolgreich %s überwiesen. ", lol[0], (knuddels == 1 ? "einen Knuddel" : String.format("%s Knuddels", knuddels))), 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);

                        client.send(popup.toString());
        	        
                
                Client target = Server.get().getClient(name);
                boolean online = true;
                if(target == null) {
                    online=false;
                    target = new Client(null);
                    target.loadStats(name);
                }
     
                String title = String.format("%s von %s", (knuddels == 1 ? "Einen Knuddel" : String.format("%s Knuddels", knuddels)), client.getName());
                String text = String.format("%s hat freundlicherweise...##_%s_##...an dich überwiesen!", client.getName(), (knuddels == 1 ? "einen Knuddel" : String.format("%s Knuddels", knuddels)));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), title, text, System.currentTimeMillis()/1000);
                client.deseaseKnuddels(knuddels);
     
                if(online) {
                    target.increaseKnuddels(knuddels);
                } else {
                    Server.get().query(String.format("update accounts set knuddels=knuddels+'%s' where name='%s'", knuddels, target.getName()));
                }
            }
        }
    }

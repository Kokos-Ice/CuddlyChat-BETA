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
import tools.PacketCreator;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class EditorHandler {

	public static void handle(String[] tokens, Client client) {
		String first = tokens[1].trim();
		String[] split = first.split("~");
		String typ = split[0];
		String what = split[1];
		String buttonText = tokens[2].trim();

		if(typ.equals("RIGHTS")) {
			if(buttonText.equals("?")) {
				client.send(PopupNewStyle.create("Rights-Editor - Hinweis", "Rights-Editor - Hinweis", String.format("In diesem Popup kann das Recht _°R°%s°r°_ entsprechend bearbeitet werden. Man kann beispielsweise einstellen welcher Rang oder welche Nicks dieses Recht haben.##_Bezeichnungen_:##°B°_|RZAHL|°r°_ (R = °>Status|/rights status-overview<°, ZAHL = 0-11, Beispiel: R1)#°B°_|SZAHL|°r°_ (S = °>Smiley|/rights smiley-overview<°, Beispiel: S5 (°>s/5...b.gif<°))#°B°_|NNICK|°r°_ (N = Nick, NICK = Chatnick, Beispiel: NCokaColaBoy)#°B°_|TMTEAM|°r°_ (TM = Teammitglied, TEAM = Teamname, Beispiel: TMAdmin)#°B°_|TLTEAM|°r°_ (TL = Teamleiter, TEAM = Teamname, Beispiel: TLAdmin)", what), 450, 275));
				return;
			}

			String rights = tokens[3].trim();
			
			client.send(PopupNewStyle.create("Rights-Editor - Änderungen gespeichert", "Rights-Editor - Änderungen gespeichert", String.format("Die Bezeichnungen für das Recht _%s wurden gespeichert_.", what), 450, 275));
			Server.permissions.put(what, rights);
			Server.get().query(String.format("UPDATE `permissions` SET `rights` = '%s' WHERE `permission` = '%s'", rights.replace("'", "\\'"), what));
		} else if(typ.equals("HZA")) {
			String hza = tokens[3].trim();
			Channel channel = Server.get().getChannel(what);
			StringBuilder nicks = new StringBuilder();
			
			if(!hza.isEmpty()) {
				for(String nick : hza.split(",")) {
					nick = nick.trim();
					
					if(nick.isEmpty()) {
						client.send(PacketCreator.createHZEditorWindow(channel.getName(), hza));
						client.send(PopupNewStyle.create("HZ-Editor - Fehler", "HZ-Editor - Fehler", "Du musst die Nicks mit einem Komma getrennt angeben.", 450, 275));
						return;
					}
					
					Client target = Server.get().getClient(nick);
                                        
                                        // James Benachrichtigung eingbeaut.  Sofern man jedoch falschen Nick als HZE einträgt, fliegt man
                                        
                                        Long time = System.currentTimeMillis()/1000;
                                        
                                        
                                
					// James Benachrichtigung gegebenfalls wieder entfernen bzw überarbeiten.
                                        
					if(target == null) {
						target = new Client(null);
						target.loadStats(nick);
                                                
						
						if(target.getName() == null) {
							client.send(PacketCreator.createHZEditorWindow(channel.getName(), hza));
							client.send(PopupNewStyle.create("HZ-Editor - Fehler", "HZ-Editor - Fehler", String.format("Der Nick _%s existiert nicht_.", nick), 450, 275));
							return;
						}
                                                
                                              
					}
					
                                          if (!channel.getHZ().contains("|"+target.getName()+"|"))  {      
                                          Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("HZA/E Rechte"), String.format("%s hat Dir soeben die HZA-Rechte im Channel %s gegeben!", client.getName(), channel.getName(), Server.get().getButler().getName()), time);
                                    }
					nicks.append("|").append(target.getName().trim()).append("|");
				
                                }
			}
			
			client.send(PopupNewStyle.create("HZ-Editor - Änderungen gespeichert", "HZ-Editor - Änderungen gespeichert", String.format("Die Änderungen des Channels %s wurden _gespeichert_.", channel.getName()), 450, 275));
			
                        
                        for(String nick : channel.getHZ().split("\\|")) { // alten
                            if (!nick.isEmpty()) {
                           
                                if (!nicks.toString().contains("|"+nick+"|")) {
                                  Client target = Server.get().getClient(nick);
                                    if(target == null) {
						target = new Client(null);
						target.loadStats(nick);
                                    }
                                    Long time = System.currentTimeMillis()/1000;
                                        
                                        
                                           Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("HZA/E Rechte"), String.format("%s hat Dich soeben als HZA im Channel %s entfernt!", client.getName(), channel.getName(), Server.get().getButler().getName()), time);
                                   
                                    
                                    
                                }
                                
                            }}
                        
                        channel.setHZ(nicks.toString());
                        
			}
		}
	}


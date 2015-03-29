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
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class MyChannelHandler {

	public static void handle(String[] tokens, Client client) {
		String what = tokens[1].trim();

		if(what.equals("mainMyChannel")) {
			StringBuilder errors = new StringBuilder();
			StringBuilder notes = new StringBuilder();
			String loginMessage = tokens[3].trim();
			String showGender = tokens[4].trim();
			String showAge = tokens[5].trim();
			String name = tokens[6].trim();
			
			String topic = tokens[7].trim();
			int topicLength = topic.length();
			boolean saveTopic = true;
			
			/*String info = tokens[8].trim();
			String design = tokens[9].trim();
			String enableLC = tokens[10].trim();
			String showName = tokens[11].trim();
			String daughterChannels = tokens[12].trim();
			String enablePassword = tokens[13].trim();
			String wordCheck = tokens[14].trim();
			String formatierung = tokens[15].trim();
			String fettKursiv = tokens[16].trim();
			String changeColor = tokens[17].trim();
			String size = tokens[18].trim();
			String password = tokens[19].trim();
			String minstatus = tokens[21].trim();
			String onlyGender = tokens[22].trim();
			String onlyAge = tokens[23].trim();
			String registerDays = tokens[25].trim();
			String butlerMute = tokens[26].trim();*/
			String cms = tokens[20].trim();
			String stammiMonths = tokens[24].trim();
			
			client.send(PopupNewStyle.create("Problem", "", "", 300, 200));
            
			if(name.isEmpty()) {
				client.send(PopupNewStyle.create("Problem", "Channelname wählen", "Bitte gib unter _Name_ einen Namen für den gewünschten Channel an.", 300, 200));
				return;
			}
			
			//Channelnamen: Anfangszeichen ungültig => Es sind nur Channelnamen zugelassen, welche mit einem _Buchstaben oder eine Zahl beginnen_.##Bitte wähle einen anderen Channelnamen.
			//Channelnamen: Unerlaubtes Zeichen => Du hast ein unerlaubtes Zeichen in deinem Channelnamen verwendet.#Alle _Buchstaben_ und _Zahlen_ sowie folgende Zeichen sind im Channelnamen erlaubt:#_+,-,@,ß, ,<,>,=,&_##Bitte wähle einen anderen Channelnamen.
			
			if(name.length() > 20) {
				client.send(PopupNewStyle.create("Problem", "Channelnamen: Zu lang", String.format("Der von dir gewünschte Channelname %s ist zu lang.#Bitte verwende einen Channelnamen, dessen Breite gut in die Channelliste passt.", name), 300, 200));
				return;
			}
			
			Channel channel = Server.get().getChannel(name);
			
			if(channel != null) {
				if(!channel.getOwner().isEmpty() && !channel.getOwner().equals(client.getName())) {
					client.send(PopupNewStyle.create("Problem", "Channelname schon vergeben", String.format("Der von dir gewünschte Channelname %s ist bereits an %s vergeben.##Wähl bitte einen anderen Channelnamen.", name, channel.getOwner()), 300, 200));
					return;
				}
				
				if(channel.getTemp() == 0 && channel.getOwner().isEmpty()) {
					client.send(PopupNewStyle.create("Problem", "Channelname schon vergeben", String.format("Der von dir gewünschte Channelname %s existiert bereits als Systemchannel bzw. ist dem Namen eines Systemchannels zu ähnlich.##Bitte wähle einen anderen Channelnamen.", channel.getName()), 300, 200));
					return;
				}
			}
			
			if(topicLength > 250) {
				errors.append("- Deine Angabe unter _Thema_ ist ").append(topicLength-250).append(" Zeichen zu lang");
			}

			if(channel == null) {
				if(client.getMyChannel() != null) {
					notes.append("- Dein alter Channelname ").append(client.getMyChannel()).append(" wurde wieder freigegeben.");
				
					Channel myChannel = client.getMyChannel();

					for(Client c : myChannel.getClients()) {
						c.logout("Channel wurde gesperrt.");
					}
					
					System.out.println("Channel gelöscht:" +myChannel.getName());
					Server.removeChannel(myChannel.getName());
					Server.get().query(String.format("delete from channels where name='%s'", myChannel.getName()));
				}
			
				notes.append("- Dein neuer Channel hat den Namen _").append(name).append("_.");
				notes.append("- Mach °R°_niemals°r°_ öffentlich in anderen Channels _Werbung_ für deinen Channel.");
				notes.append("- Du kannst in deinen Channel wechseln, indem du _/go ").append(name).append("_ eingibst.");
				notes.append("- Wenn du im Loginfenster bei \\\"Channel:\\\" ").append(name).append(" eingibst, gelangst du direkt in deinen Channel.");
			}
			
			String errorsToString = errors.toString();
			String notesToString = notes.toString();
			
			if(!errorsToString.isEmpty()) {
				client.send(PopupNewStyle.create("Problem", "Problem", String.format("Bei der Änderung deiner Channeldaten sind folgende Probleme aufgetreten:%s##Alle anderen Änderungen wurden _gespeichert_.#Einige optische Änderungen werden allerdings erst sichbar, nachdem man den Channel erneut betritt.", errorsToString), 450, 275));
				return;
			}
			
			client.send(PopupNewStyle.create("Problem", "Änderungen übernommen", String.format("Alle Änderungen wurden _gespeichert_.#Einige optische Änderungen werden allerdings erst sichbar, nachdem man den Channel erneut betritt.", notesToString.isEmpty() ? "" : String.format("Bitte beachte noch folgende _Hinweise_:%s", notesToString)), 300, 200));
			
			if(channel == null) {
				Server.get().createChannel(name, 0, client.getName(), 1, "Blaue Wolken", topic);
				
				channel = Server.get().getChannel(name);
			}
			
			if(saveTopic) {
				channel.setTopic(topic);
			}
			
			channel.setLoginMessage(Integer.parseInt(loginMessage));
			channel.setShowGender(Integer.parseInt(showGender));
			channel.setShowAge(Integer.parseInt(showAge));
			channel.setMcms(cms);
			channel.setMinstammimonths(Integer.parseInt(stammiMonths));
		}
	}
}

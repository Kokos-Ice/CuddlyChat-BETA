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

import java.util.ArrayList;
import java.util.List;

import starlight.Client;
import starlight.Poll;
import tools.Logger;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;

public class PollHandler {
    public static void handle(String[] tokens, Client client) {
    	String what = tokens[1];
    	
        if (what.startsWith("new~") && client.getRank() > 7) {
            String question = tokens[3].trim();
            String channel = "";
            String[] blabla = what.split("~");
            try { channel = blabla[1];  } catch(Exception ex) {}


            List<String> answers = new ArrayList<String>();
            
            for (String answer : tokens[4].split(";")) {
                answer = answer.trim();

                if (!answer.isEmpty()) {
                    boolean contains = false;

                    for (String a : answers) {
                        if (a.equalsIgnoreCase(answer)) {
                            contains = true;
                            break;
                        }
                    }

                    if (contains) {
                        continue;
                    }

                    answers.add(answer);
                }
            }

            if (question.isEmpty() || answers.size() < 2) {
                client.send(PopupNewStyle.create("Umfrage", "Problem", "Es müssen eine Frage und mindestens zwei verschiedene Antwortmöglichkeiten gegeben sein. Zweimal die selbe Antwort ist nicht möglich.", 450, 275));
            } else {
                new Poll(channel, question, answers).create();
            	Logger.handle(null, String.format("Umfrage von %s", client.getName()));
            }
        } else {
            Poll poll = Poll.get(Integer.parseInt(tokens[1]));

            if (poll != null) {
                poll.vote(client, tokens[2]);
            }
        }
    }
}

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

import java.util.Random;

import starlight.Client;
import tools.popup.Button;
import tools.popup.Panel;

import tools.popup.Popup;

public class CMTestHandler {
    
    public static void handle(String[] tokens, Client client) {
        client.setCmTestQuestion(client.getCmTestQuestion()+1);

    	if(client.getCmTestQuestion() == 21) {
    		if(client.getCmTestWrong() > 5) {
    			Popup popup = new Popup("Durchgefallen", "Durchgefallen", "#Du bist leider durchgefallen! °>sm_03.gif<°.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                       
                } else {
    			Popup popup = new Popup("Nominierung", "Nominierung", "#_Gratulation °>sm_00.gif<°_##Du hast den CM-Test erfolgreich bestanden!##Du hast soeben die Nominierung angenommen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        
                }
    		
            client.setCmTestQuestion(0);
            client.setCmTestWrong(0);
            return;
    	}
    	
        
    	String[] stuff = {"Wann muss ein CM nicht mit Amtsentzug rechnen?|Wenn der CM klar gegen die CM-Dokumentation verstößt~Wenn der CM sein Passwort weitergibt~Wenn das Vertrauensverhältnis unwiederbringlich zerstört ist~Wenn der Admin den CM nicht mag|0001", 
    			"Welche Antwort ist falsch:#wenn ich eine Frage zum Forum habe, dann...|schreibe ich die Frage als Fotokommentar bei Andre~gebe ich im Chat \"/h forum-team\" ein~frage ich ein Forum-Teammitglied~suche ich die Antwort in der Hilfe des Forums|0001", 
    			"Welcher Nickname muss aufgrund seiner eindeutigen, anzüglichen Klangweise auf jeden Fall von den Admins gesperrt werden?|Fescher Frank 14~SpassIstWasIhrDrausMacht~KleinPeter hat einen Ständer~NieWiederAllein|0010", 
    			/*"Woran erkennst du 'sichere' Seiten, auf denen du dir Smileys, Rosen und Knuddels kostenlos auf deinen Nick laden kannst?|Für die Seite machen so viele Leute Werbung, die muss toll und sicher sein!~Der Hintergrund ist im Knuddels-Lila gestaltet.~Solche Seiten gibt es nicht. Wenn man sich hier einträgt, ist der Nick  schnell weg - gestohlen.|", 
    			"Welche Aussage zum Mute ist richtig?|Ein Globalmute verhindert nicht das Schreiben von /m's.~Ein CM kann einen Globalmute mit /mute !NICK:GLOBAL aufheben.~Man muss einen Chatter entmuten, der von James gemutet wurde.~Ein Chatter, der gemutet wurde, kann nicht mehr geküsst werden.|", 
    			"Ein Chatter beleidigt dich dauernd privat. Welche dieser möglichen Reaktionen wäre falsch?|Die /pp Funktion anwenden und das Fenster minimieren.~Einfach überlesen.~Wenn es extrem ist, einen Notruf über das neue Notrufsystem machen und den  User melden.~Am besten gleich das CM-Amt abgeben und NetChat verlassen.|", 
    			"Welche der folgenden Aussagen steht nicht in der CM-Doku?|Man muss mindestens 20 Notrufe am Tag annehmen (außer man ist nicht  online).~Erh.lt man im Chat ein Notruf-Angebot von James, so soll man dies möglichst  immer annehmen.~Wenn man einen Notruf ablehnt, muss das Notrufsystem neu berechnen an wen  der Notruf gehen soll.~Man darf einen Notruf ablehnen, wenn man einmal wirklich keine Zeit hat.|", 
    			"Welche der folgenden Aussagen zur CM-Wahl ist nicht richtig?|In Spielechanneln dürfen sich nur Stammis zum CM nominieren lassen, die den  für den Channel nötigen Spielerang haben.~Wenn mir das CM-Amt entzogen wurde, darf ich mich bei der nächsten CM-Wahl  mit keinem anderen Nick nominieren lassen.~Wenn mir als Admin das Amt entzogen wurde, dann darf ich mich für den Rest  der Adminperiode nicht zum CM nominieren lassen.~Alle Wahlregeln der CM-Wahl gelten jeweils nur für einen Nick.|", 
    			"Welche Aussage zu den gemeldeten Inhalten eines Notrufes ist nicht falsch?|Zusätzlich zum gesicherten Inhalt des Notrufes lässt man sich einen Screen  geben, um zu kontrollieren, ob der Notruf gefälscht wurde.~Die gemeldeten Inhalte können vom Ankläger noch vor dem Absenden des  Notrufs bearbeitet werden, damit er private Texte löschen kann.~Die im Notrufsystem gemeldeten Inhalte sind gesicherte Beweise.~Weil auch Notrufe für administrative Teams an die CM gehen, müssen diese an  ein Teammitglied weitergeleitet werden.|", 
    			"Zu wessen Aufgaben gehört die Auswertung der Notrufstatistiken?|~James~Stammis~alle Chatter~Teamleiter|",
    			"Welches ist eine falsche zeitliche Reihenfolge für eine korrekte Notrufbearbeitung?_#(Es können in den Lösungen auch Schritte ausgelassen sein)|", 
    			"Welches der folgenden Passwörter erscheint dir besonders unsicher?|", 
    			"Welche Aussage zur his-Funktion ist richtig?|", 
    			"Bei welcher dieser Aussagen würdest du nicht eingreifen?|", 
    			"Welche dieser Aussagen ist extremistisch?|", 
    			"Wie gelangt man auf die Bearbeitungsseite der Adminnotrufe?|", 
    			"Welche Aussage zur CMC-Funktion ist falsch?|", 
    			"Wann solltest Du Dich an Deinen Hauptzuständigen Admin wenden?|", 
    			"Welche Aussage ist falsch, hinsichtlich des Verhaltens gegenüber anderen Chattern?|", 
    			"Welche Aussage zum Channellock ist falsch?|"*/};
    	
    	Random zufall = new Random();
    	String[] lal = stuff[zufall.nextInt(stuff.length)].split("\\|", 2);
    	String question = lal[0];
    	String xd = lal[1];
    	String[] p = xd.split("\\|");
    	String loesung = "0001";

    	if(client.getCmTestQuestion() > 1) {
    		StringBuilder toke = new StringBuilder();
    		int xx = 0;
    	
    		for(String x : tokens) {
    			if(xx>3) {
    				toke.append(x);
    			}
    		
    			xx++;
    		}
    		
    		if(!toke.toString().trim().equals(loesung.trim())) {
    			client.setCmTestWrong(client.getCmTestWrong()+1);
    		}
    		
    		System.out.println("Eingabe: "+toke.toString().trim()+", Lösung: "+loesung.trim());
    	}
    	
    	StringBuilder test = new StringBuilder();
    	
    	test.append("k\0CM-Test ");
    	test.append(client.getCmTestQuestion());
    	test.append(" / 20õsivõ467837õf\0\0\0h¾¼ûaãEl         õcgFh¾¼ûãWl         õcgFh¾¼ûãCpBNpBNl õcgFh¾¼ûãClCM-Test ");
    	test.append(client.getCmTestQuestion());
    	test.append(" / 20õblgSf\0\0\0hååÿãSl õcgFh¾¼ûããCc#Frage:##_");
    	test.append(question);
    	test.append("_õs^\0Èf\0\0\0h¾¼ûnipics/cloudsblue.gifõ\0ãSpBNl ");
    	
    	for(String lol : p[0].split("~")) {
    		test.append("õgDh¾¼ûãWxsflõf\0\0\0h¾¼ûgFrBãCl");
    		test.append(lol);
    		test.append("õgQf\0\0\0h¾¼ûãSpBNl");
    	}
    	
    	test.append(" õgFh¾¼ûãSpFb   Ok   õsdpgQf\0\0\0h¾¼ûããããããããã");
    	
    	client.send(test.toString());
    }
}

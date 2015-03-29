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
 * 
 */

/*
 * DiceMaster Erweitert durch djchrisnet
 */


package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tools.KCodeParser;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.Toolbar;

public class DiceMaster implements Game {
    private Channel channel;
    private static String[] dmNoWin = {"Schade!", "Das war wohl nichts! ", "Nichts!", "Das Pech verfolgt dich..", "Gar Nix!", "Leider nichts!"};
    private static String[] dmWin = {"Gratuliere!", "Du darfst dich freuen!", "Voll ins Schwarze!", "BÄÄÄMM!!!", "WoooW!!"};
    
    public DiceMaster(Channel channel) {
        this.channel = channel;
    }

    public boolean parsePublicMessage(String message, Client client) {
	return true;
    }

    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
        return true;
    }

    public void onLeave(Client client) {
    }

    public void onJoin(Client client) {
    }

    public boolean parseCommand(String message, Client client) {
        String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
        String cmd = command.toLowerCase();
        String arg = "";
        Random zufall = new Random();
        
        if (message.length() > cmd.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1);
        }

        int Anzahlspe = Server.countChars(arg, '°');
        
        if (Anzahlspe == 1) {
            String[] argus = arg.split("°", 2);
            arg = argus[0];
        }
               
        if(!cmd.equals("dm")) {
            return true;
        }
        
        if(!client.hasPermission("cmd.dm")) {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            return false;
        }
        
        if(client.getSpielsperre() != 0) {
            client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
            return false;
        }

        if(Server.get().checkCcm(client.getName(), channel, 3)) {
            client.sendButlerMessage(channel.getName(), "Wenn du gemutet bist, kannst du nicht spielen.");
            return false;
    	}
        
        arg = KCodeParser.escape(arg);

        if(arg.isEmpty()) {
            client.sendButlerMessage(channel.getName(), "Falsche Anwendung.");
            return false;
        }
        
        if(!Server.get().isInteger(arg)) {
            client.sendButlerMessage(channel.getName(), "Bitte nutze die Funktion wie folgt: /dm RUNDEN");
            return false;
        }
        
        int w = Integer.parseInt(arg);
        if (w <= 0) {
             client.sendButlerMessage(channel.getName(), "Bitte nutze die Funktion wie folgt: /dm RUNDEN");
           
            return false;
        }
        int k = (w*channel.getMinknuddels());

        if(k > client.getKnuddels()) {
            client.sendButlerMessage(channel.getName(), String.format("Du hast nicht genug Knuddels.", client.getKnuddels()));
        } else if(w > 5000){
            client.sendButlerMessage(channel.getName(), "Du kannst maximal _5000 Runden_ auf einmal spielen!");
        } else {
            client.sendButlerMessage(channel.getName(), String.format("Dir wurden _%s Knuddels_ abgezogen. Viel Glück!", k));
            this.runGame(client, k, w);
        }
        return false;
    }
    
    private boolean runGame(Client client, int e, int w){
        Random zufall = new Random();
        int g = 0;
                
        client.deseaseKnuddels(e);
            
        e = (e/w);
        int summe = 0;
        int fw = 0;
        String erg = "";
        ArrayList<Integer> store = new ArrayList<>();

        for (int r = 1; r <= (w+fw); r++){
            int z = zufall.nextInt(500)+1;
            erg += z;
            store.add(z);
            if(z == 110 || z == 120 || z == 130 || z == 140 || z == 160 || z == 170 || z == 180 || z == 190 || z == 310 || z == 320 || z == 330 || z == 340 || z == 360 || z == 370 || z == 380 || z == 390) {
                erg += "> ";
                fw++;
            } else if(!(r == (w+fw))) {
                erg += ", ";
            }
            summe += z;
        }

        channel.broadcastAction(">", String.format("°>_h%s|/serverpp \"|/w \"<° rollt einen Würfel...#%sW500: %s = _%s_", client.getName(), w, erg, summe));

        for (int z : store){

            if(z == 1 || z == 250 || z == 500) {
                g += (e*20);
            } else if(z == 400 || z == 410 || z == 415 || z == 420 || z == 425 || z == 430 || z == 435 || z == 440 || z == 445 || z == 460 || z == 465 || z == 470 || z == 475 || z == 480 || z == 485 || z == 490 || z == 495) {
                g += (e*10);
            } else if(z == 200 || z == 210 || z == 215 || z == 220 || z == 225 || z == 230 || z == 235 || z == 240 || z == 260 || z == 265 || z == 270 || z == 280 || z == 290 || z == 300) {
                g += (e*6);
            } else if(z == 11 || z == 22 || z == 33 || z == 44 || z == 66 || z == 77 || z == 88 || z == 99 || z == 111 || z == 222 || z == 333 || z == 444) {
                g += (e*4);
            } else if(z == 10 || z == 15 || z == 20 || z == 25 || z == 30 || z == 35 || z == 40 || z == 45 || z == 50 || z == 55 || z == 60 || z == 65 || z == 70 || z == 75 || z == 80 || z == 85 || z == 90 || z == 95) {
                g += (e*2);
            } else if(z == 110 || z == 120 || z == 130 || z == 140 || z == 160 || z == 170 || z == 180 || z == 190 || z == 310 || z == 320 || z == 330 || z == 340 || z == 360 || z == 370 || z == 380 || z == 390) {
                   // Freiwürfe
            }
        }
        e = (e*w);
                
        if(g > 0){
            client.increaseKnuddels(g);
        }
               
        if(e > g) {
            client.sendButlerMessage(channel.getName(), String.format("%s Du hast einen °R°_Verlust von %s Knuddels°r°_ gemacht.", dmNoWin[zufall.nextInt(dmNoWin.length)], e-g));
        } else if(e == g){
            client.sendButlerMessage(channel.getName(), String.format("Du erhälst deinen Einsatz von °W°_%s Knuddels°r°_ zurück.", e));
        } else {
            client.sendButlerMessage(channel.getName(), String.format("%s Du hast einen °G°_Gewinn von %s Knuddels°r°_ gemacht.", dmWin[zufall.nextInt(dmWin.length)], g-e));
        }
      return true;
    }
}
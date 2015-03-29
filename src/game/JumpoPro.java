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



package game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.GameUtilities;

public class JumpoPro implements Game, Runnable {
    private Thread thread;
    private Channel channel;
    private int sentenceLength, id;
    private boolean playing = false;
    private boolean solved = false;
    private long start;
    private String loesung;
    private DecimalFormat df;
    private List<Client> activeClients;
    Random random = new Random();
    private String[] JT = {"1~runter links hoch links runter runter", "2~runter rechts runter rechts", "3~rechts rechts hoch links hoch", "4~rechts hoch links hoch", "5~hoch hoch links runter links hoch", "6~rechts rechts hoch hoch links runter links hoch", "7~links runter rechts runter", "8~runter rechts", "9~runter links runter", "10~runter links hoch", "11~hoch rechts runter", "12~rechts hoch", "13~hoch hoch", "14~rechts rechts", "15~links hoch rechts hoch", "16~runter links hoch links runter runter rechts rechts", "17~runter rechts rechts", "18~rechts rechts runter", "19~runter runter rechts", "20~rechts runter runter"};
	
    private String[] jumpoPlayingSentences = new String[]
    {
        "Jumpo läuft bereits.",
        "Es läuft bereits Jumpo.",
        "Wir spielen schon Jumpo."
    };

    private String getRandomJumpoPlayingSentence() {
        return jumpoPlayingSentences[random.nextInt(jumpoPlayingSentences.length)];
    }
    
    public JumpoPro(Channel channel) {
        this.channel = channel;
        playing = false;

        df = new DecimalFormat("0.00");
        Init();
    }

    private void Init() {
        activeClients = new ArrayList<>();
        thread = new Thread(this);
        playing = false;
        solved = false;
    }

    private void StartRound() {
        String lal = JT[random.nextInt(JT.length)];
		id = Integer.parseInt(lal.split("~")[0]);
		loesung = lal.split("~")[1];

        channel.broadcastButlerMessage(String.format("°18BB°_Zeige dem Knuddel den Weg bevor es zu spät ist:##°>center<>jumpo-pro/%s...border_3.gif<°#°>left<°", id));
        playing = true;
        start = System.currentTimeMillis();

        CheckTime();
    }

    public boolean parsePublicMessage(String message, Client client) {
        String msg = message.toLowerCase();

        if (msg.contains(Server.get().getButler().getName().toLowerCase()) && msg.contains("jumpo")) {
            if (!playing) {
                channel.broadcastMessage(msg, client, true);
                thread.start();
                return false;
            } else {
                channel.broadcastMessage(message, client, true);
                channel.broadcastButlerMessage(getRandomJumpoPlayingSentence());
                return false;
            }
        }

        if (playing) {
            if (!activeClients.contains(client)) {
                activeClients.add(client);
            }

            if (msg.equals(loesung) && client.getSpielsperre() == 0) {
                float time = Float.parseFloat(df.format(GameUtilities.totalTime(start)).replace(",", "."));
                float points = Float.parseFloat(df.format(((float)activeClients.size() +5+ (float)sentenceLength + (time + 4.439F)) / time).replace(",", "."));

                channel.broadcastMessage(msg, client, true);
                channel.broadcastButlerMessage(String.format("°18BB°_Der Knuddel hat es dank %s nach %s Sekunden ins Ziel geschafft! %s erhält %s Jumpo Punkte.",client.getName(), time, client.getGenderLabel(), points));
                    
                client.increaseJumpoPoints(points);
                
                playing = false;
                solved = true;
                start = 0;
                    
                return false;
            } 
            
            if(client.getSpielsperre() != 0) {
                channel.broadcastMessage(msg, client, true);
            	client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
            	return false;
            }
        }

        return true;
    }

    @Override
    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
        if (targets.contains(Server.get().getButler())) {
            client.sendPrivateMessage(targets, message, channel, true);
            client.sendButlerMessage(this.channel.getName(), "Wegen Problemen mit Bots werden nur noch öffentliche Nachrichten akzeptiert.");
            return false;
        }

        return true;
    }

    @Override
    public void onLeave(Client client) {
    }

    @Override
    public void run() {
        StartRound();
        Init();
    }

    private void CheckTime() {
        while (playing && !solved) {
            if (solved) {
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }

            if (!solved && start > 0 && GameUtilities.totalTime(start) > 40) {
                solved = true;
                playing = false;
                start = 0;

                channel.broadcastButlerMessage("°BB18°_Verloren! Der Knuddel konnte es leider nicht zum Ziel schaffen.");
                break;
            }
        }
    }

    @Override
	public void onJoin(Client client) {
	}

    @Override
	public boolean parseCommand(String command, Client client) {
		return false;
	}
}
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.GameUtilities;

public class WordMixEnglish implements Game, Runnable {
    private Map<String, String> wrongAnswers; // Sentence, UserName
    private Thread thread;
    private Channel channel;
    private String rawSentence;
    private String mixedSentence;
    private int sentenceLength;
    private boolean playing = false;
    private boolean solved = false;
    private long start;
    private DecimalFormat df;
    private List<Client> activeClients;

    private String[] startMixSentences = new String[]
    {
        "Hier kommt der nächste, durchgemixte Satz eines Klassikers:",
        "Nennt mir die Lösung für das folgende, wortvermixte Rätsel:",
        "Folgende gemixte Wörter sind zu entschlüsseln:"
    };

    private String[] solvedSentences = new String[]
    {
        "_°>_h%s|/serverpp \"|/w \"<°_ hat gewonnen, °B°_'%s'_°r° war korrekt!",
        "_°>_h%s|/serverpp \"|/w \"<°_, °B°_'%s'_°r° ist die richtige Lösung!",
        "Tatsächlich ist °B°_'%s'_°r° richtig, herzlichen Glückwunsch, _°>_h%s|/serverpp \"|/w \"<°_."
    };

    private String[] notSolvedSentences = new String[]
    {
        "Welch blamable Leistung! _'%s'_ war die Lösung.",
        "Niemand hat die richtige Lösung _'%s'_ erraten. Schwach!"
    };

    private String[] mixPlayingSentences = new String[]
    {
        "Mix läuft bereits.",
        "Es läuft bereits Mix.",
        "Wir spielen schon Mix."
    };

    private String getRandomStartMixSentence() {
        return startMixSentences[new Random().nextInt(startMixSentences.length)];
    }

    private String getRandomSolvedSentence() {
        return solvedSentences[new Random().nextInt(solvedSentences.length)];
    }

    private String getRandomNotSolvedSentence() {
        return notSolvedSentences[new Random().nextInt(notSolvedSentences.length)];
    }

    private String getRandomMixPlayingSentence() {
        return mixPlayingSentences[new Random().nextInt(mixPlayingSentences.length)];
    }
    
    public WordMixEnglish(Channel channel) {
        wrongAnswers = new HashMap<String, String>();
        this.channel = channel;
        playing = false;

        df = new DecimalFormat("0.00");
        Init();
    }

    private void Init() {
        wrongAnswers = new HashMap<String, String>();
        activeClients = new ArrayList<Client>();
        thread = new Thread(this);
        playing = false;
        solved = false;
    }

    private void StartRound() {
       rawSentence = GameUtilities.getRandomWordMixSentence();
        mixedSentence = GameUtilities.mixSentence2(rawSentence);
        sentenceLength = rawSentence.split(" ").length;

        channel.broadcastButlerMessage(String.format("%s:#_%s _", getRandomStartMixSentence(), mixedSentence));
        playing = true;
        start = System.currentTimeMillis();

        CheckTime();
    }

    public boolean parsePublicMessage(String message, Client client) {
        String msg = message.toLowerCase();

        if (msg.contains(Server.get().getButler().getName().toLowerCase()) && msg.contains("mix")) {
            if (!playing) {
            	try {
            		thread.start();
            	} catch(IllegalThreadStateException ex){
            	}
            } else {
                channel.broadcastMessage(message, client, true);
                channel.broadcastButlerMessage(getRandomMixPlayingSentence());
                return false;
            }
        }

        if (playing && GameUtilities.isNumberic(msg)) {
            if (msg.length() == sentenceLength) {
                if (!activeClients.contains(client)) {
                    activeClients.add(client);
                }

                if(client.getSpielsperre() != 0) {
                	client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
                	return false;
                }
                
                List<Character> twiceChar = GameUtilities.getTwiceChar(msg);
                if (twiceChar.size() > 0) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < msg.length(); i++) {
                       char key = msg.charAt(i);

                       if (twiceChar.contains(key)) {
                           builder.append(String.format("_%s§", key));
                           continue;
                       }

                       builder.append(key);
                    }
                    
                    client.sendButlerMessage(this.channel.getName(), String.format("Sie haben beim Versuch, den Satz zu lösen, doppelte Indexzeichen verwendet: %s", builder.toString()));
                    return false;
                }

                List<Character> falseKeys = GameUtilities.falseKeys(msg);
                if (falseKeys.size() > 0) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < msg.length(); i++) {
                       char key = msg.charAt(i);

                       if (falseKeys.contains(key)) {
                           builder.append(String.format("_%s§", key));
                           continue;
                       }

                       builder.append(key);
                    }

                    client.sendButlerMessage(this.channel.getName(), String.format("Sie haben beim Versuch, den Satz zu lösen, falsche Indexzeichen verwendet: %s", builder.toString()));
                    return false;
                }

                String userSentence = GameUtilities.makeSentence(mixedSentence, msg);

                if (wrongAnswers.containsKey(userSentence)) {
                    client.sendButlerMessage(this.channel.getName(), String.format("Diese falsche Antwort hat _°>_h%s|/serverpp \"|/w \"<°_ bereits gegeben.", wrongAnswers.get(userSentence).replace("<", "\\<")));
                    return false;
                }

                if (userSentence.equals(rawSentence)) {
                    float time = Float.parseFloat(df.format(GameUtilities.totalTime(start)).replace(",", "."));
                    float points = Float.parseFloat(df.format(((float)activeClients.size() + (float)sentenceLength + (time + 4.439F)) / time).replace(",", "."));

                    client.increaseWordMixPoints(points);

                    channel.broadcastMessage(rawSentence, client, true);
                    String solvedSentence = getRandomSolvedSentence();
                    Object[] arr = new Object[] { client.getName().replace("<", "\\<"), rawSentence };
                    if (solvedSentence.startsWith("Tatsächlich")) {
                        Collections.reverse(Arrays.asList(arr));
                    }

                    channel.broadcastButlerMessage(String.format("%s (_%s_ Sekunden, _%s_ Punkte)", String.format(solvedSentence, arr), df.format(time), (points < 5 ? df.format(points) : String.format("°BB°%s°r°", df.format(points)))));
                    playing = false;
                    solved = true;
                    start = 0;

                    float rekord = WordMixRecord.getPoints();

                    if (rekord < points) {
                        channel.broadcastButlerMessage(String.format("°RR°_°>_h%s|/serverpp \"|/w \"<°_ hat gerade einen neuen Tagesrekord geschafft! Die Tagesbestleistung könnt ihr mit _°>/wordmix|\"<°_ abfragen.", client.getName().replace("<", "\\<")));
                        
                        GameUtilities.Add(client.getName(), points, rawSentence, time);
                    }
                    
                    return false;
                } else {
                    wrongAnswers.put(userSentence, client.getName());
                    channel.broadcastMessage(userSentence, client, true);

                    if (GameUtilities.almostCorrect(rawSentence, userSentence)) {
                        client.sendButlerMessage(this.channel.getName(), String.format("'_%s_' war fast richtig, °>_h%s|/serverpp \"|/w \"<°!", userSentence.toLowerCase(), client.getName().replace("<", "\\<")));
                    }
                    
                    return false;
                }
            }
        }

        return true;
    }

    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
        if (targets.contains(Server.get().getButler())) {
            client.sendPrivateMessage(targets, message, channel, true);
            client.sendButlerMessage(this.channel.getName(), "Wegen Problemen mit Bots werden nur noch öffentliche Nachrichten akzeptiert.");
            return false;
        }

        return true;
    }

    public void onLeave(Client client) {
    }

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

                channel.broadcastButlerMessage(String.format(getRandomNotSolvedSentence(), rawSentence));
                break;
            }
        }
    }

	public void onJoin(Client client) {
	}

	public boolean parseCommand(String command, Client client) {
		return false;
	}
}
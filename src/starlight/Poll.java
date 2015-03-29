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



package starlight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import tools.KCodeParser;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import tools.popup.PopupNewStyle;

public class Poll {
    private final static Map<Integer, Poll> polls;
    private static int runningId;
    private final Map<String, String> votes;
    private boolean closed;
    private String question, channel;
    private List<String> answers;
    private int id;
    private static DecimalFormat df = new DecimalFormat("0.00");

    static {
        polls = new HashMap<Integer, Poll>();
    }
    
    public Poll(String channel, String question, List<String> answers) {
        votes = new HashMap<String, String>();
        closed = false;
        this.question = question;
        this.answers = answers;
        this.channel = channel;
    }

    public static Poll get(int id) {
        synchronized (polls) {
            return polls.get(id);
        }
    }

    public void create() {
        synchronized (polls) {
            runningId++;
            id = runningId;
            polls.put(id, this);
        }
        
        Popup popup = new Popup("Umfrage", "Umfrage", String.format("_Info:_ Diese Umfrage ist anonym, nur die Statistik wird gespeichert, keine nickbezogenen Daten.##_%s", question), 450, 275);
        Panel panel = new Panel();
        popup.setButtonFontSize(12);
        for (String answer : answers) {
            Button button = new Button(answer);
            button.setStyled(true);
            button.enableAction();
            panel.addComponent(button);
        }
        
        popup.setOpcode(ReceiveOpcode.POLL.getValue(), String.valueOf(id));
            Button button = new Button("Close");
            button.setStyled(true);
            button.enableAction();
            panel.addComponent(button);
        popup.addPanel(panel);
        String poll = popup.toString();
        
       if(channel.isEmpty()) {
       // Jörns abfrage aktiviert
       //  if(channel == null) {
        	for (Client client : Server.get().getClients()) {
                client.send(poll);
            }
                
        } else {
            final Channel ch = Server.get().getChannel(channel);

        	for (Client client : ch.getClients()) {
            	client.send(poll);
        	}
        }

        new Timer().schedule(new TimerTask() {
            public void run() {
                closed = true;
                long time = System.currentTimeMillis()/1000;

                if (votes.isEmpty()) {
                    return;
                }

                StringBuilder result = new StringBuilder();
                SimpleDateFormat stunden = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String h = stunden.format(new Date());
                StringBuilder text = new StringBuilder();
                
                int gesamt = 0;
                
               if(!channel.isEmpty()) {
              // Jörns abfrage aktiviert
               // if(channel == null) {
                    final Channel ch = Server.get().getChannel(channel);
                    
                	gesamt = ch.getClients().size()-1;
                } else {
                	gesamt = Server.get().getClients().size()-1;
                }
                
                result.append(h).append(":#").append(votes.size()).append("/").append(gesamt).append(" Stimmen abgegeben#_").append(question).append("_#");
                
                for (String answer : answers) {
                    float count = 0;

                    for (String a : votes.values()) {
                        if (a.equals(answer)) {
                            count++;
                        }
                    }
                    
                    float prozent = 0;
                    prozent = count/votes.size()*100; 
                    result.append("#").append(KCodeParser.escape(answer)).append(": ").append(df.format(prozent).replace(",", ".").replace(".00", "")).append("%");
                    text.append(KCodeParser.escape(answer)).append(": ").append(df.format(prozent).replace(",", ".").replace(".00", "")).append("%|");
                    
                }

                
                if(!channel.isEmpty()) {
                    final Channel ch = Server.get().getChannel(channel);
                    
                	for (Client c : ch.getClients()) {
                  	 Popup popup2 = new Popup("Umfrage - Ergebnis", "Umfrage - Ergebnis", result.toString(), 450, 275);
        	         Panel panel2 = new Panel();
                         Button buttonMessage4 = new Button("   OK   ");
                         buttonMessage4.setStyled(true);
                         panel2.addComponent(buttonMessage4);
                         popup2.addPanel(panel2);
                         popup2.setLaufbahn(1);
                         c.send(popup2.toString());
                        }
                	
                	ch.broadcastButlerMessage(result.toString());
                } else {
                    Object[] participants = votes.keySet().toArray();
                    String winner = (String) participants[new Random().nextInt(participants.length)];
                    Client client = Server.get().getClient(winner);
                    boolean online = true;
                    
                    if (client == null) {
                    	online=false;
                    	client = new Client(null);
                    	client.loadStats(winner);
                    }
                    
                    if(online) {
                    	client.increaseKnuddels(25);
                        client.setHighlights(String.format("%s|%s 25 Knuddel bei einer Umfrage gewonnen|", client.getHighlights(), Server.get().timeStampToDate(time)));
         
                    } else {
                    	Server.get().query(String.format("update accounts set knuddels=knuddels+'25' where name='%s'", client.getName()));
                        Server.get().query(String.format("update accounts set highlights='%s|%s 25 Knuddel bei einer Umfrage gewonnen|' where name='%s'", client.getHighlights(), Server.get().timeStampToDate(time), client.getName()));
            
                    }
                    
                	Server.get().newMessage(Server.get().getButler().getName(), winner, "Umfrage", String.format("Hallo %s,##du hast soeben bei der Umfrage 25 Knuddels abgesahnt.##Liebe Grüße,#"+Server.get().getButler().getName(), winner), time);
                    
                    result.append("###Gewinner der Verlosung:#_°B>").append(winner.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_ ...hat 25 Knuddels abgesahnt!");
                    
                	for (Client c : Server.get().getClients()) {
                    Popup popup = new Popup("Umfrage - Ergebnis", "Umfrage - Ergebnis", result.toString(), 450, 275);
        	         Panel panel = new Panel();
                         Button buttonMessage3 = new Button("   OK   ");
                         buttonMessage3.setStyled(true);
                         panel.addComponent(buttonMessage3);
                         popup.addPanel(panel);
                         popup.setLaufbahn(1);
                         c.send(popup.toString());
                         
                        }
                	String stimmen = String.format("%s/%s", votes.size(), gesamt);
                	
                	Server.statistik.put(question, new String[] {text.toString(), stimmen, String.valueOf(System.currentTimeMillis()/1000)});
                	
                	Server.get().query(String.format("INSERT INTO `statistik` SET `uhrzeit` = '%s', `question` = '%s', `stimmen` = '%s', `text` = '%s'", (System.currentTimeMillis()/1000), question, stimmen, text.toString()));
                }
            }
        }, 30 * 1000);
    }

    public void vote(Client client, String answer) {
        synchronized (votes) {
            if (!votes.containsKey(client.getName()) && answers.contains(answer)) {
                if (closed) {
                    client.send(PopupNewStyle.create("Problem", "Schlafmütze", "Du hast dir zu lange Zeit gelassen, um auf das letzte Fenster zu reagieren.", 450, 275));
                } else {
                    votes.put(client.getName(), answer);
                }
            }
        }
    }
}

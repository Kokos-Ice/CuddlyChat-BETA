/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.popup.Popup;



public class Fifty implements Game, Runnable {
    private final Map<String, MafiaPlayer> players;
    private Channel channel;
    private Thread thread;
    private byte status;

    public Fifty(Channel channel) {
        players = new HashMap<String, MafiaPlayer>();
        this.channel = channel;
        init();
    }

    private void init() {
        players.clear();
        thread = new Thread(this);
        status = 0;
    }

    private void action() {
        if (status == 1 || status == 2) {
            for (int i = 0; i < 20; i++) {
                if (status == 3) {
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }

        if (status == 1) {
            synchronized (players) {
                if (players.size() < 5) {
                    channel.broadcastButlerMessage(String.format("Noch können wir leider nicht anfangen. Wir haben _erst %s Spieler_, brauchen aber _mindestens 3 Spieler_. Wer kein Angsthase ist, meldet sich mit °BB°_°>/d +|/d +<°§ an.", players.size()));
                    status++;
                } else {
                    status = 3;
                }
            }

            action();
        } else if (status == 2) {
            synchronized (players) {
                if (players.size() < 5) {
                    channel.broadcastButlerMessage(String.format("°RR20° _Fifty! findet leider nicht statt.§#Es haben sich lediglich %s Spieler angemeldet (3 Spieler wären mindestens notwendig)", players.size()));
                    status = 0;
                } else {
                    status++;
                    action();
                }
            }
        } else if (status == 3) {
            synchronized (players) {
            	StringBuilder nicks = new StringBuilder();
            	int number = 1;
            	
            	for(String client : players.keySet()) {
            		nicks.append(number!=1?", ":"").append("°>_h").append(client).append("|/serverpp \"|/w \"<°");
            		number++;
            	}
            	
            	channel.broadcastButlerMessage(String.format("%s (insgesamt x Spieler) würfeln jetzt um den Sieg:#1. Platz 10 Fifty! Punkte#2. Platz: 6 Fifty! Punkte##(Aktuell im Jackpot 50 Fifty! Punkte)", nicks.toString()));
            }
        }
    }

    public boolean parsePublicMessage(String message, Client client) {
        if (status > 2 && client.getRank() < 5) {
            client.sendButlerMessage(channel.getName(), "In diesem Channel kannst du nicht öffentlich sprechen.");
            return false;
        }

        String msg = message.toLowerCase();

        if (msg.contains(Server.get().getButler().getName().toLowerCase()) && msg.contains("fifty")) {
            channel.broadcastMessage(message, client, true);

            if (status == 0) {
                status++;
                thread.start();

               

                channel.broadcastButlerMessage("°RR20°_Die Anmeldung für Fifty läuft...§##Wer bei unserem Würfelklassiker _Fifty!_ (3 - 20 Spieler) mitspielen möchte, gibt bitte jetzt °BB°_°>/d +|/d +<°§ ein.");
            } else {
                channel.broadcastButlerMessage("Fifty! läuft bereits.");
            }

            return false;
        }

        return true;
    }

    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
        if (status > 2 && client.getRank() < 5) {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            return false;
        }

        if (targets.contains(Server.get().getButler())) {
            if (status == 1 || status == 2) {
                client.sendPrivateMessage(targets, message, channel, true);

                synchronized (players) {
                    if (players.containsKey(client.getName())) {
                        client.sendButlerMessage(channel.getName(), "Du bist bereits angemeldet.");
                    } else {
                    	if(client.getSpielsperre() != 0) {
                    		client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
                    	} else {
                    		players.put(client.getName(), new MafiaPlayer(client.getName()));
                        
                    		client.sendButlerMessage(channel.getName(), "Du bist nun für die neue Fifty!Runde angemeldet.");

                        	if (players.size() == channel.getSize() - 1) {
                            	status = 3;
                        	}
                        }
                    }
                }

                return false;
            }
        }

        return true;
    }

    public void onLeave(Client client) {
        synchronized (players) {
            if (players.containsKey(client.getName())) {
                MafiaCharacter character = players.get(client.getName()).getCharacter();

                if (character != null) {
                    channel.broadcastButlerMessage(String.format("%s der Hurensohn hat uns verlassen. Er ist ausgeschieden!", client.getName()));
                }

                players.remove(client.getName());
            }
        }
    }

    public void run() {
        action();
        init();
    }

	public void onJoin(Client client) {
	}

	public boolean parseCommand(String command, Client client) {
		return false;
	}
}
package game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.GameUtilities;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class Translate implements Game, Runnable {
    private Thread thread;
    private Channel channel;
    private int sentenceLength;
    private Random random;
    private boolean playing = false;
    private boolean solved = false;
    private long start;
    private String answer;
    private DecimalFormat df;
    private List<Client> activeClients;
   List<String> values = new ArrayList<String>();
    private String anzeige = ""; 

        
    
    
    private String[] translatePlayingSentences = new String[]
    {
        "Translate läuft bereits.",
        "Es läuft bereits Translate.",
        "Wir spielen schon Translate."
    };

 private String getRandomTranslatePlayingSentence() {
        return translatePlayingSentences[random.nextInt(translatePlayingSentences.length)];
    }
    
    public Translate(Channel channel) {
        this.channel = channel;
        playing = false;

        df = new DecimalFormat("0.00");
        Init();
    }

    private void Init() {
        random = new Random();
        activeClients = new ArrayList<Client>();
        thread = new Thread(this);
        playing = false;
        solved = false;
    }

    private void StartRound() {
        
       
 PoolConnection pcon = ConnectionPool.getConnection();
      Object[] question = Server.translate.keySet().toArray();
      
      String randomQuestion = (String) question[random.nextInt(question.length)];
      answer = Server.translate.get(randomQuestion);
      

        channel.broadcastButlerMessage(String.format("°BB18°_%s_°K°§ (Zeit: 25 Sekunden)", randomQuestion));
        playing = true;
        start = System.currentTimeMillis();
        CheckTime();
    }

    public boolean parsePublicMessage(String message, Client client) {
        String msg = message.toLowerCase();

        if (msg.contains(Server.get().getButler().getName().toLowerCase()) && msg.contains("translate")) {
            if (!playing) {
                channel.broadcastMessage(msg, client, true);
                thread.start();
                return false;
            } else {
                channel.broadcastMessage(message, client, true);
                channel.broadcastButlerMessage(getRandomTranslatePlayingSentence());
                return false;
            }
        }

        if (playing) {
            if (!activeClients.contains(client)) {
                activeClients.add(client);
            }

            if (msg.equals(answer.toLowerCase())) {
                
                float time = Float.parseFloat(df.format(GameUtilities.totalTime(start)).replace(",", "."));
                float points = Float.parseFloat(df.format(((float)activeClients.size() +5+ (float)sentenceLength + (time + 4.439F)) / time).replace(",", "."));

                if (points >= 5) {
                anzeige =  "°BB°_";  
                } else {
                anzeige = "";    
                }
                
                channel.broadcastMessage(msg, client, true);
                channel.broadcastButlerMessage(String.format("Genau _%s_, die richtige Lösung lautete °RR18°_%s_§°K° (Reaktionszeit %s Sekunden, %s%s§ Punkte)",client.getName(),answer, time,anzeige, points));
                    
                client.increaseTranslatePoints(points);
                
                playing = false;
                solved = true;
                start = 0;
                    
                return false;
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

            if (!solved && start > 0 && GameUtilities.totalTime(start) > 25) {
                solved = true;
                playing = false;
                start = 0;

                channel.broadcastButlerMessage(String.format("Die Zeit ist abgelaufen, °RR18°_%s_§°K° wäre die richtige Antwort auf diese Frage gewesen.", answer));
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
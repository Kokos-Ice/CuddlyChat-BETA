package game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.GameUtilities;


public class Quiz implements Game, Runnable {
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
   private String[] JT = new String[ values.size() ];
      private String anzeige = "";  

        
    
    
    private String[] quizPlayingSentences = new String[]
    {
        "Quiz läuft bereits.",
        "Es läuft bereits Quiz.",
        "Wir spielen schon Quiz."
    };

 private String getRandomQuizPlayingSentence() {
        return quizPlayingSentences[random.nextInt(quizPlayingSentences.length)];
    }
    
    public Quiz(Channel channel) {
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
        
       

      Object[] question = Server.quiz.keySet().toArray();
      
      String randomQuestion = (String) question[random.nextInt(question.length)];
      answer = Server.quiz.get(randomQuestion);
      

        channel.broadcastButlerMessage(String.format("°BB18°_%s_°K°§ (Zeit: 25 Sekunden)", randomQuestion));
        playing = true;
        start = System.currentTimeMillis();
        CheckTime();
    }

    public boolean parsePublicMessage(String message, Client client) {
        String msg = message.toLowerCase();

        if (msg.contains(Server.get().getButler().getName().toLowerCase()) && msg.contains("quiz")) {
            if (!playing) {
                channel.broadcastMessage(msg, client, true);
                thread.start();
                return false;
            } else {
                channel.broadcastMessage(message, client, true);
                channel.broadcastButlerMessage(getRandomQuizPlayingSentence());
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

      
           /*     
                if (client.getQuizPoints() > 64) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Newcomer_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
                
              if (client.getQuizPoints() == 127) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Warmduscher_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            }   
              
              if (client.getQuizPoints() == 255) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Besserwisser_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              
              if (client.getQuizPoints() == 511) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Schlaumeier_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 1023) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Streber_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 2047) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Blitzmerker_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 4095) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Schnelldenker_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 8128) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Pfiffkus_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 16383) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Champion_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 32767) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Superhirn_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 65535) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Genie_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 131071) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Einstein_ erreicht!", client.getName()), Server.get().getButler(), false);
                       
            } 
              if (client.getQuizPoints() > 262143) {
                 channel.broadcastMessage(String.format("°BB20°%s hat den neuen Quiz-Rang _Data_ erreicht!", client.getName()), Server.get().getButler(), false);
              }
                
                */
                
                client.increaseQuizPoints(points);
                
            
                
                
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
package game;

import java.util.List;
import java.util.Random;
import java.util.*;
import tools.popup.*;
import tools.*;
import java.text.*;
import starlight.*;


public class Quiz2 implements Game, Runnable {
    private Channel channel;
    private Thread thread;
    private long startquestion;
    private String answers = "";
    private String frage = "";
    private boolean playing; 
    private int weiter = 0;
    private boolean join;
    private boolean answer;
    private int round;
    private int question;
    private boolean next;
    private boolean finale;
    private int question_max;
    private String variante;
    Random random = new Random();
    private Map<String,Integer> players = new HashMap<String,Integer>(); // 

    public Quiz2(Channel channel) {
        this.channel = channel;
    }

    
    public void setQuestion() {
      Map<String, String> db = null; 
        if (variante.equals("Classic")) {
        db = Server.quiz_classic;
       } else if (variante.equals("Mathe")) {
        db = Server.quiz_mathe;
       } else  if (variante.equals("Translate")) {
       db = Server.quiz_translate; 
        }
    String arr = "";
        for(String key: db.keySet()) {
            if (!arr.isEmpty()) {
                arr += "|"; 
            }
          arr += key+"~"+db.get(key);            
        }
        
        String[] v = arr.split("\\|");
        String random = (v[new Random().nextInt(v.length)]);
        
        
        frage = random.split("~")[0];
        answers = random.split("~")[1];
          if (variante.equals("Classic")) {
        frage = "_"+frage+"_";
       } else if (variante.equals("Mathe")) {
         frage = "Rechne: _"+frage+"_";
       } else  if (variante.equals("Translate")) {
        frage = "Übersetze das folgende Wort: _"+frage+"_";
        }
        
        
    }
    public void setTopic() {
    String hour = new SimpleDateFormat("HH").format(new Date());
    int hours = Integer.parseInt(hour);
    int neu = hours + 1; 
    if (neu == 24) {
        neu = 00;
    }
       channel.setTopic("Jede volle Stunde beginnt in diesem Channel das große °BB>_2Quizturnier|/h Quiz<°°r° für alle Mitglieder.#Die nächste Quizrunde findet um " + neu + ":00:00 statt.");   
    
    }
    private static boolean isInteger(String s)
   {
     try {
       Integer.parseInt(s); return true; } catch (NumberFormatException e) {  }
     return false;
   }
    private void sleep(long pMillis) {
        try {
            Thread.sleep(pMillis*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        
     private void start() {        
        thread = new Thread(this);
        thread.start();      
       
    }
     
     public String getVariante(String channel) {
        /** NORMAL
         int randomInt = random.nextInt(2)+1;
        if (randomInt == 1) {
        return  "Classic";
        } else if (randomInt == 2) {
        return  "Mathe";
        } else if (randomInt == 3) {
       return  "Translate";
        }
        */
     if (channel.contains("Mathe")) {
         return "Mathe";
     }
     if (channel.contains("Translate")) {
         return "Translate";
     } else if (channel.contains("Mixed")) {  
          int randomInt = random.nextInt(2)+1;
        if (randomInt == 1) {
        return  "Classic";
        } else if (randomInt == 2) {
        return  "Mathe";
        } else if (randomInt == 3) {
       return  "Translate";
        }
     }      
        return "Classic"; 
     
     }
     
     public void setPoints(String nick, int points, String typ) {
         
               Client target = Server.get().getClient(nick);
       if (target == null) {        
        target = new Client(null);
        target.loadStats(nick);
      }   
       
       // das für alle zu quizpunkte
       if (typ.equals("Mathe") || typ.equals("Translate") || typ.equals("Classic")) {
       target.setQuizPoints(target.getQuizPoints()+points);
       }
       
       
       /** DAS GEHT AUCH
       if (typ.equals("Mathe")) {
      target.setMathe(target.getMathe+points);
       }
       if (typ.equals("Translate")) {
       target.setTranslate(target.getTranslate+points);
       }
       if (typ.equals("Classic")) {
       target.setQuiz(target.getQuiz()+points);
       }
      */ 
       
       target.saveStats();
        
     }
     public void resetRights() {
     String nickss = "";
     for(String nicks : players.keySet()) {
         int v = players.get(nicks);
         if (v >= 1) {
     nickss += "|"+nicks+"|";    
      }}
     for(String g : nickss.split("\\|")) {
         if (!g.isEmpty()) {
             players.put(g,0);
     }}    
     
     }
     @Override
     public void run() {
       while(playing) {  
         sleep(20);
         if (players.size() <= 2) {
         channel.broadcastMessage("Unser Quizturnier findet nicht statt, es hätten sich _mindestens 3 Spieler_ anmelden müssen.", Server.get().getButler(), true);
         channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°", Server.get().getButler().getName()), "macht mal Pause.");
         channel.setButlerMuted(true);
         setTopic();
         playing = false;  
         players.clear();
        break;
         } 
         
          // ab hier wiederholen
        
           for(int i4 = 1;i4<= 100; i4++) {
            while(players.size() > 1) {
                weiter = 0;
              
                sleep(5);
             int i = 0;
             StringBuilder player = new StringBuilder();
             for(String nicks : players.keySet()) {
                 if (i != 0) {
                     player.append(", ");
                 }
                 player.append(nicks);
                 i++;
                 // players.get(nicks); // array
             }             
             
            
          if (players.size() <= 3) {
                finale = true;
                question_max = 9;            
          channel.broadcastMessage("Die "+round+". Runde ist unsere _Finalrunde_. Nur noch "+player.toString()+" sind im Spiel - wer zuerst _3 Fragen beantwortet_ hat, gewinnt. 9 Fragen insgesamt.", Server.get().getButler(), true);
        } else {
              question_max = players.size()*2;
         channel.broadcastMessage("Die _"+round+". Runde_ beginnt nun für "+player.toString()+"°#°_"+question_max+" Fragen_ werde ich euch maximal stellen - bei einer richtigen Antwort seid ihr weiter.", Server.get().getButler(), true);
          finale = false;
          }
             
         
         join = false;
       
            
         for(int i3 = 1;i3<= question_max; i3++) {
              sleep(3);
              if (playing) {
                 if (!next) {
             startquestion = System.currentTimeMillis();             
             setQuestion();
             answer = true;
           channel.broadcastMessage(frage+" ("+question+" / "+question_max+")", Server.get().getButler(), true);
        
                 }
        
           while (answer) {
               sleep(1);
                     
               if (next) {
                   i3 = 1000;
                     answer = false;
               }
               if (!next) {
               if ((System.currentTimeMillis() - startquestion)/1000 > 30) {
            channel.broadcastMessage("Die Zeit ist abgelaufen, °RR°_"+answers+"_§ wäre die richtige Antwort auf diese Frage gewesen.", Server.get().getButler(), true);
        
            question++;
            answer = false;
               }
               
            sleep(2);
          }}               
         }}
         
         
         int punkte = 0;
           if (round == 1) {
             punkte = 1;
           }else if (round == 2) {
             punkte = 2;
           }else if (round == 3) {
             punkte = 4;
           }else if (round == 4) {
             punkte = 8;
         }else if (round == 5) {
             punkte = 16;
           }else if (round == 6) {
             punkte = 32;
           }else if (round == 7) {
             punkte = 64;
           }else if (round == 8) {
             punkte = 128;
           }else if (round == 9) {
             punkte = 256;
           }else if (round == 10) {
             punkte = 512;
}
       
           int i2 = 0;
           String toremove = "";
             StringBuilder player2 = new StringBuilder();
             for(String nicks : players.keySet()) {
                int p = players.get(nicks);
                if (p == 0) {
                 if (i2 != 0) {
                     player2.append(", ");
                 }
                 player2.append(nicks);
               toremove += "|"+nicks+"|";
                 i2++;
                 // players.get(nicks); // array
             }  }
             for(String g : toremove.split("\\|")) {
                 if (!g.isEmpty()) {
                   players.remove(g);    
                 }
             }
            
           
             
           
                
           if (!next && playing) {
        channel.broadcastMessage("Damit _endet_ auch schon die "+round+". Runde. Ausgeschieden sind "+player2.toString()+". Die verbeibenden Spieler haben nun schon _" + punkte + " Punkte_ sicher.", Server.get().getButler(), true);
         
           }
            resetRights();
           question = 1;
               round++;
               next = false;
               
             
              
             
                 
           }}
           // wiederholen ende
             
              
               
         sleep(5);
         
         
           if (playing) {
           
              
               channel.broadcastMessage("Somit endet Quiz ohne einen Gewinner.", Server.get().getButler(), true);
       
           channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°", Server.get().getButler().getName()), "macht mal Pause.");
         channel.setButlerMuted(true);
          setTopic();
         playing = false;  
         players.clear();
       }
     }}
    public static int countChars(String input, char toCount) {
		int counter = 0;

		for (char c : input.toCharArray()) {
			if (c != toCount)
				continue;
			counter++;
		}

		return counter;
	}
    
    public boolean parsePublicMessage(String message, Client client) {
        String msg = message.toLowerCase();
       
        if (msg.toLowerCase().trim().equals(answers.toLowerCase().trim()) && answer && players.containsKey(client.getName())) {
            channel.broadcastMessage(message, client, true);
             long totaltime = System.currentTimeMillis() - startquestion;
            int right = players.get(client.getName())+1;
            if (right == 1) {
                weiter++;
            }
         players.put(client.getName(),right);
        answer = false;
         question++;
    
         channel.broadcastMessage("Genau _" + client.getName() + "_, die richtige Lösung lautete °RR°°17°_" + answers + "_§ (Reaktionszeit: " + (totaltime/1000) + " Sekunden - " + right + " Fragen gelöst).", Server.get().getButler(), true);
       
        setPoints(client.getName(),1,variante);
        answers = "";
        
        if (right == 3 && finale) {
            
            
         int punkte = 0;
           if (round == 1) {
             punkte = 1;
           }else if (round == 2) {
             punkte = 2;
           }else if (round == 3) {
             punkte = 4;
           }else if (round == 4) {
             punkte = 8;
         }else if (round == 5) {
             punkte = 16;
           }else if (round == 6) {
             punkte = 32;
           }else if (round == 7) {
             punkte = 64;
           }else if (round == 8) {
             punkte = 128;
           }else if (round == 9) {
             punkte = 256;
           }else if (round == 10) {
             punkte = 512;
}
           punkte *= 2; 
            
           channel.broadcastMessage("Damit endet dieses Quizturnier. _" + client.getName() + " hat gewonnen_ und erhält insgesamt _" + punkte + " Punkte_. Glückwunsch!", Server.get().getButler(), true);
       
           setPoints(client.getName(),punkte,variante);
           
           channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°", Server.get().getButler().getName()), "macht mal Pause.");
         channel.setButlerMuted(true);
          setTopic();
         playing = false;  
         players.clear();
         
        }
        if (weiter >= (players.size()/2) && !finale) {
    
          channel.broadcastMessage("Da sich die _Hälfte aller Spieler_ für die nächste Runde qualifiziert hat, endet diese Runde vorzeitig.", Server.get().getButler(), true);
       
         int punkte = 0;
           if (round == 1) {
             punkte = 1;
           }else if (round == 2) {
             punkte = 2;
           }else if (round == 3) {
             punkte = 4;
           }else if (round == 4) {
             punkte = 8;
         }else if (round == 5) {
             punkte = 16;
           }else if (round == 6) {
             punkte = 32;
           }else if (round == 7) {
             punkte = 64;
           }else if (round == 8) {
             punkte = 128;
           }else if (round == 9) {
             punkte = 256;
           }else if (round == 10) {
             punkte = 512;
        }
       
           int i2 = 0;
           String toremove = "";
             StringBuilder player2 = new StringBuilder();
             for(String nicks : players.keySet()) {
                int p = players.get(nicks);
                if (p == 0) {
                 if (i2 != 0) {
                     player2.append(", ");
                 }
                 player2.append(nicks);
               toremove += "|"+nicks+"|";
                 i2++;
                 // players.get(nicks); // array
             }  }
             for(String g : toremove.split("\\|")) {
                 if (!g.isEmpty()) {
                   players.remove(g);    
                 }
             }
          channel.broadcastMessage("Damit _endet_ auch schon die "+round+". Runde. Ausgeschieden sind "+player2.toString()+". Die verbeibenden Spieler haben nun schon _" + punkte + " Punkte_ sicher.", Server.get().getButler(), true);
       
          next = true;       
       question = 1;
        }
        return false;
        // jeder kann starten
    //} else if (msg.contains("quiz") && msg.contains(Server.get().getButler().getName().toLowerCase())) {
     // nur butler kann starten
        } else  if (client.equals(Server.get().getButler()) && msg.contains("quiz") && msg.contains(Server.get().getButler().getName().toLowerCase())) {  
        if (!playing) {        
        playing = true;
        join = true;
        weiter = 0;
        round = 1;
        question = 1;
        variante = getVariante(channel.getName());       
        channel.broadcastMessage(message, client, true);
        channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°",Server.get().getButler().getName()), "hat seine Pause beendet und ist wieder voll da.");
        channel.broadcastMessage("Es ist soweit, ein neues Quizturnier (°BB°_"+variante+"_°r°) beginnt genau jetzt. Alle die jetzt mitspielen wollen, geben einfach _°BB>/quiz join|/quiz join<°_°r° ein.", Server.get().getButler(), true);
        
        channel.setButlerMuted(false);
        start();
        }
        return false;        
        }
        if (!join) {
        if (playing && !players.containsKey(client.getName())) {
            return false;
        }}
        
	return true;
    }

    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
	 if (playing) {
             return false;
         }
                 
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

        int Anzahlspe = countChars(arg, '°');
        
        if (Anzahlspe == 1) {
        	String[] argus = arg.split("°", 2);
        	arg = argus[0];
          }
        
        if(!cmd.equals("quiz")) {
        	return true;
        }
        if (arg.equals("join")) {
      if (playing && join) {
        if (players.containsKey(client.getName())) {
         client.sendButlerMessage(channel.getName(), "Du bist bereits angemeldet.");
        } else {
         client.sendButlerMessage(channel.getName(), "Du hast dich erfolgreich angemeldet.");
         players.put(client.getName(), 0);
        }
        } else {
         client.sendButlerMessage(channel.getName(), "???");
        }}
        return false;
        }
}
package game;

import java.util.List;
import java.util.Random;
import java.util.*;
import tools.popup.*;
import tools.*;
import starlight.*;


public class GuessTheNumber implements Game, Runnable {
    private Channel channel;
    private Thread thread;
    private boolean playing; 
    private int placeoncounter = 0;
    private String placeonnicks = "";
    private boolean join;
    private boolean setnumber;
    private int number_max = 0;
    private int gewinndiff = 999999;
    private int number;
    Random random = new Random();
    private Map<String,String[]> players = new HashMap<String,String[]>(); // client, array[zahl,diff]

    public GuessTheNumber(Channel channel) {
        this.channel = channel;
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
     
     @Override
     public void run() {
       while(playing) {  
         sleep(20);
         if (players.size() <= 1) {
         channel.broadcastMessage("_°BB°Guess the Number_§ konnte nicht gestartet werden. Es müssen mindestens 2 Spieler dabei sein!", Server.get().getButler(), true);
        
         
         playing = false;  
         setnumber = false;
         players.clear();
        break;
         } else {            
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
        
         channel.broadcastMessage("°20°°BB°_Guess the Number§ kann beginnen...#Folgende Spieler sind dabei: " + player.toString() + " (" + i + " Spieler)", Server.get().getButler(), true);
            
         }
         join = false;
         sleep(2);
         int randomInt = random.nextInt(5)+1;
            if (randomInt == 1) {
                int randomInt2 = random.nextInt(50)+1;
                number = randomInt2;
                number_max = 50;
                } else if (randomInt == 2) {
                int randomInt2 = random.nextInt(100)+1;
                number = randomInt2;
                number_max = 100;
                } else if (randomInt == 3) {
                int randomInt2 = random.nextInt(500)+1;
                number = randomInt2;
                number_max = 500;
                } else if (randomInt == 4) {
                int randomInt2 = random.nextInt(1000)+1;
                number = randomInt2;
                number_max = 1000;
                } else if (randomInt == 5) {
                int randomInt2 = random.nextInt(10000)+1;
                number = randomInt2;
                number_max = 10000;
                }
        channel.broadcastMessage("Ich habe mir eine Zahl zwischen _°BB°1 und " + number_max + "§ ausgedacht. Welche Zahl ist es? Entscheide dich mit _°BB°/guess ZAHL§ für eine Zahl.#(Zeit für die Entscheidung: 20 Sekunden)", Server.get().getButler(), true);
          
         setnumber = true;
         sleep(20);
         
        Map<String, Integer> sorter = new HashMap<String, Integer>();
        for(String nicks : players.keySet()) {
               String[] values = players.get(nicks); // array[zahl,diff]
               int diff = 999999999;
               if (!values[0].equals("0")) {
                   diff = Integer.parseInt(values[1]);
               }
                sorter.put(nicks+"~"+values[0],diff);
                
        }
       SortedMapUtils.createMapSortedByValuesAndKeys(sorter);
       Comparator<Integer> customComparator = Collections.reverseOrder(SortedMapUtils.<Integer>comparableComparator());
       Map<String, Integer> sortiert =  SortedMapUtils.createMapSortedByValuesAndKeys(sorter,customComparator,SortedMapUtils.<String>comparableComparator());    
       StringBuilder sortnicktoplist = new StringBuilder();
       int place = players.size();     
            
        for (String key : sortiert.keySet())  {
             int differenz = sortiert.get(key);           
            if (gewinndiff > differenz) {
                gewinndiff = differenz;
            }
        }
       for (String key : sortiert.keySet())  {
            int differenz = sortiert.get(key);
            String[] values = key.split("~");
            if (values[1].equals("0")) {     
                sortnicktoplist.insert(0, "#"+place+". _"+values[0]+"_: °RR°_Verschlafen!_°r°");
          } else {
          sortnicktoplist.insert(0, "#"+place+". _"+values[0]+"_: "+differenz+" ("+values[1]+")");
          if (differenz == gewinndiff) {
              placeoncounter++;
             
              placeonnicks += "|"+values[0]+"~"+values[1]+"|";
          }
            }   
       place = place-1;  
        }
      
         channel.broadcastMessage("°BB°°20°_Guess the Number_§ ist vorbei!#Ich hatte an die Zahl _" + number + "_ gedacht.#"+sortnicktoplist.toString(), Server.get().getButler(), true);
        
          sleep(3);
         int punkte = 0;
         if (gewinndiff == 0) {
       punkte = 100;
           } else if (gewinndiff == 1) {
       punkte = 50;
           } else if (gewinndiff >= 2 && gewinndiff <= 20) {
      punkte = 30;
           }  else if (gewinndiff >= 21 && gewinndiff <= 50) {
       punkte = 20;
           } else if (gewinndiff >= 51 && gewinndiff <= 100) {
       punkte = 10;
           } else if (gewinndiff >= 101 && gewinndiff<= 200) {
       punkte = 5;
           } else if (gewinndiff >= 201 && gewinndiff <= 500) {
       punkte = 3;
           } else if (gewinndiff >= 500) {
      punkte = 2;
       }
         if (placeoncounter == 0) {
            channel.broadcastMessage("°BB°°20°_Guess the Number_§ endet ohne einen Gewinner.", Server.get().getButler(), true);
          placeoncounter = 0;
         placeonnicks = "";
         gewinndiff = 999999;
         playing = false;  
         setnumber = false;
         players.clear();
         } else if (placeoncounter == 1) {
             String nick = "";
             String zahl = "";
           for(String v : placeonnicks.split("\\|")) {
               if (!v.isEmpty()) {
               String[] h = v.split("~");
               nick = h[0];
               zahl = h[1];
           }}
    
         channel.broadcastMessage("Der Gewinner von °BB°°20°_Guess the Number_§ ist °BB°°20°_"+nick+"_§!##Mit der Zahl "+zahl+" hat "+nick+" eine Differenz von _°RR°"+gewinndiff+"_§ erreicht.#"+nick+" erhält _"+punkte+"_ Punkte.", Server.get().getButler(), true);
          placeoncounter = 0;
         placeonnicks = "";
         gewinndiff = 999999;
         playing = false;  
         setnumber = false;
         players.clear();
          Client target = Server.get().getClient(nick);
       if (target == null) {        
        target = new Client(null);
        target.loadStats(nick);
      }         
        target.setQuessPoints(target.getQuessPoints()+punkte);
         target.saveStats();
         } else {
            int h = punkte/placeoncounter;
            if (h <= 0) {
                h = 1;
            }
            String zahl = "";
            StringBuilder nicks = new StringBuilder();
            int j = 0;
            for(String v : placeonnicks.split("\\|")) {
               if (!v.isEmpty()) {
               String[] o = v.split("~");
               if (j != 0) {
                   nicks.append(", ");
               }
               nicks.append(o[0]);
              
                 Client target = Server.get().getClient(o[0]);
       if (target == null) {        
        target = new Client(null);
        target.loadStats(o[0]);
      }         
       target.setQuessPoints(target.getQuessPoints()+h);
         target.saveStats();
               
               zahl = o[1];
               j++;
           }}
   
           channel.broadcastMessage("Die Gewinner von °BB°°20°_Guess the Number_§ sind "+nicks.toString()+"!##Mit der Zahl "+zahl+" haben "+nicks.toString()+" eine Differenz von _°RR°"+gewinndiff+"_§ erreicht.#"+nicks.toString()+" erhalten _"+h+"_ Punkte.", Server.get().getButler(), true);
         placeoncounter = 0;
         placeonnicks = "";
         gewinndiff = 999999;
         playing = false;  
         setnumber = false;
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
        if (msg.contains("guess") && msg.contains(Server.get().getButler().getName().toLowerCase())) {
        channel.broadcastMessage(message, client, true);
        if (!playing) {        
        playing = true;
        join = true;
        channel.broadcastMessage("Eine neue Runde °20°°BB°_Guess the Number_§ beginnt. (ab 2 Spieler)#Wer bei diesem Ratespiel mitspielen möchte, möge mir dies mit _°>_2/guess join|/guess join<°_ mitteilen.", Server.get().getButler(), true);
          
        start();
        } else {
        channel.broadcastMessage("Guess the Number läuft bereits.", Server.get().getButler(), true);
          
        }
        return false;        
        }
        
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

        int Anzahlspe = countChars(arg, '°');
        
        if (Anzahlspe == 1) {
        	String[] argus = arg.split("°", 2);
        	arg = argus[0];
        }
        
        if(!cmd.equals("guess")) {
        	return true;
        }
        
    if (arg.equals("join")) {
        if (playing && join) {
        if (players.containsKey(client.getName())) {
         client.sendButlerMessage(channel.getName(), "Du bist bereits angemeldet.");
        } else {
         client.sendButlerMessage(channel.getName(), "Du hast dich erfolgreich angemeldet.");
         players.put(client.getName(), new String[] { "0","0" });
        }
        } else {
         client.sendButlerMessage(channel.getName(), "???");
        }
        return false;
    }
    if (isInteger(arg)) {
        int zahl = Integer.parseInt(arg);
        if (setnumber && players.containsKey(client.getName())) {
         String[] vals = players.get(client.getName());   
       
         if (vals[0].equals("0")) {
             
             if (zahl >= 1 && zahl <= number_max) {
                 client.sendButlerMessage(channel.getName(), String.format("Du hast dich für _%s_ entschieden.", arg));
                 int diff = number-zahl;
                 if (diff < 0) {
                 diff *= -1;
                  }
                 players.put(client.getName(), new String[] { arg,String.valueOf(diff) });
                 
             } else {
                  client.sendButlerMessage(channel.getName(), "Dein Tipp muss zwischen 1 und "+number_max+" sein.");
             }
             
         } else {
               client.sendButlerMessage(channel.getName(), "Du hast bereits einen Tipp abgegeben.");
         }
            
        
    } else {
         client.sendButlerMessage(channel.getName(), "???");    
        }
        return false;
    }
    
           
            client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/guess PARAMETER");
            return false;
    	
	}
}
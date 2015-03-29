package funktionen;

import static features.hero.timeStampToDate;
import static funktionen.f.time;
import game.WordMixRecord;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.image;
import static starlight.CommandParser.moderated;
import static starlight.CommandParser.muted;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class kiss {
  public static Long time = System.currentTimeMillis()/1000; 
      private static String[] kiss = {"[C] kann einfach nicht anders und gibt [T] einen langen Kuss.", "[T] kann sich dem Küssen von [C] einfach nicht entziehen.", "[T] wird von [C] ganz fest geknutscht. Wow, jetzt ist aber ein Knutschfleck zurückgeblieben.", "[T] bekommt von [C] einen liebevollen, kleinen Kuss...", "[T] kriegt von [C] einen leichten Bussi.", "[T] wird von [C] herzlich geküsst.", "[T] wird von [C] zärtlich geküsst.", "[C] beugt sich rüber zu [T] und küsst [T] sanft.", "[T] bekommt von [C] einen Bussi aufgedrückt.", "[C] küsst [T] sehr herzlich, sodass [T] nun auf [K] Knutschflecken kommt."};
  private static String[] kizzaccept = {"[C] und [T] könnten in diesem Moment die ganze Welt um sich herum vergessen und küssen sich leidenschaftlich.", "[C] und [T] kuscheln sich eng aneinander und küssen sich zärtlich.", "[C] und [T] schließen die Augen und küssen sich innig.", "[C] und [T] sehen sich verträumt in die Augen und küssen sich zärtlich.", "[C]'s Herz schlägt höher: [T] erwidert einen liebevollen Kuss!", "[C] und [T] umarmen und küssen sich sehr leidenschaftlich.", "[C] drückt [T] sanft an sich und gibt [C] einen zärtlichen Zungenkuss.", "[C] zieht [T] zu sich hin und gibt [T] einen leidenschaftlichen Zungenkuss.", "[T] lässt sich nur zu gerne auf einen zärtlichen Zungenkuss mit [C] ein.", "[C] und [T] sehen sich verträumt in die Augen und küssen sich zärtlich.", "[T] bekommt von [C] einen sehr zärtlichen Zungenkuss.", "[C] gibt [T] einen langen, leidenschaftlichen Zungenkuss.", "[C] drückt [T] sanft an sich und gibt [T] einen zärtlichen intensiven Zungenkuss.", "[C] und [T] können einfach nicht voneinander lassen und küssen sich lang und innig."}; 
    private static String[] kizzfailed = {"[C] sollte einsehen, dass keine Kusschancen bei [T] bestehen.", "[T] lässt sich nicht so einfach von [C] einen Zungenkuss aufdrücken.", "[T] glaubt vielleicht, ein Herpesbläschen an [C]'s Lippe entdeckt zu haben. Zumindestens kommt ein inniger Kuss gerade nicht in Frage!", "[C]s Versuch, [T] so richtig zu küssen, ist vorerst gescheitert.", "[C] wollte [T] einen richtigen Kuss verpassen, aber so was lässt [T] nicht mit sich machen."};
  
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
     if(muted(client, channel)) { 
            	return;
            }
            
            if(moderated(client, channel)) {
            	client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
            	return;
            }
            
            if(!client.hasPermission("cmd.kiss")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            for (long x : Server.actions.keySet()) {
            	String[] split = Server.actions.get(x);
            	Channel cha = Server.get().getChannel(split[3]);
            	String typ = split[0];
            	String v = split[1];
            	Client von = Server.get().getClient(v);
            	Client an = Server.get().getClient(split[2]);
            	
            	if(cha == channel && an == client && v.equals(arg) && typ.equals("kizz")) {
                 
                    // Original    channel.broadcastPicAction(">>", String.format("°>_h%s|/serverpp \"<° stretches out the lips, eagerly awaiting °>_h%s|/serverpp \"|/w \"<° kiss, which °>_h%s|/serverpp \"|/w \"<° is more than happy to deliver.", von.getName().replace("<", "\\<"), client.getName().replace("<", "\\<"), client.getName().replace("<", "\\<")), String.format("actKizz_ani-bg...h_20.my_3.png<>icons/actKizz_%s_ani...h_20.mx_-40.w_0.my_3.gif<>transparent1x1...w_0.gif", image(von, client)));
                
                    
                    // Chillers Abfrage
                    
                    /*    String text = kizzaccept[zufall.nextInt(kizzaccept.length)];
                      text = text.replace("[T]",von.getName());
                      text = text.replace("[C]",client.getName());
                      client.sendButlerMessage(channel.getName(), text);*/
              
                    
                    // Meine Abfrage
            String image = "";
           String image2 = "";
            if(von.getGender() == 1) {
                image = "m";
            } else if(von.getGender() == 2) {
                image = "f";
            }
             if(client.getGender() == 1) {
                image2 = "m";
            } else if(client.getGender() == 2) {
                image2 = "f";
            }
                    String text = kizzaccept[new Random().nextInt(kizzaccept.length)].replace("[C]", von.getName()).replace("[T]", client.getName());
            
            channel.broadcastPicAction(">", text, String.format("actKizz_%s%s.png", image,image2));  
                        
  
                    if(von.getKisses() == 99) {
                        channel.broadcastMessage(String.format("Jetzt wird aber gefeiert! 100 Knutschflecken hat man nicht alle Tage, %s.", von.getName()), Server.get().getButler(), false);
                        channel.broadcastAction(Server.get().getButler().getName(), "beamt sich augenblicklich in die Küche und holt blitzschnell den eisgekühlten Aldi-Schampus aus dem Eisfach.");
                    }
                    
                    if(client.getKisses() == 99) {
                        channel.broadcastMessage(String.format("Jetzt wird aber gefeiert! 100 Knutschflecken hat man nicht alle Tage, %s.", client.getName()), Server.get().getButler(), false);
                        channel.broadcastAction(Server.get().getButler().getName(), "beamt sich augenblicklich in die Küche und holt blitzschnell den eisgekühlten Aldi-Schampus aus dem Eisfach.");
                    }
                    
                    
                   
                    
                    von.increaseKisses();
                    client.increaseKisses();
                    
                     /* Highlights Eintrag */    
        int[] kisses = {1, 2, 3, 5, 10, 20, 30, 40, 50, 100, 111, 200, 222, 300, 333, 400, 500, 1000, 1500, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 15000, 20000, 25000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000  };
        Arrays.sort(kisses);
        
        if(Arrays.binarySearch(kisses, von.getKisses()) >= 0) {
         von.setHighlights(String.format("%s|%s _%s_. Knutschfleck von °BB°_%s_°12°|", von.getHighlights(), Server.get().timeStampToDate(time), nf.format(von.getKisses()), client.getName()));
         
        }
         /* Highlights Eintrag ENDE */ 
                    
        /* Highlights Eintrag */    
        int[] kisses2 = {1, 2, 3, 5, 10, 20, 30, 40, 50, 100, 111, 200, 222, 300, 333, 400, 500, 1000, 1500, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 15000, 20000, 25000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000  };
        Arrays.sort(kisses);
        
        if(Arrays.binarySearch(kisses2, client.getKisses()) >= 0) {
         client.setHighlights(String.format("%s|%s _%s_. Knutschfleck von °BB°_%s_°12°|", client.getHighlights(), Server.get().timeStampToDate(time), nf.format(client.getKisses()), von.getName()));
         
        }
         /* Highlights Eintrag ENDE */ 
                    
                    
                    
                    
            		Server.actions.remove(x);
            		return;
            	}
            }
            
            String nickname = KCodeParser.escape(arg);
            String msg = kiss[new Random().nextInt(kiss.length)];
            msg = msg.replace("[C]", String.format("°>_h%s|/serverpp \"|/w \"<°", client.getName().replace("<", "\\<")));
            
            if (nickname.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Ja, wen willst du denn küssen???");
                return;
            }

            if (nickname.equalsIgnoreCase(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du kannst Dich doch nicht selbst küssen!");
                return;
            }
            
            if(client.hasPermission("cmd.kissparty")) {
            	int kisse = client.getRank()*10+25;
            
            	if(client.getKissed() > kisse) {
            		client.sendButlerMessage(channel.getName(), "Du hast heute schon so viel geküsst, dass du bis morgen Pause machen musst.");
            		return;
            	}
            }

            Client target = Server.get().getClient(arg);
            boolean online = true;
            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nickname);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            } 
            
            msg = msg.replace("[T]", String.format("°>_h%s|/serverpp \"|/w \"<°", target.getName().replace("<", "\\<")));
            msg = msg.replace("[K]", String.valueOf(nf.format(target.getKisses()+1)));
            
            if (!online) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }
            
            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
            
            channel.broadcastPicAction(">", msg, String.format("actKiss_%s.png", image(client, target)));
 
      
           
            
            if(target.getKisses() == 99) {
                channel.broadcastMessage(String.format("Jetzt wird aber gefeiert! 100 Knutschflecken hat man nicht alle Tage, %s.", target.getName()), Server.get().getButler(), false);
                channel.broadcastAction(Server.get().getButler().getName(), "beamt sich augenblicklich in die Küche und holt blitzschnell den eisgekühlten Aldi-Schampus aus dem Eisfach.");
            }
            
            target.increaseKisses();
            client.setLevelInfo("kiss",1);
               
        /* Highlights Eintrag */    
        int[] kisses = {1, 2, 3, 5, 10, 20, 30, 40, 50, 100, 111, 200, 222, 300, 333, 400, 500, 1000, 1500, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 15000, 20000, 25000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000  };
        Arrays.sort(kisses);
        
        if(Arrays.binarySearch(kisses, target.getKisses()) >= 0) {
         target.setHighlights(String.format("%s|%s _%s_. Knutschfleck von °BB°_%s_°12°|", target.getHighlights(), Server.get().timeStampToDate(time), nf.format(target.getKisses()), client.getName()));
         
        }
         /* Highlights Eintrag ENDE */   
            if (client.getLoveWait() == 1) {
         channel.broadcastPicAction(">", "°>_h"+client.getName()+"|/serverpp \"|/w \"<° küsst °>_h"+target.getName()+"|/serverpp \"|/w \"<° leicht auf die Wange. Daraufhin verliebt sich °>_h"+client.getName()+"|/serverpp \"|/w \"<° unsterblich und vergibt das eigene °>fullheart...b.w_16.my_2.png<° an °>_h"+target.getName()+"|/serverpp \"|/w \"<°.", "icons/actHeartPotion_"+image(client, target)+".png");
       
client.setLoveWait(0);
client.setLoveTo(target.getName());
target.setLoveFrom(client.getName());
client.setLovepotion(target.getName());
target.setLovepotion(client.getName());
 channel.sendPrefixTogether();
if (!client.getHeart().isEmpty()) {
  
                    Client z = Server.get().getClient(client.getHeart());
                    if (z == null) {z = new Client(null); z.loadStats(client.getHeart()); }
                    if (z.getName() != null) {
                    z.receivedHearts.remove(client.getName());
                    }  
                     client.setHeart("");
}

  client.setHeart(target.getName());
 target.receivedHearts.add(client.getName());
                return;
            }
            
              channel.addHistory(client.getName(), String.format("°BB°kann einfach nicht anders und gibt %s einen langen Kuss.§", target.getName()), false);
         
          
      }}
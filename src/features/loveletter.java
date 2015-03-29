package features;

import static features.hero.timeStampToDate;
import starlight.*;
import tools.*;
import java.util.*;


public class loveletter {
 public static  String toremove = "";
      public static Timer timer = new Timer();
     public static int timeoutseconds = 45;
      public static Map<Long,String[]> users = new HashMap<Long,String[]>(); // ENDE [von,an,channel]
  
            
      public static String check(String von,String an) {         
         for(Long key : users.keySet()) {
             String[] v = users.get(key);
            if (v[0].equals(von) && v[1].equals(an)) {
                return String.valueOf(key);
             }
         }
         
         
         return "";
     }
      
              
      public static String[] check2(String an) {         
         for(Long key : users.keySet()) {
             String[] v = users.get(key);
             if (v[1].equals(an)) {
                return v;
             }
         }
         
         
         return null;
     }
      
        public static void functionMake(Client client,Channel channel, String arg) {
 
            String[] l = client.getFeature("Loveletter");
 Feature ft = Server.get().getFeature("Loveletter");
 
 if (ft == null) {
     return;
 }
 
            String msg = "";
                    String[] lols = arg.split(":", 2);
                    int split2 = arg.indexOf(':');
                    String art = lols[0];
                    if (split2 >= 0) {
                      msg = lols[1];
                    }
                    
                    
                    
            if (art.equals("answer")) {
            if (msg.equals("1") || msg.equals("2") || msg.equals("3"))  {
                   
                if (!client.getZusammen().isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "???");
                      return;
                }
                
                String von = "";
                String an = "";
                String art2 = "";
                
                
                String[] v = check2(client.getName());
                if (v == null) {
                    client.sendButlerMessage(channel.getName(),"Zu spät.");
                    return;
                }
                
                von = v[0]; 
                an = v[1];
                art2 = v[3];
                
                        if (von.isEmpty()) {
                          client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                          return;
                        }
 
                        String nicknamen = KCodeParser.escape(von);
                        Client target = Server.get().getClient(nicknamen);
                        if (target == null) {
                          target = new Client(null);
                          target.loadStats(nicknamen);
                        }
 
                       
                        String text = "";
                        if (msg.equals("1"))
                          text = "_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ hat zugestimmt. _°BB>_h" + target.getName() + "|/serverpp " + target.getName() + "|/w " + target.getName() + "<°°r°_ und _°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ gehen nun miteinander!°#°°#°";
                        else if (msg.equals("2"))
                          text = "_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ hat _°BB>_h" + target.getName() + "|/serverpp " + target.getName() + "|/w " + target.getName() + "<°°r°_ leider einen Korb gegeben.°#°°#°";
                        else if (msg.equals("3")) {
                          text = "_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ muss erst nochmal über die Frage von _°BB>_h" + target.getName() + "|/serverpp " + target.getName() + "|/w " + target.getName() + "<°°r°_ nachdenken.°#°°#°";
                        }
 
                        
                        
                        String bild = "";
                        if (art2.equals("classic")) {
                          if (msg.equals("1"))
                            bild = "°>abo/loveletter_classic_bg...h_177.png<°°#°°+9177>abo/loveletter_classic_over...h_177.gif<°°#°°+913012+0063° °#°°>abo/loveletter_classic_option.png<°°#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_option_trans.gif<°°#° °#° °#°°+0000r°";
                          else if (msg.equals("2"))
                            bild = "°>abo/loveletter_classic_bg...h_177.png<°°#°°+9177>abo/loveletter_classic_over...h_177.gif<°°#°°+913012+0063° °#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_classic_option.png<°°#°°>abo/loveletter_option_trans.gif<°°#° °#° °#°°+0000r°";
                          else if (msg.equals("2"))
                            bild = "°>abo/loveletter_classic_bg...h_177.png<°°#°°+9177>abo/loveletter_classic_over...h_177.gif<°°#°°+913012+0063° °#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_classic_option.png<°°#° °#° °#°°+0000r°";
                        }
                        else if (art2.equals("rude")) {
                          if (msg.equals("1"))
                            bild = "°>abo/loveletter_rude_bg...h_179.png<°°#°°+9179>abo/loveletter_rude_over...h_179.gif<°°#°°+913112+0092° °#°°>abo/loveletter_rude_option.png<°°#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_option_trans.gif<°°#° °#° °#°°+0000r°";
                          else if (msg.equals("2"))
                            bild = "°>abo/loveletter_rude_bg...h_179.png<°°#°°+9179>abo/loveletter_rude_over...h_179.gif<°°#°°+913112+0092° °#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_rude_option.png<°°#°°>abo/loveletter_option_trans.gif<°°#° °#° °#°°+0000r°";
                          else if (msg.equals("3"))
                            bild = "°>abo/loveletter_rude_bg...h_179.png<°°#°°+9179>abo/loveletter_rude_over...h_179.gif<°°#°°+913112+0092° °#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_rude_option.png<°°#° °#° °#°°+0000r°";
                        }
                        else if (art2.equals("timid")) {
                          if (msg.equals("1"))
                            bild = "°>abo/loveletter_timid_bg...h_176.png<°°#°°+9176>abo/loveletter_timid_over...h_176.gif<°°#°°+912212+0051° °#°°>abo/loveletter_timid_option.png<°°#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_option_trans.gif<°°#° °#° °#°°+0000r°";
                          else if (msg.equals("2"))
                            bild = "°>abo/loveletter_timid_bg...h_176.png<°°#°°+9176>abo/loveletter_timid_over...h_176.gif<°°#°°+912212+0051° °#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_timid_option.png<°°#°°>abo/loveletter_option_trans.gif<°°#° °#° °#°°+0000r°";
                          else if (msg.equals("3")) {
                            bild = "°>abo/loveletter_timid_bg...h_176.png<°°#°°+9176>abo/loveletter_timid_over...h_176.gif<°°#°°+912212+0051° °#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_option_trans.gif<°°#°°>abo/loveletter_timid_option.png<°°#° °#° °#°°+0000r°";
                          }
                        }
                        String ausgabe = text + bild;
                        channel.broadcastMessage(ausgabe, Server.get().getButler(), false);
if (msg.equals("1")) {
                        client.setZusammen(target.getName());
                        
                        
                        Client oldtarget = Server.get().getClient(target.getZusammen());
                        if (oldtarget == null) {
                            oldtarget = new Client(null);
                            oldtarget.loadStats(target.getZusammen());
                        }
                        if (oldtarget.getName() != null) {
                            oldtarget.setZusammen("");
                            oldtarget.saveStats();
                        }
                        target.setZusammen(client.getName());
}
             toremove += "|"+check(target.getName(),client.getName())+"|";
             
            }
 
                      return;
                    }
 
            
                if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   }  
            
                    if (arg.isEmpty()) {
                      client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/loveletter NICK oder !#(Mit NICK zusammen sein, ! zum löschen)");
                      return;
                    }
 
                    if (arg.equals("!")) {
                      if (client.getZusammen().isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "Du bist derzeit mit niemanden zusammen.");
                      } else {
                         
                         Client target = Server.get().getClient(client.getZusammen());
                       boolean online = true;
                          if (target == null) {
                              online = false;
                          target = new Client(null);
                          target.loadStats(client.getZusammen());
                        }
                         
                        target.setZusammen("");
                        target.saveStats();
                        client.sendButlerMessage(channel.getName(), "Du bist nun mit niemanden zusammen.");
                        client.setZusammen("");
                      }
                      return;
                    }
                   
             
 
                    if (art.equals("timid") || art.equals("rude") || art.equals("classic")) {
                       
         
                       
                      String nicknamen2 = KCodeParser.escape(msg);
                      boolean online2 = true;
                      Client target2 = Server.get().getClient(nicknamen2);
                      if (target2 == null) {
                        online2 = false;
                        target2 = new Client(null);
                        target2.loadStats(nicknamen2);
                      }
                      nicknamen2 = target2.getName();
 
                      
                      if (!check(client.getName(),target2.getName()).isEmpty()) {
                         client.sendButlerMessage(channel.getName(), "Du hast "+target2.getName()+" bereits gefragt.");
                        return; 
                      }
                      
                      if (nicknamen2 == null) {
                        client.sendButlerMessage(channel.getName(), "Wer soll den "+msg+" sein?");
                        return;
                      }
 
                      if (target2.getName().equals(client.getName())) {
                        client.sendButlerMessage(channel.getName(), "Du kannst doch nicht mit dir selbst zusammen sein.");
                        return;
                      }
 
                      if (!online2) {
                        client.sendButlerMessage(channel.getName(), target2.getName()+" ist _offline_!");
                        return;
                      }
 
                      if (target2.getName().equals(Server.get().getButler().getName())) {
                        client.sendButlerMessage(channel.getName(), "Mit " + Server.get().getButler().getName() + " kannst du nicht zusammen sein.");
                        return;
                      }
 
                      if (!channel.getClients().contains(target2)) {
                        client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", new Object[] { target2.getName() }));
                        return;
                      }
 
                      channel.broadcastMessage("_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°°_ schiebt einen kleinen Zettel zu _°BB>_h" + target2.getName() + "|/serverpp " + target2.getName() + "|/w " + target2.getName() + "<°°°_ hinüber!", Server.get().getButler(), false);
                    ft.setBan(l[1],l[3],l[4],client); // setz sperre
        	    
                      if (art.equals("timid"))
                        target2.sendButlerMessage(channel.getName(), "_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ schiebt dir diesen Zettel herüber:##°>abo/loveletter_timid_bg...h_176.png<°#°+9176>abo/loveletter_timid_over...h_176.gif<°#°+912212+0051° #°>abo/loveletter_option_trans.gif|abo/loveletter_timid_option.png<>|/loveletter answer:1<°#°>abo/loveletter_option_trans.gif|abo/loveletter_timid_option.png<>|/loveletter answer:2<°#°>abo/loveletter_option_trans.gif|abo/loveletter_timid_option.png<>|/loveletter answer:3<°# # #°+0000r°");
                      else if (art.equals("rude"))
                        target2.sendButlerMessage(channel.getName(), "_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ schiebt dir diesen Zettel herüber:##°>abo/loveletter_rude_bg...h_179.png<°#°+9179>abo/loveletter_rude_over...h_179.gif<°#°+913112+0100° #°>abo/loveletter_option_trans.gif|abo/loveletter_rude_option.png<>|/loveletter answer:1<°#°>abo/loveletter_option_trans.gif|abo/loveletter_rude_option.png<>|/loveletter answer:2<°#°>abo/loveletter_option_trans.gif|abo/loveletter_rude_option.png<>|/loveletter answer:3<°# # #°+0000r°");
                      else if (art.equals("classic")) {
                        target2.sendButlerMessage(channel.getName(), "_°BB>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°°r°_ schiebt dir diesen Zettel herüber:##°>abo/loveletter_classic_bg...h_177.png<°°#°°+9177>abo/loveletter_classic_over...h_177.gif<°#°+913012+0063° °#°°>abo/loveletter_option_trans.gif|abo/loveletter_classic_option.png<>|/loveletter answer:1<°°#°°>abo/loveletter_option_trans.gif|abo/loveletter_classic_option.png<>|/loveletter answer:2<°°#°°>abo/loveletter_option_trans.gif|abo/loveletter_classic_option.png<>|/loveletter answer:3<°°#° °#° °#°°+0000r°");
                      }
 
         
                 users.put(System.currentTimeMillis()+(timeoutseconds*1000),new String[] {client.getName(), target2.getName(),channel.getName(),art });
          
          timer.schedule(new TimerTask() {
            public void run() {
             
                for(Long key : users.keySet()) {
                    String[] vals = users.get(key);
                    Long time = key;
                    String von = vals[0];
                    String an = vals[1];
                    String channel = vals[2];
                     if (time < System.currentTimeMillis()) {                 
                   if (!toremove.contains("|"+key+"|")) {
                   toremove += "|"+key+"|";                 
                 }
                  }
                }  
                 for(String f : toremove.split("\\|")) {
                if (!f.isEmpty()) {
                    users.remove(Long.parseLong(f));
                   }
             }
                  
            }
            
                      
            }, 0, 1000);
          
                      return;
                    }
 
                    String nicknamen = KCodeParser.escape(arg);
                    boolean online = true;
                    Client target = Server.get().getClient(nicknamen);
                    if (target == null) {
                      online = false;
                      target = new Client(null);
                      target.loadStats(nicknamen);
                    }
                    nicknamen = target.getName();
 
                    if (nicknamen == null) {
                      client.sendButlerMessage(channel.getName(), "Wer soll den "+arg+" sein?");
                      return;
                    }
 
                    if (target.getName().equals(client.getName())) {
                      client.sendButlerMessage(channel.getName(), "Du kannst doch nicht mit dir selbst zusammen sein.");
                      return;
                    }
 
                    if (!online) {
                      client.sendButlerMessage(channel.getName(), target.getName()+" ist _offline_!");
                      return;
                    }
                    if (target.getName().equals(Server.get().getButler().getName())) {
                      client.sendButlerMessage(channel.getName(), "Mit " + Server.get().getButler().getName() + " kannst du nicht zusammen sein.");
                      return;
                    }
 
                    if (!channel.getClients().contains(target)) {
                      client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName() ));
                      return;
                    }
 
                    if (!target.getZusammen().isEmpty()) {
                      client.sendButlerMessage(channel.getName(), target.getName()+" ist bereits mit jemanden zusammen.");
                        return;
                    }
                    client.sendButlerMessage(channel.getName(), String.format("Wie willst du den Liebesbrief schreiben?#_°BB>_hKlassisch|/loveletter classic:%s<°_°°, _°BB>_hAufreißerisch|/loveletter rude:%s<°_°° oder _°BB>_hSchüchtern|/loveletter timid:%s<°°°_",  target.getName(), target.getName(), target.getName()));   
            
            
            
        }}
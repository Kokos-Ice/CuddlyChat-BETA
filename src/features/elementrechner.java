package features;

import static features.hero.timeStampToDate;
import starlight.*;
import tools.*;
import tools.popup.*;
import java.util.*;

public class elementrechner {
  
       public static Timer timer = new Timer();
          public static String toremove ="";
             public static Map<Long,String[]> users = new HashMap<Long,String[]>(); 
             public static int timeoutseconds = 7;
             
    public static void functionMake(final Client client,Channel channel, String arg) {
 
       
            String[] l1 = client.getFeature("Elementrechner-Erde");
 Feature ft1 = Server.get().getFeature("Elementrechner-Erde");
   String[] l2 = client.getFeature("Elementrechner-Feuer");
 Feature ft2 = Server.get().getFeature("Elementrechner-Feuer");
 
   String[] l3 = client.getFeature("Elementrechner-Wasser");
 Feature ft3 = Server.get().getFeature("Elementrechner-Wasser");
   
  
   String[] l4 = client.getFeature("Elementrechner-Luft");
 Feature ft4 = Server.get().getFeature("Elementrechner-Luft");
   
 if (ft1 == null || ft2 == null || ft3 == null || ft4 == null) {
      return;
 }
  boolean allowed = true;
 
 
 if (l1[0].equals("0")|| l1[0].equals("1")) {
  allowed = false;
 } 
 if (l2[0].equals("0")|| l2[0].equals("1")) {
  allowed = false;
 } 
  if (l3[0].equals("0")|| l3[0].equals("1")) {
  allowed = false;
 } 
   if (l4[0].equals("0")|| l4[0].equals("1")) {
  allowed = false;
 } 
       
   
   if (!allowed) {
       client.sendButlerMessage(channel.getName(), String.format("Du benötigst alle 4 Element-Smileys, um dein Element berechnen zu lassen."));
       return;
   }
   
   if (client.getElementrechner() == 5) {
       client.sendButlerMessage(channel.getName(),"Die Berechung deines Elements läuft bereits.");
       return;
   }
   
   if (client.getElementrechner() != 0) {
       
       if(client.getElementrechner()==1) {
     client.sendButlerMessage(channel.getName(),"Dein Element (Feuer) wurde bereits berechnet. Mit /code -element und /code +element kannst du den Effekt in öffentlichen Nachrichten aus- bzw. anschalten.");
         return;
      }
  if(client.getElementrechner()==2) {
     client.sendButlerMessage(channel.getName(), "Dein Element (Luft) wurde bereits berechnet. Mit /code -element und /code +element kannst du den Effekt in öffentlichen Nachrichten aus- bzw. anschalten.");
      return;
      }
   if(client.getElementrechner()==3) {
     client.sendButlerMessage(channel.getName(), "Dein Element (Wasser) wurde bereits berechnet. Mit /code -element und /code +element kannst du den Effekt in öffentlichen Nachrichten aus- bzw. anschalten.");
      return;
      }
   if(client.getElementrechner()==4) {
     client.sendButlerMessage(channel.getName(), "Dein Element (Erde) wurde bereits berechnet. Mit /code -element und /code +element kannst du den Effekt in öffentlichen Nachrichten aus- bzw. anschalten.");
      return;
      }
       return;
   }
   
   client.setElementrechner(5);
   channel.broadcastMessage("Die Berechnung des Elements von °>_h"+client.getName()+"|/serverpp \"|/w \"<° startet nun...",Server.get().getButler(),false);
   users.put(System.currentTimeMillis()+(timeoutseconds*1000),new String[] { client.getName(),channel.getName() });
   timer.schedule(new TimerTask() {
              public void run() {
             for(Long key : users.keySet()) {
                Long time = key;
              if (time < System.currentTimeMillis()) {                 
                   if (!toremove.contains("|"+key+"|")) {
                   toremove += "|"+key+"|";                 
                
                 Client target = Server.get().getClient(users.get(key)[0]);
           if (target == null) {
               target = new Client(null);
               target.loadStats(users.get(key)[0]);
           }
           Channel channels = Server.get().getChannel(users.get(key)[1]);
             int element = new Random().nextInt(4)+1;
            
             String text="";
 if(element==1) {
 text="Das Element von _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ ist... _°>features/elements/nick_f-l...b.mx_-5.w_0.h_0.my_15.png<°°>features/elements/nick_f-r...b.mx_21.w_0.h_0.my_15.png<°°>{+textborder}<°Feuer°>{-textborder}<°!_";    
 }
 if(element==2) {
 text="Das Element von _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ ist... _°>features/elements/nick_a-l...b.mx_-5.w_0.h_0.my_15.png<°°>features/elements/nick_a-r...b.mx_21.w_0.h_0.my_15.png<°°>{+textborder}<°Luft°>{-textborder}<°!_";    
 } 
  if(element==3) {
 text="Das Element von _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ ist... _°>features/elements/nick_w-l...b.mx_-5.w_0.h_0.my_15.png<°°>features/elements/nick_w-r...b.mx_21.w_0.h_0.my_15.png<°°>{+textborder}<°Wasser°>{-textborder}<°!_";    
 } 
   if(element==4) {
 text="Das Element von _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ ist... _°>features/elements/nick_e-l...b.mx_-5.w_0.h_0.my_15.png<°°>features/elements/nick_e-r...b.mx_21.w_0.h_0.my_15.png<°°>{+textborder}<°Erde°>{-textborder}<°!_";    
 } 
           
   target.setElement(element);
   target.setElementrechner(element);
   channels.broadcastMessage(text,Server.get().getButler(),false);
   
           
           target.saveStats();
            } }}
             
             
             
                for(String f : toremove.split("\\|")) {
                if (!f.isEmpty()) {
                    users.remove(Long.parseLong(f));                    
                   }
             }
            }
            
                      
            }, 0, 1000);
   
   
    }}
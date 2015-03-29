package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class moskito {
  public static Long time = System.currentTimeMillis()/1000; 
      public static Timer timer = new Timer();
          public static String toremove ="";
             public static Map<Long,String[]> users = new HashMap<Long,String[]>(); 
             public static int timeoutseconds = 10;
      public static void functionMake(Client client,final Channel channel, String arg) {
   
  String[] l = client.getFeature("Moskito");
  Feature ft = Server.get().getFeature("Moskito");
 
  if (ft == null) {
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
 
 
            boolean on = true;
            Client target = Server.get().getClient(arg);
            if (target == null) {
                target = new Client(null);
                on = false;
                target.loadStats(arg);
        }

            if (arg.isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Verwende _/moskito NICK_ um einen Moskito zu Nick zu schicken.");
                return;
        }
            if (target.getName() == null) {
               client.sendButlerMessage(channel.getName(), unknownUser(arg));
                return;
        }
            if (!on) {
                  client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
        }

        if (target == client) {
            client.sendButlerMessage(channel.getName(),"Mit dir selbst geht das aber nicht.");
            return;
        }
        if (target == Server.get().getButler()) {
              client.sendButlerMessage(channel.getName(),String.format(Server.get().getButler().getName()+" kannst du nicht auswählen."));
            return;
        }

        if (!channel.getClients().contains(target)) {
                       client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                       return;
        }
        
        if (target.haveSchutzschild()) {
        client.sendButlerMessage(channel.getName(),"Eine fast durchsichtige Aura bildet ein Schutzschild °>features/schutzschild/ft_schutzschild_knuddel...b.h_20.w_24.my_14.mx_-10.png<° um °>_h"+target.getName()+"|/serverpp \"|/w \"<°, sodass du diese Funktion jetzt nicht anwenden kannst.");
        return;
        
        }
        
        
            ft.setBan(l[1],l[3],l[4],client);
            client.sendButlerMessage(channel.getName(),"Dein Moskito macht sich auf dem Weg zu "+target.getName());
            target.sendButlerMessage(channel.getName(),"Ein Moskito von "+client.getName()+" ist auf dem Weg zu dir...°>sounds/mosquito_fly_new.mp<°");
            users.put(System.currentTimeMillis()+(timeoutseconds*1000),new String[] { client.getName(),target.getName(),channel.getName()});
 
            timer.schedule(new TimerTask() {
            public void run() {
             for(Long key : users.keySet()) {
                Long time = key;
              if (time < System.currentTimeMillis()) {                 
                   if (!toremove.contains("|"+key+"|")) {
                   toremove += "|"+key+"|";                 
                
               
               String vons = users.get(key)[0];
               String ans = users.get(key)[1];
               String channels = users.get(key)[2];
               Channel channell = Server.get().getChannel(channels);
               Client von = Server.get().getClient(vons);
                   if (von == null) {
                       von = new Client(null);
                       von.loadStats(vons);
                   }
                     Client an = Server.get().getClient(ans);
                   if (an == null) {
                       an = new Client(null);
                       an.loadStats(ans);
                   }
                    int rand = new Random().nextInt(2)+1;
 
 if (rand == 2) {
     von.sendButlerMessage(channel.getName(),"Dein Moskito wurde von "+an.getName()+" abgewehrt.");
     an.sendButlerMessage(channel.getName(),"Du hast den Moskito von "+von.getName()+" erfolgreich abgewehrt.°>sounds/mosquito_squish-wilhelm.mp<°");
     an.setMoskitoAbgewehrt(an.getMoskitoAbgewehrt()+1);
     } else {
     an.setMoskitoGestochen(an.getMoskitoGestochen()+1);
     von.sendButlerMessage(channel.getName(),an.getName()+" wurde von deinem Moskito gestochen.");
     an.sendButlerMessage(channel.getName(),von.getName()+" hat dir ein Moskito gesendet, dieser hat dich gestochen.°>sounds/mosquito_sting.mp<°");
     String old = an.getEffect();
     String send = "moskitoBite";
     an.setMoskitoEnde(((System.currentTimeMillis()/1000)+3600));
     an.setEffect(send);
                for (Client targets : channel.getClients()) {
                     if (!an.getEffect().isEmpty()) {
                 targets.send(PacketCreator.removeEffect(an.getName(), old));
                }
                targets.send(PacketCreator.effect(an.getName(), send));
                }
                an.saveStats();
                }
                                   
            } }}
                     
                for(String f : toremove.split("\\|")) {
                if (!f.isEmpty()) {
                    users.remove(Long.parseLong(f));
                   }
             }
            }
                            
            }, 0, 1000);
 
      }}
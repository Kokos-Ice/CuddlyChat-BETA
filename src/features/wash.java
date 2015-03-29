package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class wash {
        private static Random zufall = new Random();
         
public static String firstCharUpperCase(String str) {
return str.substring(0, 1).toUpperCase() + str.substring(1);
}

  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
         
  String[] l = client.getFeature("Wash");
 Feature ft = Server.get().getFeature("Wash");
 
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
  client.sendButlerMessage(channel.getName(),"Verwende _/wash NICK_ um den Nickeffekt eines Users wegzuwaschen.");
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
            
 if (target.getEffect().isEmpty()) {
     client.sendButlerMessage(channel.getName(),String.format("°>_h%s|/serverpp \"|/w \"<° hat aktuell keinen aktiven Nicklisteneffekt.", target.getName()));
     return;
 }
 if (target.getEffect().equals("wash")) {
    client.sendButlerMessage(channel.getName(),"Nick hat bereits den Wash-Effekt.");
     return;
 }
 
 
 /* Erweiterungen / Änderungen Start */
 
 if (target.haveSchutzschild()) {
        client.sendButlerMessage(channel.getName(),"Eine fast durchsichtige Aura bildet ein Schutzschild °>features/schutzschild/ft_schutzschild_knuddel...b.h_20.w_24.my_14.mx_-10.png<° um °>_h"+target.getName()+"|/serverpp \"|/w \"<°, sodass du diese Funktion jetzt nicht anwenden kannst.");       
        return;
 }
 
 
 if (target.getEffect().equals("moskitoBite") || target.getEffect().equals("monster")) {
    String text = "";
                text = CommandParser.washfailed[zufall.nextInt(CommandParser.washfailed.length)].replace("[C]", client.getName()).replace("[T]", target.getName()).replace("[E]", firstCharUpperCase(target.getEffect()));
                channel.broadcastAction(">>",String.format(""+text, client.getName(), target.getName())); 
                ft.setBan(l[1],l[3],l[4],client);
                return;
 }
 


  String text = "";
                text = CommandParser.wash[zufall.nextInt(CommandParser.wash.length)].replace("[C]", client.getName()).replace("[T]", target.getName()).replace("[E]", firstCharUpperCase(target.getEffect()));
 
channel.broadcastAction(">>",String.format(""+text, client.getName(), target.getName())); 
 
/* Erweiterungen / Änderungen ENDE */

    String old = target.getEffect();
        
    String send = "wash";
    target.setWashEnde(((System.currentTimeMillis()/1000)+3600));
        target.setEffect(send);
                for (Client targets : channel.getClients()) {
                     if (!target.getEffect().isEmpty()) {
                 targets.send(PacketCreator.removeEffect(target.getName(), old));
                }
                targets.send(PacketCreator.effect(target.getName(), send));
                }
                
      ft.setBan(l[1],l[3],l[4],client); // setz sperreei 
      
 
      }}
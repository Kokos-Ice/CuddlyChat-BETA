package features;

import starlight.*;
import tools.*;
import tools.popup.*;
import java.util.*;

public class turnedhead {
  
    public static void functionMake(Client client,Channel channel, String arg) {
 
        if (arg.isEmpty()) {
         client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/turnedhead NICK#(NICK hat dir den Kopf verdreht.)");
            return;
         }
        
        if (arg.startsWith("-") && !arg.equals("-")) {
            
            if (client.getTurnedHeadTo().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Du hast niemanden den Kopf verdreht.");
             return;
        }
        
            
              Client target = Server.get().getClient(arg.replace("-",""));
         boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(arg.replace("-",""));                
            }
            
            
            if (!client.getTurnedHeadTo().contains("|"+target.getName()+"|")) {
                  client.sendButlerMessage(channel.getName(),"Du verdrehst _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_  nicht den Kopf.");
           return;
            }
               target.setTurnedHeadFrom(""); 
            target.saveStats();
            client.sendButlerMessage(channel.getName(),"Du verdrehst _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ nicht mehr den Kopf.");
           client.setTurnedHeadTo(client.getTurnedHeadTo().replace("|"+target.getName()+"|",""));
            
            return;
        }
        
        if (arg.equals("!")) {
            
            if (client.getTurnedHeadFrom().isEmpty()) {
             client.sendButlerMessage(channel.getName(), "Niemand hat dir den Kopf verdreht.");
             return;
        }
        
            
              Client target = Server.get().getClient(client.getTurnedHeadFrom());
         boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(client.getTurnedHeadFrom());                
            }
            
            
             if (online) {
               target.setTurnedHeadTo(target.getTurnedHeadTo().replace("|"+client.getName()+"|","")); 
            } else {
             //   Server.get().query("update accounts set turnedheadto='"+target.getTurnedHeadTo().replace("|"+client.getName()+"|","")+"' where name='"+target.getName()+"'");
            }
             
            client.sendButlerMessage(channel.getName(),"_°BB>_h"+client.getTurnedHeadFrom()+"|/serverpp \"|/w \"<°°°_ verdreht dir nicht mehr den Kopf.");
           client.setTurnedHeadFrom("");
        return;
        }
          String[] l = client.getFeature("TurnedHead");
 Feature ft = Server.get().getFeature("TurnedHead");
 
 if (ft == null) {
     return;
 }
 
 if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+Server.get().timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   } 
         
         Client target = Server.get().getClient(arg);
            boolean online = true;
            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(arg);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), "Wer soll den "+arg+" sein?");
                    return;
                }
            } 
            
          
            if (!online) {
                client.sendButlerMessage(channel.getName(), target.getName()+" ist _offline!_");
                return;
            }
            
            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
            if (client == target) {
                client.sendButlerMessage(channel.getName(), "Du kannst einen Stern nicht nach dir benennen.");
                return;
            }
            if (!client.getTurnedHeadFrom().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "_°BB>_h"+client.getTurnedHeadFrom()+"|/serverpp \"|/w \"<°°°_ verdreht dir bereits den Kopf! ");
                 return;
            }
                 
        String foto = "";
            if (!client.getPhoto().isEmpty()) {
            foto = " °>photos/photo/"+client.getPhoto()+"...w_50.mouseh_54.b.shadow_4.quadcut_50.border_3.mirrorh.h_0.jpg<>--<>_h|/foto "+client.getName()+"<°";
            }
           String image = ""; 
           if (target.getGender() == 1) {
               if (client.getGender() == 1) { 
                   image = "mm";
               } else {
                   image = "fm";
               }} else {
               if (client.getGender() == 1) {
                   image = "mf"; 
               } else { 
                   image = "ff";
               }
           } 
             
            String text = "_°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ °BB°hat _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_"+foto+" °BB°gerade den Kopf verdreht.";
         channel.broadcastPicAction(">>", text, "features/turned-head/turned-head_prefix_"+image+".png");

            
            target.sendButlerMessage(channel.getName(),"Du hast _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ den Kopf verdreht! Mit _/turnedhead -"+client.getName()+"_ kannst du die Verbindung wieder aufheben.");
          client.sendButlerMessage(channel.getName(),"_°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ verdreht dir nun deinen Kopf. Mit _/turnedhead !_ kannst du die Verbindung wieder aufheben.");
          client.setTurnedHeadFrom(target.getName());
          target.setTurnedHeadTo(target.getTurnedHeadTo()+"|"+client.getName()+"|");
           ft.setBan(l[1],l[3],l[4],client);
          
    }}





  
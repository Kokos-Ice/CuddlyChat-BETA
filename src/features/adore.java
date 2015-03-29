package features;

import starlight.*;
import tools.*;
import tools.popup.*;
import java.util.*;

public class adore {
  
    public static void functionMake(Client client,Channel channel, String arg) {
 
        if (arg.isEmpty()) {
         client.sendButlerMessage(channel.getName(), "Bitte verwende den Befehl so: /adore NICK oder /adore !NICK");
            return;
         }
        
        if (arg.startsWith("-") && !arg.equals("-")) {
            
            if (client.getAdoreTo().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Du wirst von niemanden angehimmelt.");
             return;
        }
        
            
              Client target = Server.get().getClient(arg.replace("-",""));
         boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(arg.replace("-",""));                
            }
            
            
            if (!client.getAdoreTo().contains("|"+target.getName()+"|")) {
                  client.sendButlerMessage(channel.getName(),"Du himmelst _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_  nicht an.");
           return;
            }
               target.setAdoreFrom(""); 
            target.saveStats();
            client.sendButlerMessage(channel.getName(),"Du himmelst _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ nicht mehr an.");
           client.setAdoreTo(client.getAdoreTo().replace("|"+target.getName()+"|",""));
            
            return;
        }
        
        if (arg.equals("!")) {
            
            if (client.getAdoreFrom().isEmpty()) {
             client.sendButlerMessage(channel.getName(), "Du himmelst jetzt niemanden mehr an.");
             return;
        }
        
            
              Client target = Server.get().getClient(client.getAdoreFrom());
         boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(client.getAdoreFrom());                
            }
            
            
             if (online) {
               target.setAdoreTo(target.getAdoreTo().replace("|"+client.getName()+"|","")); 
            } else {
             //   Server.get().query("update accounts set adoreto='"+target.getAdoreTo().replace("|"+client.getName()+"|","")+"' where name='"+target.getName()+"'");
            }
             
            client.sendButlerMessage(channel.getName(),"_°BB>_h"+client.getAdoreFrom()+"|/serverpp \"|/w \"<°°°_ himmelst du nicht mehr an.");
           client.setAdoreFrom("");
        return;
        }
          String[] l = client.getFeature("Adore");
 Feature ft = Server.get().getFeature("Adore");
 
 if (ft == null) {
     // kick vermeiden
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
                client.sendButlerMessage(channel.getName(), "Du kannst dich nicht selbst anhimmeln.");
                return;
            }
            if (!client.getAdoreFrom().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "_°BB>_h"+client.getAdoreFrom()+"|/serverpp \"|/w \"<°°°_ himmelst du bereits an! ");
                 return;
            }
                 
      /*  String foto = "";
            if (!client.getPhoto().isEmpty()) {
            foto = " °>photos/m/"+client.getPhoto()+"...w_50.mouseh_54.b.shadow_4.quadcut_50.border_3.mirrorh.h_0.jpg<>--<>_h|/foto "+client.getName()+"<°";
            }*/
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
           } // 2 = weib 1 = männ
             
            String text = "_°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ °BB°hat _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ °BB°absolut verzaubert. Derart angehimmelt wird selten jemand.";
         channel.broadcastPicAction(">>", text, "features/turned-head/turned-head_prefix_"+image+".png");

            
          target.sendButlerMessage(channel.getName(),"Du kannst mit °BB°_/adore !"+target.getName()+"°r°_ das Angehimmeltwerden jederzeit beenden.");
          client.sendButlerMessage(channel.getName(),"Du kannst mit °BB°_/adore !_°r° das Anhimmeln jederzeit beenden.");
          client.setAdoreFrom(target.getName());
          target.setAdoreTo(target.getAdoreTo()+"|"+client.getName()+"|");
           ft.setBan(l[1],l[3],l[4],client); // setz sperre
          
    }}





  
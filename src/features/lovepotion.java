package features;

import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;






public class lovepotion {
    private static Random zufall = new Random();
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Liebestrank");
    Feature ft = Server.get().getFeature("Liebestrank");
 
      // hier annehmen ablehnen
    
    
    if (arg.equals("ok")) {
        
     
        if (client.getLovepotionFrom().isEmpty()) {
            client.sendButlerMessage(channel.getName(),"Dafür ist es bereits zu Spät.");
            return;
        }
        Client target = Server.get().getClient(client.getLovepotionFrom());
if (target == null) { target = new Client(null); target.loadStats(client.getLovepotionFrom()); }
        
      target.sendButlerMessage(channel.getName(),"°>_h"+client.getName()+"|/serverpp \"|/w \"<° hat deinen Liebestrank getrunken. Was jetzt wohl passiert?");
      client.sendButlerMessage(channel.getName(),"Wow, der Trank hat dir außerordentlich gut geschmeckt! Was da wohl drin war...?");

       client.setLoveWait(1);
       client.setLovepotionTo("");
       target.setLovepotionFrom("");
       client.setLovepotionTo("");
       client.setLovepotionFrom("");
       target.saveStats();   
       return;
        
    } 
    
     
    if (arg.equals("!")) {
                      if (client.getLovepotion().isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "Du hast aktuell keinen Lovepotion der aufgelöst werden kann.");
                      } else {
                         
                        Client target = Server.get().getClient(client.getLovepotion());
                       boolean online = true;
                          if (target == null) {
                              online = false;
                          target = new Client(null);
                          target.loadStats(client.getLovepotion());
                        }
                          
                          
                         
                        target.setLovepotion("");
                        target.saveStats();
                        client.sendButlerMessage(channel.getName(), "Dein Lovepotion mit "+target.getName()+" wurde aufgelöst.");
                        Server.get().newMessage(Server.get().getButler().getName(), target.getName(),"", ""+client.getName()+" hat den bestehenden Lovepotion mit dir beendet.", (System.currentTimeMillis()/1000)); 
         
                        
                        client.setLovepotion("");
                          channel.sendPrefixTogether();
                      }
                      return;
                    }
 
 
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
           
           
           arg = KCodeParser.escape(arg);
        	Client target = Server.get().getClient(arg);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(arg);
                             
                        
             if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Wem möchtest du den Liebestrank geben?");
                return;
            }
                        
                        
        	
        if(target.getName() == null) {
        	client.sendButlerMessage(channel.getName(), unknownUser(arg));
                return;
                }
            }
        	
        if(!online) {
        	client.sendButlerMessage(channel.getName(), userIsOffline(target));
        	return;
            }
        	
        if(target == Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), "Mit unserem Butler kannst du das nicht machen.");
                return;
            }
        
        
        if(target == client) {
        	client.sendButlerMessage(channel.getName(), String.format("Mit dir selbst geht das nicht.", target.getName()));
                return;
            }
        

        if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
        
     
        /*    if (!client.getLovepotionTo().isEmpty()) {
                client.sendButlerMessage(channel.getName(), String.format("Du hast bereits eine Anfrage an "+target.getName()+ " gesendet.", target.getName()));
             
                return;
            }*/
      
        client.sendButlerMessage(channel.getName(),"Ich habe "+target.getName()+" den Liebestrank angeboten.");
        target.sendButlerMessage(channel.getName(),"°>_h"+client.getName()+"|/serverpp \"|/w \"<° reicht dir ein sehr wohlriechendes, rotes Getränk. Kostest du davon? _°>{button} Bestätigen ||call|/lovepotion ok<° _");      
    
        
        client.setLovepotionTo(target.getName());
        target.setLovepotionFrom(client.getName());
        target.setLovepotionTimeout(((System.currentTimeMillis()/1000)+60)+""); // 1 min timeout
        client.setLovepotionTimeout(((System.currentTimeMillis()/1000)+60)+""); // 1 min timeout
                  
        
                ft.setBan(l[1],l[3],l[4],client); 
                
       }
}
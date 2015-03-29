
package features;

import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.image;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;






public class partnerlook {
    private static Random zufall = new Random();
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Partnerlook");
    Feature ft = Server.get().getFeature("Partnerlook");
 
    
    
    if (arg.equals("ok")) {
        
     
        if (client.getPartnerlookFrom().isEmpty()) {
            client.sendButlerMessage(channel.getName(),"Da hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.");
            return;
        }
        Client target = Server.get().getClient(client.getPartnerlookFrom());
if (target == null) { target = new Client(null); target.loadStats(client.getPartnerlookFrom()); }
        
       client.setPartnerlook(target.getName());
       target.setPartnerlook(client.getName());
       channel.sendPrefixTogether();
       target.setPartnerlookTo("");
       target.setPartnerlookFrom("");
       client.setPartnerlookTo("");
       client.setPartnerlookFrom("");
       target.saveStats();   
       client.sendButlerMessage(channel.getName(), "_Im Partnerlook_ siehst du "+target.getName()+" nun verdammt ähnlich. Um den Partnerlook abzulegen, kannst du jederzeit _/partnerlook !_ eingeben.");
                    
       String text = "";
                text = CommandParser.partnerlook[zufall.nextInt(CommandParser.partnerlook.length)].replace("[C]", client.getName()).replace("[T]", target.getName());
               
                String icon = "icons/actPartnerLook_"+image(client,target)+".png";
                channel.broadcastPicAction(">", text, icon);
                
       return;
        
    } 
    
     
    if (arg.equals("!")) {
                      if (client.getPartnerlook().isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "Du hast aktuell keinen Partnerlook der aufgelöst werden kann.");
                      } else {
                         
                        Client target = Server.get().getClient(client.getPartnerlook());
                       boolean online = true;
                          if (target == null) {
                              online = false;
                          target = new Client(null);
                          target.loadStats(client.getPartnerlook());
                        }
                          
                          
                         
                        target.setPartnerlook("");
                        target.saveStats();
                        client.sendButlerMessage(channel.getName(), "Der Partnerlook mit "+target.getName()+" _wurde beendet_. Die Zeiten der auffälligen Neon-Jens sind vorbei.");
                        Server.get().newMessage(Server.get().getButler().getName(), target.getName(),"Dein Partnerlook wurde aufgelöst.", "Dein Partnerlook mit "+client.getName()+" wurde _aufgelöst_.", (System.currentTimeMillis()/1000)); 
         
                        
                        client.setPartnerlook("");
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
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen: _/partnerlook Nick_.");
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
                client.sendButlerMessage(channel.getName(), "Mit unserem Butler kannst du nicht in Neon-Jeans herumlaufen.");
                return;
            }
        
        
        if(target == client) {
        	client.sendButlerMessage(channel.getName(), String.format("Eigenliebe wird nicht toleriert.", target.getName()));
                return;
            }
        

        if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
        
        
        if (!client.getPartnerlook().isEmpty()) {
            Client targetold = Server.get().getClient(client.getPartnerlook());
                if (targetold == null) {
                    targetold = new Client(null);
                    targetold.loadStats(client.getPartnerlook());
                }
            
            client.sendButlerMessage(channel.getName(), String.format("Sorry, _ein weiterer Partnerlook ist dir leider nicht möglich_. Du hast bereits mit "+targetold.getName()+ " einen Partnerlook. Um einen neuen Partnerlook zu erstellen, musst du zuerst deinen alten Partnerlook mit _/partnerlook !_ beenden.", target.getName()));
                
            return;
        }
            
           
            if (!client.getPartnerlookTo().isEmpty()) {
                client.sendButlerMessage(channel.getName(), String.format("Du hast bereits eine Anfrage an "+target.getName()+ " gesendet.", target.getName()));
             
                return;
            }

        
        client.sendButlerMessage(channel.getName(),"Ich habe "+target.getName()+" deine Partnerlook-Anfrage übermittelt.");
        target.sendButlerMessage(channel.getName(),client.getName()+" lädt dich ein, künftig _im Partnerlook_ herumzulaufen. Klick auf °BB°_°>{button} Bestätigen ||call|/partnerlook ok<°_°r°, um anzunehmen.°r°");      
        
        
            
            
        // beim annehmen/ablehnen fragst du ab ob man von target ne anfrage überhaupt hat fertig.
        
        
        
        
        
        client.setPartnerlookTo(target.getName());
        target.setPartnerlookFrom(client.getName());
        target.setPartnerlookTimeout(((System.currentTimeMillis()/1000)+60)+""); // 1 min timeout
        client.setPartnerlookTimeout(((System.currentTimeMillis()/1000)+60)+""); // 1 min timeout
      
        
               
        
                ft.setBan(l[1],l[3],l[4],client); 
                
       }
}
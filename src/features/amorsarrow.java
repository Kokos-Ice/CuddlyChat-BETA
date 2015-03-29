package features;

import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;






public class amorsarrow {
    
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Amorsarrow");
    Feature ft = Server.get().getFeature("Amorsarrow");
 
      // hier annehmen ablehnen
    
    
    if (arg.equals("ok")) {
        
     
        if (client.getAmorsarrowFrom().isEmpty() && client.getAmorsarrowTo().isEmpty()) {
            client.sendButlerMessage(channel.getName(),"Dafür ist es bereits zu Spät.");
            return;
        }
        
        
        String d = "";
        if (client.getAmorsarrowFrom().isEmpty()) {
            d = client.getAmorsarrowTo();
        } else {
        d = client.getAmorsarrowFrom();
        }
        Client target = Server.get().getClient(d);
if (target == null) { target = new Client(null); target.loadStats(d); }
        


if (target.getAmorsarrowAkzeptFrom().equals("0")) {  
    
    client.sendButlerMessage(channel.getName(),"Du hast die Verbindung bestätigt. "+target.getName()+" muss jetzt noch bestätigen.");  
    client.setAmorsarrowAkzeptTo("1");
   client.setAmorsarrowAkzeptFrom("1");
    client.setAmorsarrowFrom("");
    client.setAmorsarrowTo("");
    return;
}


       client.setAmorsarrow(target.getName());
       target.setAmorsarrow(client.getName());
       channel.sendPrefixTogether();
       target.setAmorsarrowTo("");
       target.setAmorsarrowFrom("");
       client.setAmorsarrowTo("");
       client.setAmorsarrowFrom("");
       client.setAmorsarrowAkzeptFrom("0");
       client.setAmorsarrowAkzeptTo("0");
       target.setAmorsarrowAkzeptFrom("0");
       target.setAmorsarrowAkzeptTo("0");
       target.saveStats();
       
       Client von = Server.get().getClient(client.getAmorsarrowNick());
       

      
    
       
       
       
       
String foto3 = "";
if (target.getPhoto().trim().isEmpty()) {
if (target.getGender() == 2) {
foto3 = "nopic_79x79_f";
} else {
foto3 = "nopic_79x79_m";
}} else {
foto3 = target.getPhoto();
}
String foto2 = "";
if (client.getPhoto().trim().isEmpty()) {
if (client.getGender() == 2) {
foto2 = "nopic_79x79_f";
} else  {
foto2 = "nopic_79x79_m";
}} else {
foto2 = client.getPhoto();
}
String more2 = "";
String more3 = "";

if (client.getAge() != 0) {
more2 += " ("+client.getAge()+")";
}
if (client.getGender() == 2) {
more2 += " °>gender_female...b.h_0.png<°";
} else {
more2 += " °>gender_male...b.h_0.png<°";
}

if (target.getAge() != 0) {
more3 += " ("+target.getAge()+")";
}
if (target.getGender() == 2) {
more3 += " °>gender_female...b.h_0.png<°";
} else {
more3 += " °>gender_male...b.h_0.png<°";
}
       
       String text = "_°BB>_h"+von.getName()+"|/serverpp \"|/w \"<°°°_ °BB°möchte heute Amor spielen und vereint:#°+9510r>CENTER<°°>{table|w2|165|165|w2}<°°>{tc}<°_°>_h"+client.getName()+"|/serverpp \"|/w \"<°"+more2+"°b° °>{tc}<°_°>_h"+target.getName()+"|/serverpp \"|/w \"<°"+more3+"°b° °>{tc}<°°>{endtable}<°#°+9518>{table|w2|100|65,center|100|w2}<°°>{tc}<°°>photos/photo/getPicture.php?m&img="+foto2+"...quadcut_60.border_2.jpg<>--<>_h|/foto "+client.getName()+"|/w "+client.getName()+"<°°>{tc}<°°>features/amorsarrow/ft_amors-arrow_nicks-chat.png<°°>{tc}<°°>photos/photo/getPicture.php?m&img="+foto3+"...quadcut_60.border_2.jpg<>--<>_h|/foto "+target.getName()+"|/w "+target.getName()+"<°°>{tc}<°°>{tc}<°°>{endtable}<°#°>LEFT<bir°§";

channel.broadcastAction(">>", text);


channel.broadcastAction(">>","_°BB>_h"+von.getName()+"|/serverpp \"|/w \"<°°°_ °BB°hat zu einer besseren Welt beigetragen, weil _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ °BB°und _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ °BB°nun vereint sind.");
target.sendButlerMessage(channel.getName(),"Amors Pfeil hat dich und °>_h"+client.getName()+"|/serverpp \"|/w \"<° verbunden. Du kannst die Verbindung jederzeit mit _/amorsarrow !_ aufheben.");
client.sendButlerMessage(channel.getName(),"Amors Pfeil hat dich und °>_h"+target.getName()+"|/serverpp \"|/w \"<° verbunden. Du kannst die Verbindung jederzeit mit _/amorsarrow !_ aufheben.");
 

if (von != null) {
     von.sendButlerMessage(channel.getName(),"Du hast "+client.getName()+ " und "+target.getName()+" erfolgreich zusammengeführt!");    
       }

       return;

                } 
    
     
    if (arg.equals("!")) {
                      if (client.getAmorsarrow().isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "Du bist nicht durch Amors Pfeil verbunden.");
                      } else {
                         
                        Client target = Server.get().getClient(client.getAmorsarrow());
                       boolean online = true;
                          if (target == null) {
                              online = false;
                          target = new Client(null);
                          target.loadStats(client.getAmorsarrow());
                        }
                          
                          
                         
                        target.setAmorsarrow("");
                        target.saveStats();
                        client.sendButlerMessage(channel.getName(), "Deine Verbindung mit "+target.getName()+" wurde aufgelöst.");
                        Server.get().newMessage(Server.get().getButler().getName(), target.getName(),"", ""+client.getName()+" hat die Amor-Verbindung mit dir beendet.", (System.currentTimeMillis()/1000)); 
         
                        
                        client.setAmorsarrow("");
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
           
                if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen: _/amorsarrow NICK1:NICK_.");
                return;
            }
                    
                
           arg = KCodeParser.escape(arg);
          if (arg.indexOf(":") < 0) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen: _/amorsarrow NICK1:NICK2_.");
               
              return;
          }
           String[] nicks = arg.split(":");
           String nick1 = nicks[0];
           String nick2 = nicks[1];
        
           	Client target2 = Server.get().getClient(nick2);
        	boolean online2 = true;
        	
        	if(target2 == null) {
        		online2 = false;
        		target2 = new Client(null);
        		target2.loadStats(nick2);
                      
        	
        if(target2.getName() == null) {
        	client.sendButlerMessage(channel.getName(), unknownUser(nick2));
                return;
                }
            }
                
        	Client target = Server.get().getClient(nick1);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(nick1);
                      
        	
        if(target.getName() == null) {
        	client.sendButlerMessage(channel.getName(), unknownUser(nick1));
                return;
                }
            }
        	
        if(!online) {
        	client.sendButlerMessage(channel.getName(), userIsOffline(target));
        	return;
            }
        	
        if(target == Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), "Du kannst unserem Butler nicht mit jemanden Verkuppeln.");
                return;
            }
        
        
        if(target == client || target2 == client) {
        	client.sendButlerMessage(channel.getName(), String.format("Du kannst dich nicht selbst mit jemanden verkuppeln.", target.getName()));
                return;
            }
        

        if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
        
        
      /*  if (!client.getAmorsarrow().isEmpty()) {
            Client targetold = Server.get().getClient(client.getAmorsarrow());
                if (targetold == null) {
                    targetold = new Client(null);
                    targetold.loadStats(client.getAmorsarrow());
                }
            
            client.sendButlerMessage(channel.getName(), String.format("Du hast bereits mit "+targetold.getName()+ " eine Amor-Verbindung. Um einen neuen Partnerlook zu erstellen, musst du zuerst deinen alten Partnerlook mit _/partnerlook !_ auflösen.", target.getName()));
                
            return;
        }*/
            
           
          

        
        client.sendButlerMessage(channel.getName(),"Amors Pfeil macht sich jetzt auf den Weg zu "+target.getName()+" und "+target2.getName()+".");
        target.sendButlerMessage(channel.getName(),client.getName()+" hat Amor's Pfeil auf dich und "+target2.getName()+" geschossen. _°BB>Annehmen?|/amorsarrow ok<r°_");    
        target2.sendButlerMessage(channel.getName(),client.getName()+" hat Amor's Pfeil auf dich und "+target.getName()+" geschossen. _°BB>Annehmen?|/amorsarrow ok<r°_");   
        
            

           
       target2.setAmorsarrowAkzeptFrom("0");
       target2.setAmorsarrowAkzeptTo("0");
       target.setAmorsarrowAkzeptFrom("0");
       target.setAmorsarrowAkzeptTo("0");
        
        target.setAmorsarrowFrom(target2.getName());
        target2.setAmorsarrowTo(target.getName());
        target.setAmorsarrowNick(client.getName());
        target2.setAmorsarrowNick(client.getName());
        target.setAmorsarrowTimeout(((System.currentTimeMillis()/1000)+60)+""); // 1 min timeout
        target2.setAmorsarrowTimeout(((System.currentTimeMillis()/1000)+60)+""); // 1 min timeout
        ft.setBan(l[1],l[3],l[4],client); 
                
       }
}
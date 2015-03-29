package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class konfettibombe {
  public static void functionMake(Client client,Channel channel, String arg) {
 Long time = System.currentTimeMillis()/1000; 
                 
  String[] l = client.getFeature("Konfettibombe");
 Feature ft = Server.get().getFeature("Konfettibombe");
 
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
                
                client.setKonfetti(true);
                Server.actions.put(time, new String[] {"resetKonfetti", client.getName(), client.getName(), channel.getName(), "600"});
                channel.broadcastButlerMessage(String.format("In einem karnivalistischem Akt wirft °>_h%s|/serverpp \"|/w \"<° eine Konfettibombe durch den Channel...°>{sprite}type:confetti<°", client.getName()));
                ft.setBan(l[1],l[3],l[4],client); 
            
          
      }}
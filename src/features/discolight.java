package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class discolight {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   
              // wichtig            
  String[] l = client.getFeature("Discolight");
 Feature ft = Server.get().getFeature("Discolight");
 
 if (ft == null) {
     // kick vermeiden
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
              // ende    
                
                client.setDiscolight(true);
                Server.actions.put(time, new String[] {"resetDiscolight", client.getName(), client.getName(), channel.getName(), "600"});
                channel.broadcastButlerMessage(String.format("°>_h%s|/serverpp \"|/w \"<° startet eine Party! °>{sprite}type:disco<°", client.getName()));
                ft.setBan(l[1],l[3],l[4],client); // setz sperreei 
               
          
          
      }}
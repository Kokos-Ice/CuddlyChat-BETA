package features;

import static features.hero.timeStampToDate;
import static features.loveletter.timeoutseconds;
import starlight.*;
import tools.*;
import java.util.*;




public class exit {
          public static Timer timer = new Timer();
          public static String toremove ="";
             public static Map<Long,String> users = new HashMap<Long,String>(); 
             public static int timeoutseconds = 5;
     public static String image(Client client, Client target) {
        if(target.getGender() == 2 && client.getGender() == 2) return "ff";
        else if(target.getGender() == 2 && client.getGender() == 1) return "mf";
        else if(target.getGender() == 1 && client.getGender() == 2) return "fm";
        else return "mm";    
    }
          
  public static void functionMake(Client client,Channel channel, String arg) {
      
      
       String[] l = client.getFeature("Exit-Nachricht");
 Feature ft = Server.get().getFeature("Exit-Nachricht");
 
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
      
    
        arg = KCodeParser.parse(client, arg, 0, 10, 20);
                arg = Server.get().parseSmileys(client, arg);
                channel.broadcastPicAction(">>", String.format("°>_h%s|/serverpp \"|/w \"<° verlässt uns nun mit den Worten:##°>CENTER<r°\\\" %s°r° \\\"°BB°#°>LEFT<°", client.getName(), arg), String.format("actExit_%s.png", image(client, client)));
                ft.setBan(l[1],l[3],l[4],client); // setz sperre
                users.put(System.currentTimeMillis()+(timeoutseconds*1000),client.getName());
 
    timer.schedule(new TimerTask() {
            public void run() {
             for(Long key : users.keySet()) {
                Long time = key;
              if (time < System.currentTimeMillis()) {                 
                   if (!toremove.contains("|"+key+"|")) {
                   toremove += "|"+key+"|";                 
                
                 Client target = Server.get().getClient(users.get(key));
             if (target != null) {
             target.logout("Ausgeloggt.");
             }
            } }}
             
             
             
                for(String f : toremove.split("\\|")) {
                if (!f.isEmpty()) {
                    users.remove(Long.parseLong(f));
                   }
             }
            }
            
                      
            }, 0, 1000);
    
}
}

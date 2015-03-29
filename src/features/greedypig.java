package features;

import java.util.ArrayList;
import starlight.*;
import tools.*;
import features.*;
import static features.hero.timeStampToDate;
import static tools.ModuleCreator.main;

 public class greedypig  {

           
     private static boolean isInteger(String s) {
 try {
  Integer.parseInt(s);
     return true;
    }
    catch (NumberFormatException e)
   {
    }

     return false;
   }
    

     public static void functionMake(Client client,Channel channel, String arg) {
         
         
          
        if (channel.getFeatures() != null) {
               client.sendButlerMessage(channel.getName(),"In diesem Channel läuft bereits ein Spiel.");
           return;
            
        }
       
        String[] l = client.getFeature("Greedy-Pig");
            Feature ft = Server.get().getFeature("Greedy-Pig");

            if (ft == null) {
                return;
            }

            if (l[0].equals("0")) {
                client.sendButlerMessage(channel.getName(), "Du hast das " + ft.getName() + " Feature nicht.");
                return;
            }
            if (l[0].equals("1")) {
                client.sendButlerMessage(channel.getName(), "Du kannst das Feature " + ft.getName() + " erst am " + timeStampToDate(Long.parseLong(l[5])) + " Uhr wieder nutzen.");
                return;
            }      



 
        
        if (!isInteger(arg)) {
            client.sendButlerMessage(channel.getName(),"Mit wievielen Knuddels möchtest du starten?");
            return;
        }
        
        int kn = Integer.parseInt(arg);
        
        if (kn > client.getKnuddels()) {
            client.sendButlerMessage(channel.getName(),"Du hast nicht so viele Knuddels.");
            return;
        }
        
           if (kn < 5) {
            client.sendButlerMessage(channel.getName(),"Der Mindesteinsatz beträgt 5 Knuddels.");
            return;
        }

client.setKnuddels((int)client.getKnuddels()-kn);

  ft.setBan(l[1], l[3], l[4], client); // setz sperre
channel.setFeatures("GREEDY");
channel.setFeaturesname("Greedy-Pig");
channel.getFeatures().parseCommand("/start", Server.get().getButler(), new String[] {String.valueOf(kn),client.getName()});     
         
        
     }}
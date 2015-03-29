
package features;


import static features.hero.timeStampToDate;
import starlight.*;
import tools.*;
import tools.popup.*;
import java.util.*;

public class kissall {
  
      private static String[] kissall = {"[C] erprobt heute offensichtlich die eigenen Kusstechniken - und das an [T] zugleich!", "[C] sprüht nur so vor Glück und küsst [T] und damit den ganzen Channel ab.", "Früh übt sich, wer ein Meister werden will: [C] küsst nach der Reihe [T] und damit den ganzen Channel!"};   
  private static String[] kissall2 = {"Da kann man nur hoffen, dass [C] nicht schon bald der Kusskrankheit erliegt - auf einen Schlag _alle [Z] Anwesenden zu küssen_, ist schon eine ordentliche Leistung!"};   
  
      
    public static void functionMake(Client client,Channel channel, String arg) {
 
                    
                    String[] l = client.getFeature("Kissall");
 Feature ft = Server.get().getFeature("Kissall");
 
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
 
            StringBuilder nicks = new StringBuilder();
            int lala = 1;
            
            for(Client c : channel.getClients()) {
                if(!c.getName().equals(client.getName())) {
                    nicks.append(lala!=1?", ":"").append("°>_h").append(c.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                    c.increaseKisses();
                    lala++;
                }
            }
            
            String image = "";
            
            if(client.getGender() == 1) {
                image = "m";
            } else if(client.getGender() == 2) {
                image = "f";
            }
            String text = "";
            if (lala >= 50) {
            text = kissall2[new Random().nextInt(kissall2.length)].replace("[C]", client.getName()).replace("[T]", nicks.toString()).replace("[Z]",lala+"");
                 
            } else {
           text = kissall[new Random().nextInt(kissall.length)].replace("[C]", client.getName()).replace("[T]", nicks.toString());
            }
            
            channel.broadcastPicAction(">", text, String.format("actKissAll_%s.png", image));         
            
              ft.setBan(l[1],l[3],l[4],client); // setz sperre
        
    }}
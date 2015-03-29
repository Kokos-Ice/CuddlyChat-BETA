package features;

import static features.hero.timeStampToDate;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;

public class loveyou {
  
    public static void functionMake(Client client,Channel channel, String arg) {
 

        
                String[] l = client.getFeature("Loveyou");
               Feature ft = Server.get().getFeature("Loveyou");

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
            
            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/loveyou NICK#(Du liebst NICK.)");
                return;
            }
            
            if(arg.equals("-")) {
                client.sendButlerMessage(channel.getName(), "Du liebst nun niemanden mehr.");
                client.setLoveyou(null);
                return;
            }
            
            
            
            if(target == null) {
                target = new Client(null);
                target.loadStats(arg);
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }
            }
            
            if(target.getName().equals(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Schön dass du dich selbst liebst.");
                return;
            }
            
             String cg = "male";
             String tg = "male";
             if (target.getGender() == 2) {
                 tg = "female";
             }
             if (client.getGender() == 2) {
                 cg = "female";
             }
             
             
          String fts = "";
      if (target.getPhoto().trim().isEmpty()) {
if (target.getGender() == 1) {
fts = "nopic_79x79_m";
} else if (target.getGender() == 2) {
fts = "nopic_79x79_f";
}} else {
fts = target.getPhoto();
}
      
      
                  String fc = "";
      if (client.getPhoto().trim().isEmpty()) {
if (client.getGender() == 1) {
fc = "nopic_79x79_m";
} else if (client.getGender() == 2) {
fc = "nopic_79x79_f";
}} else {
fc = client.getPhoto();
}
      
      
      fc = "photos/photo/getPicture.php?m&img="+fc;
      fts = "photos/photo/getPicture.php?m&img="+fts;
                     channel.broadcastAction(">>","_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ °BB°gesteht _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ °BB°seine tiefe Liebe.#°+9510r>{table|w2|180|37|180|w2}<°°>{tc}<°°>CENTER<°_°>_h"+client.getName()+"|/serverpp "+client.getName()+"|/w "+client.getName()+"<° ("+client.getAge()+") °>gender_"+cg+"...b.h_0.png<°°b°°>{tc}<°°>CENTER<RR°_liebt°°°b>{tc}<°°>CENTER<°_°>_h"+target.getName()+"|/serverpp "+target.getName()+"|/w "+target.getName()+"<° ("+target.getAge()+") °>gender_"+tg+"...b.h_0.png<°°b°°>{endtable}<°#°+9518>{table|w2|100|118|100|0|w2}<°°>{tc}<°°>transparent1x1...h_80.w_83.mousex_-1.mousey_-41.gif<>--<>_h|/foto "+client.getName()+"<>--<>"+fc+"...w_0.center_140.shadow_4.mx_-80.border_3.my_-50.h_0.jpg<>--<>_h|/foto "+client.getName()+"<°°>{tc}<°°>{tc}<°°>transparent1x1...h_80.w_83.mousex_-1.mousey_-41.gif<>--<>_h|/foto "+target.getName()+"<>--<>"+fts+"...w_0.center_140.shadow_4.mx_-80.border_3.my_-50.h_0.jpg<>--<>_h|/foto "+target.getName()+"<°°>{tc}<°°>features/love-you/ft_in-love_foto-connection...w_0.h_0.mx_-228.my_50.png<°°>features/love-you/ft_love-you_chat-hearts...w_0.h_0.mx_-300.my_50.alwayscopy.gif<°°>{endtable}<°#°>LEFT<bir°§");
     
                     
           client.setLoveyou(target.getName());
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Jemand liebt dich.", ""+client.getName()+" hat soeben öffentlich bekannt gegeben, dass "+client.getGenderLabel()+" dich liebt.", (System.currentTimeMillis()/1000)); 
            ft.setBan(l[1],l[3],l[4],client);
    }
}
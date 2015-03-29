package features;

import static features.hero.timeStampToDate;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;

public class skymsg {
  
    public static void functionMake(Client client,Channel channel, String arg) {
 

        
                String[] l = client.getFeature("Skymsg");
               Feature ft = Server.get().getFeature("Skymsg");

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

               


        	
        	arg = KCodeParser.noKCode(arg);
        	if (arg.isEmpty())  {
                    client.sendButlerMessage(channel.getName(),"Bitte die Funktion wie folgt verwenden......");
                    return;
    }
                
           channel.broadcastButlerMessage("°>sm_plane_feature.gif<° von " + client.getName() + " macht sich soeben auf den Weg...°>{sprite}type:2|startpause:3000|flytime:1000|text:"+arg+"<°");
                ft.setBan(l[1],l[3],l[4],client); // setz sperre
                
          
      }}
               
               
               


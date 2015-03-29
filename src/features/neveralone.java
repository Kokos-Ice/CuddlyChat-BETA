package features;

import static features.hero.timeStampToDate;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;

public class neveralone {
  
    public static void functionMake(Client client,Channel channel, String arg) {

            String[] l = client.getFeature("Neveralone");
             Feature ft = Server.get().getFeature("Neveralone");

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
               
            arg = KCodeParser.escape(arg);
            Client target = Server.get().getClient(arg);
           
            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/neveralone Nick#(Immer für NICK da sein.)");
                return;
            }
           
            if(arg.equals("-")) {
                client.sendButlerMessage(channel.getName(), "Profileintrag gelöscht.");
                client.setNeveralone(null);
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
                client.sendButlerMessage(channel.getName(), "Du kannst nicht für dich selbst da sein!");
                return;
            }
           
            channel.broadcastAction("°BB°", String.format("> °>features/neveralone/neveralone_mf...b.my_10.w_40.h_27.png<° _°>_h%s|/serverpp \"|/w \"<°_ ist immer für _°>_h%s|/serverpp \"|/w \"<°_ da.", client.getName().replace("<", "\\<"), target.getName().replace("<", "\\<")));
            client.setNeveralone(target.getName());
            ft.setBan(l[1],l[3],l[4],client); // setz sperre


    }
}
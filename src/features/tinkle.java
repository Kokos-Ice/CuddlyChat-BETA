package features;

import static features.hero.timeStampToDate;
import starlight.*;

public class tinkle {

public static void functionMake(Client client,Channel channel, String arg) {

    

 
if (arg.isEmpty()) {
client.sendButlerMessage(channel.getName(), "Bitte verwende den Befehl so: /tinkle + oder /tinkle -");
return;
}

if (arg.equals("+")) {

 
            String[] l = client.getFeature("Tinkle");
 Feature ft = Server.get().getFeature("Tinkle");
 
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
           
    
client.sendButlerMessage(channel.getName(),"Die Klingel wird nun in deinem Profil angezeigt.");
client.setTinkle(1);
 ft.setBan(l[1],l[3],l[4],client); 
return;
} else if (arg.equals("-")) { 
    
 
client.sendButlerMessage(channel.getName(),"Die Klingel wird nun nicht mehr in deinem Profil angezeigt.");
client.setTinkle(0);

return;
} else {

Client target = Server.get().getClient(arg);
boolean online = true;

if (target == null) {
online = false;
target = new Client(null);
target.loadStats(arg);

if(target.getName() == null) {
client.sendButlerMessage(channel.getName(), "Wer soll denn "+arg+" sein?");
return;
}
}


if (!online) {
client.sendButlerMessage(channel.getName(), "Du kannst diese Funktion nicht anwenden, wenn _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ offline ist.");
return;
}


if (client == target) {
client.sendButlerMessage(channel.getName(), "Du kannst diese Funktion nicht auf dich selbst anwenden.");
return;
}

if (target == Server.get().getButler()) {
client.sendButlerMessage(channel.getName(),Server.get().getButler().getName()+" kannst du nicht auswählen.");
return;
}

if (target.getTinkle() == 0) {
client.sendButlerMessage(channel.getName(),target.getName()+" kann nicht angeklingelt werden.");

return;
}
if (!client.getTinkleLast().isEmpty()) {
client.sendButlerMessage(channel.getName(),"Du hast vor weniger als 10 Minuten bereits jemanden angeklingelt. Bitte gedulde dich ein wenig, bevor du es erneut versuchst.");
return;
}

client.sendButlerMessage(channel.getName(),"Du hast _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ soeben angeklingelt.°>pics/features/twinkle/bellping.mp<°");
target.sendButlerMessage(target.getChannel().getName(),"_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ möchte dringend mit dir sprechen.°>pics/features/twinkle/bellping.mp<°");
client.setTinkleLast(String.valueOf(((System.currentTimeMillis()/1000)+600)));
 

}
}}
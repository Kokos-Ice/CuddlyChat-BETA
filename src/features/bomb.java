package features;

;
import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import starlight.*;
import static starlight.Server.replaceLast;

public class bomb {
        
public static String bombe = "";

public static void checkBombe() {
     String nicks = "°>_h"+Server.get().getButler().getName()+"|/serverpp \"|/w \"<°";
            
    for(String x : bombe.split("\\|")) {
        if (!x.isEmpty()) {
            String[] a = x.split("~");
            Long bis = Long.parseLong(a[1]);
           if (bis < (System.currentTimeMillis()/1000)) {
                String von = a[0];
                String hat = a[4];
                String channels = a[3];
                Channel channel  = Server.get().getChannel(channels);
          bombe = bombe.replace("|"+x+"|","");
          Client client = Server.get().getClient(von);
          if (client == null) { client = new Client(null); client.loadStats(von); }
           Client target = Server.get().getClient(hat);
          if (target == null) { target = new Client(null); target.loadStats(hat); }
          
          target.removeIcon("pics/devilbomb_nicklist.gif"); 
          target.setDevilsbomb(0);
          
          int max = (int)target.getKnuddels()-2;
         if (target.getKnuddels() >= 2) {
          int c = channel.getClients().size();
          if (channel.getClients().contains(target)) {
              c = c-1;
          } 
          c = c-1;
          if (max > 23) {
              max = 23;
          }
          int rip = 0;
          if (c < max) {
              rip = c;
              if (c > 4) {
              rip = new Random().nextInt((c-2))+3;
              }
          } else {
              rip = new Random().nextInt((max-2))+3;             
              
          }
           int senderkn = 2;
          int rest = (max-rip)-5;
          if (rest >= 1) {
          int dazu =new Random().nextInt(rest)+1;
          senderkn = senderkn+dazu;
          if (senderkn > 10) {
              senderkn = 10;
          }
          }
         int abzug = senderkn+rip+1;
          String betrefft = "Die DevilsBomb hat dich erwischt";
          String textt = "Durch die Explosion der von °>_h"+von+"|/serverpp \"|/w \"<° gelegten DevilsBomb sind dir insgesamt "+abzug+" Knuddel durch die Lappen gegangen.";
          target.setKnuddels((int)target.getKnuddels()-abzug);
          String betreffc = "Knuddel abgestaubt";
          String textc = "Nach der Explosion deiner DevilsBomb konntest du von °>_h"+target.getName()+"|/serverpp \"|/w \"<° noch _"+senderkn+" Knuddel abstauben_!";
          client.setKnuddels((int)client.getKnuddels()+senderkn);
          client.sendMail(Server.get().getButler().getName(),textc, betreffc,"",1,"0"); 
          target.sendMail(Server.get().getButler().getName(),textt, betrefft,"",1,"0");

          
          target.saveStats();
          client.saveStats();
          
          
         Server.get().getButler().setKnuddels((int)Server.get().getButler().getKnuddels()+1);
          Server.get().getButler().saveStats();
          List list = new ArrayList<Client>();
          for(Client cc : channel.getClients()) {
               if(cc != target && cc != Server.get().getButler()){
                   list.add(cc);
               }
          }
          Collections.shuffle(list);
          
         
          for(int i=0;i<rip;i++) {
              Client as = (Client) list.get(i);
             
                 nicks += ", ";
                 
                 nicks += "°>_h"+as.getName()+"|/serverpp \"|/w \"<°";
                 as.setKnuddels((int)as.getKnuddels()+1);
              }
            
              
          }
          
          
        if (max > 0) {
          String text = "";
          int t = new Random().nextInt(2)+1;
          if (t == 1) {
          text = "°BB°> _PENG!_ Da ist es passiert! Die _DevilsBomb_ °>devilbomb_a.b.gif<° explodiert in den Händen von _°>_h"+hat+"|/serverpp \"|/w \"<°_. Zwischen den Staubwolken rieselt von _°>_h"+hat+"|/serverpp \"|/w \"<°_ jeweils 1 Knuddel auf "+replaceLast(nicks,", "," & ")+" herab.";
          } else {
          text = "°BB°> _KAWUMM!_ macht die _DevilsBomb_ °>devilbomb_a.b.gif<°, gerade als _°>_h"+hat+"|/serverpp \"|/w \"<°_ sie weiterwerfen wollte. Von _°>_h"+hat+"|/serverpp \"|/w \"<°_ geht jeweils 1 Knuddel an "+replaceLast(nicks,", "," & ")+".";
          }
          channel.broadcastAction(">>>",text);
         }
        }
        }        
        
        
    }
    
    
}

   public static void functionMake(Client client,Channel channel, String arg) {
 
        String[] l = client.getFeature("DevilsBomb");
            Feature ft = Server.get().getFeature("DevilsBomb");

        if (client.getDevilsbomb() == 0) {
      
            
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
            
        }
       
       
if (arg.isEmpty()) {
    client.sendButlerMessage(channel.getName(),"Bitte wende diese Funktion folgendermaßen an: _/bomb NICK_");
    return;
}
    

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
client.sendButlerMessage(channel.getName(), target.getName()+" ist _offline!_");
return;
}

if (!channel.getClients().contains(target)) {
client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
return;
}
if (client == target) {
client.sendButlerMessage(channel.getName(), "Du hast bereits die Bombe...");
return;
}
   
if (target == Server.get().getButler()) {
    client.sendButlerMessage(channel.getName(),"°>_h"+target.getName()+"|/serverpp \"|/w \"<° kannst du nicht auswählen.");
    return;
}


if (target.haveSchutzschild()) {
     client.sendButlerMessage(channel.getName(),"Eine fast durchsichtige Aura bildet ein Schutzschild °>features/schutzschild/ft_schutzschild_knuddel...b.h_20.w_24.my_14.mx_-10.png<° um °>_h"+target.getName()+"|/serverpp \"|/w \"<°, sodass du diese Funktion jetzt nicht anwenden kannst.");
     return;
 }
if (target.getDevilsbomb() == 1) {
    client.sendButlerMessage(channel.getName(),"Du kannst die DevilsBomb aus folgendem Grund nicht an °>_h"+target.getName()+"|/serverpp \"|/w \"<° weiter geben: _°>_h"+target.getName()+"|/serverpp \"|/w \"<° hat bereits eine Bombe. Eine zweite wäre wirklich etwas zu viel...");
    return;
}
if (target.getKnuddels() < 25) {
    client.sendButlerMessage(channel.getName(),"Du kannst die DevilsBomb aus folgendem Grund nicht an °>_h"+target.getName()+"|/serverpp \"|/w \"<° weiter geben: _°>_h"+target.getName()+"|/serverpp \"|/w \"<° hat zu wenige Knuddel.");
    return;
}
if (client.getDevilsbomb() == 1) {
   boolean found = false;
    for(String x : bombe.split("\\|")) {
        if (!x.isEmpty()) {
            if (x.endsWith("~"+client.getName()) && x.contains(";"+target.getName()+";")) {
                found = true;
            }            
        }        
    }
if (found) {
    client.sendButlerMessage(channel.getName(),"Du kannst die DevilsBomb aus folgendem Grund nicht an °>_h"+target.getName()+"|/serverpp \"|/w \"<° weiter geben: _°>_h"+target.getName()+"|/serverpp \"|/w \"<° hatte die Bombe schon einmal und kann sie kein zweites Mal erhalten_.");
    return;
}
}
if (client.getDevilsbomb() == 0) {   
     ft.setBan(l[1], l[3], l[4], client);  
   int r = new Random().nextInt(241)+60; 
  Long ende = (System.currentTimeMillis()/1000)+r;
    bombe += "|"+client.getName()+"~"+ende+"~;"+client.getName()+";;"+target.getName()+";~"+channel.getName()+"~"+target.getName()+"|";
    int rs = new Random().nextInt(2)+1;
   String text = "";
    if (rs == 1) {
       text = "°BB°> _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ zündet eine _DevilsBomb_ °>devilbomb_a.b.gif<° und katapultiert sie schnurstracks in Richtung °>_h"+target.getName()+"|/serverpp \"|/w \"<°."; 
    } else {
      text = "°BB°> _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ zündet eine _DevilsBomb_ °>devilbomb_a.b.gif<° und wirft sie im hohen Bogen genau zu °>_h"+target.getName()+"|/serverpp \"|/w \"<°.";
    }
    channel.broadcastAction(">>>",text);
} else {
  
   
    String neu = "";
    for(String a : bombe.split("\\|")) {
        if (!a.isEmpty()) {
        if (a.endsWith("~"+client.getName())){
          String[] v = a.split("~");
          neu += "|"+v[0]+"~"+v[1]+"~"+v[2]+";"+target.getName()+";~"+channel.getName()+"~"+target.getName()+"|";
        } else {
            neu += "|"+a+"|";
        }
            
        }
    }
    bombe = neu;
    String text = "";
     int rs = new Random().nextInt(4)+1;
      if (rs == 1) {
text = "°BB°> _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ wirft die _DevilsBomb_ °>devilbomb_a.b.gif<° geschwind zu °>_h"+target.getName()+"|/serverpp \"|/w \"<°. Puhhh....";
      } else  if (rs == 2) {
text = "°BB°> _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ wirft die _DevilsBomb_ °>devilbomb_a.b.gif<° ganz schnell zu °>_h"+target.getName()+"|/serverpp \"|/w \"<°.";
      } else  if (rs == 3) {
text = "°BB°> _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ wirft die _DevilsBomb_ °>devilbomb_a.b.gif<° flink zu °>_h"+target.getName()+"|/serverpp \"|/w \"<°.";
      } else {
text = "°BB°> _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ wirft die _DevilsBomb_ °>devilbomb_a.b.gif<° hastisch nach °>_h"+target.getName()+"|/serverpp \"|/w \"<°.";
      }
    channel.broadcastAction(">>>",text);
}
target.sendButlerMessage(channel.getName(),"_°RR°ACHTUNG!_°r° _"+client.getName()+"_ hat dir gerade eine _°BB>DevilsBomb|/h \"<r°_ °>devilbomb_a.b.gif<° zugeworfen. Reiche sie schnell mit _/bomb NICK_ an jemanden weiter, bevor es zu spät ist!");
client.removeIcon("pics/devilbomb_nicklist.gif"); 
client.setDevilsbomb(0);
target.setDevilsbomb(1);
target.addIcon("pics/devilbomb_nicklist.gif", 18);
}
    
}

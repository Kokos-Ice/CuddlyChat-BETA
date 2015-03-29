package features;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class bart {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   
                            // wichtig            
  String[] l = client.getFeature("Bart");
 Feature ft = Server.get().getFeature("Bart");
 
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

            if(arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte diese Funktion folgendermaßen benutzen:#/bart TEXT oder /bart NICK:TEXT");
                return;
            }
            
            if(arg.contains(":")) {
                String nick = arg.split(":", 2)[0];
                arg = arg.split(":", 2)[1];
                Client target = Server.get().getClient(nick);
                if(target == null) {
                    target = new Client(null);
                    target.loadStats(nick);

                    if(target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), CommandParser2.unknownUser(nick));
                        return;
                    } 
                    client.sendButlerMessage(channel.getName(), CommandParser2.userIsOffline(target));
                    return;
                }
                
                arg = KCodeParser.noKCode(arg);
                arg = arg.replace(" ", "+");
                channel.broadcastAction(">", String.format("°>_h%s|/serverpp \"|/w \"<° findet, dass °>_h%s|/serverpp \"|/w \"<° eindeutig den Channel stört:#", client.getName(), target.getName())+"#°%15°"+String.format("°>features/bart.php?val=%s#.gif<°°>sounds/tafel.mp<°", arg));
                ft.setBan(l[1],l[3],l[4],client); // setz sperre
                return;                
            }

            arg = KCodeParser.noKCode(arg);
            arg = arg.replace(" ", "+");
            channel.broadcastAction(">", String.format("°>_h%s|/serverpp \"|/w \"<° hat wohl heute wieder Langeweile:#", client.getName())+"#°%15°"+String.format("°>features/bart.php?val=%s#.gif<°°>sounds/tafel.mp<°", arg));
                ft.setBan(l[1],l[3],l[4],client); // setz sperre
                
           
          
      }}
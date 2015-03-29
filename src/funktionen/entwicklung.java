package funktionen;


import starlight.*;
import tools.*;

public class entwicklung {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
        if(!client.hasPermission("cmd.entwicklung")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
         if (arg.isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/entwicklung NICK#(entwickelt alle Basic-Smileys von NICK)");
             return;
        }
       
          String nickname = KCodeParser.escape(arg);
            Client target;
            boolean online = true;

            if (nickname.isEmpty() || nickname.equalsIgnoreCase(client.getName())) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nickname);

                    if (target.getName() == null) {
                      client.sendButlerMessage(channel.getName(), "Wer soll den "+arg+" sein?");
                      return;
                    }
                }
            }
            
       
       
         
          for(String id : target.getSmileys().split("%%")) {
            if (!id.isEmpty()) {
            Usersmiley d  =Server.get().getUsersmiley(id);
            d.SmileyEntwicklung(true); // true = alle
        }}
          Server.get().newSysLogEntry(client.getName(), String.format("Smileys von %s entwickelt", target.getName()));
          client.sendButlerMessage(channel.getName(),"Smileys von "+target.getName()+" entwickelt.");        
        
    }}
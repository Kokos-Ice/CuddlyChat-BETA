package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.countChars;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class showfriends {


    public static void functionMake(Client client,Channel channel, String arg) {
 if (!client.hasPermission("cmd.betafriends")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }
     
            
            if (arg.equals("config:all")) {
                client.sendButlerMessage(channel.getName(),"Jeder kann deine Freundesliste nun einsehen.");
                client.setSeeFriends("all");
                return;
            }
              if (arg.equals("config:friends")) {
                 client.sendButlerMessage(channel.getName(),"Nur deine Freunde können deine Freundesliste nun einsehen.");
                client.setSeeFriends("onlyfriends");
                return;
            }
                if (arg.equals("config:me")) {
                client.sendButlerMessage(channel.getName(),"Nur du kannst deine Freundesliste nun einsehen.");
                client.setSeeFriends("onlyme");
                return;
            }
            
            String nickname = KCodeParser.escape(arg);

            Client target;
            boolean online = true;
            boolean ent = false;

            if (!nickname.isEmpty() && arg.startsWith("+") && client.hasPermission("cmd.betafriends")) {
                ent = true;
                nickname = nickname.substring(1).trim();
            }

            if (nickname.isEmpty() || nickname.equalsIgnoreCase(client.getName())) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nickname);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                      return;
                    }
                }
            }
            if (target != client) {
            if (!target.getSeeFriends().equals("all")) {
                
                if (target.getSeeFriends().equals("onlyfriends")) {
                    if (!target.checkFriend(client.getName())) {
                        client.sendButlerMessage(channel.getName(),"Nur Freunde von "+target.getName()+" können die Freundesliste einsehen.");
                        return;
                    }
                } else  if (target.getSeeFriends().equals("onlyme")) {
                    if (target != client) {
                        client.sendButlerMessage(channel.getName(),"Nur "+target.getName()+" kann die Freundesliste einsehen.");
                        return;
                    }
                }
            }
            }

            String nick = target.getName();
            String charNick = nick.replace("<", "\\<");
            //holt er von hier!!!
             
           
            StringBuilder friends = new StringBuilder("#");
            StringBuilder same = new StringBuilder();
            StringBuilder alle = new StringBuilder();
            int s = 0;
            int a = 0;
            for (String nicks : target.getFriendlist().split("\\|")) {
                if (!nicks.isEmpty()) {
                    if (client.checkFriend(nicks)) {
                  
                        if (s != 0) {
                            same.append(", ");
                        }
                       same.append("_°BB>_h"+nicks+"|/w "+nicks+"<r°_");
                       s++;
                      
                    }
                        
                       alle.append("°%00°#_°BB>_h"+nicks+"|/w "+nicks+"<°_ °r°°%50°");
                       if (!nicks.equals(client.getName())) {  
                       if (!client.checkFriend(nicks)) {
                            alle.append("°>Hinzufügen|/f "+nicks+"<°");
                        } else {
                               alle.append("°>Entfernen|/f !"+nicks+"<°");
                        }} else {
                            alle.append("Das bist du!");
                       }
                       
                       a++;
                    }
                       
                    
                   
                }
            
            
            
             if (target == client) {
          
            if (!target.getFriendask().isEmpty()) {
               
                 friends.append("°17°Ausstehende Freundesanfragen ("+(countChars(target.getFriendask(),'|')/2)+"):_#§");
            
                for(String x : target.getFriendask().split("\\|")) {
                    if (!x.isEmpty()) {
                   friends.append("°%00°#_°BB>"+x.split("~")[0]+"|/serverpp "+x.split("~")[0]+"|/w "+x.split("~")[0]+"<r°_°%50°°>Annehmen|/f +"+x.split("~")[0]+"<°   |   °>Ablehnen|/f -"+x.split("~")[0]+"<°") ;    
                }}
                
                friends.append("#°%00°#");
                
            friends.append("°17°##_Deine Freunde ("+a+"):§#");
            }
        }

           if (client != target) {
               if (s != 0) {
             friends.append("°17°_Gemeinsame Freunde ("+s+"):_ "+same.toString()+"§");
            friends.append("°-°");
           }}
              //  else
           
           
          
           if (client != target && a >= 1) {
               
            friends.append("°17°##_Freunde ("+a+") von "+target.getName());
            friends.append(":§#");
           }
           
          
           
           
           
            friends.append(alle.toString());
          
            friends.append("°-°");
 String title = String.format("Freunde ("+a+") von " + target.getName());
           
 
 
 
             if (client == target) {
                 
                 
                title = "Deine Freunde ("+a+")";
            }
             
            Popup popup = new Popup(title, title, friends.toString(), 460, 350); // Fenster Größe Breite und Höhe.
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   OK   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            popup.setModern(1);
            client.send(popup.toString());
}
}
package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class showhighlights {


    public static void functionMake(Client client,Channel channel, String arg) {
 if (!client.hasPermission("cmd.showhighlights")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg);

            Client target;
            boolean online = true;
            boolean ent = false;

            if (!nickname.isEmpty() && arg.startsWith("+") && client.hasPermission("cmd.showhighlights")) {
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

            String nick = target.getName();
            String charNick = nick.replace("<", "\\<");
            //holt er von hier!!!
            String title = String.format("Highlights");
            StringBuilder ahelp = new StringBuilder("#");
            int eE = 1, dp = 1, spaces = 0;

   
             ahelp.append("°[137,138,142]°°14°");   
              int showbox = 0;
              int caraus = 1;
              StringBuilder box = new StringBuilder();
           
              int s = target.getHighlights().split("\\|").length/2;
         
              showbox = 1;
              if(!target.getHighlights().isEmpty()) {
                
            	for(String out : target.getHighlights().split("\\|")) {
            		if(!out.isEmpty()) {
            			String datum = out.split(" ")[0];
            			String text = out.split(" ", 2)[1];
                             
                                      String old = box.toString();
                                      String icon = "";

if(text.contains("Knutschfleck")) {
    icon = "°>w2/icons/sm_kissingcouple_blue...my_-3.h_0.w_0.gif<°";
}
else if(text.contains("Rose")) {
     icon = "°>w2/icons/sm_rosecavalier_blue...my_-3.h_0.w_0.gif<°";
}
                          box.setLength(0);
    			   box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°K°"+text+"°b°°K° °[137,138,142]°"+datum+" "+icon+"°K° #°+0000+9502°"+old);
                                
                             
                                caraus++;
                        }}}
              
            
                  ahelp.append("°[174,174,255,127]°°bir°°12°_°+0007°°+9505+0000+5005°°b°");          
                  ahelp.append(box.toString());
              
            
            PopupNewStyle popup = new PopupNewStyle(title, title, ahelp.toString(), 450, 425); // Fenster Größe Breite und Höhe.
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   OK   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            client.send(popup.toString());
      
            
    }
}
            
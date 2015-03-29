package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class deletemynick {


    public static void functionMake(Client client,Channel channel, String arg) {
 if (client.getRank() > 2 || channel.checkCm(client.getName()) || channel.checkHz(client.getName()) || client.getTeams().contains("~1|") || client.getTeams().contains("~0|")) {
                client.sendButlerMessage(channel.getName(), String.format("Dein Nickname verfügt derzeit über Sonderrechte, und kann daher nicht gelöscht werden.", client.getName()));
                return;
            }
 
 
            String title = "Sicherheitsfrage - Benutzerkonto";
            String content = "°14+0006+9505°_Nickname wirklich löschen?_#°+9510+0000>{table|8|8|180|8|8|w1}<°°>{tc}<[203,203,255]>{colorboxstart}<K°°>{tc}<11+9508°°14°Sind sie sich sicher das sie ihr vorhandendes Benutzerkonto auflösen und löschen wollen?#Die Vorhandenen Knuddels, Smileys und Feature sowie Einträge & Statistiken gehen dabei verloren#Sind sie sich sicher?..°>{tc}<>transparent1x1...w_0.h_115.gif<[203,203,255]>{colorboxend}<K°°>{tc}<>{tc}<>center<>sm_maumau_blowCards_001...b.my_7.h_26.gif<>transparent1x1...b.w_0.h_50.gif<°#°+9510>left<>smileybox/line2...mw_90.png<°#°+9510>bullet2.png<°#°+9510>bullet2.png<°°>{endtable}<°##°11+9505>center<°_°>{button}Konto löschen||call|/deletenick|heigth|24<°_  _°>{button}Abbrechen||call|_c|width|70|heigth|24<°_";

            Popup popup = new Popup(title, title, content.toString(), 400, 250);
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   OK   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            popup.setModern(1);
            client.send(popup.toString());
         
            

            
            {

                Server.get().query(String.format("update accounts set deletenick='%s' where name='%s'", 1, client.getName()));
            }

            return;
}
}
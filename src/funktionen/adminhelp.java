package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class adminhelp {


    public static void functionMake(Client client,Channel channel, String arg) {
 if (!client.hasPermission("cmd.adminhelp")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg);

            Client target;
            boolean online = true;
            boolean ent = false;

            if (!nickname.isEmpty() && arg.startsWith("+") && client.hasPermission("cmd.adminhelp")) {
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
            String title = String.format("Übersicht - Adminfunktionen");
            StringBuilder ahelp = new StringBuilder("#");
            int eE = 1, dp = 1, spaces = 0;

               // Header von W2 Beginnend
            //  ahelp.append("°[208,207,255]°°>{colorboxstart}<°°bir°°12°#°+9502°°>LEFT<°°W°_CokaColaBoy wird versteigert! (noch 2Wochen 0Tage)_#_Höchstgebot: 100°>sm_classic_yellow...h_0.gif<° von _°[0,53,217]°_°>_hJames|/w James<°°b°°K°#_°[0,53,217]°°>Jetzt mitbieten!|/auctionme CokaColaBoy<°°°_#");
            ahelp.append("§Insgesamt gibt es derzeit _xx_-Adminfunktionen (Stand 21.06.2013)");
            ahelp.append("#°BB°_ACHTUNG_°r°: Hier sind nicht alle Funktionen aufgelistet!#Beachte Bitte auch _/macro ?_ für weitere Funktionen.#Ein Klick auf die Funktion öffnet ein extra Fenster mit einer genauen Beschreibung.");
            ahelp.append("°-°");

            // Beispiel für Adminfunktions-Ausgabe über Java-Code-TEXT
            ahelp.append("§°>gt.gif<° _°BB>/admin !mask|/admin !mask<°§#Deaktiviert den Tarnmodus.");

              // W2 Header ENFDE! 
            //  ahelp.append("°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°°bir°°12°###§°bir°°12°°>LEFT<°");
               // Ausgabe der Adminfunktionen möchte ich aber über Datenbank einbauen, daher erstmal hier PAUSE. Joern FRAGEN!
            // Popup wird erzeugt mit title des Names welches er aus String title holt und Größe des Fensters
            Popup popup = new Popup(title, title, ahelp.toString(), 460, 350); // Fenster Größe Breite und Höhe.
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   OK   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            popup.setModern(1);
            client.send(popup.toString());
      
            
    }
}
            
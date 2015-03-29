package funktionen;

import static features.hero.timeStampToDate;
import game.WordMixRecord;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.moderated;
import static starlight.CommandParser.muted;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;


public class loginlist {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
         String nickname = KCodeParser.escape(arg);
            Client target;
            
            if(nickname.isEmpty() || client.getRank() < 5) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);
            }
            
            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            StringBuilder lol = new StringBuilder("_Logindatum & Uhrzeit_##");
            List<String> logins = new ArrayList<String>();

            for(String[] infos : target.logins) {
                logins.add(infos[0]);
            }
            
            Collections.sort(logins);
            
            for(String infos : logins) {
                lol.append(infos).append("#");
            }
            
            lol.append("#_Hinweis_: Mehrere, _zeitlich naheliegende Logins_ werden zu einem einzigen Login _zusammengefasst_.##_Mehr Informationen zur \"Passwortsicherheit\"_ findest du unter _°BB>_h/h passwortsicherheit|\"<r°_.");
            
             Popup popup = new Popup("Login List "+target.getName(), "Login List "+target.getName(), lol.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
        
          
      }}
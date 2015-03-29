package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class beta {


    public static void functionMake(Client client,Channel channel, String arg) {
    
         if(client.getOnlineTime() / 60 < 100) {
      StringBuilder points = new StringBuilder();
      String title = String.format(""+Server.get().getSettings("CHAT_NAME")+"-Beta Feedback", "");
      Popup popup = new Popup(title, title, points.toString(), 201,0);
      popup = new Popup("Fehler", "Wartezeit...", "Hallo " + client.getName() + ",##schön, dass Du unserem Chat ein Feedback geben möchtest.#_Warte jedoch_ noch etwas, um den Chat besser kennenzulernen.##Danke Dir. °>sm_00.gif<°", 400, 250);
                Panel panel = new Panel();
                panel.addComponent(new Button("   OK   "));
                popup.addPanel(panel);
                client.send(popup.toString());
                return;
    }
      String title = String.format(""+Server.get().getSettings("CHAT_NAME")+"-Beta Feedback", channel.getName());
            StringBuilder points = new StringBuilder();
            Popup popup = new Popup(title, title, points.toString(), 201,0);
            popup.setHeaderFontSize(18);
            Panel panel2x = new Panel();
            panel2x.addComponent(new Label("Gib unserem Team dein Feedback, so können wir "+Server.get().getSettings("CHAT_NAME")+" noch mehr verbessern! "));
             popup.addPanel(panel2x);
            Panel gay = new Panel();
            Choice whoisgay = new Choice(new String[] { "Fehler                                                                                                ", "Kritik                                                                                                ", "Lob                                                                                                ", "Wünsche                                                                                                " } , "");
            whoisgay.setFontsize(17);
            gay.addComponent(whoisgay);
            popup.addPanel(gay);
            Panel panel2 = new Panel();
            Panel panel3 = new Panel();
            String nicks="";
            panel3.addComponent(new TextArea(6,80));
            popup.addPanel(panel2);
            popup.addPanel(panel3);
            Panel panel4 = new Panel();
            Button button = new Button("Senden");
            popup.setButtonFontSize(18);
            panel4.addComponent(button);
            button.enableAction();
            panel4.addComponent(new Button("Abbrechen"));
            popup.setButtonFontSize(18);
            popup.addPanel(panel4);
            popup.setOpcode(ReceiveOpcode.FEEDBACK.getValue(), "feedback");
            client.send(popup.toString());



    }
}
        
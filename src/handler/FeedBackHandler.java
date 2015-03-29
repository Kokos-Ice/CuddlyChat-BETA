package handler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import starlight.*;
import tools.popup.*;
import java.text.*;
import java.util.*;
import tools.*;
import tools.database.*;


public class FeedBackHandler {
    public static void handle(String[] tokens, Client client) {
   
int x = tokens[4].length();
  if(tokens[3].equals("") || tokens[3].equals("") || tokens[4].equals("") || tokens[4].equals("") || x < 35)
  {

      if(x < 35)  {
         String title = ""+Server.get().getSettings("CHAT_NAME")+"-Beta Feedback";
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
            TextArea textarea = new TextArea(6,80);
            textarea.setText(tokens[4]);
            panel3.addComponent(textarea);
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



                PopupNewStyle popup2 = new PopupNewStyle("Fehler", "Fehler", "##Bitte fülle die Angaben _ordentlich_ aus.#Mindestens 35 Zeichen sollten es schon sein. °>sm_00.gif<°", 450, 275);
                Panel panel = new Panel();
                panel.addComponent(new Button("   OK   "));
                popup2.addPanel(panel);
                client.send(popup2.toString());
              
      }
        }  else  {
 String answer=tokens[4];

 String owner = "";
  PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT * FROM `globalsettings` where `option`='OWNER_NICKNAME'");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next()) {
      owner = rs3.getString("value");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps3 != null)
        try {
          ps3.close();
        }
        catch (SQLException e)
        {
        }
      pcon3.close();
    }
 Client target = Server.get().getClient(owner);
 if (target == null) {
     target = new Client(null);
     target.loadStats(owner);
 }
 String betreff = "Feedback - "+tokens[3].trim();
 String text = client.getName()+" hat soeben ein neues °BB°_°>Feedback zu "+Server.get().getSettings("CHAT_NAME")+"|/beta<°_°r° abgegeben.##_Hier ist die Original-Nachricht von "+client.getName()+"_:##''"+answer+"''.";

     Server.get().newMessage(Server.get().getButler().getName(), target.getName(),betreff,text, (System.currentTimeMillis()/1000)); 
             

    PopupNewStyle popup = new PopupNewStyle("Erfolg", "Vielen Dank!", "##_Vielen Dank für dein Feedback!_##Wir melden uns bei Dir, falls Fragen bezüglich deines Feedbacks auftreten sollten.", 450, 275);
                Panel panel = new Panel();
                panel.addComponent(new Button("Schließen"));
                popup.addPanel(panel);
                client.send(popup.toString());
                

 }
    }
}


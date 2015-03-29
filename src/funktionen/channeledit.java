package funktionen;

import starlight.Channel;
import starlight.Client;
import starlight.ReceiveOpcode;
import tools.popup.Button;
import tools.popup.Choice;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.TextField;


public class channeledit {
     public static void make(String arg, Client client, Channel channel)   {
         if(!client.hasPermission("cmd.channeledit")) {
                   client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        	   return;
               }
                    
        Popup popup = new Popup("Channel "+channel.getName()+" bearbeiten","Channel "+channel.getName()+ " bearbeiten","", 474, 0);
      
        
      // Das gehört alles auch dazu
      // wir haben in Channel.java nur bekannt gegeben was Game für ein DB Feld ist
      // und in der ChannelEditHandler.java haben wir nur gesagt welche Funktionen dort angewendet werden können 
      // Hier folgt nun der Teil diese neue methode die wir eingefügt haben die wir verwenden können
      // hier einzubauen.. im Form eines Textfeldes dort die Funktion einzufügen zu verbinden.  
      
        
        
      // Das hier ist der Beginn des 1 Textfeldes CHANNELNAME
        
     Panel panel1 = new Panel(); // neue zeile !
     panel1.addComponent(new Label("Channelname:  ")); // text
     TextField lol1 = new TextField(54); // größe der input
     lol1.setText(channel.getName()); // aktueller channelname in der input
     panel1.addComponent(lol1); // fügt das zur zeile hinzu
     popup.addPanel(panel1); // ende der zeile!

     // Hier endet die Anwendung des ersten Textfeldes.. 
     // Hier wurde ein Textfeld erzeugt und gesagt was in dem Textfeld stehen soll für ein Wet und was dort bearbeitet wird.
     

  
     Panel panel2 = new Panel(); // neue zeile !
     panel2.addComponent(new Label("Topic:                ")); // text
     TextField lol2 = new TextField(54); // größe der input
     lol2.setText(channel.getTopic()); // aktuelle Topic in der input
     panel2.addComponent(lol2); // fügt das zur zeile hinzu
     popup.addPanel(panel2); // ende der zeile!



     /*
     Panel panel3 = new Panel(); // neue zeile !
     panel3.addComponent(new Label("Style: ")); // text
     TextField lol3 = new TextField(54); // größe der input
     lol3.setText(channel.getChannelStyle()); // aktuelle Topic in der input
     panel3.addComponent(lol3); // fügt das zur zeile hinzu
     popup.addPanel(panel3); // ende der zeile!
     */
     
 
     Panel panel4 = new Panel(); // neue zeile !
     panel4.addComponent(new Label("Channelgröße: ")); // text
     Choice lol4 = new Choice(new String[] { "5","10","15","20","25","30","40","50","75","100","125","150","175","200","225","250" },"");
     lol4.setSelected(String.valueOf(channel.getSize()));
     panel4.addComponent(lol4); // fügt das zur zeile hinzu
     popup.addPanel(panel4); // ende der zeile!
     
 
     Panel panel5 = new Panel(); // neue zeile !
     panel5.addComponent(new Label("Mindeststatus: ")); // text
     Choice lol5 = new Choice(new String[] { "0","1","2","3","4","5","6","7","8","9","10","11","12" },"");
     lol5.setSelected(String.valueOf(channel.getMinRank()));
     panel5.addComponent(lol5); // fügt das zur zeile hinzu
     popup.addPanel(panel5); // ende der zeile!
     
     
     
     Panel panel6 = new Panel(); // neue zeile !
     panel6.addComponent(new Label("Sichtbar?: ")); // text
     Choice lol6 = new Choice(new String[] { "0","1" },"");
     lol6.setSelected(String.valueOf(channel.getVisible()));
     panel6.addComponent(lol6); // fügt das zur zeile hinzu
     popup.addPanel(panel6); // ende der zeile!
     
     
     
  
     
     Panel panel7 = new Panel(); // neue zeile !
     panel7.addComponent(new Label("Juschu aktiv?: ")); // text
     Choice lol7 = new Choice(new String[] { "0","1" },"");
     lol7.setSelected(String.valueOf(channel.getJuSchu()));
     panel7.addComponent(lol7); // fügt das zur zeile hinzu
     popup.addPanel(panel7); // ende der zeile!
     
     
     
     
     
     Panel panel8 = new Panel(); // neue zeile !
     panel8.addComponent(new Label("Game: ")); // text
     Choice lol8 = new Choice(new String[] { "","Blitz!","Blitz! Free","DiceMaster","FIFTY","HIGH OR LOW","JUMPO","MAFIA","MATHE","MIX","TRANSLATE","WORDMIX" },"");
     lol8.setSelected(channel.getGameName());
     panel8.addComponent(lol8); // fügt das zur zeile hinzu
     popup.addPanel(panel8); // ende der zeile!
     
  
     
   
     
     
     Panel panel20 = new Panel();
     Button button = new Button("Speichern");   
     Button button2 = new Button("Schließen");
     panel20.addComponent(button);
     panel20.addComponent(button2);
     button.enableAction(); 
     popup.addPanel(panel20);

     popup.setOpcode(ReceiveOpcode.CHANNELEDIT.getValue(), "senden~"+channel.getName());
     client.send(popup.toString());
    
         
     }
}

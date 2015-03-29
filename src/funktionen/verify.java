package funktionen;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class verify {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   	if(!client.hasPermission("cmd.verify")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            Popup popup = new Popup("Altersverifikation", "Altersverifikation", "Für diese Altersverifikation benötigst du deinen _Personalausweis_. Nur die _letzte Zeile ganz unten_ auf der Vorderseite des Personalausweises wird eingeben. Sie besteht aus vier Zahlen, die durch << getrennt sind. Bitte gib diese nun in der richtigen Reihenfolge unten ein.##Hinweis: Die von dir eingegebenen Daten werden _nicht_ personenbezogen gespeichert. Bitte bedenke, dass du nur _einen_ Nick mit deiner Personalausweisnummer verifizieren kannst. Nach der Verifikation wird dein Geburtsdatum der Ausweisnummer entnommen und in deiner Info _permanent_ eingetragen.", 500, 250);
            Panel panel1 = new Panel();
            panel1.addComponent(new TextField(10));
            panel1.addComponent(new Label("<<"));
            panel1.addComponent(new TextField(7));
            panel1.addComponent(new Label("<"));
            panel1.addComponent(new TextField(7));
            panel1.addComponent(new Label("<<<<<<<"));
            panel1.addComponent(new TextField(1));
            popup.addPanel(panel1);
            Panel panel2 = new Panel();
            Button button = new Button("Absenden");
            button.setStyled(true);
            button.enableAction();
            button.disableClose();
            panel2.addComponent(button);
            popup.addPanel(panel2);
                    
            popup.setOpcode(ReceiveOpcode.VERIFY.getValue(), "verify");
            client.send(popup.toString());
     
          
      }}
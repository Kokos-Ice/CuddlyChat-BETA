package funktionen;

import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class cmtest {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   
          
          if(!client.hasPermission("cmd.cmtest")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(arg.equalsIgnoreCase("iplist")) {
        		StringBuilder list = new StringBuilder();
        		
        		
        		
        		Popup popup = new Popup("IPList", "IPList", list.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
        	}
        	
        	StringBuilder cmtest = new StringBuilder();
        	cmtest.append("Bevor Du auf die CM-Nominierungsliste kommst, musst Du Deine Fähigkeiten als CM mit Bestehen des CM-Tests beweisen.##- Studiere vor dem CM-Test intensiv die CM-Doku: _°B>http://inetchat.sytes.net/cmdoku|\"<r°_#- Der CM-Test besteht aus _20 Multiple-Choice Fragen_#- Du hast _pro Frage zwei Minuten_ Zeit, danach öffnet sich die nächste Frage#- Der Test dauert ca. _30 Minuten_#- Der Test ist bestanden, wenn Du _weniger als 5 Fehler_ machst#- Du hast _nur zwei Versuche_, den Test zu bestehen#- Ein _Bestehen bis zum 01.01.2012_ ist Voraussetzung für die CM-Wahlen#- °R°_Niemand darf Dir beim CM-Test helfen und Du darfst nicht über die gestellten Fragen sprechen oder mit mehr als einem Nick teilnehmen, sonst wird Dein Nick für immer gesperrt");
            Popup popup = new Popup("Infos zum CM-Test", "Infos zum CM-Test", cmtest.toString(), 400, 250);
            Panel panel2 = new Panel();
            Button button = new Button("CM-Test starten");
            button.setStyled(true);
            button.enableAction();
            panel2.addComponent(button);
            popup.addPanel(panel2);
                    
            popup.setOpcode(ReceiveOpcode.CMTEST.getValue(), "cmtest");
            client.send(popup.toString());
          
      }}
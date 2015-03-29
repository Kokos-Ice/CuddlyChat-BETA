package funktionen;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;

public class showwahl {
    
       public static void functionMake(Client client,Channel channel, String arg) {
   
    
            if(!client.hasPermission("cmd.showwahl")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
           
             if(!Server.elections.containsKey(arg)) {
            			client.sendButlerMessage(channel.getName(), String.format("Die Wahl _%s gibt es nicht_.", arg));
            			return;
            		}
         String[] vals = Server.elections.get(arg);
          if (vals[5].trim().isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Es gibt keine Ergebnisse für diese Wahl.");
              return;
          } 
          String text = "";
          
          int i = 1;
          for(String x : vals[5].split("#")) {
              if (!x.isEmpty()) {
                  String[] a = x.split("~.~");
                  text += i+". "+a[0]+" ("+a[1]+")#";
               i++;   
              }
              
              
          }
          
        //  client.send(Popup.create("Wahlergebnis: "+arg,"Wahlergebnis: "+arg,text,400,300));
          Popup popup = new Popup("Wahlergebnis: "+arg, "Wahlergebnis: "+arg, text, 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString());
                 return;
                 
           
           
}
}

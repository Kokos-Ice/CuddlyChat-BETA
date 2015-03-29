package funktionen;

import java.util.Arrays;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;


public class fotowhois {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   
          if(!client.hasPermission("cmd.fotowhois")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	Client target = Server.get().getClient(arg.isEmpty() ? client.getName() : arg);
        	StringBuilder fw = new StringBuilder();
        	
        	if(target == null) {
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}
        	
        	fw.append("°>CENTER<##°_°>_h").append(target.getName().replace("<", "\\<")).append(" (").append(target.getAge()).append(")    |/fotowhois ").append(target.getName().replace("<", "\\<")).append("|/w ").append(target.getName().replace("<", "\\<")).append("<>");
        	
        	if(target.getGender() == 1) {
        		fw.append("male...b.my_3");
        	} else {
        		fw.append("female...b.my_4");
        	}
        	
        	fw.append(".w_0.mx_-14.png<°_°##>");
        	
        	if(target.getPhoto().isEmpty()) {
        		fw.append("nopic_79x79_").append(target.getGender() == 1 ? "m":"f");
        	} else {
        		fw.append("photos/photo/getPicture.php?l&img=").append(target.getPhoto());
        	}
        	
        	fw.append("...border_3.jpg<>--<>|/foto ").append(target.getName().replace("<", "\\<")).append("|/w ").append(target.getName().replace("<", "\\<")).append("<°##");
        	
        	if(target.getDesc() == null) {
        		fw.append("keine Beschreibung");
        		
        		if(client == target) {
        			fw.append("##(Beschreibung mit /desc TEXT hinzufügen)");
        		}
        	} else {
        		fw.append("\"").append(target.getDesc()).append("\"");
        	}
        	
        	  Popup popup = new Popup("FotoWhois zu "+target.getName(), "FotoWhois zu "+target.getName(), fw.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setCodeactivate(1);
                        client.send(popup.toString());
        
          
      }}
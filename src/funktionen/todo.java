package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class todo {


    public static void functionMake(Client client,Channel channel, String arg) {
     


if(!client.hasPermission("cmd.todo")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(arg.isEmpty()) {
            	StringBuilder todo = new StringBuilder();
                todo.append("Hier findest du eine Übersicht aller bekannten Bugs. Sofern du selber einen Bug entdecken solltest, so trage ihn mit /todo TEXT bitte unbedingt ein.");
            	
        		for(String text : Server.todo.keySet()) {
                            
                              //  todo.append("°14°_Hier findest du eine Übersicht aller bekannten eingetragenen Bugs. Sofern du selber einen Bug entdeckst, so trage diesem mit /todo TEXT bitte hier ein._##");
        			
        			String von = Server.todo.get(text);
        			todo.append("##°14°°>gt.gif<° °RR°_").append(text).append("_°r°#°12°(_gemeldet von:°r° °12°").append(von).append("°12°°r°_)##°r°§");
        		}
        	
        		String title = "ToDo List";
   
                        PopupNewStyle popup = new PopupNewStyle(title, title, todo.toString(), 450, 425);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
                 
                        
        	}
        	
        	arg = arg.replace("'", "\\'");
        	
        	client.sendButlerMessage(channel.getName(), "Der Eintrag wurde der ToDo List hinzugefügt.");
        	Server.todo.put(arg, client.getName());
        	Server.get().query(String.format("insert into todo set text='%s',von='%s'", arg, client.getName()));
      
        
    }
}
  
        
       
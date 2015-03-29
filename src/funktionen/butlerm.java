/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package funktionen;
import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.ReceiveOpcode;
import starlight.Server;
import tools.KCodeParser;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.TextArea;
/**
 *
 * @author CokaColaBoy
 */
public class butlerm {
      public static void functionMake(Client client,Channel channel, String arg) {
          if(!client.hasPermission("cmd.butlermail")) {
                      client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                      return;
                  }
String nickname = KCodeParser.escape(arg).split(":", 2)[0];          
  
String msg = "";
            

if (arg.equals("rundmail")) {

    
Popup popup = new Popup(String.format("RundMail an alle Chatter"), String.format("RundMail an alle Chatter schreiben"), "", 0, 0);
            Panel panel7 = new Panel();
            TextArea a = new TextArea(15, 50);
            a.setText("");
            panel7.addComponent(a);
            popup.addPanel(panel7);
            Panel panel2 = new Panel();
            Button button2 = new Button("Absenden");
            button2.enableAction();
            panel2.addComponent(button2);
            panel2.addComponent(new Button("Abbrechen"));
            popup.addPanel(panel2);
            popup.setOpcode(ReceiveOpcode.RUNDMAIL.getValue(), client.getName());
            client.send(popup.toString());    
    
    return;
}

           if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

if(msg.length() > 2000) {
                client.sendButlerMessage(channel.getName(), "Deine Nachricht konnte nicht gesendet werden:##Ihre _Nachricht ist zu lang_, sie darf höchstens 2000 Zeichen lang sein.");
                return;
            }

String betreff = "";
            
            if(msg.contains("§")) {
            	String[] split = msg.split("\\§");
            	
            	msg = split[1]; 
            	betreff = split[0];
            	
            	if(betreff.length() > 50) {
            		client.sendButlerMessage(channel.getName(), "Deine Nachricht konnte nicht gesendet werden:##Der _Betreff Ihrer Nachricht ist zu lang_, dieser darf höchstens 50 Zeichen lang sein.");
            		return;
            	}
            }
            Long time = System.currentTimeMillis()/1000; 
            betreff = KCodeParser.noKCode(betreff);
            msg = KCodeParser.noKCode(msg);
            
            
            Client target = Server.get().getClient(nickname);
            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                    return;
                }
            }
            	client.sendButlerMessage(channel.getName(), CommandParser.messageSent(target.getName()));

Server.get().newMessage(Server.get().getButler().getName(), target.getName(), betreff, msg, time);
                      
          
          
          
          
      }
}


package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class info {


    public static void functionMake(Client client,Channel channel, String arg) {




 if(!client.hasPermission("cmd.info")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
            }
            
            arg = KCodeParser.escape(arg);
            Channel target = arg.isEmpty()?channel:Server.get().getChannel(arg);
            
            if(target == null) {
                client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", arg));
                return;
            }
            
            if(!target.isVisible() && target != channel) {
            	client.sendButlerMessage(channel.getName(), "Die Channelinfo kann nur von öffentlichen Channels angezeigt werden.");
                return;
            }
            
            StringBuilder info = new StringBuilder();
            String title = String.format("Channel %s", target.getName());
            info.append("#Der Channel ").append(target.getName()).append(" ");
            
            if(!target.isVisible() && target.getTemp() == 1) {
            	info.append("ist ein _temporärer Channel_ (angelegt von ");
            	Client owner = Server.get().getClient(target.getOwner());
            	boolean blau = false;
            	
            	if(owner != null) {
            		blau = true;
            	}
            	
            	info.append(blau?"_°B":"°").append(">_h").append(target.getOwner()).append("|/serverpp \"|/w \"<").append(blau?"r°_":"°").append(")");
            } else if(!target.getOwner().isEmpty()) {
            	info.append("gehört ");
            	Client owner = Server.get().getClient(target.getOwner());
            	boolean blau = false;
            	
            	if(owner != null) {
            		blau = true;
            	}
            	
            	info.append(blau?"_°B":"°").append(">_h").append(target.getOwner()).append("|/serverpp \"|/w \"<").append(blau?"r°_":"°").append(".");
            } else if(!target.isVisible()) {
            	info.append("ist unsichtbar.");
            } else {
            	info.append("ist ein _Systemchannel_.");
            }
            
            info.append("##");
            
            if(target.getInfo().isEmpty()) {
                info.append("Es gelten _°BB>AGB|/h \"<°§ & _°BB>Knigge|/h \"<°§.");
            } else {
                info.append("Neben _°BB>AGB|/h \"<°§ & _°BB>Knigge|/h \"<°§ gelten folgende _Infos & Regeln_ im Channel ").append(target.getName()).append(":°-°").append(target.getInfo()).append("°-°#");
            }

			info.append("§##_LieblingsChannel von ").append(target.getLcStammis()).append(" Stammis_.##");

            int cm = 1;
            int eE = 1;

            if(target.getCms().isEmpty()) {
                info.append("Keine Channelmoderatoren.");
            } else {
            	info.append("_Channelmoderatoren:_#");
            	
            	for(String x : target.getCms().split("\\|")) {
            		if(!x.isEmpty()) {
            			Client t = Server.get().getClient(x);
            			boolean blau = true;
            			
            			if(t == null) {
            				blau = false;
            			}
            			
            			if(cm != 1) {
            				info.append(", ");
            			}
            			
            			info.append(blau?"_°B":"°").append(">_h").append(x).append("|/serverpp \"|/w \"<").append(blau?"r°_":"°");
            			cm++;
            		}
            	}
            }
            
            if(!target.getHZ().isEmpty()) {
                info.append("##_Hauptzuständige Admins & Ehrenz:_#");

            	for(String x : target.getHZ().split("\\|")) {
            		if(!x.isEmpty()) {
            			Client t = Server.get().getClient(x);
            			boolean blau = true;
            			
            			if(t == null) {
            				blau = false;
            			}
            			
            			if(eE != 1) {
            				info.append(", ");
            			}
            			
            			info.append(blau?"_°B":"°").append(">_h").append(x).append("|/serverpp \"|/w \"<").append(blau?"r°_":"°");
            			eE++;
            		}
            	}
            }
            
            Popup popup = new Popup(title, title, info.toString(), 575, 400);
            Panel panel2 = new Panel();
            Button button = new Button("   OK   ");
            button.setStyled(true);
            popup.setChannelinfo(1);
            panel2.addComponent(button);
            
            
            if(channel.isVisible()) {
            	Button button2 = new Button("Moderatorenwahl");
                button2.setStyled(true);
            	button2.enableAction();
                button2.disableClose();
            	panel2.addComponent(button2);
            }
        
            popup.addPanel(panel2);
                    
            popup.setOpcode(ReceiveOpcode.INFO.getValue(), target.getName());
            client.send(popup.toString());
 }
}
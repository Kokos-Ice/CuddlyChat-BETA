package funktionen;

import static features.hero.timeStampToDate;
import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class wc {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
   	
          if(!client.hasPermission("cmd.wc")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            arg = KCodeParser.escape(arg);
            Channel channelTo = Server.get().getChannel(arg);
            
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim().toLowerCase();
            String msg = "";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

            StringBuilder wc = new StringBuilder();
            
            if (arg.isEmpty() || nickname.equals("cat") && Server.get().isInteger(msg)) {
                if(!nickname.equals("cat")) {
            	int channels = Server.get().getChannels().size();
                int online = Server.get().getClients().size();
                
                wc.append("#_Channels insgesamt_:_°%46°").append(channels).append("°%00°_#_öffentliche_ Channels: _°%46°").append(channels).append("_°%56° (").append(online).append(")°%00°#_private_ Channels: _°%46°0°%00°_##");
            
                for (Channel names : Server.get().getChannels()) {
                	if(names.isVisible()) {
                		wc.append("°>_h").append(names.getName()).append("|/go \"|/go +\"<° (").append(names.countClients()).append(")");
                	
                                
                                
                		if(names.countClients() == names.getSize()) {
                			wc.append(" °>icon_fullChannel.gif<°");
                		}
                		
                		if(names.getSpecialEvent() == 1) {
                			wc.append(" °>icon_specialEventChannel.gif<°");
                		}
                		
                		wc.append("#");
                	}
                }} else {
                	
                	int channels = 0;
                    int online = 1;
                	int category = Integer.parseInt(msg);
                    
                    for(Channel names : Server.get().getChannels()) {
                    	if(names.isVisible() && names.getCategory() == category) {
                    		channels++;
                    		online+=names.countClients()-1;
                    	}
                	}
                    
                    wc.append("#_Channels insgesamt_:_°%46°").append(channels).append("°%00°_#_öffentliche_ Channels: _°%46°").append(channels).append("_°%56° (").append(online).append(")°%00°#_private_ Channels: _°%46°0°%00°_##");
                
                    for (Channel names : Server.get().getChannels()) {
                    	if(names.isVisible() && names.getCategory() == category) {
                    		wc.append("°>_h").append(names.getName()).append("|/go \"|/go +\"<° (").append(names.countClients()).append(")");
                    		
                    		if(names.countClients() == names.getSize()) {
                    			wc.append(" °>icon_fullChannel.gif<°");
                    		}
                    		
                    		if(names.getSpecialEvent() == 1) {
                    			wc.append(" °>icon_specialEventChannel.gif<°");
                    		}
                    		
                    		wc.append("#");
                    	}
                    }
                }
                
                wc.append("##_Stand_: ").append(Server.get().timeStampToDate(time)).append(" um ").append(Server.get().timeStampToTime(time)).append(" Uhr");
                 Popup popup = new Popup("Channels", "Channels", wc.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString());
                 return;
            }
            
            if (channelTo == null) {
                client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", arg));
                return;
            }
            
            if(!channelTo.isVisible() && channelTo != channel) {
            	client.sendButlerMessage(channel.getName(), String.format("Der Channel %s ist unsichtbar.", channelTo.getName()));
                return;
            }
            
            StringBuilder wc2 = new StringBuilder("#");   
            int cC = 1; 
            String title = String.format("Chatter im %s (%s)", channelTo.getName(), channelTo.countClients());
        
            if(channelTo.getTopic() != null) {
                wc2.append("_Topic:#").append(channelTo.getTopic()).append("_##");
            }
            
            for (Client eNick : channelTo.getClients()) {
                wc2.append(cC != 1 ? ", " : "").append("°>_h").append(eNick.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                cC++;
            }
                
        
                 Popup popup = new Popup(title, title, wc2.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setGullideckel(1);
                 client.send(popup.toString());
              
            
      }}
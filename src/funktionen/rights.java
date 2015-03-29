package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class rights {


    public static void functionMake(Client client,Channel channel, String arg) {
    if(!client.hasPermission("cmd.rights")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	String typ = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > typ.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
        	
        	if(arg.equals("editor")) {
                    
              
        		StringBuilder per = new StringBuilder("Willkommen im Rights-Editor _"+client.getName()+"_!#Hier kannst du die Rechte für den Chat Erstellen und bearbeiten.##°-°                                       °BB°°>finger.b.gif<° _°>Recht hinzufügen|/server rechte<°_°r°°-°#");                
                        per.append("°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_§°bir°°12°°>LEFT<°°b°#°+0000+9502°°S18°_ Recht°%50°°S18°Bezeichnung°%00°_#°r°°+9502°°[174,174,255,127]°#°+9502°°>{colorboxend}<°#°r°°>left<°##");              
                        String title = "Rights-Editor - Übersicht";                    
        		List<String> sortedList = new ArrayList<String>();
                        sortedList.addAll(Server.permissions.keySet());
        		Collections.sort(sortedList);
        		 
        		Iterator<String> iter = sortedList.iterator();
        		
        		while (iter.hasNext()) {
        			String permission = iter.next();
        			String rights = Server.permissions.get(permission);
                                
        			per.append("_°B>").append(permission).append("|/rights edit:\"<r°_");
        			
        			if(!rights.isEmpty()) {
        				per.append("°%50°").append(rights).append("°%00°");
        			}
        			
                                
        			per.append("#");
        			
        		}
        		//client.send(Popup.create(title, title, per.toString(), 500, 300));
                        PopupNewStyle popup = new PopupNewStyle(title, title, per.toString(), 550, 300);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Rechte-Editor aufgerufen", client.getName()));
                        return;    
        	}
        	
        	
        	
        	if(arg.equals("status-overview")) {
        		client.send(Popup.create("Rechte-Editor - Status-Übersicht", "Rechte-Editor - Status-Übersicht", "_0_°%30°Mitglied°%00B°#_1_°%30°Familymitglied°%00E°#_2_°%30°Stammi°%00°#_3_°%30°Ehrenmitglied°%00°#_4_°%30°Status 4°%00°#_5_°%30°inoffizieller Admin°%00R°#_6_°%30°Admin°%00°#_7_°%30°Admin Status 7°%00°#_8_°%30°inoffizieller Sysadmin°%00°#_9_°%30°Sysadmin Status 9°%00°#_10_°%30°Sysadmin Status 10°%00°#_11_°%30°Sysadmin Status 11°%00°", 400, 250));
        		return;
        	}
        	
        	if(typ.equals("edit")) {
        		if(!Server.permissions.containsKey(msg)) {
        			client.sendButlerMessage(channel.getName(), String.format("Das Recht _%s existiert nicht_.", msg));
        			return;
        		}
        	
        		client.send(PacketCreator.createRightsEditorWindow(msg, Server.permissions.get(msg)));
        		return;
        	}
        	
        	Client target = typ.isEmpty() ? client : Server.get().getClient(typ);
        	
        	if(target == null) {
        		target = new Client(null);
        		target.loadStats(typ);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(typ));
        			return;
        		}
        	}
        	
        	if(!msg.isEmpty()) {
        		boolean remove = false;
        		
        		if(msg.startsWith("!")) {
        			remove = true;
        			msg = msg.substring(1);
        		}
        		
        		if(!Server.permissions.containsKey(msg)) {
        			client.sendButlerMessage(channel.getName(), String.format("Das Recht _%s existiert nicht_.", msg));
        			return;
        		}
        		
        		String bez = Server.permissions.get(msg);
        		
        		if(remove) {
            		if(!target.hasPermission(msg)) {
            			client.sendButlerMessage(channel.getName(), String.format("%s besitzt das Recht _%s_ nicht.", target.getName(), msg));
            			return;
            		}
            		
            		client.sendButlerMessage(channel.getName(), String.format("%s wurde das Recht _%s entzogen_.", target.getName(), msg));
            		Server.permissions.put(msg, bez.replace(String.format("|N%s|", target.getName()), ""));
            		Server.get().query(String.format("UPDATE `permissions` SET `rights`='%s' WHERE `permission` = '%s'", bez.replace(String.format("|N%s|", target.getName()), ""), msg));
        		} else {
        			if(target.hasPermission(msg)) {
            			client.sendButlerMessage(channel.getName(), String.format("%s besitzt das Recht _%s_ bereits.", target.getName(), msg));
            			return;
            		}
            		
            		client.sendButlerMessage(channel.getName(), String.format("%s wurde das Recht _%s hinzugefügt_.", target.getName(), msg));
            		Server.permissions.put(msg, String.format("%s|N%s|", bez, target.getName()));
            		Server.get().query(String.format("UPDATE `permissions` SET `rights`='%s' WHERE `permission` = '%s'", String.format("%s|N%s|", bez, target.getName()), msg));
        		}
        		
        		return;
        	}
        	
        	StringBuilder per = new StringBuilder("_");
    		String title = String.format("Rechte von %s", target.getName());
    		List<String> sortedList = new ArrayList<String>();
    		sortedList.addAll(Server.permissions.keySet());
    		Collections.sort(sortedList);
    		 
    		Iterator<String> iter = sortedList.iterator();
    		
    		while (iter.hasNext()) {
    			String permission = iter.next();
    			String key = Server.permissions.get(permission);
    			
    			if(target.hasPermission(permission)) {
    				per.append(permission);
    				
    				if(key.contains(String.format("|N%s|", target.getName()))) {
    					per.append(" °%70>finger.b.gif<° °BB>Entziehen|/rights ").append(target.getName()).append(":!").append(permission).append("<r%00°");
    				}
    				
    				per.append("#");
    			}
    		}
    		
    		
                 Popup popup = new Popup(title, title, per.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setModern(1);
                 client.send(popup.toString());
                 return;
    		
    
    
    }
    }
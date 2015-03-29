package funktionen;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import tools.KCodeParser;
import tools.ProfileTools;
import tools.Source;
import tools.Zodiac;
import tools.popup.Button;
import tools.popup.KTab;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.FunctionParser;
import starlight.ReceiveOpcode;
import starlight.Server;

public class w2 {

	
       

	public static void handle(String cmd, String arg, Client client,
			Channel channel) {
		if(!client.hasPermission("cmd.whoisnew")) {
    		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
    		return;
    	}
    	
		Client target;
    	String nickname	= KCodeParser.escape(arg);
        boolean online	= true;
        boolean ent		= false;
        
        if(!nickname.isEmpty() && arg.startsWith("+") && client.hasPermission("cmd.w.plus")) {
        	ent			= true;
            nickname	= nickname.substring(1).trim();
        }
        
        if(nickname.isEmpty() || nickname.equalsIgnoreCase(client.getName())) {
            target = client;
        } else {
            target = Server.get().getClient(nickname);

            if(target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                    return;
                }
            }
        }

        if(target.checkIgnored(client.getName())) {
            client.sendButlerMessage(channel.getName(), String.format("Du wirst von %s ignoriert und kannst deshalb die Whois nicht einsehen.", target.getName()));
            return;
        }

        int eE = 1, dp = 1, spaces=0;
        NumberFormat nf				= NumberFormat.getInstance(Locale.GERMAN);
		nickname					= target.getName();
		String charNick				= nickname.replace("<", "\\<");
		StringBuilder whois 		= new StringBuilder();
		KTab content				= new KTab(0);
		String extended				= "";
		
		if(target.getGender() == 1) {
			extended += "°>male5.png<°";
		} else if (target.getGender() == 2) {
			extended += "°>female5.png<°";
		}
		
		if(target.getGender() >= 1 && target.getGender() <= 2 && target.getAge() != 0) {
			extended += " ";
		}
		
		if(target.getAge() != 0) {
			extended += " (" + target.getAge() + ")";
		}
		 
		String title				= "°>iwhois_" + (online ? "on" : "off") + "_button.png<° Profil von " + nickname + extended + "#";
		String readme				= target.getReadme();
        StringBuilder cls			= new StringBuilder();
        StringBuilder mutes			= new StringBuilder();
        StringBuilder beschwerden	= new StringBuilder();
        String photo				= target.getPhoto();
        int knuddels				= (int) target.getKnuddels();
        int luftlinie				= 0;
        
        if(readme == null) {
        	readme = "";
        } else if(!readme.equals("")) {
        	readme = "_°>_hReadme|/readmehis " + nickname + "<°:_ \"" + readme + "\"";
		}
		
		// Profil
        StringBuilder profile = new StringBuilder();
        
        // Short Admin Info
        if(channel.checkCm(client.getName()) || channel.checkHz(client.getName()) || client.getRank() > 2 || client.getTeams().contains("~1|") || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz")) {
           for(Channel s : Server.get().getChannels()) {
            	if(target.checkCl(s)) {
            		cls.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_ von °>_h").append("").append("|/serverpp \"|/w \"<°");
            	}
            	
            	if(Server.get().checkCcm(target.getName(), s, 2)) {
            		mutes.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" (Color) von °>_h").append("").append("|/serverpp \"|/w \"<°");
            	}
            	
            	if(Server.get().checkCcm(target.getName(), s, 3)) {
            		mutes.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" von °>_h").append("").append("|/serverpp \"|/w \"<°");
            	}
            }
            
            if(target.getDisable() != 0) {
                if(client.getRank() > 10) {
                	profile.append("°R°_°12°Nick ist derzeit deaktiviert_##");
                }
            }
        
        	if(target.getDeletenick() != 0) {
        		if(client.getRank() > 4) {
        			profile.append("°R°_°12°Nick ist zur Löschung freigegeben!_##");
        		}
    		}
        	
    		if(target.getSpielsperre() != 0) {
    			profile.append("°R°_°12°Momentan für alle Spiele GESPERRT_##");
    		}
    	
    		if(target.getWahlsperre() != 0) {
    			profile.append("°R°_°12°Bis zum ").append(Server.get().timeStampToDate(target.getWahlsperre())).append(" °12°für alle Wahlen GESPERRT_##");
    		}
    	
    		if(target.getSperre() != 0) {
    			profile.append("°R°_").append(target.getName());
    			profile.append("°R° ist von ").append(target.getSperrevon()).append(" gesperrt_");
    		
    			if(client.getRank() > 2) {
    				profile.append("#_Begründung:#").append(target.getSperreinfo()).append("_");
    			}
    		
    			profile.append("##");
    		}
    	
    		if(!cls.toString().isEmpty()) {
    			profile.append("°B°_°12°Channellocks_:").append(cls.toString()).append("##");
    		}
    
    		if(!mutes.toString().isEmpty()) {
    			profile.append("°B°_°12°Gemutet_:").append(mutes.toString()).append("##");
    		}
    	
    		profile.append("°r°_");
    	}

        profile.append("°>LEFT<>{table|150|w1}<°");
        
        // Picture
        
        if(!photo.isEmpty()) {
        	// PIC
        	String[] ext = photo.split(".");
        	String e;
        	if(ext.length >= 1 && ext[1] != null) {
        		e = "." + ext[1];
        	} else {
        		e = ".jpg";
        	}
        	
        	profile.append("_°B>photos/m/").append(photo.replace(e, "")).append("...center_140.border_3.shadow_4" + e + "<>--<>|").append(Server.get().getURL()).append("photos/m").append(charNick).append("<°");
        } else {
        	profile.append("_°B>nopic_79x79_" + (target.getGender() == 2 ? "f" : "m") + "...center_140.border_3.shadow_4.jpg<°");
        }
        
        if(!photo.isEmpty() && target.getPhoto_verify() == 1) {
        	profile.append("°>w2/fv_checked...w_0.h_0.mx_-40.my_-10.vtop.png<°");
        }
        	
        if(!photo.isEmpty()) {
        	profile.append("#°26°  °>--<>lupe...my_2.mx_5.gif<13°  °>--<>_hVergrößern|").append(Server.get().getURL()).append("photos-profile.php?id=%s").append(charNick).append("<r°_");
        }
        
        // Shortinfo
        
        profile.append("°>{tc}<r12°");
         
        // Main Info
        if(target == client) {
        	profile.append("Du hast Dich");
        } else {
            String grippefarbe = "K";
            
            if(target.getGrippeStatus() == 1) {
            	grippefarbe = "B";
            } else if(target.getGrippeStatus() == 2 || target.getGrippeStatus() == 3 || target.getGrippeStatus() == 4) {
            	grippefarbe = "R";
            } else if(target.getGrippeStatus() == 5) {
            	grippefarbe = "G";
            }
            
            profile.append("_°").append(grippefarbe).append(">_h").append(charNick).append("|/m \"<r12°_ hat sich");
        }
        
        profile.append(" am _");
        
        if(target==Server.get().getButler()) {
        	profile.append("21.05.1935_ um 11:11:11");
        } else {
        	profile.append(target.getRegistrationDate()).append("_ um ").append(target.getRegistrationTime());
        }
        
        profile.append(" bei Chattrix registriert und seitdem schon _").append(nf.format(target.getOnlineTime()/60)).append("_ Minuten hier verbracht.##");

        if(online) {
        	profile.append("°>py_g.gif<12°");
    		
    		if(client == target) {
    			profile.append("Du bist");
    		} else {
    			profile.append(target.getGenderLabel()).append(" ist");
    		}
    		
    		profile.append(" im Moment im Channel _");
    		
    		if(target.getChannel() == null || !target.getChannel().isVisible()) {
    			profile.append("?");
    		} else {
    			profile.append("°>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<r°");
    		}
            
    		profile.append(" °E°ONLINE_°r°!");
    		
            if(target.isAway()) {
            	if(target.hasPermission("popup.awayaniicon")) {
            		profile.append(" °>icon_away_ani_new.gif<°");
            	} else {
            		profile.append(" °>away.png<°");
            	}
            }
            
            profile.append("°%00°##");
    	} else {
    		if(target.getLastOnlineChannel() == null) {
    			profile.append("°>py_b.gif<12°").append(target.getGenderLabel()).append(" war niemals im Chat online°%00°##");
    		
    			profile.append("°>py_r.gif<12°").append(target.getGenderLabel()).append(" war zuletzt am _").append(target.getLastOnlineDate()).append("_ ").append(target.getLastOnlineTime()).append(" im Channel _");
    			Channel lastchannel = Server.get().getChannel(target.getLastOnlineChannel());
    			
    			if(lastchannel == null || !lastchannel.isVisible()) {
    				profile.append("?");
    			
    				profile.append("°>_h").append(target.getLastOnlineChannel()).append("|/go \"|/go +\"<°");
    			}
                    
    			 if(!target.getLC().isEmpty() && target.getRank() > 1) {
            	Channel lc = Server.get().getChannel(target.getLC());
            	
                profile.append("_LieblingsChannel_:°%").append("").append("°").append("°>_h").append(target.getLC()).append("|/go \"|/wc \"<°");
                
                if(lc.checkCm("%s")) {
                	profile.append(" (°>cm.png<°)");
                }
                
                if(client.getLC().equals(target.getLC()) || client.hasPermission("profile.seefavoritechannel")) {
                	profile.append(" (seit ");
                	
                	if(target.getLcmonths() == 0) {
                		profile.append("diesem Monat");
                	} else if(target.getLcmonths() == 1) {
                    	profile.append("einem Monat");
                    
                    	profile.append(target.getLcmonths()).append(" Monaten");
                    }
                	
                	profile.append(")");
                }
                 
                profile.append("°%00°#");
            }
    			profile.append("_##");
    		}
    		
            if(!ent) {
            	Server.get().query(String.format("update accounts set missed='%s|%s|' where name='%s'", target.getMissed(), client.getName(), target.getName()));
            }
    	
    	}
        profile.append("°>{endtable}<°");
        
		content.newTab(title, readme, ">w2/actionmenu_foto...my_3.png<>_h", null, profile.toString());
		
		// Statistiken
		content.newTab(title, readme, ">w2/actionmenu_miss...my_3.png<>_h", null, "");
                 if(!target.getLC().isEmpty() && target.getRank() > 1) {
            	Channel lc = Server.get().getChannel(target.getLC());
            	
                whois.append("_LieblingsChannel_:°%").append("").append("°").append("°>_h").append(target.getLC()).append("|/go \"|/wc \"<°");
                
                if(lc.checkCm("%s")) {
                	whois.append(" (°>cm.png<°)");
                }
                
                if(client.getLC().equals(target.getLC()) || client.hasPermission("profile.seefavoritechannel")) {
                	whois.append(" (seit ");
                	
                	if(target.getLcmonths() == 0) {
                		whois.append("diesem Monat");
                	} else if(target.getLcmonths() == 1) {
                    	whois.append("einem Monat");
                    
                    	whois.append(target.getLcmonths()).append(" Monaten");
                    }
                	
                	whois.append(")");
                }
                 
                profile.append("°%00°#");
            }
    		
		// Einstellungen
		if(client == target) {
			content.newTab(title, readme, ">w2/actionmenu_edit...my_3.png<>_h", null, "°>{button}Profilbearbeiten||call|/edit<°###°>{button}Fotoladen||call|/foto<°");
		}
                
		
		// CM
		if(client.getRank() > 2 || client.getTeams().contains("~1|") || channel.checkCm(client.getName()) || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz")) {
			StringBuilder cm = new StringBuilder();
			cm.append("°>CENTER<R°_Die Channelmoderator-Info ist vertraulich!_:##°B>LEFT<°");
        	cm.append("_Channellocks_:°%45°").append(target.getCls()).append("°%00°#");
        	cm.append("_Colormutes_:°%45°").append(target.getCmutes()).append("°%00°#");
            cm.append("_Mutes_:°%45°").append(target.getMutes()).append("°%00°#");
            cm.append("##_CM-Comments_:#");
            
            if(target.getCmcomments().equals("")) {
            	cm.append("Keine Einträge vorhanden");
            } else {
            	cm.append(target.getCmcomments());
            }
            
            content.newTab(title, readme, ">cm...my_4.png<>_h", null, cm.toString());
		}
			
		// Admin
		if(client.getRank() > 2 || client.getTeams().contains("~1|")) {
			StringBuilder admin = new StringBuilder();
			
			admin.append("°B°_").append(target.getRank() > 7 ? "Sysa":"A").append("dmininfo_:###");
    		
            if(client.getRank() < 7 && target.getRank() > 7) {
            	/* Do Nothing */
    		} else {
    			admin.append("_Last Host_:°%45°");
            
            	if(client.getRank() > 7) {
            		admin.append("°>_h");
            	}
            
            	admin.append(target.getIPAddress().replace(".", "-"));
            
            	if(client.getRank() > 7) {
            		admin.append("|/checkip \"<°");
            		
            		if(!target.getHost().isEmpty()) {
            			admin.append(" (").append(ProfileTools.getCountry(target.getHost())).append(")");
            		}
            	}
            }
            
            admin.append("°%00°#_User Reg-No._:°%45°").append(nf.format(target.getID())).append("°%00°#_Status_:°%45°").append(target.getRank()).append(" (").append(target.getRankLabel(target.getRank()));
            
            if(target == Server.get().getNRS() || target == Server.get().getButler()) {
            	admin.append(", Bot");
        	}
            
            admin.append(")°%00°");
            
            if(target.getMutes() > 0) {
            	admin.append("#_Mutes_:°%45°").append(target.getMutes()).append("°%00°");
            }
            
            if(target.getCmutes() > 0) {
            	admin.append("#_Colormutes_:°%45°").append(target.getCmutes()).append("°%00°");
            }
            
            if(target.getKicks() > 0) {
            	admin.append("#_Kicks_:°%45°").append(target.getKicks()).append("°%00°");
            }
            
             if(target.getLocks() > 0) {
            	 admin.append("#_Locks_:°%45°").append(target.getLocks()).append("°%00°");
            }
            
            if(target.getCls() > 0) {
            	admin.append("#_Channellocks_:°%45°").append(target.getCls()).append("°%00°");
            }
            
            if(target.getFlames() > 0) {
            	admin.append("#_Flames_:°%45°").append(target.getFlames()).append("°%00°");
            }
            
            StringBuilder nn = new StringBuilder();
            int xl = 1;

            for (Client ob : Server.get().getClients()) {
           	 	if(ob != target && ob.getIPAddress().equals(target.getIPAddress())) {
           	 		nn.append(xl!=1?", ":"").append("°>_h").append(ob.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
           	 		xl++;
           	 	}
            }
            
            if(!nn.toString().isEmpty()) {
            	admin.append("#_Nicks mit gleicher IP_:°%45°").append(nn.toString()).append("°%00°");
            }
            
            admin.append("#_Notrufe (\\# / Ok / Missbr.)_:°%45°").append(target.getAdmincallFirst()).append(" / ").append(target.getAdmincallSecond()).append(" / ").append(target.getAdmincallThird()).append("°%00°");

            if(target.getNotrufsperre() != 0) {
            	admin.append("#_Notrufsperre bis_:°%45°").append(Server.get().timeStampToDate(target.getNotrufsperre())).append("°%00°");
            }
            
            if(client.getRank() > 7 || client.getName().equals("Toby") || client.getName().equals("Bandit")) {
            	if(target.getRegisterIP() != null) { 
            		admin.append("#_Register-IP_:°%45>_h").append(target.getRegisterIP().replace(".", "-")).append("|/checkip \"<%00°");
            		admin.append("#_E-Mail_:°%45>_h").append(target.getEmail().replace(".", "-")).append("|/checkmail \"<%00°");
            	}
            	
            	if(target.getAgent() != null) {
            		admin.append("#_Betriebssystem_:°%45°").append(ProfileTools.getOS(target.getAgent())).append("°%00°");
            		admin.append("#_Browser_:°%45°").append(ProfileTools.getBrowser(target.getAgent())).append("°%00°");
            	}
            	
            	if(!target.getHost().isEmpty()) {
            		admin.append("#_Host_:°%45°").append(target.getHost()).append("°%00°");
            	}
            
            	admin.append("#_Zeichenzahl_:°%45°").append(nf.format(target.getZeichen())).append("°%00°");

            	if(target.getAppletVersion() != null) {
            		admin.append("#_Appletversion_:°%45°").append(target.getAppletVersion()).append("°%00°");
            		admin.append("#_Javaversion_:°%45°").append(KCodeParser.escape(target.getJavaVersion())).append("°%00°");
            	}
            	
            	admin.append("#_E-Mail-Verify_:°%45°").append(target.getEmailVerify() == 2 ? "Verifiziert":target.getEmailVerify()==1?"Verifizierung läuft...":"Nicht verifiziert").append("°%00°");
                
            	if(online) {
            		admin.append("#_Online seit_:°%45°").append(target.getLoginTime()).append("°%00°");
            	}
            
            	if(target != Server.get().getButler()) {
            		if(online && !target.getChannel().isVisible()) {
            			admin.append("#_Online in_:°%45>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<%00°");
            		}
            	}
            	
            	if(!target.getMentor().isEmpty()) {
            		admin.append("#_Mentor_:°%45°").append("°>_h").append(target.getMentor().replace("<", "\\<")).append("|/serverpp \"|/w \"<°°%00°");
            	}
            }
            
            if(!target.getCmcomments().isEmpty()) {
            	admin.append("##_CM-Comments_:#").append(target.getCmcomments());
            }
            
            for(String[] infos : client.admincalls) {
            	String verstoss = infos[0];
        		String id = infos[1];
        		String uhrzeit = infos[2];
            	
            	beschwerden.append("#_").append(verstoss).append(" - *°>_h").append(id).append("|/admincall info:\"<°_ (").append(uhrzeit).append(")");
            }
            
            if(!beschwerden.toString().isEmpty()) {
            	admin.append("##_Beschwerden_:#").append(beschwerden.toString());
            }
        
            if(!target.getComments().isEmpty()) {
            	admin.append("##_Comments_:#");
            	admin.append(target.getComments());
            }
            
			content.newTab(title, readme, ">Symbole/Admin5.png<>_h", null, admin.toString());
		}
		
		whois.append(content.getSwitchTab());
		
		Popup popup2		= new Popup("Profil von " + nickname, null, whois.toString(), 500, 600);
		/*
		 * 1 = old blue
		 * 2 = new blue
		 * 3 = admin red
		 */
		//popup2.setDesign(3);
		popup2.setDesign(2);
		Panel panel			= new Panel();
		Button button		= new Button("        OK        ");
		
		button.setStyled(true);
		panel.addComponent(button);
		popup2.addPanel(panel);
		client.send(popup2.toString());
	}

}

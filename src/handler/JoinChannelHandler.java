/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (C) 2011 - 2012 Flav <http://banana-coding.com>
 * 
 * Diese Dateien unterliegen dem Coprytight von Banana-Coding und
 * darf verändert, aber weder in andere Projekte eingefügt noch
 * reproduziert werden.
 * 
 * Der Emulator dient - sofern der Client nicht aus Eigenproduktion
 * stammt - nur zu Lernzwecken, das Hosten des originalen Knuddels Clients
 * ist auf eigene Gefahr und verstößt möglicherweise gegen Schutzrechte
 * der Knuddels.de GmbH & Co KG
 * 
 * Autoren: Flav (Grundversion), Localhost (Erweiterte Version), Kokos-Ice (Erweiterte Version)
 */



package handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.ReceiveOpcode;
import starlight.Server;
import starlight.Settings;
import tools.HexTool;
import tools.Logger;
import tools.PacketCreator;
import tools.ProfileTools;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;
import tools.popup.TextField;

public class JoinChannelHandler {
    
    
        private static NumberFormat nf;

	static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
	}
    public static void handle(String[] tokens, Client client) {
        Long time = System.currentTimeMillis()/1000;
        String nickname = tokens[2];
        String passwort = tokens[3];

         client.receivedHearts.clear();
         client.oldMessages.clear();
	 client.sentMessages.clear();
	 client.newMessages.clear();
        client.loadStats(client.getName());
        
        if (nickname.isEmpty()) {
            PopupNewStyle popup = new PopupNewStyle("Problem", "Nickname fehlt", "##Um in den Chat eintreten zu können, musst Du _vorher einen Nick registrieren_.##Klicke dazu auf der Webseite auf folgenden Button:##°>neu_reg.gif<°", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (Localhost)", client.getName()));        
                        return;        
       }
        
        if(client.getLogin()+1 >= time) {
        	return;
        }
        
       
        
        PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;

        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery(String.format("SELECT `name`, `password` FROM `accounts` WHERE `name` = '%s'", nickname));
            
            if (!rs.next()) {
                PopupNewStyle popup = new PopupNewStyle("Problem", "Nick existiert nicht", String.format("##Der Nickname '%s' ist _nicht registriert_.##Um diesen Nicknamen zu registrieren, klickst Du auf folgenden Button:##°>reg.png<>--<>|%sregistrieren.php<°", nickname, Server.get().getURL()), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;         
            }

            if (!HexTool.hash("SHA1", passwort).equals(rs.getString("password"))) {
            
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Falsches Passwort", String.format("#_Falsches Passwort_ für %s verwendet. Achte auf mögliche Groß-/Kleinschreibung deines Passworts.##°B>Passwort vergessen/funktioniert nicht mehr?|%spwforgot.php<r°##Um einen neuen Nicknamen zu registrieren, klick den folgenden Button an:##°>reg.png<>--<>|%sregistrieren.php<°", rs.getString("name"), Server.get().getURL(), Server.get().getURL()), 400, 325);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
            }
            
            client.login(nickname);
            client.increaseUserLogin(+1);

            
            if (client.getBot() != 0) {
                       client.setBot((byte)0);
            }       
            
            if (client.getDeletenick() != 0) {
                       client.setDeletenick((byte)0);
                       Server.get().newMessage(Server.get().getButler().getName(), client.getName(),"Löschvorgang abgebrochen!", "Am Datum-XYZ wurde mit der IP-Adresse XYZ im Channel XYZ die Löschung deines Nicknamen aktiviert. Zur Sicherheit wurde der Löschvorgang durch die erneut Verwendung von "+client.getName()+" abgebrochen.#Wenn du selber die Löschung nicht beantragt hast, kontrolliere bitte ob jemand Fremdes Zugang zu deinem Passwort, deinem Computer oder zu deiner E-Mailadresse hatte.#Die letzten Logins deines Nicknamen kannst du °>hier|/loginlist<° einsehen.", (System.currentTimeMillis()/1000));      
                     
                       
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            pcon.close();
        }
        
        
        
        
    	
       if(!client.getAppletVersion().equals("V9.0biw")) {
    		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("#Leider verwendest du ein zu altes Applet, um den Chat benutzen zu können.##Bitte _leere deinen Browser-Cache und starte deinen Browser nochmal neu_.##Solltest du weiterhin Probleme haben, verwende übergangsweise diesen Link um den Chat zu betreten:#_°BB>%sindex.php|\"<°°r°_##Entschuldige die Unanehmlichkeiten,#Dein "+Server.get().getSettings("CHAT_NAME")+"-Team", Server.get().getURL()), 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        
                Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (Applet veraltet).", client.getName()));
            	return;
    	}
        
        if(client.getSperre() != 0) {     
            PopupNewStyle popup = new PopupNewStyle("Nick Gesperrt", "Nick Gesperrt", String.format("Dein Nick %s wurde %s.##_Begründung_:#%s##§Bei Rückfragen zu dieser Sperrung bitte an den Admin %s als Ansprechpartner per /m im Chat wenden.", client.getName(), client.getSperre() == 1 ? "_permanent gesperrt_":String.format("bis _einschließlich %s gesperrt_ und wird am darauffolgenden Tag automatisch entsperrt", Server.get().timeStampToDate(client.getSperre())), client.getSperreinfo(), client.getSperrevon()), 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (Nick ist gesperrt)", client.getName()));
                        return;
        }
        
        if(client.getDisable() != 0) {
    	PopupNewStyle popup = new PopupNewStyle("Nick Deaktiviert", "Nick Deaktiviert", String.format("Dein Nick %s wurde _deaktiviert_.##_Begründung_:#%s", client.getName(), client.getDisableinfo(), Server.get().getURL()), 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (Nick ist deaktiviert)", client.getName()));     
                        return;     
    	}
               
       
 
         
         
         String agent = client.getAgent(); // FDa hastes
         
       /*  if(ProfileTools.getOS(agent) == null) {
             Popup popup = new Popup("OS ungültig", "OS ungültig", String.format("Dein Betriebssystem (OS) ist für den Chat aus Sicherheitsgründen nicht zugelassen.", client.getName(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setSperrmeldung(1);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (OS nicht zugelassen)", client.getName()));        
                        return;  
         }*/
     
        
        if(client.getName().equals("Localhost")) {
    		PopupNewStyle popup = new PopupNewStyle("Nickname unzulässig", "Nickname unzulässig", String.format("##Dieser Nickname ist für den Chat nicht zugelassen.", Server.get().getURL()), 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (Localhost)", client.getName()));        
                        return;          
    	}
        
    
 
        if (client.isAway()) {
            client.setAway(false);
        }
        
        
        String ch = tokens[1];
        Channel channel = Server.get().getChannel(ch);

        if (channel == null) {
        	if(ch.startsWith("/") && client.hasPermission("createtemporarychannel") || client.hasPermission("createchannel")) {
        		if (ch.length() > 20) {
        			client.send(PopupNewStyle.create("Problem", "Problem", String.format("#Der Channel %s kann nicht neu angelegt werden, da er aus zu vielen Zeichen besteht. Maximal sind _20 Zeichen_ erlaubt.", ch), 400, 300));
                    return;
            	}

            	if ((ch.indexOf(">") >= 0) || (ch.indexOf("}") >= 0) || (ch.indexOf("{") >= 0) || (ch.indexOf("[") >= 0) || (ch.indexOf("]") >= 0) || (ch.indexOf("_") >= 0) || (ch.indexOf("`") >= 0) || (ch.indexOf("´") >= 0) || (ch.indexOf("<") >= 0) || (ch.indexOf(";") >= 0) || (ch.indexOf(",") >= 0) || (ch.indexOf(".") >= 0) || (ch.indexOf("?") >= 0) || (ch.indexOf("!") >= 0) || (ch.indexOf("%") >= 0) || (ch.indexOf("°") >= 0) || (ch.indexOf("\"") >= 0) || (ch.indexOf("(") >= 0) || (ch.indexOf(")") >= 0) || (ch.indexOf("=") >= 0) || (ch.indexOf("#") >= 0) || (ch.indexOf("~") >= 0) || (ch.indexOf("'") >= 0 || (ch.indexOf("§") >= 0) || (ch.indexOf("|") >= 0))) {
            		client.send(PopupNewStyle.create("Problem", "Problem", String.format("##Der Channel %s kann nicht neu angelegt werden, da er ein ungültiges Zeichen enthielt. Alle _Buchstaben_ und _Zahlen_ sowie folgende Zeichen sind im Channelnamen erlaubt: ##_+, -, @, ß,  , /, *_",  ch), 400, 300));
                    return;
            	}
            	
            	Server.get().createChannel(ch, 1, client.getName(), 1, null, null);
            	channel = Server.get().getChannel(ch);
            	
            	client.joinChannel(channel);
                client.send(PacketCreator.channelFrame(channel, client.getName(), client.newMessages.size()));
                channel.join(client);
                client.setLogin(System.currentTimeMillis()/1000);

                client.setuPassword(passwort);
        	} else{
        	PopupNewStyle popup = new PopupNewStyle("Problem", "Channellogin nicht möglich", String.format("##Der Channel _%s existiert nicht_.", tokens[1]), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return; 	             
                        }
        	
        	return;
        }

        if (channel.getClients().contains(client)) {
            return;
        }
        
        if(client.checkCl(channel)) {
        	PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Für den Channel %s wurdest Du leider bis morgen früh gesperrt.", channel.getName()), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (CL!).", channel.getName()));
                        return; 	      
        }
        
        if(Settings.getBan().contains(String.format("|%s|", client.getIPAddress())) && client.getRank() < 2) {
        	PopupNewStyle popup = new PopupNewStyle("Problem", "Zugang gesperrt", "#Dein _Internetzugang_ ist für den Chat _bis morgen gesperrt_. Diese Sperre kann folgende Gründe haben:#- Du hast im Chat zuviele Nachrichten auf einmal geschickt#- Du wurdest von einem Admin aufgrund von einer AGB-Verletzung gesperrt#- Den Hergang zu dieser Sperrung kann ein anderer Internetnutzer verursacht haben, der von Deinem Provider dieselbe Internetadresse erhielt", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (IP gesperrt).", client.getName()));
                         return; 	          
        }
        
        if(client.getEmailVerify() < 2) {
        	int minutes = 300000000-client.getOnlineTime()/60;
        
        	if(minutes <= 250) {
        		client.send(PacketCreator.createVerifyWindow(client.getName(), client.getEmail(), 1));
        		return;
        	}
        }
        
        if(client.getRank() < channel.getMinRank() && !channel.getName().equals("Knuddelsmillionär") && !client.checkTeam("Knuddelsmillionär")) {
        	String minrank = "";
        	
        	if(channel.getMinRank() == 1) {
        		minrank = "Familymitglieder";
        	} else if(channel.getMinRank() == 2) {
        		minrank = "Stammis";
        	} else if(channel.getMinRank() == 3) {
        		minrank = "Ehrenmitglieder";
        	} else if(channel.getMinRank() > 3 && channel.getMinRank() < 8) {
        		minrank = "Admins";
        	}else {
        		minrank = "Sysadmins";
        	}
        	
              PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Nur %s können den Channel %s betreten.", minrank, channel.getName()), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Status zu niedrig).", channel.getName()));
                        return;
      
        }
    	
        if(!client.hasPermission("channeljoin")) {
            if(Settings.getMaintenance() == 1) {
                PopupNewStyle popup = new PopupNewStyle("Problem", "Bereit? Wir sind es bald.", String.format("##Der Chat befindet sich momentan im _Wartungsmodus_.##Zur Zeit können ausschließlich Administratoren den Chat betreten. Bitte habe etwas Geduld, der Chat wird in Kürze wieder verfügbar sein."), 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (Wartungsmodus).", client.getName()));
                        return;            
            }
            
        	Server.get().addIP(client.getIPAddress());
        	
        	int count = 0;

            for (Client c : Server.get().getClients()) {
                    if (c.getIPAddress().equals(client.getIPAddress())) {
                            count++;
                    }
            }

            if (count > 1) {
                    PopupNewStyle popup = new PopupNewStyle("Problem", "Login nicht möglich", "##Du kannst dich _maximal mit 2 Nicknamen_ einloggen.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login fehlgeschlagen (mehr als 2 Nicknamen online).", channel.getName()));
                        return;
            }
            
           /* if (Server.get().checkIP(client.getIPAddress())) {
                    client.send(Popup.create("Problem", "Login nicht möglich","Du kannst dich gerade nicht einloggen. (Zu viele Logins)", 400, 300));
                    return;
            }*/
        	if(!client.hasPermission("channeljoin")) 
        	if (channel.countClients() >= channel.getSize() && !client.checkCode(72) && !client.hasPermission("channeljoin")) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Dieser Channel ist auf _maximal %s_ Leute beschränkt, bitte wähl einen anderen Channel.##Oder logge dich im Alternativchannel °>Flirt|/go Flirt<° ein.", channel.getSize()), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Channel überfüllt).", channel.getName()));
                        return;
                  
        	}
        	
        	if(channel.getMinage() != 0 && client.getAge() > channel.getMinage()) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Du kannst diesen Channel nicht betreten, da er für jüngere Nutzer reserviert ist.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());    
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Altersbegrenzung).", channel.getName()));
                        return;
        	}
        	
        	if(channel.getMingender() != 0) {
        		if(client.getGender() != channel.getMingender()) {
        	        PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Dieser Channel kann nur von %s Mitgliedern betreten werden.", channel.getMingender() ==1 ? "männlichen":"weiblichen"), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());           
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Geschlechtsbegrenzung).", channel.getName()));
                        return;
        		}
        	}
        	
                
                
                
                
        	if(client.getStammiMonths() < channel.getMinstammimonths()) {
        	        PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Du hast nicht genug Stammimonate, um diesen Channel betreten zu können.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());                           
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Nicht genug Stammimonate).", channel.getName()));
                        return;
        	}
                
                if(client.getKnuddels() < channel.getMinknuddels()) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Du besitzt leider nicht genug Knuddels, um diesen Channel betreten zu können.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());             
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Nicht genug Knuddels).", channel.getName()));
                        return;
        	}
                
                if(client.getHol() < channel.getMinholpoints()) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Du brauchst mindestens %s High or Low Punkte, um diesen Channel betreten zu können.##Oder logge dich im Alternativchannel °>High or Low|/go High or Low<° ein.", nf.format(channel.getMinholpoints())), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());    
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Nicht genug HOL-Punkte).", channel.getName()));
                        return;
        	}
                
               
                
                if(client.getJumpopunkte() < channel.getMinjumpopoints()) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Du brauchst mindestens %s Jumpo Punkte, um diesen Channel betreten zu können.##Oder logge dich im Alternativchannel °>Jumpo|/go Jumpo<° ein.", nf.format(channel.getMinjumpopoints())), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Nicht genug Jumpo-Punkte).", channel.getName()));
                        return;
        	}
                
                 if(client.getQuizPoints() < channel.getMinquizpoints()) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", String.format("##Du brauchst mindestens %s Quiz Punkte, um diesen Channel betreten zu können.##Oder logge dich im Alternativchannel °>Quiz|/go Quiz<° ein.", channel.getMinquizpoints()), 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Nicht genug Jumpo-Punkte).", channel.getName()));
                        return;
        	}
                
        	if(channel.getName().equals("Foto Verified") && client.getPhoto_verify() == 0) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Du kannst den Channel Foto Verified nur betreten, wenn du ein verifiziertes Foto hast.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());                 
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (Foto nicht verifiziert).", channel.getName()));
                        return;
        	}
                
                
                if(channel.getName().equals("iPhone") && client.getRank() < 8) {
        	        PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Du kannst den Channel iPhone nur mit einem iPhone/ iPad/ iPod Touch betreten.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());                  
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (kein iPhone-Gerät).", channel.getName()));
                        return;
        	}
                
                if(channel.getName().equals("Android") && client.getRank() < 8) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Du kannst den Channel Android nur mit einem Android-Smartphone betreten.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (kein Android-Smartphone).", channel.getName()));
                        return;
        	}
                              
        	
        	if(channel.getName().equals("Smileys")) {
        		if(!client.checkCode(47) && !client.checkCode(48) && !client.checkCode(68)) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Den Channel Smileys kannst Du aus geheimnisvollen Gründen nicht betreten.", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                      	Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (fehlendes Smiley-Feature).", channel.getName()));
                        return;
        		}
        	}
        	
        	if(channel.getName().equals("CMs")) {
        		boolean cm = false;
        		
                for(Channel cha : Server.get().getChannels()) {
                	if(cha.checkCm(client.getName())) {
                		cm = true;
                		continue;
                	}
                }
        		
        		if(!cm) {
        		PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Um diesen Channel zu betreten, musst du CM oder Admin sein", 400, 325);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());                            
                        Server.get().newSysLogEntry(client.getName(), String.format("Login im Channel %s fehlgeschlagen (kein CM oder Admin).", channel.getName()));
                        return;
        		}
        	}
        
        	if(!channel.getPassword().isEmpty()) {
            	Popup popup = new Popup("Passwort notwendig", "Passwort notwendig", String.format("#Für den Channel %s ist ein Passwort notwendig.", channel.getName()), 350, 130);
            	Panel panel8 = new Panel();
            	panel8.addComponent(new Label(String.format("Passwort: ")));
            	panel8.addComponent(new TextField(20));
            	popup.addPanel(panel8);
            	Panel panel2 = new Panel();
            	Button button = new Button("OK");
                button.setStyled(true);
            	panel2.addComponent(new Label("  ")); 
            	button.enableAction();
            	panel2.addComponent(button);
            	Button button3 = new Button("Abbrechen");
                button3.setStyled(true);
                panel2.addComponent(button3);
            	popup.addPanel(panel2);
                
            	popup.setOpcode(ReceiveOpcode.PASSWORD.getValue(), String.format("%s:%s:%s", channel.getName(), channel.getName(), "login"));
            	client.send(popup.toString());
            	return;
        	}
        }
        
        if(client.hasPermission("loginauthentication") && client.getAuthActive() == 1) {
        	Popup popup = new Popup("Sicherheitsabfrage", "Sicherheitsabfrage", String.format("Du bist dabei dich mit dem Nick °R°_%s_§ einzuloggen. Dieser Nickname verfügt über °R°_Sonderrechte_§.##_Aus Sicherheitsgründen musst du zusätzlich einen Sicherheitscode eingeben.:_", client.getName()), 350, 200);
        	Panel panel8 = new Panel();
        	panel8.addComponent(new Label(String.format("Sicherheitscode: ")));
        	panel8.addComponent(new TextField(20));
        	popup.addPanel(panel8);
        	Panel panel2 = new Panel();
        	Button button = new Button("Einloggen");
        	panel2.addComponent(new Label("  ")); 
        	button.enableAction();
                button.setStyled(true);
        	panel2.addComponent(button);
        	Button button3 = new Button("Abbrechen");
                button3.setStyled(true);
                panel2.addComponent(button3);
        	popup.addPanel(panel2);
               
                
            
        	popup.setOpcode(ReceiveOpcode.AUTH.getValue(), channel.getName());
        		
                
                client.send(popup.toString());
            client.setuPassword(passwort);
        	return;
        }

        if(client.getLastOnline()+300 <= time) {
        	String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(time), Server.get().timeStampToTime(time));
        	client.logins.add(new String[] {uhrzeit, client.getIPAddress()});
        	Server.get().query(String.format("insert into loginlist set `name` = '%s', `ip` = '%s', `uhrzeit` = '%s'", client.getName(), client.getIPAddress(), uhrzeit));
       
    	if(Server.count(String.format("select count(name) as a from loginlist where name = '%s'", client.getName())) > 25) {
    			Server.get().query(String.format("delete from loginlist where name = '%s' order by id limit 1", client.getName()));
                      
    		}
        }
    	
        client.setLogin(time);
        client.setKonfetti(false);
         if (client.getSun()==1 && client.getSunEnde() < (System.currentTimeMillis()/1000)) {
            client.setSun((byte) 0);
        }
        if (client.getEffect().equals("wash") && client.getWashEnde() < (System.currentTimeMillis()/1000)) {
            client.setEffect("");
        }
          if (client.getEffect().equals("moskitoBite") && client.getMoskitoEnde() < (System.currentTimeMillis()/1000)) {
            client.setEffect("");
        }
        Logger.handle(null, String.format("%s betritt den Channel %s", client.getName(), channel.getName()));
        client.setuPassword(passwort);
        if(!Server.onlineUsers.contains(client.getName())) {
        	Server.onlineUsers.add(client.getName());
        }
        
        client.joinChannel(channel);
        client.send(PacketCreator.channelFrame(channel, client.getName(), client.newMessages.size()));
        channel.join(client);
        
    }
}

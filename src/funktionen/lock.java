package funktionen;

import static funktionen.his.time;
import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class lock {


    public static void functionMake(Client client,Channel channel, String arg) {





if(!client.hasPermission("cmd.lock")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
            }
        	
        	if(arg.isEmpty()) {
                if(Server.lockedNicks.size() == 0) {
                	client.sendButlerMessage(channel.getName(), "Momentan ist niemand gesperrt.");
                	return;
                }
                
        		StringBuilder lock = new StringBuilder("##Willkommen in der Sperr-Übersicht _"+client.getName()+"_!#Hier findest du eine Übersicht aller derzeit im Chat gesperrten Nicknamen.°-°");

        		lock.append("°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°_§°bir°°12°°>LEFT<°°b°°+0000°°+9505°°S18°_°>gg.png<° Sperrnick°%30°°>gg.png<° Sperradmin°%50°°>gg.png<° Sperrungsdatum°%70°°>gg.png<° Entsperrungsdatum_°%00°_°r°°+9505°°[174,174,255,127]°#°>{colorboxend}<°#°r°°>left<°§#");
        		for(String nick : Server.lockedNicks.keySet()) {
        			String[] split = Server.lockedNicks.get(nick).split("~");
        			String lockedNick = nick.replace("<", "\\<");
        			String sperrAdmin = split[0].replace("<", "\\<");
        			String sperrdatum = split[1];
        			String entsperrdatum = split[2];
        			
        			lock.append("#°>_h").append(lockedNick).append("|/serverpp \"|/w \"<°°%30°°>_h").append(sperrAdmin).append("|/serverpp \"|/w \"<°°%50°").append(sperrdatum).append("°%70°").append(entsperrdatum).append("°%00°");
        		}
                 PopupNewStyle popup = new PopupNewStyle("Gesperrte Nicks", "Gesperrte Nicks",lock.toString(), 700, 500);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
                        
        	}
        	
        	String[] pieces = arg.split(":", 4);
        	String nick = "", comment = "", info = "", tage = "";
        	String infor = "Bitte die Funktion folgendermaßen benutzen:#/lock oder !NICK:COMMENT oder NICK:COMMENT:INFO:TAGE#(Zeigt eine Liste aller gesperrten Nicks. !NICK:COMMENT entsperrt NICK wieder und setzt COMMENT als Adminkommentar bei NICK. Letzteres sperrt NICK TAGE Tage für den Chat. Ist keine Angabe bei TAG gemacht worden, wird NICK automatisch für 3 Tage gesperrt. ! als TAG sperrt NICK permanent.)";
        	boolean entsperren = false;
        	
        	try { 
        		nick = pieces[0];
        		comment = pieces[1];
        		info = pieces[2];
        		tage = pieces[3];
        	} catch(Exception ex) {
        	}
        	
        	if(nick.startsWith("!")) {
        		entsperren = true;
        		nick = nick.replace("!", "");
        	}
        	
        	if(nick.isEmpty() || comment.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), infor);
        		return;
        	}
        	
        	Client target = Server.get().getClient(nick);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(nick);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nick));
        			return;
        		}
        	}
        	
        	if(comment.length() < 8 || comment.length() > 115) {
        		client.sendButlerMessage(channel.getName(), "Das Comment muss mindestens 8 und darf maximal 115 Zeichen lang sein.");
        		return;
        	}

            comment = KCodeParser.parse(client, comment, 5, 10, 20);
            comment = Server.get().parseSmileys(client, comment);
            
        	if(entsperren) {
        		if(target.getSperre() == 0) {
        			client.sendButlerMessage(channel.getName(), String.format("%s ist nicht gesperrt.", target.getName()));
        			return;
        		}
        		
        		if(client.getRank() < 7 && !client.checkTeam("Vertrauensadmin")) {
        			if(!target.getSperrevon().equals(client.getName())) {
        				client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht entsperren, weil du nicht der Sperradmin bist. Dein Comment wurde an den Sperradmin %s geschickt, weil der Nick deiner Meinung nach entsperrt werden sollte.", target.getName(), target.getSperrevon()));
        				Server.get().newMessage(Server.get().getButler().getName(), target.getSperrevon(), "Entsperrung", String.format("%s hat versucht den Nick %s, der von dir gesperrt wurde, zu entsperren.##_Begründung_ von %s:#%s", client.getName(), target.getName(), client.getName(), comment), time);
        				return;
        			}
        		}
        		
        		client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wurde _entsperrt_.", target.getName().replace("<", "\\<")));
        		target.setComment(time, null, "Entsperrt!", client.getName(), comment);
        		Server.get().query(String.format("update accounts set sperrewann='', sperre='0', sperrevon='', sperreinfo='' where name='%s'", target.getName()));
        		Server.lockedNicks.remove(target.getName());
        		return;
        	}
        	
        	if(info.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), infor);
        		return;
        	}
        	
        	if(tage.equals("sug")) {
        		if(info.equals("!")) {
        			if(target.getWahlsperre() == 0) {
            			client.sendButlerMessage(channel.getName(), String.format("%s hat keine Wahlsperre.", target.getName()));
        				return;
        			}
        			
        			client.sendButlerMessage(channel.getName(), String.format("%s wurde für alle _Wahlen entsperrt_.", target.getName()));
        			Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Wahlsperre entfernt", String.format("Du wurdest von %s wieder _für alle Wahlen entsperrt_.", client.getName()), time);
        			
        			if(online) {
        				target.setWahlsperre(0);
        			} else {
        				Server.get().query(String.format("update accounts set wahlsperre='0' where name='%s'", target.getName()));
        			}
        			
        			target.setComment(time, null, "Für alle Wahlen entsperrt!", client.getName(), comment);
            		return;
        		}

    			if(target.getWahlsperre() != 0) {
        			client.sendButlerMessage(channel.getName(), String.format("%s ist bereits für alle Wahlen gesperrt.", target.getName()));
    				return;
    			}
    			
        		DateFormat formater = DateFormat.getDateInstance();
        		formater.setLenient(false);
            
        		try{
        			formater.parse(info);
        		}catch(ParseException e) {
        			
        		}
        		
        		long diffDays = 0;
        		
        		try {
        			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        			Date from = df.parse(Server.get().timeStampToDate(time));
        			Date to = df.parse(info);
        			long diffMillis = to.getTime() - from.getTime();
        			diffDays = Math.round(diffMillis / (1000. * 60. * 60. * 24.));
        		} catch (ParseException ex) {
        			return;
        		}
        		
                if(diffDays < 1) {
                	client.sendButlerMessage(channel.getName(), "Du musst mindestens das Datum von morgen angeben.");
                }

                long when = (int) (time+(diffDays*86400));
                
    			client.sendButlerMessage(channel.getName(), String.format("%s wurde bis zum _%s (%s Tage) für alle Wahlen gesperrt_.", target.getName(), info, diffDays));
    			Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Wahlsperre", String.format("Du wurdest von %s bis zum _%s für alle Wahlen gesperrt_.", client.getName(), info), time);
    			
    			if(online) {
            		target.setWahlsperre(when);
            	} else {
            		Server.get().query(String.format("update accounts set wahlsperre='%s' where name='%s'", when, target.getName()));
            	}
            	
    			target.setComment(time, null, String.format("°B°\"(°R°%s Tage°B°)\" Wahlsperre! \"(bis zum %s)\"°B°", diffDays, info), client.getName(), comment);
            	return;
            }
        	
        	if(target.getSperre() != 0) {
        		client.sendButlerMessage(channel.getName(), String.format("%s ist bereits gesperrt.", target.getName()));
        		return;
        	}
        	
        	
        	if(info.length() < 20 || info.length() > 2000) {
        		client.sendButlerMessage(channel.getName(), "Die Info muss mindestens 20 und darf maximal 2000 Zeichen lang sein.");
        		return;
        	}
        	
        	if(target == client || target.getRank() > 4 && client.getRank() < 7 || target == Server.get().getButler()) {
        		client.sendButlerMessage(channel.getName(), String.format("%s kann nicht gesperrt werden.", target.getName()));
        		return;
        	}
            
            if(tage.isEmpty()) {
            	tage = "3";
            }
            
            if(tage.equals("!")) {
            	tage = "462";
            }
            
            try {
            	Integer.parseInt(tage);
            } catch(Exception ex) {
            	client.sendButlerMessage(channel.getName(), "Du musst eine Zahl angeben.");
            	return;
            }
            
            int days = Integer.parseInt(tage);
            long when = (int) (time+(days*86400));
            
            if(days < 1) {
            	client.sendButlerMessage(channel.getName(), "Du musst eine Zahl >= 1 angeben.");
            }

            info = KCodeParser.parse(client, info, 15, 10, 20);
            info = Server.get().parseSmileys(client, info);
        	String sperrewann = Server.get().timeStampToDate(time);
        	String entsperrdatum = Server.get().timeStampToDate(when);
        	
        	client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wurde %s °>sounds/gesperrt.mp<°_gesperrt_.%s", target.getName().replace("<", "\\<"), tage.equals("462")?"_permanent_":String.format("für _%s Tage_", days), target.getRank() == 1 && days > 30 && days != 462?" Außerdem wurde automatisch eine Wahlsperre von _180 Tagen_ gesetzt.":""));
        	if(online) {
                    target.setLocks(target.getLocks()+1);
        		String title = "Chat-Knigge - die Benimmregeln für den Chat";
                        target.send(Popup.create(title, title, "°>sounds/ban.mp<°_Chat-Knigge, Benimmregeln für den Chat_##Damit dir, deinen Freunden und allen anderen Menschen "+Server.get().getSettings("CHAT_NAME")+" immer Spaß macht, ist es wichtig, dass alle Mitglieder Grundregeln einhalten. Diese Grundregeln sorgen dafür, dass selbst bei Tausenden von Mitgliedern alle aufeinander Rücksicht nehmen und sich respektvoll begegnen. Du kannst von anderen erwarten, dass sie auf dich Rücksicht nehmen, dir freundlich begegnen solange du anderen Mitgliedern immer mit Respekt begegnest.##Der Knigge besteht aus den zehn wichtigsten Grundregeln, an die du dich stets halten solltest, da diese Regeln ein respektvolles und tolerantes Klima im Umgang miteinander schaffen.#Akzeptierst du diese Grundregeln nicht, wirst du innerhalb unserer Gemeinschaft keine Akzeptanz finden. Menschen, die Streit und Ärger suchen, sind bei NetChat fehl am Platz. Deshalb kann ein deutlicher Verstoß gegen die Regeln zu einer permanenten Sperre führen.##1.#Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##2.#Werbung jeder Art (z.B. für die eigene Homepage, Foto bzw. Wünsche nach Fotokommentaren oder für Mychannel, ...) stört alle Anwesenden und sollte unterlassen werden. Außerdem ist die Wiederholung derselben öffentlichen Nachricht nicht erwünscht.#3.#Ständiges Schreiben in °20°großer§ Schrift, _fett_, in GROSSBUCHSTABEN, _°B°b°R°u°G°n°Y°t_§ und _°E°farbig§, oder mit vielen !!!!! wird im Chat meist als Schreien oder als Aggression interpretiert, vermeide es.##Öffentlich sichtbare Nachrichten sollen immer in Deutsch oder in Ausnahmen auf Englisch verfasst werden, da sonst die Mehrzahl der Anwesenden (inkl. der Administration) deine Nachricht nicht verstehen kann.##Verwende keine nicht lesbaren Schriftfarben, wie z.B. die des Hintergrundes.##Falls bestimmte Textformatierungen oder Sprachen in einem Channel ausdrücklich zugelassen sind, so ist dies in den jeweiligen channeleigenen Regeln (/info)  oder im Channelthema vermerkt.##4.#Rassistische Äußerungen sind, neben allen weiteren Äußerungen, die gegen Verfassung, Recht und die guten Sitten verstoßen, strengstens verboten. Solltest du jemanden beobachten, der gegen diese Regel verstößt, rufe bitte umgehend einen Channelmoderator oder Admin.##5.#Öffentliche und private vulgäre Anmachen stoßen auf Ablehnung. Denke daran, dass auch im Chat primitives, sexorientiertes Verhalten eine Belästigung darstellt. Die Frage nach Cybersexpartnern oder erotischen Camchats via Messenger schließt dies ein.##6.#Vergiss bitte nicht, dass der Channel nicht dir und deinem Gesprächspartner allein gehört. Insbesondere durch öffentlich geführte Gespräche über private/intime Themen (wie bspw. das eigene Liebesleben) oder hitzige Diskussionen, fühlen sich andere Mitglieder oft gestört. In so einem Fall solltet ihr privat weiterreden.##Das permanente, ununterbrochene Fragen nach Gesprächspartnern (z.B. Wer will chatten?) stört das Chatklima und ist meist nicht erwünscht.##7.#Wenn dich etwas nervt oder aufregt, solltest du mit Gelassenheit und Größe ruhig und freundlich reagieren. Menschen, die dich ohne Grund angreifen, verdienen deine Aufmerksamkeit nicht. Ignoriere sie einfach mit der /ig NICK Funktion, das ist die beste Antwort.##8.#Neu angemeldeten Chattern solltest du hilfsbereit und freundlich begegnen. Wer sich gerade neu angemeldet hat, ist meist etwas überfordert mit der Flut an Chatfunktionen und dankbar für jede noch so kleine Hilfe.##9.#Die demokratisch gewählten Channelmoderatoren und Admins machen ihren Job ehrenamtlich. Wenn du dich mit einem Problem oder einer Frage an sie wendest, solltest du dies immer freundlich tun. Du musst Verständnis haben, wenn sie dir manchmal nicht oder erst später helfen können.##10.#Sollte jemand deutlich gegen diese Regeln verstoßen, dann weise ihn einfach freundlich auf seinen Fehler hin. Erkläre mit Nachsicht, dass es gegen den Knigge verstößt.#Sollte dies nicht helfen, wende dich bitte an einen Channelmoderator oder Admin.", 400, 250));
                        target.logout("Ausgeloggt.");
        	}
                
        	
        	Server.get().query(String.format("update accounts set sperrewann='%s', sperre='%s', sperreinfo='%s', sperrevon='%s' where name='%s'", sperrewann, tage.equals("462")?1:when, info.replace("'", "\\'"), client.getName(), target.getName()));
        	target.setComment(time, null, days==462?"°R°\"Permanent\"°B° gesperrt!":String.format("°B°\"_Bis %s (_°R°%s Tage°B°_)_\" gesperrt!°B°", entsperrdatum, days), client.getName(), comment);
        	Server.lockedNicks.put(target.getName(), String.format("%s~%s~%s", client.getName(), sperrewann, days == 462 ? " ":entsperrdatum));
        	
        	if(target.getRank() == 1 && days > 30 && days != 462) {
        		long diffDays = 0;
                long wann = (int) (time+(180*86400));
                
        		try {
        			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        			Date from = df.parse(Server.get().timeStampToDate(time));
        			Date to = df.parse(Server.get().timeStampToDate(time+(86400*180)));
        			long diffMillis = to.getTime() - from.getTime();
        			diffDays = Math.round(diffMillis / (1000. * 60. * 60. * 24.));
        		} catch (ParseException ex) {
        			return;
        		}
        		
        		Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Wahlsperre", String.format("Du wurdest bis zum %s für alle Wahlen gesperrt.", info), time);
    			Server.get().query(String.format("update accounts set wahlsperre='%s' where name='%s'", wann, target.getName()));
            	target.setComment(time, null, String.format("Bis zum %s (°R°%s Tage°B°) für alle Wahlen gesperrt!", Server.get().timeStampToDate(time+(86400*180)), diffDays), Server.get().getButler().getName(), String.format("Automatische Wahlsperre durch Sperrung von %s", client.getName()));
        	}
        	
        	for(Channel c : Server.get().getChannels()) {
        		if(c.isVisible()) {
        			if(c.checkCm(target.getName())) {
        				for(String hz : c.getHZ().split("\\|")) {
        					if(!hz.isEmpty()) {
        						Server.get().newMessage(Server.get().getButler().getName(), hz, "CM gesperrt", String.format("Hallo %s,##der CM °>_h%s|/serverpp \"|/w \"<° aus deinem HZA-Channel %s wurde _von %s %s gesperrt_.##_Begründung_ von %s:##%s", hz, target.getName().replace("<", "\\<"), c.getName(), client.getName(), days == 462 ? "permanent":String.format("für %s Tage", days), client.getName(), info), time);
        					}
        				}
        			}
        		}
        	}
    }
}

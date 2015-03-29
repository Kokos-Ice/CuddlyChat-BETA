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
     
import tools.popup.Popup;
import tools.HexTool;
import tools.PacketCreator;
import tools.Source;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import starlight.Client;
import starlight.CommandParser;
import starlight.Server;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.PopupNewStyle;
     
public class EditHandler {

    private static Pattern emailPattern;
    private static final String validEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    static {
        emailPattern = Pattern.compile(validEmail);
    }
    
    @SuppressWarnings("unused")
	public static void handle(String[] tokens, Client client) {
        String[] params = tokens;
        
        Client rightUser = client;
        
    
        String nick = client.getName();
        String receivedNick = params[1].split("\\|")[0];
        
        if(client.hasPermission("edit.nickname") && !nick.equals(receivedNick)) {
        	client = Server.get().getClient(receivedNick);
                
                  if (client == null) {
         client = new Client(null);
         client.loadStats(receivedNick);
     }
                  
        }
        
       
         
        int receivedID = Integer.parseInt(params[1].split("\\|")[1]);
        
        if (params.length == 3) {
            String text = params[2].trim();

            if (text.equals("Editieren")) {
                CommandParser.parse("/mychannel", client, client.getChannel(), false);
                return;
            } 

            if (!text.equals("Speichern")) {
                CommandParser.parse(String.format("/e description %s", text), client, client.getChannel(), false);
               Server.get().newSysLogEntry(client.getName(), String.format("Profil editiert.", client.getName()));
               return;
            }
        }
        
        StringBuilder errors = new StringBuilder();
        StringBuilder hinweise = new StringBuilder();
        String currentPass = params[3].trim();
        String newPass1 = params[4].trim();
        String newPass2 = params[5].trim();
        String email = params[6].trim();
        boolean saveEmail = true;
        String city = "", bundesland = "";
        int showEmail = Integer.parseInt(params[7].trim());
        int getEmails = Integer.parseInt(params[8].trim());
        int showBirthday = Integer.parseInt(params[9].trim());
        int showZodiac = Integer.parseInt(params[10].trim());
        int activateSearch = Integer.parseInt(params[11].trim());

        String searchGender = params[12].trim();
        String searchAgeFrom = params[13].trim();
        String searchAgeUntil = params[14].trim();
        String searchMotiv = params[15].trim();
        String searchEntfernung = params[16].trim();
        String age = params[17].trim();
        String gender = params[18].trim();
        String plzlala = "";
        String vergeben = params[19].trim();
        String stadt = params[20].trim();
        String land = params[21].trim();
        String hobbys = params[22].trim();
        String motto = params[23].trim();
        String realname = params[24].trim();
        String birthday = params[25].trim();
        String plzLand = params[26].trim();
        String plz = params[27].trim();
        String job = params[28].trim();
        String spitznamen = params[29].trim();
        int stadtlang = stadt.length();
        int landlang = land.length();
        int realnamelang = realname.length();
        int mottolang = motto.length();
        int hobbyslang = hobbys.length();
        int joblang = job.length();
        boolean savePlz = true;
        boolean savePlzLand = true;
        boolean saveAge = true;
        boolean saveGender = true;
        boolean saveStadt = true;
        boolean saveLand = true;
        boolean saveHobbys = true;
        boolean saveJob = true;
        boolean saveMotto = true;
        boolean saveShowZodiac = true;
        boolean saveBirthday = true;
        boolean saveSpitznamen = true;
        boolean saveRealname = true;
        byte geschlecht = 0;
        
        if(rightUser.getRank() < 10) {
        	if(!nick.equals(receivedNick) || receivedID != client.getID()) {
            	client.send(PopupNewStyle.create("Problem", "Nicht eingeloggt", String.format("#Du bist _nicht_ mit ndem Nick %s _eingeloggt_.", receivedNick), 300, 200));
            	Server.get().newSysLogEntry(client.getName(), String.format("Profil editieren fehlgeschlagen (Nicht eingeloggt).", client.getName()));
                return;
        	}
        
        	if(!HexTool.hash("SHA1", currentPass).equals(client.getPassword())) {
            		Popup popup = new Popup("Problem", "Problem", "#Bitte gib unter _'Passwort'_ dein aktuelles Passwort korrekt an.", 300, 200);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                Server.get().newSysLogEntry(client.getName(), String.format("Profil editieren fehlgeschlagen (Falsches Passwort).", client.getName()));
                return;
        	}
        }

        if(Server.plz.containsKey(plz)) {
        	String[] split = Server.plz.get(plz);

        	plzlala = plz;
        	city = split[0];
        	bundesland = split[1];
        }

        if(!newPass1.isEmpty() || !newPass2.isEmpty()) {
            if(!newPass1.equals(newPass2)) {
                errors.append("##Trag bei _'Neues Passwort'_ und bei _'Neues Passwort (2x)'_ dasselbe _neue Passwort_ ein.");
            } else if(!newPass1.isEmpty() && newPass1.length() < 5) {
                errors.append("##Das von dir gewünschte neue Passwort ist _unsicher_, da es weniger als 5 Zeichen enthält. Dein _Passwort wurde nicht geändert_, bitte wähl ein anderes Passwort.");
            } else {
                client.setPassword(HexTool.hash("SHA1", newPass1));
                client.setuPassword(newPass1);
            }
        }
        
        if(email.isEmpty()) {
            saveEmail = false;
            errors.append("##Trag bei _'E-Mail'_ deine richtige E-Mail-Adresse ein.");
        } else if(!emailPattern.matcher(email).matches()) {
            saveEmail = false;
            errors.append("##E-Mail-Adresse fehlerhaft");
        }
        
        if(!email.equalsIgnoreCase(client.getEmail())) {
        	if(client.getEmailVerify() == 1) {
        		saveEmail = false;
        		hinweise.append("##Die Verifizierung deiner E-Mail läuft momentan und daher kann deine E-Mail nicht geändert werden. °>Hier|/e description Verifizierung läuft...<° kannst du Einstellungen zur Verifizierung vornehmen.");
        	} else if(client.getEmailVerify() == 2) {
        		saveEmail = false;
        		hinweise.append("##Deine E-Mail wurde nicht gespeichert. °>Hier|/e description changever<° kannst du deine E-Mail umverifizieren.");
        	}
        }
        
        if(age.isEmpty()) {
            saveAge = false;
            errors.append("##Das _'Alter'_ muss angegeben werden.");
        } else if(!Server.get().isInteger(age)) {
        	saveAge = false;
        	errors.append("##Trag bei _'Alter'_ eine Zahl ein.");
        }
        
        
        if(saveAge == true) {
            int alter = Integer.parseInt(age);
        
            if(alter < 10 || alter > 90) {
                errors.append("##Das Alter muss zwischen 10 und 90 liegen.");
            } else if(client.getAge() != alter) {
                errors.append("##- Ihr _Alter wurde nicht geändert_, da Ihre Angabe zu stark von dem Alter abweicht, dass Sie zuvor angegeben hatten.");
                Server.get().newSysLogEntry(client.getName(), String.format("Profil editieren fehlgeschlagen (Altersabweichung).", client.getName()));
            } else {
                client.setAge((byte)alter);
            }
        }
        
        if(gender.isEmpty()) {
            saveGender = false;
            errors.append("##Das _'Geschlecht'_ muss angegeben werden.");
        }
        
        if(gender.equals("männl.")) {
            geschlecht = 1;
        } else if(gender.equals("weibl.")) {
            geschlecht = 2;
        }
        
        if(client.getGender() != geschlecht) {
            saveGender = false;
            errors.append("##- Ihr _Geschlecht wurde nicht geändert_, da Ihre Angabe zu stark von dem Geschlecht abweicht, dass Sie zuvor angegeben hatten.");
            Server.get().newSysLogEntry(client.getName(), String.format("Profil editieren fehlgeschlagen (Geschlechtsabweichung).", client.getName()));
        }
        
        if(stadtlang > 50 && !client.hasPermission("unlimitedchars")) {
            saveStadt = false;
            errors.append("##Deine Angabe unter _'Stadt'_ ist ").append(stadtlang-50).append(" Zeichen _zu lang_.");
        }
        
        if(landlang > 50 && !client.hasPermission("unlimitedchars")) {
            saveLand = false;
            errors.append("##Deine Angabe unter _'Land'_ ist ").append(landlang-50).append(" Zeichen _zu lang_.");
        }
        
        if(hobbyslang > 80 && !client.hasPermission("unlimitedchars")) {
            saveHobbys = false;
            errors.append("##Deine Angabe unter _'Hobbys'_ ist ").append(hobbyslang-50).append(" Zeichen _zu lang_.");
        }
        
        if(mottolang > 400 && !client.hasPermission("unlimitedchars")) {
            saveMotto = false;
            errors.append("##Deine Angabe unter _'Motto'_ ist ").append(mottolang-50).append(" Zeichen _zu lang_.");
        }
        
        if(realnamelang > 50 && !client.hasPermission("unlimitedchars")) {
            saveRealname = false;
            errors.append("##Deine Angabe unter _'Realname'_ ist ").append(realnamelang-50).append(" Zeichen _zu lang_.");
        }
        
        if(!birthday.isEmpty() && client.getVerify() == 0) {
            DateFormat formater = DateFormat.getDateInstance();
            formater.setLenient(false);

            try{
                formater.parse(birthday);
            }catch(ParseException e) {
                saveBirthday = false;
                errors.append("##Gib dein richtiges _Geburtsdatum_ im Format _TT.MM.JJJJ_ (z.B. 15.06.1966) an.");
            }
        }
        
        if(showZodiac == 1 && birthday.isEmpty()) {
            saveShowZodiac = false;
            errors.append("##Gib ein _korrektes Geburtsdatum_ an, damit dein _Sternzeichen_ angezeigt wird.");
        }
        
        if(joblang > 50 && !client.hasPermission("unlimitedchars")) {
            saveJob = false;
            errors.append("##Deine Angabe unter _'Job'_ ist ").append(joblang-50).append(" Zeichen _zu lang_.");
        }
        
        if(client.getRank() > 1) {
            String lieblingschannel = params[30].trim();
            int lcsperre = client.getLCSperre();
            
            if(lcsperre == 1) {
                if(lieblingschannel.isEmpty()) {
                    client.setLCSperre((byte)2);
                    client.setLC("");
                    hinweise.append("##Du hast nun keinen Lieblingschannel mehr. Du kannst deinen Lieblingschannel erst im nächsten Monat wieder ändern.");
                }
            } else if(lcsperre == 0) {
                if(!lieblingschannel.isEmpty()) {
                	if(!client.getLC().equals(lieblingschannel)) {
                		client.setLCSperre((byte)2);
                    	client.setLC(lieblingschannel);
                    	client.setLcmonths((byte)0);
                    	hinweise.append("##Dein Lieblingschannel ist nun _").append(lieblingschannel).append("_. Du kannst deinen Lieblingschannel erst im nächsten Monat wieder ändern.");
                	}
                }
            }
        }
        
        if(!plz.isEmpty() && plzLand.isEmpty()) {
        	savePlz = false;
        	savePlzLand = false;
        	errors.append("##Gib auch das _Land deiner Postleitzahl_ an.");
        } else if(plz.isEmpty() && !plzLand.isEmpty()) {
        	savePlz = false;
        	savePlzLand = false;
        	errors.append("##Gib eine _Postleitzahl_ für dein Land an.");
        }
        
        if(!plz.isEmpty() && !plzLand.isEmpty()) {
        	if(plz.length() < 5) {
        		savePlz = false;
        		savePlzLand = false;
        		errors.append("##Du musst mindestens die _ersten 5 Ziffern_ deiner _Postleitzahl_ angeben.");
        	} else if(plz.length() > 5) {
        		savePlz = false;
        		savePlzLand = false;
        		errors.append("##Die _Postleitzahl darf maximal 5 Ziffern_ enthalten.");
        	} else {
        		if(plzlala.isEmpty()) {
        			String url = String.format("http://onlinestreet.de/plz/%s.html", plz);
        			String source = Source.get(url);
        	
        			try {
        				String km = source.split("</a>\\) zugeordnet.")[0].split("in ")[1];
        			} catch(Exception e) {
        				savePlz = false;
        				savePlzLand = false;
        				errors.append("##Die von dir angegebene _Postleitzahl ").append(plz).append("_ existiert nicht.");
        			}
        		}
        	
        		if(savePlz && savePlzLand) {
        			if(!client.getPlz().equals(plz)) {
        				if(city.isEmpty() && bundesland.isEmpty()) {
        					String url = String.format("http://onlinestreet.de/plz/%s.html", plz);
        					String source = Source.get(url);
            			
        					//city = source.split(" ist als Postleitzahl ")[1].split("\\(")[0].trim();
        					//bundesland = source.split(".html\">in ")[1].split("<")[0];
        				}
        			
        				hinweise.append("##Deine _Postleitzahl ").append(plz).append(" (Ort: ").append(city).append(", Bundesland: ").append(bundesland).append(")_ ist eingetragen und die _Entfernungsbestimmung_ aktiviert.");

            			client.setPlz(plz);
            			client.setPlzLand(plzLand);
            		}
        		}
        	}
        } else {
			client.setPlz("");
			client.setPlzLand("");
        }
        
        if(!spitznamen.isEmpty()) {
        	 if(spitznamen.length() > 50) {
        		saveSpitznamen = false;
        		errors.append("##Du hast zu viele Spitznamen definiert.");
        	} else {
        		for(String x : spitznamen.split(",")) {
        			x = x.trim();
        			
        			if(x.length() > 10) {
        				saveSpitznamen = false;
        				errors.append("##Der angegebene Spitzname ").append(x).append(" ist zu lang.");
        			}
        		}
        	}
        }
        
        int searchAge = 0;

        if (activateSearch == 1) {
        	if (searchAgeFrom.isEmpty() || searchAgeUntil.isEmpty()) {
        		hinweise.append("##Gib in der _Suche & Blinddate_-Kategorie bei _'Alter von'_ in _beiden Feldern_ eine Altersgrenze an.");
        	} else if (Integer.parseInt(searchAgeFrom) > Integer.parseInt(searchAgeUntil)) {
        		hinweise.append("##Gib in der _Suche & Blinddate_-Kategorie bei _'Alter von'_ im ersten Feld ein _geringeres Alter_ als im zweiten Feld an.");
        	} else {
        		client.setSearchAgeFrom((byte) (searchAgeFrom.isEmpty()?0:Integer.parseInt(searchAgeFrom)));
        		client.setSearchAgeUntil((byte) (searchAgeFrom.isEmpty()?0:Integer.parseInt(searchAgeUntil)));
        		searchAge = 1;
        	}

        	if (searchGender.isEmpty()) {
        		hinweise.append("##Damit deine _Sucheinstellungen_ aktiv werden, musst du in der _Suche & Blinddate_-Kategorie ein _Geschlecht_ wählen.");
        	} else if (searchAge == 0) {
        		hinweise.append("##Damit deine _Sucheinstellungen_ aktiv werden, musst du in der _Suche & Blinddate_-Kategorie ein _Altersintervall_ wählen.");
        	} else {
        		client.setSearchActivate((byte)1);
        	}
        } else {
        	client.setSearchActivate((byte)0);
        }
        
        if(!errors.toString().isEmpty()) {
            String title = "Problem";
           // rightUser.send(Popup.create(title, title, String.format("Bei der Änderung deiner Daten sind folgende _Probleme_ aufgetreten:%s##Alle anderen Änderungen wurden _gespeichert_.%s", errors.toString(), hinweise.toString().isEmpty()?"":String.format("##Beachte noch folgende _Hinweise_:_##%s", hinweise.toString())), 450, 275));
             PopupNewStyle popup = new PopupNewStyle(title, title, String.format("##Bei der Änderung deiner Daten sind folgende#_Probleme_ aufgetreten:#%s###Alle anderen Änderungen wurden _gespeichert_.%s", errors.toString(), hinweise.toString().isEmpty()?"":String.format("##Beachte noch folgende _Hinweise_:_##%s", hinweise.toString())), 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        rightUser.send(popup.toString());
        
        
        } else {
            String title = "Änderungen übernommen";
            rightUser.send(Popup.create(title, title, String.format("##Alle Änderungen wurden _gespeichert_.%s", hinweise.toString().isEmpty()?"":String.format("##Beachte noch folgende _Hinweise_:%s", hinweise.toString())), 450, 275));
        }
        
        client.setEmails((byte) getEmails);
        client.setSearchGender(searchGender);
        client.setSearchEntfernung(searchEntfernung);
        client.setSearchMotiv(searchMotiv);
        client.setShowEmail((byte)showEmail);
        client.setShowBirthday((byte)showBirthday);
        client.setSearchActivate((byte)activateSearch);
        client.setVergeben(vergeben);
        
        if(saveShowZodiac) {
            client.setShowZodiac((byte)showZodiac);
        }
        
        if(saveBirthday) {
            client.setBirthday(birthday);
        }
        
        if(saveMotto) {
            client.setMotto(motto);
        }
        
        if(saveRealname) {
            client.setRealName(realname);
        }
        
        if(saveSpitznamen) {
        	if(!client.getSpitznamen().equals(spitznamen)) {
        		ArrayList<String> names = new ArrayList<String>();
        		
                for(String lal : spitznamen.split(",")) {
                	names.add(lal.trim());
                }
                
                client.send(PacketCreator.boldNames(names));
        	}
        	
        	client.setSpitznamen(spitznamen);
        }
        
        if(saveJob) {
            client.setJob(job);
        }
        
        if(saveStadt) {
            client.setStadt(stadt);
        }
        
        if(saveHobbys) {
            client.setHobbys(hobbys);
        }
        
        if(saveLand) {
            client.setLand(land);
        }
        
        if(saveGender) {
            client.setGender(geschlecht);
        }
        
        if(saveEmail) {
            client.setEmail(email);
        }
        
        if(rightUser != client) {
        	Server.get().newSysLogEntry(rightUser.getName(), String.format("Profil von %s Administrativ bearbeitet", client.getName()));
        }
        client.saveValues();
    }
}

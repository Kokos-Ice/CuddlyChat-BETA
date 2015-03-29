package handler;

import static funktionen.f.time;
import java.net.Socket;
import java.sql.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;


public class WhoisEditHandler {
    
    
    
    
    public static int getChoiceValueYesNo(String i) {
        if (i.equals("Ja")) {
            return 1;
        }
        
        return 0;
    }
    
     public static int getChoiceValueVisit(String i) {
        if (i.equals("Ja")) {
            return 0;
        }
        
        return 1;
    }
    
    public static int getChoiceValueGender(String i) {
        if (i.equals("männlich")) {
            return 1;
        } else if (i.equals("weiblich")) {        
            return 2;
        } 
        
        return 0;
        
    } 
    
    
    
   /*  public static int getChoiceValueRank(String i) {
        if (i.equals("Family")) {
            return 1;
        } else if (i.equals("Stammi")) {        
            return 2;
        } else if (i.equals("Ehrenz")) {        
            return 3;
        } else if (i.equals("inof. Admin")) {        
            return 5;
        } else if (i.equals("Admin")) {        
            return 6;
        } else if (i.equals("Admin (Status 7)")) {        
            return 7;
        } else if (i.equals("inof. Sysadmin")) {        
            return 8;
        } else if (i.equals("Sysadmin (Status 10)")) {        
            return 10;
        } else if (i.equals("Sysadmin (Status 11)")) {        
            return 11;
        } else if (i.equals("Sysadmin (Status 12)")) {        
            return 12;
        } 
        
        return 0;
        
    } */
     

    
    
    public static int getChoiceValueEmailYesNo(String i) {
        if (i.equals("1")) {
            return 2;
        }
        
        return 0;
    }
    
    
     public static int getChoiceValueWStyle(String i) {
        if (i.equals("w2 BETA")) {
            return 1;
        }
       /* if (i.equals("v2 BETA")) {
            return 2;
        }*/
        
        return 0;
    }
    public static int getTokenID(String feld, String use) {
     int id = 0;
     int ende = 0;
        for(String x : use.split(",")) {
            if (x.equals(feld)) {
               ende = id;
               break;
            }
            
            id++;
            
        }
        
        
      return ende;  
    }
    public static void handle(String[] tokens, Client client) {
      StringBuilder errors = new StringBuilder();
      

int i = 0;
for(String v : tokens) {
i++;
}


String[] args = tokens[1].split("~");
boolean on = true;
String useable = ",,,"+args[2];
String nickname  = args[1];
Client target = Server.get().getClient(nickname);
if (target == null) {
    on = false;
    target = new Client(null);
    target.loadStats(nickname);
}


    

if (target == null) {
    client.send(PopupNewStyle.create("Problem","Problem","Der Nickname kann nicht geändert werden.",400,250));
    return;
}
if (useable.contains("age,")) {
    int id = getTokenID("age", useable);
target.setAge((byte)Integer.parseInt(tokens[id]));
}


if (useable.contains("onlinetime,")) {
    int id = getTokenID("onlinetime", useable);
target.setonlineTime(Integer.parseInt(tokens[id]));
}

if (useable.contains("knuddels,")) {
    int id = getTokenID("knuddels", useable);
target.setKnuddels(Integer.parseInt(tokens[id]));
}

if (useable.contains("kisses,")) {
    int id = getTokenID("kisses", useable);
target.setKisses(Integer.parseInt(tokens[id]));
}



/*if (useable.contains("rank,")) {

    int id = getTokenID("rank", useable);
    
    
    byte oldrank = target.getRank();
    byte newrank = (byte)getChoiceValueRank(tokens[id]);
    byte myrank = client.getRank();
    boolean stop = false;
    if (myrank == oldrank && oldrank > newrank) {
        stop = true;
    }
    
    
    if (getChoiceValueRank(tokens[id]) != target.getRank() && (byte)getChoiceValueRank(tokens[id]) <= client.getRank() && !stop) {
          String rank = target.getRankLabel((byte)getChoiceValueRank(tokens[id]));
                String datum = Server.get().timeStampToDate(time);
                
      Channel channel = Server.get().getChannel(client.getChannel().getName());
                client.sendButlerMessage(channel.getName(), String.format("%s wurde auf '%s' gesetzt.", target.getName(), rank));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), rank, String.format("Hallo %s,##Du wurdest von %s auf den Rang %s gesetzt.##Liebe Grüße, %s", target.getName(), client.getName(), rank, Server.get().getButler().getName()), time);
                target.setSyscomments(time, null, null, client.getName(), String.format("Rang auf _%s_ gesetzt", rank));
                 Server.get().newSysLogEntry(client.getName(), String.format("Status von %s auf %s gesetzt", target.getName(), rank));
                     target.setDate(datum);
                    target.setCareer(String.format("%s|%s %s|", target.getCareer(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin")));
                 
 }
    if (stop) {
    
     errors.append("#Du kannst den _'Rang'_ von "+target.getName()+" nicht verringern.");
    } else if ((byte)getChoiceValueRank(tokens[id]) <= client.getRank()) {
    target.setRank((byte)getChoiceValueRank(tokens[id]));
    } else {
    
     errors.append("#Du kannst den _'Rang'_ von "+target.getName()+" nicht höher als deinen eigenen Rang setzen.");
    }             
          
}*/
       


if (useable.contains("mentorpoints,")) {
    int id = getTokenID("mentorpoints", useable);
    
    
    String x = tokens[id];
    if (!Server.get().isInteger(x)) {
        
            errors.append("Ich weiß ja nicht was das genau für ein Fehler ist?");
  
    } else {
    int neu = Integer.parseInt(tokens[id]);
    
    if (target.getRank() > 2 && neu < 400) {
        neu = 400;
    }
    
target.setMentorPoints(neu);
}
}



if (useable.contains("gender,")) {

    int id = getTokenID("gender", useable);
    if ((byte)getChoiceValueGender(tokens[id]) != target.getGender()) {
        
        if ((byte)getChoiceValueGender(tokens[id]) == 1) {
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Geschlecht auf männlich gesetzt"), String.format("Hallo %s,##dein Geschlecht wurde soeben von %s auf männlich gesetzt.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                target.setComment(time, null, null, client.getName(), String.format("Geschlecht auf _männlich_ gesetzt"));
                Server.get().newSysLogEntry(client.getName(), String.format("Geschlecht von %s auf männlich gesetzt", target.getName()));
        
             
        } else if ((byte)getChoiceValueGender(tokens[id]) == 2) {
               Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Geschlecht auf weiblich gesetzt"), String.format("Hallo %s,##dein Geschlecht wurde soeben von %s auf weiblich gesetzt.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                target.setComment(time, null, null, client.getName(), String.format("Geschlecht auf _weiblich_ gesetzt"));
                Server.get().newSysLogEntry(client.getName(), String.format("Geschlecht von %s auf weiblich gesetzt", target.getName()));

        }
 }
    target.setGender((byte)getChoiceValueGender(tokens[id]));
    
}




if (useable.contains("visit,")) {
    
     int id = getTokenID("visit", useable);
    if (getChoiceValueYesNo(tokens[id]) != target.getVisit()) {
        
        if (getChoiceValueYesNo(tokens[id]) == 0) {
             Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Dein Profil", String.format("Hallo %s,##dein Profil ist jetzt _sichtbar_ geschaltet.#Jeder kein dein Profil jetzt einsehen#Viele Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
             
            
        } else {
            // client.sendButlerMessage(channel.getName(), "Profil _unsichtbar geschaltet_.#(Wichtig! - Mitglieder ab dem Status _Ehrenmitglied_ oder höher, können nach wie vor dein Profil einsehen.)");
             Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Dein Profil", String.format("Hallo %s,##dein Profil ist jetzt _unsichtbar_ geschaltet.#Mitglieder ab dem Status _Ehrenmitglied_ oder höher, können dein Profil aber dennoch einsehen#Viele Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                  
             
        }
        
    }    
     
target.setVisit(getChoiceValueYesNo(tokens[id]));
}

if (useable.contains("wstyle,")) {
    
     int id = getTokenID("wstyle", useable);
    if (getChoiceValueWStyle(tokens[id]) != target.getWStyle()) {
        
        if (getChoiceValueWStyle(tokens[id]) == 1) {
           // v3 Whois
            //Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Wichtiger Hinweis!", String.format("Hallo %s,##Du hast soeben die Darstellung der Nutzer-Profile auf die Option: [_v3 BETA_] gesetzt.#Bitte beachte, dass dieser Whois-Style noch in im frühen Stadium der Entwicklungs-Phase ist, und einige Anzeigen und / oder Funktionen des Profils, noch nicht richtig angezeigt werden.##_Hierzu zählen unter anderen_:##°r°   -   °R°_Fotos werden nicht angezeigt_#°r°   -   °R°_kein Online-Status sichtbar_#°r°   -   °R°_keine Administrativen Einsichten oder Funktionen_#°r°   -   °R°_Anzeige von Fotos, Fotoalben, Motto, Aktuelle Gefühlslage, sind nicht korrekt_##°r°Wir würden uns freuen wenn du aktive Bugs oder Störungen in Verbindung mit dem neuem Profilaussehen, dem Cuddly-Team meldest. Außerdem sind auch Anregungen und Ideen sehr genre gesehen.#Mehr Informationen zur neuen Whois, erfährst du in Kürze im neuem Forum von "+Server.get().getSettings("CHAT_NAME")+"!##Hochachtungsvoll,#%s", target.getName(), Server.get().getButler().getName()), time);
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Wichtiger Hinweis!", String.format("Hallo %s,##Du hast soeben das Aussehen der Nutzer-Profile auf die Option: [_w2 BETA_] gesetzt.#Bitte beachte, dass dieser Whois-Style noch in der  Entwicklungs-Phase ist und derzeit noch nicht alles korrekt funktioniert.#Die meisten Funktionen aus dem Classic-Profil, sollten auch unter diesem Profil-Design funktionieren. Fehler's können jedoch trotzdem nicht ganz ausgeschlossen werden.##Wir würden uns freuen wenn du aktive Bugs oder Störungen in Verbindung mit dem neuem Profilaussehen, dem "+Server.get().getSettings("CHAT_NAME")+"-Team meldest. Außerdem sind auch Anregungen und Ideen sehr genre gesehen.#Mehr Informationen zur neuen Whois, erfährst du in Kürze im neuem Forum von "+Server.get().getSettings("CHAT_NAME")+"!##Hochachtungsvoll,#%s", target.getName(), Server.get().getButler().getName()), time);
            
        }
        if (getChoiceValueWStyle(tokens[id]) == 2) {
           // v2 Whois
               
             Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Wichtiger Hinweis!", String.format("Hallo %s,##Du hast soeben das Aussehen der Nutzer-Profile auf die Option: [_v2 BETA_] gesetzt.#Bitte beachte, dass dieser Whois-Style noch in im frühen Stadium der Entwicklungs-Phase ist und derzeit nicht aktiv weiterentwickelt wird.#Die meisten Funktionen aus dem Classic-Profil, sollten auch unter diesem Profil-Design funktionieren. Fehler's können jedoch trotzdem nicht ganz ausgeschlossen werden.##Wir würden uns freuen wenn du aktive Bugs oder Störungen in Verbindung mit dem neuem Profilaussehen, dem "+Server.get().getSettings("CHAT_NAME")+"-Team meldest. Außerdem sind auch Anregungen und Ideen sehr genre gesehen.#Mehr Informationen zur neuen Whois, erfährst du in Kürze im neuem Forum von "+Server.get().getSettings("CHAT_NAME")+"!##Hochachtungsvoll,#%s", target.getName(), Server.get().getButler().getName()), time);
           
        } else {
           // Classic Whois
             
        }
        
    }    
     
target.setWStyle(getChoiceValueWStyle(tokens[id]));


}


if (useable.contains("emailverify,")) {
    
     int id = getTokenID("emailverify", useable);
    if ((byte)getChoiceValueEmailYesNo(tokens[id]) != target.getEmailVerify()) {
        
        if ((byte)getChoiceValueEmailYesNo(tokens[id]) == 2) {
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine E-Mail-Adresse wurde soeben von %s verifiziert.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "E-Mail verifiziert");
                    Server.get().newSysLogEntry(client.getName(), String.format("E-Mail von %s auf verifiziert gesetzt", target.getName(), id));

        } else {
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine E-Mail-Verifikation wurde soeben von %s gelöscht.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "E-Mail-Verifikation gelöscht");
                    Server.get().newSysLogEntry(client.getName(), String.format("E-Mail-Verifikation von %s gelöscht", target.getName(), id));

        }
        
    }    
     
target.setEmailVerify((byte)getChoiceValueEmailYesNo(tokens[id]));
}
if (useable.contains("alterverify,")) {
    
     int id = getTokenID("alterverify", useable);
    if ((byte)getChoiceValueYesNo(tokens[id]) != target.getVerify()) {
        
        if ((byte)getChoiceValueYesNo(tokens[id]) == 1) {
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##dein Alter wurde soeben von %s verifiziert.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "Alter verifiziert");
                    Server.get().newSysLogEntry(client.getName(), String.format("Altersverifikation von %s auf verifiziert gesetzt", target.getName(), id));

        } else {
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine Altersverifikation wurde soeben von %s gelöscht.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "Altersverifikation gelöscht");
                    Server.get().newSysLogEntry(client.getName(), String.format("Altersverifikation von %s gelöscht", target.getName(), id));

        }
        
    }    
     
target.setVerify((byte)getChoiceValueYesNo(tokens[id]));
}

if (errors.toString().isEmpty()) {
PopupNewStyle popup = new PopupNewStyle("Gespeichert", "Gespeichert", "##Alle Änderungen wurden _gespeichert_.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
} else {
PopupNewStyle popup = new PopupNewStyle("Hinweis", "Hinweis", "##Bei der Änderung der Einstellungen sind folgende#_Probleme_ aufgetreten:##"+errors.toString()+"###Alle anderen Änderungen wurden _gespeichert_.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());    
}

target.saveStats();

    }}
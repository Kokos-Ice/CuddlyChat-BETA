/*    */ package funktionen;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import starlight.Channel;
/*    */ import starlight.Client;
/*    */ import starlight.ReceiveOpcode;
/*    */ import starlight.Server;
         import static starlight.CommandParser.unknownUser;
         import static starlight.CommandParser.userIsOffline;
/*    */ import tools.KCodeParser;
/*    */ import tools.database.ConnectionPool;
/*    */ import tools.database.PoolConnection;
/*    */ import tools.popup.Button;
/*    */ import tools.popup.Panel;
/*    */ import tools.popup.Popup;
import tools.popup.PopupNewStyle;

/*    */ 
/*    */ public class sperre
/*    */ {
    
/*    */   static String timestamp2(Long timestamp)
/*    */   {
/* 18 */     Date date = new Date(timestamp.longValue() * 1000L); SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy"); return uhrzeits.format(date);
/*    */   }
/*    */   public static void aktion(String arg, Client client, Channel channel) {
    if (!client.hasPermission("cmd.lock")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }
/* 21 */     String nickname = KCodeParser.escape(arg);
/*    */ 
/* 23 */     boolean online = true;
/*    */     
/*    */     Client target;
/* 23 */     if ((nickname.isEmpty()) || (nickname.equalsIgnoreCase(client.getName()))) {
/* 24 */       target = client; } else { target = Server.get().getClient(nickname);
/* 25 */       if (target == null) {
/* 26 */         online = false;
/* 27 */         target = new Client(null);
/* 28 */         target.loadStats(nickname);
/* 29 */         if (target.getName() == null) {
/* 30 */           client.sendButlerMessage(channel.getName(), starlight.CommandParser.unknownUser(arg));
/* 31 */           return;
/*    */         }
/*    */       }
/*    */     }
/* 35 */     nickname = target.getName();
/*    */ 
/* 38 */     String title = String.format("%s Sperren", new Object[] { nickname });
/* 39 */     StringBuilder career = new StringBuilder();
            career.append(String.format("#°R°_Provonick_°K°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Provonick - neu angelegter Nick zur Störung des Chatklimas:Hallo,#da durch dich das Chatklima gestört wurde, obwohl du erst vor kurzem bei der Registrierung die allgemeinen Geschäftsbedingungen, demnach auch den Chatknigge, akzeptiert hast, wird dieser Nickname permanent vom Chatbetrieb ausgeschlossen.##Solltest du weiterhin die Knuddels.de Dienste nutzen wollen, halte dich bitte an den Knigge (www.knuddels.de/agb, Punkt 3). Akzeptierst du diese Grundregeln nicht, wirst du innerhalb unserer Gemeinschaft keine Akzeptanz finden. Menschen, die Streit und Ärger suchen, sind bei Knuddels fehl am Platz.:!<°", new Object[] { nickname }));
            career.append(String.format("##°R°_Werbung_°K°", new Object[] { nickname }));
          //  career.append(String.format("°>gg.png<° °>3 Tage Sperren wegen Werbung|/lock %s:Sperre auf Grund wiederholte Werbung:Hallo,#du erhältst hiermit eine Chatsperre, da du wiederholt durch Werbung das Chatklima gestört hast.##Werbung jeder Art (z.B. für die eigene Homepage, Foto bzw. Wünsche nach Fotokommentaren oder für Mychannel, ...) stört alle Anwesenden und sollte unterlassen werden.##Quelle - bezugnehmendes Dokument#(1) http://knuffels.berlin/agb:3<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>7 Tage Sperren wegen Werbung|/lock %s:Sperre auf Grund wiederholte Werbung:Hallo,#du erhältst hiermit eine Chatsperre, da du wiederholt durch Werbung das Chatklima gestört hast.##Werbung jeder Art (z.B. für die eigene Homepage, Foto bzw. Wünsche nach Fotokommentaren oder für Mychannel, ...) stört alle Anwesenden und sollte unterlassen werden.##Quelle - bezugnehmendes Dokument#(1) http://knuffels.berlin/agb:7<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>14 Tage Sperren wegen Werbung|/lock %s:Sperre auf Grund wiederholte Werbung:Hallo,#du erhältst hiermit eine Chatsperre, da du wiederholt durch Werbung das Chatklima gestört hast.##Werbung jeder Art (z.B. für die eigene Homepage, Foto bzw. Wünsche nach Fotokommentaren oder für Mychannel, ...) stört alle Anwesenden und sollte unterlassen werden.##Quelle - bezugnehmendes Dokument#(1) http://knuffels.berlin/agb:14<°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Sperre auf Grund wiederholte Werbung:Hallo,#du erhältst hiermit eine Chatsperre, da du wiederholt durch Werbung das Chatklima gestört hast.##Werbung jeder Art (z.B. für die eigene Homepage, Foto bzw. Wünsche nach Fotokommentaren oder für Mychannel, ...) stört alle Anwesenden und sollte unterlassen werden.##Quelle - bezugnehmendes Dokument#(1) http://knuffels.berlin/agb:!<°", new Object[] { nickname }));
            career.append(String.format("##°R°_Beleidigungen_°K°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>3 Tage Sperren wegen Beleidigung|/lock %s:Sperre aufgrund von Provozieren Beleidigen:Hallo,#du erhältst hiermit eine Chatsperre, da du durch Beleidigungen, Drohungen und/oder Provokationen im Chat wiederholt negativ aufgefallen bist.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:3<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>7 Tage Sperren wegen Beleidigung|/lock %s:Sperre aufgrund von Provozieren Beleidigen:Hallo,#du erhältst hiermit eine Chatsperre, da du durch Beleidigungen, Drohungen und/oder Provokationen im Chat wiederholt negativ aufgefallen bist.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:7<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>14 Tage Sperren wegen Beleidigung|/lock %s:Sperre aufgrund von Provozieren Beleidigen:Hallo,#du erhältst hiermit eine Chatsperre, da du durch Beleidigungen, Drohungen und/oder Provokationen im Chat wiederholt negativ aufgefallen bist.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:14<°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Sperre aufgrund von Provozieren Beleidigen:Hallo,#du erhältst hiermit eine Chatsperre, da du durch Beleidigungen, Drohungen und/oder Provokationen im Chat wiederholt negativ aufgefallen bist.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder Ã„rgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:!<°", new Object[] { nickname }));
            career.append(String.format("##°R°_Bedrohung_°K°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>3 Tage Sperren wegen Bedrohung|/lock %s:Sperre wegen Drohen eines Mitgliedes:Hallo,#du erhältst hiermit eine Chatsperre, da du im Chat Mitglieder bedrohst.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:3<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>7 Tage Sperren wegen Bedrohung|/lock %s:Sperre wegen Drohen eines Mitgliedes:Hallo,#du erhältst hiermit eine Chatsperre, da du im Chat Mitglieder bedrohst.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:7<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>14 Tage Sperren wegen Bedrohung|/lock %s:Sperre wegen Drohen eines Mitgliedes:Hallo,#du erhältst hiermit eine Chatsperre, da du im Chat Mitglieder bedrohst.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:14<°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Sperre wegen Drohen eines Mitgliedes:Hallo,#du erhältst hiermit eine Chatsperre, da du im Chat Mitglieder bedrohst.##Vergiss niemals, dass am anderen Ende, hinter jedem Nicknamen, ein Mensch sitzt. Wie im echten Leben soll man anderen Menschen mit Respekt, Toleranz und Hilfsbereitschaft begegnen und sie auf keinen Fall beleidigen, kränken oder ärgern.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:!<°", new Object[] { nickname }));
            career.append(String.format("##°R°_Botnutzung_°K°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>3 Tage Sperren wegen Botnutzung|/lock %s:Bekam _erste_ sperre wegen Botnutzung:Hallo,#du erhältst hiermit eine Chatsperre, aufgrund Botnutzung.##Das Verwenden von Programmen, die für den Nutzer im Chat Texte schreiben oder auf andere Weise das automatische Trennen nach langzeitigem Untätigbleiben im Chat verhindern, (sog. Bots) ist laut den Allgemeinen Geschäftsbedingungen von KnuFFelsChat untersagt.#Ebenso ist die Benutzung von Programmen, mit denen Einfluss auf die im Chat zur Verfügung gestellten Spiele oder Nutzerinformationen (Info) genommen werden kann (sog. Cheat-Programme), untersagt.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:3<°", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>7 Tage Sperren wegen Botnutzung|/lock %s:Sperre wegen wiederholte Botnutzung:Hallo,#du erhältst hiermit eine Chatsperre, aufgrund Botnutzung.##Das Verwenden von Programmen, die für den Nutzer im Chat Texte schreiben oder auf andere Weise das automatische Trennen nach langzeitigem Untätigbleiben im Chat verhindern, (sog. Bots) ist laut den Allgemeinen Geschäftsbedingungen von KnuFFelsChat untersagt.#Ebenso ist die Benutzung von Programmen, mit denen Einfluss auf die im Chat zur Verfügung gestellten Spiele oder Nutzerinformationen (Info) genommen werden kann (sog. Cheat-Programme), untersagt.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:7<°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Sperre wegen wiederhilte Botnutzung:Hallo,#du erhältst hiermit eine Chatsperre, aufgrund Botnutzung.##Das Verwenden von Programmen, die für den Nutzer im Chat Texte schreiben oder auf andere Weise das automatische Trennen nach langzeitigem Untätigbleiben im Chat verhindern, (sog. Bots) ist laut den Allgemeinen Geschäftsbedingungen von KnuFFelsChat untersagt.#Ebenso ist die Benutzung von Programmen, mit denen Einfluss auf die im Chat zur Verfügung gestellten Spiele oder Nutzerinformationen (Info) genommen werden kann (sog. Cheat-Programme), untersagt.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:!<°", new Object[] { nickname }));
            career.append(String.format("##°R°_Sicherheitslock_°K°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (vorrübergehend)|/lock %s:Sicherheitssperre Verdacht auf Nickweitergabe oder Diebstahl:Hallo,#der Nick wird vorrübergehend gesperrt, da der begründete Verdacht besteht, dass das Passwort des Nicks durch den Besitzer wohlmöglich an einen Bekannten/Freund weitergegeben wurde, was laut den allgemeinen Geschäftsbedingungen von KnuFFelsChat nicht gestattet ist.##Zur Fallklärung soll sich bitte der rechtmäßige Nickbesitzer mit einem Zweitnick im Chat per /m an mich wenden!:!<°", new Object[] { nickname }));
          //  career.append(String.format("#°R°_Wahlbetrug_°K°#", new Object[] { nickname }));
          //  career.append(String.format("#°>gg.png<° °>40 Tage Wahlbetrug|/lock %s:Sperre wegen Verstoß gegen die Wahlregeln:Dieser Nickname wird, aufgrund eines wiederholten und/oder massiven Verstoßes gegen die Wahl-/Nominierungsregeln (/h wahlregeln) von der Nutzung der Chatdienste ausgeschlossen. Administrative Rechte, die durch die Verwendung von unerlaubten Wahlmethoden erlangt wurden, können desweiteren jederzeit entzogen werden##_Wahlsperre_ Zusatzlich ist die Teilnahme an Wahlen im Chat mit allen Nicks für 180 Tage verboten:40<°", new Object[] { nickname }));
            career.append(String.format("##°R°_Anzüglicher oder Verbotener Nickname_°K°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Nick auf Grund von obszönen Nicknamen:anzüglicher / obszöner / doppeldeutiger Nickname Der gewählte Nickname weist einen anzüglichen, obszönen und/oder doppeldeutigen Charakter auf. Er ist deshalb nicht mit den Nutzungsbestimmungen zu vereinbaren und wird permanent ausgeschlossen. Wenn die KnuFFelsChat-Dienste weiter genutzt werden möchten, dann unter einem neutralen Nicknamen, der im Einklang mit unseren Nutzungsbedingungen steht.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:!<°", new Object[] { nickname }));              
            career.append(String.format("##°R°_Chatverbot_°K°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Dieser User hat Chatverbot:Hallo##Du hast in Dieser Community Chatverbot und wir Bitten dich das du keine weiteren Nicknamen mehr anmeldest Bitte unterlasse das in zukunft:!<°", new Object[] { nickname }));      
            career.append(String.format("##°R°_Bugnutzung_°K°", new Object[] { nickname }));
            career.append(String.format("#°>gg.png<° °>Permanent (dauerhaft)|/lock %s:Sperre wegen Ausnutzung von Bugs:Hallo,#du erhältst hiermit eine Chatsperre, aufgrund Bugausnutzung.##Das Verwenden von missbrauchen von Bugs, ist laut den Allgemeinen Geschäftsbedingungen von KnuFFelsChat untersagt.#Ebenso ist die Benutzung von Programmen, mit denen Einfluss auf die im Chat zur Verfügung gestellten Spiele oder Nutzerinformationen (Info) genommen werden kann (sog. Cheat-Programme), untersagt.##Quelle - bezugnehmendes Dokument#(1) www.de-cafee.de/agb.php:!<°", new Object[] { nickname }));
            
            
            
            
/* 48 */     PopupNewStyle popup = new PopupNewStyle(title, title, career.toString(), 400, 275);
/* 78 */       Panel panel = new Panel();
                    Button buttonMessage = new Button("Ok");
                    buttonMessage.setStyled(true);

              panel.addComponent(buttonMessage);
/* 51 */     popup.addPanel(panel);
/* 52 */     popup.setOpcode(ReceiveOpcode.WHOIS.getValue(), nickname);
/* 53 */     client.send(popup.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator-\Desktop\bananachat (2).jar
 * Qualified Name:     funktionen.showcareer
 * JD-Core Version:    0.6.0
 */
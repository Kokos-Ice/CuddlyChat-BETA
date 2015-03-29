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

import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.Server;
import tools.popup.*;
import tools.KCodeParser;
import tools.PacketCreator;

public class MessageHandler {
public static void handle(String[] tokens, Client client) {
String what = tokens[1];
String lol = tokens[2];
long systemtime = System.currentTimeMillis()/1000;


if (lol.equals("Vorschau")) {
String name = tokens[3].trim();
String betreff = tokens[4].trim();
String icon = tokens[5].trim();
String text = String.format("%s#",tokens[8].trim());
String bicon = "";
text = text.replace("[q]","§");
text = text.replace("[/q]","");
if ("-1:NoIcon".equals(icon)) {
bicon = String.format("%s",betreff);    
} else
if ("-1:Exclamation".equals(icon)) {
bicon = String.format("°>posts/msg_ico_xmark...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else 
if ("-1:Question".equals(icon)) {
bicon = String.format("°>posts/msg_ico_qmark...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else
if ("-1:Warn".equals(icon)) {
bicon = String.format("°>posts/msg_ico_warning...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else 
if ("-1:Clover".equals(icon)) {
bicon = String.format("°>posts/msg_ico_clover...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else
if ("-1:Bulb".equals(icon)) {
bicon = String.format("°>posts/msg_ico_bulb...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else 
if ("-1:ThumpDown".equals(icon)) {
bicon = String.format("°>posts/msg_ico_thumbdown...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else     
if ("-1:ThumpUp".equals(icon)) {
bicon = String.format("°>posts/msg_ico_thumbup...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else     
if ("-1:CalendarSummer".equals(icon)) {
bicon = String.format("°>posts/msg_ico_sun...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
}   
Client target = Server.get().getClient(name);
Client who = Server.get().getClient(name);
if (target == null) {
target = new Client(null);
target.loadStats(name);

if(target.getName() == null) {
Popup popup = new Popup("Problem", "Problem", String.format("#Den Nicknamen %s gibt es nicht.",name, Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}}
if(text.isEmpty()) {
Popup popup = new Popup("Problem", "Problem", String.format("#Bitte gebe einen Text bei Nachricht an.", Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(target == Server.get().getButler()) {
Popup popup = new Popup("Problem", "Problem", String.format("#Deine Nachricht konnte nicht abgesendet werden:##An %s können keine Nachrichten geschickt werden.", Server.get().getButler(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(target.checkIgnored(client.getName())) {
Popup popup = new Popup("Problem", "Problem", String.format("#Du wirst von %s ignoriert.",target.getName(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(client.checkIgnored(target.getName())) {
for(Channel c : client.getChannels()) {
client.sendButlerMessage(c.getName(), String.format("_%s steht auf deiner Ignore-Liste_ und wird deshalb nicht antworten können. Mit °BB°_°>/ig !%s|\"<r°_ kannst du %s wieder von der Ignore-Liste löschen.", target.getName(), target.getName(), target.getName()));
}}
StringBuilder messages = new StringBuilder();
boolean online = true;
if(who == null) {
online = false;
who = new Client(null);
who.loadStats(name);
}
String whoChar = who.getName().replace("<", "\\<");
messages.append("k\0").append("Vorschau deiner Nachricht an ").append(whoChar).append("õf\0\0\0hÿÿÿocloseButtonõãEl  õcgPhÿÿÿãWl  õcgPhÿÿÿãSl õcgPhÿÿÿãCpBNpBNl           õgIf\0\0\0hÿÿÿãCpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãCpBNpBNpBNc");
messages.append("°20°_").append("Vorschau deiner Nachricht an ").append(whoChar).append("õs\0\0(f\0\0\0hÞÞÿoãããCc°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#");
if(who.getPhoto().isEmpty()) {
messages.append("°>nopic_79x79_").append(who.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<°");
} else {
messages.append("°>photos/l/").append(who.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(whoChar).append("<°");
}
String sig = "";
String butlerm = "";                          
if (!client.getSignatur().isEmpty()) {
sig = String.format("°>{signaturestart}<°°° §#°05° °>layout/hr_over-sg.png<°#°05° #°+701012°°>{globalopacity}50<°%s°>{globalopacity}100<°#", client.getSignatur());    
}
if (who == Server.get().getButler()) {
butlerm = "°>posts/sig_systemmsg...h_28.my_4.mx_-5.png<°#";    
}

messages.append("#°+0075+8002°°>").append(!online?"off":who==Server.get().getButler() || who.isAway()?"ono":"ong").append("...my_1.png<° °14°_°BB>_h").append(whoChar).append("|/serverpp \"|/w \"<°°°").append("#").append(bicon.isEmpty()?"°r°_":bicon+"§#").append(!bicon.isEmpty()?"°r°_":bicon+"§##").append("#§°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°").append(text).append(sig).append("#°+5000°°r°°>center<°").append(butlerm).append("°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010°#°1° °>left<°#°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#");

messages.append("#õsD?tsendbackõf\0\0\0hÞÞÿzMããEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããããSpBWl                                                           õgPf\0\0\0hÿÿÿãCbSchließenõ~closeButtonõcedbgPf\0\0\0hÿÿÿãEl                                                           õgPf\0\0\0hÿÿÿãããã");     
client.send(messages.toString());
}  


if (what.equals("mow")) {
byte messageSound = Byte.parseByte(tokens[3].trim());
String signature = KCodeParser.parse(client, tokens[4].trim(), 5, 10, 20);



client.setMessageSound(messageSound);

if(signature.length() > 75) {
Popup popup = new Popup("Problem", "Problem", String.format("#Deine Signatur darf nicht länger als 75 Zeichen lang sein.", Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        



} else {
client.setSignatur(signature);
for(Channel c : client.getChannels()) {
client.sendButlerMessage(c.getName(), "Die Einstellungen für deinen _°BB>_hBriefkasten|/m sig<r°_ wurden gespeichert.");
}
}

}  
if (lol.equals("Senden")) {
String name = "";

String time = "";
String text = "";
name = tokens[3].trim();
String betreff = tokens[4].trim();
String icon = tokens[5].trim();
time = tokens[6].trim();
if (time.equals("0")) {
text = tokens[8].trim();    
} else {
text = tokens[9].trim();    
}

String bicon = "";
if ("-1:NoIcon".equals(icon)) {
bicon = String.format("%s",betreff);    
} else
if ("-1:Exclamation".equals(icon)) {
bicon = String.format("°>posts/msg_ico_xmark...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else 
if ("-1:Question".equals(icon)) {
bicon = String.format("°>posts/msg_ico_qmark...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else
if ("-1:Warn".equals(icon)) {
bicon = String.format("°>posts/msg_ico_warning...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else 
if ("-1:Clover".equals(icon)) {
bicon = String.format("°>posts/msg_ico_clover...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else
if ("-1:Bulb".equals(icon)) {
bicon = String.format("°>posts/msg_ico_bulb...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else 
if ("-1:ThumpDown".equals(icon)) {
bicon = String.format("°>posts/msg_ico_thumbdown...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else     
if ("-1:ThumpUp".equals(icon)) {
bicon = String.format("°>posts/msg_ico_thumbup...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} else     
if ("-1:CalendarSummer".equals(icon)) {
bicon = String.format("°>posts/msg_ico_sun...w_20.h_8.my_3.mx_-10.png<° %s",betreff);    
} 

Client target = Server.get().getClient(name);
if (target == null) {
target = new Client(null);
target.loadStats(name);

if(target.getName() == null) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#Den Nicknamen %s gibt es nicht.",name, Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}}        
if(betreff.length() > 50) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#Deine Nachricht konnte nicht gesendet werden:##Der _Betreff deiner Nachricht ist zu lang_, dieser darf höchstens 50 Zeichen lang sein.", Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(text.isEmpty()) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#Bitte gebe einen Text bei Nachricht an.", Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(target == Server.get().getButler()) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#Deine Nachricht konnte nicht abgesendet werden:##An %s können keine Nachrichten geschickt werden.", Server.get().getButler().getName(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(target.checkIgnored(client.getName())) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#Du wirst von %s ignoriert.",target.getName(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(client.checkIgnored(target.getName())) {
Popup popup = new Popup("Problem", "Problem", String.format("#%s steht auf deiner Ignorierliste.",target.getName(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;	
}
if(target.getSilence() == 1 && client.getRank() < 5) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#%s können derzeit keine Nachrichten hinterlassen werden.",target.getName(), Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
if(client.getRank() < 8 && text.length() > 2000) {
client.send(PacketCreator.createMessageWindow(name, betreff, text));
Popup popup = new Popup("Problem", "Problem", String.format("#Deine Nachricht konnte nicht gesendet weden:##Deine _Nachricht ist zu lang_, sie darf höchstens 2000 Zeichen lang sein.", Server.get().getURL()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setMessageproblem(1);
                        client.send(popup.toString());
                        return;
}
client.sendButlerMessage(client.getChannel().getName(), CommandParser.messageSent(target.getName()));


Server.get().newMessage(client.getName(), target.getName(), bicon, text, systemtime); 

}
}}
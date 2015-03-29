
package handler;

import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;


public class GameHandler {



public static void handle(String[] tokens, Client client) {
Long time = System.currentTimeMillis()/1000;    
Channel channel = client.getChannel();
String game = tokens[3].trim();
String image = "";
Random r = new Random();
int zufall = r.nextInt(7) + 1;

if (zufall == 1) {
image = "blue";    
} else
if (zufall == 2) {
image = "brightgreen";
} else
if (zufall == 3) {
image = "darkblue";
} else
if (zufall == 4) {
image = "green";    
} else
if (zufall == 5) {
image = "orange";   
} else
if (zufall == 6) {
image = "pink";    
} else 
if (zufall == 7) {
image = "red";    
}    


if(game.equals("Darten")) {

String privat = tokens[4].trim();    
String nachwurf = tokens[6].trim();    
String knuddelseinsatz = tokens[7].trim();
//String rosen = tokens[8].trim();
String gegenspieler = tokens[9].trim();
String startzahl = tokens[10].trim();
String arg = tokens[9].trim();
String nickname = KCodeParser.escape(arg);
String nachaktiv = "";
String einsatzart = "";
String privatison = "";

int privi = Integer.parseInt(privat); 
int knuddel = Integer.parseInt(knuddelseinsatz); 
int startnumber = Integer.parseInt(startzahl);
int nach = Integer.parseInt(nachwurf);

if (knuddel > 0) {
einsatzart = " Knuddels";

}
if (nach == 1) {
nachaktiv = "Nachwurf Aktiv, ";   
}
if (privi == 1) {
privatison = "Privates würfeln, ";    
}



if (nickname.equalsIgnoreCase(client.getName())) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Du kannst nicht gegen dich selber Spielen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
if (knuddel > client.getKnuddels()) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Soviele Knuddels hast du nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
if (knuddel == 0) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Du musst Rosen oder Knuddel setzen, um das Spiel zu starten.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return; 
}
if (knuddelseinsatz.isEmpty()) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Du musst Rosen oder Knuddel setzen, um das Spiel zu starten.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
Client target = Server.get().getClient(nickname);
boolean online = true;

if (target == null) {
online = false;
target = new Client(null);
target.loadStats(nickname);

if(target.getName() == null && !gegenspieler.isEmpty()) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Dieser User _existiert_ nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

if((!online) && !gegenspieler.isEmpty()) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Dieser User ist derzeit _ nicht_ online.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}}

if (target == Server.get().getButler()) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Du kannst nicht gegen den Butler spielen!", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

if (startnumber < 100 || startnumber > 100000) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Die Startzahl darf nicht _unter 100_ oder _über 100.000_ sein.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;   
}
if (client.getDartenOpen() == 1) {
client.send(PacketCreator.game(client.getName()));
Popup popup = new Popup("Game - Problem", "Game Problem", "##Du bietest oder bist momentan in ein Spiel, und kannst deshalb kein weiteres Spiel spielen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

if (gegenspieler.isEmpty()) {         
channel.broadcastPicAction(">",String.format("°>_h%s|/serverpp \"|/w \"<° bietet eine Runde °RR22°_Darten_°BB° §an. _°>{button}Mitspielen||call|/game join:%s<°_ °BB°(_°>Startzahl: %s, %s %sEinsatz: %s %s|/game details:%s<°_)",client.getName(),client.getName(),startzahl,nachaktiv,privatison,knuddelseinsatz,einsatzart,client.getName()),"../mychannel/cubes_prefix_"+image+".png"); 
client.deseaseKnuddels(knuddel);
} else {
target.sendButlerMessage(channel.getName(),String.format("°>_h%s|/serverpp \"|/w \"<° bietet dir eine Runde °RR22°_Darten_°BB° §an. _°>{button}Mitspielen||call|/game join:%s<°_ °BB°(_°>Startzahl: %s,%s %sEinsatz: %s %s|/game details:%s<°_)",client.getName(),client.getName(),startzahl,nachaktiv,privatison,knuddelseinsatz,einsatzart,client.getName()));
client.sendButlerMessage(channel.getName(), String.format("Du hast _%s_ soebend ein Angebot gemacht.",target.getName()));      
client.deseaseKnuddels(knuddel);
}
Server.actions.put(time, new String[] {"game", client.getName(), client.getName(), channel.getName(), "120"});         
client.setDartenWurfLast(startnumber);

client.setDartenLastPrivat(privi);
client.setDartenLastWurf(startnumber);
client.setDartenLastArt(1);
client.setDartenLastEinsatz(knuddel);
client.setDartenLastEinsatzArt(1);
if (!gegenspieler.isEmpty()) {
client.setDartenLastGegner(target.getName());
}
client.setDartenOpen(1);
client.setDartenPrivat(privi);
client.setDartenWurf(startnumber);
client.setDartenArt(1);
client.setDartenEinsatz(knuddel);
client.setDartenEinsatzArt(1);

}}}

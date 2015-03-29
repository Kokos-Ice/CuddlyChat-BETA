package handler;

import tools.database.*;
import java.net.Socket;
import java.sql.*;
import tools.*;
import starlight.*;
import java.util.*;
import tools.popup.*;


public class ChannelEditHandler {
    public static void handle(String[] tokens, Client client) {
      
// start hilfe
// zeigt dir die values die gesendet werden
int i = 0;
for(String v : tokens) {
System.out.println("tokens["+i+"] hat den Value von "+v);
i++;
}
// hilfe ende

// daraus ergibt sich
String channelname  = tokens[3];
Channel channel = Server.get().getChannel(channelname);

if (channel == null) {
    client.send(PopupNewStyle.create("Problem","Problem","Der Channelname kann nicht geändert werden.",400,250));
    return;
}

String channelthema = tokens[4];
// String channelstyle = tokens[5];
String channelgröße = tokens[5];
String channelrank = tokens[6];
String channelvisible = tokens[7];
String channeljuschu = tokens[8];
String channelgame = tokens[9].trim();




channel.setTopic(channelthema);
// channel.setStyle(channelstyle);
channel.setSize(Integer.parseInt(channelgröße));
channel.setMinRank(Integer.parseInt(channelrank));
channel.setVisible(Integer.parseInt(channelvisible));
channel.setJuSchu(Integer.parseInt(channeljuschu));
channel.setGame(channelgame);
Popup popup = new Popup("Gespeichert", "Gespeichert", "#Alle Änderungen wurden gespeichert.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                       


}}
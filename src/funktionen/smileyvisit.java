package funktionen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintStream;
import java.text.*;
import java.util.*;
import starlight.*;
import java.util.regex.*;
import tools.*;
import tools.popup.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;




public class smileyvisit {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
            if(!client.hasPermission("cmd.smileyvisit")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
     
            String text = "#";
        
        if (arg.equals("on")) {
            if (client.getSmileyvisit() == 1) {
                  client.sendButlerMessage(channel.getName(), "Smileyvisit ist bereits aktiviert.");
            } else {
                       client.sendButlerMessage(channel.getName(), "Smileyvisit: _°BB°ON°r°_");
           client.setSmileyvisit(1);
            }
        } else if (arg.equals("off")) {
             if (client.getSmileyvisit() == 0) {
                  client.sendButlerMessage(channel.getName(), "Smileyvisit ist bereits deaktiviert.");
            } else {
                       client.sendButlerMessage(channel.getName(), "Smileyvisit: _°BB°OFF°r°_");
           client.setSmileyvisit(0);
            }
            
        } else {
        
         String nickname = KCodeParser.escape(arg);
            Client target;
            boolean online = true;

            if (nickname.isEmpty() || nickname.equalsIgnoreCase(client.getName())) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nickname);

                    if (target.getName() == null) {
                      client.sendButlerMessage(channel.getName(), "Wer soll den "+arg+" sein?");
                      return;
                    }
                }
            }
            
            
            if (target.getSmileyvisit() == 0) {
               client.sendButlerMessage(channel.getName(),target.getName()+" hat die Einsicht der Smileys deaktiviert.");
            return;
            }
            
        if (target.getSmileys().isEmpty()) {
            client.sendButlerMessage(channel.getName(),target.getName()+" hat derzeit keine Smileys.");
            return;
        }
        String[] sms = Server.get().getSortSmileyvisitbyKategorie(target.getSmileys()).split("%%");
    for(String id : sms) {
        if (!id.isEmpty()) {
            Usersmiley usm = Server.get().getUsersmiley(id);
            if (usm.getVerliehen().isEmpty()) {
            Smiley sm = Server.get().getSmiley(String.valueOf(usm.getSMID()));
            if (!sm.getSpez()) {
           text += sm.getKCode()+" ";
        }}}}
        
       Popup popup = new Popup("Smileys von "+target.getName(), "Smileys von "+target.getName(), text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setModern(1);
                 client.send(popup.toString());
                 return; 
     
        
    }}
    
    
}

package funktionen;

import static funktionen.f.time;
import handler.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;




public class channelrules {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
       
        	if(!client.hasPermission("cmd.channelrules") && !channel.checkHz(client.getName())) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}

            arg = KCodeParser.escape(arg);
            Channel channelTo = arg.isEmpty()?channel:Server.get().getChannel(arg);
                
            if (channelTo == null) {
                client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", arg));
                return;
            }
            
            Popup popup = new Popup(String.format("Channelinfo %s bearbeiten", channelTo.getName()), String.format("Channelinfo %s bearbeiten", channelTo.getName()), "", 0, 0);
            Panel panel7 = new Panel();
            TextArea ae = new TextArea(15, 50);
            ae.setText(channel.getInfo());
            panel7.addComponent(ae);
            popup.addPanel(panel7);
            Panel panel2 = new Panel();
            Button button = new Button("Speichern");
            button.setStyled(true);
            button.enableAction();
            button.disableClose();
            panel2.addComponent(button);
            Button button3 = new Button("Schließen");
            button3.setStyled(true);
            panel2.addComponent(button3);
            popup.addPanel(panel2);    
            popup.setOpcode(ReceiveOpcode.CHANNELRULES.getValue(), channelTo.getName());
            client.send(popup.toString());
            
        
    }
    }
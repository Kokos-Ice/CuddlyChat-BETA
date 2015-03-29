package funktionen;

import game.Game;
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




public class serverpp {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
      
        
         if(!client.hasPermission("cmd.serverpp")) {
     		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
     		return;
     	}
            
    	String nickname = KCodeParser.escape(arg);

        if (nickname.isEmpty()) {
            client.send(PacketCreator.createMessageWindow("", "", ""));
            return;
        }

        Client target = Server.get().getClient(nickname);

        if (target == null) {
          	target = new Client(null);
           	target.loadStats(nickname);
            	
           	if(target.getName() == null) {
                   client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                   return;
           	}
            	
           	client.send(PacketCreator.createMessageWindow(arg, "", ""));
            return;
        }
            
        client.send(PacketCreator.privateChat(channel.getName(), target.getName()));
        
       
    }}
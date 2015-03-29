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




public class save {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
      
         if(!client.hasPermission("cmd.save")) {
     		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
     		return;
     	}
    	 
    	 StringBuilder nicks = new StringBuilder();
    	 int number = 1;
    	 
         for (Object target : Server.get().getClients().toArray()) {
        	 Client ob = ((Client) target);
             ob.saveStats();
             
             if(number != 1) {
            	 nicks.append(", ");
             }
             
             nicks.append("°>_h").append(ob.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
             number++;
         }
         
         client.sendButlerMessage(channel.getName(), String.format("Die Profileigenschaften von %s wurden °RR°_gespeichert°r°_.", nicks.toString()));
   
        
        
    }}
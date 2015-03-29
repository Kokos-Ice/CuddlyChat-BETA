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




public class shutdown {
    
   
    
    public static void functionMake(Client client,Channel channel, String arg) {
   	if(!client.hasPermission("cmd.shutdown")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            for (Object target : Server.get().getClients().toArray()) {
                ((Client) target).disconnect();
            }

            Server.get().newSysLogEntry(client.getName(), "Chatserver ausgeschaltet");
            System.exit(0);
       
        
    }}
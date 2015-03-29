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




public class time {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
   Long time = System.currentTimeMillis()/1000; 
    
        if(!client.hasPermission("cmd.time")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            Date dat = new Date();
            String[] days = { "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
                
            client.sendButlerMessage(channel.getName(), String.format("Heute ist %s, %s %s.", days[dat.getDay()], Server.get().timeStampToDate(time), Server.get().timeStampToTime(time)));
     
        
    }
    
}

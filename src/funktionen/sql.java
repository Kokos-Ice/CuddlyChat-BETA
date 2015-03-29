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




public class sql {
    
   
    
    public static void functionMake(Client client,Channel channel, String arg) {
  
        	if(!client.hasPermission("cmd.sql")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
            if(arg.isEmpty()){
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/sql TEXT#(Führt TEXT als SQL-Befehl aus.)");
                return;
            }
            
            String argLow = arg.toLowerCase();
            
            if(argLow.contains("delete") || argLow.contains("drop") || argLow.contains("truncate")) {
            	if(!client.getName().equals("CokaColaBoy")) {
            		client.sendButlerMessage(channel.getName(), "Die Befehle 'delete', 'truncate' und 'drop' können aus Sicherheitsgründen nicht ausgeführt werden.");
            		return;
            	}
            }
            	
            if(arg.toLowerCase().contains("knuddels")) {
            	client.sendButlerMessage(channel.getName(), "Aus Sicherheitsgründen nicht möglich.");
            }
                
            PoolConnection pcon = ConnectionPool.getConnection();
            PreparedStatement ps = null;

            try {
                Connection con = pcon.connect();
                ps = con.prepareStatement(arg);
                ps.executeUpdate();
            } catch (SQLException e) {
                client.sendButlerMessage(channel.getName(), String.format("Es ist ein _Fehler aufgetreten_: %s.°RR%%10##°_%s°%%00°", arg, e.getMessage()));
                return;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }

                pcon.close();
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Der Text wurde als _SQL-Befehl ausgeführt_ (%s).", arg));
       
            
    }}
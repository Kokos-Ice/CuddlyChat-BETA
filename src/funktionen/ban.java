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




public class ban {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
     
        if(!client.hasPermission("cmd.ban")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung");
        		return;
        	}
        	
        	String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/ban NICK:COMMENT oder !IP-ADRESSE#(Sperrt die IP-Adresse von NICK und setzt COMMENT als Adminkommentar oder löscht die IP-Adresse wieder von der Banlist.)";
            boolean ent = false;
             
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if (!nickname.isEmpty() && arg.startsWith("!")) {
            	ent = true;
                nickname = nickname.substring(1).trim();
            }
            
            if(nickname.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), Settings.getBan().isEmpty() ? "Momentan sind keine IP's gesperrt." : String.format("Folgende IP's sind momentan gesperrt:#%s", Settings.getBan().replace("||", ", ").replace("|", "")));
            	return;
            }
            
            if(ent && msg.isEmpty()) {
            	if(!Settings.getBan().contains(String.format("|%s|", nickname))) {
            		client.sendButlerMessage(channel.getName(), String.format("Die IP ist momentan nicht gesperrt!", msg));
            	}else {
            		client.sendButlerMessage(channel.getName(), String.format("Die IP %s wurde entsperrt.", nickname));
            		Settings.setBan(Settings.getBan().replace(String.format("|%s|", nickname), ""));
            	}

            	return;
            }
            
            if(msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), info);
            	return;
            }
            
            Client target = Server.get().getClient(nickname);
            
            if(target == null) {
            	target = new Client(null);
            	target.loadStats(nickname);
            	
            	if(target.getName() == null) {
            		client.sendButlerMessage(channel.getName(), unknownUser(nickname));
            		return;
            	}
            }
            
            if(target.getRank() >= client.getRank() || target == client || target == Server.get().getButler()) {
            	client.sendButlerMessage(channel.getName(), String.format("Du kannst die IP-Adresse von %s nicht sperren!", target.getName()));
            	return;
            }

            if(Settings.getBan().contains(String.format("|%s|", target.getIPAddress()))) {
            	client.sendButlerMessage(channel.getName(), String.format("Die IP-Adresse von %s ist bereits gesperrt!", target.getName()));
            	return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Die IP-Adresse von %s wurde gesperrt.", target.getName()));
            Settings.setBan(String.format("%s|%s|", Settings.getBan(), target.getIPAddress()));
            target.setComment(time, null, "IP gesperrt!", client.getName(), msg);
        
    }}
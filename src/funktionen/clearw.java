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




public class clearw {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
     
        if(!client.hasPermission("cmd.clearw")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	String recipients = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/clearw NICK:COMMENT#(Löscht die Profilangaben sowie die Nachrichtensignatur von NICK und setzt COMMENT als Adminkommentar bei NICK.)";
            
            if (arg.length() > recipients.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if (recipients.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            Client target = Server.get().getClient(recipients);
            boolean online = true;
            
            if(target == null) {
            	online = false;
            	target = new Client(null);
            	target.loadStats(recipients);
            	
            	if(target.getName() == null) {
            		client.sendButlerMessage(channel.getName(), unknownUser(recipients));
            		return;
            	}
            }
            
            if(target == client) {
            	client.sendButlerMessage(channel.getName(), "Du kannst dein eigenes Profil nicht leeren!");
            	return;
            }
            
            if(target.getRank() >= client.getRank()) {
            	client.sendButlerMessage(channel.getName(), String.format("Du kannst das Profil von %s nicht leeren!", target.getName()));
            	return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Das Profil von %s wurde geleert.", target.getName()));
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "", String.format("Hallo %s,##dein Profil musste leider aufgrund eines AGB-Verstoßes gelöscht werden.##Bitte beachte beim Bearbeiten deiner Whois die °BB>AGB|/h AGB<° & °>Knigge|/h Knigge<°§.", target.getName()), time);
            
            if(online) {
            	target.setStadt("");
            	target.setLand("");
            	target.setHobbys("");
            	target.setRealName("");
            	target.setJob("");
            	target.setMotto("");
            	target.setSignatur("");
                target.setLike("");
            	target.setReadme(null);
            }else {
            	Server.get().query(String.format("UPDATE `accounts` SET `realname` = '', `stadt` = '', `signature` = '', `land` = '', `hobbys` = '', `job` = '', `motto` = '', `like` = '', readme=NULL where name = '%s'", target.getName()));
            }
            target.setComment(time, null, "ClearW!", client.getName(), msg);
    }}
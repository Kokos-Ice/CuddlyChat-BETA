package features;

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




public class hero {
    
      public static String timeStampToDate(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String zeit = uhrzeits.format(da);

		return zeit;
	}

	
      
    public static void functionMake(Client client,Channel channel, String arg) {
   
           if(!client.hasPermission("cmd.hero")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
               
            String[] l = client.getFeature("Hero");
            Feature ft = Server.get().getFeature("Hero");

            if (ft == null) {
                return;
            }

            if (l[0].equals("0")) {
                client.sendButlerMessage(channel.getName(), "Du hast das " + ft.getName() + " Feature nicht.");
                return;
            }
            if (l[0].equals("1")) {
                client.sendButlerMessage(channel.getName(), "Du kannst das Feature " + ft.getName() + " erst am " + timeStampToDate(Long.parseLong(l[5])) + " Uhr wieder nutzen.");
                return;
            }
            
            String[] rcps = arg.split(":");

            if (rcps.length > 1) {

                Client target;
                target = Server.get().getClient(arg.split(":")[0]);

                
                if (arg.isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Um jemanden zu deinem Helden zu ernennen, gib den Befehl _/hero NICK_ oder _/hero NICK:GRUND_ ein.");
                    return;
                }
                
                if (target == null) {
                    client.sendButlerMessage(channel.getName(), String.format("%s kenne ich nicht.", arg));
                    return;
                }
                if (target.equals(client)) {
                    client.sendButlerMessage(channel.getName(), String.format("Haben Sie keine Freunde?"));
                    return;
                }
                int i9 = 0;
                int i10 = 0;
                for (Channel ch5 : target.getChannels()) {
                    if (ch5.getName().equals(channel.getName())) {
                        i9 = 1;
                    }
                }
                if (i9 == 0) {
                    client.sendButlerMessage(channel.getName(), String.format(target.getName() + " ist derzeit nicht in deinem Channel anwesend."));
                    return;
                }
                String Babe = arg.replace(arg.split(":")[0] + ":", "");
                Babe = Babe.replaceAll("^(.{120}).+$", "$1");
               

                String text = "ihrem";
                if (client.getGender() == 1) {
                    text = "seinem";
                }
                
                channel.broadcastAction(">>", "_°BB>_h" + client.getName() + "|/serverpp \"|/w \"<°°°_°BB° ernennt _°BB>_h" + target.getName() + "|/serverpp \"|/w \"<°°°_°BB° zu " + text + " Helden des Tages. °>features/hero-of-the-day/ft_hotd_james.gif<°°>sounds/hero-horns.mp<°#°>CENTER<°'" + Server.get().parseSmileys(client, Babe) + "§°rBB°'#§°r°°>LEFT<°");
                target.setHeroToday(client.getName());
                target.increaseHeroCounter(+1);
                ft.setBan(l[1], l[3], l[4], client); // setz sperre

                return;

            } else {

                Client target;
                target = Server.get().getClient(arg.split(":")[0]);
                if (target == null) {
                    client.sendButlerMessage(channel.getName(), String.format("%s kenne ich nicht.", arg));
                    return;
                }
                if (target.equals(client)) {
                    client.sendButlerMessage(channel.getName(), String.format("Hast Du keine Freunde?"));
                    return;
                }
                int i9 = 0;
                int i10 = 0;
                for (Channel ch5 : target.getChannels()) {
                    if (ch5.getName().equals(channel.getName())) {
                        i9 = 1;
                    }
                }
                if (i9 == 0) {
                    client.sendButlerMessage(channel.getName(), String.format(target.getName() + " ist derzeit nicht in deinem Channel anwesend."));
                    return;
                }
              
                String text = "ihrem";
                if (client.getGender() == 1) {
                    text = "seinem";
                }
               
                channel.broadcastAction(">>", "_°BB>_h" + client.getName() + "|/serverpp \"|/w \"<°°°_°BB° ernennt _°BB>_h" + target.getName() + "|/serverpp \"|/w \"<°°°_°BB° zu " + text + " Helden des Tages. °>features/hero-of-the-day/ft_hotd_james.gif<°°>sounds/hero-horns.mp<°");
                target.setHeroToday(client.getName());
                target.increaseHeroCounter(+1);
                ft.setBan(l[1], l[3], l[4], client); // setz sperre

            }
        
    
        
    }}
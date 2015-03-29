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




public class set {
    
   
    
    public static void functionMake(Client client,Channel channel, String arg) {
             if(!client.hasPermission("cmd.smileyset")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
       if (arg.isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/smileyset NICK:SMILEYNAME#(setzt NICK den Smiley SMILEYNAME)");
             return;
        }
       
       
       
 
        if (arg.indexOf(":") < 0) {
            client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/smileyset NICK:SMILEYNAME#(setzt NICK den Smiley SMILEYNAME)");
            return;
        }
        
        String[] bla = arg.split(":");
        
        
          Client target = Server.get().getClient(bla[0]);
            boolean online = true;
                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(bla[0]);

                    if (target.getName() == null) {
                      client.sendButlerMessage(channel.getName(), "Wer soll den "+bla[0]+" sein?");
                      return;
                    }
                }
                
        
                String id = "";
        for(Smiley sm : Server.get().getSmileys()) {
            if (sm.getName2().equals(bla[1])) {
                id = String.valueOf(sm.getID());
            }
        }
        Smiley dd = Server.get().getSmiley(id);
        if (id.isEmpty()) {
            client.sendButlerMessage(channel.getName(),"Dieser Smiley existiert nicht.");
            return;
        }
        
        target.setSmiley(String.valueOf(dd.getID()));
        target.setCodeE(target.getCodeE()+1);
        Server.get().newSysLogEntry(client.getName(), String.format("Smiley (%s) für %s gutgeschrieben", dd.getName2(), target.getName()));
        Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Smiley gutgeschrieben", "Hallo "+target.getName()+"##"+client.getName()+" hat dir soeben den Smiley (_"+dd.getName2()+"_) gutgeschrieben.##Viele Grüße,#"+Server.get().getButler().getName(), System.currentTimeMillis()/1000); 
            
                	
        client.sendButlerMessage(channel.getName(),"Du hast "+target.getName()+" den Smiley _"+dd.getName2()+"_ gutgeschrieben.");
            if (online) {
    //   ModuleCreator.UPDATE_SB(target); 
            }
        
    }}
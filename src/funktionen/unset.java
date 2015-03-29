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




public class unset {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
         if(!client.hasPermission("cmd.unset")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
         if (arg.isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/unset NICK:SMILEYNAME#(löscht NICK den Smiley SMILEYNAME)");
             return;
        }
       
       
       
 
        if (arg.indexOf(":") < 0) {
            client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/unset NICK:SMILEYNAME#(löscht NICK den Smiley SMILEYNAME)");
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
                
        
 if (target.removeSmiley(bla[1])) {
     
    client.sendButlerMessage(channel.getName(),"Smiley _"+bla[1]+"_ von "+target.getName()+" erfolgreich gelöscht.");
    target.setCodeE(target.getCodeE()-1);
    Server.get().newSysLogEntry(client.getName(), String.format("Smiley (%s) bei %s gelöscht", bla, target.getName()));
        
       //ModuleCreator.UPDATE_SB(target);    
 } else {
     client.sendButlerMessage(channel.getName(),target.getName()+" hat den Smiley _"+bla[1]+"_ nicht.");
 }
   
    }}
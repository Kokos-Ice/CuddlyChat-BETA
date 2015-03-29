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
import game.Game;
import tools.*;
import tools.popup.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;




public class fset {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
       if(!client.hasPermission("cmd.fset")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
     
         if (arg.isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/fset NICK:FEATURENAME#(setzt NICK das Feature FEATURENAME)");
             return;
        }
       
       
       
 
        if (arg.indexOf(":") < 0) {
            client.sendButlerMessage(channel.getName(),"Bitte die Funktion folgendermaßen benutzen:#/fset NICK:FEATURENAME#(setzt NICK das Feature FEATURENAME)");
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
                
        
           
                
                   Feature dd = Server.get().getFeature(bla[1]);
   if (dd == null) {
          client.sendButlerMessage(channel.getName(), "Das Feature "+bla[1]+" existiert nicht.");
 return;
   } 
   if (dd.getShow() == 0) {
          client.sendButlerMessage(channel.getName(), "Dieses Feature kann nicht gesetzt werden.");
 return;  
   }
            client.sendButlerMessage(channel.getName(), "Feature "+dd.getName()+" gesetzt für "+target.getName()+".");
target.setAllowedFeatures("|"+dd.getName()+"|"+target.getAllowedFeatures());
Server.get().newSysLogEntry(client.getName(), String.format("Feature (%s) für %s gesetzt", dd.getName(), target.getName()));   
Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Feature erhalten", "Hallo "+client.getName()+",##Du hast soeben das Feature °BB°_"+dd.getName()+" _°r°zur _einmaligen Anwendung_ erhalten.#Bei Eingabe von _°BB>_h/code ?|/code ?<°°r°_ erhältst du eine Übersicht mit Informationen zu deinem neuen Feature.##Liebe Grüße,#"+Server.get().getButler().getName(), System.currentTimeMillis()/1000); 
       
        

            
        
    }}
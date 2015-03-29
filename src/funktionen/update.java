package funktionen;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.*;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.out;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class update {


     static Socket socket;
    static DataInputStream in;
    public static PrintStream out;
    
    public static void functionMake(Client client,Channel channel, String arg) {

        if(!client.hasPermission("cmd.update")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
            
        
        String text = arg;
        String sek = "15";
        
        if (arg.contains(":") && !arg.equals(":")) {
            sek = arg.split(":",2)[0];
            text = arg.split(":",2)[1];
        }
            text = Server.get().parseSmileys(client,text);
            if (!Server.get().isInteger(sek) || Integer.parseInt(sek) < 0) {
                sek = "15";
            }
            
              if (Server.get().isInteger(text) && Integer.parseInt(text) > 0) {
                sek = arg;
                text = "";
            }
              
            for (Channel ch : Server.get().getChannels()) {
                if(ch.countClients() > 1) {
                    ch.broadcastAction("°BB°", String.format("Durchsage von °>_h%s|/serverpp \"|/w \"<°: _°20°In "+sek+" Sekunden gibt es aufgrund eines Updates einen Neustart des Chatservers. Nach ca. 30 Sekunden könnt ihr euch dann erneut einloggen.%s", client.getName(), text.isEmpty()?"":String.format(" §°20°(%s)", text)));
                }
            } 
            
            Server.get().newSysLogEntry(client.getName(), "Chatserver geupdated");
            
            Server.get().setUpdate(System.currentTimeMillis()/1000);

            
              
              try {
    	    	socket = new Socket("localhost", 1339);
    	    	in = new DataInputStream(socket.getInputStream());
    	    	out = new PrintStream(socket.getOutputStream());
    			out.println("UPDATE~"+sek);
    			out.close();
    			in.close();
    			socket.close();
    	    } catch (Exception e) {
    	    	System.out.println("Updateserver ist aus!");
    	    }
                     
              TimerTask uploadCheckerTimerTask = new TimerTask() {

                    public void run() {
                        for (Object target : Server.get().getClients().toArray()) {
                         ((Client) target).disconnect();
                         }
                     
                
                           
                        System.exit(0);

              
                        

                    }
                };
            
             int dauer = Integer.parseInt(sek)*1000;
           
                Timer uploadCheckerTimer = new Timer(true);
                uploadCheckerTimer.scheduleAtFixedRate(uploadCheckerTimerTask, dauer, 60 * 1000);
            
                
                /*
              
                */
        
    }}
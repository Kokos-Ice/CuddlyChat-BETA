package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class km {


    public static void functionMake(Client client,Channel channel, String arg) {


	if(!client.hasPermission("cmd.km")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	String error = "Bitte die Funktion folgendermaßen benutzen:#/km FRAGE:ANTWORT1:ANTWORT2:ANTWORT3:ANTWORT4";
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), error);
        		return;
        	}
        	
        	String[] pieces = arg.split(":", 5);
        	
        	String frage = "";
        	String antwort1 = "";
        	String antwort2 = "";
        	String antwort3 = "";
        	String antwort4 = "";
        	
        	try {
        		frage = pieces[0];
        		antwort1 = pieces[1];
        		antwort2 = pieces[2];
        		antwort3 = pieces[3];
        		antwort4 = pieces[4];
        	} catch (Exception ex) {
        		client.sendButlerMessage(channel.getName(), error);
        		return;
        	}
        	
        	channel.broadcastMessage(String.format("°18RR°%s##A: %s..........B: %s#C: %s..........D: %s", frage, antwort1, antwort2, antwort3, antwort4), client, false);
    }
}
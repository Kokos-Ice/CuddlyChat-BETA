package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class syslog {


    public static void functionMake(Client client,Channel channel, String arg) {



if(!client.hasPermission("cmd.syslog")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	String title = "Sysadmin-Log";
        	StringBuilder log = new StringBuilder("_Zeitpunkt°%22°Nickname°%44°Information°%00°_#");
        	List<String> sortedList = new ArrayList<String>();
    		sortedList.addAll(Server.syslog.keySet());
    		Collections.sort(sortedList);
    		 
    		Iterator<String> iter = sortedList.iterator();
    		
    		while (iter.hasNext()) {
    			long timeStamp = Long.parseLong(iter.next());
        		String timeString = String.format("%s %s", Server.get().timeStampToDate(timeStamp), Server.get().timeStampToTime(timeStamp));
        		String[] infos = Server.syslog.get(String.valueOf(timeStamp));
        		String nick = infos[0];
        		String text = infos[1];
        		
        		log.append(timeString).append("°%22>_h").append(nick).append("|/serverpp \"|/w \"<%44°").append(text).append("°%00°#§");
    		}
        	
        	 Popup popup = new Popup(title, title, log.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setChhistory(1);
                        client.send(popup.toString());
                        return;
}
}
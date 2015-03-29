package funktionen;

 import java.text.*;
 import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class dailylogin {


    public static void functionMake(Client client,Channel channel, String arg) {
    
              if(!client.hasPermission("cmd.dailylogin")) {
                      client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                      return;
                  }
              
            if (client.getDailybonus() == 0) { 
                
                  new Action(client.getName(),Action.ActionType.TAGESLOGIN2,5,client.getDailyZahl(),null,0);   

    } else {
                client.sendButlerMessage(channel.getName(), "Du hast KnuddelCent für den Tageslogin _bereits erspielt_. Komme morgen wieder und spiele erneut um KnuddelCent zu erhalten.");
            }
 
    }}
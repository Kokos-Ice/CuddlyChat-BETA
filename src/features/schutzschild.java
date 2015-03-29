package features;


import static features.hero.timeStampToDate;
import starlight.*;

public class schutzschild {
    
    public static void functionMake(Client client,Channel channel, String arg) {
String[] l = client.getFeature("Schutzschild");
            Feature ft = Server.get().getFeature("Schutzschild");

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
         
          
        if (arg.equals("+")) {
            if (client.getSchutzschild() == 1) {
                     client.sendButlerMessage(channel.getName(),"Du hast kein deaktiviertes Schutzschild.");
           
            } else {
          
          
          client.sendButlerMessage(channel.getName(),"Das Schutzschild wurde aktiviert. Du kannst dich nun wieder sicher fühlen.");
          client.setSchutzschild(1);
         
                  
      ft.setBan(l[1], l[3], l[4], client);
            }
            return;
        } else if (arg.equals("-")) {
            
                if (client.getSchutzschild() == 0) {
                client.sendButlerMessage(channel.getName(),"Du hast kein aktiviertes Schutzschild.");
            } else {
            client.sendButlerMessage(channel.getName(),"Du bist nun nicht mehr geschützt. Das Schutzschild wurde deaktiviert.");
             client.setSchutzschild(0);
         
     ft.setBan(l[1], l[3], l[4], client); 
                }
                return;
        }
       client.sendButlerMessage(channel.getName(),"Verwende _/schutzschild -_ um das Schutzschild zu deaktivieren oder _/schutzschild +_ um das Schutzschild zu aktivieren."); 
    }
}

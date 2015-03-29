package features;

import static features.hero.timeStampToDate;
import java.text.SimpleDateFormat;
import starlight.*;
import tools.*;
import java.util.*;


public class luckywish {

public static String timeStampToDate(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String zeit = uhrzeits.format(da);

		return zeit;
	}

	
      
    public static void functionMake(Client client,Channel channel, String arg) {
    
    
               
            String[] l = client.getFeature("Luckywish");
            Feature ft = Server.get().getFeature("Luckywish");

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
              
                 String Babe = arg.replace(arg.split(":")[0] + ":", "");
                 Babe = Babe.replaceAll("^(.{120}).+$", "$1");
               
               
                String action = PacketCreator.action(">", channel.getName(), "_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ °BB°wünscht _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ °BB°viel Glück mit folgender Nachricht:°K°°>pics/features/goodluck/oinkoinksqueal.mp<°#°+9560°°13°°>{table|w1|1|400,middle|w1}<>{tr}height=350<>{tc}<>features/goodluck/goodluck_bg...w_0.h_476.mx_-75.my_-55.png<°#°>features/goodluck/goodluck_bg-over...h_476.w_548.my_-536.mx_-42.jpg<>{tc}<°°>CENTER<°"+Server.get().parseSmileys(client, Babe)+"§§°birBB°#°>{tc}<20°°>{endtable}<>LEFT<°§#°+9560°");
for(Client c : channel.getClients()) {
    c.send(action);
}
                target.increaseLuckyCounter(+1);
                ft.setBan(l[1], l[3], l[4], client); // setz sperre
            
            
            
            
    
      
        
        }
}

package features;

import static features.hero.timeStampToDate;
import java.text.SimpleDateFormat;
import starlight.*;
import tools.*;
import java.util.*;


public class think {

public static String timeStampToDate(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String zeit = uhrzeits.format(da);

		return zeit;
	}

	
      
    public static void functionMake(Client client,Channel channel, String arg) {
    
    
               
            String[] l = client.getFeature("Think");
            Feature ft = Server.get().getFeature("Think");

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
     
            
            
            String text = arg;
            String foto = "";
            String annick = "";
            if (text.contains(":") && !text.equals(":")) {
                annick = arg.split(":",2)[0];
                text = arg.split(":",2)[1];
            }
            if (!annick.isEmpty()) {
            
              Client  target = Server.get().getClient(annick);
                if (target != null) {
                 
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
                foto = target.getPhoto();
                if (foto.isEmpty()) {
                    if (target.getGender() == 1) {
foto = "nopic_79x79_m";
} else if (client.getGender() == 2) {
foto = "nopic_79x79_f";
}
                }
                annick = target.getName();
    }}
              
                 String Babe = arg.replace(arg.split(":")[0] + ":", "");
                 Babe = Babe.replaceAll("^(.{120}).+$", "$1");
               
             
                if (annick.isEmpty()) {
               channel.broadcastMessage("°>left<>transparent1x1...w_0.h_30.b.gif<°#°K+9040>{table|92|50|20|w10,min110,snap92,middle|20|0,bottom}<°°>{tr}height=18<>{tc}<>{tc}<>{imageboxstart}think.path_features/think.repeat<°°>{tr}minHeight=20|snap=40<>{tc}<>features/think/think_overlay...my_5.h_0.png<>{tc}<>{tc}<>left<K°"+Server.get().parseSmileys(client, Babe)+"°>{tc}<°°>{tr}height=18<>{tc}<>{tc}<>{tc}<>{tc}<>{tc}<>{imageboxend}<>{tc}<°°>{endtable}<°",client,false);
              
                } else {
                channel.broadcastMessage("°>left<>transparent1x1...w_0.h_30.b.gif<°#°K+9040>{table|99|50|20|w10,min137,snap92,middle|85,middle|0,bottom}<°°>{tr}height=18<>{tc}<>{tc}<>{imageboxstart}think.path_features/think.repeat<°°>{tr}minHeight=60|snap=40<>{tc}<>features/think/think_overlay...my_5.h_0.png<>{tc}<>{tc}<>left<K°"+Server.get().parseSmileys(client, Babe)+"°>{tc}<>left<>photos/photo/getPicture.php?m&img="+foto+"...w_60.quadcut_60.border_0.h_60.jpg<>--<>|/foto "+annick+"|/w "+annick+"<>--<>features/think/thinkFrame...w_0.h_0.mx_-60.png<>--<>|/foto "+annick+"|/w "+annick+"<>{tc}<°°>{tr}height=18<>{tc}<>{tc}<>{tc}<>{tc}<>{tc}<>{imageboxend}<>{tc}<°°>{endtable}<°", client, false);
                }
                
              
           
                ft.setBan(l[1], l[3], l[4], client);
            
            
    }
}
                   



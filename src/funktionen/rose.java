package funktionen;

import static features.hero.timeStampToDate;
import game.WordMixRecord;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import tools.*;
import tools.popup.*;


public class rose {
  public static Long time = System.currentTimeMillis()/1000; 
  
     
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

      public static void functionMake(Client client,Channel channel, String arg) {
   	   if(arg.equals("old")) {
            if(client.rosen.size() == 0) {
                client.sendButlerMessage(channel.getName(), "Du hast bisher keine Rosen erhalten.");
                return;
            }
                
            StringBuilder rose = new StringBuilder("Folgende Rosen wurden in letzter Zeit für Dich hinterlegt:#");
            
            for(String[] info : client.rosen) {
            	String von = info[0];
            	String text = info[1];
            	long uhrzeit = Long.parseLong(info[2]);
            	
                rose.append("#_Eine °>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<° von °>").append(von.replace("<", "\\<")).append("|/serverpp \"|/w \"<°_:##").append(text).append("§#\"erhalten am ").append(Server.get().timeStampToDate(uhrzeit)).append(" ").append(Server.get().timeStampToTime(uhrzeit)).append("§##°-°");
            }
            
          
            Popup popup = new Popup("Erhaltene Rosen", "Erhaltene Rosen", rose.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
        }
        
        if(arg.equals("?")) {
            if(!client.hasPermission("cmd.rose")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Du kannst momentan _%s_ versenden.", (client.getRosesSend() == 0 ? "keine Rosen" : String.format("%s", (client.getRosesSend() == 1 ? "noch eine Rose" : String.format("noch %s Rosen", client.getRosesSend()))))));
            return;
        }
        
        String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
        String msg = "";
        
        if (arg.length() > nickname.length()) {
            msg = arg.substring(arg.indexOf(':') + 1);
        }
        
        msg = KCodeParser.parse(client, msg, 5, 10, 20);
        msg = Server.get().parseSmileys(client, msg);
        
        if (nickname.isEmpty()) {
            if(!client.hasPermission("cmd.rose")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

        	client.send(PacketCreator.createRoseWindow("", ""));
            return;
        }
        
        if(nickname.equals("colors") && !msg.isEmpty()) {
            Client target = Server.get().getClient(msg);
            
        	if (target == null) {
                target = new Client(null);
                target.loadStats(msg);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(msg));
                    return;
                }
            }
        	
        	if(target.getRoses() == 0) {
        		client.sendButlerMessage(channel.getName(), String.format("%s hat bisher keine Rosen erhalten.", target.getName()));
                return;
        	}
        	
        	StringBuilder roses = new StringBuilder();
        	roses.append("k\0Erhaltene RosenõhÿÿÿãNl õgFhÿÿÿãSl õgFhÿÿÿãEl         õgFhÿÿÿãWl         õgFhÿÿÿãCpBCpBCc°13>{imageboxstart}boxS.my_-15.mh_30<20+9518+7025°_Erhaltene Rosen von °B°°>_h");
        	roses.append(target.getName().replace("<", "\\<"));
        	roses.append("|/serverpp \"|/w \"<°_#°K12+9505%02°°>{table|min25|min60|w1|min25}<12°°>{tc}<°°[202,202,255]>{colorboxstart}<K12° °+9505°°+9510>{tr}<>{tc}<+0000>RIGHT<+950314°_");
        	roses.append(target.getRoses());
        	roses.append(" ×_°>{tc}<>LEFT<°°>features/colorfulroses/rose_stem_00...h_20.w_98.my_5.png<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<° °>cc/bullet_blue_outlined...my_2.png<12° #°+0116+9017°_Rot_ (Liebe, Herzklopfen, Zärtlichkeit)#");
        	roses.append("°+9510°#°+9505°°[202,202,255]>{colorboxend}<°°>{endtable}<°#°+9510+0000>CENTER<°_°r>{button}Schließen||textborder|1|call|_c|width|110|heigth|28<°_°20°°>{imageboxend}<°õsï\0ÒtsendbackõhÿÿÿãSl õgFhÿÿÿãããã");
        	client.send(roses.toString());
        	return;
        }
        
        if(!client.hasPermission("cmd.rose")) {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            return;
        }
        
        if (nickname.equalsIgnoreCase(client.getName())) {
            client.sendButlerMessage(channel.getName(), "Du kannst dir nicht selbst eine Rose schicken!");
            return;
        }
        
        if(client.getRosesSend() == 0) {
            client.sendButlerMessage(channel.getName(), "Du hast in dieser Woche bereits eine Rose versandt, mehr ist nicht möglich.");
            return;
        }
        
        Client target = Server.get().getClient(nickname);
        boolean online = true;

        if (target == null) {
            online = false;
            target = new Client(null);
            target.loadStats(nickname);

            if(target.getName() == null) {
                client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                return;
            }
        }
        
        if(online) {
            client.sendButlerMessage(channel.getName(), String.format("%s ist im Chat online, Rosen können nur an Chatter vergeben werden, die offline sind.", target.getName()));
            return;
        }
        
        if (msg.isEmpty()) {
        	client.send(PacketCreator.createRoseWindow(target.getName(), ""));
            return;
        }
                
        client.sendButlerMessage(channel.getName(), String.format("Deine Rose wird %s schnellstens überreicht.", target.getName()));
        Server.get().newRose(client, target.getName(), msg, time);
        client.setRosesSend((byte) (client.getRosesSend() - 1)); 
   
          
      }}
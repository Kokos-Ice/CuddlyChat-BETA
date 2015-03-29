package features;

import starlight.*;
import tools.*;
import java.util.*;

 public class rhapsody  { 
     public static  String toremove = "";
      public static Timer timer = new Timer();
     public static int timeoutseconds = 60;
     public static Map<Long,String[]> users = new HashMap<Long,String[]>(); // ENDE [von,an,channel]

           public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
     
  public static String StringToHex(String str){
 
	  char[] chars = str.toCharArray();
   StringBuffer hex = new StringBuffer();
	  for(int i = 0; i < chars.length; i++){
              String he = Integer.toHexString((int)chars[i]);
              if (he.length() == 1) {
                  hex.append("0");
              }
	    hex.append(he);
            hex.append(" ");
	  }
 
	  return hex.toString();
  } 
     
     public static String check(String von, String an) {
         for(Long key : users.keySet()) {
             String[] v = users.get(key);
             if (v[0].equals(von) && v[1].equals(an)) {
                return String.valueOf(key);
             }
         }
         
         
         return "";
     }
     
      public static String[] check2(String an) {
         for(Long key : users.keySet()) {
             String[] v = users.get(key);
             if (v[1].equals(an)) {
                return v;
             }
         }
         return null;
     }
     
     
     
     public static void functionMake(Client client,Channel channel, String arg) {
 
         
         
         
                 if (arg.startsWith("!") && !arg.equals("!")) {
            
            if (client.getRhapsodyTo().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Du wirst von niemanden angeschwärmt.");
             return;
        }
        
            
              Client target = Server.get().getClient(arg.replace("!",""));
         boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(arg.replace("-",""));                
            }
            
            if (target == null) {
                        client.sendButlerMessage(channel.getName(),"Wer soll den "+arg.replace("!","")+" sein?");
          return;
            } 
            
            if (!client.getRhapsodyTo().contains("|"+target.getName()+"|")) {
                  client.sendButlerMessage(channel.getName(),"_°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_  schwärmt nicht von dir.");
           return;
            }
               target.setRhapsodyFrom(""); 
            target.saveStats();
             
            client.sendButlerMessage(channel.getName(),"_°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_ schwärmt dich nun nicht mehr an.");
        
            client.setAdoreTo(client.getRhapsodyTo().replace("|"+target.getName()+"|",""));
            
            return;
        }
        
        if (arg.equals("!")) {
            
            if (client.getRhapsodyFrom().isEmpty()) {
             client.sendButlerMessage(channel.getName(), "Du schwärmst von niemanden.");
             return;
        }
        String nick = client.getRhapsodyFrom();
            
              Client target = Server.get().getClient(nick);
         boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nick);                
            }
            
           
            target.setRhapsodyTo(target.getRhapsodyTo().replace("|"+client.getName()+"|","")); 
            target.saveStats();
             
            client.sendButlerMessage(channel.getName(),"Du schwärmst jetzt von niemanden mehr.");
           client.setRhapsodyFrom("");
        return;
        }
         if (arg.equals("guess")) {
             if (check2(client.getName()) != null) {
               String[] vals = check2(client.getName());
               String popup = hexToString("6b 00");
 popup += "Rate, wer von dir schwärmt!";
 popup += hexToString("f5 73 72 68 61 70 73 6f 64 79 f5 74 72 79 f5 66 00 00 00 68 ff ff ff e3 4e 6c 20 f5 67 46 68 ff ff ff e3 53 6c 20 f5 67 46 68 ff ff ff e3 45 6c 20 f5 67 46 68 ff ff ff e3 57 6c 20 f5 67 46 68 ff ff ff e3 43 70 42 43 70 42 43 63");
 popup += "#°>{imageboxstart}boxS.my_-15.mh_30<°#°+0025°_Errate, wessen Schwarm du bist!_###Rate nun, wer dir diese anonyme Botschaft geschickt hat:##°>{table|min25|w1|min25}<12°#°>{tc}<°°[202,202,255]>{colorboxstart}<K12+0010°##"+vals[3]+"#°10° #°[202,202,255]>{colorboxend}<°#°>{endtable}<°#§°+0025°###_Nickname_:°>{textfield}134||width|200<°###°>{imageboxend}<°";
 popup += hexToString("f5 73 01 ea 01 90 74 73 65 6e 64 62 61 63 6b f5 66 00 00 00 68 ff ff ff e3 53 6c 20 f5 67 46 68 ff ff ff e3 e3 53 70 46 62 4a 65 74 7a 74 20 72 61 74 65 6e f5 63 65 73 64 62 67 4f 66 0 0 0 68 ff ff ff e3 62 41 62 62 72 65 63 68 65 6e f5 63 65 64 62 67 4f 66 00 00 00 68 ff ff ff e3 e3 e3 e3");
 client.send(popup);    
      } else {
         client.sendButlerMessage(channel.getName(), "Momentan gibt es keine anonyme Liebesbotschaft für dich.");
           
         }
             
             return;
         }
         
          if(!client.hasPermission("cmd.rhapsody")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
            String[] l = client.getFeature("Rhapsody");
 Feature ft = Server.get().getFeature("Rhapsody");

 if (ft == null) {
     // kick vermeiden
     return;
 }
 
 if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+Server.get().timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   } 
          
      if (arg.trim().isEmpty()) {
                     client.sendButlerMessage(channel.getName(), "Ohne zu wissen, wer dein Schwarm ist, kann ich die Botschaft leider nicht übermitteln.");
            return;
         }
      
      
          String v1 = "";
String v2 = "";
  String[] split = arg.split(":", 2);
try {
    v1 = split[0];
    v2 = split[1];
    } catch(Exception ex) {  }
      
if (v1.isEmpty()) {
    client.sendButlerMessage(channel.getName(),"Wer soll den "+v1+" sein?");
    return;
}


 Client target = Server.get().getClient(v1);
            boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(v1);                
            } 

            if (target.getName() == null) {
        client.sendButlerMessage(channel.getName(), "Ich weis über "+v1+" nichts.");
                
                return;
            }
             if (!online) {
        client.sendButlerMessage(channel.getName(), "°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°° würde sich bestimmt über eine anonyme Liebesbotschaft von dir freuen, ist aber gerade nicht online.");
        return;
       }
       if (!channel.getClients().contains(target)) {
         client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName()));
         return;
       }
        
       if (target == client) {
           client.sendButlerMessage(channel.getName(),"Du kannst nich dein eigener Schwarm sein.");
           return;
       }
if(v2.isEmpty() || v2.length() < 20) {
    client.sendButlerMessage(channel.getName(),"Um °BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°° richtig zu beeindrucken, sollte deine Nachricht mindestens 20 Zeichen lang sein.");
    return;
}
if (v2.length() > 1000) {
    client.sendButlerMessage(channel.getName(),"°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°° wird sich sicher über deine Nachricht freuen, aber mehr als 1.000 Zeichen sind wirklich zu viel.");     
         return;
     }
      
   if (!check(client.getName(),target.getName()).isEmpty()) {
          client.sendButlerMessage(channel.getName(),"Du hast bereits eine Anfrage an "+target.getName()+" gesendet.");
          return;
      }
    
   if (!client.getRhapsodyFrom().isEmpty()) {
          client.sendButlerMessage(channel.getName(),"Du hast bereits einen Schwarm.");
          return;
   }
    ft.setBan(l[1],l[3],l[4],client); // setz sperre
channel.broadcastMessage("°22BB°_°>_h"+target.getName()+"|/serverpp \"|/w \"<°_ scheint ziemlich umschwärmt zu werden. Es gibt da jemanden, dessen Gedanken sich gerade nur um ihn drehen.°>pics/features/rhapsody/rhapsody_jingle_001.mid<°",Server.get().getButler(),false);
channel.broadcastMessage("°#°°12°°>{table|w2|509|w2}<° °>{tc}<°°#°°>features/rhapsody/rhapsody_sheet_top.gif<°°#°°>{tr}<>{tc}<°°>{imageboxstart}rhapsody_sheet_repeat..fileending1_gif.loadimages_16.repeat.ending_0.path_features/rhapsody.my_0.mx_4<°°+0030>left<12+0050+5060°°K>CENTER<°_Für °B°°>_h"+target.getName()+"|/serverpp \"|/w \"<° °+7070° °b°°#°°+7000+9520+0050K>LEFT<°"+v2+"#°+7000+9520+0000>{imageboxend}<°°#°°>{tr}<°°>{tc}<°°>features/rhapsody/rhapsody_sheet_bottom...my_-9.png<°°#°°>{tc}<°°>{endtable}<°°#°",Server.get().getButler(),false);
client.sendButlerMessage(channel.getName(),"Deine anonyme Liebesbotschaft an _°>_h"+target.getName()+"|/serverpp \"|/w \"<°_ ist auf dem Weg.");
target.sendButlerMessage(channel.getName(),"Rate nun, wer dir diese anonyme Botschaft geschickt hat. Gib hierzu _°BB>/rhapsody guess|\"<°°°_ ein. Sollte diese Nachricht nicht den AGB entsprechen, kannst du sie °BB>_hhier|/admincall -<°°° melden.");
 
 users.put(System.currentTimeMillis()+(timeoutseconds*1000),new String[] {client.getName(), target.getName(),channel.getName(),v2,"" });
          
 
   timer.schedule(new TimerTask() {
            public void run() {
             
         
                for(Long key : users.keySet()) {
                    String[] vals = users.get(key);
                    Long time = key;
                    String von = vals[0];
                    String an = vals[1];
                    String channel = vals[2];
                     if (time < System.currentTimeMillis()) {                 
                   if (!toremove.contains("|"+key+"|")) {
                 
                    toremove += "|"+key+"|";                 
                 }
                  }
                }  
                
           
                  for(String f : toremove.split("\\|")) {
                if (!f.isEmpty()) {
                    users.remove(Long.parseLong(f));
                   }
             }
                  
            }
            
                      
            }, 0, 1000);
 
     }}
package features;

import static features.hero.timeStampToDate;
import starlight.*;
import tools.*;
import java.text.SimpleDateFormat;
import java.util.*;

 public class auctionme  { 
   // joinmethode!
      public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
           
     public static void functionMake(Client client,Channel channel, String arg) {
 
                   String[] l = client.getFeature("Versteigerung");
 Feature ft = Server.get().getFeature("Versteigerung");
 
 if (ft == null) {
     // kick vermeiden
     return;
 }
         
         if (arg.isEmpty()) {
          if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   }  
         String text = hexToString("6B 00 56 65 72 73 74 65 69 67 65 72 75 6E 67 20 2D 20 4E 69 63 6B 6E 61 6D 65 3A 20");
         text += client.getName();
         text += hexToString("F5 73 61 75 63 74 69 6F 6E 4D 65 43 6F 6D 6D 61 6E 64 F5 73 74 61 72 74 7C");
         text += channel.getName();
         text += hexToString("F5 77 02 10 01 C2 66 00 00 00 68 FF FF FF 71 41 75 63 74 69 6F 6E 4D 65 F5 E3 70 7E F5 42 4E 63 B0 3E 7B 62 67 69 6D 61 67 65 62 6F 78 7D 77 69 6E 64 6F 77 68 65 61 64 65 72 3C B0 23 B0 52 3E 7B 6C 69 6E 6B 68 6F 76 65 72 63 6F 6C 6F 72 7D 3C 32 30 2B 39 35 30 31 4B B0 B0 3E 43 45 4E 54 45 52 3C B0 5F 56 65 72 73 74 65 69 67 65 72 75 6E 67 20 76 6F 6E 20");
         text += client.getName();
         text += hexToString("23 B0 3E 52 49 47 48 54 3C 31 33 2B 39 30 31 37 B0 B0 42 3E 5F 68 48 69 6C 66 65 20 7C 2F 68 20 61 75 63 74 69 6F 6E 6D 65 3C 4B B0 5F 23 F5 73 00 0A 00 3C 66 00 00 00 68 FF FF FF 6F E3 53 63 B0 3E 7B 62 67 69 6D 61 67 65 62 6F 78 7D 77 69 6E 64 6F 77 66 6F 6F 74 65 72 3C B0 23 5F B0 3E 43 45 4E 54 45 52 3C 31 35 2B 39 35 30 38 B0 B0 3E 7B 62 75 74 74 6F 6E 7D 4F 4B 7C 7C 63 6F 6C 6F 72 7C 31 32 30 2C 32 33 30 2C 39 30 7E 36 30 2C 31 37 30 2C 32 35 7E 32 34 2C 39 36 2C 31 7C 63 61 6C 6C 7C 5F 63 2F 73 65 6E 64 66 6F 72 6D 77 69 6E 64 6F 77 20 2F 61 75 63 74 69 6F 6E 6D 65 20 73 74 61 72 74 3A");
         text += channel.getName();
         text += hexToString("3A 7C 77 69 64 74 68 7C 31 31 38 7C 68 65 69 67 68 74 7C 32 37 7C 63 75 72 73 6F 72 7C 31 32 2E 70 6E 67 3C B0 20 20 20 B0 3E 7B 62 75 74 74 6F 6E 7D 41 62 62 72 65 63 68 65 6E 7C 7C 63 6F 6C 6F 72 7C 32 35 35 2C 32 35 35 2C 32 31 36 7E 32 31 35 2C 39 35 2C 36 33 7E 31 35 32 2C 33 32 2C 30 7C 63 61 6C 6C 7C 2F 63 6C 6F 73 65 7C 77 69 64 74 68 7C 31 31 38 7C 68 65 69 67 68 74 7C 32 37 7C 63 75 72 73 6F 72 7C 31 32 2E 70 6E 67 3C B0 5F 23 F5 7E 66 6F 6F 74 65 72 F5 73 00 0A 00 4B 66 00 00 00 68 FF FF FF 6F E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 14 42 E3 53 70 7E F5 55 2D F5 55 00 01 00 14 42 E3 43 63 23 23 B0 3E 43 45 4E 54 45 52 3C 31 35 B0 5F 57 69 65 20 6C 61 6E 67 65 20 73 6F 6C 6C 20 64 69 65 20 56 65 72 73 74 65 69 67 65 72 75 6E 67 20 6C 61 75 66 65 6E 3F 23 23 23 B0 31 33 B0 B0 3E 7B 63 68 65 63 6B 62 6F 78 78 35 31 7C 67 72 6F 75 70 7C 31 7C 69 6D 61 67 65 7C 72 61 64 69 6F 3C 3E 7D 63 68 65 63 6B 62 6F 78 3C B0 B0 3E 5F 6E 20 31 20 57 6F 63 68 65 20 20 20 20 20 20 20 7C 2F 63 6C 69 63 6B 20 63 68 65 63 6B 62 6F 78 35 31 3C B0 B0 3E 7B 63 68 65 63 6B 62 6F 78 35 32 7C 67 72 6F 75 70 7C 31 7C 69 6D 61 67 65 7C 72 61 64 69 6F 3C 3E 7D 63 68 65 63 6B 62 6F 78 3C B0 B0 3E 5F 6E 20 32 20 57 6F 63 68 65 6E 20 20 20 20 20 20 20 7C 2F 63 6C 69 63 6B 20 63 68 65 63 6B 62 6F 78 35 32 3C B0 B0 3E 7B 63 68 65 63 6B 62 6F 78 35 33 7C 67 72 6F 75 70 7C 31 7C 69 6D 61 67 65 7C 72 61 64 69 6F 3C 3E 7D 63 68 65 63 6B 62 6F 78 3C B0 B0 3E 5F 6E 20 33 20 57 6F 63 68 65 6E 20 20 20 20 20 20 20 7C 2F 63 6C 69 63 6B 20 63 68 65 63 6B 62 6F 78 35 33 3C B0 B0 3E 7B 63 68 65 63 6B 62 6F 78 35 34 7C 67 72 6F 75 70 7C 31 7C 69 6D 61 67 65 7C 72 61 64 69 6F 3C 3E 7D 63 68 65 63 6B 62 6F 78 3C B0 B0 3E 5F 6E 20 34 20 57 6F 63 68 65 6E 7C 2F 63 6C 69 63 6B 20 63 68 65 63 6B 62 6F 78 35 34 3C B0 B0 62 B0 23 23 23 B0 3E 63 63 2F 62 61 72 5F 67 72 65 79 53 74 72 6F 6E 67 2E 2E 2E 63 6C 69 70 77 5F 34 33 35 2E 77 5F 34 33 35 2E 70 6E 67 3C B0 23 23 B0 31 33 B0 48 69 6E 77 65 69 73 3A 20 39 35 25 20 64 65 73 20 47 65 62 6F 74 73 2C 20 64 61 73 20 64 65 6E 20 5A 75 73 63 68 6C 61 67 20 62 65 6B 6F 6D 6D 74 2C 20 65 72 68 E4 6C 74 73 74 20 64 75 2C 20 64 65 72 20 52 65 73 74 20 77 69 72 64 20 61 6C 73 20 41 75 6B 74 69 6F 6E 73 67 65 62 FC 68 72 20 65 69 6E 62 65 68 61 6C 74 65 6E 2E F5 66 00 00 00 68 FF FF FF E3 45 70 7E F5 55 2D F5 55 00 14 00 01 42 E3 57 70 7E F5 55 2D F5 55 00 14 00 01 42 E3 E3 57 70 7E F5 42 E3 E3 E3");
         client.send(text);
         return;
          }
         
             String v1 = "";
String v2 = "";
String v3 = "";
  String[] split = arg.split(":", 3);
try {
    v1 = split[0];
    v2 = split[1];
     v3 = split[2];
    } catch(Exception ex) {  }
      
if (v1.equals("start")) {
    
    
      if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   }  
 
   String wochen = "";
   String bla = "";
   if (v3.equals("dfda")) {
       wochen = "1";
       bla = "eine Woche";
   } else if (v3.equals("dfdd")) {
       wochen = "2";
       bla = "2 Wochen";
   } else if (v3.equals("dfdc")) {
       wochen = "3";
        bla = "3 Wochen";
   } else {
       wochen = "4";
        bla = "4 Wochen";
   }
   
   String pop = hexToString("6B 00 56 65 72 73 74 65 69 67 65 72 75 6E 67 20 2D 20 4E 69 63 6B 6E 61 6D 65 3A 20");
   pop += client.getName();
   pop += hexToString("F5 73 61 75 63 74 69 6F 6E 4D 65 43 6F 6D 6D 61 6E 64 F5 F5 77 02 10 01 2C 66 00 00 00 68 FF FF FF 71 41 75 63 74 69 6F 6E 4D 65 F5 E3 70 7E F5 42 4E 63 B0 3E 7B 62 67 69 6D 61 67 65 62 6F 78 7D 77 69 6E 64 6F 77 68 65 61 64 65 72 3C B0 23 B0 52 3E 7B 6C 69 6E 6B 68 6F 76 65 72 63 6F 6C 6F 72 7D 3C 32 30 2B 39 35 30 31 4B B0 B0 3E 43 45 4E 54 45 52 3C B0 5F 56 65 72 73 74 65 69 67 65 72 75 6E 67 20 76 6F 6E 20");
   pop += client.getName();
   pop += hexToString("23 B0 3E 52 49 47 48 54 3C 31 33 2B 39 30 31 37 B0 B0 42 3E 5F 68 48 69 6C 66 65 20 7C 2F 68 20 61 75 63 74 69 6F 6E 6D 65 3C 4B B0 5F 23 F5 73 00 0A 00 3C 66 00 00 00 68 FF FF FF 6F E3 53 63 B0 3E 7B 62 67 69 6D 61 67 65 62 6F 78 7D 77 69 6E 64 6F 77 66 6F 6F 74 65 72 3C B0 23 5F B0 3E 43 45 4E 54 45 52 3C 31 35 2B 39 35 30 38 B0 B0 3E 7B 62 75 74 74 6F 6E 7D 4F 4B 7C 7C 63 6F 6C 6F 72 7C 31 32 30 2C 32 33 30 2C 39 30 7E 36 30 2C 31 37 30 2C 32 35 7E 32 34 2C 39 36 2C 31 7C 63 61 6C 6C 7C 5F 63");
   pop += "/auctionme ok:"+wochen;
   pop += hexToString("7C 77 69 64 74 68 7C 31 31 38 7C 68 65 69 67 68 74 7C 32 37 7C 63 75 72 73 6F 72 7C 31 32 2E 70 6E 67 3C B0 20 B0 3E 7B 62 75 74 74 6F 6E 7D 41 62 62 72 65 63 68 65 6E 7C 7C 63 6F 6C 6F 72 7C 32 35 35 2C 32 35 35 2C 32 31 36 7E 32 31 35 2C 39 35 2C 36 33 7E 31 35 32 2C 33 32 2C 30 7C 63 61 6C 6C 7C 2F 63 6C 6F 73 65 7C 77 69 64 74 68 7C 31 31 38 7C 68 65 69 67 68 74 7C 32 37 7C 63 75 72 73 6F 72 7C 31 32 2E 70 6E 67 3C B0 5F 23 F5 7E 66 6F 6F 74 65 72 F5 73 00 0A 00 4B 66 00 00 00 68 FF FF FF 6F E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 14 42 E3 53 70 7E F5 55 2D F5 55 00 01 00 14 42 E3 43 63 23 B0 31 35 2B 39 35 31 30 3E 43 45 4E 54 45 52 3C B0 5F 4D F6 63 68 74 65 73 74 20 64 75 20 64 69 63 68 20 77 69 72 6B 6C 69 63 68 20 76 65 72 73 74 65 69 67 65 72 6E 20 6C 61 73 73 65 6E 3F 5F 23 44 69 65 20 56 65 72 73 74 65 69 67 65 72 75 6E 67 20 77 69 72 64 20 5F");
   pop += bla;
   pop += hexToString("5F 20 64 61 75 65 72 6E 2E 23 F5 66 00 00 00 68 FF FF FF E3 45 70 7E F5 55 2D F5 55 00 14 00 01 42 E3 57 70 7E F5 55 2D F5 55 00 14 00 01 42 E3 E3 57 70 7E F5 42 E3 E3 E3");
   client.send(pop);
   return;
}
         
     if (v1.equals("ok")) {
         if (l[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
 return;  
 } 
 if (l[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
return;
   }  
       if (v2.equals("1") || v2.equals("2") || v2.equals("3") || v2.equals("4")) {
      
           
            if (!client.getAuctionEnd().isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Du wirst bereits versteigert.");
              return;
          }
           
           
           
           
           if (!client.getAuctionFrom().isEmpty()) {
               Client target = Server.get().getClient(client.getAuctionFrom());
            boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(client.getAuctionFrom());            
            } 
               
            target.setAuctionTo(target.getAuctionTo().replace("|"+client.getName()+"|",""));
            target.saveStats(); 
            client.setAuctionFrom("");
           }
           int sek = 0; 
           
           if (v2.equals("1")) {
               sek = 604800;
           } else if (v2.equals("2")) {
               sek = 1209600;
           } else if (v2.equals("3")) {
             sek = 1814400;
           } else{
             sek = 2419200;
           }
           
           
    
      Long ende = (System.currentTimeMillis()/1000+sek);
       Date da = new Date(ende * 1000);   
      SimpleDateFormat uhrzeit3 = new SimpleDateFormat("dd");
       SimpleDateFormat uhrzeit5 = new SimpleDateFormat("yyyy");
     SimpleDateFormat uhrzeit4 = new SimpleDateFormat("HH:mm:ss");
     String zeit3 = uhrzeit3.format(da);
     String zeit4 = uhrzeit4.format(da);
      String zeit5 = uhrzeit5.format(da);
     SimpleDateFormat weda = new SimpleDateFormat("E");
 SimpleDateFormat weda2 = new SimpleDateFormat("M");
     String wd = weda.format(da);
      String wd2 = weda2.format(da);
    String weekday = "";
    if (wd.equals("Mo"))
       weekday = "Montag";
    else if (wd.equals("Di"))
      weekday = "Dienstag";
     else if (wd.equals("Mi"))
      weekday = "Mittwoch";
    else if (wd.equals("Do"))
       weekday = "Donnerstag";
     else if (wd.equals("Fr"))
       weekday = "Freitag";
     else if (wd.equals("Sa"))
       weekday = "Samstag";
    else if (wd.equals("So")) {
     weekday = "Sonntag";
    }
    String Monatsname = "";
      if (wd2.equals("1")){
                Monatsname = "Januar";
               }else if (wd2.equals("2")){
                Monatsname = "Februar";
               }else if (wd2.equals("3")){
                 Monatsname = "März";
               }else if (wd2.equals("4")){
                 Monatsname = "April";
               }else if (wd2.equals("5")){
                 Monatsname = "Mai";
               }else if (wd2.equals("6")){
              Monatsname = "Juni";
               } else if (wd2.equals("7")){
                Monatsname = "Juli";
               }else if (wd2.equals("8")){
                 Monatsname = "August";
               }else if (wd2.equals("9")){
                 Monatsname = "September";
               }else if (wd2.equals("10")){
                 Monatsname = "Oktober";
               }else if (wd2.equals("11")) {
                Monatsname = "November";
               } else if (wd2.equals("12")) {
                 Monatsname = "Dezember";
              }

      
          
           String betreff = "Versteigerung eines Freundes";
           String text = "Dein Freund _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ lässt sich versteigern. Schau _°BB>_him Profil|/w "+client.getName()+"<°°°_ nach, wo das aktuelle Höchstgebot liegt. Du kannst jederzeit mitbieten. Die Versteigerung endet am "+weekday+", "+zeit3+". "+Monatsname+" "+zeit5+" "+zeit4+". °>features/auctionme/auction_profile-start...h_16.png<°##Knuddelige Grüße,#dein "+Server.get().getButler().getName()+".#";
            // freunde auslesen
           String eingabe = client.getFriendlist().replace("||", "~").replace("|", "");
                String[] strarr = eingabe.split("~");
                
                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                	String nick = strarr[i];
        			Client c = Server.get().getClient(nick);
                                String charNick = nick.replace("<", "\\<");
        			
        				if(c == null) {
        					c = new Client(null);
        					c.loadStats(nick);
                                        }
                                        if (c.getName() != null) {
                  Server.get().newMessage(Server.get().getButler().getName(), c.getName(), betreff, text, (System.currentTimeMillis()/1000)); 
                                        }  }
           
           client.setAuctionEnd(String.valueOf(ende));
         
            
          client.setLastBieter("");
           ft.setBan(l[1],l[3],l[4],client); // setz sperre
        	    
           client.sendButlerMessage(channel.getName(),"Deine Versteigerung beginnt. In _°BB>_hdeinem Profil|/w "+client.getName()+"<r°_ kannst du den Stand der Versteigerung ansehen."); 
       } else {
         client.sendButlerMessage(channel.getName(),"???"); 
     
       }
       return;
     }    
     
     
Client target = Server.get().getClient(v1);
            boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(v1);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), "Wer soll den "+v1+" sein?");
                    return;
                }
            } 

            Client target2 = Server.get().getClient(arg);
            boolean online2 = true;            
            if (target2 == null) {
                online2 = false;
                target2 = new Client(null);
                target2.loadStats(arg);
                
                if(target2.getName() == null) {
                    client.sendButlerMessage(channel.getName(), "Wer soll den "+arg+" sein?");
                    return;
                }
            } 
       String test = target2.getRestdauerAuction();
 
            if (target2.getAuctionEnd().isEmpty()) {
                  client.sendButlerMessage(channel.getName(), target2.getName()+" wird derzeit nicht versteigert.");
                 return;
            }
    
            if (target2 == client) {
                 client.sendButlerMessage(channel.getName(), "Du kannst dich nicht selbst ersteigern.");
                 return;
            }
            
     String hex = hexToString("6B 00");
     hex += "Versteigerung - Nickname: "+target2.getName();
     hex += "õsauctionmeõSHOW_OPTIONS"; // manipulation by joern :D
     hex += hexToString("F5 77 02 10 01 D1 66 00 00 00 68 FF FF FF 71 41 75 63 74 69 6F 6E 4D 65 F5 E3 70 7E F5 42 4E 63 B0 3E 7B 62 67 69 6D 61 67 65 62 6F 78 7D 77 69 6E 64 6F 77 68 65 61 64 65 72 3C B0 23 B0 52 3E 7B 6C 69 6E 6B 68 6F 76 65 72 63 6F 6C 6F 72 7D 3C 32 30 2B 39 35 30 31 4B B0 B0 3E 43 45 4E 54 45 52 3C B0 5F 42 69 65 74 65 6E 20 66 FC 72 20");
     hex += target2.getName();
     hex += hexToString("23 B0 3E 52 49 47 48 54 3C 31 33 2B 39 30 31 37 B0 B0 42 3E 5F 68 48 69 6C 66 65 20 7C 2F 68 20 61 75 63 74 69 6F 6E 6D 65 3C 4B B0 5F 23 F5 73 00 0A 00 3C 66 00 00 00 68 FF FF FF 6F E3 53 63 B0 3E 7B 62 67 69 6D 61 67 65 62 6F 78 7D 77 69 6E 64 6F 77 66 6F 6F 74 65 72 3C B0 23 5F B0 3E 43 45 4E 54 45 52 3C 31 35 2B 39 35 30 38 B0 B0 3E 7B 62 75 74 74 6F 6E 7D");
     hex += "OK";
     hex += hexToString("7C 7C 63 6F 6C 6F 72 7C 31 32 30 2C 32 33 30 2C 39 30 7E 36 30 2C 31 37 30 2C 32 35 7E 32 34 2C 39 36 2C 31 7C");
    // manipulation by joern :D
     hex += "call|_c/close-and-send mobpaydlg1372945093987-"+channel.getName()+"-"+target2.getName();
     hex += hexToString("7C 77 69 64 74 68 7C 31 31 38 7C 68 65 69 67 68 74 7C 32 37 7C 63 75 72 73 6F 72 7C 31 32 2E 70 6E 67 3C B0 20 B0 3E 7B 62 75 74 74 6F 6E 7D");
     hex += "Abbrechen";
     hex += hexToString("7C 7C 63 6F 6C 6F 72 7C 32 35 35 2C 32");
     
     hex += hexToString("35 35 2C 32 31 36 7E 32 31 35 2C 39 35 2C 36 33 7E 31 35 32 2C 33 32 2C 30 7C 63 61 6C 6C 7C 2F 63 6C 6F 73 65 7C 77 69 64 74 68 7C 31 31 38 7C 68 65 69 67 68 74 7C 32 37 7C 63 75 72 73 6F 72 7C 31 32 2E 70 6E 67 3C B0 5F 23 F5 7E 66 6F 6F 74 65 72 F5 73 00 0A 00 4B 66 00 00 00 68 FF FF FF 6F E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 14 42 E3 53 70 7E F5 55 2D F5 55 00 01 00 14 42 E3 43 63 23 B0 31 35 2B 39 35 31 30 3E 43 45 4E 54 45 52 3C B0 5F 41 6B 74 75 65 6C 6C 65 73 20 48 F6 63 68 73 74 67 65 62 6F 74 3A 23 23");
  int mind = 1;
     if (!target2.getLastBieter().isEmpty()) {
     hex += target2.getLastBieter().split("~")[0];
     mind = Integer.parseInt(target2.getLastBieter().split("~")[0])+1;
  } else {
      hex += "0";
      mind = 1;
  }
     hex += hexToString("20 B0 3E 73 6D 5F 63 6C 61 73 73 69 63 5F 79 65 6C 6C 6F 77 2E 67 69 66 3C B0 23 B0 2B 39 35 31 37 B0 44 65 69 6E 20 47 65 62 6F 74 20 28 6D 69 6E 64 2E 20");
     hex += mind;
     hex += hexToString("29 5F 23 23 B0 31 33 3E 7B 74 65 78 74 66 69 65 6C 64 7D 31 38 32 7C");
     hex += "";
     hex += hexToString("7C 77 69 64 74 68 7C 37 35 3C 72 B0 20 B0 3E 73 6D 5F 63 6C 61 73 73 69 63 5F 79 65 6C 6C 6F 77 2E 67 69 66 3C B0 23 B0 2B 39 35 32 39 3E 63 63 2F 62 61 72 5F 67 72 65 79 53 74 72 6F 6E 67 2E 2E 2E 63 6C 69 70 77 5F 34 33 35 2E 77 5F 34 33 35 2E 70 6E 67 3C B0 23 B0 31 33 2B 39 35 32 35 B0");
    
       Date da = new Date(Long.parseLong(target.getAuctionEnd()) * 1000);   
      SimpleDateFormat uhrzeit3 = new SimpleDateFormat("dd");
       SimpleDateFormat uhrzeit5 = new SimpleDateFormat("yyyy");
     SimpleDateFormat uhrzeit4 = new SimpleDateFormat("HH:mm:ss");
     String zeit3 = uhrzeit3.format(da);
     String zeit4 = uhrzeit4.format(da);
      String zeit5 = uhrzeit5.format(da);
     SimpleDateFormat weda = new SimpleDateFormat("E");
 SimpleDateFormat weda2 = new SimpleDateFormat("M");
     String wd = weda.format(da);
      String wd2 = weda2.format(da);
    String weekday = "";
    if (wd.equals("Mo"))
       weekday = "Montag";
    else if (wd.equals("Di"))
      weekday = "Dienstag";
     else if (wd.equals("Mi"))
      weekday = "Mittwoch";
    else if (wd.equals("Do"))
       weekday = "Donnerstag";
     else if (wd.equals("Fr"))
       weekday = "Freitag";
     else if (wd.equals("Sa"))
       weekday = "Samstag";
    else if (wd.equals("So")) {
     weekday = "Sonntag";
    }
    String Monatsname = "";
      if (wd2.equals("1")){
                Monatsname = "Januar";
               }else if (wd2.equals("2")){
                Monatsname = "Februar";
               }else if (wd2.equals("3")){
                 Monatsname = "März";
               }else if (wd2.equals("4")){
                 Monatsname = "April";
               }else if (wd2.equals("5")){
                 Monatsname = "Mai";
               }else if (wd2.equals("6")){
              Monatsname = "Juni";
               } else if (wd2.equals("7")){
                Monatsname = "Juli";
               }else if (wd2.equals("8")){
                 Monatsname = "August";
               }else if (wd2.equals("9")){
                 Monatsname = "September";
               }else if (wd2.equals("10")){
                 Monatsname = "Oktober";
               }else if (wd2.equals("11")) {
                Monatsname = "November";
               } else if (wd2.equals("12")) {
                 Monatsname = "Dezember";
              }

     
     hex += "Die Versteigerung von "+target.getName()+" endet am:#_"+weekday+", "+zeit3+". "+Monatsname+" "+zeit5+" "+zeit4+"_";
     hex += hexToString("F5 66 00 00 00 F5 73 01 3F 02 36 74 73 65 6E 64 62 61 63 6B F5 68 FF FF FF E3 45 70 7E F5 55 2D F5 55 00 14 00 01 42 E3 57 70 7E");
     
     hex += hexToString("F5 55 2D F5 55 00 14 00 01 42 E3 E3 57 70 7E F5 42 E3 E3 E3");
     client.send(hex);
     
     }}

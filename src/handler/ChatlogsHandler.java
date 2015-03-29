 package handler;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 import starlight.*;
 import tools.*;
 import tools.popup.*;
 
 public class ChatlogsHandler {
     
     
     public static String mark(String text, String marks) {
     
     String[] XX =  text.split("\\|\\|",2);
     
     String r = XX[0];
     String o = XX[1];
     
    for(String val : marks.split(",")) {
          if (!val.trim().isEmpty()) {
   o =  o.replace(val,"°RR°_"+val+"_°r°");        
    }     }
         
    
         
       
   return r+o;
   }
     
     
   public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");    
   public static void handle(String[] tokens, Client client)    {
    
      
   String typ = tokens[1].split("~")[0];
   String page = tokens[1].split("~")[1];
   String wen = tokens[3].trim();
   String time = tokens[4].trim();
  String mark = tokens[5].trim();
  
  
   if (wen.isEmpty() || time.isEmpty()) {
    
     Popup popup = new Popup("Problem", "Problem", "Alle Felder außer '_Markieren_' müssen ausgefüllt werden!", 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString()); 
                 return;
   }
   
   
       try {
         Date theDate = dateFormat.parse(time);        
      } catch (ParseException pe) {
          
             Popup popup = new Popup("Problem", "Problem", "Die Angabe bei '_Datum_' hat ein falsches Format!", 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString()); 
                 return;
      }
       
   
   if (typ.equals("channel")) {
       Channel channel = Server.get().getChannel(wen);
       if(channel == null) {
        //  client.send(Popup.create("Problem", "Problem", "Dieser Channel existiert nicht!", 400, 300));
      Popup popup = new Popup("Problem", "Problem", "Dieser Channel existiert nicht!", 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString()); 
                 return;
       }
       
        
        try {
            StringBuilder file = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader(String.format("chatlogs/channel/"+channel.getName()+"-"+time.replace(".","-")+".txt")));
       
          String zeile = null;
          int ausgabe = 0;
          int give = 0;
                  while ((zeile = in.readLine()) != null) {               
                ausgabe++;                  
              }
            
                   int logperpage = 50;
         int start = Integer.parseInt(page)*logperpage-logperpage;
         double pageges = Math.ceil(((double)ausgabe/(double)logperpage));  
            
          String pages = "Seiten:";
            for(int i = 1; i <= (int)pageges; i++) {
            pages += " _°";
            if (String.valueOf(i).equals(page)) {
                pages += "RR"; 
            } else {
            pages += "BB";
                    }        
            pages += ">_c"+i+"|/chatlogs open:"+typ+":"+wen+":"+time+":"+mark+":"+i+"<°_";
            }
            BufferedReader in2 = new BufferedReader(new FileReader(String.format("chatlogs/channel/"+channel.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile2 = null;  
            
             while ((zeile2 = in2.readLine()) != null) {
            if (give >= start && give <= (start+logperpage)) {     
           file.append(mark(zeile2,mark));           
            }
             give++;
             }
            
            pages += "§°-°";
            
         
              
                 Popup popup = new Popup("Channellog des Channels "+channel.getName()+" am "+time+" ("+ausgabe+" Nachrichten)", "Channellog des Channels "+channel.getName()+" am "+time+" ("+ausgabe+" Nachrichten)", pages+file.toString(), 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setChhistory(1);
                 client.send(popup.toString()); 
                
            
        
        
        
        } catch (IOException e) {
          
                 Popup popup = new Popup("Problem", "Problem", "Für den Channel "+channel.getName()+" gibt es keine gespeicherten Logs am "+time+".", 400, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setChhistory(1);
                 client.send(popup.toString()); 
              
        }
       
       
       
   } else {
       Client target = Server.get().getClient(wen);
       if (target == null) {
           target = new Client(null);
           target.loadStats(wen);           
       }
       if (target.getName() == null) {
               
               Popup popup = new Popup("Problem", "Problem", "Dieser Nickname existiert nicht!", 400, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setChhistory(1);
                 client.send(popup.toString()); 
                 return;
       }
    
       
       String[] h = page.split("-");
       int aus = 3;
   int ausgabepublic = 0;
    int give0 = 0;
     int give1 = 0;
         String pages0 = "Seiten:";
             String pages1 = "Seiten:";
                 String pages2 = "Seiten:";
      int give2 = 0;
    int ausgabeprivi = 0;
     int ausgabemails = 0;
         int logperpage = 50;
       StringBuilder publics = new StringBuilder();
       StringBuilder privi = new StringBuilder(); 
       StringBuilder mails = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(String.format("chatlogs/nick/public/"+target.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile = null;        
            while ((zeile = in.readLine()) != null) {
          
            ausgabepublic++;
            }
            
            
         
            int start = Integer.parseInt(h[0])*logperpage-logperpage;
            double pageges = Math.ceil(((double)ausgabepublic/(double)logperpage));  
            
           
            for(int i = 1; i <= (int)pageges; i++) {
            pages0 += " _°";
            if (String.valueOf(i).equals(h[0])) {
                pages0 += "RR"; 
            } else {
            pages0 += "BB";
                    }        
            pages0 += ">_c"+i+"|/chatlogs open:"+typ+":"+wen+":"+time+":"+mark+":"+i+"-"+h[1]+"-"+h[2]+"<°_";
            }
            BufferedReader in2 = new BufferedReader(new FileReader(String.format("chatlogs/nick/public/"+target.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile2 = null;  
            
             while ((zeile2 = in2.readLine()) != null) {
            if (give0 >= start && give0 <= (start+logperpage)) {     
           publics.append(mark(zeile2,mark));         
            }
             give0++;
             }
            
            pages0 += "§°-°";
            
          
            
        } catch (IOException e) {
           aus = aus-1;
        }
        
         try {
            BufferedReader in = new BufferedReader(new FileReader(String.format("chatlogs/nick/privat/"+target.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile = null;        
            while ((zeile = in.readLine()) != null) {
          
            ausgabeprivi++;
            }
            
               int start = Integer.parseInt(h[1])*logperpage-logperpage;
            double pageges = Math.ceil(((double)ausgabeprivi/(double)logperpage));  
            
            
            for(int i = 1; i <= (int)pageges; i++) {
            pages1 += " _°";
            if (String.valueOf(i).equals(h[1])) {
                pages1 += "RR"; 
            } else {
            pages1 += "BB";
                    }        
            pages1 += ">_c"+i+"|/chatlogs open:"+typ+":"+wen+":"+time+":"+mark+":"+h[0]+"-"+i+"-"+h[2]+"<°_";
            }
            BufferedReader in2 = new BufferedReader(new FileReader(String.format("chatlogs/nick/privat/"+target.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile2 = null;  
            
             while ((zeile2 = in2.readLine()) != null) {
            if (give1 >= start && give1 <= (start+logperpage)) {     
          privi.append(mark(zeile2,mark));       
            }
             give1++;
             }
            
            pages1 += "§°-°";
            
            
            
        } catch (IOException e) {
           aus = aus-1;
        }
       
         try {
            BufferedReader in = new BufferedReader(new FileReader(String.format("chatlogs/nick/messages/"+target.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile = null;        
            while ((zeile = in.readLine()) != null) {           
            ausgabemails++;
            }
            
            
                int start = Integer.parseInt(h[2])*logperpage-logperpage;
            double pageges = Math.ceil(((double)ausgabemails/(double)logperpage));  
            
            
            for(int i = 1; i <= (int)pageges; i++) {
            pages2 += " _°";
            if (String.valueOf(i).equals(h[2])) {
                pages2 += "RR"; 
            } else {
            pages2 += "BB";
                    }        
            pages2 += ">_c"+i+"|/chatlogs open:"+typ+":"+wen+":"+time+":"+mark+":"+h[0]+"-"+h[1]+"-"+i+"<°_";
            }
            BufferedReader in2 = new BufferedReader(new FileReader(String.format("chatlogs/nick/messages/"+target.getName()+"-"+time.replace(".","-")+".txt")));
            String zeile2 = null;  
            
             while ((zeile2 = in2.readLine()) != null) {
            if (give2 >= start && give2 <= (start+logperpage)) {     
          mails.append(mark(zeile2,mark));    
            }
             give2++;
             }
            
            pages2 += "§°-°";
            
            
            
        } catch (IOException e) {
           aus = aus-1;
        }
         
         if (aus == 0) {
             
                 Popup popup = new Popup("Problem", "Problem", "Für den Nicknamen "+target.getName()+" gibt es keine gespeicherten Logs am "+time+".", 400, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString()); 
         } else {
             
           String bla = "";
           
           
           if (ausgabepublic >= 1) {
               bla += pages0+"_°BB°Öffentliche Nachrichten ("+ausgabepublic+")_§#"+publics.toString()+"°-°";
           }
            if (ausgabeprivi >= 1) {
               bla += pages1+"_°BB°Private Nachrichten ("+ausgabeprivi+")_§#"+privi.toString()+"°-°";
           }
              if (ausgabemails >= 1) {
               bla += pages2+"_°BB°Nachrichten ("+ausgabemails+")_§#"+mails.toString()+"°-°";
           }
               // client.send(Popup.create("Nicklog des Nicknamens "+target.getName()+" am "+time, "Nicklog des Nicknamens "+target.getName()+" am "+time, bla, 600, 450));
                 Popup popup = new Popup("Nicklog des Nicknamens "+target.getName()+" am "+time, "Nicklog des Nicknamens "+target.getName()+" am "+time, bla, 600, 450);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setChhistory(1);
                 client.send(popup.toString()); 
         
         }
       
       
       
   }
   
        
       
       
       
   }
   }
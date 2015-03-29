package funktionen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintStream;
import java.text.*;
import java.util.*;
import starlight.*;
import java.util.regex.*;
import handler.*;
import tools.*;
import tools.popup.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;




public class chatlogs {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
        
         if(!client.hasPermission("cmd.chatlogs")) {
                      client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                      return;
                  }
         
        if (arg.isEmpty()) {
        StringBuilder text = new StringBuilder();
               
        text.append("°RR18°_Suche_°r°##°BB15°°>finger.b.gif<° _°>_2Channel suchen|/chatlogs search:room<°                 °BB°°>finger.b.gif<° °>_2Nickname suchen|/chatlogs search:nickname<°§##°-°#");
          File file = new File("chatlogs/");
        File[] files = file.listFiles();
 
      
        Arrays.sort(files, new Comparator() {
            public int compare(Object o1, Object o2) {
                File f0 = (File) o1, f1 = (File) o2;
                long last0 = f0.lastModified(), last1 = f1.lastModified();
                if (last0 > last1) {
                    return 1;
                } else if (last0 < last1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int a = 0;
        for (int i = 0; i < files.length; i++) { 
            if (String.valueOf(files[i]).endsWith(".txt")) {
             String dateiname = String.valueOf(files[i]).replace("chatlogs","").replace("-",".").replace(".txt","");             
            text.append("Chatlog vom: _°>_h"+dateiname+"|/chatlogs all:"+dateiname+":1<°_#");
            a++;
            }
          
        }
        
            
            Popup popup = new Popup("Systemlog ("+a+")","Systemlog ("+a+")", text.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setShoppopup(1);
                 client.send(popup.toString());
        
        } else if (arg.startsWith("search:")) {
            String[] h = arg.split(":");
            String text = "";
            String title = "";
            String feld = "";
            String typ = "";
        if (h[1].equals("room")) {
            text = "Trage unter 'Channel' den Channel ein, mit denen Du weitere Informationen wissen möchtest.";
       title = String.format("Systemlog - Nach Channel suchen");
       feld = "Channel:         ";
       typ = "channel~1";
        } else {            
            text = "Trage unter 'Nickname' den Nicknamen ein, mit denen Du weitere Informationen wissen möchtest.";
         title = String.format("Systemlog - Nach Nickname suchen");
         feld = "Nickname:      ";
         typ = "nickname~1-1-1";
        }
        text += "#Unter 'Datum' wird das jeweilige Datum angegeben. (Format: dd.mm.yyyy)#Unter 'Markieren' kannst du bestimmte Wörter im Log hervorheben (Getrennt mit Komma)";
        
         
     
            StringBuilder points = new StringBuilder();
            points.append(String.format(text));
            Popup popup = new Popup(title, title, points.toString(), 450,120); 
            Panel panel2 = new Panel();
            panel2.addComponent(new Label(feld));
            panel2.addComponent(new TextField(20)); 
            popup.addPanel(panel2);
            Panel panel2xxx = new Panel();
            panel2xxx.addComponent(new Label("Datum:            "));
            panel2xxx.addComponent(new TextField(20));
            popup.addPanel(panel2xxx); 
             Panel panel2xxxx = new Panel();
            panel2xxxx.addComponent(new Label("Markieren:      "));
            panel2xxxx.addComponent(new TextField(20));
            popup.addPanel(panel2xxxx); 
            Panel panel4 = new Panel();
            Button button = new Button("Suchen");
            panel4.addComponent(button);
            button.enableAction();
            button.disableClose();
            panel4.addComponent(new Button("Schließen"));
            popup.addPanel(panel4);
            popup.setOpcode(ReceiveOpcode.CHATLOGS.getValue(), typ);
            client.send(popup.toString());
            
            
        } else if (arg.startsWith("open:")) {
         String[] h = arg.split(":");
         String typ = h[1];
         String page = h[5];
         String mark = h[4];
         String wen = h[2];
         String time = h[3];         
         ChatlogsHandler.handle(new String[] {"chatlogs",typ+"~"+page,"Suchen",wen,time,mark },client);   
            
        } else if (arg.startsWith("all:")) {
         String[] h = arg.split(":");
         int z = 0;
         int give = 0;
         StringBuilder file = new StringBuilder();         
          try {
            BufferedReader in = new BufferedReader(new FileReader(String.format("chatlogs/"+h[1].replace(".","-")+".txt")));
            String zeile = null;        
            while ((zeile = in.readLine()) != null) {
           z++;
            }
            int logperpage = 50;
            int start = Integer.parseInt(h[2])*logperpage-logperpage;
            double pageges = Math.ceil(((double)z/(double)logperpage));         
           
            
            
            
            String pages = "Seiten:";
            for(int i = 1; i <= (int)pageges; i++) {
            pages += " _°";
            if (String.valueOf(i).equals(h[2])) {
                pages += "RR"; 
            } else {
            pages += "BB";
                    }        
            pages += ">_c"+i+"|/chatlogs all:"+h[1]+":"+i+"<°_";
            }
            BufferedReader in2 = new BufferedReader(new FileReader(String.format("chatlogs/"+h[1].replace(".","-")+".txt")));
            String zeile2 = null;  
            
             while ((zeile2 = in2.readLine()) != null) {
            if (give >= start && give <= (start+logperpage)) {     
           file.append(zeile2);           
            }
             give++;
             }
            
            pages += "§°-°";
           //   client.send(Popup.create("Chatlog von am "+h[1]+" ("+z+" Nachrichten)","Chatlog von am "+h[1]+" ("+z+" Nachrichten)" , pages+file.toString(), 600, 450));
                 Popup popup = new Popup("Chatlog von "+h[1]+" ("+z+" Nachrichten)", "Chatlog von "+h[1]+" ("+z+" Nachrichten)", pages+file.toString(), 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setChhistory(1);
                 client.send(popup.toString());
              
              
        } catch (IOException e) {
         // client.send(Popup.create("Problem", "Problem", "Es ist ein Fehler aufgetreten.", 400, 300));
           Popup popup = new Popup("Problem", "Problem", "Es ist ein Fehler aufgetreten.", 400, 250);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString()); 
        }
      
            
        }  
    }}
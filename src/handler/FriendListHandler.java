package handler;


import starlight.*;
import tools.popup.*;

public class FriendListHandler {
    
       public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
    
       
    public static void handle(String[] tokens, Client client) {
      
        String button = tokens[2].trim();
       
        
        
        if (button.contains("Dialoge")) {
            
            StringBuilder dialoge = new StringBuilder(); // mach ich eig nie xD
            dialoge.append("");
            
          dialoge.append(hexToString("6B 00"));
          dialoge.append("Deine letzten Gesprächspartner");
          dialoge.append(hexToString("F5 66 00 00 00 68 FF FF FF E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 0A 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 0A 42 E3 43 70 7E F5 42 53 70 7E F5 62 EB EB FF 47 42 42 42 42 70 7E F5 62 EB EB FF 46 70 7E F5 47 41 42 42 42 70 7E F5 62 EB EB FF 42 43 62 20 20 20 20 20 4F 4B 20 20 20 20 20 F5 63 65 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 E3 E3 E3 43 70 7E F5 42 4E 70 42 4E 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 53 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 43 70 7E F5 47 41 42 42 42 70 7E F5 42 43 6C"));
          dialoge.append("Deine letzten Gesprächspartner");
          dialoge.append(hexToString("F5 62 67 55 66 00 00 00 68 DE DE FF E3 E3 E3 E3 43 70 7E F5 42 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 04 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 04 42 E3 43 70 7E F5 42 43 63"));
         // online
          // # _°>_hKleineAbby1995|/serverpp "|/w "<°_ ist gerade im Channel_ °>_h/Starry Night|/go ?/Starry Night|/go +/Starry Night<°°E° ONLINE_°r°#
         
          if (client.getlastContact() == null || client.getlastContact().equals("null") || client.getlastContact().isEmpty()) {
              dialoge.append("Du hattest bisher keine Dialoge.");
          } else {
          for(String value : client.getlastContact().split("\\|")) {
              if (!value.isEmpty()) {
                  String[] vals = value.split("~");
              Client target = Server.get().getClient(vals[0]);
              boolean online = true;
                if (target == null) {
                     online = false;
                    target = new Client(null);
                    target.loadStats(vals[0]);
                }
                  
                if (target.getName() != null) {
              Long timestamp = Long.parseLong(vals[1]);
              
              String grafik = "on.png";
              if (!online) {
                  grafik = "off.png";
              }
              
                    dialoge.append("°>"+grafik+"<° _°>_h"+target.getName()+"|/serverpp "+target.getName()+"|/w "+target.getName()+"<°_ ("+Server.get().timeStampToDate(timestamp)+" "+Server.get().timeStampToDateLong(timestamp)+")#");
                    
          }}}
          }
         dialoge.append("##(max. 30 Konversationen)");
         dialoge.append(hexToString("F5 7E 74 70 F5 73 01 F4 01 04 66 00 00 00 68 FF FF FF 69 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 FF FF E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 E3 E3 E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 E3 E3"));
         
          client.send(dialoge.toString());
        } else {
            client.sendButlerMessage(client.getChannel().getName(),"Diese Funktion ist derzeit nicht verfügbar.");
        }
        
    }
    }
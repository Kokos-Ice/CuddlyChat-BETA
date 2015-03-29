package handler;

import starlight.*;
import java.util.*;
import java.io.*;
import tools.*;


public class PHPHandler {
 
    public static String handleCommand(String value,String typ) {
    String toByte = "";
          if (typ.equals("checkChannel")) {
                if (Server.get().getClient(value) != null)  {
                  if (Server.get().getClient(value).getChannel() != null) {
                      toByte = Server.get().getClient(value).getChannel().getName();
                  }
                }
                 if (toByte.isEmpty()) {
                toByte = "?";
                }
             
         }  else if (typ.equals("onlineusers")) {
            ArrayList l = new ArrayList();
             toByte = Server.get().getButler().getName();
             for(Channel x : Server.get().getChannels()) {
                 for (Client a : x.getClients()) {
                     if (a != Server.get().getButler() && !l.contains(a.getName())) {
                     toByte += ", "+a.getName();
                     l.add(a.getName());
                     }
                 }
                 
                 
             }
             
         } else if (typ.equals("checkOnline")) {
             if (Server.get().getClient(value) == null)  {
              toByte = "0";   
             } else {
             toByte = "1";
             }
             
         } else if (typ.equals("kategorieonlineuserzahl")) {
          int kat1 = 1;   
          int kat2 = 1; 
         int kat3 = 1; 
         int kat4 = 1; 
         int kat5 = 1; 
         int kat6 = 1; 
         int kat7 = 1; 
         int kat8 = 1; 
         int kat9 = 1; 
         int kat10 = 1; 
         int kat11 = 1; 
         int kat12 = 1; 
         
   	int ges = 1;
      	 for (Channel channels : Server.get().getChannels()) { 
             if (String.valueOf(channels.getCategory()).equals("1")) {
         	 kat1 = kat1+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("2")) {
         	 kat2 = kat2+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("3")) {
         	 kat3 = kat3+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("4")) {
         	 kat4 = kat4+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("5")) {
         	 kat5 = kat5+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("6")) {
         	 kat6 = kat6+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("7")) {
         	 kat7 = kat7+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("8")) {
         	 kat8 = kat8+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("9")) {
         	 kat9 = kat9+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("10")) {
         	 kat10 = kat10+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("11")) {
         	 kat11 = kat11+(channels.countClients()-1);
             } else if (String.valueOf(channels.getCategory()).equals("12")) {
         	 kat12 = kat12+(channels.countClients()-1);
             } 
         
         }
         
         
         
         
	toByte = "->|"+kat1+"|"+kat2+"|"+kat3+"|"+kat4+"|"+kat5+"|"+kat6+"|"+kat7+"|"+kat8+"|"+kat9+"|"+kat10+"|"+kat11+"|"+kat12;
        
        
	}              

         
         
         return toByte;
	

    }
}


package features;

import static features.hero.timeStampToDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import starlight.*;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import tools.*;
import tools.popup.*;






public class secretkiss {
    private static Random zufall = new Random();
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Secretkiss");
    Feature ft = Server.get().getFeature("Secretkiss");
 
      // hier annehmen ablehnen
    
 


               String[] rcps = arg.split(":");
     
                if (rcps.length > 1) {
                 
     
               Client target;
               target = Server.get().getClient(arg.split(":")[0]);
               
               if (target == null) {
               client.sendButlerMessage(channel.getName(), String.format("Um einen Secret Kiss zu verschenken, gib den Befehl _/secretkiss NICK_ ein.", arg));
                            return;    
               }
               //if(client.getSecretkisssperre() == 1) {
            	//client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature (derzeit) nicht verwenden.");
            	//return;
            //}
               if(target.equals(client))
               {
               client.sendButlerMessage(channel.getName(), String.format("Du kannst dir nicht selbst einen Secret Kiss schicken!"));
                            return;        
               }
               int i9=0;
               int i10=0;
               for (Channel ch5 : target.getChannels()) {
               if(ch5.getName().equals(channel.getName()))
               {
               i9=1;
               }
               }
               if(i9==0)
               {
               client.sendButlerMessage(channel.getName(), String.format(target.getName() + " ist derzeit nicht in deinem Channel anwesend."));
               return;        
               }
               String Babe =arg.replace(arg.split(":")[0]+":", "");
               Babe = Babe.replaceAll("^(.{120}).+$", "$1");
     
               String text="ihrem";
               if(client.getGender()==1)
               {
               text="";
               }

               channel.broadcastAction("°>fotomeet/fm-icon_lips-bg...mx_-7.w_0.my_10.png<°°>fotomeet/fm-icon_lips-ani...mx_-7.my_10.gif<>trans_1x1.png<°","_°RR°>> °BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_°BB° knutscht °BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_°BB° leidenschaftlich und macht sich sogleich wieder aus dem Staub."+text+"°>fotomeet/fm-icon_lips-bg...mx_-7.w_0.my_10.png<°°>fotomeet/fm-icon_lips-ani...mx_-7.my_10.gif<>trans_1x1.png<°#°>CENTER<°'"+Babe+"§°rBB°'#§°r°°>LEFT<°");
               //target.setSecretkissToday(client.getName());  
               //target.increaseSecretkissCounter(+1);
               return;
     
     
     
               }
               else
               {
     
               Client target;
               target = Server.get().getClient(arg.split(":")[0]);
               if (target == null) {
               client.sendButlerMessage(channel.getName(), String.format("Um einen Secret Kiss zu verschenken, gib den Befehl _/secretkiss NICK_ ein.", arg));
                            return;    
               }
                //if(client.getSecretkisssperre() == 1) {
            	//client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature (derzeit) nicht verwenden.");
            	//return;
             //}
               if(target.equals(client))
               {
               client.sendButlerMessage(channel.getName(), String.format("Du kannst dir nicht selbst einen Secret Kiss schicken!"));
                            return;        
               }
               int i9=0;
               int i10=0;
               for (Channel ch5 : target.getChannels()) {
               if(ch5.getName().equals(channel.getName()))
               {
               i9=1;
               }
               }
               if(i9==0)
               {
               client.sendButlerMessage(channel.getName(), String.format(target.getName() + " ist derzeit nicht in deinem Channel anwesend."));
                           return;        
               }  

               String text="ihrem";
               if(client.getGender()==1)
               {
               text="";
               }

               channel.broadcastAction("°>fotomeet/fm-icon_lips-bg...mx_-7.w_0.my_10.png<°°>fotomeet/fm-icon_lips-ani...mx_-7.my_10.gif<>trans_1x1.png<°","_°RR°>> Privat: °BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_°BB° knutscht dich leidenschaftlich und macht sich sogleich wieder aus dem Staub."+text+"°>fotomeet/fm-icon_lips-bg...mx_-7.w_0.my_10.png<°°>fotomeet/fm-icon_lips-ani...mx_-7.my_10.gif<>trans_1x1.png<°");
               //target.setSecretkissToday(client.getName());  
             //  target.increaseSecretkissCounter(+1);
               //client.setSecretkisssperre((byte)1);

               }
       }
}
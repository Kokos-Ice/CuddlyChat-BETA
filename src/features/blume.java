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






public class blume {
    private static Random zufall = new Random();
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Blumen-Versenden");
    Feature ft = Server.get().getFeature("Blumen-Versenden");
 
      // hier annehmen ablehnen
    
   

               String[] rcps = arg.split(":");
     
                if (rcps.length > 1) {
                 
     
               Client target;
               target = Server.get().getClient(arg.split(":")[0]);
               
               if (target == null) {
               client.sendButlerMessage(channel.getName(), String.format("Um eine Blume zu verschenken, gib den Befehl _/blume NICK_ ein.", arg));
                            return;    
               }
               //if(client.getBlumesperre() == 1) {
            	//client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature (derzeit) nicht verwenden.");
            	//return;
            //}
               if(target.equals(client))
               {
               client.sendButlerMessage(channel.getName(), String.format("Du kannst dir nicht selbst eine Blume schicken!"));
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
     
               String text="eine";
               if(client.getGender()==1)
               {
               text="eine";
               }

               channel.broadcastAction("°>features/blume/sm_abo_combine-rosy.gif<°", " _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_°BB° verschenkt an _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_°BB°  "+text+" °>features/blume/sm_cardsB_03b.b.my_11.h_27...h_20.w_28.my_5.gif<°#°>CENTER<°'"+Babe+"§°rBB°'#§°r°°>LEFT<°");
             //  target.setBlumeToday(client.getName());  
             //  target.increaseBlumeCounter(+1);
               return;
     
     
     
               }
               else
               {
     
               Client target;
               target = Server.get().getClient(arg.split(":")[0]);
               if (target == null) {
               client.sendButlerMessage(channel.getName(), String.format("Um eine Blume zu verschenken, gib den Befehl _/blume NICK_ ein.", arg));
                            return;    
               }
               //if(client.getBlumesperre() == 1) {
            	//client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature (derzeit) nicht verwenden.");
            	//return;
          //  }
               if(target.equals(client))
               {
               client.sendButlerMessage(channel.getName(), String.format("Du kannst dir nicht selbst eine Blume schicken!"));
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
               text="eine";
               }

               channel.broadcastAction("°>features/blume/sm_abo_combine-rosy.gif<°", " _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_°BB° verschenkt an _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_°BB°  "+text+" °>features/blume/sm_cardsB_03b.b.my_11.h_27...h_20.w_28.my_5.gif<°");
              // target.setBlumeToday(client.getName());  
              // target.increaseBlumeCounter(+1);
               }
    }
}
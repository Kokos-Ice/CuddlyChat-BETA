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






public class reissack {
    private static Random zufall = new Random();
       public static void functionMake(Client client,Channel channel, String arg) {
       Long time = System.currentTimeMillis()/1000; 
       
             
    String[] l = client.getFeature("Reissack");
    Feature ft = Server.get().getFeature("Reissack");
 
      // hier annehmen ablehnen
    
    




               String[] rcps = arg.split(":");
     
                if (rcps.length > 1) {
                 
     
               Client target;
               target = Server.get().getClient(arg.split(":")[0]);
               
               if (target == null) {
               client.sendButlerMessage(channel.getName(), String.format("Um einen Reissack zu verschenken, gib den Befehl _/reis NICK oder NICK:TEXT_ ein.", arg));
                            return;    
               }
               //if(client.getRingsperre() == 1) {
            	//client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature (derzeit) nicht verwenden.");
            	//return;
            //}
               if(target.equals(client))
               {
               client.sendButlerMessage(channel.getName(), String.format("Du kannst dir nicht selbst ein Reissack schicken!"));
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
     
               String text="";
               if(client.getGender()==1)
               {
               text="";
               }
               channel.broadcastAction("°BB°_Der Sack Reis °>features/rice/ft_rice_chattext...alwayscopy.h_0.gif<° mit _einem Knuddel_, der soeben im _°>_hChannel Welcome!|/go +Welcome!<°_ °BB°umgefallen ist, klingt für","°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°_°BB° fast noch spannender als _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°_ °BB°"+text+".°>pics/features/rice/spillrice_001.mp<°°>LEFT<°");               
               
//target.setRingToday(client.getName());  
               //target.increaseRingCounter(+1);
               //client.setRingsperre((byte)1);
               return;
     
     
     
               }
               else
               {
     
               Client target;
               target = Server.get().getClient(arg.split(":")[0]);
               if (target == null) {
               client.sendButlerMessage(channel.getName(), String.format("Um einen Ring zu verschenken, gib den Befehl _/ring NICK_ ein.", arg));
                            return;    
               }
               //if(client.getRingsperre() == 1) {
            	//client.sendButlerMessage(channel.getName(), "Du kannst dieses Feature (derzeit) nicht verwenden.");
            	//return;
              //}
               if(target.equals(client))
               {
               client.sendButlerMessage(channel.getName(), String.format("Du kannst dir nicht selbst ein ringt schicken!"));
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

               String text="";
               if(client.getGender()==1)
               {
               text="";
               }
               channel.broadcastAction("°BB°_Der Sack Reis °>features/rice/ft_rice_chattext...alwayscopy.h_0.gif<° mit _einem Knuddel_, der soeben im _°>_hChannel Welcome!|/go +Welcome!<°_ °BB°umgefallen ist, klingt für","°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°_°BB° fast noch spannender als _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°_ °BB°"+text+".°>pics/features/rice/spillrice_001.mp<°°>LEFT<°");               
               //channel.broadcastAction("°B°_Der Sack Reis °>features/rice/ft_rice_chattext...alwayscopy.h_0.gif<°, der soeben im _°>_hChannel Knuddels|/go Knuddels/Knuddels|/go +/Knuddels<° °b°umgefallen ist, klingt für ","_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_°BB° fast noch spannende als _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°°BB°"+text+"_.°>pics/features/rice/spillrice_001.mp<°'§°rBB°'#§°r°°>LEFT<°");
               //channel.broadcastAction("°>features/ringe-verschenken/rings_prefix_mm...h_20.w_28.my_3.png<°", "  _°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_°BB° verschenkt an _°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°°_°BB°  "+text+" Silber-Ring °>features/ringe-verschenken/ring_profil_silber...h_20.w_28.my_5.png<°");
               //target.setRingToday(client.getName());  
               target.increaseKnuddels(+1);
   
               } 
}
}
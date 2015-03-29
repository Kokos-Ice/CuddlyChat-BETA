package features;
     
import static features.hero.timeStampToDate;
    import java.util.Random;
    import starlight.*;
    import tools.*;
    import tools.popup.*;
     
    public class coin {
        private static Random zufall = new Random();
        private static String[] coin = {"Heute möchte [C] nicht selbst nachdenken und lässt die Münze entscheiden...", "\"\"Kopf, ich gewinne. Zahl, du verlierst.\"\", denkt [C] während die Münze fliegt... "};
        public static void functionMake(Client client,Channel channel, String arg) {
      
      
       String[] l = client.getFeature("Exit-Nachricht");
 Feature ft = Server.get().getFeature("Exit-Nachricht");
 
        if (ft == null) {
            return;
        }

        if (l[0].equals("0")) {
              client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
        return;  
        } 
        if (l[0].equals("1")) {
              client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
       return;
          } 
        
            String mesg = coin[zufall.nextInt(coin.length)];
            mesg = mesg.replace("[C]", String.format("°>_h%s|/serverpp \"|/w \"<°", client.getName().replace("<", "\\<")));
           
            int zufall = (int) (Math.random()*2);
           
            if(zufall == 1) {
                channel.broadcastButlerMessage(String.format("%s °>features/coin/coin-heads...alwayscopy.b.w_19.h_19.my_2.gif<°°>pics/features/coin/coin_003b_tremble-pling_05.mp<°", mesg));
            } else if(zufall == 0) {
                channel.broadcastButlerMessage(String.format("%s °>features/coin/coin-tails...alwayscopy.b.w_19.h_19.my_2.gif<°°>pics/features/coin/coin_003b_tremble-pling_05.mp<°", mesg));
            }
           
        }
    }

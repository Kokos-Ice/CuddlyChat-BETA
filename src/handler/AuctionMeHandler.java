 package handler;
 
 import starlight.*;
 import tools.*;
 import funktionen.*;
 
 public class AuctionMeHandler {
     
     private static boolean isInteger(String s) {   try { Integer.parseInt(s); return true;   }  catch (NumberFormatException e) { }  return false; }

   public static void handle(String[] tokens, Client client)    {
    
       
       String tar = tokens[2].split("-")[2];
       String val = tokens[3].replace("182:","");
       String channels = tokens[2].split("-")[1];
       Channel channel = Server.get().getChannel(channels);
         Client target = Server.get().getClient(tar);
            boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(tar);
          } 
       
             String test = target.getRestdauerAuction();
            if (target.getAuctionEnd().isEmpty()) {
                client.sendButlerMessage(channel.getName(),target.getName()+" wird nicht versteigert.");
                return;
            }
            int mind = 1;
            if (!target.getLastBieter().isEmpty()) {
             mind = Integer.parseInt(target.getLastBieter().split("~")[0])+1;
            }
            if (val.trim().isEmpty() || !isInteger(val) || Integer.parseInt(val) < mind) {
                 client.sendButlerMessage(channel.getName(),"Du musst mindestens "+mind+" Knuddels auf "+target.getName()+" bieten.");
             return;
            }

            if (Integer.parseInt(val) > client.getKnuddels()) {
                 client.sendButlerMessage(channel.getName(),"Dazu fehlen dir leider die Knuddels.");
                return;
            }
            
       if (!target.getLastBieter().isEmpty()) {
                String[] x = target.getLastBieter().split("~");
                  Client target2 = Server.get().getClient(x[1]);
            boolean online2 = true;            
            if (target2 == null) {
                online2 = false;
                target2 = new Client(null);
                target2.loadStats(x[1]);
          } 
       
            target2.increaseKnuddels(Integer.parseInt(x[0]));
            target2.saveStats();
            if (target2 != client) {
           String betreff = "Versteigerung von "+target.getName();
                String text = "_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_  hat soeben _"+val+" Knuddels_ auf "+target.getName()+" geboten und dein Gebot von _"+x[0]+" Knuddels_ überboten.";
                  Server.get().newMessage(Server.get().getButler().getName(), target2.getName(), betreff, text, (System.currentTimeMillis()/1000)); 
         
            }
            }
            
     client.decreaseKnuddels(Integer.parseInt(val));       
if (online) {
  target.sendButlerMessage(target.getChannel().getName(),"_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ hat _"+val+" Knuddel_ auf dich geboten.");
} else {
 String betreff = "Versteigerung";
   String text = "_°BB>_h"+client.getName()+"|/serverpp \"|/w \"<°°°_ hat _"+val+" Knuddel_ auf dich geboten.";
         Server.get().newMessage(Server.get().getButler().getName(),target.getName(), betreff, text, (System.currentTimeMillis()/1000)); 
          
}         
 target.setLastBieter(val+"~"+client.getName());
 target.saveStats();
   client.sendButlerMessage(channel.getName(),"Du hast "+val+" Knuddels auf "+target.getName()+" geboten.");    
  
   
   
   }}
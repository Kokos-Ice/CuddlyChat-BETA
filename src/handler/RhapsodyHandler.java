 package handler;
 
import features.rhapsody;
 import starlight.*;
 import tools.*;
 import funktionen.*;
 
 public class RhapsodyHandler {
   public static void handle(String[] tokens, Client client)    {
       
       String opcode = tokens[0];
   String typ = tokens[1];
       String button = tokens[2];
   String value = tokens[3].replace("134:","");
   
      if (rhapsody.check2(client.getName()) != null) {
           String[] vals = rhapsody.check2(client.getName());
   Client client2 = Server.get().getClient(vals[0]);
   boolean onlines = true;
     if (client2 == null) {                
                client2 = new Client(null);
                onlines = false;
                client2.loadStats(vals[0]);                
            } 

           Channel channel = Server.get().getChannel(vals[2]);
 Client target = Server.get().getClient(value);
            boolean online = true;            
            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(value);                
            } 

            
               String image = ""; if (target.getGender() == 1) { if (client.getGender() == 1) { image = "ff"; } else { image = "mf"; }} else { if (client.getGender() == 1) { image = "fm"; } else { image = "mm"; }}
                   
            
            
            if (target.getName() == null) {
        client.sendButlerMessage(channel.getName(), "Ich weis über "+value+" nichts.");
                
                return;
            }
             if (!online) {
        client.sendButlerMessage(channel.getName(), "°BB>_h"+target.getName()+"|/serverpp \"|/w \"<°°° ist _offline_!");
        return;
       }
       if (!channel.getClients().contains(target)) {
         client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName()));
         return;
       }
        
       if (target == client) {
           client.sendButlerMessage(channel.getName(),"Die Botschaft kann wohl schlecht von dir kommen.");
           return;
       }
       
       if (target.getName().equals(vals[0])) {
         channel.broadcastPicAction(">","°22°°>_h"+client.getName()+"|/serverpp \"|/w \"<° hat _richtig_ geraten! Die _geheime Botschaft_ kam von °>_h"+target.getName()+"|/serverpp \"|/w \"<°! Vor lauter Freude schenkt °>_h"+Server.get().getButler().getName()+"|/serverpp \"|/w \"<° °>_h"+client.getName()+"|/serverpp \"|/w \"<° nun _einen Knuddel_!","icons/actRhapsody_"+image+".png");
   client.setKnuddels((int)client.getKnuddels()+1);
   client2.setRhapsodyFrom(client.getName());
   client.setRhapsodyTo(client.getRhapsodyTo()+"|"+client2.getName()+"|");
         
   client2.sendButlerMessage(channel.getName(),"Bei deiner anonymen Botschaft hat °>_h"+client.getName()+"|/serverpp \"|/w \"<° dich als Absender _richtig erraten_! Um deine Schwärmerei zu beenden, kannst du jederzeit _/rhapsody !_ eingeben.");   
   client.sendButlerMessage(channel.getName(),"Du kannst die Schwärmerei jederzeit mit der Eingabe von _/rhapsody !"+client2.getName()+"_ aufheben.");
   rhapsody.toremove += "|"+rhapsody.check(client2.getName(),client.getName())+"|";
             
       } else {
           
           
           if (vals[4].equals(target.getName())) {
               client.sendButlerMessage(channel.getName(),"Du hast "+target.getName()+" schon einmal fälschlicher Weise als Absender vermutet, es war sicher jemand anderes.");
               return;
           }
           
           if (target.getName().equals(Server.get().getButler().getName())) {
              client.sendButlerMessage(channel.getName(),"Es tut mir leid, aber ich habe keine Zeit, um geheime Nachrichten zu verschicken. Es war sicher jemand anderes.");
                return;
              }
           
         if (vals[4].isEmpty()) {
           
     channel.broadcastPicAction(">","°>_h"+client.getName()+"|/serverpp \"|/w \"<° lag mit der Vermutung, es sei _°>_h"+target.getName()+"|/serverpp \"|/w \"<°°b° gewesen, leider _°RR°falsch°BB°_! °>_h"+client.getName()+"|/serverpp \"|/w \"<° hat nun noch _1 Versuch_ zu erraten, von wem die anonyme Botschaft wohl kommt.","icons/actRhapsody_"+image+".png");
     if (onlines) {
     client2.sendButlerMessage(channel.getName(),"Bei deiner anonymen Botschaft hat _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ vermutet, sie würde von _°>_h"+target.getName()+"|/serverpp \"|/w \"<°_ kommen.");    
     }
     
     rhapsody.users.put(Long.parseLong(rhapsody.check(client2.getName(),client.getName())),new String[] {client2.getName(), client.getName(),channel.getName(),vals[3],target.getName()});
     
         }else {
              channel.broadcastPicAction(">","°>_h"+client.getName()+"|/serverpp \"|/w \"<° lag mit der Vermutung, es sei _°>_h"+target.getName()+"|/serverpp \"|/w \"<°°b° gewesen, leider _°RR°falsch°BB°_! °>_h"+client.getName()+"|/serverpp \"|/w \"<° hat nun _keinen Versuch_ mehr zu erraten, von wem die anonyme Botschaft wohl kommt.","icons/actRhapsody_"+image+".png");
     if (onlines) {
     client2.sendButlerMessage(channel.getName(),"Bei deiner anonymen Botschaft hat _°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ vermutet, sie würde von _°>_h"+target.getName()+"|/serverpp \"|/w \"<°_ kommen.");    
     }
              rhapsody.toremove += "|"+rhapsody.check(client2.getName(),client.getName())+"|";
             
         }
       
       }
       
       
   
      } else {
           client.sendButlerMessage(client.getChannel().getName(), "Momentan gibt es keine anonyme Liebesbotschaft für dich.");
        
      }
   
   }
   }
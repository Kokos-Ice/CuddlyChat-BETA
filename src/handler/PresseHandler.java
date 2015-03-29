package handler;
 
 import starlight.*;
 import tools.*;
 
 public class PresseHandler {
   public static void handle(String[] tokens, Client client)    {
   String opcode = tokens[0];
   String smid = tokens[1];
   String buttontext = tokens[2].trim();
   boolean abzug = false;
   if (!buttontext.equals("Abbruch")) {
   if (buttontext.equals("Ja - Geschenke im Profil um eins reduzieren")) {
       abzug = true;
   }
   
   Usersmiley sm = Server.get().getUsersmiley(smid);
    if (sm != null) { // sicherheitsschutz
         Smiley sms = Server.get().getSmiley(String.valueOf(sm.getSMID()));
          client.getChannel().broadcastAction("°BB°>", ""+client.getName()+" wirft die °>smileys/sm_abo_11-01_008_knuddelpress-bg...b.w_100.h_52.my_3.png<>smileys/sm_abo_11-01_008_knuddelpress...b.w_0.h_0.my_3.mx_-100.gif<° an, gibt "+sms.getKCode()+" hinein und drückt auf START... nachdem die Presse ihre Arbeit getan hat, kann sich "+client.getName()+" über _10 Knuddels_ freuen.");
        client.increaseKnuddels(10);
        Feature ft = Server.get().getFeature("Smileypress");
         String[] l = client.getFeature("Smileypress");
        ft.setBan(l[1],l[3],l[4],client); // setz sperre
       client.removeSmiley(sms.getName2());
     if (abzug) {
         client.setCodeE(client.getCodeE()-1);
     }
    } else {
          client.sendButlerMessage(client.getChannel().getName(),"???");
    }
   
   
   }
   
   
       
   }}
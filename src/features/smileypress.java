package features;

import static features.hero.timeStampToDate;
import starlight.*;
import tools.KCodeParser;

 public class smileypress  {

      public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
      public static String replaceLast(String string, String toReplace, String replacement) {
    int pos = string.lastIndexOf(toReplace);
    if (pos > -1) {
        return string.substring(0, pos)
             + replacement
             + string.substring(pos + toReplace.length(), string.length());
    } else {
        return string;
    }
}     
  public static void functionMake(Client client,Channel channel, String arg) {



      
       String[] l = client.getFeature("Smileypress");
 Feature ft = Server.get().getFeature("Smileypress");
 
 if (ft == null) {
     // kick vermeiden
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
   if (arg.isEmpty()) {
       
       
       String smss = "";
    
    if (!client.getSmileys().isEmpty()) {
         String[] smileys  = client.getSmileys().split("%%");
       for(String d : smileys) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sm = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getVerliehen().isEmpty() && sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sm.getTauschbar()) {
           if (!smss.isEmpty()) {
               smss += ",";
           }
         
           smss += d;
       }
       }}
         
    
    
       if(smss.isEmpty()) {
          client.sendButlerMessage(channel.getName(),"Du hast keine Smileys, die du pressen könntest.");     
           return;
       }
       
     StringBuilder lol = new StringBuilder();
     
     lol.append(hexToString("6B 00"));
     lol.append("Smiley-Presse - Auswahl des Smileys");
     lol.append(hexToString("F5 66 00 00 00 68 FF FF FF E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 0A 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 0A 42 E3 43 70 7E F5 42 53 70 7E F5 62 EB EB FF 47 42 42 42 42 70 7E F5 62 EB EB FF 46 70 7E F5 47 41 42 42 42 70 7E F5 62 EB EB FF 42 43 62 20 20 20 20 20 4F 4B 20 20 20 20 20 F5 63 65 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 E3 E3 E3 43 70 7E F5 42 4E 70 42 4E 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 53 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 43 70 7E F5 47 41 42 42 42 70 7E F5 42 43 6C"));
     lol.append("Smiley-Presse - Auswahl des Smileys");
     lol.append(hexToString("F5 62 67 55 66 00 00 00 68 DE DE FF E3 E3 E3 E3 43 70 7E F5 42 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 04 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 04 42 E3 43 70 7E F5 42 43 63"));
     
     lol.append("°R>{linkhovercolor}<r°#Klicke auf den Smiley, den du pressen möchtest.");
     String[] sms = Server.get().getSortSmileyvisitbyKategorie(client.getSmileys()).split("%%");
  int i = 0;
     for(String id : sms) {
        if (!id.isEmpty()) {
            Usersmiley usm = Server.get().getUsersmiley(id);
            if (usm.getVerliehen().isEmpty()) {
                 Smiley sm = Server.get().getSmiley(String.valueOf(usm.getSMID()));
            if (!sm.getSpez()) {
          if (i == 0) {
              lol.append("°%00°#");
          } else {
              lol.append("°%"+i*25+"°");
              
          }
          String kcode = sm.getKCode();
             kcode = kcode.replace("<>","<>--<>_c|/smileypress "+usm.getID()+"<>--<>");
             kcode = replaceLast(kcode,"<°","<>--<>_c|/smileypress "+usm.getID()+"<°");
               
                lol.append(kcode);
            
            
            if (i == 3) {
                  i = 0;
                  lol.append("#");
              } else {
                 i++;
            }
            
            }
            }}}
    
  
    lol.append("°>{linkhovercolorreset}<°");        
     lol.append(hexToString("F5 7E 74 70 F5 73 02 3F 01 E0 66 00 00 00 68 FF FF FF 69 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 FF FF E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 E3 E3 E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 E3 E3"));
       client.send(lol.toString());
   } else {
    Usersmiley sm = Server.get().getUsersmiley(arg.trim());
    if (sm != null) {
        Smiley sms = Server.get().getSmiley(String.valueOf(sm.getSMID()));
        StringBuilder lol = new StringBuilder();
        lol.append(hexToString("6B 00"));
        lol.append("Smiley-Presse - Bestätigung");
        lol.append(hexToString("F5 73"));
        lol.append("sud"); // opcode
        lol.append(hexToString("F5"));
        lol.append(sm.getID()); // smid
        lol.append(hexToString("F5 66 00 00 00 68 FF FF FF E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 0A 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 0A 42 E3 43 70 7E F5 42 53 70 7E F5 62 EB EB FF 47 42 42 42 42 70 7E F5 62 EB EB FF 46 70 7E F5 47 41 44 42 42 70 7E F5 62 EB EB FF 42 43 62"));
        lol.append("Abbruch");
        lol.append(hexToString("F5 63 65 73 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 70 7E F5 62 EB EB FF 42 43 62"));
        lol.append("Ja - Geschenke im Profil um eins reduzieren");
        lol.append(hexToString("F5 63 65 73 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 70 7E F5 62 EB EB FF 42 43 62"));
        lol.append("Ja - Geschenke im Profil unverändert lassen");
        lol.append(hexToString("F5 63 65 73 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 E3 E3 E3 43 70 7E F5 42 4E 70 42 4E 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 53 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 43 70 7E F5 47 41 42 42 42 70 7E F5 42 43 6C"));
        lol.append("Smiley-Presse - Bestätigung");
        lol.append(hexToString("F5 62 67 55 66 00 00 00 68 DE DE FF E3 E3 E3 E3 43 70 7E F5 42 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 04 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 04 42 E3 43 70 7E F5 42 43 63"));
        lol.append("°R>{linkhovercolor}<r°#_Frage:_ Bist du sicher, dass du deinen Smiley#...#"+sms.getKCode()+"##...vernichten möchtest um 10 Knuddel zu erhalten?##_°R°ACHTUNG°r°_: Der gepresste Smiley geht dadurch _unwiderruflich verloren_, du erhältst dafür _10 Knuddel_ gutgeschrieben.#°>{linkhovercolorreset}<°");
        lol.append(hexToString("F5 7E 74 70 F5 73 01 C2 01 2C 66 00 00 00 68 FF FF FF 69 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 FF FF E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 E3 E3 E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 E3 E3"));
        client.send(lol.toString());
        
    } else {
           client.sendButlerMessage(channel.getName(),"???");
    
    }
   }
         
      
      
  }}
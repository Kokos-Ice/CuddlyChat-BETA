 package handler;

import static funktionen.kiss.time;
 import starlight.*;
import tools.popup.Popup;
import tools.*;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import java.sql.Statement;
import java.util.Arrays;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.PopupNewStyle;
 
public class CodeHandler {
    
         private static boolean isInteger(String s) { try { Integer.parseInt(s); return true; } catch (NumberFormatException e) {}  return false; }
private static int countChars(String input, char toCount) { int counter = 0; for (char c : input.toCharArray()) { if (c != toCount) continue; counter++; } return counter; } 
 
  public static void handle(String[] tokens, Client client)
   {
       
      
       
       if (tokens[1].equals("SHOW_OPTIONS")) {
           String join = "0";
           String close = "0";
           String pos = "0";
           String trans = "1";
           String ani = "0";
           String tab1 = "Alle";
           String tab2 = "Pres";
           String tab3 = "-keiner-";
            String tab4 = "-keiner-";
             String tab5 = "-keiner-";
           
           if (tokens[3].contains("10,")) {
               join = "1";
           } else if (tokens[3].contains("11,")) {
               join = "0";
           }
            if (tokens[3].contains("20,")) {
               close = "1";
           } else if (tokens[3].contains("21,")) {
               close = "0";
           }
            
            if (tokens[3].contains("32,")) {
              pos = "2";
           } else if (tokens[3].contains("31,")) {
               pos = "1";
           }else if (tokens[3].contains("30,")) {
               pos = "0";
           }
            
              if (tokens[3].contains("60,")) {
              trans = "1";
           } else  {
              trans = "0";
           }

             if (tokens[3].contains("70,")) {
              ani = "1";
           } else  {
              ani = "0";
           }

  String tabs = tokens[3].split(",51:")[1];
  String tabu = tabs.replace("52:","").replace("53:","").replace("54:","").replace("55:","");
  
  String[] tabnamen = tabu.split(",");
  if (tabnamen[0].equals("Alle")){
   tab1 = "0";   
  } else if (tabnamen[0].equals("Favs")){
   tab1 = "1";   
  } else if (tabnamen[0].equals("Features")){
   tab1 = "2";   
  }  else if (tabnamen[0].equals("Gratis")){
   tab1 = "3";   
  }  else if (tabnamen[0].equals("Pres")){
   tab1 = "4";   
  }  else if (tabnamen[0].equals("-keiner-")){
   tab1 = "5";   
  }  
  
  
  if (tabnamen[1].equals("Alle")){
   tab2 = "0";   
  } else if (tabnamen[1].equals("Favs")){
   tab2 = "1";   
  } else if (tabnamen[1].equals("Features")){
   tab2 = "2";   
  }  else if (tabnamen[1].equals("Gratis")){
   tab2 = "3";   
  }  else if (tabnamen[1].equals("Pres")){
   tab2 = "4";   
  }  else if (tabnamen[1].equals("-keiner-")){
   tab2 = "5";   
  }  
  
  
   if (tabnamen[2].equals("Alle")){
   tab3 = "0";   
  } else if (tabnamen[2].equals("Favs")){
   tab3 = "1";   
  } else if (tabnamen[2].equals("Features")){
   tab3 = "2";   
  }  else if (tabnamen[2].equals("Gratis")){
   tab3 = "3";   
  }  else if (tabnamen[2].equals("Pres")){
   tab3 = "4";   
  }  else if (tabnamen[2].equals("-keiner-")){
   tab3 = "5";   
  }  
   if (tabnamen[3].equals("Alle")){
   tab4 = "0";   
  } else if (tabnamen[3].equals("Favs")){
   tab4 = "1";   
  } else if (tabnamen[3].equals("Features")){
   tab4 = "2";   
  }  else if (tabnamen[3].equals("Gratis")){
   tab4 = "3";   
  }  else if (tabnamen[3].equals("Pres")){
   tab4 = "4";   
  }  else if (tabnamen[3].equals("-keiner-")){
   tab4 = "5";   
  } 
   if (tabnamen[4].equals("Alle")){
   tab5 = "0";   
  } else if (tabnamen[4].equals("Favs")){
   tab5 = "1";   
  } else if (tabnamen[4].equals("Features")){
   tab5 = "2";   
  }  else if (tabnamen[4].equals("Gratis")){
   tab5 = "3";   
  }  else if (tabnamen[4].equals("Pres")){
   tab5 = "4";   
  }  else if (tabnamen[4].equals("-keiner-")){
   tab5 = "5";   
  }  
   
  // client.setSmileybox(join+"|"+close+"|"+pos+"|"+tab1+"|"+tab2+"|"+tab3+"|"+tab4+"|"+tab5+"|"+trans+"|"+ani);
   
   // client.send(ModuleCreator.SB_INVALIDATE(null));
    //client.send(ModuleCreator.SB_TABS(client));
    // client.send(ModuleCreator.SB_TAB(client,tabnamen[0]));
        
     } else if (tokens[1].equals("menu")) {
        if (tokens[2].contains("Meine Smileys")) {
            CommandParser.parse("/code ?", client, client.getChannel(),false);
        } else if (tokens[2].contains("Code registrieren")) {
            CommandParser.parse("/code reg", client, client.getChannel(),false);
        } else if (tokens[2].contains("Tausch-Fenster")) {
            CommandParser.parse("/code trade", client, client.getChannel(),false);
        } else if (tokens[2].contains("Smileys verleihen")) {
            CommandParser.parse("/code lend", client, client.getChannel(),false);
        } else if (tokens[2].contains("Code aktivieren")) {
            CommandParser.parse("/code aktiv", client, client.getChannel(),false);
        } else if (tokens[2].contains("Code verschenken")) {
            CommandParser.parse("/code send", client, client.getChannel(),false);
        } else if (tokens[2].contains("Letzte Tausch-Aktionen")) {
            CommandParser.parse("/code lasttrade", client, client.getChannel(),false);
        } else if (tokens[2].contains("Shop")) {
            CommandParser.parse("/shop", client, client.getChannel(),false);
        } else if (tokens[2].contains("Code-ID")) {
            CommandParser.parse("/code id", client, client.getChannel(),false);
        } else if (tokens[2].contains("Meine Basics")) {
            CommandParser.parse("/code basic", client, client.getChannel(),false);
        }  else if (tokens[2].contains("Suche")) {
            CommandParser.parse("/code search", client, client.getChannel(),false);
        } 
    }
     if (tokens[1].contains("send2~")) {
        
        String code = tokens[1].split("~")[1];
        
    
      String fakearray = "code|sent|bla|"+code.split("-")[0].trim()+"|"+code.split("-")[1].trim()+"|"+code.split("-")[2].trim()+"|"+code.split("-")[3].trim()+"|"+tokens[3].trim()+"|"+tokens[4].trim();
      tokens = fakearray.split("\\|");
       
     }
        if (tokens[1].contains("ok2~")) {
           String nick = tokens[1].split("~")[1];
           String buttontext = tokens[2].trim();
           
           String[] aa = Server.get().getLeihen(nick);
           
           if (aa == null) {
              
               Popup popup = new Popup("Problem", "Problem", "#Du hast dir zu lange Zeit gelasse, um auf diesen Link zu klicken.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
           }
           String okdu = "0";
           String okan = "0";
           String nickdu = "";
           String smdu = "";
           String sman = "";
           String kndu = "";
           String knan = "";
           String nickan = "";
           if (client.getName().equals(nick)) {
               okdu = aa[6];  
               nickdu = aa[0];
               kndu = aa[2];
               smdu = aa[1];                                             
               nickan = aa[3];
               knan = aa[5];
               sman = aa[4];
               okan = aa[7];
           } else {
               okdu = aa[7];
                kndu = aa[5];
               smdu = aa[4];
               okan = aa[6];
               nickan = aa[0];
               knan = aa[2];
               sman = aa[1];
               nickdu = aa[3];
           }
           
           if (okan.equals("0")) { // andere noch nix gemacht
               if (buttontext.equals("Verleihen annehmen")) {
                   Server.get().removeTauschen(nick);
                   if (client.getName().equals(nick)) {
Server.leihen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"1","0",(System.currentTimeMillis()/1000)+"","1",aa[10]} );
                   } else {
Server.leihen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"0","1",(System.currentTimeMillis()/1000)+"","1",aa[10]} );
                   }
               } else {
               
                    Server.get().removeLeihen(nick);
                   if (client.getName().equals(nick)) {
Server.leihen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"2","0",(System.currentTimeMillis()/1000)+"","1",aa[10]} );
                   } else {
Server.leihen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"0","2",(System.currentTimeMillis()/1000)+"","1",aa[10]} );
                   }
               }
               
               
           } else { // andere bereits geklickt = auswerten
            String other = okan;
            String du = "0";
             if (buttontext.equals("Verleihen annehmen")) {
                 du = "1";
             } else {
                 du = "2";
             }
             
           
             
          
            String nickname = KCodeParser.escape(nickdu);
        boolean online = true; Client target1 = Server.get().getClient(nickdu);
        if (target1 == null) {  online = false;  target1 = new Client(null);  target1.loadStats(nickdu);  }
     
        String nickname2 = KCodeParser.escape(nickan);
        boolean online2 = true; Client target2 = Server.get().getClient(nickan);
        if (target2 == null) {  online2 = false;  target2 = new Client(null);  target2.loadStats(nickan);  }
  
          
String durchgeführt1 = "Du hast folgende Smileys mit "+target1.getName()+" gegenseitig °[0,128,0]°_verliehen°r°_:##";
String durchgeführt2 = "Du hast folgende Smileys mit "+target2.getName()+" gegenseitig °[0,128,0]°_verliehen°r°_:##";
String abgelehnt2 = "Folgender Verleih wurde von _°>_2"+target1.getName()+"|/serverpp \"|/w \"<° und °>_2"+target2.getName()+"|/serverpp \"|/w \"<° °R°abgelehnt_°r°:";


   int anzahldu = countChars(smdu, ',')+1;
     int anzahlan = countChars(sman, ',')+1;
     
      String smimagesdu = "";
      if (!smdu.equals("NIX")) {
for(String id : smdu.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimagesdu.isEmpty()) {
        smimagesdu += ", ";
    }
    smimagesdu += sss.getKCode();
}
      }

 String smimagesan = "";
 if (!sman.equals("NIX")) {
for(String id : sman.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimagesan.isEmpty()) {
        smimagesan += ", ";
    }
    smimagesan += sss.getKCode();
}}
     
  String gebot = "_°>_2"+nickdu+"|/serverpp \"|/w \"<°_ bietet folgendes:##";
  if (smdu.equals("NIX")) {
   gebot += "°>bullet2...b.png<° _*nichts*_#";   
  } else {
      gebot += "°>bullet2...b.png<° _"+anzahldu+" Smileys_ ("+smimagesdu+")#";
  }
if (!kndu.equals("0")) {
gebot += "#°>bullet2...b.png<° "+kndu+" Knuddels";
}
gebot += "##_°>_2"+nickan+"|/serverpp \"|/w \"<°_ bietet dafür folgendes:##";
  if (sman.equals("NIX")) {
    gebot += "°>bullet2...b.png<° _*nichts*_#";
} else {
gebot += "°>bullet2...b.png<° _"+anzahlan+" Smileys_ ("+smimagesan+")#";
} 
if (!knan.equals("0")) {
gebot += "#°>bullet2...b.png<° "+knan+" Knuddels";
}

gebot += "##Verleihdauer: "+aa[10];
           
              if (du.equals("1") && other.equals("1")) {
                 // beide ok
                 
     
       
       
       Popup popup = new Popup("Smileys verliehen", "Smileys verliehen", durchgeführt2+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
      Popup popup2 = new Popup("Smileys verliehen", "Smileys verliehen", durchgeführt1+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
              
           
        int sek = 1209600;
       // dauert aa[10]
       // 1 Minute,10 Minuten,30 Minuten,1 Stunde,2 Stunden,6 Stunden,12 Stunden,1 Tag,2 Tage,3 Tage,1 Woche,2 Wochen
       if (aa[10].equals("1 Minute")) {
          sek = 60; 
       } else if (aa[10].equals("3 Minuten")) {
          sek = 180; 
       }else if (aa[10].equals("10 Minuten")) {
          sek = 600; 
       }else if (aa[10].equals("30 Minuten")) {
          sek = 1800; 
       }else if (aa[10].equals("1 Stunde")) {
          sek = 3600; 
       }else if (aa[10].equals("2 Stunden")) {
          sek = 7200; 
       }else if (aa[10].equals("6 Stunden")) {
          sek = 21600; 
       }else if (aa[10].equals("12 Stunden")) {
          sek = 43200; 
       }else if (aa[10].equals("1 Tag")) {
          sek = 86400; 
       }else if (aa[10].equals("2 Tage")) {
          sek = 172800; 
       }else if (aa[10].equals("3 Tage")) {
          sek = 259200; 
       }else if (aa[10].equals("1 Woche")) {
          sek = 604800;
       }else if (aa[10].equals("2 Wochen")) {
          sek = 1209600; 
       }
       
       
       Long bis = (System.currentTimeMillis()/1000)+sek;
       
       // smiley setzen fehlt
    if (!smdu.equals("NIX")) {
for(String id : smdu.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    
    bla.setVerliehen(target2.getName()+"|"+bis);
    target2.setSmileys2(target2.getSmileys2()+bla.getID()+"%%");
   Server.get().query("insert into `sm_verliehen` set usersmileyid='"+bla.getID()+"',`from`='"+target1.getName()+"', `to`='"+target2.getName()+"',`bis`='"+bis+"'");
           
           PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_verliehen` WHERE `usersmileyid` = '"+bla.getID()+"'");
            
            if (rs.next()) {
               Verliehen put = new Verliehen(rs);
             Server.verliehen.put(String.valueOf(bla.getID()), put);
      
            } } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            pcon.close();
        }
}}
    
    
       if (!sman.equals("NIX")) {
for(String id : sman.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));

      bla.setVerliehen(target1.getName()+"|"+bis);
    target1.setSmileys2(target1.getSmileys2()+bla.getID()+"%%");
   Server.get().query("insert into `sm_verliehen` set usersmileyid='"+bla.getID()+"',`from`='"+target2.getName()+"', `to`='"+target1.getName()+"',`bis`='"+bis+"'");
           
           PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_verliehen` WHERE `usersmileyid` = '"+bla.getID()+"'");
            
            if (rs.next()) {
               Verliehen put = new Verliehen(rs);
             Server.verliehen.put(String.valueOf(bla.getID()), put);
      
            } } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            pcon.close();
        }
        
}}
      
       
      
       target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(knan));
       target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(kndu)); 
         // ModuleCreator.UPDATE_SB(target1);
           //  ModuleCreator.UPDATE_SB(target2);
        
     
              } else if (du.equals("2") && other.equals("2")) {
                    
       Popup popup = new Popup("Verleihen abgelehnt", "Verleihen abgelehnt", abgelehnt2+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
      Popup popup2 = new Popup("Verleihen abgelehnt", "Verleihen abgelehnt", abgelehnt2+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
              
       
       
                      target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(kndu));                                  
                      target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(knan));               
            
              } else if (du.equals("1") && other.equals("2")) {
                  String abgelehnt = "Folgender Verleih wurde von _°>_2"+nickan+"|/serverpp \"|/w \"<° °R°abgelehnt_°r°:";
             
                     
       Popup popup = new Popup("Verleihen abgelehnt", "Verleihen abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
      Popup popup2 = new Popup("Verleihen abgelehnt", "Verleihen abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
              
                           target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(kndu));                                  
                           target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(knan));
           
              } else if (du.equals("2") && other.equals("1")) {
                 

                 String abgelehnt = "Folgender Verleih wurde von _°>_2"+nickdu+"|/serverpp \"|/w \"<° °R°abgelehnt_°r°:";
           
                 Popup popup = new Popup("Verleihen abgelehnt", "Verleihen abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
                 Popup popup2 = new Popup("Verleihen abgelehnt", "Verleihen abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
                 
                 target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(kndu));                                  
                 target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(knan));
             } 
               
               Server.get().removeLeihen(nick);
                 Action.delete = Action.delete+"|SM2~"+nick+"|";
         
              
           }
           
           
       }
        
       if (tokens[1].contains("leihen2")) {
           
           
          
        
          String nick = tokens[1].split("~")[1];
          String sm = "";
          int knuddelszahl = 0;
          String knuddels = "";
          
              sm = tokens[4].trim();
              knuddels = tokens[3].trim();              
         
          
           if (isInteger(knuddels) == false || knuddels.isEmpty() || Integer.parseInt(knuddels) < 0) {
        knuddelszahl = 0;
    } else {
        knuddelszahl = Integer.parseInt(knuddels);
    }
        
   
 if (knuddelszahl != 0 && client.getKnuddels() < knuddelszahl) {
              int lost = knuddelszahl-(int)client.getKnuddels();
              Popup popup = new Popup("Nicht genügend Knuddels", "Nicht genügend Knuddels", "Es fehlen dir "+lost+" Knuddels.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
 } 
 
 String nickname = KCodeParser.escape(nick);   boolean online = true;  Client target = Server.get().getClient(nick);  if (target == null) {  online = false;  target = new Client(null); target.loadStats(nick);  }
      
 if (target.getName() == null) {
           
      Popup popup = new Popup("Nick existiert nicht", "Nick existiert nicht", "Der Nickname "+nick+"§ existiert nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

 
 String[] blas = Server.get().getLeihen(target.getName());
 
 if (blas == null || !blas[4].isEmpty()) {
      return;
     
 }
 
if (target.equals(client)) {
          
    Popup popup = new Popup("Schizophren?", "Schizophren?", "#Du kannst nicht mit dir selbst tauschen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

 if (!client.getChannel().getClients().contains(target) || target.getName().equals(Server.get().getButler().getName())) {
            
     Popup popup = new Popup("Nick nicht im gleichen Channel", "Nick nicht im gleichen Channel", target.getName()+" muss sich im gleichen Channel wie du aufhalten, um mit dir tauschen zu können.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
 }
 
   int f = 0;
    String[] as = null;
      for(String[] a : Server.get().getLeihen()) {
          if (a[3].equals(client.getName())) {
          f = 1;  
          as = a;
          }
          
      }
      if (f == 0) {
               Popup popup = new Popup("Problem", "Problem", "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
      }
   int anzahl = countChars(sm, ',')+1;
     int anzahl2 = countChars(as[1], ',')+1;
     
  
   
  if (anzahl2 < anzahl) { // zuviel
      String kann = "";
      
      if (anzahl2 == 1) {
          kann = "einen";
      } else  {
          kann = String.valueOf(anzahl2);
      }   
      Popup popup = new Popup("Zu viele Smileys ausgewählt", "Zu viele Smileys ausgewählt", "Du darfst _maximal "+kann+" Smileys_ zum Leihen mit "+nick+" anbieten.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
  }
      
  String smimages = "";
  if (!sm.isEmpty()) {
for(String id : sm.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}}


 String smimages2 = "";
for(String id : as[1].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages2.isEmpty()) {
        smimages2 += ", ";
    }
    smimages2 += sss.getKCode();
}
  
    String texti = "";
if (sm.isEmpty()) {
    texti += "°>bullet2...b.png<° _*nichts*_#";
    sm = "NIX";
} else {
texti += "°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";
}
  if (knuddelszahl >= 1) {
      texti += "#°>bullet2...b.png<° "+knuddelszahl+" Knuddels";
  }
texti += "##_Du_ bietest zum Verleih:#°>bullet2...b.png<° _"+anzahl2+" Smileys_ ("+smimages2+")#";
if (Integer.parseInt(as[2]) >= 1) {
      texti += "#°>bullet2...b.png<° "+as[2]+" Knuddels";
  }
texti += "##Annehmen? _°BB>/code lend:ok|/code lend:ok<r°_";

  
   target.sendButlerMessage(target.getChannel().getName(), "_Leih-Angebot:_ (für "+as[10]+")#_°>"+client.getName()+"|/serverpp \"|/w \"<°_ bietet zum Verleih:#"+texti);
   client.sendButlerMessage(client.getChannel().getName(), "Verleihen eingeleitet: #"+target.getName()+" wurde über dein Tauschangebot informiert.");
  client.setKnuddels((int)client.getKnuddels()-knuddelszahl);
 // map upaten
  
Server.get().removeLeihen(target.getName());
Server.leihen.put(target.getName(), new String[] { blas[0],blas[1], blas[2], blas[3],sm,String.valueOf(knuddelszahl),"0","0",(System.currentTimeMillis()/1000)+"","0",blas[10]} );
 
 // ende popup2
          
          
      }
       
       
   if (tokens[1].contains("Tausch-Fenster öffnen")) {
       String nick = tokens[3].trim();
       String dauer = tokens[4].trim();
       String knuddels = tokens[5].trim();
       String sm = tokens[6].trim();
       int knuddelszahl = 0;
       
       
         if (isInteger(knuddels) == false || knuddels.isEmpty() || Integer.parseInt(knuddels) < 0) {
        knuddelszahl = 0;
    } else {
        knuddelszahl = Integer.parseInt(knuddels);
    }
      
      if (nick.trim().isEmpty()) {
            
          Popup popup = new Popup("Kein Nick angegeben", "Kein Nick angegeben", "Du musst bei _Tauschen mit (Nick)_ den Nicknamen desjenigen angeben, mit dem du tauschen möchtest.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
if (sm.trim().isEmpty()) {
           
    Popup popup = new Popup("Keinen Smiley / Code ausgewählt", "Keinen Smiley / Code ausgewählt", "#Du musst mindestens einen Smiley oder Code zum Tauschen auswählen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
 if (knuddelszahl != 0 && client.getKnuddels() < knuddelszahl) {
              int lost = knuddelszahl-(int)client.getKnuddels();
              Popup popup = new Popup("Nicht genügend Knuddels", "Nicht genügend Knuddels", "Es fehlen dir "+lost+" Knuddels.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
 } 
 
 String nickname = KCodeParser.escape(nick);   boolean online = true;  Client target = Server.get().getClient(nick);  if (target == null) {  online = false;  target = new Client(null); target.loadStats(nick);  }
      
 if (target.getName() == null) {
           
     Popup popup = new Popup("Nick existiert nicht", "Nick existiert nicht", "Der Nickname "+nick+"§ existiert nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

if (target.equals(client)) {
             
     Popup popup = new Popup("Schizophren?", "Schizophren?", "#Du kannst nicht mit dir selbst tauschen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

 if (!client.getChannel().getClients().contains(target) || target.getName().equals(Server.get().getButler().getName())) {
            
     Popup popup = new Popup("Nick nicht im gleichen Channel", "Nick nicht im gleichen Channel", target.getName()+" muss sich im gleichen Channel wie du aufhalten, um mit dir tauschen zu können.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
 }
 
 
  int anzahl = countChars(sm, ',')+1;
  
   String sms = "";
    
if (!target.getSmileys().isEmpty()) {    
         String[] smileys  = target.getSmileys().split("%%");
       for(String d : smileys) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sml = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sml.getTauschbar()) {
           if (!sms.isEmpty()) {
               sms += ",";
           }
         
           sms += d;
       }
       }}
       
  int anzahl2 = countChars(sms, ',')+1;
 
  
  if (anzahl > anzahl2 || sms.isEmpty() && anzahl > 1) {
      String kann = "";
      
      if (anzahl == 1) {
          kann = "einen";
      } else  {
          kann = String.valueOf(anzahl);
      }
        Popup popup = new Popup("Zuviele Smileys", "Zuviele Smileys", target.getName()+" kann momentan weniger als "+kann+" Smiley tauschen.#Bitte wähle weniger Smileys zum Tauschen aus.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return; 
      
  }
  
  
  String du = "0";
  String andere = "0";
  for(String[] a : Server.get().getTauschen()) {
      if (a[0].equals(client.getName())) {
          du = "1";
      }
      if (a[3].equals(client.getName())) {
          du = "1";
      }
       if (a[0].equals(target.getName())) {
      andere = "1";
  }
      if (a[3].equals(target.getName())) {
      andere = "1";
  }      
  }
 
  
  
  String du2 = "0";
  String andere2 = "0";
  for(String[] a : Server.get().getLeihen()) {
      if (a[0].equals(client.getName())) {
          du2 = "1";
      }
      if (a[3].equals(client.getName())) {
          du2 = "1";
      }
       if (a[0].equals(target.getName())) {
      andere2 = "1";
  }
      if (a[3].equals(target.getName())) {
      andere2 = "1";
  }      
  }
  
  
  if (du.equals("1")) {
     client.send(PopupNewStyle.create("Leihen nicht möglich", "Leihen nicht möglich", "Bitte warte, bis das alte Tauschangebot beendet ist, bevor du ein neues startest.", 450, 275));
     return; 
  }
  
   if (andere.equals("1")) {
     client.send(PopupNewStyle.create("Tauschpartner nicht möglich", "Tauschpartner nicht möglich", "Dein Tauschpartner ist derzeit in einen Tausch verwickelt.", 450, 275));
      return; 
 }
   
    if (du2.equals("1")) {
     client.send(PopupNewStyle.create("Leihen nicht möglich", "Leihen nicht möglich", "Bitte warte, bis das alte Leihangebot beendet ist, bevor du ein neues startest.", 450, 275));
     return; 
  }
  
   if (andere2.equals("1")) {
     client.send(PopupNewStyle.create("Tauschpartner nicht möglich", "Tauschpartner nicht möglich", "Dein Tauschpartner ist derzeit in einen Leih verwickelt.", 450, 275));
      return; 
 }
   
   
  
String smimages = "";
for(String id : sm.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}
  String text = "_Leih-Angebot: (für "+dauer+")_#_°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ bietet zum _Tausch_:#°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";
if (knuddelszahl >= 1) {
text += "°>bullet2...b.png<° _"+knuddelszahl+" Knuddel_#";
}
text += "#Klicke hier um das _Verleih_-Fenster zu öffnen: _°BB>/code lend:"+client.getName()+"|/code lend:"+client.getName()+"<r°_"; 

target.sendButlerMessage(target.getChannel().getName(), text);

    client.sendButlerMessage(client.getChannel().getName(), "Verleihen eingeleitet: #°>_h"+target.getName()+"|/serverpp \"|/w \"<° wurde über dein Leihangebot informiert.");
     client.setKnuddels((int)client.getKnuddels()-knuddelszahl);
   Server.leihen.put(client.getName(), new String[] { client.getName(),sm, String.valueOf(knuddelszahl), target.getName(),"","0","0","0",(System.currentTimeMillis()/1000)+"","0",dauer} );
  new Action(client.getName(),Action.ActionType.LEIHENTIMEOUT,30,client.getChannel().getName(),new String[] {target.getName(),sm },0);   
         
   
 
  
  
       
   } else   if (tokens[1].contains("ok~")) {
           String nick = tokens[1].split("~")[1];
           String buttontext = tokens[2].trim();
           
           String[] aa = Server.get().getTausch(nick);
           
           if (aa == null) {
                 client.send(PopupNewStyle.create("Problem", "Problem", "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.", 450, 275));
              return;
           }
           String okdu = "0";
           String okan = "0";
           String nickdu = "";
           String smdu = "";
           String sman = "";
           String kndu = "";
           String knan = "";
           String nickan = "";
           if (client.getName().equals(nick)) {
               okdu = aa[6];  
               nickdu = aa[0];
               kndu = aa[2];
               smdu = aa[1];                                             
               nickan = aa[3];
               knan = aa[5];
               sman = aa[4];
               okan = aa[7];
           } else {
               okdu = aa[7];
                kndu = aa[5];
               smdu = aa[4];
               okan = aa[6];
               nickan = aa[0];
               knan = aa[2];
               sman = aa[1];
               nickdu = aa[3];
           }
           
           if (okan.equals("0")) { // andere noch nix gemacht
               if (buttontext.equals("Tausch annehmen")) {
                   Server.get().removeTauschen(nick);
                   if (client.getName().equals(nick)) {
Server.tauschen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"1","0",(System.currentTimeMillis()/1000)+"","1"} );
                   } else {
Server.tauschen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"0","1",(System.currentTimeMillis()/1000)+"","1"} );
                   }
               } else {
               
                    Server.get().removeTauschen(nick);
                   if (client.getName().equals(nick)) {
Server.tauschen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"2","0",(System.currentTimeMillis()/1000)+"","1"} );
                   } else {
Server.tauschen.put(nick, new String[] { aa[0],aa[1], aa[2], aa[3],aa[4],aa[5],"0","2",(System.currentTimeMillis()/1000)+"","1"} );
                   }
               }
               
               
           } else { // andere bereits geklickt = auswerten
            String other = okan;
            String du = "0";
             if (buttontext.equals("Tausch annehmen")) {
                 du = "1";
             } else {
                 du = "2";
             }
             
           
             
          
            String nickname = KCodeParser.escape(nickdu);
        boolean online = true; Client target1 = Server.get().getClient(nickdu);
        if (target1 == null) {  online = false;  target1 = new Client(null);  target1.loadStats(nickdu);  }
     
        String nickname2 = KCodeParser.escape(nickan);
        boolean online2 = true; Client target2 = Server.get().getClient(nickan);
        if (target2 == null) {  online2 = false;  target2 = new Client(null);  target2.loadStats(nickan);  }
  
          
String durchgeführt1 = "Du hast folgenden °E°_Tausch_°r° mit "+target1.getName()+" endgültig _°E°durchgeführt_°r°:##";
String durchgeführt2 = "Du hast folgenden °E°_Tausch_°r° mit "+target2.getName()+" endgültig _°E°durchgeführt_°r°:##";
String abgelehnt2 = "Folgender Tausch wurde von _°>_2"+target1.getName()+"|/serverpp \"|/w \"<° und °>_2"+target2.getName()+"|/serverpp \"|/w \"<° °R°abgelehnt_°r°:";


   int anzahldu = countChars(smdu, ',')+1;
     int anzahlan = countChars(sman, ',')+1;
     
      String smimagesdu = "";
for(String id : smdu.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimagesdu.isEmpty()) {
        smimagesdu += ", ";
    }
    smimagesdu += sss.getKCode();
}


 String smimagesan = "";
for(String id : sman.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimagesan.isEmpty()) {
        smimagesan += ", ";
    }
    smimagesan += sss.getKCode();
}
     
  String gebot = "_°>_2"+nickdu+"|/serverpp \"|/w \"<°_ bietet folgendes:##";
      gebot += "°>bullet2...b.png<° _"+anzahldu+" Smileys_ ("+smimagesdu+")#";
if (!kndu.equals("0")) {
gebot += "#°>bullet2...b.png<° "+kndu+" Knuddels";
}
gebot += "##_°>_2"+nickan+"|/serverpp \"|/w \"<°_ bietet dafür folgendes:##";
gebot += "°>bullet2...b.png<° _"+anzahlan+" Smileys_ ("+smimagesan+")#";
if (!knan.equals("0")) {
gebot += "#°>bullet2...b.png<° "+knan+" Knuddels";
}

           
              if (du.equals("1") && other.equals("1")) {
                 // beide ok
                 
                      
                 Popup popup = new Popup("Tausch durchgeführt", "Tausch durchgeführt", durchgeführt2+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
                 Popup popup2 = new Popup("Tausch durchgeführt", "Tausch durchgeführt", durchgeführt1+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
                 
       
                           
                           
       String neu1 = target1.getSmileys();
       for(String d : smdu.split(",")) {
     Usersmiley sm = Server.get().getUsersmiley(d);
     
     
         String toremove = "";
     for(Favs f : Server.get().getFavs()) {
         if (f.getUser().equals(target1.getName()) && f.getSMID().equals(String.valueOf(sm.getSMID()))) {
         toremove = "|"+f.getID()+"|";    
         }         
     }
     for(String v : toremove.split("\\|")) {
         if (!v.isEmpty()) {
             Server.get().query("delete from sm_favs where id='"+v+"'");
             Server.get().removeFav(v);
         }
     }
         
     
     sm.setUser(target2.getName());
     target2.setSmileys(target2.getSmileys()+sm.getID()+"%%");
     String aaa = "";
    for(String a : neu1.split("%%")) {
        if (!a.isEmpty()) {
            if (!a.equals(sm.getID()+"")) {
                aaa += a+"%%";
            }
            
        }
    }
    neu1 = aaa;
    }
    
    target1.setSmileys(neu1); 
    
    String neu2 = target2.getSmileys();
    for (String d2 : sman.split(",")) {
          Usersmiley sm = Server.get().getUsersmiley(d2); 
          
           String toremove = "";
     for(Favs f : Server.get().getFavs()) {
         if (f.getUser().equals(target2.getName()) && f.getSMID().equals(String.valueOf(sm.getSMID()))) {
         toremove = "|"+f.getID()+"|";    
         }         
     }
     for(String v : toremove.split("\\|")) {
         if (!v.isEmpty()) {
             Server.get().query("delete from sm_favs where id='"+v+"'");
             Server.get().removeFav(v);
         }
     }
          
            sm.setUser(target1.getName());
              target1.setSmileys(target1.getSmileys()+sm.getID()+"%%");
              
              
                String aaa2 = "";
    for(String a : neu2.split("%%")) {
        if (!a.isEmpty()) {
            if (!a.equals(sm.getID()+"")) {
                aaa2 += a+"%%";
            }
            
        }
    }
    
    neu2 = aaa2;
        
    }
     target2.setSmileys(neu2); 
       // set knuddel
   // set code
      
       
       
       target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(knan));
       target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(kndu));        
      String neulog1 = target1.getName()+"~"+smdu+"~"+kndu+"~"+target2.getName()+"~"+sman+"~"+knan+"~"+(System.currentTimeMillis()/1000)+"|"+target1.getCodeTradeLog();
      String neulog2 = target2.getName()+"~"+sman+"~"+knan+"~"+target1.getName()+"~"+smdu+"~"+kndu+"~"+(System.currentTimeMillis()/1000)+"|"+target2.getCodeTradeLog();
      target1.setCodeTradeLog(neulog1);  
      target2.setCodeTradeLog(neulog2); 
             //  ModuleCreator.UPDATE_SB(target1);
               //   ModuleCreator.UPDATE_SB(target2);
        
              } else if (du.equals("2") && other.equals("2")) {
                
       
                  
                  
                  Popup popup = new Popup("Tausch abgelehnt", "Tausch abgelehnt", abgelehnt2+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
                 Popup popup2 = new Popup("Tausch abgelehnt", "Tausch abgelehnt", abgelehnt2+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
       
       target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(kndu));                                  
        target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(knan));               
             } else if (du.equals("1") && other.equals("2")) {
                  String abgelehnt = "Folgender Tausch wurde von _°>_2"+nickan+"|/serverpp \"|/w \"<° °R°abgelehnt_°r°:";
             
                        
                         
                  Popup popup = new Popup("Tausch abgelehnt", "Tausch abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
                 Popup popup2 = new Popup("Tausch abgelehnt", "Tausch abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
                  
                           
                           target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(kndu));                                  
        target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(knan));
             } else if (du.equals("2") && other.equals("1")) {
                 

                 String abgelehnt = "Folgender Tausch wurde von _°>_2"+nickdu+"|/serverpp \"|/w \"<° °R°abgelehnt_°r°:";
              
        Popup popup = new Popup("Tausch abgelehnt", "Tausch abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        target1.send(popup.toString());
              
                 Popup popup2 = new Popup("Tausch abgelehnt", "Tausch abgelehnt", abgelehnt+"##"+gebot, 450, 275);
                        Panel panel2 = new Panel();
                        Button buttonMessage4 = new Button("   OK   ");
                        buttonMessage4.setStyled(true);
                        panel2.addComponent(buttonMessage4);
                        popup2.addPanel(panel2);
                        popup2.setTrade(1);
                        target2.send(popup2.toString());
                  
       
        target1.setKnuddels((int)target1.getKnuddels()+Integer.parseInt(kndu));                                  
        target2.setKnuddels((int)target2.getKnuddels()+Integer.parseInt(knan));
             } 
               
               Server.get().removeTauschen(nick);
                 Action.delete = Action.delete+"|SM~"+nick+"|";
         
              
           }
           
           
       }
      if (tokens[1].contains("tauschen2")) {
           String save = tokens[1].split("~")[2];
          String nick = tokens[1].split("~")[1];
          String pw = "";
          String sm = "";
          int knuddelszahl = 0;
          String knuddels = "";
          if (save.equals("F")) {
              sm = tokens[5].trim();
              knuddels = tokens[4].trim();              
          } else {
              pw = tokens[3].trim();
              knuddels = tokens[5].trim();
              sm = tokens[6].trim();
          }
          
           if (isInteger(knuddels) == false || knuddels.isEmpty() || Integer.parseInt(knuddels) < 0) {
        knuddelszahl = 0;
    } else {
        knuddelszahl = Integer.parseInt(knuddels);
    }
        
            if (sm.isEmpty()) {
           
                Popup popup = new Popup("Keinen Smiley / Code ausgewählt", "Keinen Smiley / Code ausgewählt", "#Du musst mindestens einen Smiley oder Code zum Tauschen auswählen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
 if (knuddelszahl != 0 && client.getKnuddels() < knuddelszahl) {
              int lost = knuddelszahl-(int)client.getKnuddels();
              client.send(PopupNewStyle.create("Nicht genügend Knuddels", "Nicht genügend Knuddels", "Es fehlen dir "+lost+" Knuddels.", 450, 275));
              return;
 } 
 
 String nickname = KCodeParser.escape(nick);   boolean online = true;  Client target = Server.get().getClient(nick);  if (target == null) {  online = false;  target = new Client(null); target.loadStats(nick);  }
      
 if (target.getName() == null) {
           
     Popup popup = new Popup("Nick existiert nicht", "Nick existiert nicht", "Der Nickname "+nick+"§ existiert nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

 
 String[] blas = Server.get().getTausch(target.getName());
 
 if (blas == null || !blas[4].isEmpty()) {
      return;
     
 }
 
if (target.equals(client)) {
            
     Popup popup = new Popup("Schizophren?", "Schizophren?", "#Du kannst nicht mit dir selbst tauschen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

 if (!client.getChannel().getClients().contains(target) || target.getName().equals(Server.get().getButler().getName())) {
           
     Popup popup = new Popup("Nick nicht im gleichen Channel", "Nick nicht im gleichen Channel", target.getName()+" muss sich im gleichen Channel wie du aufhalten, um mit dir tauschen zu können.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
 }
 
   int f = 0;
    String[] as = null;
      for(String[] a : Server.get().getTauschen()) {
          if (a[3].equals(client.getName())) {
          f = 1;  
          as = a;
          }
          
      }
      if (f == 0) {
           client.send(PopupNewStyle.create("Problem", "Problem", "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.", 450, 275));
             return;
      }
   int anzahl = countChars(sm, ',')+1;
     int anzahl2 = countChars(as[1], ',')+1;
      if (anzahl2 < anzahl) { // zuwenig 
      String kann = "";
      
      if (anzahl2 == 1) {
          kann = "einen";
      } else  {
          kann = String.valueOf(anzahl2);
      }
      client.send(PopupNewStyle.create("Zu wenige Smileys ausgewählt", "Zu wenige Smileys ausgewählt", "Sie müssen _genau "+kann+" Smileys_ zum Tausch mit "+nick+" anbieten.", 450, 275));
        
      return;
  }
  
   
  if (anzahl2 < anzahl) { // zuviel
      String kann = "";
      
      if (anzahl2 == 1) {
          kann = "einen";
      } else  {
          kann = String.valueOf(anzahl2);
      }
      client.send(PopupNewStyle.create("Zu viele Smileys ausgewählt", "Zu viele Smileys ausgewählt", "Sie müssen _genau "+kann+" Smileys_ zum Tausch mit "+nick+" anbieten.", 450, 275));
        
      return;
  }
      
  String smimages = "";
for(String id : sm.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}


 String smimages2 = "";
for(String id : as[1].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages2.isEmpty()) {
        smimages2 += ", ";
    }
    smimages2 += sss.getKCode();
}
  
    String texti = "°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";
  
  if (knuddelszahl >= 1) {
      texti += "#°>bullet2...b.png<° "+knuddelszahl+" Knuddels";
  }
texti += "##_Du_ bietest zum Tausch:#°>bullet2...b.png<° _"+anzahl2+" Smileys_ ("+smimages2+")#";
if (Integer.parseInt(as[2]) >= 1) {
      texti += "#°>bullet2...b.png<° "+as[2]+" Knuddels";
  }
texti += "##Annehmen? _°BB>/code trade:ok|/code trade:ok<r°_";

  
   target.sendButlerMessage(target.getChannel().getName(), "_Tausch-Angebot:_#_°>"+client.getName()+"|/serverpp \"|/w \"<°_ bietet zum Tausch:#"+texti);
   client.sendButlerMessage(client.getChannel().getName(), "Tausch eingeleitet: #"+target.getName()+" wurde über dein Tauschangebot informiert.");
  client.setKnuddels((int)client.getKnuddels()-knuddelszahl);
 // map upaten
  
Server.get().removeTauschen(target.getName());
Server.tauschen.put(target.getName(), new String[] { blas[0],blas[1], blas[2], blas[3],sm,String.valueOf(knuddelszahl),"0","0",(System.currentTimeMillis()/1000)+"","0"} );
 
 // ende popup2
          
          
      }
      if (tokens[1].contains("Codes tauschen")) {
        String pw = "";
        String nick = "";
        int knuddelszahl = 0;
        String knuddels = "";
        String sm = "";
        String save = tokens[1].split("~")[1]; // T oder F
          if (save.equals("T")) {
          pw = tokens[3].trim();
          nick =  tokens[4].trim();
          knuddels =  tokens[5].trim();
          sm =  tokens[6].trim();
          }else {
          nick =  tokens[3].trim();
          knuddels = tokens[4].trim();
          sm =  tokens[5].trim();
          }
          
      if (isInteger(knuddels) == false || knuddels.isEmpty() || Integer.parseInt(knuddels) < 0) {
        knuddelszahl = 0;
    } else {
        knuddelszahl = Integer.parseInt(knuddels);
    }
      
      if (nick.trim().isEmpty()) {
            
          Popup popup = new Popup("Kein Nick angegeben", "Kein Nick angegeben", "Du musst bei _Tauschen mit (Nick)_ den Nicknamen desjenigen angeben, mit dem du tauschen möchtest.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
if (sm.trim().isEmpty()) {
           
    Popup popup = new Popup("Keinen Smiley / Code ausgewählt", "Keinen Smiley / Code ausgewählt", "#Du musst mindestens einen Smiley oder Code zum Tauschen auswählen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}
 if (knuddelszahl != 0 && client.getKnuddels() < knuddelszahl) {
              int lost = knuddelszahl-(int)client.getKnuddels();
              client.send(PopupNewStyle.create("Nicht genügend Knuddels", "Nicht genügend Knuddels", "Es fehlen dir "+lost+" Knuddels.", 450, 275));
              return;
 } 
 
 String nickname = KCodeParser.escape(nick);   boolean online = true;  Client target = Server.get().getClient(nick);  if (target == null) {  online = false;  target = new Client(null); target.loadStats(nick);  }
      
 if (target.getName() == null) {
             
     Popup popup = new Popup("Nick existiert nicht", "Nick existiert nicht", "#Der Nickname "+nick+"§ existiert nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

if (target.equals(client)) {
            
     Popup popup = new Popup("Schizophren?", "Schizophren?", "#Du kannst nicht mit dir selbst tauschen.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
}

 if (!client.getChannel().getClients().contains(target) || target.getName().equals(Server.get().getButler().getName())) {
          
     Popup popup = new Popup("Nick nicht im gleichen Channel", "Nick nicht im gleichen Channel", target.getName()+" muss sich im gleichen Channel wie du aufhalten, um mit dir tauschen zu können.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
 }
 
 
  int anzahl = countChars(sm, ',')+1;
  
   String sms = "";
    
if (!target.getSmileys().isEmpty()) {    
         String[] smileys  = target.getSmileys().split("%%");
       for(String d : smileys) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sml = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sml.getTauschbar()) {
           if (!sms.isEmpty()) {
               sms += ",";
           }
         
           sms += d;
       }
       }}
       
  int anzahl2 = countChars(sms, ',')+1;
 
  if (anzahl > anzahl2 || sms.isEmpty()) {
      String kann = "";
      
      if (anzahl == 1) {
          kann = "einen";
      } else  {
          kann = String.valueOf(anzahl);
      }
      client.send(PopupNewStyle.create("Zuviele Smileys", "Zuviele Smileys", target.getName()+" kann momentan weniger als "+kann+" Smiley tauschen.#Bitte wähle weniger Smileys zum Tauschen aus.", 450, 275));
        
      return;
  }
  
  String du = "0";
  String andere = "0";
  for(String[] a : Server.get().getTauschen()) {
      if (a[0].equals(client.getName())) {
          du = "1";
      }
      if (a[3].equals(client.getName())) {
          du = "1";
      }
       if (a[0].equals(target.getName())) {
      andere = "1";
  }
      if (a[3].equals(target.getName())) {
      andere = "1";
  }      
  }
 
  
  
  String du2 = "0";
  String andere2 = "0";
  for(String[] a : Server.get().getLeihen()) {
      if (a[0].equals(client.getName())) {
          du2 = "1";
      }
      if (a[3].equals(client.getName())) {
          du2 = "1";
      }
       if (a[0].equals(target.getName())) {
      andere2 = "1";
  }
      if (a[3].equals(target.getName())) {
      andere2 = "1";
  }      
  }
  
  
  if (du.equals("1")) {
     client.send(PopupNewStyle.create("Tausch nicht möglich", "Tausch nicht möglich", "Bitte warte, bis das alte Tauschangebot beendet ist, bevor du ein neues startest.", 450, 275));
     return; 
  }
  
   if (andere.equals("1")) {
     client.send(PopupNewStyle.create("Tauschpartner nicht möglich", "Tauschpartner nicht möglich", "Dein Tauschpartner ist derzeit in einen Tausch verwickelt.", 450, 275));
      return; 
 }
   
    if (du2.equals("1")) {
     client.send(PopupNewStyle.create("Tausch nicht möglich", "Tausch nicht möglich", "Bitte warte, bis das alte Leiangebot beendet ist, bevor du ein neues startest.", 450, 275));
     return; 
  }
  
   if (andere2.equals("1")) {
     client.send(PopupNewStyle.create("Tauschpartner nicht möglich", "Tauschpartner nicht möglich", "Dein Tauschpartner ist derzeit in einen Leih verwickelt.", 450, 275));
      return; 
 }
   
   
  
String smimages = "";
for(String id : sm.split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}
  String text = "_Tausch-Angebot:_#_°>_h"+client.getName()+"|/serverpp \\\"|/w \\\"<°_ bietet zum _Tausch_:#°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";
if (knuddelszahl >= 1) {
text += "°>bullet2...b.png<° _"+knuddelszahl+" Knuddel_#";
}
text += "#Klicke hier um das _Tausch_-Fenster zu öffnen: _°BB>/code trade:"+client.getName()+"|/code trade:"+client.getName()+"<r°_"; 

target.sendButlerMessage(target.getChannel().getName(), text);

    client.sendButlerMessage(client.getChannel().getName(), "Tausch eingeleitet: #"+target.getName()+" wurde über dein Tauschangebot informiert.");
     client.setKnuddels((int)client.getKnuddels()-knuddelszahl);
   Server.tauschen.put(client.getName(), new String[] { client.getName(),sm, String.valueOf(knuddelszahl), target.getName(),"","0","0","0",(System.currentTimeMillis()/1000)+"","0"} );
  new Action(client.getName(),Action.ActionType.TAUSCHTIMEOUT,30,client.getChannel().getName(),new String[] {target.getName(),sm },0);   
         
     
      }
       
       if (tokens[1].equals("sent")) {
        String blockA = tokens[3].trim();
       String blockB = tokens[4].trim();
       String blockC = tokens[5].trim();
      String blockD = tokens[6].trim();
      String nick = tokens[7].trim();
       String text2 = tokens[8].trim();
       String error = "";

        String nickname = KCodeParser.escape(nick);
 
    boolean online = true;
      Client target = Server.get().getClient(nickname);
       if (target == null) {
         online = false;
         target = new Client(null);
         target.loadStats(nickname);       }
       nickname = target.getName();
       String arg = blockA + "-" + blockB + "-" + blockC + "-" + blockD;
       
         int found = 0;
        String id = "";
        for(Codes d : Server.get().getCode()) {
        if (d.getCodecode().equals(arg)) {
            found = 1;
            id = d.getCodeid();
        }
        }
            
         if (found == 0) {
      String popup = Popup.create("Hinweis", "Hinweis", "Ungültiger Code.", 400, 200);
         return;
       }
       Codes code = Server.get().getCode(id);
       
       if (code.getAktiviert()) {
     String popup = Popup.create("Hinweis", "Hinweis", "Ungültiger Code.", 400, 200);
          return;   
       }
       
       if (!code.getUser().equals(client.getName())) {
        String popup = Popup.create("Hinweis", "Hinweis", "Dieser Code ist nicht auf deinem Nicknamen registriert.", 400, 200);
         return;
       }
       
       
     if (text2.isEmpty()) {
      String popup = Popup.create("Hinweis", "Hinweis", "Du hast vergessen einen Geschenkkartentext einzugeben.", 400, 200);
         client.send(popup);
         return;
      }
       if (nickname == null) {
       //  String popup = Popup.create("Nick existiert nicht", "Nick existiert nicht", String.format("Der Nickname _%s existiert nicht._##Vermutlich hast du dich bei der Eingabe vertippt.",  nick ), 400, 200);
       //  client.send(popup);
       //  return;
           Popup popup = new Popup("Nick existiert nicht", "Nick existiert nicht", String.format("Der Nickname _%s existiert nicht._##Vermutlich hast du dich bei der Eingabe vertippt.",  nick ), 400, 200);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
       }
       if (target == client) {
           String popup = Popup.create("Hinweis", "Hinweis", "Du kannst deinen Code nicht an dich selbst verschenken.", 400, 200);
         client.send(popup);
         return;
       }
       
         Kategorie lala = Server.get().getKategorie(code.getKategorie());
        
         
       //   String popup = Popup.create("Code aktiviert", "Code aktiviert", "Du hast soeben einen _"+lala.getName()+"-Code aktiviert_.", 400, 200);
      //    client.send(popup);
           Popup popup = new Popup("Code verschenkt", "Code verschenkt", "Du hast soeben einen _"+lala.getName()+"-Code an "+target.getName()+" verschenkt_.", 400, 200);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setGullideckel(1);
                        client.send(popup.toString());
                          
       
     
       target.setCodeE(target.getCodeE()+1);
       
       
        /* Highlights Eintrag */    
        int[] codeE = {1, 2, 3, 5, 10, 20, 30, 40, 50, 100, 111, 200, 222, 300, 333, 400, 500, 1000, 1500, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 15000, 20000, 25000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000  };
        Arrays.sort(codeE);
        
        if(Arrays.binarySearch(codeE, target.getCodeE()) >= 0) {
         target.setHighlights(String.format("%s|%s _%d_. Geschenk von °BB°_%s_°12°|", target.getHighlights(), Server.get().timeStampToDate(time), target.getCodeE(), client.getName()));
         
        }
         /* Highlights Eintrag ENDE */ 
       
       
       client.setCodeV(client.getCodeV()+1);
       client.removeCode(code.getCodeid());
       code.setAktiviert(target.getName());
       target.setNewCode(client.getName()+"|~|"+lala.getName()+"|~|"+text2+"|~~|");
       
       
       } else  if (tokens[1].equals("aktivieren")) { 
           
           String arg = tokens[3].trim()+"-"+tokens[4].trim()+"-"+tokens[5].trim()+"-"+tokens[6].trim();
              int found = 0;
        String id = "";
        for(Codes d : Server.get().getCode()) {
        if (d.getCodecode().equals(arg)) {
            found = 1;
            id = d.getCodeid();
        }
        }
            
         if (found == 0) {
      String popup = Popup.create("Hinweis", "Hinweis", "Code ungültig.", 400, 200);
          client.send(popup);
          return;
       }
       Codes code = Server.get().getCode(id);
       
       if (code.getAktiviert()) {
       String popup = Popup.create("Hinweis", "Hinweis", "Code ungültig.", 400, 200);
          client.send(popup);
        
      return;   
       }
       
       if (!code.getUser().equals(client.getName())) {

              String popup = Popup.create("Hinweis", "Hinweis", "Dieser Code ist nicht auf deinem Nicknamen registriert.", 400, 200);
          client.send(popup);
         return;
       }
     
        Kategorie lala = Server.get().getKategorie(code.getKategorie());
        
          String popup = Popup.create("Code aktiviert", "Code aktiviert", " Du hast soeben einen _"+lala.getName()+"-Code aktiviert_.", 400, 200);
          client.send(popup);
        
       code.aktivateSmileyCode(client,"");
      
       client.setCodeE(client.getCodeE()+1);
       client.removeCode(code.getCodeid());
       code.setAktiviert(client.getName());
        
       } else if (tokens[1].equals("Code registrieren")) {
           int found = 0;
           String id = "";
           String codes = "";
            for(Codes code : Server.get().getCode()) {
          if (code.getCodecode().equals(tokens[3].trim())) {
              found = 1;
              id = code.getCodeid();
              codes = code.getCodecode();
              if (!code.getUser().isEmpty()) {
                  String popup = Popup.create("Hinweis", "Hinweis", "Dieser Code ist bereits registriert.", 400, 200);
                  client.send(popup);  
                return;    
              }
              
          } 
       }
            if (found == 0) {
                 String popup = Popup.create("Hinweis", "Hinweis", "Ungültiger Code.", 400, 200);
        client.send(popup);  
                return;
            }
           
           
            client.setCodes(id+"|"+codes+"%%"+client.getCodes());
             String popup = Popup.create("Code registriert", "Code registriert", "Code erfolgreich registriert.", 400, 200);
        client.send(popup);  
            
       } else if (tokens[1].equals("Code-ID prüfen")) {
        
          Codes code = Server.get().getCode(tokens[3].trim());
          
          if (code == null) {
          String popup = Popup.create("ID unbekannt", "ID unbekannt", String.format("Die ID %s existiert nicht.", tokens[3].trim() ), 400, 200);
        client.send(popup);
         return;
          }
          Kategorie kat = Server.get().getKategorie(code.getKategorie());
          String rest = "";
         int aus = 0;
         for(String v : kat.getSmileysWithoutBasic().split(",")) {
             if (!v.isEmpty() && aus < 5) {
                 Smiley sm = Server.get().getSmiley(v);
                 rest += sm.getKCode()+" ";
              aus++;   
             }
         }
         
         if (!code.getAktiviert()) {
          if (!kat.getSofort()) {
         Smiley basic = Server.get().getSmiley(kat.getBasics().split(",")[0]);
         
         
            String popup = Popup.create("ID gefunden", "ID gefunden", String.format("Der Code zur ID _%s_ wurde bislang noch nicht aktiviert.##_%s_#%s##%s", code.getCodeid(), kat.getName(), basic.getKCode(), rest), 400, 200);
 client.send(popup);
 return;
          } else {
String popup = Popup.create("ID gefunden", "ID gefunden", String.format("Der Code zur ID _%s_ wurde bislang noch nicht aktiviert.##_%s_#(Kein Basic vorhanden)##%s", code.getCodeid(), kat.getName(), rest), 400, 200);
 client.send(popup);
 return;
          }} else {
             
            String[] lala = code.getUserAktiviert().split("\\|");
            String von = lala[0];
            String time = lala[1];
                      if (!kat.getSofort()) {
                           Smiley basic = Server.get().getSmiley(kat.getBasics().split(",")[0]);
            String popup = Popup.create("ID gefunden", "ID gefunden", String.format("Der Code mit der ID %s ist am %s von _%s_ verwendet worden.##_%s_#%s##%s", code.getCodeid(), time, von, kat.getName(), basic.getKCode(), rest), 400, 200);
           client.send(popup);
           return;
         } else {
           String popup = Popup.create("ID gefunden", "ID gefunden", String.format("Der Code mit der ID %s ist am %s von _%s_ verwendet worden.##_%s_#(Kein Basic vorhanden)##%s", code.getCodeid(), time, von, kat.getName(), rest), 400, 200);
          client.send(popup);
return;
         }
             
             
         }
       }
   
      
       
   }}
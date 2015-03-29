package handler;

import starlight.*;
import java.util.*;
import java.text.*;
import tools.*;

public class BridgeHandler {
 
    public static void handleCommand(String nick, String info1,String info2,String info3, String requesttype) {
         Client client = Server.get().getClient(nick);
         boolean online = true;
      if (client == null) {
      client = new Client(null);
      client.loadStats(nick);
      online = false;
      }
 
        if (client.getName() != null) {   
            // Überprüfen auf Fehler 31.08.2014
        Channel channel = client.getChannel();    
         if (requesttype.equals("publicmessage") && online) {  
              info2 = info2.replace("Â","");
              info1 = info1.replace("Â","");
          if (info3.trim().isEmpty()) {
            channel.broadcastAction(info1,info2);
          } else {
          // picaction    
         }
          } else if (requesttype.equals("privatemessage") && online) {
               info1 = info1.replace("Â","");
             client.sendButlerMessage(channel.getName(),info1);
          } else if (requesttype.equals("newmessage")) {             
               Client target = Server.get().getClient(info1);
                    if (target == null) {
                        target = new Client(null);
                        target.loadStats(info1);
                    }
                String betreff = info2;
                String text = info3;
                // /m von client an target   
                text = text.replace("Â","");
                betreff = betreff.replace("Â","");
                  Server.get().newMessage(client.getName(), target.getName(),betreff,text, (System.currentTimeMillis()/1000)); 
         
                  
          } else if (requesttype.equals("messagetofriends")) { 
               String betreff = info1;
                String text = info2;
                 text = text.replace("Â","");
                betreff = betreff.replace("Â","");
                
                String eingabe = client.getFriendlist().replace("||", "~").replace("|", "");
                String[] strarr = eingabe.split("~");
                
                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                	String nicks = strarr[i];
        		Client c = Server.get().getClient(nicks);
                         if (c == null) {
                       c = new Client(null);
                        c.loadStats(nicks);
                    }
                     if (c.getName() != null) {
                         Server.get().newMessage(Server.get().getButler().getName(), c.getName(),betreff,text, (System.currentTimeMillis()/1000)); 
            
                         
                     }    
                                
                }
                
                
                
              // /m von client an alle freunde
          }else if (requesttype.equals("fansmiley")) { 
           
               String fansmileynumber = info1;
               String smileyname = ""; // leer lassen
               if (fansmileynumber.equals("100")) {
                   smileyname = ""; // hier name von 100er;
               } else  if (fansmileynumber.equals("300")) {
                   smileyname = ""; // hier name von 300er;
               } else  if (fansmileynumber.equals("1000")) {
                   smileyname = ""; // hier name von 1000er;
               }  else  if (fansmileynumber.equals("5000")) {
                   smileyname = ""; // hier name von 5000er;
               }
               
                  String id = "";
        for(Smiley sm : Server.get().getSmileys()) {
            if (sm.getName2().equals(smileyname)) {
                id = String.valueOf(sm.getID());
            }
        }
              Smiley dd = Server.get().getSmiley(id);
              if (dd != null) {
                   client.setSmiley(String.valueOf(dd.getID()));
              }
          } else if (requesttype.equals("verifysmiley")) {
              
              String smileyname = "Knuddel-Sign ''Mag ich!''";
               String id = "";
        for(Smiley sm : Server.get().getSmileys()) {
            if (sm.getName2().equals(smileyname)) {
                id = String.valueOf(sm.getID());
            }
        }
              Smiley dd = Server.get().getSmiley(id);
              if (dd != null) {
                   client.setSmiley(String.valueOf(dd.getID()));
              }
              // xD
          } else if (requesttype.equals("setvalue") && online) {
              if (info1.equals("photo")) {
                  client.setPhoto(info2);                 
              } else  if (info1.equals("photo_fancounter")) {
                  client.setFans(Integer.parseInt(info2));              
              } else if (info1.equals("photo_verify")) {
                  client.setPhoto_verify((byte)Integer.parseInt(info2));
              }else if (info1.equals("emailverify")) {
                  client.setEmailVerify((byte)Integer.parseInt(info2));     
              }else if (info1.equals("email")) {
                  client.setEmail(info2);
              }else if (info1.equals("verify_time")) {
                 // client.setVerifytime(info2);
              }else if (info1.equals("verify_code1")) {
               //   client.setVerifycode1(info2);
              }else if (info1.equals("verify_code2")) {
             //     client.setVerifycode2(info2);
              }else if (info1.equals("verify_code3")) {
                //  client.setVerifycode3(info2);
              } else if (info1.equals("photo_verify_last")) {
                  client.setPhotoVerifyLast(Integer.parseInt(info2));
              } else if (info1.equals("album_counter")) {
                  client.setAlbum_counter(info2);
              } else if (info1.equals("album_id")) {
                  client.setAlbum_id(info2);
              } else if (info1.equals("album_cover_image")) {
                  client.setAlbum_cover_image(info2);
              }else if (info1.equals("hp")) {
                    client.setHP(Integer.parseInt(info2));
              }else if (info1.equals("gb")) {
                  client.setGB(Integer.parseInt(info2));
              }else if (info1.equals("knuddels")) {
                  client.setKnuddels(Integer.parseInt(info2)); // info2 senden die gesamt kn die neu sind.
              }else if (info1.equals("hpban")) {
                  client.setHPBan(Integer.parseInt(info2));
              }else if (info1.equals("logincookie")) {
                  client.setLoginCookie(info2);
              }else if (info1.equals("donate")) {
                  client.setDonate(Integer.parseInt(info2));
              } else if (info1.equals("forum_post_counter")) {
                  client.setForumpostcounter(Integer.parseInt(info2));
              }

              
          } else if (Server.defaultmessages.containsKey(requesttype)) {
             
          Client target = Server.get().getClient(info1);
         boolean online2 = true;
        if (target == null) {
        target = new Client(null);
        target.loadStats(info1);
        online2 = false;
        }
        if (online2) {
        channel = target.getChannel();  
        }
        String[] va = Server.defaultmessages.get(requesttype);
        String betreff = va[0];
        String[] x = info3.split(",");
        String text = "";
        
        if (x.length == 0) {
         text = va[1];
        } else if (x.length == 1) {
        text = String.format(va[1],x[0]);
        }else if (x.length == 2) {
        text = String.format(va[1],x[0],x[1]);
        }else if (x.length == 3) {
        text = String.format(va[1],x[0],x[1],x[2]);
        }else if (x.length == 4) {
        text = String.format(va[1],x[0],x[1],x[2],x[3]);
        }else if (x.length == 5) {
        text = String.format(va[1],x[0],x[1],x[2],x[3],x[4]);
        }
        
           Server.get().newMessage(Server.get().getButler().getName(), target.getName(),betreff,text, (System.currentTimeMillis()/1000)); 
          }
            
         
      }    
        
    }
}

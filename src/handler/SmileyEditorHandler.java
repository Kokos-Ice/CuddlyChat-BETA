package handler;

import starlight.*;
import tools.popup.Popup;
import tools.*;
import java.util.*;
import java.text.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import java.sql.Statement;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.PopupNewStyle;
 
public class SmileyEditorHandler {
    
         private static boolean isInteger(String s) { try { Integer.parseInt(s); return true; } catch (NumberFormatException e) {}  return false; }
private static int countChars(String input, char toCount) { int counter = 0; for (char c : input.toCharArray()) { if (c != toCount) continue; counter++; } return counter; } 

public static int getInt2(boolean v) {
    if (v) {
        return 1;
    }
    return 0;
}
public static String parseSelten(String v) {
  
     if (v.equals("extreeem häufig")) {
           return "1";
       }else  if (v.equals("extrem häufig")) {
           return "2";
       }else  if (v.equals("seeehr häufig")) {
           return "3";
       }else  if (v.equals("sehr häufig")) {
           return "4";
       }else  if (v.equals("häufig")) {
           return "5";
       }else  if (v.equals("manchmal")) {
           return "6";
       }else  if (v.equals("selten")) {
           return "7";
       }else  if (v.equals("sehr selten")) {
           return "8";
       }else  if (v.equals("seeehr selten")) {
           return "9";
       }else  if (v.equals("extrem selten")) {
           return "10";
       }else  if (v.equals("extreeem selten")) {
           return "11";
       } 
     return "0";
    
}
public static int getInt(String v) {
    if (v.equals("Ja")) {
        return 1;
    }
    return 0;
}
  public static void handle(String[] tokens, Client client)
   {
    
     
     if (tokens[1].contains("search")) {
         
         String typ = tokens[1].split("~")[1];
         
         String treffer = "";
         int treffercount = 0;
         if (tokens[3].replace(",","").trim().isEmpty()) {
 
        
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Du musst einen Suchbegriff angeben.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
         }
         String[] words = tokens[3].trim().split(",");
         
         
         for(Smiley sf : Server.get().getSmileys()) {
             
           for(String word : words) {
               if (!word.isEmpty()) {
         if (sf.getName2().toLowerCase().contains(word.toLowerCase()) || sf.getTags().toLowerCase().contains(word.toLowerCase())) {
         treffercount++;
         
         treffer += "|"+sf.getID()+"|";
         }            
         }
             
         }
         }
         String text = "Deine Suche nach \"\"_"+tokens[3].trim()+"_§°r°\"\" ergab _"+treffercount+"_ Treffer:##";
  if (treffercount == 0) {
      text = "Keine Smileys gefunden. °>sm_03.gif<°";
  } else {
      for(String d : treffer.split("\\|")) {
          if (!d.isEmpty()) {
              Smiley ss = Server.get().getSmiley(d);
              if (typ.equals("acp")) {
              text += "°>finger.b.gif<° _°BB>"+ss.getName2().replace("°","\\°")+"|/smileyedit smileys:"+ss.getID()+"<r°_#";
              } else {
              text += "°>finger.b.gif<° _°BB>"+ss.getName2().replace("°","\\°")+"|/code ?:"+ss.getID()+"<r°_#";
          }}
      }
      
  }
         
            client.send(PopupNewStyle.create("Suchergebnis", "Suchergebnis",text, 450, 275)); 
        
     }
      if (tokens[1].contains("smiley~")) {
           String buttontext = tokens[2].trim();
       String edit = tokens[1].split("~")[1].trim();
       String name = tokens[3].trim();
      String syntax = tokens[4].trim();
      String kcode = tokens[5].trim();
       String text = tokens[6].trim();
        String split = tokens[7].trim();
         String knuddels = tokens[8].trim();
          String kategorie = tokens[9].trim();
           String selten = tokens[10].trim();
            String tausch = tokens[11].trim();
             String vorschau = tokens[12].trim();
              String verlinkt = tokens[13].trim();
               String sonder = tokens[14].trim();
               String ft1 = tokens[15].trim();
               String ft1ban = tokens[16].trim();
                String ft2 = tokens[17].trim();
                String ft2ban = tokens[18].trim();
                 String ft3 = tokens[19].trim();
                  String ft3ban = tokens[20].trim();
                  String tags = tokens[21].trim();
          if (buttontext.equals("Löschen")) {
      Smiley a = Server.get().getSmiley(edit);
      String toremove2 = ""; 
                for(Verliehen x : Server.get().getVerleih()) {
             Usersmiley sd = Server.get().getUsersmiley(x.getUserSmileyID());
                  if (String.valueOf(sd.getSMID()).equals(edit)) {
                 
                     toremove2 += "|"+x.getUserSmileyID()+"|";
               }   
              }
                
                  for(String v : toremove2.split("\\|")) {
                  if (!v.isEmpty()) {
                 Server.get().removeVerliehen(v);
                 Server.get().query("delete from sm_verliehen where usersmileyid='"+v+"'");
                  }
              }
                  
                  String toremove3 = "";
                  
                  
                    for(Favs ss : Server.get().getFavs()) {
                  if (String.valueOf(ss.getSMID()).equals(edit)) {
                        toremove3 += "|"+ss.getID()+"|";
                      
                  }
                  }
                    
                     for(String v : toremove3.split("\\|")) {
                  if (!v.isEmpty()) {
               Server.get().removeFav(v);
                 Server.get().query("delete from sm_favs where id='"+v+"'");
               
                  }
              }
                     
String toremove = "";                
              for(Usersmiley ss : Server.get().getUsersmileys()) {
                  if (String.valueOf(ss.getSMID()).equals(edit)) {
                 toremove += "|"+ss.getID()+"|";
                 
                 
                 
              String nickname = ss.getUser();   
      boolean online = true;
       Client target = Server.get().getClient(nickname);
       if (target == null) {
         online = false;
        target = new Client(null);
        target.loadStats(nickname);
      }
       
       String neu1 = target.deleteSmiley(ss.getID()+"");
       String neu2 = target.deleteSmiley2(ss.getID()+"");
       if (online) {
                  target.setSmileys(neu1);
                  target.setSmileys2(neu2);
       } else {
           Server.get().query("update accounts set smileys='"+neu1+"',smileyidsgeliehen='"+neu2+"' where name='"+target.getName()+"'");
       }
                  }
                  
              }
              for(String v : toremove.split("\\|")) {
                  if (!v.isEmpty()) {
               Server.get().removeUsersmiley(v);
                Server.get().query("delete from sm_usersmileys where id='"+v+"'");
               
                  }
              }
            
              
        Kategorie d = Server.get().getKategorie(a.getKategorie());
        if (d != null) {
        d.setSmileys(d.getSmileys().replace(","+a.getID(),""));
        }
              Server.get().removeSmiley(edit);
              Server.get().query("delete from sm_smileys where id='"+edit+"'");
              
              
                
                 PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg","##Der Smiley "+a.getName()+"_ wurde erfolgreich aus der Datenbank entfernt.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
     } else {
              
              
              if (name.isEmpty()) {
                  
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Die Zeile '_Name_' darf nicht leer sein.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
         }
         
          if (kcode.isEmpty()) {
          
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Die Zeile '_KCode_' darf nicht leer sein.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
         }
     
       if (knuddels.isEmpty() || !isInteger(knuddels) || Integer.parseInt(knuddels) < 0) {
       
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Du musst eine mögliche Zahl der Knuddels angeben.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
       }
          
       if (ft1ban.isEmpty() || !isInteger(ft1ban) || Integer.parseInt(ft1ban) < 0) {
         
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Du musst die Sperrzeit des ersten Features definieren.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
       }
       if (ft2ban.isEmpty() || !isInteger(ft2ban) || Integer.parseInt(ft2ban) < 0) {
           
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Du musst die Sperrzeit des zweiten Features definieren.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
       }
         if (ft3ban.isEmpty() || !isInteger(ft3ban) || Integer.parseInt(ft3ban) < 0) {
           
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Du musst die Sperrzeit des dritten Features definieren.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
       }
         
         if (!split.isEmpty()) {
             int right = 1;
             for(String v : split.split(",")) {
                 if (!v.isEmpty()) {
                     Smiley s = Server.get().getSmiley(v);
                     if (s == null) {
                         right = 0;
                     }

                 }
             }
         if (right == 0) {
             
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Splitsmileys falsch definiert.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
          
         }
         }
         
            if (edit.isEmpty()) {
                
                int f = 0;
                for(Smiley ss : Server.get().getSmileys()) {
                    if (ss.getName().equals(name) && ss.getKategorie().equals(kategorie)) {
                        f = 1;
                    }
                }
                if (f == 1) {
            
                 PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Der Smiley _"+name+"_ existiert bereits in der Datenbank.", 400, 275);  
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;  
                   
                   
                }
                
              
                
                 PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg","##Der Smiley _"+name+"_ wurde erfolgreich erstellt und der Datenbank hinzugefügt.", 400, 275);   
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 
                   
                   
                   
                   Server.get().query("insert into `sm_smileys` set `name` = '"+name.replace("\'","\\\'")+"',`category`='"+kategorie.replace("\'","\\\'")+"',`splits`='"+split+"',`kcode`='"+kcode+"',`syntax`='"+syntax+"',`desc`='"+text.replace("\'","\\\'")+"',`feature1`='"+ft1+"',`feature2`='"+ft2+"',`feature3`='"+ft3+"',`linkable`='"+getInt(verlinkt)+"',`onlyPreview`='"+getInt(vorschau)+"',`tauschbar`='"+getInt(tausch)+"',`selten`='"+parseSelten(selten)+"',`feature1_bantime`='"+ft1ban+"',`feature2_bantime`='"+ft2ban+"',`feature3_bantime`='"+ft3ban+"',`knuddels`='"+knuddels+"',`spez`='"+getInt(sonder)+"',tags='"+tags+"'");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_smileys` WHERE `name` = '"+name.replace("\'","\\\'")+"' and category='"+kategorie.replace("\'","\\\'")+"'");
            
            if (rs.next()) {
               Smiley put = new Smiley(rs);
              
             Server.smileys.put(rs.getString("id").toLowerCase(), put);
              Server.smileys2.put(put.getName2(), put);
             
             
               if (!kategorie.isEmpty()) {
                       Kategorie ka = Server.get().getKategorie(kategorie);
                       
                       if (ka != null) {
                           ka.setSmileys(ka.getSmileys()+","+rs.getString("id"));
                       }
                       
                   
               
                   }
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
           
            
                
            } else {
                
                
                 Smiley ss = Server.get().getSmiley(edit);
             ss.setSplit(split);
             ss.setKCode(kcode);
             ss.setSyntax(syntax);
             ss.setLinkable(getInt(verlinkt));
             ss.setPreview(getInt(vorschau));
             ss.setText(text);
             ss.setTauschbar(getInt(tausch));
             ss.setSelten(Integer.parseInt(parseSelten(selten)));
             ss.setKnuddels(Integer.parseInt(knuddels));
             ss.setSpez(getInt(sonder));
             ss.setFT1(ft1);
             ss.setFT2(ft2);
             ss.setTags(tags);
             ss.setFT3(ft3);
             ss.setFT1Ban(ft1ban);
             ss.setFT2Ban(ft2ban);
             ss.setFT3Ban(ft3ban);
             
             if (!name.equals(ss.getName())) {
              for(Usersmiley sg : Server.get().getUsersmileys()) {
                  if (sg.getName().equals(ss.getName()) && sg.getKategorie().equals(kategorie)) {
                  sg.setName(name);
                  }
              }   
                 
              ss.setName(name);   
             }
             
            
             if (!kategorie.equals(ss.getKategorie())) {
                 for(Usersmiley sg : Server.get().getUsersmileys()) {
                  if (sg.getName().equals(ss.getName()) && sg.getKategorie().equals(kategorie)) {
                  sg.setKategorie(kategorie);
                  }
              }
                 
                
                 Kategorie als = Server.get().getKategorie(ss.getKategorie());
                 if (als != null) {
                   
                     als.setSmileys(als.getSmileys().replace(","+ss.getID(),""));
                 }
                 
                 Kategorie neu = Server.get().getKategorie(kategorie);
                 if (neu != null) {
                      neu.setSmileys(neu.getSmileys()+","+ss.getID());
                 }
                 
                 
                 
                 
                 ss.setKategorie(kategorie);
             }

               PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg", "##Du hast erfolgreich den Smiley _"+name+"_ bearbeitet.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
               }
         
           
                              
              
          }
          
      }
     if (tokens[1].contains("feature~")) {
       String buttontext = tokens[2].trim();
       String edit = tokens[1].split("~")[1].trim();
       String name = tokens[3].trim();
      String text = tokens[4].trim();
      String makro = tokens[5].trim();
      String show = tokens[6].trim();
      
     if (buttontext.equals("Löschen")) {
         // löschen
         
              PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
      
        try {
           Connection con = pcon.connect();
           ps = con.prepareStatement("SELECT * FROM `accounts`");
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            
                          String nickname = KCodeParser.escape(rs.getString("name"));

      boolean online = true;
       Client target = Server.get().getClient(nickname);
       if (target == null) {
         online = false;
        target = new Client(null);
        target.loadStats(nickname);
      }
     if (online) {
       target.setAllowedFeatures(target.getAllowedFeatures().replace("|"+name+"|",""));
     } else {
         Server.get().query("update accounts set allowedFeatures='"+target.getAllowedFeatures().replace("|"+name+"|","")+"' where name='"+target.getName()+"'");
     }
              
             }}
        catch (SQLException e) {
         e.printStackTrace();
       } finally {
          if (ps != null)
            try {
             ps.close();
            }
            catch (SQLException e)
            {
            }
           pcon.close();
        }
         
         // allowedfeature löschen
         
         for(Smiley sm : Server.get().getSmileys()) {
             if (sm.getFT1().equals(name)) {
              sm.setFT1("");   
             }
              if (sm.getFT2().equals(name)) {
                 sm.setFT2("");
             }
               if (sm.getFT3().equals(name)) {
                 sm.setFT3("");
             }
             
         }
           
             PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg" ,"##Du hast das Feature _"+name+"_ gelöscht.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
         Server.get().removeFeature(name);
         Server.get().query("delete from `sm_features` where name='"+name+"'");
        
     } else {
         if (name.isEmpty()) {
            PopupNewStyle popup = new PopupNewStyle("Problem", "Problem" ,"##Du musst einen Featurenamen angeben.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;    
         }
       if (text.isEmpty()) {
            PopupNewStyle popup = new PopupNewStyle("Problem", "Problem" ,"##Du musst einen Text angeben.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
          }
        
         
         if (edit.isEmpty()) {
           Feature la =  Server.get().getFeature(name);
           if (la != null) {
                 client.send(PopupNewStyle.create("Problem", "Problem","Dieses Feature existiert bereits.", 450, 275)); 
               return;
           }
               PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg", "##Du hast erfolgreich das Feature _"+name+"_ erstellt.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
              
               
                   Server.get().query("insert into `sm_features` set name='"+name+"', text='"+text.replace("\'","\\\'")+"',makro='"+makro+"',`sb_show`='"+getInt(show)+"'");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_features` WHERE `name` = '"+name+"'");
            
            if (rs.next()) {
               Feature put = new Feature(rs);
                   synchronized (Server.feature) {
             Server.feature.put(rs.getString("name").toLowerCase(), put);
                   }
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
             
             
         } else {
             
            Feature edits = Server.get().getFeature(edit);
             edits.setShow(getInt(show));
             edits.setText(text);
             edits.setMakro(makro);
            
            if (!name.toLowerCase().equals(edits.getName().toLowerCase())) {
               
                
                   Server.get().query("insert into `sm_features` set name='"+name+"', text='"+edits.getText().replace("\'","\\\'")+"',makro='"+edits.getMakro()+"'");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_features` WHERE `name` = '"+name+"'");
            
            if (rs.next()) {
               Feature put = new Feature(rs);
            synchronized (Server.feature) {
               Server.feature.put(rs.getString("name").toLowerCase(), put);
            }
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
                
             Server.removeFeature(edits.getName().toLowerCase());
             Server.get().query("delete from sm_features where name='"+edits.getName()+"'");    
             for(Smiley ss : Server.get().getSmileys()) {
                 
                 if (ss.getFT1().equals(edits.getName())) {
                     ss.setFT1(name);
                 }
                 if (ss.getFT2().equals(edits.getName())) {
                     ss.setFT2(name);
                 }
                 if (ss.getFT3().equals(edits.getName())) {
                     ss.setFT3(name);
                 }
                 
             }
             
          
          PoolConnection pcon5 = ConnectionPool.getConnection();
     PreparedStatement ps5 = null;
     try {
     Connection con5 = pcon5.connect();
       ps5 = con5.prepareStatement("SELECT * FROM accounts where allowedFeatures != ''");
     ResultSet rs5 = ps5.executeQuery();
       while (rs5.next())
      {
Client s = Server.get().getClient(rs5.getString("name"));
if (s == null) {
    String neu = rs5.getString("allowedFeatures").replace("|"+edits.getName()+"|","|"+name+"|");
    Server.get().query("update accounts set allowedFeatures='"+neu+"' where name='"+rs5.getString("name")+"'");
} else {
    client.setAllowedFeatures(client.getAllowedFeatures().replace("|"+edits.getName()+"|","|"+name+"|"));
}

      }
    }
    catch (SQLException e) {
       e.printStackTrace();
     } finally {
      if (ps5 != null)
        try {
          ps5.close();
         }
         catch (SQLException e)
        {
         }
    pcon5.close();    } 
            
                
                
            } 
             
             PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg", "##Du hast erfolgreich das Feature _"+name+"_ bearbeitet.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
             
              
         }
         
     }         
     } 
     
     
     
     if (tokens[1].contains("kategorie~")) {
       String buttontext = tokens[2].trim();
       String edit = tokens[1].split("~")[1].trim();
       String name = tokens[3].trim();
       String preis = tokens[4].trim();
       int anzeigen = getInt(tokens[5].trim());
       int sofort = getInt(tokens[6].trim());
       String dauer = tokens[7].trim();
     if (buttontext.equals("Löschen")) {
           Kategorie la =  Server.get().getKategorie(edit);
         
           if (!la.getSmileys().isEmpty()) {
                   PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Es können nur leere Kategorien gelöscht werden.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
           }
           
           Server.get().query("delete from sm_kategorien where name='"+edit.replace("\'","\\\'")+"'");
           Server.get().removeKategorie(edit);
    
                  PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg", "##Die Kategorie "+edit+" wurde gelöscht.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        
          
         
         
     } else {
         if (name.isEmpty()) {
            PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Du musst einen Kategorienamen angeben.", 400, 275); ;
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;    
         }
         if (!isInteger(preis) || Integer.parseInt(preis) <= 0) { 
              PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Dies ist kein gültiger Preis.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
     }
     
         if (!isInteger(dauer) || Integer.parseInt(dauer) <= 0) {

          PopupNewStyle popup = new PopupNewStyle("Problem", "Problem","##Dies ist keine gültige Entwicklungsdauer.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
          
   }
         
         if (edit.isEmpty()) {
           Kategorie la =  Server.get().getKategorie(name);
           if (la != null) {
               PopupNewStyle popup = new PopupNewStyle("Problem", "Problem", "##Diese Kategorie existiert bereits.", 400, 275); 
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        return;
              
           }
               PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg", "##Du hast erfolgreich die Kategorie _"+name+"_ erstellt.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                        
                        
                        
               SimpleDateFormat uhrzeit6 = new SimpleDateFormat("yyyy");
              String Jahr = uhrzeit6.format(new Date());
                 SimpleDateFormat weda2 = new SimpleDateFormat("M");
             String wd2 = weda2.format(new Date());
                String Monatsname = "";

               if (wd2.equals("1")){
                Monatsname = "Januar";
               }else if (wd2.equals("2")){
                Monatsname = "Februar";
               }else if (wd2.equals("3")){
                 Monatsname = "März";
               }else if (wd2.equals("4")){
                 Monatsname = "April";
               }else if (wd2.equals("5")){
                 Monatsname = "Mai";
               }else if (wd2.equals("6")){
              Monatsname = "Juni";
               } else if (wd2.equals("7")){
                Monatsname = "Juli";
               }else if (wd2.equals("8")){
                 Monatsname = "August";
               }else if (wd2.equals("9")){
                 Monatsname = "September";
               }else if (wd2.equals("10")){
                 Monatsname = "Oktober";
               }else if (wd2.equals("11")) {
                Monatsname = "November";
               } else if (wd2.equals("12")) {
                 Monatsname = "Dezember";
              }
               
              String tm = Monatsname+" "+Jahr;
               
               
                   Server.get().query("insert into `sm_kategorien` set name='"+name.replace("\'","\\\'")+"', price='"+preis+"',direct='"+sofort+"',visable='"+anzeigen+"',entwicklungsdauer='"+dauer+"',smileys='',creates='"+tm+"'");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_kategorien` WHERE `name` = '"+name.replace("\'","\\\'")+"'");
            
            if (rs.next()) {
               Kategorie put = new Kategorie(rs);
                   synchronized (Server.kategorie) {
             Server.kategorie.put(rs.getString("name").toLowerCase(), put);
                   }
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
                     
             
         } else {
             
             Kategorie edits = Server.get().getKategorie(edit);
             
             edits.setDauer(Integer.parseInt(dauer));
             edits.setPreis(Integer.parseInt(preis));
             edits.setSofort(sofort);
             edits.setAnzeigen(anzeigen);
             
            if (!name.toLowerCase().equals(edits.getName().toLowerCase())) {
               
     
                   Server.get().query("insert into `sm_kategorien` set name='"+name.replace("\'","\\\'")+"', price='"+edits.getPreis()+"',direct='"+getInt2(edits.getSofort())+"',visable='"+getInt2(edits.getAnzeigen())+"',entwicklungsdauer='"+edits.getDauer()+"',smileys='"+edits.getSmileys()+"',creates='"+edits.getCreate()+"'");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_kategorien` WHERE `name` = '"+name.replace("\'","\\\'")+"'");
            
            if (rs.next()) {
               Kategorie put = new Kategorie(rs);
            synchronized (Server.kategorie) {
               Server.kategorie.put(rs.getString("name").toLowerCase(), put);
            }
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
                
             Server.removeKategorie(edits.getName().toLowerCase());
             Server.get().query("delete from sm_kategorien where name='"+edits.getName().replace("\'","\\\'")+"'");    
             for(Usersmiley ss : Server.get().getUsersmileys()) {
                 
                 if (ss.getKategorie().equals(edits.getName())) {
                     ss.setKategorie(name);
                 }
                 
             }
           for(Codes c : Server.get().getCode()) {        
            
                 if (c.getKategorie().equals(edits.getName())) {
                     c.setKategorie(name);
                 } }
                 
             
                
                
            } 
             
              PopupNewStyle popup = new PopupNewStyle("Erfolg", "Erfolg", "##Du hast erfolgreich die Kategorie _"+name+"_ bearbeitet.", 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
              
         }
         
     }         
     } 
     
   }}
   
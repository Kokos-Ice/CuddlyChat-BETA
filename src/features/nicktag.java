package features;

import static features.hero.timeStampToDate;
import starlight.*;


public class nicktag {
      public static void functionMake(Client client,Channel channel, String arg, String cmd) {
   
          if (cmd.equals("nicktag")) {
           
           
                   String[] l = client.getFeature("Nicktag-Universal");
 Feature ft = Server.get().getFeature("Nicktag-Universal");
 
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
              if (arg.equals("0") || arg.equals("1") || arg.equals("2") || arg.equals("3") || arg.equals("4") || arg.equals("5")|| arg.equals("6")) {
                
                  
                  String colorde = "";
                  String coloren = "";
                  if (arg.equals("0")) {
                      colorde = "Rot";
                      coloren = "red";
                  } else if (arg.equals("1")) {
                      colorde = "Grün";
                      coloren = "green";
                  } else if (arg.equals("2")) {
                      colorde = "Gelb";
                      coloren = "yellow";
                  } else if (arg.equals("3")) {
                      colorde = "Lila";
                      coloren = "purple";
                  } else if (arg.equals("4")) {
                      colorde = "Blau";
                      coloren = "blue";
                  } else if (arg.equals("5")) {
                      colorde = "Türkis";
                      coloren = "cyan";
                  } else if (arg.equals("6")) {
                      colorde = "Rosa";
                      coloren = "pink";
                  } 
                  // regenbogensmiley löschen und marktschreier FARBE setzen
                  // removesmiley noch machen
                  // Abo 2013/03 Marktschreier
                    client.removeSmiley("Abo März 2013-Marktschreier");
                             String id = "";
        for(Smiley sm : Server.get().getSmileys()) {
            if (sm.getName2().equals("Abo März 2013-Marktschreier-"+colorde)) {
                id = String.valueOf(sm.getID());
            }
        }
                    Smiley dd = Server.get().getSmiley(id);
                    
                    client.setSmiley(String.valueOf(dd.getID()));
                  client.sendButlerMessage(channel.getName(),"Du hast dich für die _Farbe "+colorde+"_ entschieden. Du kannst den Megaphone-Nicktag nun mit _°BB>_h/nicktag"+coloren+" +|\"<r°_ aktivieren.");
                 
             
              } else {
              
              client.sendButlerMessage(channel.getName(),"Welche Farbe soll dein Marktschreier bekommen?#°>finger.b.gif<° _°BB>_hBlau|/nicktag 4<r°_ ,_°BB>_hTürkis|/nicktag 5<r°_, _°BB>_hGrün|/nicktag 1<r°_, _°BB>_hGelb|/nicktag 2<r°_, _°BB>_hRot|/nicktag 0<r°_ ,_°BB>_hRosa|/nicktag 6<r°_ oder _°BB>_hLila|/nicktag 3<r°_");
              }
              return;
          }
          
         if (cmd.equals("nicktagred")) {
        if (arg.equals("+")) {  
            
            
            
String[] l1 = client.getFeature("Nicktag-Red");
 Feature ft1 = Server.get().getFeature("Nicktag-Red");

 if (ft1 == null) {
     return;
 }
         
        
          if (l1[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft1.getName()+" Feature nicht.");
 return;  
 } 
 if (l1[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft1.getName()+" erst am "+timeStampToDate(Long.parseLong(l1[5]))+" Uhr wieder nutzen.");
return;
   }  
 
            if (client.getNickTag().equals("0")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("0");   
          ft1.setBan(l1[1],l1[3],l1[4],client); 
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("0")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
             
             return;
      }   
         
         
         
         
      
          if (cmd.equals("nicktaggreen")) {
          
              
              if (arg.equals("+")) {  
                    
            
String[] l2 = client.getFeature("Nicktag-Green");
 Feature ft2 = Server.get().getFeature("Nicktag-Green");

 if (ft2 == null) {
     return;
 }
         
      
          if (l2[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft2.getName()+" Feature nicht.");
 return;  
 } 
 if (l2[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft2.getName()+" erst am "+timeStampToDate(Long.parseLong(l2[5]))+" Uhr wieder nutzen.");
return;
   }  
            if (client.getNickTag().equals("1")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("1");   
          ft2.setBan(l2[1],l2[3],l2[4],client); 
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("1")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
                return;
      }
            if (cmd.equals("nicktagyellow")) {
                if (arg.equals("+")) {  
                    
            
String[] l3 = client.getFeature("Nicktag-Yellow");
 Feature ft3 = Server.get().getFeature("Nicktag-Yellow");

 if (ft3 == null) {
     return;
 }
         
       if (l3[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft3.getName()+" Feature nicht.");
 return;  
 } 
 if (l3[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft3.getName()+" erst am "+timeStampToDate(Long.parseLong(l3[5]))+" Uhr wieder nutzen.");
return;
   }  
            if (client.getNickTag().equals("2")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("2");   
          ft3.setBan(l3[1],l3[3],l3[4],client); 
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("2")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
            return;
      }
              if (cmd.equals("nicktagpurple")) {
          if (arg.equals("+")) {  
            
                      
            
String[] l4 = client.getFeature("Nicktag-Purple");
 Feature ft4 = Server.get().getFeature("Nicktag-Purple");

 if (ft4 == null) {
     return;
 }
         
        
          if (l4[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft4.getName()+" Feature nicht.");
 return;  
 } 
 if (l4[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft4.getName()+" erst am "+timeStampToDate(Long.parseLong(l4[5]))+" Uhr wieder nutzen.");
return;
   }  
 
            if (client.getNickTag().equals("3")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("3");   
          ft4.setBan(l4[1],l4[3],l4[4],client);
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("3")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
                    return;
      }
      
              
              
              if (cmd.equals("nicktagblue")) {
       if (arg.equals("+")) {  
                            
            
String[] l5 = client.getFeature("Nicktag-Blue");
 Feature ft5 = Server.get().getFeature("Nicktag-Blue");

 if (ft5 == null) {
     return;
 }
         
        
          if (l5[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft5.getName()+" Feature nicht.");
 return;  
 } 
 if (l5[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft5.getName()+" erst am "+timeStampToDate(Long.parseLong(l5[5]))+" Uhr wieder nutzen.");
return;
   }  
            if (client.getNickTag().equals("4")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("4"); 
          ft5.setBan(l5[1],l5[3],l5[4],client); 
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("4")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
                      return;
      }
                
                
      if (cmd.equals("nicktagcyan")) {
        if (arg.equals("+")) {  
            
         
 String[] l6 = client.getFeature("Nicktag-Cyan");
 Feature ft6 = Server.get().getFeature("Nicktag-Cyan");

 if (ft6 == null) {
     return;
 }
         
        
          if (l6[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft6.getName()+" Feature nicht.");
 return;  
 } 
 if (l6[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft6.getName()+" erst am "+timeStampToDate(Long.parseLong(l6[5]))+" Uhr wieder nutzen.");
return;
   }  
 
            if (client.getNickTag().equals("0")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("5");   
          ft6.setBan(l6[1],l6[3],l6[4],client); 
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("0")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
             
             return;
      }   
         
          
      
      
      

             if (cmd.equals("nicktagpink")) {
        if (arg.equals("+")) {  
                     
            
 String[] l7 = client.getFeature("Nicktag-Pink");
 Feature ft7 = Server.get().getFeature("Nicktag-Pink");

 if (ft7 == null) {
     return;
 }
         
        
          if (l7[0].equals("0")) {
       client.sendButlerMessage(channel.getName(),"Du hast das "+ft7.getName()+" Feature nicht.");
 return;  
 } 
 if (l7[0].equals("1")) {
       client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft7.getName()+" erst am "+timeStampToDate(Long.parseLong(l7[5]))+" Uhr wieder nutzen.");
return;
   }  
 
            if (client.getNickTag().equals("0")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist bereits aktiv.");
                return;
            }
            
          client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde aktiviert.");
         client.setNickTag("6");   
          ft7.setBan(l7[1],l7[3],l7[4],client); 
        } else if (arg.equals("-")) {       
            
               if (!client.getNickTag().equals("0")) {
                client.sendButlerMessage(channel.getName(),"Dieser Megaphon-Nicktag ist nicht aktiv.");
                return;
            }
             client.sendButlerMessage(channel.getName(),"Das Megaphon-Nicktag wurde deaktiviert.");
         client.setNickTag("");   
        }
             
             return;
      } 
      
      
      }
}

      
      

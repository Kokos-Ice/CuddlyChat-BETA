 package tools;
     
import static funktionen.heart.time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
    import tools.popup.Popup;
    import starlight.*;
     
    public class Action implements Runnable {
           public static String delete = "";  
            private String nickname;
            public static String settings = "Tageslogin - Smile~1|Tageslogin - Laughing~100|Tageslogin - Wave~250|Tageslogin - Nod~500|Tageslogin - Nope~900|Tageslogin - Angry~1500|Tageslogin - Wink~2300|Tageslogin - Kiss~3300|Tageslogin - Giggle~4500|Tageslogin - Speechless~6000|Tageslogin - Surprised~7800|Tageslogin - Tongue Out~10000|Tageslogin - Cry~12500|Tageslogin - Sad~15500|Tageslogin - Yell~18800|Tageslogin - In Love~22200|Tageslogin - Keyboard~26600|Tageslogin - Knock Knock~30000|Tageslogin - Money~35200|Tageslogin - Embarassed~41600|Tageslogin - Coffee~47100|Tageslogin - Innocent~53900|Tageslogin - Yawn~69200|Tageslogin - Sealed Lips~76900|Tageslogin - Undecided~86700|Tageslogin - Cool~100000|";
            public static int lastgameid = 0;
            private String channel;
            private ActionType type;
            
            private int timeout,tid;
            
            private String[] infos;
            
       
           
            public static int countChars(String input, char toCount) {
		int counter = 0;

		for (char c : input.toCharArray()) {
			if (c != toCount)
				continue;
			counter++;
		}

		return counter;
	}
            public static int getNextId() {
                lastgameid = lastgameid+1;               
                return lastgameid;
            }
            
            public Action(String nickname, ActionType type, int timeout,String channel,String[] infos,int id) {
                    this.nickname = nickname;
                    this.type = type;
                    this.channel = channel;
                    this.tid = id;
                    this.infos = infos;
                    this.timeout = timeout;
                    
                    new Thread(this).start(); // Thread (run Methode) starten
            }
     
            public enum ActionType {
                BAD6TIMEOUT,BAD6START,TAGESLOGIN2,TAGESLOGIN,LEIHEN,LEIHENTIMEOUT,TAUSCHTIMEOUT,TUTORIALLOGIN;
            }
     
            @Override
            public void run() {
                    while(true) {
                            Client client = Server.get().getClient(nickname);
     
                            if(client == null) { // Client hat die Verbindung zum Server verloren
                                client = new Client(null); client.loadStats(nickname);
                             
                                client.saveStats();
                                break; // Ende
                            }
            
                                   
                           /* KnuddelsCent by Chiller */ 
                            if (type == ActionType.TAGESLOGIN2) {
                                
                                 try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                                   if(client == null) { break; }
                             
                                 
                                 String zahl = channel;
                                 
                                 
                                 
                            String nextname = "";
                            String nextname2 = "";
                            int grenze = 0;
                            int grenze2 = 0;
                           
                            for(String c : settings.split("\\|")) {
                                if (!c.isEmpty()) {
                                 String[] a = c.split("~");
                                if (Integer.parseInt(a[1]) > client.getKnuddelscent() && nextname.isEmpty()) {
                                    grenze = Integer.parseInt(a[1]);
                                    nextname = a[0];
                                } else  if (Integer.parseInt(a[1]) > client.getKnuddelscent() && nextname2.isEmpty()) {
                                    grenze2 = Integer.parseInt(a[1]);
                                    nextname2 = a[0];
                                }
                                 
                                 
                                }                           
                            }
                                 
                                 
                                   if (zahl.equals("00")) {
                 client.sendButlerMessage(client.getChannel().getName(), "Du hattest heute leider kein Glück. Versuche es morgen wieder.");
                client.setDailybonus(1);   
             } else {
               double old = client.getKnuddelscent()/100;               
               client.setKnuddelscent(client.getKnuddelscent()+Integer.parseInt(zahl));
               double neu = client.getKnuddelscent()/100;
                // knuddel erhalten
              int show = 1;
               if ((int)old != (int)neu) {
               client.sendButlerMessage(client.getChannel().getName(),"_Glückwunsch_, mit dem heutigen _Tageslogin_, hast du jetzt _einen ganzen Knuddel °>smileys/sm_classic_00.gif<°_ gesammelt! Dir wurde(n) °BB°_"+zahl+" Knuddelscent°r° gutgeschrieben_.");
            client.setKnuddels((int)client.getKnuddels()+1);
               show = 0;
               }
               if (show == 1) {
                   client.sendButlerMessage(client.getChannel().getName(),"Dir wurde(n) °BB°_"+zahl+" Knuddelscent°r° gutgeschrieben_.");
               }
               // smiley erhalten
               int ueber = 0;
               if (client.getKnuddelscent() > grenze && grenze != 0) {
               Smiley ss = Server.get().getSmiley2(nextname);
               client.sendButlerMessage(client.getChannel().getName(),"_Glückwunsch!_ Du hast mehr als "+grenze+" Knuddelscent eingesammelt und _erhälst jetzt folgenden Smiley:_ "+ss.getKCode()+" "+ss.getSyntax());
             client.setSmiley(String.valueOf(ss.getID()));
               ueber = 1;
               }
               
             // immmer
                Smiley ss = Server.get().getSmiley2(nextname);
                String more = "";
               if (ueber == 1) {
                    ss = Server.get().getSmiley2(nextname2);
                    grenze = grenze2;
               }
               if (ss != null) {
                   more += " Zum nächsten Smiley "+ss.getKCode()+" fehlen: _"+(grenze-client.getKnuddelscent())+" Knuddelscent_";
               }
               client.sendButlerMessage(client.getChannel().getName(),"Insgesamt _"+client.getKnuddelscent()+"_ Knuddelscent gesammelt."+more);
            //
               if (zahl.equals("11") || zahl.equals("22") || zahl.equals("33") || zahl.equals("44") || zahl.equals("55") || zahl.equals("66") || zahl.equals("77") || zahl.equals("88") || zahl.equals("99")) {
               client.sendButlerMessage(client.getChannel().getName(), zahl+" ist eine _Schnappszahl_, du darfst sofort _noch einmal_ spielen.");
             Random random = new Random();
             int randomInt = random.nextInt(9)+1;
             int randomInt2 = random.nextInt(9)+1;                  
            String zahls = ""+randomInt2+""+randomInt;                   
              client.setDailyZahl(zahls);
              
              String bla = "";
              
                 String bnextname = "";
                            String bnextname2 = "";
                             String bnextname3 = "";
                            for(String c : settings.split("\\|")) {
                                if (!c.isEmpty()) {
                                 String[] a = c.split("~");
                                if (Integer.parseInt(a[1]) > client.getKnuddelscent() && bnextname.isEmpty()) {
                                     bnextname = a[0];
                                } else  if (Integer.parseInt(a[1]) > client.getKnuddelscent() && bnextname2.isEmpty()) {
                                    bnextname2 = a[0];
                                }else  if (Integer.parseInt(a[1]) > client.getKnuddelscent() && bnextname3.isEmpty()) {
                                     bnextname3 = a[0];
                                }
                                 
                                 
                                }                           
                            }
                            if (!bnextname.isEmpty()) {
                                if (bnextname3.isEmpty()) {
                                 if (!bnextname2.isEmpty()) {
                                     Smiley s1 = Server.get().getSmiley2(bnextname);
                                      Smiley s2 = Server.get().getSmiley2(bnextname2);
                                     bla = " und gewinne Smileys wie "+s1.getKCode()+" und "+s2.getKCode();  
                                 } else {
                                       Smiley s1 = Server.get().getSmiley2(bnextname);
                                   bla = " und gewinne den Smiley "+s1.getKCode();  
                                   // nur 1
                                     }
                                } else {
                                       Smiley s1 = Server.get().getSmiley2(bnextname);
                                      Smiley s2 = Server.get().getSmiley2(bnextname2);                                       
                                      Smiley s3 = Server.get().getSmiley2(bnextname3);
                                  bla = " und gewinne Smileys wie "+s1.getKCode()+", "+s2.getKCode()+" und "+s3.getKCode();  
                                 
                                    
                                }   }
              
               
            client.sendButlerMessage(client.getChannel().getName(), "°>{challenge}<°Erspiele dir für den _Tageslogin_ jetzt _KnuddelCent_"+bla+"! °>loginknuddelscent/randombox_dark.png<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/randombox_button...clickchange.clicktoggleonce.w_0.h_0.mx_-123.gif|loginknuddelscent/randombox_button-hover...clickchange.clicktoggleonce.w_0.h_0.mx_-122.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_start...w_0.h_0.mx_-46.my_1.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_start...w_0.h_0.mx_-27.my_1.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_"+randomInt2+"_10...clickchange.clicktoggleonce.w_0.h_0.mx_-46.my_1.alwayscopy.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_"+randomInt+"_1...clickchange.clicktoggleonce.w_0.h_0.mx_-27.my_1.alwayscopy.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<°°>sm_abo_11-12_dollar-eyes...b.w_19.h_17.gif<°");
           client.setDailybonus(0);     
               } else {
                client.setDailybonus(1);   
                
                if (client.getFirstSmiley() ==0) {
                client.setHighlights(String.format("%s|%s Ersten Smiley erhalten|", client.getHighlights(), Server.get().timeStampToDate(time)));
                client.setFirstSmiley(1);
                }
                
               }}
                    
                             break;    
                             }
                             if (type == ActionType.TAGESLOGIN) {
                                
                                 try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                
                                  if(client == null) { break; }
                             
                                 
                                   Random random = new Random();
             int randomInt = random.nextInt(9)+1;
             int randomInt2 = random.nextInt(9)+1;             
            String zahl = ""+randomInt2+""+randomInt;                   
             client.setDailyZahl(zahl);
             
             String bla = "";
              
                 String bnextname = "";
                            String bnextname2 = "";
                             String bnextname3 = "";
                            for(String c : settings.split("\\|")) {
                                if (!c.isEmpty()) {
                                 String[] a = c.split("~");
                                if (Integer.parseInt(a[1]) > client.getKnuddelscent() && bnextname.isEmpty()) {
                                     bnextname = a[0];
                                } else  if (Integer.parseInt(a[1]) > client.getKnuddelscent() && bnextname2.isEmpty()) {
                                    bnextname2 = a[0];
                                }else  if (Integer.parseInt(a[1]) > client.getKnuddelscent() && bnextname3.isEmpty()) {
                                     bnextname3 = a[0];
                                }
                                 
                                 
                                }                           
                            }
                            if (!bnextname.isEmpty()) {
                                if (bnextname3.isEmpty()) {
                                 if (!bnextname2.isEmpty()) {
                                     Smiley s1 = Server.get().getSmiley2(bnextname);
                                      Smiley s2 = Server.get().getSmiley2(bnextname2);
                                     bla = " und gewinne Smileys wie "+s1.getKCode()+" und "+s2.getKCode();  
                                 } else {
                                       Smiley s1 = Server.get().getSmiley2(bnextname);
                                   bla = " und gewinne den Smiley "+s1.getKCode();  
                                   // nur 1
                                     }
                                } else {
                                
                                    
                                    Smiley s1 = Server.get().getSmiley2(bnextname);
                                      Smiley s2 = Server.get().getSmiley2(bnextname2);                                       
                                      Smiley s3 = Server.get().getSmiley2(bnextname3);
                                  bla = " und gewinne Smileys wie "+s1.getKCode()+", "+s2.getKCode()+" und "+s3.getKCode();  
                                 
                               
                                    
                                } }
             
                              client.sendButlerMessage(client.getChannel().getName(),"°>{challenge}<°Erspiele dir für den _Tageslogin_ jetzt _KnuddelCent_"+bla+"! °>loginknuddelscent/randombox_dark.png<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/randombox_button...clickchange.clicktoggleonce.w_0.h_0.mx_-123.gif|loginknuddelscent/randombox_button-hover...clickchange.clicktoggleonce.w_0.h_0.mx_-122.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_start...w_0.h_0.mx_-46.my_1.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_start...w_0.h_0.mx_-27.my_1.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_"+randomInt2+"_10...clickchange.clicktoggleonce.w_0.h_0.mx_-46.my_1.alwayscopy.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<>--<>loginknuddelscent/num_"+randomInt+"_1...clickchange.clicktoggleonce.w_0.h_0.mx_-27.my_1.alwayscopy.gif<>--<>|/doubleaction /playsound 9186543354290431621:pics/loginknuddelscent/randomgame-normal_001b.mp\\|/challenge -7525339034264614438 dailylogin<°°>sm_abo_11-12_dollar-eyes...b.w_19.h_17.gif<°"); 

                                break;
                            }

                             /* KnuddelsCent ENDE */
                             
if (type == ActionType.BAD6TIMEOUT) {
                                 Channel ch = Server.get().getChannel(channel);
                               
                                    try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                    // hier prüfen ob gelöscht wird
                                 
                                   
                                    if (delete.contains("|"+tid+"|")) {
                                      delete = delete.replace("|"+tid+"|","");
                                        break;
                                    }
                                   
                                    if (client.getBad6OK() == 1) {
                                  String gegner = infos[0];                            
                                 
                               
                                      Client target = Server.get().getClient(gegner);
                                    if (target != null && client != null) {
                                client.sendButlerMessage(ch.getName(),"Du hast dir zulange Zeit gelassen.");        
                                target.sendButlerMessage(ch.getName(),client.getName()+" hat sich zulange Zeit gelassen. Du erhälst 15 Punkte.");        
        target.setBad6(target.getBad6()+15);
        target.setBad6OK(0);
        target.setBad6User("");
        target.setBad6Ask("");      
                  //  Toolbar.sendButtons(target,ch.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
  // Toolbar.sendButtons(client,ch.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
        client.setBad6OK(0);
        client.setBad6User("");
        client.setBad6Ask("");            
                                    }                                                  
                                     
                                  }
                                    break;
                            } else if(type == ActionType.BAD6START) {
                           
                                     Channel ch = Server.get().getChannel(channel);
                                    try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                    Client target = Server.get().getClient(client.getBad6Gegner());
                                    if (client.getBad6OK() == 1) {
                                    client.sendButlerMessage(ch.getName(),"Ein neues Duell Bad6 zwischen "+client.getName()+" und "+target.getName()+" beginnt. Wer sich zuerst 50 Punkte sichert gewinnt dieses Spiel!");
                                    target.sendButlerMessage(ch.getName(),"Ein neues Duell Bad6 zwischen "+client.getName()+" und "+target.getName()+" beginnt. Wer sich zuerst 50 Punkte sichert gewinnt dieses Spiel!");
                                    client.sendButlerMessage(ch.getName(),target.getName()+" beginnt das Spiel.");
                                    target.sendButlerMessage(ch.getName(),"Du beginnst das Spiel. Würfel nun zuerst mit _°BB>_h/bad6 dice|\"<°_°r°");
                                    target.setBad6Dran(target.getName());
                                    client.setBad6Dran(target.getName());
                                    target.setBad6NachwurfPlayer(client.getName());
                                    client.setBad6NachwurfPlayer(client.getName());
                                    int uid = getNextId();
       
                                   client.setBad6Gameid(uid);
                                     target.setBad6Gameid(uid);
               //   Toolbar.sendButtons(target,ch.getName(),"Weiter|/bad6 dice||left~Topliste|/bad6 top||right~?|/h bad6||right");
  // Toolbar.sendButtons(client,ch.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
                   new Action(target.getName(),Action.ActionType.BAD6TIMEOUT,60,ch.getName(),new String[] { client.getName() },uid);
                                    }
                                 
                            break;
                             } else 
                             
                             
                             
                             
                             if (type == ActionType.LEIHEN) {
                                try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                            String toreturn = "";
                            for(Verliehen a : Server.get().getVerleih()) {
                                if (Long.parseLong(a.getBis()) <= (System.currentTimeMillis()/1000)) {
                                  toreturn += "|"+a.getUserSmileyID()+"|";  
                                  Usersmiley s = Server.get().getUsersmiley(a.getUserSmileyID());
                                  String[] w = s.getVerliehen().split("\\|");
                                  Client bla = Server.get().getClient(w[0]);
                                 if (bla == null) {
                                     bla = new Client(null); bla.loadStats(w[0]);
                                 }
                                 
                                 // leihen fehler
                                 String neu1 = bla.deleteSmiley(a.getUserSmileyID()+"");
                                  bla.setSmileys2(neu1);
                                 bla.saveStats();
                                  s.setVerliehen("");
                                 Client s1 = Server.get().getClient(a.getFrom());
                                 Client s2 = Server.get().getClient(a.getTo());
                                 if (s1 != null) {
                              //     ModuleCreator.UPDATE_SB(s1);
                                 }
                                  if (s2 != null) {
                                //     ModuleCreator.UPDATE_SB(s2); 
                                 }
                                  
                                  
                                }
                                
                            }
                            
                            
                            for(String d : toreturn.split("\\|")) {
                                if (!d.isEmpty()) {
                              
                               Server.get().removeVerliehen(d);  
                              Server.get().query("delete from `sm_verliehen` where usersmileyid='"+d+"'");
                            
                                
                                
                                }}
                                new Action(client.getName(),Action.ActionType.LEIHEN,2,null,null,0);   
break;
                             }
                             
                            if (type == ActionType.LEIHENTIMEOUT) {
                                try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                                                if (delete.contains("|SM2~"+client.getName()+"|")) {
                                      delete = delete.replace("|"+tid+"|","");
                                        break;
                                    }
                                 String[] d = Server.get().getLeihen(client.getName());
                   if (d[1].equals(infos[1])) {
                                if ((Integer.parseInt(d[8])+30) <= (System.currentTimeMillis()/1000)) {
                                      
                                    // smileys_to leer, dann nur kn von from back
                                    if (d[4].isEmpty()) {
                                        client.setKnuddels((int)client.getKnuddels()+Integer.parseInt(d[2]));
                                    } else if (d[9].equals("0") && !d[4].isEmpty()) {
                                        Client target = Server.get().getClient(d[3]);
                                     
                                        client.setKnuddels((int)client.getKnuddels()+Integer.parseInt(d[2]));                                  
                                            if (target != null) {
                                              target.setKnuddels((int)target.getKnuddels()+Integer.parseInt(d[5]));
                                            }
                                    } else {
                                        Client target = Server.get().getClient(d[3]);
                                     if (target != null) {
                                         if (d[7].equals("0")) {
                                             // fakeresult
                                             handler.CodeHandler.handle(new String[] {"code","ok2~"+client.getName(),"Verleihen ablehnen"},target);
                                         }
                                     }
                                     if (d[6].equals("0")) {
                                         // fakeresult
                                        handler.CodeHandler.handle(new String[] {"code","ok2~"+client.getName(),"Verleihen ablehnen"},client);
                                          }
                                         
                                        // ansonsten tausch ablehnen.
                                    }
                                    
                                     Server.get().removeLeihen(client.getName());
                                } else {
                                    new Action(client.getName(),Action.ActionType.LEIHENTIMEOUT,30,channel,new String[] {infos[0],infos[1] },0);   

                                }}
                                
                                break;
                            }
                            
                            if (type == ActionType.TAUSCHTIMEOUT) {
                                try {
                                            Thread.sleep((timeout*1000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                                                if (delete.contains("|SM~"+client.getName()+"|")) {
                                      delete = delete.replace("|"+tid+"|","");
                                        break;
                                    }
                                 String[] d = Server.get().getTausch(client.getName());
                   if (d[1].equals(infos[1])) {
                                if ((Integer.parseInt(d[8])+30) <= (System.currentTimeMillis()/1000)) {
                                      
                                    // smileys_to leer, dann nur kn von from back
                                    if (d[4].isEmpty()) {
                                        client.setKnuddels((int)client.getKnuddels()+Integer.parseInt(d[2]));
                                    } else if (d[9].equals("0") && !d[4].isEmpty()) {
                                        Client target = Server.get().getClient(d[3]);
                                     
                                        client.setKnuddels((int)client.getKnuddels()+Integer.parseInt(d[2]));                                  
                                            if (target != null) {
                                              target.setKnuddels((int)target.getKnuddels()+Integer.parseInt(d[5]));
                                            }
                                    } else {
                                        Client target = Server.get().getClient(d[3]);
                                     if (target != null) {
                                         if (d[7].equals("0")) {
                                             // fakeresult
                                             handler.CodeHandler.handle(new String[] {"code","ok~"+client.getName(),"Tausch ablehnen"},target);
                                         }
                                     }
                                     if (d[6].equals("0")) {
                                         // fakeresult
                                        handler.CodeHandler.handle(new String[] {"code","ok~"+client.getName(),"Tausch ablehnen"},client);
                                          }
                                         
                                        // ansonsten tausch ablehnen.
                                    }
                                    
                                     Server.get().removeTauschen(client.getName());
                                } else {
                                    new Action(client.getName(),Action.ActionType.TAUSCHTIMEOUT,30,channel,new String[] {infos[0],infos[1] },0);   

                                }}
                                
                                break;
                            }
         
                            
                                    
                            
                            if(type == ActionType.TUTORIALLOGIN) {
                                
                                 try {
                                            Thread.sleep((timeout*4000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                                   if(client == null) { break; }
                            }
                            
                     if (client.getTutorialsend() ==0) {
                             
                          
                              client.sendButlerMessage(client.getChannel().getName(), "Willkommen bei "+Server.get().getSettings("CHAT_NAME")+"!#Ich bin _°BB°°>"+Server.get().getButler().getName()+"|/w "+Server.get().getButler().getName()+"<°§, das Mädchen für alles! Und werde dir in den nächsten Minuten unseren Chat zeigen. Mach es dir gemütlich und schau dich in Ruhe bei uns um.");
                              client.setTutorialsend(1);
                             }
                              try {
                                            Thread.sleep((timeout*4000)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                            if(client == null) { break; }
                            
              
                                   
                                   
                       if (client.getTutorialsend() ==1) {
                             
                          
                              client.sendButlerMessage(client.getChannel().getName(), "Willkommen bei "+Server.get().getSettings("CHAT_NAME")+"!#Ich bin _°BB°°>"+Server.get().getButler().getName()+"|/w "+Server.get().getButler().getName()+"<°§ das Mädchen für alles!_  Und werde dir in den nächsten Minuten unseren Chat zeigen. Mach es dir gemütlich und schau dich in Ruhe bei uns um.");
                              client.setTutorialsend(2);
                             }
                             try {
                                            Thread.sleep((timeout*7500)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                         if(client == null) { break; }
                                   
                                   
                                   
                                   
                                   
                 if (client.getTutorialsend() ==2) {
                             
                             
                              client.sendButlerMessage(client.getChannel().getName(), "Nette Mädels & Jungs werden bei "+Server.get().getSettings("CHAT_NAME")+" oft angesprochen. Keine Angst, die meisten User sind ganz harmlos. Und allein _du entscheidest, mit wem du dich unterhalten möchtest._ Wenn du keine Lust auf ein Gespräch hast, dann ignorier deinen Gesprächspartner einfach.");
                              client.setTutorialsend(3);
                             }
                             try {
                                            Thread.sleep((timeout*7500)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                                  if(client == null) { break; }
                 
                 
               if (client.getTutorialsend() ==3) {
                            
                              client.sendButlerMessage(client.getChannel().getName(), "Gestalte _°BB°°>dein Profil|/w<°_§ individuell - lade doch jetzt ein schönes Foto von dir hoch. °BB°_°>Hier|/foto<°§ geht's zum Foto-Upload");
                              client.setTutorialsend(4);
                         
                             }
                              try {
                                            Thread.sleep((timeout*7500)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                                   if(client == null) { break; }
                                   
                      
                                  
               if (client.getTutorialsend() ==4) {
                             
                            
                              client.sendButlerMessage(client.getChannel().getName(), "Du kannst _°BB°°>in deinem Profil|/w<°_§ etwas über deine _Person_, deine _Hobbies_ und _was du magst und nicht magst_ erzählen. So wird dein Profil zu deiner persönlichen Visitenkarte# °BB°_°>Hier|/e<°§ kannst du dein Profil ausfüllen");
                              client.setTutorialsend(5);
                             }
                              try {
                                            Thread.sleep((timeout*7500)); // 1sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                              if(client == null) { break; }
                                   
                                   
                 
                                  
                    if (client.getTutorialsend() ==5) {
                                        
                              client.sendButlerMessage(client.getChannel().getName(), "Willst du _Bilder_ und _persönliche Infos_ von deinen Gesprächspartner sehen? überall im Chat kannst du das Profil anderer User öffnen, wenn du mit der _rechten Maustaste_ auf ihre Namen klickst. Das geht auch rechts in der Nickliste.");
                              client.setTutorialsend(6);
                             }
                              try {
                                            Thread.sleep((timeout*7500)); // 7 Sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                                   if(client == null) { break; }
                            
                                   
                                   
                
                                
                  if (client.getTutorialsend() ==6) {
                             
                           
                              client.sendButlerMessage(client.getChannel().getName(), "Bei "+Server.get().getSettings("CHAT_NAME")+" gibt es über _100 Channels_ zum Flirten, Spielen und Leute Kennenlernen.#Flirten im Channel Flirt - _°BB°°>da will ich hin!|/go flirt<°_§#WordMix spielen - _°BB°°>da will ich hin!|/go WordMix<°_§#Leute aus deiner Gegend, z.B in den Channels _°BB°°>Essen|/go Essen<°_§, _°BB°°>Hamburg|/go Hamburg<°_§");
                              client.setTutorialsend(7);
                             }
                              try {
                                            Thread.sleep((timeout*7500)); // 8sek
                                    } catch (InterruptedException e) {
                                    }
                                 
                                   if(client == null) { break; }
                            
                                   
                                                
          
                if (client.getTutorialsend() ==7) {
                       
                              client.sendButlerMessage(client.getChannel().getName(), "Willst du noch schneller Aktionen ausführen? Du kannst Befehle direkt in die Eingabezeile schreiben.#_/w NAME_ ruft das Profil(die '_w_hois') auf: °>"+Server.get().getButler().getName()+"|/w "+Server.get().getButler().getName()+"<°#_/p NAME:TEXT_ schreibt _p_rivat einen Text: _°>/p "+Server.get().getButler().getName()+":Ich schreibe dir|/p "+Server.get().getButler().getName()+":Ich schreibe dir<°_#_/go CHANNEL_ wechselt den Chatraum: °>/go Flirt|/go Flirt<°");
                              client.setTutorialsend(8);
                             }
                                  
                            
                    }
                    
            }
    }


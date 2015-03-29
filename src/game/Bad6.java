package game;
     
    import java.util.List;
    import java.util.Random;
    import java.util.*;
    import tools.popup.*;
    import tools.*;
    import starlight.*;
     
     
    public class Bad6 implements Game {
        private Channel channel;
     
        public Bad6(Channel channel) {
            this.channel = channel;
        }
     
       
        public static int countChars(String input, char toCount) {
                    int counter = 0;
     
                    for (char c : input.toCharArray()) {
                            if (c != toCount)
                                    continue;
                            counter++;
                    }
     
                    return counter;
            }
       
        public boolean parsePublicMessage(String message, Client client) {
                    return true;
        }
     
        public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
                    return true;
        }
     
        public void onLeave(Client client) {
            // spiel löschen
            if (client.getBad6OK() == 1) {
                             
                Client target = Server.get().getClient(client.getBad6Gegner());
                 target.sendButlerMessage(channel.getName(),client.getName()+" hat das Spiel verlassen. Du erhälst 10 Punkte.");        
                     
             target.setBad6(target.getBad6()+10);
            target.setBad6OK(0);
            target.setBad6User("");
            target.setBad6Ask("");        
            client.setBad6OK(0);
      Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
            client.setBad6User("");
            client.setBad6Ask("");  
             if (target != null) {
                 target.setBad6OK(0);
            target.setBad6User("");
            target.setBad6Ask("");
         Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
     
             }
            }
            client.setBad6OK(0);
            client.setBad6User("");
            client.setBad6Ask("");
       
        }
     
            public void onJoin(Client client) {
                       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
     
            }
     
            public boolean parseCommand(String message, Client client) {
            String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
            String cmd = command.toLowerCase();
            String arg = "";
            Random zufall = new Random();
           
            if (message.length() > cmd.length() + 1) {
                arg = message.substring(message.indexOf(' ') + 1);
            }
     
            int Anzahlspe = countChars(arg, '°');
           
            if (Anzahlspe == 1) {
                    String[] argus = arg.split("°", 2);
                    arg = argus[0];
            }
           
            if(!cmd.equals("bad6")) {
                    return true;
            }
           
            if (arg.equals("dice")) {
                if (client.getBad6OK() == 0) {
                    client.sendButlerMessage(channel.getName(),"Du hast derzeit keinen Gegner.");
                    return false;
                }
               if (!client.getBad6Dran().equals(client.getName())) {
                    client.sendButlerMessage(channel.getName(),"Du bist derzeit nicht an der Reihe.");
                    return false;
                }
               
                Client target = Server.get().getClient(client.getBad6Gegner());
               
                  Random xd = new Random();
           int erg = xd.nextInt(6)+1; //
                client.sendButlerMessage(channel.getName(),client.getName()+" würfelt W6 = _"+erg+"_");
                target.sendButlerMessage(channel.getName(),client.getName()+" würfelt W6 = _"+erg+"_");
                if (erg == 6) {
                    // ende
                    if (target.getBad6Points() >= 50) {
                 
                        int win = target.getBad6Points()-client.getBad6Points();
                  target.setBad6(target.getBad6()+win);
                        target.sendButlerMessage(channel.getName(),"_Du hast °GG°gewonnen°r°_!°##°°>center<°Endstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°°##°Du erhälst _"+win+" Punkte_.");
            client.sendButlerMessage(channel.getName(),"_Du hast leider °RR°verloren°r°_!°##°°>center<°Endstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
            target.setBad6OK(0);
            target.setBad6User("");
            target.setBad6Ask("");        
            client.setBad6OK(0);
            Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
                        Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
     
            client.setBad6User("");
            client.setBad6Ask("");
                        return false;
                    }
                   
                   
                   
                    client.setBad6Temp(0);
                 client.sendButlerMessage(channel.getName(),"_"+client.getName()+"_ hat die _°20R°Bad6_°r° getroffen. Alle in _dieser Runde_ erspielten Punkte sind verloren.°##°°>center<°Spielstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
              target.sendButlerMessage(channel.getName(),"_"+client.getName()+"_ hat die _°20R°Bad6_°r° getroffen. Alle in _dieser Runde_ erspielten Punkte sind verloren.°##°°>center<°Spielstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
                target.sendButlerMessage(channel.getName(),"Du bist nun dran. Würfel nun mit _°BB>_h/bad6 dice|\"<°_°r°!");
                               Toolbar.sendButtons(target,channel.getName(),"Weiter|/bad6 dice||left~Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
         
               client.setBad6Dran(target.getName());
               target.setBad6Dran(target.getName());
                client.sendButlerMessage(channel.getName(),target.getName()+" ist nun an der Reihe.");
             
                // alte löschen + neue setzen
               Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
               int uid = Action.getNextId();
               
                                       client.setBad6Gameid(uid);
                                         target.setBad6Gameid(uid);
                  // TIMEOUT STARTEN Info = target.getBad6Gameround()+1~target
              new Action(target.getName(),Action.ActionType.BAD6TIMEOUT,60,channel.getName(),new String[] {client.getName() },uid);  
             
             
              return false;
                }
                client.setBad6Temp(client.getBad6Temp()+erg);
               
                  if (target.getBad6Points() >= 50) {
               
                   if (target.getBad6Points() < (client.getBad6Points()+client.getBad6Temp())) {
                       client.setBad6Points(client.getBad6Points()+client.getBad6Temp());
                          int win = client.getBad6Points()-target.getBad6Points();
                             
                                  target.sendButlerMessage(channel.getName(),"_Du hast leider °RR°verloren°r°_!°##°°>center<°Endstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
            client.sendButlerMessage(channel.getName(),"_Du hast °GG°gewonnen°r°_!°##°°>center<°Endstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°°##°Du erhälst _"+win+" Punkte_.");
             client.setBad6(client.getBad6()+win);
            target.setBad6OK(0);
            target.setBad6User("");
            target.setBad6Ask("");        
            client.setBad6OK(0);
            Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
                       Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
     
            client.setBad6User("");
            client.setBad6Ask("");
                  return false;
                      } else if (target.getBad6Points() == (client.getBad6Points()+client.getBad6Temp())) {
                       
                          target.sendButlerMessage(channel.getName(),"Die Bad6-Runde endet mit einem Unentschieden!");
            client.sendButlerMessage(channel.getName(),"Die Bad6-Runde endet mit einem Unentschieden!");
            target.setBad6OK(0);
            target.setBad6User("");
            target.setBad6Ask("");        
            client.setBad6OK(0);
            Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
                        Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
     
            client.setBad6User("");
            client.setBad6Ask("");
                 return false;
                   }
                     
                  }
                 if (target.getBad6Points() >= 50) {
                client.sendButlerMessage(channel.getName(),"Du hast es noch nicht geschafft! °>{button} Weiter ||call|/bad6 dice|width|100|heigth|24<°");
                                  Toolbar.sendButtons(client,channel.getName(),"Weiter|/bad6 dice||left~Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
           
                 } else {
                client.sendButlerMessage(channel.getName(),"Möchtest du deine Punkte _°BB>_hsichern|/bad6 save<r°_ oder _°BB>_hweiterspielen|/bad6 dice<r°_?");
                               Toolbar.sendButtons(client,channel.getName(),"Weiter|/bad6 dice||left~Sichern|/bad6 save||left~Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
         
                 }
                return false;
               
            } else if (arg.equals("save")) {
                  if (client.getBad6OK() == 0) {
                    client.sendButlerMessage(channel.getName(),"Du hast derzeit keinen Gegner.");
                    return false;
                }
                  if (client.getBad6Temp() == 0) {
                      client.sendButlerMessage(channel.getName(),"Du musst erst Punkte erspielen, bevor du diese sichern kannst.");
                    return false;
                  }
                if (!client.getBad6Dran().equals(client.getName())) {
                    client.sendButlerMessage(channel.getName(),"Du bist derzeit nicht an der Reihe.");
                    return false;
                }
               
               
                Client target = Server.get().getClient(client.getBad6Gegner());
               
                if (target.getBad6Points() >= 50) {
                     client.sendButlerMessage(channel.getName(),"Du kannst nun nicht mehr sichern. Überbiete die Punkte von "+target.getName()+"!");
                    return false;
                   
                }
               
                client.setBad6Points(client.getBad6Points()+client.getBad6Temp());
                client.setBad6Temp(0);
                client.sendButlerMessage(channel.getName(),"_"+client.getName()+"_ sichert seine Punkte!°##°°>center<°Spielstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
                target.sendButlerMessage(channel.getName(),"_"+client.getName()+"_ sichert seine Punkte!°##°°>center<°Spielstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
               
                if (client.getBad6Points() >= 50 && client.getBad6NachwurfPlayer().equals(client.getName())) {
                  int win = client.getBad6Points()-target.getBad6Points();
                             
            target.sendButlerMessage(channel.getName(),"_Du hast leider °RR°verloren°r°_!°##°°>center<°Endstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°");
            client.sendButlerMessage(channel.getName(),"_Du hast °GG°gewonnen°r°_!°##°°>center<°Endstand:  _°BB20°"+client.getName()+"°r° "+client.getBad6Points()+":"+target.getBad6Points()+" °BB20°"+target.getName()+"°r°°#°°>left<°°##°Du erhälst _"+win+" Punkte_.");
            client.setBad6(client.getBad6()+win);
            target.setBad6OK(0);
            target.setBad6User("");
            target.setBad6Ask("");        
            client.setBad6OK(0);
            Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
          
            
                      Toolbar.sendButtons(target,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
      Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
     
            
            client.setBad6User("");
            client.setBad6Ask("");
           
                    return false;
                }
                if (client.getBad6Points() >= 50 && !client.getBad6NachwurfPlayer().equals(client.getName())) {
                target.sendButlerMessage(channel.getName(),"_Unter Druck!_ Du musst nun in dieser Runde versuchen den Punktestand von "+client.getName()+" zu überbieten. Würfel nun mit _°BB>_h/bad6 dice|\"<°_°r°!");
                client.sendButlerMessage(channel.getName(),"_Unter Druck!_ "+target.getName()+" muss nun versuchen deinen Punktestand in dieser Runde zu überbieten.");
                                Toolbar.sendButtons(target,channel.getName(),"Weiter|/bad6 dice||left~Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
           
                   
                } else {
               
                target.sendButlerMessage(channel.getName(),"Du bist nun dran. Würfel nun mit _°BB>_h/bad6 dice|\"<°_°r°!");
                               Toolbar.sendButtons(target,channel.getName(),"Weiter|/bad6 dice||left~Topliste|/bad6 top||right~?|/h bad6||right");
       Toolbar.sendButtons(client,channel.getName(),"Topliste|/bad6 top||right~?|/h bad6||right");
         
                client.sendButlerMessage(channel.getName(),target.getName()+" ist nun an der Reihe.");
               
                }
                client.setBad6Dran(target.getName());
               target.setBad6Dran(target.getName());
               Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
               int uid = Action.getNextId();
             
                                       client.setBad6Gameid(uid);
                                         target.setBad6Gameid(uid);
                                         
           
                  // TIMEOUT STARTEN Info = target.getBad6Gameround()+1~target
             new Action(target.getName(),Action.ActionType.BAD6TIMEOUT,60,channel.getName(),new String[] { client.getName() },uid);
                  return false;
               
               
            } else if (arg.equals("ok")) {
            if (client.getBad6User().isEmpty()) {
                  client.sendButlerMessage(channel.getName(),"???");
            } else {
         
               
                  if (client.getBad6OK() == 0) {
                    Client target = Server.get().getClient(client.getBad6User());
           
            if (target != null) {
            client.sendButlerMessage(channel.getName(),"Du hast die Herausforderung angenommen. Das Spiel beginnt in Kürze...");
            target.sendButlerMessage(channel.getName(),client.getName()+" hat deine Herausforderung angenommen. Es geht gleich los...");
           
            target.setBad6OK(1);
            target.setBad6Points(0);
            target.setBad6Temp(0);
            client.setBad6Points(0);
            client.setBad6Temp(0);
            client.setBad6OK(1);
           
            client.setBad6Gegner(target.getName());
            target.setBad6Gegner(client.getName());
            new Action(client.getName(),Action.ActionType.BAD6START,2,channel.getName(),null,0);  
                       
            } else {
            client.sendButlerMessage(channel.getName(),"Dein Gegner ist bereits offline.");
             client.setBad6User("");
               
            }} else {
            client.sendButlerMessage(channel.getName(),"Du hast die Herausforderung bereits angenommen.");
           
                    }
               
               
            }
            return false;
        }
           if (arg.equals("no")) {
                if (client.getBad6User().isEmpty()) {
                  client.sendButlerMessage(channel.getName(),"???");
            } else {
                    if (client.getBad6OK() == 0) {
                    Client target = Server.get().getClient(client.getBad6User());
            client.sendButlerMessage(channel.getName(),"Du hast die Herausforderung abgelehnt.");
            client.setBad6User("");
            if (target != null) {
            target.sendButlerMessage(channel.getName(),client.getName()+" hat deine Herausforderung abgelehnt.");
            target.setBad6Ask("");
            }} else {
            client.sendButlerMessage(channel.getName(),"Du hast die Herausforderung bereits angenommen.");
           
                    }
                }
            return false;
        }
              String nickname = KCodeParser.escape(arg);
          boolean online = true;
           Client target = Server.get().getClient(nickname);
           if (target == null) {
             online = false;
            target = new Client(null);
            target.loadStats(nickname);
          }
           if (target.getName() == null){
               client.sendButlerMessage(channel.getName(),"Wer soll den "+arg+" sein?");
               return false;
           }
           if (!online) {
                client.sendButlerMessage(channel.getName(),target.getName()+" ist offline!");
               return false;
           }
           if (target == Server.get().getButler()) {
               client.sendButlerMessage(channel.getName(),String.format("Du kannst %s nicht herausfordern.",target.getName()));
                    return false;
           }
               if (!channel.getClients().contains(target)) {
                    client.sendButlerMessage(channel.getName(),String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.",target.getName()));
                    return false;
            }
               if (client == target) {
                   client.sendButlerMessage(channel.getName(),"Du kannst dich nicht selbst herausfordern.");
                 
                   return false;
               }
               if (client.getBad6OK() == 1) {
                  client.sendButlerMessage(channel.getName(),"Du bist bereits mitten im Spiel.");
                    return false;
               }
                if (target.getBad6OK() == 1) {
                    client.sendButlerMessage(channel.getName(),"Dein Gegner ist bereits mitten im Spiel.");
               return false;
                }
               
               
                 if (client.getBad6AskStart()+60 < System.currentTimeMillis()/1000) {
                    client.setBad6Ask("");
                    client.setBad6User("");
                    Client old = Server.get().getClient(client.getBad6Ask());
                    Client old2 = Server.get().getClient(client.getBad6User());
                     
                    if (old != null) {
                        old.setBad6Ask("");
                        old.setBad6User("");
                    }
                      if (old2 != null) {
                        old2.setBad6Ask("");
                        old2.setBad6User("");
                    }
                   
                   
                 }
                  if (target.getBad6AskStart()+60 < System.currentTimeMillis()/1000) {
                   
                       target.setBad6Ask("");
                    target.setBad6User("");
                    Client old = Server.get().getClient(target.getBad6Ask());
                    Client old2 = Server.get().getClient(target.getBad6User());
                     
                    if (old != null) {
                        old.setBad6Ask("");
                        old.setBad6User("");
                    }
                      if (old2 != null) {
                        old2.setBad6Ask("");
                        old2.setBad6User("");
                    }
                     
                 }
               
                 if (!client.getBad6User().isEmpty()) {
                     client.sendButlerMessage(channel.getName(),"Du wurdest bereits von jemanden herausgefordert.");
              return false;
                 }
                  if (!target.getBad6User().isEmpty()) {
               client.sendButlerMessage(channel.getName(),"Dein Gegner wurde bereits von jemanden herausgefordert.");
            return false;
               }
                   if (!target.getBad6Ask().isEmpty()) {
                 
             client.sendButlerMessage(channel.getName(),"Dein Gegner hat bereits jemanden herausgefordert.");
            return false;
               }
                    if (!client.getBad6Ask().isEmpty()) {
                          client.sendButlerMessage(channel.getName(),"Du hast bereits jemanden herausgefordert.");
              return false;
               }
               
               
               client.sendButlerMessage(channel.getName(),"Ich habe "+target.getName()+" deine Herausforderung geschickt.");
               target.sendButlerMessage(channel.getName(),client.getName()+" möchte eine Runde Bad6 mit dir spielen. _Bist du dabei?_ °>{button} Annehmen ||call|/bad6 ok|width|100|heigth|24<° °>{button} Ablehnen ||call|/bad6 no|width|100|heigth|24<°");
               client.setBad6Ask(target.getName());
               target.setBad6User(client.getName());
               client.setBad6OK(0);
               target.setBad6OK(0);
               Action.delete = Action.delete+"|"+String.valueOf(client.getBad6Gameid())+"|";
             
               client.setBad6AskStart(System.currentTimeMillis()/1000);
              target.setBad6AskStart(System.currentTimeMillis()/1000);
                   
               
                   
                   
               return false;
           
            }
    }


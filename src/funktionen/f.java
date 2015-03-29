package funktionen;

import java.util.Arrays;
import starlight.*;
import tools.*;
import tools.popup.*;


public class f {
  public static Long time = System.currentTimeMillis()/1000; 
      public static void functionMake(Client client,Channel channel, String arg) {
     if(!client.hasPermission("cmd.f")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
           int maxFriends = 40;
            int currentFriends = (int)Server.countChars(client.getFriendlist(), '|')/2;
            
            if(client.getRank() > 2) {
            	maxFriends = 100;
            } else if(client.getRank() == 2) {
            	maxFriends = 60;
            }
          
          if (arg.isEmpty()) {
              
                if(client.getFriendlist().isEmpty()) {
                	client.sendButlerMessage(channel.getName(), "Du hast niemanden auf deiner Freundesliste stehen! (Mit _/f NICK_ kannst du jemanden hinzufügen)");
                    return;
                }
                
              StringBuilder f = new StringBuilder();
                StringBuilder on = new StringBuilder();    
                String offvalue = "";
                String eingabe = client.getFriendlist().replace("||", "~").replace("|", "");
                String[] strarr = eingabe.split("~");
                
                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                	String nick = strarr[i];
        			Client c = Server.get().getClient(nick);
        			int readmeZahl = 0; ///bug, changen.
                	String charNick = nick.replace("<", "\\<");
        			
        				if(c == null) {
                                            
                                            //off start
        					c = new Client(null);
        					c.loadStats(nick);
        					
                                                if (c.getName() == null) {
                                                   // von der /f löschen
                                              client.setFriendlist(client.getFriendlist().replace("|"+nick+"|",""));
                                                } else {
                                                
        					if(c.getLastOnlineChannel() == null) {
                                offvalue += "%~~%#°>py_r.gif<°°%06°_°>_h"+charNick+"|/serverpp \"|/w \"<r°_ war niemals im Chat online°%00°|~~|~~|999999999999999%~~%";   
                            } else {
                                Channel n = Server.get().getChannel(c.getLastOnlineChannel());
                                StringBuilder anzeige = new StringBuilder();
                                
                                long sek = time-c.getLastOnline();
                                long min = sek/60;
                                long std = min/60;
                                long restmin = min-std*60;
                                long days = std/24;
                                long reststd = std-days*24;
                                        
                                if(sek < 60) {
                                	anzeige.append("gerade");
                                } else {
                                	anzeige.append("vor");
                                	
                                	if(days > 0) {
                                		anzeige.append(" ").append(days).append(" Tag");
                                		if(days > 1) {
                                			anzeige.append("en");
                                		}
                                	}
                                	
                                	if(reststd > 0) {
                                		anzeige.append(" ").append(reststd).append(" Stunde");
                                		if(reststd > 1) {
                                			anzeige.append("n");
                                		}
                                	}
                                	
                                	if(restmin > 0) {
                                		anzeige.append(" ").append(restmin).append(" Minute");
                                		if(restmin > 1) {
                                			anzeige.append("n");
                                		}
                                	}
                                }
                                	
                                
                                        
                                offvalue += "%~~%#°>py_r.gif<°°%06°_°>_h"+charNick+"|/serverpp \"|/w \"<r°_ war _"+anzeige+"_ im Channel _";
                                
                                if(n == null || !n.isVisible()) {
                                	offvalue += "?";
                                } else {
                                	offvalue += "°>_h"+n.getName()+"|/go \"|/go +\"<°";
                                }
                                
                                offvalue += "_";
        					
                                if(c.getReadme() != null) {
                                	offvalue += "#°%06°\"\""+c.getReadme()+"§\"\"";
                                	
                                	if(readmeZahl > 1) {
                                		offvalue += "°>_h[His]|/readmehis "+charNick+"<°";
                                	}
                                }
        					
                                offvalue +="°%00°|~~|~~|"+sek+"%~~%";
                            }}
        				
                                                
                                     // off ende           
                                                
                                                
                                                
                                                
                                                
        				} else {
        					on.append("#°>py_g.gif<°°%06°_°>_h").append(charNick).append("|/serverpp \"|/w \"<°_ ist im Channel _");
        					
        					if(c.getChannel().isVisible()) {
        						on.append("°>_h").append(c.getChannel().getName()).append("|/go \"|/go +\"<°");
        					} else {
        						on.append("?");
        					}
        					
        					on.append("°E° ONLINE_°r°!");
        					
                            if(c.isAway()) {
                            	if(c.getRank() > 7) {
                                	on.append(" °>icon_away_ani_new.gif<°");
                            	}else {
                            		on.append(" °>away.png<°");
                            	}
                            }
        					
        					if(c.getReadme() != null) {
        						on.append("#°%06°\"\"").append(c.getReadme()).append("§\"\"");
        			           	
                            	if(readmeZahl > 1) {
                            		on.append(" °>_h[His]|/readmehis ").append(charNick).append("<°");
                            	}
                            }
        					
        					on.append("°%00°");
        				}
        			}
                
                
                StringBuilder off = new StringBuilder();                
                String offs = Server.getSortFriendsoffline(offvalue);                
                off.append(offs);
                f.append(on.toString()).append(on.toString().isEmpty()?"":"#").append(off.toString());
                f.append("##Auf deiner Liste stehen im Moment _").append(currentFriends).append("_ von ").append(maxFriends).append(" möglichen Freunden.");
             
                
                String title = "Friendlist";
            Popup popup = new Popup(title, title, f.toString(), 575, 325);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);                
                 popup.addPanel(panel);
                 popup.setWatchlistpopup(1);
                 client.send(popup.toString());
return;         
          }
          
             String nick;
         
if (arg.startsWith("-")) {
     nick = arg.substring(1).trim();
       Client target = Server.get().getClient(nick);
            if(target == null) {
                target = new Client(null);
                target.loadStats(nick);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", nick));
                    return;
                }
            }
            
    
    if (client.checkFriendask(target.getName())) {
    
      client.sendButlerMessage(channel.getName(),"Du hast die Freundschaftsanfrage von "+target.getName()+" abgelehnt.");
    
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Freundschaftsanfrage abgelehnt", "Hallo "+target.getName()+"##"+client.getName()+" hat deine Freundschaftsanfrage abgelent.", (System.currentTimeMillis()/1000)); 
             
            
      String neu = "";
      for(String v : client.getFriendask().split("\\|")) {
         if (v.isEmpty()) { 
          if (!v.contains(target.getName())) {
              neu += "|"+v+"|";
          }
      }}
      client.setFriendask(neu);
     return;
    }
    
    client.sendButlerMessage(channel.getName(),target.getName()+" hat dir keine Freundschaftsanfrage gesendet.");
    return;
}
if (arg.startsWith("+")) {
    nick = arg.substring(1).trim();
       Client target = Server.get().getClient(nick);
            if(target == null) {
                target = new Client(null);
                target.loadStats(nick);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", nick));
                    return;
                }
            }
            
    
    if (client.checkFriendask(target.getName())) {
    
        
      client.sendButlerMessage(channel.getName(),"Du hast die Freundschaftsanfrage von "+target.getName()+" angenommen.");
       
      
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(),"Freundschaftsanfrage angenommen","Hallo "+target.getName()+",##soeben hat _°BB°°>"+client.getName()+"|/m|/w<°°r°_ deine Freundschaftsanfrage angenommen.#Du kannst °BB°_°>_hdeine Freunde jetzt verwalten|/showfriends<°_°r°.##Dein "+Server.get().getButler().getName(), (System.currentTimeMillis()/1000)); 
         
      String neu = "";
      for(String v : client.getFriendask().split("\\|")) {
         if (!v.isEmpty()) { 
          if (!v.contains(target.getName())) {
              neu += "|"+v+"|";
          }
      }}
      client.setFriendask(neu);
      if (!client.getFriendlist().contains("|"+target.getName()+"|")) {
      client.setFriendlist(client.getFriendlist()+"|"+target.getName()+"|");
      }
      if (!target.getFriendlist().contains("|"+client.getName()+"|")) {
      target.setFriendlist(target.getFriendlist()+"|"+client.getName()+"|");
         }
      target.saveStats();
      if(target.getName().equals(client.getMentor())) {
        		if(client.getMpFriendlist() == 0) {
        			client.setMpFriendlist((byte)1);
        		}
        	}
      
       if(client.getName().equals(target.getMentor())) {
        		if(target.getMpFriendlist() == 0) {
        			target.setMpFriendlist((byte)1);
        		}
        	}
      target.saveStats();
     return;
    }
    
    client.sendButlerMessage(channel.getName(),target.getName()+" hat dir keine Freundschaftsanfrage gesendet.");
    return;
}
             
           if (arg.startsWith("!")) {
              nick = arg.substring(1).trim();
                Client target = Server.get().getClient(nick);
            if(target == null) {
                target = new Client(null);
                target.loadStats(nick);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", nick));
                    return;
                }
            }
            
            
            
              if(client.checkFriend(target.getName())) {
            
                	client.sendButlerMessage(channel.getName(), String.format("%s steht nun nicht mehr auf deiner Freundesliste!", target.getName()));
            		client.setFriendlist(client.getFriendlist().replace(String.format("|%s|", target.getName()), ""));
            		target.setFriendlist(target.getFriendlist().replace(String.format("|%s|", client.getName()), ""));
            		target.saveStats();
                        return;
            }
              
              
              client.sendButlerMessage(channel.getName(), String.format("%s steht nicht auf deiner Freundesliste!", target.getName()));
            	
              
           return;
              } 
            nick = arg;
          Client target = Server.get().getClient(nick);
            if(target == null) {
                target = new Client(null);
                target.loadStats(nick);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", nick));
                    return;
                }
            }
           
if (target == client) {
      client.sendButlerMessage(channel.getName(), "Du willst dein eigener Freund sein?!");
      return;
}
if(target.getName().equals(Server.get().getButler().getName())) {
            
client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht auf deine Freundesliste nehmen.", target.getName()));
            	return;
        	}
        	
        
          
          if(client.checkFriend(target.getName())) {
            	client.sendButlerMessage(channel.getName(), String.format("%s steht schon auf deiner Freundesliste!", target.getName()));
                return;
         }
          
           if(target.checkFriendask(client.getName())) {
            	client.sendButlerMessage(channel.getName(), String.format("%s hat bereits eine Freundschaftsanfrage erhalten.", target.getName()));
                return;
         }
           
            if(client.checkIgnored(target.getName())) {
            	client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht zu deiner Freundesliste hinzufügen, da du %s ignorierst.", target.getName(), target.getName()));
            	return;
            }
            
            if(currentFriends > maxFriends) {
            	client.sendButlerMessage(channel.getName(), String.format("Du kannst maximal %s Nicks auf deine Friendlist nehmen, mehr ist nicht möglich.", maxFriends));
            	return;
            }
         
            
           client.sendButlerMessage(channel.getName(),"Deine Anfrage an "+target.getName()+" wurde soeben gesendet."); 
            target.setFriendask(target.getFriendask()+"|"+client.getName()+"~"+time+"|");
            target.saveStats();
            Server.get().newMessage(Server.get().getButler().getName(), target.getName(),"Freundschaftsanfrage von "+client.getName(),"°BB°_°>"+client.getName()+"|/m|/w<°°r°_ möchte gern mit dir _befreundet_ sein:##''Hallo "+target.getName()+", ich würde mich freuen, dich als Freund meinem neuen Profil hinzufügen#zu können.''##°%25°°>{button}Annehmen||call|/f +"+client.getName()+"<° °>{button}Ablehnen||call|/f -"+client.getName()+"<° °>{button}Anfragenübersicht||call|/showfriends<°°%02°##°%02°Dein "+Server.get().getButler().getName(), (System.currentTimeMillis()/1000)); 
                  
            
      }
}

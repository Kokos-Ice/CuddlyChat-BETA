package funktionen;

import starlight.Channel;
import starlight.Client;
import static starlight.CommandParser.unknownUser;
import starlight.Server;
import static starlight.Server.countChars;
import tools.KCodeParser;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;


public class sug {
    
     
public static String firstCharUpperCase(String str) {
return str.substring(0, 1).toUpperCase() + str.substring(1);
}

       public static void functionMake(Client client,Channel channel, String arg) {
 
           
           
           if(!client.hasPermission("cmd.sug")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	String election = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String name = "";
            
            if (arg.length() > election.length()) {
                name = arg.substring(arg.indexOf(':') + 1);
            }
        	
            boolean accept = false;
            boolean deny = false;
            
            if(!election.isEmpty()) {
            	if(election.startsWith("+")) {
            		accept = true;
            		
            		election = election.substring(1);
            	} else if(election.startsWith("-")) {
            		deny = true;
            		
            		election = election.substring(1);
            	}
            }
            
            if(accept) {
                
                   
                if(!Server.elections.containsKey(election)) {
            			client.sendButlerMessage(channel.getName(), String.format("Die Wahl _%s gibt es nicht_.", election));
            			return;
            		}
                String[] val = Server.elections.get(election);
                if (val[0].equals("0") || val[0].equals("3")) {
                    client.sendButlerMessage(channel.getName(), String.format("Die %s-Wahl ist leider schon vorüber.", arg));
               return; 	
                }
                if (val[0].equals("2")) {
                   client.sendButlerMessage(channel.getName(),"Momentan läuft die Wahlphase.");
                    return;
                }   
                
                if (!val[6].contains("~"+client.getName()+"|")) {
                    client.sendButlerMessage(channel.getName(),"Du bist für diese Wahl nicht vorgeschlagen worden.");
                    return;
                }
                
                
                String neu = "";
                int from = 0;
                for(String x : val[6].split("\\|")) {
                    if (!x.trim().isEmpty()) {
                        
                     if (x.endsWith("~"+client.getName())) {
                         from++;
                         neu += "|"+x.split("~")[0]+"~|";
                     } else {
                         neu += "|"+x+"|";
                     }  
                    }                   
                    
                }
               if (neu.isEmpty()) {
                   neu = " ";
               }
               
           
               String neu2 = val[4].trim()+"|"+client.getName()+"~"+from+"|";
                
                Server.get().query("update wahlen set nominated='"+neu2+"', suglist='"+neu+"' where name='"+election+"'");
                Server.elections.put(election,new String[]{ val[0],val[1],val[2],val[3],neu2,val[5],neu});
                
            	client.sendButlerMessage(channel.getName(), "Du hast die Nominierung angenommen.");
            	return;
            }
            
            if(deny) {
                
                   
                if(!Server.elections.containsKey(election)) {
            			client.sendButlerMessage(channel.getName(), String.format("Die Wahl _%s gibt es nicht_.", election));
            			return;
            		}
                String[] val = Server.elections.get(election);
                if (val[0].equals("0") || val[0].equals("3")) {
                    client.sendButlerMessage(channel.getName(), String.format("Die %s-Wahl ist leider schon vorüber.", arg));
               return; 	
                }
                if (val[0].equals("2")) {
                   client.sendButlerMessage(channel.getName(),"Momentan läuft die Wahlphase.");
                    return;
                }   
                
                
                 if (!val[6].contains("~"+client.getName()+"|")) {
                    client.sendButlerMessage(channel.getName(),"Du bist für diese Wahl nicht vorgeschlagen worden.");
                    return;
                }
                
                     String neu = "";
              
                for(String x : val[6].split("\\|")) {
                    if (!x.trim().isEmpty()) {
                        
                     if (!x.endsWith("~"+client.getName())) {     
                          neu += "|"+x.split("~")[0]+"~|";
                     }else {
                   
                         neu += "|"+x+"|";
                     }  
                    }                   
                    
                }
               if (neu.isEmpty()) {
                   neu = " ";
               }
               
                    Server.get().query("update wahlen set suglist='"+neu+"' where name='"+election+"'");
                Server.elections.put(election,new String[]{ val[0],val[1],val[2],val[3],val[4],val[5],neu});
              
            	client.sendButlerMessage(channel.getName(), "Du hast die Nominierung abgelehnt.");
            	return;
            }
            
            if(election.isEmpty() || !election.isEmpty() && name.isEmpty()) {
            	StringBuilder sug = new StringBuilder();
        	
            	if(election.isEmpty()) {
            		for(String wahl : Server.elections.keySet()) {
            			String[] more = Server.elections.get(wahl);
            			int aktiv = Integer.parseInt(more[0]);
        		String start = more[1];
                        String ende = more[3];
                        int nomizahl = countChars(more[4],'|')/2;
            			if(aktiv == 1) {
            				String nominated = more[4].trim();
            				sug.append("_").append(firstCharUpperCase(wahl)).append("_ ("+Server.get().timeStampToDate(Long.parseLong(start))+" - "+Server.get().timeStampToDate(Long.parseLong(ende))+")#("+nomizahl+" Kandidaten)#Kandidatenliste:##");
            		
            				for(String x : nominated.split("\\|")) {
            					if(!x.isEmpty()) {
                                                    if (x.contains("~")) {
            						String[] split = x.split("~");
            						String nick = split[0];
            						String nominiert = split[1];
            				
            						sug.append(nick).append(" (nom. von ").append(nominiert).append(")#");
            					}}
            				}
            			}
            		} 
            	} else { 
            		if(!Server.elections.containsKey(election)) {
            			client.sendButlerMessage(channel.getName(), String.format("Die Wahl _%s gibt es nicht_.", election));
            			return;
            		}
            		
            		String[] more = Server.elections.get(election);
        			int aktiv = Integer.parseInt(more[0]);
    		
        			if(aktiv == 1 || aktiv == 2) {
        				String nominated = more[4].trim();
        				sug.append("_").append(election).append("_#");
        		
        				for(String x : nominated.split("\\|")) {
        					if(!x.isEmpty()) {
        						String[] split = x.split("~");
        						String nick = split[0];
        						String nominiert = split[1];
        				
        						sug.append(nick).append(" (nom. von ").append(nominiert).append(")#");
        					}
        				}
        			}
            	}
        	
            
            	if(sug.toString().isEmpty()) {
            		client.sendButlerMessage(channel.getName(), "Derzeit laufen keine Nominierungen oder es wurde in der aktuellen Nominierungsphase noch niemand nominiert..");
            		return;
            	}
            
                
                
             	Popup popup = new Popup("Nominierungen", "Nominierungen", sug.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
            }
            
            if(!name.isEmpty()) {
                
                boolean delete = false;
                if (name.startsWith("!") && !name.equals("!") && client.getRank() >= 10) {
                    delete = true;
                    name = name.substring(1);                    
                }
                
                
                
                if(!Server.elections.containsKey(election)) {
            			client.sendButlerMessage(channel.getName(), String.format("Die Wahl _%s gibt es nicht_.", election));
            			return;
            		}
                String[] val = Server.elections.get(election);
                if (val[0].equals("0") || val[0].equals("3")) {
                    client.sendButlerMessage(channel.getName(), String.format("Die %s-Wahl ist leider schon vorüber.", arg));
               return; 	
                }
                
                
               
                
                if (val[0].equals("2")) {
                   client.sendButlerMessage(channel.getName(),"Momentan läuft die Wahlphase.");
                    return;
                } 
                
                
                
              
                
                Client target = Server.get().getClient(name);
                if (target == null) {
                    target = new Client(null);
                    target.loadStats(name);
                }
                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(),unknownUser(name));
                    return;
                }
                
                
                
                if (delete) {
                       if (!val[4].contains("|"+target.getName()+"~")) {    
                         client.sendButlerMessage(channel.getName(),target.getName()+" steht nicht auf der Nominierungsliste.");
                           return;
                     } 
                          client.sendButlerMessage(channel.getName(),target.getName()+" wurde von der Nominierungsliste entfernt.");
                        
                          String neu = "";
                          for(String x : val[4].split("\\|")) {
                              if (!x.isEmpty()) {
                                  
                                  if (!x.startsWith(target.getName()+"~")) {
                                      neu += "|"+x+"|";
                                  }
                              }
                              
                          }
                                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Nominierung gelöscht.", "Du wurdest von "+client.getName()+" für die aktuelle Adminwahl ausgeschlossen.##Dies kann unter anderem aus folgenden Gründen sein:##-GRUND#-GRUND2#-GRUND3##Viele Grüße,#"+Server.get().getButler().getName(),  (System.currentTimeMillis()/1000)); 
         
                          Server.get().query("update wahlen set nominated='"+neu+"' where name='"+election+"'");
                Server.elections.put(election,new String[]{ val[0],val[1],val[2],val[3],neu,val[5],val[6]});
                          return;
                         
                     }
                    
                    
               
                
                if (val[6].contains("|"+client.getName()+"~") && client.getRank() < 10) { 
                    client.sendButlerMessage(channel.getName(),"Du hast bereits jemanden nominiert");
                    return;
                } 
                
                if (client == target) {
                    client.sendButlerMessage(channel.getName(),"Du kannst dich nicht selbst nominieren.");
                    return;
                }
                
               /*
                if (client.getRank() < 10) {
                if (client.getLC().isEmpty() || client.getLcmonths() == 0) {
                 // man darf net weil man kein lc hat  oder kein monat lc  
              return;
                }
               
                    
                  if (!client.getLC().equals(target.getLC())) {
                    // andere nick hat anderen lc ^^
                      return;
                }
                }
                
                
                  if (target.getLcmonths() < 2) {
                      // andere nick hat zuwenig lcmonate
                      return;
                  }
                       */
                
                if (target.getRank() < 2 || target.getRank() >= 7) {
                    client.sendButlerMessage(channel.getName(),target.getName()+" kann nicht nominiert werden.");
                    return;
                }
                
                 if(target.getWahlsperre() != 0) {
                   client.sendButlerMessage(channel.getName(), target.getName()+" ist derzeit für die Adminwahlen gesperrt und kann nicht nominiert werden.");
                   return;
                }
                
                
            	client.sendButlerMessage(channel.getName(), "Ich werden "+target.getName()+" wegen der Nominierung fragen.");
              // m an target
                if (val[6].contains("~"+target.getName()+"|")) {
                    // ebenfalls nominiert
                     if (val[4].contains("|"+target.getName()+"~")) {
                          // bereits angenommen
                           Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Nominierung", ""+client.getName()+" hat dich ebenfalls nomiert.", (System.currentTimeMillis()/1000)); 
         
                     } else {
                         // bereits nominiert aber noch net angenommen
                           Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Nominierung", "Du wurdest ebenfalls von "+client.getName()+" nominiert. Wahl annehmen mit /sug +"+election+" und Wahl ablehnen mit /sug -"+election, (System.currentTimeMillis()/1000)); 
         
                     }
                } else {
                    // erstmalig
                      Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Nominierung", "Du wurdest von "+client.getName()+" nominiert. Wahl annehmen mit /sug +"+election+" und Wahl ablehnen mit /sug -"+election, (System.currentTimeMillis()/1000)); 
         
                    
                }
                          
                
                if (val[4].contains("|"+target.getName()+"~")) {
                    String neu = "";
                    int old = 0;
                    for(String x : val[4].split("\\|")) {
                        if (!x.trim().isEmpty()) {
                            if (x.startsWith(target.getName()+"~")) {
                              old = Integer.parseInt(x.split("~")[1]);                              
                            } else {
                                neu += "|"+x+"|";
                            }
                        }
                        
                    }
                    
                    neu = neu+"|"+target.getName()+"~"+(old+1)+"|";
                   
                    String neu2 = val[6].trim()+"|"+client.getName()+"~|";
                     
                    
                    Server.get().query("update wahlen set suglist='"+neu2+"',nominated='"+neu+"' where name='"+election+"'");
                Server.elections.put(election,new String[]{ val[0],val[1],val[2],val[3],neu,val[5],neu2});
                } else {
                String neu = val[6].trim()+"|"+client.getName()+"~"+target.getName()+"|";
                Server.get().query("update wahlen set suglist='"+neu+"' where name='"+election+"'");
                Server.elections.put(election,new String[]{ val[0],val[1],val[2],val[3],val[4],val[5],neu});
                }
                return;
            }
       }
}

package funktionen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintStream;
import java.text.*;
import java.util.*;
import starlight.*;
import java.util.regex.*;
import handler.*;
import tools.*;
import tools.popup.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;




public class fa {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
     
        if(!client.hasPermission("cmd.fa") && !channel.checkCm(client.getName()) && !channel.checkHz(client.getName())) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
             String sort = "";
             if (arg.equals(":sort:0")) {
                 arg = "";
                sort = "sort:0";              
             } else if (arg.equals(":sort:1")) {
                 arg = "";
                sort = "sort:1";
           
             } else if (arg.endsWith(":sort:0") || arg.endsWith(":sort:1")) {             
                 sort = "sort:"+arg.split(":")[2];
                 arg = arg.split(":")[0];
                 
             }

                        
           
              
             if(sort.equals("sort:0")) {
        	client.setFaSort((byte)0);
                
        	} else if(sort.equals("sort:1")) {
            	client.setFaSort((byte)1);
                 
            }
             
            String typ = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > typ.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
        	
            if(typ.equalsIgnoreCase("edit")) { 
            	if(msg.length() < 2) {
            		return;
            	}
            	
            	String rightTeam = Settings.getTeam(msg);
            	
            	if(!Settings.checkTeam(msg)) {
            		client.sendButlerMessage(channel.getName(), String.format("Das Team _%s existiert nicht_.", msg));
            		return;
            	}
            	
            	if(!client.checkTeamLeader(msg) && !client.hasPermission("cmd.fa.edit.allteams")) {
            		client.sendButlerMessage(channel.getName(), String.format("Du bist kein Teamleiter im Team %s.", rightTeam));
            		return;
            	}
            	
            	StringBuilder team = new StringBuilder();
            	String title = String.format("Team %s - Übersicht", Settings.getTeamname(rightTeam));

            	List<String> mitglieder = new ArrayList<String>();
            	List<String> teamleiter = new ArrayList<String>();
                List<String> teamnick = new ArrayList<String>();

    			PoolConnection pcona = ConnectionPool.getConnection();
                PreparedStatement psa = null;
            
                try {
                    Connection cona = pcona.connect();
                    psa = cona.prepareStatement("SELECT `teams`,`name` FROM `accounts` WHERE `teams` != ''");
                    ResultSet rsa = psa.executeQuery();
                
                    while(rsa.next()) {
                        String tea = rsa.getString("teams").toLowerCase();
                        String name = rsa.getString("name");
                        
                        if(tea.contains(String.format("|%s~6|", rightTeam.toLowerCase()))) {
                        	teamnick.add(name);
                        }
                        if(tea.contains(String.format("|%s~1|", rightTeam.toLowerCase()))) {
                        	teamleiter.add(name);
                        } else if(tea.contains(String.format("|%s~0|", rightTeam.toLowerCase()))) {
                            mitglieder.add(name);
                        }
                    }    
                }catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (psa != null) {
                        try {
                            psa.close();
                        } catch (SQLException e) {
                        }
                    }

                    pcona.close();
                }
            	
            	team.append("#°>finger.b.gif<° °17°_Teamleiter_:§#");
                team.append("°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°_§°bir°°12°°>LEFT<°°b°°+0000°°+9505°°S18°_°>gg.png<° Name°%41°°>gg.png<° Status°%64°°>gg.png<° Minuten in diesem Monat°%70°_°%00°_°r°°+9505°°[174,174,255,127]°#°>{colorboxend}<°#°r°°>left<°§#");
            	
            	
            	if(teamleiter.size() == 0) {
            		team.append("# °>cc/bullet_blue_outlined.png<° Keine");
            	} else {
            		for(String nick : teamleiter) {
            			Client c = Server.get().getClient(nick);
            			
            			if(c == null) {
            				c = new Client(null);
            				c.loadStats(nick);
            			}
            			
                                  
                        String rang = "";
            if(c.getRank() ==0) { 
            rang = "°K12°_Mitglied_°K°";
            }  
            if(c.getRank() ==1) { 
            rang = "°[0,0,150]12°_°>_hFamilymitglied|/h family<°_°K°";
            } 
            if(c.getRank() ==2) { 
            rang ="°[0,0,150]12°_°>_hStammi|/h stammi<°_°K°";
            }
            if(c.getRank() ==3) { 
            rang ="°[0,0,150]12°_°>_hEhrenmitglied|/h ehrenmitglied<°_°K° °>ct/sm_ehren_01.gif<°";
            }
            if(c.getRank() ==5) { 
            rang ="°[0,0,150]12°_°>_hEhrenmitglied|/h ehrenmitglied<°_°K° °>ct/sm_ehren_01.gif<°";
            }
            if(c.getRank() ==6) { 
            rang = "°R12°_°>_hAdmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
            if(c.getRank() ==7) { 
            rang ="°R12°_°>_hAdmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
            if(c.getRank() >7) { 
            rang = "°R12°_°>_hSysadmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
                                
            team.append("#°>cc/bullet_blue_outlined.png<°  ");
                                
                                if(c.getPhoto().isEmpty()) {
            team.append("°>nopic_79x79_").append(c.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_24.border_2.jpg<°");
            } else {
            team.append("°>photos/photo/getPicture.php?l&img=").append(c.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_24.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(c).append("<°");
            
                                }
                                
                                
                                
                                
            			team.append("  _°BB>_h").append(c.getName()).append("|/serverpp \"|/w \"<°§").append("°%42°").append(rang).append("°%65°°12°_").append(c.getTimeMonth()/60).append("_§°%00°");
            		
                      
                        
                        
                        
                        
                        
                        }
            	}
            	
            	team.append("###°>finger.b.gif<° °17°_Mitglieder_:§#");
               team.append("°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°_§°bir°°12°°>LEFT<°°b°°+0000°°+9505°°S18°_°>gg.png<° Name°%41°°>gg.png<° Status°%64°°>gg.png<° Minuten in diesem Monat°%70°_°%00°_°r°°+9505°°[174,174,255,127]°#°>{colorboxend}<°#°r°°>left<°§#");
            	if(mitglieder.size() == 0) {
            		team.append("# °>cc/bullet_blue_outlined.png<° Keine");
            	} else {
            		for(String nick : mitglieder) {
            			Client c = Server.get().getClient(nick);
            			
            			if(c == null) {
            				c = new Client(null);
            				c.loadStats(nick);
            			}
            			
                                    String rang = "";
            if(c.getRank() ==0) { 
            rang = "°K12°_Mitglied_°K°";
            }  
            if(c.getRank() ==1) { 
            rang = "°[0,0,150]12°_°>_hFamilymitglied|/h family<°_°K°";
            } 
            if(c.getRank() ==2) { 
            rang ="°[0,0,150]12°_°>_hStammi|/h stammi<°_°K°";
            }
            if(c.getRank() ==3) { 
            rang ="°[0,0,150]12°_°>_hEhrenmitglied|/h ehrenmitglied<°_°K° °>ct/sm_ehren_01.gif<°";
            }
            if(c.getRank() ==5) { 
            rang ="°[0,0,150]12°_°>_hEhrenmitglied|/h ehrenmitglied<°_°K° °>ct/sm_ehren_01.gif<°";
            }
            if(c.getRank() ==6) { 
            rang = "°R12°_°>_hAdmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
            if(c.getRank() ==7) { 
            rang ="°R12°_°>_hAdmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
            if(c.getRank() >7) { 
            rang = "°R12°_°>_hSysadmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
                                
                           team.append("#°>cc/bullet_blue_outlined.png<°  ");
                                
                                if(c.getPhoto().isEmpty()) {
            team.append("°>nopic_79x79_").append(c.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_24.border_2.jpg<°");
            } else {
            team.append("°>photos/photo/getPicture.php?l&img=").append(c.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_24.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(c).append("<°");
            
                                }
                                
                                
                                
                                
            			team.append("  _°BB>_h").append(c.getName()).append("|/serverpp \"|/w \"<°§").append("°%42°").append(rang).append("°%65°°12°_").append(c.getTimeMonth()/60).append("_§°%00°");
            		
                        
                        
                        }
            	}
                
                if(teamnick.size() != 0) {
                team.append("###°>finger.b.gif<° _°17°Teamnicks_:§#");
                team.append("°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°_§°bir°°12°°>LEFT<°°b°°+0000°°+9505°°S18°_°>gg.png<° Name°%41°°>gg.png<° Status°%64°°>gg.png<° Minuten in diesem Monat°%70°_°%00°_°r°°+9505°°[174,174,255,127]°#°>{colorboxend}<°#°r°°>left<°§#");
                
            	
            	
            	 
            		for(String nick : teamnick) {
            			Client c = Server.get().getClient(nick);
            			
            			if(c == null) {
            				c = new Client(null);
            				c.loadStats(nick);
            			}
            			
                                
                                    String rang = "";
            if(c.getRank() ==0) { 
            rang = "°K12°_Mitglied_°K°";
            }  
            if(c.getRank() ==1) { 
            rang = "°[0,0,150]12°_°>_hFamilymitglied|/h family<°_°K°";
            } 
            if(c.getRank() ==2) { 
            rang ="°[0,0,150]12°_°>_hStammi|/h stammi<°_°K°";
            }
            if(c.getRank() ==3) { 
            rang ="°[0,0,150]12°_°>_hEhrenmitglied|/h ehrenmitglied<°_°K° °>ct/sm_ehren_01.gif<°";
            }
            if(c.getRank() ==5) { 
            rang ="°[0,0,150]12°_°>_hEhrenmitglied|/h ehrenmitglied<°_°K° °>ct/sm_ehren_01.gif<°";
            }
            if(c.getRank() ==6) { 
            rang = "°R12°_°>_hAdmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
            if(c.getRank() ==7) { 
            rang ="°R12°_°>_hAdmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
            if(c.getRank() >7) { 
            rang = "°R12°_°>_hSysadmin|/h admin<°_°K° °>ct/sm_ehren_02.gif<°";
            }
                           
                        team.append("#°>cc/bullet_blue_outlined.png<°  ");
                                
                                if(c.getPhoto().isEmpty()) {
            team.append("°>nopic_79x79_").append(c.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_24.border_2.jpg<°");
            } else {
            team.append("°>photos/photo/getPicture.php?l&img=").append(c.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_24.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(c).append("<°");
            
                                }
                                
                                
                                
                                
            			team.append("  _°BB>_h").append(c.getName()).append("|/serverpp \"|/w \"<°§").append("°%42°").append(rang).append("°%65°°12°_").append(c.getTimeMonth()/60).append("_§°%00°");
            		
                        
                        
                        }
            	}

                Popup popup = new Popup(title, title, team.toString(), 600, 300);
                Panel panel2 = new Panel();
                Button button = new Button("Teaminfo");
                button.setStyled(true);
                button.enableAction();
                button.disableClose();
                panel2.addComponent(button);
                Button button2 = new Button("Mitglieder");
                button2.setStyled(true);
                button2.enableAction();
                button2.disableClose();
                panel2.addComponent(button2);
                Button button3 = new Button("RundMail");
                button3.setStyled(true);
                button3.enableAction();
                button3.disableClose();
                panel2.addComponent(button3);
                popup.addPanel(panel2);
                        
                popup.setOpcode(ReceiveOpcode.FA.getValue(), Settings.getTeamname(rightTeam));
                client.send(popup.toString());
            	return;
            }
            
            
            // Eigener Versuch die Onlinemitglieder der einzelnen Teams in der /fa ? Übersicht anzeigen zu lassen
            
         /*   List<String> lol2 = new ArrayList<String>();
            String rightTeam2 = Settings.getTeam(arg);
            int lala2 = 0;
         
    				
                                
                                   for (Object target : Server.get().getClients().toArray()) {
            	Client cl = ((Client) target);

    			if(cl != Server.get().getButler()) {
    				if(cl.checkTeam(Settings.getTeamname(rightTeam2))) {
    					lol2.add(cl.getName());
    					lala2++;
               
                                }
    			}
            }*/
            
            
        	if(arg.equals("?")) {
        		StringBuilder fa = new StringBuilder("");
        		
        		for(String team : Settings.getTeams().split("\\|")) {
        			if(!team.isEmpty()) {
        				fa.append("#°>cc/bullet_blue_outlined.png<° _°>_h").append(team)/*.append(" (").append(lala2).append(") ")*/.append("|/fa \"|/h \"-team<r°_");
        			}
        		}
        		
                        
                        // Versuch ENDE
                        
        		String title = "Teamübersicht";
        		
        		PopupNewStyle popup = new PopupNewStyle(title, title, fa.toString(), 400, 425);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);

                        client.send(popup.toString());
        	        return; 
                }
        	
        	
                
               
        	
        	List<String> lol = new ArrayList<String>();
        	int lala = 0;
        	StringBuilder fa = new StringBuilder();
 String title = "";
        	
                if (arg.isEmpty()) {
            for (Object target : Server.get().getClients().toArray()) {
            	Client cl = ((Client) target);

    			if(cl != Server.get().getButler()) {
    				if(cl.getRank() > 5) {
    					lol.add(cl.getName());
    					lala++;
    				}
    			}
            }
            
        	if(lala == 0) {
        		client.sendButlerMessage(channel.getName(), "Momentan sind keine Admins online.");
        		return;
        	}
               title = String.format("Adminliste (%s)", lala);
        	
                } else {
                    
                    String rightTeam = Settings.getTeam(arg);
            	
            	if(!Settings.checkTeam(arg)) {
            		client.sendButlerMessage(channel.getName(), String.format("Das Team _%s existiert nicht_.", arg));
            		return;
            	}
                    
                
                  for (Object target : Server.get().getClients().toArray()) {
            	Client cl = ((Client) target);

    			if(cl != Server.get().getButler()) {
    				if(cl.checkTeam(Settings.getTeamname(rightTeam))) {
    					lol.add(cl.getName());
    					lala++;
    				}
    			}
            }
                  if(lala == 0) {
        		client.sendButlerMessage(channel.getName(), "Derzeit sind leider keine Teamler aus dem "+Settings.getTeamname(rightTeam)+"-Team online.");
        		return;
        	}
                 title = String.format(Settings.getTeamname(rightTeam)+"-Team (%s)", lala);
        	
                }
            
            if(client.getFaSort() == 0) {
        		Collections.shuffle(lol);
            } else {
            	Collections.sort(lol);
            }
        	
        	for(String nick : lol) {
        		Client c = Server.get().getClient(nick);
        		
				fa.append("#°>py_g.gif<°°%06°_°>_h").append(nick.replace("<", "\\<")).append("|/serverpp \"|/w \"<°_ ist im Channel _");
				
				if(c.getChannel().isVisible()) { 
					fa.append("°>_h").append(c.getChannel().getName()).append("|/go \"|/go +\"<°");
				} else {
					fa.append("?");
				}
				
				fa.append("°E° ONLINE_°r°!");
				
                if(c.isAway()) {
                	if(client.hasPermission("popup.awayaniicon")) {
                    	fa.append(" °>icon_away_ani_new.gif<°");
                	}else {
                		fa.append(" °>away.png<°");
                	}
                }
				
				if(c.getReadme() != null) {
					fa.append("#°%06°\"\"").append(Server.get().parseSmileys(c, c.getReadme())).append("§\"\"");
                }
				
				fa.append("°%00°");
        	}

        	
        	Popup popup = new Popup(title, title, fa.toString(), 550, 250);
        	popup.setButtonFontSize(18);
            Panel panel = new Panel();

            Button buttonMessage = new Button("   OK   ");
            buttonMessage.setStyled(true);
            panel.addComponent(buttonMessage);

            Button buttonMessage2 = new Button(client.getFaSort()==0?"Sortiert":"Zufällig");
            buttonMessage2.setCommand(String.format("/fa "+arg+":sort:%s", client.getFaSort()==0?1:0));
            buttonMessage2.setStyled(true);
            panel.addComponent(buttonMessage2);
            
            popup.addPanel(panel);
            client.send(popup.toString());
            
        
    }}
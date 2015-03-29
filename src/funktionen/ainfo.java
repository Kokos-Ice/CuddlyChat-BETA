package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;   


public class ainfo {
 private static NumberFormat nf;
 private static NumberFormat df;
 static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

    public static void functionMake(Client client,Channel channel, String arg) {
    if (!client.hasPermission("cmd.ainfo") && !client.checkTeam("Jugendschutz") && !client.checkTeam("Spiele") && !client.checkTeam("AntiExtremismus") && !channel.checkCm(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg);
            Client target;
            boolean online = true;
            boolean ent = false;

            if (!nickname.isEmpty() && arg.startsWith("+") && client.hasPermission("cmd.w.plus")) {
                ent = true;
                nickname = nickname.substring(1).trim();
            }

            if (nickname.isEmpty() || nickname.equalsIgnoreCase(client.getName())) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nickname);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                        return;
                    }
                }
            }

            String nick = target.getName();
            String charNick = nick.replace("<", "\\<");
            String title = String.format("Admininfo - %s", nick);
            StringBuilder pem = new StringBuilder("#");
            int eE = 1, dp = 1, spaces = 0;
            String photo = target.getPhoto();

            
            if(target.getPhoto().isEmpty()) {
                
                pem.append("°>center<°##°>nopic_79x79_").append(target.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_70.border_2.jpg<°");
                pem.append("###°>left<°");
            } else {
                pem.append("°>center<°##°>photos/photo/getPicture.php?l&img=").append(target.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_70.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(photo).append("<°");
                pem.append("###°>left<°");
            }
            
   // TEXTCENTER KASTEN ENDE 
            // #°>{imageboxend}<°°>{tc}<°°>center<°_#°>{endtable}<°°>left<°
            
            if (online) {
                
                pem.append("°>{table|0|410|200|w1<°#°>{tc}<°#°>{imageboxstart}hiwhoisp_boy.ending_0.mx_8.my_-15<°°%04°°>justify<°°18°°>{textborder}<°");
                pem.append(String.format("##°20°°>center<°°>bullet.b.png<° _Admininfo -°BB20° °>%s|/m %s<° °>on.png<° §###", nick, nick));
                pem.append("°>left<°");
            } else {
                pem.append("°>{table|0|410|200|w1<°#°>{tc}<°#°>{imageboxstart}hiwhoisp_boy.ending_0.mx_8.my_-15<°°%04°°>justify<°°18°°>{textborder}<°");
                pem.append(String.format("##°20°°>center<°°>bullet.b.png<° _Admininfo -°BB20° °>%s|/m %s<° °>off.png<° §###", nick, nick));
                pem.append("°>left<°");
            }
            
                pem.append("§°%05°#°>gt.gif<° Rang:°%32°");
                Server.get().newSysLogEntry(client.getName(), String.format("Admininfo von %s abgerufen", nick));

            if (target.getRank() == 0) {
                pem.append("_").append(target.getRankLabel(target.getRank())).append("§");
            }
            if (target.getRank() == 1) {
                pem.append("_°B°").append(target.getRankLabel(target.getRank())).append("§ (_°BB>?|/h familymitglieder<°§)");
            }
            if (target.getRank() == 2) {
                pem.append("_°[0,0,150]°").append(target.getRankLabel(target.getRank())).append("§ (_°BB>?|/h stammi<§)");
            }
            if (target.getRank() == 3) {
                pem.append("_°[0,0,150]°").append(target.getRankLabel(target.getRank())).append("§ °>ct/sm_ehren_01.gif<° (_°BB>?|/h ehrenmitglied<°§)");
            }
            if (target.getRank() == 4) {
                pem.append("_°[0,0,150]°").append(target.getRankLabel(target.getRank())).append("§ °>ct/sm_ehren_01.gif<° (_°BB>?|/h ehrenmitglied<°§)");

            }
            if (target.getRank() == 5) {
                pem.append("_°[0,0,150]°").append(target.getRankLabel(target.getRank())).append("§ °>ct/sm_ehren_01.gif<° (_°BB>?|/h admin<°§)");

            }
            if (target.getRank() == 6) {
                pem.append("_°R°").append(target.getRankLabel(target.getRank())).append("§ °>ct/sm_ehren_02.gif<° (_°BB>?|/h admin<°§)");

            }
            if (target.getRank() > 6) {
                pem.append("_°R°").append(target.getRankLabel(target.getRank())).append("§ °>ct/sm_ehren_02.gif<° (_°BB>?|/h admin<°§)");

            }

            if (target == Server.get().getNRS() || target == Server.get().getButler()) {
                pem.append(", Bot");
            }
            pem.append("§°%05°#°>gt.gif<° Registration:°%32°_").append(target.getRegistrationDate());
            pem.append("§°%05°#°>gt.gif<° Minuten:°%32°_").append(nf.format(target.getOnlineTime() / 60));

              //  pem.append(")°%00°");
            StringBuilder hz = new StringBuilder();

            for (Channel c : Server.get().getChannels()) {
                if (c.isVisible()) {
                    if (c.checkHz(target.getName())) {
                        hz.append(eE != 1 ? ", " : "").append("°>_h").append(c.getName()).append("|/go \"|/go +\"<°");
                        eE++;
                    }
                }
            }

            if (!hz.toString().isEmpty()) {
                pem.append("§°%05°#°>gt.gif<° ");
                pem.append("HZA/E:°%32°").append(hz.toString()).append("°%00°#");
            }

            if (client.getRank() >= target.getRank()) {
            }

            if (!target.getTeams().isEmpty()) {
                pem.append("#§°%05°#°>gt.gif<° ");
                pem.append("Teams:°%32°");

                String eingabe = target.getTeams().replace("||", "<").replace("|", "");
                String[] strarr = eingabe.split("<");

                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                    String[] x = strarr[i].split("~");
                    String team = x[0];
                    String extra = x[1];

                    pem.append(dp != 1 ? ", " : "").append("°>_h").append(team);

                    /*if(extra.equals("1")) {
                     pem.append(" (Teamleiter)");
                     } else if(extra.equals("2")) {
                     pem.append(" (Vorsitz)");
                     } else if(extra.equals("3")) {
                     pem.append(" (Forumsmoderator)");
                     } else if(extra.equals("4")) {
                     pem.append(" (Forumsadmin)");
                     } else if(extra.equals("5")) {
                     pem.append(" (Forums-Sysadmin)");
                     } else if(extra.equals("6")) {
                     pem.append(" (Teamnick)");
                     }
                     */
                    pem.append("|/h ").append(team).append("-team<°");
                    dp++;
                }

                pem.append("°%00°");
            }
            pem.append("##°>{imageboxend}<°°>{tc}<°°>center<°_°>{endtable}<°°>left<°");
            pem.append("§");
            pem.append("°-°");
            
            
            
             // Mitglied selbst (Client) kann seine eigene Ainfo dann nicht mehr sehen sofern es Das Recht Hide besitzt 
            // Sysadmins können auch nicht mehr die Ainfo von Mitglied einsehen.
            // Mitglied muss trotz Hide seine eigene AInfo sehen können und Sysadmins ab Rank x müssen es auch sehen können.
            // Bei gelegenheit Chiller mal Fragen!
            
            
            if(client.hasPermission("cmd.admininfo") && target.hasPermission("cmd.append.ainfo.hide") && !client.hasPermission("cmd.append.ainfo.alltime")&& client != target)  {
                           // pem.append("#").append(target.getRank() > 6 ?  "Sysa":"A").append("dmininfo_:##");
                            pem.append("°>center<°#°[198,198,231]>{colorboxstart}<°°K°°3°#°18°");
                            pem.append("°R°_Du bist nicht berechtigt diese Admininfo einzusehen.");
                            pem.append("°%00°°3°°[198,198,231]>{colorboxend}<15K°");
                            pem.append("#§°>left<°##");
                }else
                
            
            

        	if (client.hasPermission("cmd.admininfo") || client.getTeams().contains("~1|") || client.hasPermission("cmd.admininfo")) {
        	          // pem.append("#").append(target.getRank() > 6 ?  "Sysa":"A").append("dmininfo_:##");
        	          //ehemals < 6 && target.getRank() > 6) {
                if(client.getRank() < 5 && target.getRank() > 3 || client.getRank() < 7 && target.getRank() > 7) {
        		}else{
                    pem.append("°%70°");
                       // pem.append(String.format("°>bullet.b.png<° _°BB°°>_hPermissions|/permissions %s<°#§", nick));
                        pem.append("°%00°");
                        
                        
                        
                        pem.append("§°%00°#Last Host:°%43°");
                
                	if(client.getRank() > 6) {
                		pem.append("°>_h");
                	}
                        if (target.hasPermission("cmd.append.ainfo.bot")) {
                            pem.append("127.0.0.1");
                       
                        }else
                	pem.append(target.getIPAddress().replace(".", "-"));
                        
                        
                        
                	if(client.getRank() > 6) {
                		pem.append("|/checkip \"<°");
                		
                             
                                
                		if(!target.getHost().isEmpty()) {
                			pem.append(" (").append(ProfileTools.getCountry(target.getHost())).append(")");
                		}
                	}
                
                }
                
                if(target.hasPermission("cmd.append.ainfo.bot")) {
                    {
                       pem.append(String.format(", (°>_hWhois|http://www.utrace.de/?query=%s<°)", new Object[] { ("127.0.0.1") })); 
                }
                }else
                
                
                
                
                if(client.getRank() >= target.getRank())      
                {
                       pem.append(String.format(", (°>_hWhois|http://www.utrace.de/?query=%s<°)", new Object[] { target.getIPAddress() })); 
                }
                
                       pem.append("°%00°#User Reg-No.:°%43°").append(nf.format(target.getID())).append("°%00°#Status:°%43°").append(target.getRank()).append(" (").append(target.getRankLabel(target.getRank()));
                
                if(target == Server.get().getNRS() || target == Server.get().getButler() || target.hasPermission("cmd.append.ainfo.bot") || target.getBot() == 1) {
                	pem.append(", Bot");
            	}
                
                pem.append(")°%00°");
                
                if(target.getMutes() > 0) {
                    pem.append("#Mutes:°%43°").append(target.getMutes()).append("°%00°");
                }
                
                if(target.getCmutes() > 0) {
                    pem.append("#Colormutes:°%43°").append(target.getCmutes()).append("°%00°");
                }
                
                if(target.getKicks() > 0) {
                    pem.append("#Kicks:°%43°").append(target.getKicks()).append("°%00°");
                }
                
                 if(target.getLocks() > 0) {
                    pem.append("#Locks:°%43°").append(target.getLocks()).append("°%00°");
                }
                
                if(target.getCls() > 0) {
                    pem.append("#Channellocks:°%43°").append(target.getCls()).append("°%00°");
                }
                
                if(target.getFlames() > 0) {
                    pem.append("#Flames:°%43°").append(target.getFlames()).append("°%00°");
                }
                
                StringBuilder nn = new StringBuilder();
                int xl = 1;

                for (Client ob : Server.get().getClients()) {
               	 	if(ob != target && ob.getIPAddress().equals(target.getIPAddress())) {
               	 		nn.append(xl!=1?", ":"").append("°>_h").append(ob.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
               	 		xl++;
               	 	}
                }
                
                // Für Nicknamen die das Permission Bot haben
                if(target.hasPermission("cmd.append.ainfo.bot")) {
                 //   pem.append("#_Nicks mit gleicher IP_:°%43°").append("").append("°%00°");
   
                }else
                    
               // Ende
                    
                 // CokaColaBoy's Ainfo zeigt keinen Nick mit selber IP an.   
                 if(target.getName().equals("CokaColaBoy")) {
                     
                 }else
                
                 // Ende
                     
                if(!nn.toString().isEmpty()) {
                	pem.append("#Nicks mit gleicher IP:°%43°").append(nn.toString()).append("°%00°");
                }
                
                pem.append("#Notrufe (\\# / Ok / Missbr.):°%43°").append(target.getAdmincallFirst()).append(" / ").append(target.getAdmincallSecond()).append(" / ").append(target.getAdmincallThird()).append("°%00°");

                if(target.getNotrufsperre() != 0) {
                	pem.append("#Notrufsperre bis:°%43°").append(Server.get().timeStampToDate(target.getNotrufsperre())).append("°%00°");
                }
                
                if(client.getRank() > 6 || client.getName().equals("Sternrainy") || client.hasPermission("cmd.admininfo.full")) {
                	if(target.getRegisterIP() != null) { 
                		pem.append("#Register-IP:°%43>_h").append(target.getRegisterIP().replace(".", "-")).append("|/checkip \"<%00°");
                                pem.append("#E-Mail:°%43>_h").append(target.getEmail().replace(".", "-")).append("|/checkmail \"<%00°");
                	}
                        
                        
                        // Für Nicknamen die das Permission Bot haben
                        if(target.hasPermission("cmd.append.ainfo.bot")) {
                          //  pem.append("#_Betriebssystem_:°%43°").append("???").append("°%00°");
                          //  pem.append("#_Browser_:°%43°").append("???").append("°%00°");
                        }else
                        // Ende    
                        
                	
                	if(target.getAgent() != null) {
                		pem.append("#Betriebssystem:°%43°").append(ProfileTools.getOS(target.getAgent())).append("°%00°");
                		pem.append("#Browser:°%43°").append(ProfileTools.getBrowser(target.getAgent())).append("°%00°");
                	}
                	
                        // Für Nicknamen die das Permission Bot haben
                        if(target.hasPermission("cmd.append.ainfo.bot")) {
                        //    pem.append("#_Host_:°%43°").append("localhost").append("°%00°");
                        }else
                        // Ende
                        
                        
                	if(!target.getHost().isEmpty()) {
                		pem.append("#Host:°%43°").append(ProfileTools.getHost(target.getHost())).append("°%00°");
                	}
                
                	pem.append("#Zeichenzahl:°%43°").append(nf.format(target.getZeichen())).append("°%00°");
                        pem.append("#Whois Style:°%43°").append(target.getWStyle() == 2 ? "???":target.getWStyle()==1?"W2 BETA":"Classic").append("°%00°");
                        pem.append("#Whois Sichtbar?:°%43°").append(target.getVisit() == 2 ? "???":target.getVisit()==1?"false":"true").append("°%00°");
                          
                        if(target.getUserLogin() > 0) {
                        pem.append("#Logins:°%43°").append(nf.format(target.getUserLogin())).append("°%00°");
                        }
                        
                        // Für Nicknamen die das Permission Bot haben
                        if(target.hasPermission("cmd.append.ainfo.bot")) {
                         //  pem.append("#_Appletversion_:°%43°").append("???").append("°%00°");
                	  //	pem.append("#_Javaversion_:°%43°").append("???").append("°%00°");
                	
                        }else
                         // Ende   
                            
                        
                	if(target.getAppletVersion() != null) {
                		pem.append("#Appletversion:°%43°").append(target.getAppletVersion()).append("°%00°");
                		pem.append("#Javaversion:°%43°").append(KCodeParser.escape(target.getJavaVersion())).append("°%00°");
                	        pem.append("#LastLoginURL:°%43°").append(target.getLoginUrl()).append("°%00°");
                        }
                	
                	pem.append("#E-Mail-Verify:°%43°").append(target.getEmailVerify() == 2 ? "Verifiziert":target.getEmailVerify()==1?"Verifizierung läuft...":"Nicht verifiziert").append("°%00°");
                        
                	if(online) {
                		pem.append("#Online seit:°%43°").append(target.getLoginTime()).append("°%00°");
                	}
                
                	if(target != Server.get().getButler()) {
                		if(online && !target.getChannel().isVisible()) {
                			pem.append("#Online in:°%43>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<%00°");
                		}
                	}
                	
                	if(!target.getMentor().isEmpty()) {
                		pem.append("#Mentor:°%43°").append("°>_h").append(target.getMentor().replace("<", "\\<")).append("|/serverpp \"|/w \"<°°%00°");
                	}
                }
                
                if(!target.getCmcomments().isEmpty()) {
                    pem.append("##CM-Comments:#").append(target.getCmcomments());
                }
                
            /*    for(String[] infos : client.admincalls) {
                	String verstoss = infos[0];
            		String id = infos[1];
            		String uhrzeit = infos[2];
                	
                	beschwerden.append("#_").append(verstoss).append(" - *°>_h").append(id).append("|/admincall info:\"<°_ (").append(uhrzeit).append(")");
                }
                
                if(!beschwerden.toString().isEmpty()) {
                    pem.append("##_Beschwerden_:#").append(beschwerden.toString());
                }    
            */
                if(!target.getComments().isEmpty()) {
                    pem.append("##Comments:#");
                    pem.append(target.getComments());
                }
                if(client.getRank() > 6 || client.hasPermission("cmd.admininfo.syscomments")) {
                        if(!target.getSyscomments().isEmpty()) {
                            pem.append("##Sys-Comments:#");
                            pem.append(target.getSyscomments());
                        }
                    }
                
            } else if(channel.checkCm(client.getName()) || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz") || client.hasPermission("cmd.cminfo")) {
            	//pem.append("Channelmoderatorinfo:##");
            	//if(target.getCls() > 0) {
                   pem.append("Channellocks:°%43°").append(target.getCls()).append("°%00°#");
               // }
                
               // if(target.getCmutes() > 0) {
                    pem.append("Colormutes:°%43°").append(target.getCmutes()).append("°%00°#");
               // }
                
               // if(target.getMutes() > 0) {
                    pem.append("Mutes:°%43°").append(target.getMutes()).append("°%00°#");
               // }
                
                if(!target.getCmcomments().isEmpty()) {
                    pem.append("##CM-Comments:#").append(target.getCmcomments());
                }
            }
            
            
          
            pem.append("°-°");
            pem.append("°>center<°#°[198,198,231]>{colorboxstart}<°°K°°3°#°15°_");
            pem.append(target.getName()).append(" besitzt folgende Rechte_:");
            pem.append("°%00°°3°°[198,198,231]>{colorboxend}<15K°");
            pem.append("##°%30°°>left<°");
            
            
            

            List<String> sortedList = new ArrayList<String>();
            sortedList.addAll(Server.permissions.keySet());
            Collections.sort(sortedList);

            Iterator<String> iter = sortedList.iterator();

            while (iter.hasNext()) {
                String permission = iter.next();
                String key = Server.permissions.get(permission);

                if (target.hasPermission(permission)) {
                    pem.append(permission);

                    if (key.contains(String.format("|N%s|", target.getName()))) {
                        pem.append(" °%70>finger.b.gif<° °BB>Entziehen|/rights ");
                        pem.append(target.getName()).append(":!").append(permission).append("<r%00°");
                        pem.append("°%30°");
                    }

                    pem.append("#");
                }
            }

            pem.append("#");
            pem.append("§");

            Popup popup = new Popup(title, title, pem.toString(), 460, 350);
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   OK   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            popup.setModern(1);
            client.send(popup.toString());

    
    }
    }
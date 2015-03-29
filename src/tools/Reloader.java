/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (C) 2011 - 2012 Flav <http://banana-coding.com>
 * 
 * Diese Dateien unterliegen dem Coprytight von Banana-Coding und
 * darf verändert, aber weder in andere Projekte eingefügt noch
 * reproduziert werden.
 * 
 * Der Emulator dient - sofern der Client nicht aus Eigenproduktion
 * stammt - nur zu Lernzwecken, das Hosten des originalen Knuddels Clients
 * ist auf eigene Gefahr und verstößt möglicherweise gegen Schutzrechte
 * der Knuddels.de GmbH & Co KG
 * 
 * Autoren: Flav (Grundversion), Localhost (Erweiterte Version), Kokos-Ice (Erweiterte Version)
 */



package tools;

import blitz.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
 import java.sql.PreparedStatement;
import java.util.Random;
import java.util.*;
import starlight.Channel;
import starlight.Client;
import starlight.CommandParser;
import starlight.Server;

import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class Reloader {
    
    
    private static Random zufall = new Random();
    private static String[] kizzfailed = {"[C] sollte einsehen, dass keine Kusschancen bei [T] bestehen.", "[T] lässt sich nicht so einfach von [C] einen Zungenkuss aufdrücken.", "[T] glaubt vielleicht, ein Herpesbläschen an [C]'s Lippe entdeckt zu haben. Zumindestens kommt ein inniger Kuss gerade nicht in Frage!", "[C]s Versuch, [T] so richtig zu küssen, ist vorerst gescheitert.", "[C] wollte [T] einen richtigen Kuss verpassen, aber so was lässt [T] nicht mit sich machen."};
   private static String[] highfivefailed = {"[C] ist sehr enttäuscht, weil [T] nicht einschlagen möchte."};
    
    
    
	
    public static void run() {
        long time = System.currentTimeMillis()/1000;   
        
          	String wahlsetts = "";	
     for(String wahl : Server.elections.keySet()) {
      String[] more = Server.elections.get(wahl);
       // 4 = nominicks
      // 5 = gewähltenicks      
     
      if (Integer.parseInt(more[0]) == 0 && Integer.parseInt(more[1]) <= time) {
          
      wahlsetts  += "|--|"+wahl+"~~1;;;"+more[1]+";;;"+more[2]+";;;"+more[3]+";;;"+more[4]+";;;"+more[5]+";;;"+more[6]+"|--|";
          Server.get().query("update `wahlen` SET `aktiv` = '1' where name='"+wahl+"'");
	 // start nomiphase 
           String betreff = "Admin-Nominierungsphase";
           String text = "In "+Server.get().getSettings("CHAT_NAME")+" beginnen nun die Nominierungen zum Admin (genauere Informationen findest du unter diesen Link.) und enden am 26.09.2014.#Mit _/sug admin:NICK_ kannst Du einen anderen Stammchatter nominieren.";
         Server.get().sendMailToAllAccounts(Server.get().getButler().getName(),betreff,text,"wahl");
                  
      } else  if (Integer.parseInt(more[0]) == 1 && Integer.parseInt(more[2]) <= time) {
          // start wahlphase
        
         String betreff = "Admin-Wahlphase";
           String text = "Die Wahlen zum Admin haben in "+Server.get().getSettings("CHAT_NAME")+" begonnen. Bitte gebe _/vote admin_ ein und nimm an der Wahl teil.##Die 2 Nominierten mit dem besten Wahlergebnis werden anschließend die neuen Admins.##Die Ergebnisse werden am 29.09.2014 bekannt gegeben.";
         Server.get().sendMailToAllAccounts(Server.get().getButler().getName(),betreff,text,"wahl");
         
        wahlsetts  += "|--|"+wahl+"~~2;;;"+more[1]+";;;"+more[2]+";;;"+more[3]+";;;"+more[4]+";;;"+more[5]+";;;"+more[6]+"|--|";
          Server.get().query("update `wahlen` SET `aktiv` = '2' where name='"+wahl+"'");
      }  else  if (Integer.parseInt(more[0]) == 2 && Integer.parseInt(more[3]) <= time) {
          // wahl auswerten ^^
       
          
        String erg = Wahlauswertung.Auswerten(wahl);
          
          
         
          wahlsetts  += "|--|"+wahl+"~~3;;;"+more[1]+";;;"+more[2]+";;;"+more[3]+";;;"+more[4]+";;;"+erg+";;;"+more[6]+"|--|";
          Server.get().query("update `wahlen` SET `aktiv` = '3', wahl='"+erg+"' where name='"+wahl+"'");
        
           
      }    
      
    
                                
     }
     for(String l : wahlsetts.split("\\|--\\|")) {
         if (!l.isEmpty()) {
             String[] a = l.split("~~");
                       String[] b = a[1].split(";;;");
            Server.elections.put(a[0],b);
           wahlsetts = wahlsetts.replace("|"+l+"|","");
	    
         }
     }
     
        
         String mins = new SimpleDateFormat("mm").format(new Date());
        
         for (Channel channels : Server.get().getChannels()) {
          
// blitz start
             
           
               if (channels.getName().equals("Blitz!"))
        {
           String next = "";
         Long rest6 = Long.valueOf(System.currentTimeMillis() / 1000L);
        Long time5 = Long.valueOf(System.currentTimeMillis() / 1000L);
        PoolConnection pconau = ConnectionPool.getConnection();
      PreparedStatement psau = null;
          try
          {
             Connection conau = pconau.connect();
           psau = conau.prepareStatement("SELECT * FROM `blitzsettings`");
             ResultSet rsau = psau.executeQuery();
          while (rsau.next()) {
               next = rsau.getString("next");
               Long rest5 = Long.valueOf(rsau.getLong("next") + 20L);
               rest6 = Long.valueOf(rest5.longValue() - time5.longValue());
            }

          }
          catch (SQLException e)
          {
             e.printStackTrace();
          } finally {
             if (psau != null)
              try {
                 psau.close();
              }
              catch (SQLException e)
              {
              }
             pconau.close();
          }

           int dabei = 0;
           PoolConnection pcon3 = ConnectionPool.getConnection();
           PreparedStatement ps3 = null;
          try
          {
             Connection con = pcon3.connect();
             ps3 = con.prepareStatement("SELECT COUNT(user) AS summe FROM `blitzdabei`");
            ResultSet rs3 = ps3.executeQuery();
             while (rs3.next())
            {
               dabei = rs3.getInt("summe");
            }
          }
          catch (SQLException e)
          {
             e.printStackTrace();
          } finally {
             if (ps3 != null)
              try {
                 ps3.close();
              }
              catch (SQLException e)
              {
              }
             pcon3.close();
          }

           String userid = "";
           int runde = 1;

           PoolConnection pconi = ConnectionPool.getConnection();
           PreparedStatement psi = null;
          try
          {
             Connection coni = pconi.connect();
            psi = coni.prepareStatement("SELECT * FROM `blitzsettings`");
             ResultSet rsi = psi.executeQuery();
            while (rsi.next())
            {
               userid = rsi.getString("aktuell");
               runde = rsi.getInt("runde");
            }

          }
          catch (SQLException e)
          {
             
          } finally {
             if (psi != null)
              try {
                 psi.close();
              }
              catch (SQLException e)
              {
              }
             pconi.close();
          }
         
         
          
          String user = "";
          
          PoolConnection pcon4 = ConnectionPool.getConnection();
           PreparedStatement ps4 = null;
          try
          {
             Connection con4 = pcon4.connect();
             ps4 = con4.prepareStatement("SELECT * FROM `blitzdabei` where `id` = '"+userid+"'");
             ResultSet rs4 = ps4.executeQuery();
             while (rs4.next())
            {
               user = rs4.getString("user");
            }
          }
          catch (SQLException e)
          {
            
          } finally {
             if (ps4 != null)
              try {
                 ps4.close();
              }
              catch (SQLException e)
              {
              }
             pcon4.close();
          }
           if (rest6 <= 0L && !user.isEmpty() && !next.isEmpty())
          {
             Random random = new Random();
             int randomInt = random.nextInt(15);

            randomInt += 1;
             int neuerunde = runde + 1;
                    Server.get().query("UPDATE `blitzsettings` SET `zahl` = '"+randomInt+"', `aktuell` = '1', `next` = '', `runde` = '"+neuerunde+"'");
                    Server.get().query("delete from blitzdabei where user = '"+user+"'");
 Server.get().query("ALTER TABLE blitzdabei DROP id");
 Server.get().query("ALTER TABLE `blitzdabei` ADD `id` INT( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST");


            List prefix = new ArrayList();
             prefix.add(user + "~");
             String nickname656 = KCodeParser.escape(user);

             boolean onlinert = true;
             Client targeter = Server.get().getClient(nickname656);
            if (targeter == null) {
               onlinert = false;
               targeter = new Client(null);
               targeter.loadStats(nickname656);
            }

             String action = PacketCreator.action(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), channels.getName(), String.format("°RR18°_" + user + "_§, du hast dir zulange Zeit gelassen.", new Object[0]));
             for (Client c : channels.getClients()) {
               for (Client lol : channels.getClients()) {
                 lol.send(PacketCreator.showPrefixIcons(channels.getName(), prefix,true));
              }
              c.send(action);
            }

             if (dabei == 2) {
             blitz.activ3 = true;
               thread3.channel = channels;
               Object mixThread = new thread3();
               ((Thread)mixThread).start();
            }
            else {
               blitz.activ2 = true;
               thread2.channel = channels;
               Object mixThread = new thread2();
               ((Thread)mixThread).start();
            }

          }

        }
               // neues quiz
               if (channels.getGameName().equals("QUIZ2")) {
               if (mins.equals("00")) {
                  	channels.broadcastMessage(Server.get().getButler().getName()+" quiz", Server.get().getButler(),false);
               }     
                   
               }
             // quiz start
           
         
         }
        
        String remover = "";
        for(long zeit : Server.actions.keySet()) {
        	String[] split = Server.actions.get(zeit);
        	int seconds = Integer.parseInt(split[4]);
        	
            if ((zeit+seconds)-time <= 0) {
            	Channel channel = Server.get().getChannel(split[3]);
            	String typ = split[0];
            	Client von = Server.get().getClient(split[1]);
            	Client an = Server.get().getClient(split[2]);
            	String vonChar = von.getName().replace("<", "\\<");
            	String anChar = an.getName().replace("<", "\\<");
            	
            	if(typ.equals("kizz")) {
            	 String image = "";
           String image2 = "";
            if(von.getGender() == 1) {
                image = "m";
            } else if(von.getGender() == 2) {
                image = "f";
            }
             if(an.getGender() == 1) {
                image2 = "m";
            } else if(an.getGender() == 2) {
                image2 = "f";
            }
                    String text = kizzfailed[zufall.nextInt(kizzfailed.length)].replace("[C]", von.getName()).replace("[T]", an.getName());
            
            channel.broadcastPicAction(">", text, String.format("actNokizz_%s%s.png", image,image2));  
                        
                    
                    
                    
                    // Original	channel.broadcastPicAction(">>", String.format("%s glaubt vielleicht, ein Herpesbläschen an %s Lippe entdeckt haben. Zumindest kommt ein inniger Kuss gerade nicht in Frage!", anChar, vonChar), String.format("actNokizz_%s...h_0.mx_-0.png", CommandParser.image(an, von)));
            	} else 
                    
                 if(typ.equals("gameWurf")) {
                  if (an.getDartenDran() == 1 && an.getDartenWurfLast() == an.getDartenWurf()) {
                Client target = Server.get().getClient(an.getDartenGegner());
            	an.sendButlerMessage(channel.getName(),String.format("Du hast dir zulange Zeit gelassen, weshalb diese Runde abgebrochen wird! Jeder erhält seinen Einsatz (%s Knuddels) zurück.", an.getDartenEinsatz()));
                target.sendButlerMessage(channel.getName(),String.format("_%s_ hat sich zulange Zeit gelassen, weshalb diese Runde abgebrochen wird! Jeder erhält seinen Einsatz (%s Knuddels) zurück.",an.getName(), an.getDartenEinsatz()));
                an.increaseKnuddels(an.getDartenEinsatz());
                target.increaseKnuddels(an.getDartenEinsatz());
                target.setDartenOpen(0);
                target.setDartenGegner("");
                target.setDartenWurf(0);
                target.setDartenDran(0);
                target.setDartenPrivat(0);
                target.setDartenArt(0);
                target.setDartenEinsatz(0);
                target.setDartenEinsatzArt(0);
                
                an.setDartenOpen(0);
                an.setDartenGegner("");
                an.setDartenWurf(0);
                an.setDartenDran(0);
                an.setDartenPrivat(0);
                an.setDartenArt(0);
                an.setDartenEinsatz(0);
                an.setDartenEinsatzArt(0);
                  }
                }
            
            if(typ.equals("game")) {
                if (an.getDartenGegner().isEmpty() && an.getDartenOpen() == 1) {
            	an.sendButlerMessage(channel.getName(),String.format( "Dein offenes Spielangebot wurde soeben gelöscht, da niemand darauf eingegangen ist. Der Einsatz (%s %s) wurde dir ausgezahlt. _°>finger.b.gif<>--<>|/game<>--<° °>--<BB>Eröffne ein Spiel!|/game<°_", an.getDartenEinsatz(), "Knuddels"));
                an.increaseKnuddels(an.getDartenEinsatz());
                an.setDartenOpen(0);
                an.setDartenGegner("");
                an.setDartenWurf(0);
                an.setDartenDran(0);
                an.setDartenPrivat(0);
                an.setDartenArt(0);
                an.setDartenEinsatz(0);
                an.setDartenEinsatzArt(0); 
                }
                }
                    
                    if(typ.equals("highfive")) {
                    
                    
                    String text = highfivefailed[zufall.nextInt(highfivefailed.length)].replace("[C]", anChar).replace("[T]", vonChar);
            
            		channel.broadcastPicAction(">>", text, String.format("actNoHighFive_%s.png", CommandParser.image(an, von)));
            	}
            	
            	//holdhands, loveletter empty
                    remover += "|"+zeit+"|";
            
            }
        }
        for(String x : remover.split("\\|")) {
        if (!x.isEmpty()) {
            	Server.actions.remove(Long.parseLong(x));
                remover = remover.replace("|"+x+"|","");
            
        }    
        
        }
       
        
        PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;

        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select name,photoUploadLock, priviMessage2, priviMessage, photo,friendlist from accounts where priviMessage != 0");
            
            while(rs.next()) {
            	String nick = rs.getString("name");
            	String photo = rs.getString("photo");
            	String p = rs.getString("priviMessage2");
            	int priviMessage = rs.getInt("priviMessage");
            	int photoUploadLock = rs.getInt("photoUploadLock");
                Client target = Server.get().getClient(nick);
                boolean online = true;
                
                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nick);
                }
                
                if(priviMessage == 1) {
                	if(online) {
                		if(!photo.isEmpty()) {
                			if(photoUploadLock == 0) {
                				target.getChannel().broadcastAction("°BB°_>>>", String.format("_°>_h%s|/serverpp \"|/w \"<° hat ein _°>_hneues Foto hochgeladen!|/foto %s<°", target.getName().replace("<", "\\<"), target.getName().replace("<", "\\<")));
                				Server.get().query(String.format("update accounts set photoUploadLock='1' where name='%s'", target.getName()));
                			}
                		}
                		
                		target.setPhoto(photo);
                	}
                	
                	if(photoUploadLock == 0) {
                		ResultSet rs2 = ps.executeQuery("select name, friendlist from accounts where friendlist!=''");
                    
                		while(rs2.next()) {
                			String friendlist = rs2.getString("friendlist");
                    	
                			if(friendlist.contains(String.format("|%s|", nick))) {
                				String blubb = rs2.getString("name");
                				Client blu = Server.get().getClient(blubb);
                				
                				Server.get().newMessage(Server.get().getButler().getName(), blubb, String.format("%s lädt ein neues Foto hoch!", nick), String.format("%s hat ein neues Foto hochgeladen:##_°BB>_h%ss Foto anzeigen|/foto %s<r°_##...schreib doch einen Kommentar! %s wird sich ganz bestimmt darüber freuen.", nick, nick, nick, nick), System.currentTimeMillis()/1000);
                			
                                if(blu != null) {
                                	for(Channel t : blu.getChannels()) {
                            			blu.sendButlerMessage(t.getName(), String.format("°%%-1°°BB°_°m°°>_h%s|/serverpp \"|/w \"<°°m°_°° hat dir gerade eine Nachricht (_%s lädt ein neues Foto hoch!_) geschickt. Sie liegt nun in deinem °>_hBriefkasten|/m<°.°#°°>mailclosed-outerglow.png<>--<>|/m<>--<° _°BB>Jetzt lesen|/m<r°_", Server.get().getButler().getName(), nick));
                            		}
                                }
                			}
                		}
                }
                } else if(priviMessage == 4) {
                	if(online) {
                		for(String n : p.split(",")) {
                			if(!n.isEmpty()) {
                				for(Channel c : target.getChannels()) {
                					target.sendButlerMessage(c.getName(), String.format("_°>_h%s|/serverpp \"|/w \"<°_ hat soeben einen _°>_hKommentar zu deinem Foto|/foto %s<°_ geschrieben.", n, nick));
                				}
                			}
                		}
                	}
                } else if(priviMessage == 5) {
                	target.setEmailVerify((byte) 0);
                	Server.get().newMessage(Server.get().getButler().getName(), nick, "", String.format("Die von dir in Gang gesetzte Verifizierung der E-Mail-Adresse %s war nicht erfolgreich. Entweder hast du zu lange abgewartet um auf den in der E-Mail enthaltenen Verifizierungslink zu klicken oder der Besitzer der E-Mail-Adresse hat die Verifizierung abgelehnt.", target.getEmail()), time);
                } else if(priviMessage == 6) {
                	target.setEmailVerify((byte) 2);
                	
                	Server.get().newMessage(Server.get().getButler().getName(), nick, "", String.format("Herzlichen Glückwunsch, du hast _erfolgreich die E-Mail-Adresse %s verifiziert_.##Solltest du zu einem späteren Zeitpunkt dein Passwort verlieren, so beantrage über die Startseite die Passwortneusetzung per E-Mail.##°R°_Ganz wichtig°r°_: Behalte dein Passwort aus Sicherheitsgründen stets für dich.", target.getEmail()), time);
                } else if(priviMessage == 7) {
                	String code = Password.generateRandom(15);
                	
                	Server.get().query(String.format("INSERT INTO `lostpassword` SET `nick` = '%s', `code` = '%s'", nick, code));
                } else if(priviMessage == 8) {
                	if(!target.getEmail().isEmpty()) {
                	}
                        
                        
                        // ThiefGame START // 
                        
                        
                } else if(priviMessage == 9) {
                	Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "ThiefGame", String.format("Jemand hat dir soeben _%s Punkte_ gespendet.", p), System.currentTimeMillis()/1000);
                	target.setThiefGame(target.getThiefGame()+Integer.parseInt(p));
               }
                
                
                      // ThiefGame ENDE //
                
                
                Server.get().query(String.format("update accounts set priviMessage='0',priviMessage2=''%s%s%s where name = '%s'", priviMessage == 4 && !online ? ", newPhotoComment='1'":"", priviMessage == 5 && !online ? ", emailVerify='0'":"",priviMessage == 6 && !online ? ", emailVerify='2'":"", nick));
            }
        } catch (SQLException e) {
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
        
        for (Channel channels : Server.get().getChannels()) {
        	if(channels.getNewVoteEnd() != 0 && channels.getNewVoteEnd() <= time) {
        		VoteBox.newVoteResult(channels);
        	}
        	
        	if(channels.history.size() > 0) {
        		for(long x : channels.history.keySet()) {
        			if(x <= time-600) {
        				channels.history.remove(x);
        			}
        		}
        	}
        	
            if(channels.getPfActive()) {
            	int mitspieler = Server.countChars(channels.getPfSpieler(), '|')/2;
            	
            	if(channels.getPfStatus() == 0) {
        			channels.broadcastButlerMessage(String.format("Eine _Kissenschlacht_ wurde von _°BB>_h%s|/serverpp \"|/w \"<r°_ gestartet! Bei diesem lustigen Spiel versuchen Mitspieler, _sich gegenseitig mit Kissen zu bewerfen_.##Wer zweimal getroffen wurde, scheidet aus, _dem Gewinner_ hingegen _winkt ein °>s/24.gif<°Knuddel_!##Alle, die mitspielen wollen: _°BB>/kissen +join|\"<r°_ eingeben.", channels.getPfStarter()));
        			channels.setPfTime(time);
        			channels.setPfStatus(1);
            	} else if(channels.getPfStatus() == 1) {
            		if((channels.getPfTime()+30)-time <= 0) {
            			if(mitspieler < 2) {
            				channels.broadcastButlerMessage("Die Kissenschlacht kann leider nicht stattfinden, da nicht genug Spieler angemeldet sind.");

                			for(Client c : channels.getClients()) {
                				c.send(PacketCreator.removePrefixIcons(channels.getName()));
                			}
                			
                			channels.setPfActive(false);
                			channels.setPfSpieler("");
                			
                			channels.setPfStatus(0);
            			} else {
            				StringBuilder spieler = new StringBuilder();
            				int lala = 1;
            				String eingabe = channels.getPfSpieler().replace("||", "~").replace("|", "");
                            String[] strarr = eingabe.split("~");
                            
                            Arrays.sort(strarr);
                            for (int i = 0; i < strarr.length; i++) {
                            	spieler.append(lala!=1?", ":"").append("°>_h").append(strarr[i].replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                            	
                            	Client l = Server.get().getClient(strarr[i]);
                            	
                            	if(l == null) {
                            		l = new Client(null);
                            		l.loadStats(strarr[i]);
                            	}
                            	
                            	l.setPfLeben((byte)3);
                            	lala++;
                            }
                            
            				channels.broadcastButlerMessage(String.format("_Gleich beginnt die Kissenschlacht_. Mitspieler sind:#%s", spieler.toString()));
            				channels.setPfTime(time);
                			channels.setPfStatus(2);
            			}
            		}
            	} else if(channels.getPfStatus() == 2) {
            		if((channels.getPfTime()+5)-time <= 0) {
            			channels.broadcastButlerMessage("Kissen los! °>pf.gif<°");
            			channels.setPfTime(time);
            			channels.setPfStatus(3);
            		}
            	} else if(channels.getPfStatus() == 3) {
            		if((channels.getPfTime()+300)-time <= 0) {
            			channels.broadcastButlerMessage("Die Kissenschlacht ist _vorbei_! Es gibt _keinen Gewinner_.");
            			
            			for(Client c : channels.getClients()) {
            				c.send(PacketCreator.removePrefixIcons(channels.getName()));
            			}
            			
            			channels.setPfActive(false);
            			channels.setPfSpieler("");
            			
            			channels.setPfStatus(0);
            		}
            	}
            }
        }
    }
}

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

import features.konto;
import funktionen.heart;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import starlight.*;

import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class Cronjob {

     public static String getMonat() {
         SimpleDateFormat dateef = new SimpleDateFormat("MM");
 String uzef = dateef.format(new java.util.Date());
       String monat="";
if(uzef.equals("01"))
{
monat="Januar";
}
if(uzef.equals("02"))
{
monat="Februar";
}
if(uzef.equals("03"))
{
monat="März";
}
if(uzef.equals("04"))
{
monat="April";
}
if(uzef.equals("05"))
{
monat="Mai";
}
if(uzef.equals("06"))
{
monat="Juni";
}
if(uzef.equals("07"))
{
monat="Juli";
}
if(uzef.equals("08"))
{
monat="August";
}
if(uzef.equals("09"))
{
monat="September";
}
if(uzef.equals("10"))
{
monat="Oktober";
}
if(uzef.equals("11"))
{
monat="November";
}
if(uzef.equals("12"))
{
monat="Dezember";
}
return monat;
   }
   
     
	@SuppressWarnings("deprecation")
	public static void execute(Client man) {    
            
          
            konto.KontoVermehrung(); 
		String datum = Server.get().timeStampToDate(System.currentTimeMillis()/1000); 
		String tag = new SimpleDateFormat("dd").format(new Date());
		Date dat = new Date();
    	long time = System.currentTimeMillis()/1000;
        String[] days = { "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};

        
         
          PoolConnection pcon2 = ConnectionPool.getConnection();
        PreparedStatement ps2 = null;
      
        try {
           Connection con = pcon2.connect();
           ps2 = con.prepareStatement("SELECT * FROM `accounts`");
          ResultSet rs = ps2.executeQuery();
          while (rs.next()) {
             String nickname = KCodeParser.escape(rs.getString("name"));

      boolean online = true;
       Client target = Server.get().getClient(nickname);
       if (target == null) {
         online = false;
        target = new Client(null);
        target.loadStats(nickname);
      }

      nickname = target.getName();
  
        if (target.getGrippeStatus() == 5) {
         if (online) {
             target.removeIcon("pics/nose.gif");
          
         }
            String text = "Endlich ist es vorbei! Du hast die Grippe besiegt und könntest wieder virtuelle Wälder entwurzeln. Die triefende Nase ist Vergangenheit, die aufgebauten Abwehrkräfte sollten Dich eine Zeit lang vor jeder neuen Infektion schützen.";
         target.sendMail(Server.get().getButler().getName(),text,"","",1,"0");
   
          target.setGrippeStatus((byte)0);
          target.saveStats();
      } else if (target.getGrippeStatus() == 4) {
           String text = "Erstmals nach den letzten Grippetagen fühlst Du Dich wieder besser. Der Gipfel der Erkrankung ist überschritten und lange wird es nicht mehr dauern";
          target.sendMail(Server.get().getButler().getName(),text,"","",1,"0");
           target.setGrippeStatus((byte)5);
          target.saveStats();
      } else if (target.getGrippeStatus() == 3) {         
          target.setGrippeStatus((byte)4);
          target.saveStats();
      } else if (target.getGrippeStatus() == 2) {        
          target.setGrippeStatus((byte)3);
          target.saveStats();
      } else if (target.getGrippeStatus() == 1) {
          String text = "Seit heute morgen fühlst Du Dich wirklich elend. Die Grippe scheint ihren Höhepunkt erreicht zu haben. Du spürst, dass Du dringend Ruhe brauchst.";
           target.sendMail(Server.get().getButler().getName(),text,"","",1,"0");
          target.setGrippeStatus((byte)2);
          target.saveStats();
      }
      
      if (target.getGrippeInfiziert() == 1) {
          
          target.setGrippeStatus((byte)1);
        
          if (online) {
              target.addIcon("pics/nose.gif",18);
          }
          String text = "Seit heute Morgen fühlst Du Dich gar mehr nicht wohl. Du hast Dir eine Grippe eingefangen, Deine Nase läuft und Dein Hals zieht sich langsam zu. Husten und Schnupfen wirst Du Dir wohl in den kommenden Tagen kaum verkneifen können.";
          target.sendMail(Server.get().getButler().getName(),text,"","",1,"0");
         
          target.setGrippeInfiziert(0);
          target.saveStats();
      }
     
      
      
          if (online) {
          target.setDailybonus(0);
      } else {
          Server.get().query("update accounts set dailybonus='0' where name='"+target.getName()+"'");
      }
          
          
          
      
         /* Inaktive Nicknamen Löschen */
                // Hole die letze OnlineZeit
              /*  
                long last_online        = target.getLastOnline();
                String teams = target.getTeams();
                int time_max_normal     = (60 * 60 * 24 * 90); // Nicks mit 90 Tagen inaktivität werden gelöscht
                int time_max_dmn        = (60 * 60 * 24 * 10); // Nicks mit DeleteMyNick werden nach 10 Tage gelöscht
                int min_rank            = 1;                   // Maximal Family   
                long is_deletemynick    = target.getDeletenick(); 
                boolean deleting        = false;
               // Exception bei Cronjob wegen Zeile 186 
                if (teams.isEmpty()) {
                if((last_online+ time_max_normal) <= (System.currentTimeMillis() / 1000)) {
                    if(target.getRank() <= min_rank && target.getSperre() ==0 && target.getDisable() ==0) {
                       deleting = true;
                    }
                }
                
                if((last_online+ time_max_dmn) <= (System.currentTimeMillis() / 1000)) {
                    if(is_deletemynick > 0 && target.getSperre() ==0 && target.getDisable() ==0) {
                       deleting = true;
                    }
                }
                 }
               
                if(deleting && target != Server.get().getButler()) {
                    
                  
                    // Nickname selbst löschen
                    Server.get().query(String.format("DELETE FROM `accounts` WHERE `name`='%s'", target.getName()));
                    
                    // Nachrichten von Nickname Löschen
                    Server.get().query(String.format("DELETE FROM `messages` WHERE `von`='%s' OR `an`='%s'", target.getName(), target.getName()));
              
                   // Smileys von Nickname Löschen
                    Server.get().query(String.format("DELETE FROM `sm_usersmileys` WHERE `user`='%s'", target.getName()));
              
                   // Profil-Feature Einträge Löschen
                    Server.get().query(String.format("DELETE FROM `acts` WHERE `von`='%s' OR `an`='%s'", target.getName(), target.getName()));
                                
                   // Email-Verifizierung Löschen
                    Server.get().query(String.format("DELETE FROM `emailverify` WHERE `name`='%s'", target.getName()));
              
                   // Fans Löschen   
                    Server.get().query(String.format("DELETE FROM `fans` WHERE `von`='%s' OR `an`='%s'", target.getName(), target.getName()));
              
                   // Fotoalben Löschen
                    Server.get().query(String.format("DELETE FROM `fotoalben` WHERE `von`='%s'", target.getName()));
 
                    // Foto Kommentare Löschen   
                    Server.get().query(String.format("DELETE FROM `photocomments` WHERE `von`='%s' OR `bei`='%s'", target.getName(), target.getName()));
               
                    // Foto Ansichten Löschen  
                    Server.get().query(String.format("DELETE FROM `photoviews` WHERE `name`='%s'", target.getName()));
              
                    // Rosen Löschen
                    Server.get().query(String.format("DELETE FROM `roses` WHERE `von`='%s' OR `an`='%s'", target.getName(), target.getName()));
               
                    // Tausch History Löschen
                    Server.get().query(String.format("DELETE FROM `tauschaktionen` WHERE `von`='%s' OR `an`='%s'", target.getName(), target.getName()));
               
                   // Loginliste Löschen
                    Server.get().query(String.format("DELETE FROM `loginlist` WHERE `name`='%s'", target.getName()));
              
                   // LostPaswordList Löschen
                    Server.get().query(String.format("DELETE FROM `lostpassword` WHERE `nick`='%s'", target.getName()));
              
                   // Lotto Löschen
                    Server.get().query(String.format("DELETE FROM `lotto` WHERE `name`='%s'", target.getName()));
           
                }
              */
          

          
      
           
                
                                /* Smileys Entwickeln */
                           // Exception bei Zeile 254-255 wegen Smiley Entwickeln
                        /*   for(String id : target.getSmileys().split("%%")) {
                           if (!id.isEmpty()) {
                           Usersmiley d =Server.get().getUsersmiley(id);
                           if (d != null) {
                           d.SmileyEntwicklung(false); // true = alle
        
                           }
                           }
                           }*/
             
             
          
                     
          }
        }
        catch (SQLException e) {
         e.printStackTrace();
       } finally {
          if (ps2 != null)
            try {
             ps2.close();
            }
            catch (SQLException e)
            {
            }
           pcon2.close();
        }
        
        
        for(Channel s : Server.get().getChannels()) {
        	s.setMutes("");
        	s.setCmutes("");
        	s.setCls("");
        }
        
        Server.onlineUsers.clear();
        
        
        
        
        for(Channel a : Server.get().getChannels()) {
        if(!a.getHolnick().isEmpty()) {
            Client holnick = Server.get().getClient(a.getHolnick());
            boolean online = true;
            
            if(holnick == null) {
            	online = false;
            	holnick = new Client(null);
            	holnick.loadStats(a.getHolnick());
            }
            
        	Server.get().newMessage(Server.get().getButler().getName(), a.getHolnick(), "High or Low", String.format("Hallo %s,##da dein High or Low Rekord nicht geknackt wurde, erhälst du %s Knuddels! Glückwunsch!##Liebe Grüße,#"+Server.get().getButler().getName(), a.getHolnick(), a.getHoljackpot()), time);
            
        	if(online) {
        		holnick.increaseKnuddels(a.getHoljackpot());
                      //  holnick.setHolsperre((byte)+1);
        	} else {
        		Server.get().query(String.format("update accounts set knuddels=knuddels+'%s' where name='%s'", a.getHoljackpot(),a.getHolnick()));
        	        Server.get().query(String.format("update accounts set holsperre=holsperre+'1' where name='%s'", a.getHolnick()));
        	
                }
        	
            a.setHoljackpot(0);
            a.setHolrunde(0);
            a.setHolnick("");
            String einsatz = "";
            // HIER 3 verschiedene Abfragen für jeden Channel eine
            if (a.getName().contains("Free")) {
                
            } else if (a.getName().contains("Pro")) {
                einsatz = " (Einsatz: 5 Knuddel)";
            } else if (a.getName().contains("Champion")) {
                einsatz = " (Einsatz: 10 Knuddel)";
            } else {
                 einsatz = " (Einsatz: 1 Knuddel)";
            } 
            
            
            a.setTopic("In diesem Channel kann High or Low gespielt werden. °>gt.gif<° °BB>Jetzt mitspielen!|/hol start<r°"+einsatz);
        }}
        
        
        
        
        Settings.setBan("");
        
        for(Client s : Server.get().getClients()) {
        	if(s.getNotrufsperre() <= time) {
        		s.setNotrufsperre(0);
                       
        	}

        	if(s.getSperre() <= time) {
                    if (s.getSperre() == 1) {
        	 s.setComment(time, null, null, Server.get().getButler().getName(), "Automatisch \"_entsperrt_!\"");
                    }
                    s.setSperre(0);
                       
                        
        	}
                
        
                
        	if(s.getSpielsperre() <= time) {
        		s.setSpielsperre(0);
        	}

        	if(s.getWahlsperre() <= time) {
        		s.setWahlsperre(0);
        	}
        	
                s.setGmute(0);
        	s.setAdminTime(0);
        	s.setSnp((byte)0);
        	s.setKissed((byte)0);
        	s.setWinksperre((byte)0);
        	s.setSunLock((byte)0);
        	s.setWeckMessage((byte)0);
        	s.setButterflysperre((byte)0);
        	s.setHeartMessage((byte)0);
        	s.setAnalysedatasperre((byte)0);
        	s.setCmcomments("");
                s.setHoldHands("");
                s.setHeroToday("");
        	s.setSexysperre((byte)0);
                s.setHerosperre((byte)0);
                s.setCoolsperre((byte)0);
        	s.setDreamsperre((byte)0);
        	s.setStarlitesperre((byte)0);
        	s.setFriendssperre((byte)0);
        	s.setKissall((byte)0);
        	s.setKnuddelscentLock((byte) 0);
                
                
                
             
                    
                
        }
        
        Server.get().query(String.format("update accounts set notrufsperre='0' where notrufsperre<='%s'", time));
        Server.get().query(String.format("update accounts set spielsperre='0' where spielsperre<='%s'", time));
        Server.get().query(String.format("update accounts set wahlsperre='0' where wahlsperre<='%s'", time));
        Server.get().query(String.format("update accounts set comments=CONCAT(`comments`, '_°>_h"+Server.get().getButler().getName()+"|/serverpp "+Server.get().getButler().getName()+"|/w "+Server.get().getButler().getName()+"<°_: Automatisch \"_entsperrt_!\" (%s)#') where sperre<='%s' and sperre!='1' and sperre != '0'", datum, time));
        Server.get().query(String.format("update accounts set sperre='0' where sperre<='%s' and sperre!='1'", time));
        Server.get().query(String.format("update accounts set regtage=regtage+'1'"));
        Server.get().query("delete from register");
    	Server.get().query("delete from donations");
    	Server.get().query("delete from photoviews");
    	Server.get().query("update wordmixrekord set nick='', sentence='', points=NULL, seconds=NULL");
    	
    	PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;

        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
 
            ResultSet rs = ps.executeQuery(String.format("select registration,schuetzlinge,name, career,rank,timeMonth,stammiwhen from accounts where rank>='1' and name != '"+Server.get().getButler().getName()+"'"));

            	String timeString = new SimpleDateFormat("M/yy").format(new Date());
            
                
                if(tag.equals("01")) {
                    Channel ch = Server.get().getChannel(Server.get().getSettings("START_CHANNEL"));
                    heart.functionMake(Server.get().getButler(), ch, "/heart !");
                   Server.get().getButler().saveStats();
            		while(rs.next()) {
            			String name = rs.getString("name");
            			String career = rs.getString("career");
            			String stammiwhen = rs.getString("stammiwhen");
            			int rang = rs.getInt("rank");
            			int timeMonth = rs.getInt("timeMonth");
            			
            			Client n = Server.get().getClient(name);
            			
            			if(timeMonth >= 90000 || rang >= 3) {
        					if(rang == 1) {
        						if(n != null) {
        							n.setRank((byte) 2);
                					n.setDate(datum);
                					n.setCareer(String.format("%s|%s Stammi|", n.getCareer(), datum));
        						} else {
        							Server.get().query(String.format("update accounts set rank='2', career='%s|%s Stammi|', date='%s' where name='%s'", career, datum, datum, name));
        						       
                                                        }
        					}
        					
        					if(n != null) {
            					n.setStammiwhen(String.format("%s%s%s", stammiwhen, stammiwhen.isEmpty()?"":", ", timeString));
            				} else {
            					Server.get().query(String.format("update accounts set stammiwhen='%s%s%s', stammiMonths=stammiMonths+'1' where name='%s'", stammiwhen, stammiwhen.isEmpty()?"":", ", timeString, name));
            				}
            				
            				Server.get().newMessage(Server.get().getButler().getName(), name, "Stammi", "Herzlichen Glückwunsch,##du wurdest soeben für den kommenden Monat in die Stammis aufgenommen. Stammis haben verschiedene, zusätzliche Privilegien (Rosenversand, Wahlteilnahme, ...) mit denen ich dir viel Vergnügen wünsche.##Liebe Grüße,##dein "+Server.get().getButler().getName(), System.currentTimeMillis()/1000);
            			} else {
            				if(rang == 2) {
            					if(n != null) {
            						n.setRank((byte) 1);
            						n.setCareer(String.format("%s|%s Familymitglied|", n.getCareer(), datum));
            						n.setDate(datum);
            					} else {
            						Server.get().query(String.format("update accounts set rank='1', career='%s|%s Familymitglied|', date='%s' where name='%s'", career, datum, datum, name));
            				         	  }
            				}
            			
            				Server.get().newMessage(Server.get().getButler().getName(), name, "Kein Stammi mehr", "Diesen Monat wurdest du leider nicht in die Stammis aufgenommen.##Nicht bös' drüber sein,##dein "+Server.get().getButler().getName(), System.currentTimeMillis()/1000);
            			
            		
            	// Automatische Nominierungssperre für CMs sofern Sie keine 2000 Mins erreicht haben.
                // Es fehlt die Abfrage dass es nur alle 2 Monate geprüft werden soll
                // weitere Änderungen... Chiller mal fragen!
                
            /*    if(timeMonth <= 120000 && checkCm = 1) {
            					if(n != null) {
            						n.setWahlsperre((byte) 1);
            						
            					
            					} else {
            						Server.get().query(String.format("update accounts set wahlsperre='1' where name='%s'", name));
            					}
            				}
            			
            				Server.get().newMessage(Server.get().getButler().getName(), name, "CM Nominierungs-Sperre", "Hallo %s,##leider kannst Du bei den nächsten CM-Wahlen nicht antreten. Du bist in der laufenden CM-Periode mit %s Onlineminuten unter der Voraussetzung von %s OnlineMinuten geblieben.##Liebe Grüße,##/kdein James", System.currentTimeMillis()/1000);
            			*/}
            		}
            	}
            	
            	rs.close();
                rs = ps.executeQuery(String.format("select name, career,mentor,registration from accounts where rank='0' and zeichen >= '10000' and onlineTime >= '60000'"));
                
                while(rs.next()) {
                	String name = rs.getString("name");
                	String career = rs.getString("career");
                	String mentor = rs.getString("mentor");
                	long registration = rs.getLong("registration");
                	
                	Client n = Server.get().getClient(name);
                	
                	if((time-registration)/86400 > 6) {
                		if(n != null) {
                			n.setRank((byte)1);
                			n.setCareer(String.format("%s|%s Familymitglied|", n.getCareer(), datum));
                			n.setDate(datum);
                		} else {
                			Server.get().query(String.format("update accounts set rank='1', career='%s|%s Familymitglied|', date='%s' where name='%s'", career, datum, datum, name));
                		          }
                		
                		Server.get().newMessage(Server.get().getButler().getName(), name, "Familymitgliedschaft", "Herzlich willkommen in der Familymitgliedschaft!##Von nun an hast du u.a. die Möglichkeit ein Herz zu verschenken, einen eigenen Channel und eine eigene Homepage anzulegen (...)##Liebe Grüße,#dein "+Server.get().getButler().getName()+"##P.S. Wähle mit _/mentor reward:NICK_ den Nick aus, der dir bei "+Server.get().getSettings("CHAT_NAME")+" bisher am meisten geholfen hat!", time);
                	
                		if(!mentor.isEmpty()) {
                                    Client men = Server.get().getClient(mentor);
                                
                			if(men != null) {                                            
                                          
                				men.increaseMentorPoints(20);
                			} else {
                				Server.get().query(String.format("update accounts set mentorPoints=mentorPoints+'20' where name='%s'", mentor));
                			}
                			
                			Server.get().newMessage(Server.get().getButler().getName(), mentor, "20 Mentorpunkte", String.format("Hallo %s,##da dein Schützling %s Familymitglied wurde, erhälst du _20 Mentorpunkte_!##Liebe Grüße,#dein "+Server.get().getButler().getName(), mentor, name), System.currentTimeMillis()/1000);
                    	}
                	}
                }
                
                rs.close();
                
                
                
                
                
                
                rs = ps.executeQuery(String.format("select name,timeDay from accounts where name != '"+Server.get().getButler().getName()+"' order by timeDay desc limit 1"));
                while(rs.next()) {
                	
                	
                	String name = rs.getString("name");
                	Client n = Server.get().getClient(name);
                	
                        if (n != null )  {
                             if(n.getGender()==1) {
             n.addIcon("pics/top_online_boy.png", 17);
        
    } else {
              n.addIcon("pics/top_online_girl.png", 17);
         
    }
                        }
                	Settings.setTopdayuser(name);
                	Server.get().newMessage(Server.get().getButler().getName(), name, "Top-Online-Day", "Herzlichen Glückwunsch, du stehst heute auf dem _ersten Platz der °BB°°>Top Online Day|/top online day<°_°r°.##Diese Besonderheit wird bei dir am heutigen Tag mit einem besonderem Icon (für alle sichtbar) belohnt.##Hochachtungsvoll,#dein "+Server.get().getButler().getName(), time);
                	
                }

                rs.close();
                
            	rs = ps.executeQuery(String.format("select name,adminTime,teams from accounts where teams!=''"));
                while(rs.next()) {
                	String teams = rs.getString("teams");
                	
                	StringBuilder teamsRight = new StringBuilder();
                	int cc = 1;
                	
                	for(String team : teams.split("\\|")) {
                		if(!team.isEmpty()) {
                			if(cc != 1) {
                				teamsRight.append(", ");
                			}
                			
                			teamsRight.append(team.split("~")[0]);
                		
                			cc++;
                		}
                	}
                	
                	int knuddels = ((rs.getInt("adminTime")/60)/6)*(CommandParser.countChars(teams, '|')/2)/10;
                	String name = rs.getString("name");
                	Client n = Server.get().getClient(name);
                	
                	if(knuddels > 0) {
                		if(n != null) {
                			n.increaseKnuddels(knuddels);
                		} else {
                			Server.get().query(String.format("update accounts set knuddels=knuddels+'%s' where name = '%s'", knuddels, name));
                		}
                		
                		Server.get().newMessage(Server.get().getButler().getName(), name, "Knuddels makes the world go around!", String.format("Hi %s,##für deinen ehrenamtlichen Einsatz erhälst du#als Dankeschön _%s_ Knuddels!##Das verdankst du deiner Mitarbeit in diesen Teams:##( %s)##Viel Vergnügen damit,##deine %s", name, knuddels, teamsRight.toString(), Server.get().getButler().getName()), time);
                	}
                }

                rs.close();
            	rs = ps.executeQuery(String.format("select name from accounts where rank>'0'"));
                while(rs.next()) {
                	String name = rs.getString("name");
                	Client n = Server.get().getClient(name);
                	int eins = Server.count(String.format("select sum(mpFriendlist) as a from accounts where mentor='%s' and mpFriendlist='1'", name));
                	int zwei = Server.count(String.format("select sum(mpChat) as a from accounts where mentor='%s' and mpChat='1'", name));
                	int gesamt = eins+zwei;

                	if(gesamt > 0) {
                		if(n != null) {
                			n.increaseMentorPoints(gesamt);
                		} else {
                			Server.get().query(String.format("update accounts set mentorPoints=mentorPoints+'%s' where name = '%s'", gesamt, name));
                		}
                		
                		Server.get().newMessage(Server.get().getButler().getName(), name, "Mentorenpunkte", String.format("Du hast _%s Mentorenpunkte_ für deine besondere Hilfsbereitschaft erhalten.##Diese Punkte kannst du auch für Mitglieder erhalten haben, die du schon vor längerer Zeit eingeführt hast.", gesamt), time);
                	}
                	if(n != null) {
                		for(Client s : Server.get().getClients()) {
                			if(s.getMpFriendlist() == 1 && s.getMentor().equals(name)) {
                				s.setMpFriendlist((byte)2);
                			}

                			if(s.getMpChat() == 1 && s.getMentor().equals(name)) {
                				s.setMpChat((byte)2);
                			}
                		}
                	} else {
                		Server.get().query(String.format("update accounts set mpFriendlist='2' where mentor='%s' and mpFriendlist='1'", name));
                		Server.get().query(String.format("update accounts set mpChat='2' where mentor='%s' and mpChat='1'", name));
                	}
                }
                
               if(tag.equals("01")) {
                    rs.close();
                    rs = ps.executeQuery(String.format("SELECT * FROM toplisten where name != 'timeDay'"));
                   
                    while(rs.next()) {
                        String name = rs.getString("name");
                        String word = rs.getString("word");
                    
                          SimpleDateFormat datee = new SimpleDateFormat("yyyy");
 String year = datee.format(new java.util.Date());
                    String month = getMonat();
                        
                        Client n = Server.get().getClient(name);
                        Statement p = con.createStatement();
                        ResultSet r = p.executeQuery(String.format("SELECT name,career FROM accounts where name != '%s' order by %s desc limit 3", Server.get().getButler().getName(), name));
                       
                        int platz = 1;
                       
                        while(r.next()) {
                                String nick = r.getString("name");
                                String career = r.getString("career");
                                String text = String.format("%s. Platz Top-%s im %s / %s", platz, word, month, year);
                                Client ns = Server.get().getClient(nick);
                                if(ns != null) {
                                        ns.setCareer(String.format("%s|%s %s|", ns.getCareer(), datum, text));
                                } else {
                                        Server.get().query(String.format("update accounts set career='%s|%s %s|' where name='%s'", career, datum, text, nick));
                                         }
                               
                                Server.get().newMessage(Server.get().getButler().getName(), nick, word, String.format("Hallo %s,##diesen Monat hast du den %s. Platz auf der Topliste %s geschafft.#Diese besondere Leistung wurde in Form eines Career Eintrags bei dir dokumentiert.##dein James", nick, platz, word), time);
                                platz++;
                        }
                    }
                }
                
                
                  if(tag.equals("01") || days[dat.getDay()].equals("Sonntag")) {
                    rs.close();
                    rs = ps.executeQuery("select * from accounts");
                      while (rs.next()) {
                       Client target = Server.get().getClient(rs.getString("name"));
                        if (target == null) {
                            target = new Client(null);
                           target.loadStats(rs.getString("name"));
                        }
                      
                            String[] l = target.getFeature("Rose-Sammeln");
                            Feature ft = Server.get().getFeature("Rose-Sammeln");
                           if (target.getRank() >= 2) {
                         if (!l[0].equals("2")) {
                        target.setRosesSend((byte)1);
                         } else {                        
                          target.setRosesSend((byte) (target.getRosesSend() + 1));  
                        }
                       
                        target.saveStats();
                    } 
}} 

                if(days[dat.getDay()].equals("Sonntag")) {
                    rs.close();
                    rs = ps.executeQuery("select name, lotto from accounts where lotto != '0' order by lotto limit 1");
                   
                    if(rs.next()) {
                    	String name = rs.getString("name");
                    	String lotto = rs.getString("lotto");
                    	int zahl = Server.get().getLottoJackpot()*2;
                    	Client n = Server.get().getClient(name);
                    	
                    	Server.get().newMessage(Server.get().getButler().getName(), name, "Lotto-Jackpot geknackt!", String.format("Herzlichen Glückwunsch,##Du hast mit deiner Zahl %s den _Lotto-Jackpot von %s Knuddels geknackt_!#Sie wurden dir soeben gutgeschrieben.##Liebe Grüße,#%s", lotto, zahl, Server.get().getButler().getName()), time);
                    	
                    	if(n != null) {
                    		n.increaseKnuddels(zahl);
                                
                    	} else {
                    		Server.get().query(String.format("update accounts set knuddels=knuddels+'%s' where name = '%s'", zahl, name));
                    	}

                    	for(Client s : Server.get().getClients()) {
                    		s.setLotto(0);
                    	}
                    	
                    	Server.get().query("update accounts set lotto='0'");
                    	Server.get().query(String.format("insert into lotto set zahl='%s',name='%s', knuddels='%s', datum='%s'", lotto, name, zahl, datum));
                    	Server.lotto.put(datum, new String[] {name, lotto, String.valueOf(zahl)});
                    	Server.get().setLottoJackpot(0);
                    }
                }
        	} catch(SQLException e) {
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
        
        	Server.sexy.clear();
                Server.cool.clear();
                
                for(Client all : Server.get().getClients()) {
                    all.saveStats();
                }
        	Server.get().query("update accounts set knuddelscentLock='0',snp='0',kissed='0',winksperre='0',sunLock='0',weckMessage='0',butterflysperre='0',timeDay='0',heartMessage='0',analysedatasperre='0',cmComments='',holdhands='',herotoday='',adminTime='0',sexysperre='0',herosperre='0',coolsperre='0',photoUploadLock='0', dreamsperre='0',starlitesperre='0', friendssperre='0',kissall='0'");
                   for(Client all : Server.get().getClients()) {
                    all.loadStats(all.getName());
                }
        	if(tag.equals("01")) {
        		Server.get().query("update accounts set `agePlus`='0',`timeMonth`='0',`heartLock`='0',`lcmonths`=`lcmonths`+'1',`lcsperre`='0'");
        		
                        
                        for(Channel a : Server.get().getChannels()) {
                            if (!a.getCms().isEmpty()) {
                                for(String nick : a.getCms().split("\\|")) {
                                    if (!nick.isEmpty()) {
                                     Client t = Server.get().getClient(nick);
                                     if (t == null) { t = new Client(null); t.loadStats(nick); }
                                     // m an t ^^ ode mom
                                     Server.get().newMessage(Server.get().getButler().getName(),t.getName(), "Dank für deinen Einsatz als CM!", "Hallo "+t.getName()+",##ich möchte mich bei dir ganz herzlich dafür bedanken, dass du dich im Channel "+a.getName()+" als ehrenamtlicher Channelmoderator engagiert hast und wünsche dir weiterhin viel Vergnügen bei "+Server.get().getSettings("CHAT_NAME")+".##Hochachtungsvoll,##dein "+Server.get().getButler().getName(), time);
                                     t.setHallOfFameMessage(1);
                                     t.saveStats();
                                    }
                                }
                            }
                            a.setCms("");
                            
                        }
                        
        		for(Client s : Server.get().getClients()) {
        			s.setAgePlus((byte)0);
        			s.setHeartsperre((byte)0);
        			s.setLcmonths((byte) (s.getLcmonths()+1));
        			s.setLCSperre((byte)0);
        			
        			
        		}
        		
        	} else if(days[dat.getDay()].equals("Sonntag")) {
        		Server.get().query("update accounts set flyingbedsperre='0',bazookasperre='0', holsperre='0'");
        		
        		for(Channel c : Server.get().getChannels()) {
        			c.setCmk(0);
        		}
        		
        		for(Client s : Server.get().getClients()) {
        			s.setFlyingbedsperre((byte)0);
        			s.setBazookasperre((byte)0);
                                s.setHolsperre((byte)0);
        			
        			
        		}
        		
        	}
        	
    		Logger.handle(null, "Aktualisierung erfolgreich ausgefuehrt!");
               if (man != null && man.getName() != null) {
                    man.sendButlerMessage(man.getChannel().getName(),"Aktualisierung erfolgreich ausgefuehrt!");
                }
    		Settings.setCronjob((System.currentTimeMillis()/1000)+86400);
	}
}

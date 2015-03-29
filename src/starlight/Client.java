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



package starlight;

import features.bomb;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import tools.huffman.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.*;
import java.util.List;
import java.util.Map;
import tools.IntegerUtil;

import tools.KCodeParser;
import tools.Logger;
import tools.PacketCreator;
import tools.Pair;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class Client {
	private final List<Channel> channels;
	private final Socket socket;
              private String codes,smileys,smileys2,effekt,allowedFeatures,codesinfo,codetradelog;
        private int tageslogin,codev,codee,smileyvisit,showeffekt; 
        private int dailybonus, firstSmiley,knuddelscent, schutzschild;
        private int tutorialsend,holrank, blitzrank,gendersmiley;
       
  private boolean konfetti;
            private int devilsbomb = 0;
	private OutputStream out;
	private List<Pair<String, Integer>> icons;
	private byte rank, forenrank, gender, messageSound;
	private float quizPoints;
         private String bad6ask;        
        private int bad6points = 0;
        private int bad6temp = 0;   
        private String bad6dran;
        private String partnerlook;
        private String levelsystem;
        private int level;
        private String lovepotion;
        private String amorsarrow;
        private float mixPoints;
        private float translatePoints;
        private float mathePoints;
        private float wordmixPoints;
        private float _blitzPoints;
        private float _quessPoints;
        private float _fiftyPoints;
        private long coin;
        private long washende,sunende,moskitoende;
        private int donate,forum_post_counter,_blitzDice,fans,photo_verify_last,moskitogestochen,moskitoabgewehrt;
        private boolean _blitzCan;
        private List<Integer> _blitzDiceHistory = new ArrayList<Integer>();
        public Map<Long, String[]> history = new HashMap<Long,String[]>();
        private float heroCounter;
        private int luckyCounter;
        private float userLogin;
        private float regTage;
        public String nicktag,rosenick,clink,album_cover_image,album_id,album_counter;
        public int dailysend = 0;
        public String dailyzahl = "";
        public String tinklelast;
        private int bad6;
	private byte emailVerify;
        private String loveyou,logincookie;
        private String neveralone;
        private String coinchannel;
	private byte emails;
	private byte tutOpen;
	private String authPassword;
	private byte authActive;
        private String joinSound;
        private String leftSound;
	private byte aktuTut;
        private String bad6nachwurfplayer;
        private String bad6gegner;       
        private int bad6gameid = 0;
        private String bad6user;
        private Long bad6askstart = System.currentTimeMillis()*0;
        private int bad6ok = 0;  
	private byte tutAktiv;
        private int hallOfFameMessage;
	private int element;
        private int elementrechner;
	private int tutopen,hp,gb,hpban;;
	private byte knuddelscentLevel;
	private byte knuddelscentLock;
	private byte readmeEffects;
	private byte newPhotoComment;
	private byte snp;
	private byte agePlus;
	private byte childJuSchuMessage;
	private byte warnings = 1;
	private byte kissed;
	private int dmWin;
	private int dmStartKnuddels;
	private int dm;
         //DARTEN
        private int dartenWurfLast = 0;
        private int dartenWurf = 0;
        private int dartenOpen = 0;
        private int dartenDran = 0;
        private String dartenGegner = "";
        private int dartenArt = 0;
        private int dartenEinsatzArt = 0;
        private int dartenEinsatz = 0;
        private int dartenWuerfel = 0;
        private int dartenPrivat = 0;
        //
        
        
        // Jörns Game
        private String lastGame = "";
        
        
        
        // ENDE
        
        // auctionme
         private String auctionend = "";
        private String lastbieter = "";
      private String auctionto = "";
      private String auctionfrom = "";
        
        // DARTEN LASTCONFIG 
        private int dartenLastWurf = 0;
        private String dartenLastGegner = "";
        private int dartenLastArt = 0;
        private int dartenLastEinsatzArt = 0;
        private int dartenLastEinsatz = 0;
        private int dartenLastPrivat = 0;
        //
	private byte sun;
        private byte sunLock;
	private byte weckMessage;
        private byte bazookasperre;
	private byte butterflysperre;
	private byte flyingbedsperre;
	private byte winksperre;
	private byte immunsperre;
	private byte healsperre;
	private byte heartMessage;
	private int kontoKnuddels;
	private byte mpChat;
	private byte mpFriendlist;
        private float knuddels;
	private byte belohnen;
	private int timeMonth;
	private int timeDay;
	private byte lcmonths;
	private byte holknuddelsrunde;
	private int thiefGame;
	private int hol; 
	private byte highOrLowNumber;
	private byte persofalsch;
        private byte analysedatasperre;
	private int kicks;
        private int locks;
        private int visit;
	private byte showEffects;
	private byte faSort;
        private byte fSort;
        private byte herosperre;
        private byte holsperre;
        private byte bot;
        private byte sexysperre;
        private byte coolsperre;
	private int admincallFirst;
	private int admincallSecond;
	private int admincallThird;
	private byte holrunde;
	private int aufrufe;
	private byte photo_verify;
	private int jumpopunkte;
	private byte kissall;
	private byte silence;
        private byte dreamsperre;
	private byte starlitesperre;
	private byte friendssperre;
	private byte loginCategory;
	private byte verify;
	private byte heartsperre;
	private byte grippestatus;
        private int grippeinfiziert;
	private byte newssperre;
	private int lotto;
	private int roses;
	private byte rosesSend;
	private int flames;
	private int cmutes;
	private int mutes;
	private int cls;
	private int zeichen;
        private int postCountChanged;
	private byte lcsperre;
	private byte searchAgeFrom;
	private byte searchAgeUntil;
	private byte showEmail;
	private byte showZodiac;
	private byte showBirthday;
	private byte searchActivate;
	private byte effect;
	private byte mask;
	private int mentorPoints;
	private int id;
	private int kisses, tinkle;
        private String partnerlookFrom = "", partnerlookTo = "";
        private String lovepotionFrom = "", lovepotionTo = "";
        private String loveto, lovefrom;
       private int lovewait;
	private byte age;
	private byte spam;
	private int onlineTime;
	private int adminTime;
	private byte pfLeben;
	private long wahlsperre;
	private long pfGeworfen;
	private long botkontrolleZeit;
	private long sperre;
        private long disable;
        private int wStyle;
        private long gmute;
        private String partnerlookTimeout = "";
        private String lovepotionTimeout = "";
        private String amorsarrowTimeout = "";
        private String amorsarrowAkzeptTo = "0";
        private String amorsarrowNick = "";
        private String amorsarrowAkzeptFrom = "0";
        private String amorsarrowFrom = "", amorsarrowTo = "";
        private long deletenick;
	private long spielsperre;
	private long notrufsperre;
	private long lastOnline;
	private long timeOut;
	private long loginTimestamp;
	private long lastAction;
	private long login;
        private String highOrLowChannel;
	private String missed;
	private String like;
	private String holdhands;
	private String zusammen;
        private String heroToday;
        private String seeFriends;
        private String turnedHeadTo;
        private String turnedHeadFrom;
        private String rhapsodyto;
        private String rhapsodyfrom;
        private String adoreTo;
        private String adoreFrom;
	private String host;
	private String botkontrolleAn;
	private String botkontrolleVon;    
	private String color;
	private String agent;
	private String kontoPassword;
	private String sperreinfo;
        private String disableinfo;
	private String sperrevon;
        private String mutevon;
        private String channellockvon;
	private String heart;
	private String photo;
        private String lastContact;        
	private String newChannel;
	private String lastChannel;
	private String deny;
	private String schuetzlinge;
	private String stammiwhen;
	private String cmwhen;
	private String uPassword;
	private String quizrang;
	private String spitznamen;
	private String mentor = "";
	private String javaVersion;
	private String desc;
	private String plz;
	private String plzLand;
	private String comments;
	private String cmcomments;
        private String syscomments;
	private String teams;
	private String friendlist,friendask;
	private String appletVersion;
        private String loginUrl;
	private String career;
        private String career2;
        private String highlights;
	private String profile;
	private String date;
	private String awayReason;
	private String name;
	private String dream;
	private String friends;
	private String registerIP;
	private String lc;
	private String birthday;
	private String vergeben;
	private String searchGender;
	private String searchEntfernung;
	private String searchMotiv;
	private String email;
	private String signatur;
	private String lastOnlineChannel;
	private String readme;
	private String password;
	private String realname;
	private String stadt;
	private String land;
	private String hobbys;
	private String job;
	private String motto;
	private String registrationDate;
	private String registrationTime;
        private String registrationTime2;
	private String ipAddress;
	private String ignoredNicks;
        private String visitNicks;
	
	public Map<Long, String> readmes = new HashMap<Long, String>();
	public List<String[]> logins = new ArrayList<String[]>();
	public List<String[]> newRoses = new ArrayList<String[]>();
	public List<String[]> rosen = new ArrayList<String[]>();
	public List<String[]> admincalls = new ArrayList<String[]>();
        
	public List<String> receivedHearts = new ArrayList<String>();
	public List<String[]> oldMessages = new ArrayList<String[]>();
	public List<String[]> sentMessages = new ArrayList<String[]>();
	public List<String[]> newMessages = new ArrayList<String[]>();
        public List<String[]> archivMessages = new ArrayList<String[]>();
    
	
	public int getWarnings() {
		return warnings;
	}
        
         public static int countWords(String text, String word){
        int count=0;
        Pattern pat = Pattern.compile(Pattern.quote(word));
        Matcher m;
        for(m = pat.matcher(text); m.find(); count++);
        return count;
    }
         

	public int existBlindDate() {
		int exist = 0;

		PoolConnection pcon = ConnectionPool.getConnection();
		PreparedStatement ps = null;

		try {
			Connection con = pcon.connect();
			ps = con.prepareStatement("SELECT `id`,`user` FROM `blinddate` WHERE `user` = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				exist = 1;
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

		return exist;
	}

	public void increaseWarnings() {
		warnings++;
	}

	public void setAdminTime(int adminTime) {
		this.adminTime = adminTime;
	}

	public void setWarnings(byte warnings) {
		this.warnings = warnings;
	}
        
        
       public int getGendersmiley() {
           return gendersmiley;
       }
      
        public void setGendersmiley(int a) {
            if (a == 1 && gendersmiley != 1) {
             if (gender == 1) {
                 removeIcon("pics/male.png");
                 addIcon("pics/features/gendericon/ft_11-09_boy...iconoverflow.png", 21);
                 
		} else if (gender == 2) {
                    removeIcon("pics/female.png");
                    addIcon("pics/features/gendericon/ft_11-09_girl...iconoverflow.png", 21);
              }
        } else if (a == 0 && gendersmiley != 0) {
   if (gender == 1) {
            removeIcon("pics/features/gendericon/ft_11-09_boy...iconoverflow.png");
            addIcon("pics/male.png",16);                                  
		} else if (gender == 2) {
                 removeIcon("pics/features/gendericon/ft_11-09_girl...iconoverflow.png");
                 addIcon("pics/female1.png", 14);
                    
              }
}
            gendersmiley = a;
        }
        
        public boolean haveSchutzschild() {            
            String[] l = getFeature("Schutzschild");
            if (l[0].equals("2")) {
                if (schutzschild == 1) {
                return true;
                }
            }            
            return false;
        }
        public long getWashEnde() {
            return washende;
        }
        public void setWashEnde(Long a) {
            washende = a;
        }
        
        public long getCoin()
  {
    return this.coin;
  }
  
  public void setCoin(long coin)
  {
    this.coin = coin;
  }
        
        
        public long getSunEnde() {
            return sunende;
        }
        public void setSunEnde(Long a) {
            sunende = a;
        }
        
  public long getMoskitoEnde() {
            return moskitoende;
        }
        public void setMoskitoEnde(Long a) {
            moskitoende = a;
        }
        
	public long getLogin() {
		return login;
	}
  
        
        
        public String getRhapsodyFrom() {
               return rhapsodyfrom;               
           }
           public void setRhapsodyFrom(String a) {
              rhapsodyfrom = a;
           }           
           public String getRhapsodyTo() {                 
            return rhapsodyto; 
           }
           public void setRhapsodyTo(String a) {
               rhapsodyto = a;
           }
              
         public String getCoinchannel()
          {
            return this.coinchannel;
          }

         public void setCoinchannel(String coinchannel)
         {
              this.coinchannel = coinchannel;
          }
           
        
            public String getAlbum_cover_image() {
            return album_cover_image;
        }
        public void setAlbum_cover_image(String a) {
            album_cover_image = a;
        }
          public String getAlbum_id() {
            return album_id;
        }
        public void setAlbum_id(String a) {
            album_id = a;
        }
         public String getAlbum_counter() {
            return album_counter;
        }
        public void setAlbum_counter(String a) {
            album_counter = a;
        }
         public int getHP() {
            return hp;
        }
        public int getGB() {
            return gb;
        }
        public void setHP(int a) {
            hp = a;
        }
        public void setGB(int a) {
            gb = a;
        }
        public int getHPBan() {
    return hpban;
}
public void setHPBan(int a) {
    hpban = a;
}
        
        
        public String getHighOrLowChannel() {
               return highOrLowChannel;

      }
    
     public void setHighOrLowChannel(String highOrLowChannel) {
		this.highOrLowChannel = highOrLowChannel;

	}
        
        
       public void setNewCode(String text) {
             
             
              if (codesinfo.isEmpty()) {
                 codesinfo = " |~~~| ";
             }
             
             
             // 0 erhalten 1 neue
             String[] info = codesinfo.split("\\|~~~\\|");
             String neu = "";
             neu = info[0].trim()+"|~~~|"+info[1].trim()+text;
             codesinfo = neu;
             saveStats();
         }
         
            public String getAllowedFeatures() {
                return allowedFeatures;
            }
            public void setAllowedFeatures(String c) {
                allowedFeatures = c;
            }
         
         public String getNewCode() {
             
             
             if (codesinfo.isEmpty()) {
                 codesinfo = " |~~~| ";
             }
             
              String[] info = codesinfo.split("\\|~~~\\|");
             if (info[1].trim().isEmpty()) {
                 return "";
             }
              String[] neue = info[1].trim().split("\\|~~\\|");
              return neue[0];             
         }
         public String[] getCodeLog() {
              String[] info = codesinfo.split("\\|~~~\\|");
             return info[0].trim().split("\\|~~\\|");
         }
         
         
        
           public void setSmiley(String smileyid) {
               
              Smiley sms = Server.get().getSmiley(smileyid);
            
                 Server.get().query("insert into `sm_usersmileys` set smid='"+sms.getID()+"',name='"+sms.getName().replace("\'","\\\'")+"',kategorie='"+sms.getKategorie()+"',user='"+getName()+"', `basic`='',feature1ban='', verliehen='',feature2ban='', feature3ban=''");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_usersmileys` WHERE smid='"+sms.getID()+"' and `name` = '"+sms.getName().replace("\'","\\\'")+"' and user='"+getName()+"' and `basic`='' order by id desc limit 1");
            
            if (rs.next()) {
               Usersmiley put = new Usersmiley(rs);
                   synchronized (Server.usersmileys) {
             Server.usersmileys.put(rs.getString("id"), put);
                   }
             setSmileys(rs.getString("id")+"%%"+getSmileys());
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
    //    ModuleCreator.UPDATE_SB(this);
        }
         
         
            public void saveValues() {
                this.saveStats();
	}
            public void CodeWithKnuddel(int anzahl) {
          if (anzahl >= 1) {
              String betreff = "Code bringt Knuddel";
           String text = "Hallo "+name+",##heute scheint _dein Glückstag_ zu sein!#Dein aktivierter Code hatte noch _"+anzahl+" Knuddel_ im Gepäck, die ich dir gerade gutgeschrieben habe.##Viel Spaß beim Knuddeln,##dein "+Server.get().getButler().getName();
         
            //Server.get().newMessage(Server.get().getButler().getName(), getName(), betreff, text, System.currentTimeMillis()/1000);
        
           
           sendButlerMessage(getChannel().getName(),text); // als /m senden
          setKnuddels((int)getKnuddels()+anzahl);
          }
          }
            
           public void sendMail(String von, String text, String betreff,String icon,int sig, String sendam) {
      
               Server.get().newMessage(von,name,betreff,text, System.currentTimeMillis()/1000);
            }
         public String[] getFeature(String name) {
         
             
             String useable = "0";
             String ids = "";
             int pos = 0;
             int ban = 0;
             int allowed = 0;
             int anzahl = 0;
             String bantime = "0";
            
            
             
             String li = smileys+smileys2;
             for(String id : li.split("%%")) {
                 if (!id.isEmpty()) {
                   int have = 0;
                  
               Usersmiley sm = Server.get().getUsersmiley(id);
              if (sm != null) {
               if (sm.getUser().equals(getName()) && sm.getVerliehen().isEmpty()) {
                   have = 1;
               } else if (!sm.getVerliehen().isEmpty()) {
                  String[] z = sm.getVerliehen().split("\\|");
                  if (z[0].equals(getName())) {
                   have = 1;
                  } else {
                    have = 0;
                  }
               } 
                
               if (have == 1) {
               Smiley sml = Server.get().getSmiley(String.valueOf(sm.getSMID()));
             if (!sm.getBan1().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(sm.getBan1())) {
                  sm.setBan1("");
                 //   ModuleCreator.UPDATE_SB(this);
              }}          
              if (!sm.getBan2().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(sm.getBan2())) {
                  sm.setBan2("");
                //        ModuleCreator.UPDATE_SB(this);
              }}
               if (!sm.getBan3().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(sm.getBan3())) {
                  sm.setBan3("");
                    // ModuleCreator.UPDATE_SB(this);
              }}
              sm = Server.get().getUsersmiley(id);
               if (sml.getFT1().equals(name)) {
                   anzahl++;
                  if (sm.getBan1().isEmpty()) {
                      useable = "2";
                      pos = 1;
                      allowed++; 
                      bantime = String.valueOf(sml.getBanTime1());
                      ids = String.valueOf(sm.getID());
                  } else {
                      if (!useable.equals("2")) {
                          useable = "1";
                           bantime = String.valueOf(sml.getBanTime1());
                          if (ban == 0 || ban > Integer.parseInt(sm.getBan1())) {
                              ban = Integer.parseInt(sm.getBan1());
                          }
                      }
                  }
                  
               } else  if (sml.getFT2().equals(name)) {
                     anzahl++;
                    if (sm.getBan2().isEmpty()) {
                      useable = "2";
                      allowed++; 
                      pos = 2;
                      bantime = String.valueOf(sml.getBanTime2());
                        ids = String.valueOf(sm.getID());
                  } else {
                      if (!useable.equals("2")) {
                          useable = "1";
                           bantime = String.valueOf(sml.getBanTime2());
                            if (ban == 0 || ban > Integer.parseInt(sm.getBan2())) {
                              ban = Integer.parseInt(sm.getBan2());
                          }
                      }
                  }
               } else  if (sml.getFT3().equals(name)) {
                     anzahl++;
                    if (sm.getBan3().isEmpty()) {
                      useable = "2";
                      allowed++; 
                      bantime = String.valueOf(sml.getBanTime3());
                      pos = 3;
                        ids = String.valueOf(sm.getID());
                  } else {
                      if (!useable.equals("2")) {
                          useable = "1";
                           bantime = String.valueOf(sml.getBanTime3());
                            if (ban == 0 || ban > Integer.parseInt(sm.getBan3())) {
                              ban = Integer.parseInt(sm.getBan3());
                          }
                      }
                  }
               }
               
                     
               } 
             }}}
             
             if (!useable.equals("2")) {
                    if (countWords(allowedFeatures,"|"+name+"|") >= 1) {
                 useable = "2";
                 pos = 4; 
                 allowed++; 
                 ids = "own";
             }
                 
             } 
             if (hasPermission("use.allFeature")) {
                 useable = "2";
             }
             
             
             
             return new String[] { useable,ids,String.valueOf(anzahl),String.valueOf(pos),bantime,String.valueOf(ban),String.valueOf(allowed)};
         }
         
         public String getCodeTradeLog() {
             return codetradelog;
         }
         public void setCodeTradeLog(String v) {
             codetradelog = v;
         }
         
       
        
          public void setTageslogin(int a) {
             tageslogin = a;
         //    ModuleCreator.UPDATE_SB(this);
          }
          public void setTutorialsend(int a) {
             tutorialsend = a;
         //    ModuleCreator.UPDATE_SB(this);
            
         }
          
          public void setHolrank(int a) {
             holrank = a;
         //    ModuleCreator.UPDATE_SB(this);
            
         }
          
          public void setBlitzrank(int a) {
             blitzrank = a;
         //    ModuleCreator.UPDATE_SB(this);
            
         }
          
          
          
         public int getTageslogin() {
             return tageslogin;
         }
         public int getTutorialsend() {
             return tutorialsend;
         }
         
         public int getHolrank() {
             return holrank;
         }
         
          public int getBlitzrank() {
             return blitzrank;
         }
         
         
     // auctionme
         
        public void setAuctionFrom(String a) {
            auctionfrom = a;
        }
        
        public void setAuctionTo(String a) {
            auctionto = a;
        }
        
        
        public String getAuctionFrom() {
            return auctionfrom;
        }
        
        public String getAuctionTo() {
            return auctionto;
        }
       public String getRestdauerAuction() {
             String aus = "";
         if (!auctionend.isEmpty()) {
           Long ende = Long.parseLong(auctionend);        
           Long aktu = System.currentTimeMillis()/1000;
           Long seks = ende-aktu;
           if (seks > 1) {
                int sek1 = 1;
                int min = (60*sek1);
                int std = (60*min);
                int tag = (24*std);
                int wochen = (7*tag);              
                int sek = Integer.parseInt(String.valueOf(seks)); 
                int wochenrg = sek/(tag)/7;
                int tagerg = (sek%wochen)/(tag);
                int stderg = (sek%tag)/(std);
                int minerg = (sek%std)/(min);
                int sekerg = (sek%min*sek1); 
               if (wochenrg > 0) {
                   aus += wochenrg+" Wochen ";
               }
               if (tagerg > 0) {
                   aus += tagerg+" Tage ";
               }
                if (stderg > 0) {
                   aus += stderg+" Stunden ";
               }
                if (minerg > 0) {
                   aus += minerg+" Minuten ";
               }
               /*  if (sekerg > 0) {
                   aus += sekerg+"";
               }*/
                
                
               
           
           } else {
               String betreff = "Versteigerung beendet";
               String text = "";
               String rest = "0";
               String geb = "0";
               
               if (!lastbieter.isEmpty()) {
                    String[] x = lastbieter.split("~");
           
                   for(Channel channels : getChannels()) {                   
             channels.broadcastPicAction(">","_°>_h"+x[1]+"|/serverpp \"|/w \"<°_ hat _°>_h"+name+"|/serverpp \"|/w \"<°_ für "+x[0]+" °>sm_classic_yellow...h_0.gif<° ersteigert!","features/auctionme/auction_profile-start...h_16.png");
            }
               
                double r = Integer.parseInt(x[0])*0.95;
            geb = String.valueOf((int)(Integer.parseInt(x[0])-r));
            rest = String.valueOf((int)r); 
            if (geb.equals("0")) {
            text = "Du wurdest von _°BB>_h"+x[1]+"|/serverpp \"|/w \"<°°°_ für _"+x[0]+" Knuddels_ ersteigert. Es wurden keine Gebühren abgezogen. °>smileys/sm_abo-wlcm2012_frenzy-ani.gif<°##Knuddelige Grüße,#dein "+Server.get().getButler().getName();
            } else{
                text = "Du wurdest von _°BB>"+x[1]+"|/w \"<r°_ für _"+x[0]+" Knuddels_ ersteigert. Es wurden _"+geb+" Knuddels_ Gebühren abgezogen, du erhälst die restlichen _"+rest+" Knuddels_. >(:D)<##Knuddelige Grüße,#dein "+Server.get().getButler().getName();
            }
            
              Client target2 = Server.get().getClient(x[1]);
            boolean online2 = true;            
            if (target2 == null) {
                online2 = false;
                target2 = new Client(null);
                target2.loadStats(x[1]);
          } 
            String text2 = "Du hast _°BB>"+name+"|/w \"<r°_ für _"+x[0]+" Knuddels_ ersteigert.##Knuddelige Grüße,#dein "+Server.get().getButler().getName();
             Server.get().newMessage(Server.get().getButler().getName(), target2.getName(), betreff, text2, (System.currentTimeMillis()/1000)); 
            
            target2.setAuctionTo(target2.getAuctionTo()+"|"+name+"|"); // hat
            setAuctionFrom(target2.getName()); 
               } else {
             text = "Die Versteigerung von dir ging leider ohne einen Bieter zu ende.";      
               }
         Server.get().newMessage(Server.get().getButler().getName(), name, betreff, text, (System.currentTimeMillis()/1000)); 
            
             increaseKnuddels(Integer.parseInt(rest));
               setAuctionEnd("");
               setLastBieter("");
               saveStats();
           }}
           return aus.trim();
       }
        public void setAuctionEnd(String a) {
            auctionend = a;
        }
        public String getAuctionEnd() {
            return auctionend;
        }
        
        public void setLastBieter(String a) {
            lastbieter = a;
        }
        public String getLastBieter() {
            return lastbieter;
        }
        
        
           public int getPostCountChanged() {
		return postCountChanged;
	}

	public void setPostCountChanged(int postCountChanged) {
		this.postCountChanged = postCountChanged;
	}
        
        
         public int getSchutzschild() {
           return schutzschild;
       } 
         
         public void setSchutzschild(int schutzschild) {
           this.schutzschild = schutzschild;
            
       }
        
         
       /* KnuddelsCents by Chiller */
       public int getDailybonus() {
           return dailybonus;
       } 
       
       public int getFirstSmiley() {
           return firstSmiley;
       } 
       
       public int getDailysend() {
           return dailysend;
       }
       
       public int getKnuddelscent() {
           return knuddelscent;
       }
       public void setKnuddelscent(int a) {
           knuddelscent = a;
         //    ModuleCreator.UPDATE_SB(this);
       }
              
       public void setDailybonus(int set) {
           this.dailybonus = set;
            
       }
       
       public void setFirstSmiley(int set) {
           this.firstSmiley = set;
            
       }
       
       
        public void setDailysend(int set) {
           this.dailysend = set;
       }
        
        public String getDailyZahl() {
            return dailyzahl;
        }
        public void setDailyZahl(String text) {
            this.dailyzahl = text;
        }
        
        /* KnuddelsCent ENDE */
         
         
         
       
         public void setMentorSmileys() {
             // settings
             boolean useall = false;
             if (hasPermission("useallmentorsmileys")) {
                 useall = true;
             }
           String mentorsm = "Der Standardsmiley (1/14)~100|Der Traurige (2/14)~200|Der Unentschlossene (3/14)~300|Der Laut-Lacher (4/14)~400|Der Verblüffte (5/14)~500|Der Zwinkernde (6/14)~600|Der mit der dicken Nase (7/14)~700|Der Gebisszeiger (8/14)~800|Der Unschuldsengel (9/14)~900|Der ganz traurige (10/14)~1000|Der K.O. Smiley (11/14)~1200|Der Australier (12/14)~1500|Der Verrückte (13/14)~2000|Der Coole (14/14)~2500";
             int ranktousefirstfour = 3; // ab rang 2 die ersten 4 mentorsmileys
             // methode start
            int frompoints = 1;
            int punkte = getMentorPoints();
            int rang = getRank();            
           if (useall) {
                punkte = 2500;
                frompoints = 0;
            } else if (rang >= ranktousefirstfour && punkte < 400) {
                punkte = 400;
                frompoints = 0;
            }
            
            
            
            for(String value : mentorsm.split("\\|")) {
            String[] vals = value.split("~");
            boolean densmileyhabe = false;
            Smiley s = Server.get().getSmiley2(vals[0]);
          
            if (Integer.parseInt(s.getCounter(name,true)[0]) >= 1) {
            densmileyhabe = true;
            }
            if (Integer.parseInt(vals[1]) <= punkte && !densmileyhabe) {
              setSmiley(String.valueOf(s.getID()));
             
                if (frompoints == 1) {
                    String betreff = "Neuer Mentor-Smiley";
                    String text = "Herzlichen Glückwunsch,##Du hast soeben die _"+vals[1]+"er-Grenze der Mentorenpunkte_ überschritten!##Von nun an kannst Du den Smiley "+s.getKCode()+" verwenden:#_"+s.getName2()+"_##"+s.getText()+"##"+s.getSyntax()+" = "+s.getKCode()+"##Dein "+Server.get().getButler().getName();
                  
                    Server.get().newMessage(Server.get().getButler().getName(), getName(), betreff, text, System.currentTimeMillis()/1000);
        
                    
                //    sendButlerMessage(getChannel().getName(),text);
                    // 
                }
                
            }
             if (Integer.parseInt(vals[1]) > punkte && densmileyhabe) {
                removeSmiley(s.getName2());
               
            }
            
            }
           }
         
         public String getCodeinfo() {
             return codesinfo;
         }
         public String getEffect() {
             return effekt;
         }
         public int getShowEffects() {
             return showeffekt;
         }
         public void setNewCodeLog(String text) {
             
             String[] info = codesinfo.split("\\|~~~\\|");
             String neu = "";
      
             neu = text+"|~|"+(System.currentTimeMillis()/1000)+"|~~|"+info[0].trim()+"|~~~|"+info[1].trim().replace(text+"|~~|","")+" ";
             codesinfo = neu;
         
             
         }
         
       
        public int getCodeV() {
            return codev;
        }
        public int getCodeE() {
            return codee;
        }
        public int getSmileyvisit() {
            return smileyvisit;
        }
        public void setSmileyvisit(int a) {
           smileyvisit = a;
        }
        public void setCodeV(int a) {
            codev = a;
            saveStats();
        }
        public void setCodeE(int a) {
            codee = a;
            saveStats();
        }
    
        public void setEffect(String a) {
            effekt = a;
        }
        public void setShowEffects(int a) {
            showeffekt = a;
        }
      

        public void setCodes(String value) {
            codes = value;
        }
       public void removeCode(String value) {
           String neu = "";
           
        
              String[] sx = codes.split("%%"); 
              for(String x : sx) {
                  if (!x.isEmpty()) {
                     String[] a = x.split("\\|");
                     if (a[0].equals(value)) {
                     } else {
                         neu += x+"%%";
                     }
                      
                  }
              }
           
           codes = neu;
       }
        
           public void setLevelInfo(String typ,int anzahl) {
            // zeichen:m:p:a:kiss:readme:w:knuddel:go
        int pos = 0;
            if (typ.equals("zeichen")) {
              pos = 0;  
            } else if (typ.equals("m")) {
              pos = 1;  
            }else if (typ.equals("p")) {
              pos = 2;  
            }else if (typ.equals("a")) {
              pos = 3;  
            }else if (typ.equals("kiss")) {
              pos = 4;  
            }else if (typ.equals("readme")) {
              pos = 5;  
            }else if (typ.equals("w")) {
              pos = 6;  
            }else if (typ.equals("knuddel")) {
              pos = 7;  
            }else if (typ.equals("go")) {
              pos = 8;  
            }
            
            String[] infos = getLevelInfos().split(":");
            int z = 0;
            String newinfo = "";
            for(String number : infos) {
                if (z == pos) {
                    int num = Integer.parseInt(number);
                    int neu = num+anzahl;
                    newinfo += neu+":";
                } else {
                    newinfo += number+":";
                }
                z++;
            }
            levelsystem = newinfo;
            infos = levelsystem.split(":");
            int havezeichen = Integer.parseInt(infos[0]);
            int havem = Integer.parseInt(infos[1]);
            int havep = Integer.parseInt(infos[2]);
            int havea = Integer.parseInt(infos[3]);
            int havekiss = Integer.parseInt(infos[4]);
             int havereadme = Integer.parseInt(infos[5]);
             int havew = Integer.parseInt(infos[6]);
             int haveknuddel = Integer.parseInt(infos[7]);
             int havego = Integer.parseInt(infos[8]);
            
            int needzeichen = (getLevel()+1)*2500;
            int needm = (getLevel()+1)*20;
            int needp = (getLevel()+1)*50;
            int needa = (getLevel()+1)*100;
            int needkiss = (getLevel()+1)*15;
            int needreadme = (getLevel()+1)*5;
            int needw = (getLevel()+1)*30;
            int needknuddel = (getLevel()+1)*5;
            int needgo = (getLevel()+1)*10;
if (getLevel() < 99) {
if (havezeichen >= needzeichen && havem >= needm && havep >= needp  && havea >= needa && havekiss >= needkiss && havereadme >= needreadme && havew >= needw && haveknuddel >= needknuddel && havego >= needgo) {
    setLevel(getLevel()+1);
   
}
}
            
        }

        
        
        
        public void setLevel(int neu) {
            String betreff = "Levelaufstieg";
            String message = "Hallo "+name+",#du bist soeben von Level °>levels/"+level+".png<° auf Level °>levels/"+neu+".png<° aufgestiegen.";
         
           
            if (neu <= 50) {
                String smname = "Sondersmiley - Level "+neu;
                   String id = "";
        for(Smiley sm : Server.get().getSmileys()) {
            if (sm.getName2().equals(smname)) {
                id = String.valueOf(sm.getID());
            }
        }
        Smiley dd = Server.get().getSmiley(id);
            if (dd != null) {
              message += "##Zudem erhälst du den Smiley "+dd.getName()+"#"+dd.getSyntax()+" = "+dd.getKCode()+"#"+dd.getText();  
             setSmiley(String.valueOf(dd.getID()));
        setCodeE(getCodeE()+1);    
            }    
            }
            
           message += "##Dein "+Server.get().getButler().getName();
          Server.get().newMessage(Server.get().getButler().getName(),name, betreff, message, System.currentTimeMillis()/1000); 
         
            
            level = neu;
        }
        public String getLevelInfos() {
            return levelsystem;
        }
        public int getLevel() {
            return level;
        }
        
       public String deleteSmiley(String id) {
           String aaa = "";
           for(String x : smileys.split("%%")) {
               if (!x.isEmpty()) {
                   if (!x.equals(id)) {
                       aaa += x+"%%";
                   }
               }
           }
          return aaa;
       }
       
        public String deleteSmiley2(String id) {
           String aaa = "";
           for(String x : smileys2.split("%%")) {
               if (!x.isEmpty()) {
                   if (!x.equals(id)) {
                       aaa += x+"%%";
                   }
               }
           }
           return aaa;
       }
        
       public boolean removeSmiley(String name) {
           String toremove = "";
           
        for(String aa : smileys.split("%%")) {
            if (!aa.isEmpty()) {
                Usersmiley ss = Server.get().getUsersmiley(aa);
                Smiley sss = Server.get().getSmiley(String.valueOf(ss.getSMID()));
                if (sss.getName2().equals(name) && toremove.isEmpty()) {
                    toremove = String.valueOf(ss.getID());
                }
            }
        }
        if (!toremove.isEmpty()) {
        smileys = smileys.replace(toremove+"%%","");
       Server.get().removeUsersmiley(toremove);
       saveStats();
       Server.get().query("delete from `sm_usersmileys` where id='"+toremove+"'"); 
     return true;
        }
        
        return false;
       }
            
        public void setSmileys(String value) {
            smileys = value;
            saveStats();
        }
        public void setSmileys2(String value) {
            smileys2 = value;
            saveStats();
        }
        public String getCodes() {
            return codes;
        }
        public String getSmileys() {
            return smileys;
        }
        public String getSmileys2() {
            return smileys2;
        }
      
        
	
	public void setLogin(long login) {
		this.login = login;
	}
public void setLoginCookie(String a) {
            logincookie = a;
            saveStats();
        }
        public String getLoginCookie() {
            return logincookie;
        }
	public void setName(String name) {
		this.name = name;
	}

	public int getLoginCategory() {
		return loginCategory;
	}
        

	public void setLoginCategory(byte loginCategory) {
		this.loginCategory = loginCategory;
	}

	private int scrollspeed, cmTestQuestion, cmTestWrong;

	public int getCmTestQuestion() {
		return cmTestQuestion;
	}

	public void setCmTestQuestion(int cmTestQuestion) {
		this.cmTestQuestion = cmTestQuestion;
	}

	public int getCmTestWrong() {
		return cmTestWrong;
	}

	public boolean checkCl(Channel channel) {
		return channel.getCls().contains(String.format("|%s|", name));
	}

	public int getDm() {
		return dm;
	}

	public void setDm(int dm) {
		this.dm = dm;
	}

         public void setQuizPoints(float quizPoints) { 
                this.quizPoints = quizPoints;
           
        } 
         
         public void setMixPoints(float mixPoints) { 
                this.mixPoints = mixPoints;
           
        } 
         
         public void setTranslatePoints(float translatePoints) { 
                this.translatePoints = translatePoints;
           
        } 
         
          public void setMathePoints(float mathePoints) { 
                this.mathePoints = mathePoints;
           
        } 
         
         public void increaseMixPoints(float mixPoints) {
                this.mixPoints += mixPoints; 
      
         }
         
          public void increaseQuizPoints(float quizPoints) {
                this.quizPoints += quizPoints; 
      
         }
         
         public void increaseTranslatePoints(float translatePoints) {
                this.translatePoints += translatePoints; 
      
         }
         
         public void increaseMathePoints(float mathePoints) {
                this.mathePoints += mathePoints; 
      
         }
         
         public float getQuizPoints() { 
               return quizPoints; }
         
         public float getMixPoints() { 
               return mixPoints; }

          public float getTranslatePoints() { 
               return translatePoints; }  

           public float getMathePoints() { 
               return mathePoints; }
        
	public int getElement() {
		return element;
	}
         public int getDonate() {
            return donate;
        }
        public void setDonate(int a) {
            donate = a;
        }
         public int getForumpostcounter() {
            return forum_post_counter;
        }
        public void setForumpostcounter(int a) {
            forum_post_counter = a;
        }

	public void setElement(int element) {
		this.element = element;
	}
        public int getElementrechner() {
		return elementrechner;
	}

	public void setElementrechner(int element) {
		this.elementrechner = element;
	}
        
      
        public void setKonfetti(boolean set) {
            konfetti = set;
        }

        public boolean getKonfetti() {
            return konfetti;
        }
        
        private boolean discolight;
        
        public void setDiscolight(boolean set) {
            discolight = set;
        }

        public boolean getDiscolight() {
            return discolight;
        }
        
        private boolean montgolfier;
        
        public void setMontgolfier(boolean set) {
            montgolfier = set;
        }

        public boolean getMontgolfier() {
            return montgolfier;
        }
        
        private boolean fireflies;
        
        public void setFireflies(boolean set) {
            fireflies = set;
        }

        public boolean getFireflies() {
            return fireflies;
        } 

        private boolean snowball;
        
        public void setSnowball(boolean set) {
            snowball = set;
        }

        public boolean getSnowball() {
            return snowball;
        } 
        
	public void setCmTestWrong(int cmTestWrong) {
		this.cmTestWrong = cmTestWrong;
	}

	public int getScrollspeed() {
		return scrollspeed;
	}
	
	 public void CheckNewTut(String cmd, Channel channel, int aktututs)
	 /*      */   {
	 /*  714 */     String aktuheader = "";
	 /*  715 */     String aktufunktion = "";
	 /*  716 */     PoolConnection pcon98 = ConnectionPool.getConnection(); PreparedStatement ps98 = null;
	 /*      */     try { Connection con98 = pcon98.connect();
	 /*  717 */       ps98 = con98.prepareStatement(new StringBuilder().append("SELECT * FROM tutorials where tutid = '").append(aktututs).append("'").toString());
	 /*  718 */       for (ResultSet rs98 = ps98.executeQuery(); rs98.next(); aktuheader = rs98.getString("titel")) aktufunktion = rs98.getString("funktion");  } catch (SQLException e) {
	 /*  718 */       e.printStackTrace(); } finally { if (ps98 != null) try { ps98.close(); } catch (SQLException e) {
	 /*      */         } pcon98.close(); }
	 /*  719 */     String nextheader = "";
	 /*  721 */     PoolConnection pcon97 = ConnectionPool.getConnection(); PreparedStatement ps97 = null;
	 /*      */     try { Connection con97 = pcon97.connect();
	 /*  722 */       ps97 = con97.prepareStatement(new StringBuilder().append("SELECT * FROM tutorials where tutid = '").append(aktututs + 1).append("'").toString());
	 /*  723 */       for (ResultSet rs97 = ps97.executeQuery(); rs97.next(); nextheader = rs97.getString("titel"));  } catch (SQLException e) {
	 /*  723 */       e.printStackTrace(); } finally { if (ps97 != null) try { ps97.close(); } catch (SQLException e) {
	 /*      */         } pcon97.close(); }
	 /*  724 */     int use = 0;
	 /*      */ 
	 /*  726 */     if (!aktufunktion.isEmpty()) {
	 /*  727 */       String[] lol = aktufunktion.split(";");
	 /*      */ 
	 /*  729 */       for (String item : lol) {
	 /*  730 */         String command = KCodeParser.escape(item.substring(1).split(" ")[0]);
	 /*  731 */         if (item.equals(cmd)) {
	 /*  732 */           use = 1;
	 /*      */         }
	 /*  734 */         if (command.equals(item)) {
	 /*  735 */           use = 1;
	 /*      */         }
	 /*      */       }
	 /*      */     }
	 /*      */ 
	 /*  740 */     if ((use == 1) && (!aktufunktion.isEmpty()) && (this.tutopen == 0) && (this.tutAktiv == 1)) {
	 /*  741 */       sendButlerMessage(channel.getName(), String.format(new StringBuilder().append("Du hast soeben erfolgreich die TuT-Lektion ''").append(aktuheader).append("'' abgeschlossen. Als nächstes erwartet dich die Lektion ''").append(nextheader).append("''.").toString(), new Object[0]));
	 /*  742 */       this.tutopen = 1;
	 /*  743 */       this.aktuTut += 1;
	 /*      */     }
	 /*      */ 
	 /*  747 */     if ((aktufunktion.isEmpty()) && (!nextheader.isEmpty()) && (this.tutopen == 0) && (this.tutAktiv == 1)) {
	 /*  748 */       this.tutopen = 1;
	 /*  749 */       this.aktuTut += 1;
	 /*      */     }
	 /*      */   }

	public void setScrollspeed(int scrollspeed) {
		this.scrollspeed = scrollspeed;
	}

	private String kdiice_dice, starsFrom, starsTo;

	
        public int getBazookasperre() {
		return bazookasperre;
	}

	public boolean hasPermission(String permission) {
		if (Server.permissions.containsKey(permission)) {
			String keys = Server.permissions.get(permission);

			for (String key : keys.split("\\|")) {
				if (!key.isEmpty()) {
					if (key.equals(String.format("N%s", name))) {
						return true;
					} else if (key.startsWith("S")) {
						int smiley = Integer.parseInt(key.substring(1));

						if (smileys.contains(String.format("|%s~", smiley))) {
							return true;
						}
					} else if (key.startsWith("TM")) {
						String team = key.substring(2);

						if (checkTeam(team)) {
							return true;
						}
					} else if (key.startsWith("TL")) {
						String team = key.substring(2);

						if (checkTeamLeader(team)) {
							return true;
						}
					} else if (key.startsWith("R")) {
						int status = Integer.parseInt(key.substring(1));

						if (rank >= status) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

        
        
        public void setBazookasperre(byte bazookasperre) {
		this.bazookasperre = bazookasperre;
	}
        
       
	public int getButterflysperre() {
		return butterflysperre;
	}

	public void setButterflysperre(byte butterflysperre) {
		this.butterflysperre = butterflysperre;
	}

	public String getStarsFrom() {
		return starsFrom;
	}

	public int getAufrufe() {
		return aufrufe;
	}

	public int getEmailVerify() {
		return emailVerify;
	}

	public void setEmailVerify(byte emailVerify) {
		this.emailVerify = emailVerify;
	}

	public void increaseAufrufe() {
		aufrufe++;
	}

	public int getBelohnen() {
		return belohnen;
	}

	public void setBelohnen(byte belohnen) {
		this.belohnen = belohnen;
	}

	

	public int getWeckMessage() {
		return weckMessage;
	}
            public String timeStampToTime(long string) {
		Date da = new Date(string * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("HH:mm:ss");
		String zeit = uhrzeits.format(da);

		return zeit;
	}

	       
    public String timeStampToDate(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy");
		String zeit = uhrzeits.format(da);

		return zeit;
	}
    

	public void setWeckMessage(byte weckMessage) {
		this.weckMessage = weckMessage;
	}
        
        public int getFlyingbedsperre() {
		return flyingbedsperre;
	}

	public void setRosensperre(String string) {
		this.rosesSend = Byte.parseByte(string);
	}
        
        public void setRosensperre(byte rosenSperre) {
		this.rosesSend = rosenSperre;
	}

	public String getRosensperre() {
		if (rosesSend > 0) {
			return "1";
		}

		return "0";
	}

        public void setFlyingbedsperre(byte flyingbedsperre) {
		this.flyingbedsperre = flyingbedsperre;
	}
	
    
        
        public int getWinksperre() {
		return winksperre;
	}
        
	public byte getAuthActive() {
		return authActive;
	}

	public void setAuthActive(byte authActive) {
		this.authActive = authActive;
	}
        
        public void setWinksperre(byte winksperre) {
		this.winksperre = winksperre;
	}

	public void setStarsFrom(String starsFrom) {
		this.starsFrom = starsFrom;
	}
           public int getPhotoVerifyLast() {
            return photo_verify_last;
        }

        public void setPhotoVerifyLast(int a) {
            photo_verify_last = a;
        }

	public int getDmWin() {
		return dmWin;
	}

	public void setDmWin(int dmWin) {
		this.dmWin = dmWin;
	}

	public int getKissall() {
		return kissall;
	}

	public int getChildJuSchuMessage() {
		return childJuSchuMessage;
	}

	public void setChildJuSchuMessage(byte childJuSchuMessage) {
		this.childJuSchuMessage = childJuSchuMessage;
	}

	public void setKissall(byte kissall) {
		this.kissall = kissall;
	}

	public int getHeartMessage() {
		return heartMessage;
	}
        
        public int getHealsperre() {
		return healsperre;
	}

	public void setHealsperre(byte healsperre) {
		this.healsperre = healsperre;
	}

	public void setHeartMessage(byte heartMessage) {
		this.heartMessage = heartMessage;
	}

	public int getHighOrLowRound() {
		return holrunde;
	}

	

	public int getHighOrLowNumber() {
		return highOrLowNumber;
	}

	public void setHighOrLowNumber(byte highOrLowNumber) {
		this.highOrLowNumber = highOrLowNumber;
	}

	public String getHost() {
		return host;
	}

	public void setHighOrLowRound(byte highOrLowRound) {
		this.holrunde = highOrLowRound;
	}

	public void increaseHighOrLowRound() {
		holrunde++;
	}

	public String getZusammen() {
		return zusammen;
	}
        
        public void setZusammen(String zusammen) {
		this.zusammen = zusammen;
	}
        
        public String getHeroToday() {
		return heroToday;
	}

	public void setHeroToday(String heroToday) {
		this.heroToday = heroToday;
	}
        
        public String getSeeFriends() {
		return seeFriends;
	}

         public void setDevilsbomb(int a) {
     devilsbomb = a;
 }
 public int getDevilsbomb() {
     
     if (bomb.bombe.contains("~"+name+"|")) {
         setDevilsbomb(1);
         devilsbomb = 1;
     }
     
     return devilsbomb;
 }
	public void setSeeFriends(String seeFriends) {
		this.seeFriends = seeFriends;
	}
        
        public String getTurnedHeadTo() {
		return turnedHeadTo;
	}

	public void setTurnedHeadTo(String turnedHeadTo) {
		this.turnedHeadTo = turnedHeadTo;
	}
        
         public String getTurnedHeadFrom() {
		return turnedHeadFrom;
	}

	public void setTurnedHeadFrom(String turnedHeadFrom) {
		this.turnedHeadFrom = turnedHeadFrom;
	}
        
        public String getLoveyou() {
                return loveyou;
        }
         public void setLoveyou(String loveyou) {
                this.loveyou = loveyou;
        }
         
         public String getNeveralone() {
                return neveralone;
        }
         public void setNeveralone(String neveralone) {
                this.neveralone = neveralone;
        }
        
        public String getAdoreTo() {
		return adoreTo;
	}

	public void setAdoreTo(String adoreTo) {
		this.adoreTo = adoreTo;
	}

         public String getAdoreFrom() {
		return adoreFrom;
	}

	public void setAdoreFrom(String adoreFrom) {
		this.adoreFrom = adoreFrom;
	}
        
	public String getStarsTo() {
		return starsTo;
	}

	public void setStarsTo(String starsTo) {
		this.starsTo = starsTo;
	}

	public String getDiice() {
		return kdice_dice;
	}

	public String getAgent() {
		return agent;
	}

	public long getWahlsperre() {
		return wahlsperre;
	}

	public void setWahlsperre(long wahlsperre) {
		this.wahlsperre = wahlsperre;
	}

	public boolean checkCode(int smiley) {
		return smileys.contains(String.format("|%s~", smiley));
	}

     // Chiller's  Smiley +1 & +5 Script
        
        public int SummCode(int smiley,int smiley2) {
            int zahl = 1;
           if (rank >= 7) {
               zahl = 99999;
            }
          
           for(String id : smileys.split("\\|")) {
               if (!id.isEmpty()){
               String[] bla = id.split("~");
               if (bla[0].equals(""+smiley+"")) {
                   zahl = zahl+1;
               } else  if (bla[0].equals(""+smiley2+"")) {
                   zahl = zahl+5;
               }
           }}
           

                return zahl;
	}
        
        //  Ende
        
	public int getPersofalsch() {
		return persofalsch;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public void increasePersofalsch() {
		persofalsch++;
	}
	
	public void setWordMixPoints(float wordMixPoints) {
		this.wordmixPoints = wordMixPoints;
	}
        
        public float getBlitzPoints() {
              return _blitzPoints;
        }
        
        public float getQuessPoints() {
              return _quessPoints;
        }
        
         public float getFiftyPoints() {
              return _fiftyPoints;
        }

        public void setBlitzPoints(float pBlitzpoints) {
             _blitzPoints = pBlitzpoints;
        }
        
         public void setQuessPoints(float pQuesspoints) {
             _quessPoints = pQuesspoints;
        }
         
         public void setFiftyPoints(float pFiftypoints) {
             _fiftyPoints = pFiftypoints;
        }

        public void increaseBlitzPoints(float pBlitzpoints) {
              _blitzPoints += pBlitzpoints;
        }
        
        public void increaseFiftyPoints(float pFiftypoints) {
              _fiftyPoints += pFiftypoints;
        }
        
        public void increaseQuessPoints(float pQuesspoints) {
              _quessPoints += pQuesspoints;
        }

        public boolean getBlitzCan() {
                return _blitzCan;
        }

        public void setBlitzCan(boolean pCan) {
                  _blitzCan = pCan;
        }
        
        public void setHeroCounter(float heroCounter) {
		this.heroCounter = heroCounter;
        }
        
        public void setLuckyCounter(int luckyCounter) {
		this.luckyCounter = luckyCounter;
        }
        
        public void setUserLogin(float userLogin) {
		this.userLogin = userLogin;
        }
         
        public int getBlitzDice() {
              return _blitzDice;
        }

    public void setBlitzDice(int pBlitzDice) {
        _blitzDice = pBlitzDice;
        if (pBlitzDice != 0 && !_blitzDiceHistory.contains(pBlitzDice)) {
            _blitzDiceHistory.add(pBlitzDice);
        }
    }

    public boolean hasBlitzDice(int pBlitzDice) {
        return _blitzDiceHistory.contains(pBlitzDice);
    }

    public List<Integer> getBlitzHistory() {
        return _blitzDiceHistory;
    }

    public void resetBlitzHistory() {
        _blitzDiceHistory.clear();
    } 
         
         
         
	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;

		PoolConnection pcon = ConnectionPool.getConnection();
		PreparedStatement ps = null;

		try {
			Connection con = pcon.connect();
			ps = con.prepareStatement("update accounts set heart = ? where name = ?");
			ps.setString(1, heart);
			ps.setString(2, name);
			ps.execute();
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
               
             
                
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPfLeben() {
		return pfLeben;
	}

	public int getLcmonths() {
		return lcmonths;
	}
        
        public int getTinkle() {
         return tinkle;
         }
          public void setTinkleLast(String anshdbifjhgifusgfszu) {
             tinklelast = anshdbifjhgifusgfszu;

         }

            public String getTinkleLast() {

             if (!tinklelast.isEmpty() && Long.parseLong(tinklelast) < System.currentTimeMillis()/1000) {

            tinklelast = "";

            setTinkleLast("");

             } 

             return tinklelast;

        
             }
        
            public String getPartnerlookTimeout() {
                return partnerlookTimeout;
            }
            public void setPartnerlookTimeout(String a) {
                partnerlookTimeout = a;
            }
            
            
              public String getLovepotionTimeout() {
                return lovepotionTimeout;
            }
            public void setLovepotionTimeout(String a) {
                lovepotionTimeout = a;
            }
            public String getAmorsarrowNick() {
                return amorsarrowNick;
            }
            public void setAmorsarrowNick(String a) {
                amorsarrowNick = a;
            }
            
            public String getAmorsarrowAkzeptTo() {
                   if (!amorsarrowTo.isEmpty() && Long.parseLong(getAmorsarrowTimeout()) < (System.currentTimeMillis()/1000)) {
           amorsarrowTo = "";              
            setAmorsarrowTimeout("");
             } 
                                      
                return amorsarrowAkzeptTo;
            }
            public String getAmorsarrowAkzeptFrom() {
                if (!amorsarrowFrom.isEmpty() && Long.parseLong(getAmorsarrowTimeout()) < (System.currentTimeMillis()/1000)) {
           amorsarrowFrom = "";              
            setAmorsarrowTimeout("");
             } 
                
                return amorsarrowAkzeptFrom;
            }
             public String getPartnerlookFrom() {

             if (!partnerlookFrom.isEmpty() && Long.parseLong(getPartnerlookTimeout()) < (System.currentTimeMillis()/1000)) {
            partnerlookFrom = "";              
            setPartnerlookTimeout("");
             } 
             return partnerlookFrom;        
             }

          public String getPartnerlookTo() {

             
             if (!partnerlookTo.isEmpty()  && Long.parseLong(getPartnerlookTimeout()) < (System.currentTimeMillis()/1000)) {
            partnerlookTo = "";          
            setPartnerlookTimeout("");
             } 
             return partnerlookTo;        
         }
          
          
          
           public String getLovepotionFrom() {

             if (!lovepotionFrom.isEmpty() && Long.parseLong(getLovepotionTimeout()) < (System.currentTimeMillis()/1000)) {
            lovepotionFrom = "";              
            setLovepotionTimeout("");
             } 
             return lovepotionFrom;        
             }

          public String getLovepotionTo() {

             
             if (!lovepotionTo.isEmpty()  && Long.parseLong(getLovepotionTimeout()) < (System.currentTimeMillis()/1000)) {
            lovepotionTo = "";          
            setLovepotionTimeout("");
             } 
             return lovepotionTo;        
         }
          
          
          
          
          public String getAmorsarrowTimeout() {
                return amorsarrowTimeout;
            }
            public void setAmorsarrowTimeout(String a) {
                amorsarrowTimeout = a;
            }
             public String getAmorsarrowFrom() {

             if (!amorsarrowFrom.isEmpty() && Long.parseLong(getAmorsarrowTimeout()) < (System.currentTimeMillis()/1000)) {
            amorsarrowFrom = "";
                System.out.println("delete from");
            setAmorsarrowTimeout("");
             } 
             return amorsarrowFrom;        
             }

          public String getAmorsarrowTo() {

             
             if (!amorsarrowTo.isEmpty()  && Long.parseLong(getAmorsarrowTimeout()) < (System.currentTimeMillis()/1000)) {
            amorsarrowTo = "";
            System.out.println("delete to");
            setAmorsarrowTimeout("");
             } 
             return amorsarrowTo;        
         }
          
          
          
          
          
          
          
          
         public void setTinkle(int tinkle) {
         this.tinkle =tinkle;
         }
         
         
         
         public void setPartnerlookFrom(String partnerlookFrom) {
         this.partnerlookFrom =partnerlookFrom;
         }
         
         public void setPartnerlookTo(String partnerlookTo) {
         this.partnerlookTo =partnerlookTo;
         }
         
          public void setLovepotionFrom(String lovepotionFrom) {
         this.lovepotionFrom =lovepotionFrom;
         }
         
         public void setLovepotionTo(String lovepotionTo) {
         this.lovepotionTo =lovepotionTo;
         }
         
         
         public void setAmorsarrowFrom(String amorsarrowFrom) {
         this.amorsarrowFrom =amorsarrowFrom;
         }
         
         public void setAmorsarrowTo(String amorsarrowTo) {
         this.amorsarrowTo =amorsarrowTo;
         }
         
         public void setAmorsarrowAkzeptFrom(String amorsarrowAkzeptFrom) {
         this.amorsarrowAkzeptFrom =amorsarrowAkzeptFrom;
         }
         
         public void setAmorsarrowAkzeptTo(String amorsarrowAkzeptTo) {
         this.amorsarrowAkzeptTo =amorsarrowAkzeptTo;
         }
         
	public void setLcmonths(byte lcmonths) {
		this.lcmonths = lcmonths;
	}

	public void setPfLeben(byte pfLeben) {
		this.pfLeben = pfLeben;
	}

	public int getKontoKnuddels() {
		return kontoKnuddels;
	}

        public float getKnuddels() {
		return knuddels;
	}
        
	public Channel getMyChannel() {
		for (Channel channel : Server.get().getChannels()) {
			if (channel.getOwner().equals(name)) {
				return channel;
			}
		}

		return null;
	}

	public void setKontoKnuddels(int kontoKnuddels) {
		this.kontoKnuddels = kontoKnuddels;
	}
        
        public void setKnuddels(int knuddels) {
		this.knuddels = knuddels;
	}
        
         public void setKisses(int kisses) {
		this.kisses = kisses;
	}
         
          public void setMentorPoints(int mentorPoints) {
		this.mentorPoints = mentorPoints;
                 setMentorSmileys();
	}
        
        public void setonlineTime(int onlinetime) {
		this.onlineTime = onlinetime;
	}

	public long getPfGeworfen() {
		return pfGeworfen;
	}

	public void setPfGeworfen(long pfGeworfen) {
		this.pfGeworfen = pfGeworfen;
	}

	public String getKontoPassword() {
		return kontoPassword;
	}

	public long getBotkontrolleZeit() {
		return botkontrolleZeit;
	}
        
        
	public void setBotkontrolleZeit(long botkontrolleZeit) {
		this.botkontrolleZeit = botkontrolleZeit;
	}
        
       

	public String getBotkontrolleAn() {
		return botkontrolleAn;
	}
        
       

	public void setBotkontrolleAn(String botkontrolleAn) {
		this.botkontrolleAn = botkontrolleAn;
	}
        
       

	public String getBotkontrolleVon() {
		return botkontrolleVon;
	}

	public void setBotkontrolleVon(String botkontrolleVon) {
		this.botkontrolleVon = botkontrolleVon;
	}
        public String getRosenick() {
            return rosenick;
        }
        public void setRosenick(String a) {
            rosenick = a;
        }
        
        public String getNickTag() {
            return nicktag;
        }
        public void setNickTag(String a) {
            nicktag = a;
        }
        public void setClink(String text) {
            clink = text;
        }
        
        public String getClink() {
            return clink;
        }

	public void setMissed(String missed) {
		this.missed = missed;
	}

	public String getMissed() {
		return missed;
	}

	public byte getAktuTut() {
		return aktuTut;
	}

	public void setAktuTut(byte aktuTut) {
		this.aktuTut = aktuTut;
	}

	public void setKontoPassword(String kontoPassword) {
		this.kontoPassword = kontoPassword;
	}

	public int getVerify() {
		return verify;
	}
    

	public byte getTutOpen() {
		return tutOpen;
	}

	public void setTutOpen(byte tutOpen) {
		this.tutOpen = tutOpen;
	}

	public void setVerify(byte verify) {
		this.verify = verify;
	}


	public String getMentor() {
		return mentor;
	}
        

	public long getSperre() {
		return sperre;
	}
        
        public long getDisable() {
		return disable;
	}
        
        public int getWStyle() {
		return wStyle;
	}
        
        public long getGmute() {
		return gmute;
	}
        
        public long getDeletenick() {
		return deletenick;
	}

	public int getKnuddelscentLevel() {
		return knuddelscentLevel;
	}

	public void setKnuddelscentLevel(byte knuddelscentLevel) {
		this.knuddelscentLevel = knuddelscentLevel;
	}

	public int getKnuddelscentLock() {
		return knuddelscentLock;
	}

	public void setKnuddelscentLock(byte knuddelscentLock) {
		this.knuddelscentLock = knuddelscentLock;
	}

	public void setSperre(long sperre) {
		this.sperre = sperre;
	}

        public void setDisable(long disable) {
		this.disable = disable;
	}
        
         public void setWStyle(int wStyle) {
		this.wStyle = wStyle;
	}
        
        public void setGmute(long gmute) {
		this.gmute = gmute;
	}
        
        public void setDeletenick(long deletenick) {
		this.deletenick = deletenick;
	}
        
        
	public String getSperreinfo() {
		return sperreinfo;
	}
        
        public String getDisableinfo() {
		return disableinfo;
	}

	public void setSperreinfo(String sperreinfo) {
		this.sperreinfo = sperreinfo;
	}
        
        public void setDisableinfo(String disableinfo) {
		this.disableinfo = disableinfo;
	}

	public String getSperrevon() {
		return sperrevon;
	}

	public void setSperrevon(String sperrevon) {
		this.sperrevon = sperrevon;
	}
        
         public String getMutevon() {
		return mutevon;
	}

	public void setMutevon(String mutevon) {
		this.mutevon = mutevon;
	}
        
        public String getChannellockvon() {
		return channellockvon;
	}

	public void setChannellockvon(String channellockvon) {
		this.channellockvon = channellockvon;
	}
        
	public void setMentor(String mentor) {
		this.mentor = mentor;
	}
        
	public int getMpChat() {
		return mpChat;
	}

	public byte getMessageSound() {
		return messageSound;
	}

	public void setMessageSound(byte messageSound) {
		this.messageSound = messageSound;
	}

	public void setMpChat(byte mpChat) {
		this.mpChat = mpChat;
	}
        public String getFriendask() {
            
           return friendask;
        }
        public void setFriendask(String a) {
            friendask = a;
        }
	public int getMpFriendlist() {
		return mpFriendlist;
	}

	public void setMpFriendlist(byte mpFriendlist) {
		this.mpFriendlist = mpFriendlist;
	}

	public byte getTutAktiv() {
		return tutAktiv;
	}

	public void setTutAktiv(byte tutAktiv) {
		this.tutAktiv = tutAktiv;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getHoldHands() {
		return holdhands;
	}

	public void setHoldHands(String holdhands) {
		this.holdhands = holdhands;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getDmStartKnuddels() {
		return dmStartKnuddels;
	}

	public void setDmStartKnuddels(int dmStartKnuddels) {
		this.dmStartKnuddels = dmStartKnuddels;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getPlzLand() {
		return plzLand;
	}

	public void setPlzLand(String plzLand) {
		this.plzLand = plzLand;
	}

	public String getComments() {
		return comments;
	}
        
        public String getSyscomments() {
		return syscomments;
	}

	public int getThiefGame() {
		return thiefGame;
	}

	public void setThiefGame(int thiefGame) {
		this.thiefGame = thiefGame;
	}
	
	public String getDeny() {
		return deny;
	}

	public int getSnp() {
		return snp;
	}

	public void setSnp(byte snp) {
		this.snp = snp;
	}

	public void setDeny(String deny) {
		this.deny = deny;
	}

	public int getSun() {
		return sun;
	}

	public void setSun(byte sun) {
		this.sun = sun;
	}
        
        public int getSunLock() {
		return sunLock;
	}

	public int getNewPhotoComment() {
		return newPhotoComment;
	}

	public void setNewPhotoComment(byte newPhotoComment) {
		this.newPhotoComment = newPhotoComment;
	}
        
        public void setSunLock(byte sunLock) {
		this.sunLock = sunLock;
	}

	public int getAgePlus() {
		return agePlus;
	}

	public void setAgePlus(byte agePlus) {
		this.agePlus = agePlus;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

        public void setSyscomments(String syscomments) {
		this.syscomments = syscomments;
	}
        
	public String getCmcomments() {
		return cmcomments;
	}

	public void setCmcomments(String cmcomments) {
		this.cmcomments = cmcomments;
	}

	public String getFriendlist() {
		return friendlist;
	}

	public long getNotrufsperre() {
		return notrufsperre;
	}

	public void setNotrufsperre(long notrufsperre) {
		this.notrufsperre = notrufsperre;
	}
        
        public int getAnalysedatasperre() {
		return analysedatasperre;
	}

	public void setAnalysedatasperre(byte analysedatasperre) {
		this.analysedatasperre = analysedatasperre;
	}

	public void setFriendlist(String friendlist) {
		this.friendlist = friendlist;
	}

	public String getTeams() {
		return teams;
	}

	public String getNewChannel() {
		return newChannel;
	}

	public void setNewChannel(String newChannel) {
		this.newChannel = newChannel;
	}

	public void setCmComment(String von, String text, String comment) {
		Client target = Server.get().getClient(getName());
		comment = comment.replace("'", "\'");
		boolean online = true;
		String uhrzeit = String
				.format("%s %s",
						Server.get().timeStampToDate(
								System.currentTimeMillis() / 1000),
						Server.get().timeStampToTime(
								System.currentTimeMillis() / 1000));

		if (target == null) {
			online = false;
			target = new Client(null);
			target.loadStats(getName());
		}

		if (online) {
			target.setCmcomments(String.format("%s|%s°s9,°%s°s9,°%s|", von,
					target.getCmcomments(),
					text != null ? String.format("\"_%s_\" %s", text, comment)
							: comment, uhrzeit));
		} else {
			Server.get()
					.query(String
							.format("UPDATE `accounts` SET `cmcomments` = '%s|%s°s9,°%s°s9,°%s|' WHERE `name` = '%s'",
									von,
									target.getCmcomments(),
									text != null ? String.format("\"_%s_\" %s",
											text, comment) : comment, uhrzeit,
									getName()));
		}
	}
        

	public void setTeams(String teams) {
		this.teams = teams;
	}

	public boolean checkFriend(String nick) {
		return getFriendlist().contains(String.format("|%s|", nick));
	}
        
        public boolean checkFriendask(String nick) {
                    return friendask.contains(String.format("|%s~", nick));
	}

	public boolean checkSchuetzling(String nick) {
		return getSchuetzlinge().contains(String.format("|%s|", nick));
	}

	public int getHeartsperre() {
		return heartsperre;
	}

	public void setHeartsperre(byte heartsperre) {
		this.heartsperre = heartsperre;
	}

	public int getReadmeEffects() {
		return readmeEffects;
	}

	public void setReadmeEffects(byte readmeEffects) {
		this.readmeEffects = readmeEffects;
	}

	public int getHolknuddelsrunde() {
		return holknuddelsrunde;
	}

	public void setHolknuddelsrunde(byte holknuddelsrunde) {
		this.holknuddelsrunde = holknuddelsrunde;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getCareer() {
		return career;
	}
        
        public String getCareer2() {
		return career2;
	}
        
        public String getHighlights() {
		return highlights;
	}

	public int getGrippeStatus() {
		return grippestatus;
	}
        public int getGrippeInfiziert() {
            return grippeinfiziert;
        }
        public void setGrippeInfiziert(int a) {
            grippeinfiziert = a;
        }
	public void setGrippeStatus(byte i) {
		this.grippestatus = i;
	}

	public void setCareer(String career) {
		this.career = career;
	}
        
        public void setCareer2(String career2) {
		this.career2 = career2;
	}
        
        public void setHighlights(String highlights) {
		this.highlights = highlights;
	}

	public String getAppletVersion() {
		return appletVersion;
	}
        
        public String getLoginUrl() {
		return loginUrl;
	}

	public void setAppletVersion(String version) {
		this.appletVersion = version;
	}
        
        public void setLoginUrl(String url) {
		this.loginUrl = url;
	}

	public int getKicks() {
		return kicks;
	}
        
        public int getLocks() {
		return locks;
        }
                
        public int getVisit() {
		return visit;
	}

	public void setKicks(int kicks) {
		this.kicks = kicks;
	}
        
        public void setLocks(int locks) {
		this.locks = locks;
	}
        
        public void setVisit(int visit) {
		this.visit = visit;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCmwhen(String cmwhen) {
		this.cmwhen = cmwhen;
	}

	public int getRoses() {
		return roses;
	}

	public void setRoses(int roses) {
		this.roses = roses;
	}

	public int getEmails() {
		return emails;
	}

	public void setEmails(byte emails) {
		this.emails = emails;
	}

	public int getSilence() {
		return silence;
	}

	public int getFaSort() {
		return faSort;
	}
        
        public int getFSort() {
		return fSort;
	}

	public void setFaSort(byte faSort) {
		this.faSort = faSort;
	}
        
        public void setFSort(byte fSort) {
		this.fSort = fSort;
	}

	public void setSilence(byte silence) {
		this.silence = silence;
	}
        
        public int getDreamsperre() {
		return dreamsperre;
	}

	public void setDreamsperre(byte dreamsperre) {
		this.dreamsperre = dreamsperre;
	}

	public int getPhoto_verify() {
		return photo_verify;
	}

	public void setPhoto_verify(byte photo_verify) {
		this.photo_verify = photo_verify;
	}

	public String getIgnoredNicks() {
		return ignoredNicks;
	}
        
        public String getVisitNicks() {
		return visitNicks;
	}

	public void setIgnoredNicks(String ignoredNicks) {
		this.ignoredNicks = ignoredNicks;
	}
        
        public void setVisitNicks(String visitNicks) {
		this.visitNicks = visitNicks;
	}

	public boolean checkIgnored(String nick) {
		return ignoredNicks.contains(String.format("|%s|", nick));
	}
        
        public boolean checkVisit(String nick) {
		return visitNicks.contains(String.format("|%s|", nick));
	}

	public void addIgnoredNick(String nick) {
		ignoredNicks = String.format("%s|%s|", ignoredNicks, nick);
	}
        
        public void addVisitNick(String nick) {
		visitNicks = String.format("%s|%s|", visitNicks, nick);
	}

	public void removeIgnoredNick(String nick) {
		ignoredNicks = ignoredNicks.replace(String.format("|%s|", nick), "");
	}
        
        public void removeVisitNick(String nick) {
		visitNicks = visitNicks.replace(String.format("|%s|", nick), "");
	}

        public int getStarlitesperre() {
		return starlitesperre;
	}

	public void setStarlitesperre(byte starlitesperre) {
		this.starlitesperre = starlitesperre;
	}
        
        

        // SPIEL DARTEN LASTCONFIG
         public int getDartenLastWurf() {
		return dartenLastWurf;
	}
    
    public void setDartenLastWurf(int dartenLastWurf) {
		this.dartenLastWurf = dartenLastWurf;
	}
    
         public int getDartenLastArt() {
		return dartenLastArt;
	}
    
    public void setDartenLastArt(int dartenLastArt) {
		this.dartenLastArt = dartenLastArt;
	}
    
         public int getDartenLastEinsatzArt() {
		return dartenLastEinsatzArt;
	}
    
    public void setDartenLastEinsatzArt(int dartenLastEinsatzArt) {
		this.dartenLastEinsatzArt = dartenLastEinsatzArt;
	}
    
         public int getDartenLastEinsatz() {
		return dartenLastEinsatz;
	}
    
    public void setDartenLastEinsatz(int dartenLastEinsatz) {
		this.dartenLastEinsatz = dartenLastEinsatz;
	}
        
         public int getDartenLastPrivat() {
		return dartenLastPrivat;
	}
    
    public void setDartenLastPrivat(int dartenLastPrivat) {
		this.dartenLastPrivat = dartenLastPrivat;
	}
    
          public String getDartenLastGegner() {
		return dartenLastGegner;
	}
    
    public void setDartenLastGegner(String dartenLastGegner) {
		this.dartenLastGegner = dartenLastGegner;
	}
    // SPIEL DARTEN
        
    // Game von Jörn
    public String getLastGame() {
        return lastGame;
    }
    
    
    //
    
     public int getDartenWurfLast() {
		return dartenWurfLast;
	}
    
    public void setDartenWurfLast(int dartenWurfLast) {
		this.dartenWurfLast = dartenWurfLast;
	}
    
        public int getDartenWurf() {
		return dartenWurf;
	}
    
    public void setDartenWurf(int dartenWurf) {
		this.dartenWurf = dartenWurf;
	}
        
    public int getDartenPrivat() {
		return dartenPrivat;
	}
    
    public void setDartenPrivat(int dartenPrivat) {
		this.dartenPrivat = dartenPrivat;
	}
    
    public int getDartenWuerfel() {
		return dartenWuerfel;
	}
    
    public void setDartenWuerfel(int dartenWuerfel) {
		this.dartenWuerfel = dartenWuerfel;
	}
    
    public int getDartenEinsatz() {
		return dartenEinsatz;
	}
    
    public void setDartenEinsatz(int dartenEinsatz) {
		this.dartenEinsatz = dartenEinsatz;
	}
    
    public int getDartenOpen() {
		return dartenOpen;
	}
    
    public void setDartenOpen(int dartenOpen) {
		this.dartenOpen = dartenOpen;
	}
    
   public String getDartenGegner() {
		return dartenGegner;
	}
    
    public void setDartenGegner(String dartenGegner) {
		this.dartenGegner = dartenGegner;
	}
    
        public int getDartenDran() {
		return dartenDran;
	}
    
    public void setDartenDran(int dartenDran) {
		this.dartenDran = dartenDran;
	}
    
        public int getDartenArt() {
		return dartenArt;
	}
    
    public void setDartenArt(int dartenArt) {
		this.dartenArt = dartenArt;
	}
    
        public int getDartenEinsatzArt() {
		return dartenEinsatzArt;
	}
    
    public void setDartenEinsatzArt(int dartenEinsatzArt) {
		this.dartenEinsatzArt = dartenEinsatzArt;
	}
        
    
         public int getFriendssperre() {
		return friendssperre;
	}

	public void setFriendssperre(byte friendssperre) {
		this.friendssperre = friendssperre;
	}
    
    
 
	public int getKissed() {
		return kissed;
	}

	public void setKissed(byte kissed) {
		this.kissed = kissed;
	}

	public int getRosesSend() {
		return rosesSend;
	}

	public void setAwayReason(String awayReason) {
		this.awayReason = awayReason;
	}

	public String getAwayReason() {
		return awayReason;
	}

	public void setLotto(int lotto) {
		this.lotto = lotto;
	}

	public String getSpitznamen() {
		return spitznamen;
	}

	public void setSpitznamen(String spitznamen) {
		this.spitznamen = spitznamen;
	}

        public int getImmunsperre() {
		return immunsperre;
	}

	public void setImmunsperre(byte immunsperre) {
		this.immunsperre = immunsperre;
	}
        
	public int getLotto() {
		return lotto;
	}


	public void setRosesSend(byte rosesSend) {
		this.rosesSend = rosesSend;
	}

	private boolean away;

        
        
        private Client kdice_user;
        private int kdice_id;
        private int kdice_count;
        private String kdice_dice;
        
        
	public Client(Socket socket) {
		channels = new ArrayList<Channel>();
		this.socket = socket;

		if (socket == null) {
			return;
		}

		try {
			out = socket.getOutputStream();
		} catch (IOException e) {
		}
	}

	public List<Pair<String, Integer>> getIcons() {
		return icons;
	}

	public  void addIcon(String icon, int size) {
		Pair<String, Integer> pair = new Pair<String, Integer>(icon, size);
		icons.add(pair);

		for (Channel channel : getChannels()) {
			for (Client target : channel.getClients()) {
				target.send(PacketCreator.addIcon(channel.getName(), name, pair));
			}
		}
	}

	public void removeIcon(String icon) {
		for (Pair<String, Integer> pair : icons) {
			if (pair.getLeft().equals(icon)) {
				icons.remove(pair);
				break;
			}
		}

		for (Channel channel : getChannels()) {
			String remove = PacketCreator.removeIcon(channel.getName(), name,
					icon);

			for (Client target : channel.getClients()) {
				target.send(remove);
			}
		}
	}

        
        /* Jörns altes Game */
        
        public String getlastGame() { String text = "";
    String text1 = "";
    String text2 = "";
    String text3 = "";

    int time1 = 0;
    int time2 = 0;
    int time3 = 0;
    PoolConnection pcon4 = ConnectionPool.getConnection();
    PreparedStatement ps4 = null;
    try { Connection con4 = pcon4.connect();
      ps4 = con4.prepareStatement(new StringBuilder().append("SELECT * FROM game_darten_log WHERE  user != '").append(this.name).append("' or  gegner != '").append(this.name).append("'").toString());
      ResultSet rs4 = ps4.executeQuery();
      while (rs4.next()) {
        text1 = new StringBuilder().append("darten|").append(rs4.getString("id")).toString();
        time1 = rs4.getInt("savetime");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (ps4 != null) try { ps4.close(); } catch (SQLException e) {  } pcon4.close();
    }
    PoolConnection pcon5 = ConnectionPool.getConnection();
    PreparedStatement ps5 = null;
    try { Connection con5 = pcon5.connect();
      ps5 = con5.prepareStatement(new StringBuilder().append("SELECT * FROM game_dicen_log WHERE user != '").append(this.name).append("' or gegner != '").append(this.name).append("'").toString());
      ResultSet rs5 = ps5.executeQuery();
      while (rs5.next()) {
        text2 = new StringBuilder().append("dicen|").append(rs5.getString("id")).toString();
        time2 = rs5.getInt("savetime");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (ps5 != null) try { ps5.close(); } catch (SQLException e) {  } pcon5.close();
    }
    PoolConnection pcon6 = ConnectionPool.getConnection();
    PreparedStatement ps6 = null;
    try { Connection con6 = pcon6.connect();
      ps6 = con6.prepareStatement(new StringBuilder().append("SELECT * FROM game_freidiffen_log WHERE user != '").append(this.name).append("' or gegner != '").append(this.name).append("'").toString());
      ResultSet rs6 = ps6.executeQuery();
      while (rs6.next()) {
        text3 = new StringBuilder().append("freidiffen|").append(rs6.getString("id")).toString();
        time3 = rs6.getInt("savetime");
      } } catch (SQLException e) { e.printStackTrace(); } finally { if (ps6 != null) try { ps6.close(); } catch (SQLException e) {  } pcon6.close();
    }
    if ((time1 > time2) && (time1 > time3)) {
      text = text1;
    }
    if ((time2 > time1) && (time2 > time3)) {
      text = text2;
    }
    if ((time3 > time1) && (time3 > time2)) {
      text = text3;
    }
    return text; }
        
        
        
        /* ENDE */
        
        
        
	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getSchuetzlinge() {
		return schuetzlinge;
	}

	public void setSchuetzlinge(String schuetzlinge) {
		this.schuetzlinge = schuetzlinge;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStammiwhen() {
		return stammiwhen;
	}

	public void setStammiwhen(String stammiwhen) {
		this.stammiwhen = stammiwhen;
	}

	public String getCmwhen() {
		return cmwhen;
	}

	public int getCmutes() {
		return cmutes;
	}

	public int getFlames() {
		return flames;
	}

	public void setFlames(int flames) {
		this.flames = flames;
	}

	public void setCmutes(int cmutes) {
		this.cmutes = cmutes;
	}

	public int getMutes() {
		return mutes;
	}

	
        
         public void addMailHistory(String to,String betreff,String message) {
                message = message.replaceAll("\\n", "#");
	    	
	    	message = message.replaceAll("\n", "#");
	    	message = message.replaceAll("\r", "#");
	    	message = message.replaceAll("\\r", "#");
              if (this != Server.get().getButler() && !to.equals(Server.get().getButler().getName())) {
    
    	long time = System.currentTimeMillis()/1000;
    	String date = timeStampToDate(time).replace(".", "-");
    	String tim = timeStampToTime(time);
        
        if (betreff.trim().isEmpty()) {
        betreff = "Kein Betreff";
        }
        FileWriter writer;
    	File file = new File(String.format("chatlogs/nick/messages/%s-%s.txt", name,date));
        try {
    	    writer = new FileWriter(file, true);
    	    writer.write(String.format("_°BB>_2%s|/serverpp %s|/w %s<° (Nachricht - %s§°BB°_ an °>_h%s|/serverpp %s|/w %s<°):°r°_ ||%s_§ °12°(%s)§#", name,name,name, betreff,to,to,to,message,tim));
    	    writer.write(System.getProperty("line.separator"));
    	    writer.flush();
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
        FileWriter writer2;
    	File file2 = new File(String.format("chatlogs/nick/messages/%s-%s.txt", to,date));
        try {
    	    writer2 = new FileWriter(file2, true);
    	    writer2.write(String.format("_°BB>_2%s|/serverpp %s|/w %s<° (Nachricht - %s§°BB°_):°r°_ ||%s_§ °12°(%s)§#",name,name,name,betreff,message,tim));
    	    writer2.write(System.getProperty("line.separator"));
    	    writer2.flush();
    	    writer2.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}}
    }
          
       
          public void addPrivatHistory(String to,String channel, String message) {
             
                message = message.replaceAll("\\n", "#");
	    
	    	message = message.replaceAll("\n", "#");
	    	message = message.replaceAll("\r", "#");
	    	message = message.replaceAll("\\r", "#");
              if (this != Server.get().getButler() && !to.equals(Server.get().getButler().getName())) {
    
    	long time = System.currentTimeMillis()/1000;
    	String date = timeStampToDate(time).replace(".", "-");
    	String tim = timeStampToTime(time);
        FileWriter writer;
    	File file = new File(String.format("chatlogs/nick/privat/%s-%s.txt", name,date));
        try {
    	    writer = new FileWriter(file, true);
    	    writer.write(String.format("_°RR>_2%s|/serverpp %s|/w %s<° (privat an °>_h%s|/serverpp +%s|/w %s<° - °>_h%s|/go +%s|/go %s<°):°r°_|| %s_§ °12°(%s)§#", name,name,name, to,to,to,channel,channel,channel,message,tim));
    	    writer.write(System.getProperty("line.separator"));
    	    writer.flush();
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
        FileWriter writer2;
    	File file2 = new File(String.format("chatlogs/nick/privat/%s-%s.txt", to,date));
        try {
    	    writer2 = new FileWriter(file2, true);
    	    writer2.write(String.format("_°RR>_2%s|/serverpp %s|/w %s<° (privat - °>_h%s|/go +%s|/go %s<°):°r°_ ||%s_§ °12°(%s)§#",name,name,name,channel,channel,channel, message,tim));
    	    writer2.write(System.getProperty("line.separator"));
    	    writer2.flush();
    	    writer2.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }}
          
          
        public void addPublicHistory(String channel, String message, boolean doppelpunkt) {
            message = message.replaceAll("\\n", "#");
	    
	    	message = message.replaceAll("\n", "#");
	    	message = message.replaceAll("\r", "#");
	    	message = message.replaceAll("\\r", "#");
            if (this != Server.get().getButler()) {
    
               long time = System.currentTimeMillis()/1000;
    	String date = timeStampToDate(time).replace(".", "-");
    	String tim = timeStampToTime(time);
    	// NICKLOG
        String doppel = (doppelpunkt) ? "1" : "0";
        FileWriter writer;
    	File file = new File(String.format("chatlogs/nick/public/%s-%s.txt", name,date));
        try {
    	    writer = new FileWriter(file, true);
    	    writer.write(String.format("_°>_h%s|/go +%s|/go %s<° - °>_h%s|/serverpp %s|/w %s<°%s_ ||%s§ °12°(%s)§#", channel,channel,channel,name,name,name,doppelpunkt ? ":" : "", message,tim));
    	    writer.write(System.getProperty("line.separator"));
    	    writer.flush();
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
        // CHANNELLOG        
        FileWriter writer2;
    	File file2 = new File(String.format("chatlogs/channel/%s-%s.txt", channel,date));
        try {
    	    writer2 = new FileWriter(file2, true);
    	    writer2.write(String.format("_°>_h%s|/serverpp %s|/w %s<°%s_ ||%s§ °12°(%s)§#",name,name,name,doppelpunkt ? ":" : "", message,tim));
    	    writer2.write(System.getProperty("line.separator"));
    	    writer2.flush();
    	    writer2.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
        // TAGESLOG
         FileWriter writer3;
    	File file3 = new File(String.format("chatlogs/%s.txt",date));
        try {
    	    writer3 = new FileWriter(file3, true);
    	    writer3.write(String.format("_°>_h%s|/go +%s|/go %s<° - °>_h%s|/serverpp %s|/w %s<°%s_ %s§ °12°(%s)§#", channel,channel,channel,name,name,name,doppelpunkt ? ":" : "", message,tim));
    	    writer3.write(System.getProperty("line.separator"));
    	    writer3.flush();
    	    writer3.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
         }
    }
        
        
        

	public long getSpielsperre() {
		return spielsperre;
	}
        
        public int getSexysperre() {
		return sexysperre;
	}
        
        public int getHerosperre() {
		return herosperre;
	}
        
        public int getHolsperre() {
		return holsperre;
	}
        
         public int getBot() {
		return bot;
	}
        
        public int getCoolsperre() {
		return coolsperre;
	}
        
	public void setSpielsperre(long spielsperre) {
		this.spielsperre = spielsperre;
	}
        
        public void setSexysperre(byte sexysperre) {
		this.sexysperre = sexysperre;
	}
        
        public void setHerosperre(byte herosperre) {
		this.herosperre = herosperre;
	}
        
        public void setHolsperre(byte holsperre) {
		this.holsperre = holsperre;
	}
        
        public void setBot(byte bot) {
		this.bot = bot;
	}
        
        public void getBot(byte bot) {
		this.bot = bot;
	}
        
        public void setCoolsperre(byte coolsperre) {
		this.coolsperre = coolsperre;
	}


	public String getPhoto() {
		return photo;
	}
        

	public void setPhoto(String photo) {
		this.photo = photo;
	}
        

	public void setMutes(int mutes) {
		this.mutes = mutes;
	}

	public int getCls() {
		return cls;
	}

	public String getQuizrang() {
		return quizrang;
	}

	public void setQuizrang(String quizrang) {
		this.quizrang = quizrang;
	}

	public void setCls(int cls) {
		this.cls = cls;
	}

	public int getJumpopunkte() {
		return jumpopunkte;
	}
	
	

        
      /*  public String getAlbumCoverImage() {
            
            // return album_cover;
        }
        public int getAlbumImageCounter() {
            // return album_image_counter;
        }*/
        
        public String getFotoAlbum() {
            
            PoolConnection pcon3 = ConnectionPool.getConnection();
    PreparedStatement ps3 = null;
    try
    {
      Connection con = pcon3.connect();
      ps3 = con.prepareStatement("SELECT datei FROM `fotoalbenpics` where von='"+name+"' and cover='1' order by albenid desc limit 1");
      ResultSet rs3 = ps3.executeQuery();
      while (rs3.next()) {
     return rs3.getString("datei");
      }
    }
    catch (SQLException e) {
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
    
       return ""; 
        }
	public void increaseJumpoPoints(float jumpoPoints) {
		this.jumpopunkte += jumpoPoints;
	}

	public void deseaseJumpoPoints(float jumpoPoints) {
		this.jumpopunkte -= jumpoPoints;
	}
	
	public void setHol(int hol) {
		this.hol = hol;
	}

	public int getSearchAgeFrom() {
		return searchAgeFrom;
	}

	public int getLCSperre() {
		return lcsperre;
	}

	public int getZeichen() {
		return zeichen;
	}

	public void setZeichen(int zeichen) {         
		this.zeichen = zeichen;
	}

	public String getRegisterIP() {
		return registerIP;
	}

	public int getNewssperre() {
		return newssperre;
	}

	public void setNewssperre(byte newssperre) {
		this.newssperre = newssperre;
	}

	
	public String getLastChannel() {
		return lastChannel;
	}

	public void setLastChannel(String lastChannel) {
		this.lastChannel = lastChannel;
	}

	public void setShowEffects(byte showEffects) {
		this.showEffects = showEffects;
	}

	public void setLCSperre(byte lcsperre) {
		this.lcsperre = lcsperre;
	}

	public String getLC() {
		return lc;
	}

	public void setLC(String lc) {
		this.lc = lc;
	}

	public String getVergeben() {
		return vergeben;
	}

	public String getDream() {
		return dream;
	}

	public void setDream(String dream) {
		this.dream = dream;
	}

	public int getAdmincallFirst() {
		return admincallFirst;
	}

	public void increaseAdmincallFirst() {
		this.admincallFirst += 1;
	}

	public int getAdmincallSecond() {
		return admincallSecond;
	}

	public void increaseAdmincallSecond() {
		this.admincallSecond += 1;
	}
      

        public void setJoinSound(String file) {
            this.joinSound = file;
        }
        
        public String getJoinSound() {
            return this.joinSound;
        }
        
         public void setLeftSound(String file) {
            this.leftSound = file;
        }
        
        public String getLeftSound() {
            return this.leftSound;
        }     
        
	public int getAdmincallThird() {
		return admincallThird;
	}

	public void increaseAdmincallThird() {
		this.admincallThird += 1;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public void setVergeben(String vergeben) {
		this.vergeben = vergeben;
	}

	public int getSearchAgeUntil() {
		return searchAgeUntil;
	}

	public String getSearchGender() {
		return searchGender;
	}

	public String getSearchMotiv() {
		return searchMotiv;
	}

	public String getSearchEntfernung() {
		return searchEntfernung;
	}

	public void setSearchEntfernung(String searchEntfernung) {
		this.searchEntfernung = searchEntfernung;
	}
	
	public int getStammiMonths() {
		if(!stammiwhen.isEmpty()) {
			return CommandParser.countChars(stammiwhen, '|')+1;
		}
		
		return 0;
	}

	public void setSearchAgeFrom(byte searchAgeFrom) {
		this.searchAgeFrom = searchAgeFrom;
	}

	

    public int getMoskitoAbgewehrt() {
      return moskitoabgewehrt;
    }

  

    public int getMoskitoGestochen() {
      return moskitogestochen;
    }
   
     public void setMoskitoAbgewehrt(int i) {
       moskitoabgewehrt = i;
    }

    public void setMoskitoGestochen(int i) {
      moskitogestochen = i;
    }
	

	public int getHol() {
		return hol;
	}

	public void increaseHol(int hol) {
		this.hol += hol;
	}

	

	

	public void setSearchAgeUntil(byte searchAgeUntil) {
		this.searchAgeUntil = searchAgeUntil;
	}

	public void setSearchMotiv(String searchMotiv) {
		this.searchMotiv = searchMotiv;
	}

	public void setSearchGender(String searchGender) {
		this.searchGender = searchGender;
	}

	public String getName() {
		return name;
	}
        
        public void setHallOfFameMessage(int a) {
            hallOfFameMessage = a;
        }
        public int getHallOfFameMessage() {
            return hallOfFameMessage;
        }
        
        
   public int getBad6Points() {
            return bad6points;
        }
        public int getBad6Gameid() {
            return bad6gameid;
        }
        public int getBad6Temp() {
            return bad6temp;
        }
        public String getBad6NachwurfPlayer() {
            return bad6nachwurfplayer;
        }



 public String getBad6User() {
            return bad6user;
        }
        public String getBad6Gegner() {
            return bad6gegner;
        }
        public String getBad6Ask() {
            return bad6ask;
        }
        public Long getBad6AskStart() {
            return bad6askstart;
        }
       
        public String getBad6Dran() {
            return bad6dran;
            
        }   


 public int getBad6OK() {
            
            return bad6ok;
        }
        public void setBad6Gameid(int id) {
            bad6gameid = id;
        }
        public void setBad6NachwurfPlayer(String nick) {
            bad6nachwurfplayer = nick;
        }
        public void setBad6OK(int ok) {
            
            bad6ok = ok;
        }
        public void setBad6Points(int z) {
         bad6points = z;  
        }
        public void setBad6Temp(int z) {
            bad6temp = z;
        }
        public void setBad6Dran(String nick) {
            bad6dran = nick;
        }
        public void setBad6User(String nick) {
           bad6user = nick;
        }
        public void setBad6Gegner(String nick) {
            bad6gegner = nick;
        }
       public void setBad6Ask(String nick) {
           bad6ask = nick;
       }
       public void setBad6AskStart(Long time) {
           bad6askstart = time;
       }
       public int getBad6() {
           return bad6;
       }
      
       public void setBad6(int points) {
           bad6 = points;
       }
       
        public String getLoveFrom() {
            return lovefrom;
        }
        public String getLoveTo() {
            return loveto;
        }
        public int getLoveWait() {
            return lovewait;
        }
        public void setLoveFrom(String a) {
            lovefrom = a;
        }
        public void setLoveTo(String a) {
            loveto = a;
        }
        public void setLoveWait(int a) {
            lovewait = a;
        }
        public String getPartnerlook() {
            return partnerlook;
        }
        public void setPartnerlook(String a) {
            partnerlook = a;
        }
        
        
        public String getLovepotion() {
            return lovepotion;
        }
        public void setLovepotion(String a) {
            lovepotion = a;
        }
        
        
         public String getAmorsarrow() {
            return amorsarrow;
        }
        public void setAmorsarrow(String a) {
            amorsarrow = a;
        }
        
        public String getlastContact() {
            return lastContact;
        }

        public void setLastContact(String nick) {
        if (!nick.isEmpty()) {
            boolean exist = false;
                String valueToDelete = "";
                String lastContactNew = "";
                String lastContactEnd = "";
                // prüfen ob dieser bereits vorhanden ist und ggf löschen
               
                if (lastContact != null && lastContact.contains("|"+nick+"~")) {
                exist = true;
                }
                if (exist == true) {
                for(String value : lastContact.split("\\|")) {
                if (value.startsWith(nick+"~")) {
                valueToDelete = value;
                }}}
                if (!valueToDelete.isEmpty()) {
                lastContactNew = lastContact.replace("|"+valueToDelete+"|","");
                } else {
                lastContactNew = lastContact;
                }
                // kontakt hinzufügen
                lastContactNew = "|"+nick+"~"+System.currentTimeMillis()/1000+"|"+lastContactNew;
                // nur die neusten 30 kontakte behalten
                int counter = 1;
                for(String value : lastContactNew.split("\\|")) {
                if (!value.isEmpty()) {
                if (counter <= 30 && !value.equals("null") && !nick.equals(this.name)) {                
                lastContactEnd = lastContactEnd+"|"+value+"|";
                counter++;
                }}}
        
                lastContact = lastContactEnd;
        } else {
            lastContact = lastContact;
        }
                }

	public int getShowEmail() {
		return showEmail;
	}

	public void setShowEmail(byte showEmail) {
		this.showEmail = showEmail;
	}

	public int getShowBirthday() {
		return showBirthday;
	}

	public void setShowBirthday(byte showBirthday) {
		this.showBirthday = showBirthday;
	}

	public int getShowZodiac() {
		return showZodiac;
	}

	public void setShowZodiac(byte showZodiac) {
		this.showZodiac = showZodiac;
	}

	public int getSearchActivate() {
		return searchActivate;
	}

	public void setSearchActivate(byte searchActivate) {
		this.searchActivate = searchActivate;
	}

	public String getSignatur() {
		return signatur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSignatur(String signatur) {
		this.signatur = signatur;
	}

	public void increaseWordMixPoints(float wordmixPoints) {
		this.wordmixPoints += wordmixPoints;
	}
        
        public void increaseHeroCounter(float heroCounter) {
		this.heroCounter += heroCounter;
	}
        
         public void increaseLuckyCounter(int luckyCounter) {
		this.luckyCounter += luckyCounter;
	}
        
        public void increaseUserLogin(float userLogin) {
		this.userLogin += userLogin;
	}

        
        public void increaseRegTage(float regTage) {
		this.regTage += regTage;
	}
        
	public float getWordMixPoints() {
		return wordmixPoints;
	}
        
        public float getHeroCounter() {
		return heroCounter;
	}  
        
        public int getLuckyCounter() {
		return luckyCounter;
	}  
        
        public float getUserLogin() {
		return userLogin;
	}  
        
         public float getRegTage() {
		return regTage;
	}  
	
        public String getReadme() {
		return readme;
	}

	public int getMask() {
		return mask;
	}

	public void setMask(byte mask) {
		this.mask = mask;
	}

	public int getMentorPoints() {		

		return mentorPoints;
	}

	public void increaseMentorPoints(int mentorPoints) {
		this.mentorPoints += mentorPoints;
                  setMentorSmileys();
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

	public int getID() {
		return id;
	}

	public long getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(long lastOnline) {
          	this.lastOnline = lastOnline;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getRank() {
		return rank;
	}

	public void setRank(byte rank) {
		this.rank = rank;
	}
        
        public byte getForenrank() {
		return forenrank;
	}

	public void setForenrank(byte forenrank) {
		this.forenrank = forenrank;
	}
        
        
        public String getRankLabelWhois2(byte rank) {
		if (rank == 0) {
			return "°[0,53,217]°_Mitglied_°r°";
		} else if (rank == 1) {
			return "°[0,53,217]°_°>_hFamilymitglied|/h family<°_°r°";
		} else if (rank == 2) {
			return "°[0,53,217]°_°>_hStammi|/h stammi<°_°r°";
		} else if (rank == 3) {
			return "°[0,53,217]°_°>_hEhrenmitglied|/h ehrenmitglied<°_°r°";
		} else if (rank == 4) {
			return "°[0,53,217]°_°>_hEhrenmitglied|/h ehrenmitglied<°_°r°";
		} else if (rank == 5) {
			return "°[0,53,217]°_°>_hEhrenmitglied|/h ehrenmitglied<°_°r°";
		} else if (rank == 6 || rank == 7) {
			return "°R°_°>_hAdmin|/h admin<°_°r°";
                } else if (rank == 8) {
			return "°R°_°>_hAdmin|/h admin<°_°r°";
		}

		return "°R°_°>_hSysadmin|/h sysadmin<°_°r°";
        
        
        }
        
        
        
        public String[] getPhotoDetails() {
              String photo = "nopic";
            String endung = "gif";
            if (getPhoto() != null && !getPhoto().isEmpty()) {
                 photo = getPhoto();
                endung = "jpg";  
            }
                return new String[] {photo,endung};
            
        
        }
        
        
        public String getRankLabelWhois3(byte rank) {
		if (rank == 0) {
			return "°[0,53,217]°_Mitglied_°r°";
		} else if (rank == 1) {
			return "_°>_hFamilymitglied|/h family<°_°r°";
		} else if (rank == 2) {
			return "_°>_hStammi|/h stammi<°_°r°";
		} else if (rank == 3) {
			return "_°>_hEhrenmitglied|/h ehrenmitglied<°_°r°";
		} else if (rank == 4) {
			return "_°>_hEhrenmitglied|/h ehrenmitglied<°_°r°";
		} else if (rank == 5) {
			return "_°>_hEhrenmitglied|/h ehrenmitglied<°_°r°";
		} else if (rank == 6 || rank == 7) {
			return "°R°_°>_hAdmin|/h admin<°_°r°";
                } else if (rank == 8) {
			return "°R°_°>_hAdmin|/h admin<°_°r°";
		}

		return "°R°_°>_hSysadmin|/h sysadmin<°_°r°";
        
        }
        
        
        
        

	public String getRankLabel(byte rank) {
		if (rank == 0) {
			return "Mitglied";
		} else if (rank == 1) {
			return "Familymitglied";
		} else if (rank == 2) {
			return "Stammi";
		} else if (rank == 3) {
			return "Ehrenmitglied";
		} else if (rank == 4) {
			return "Ehrenmitglied";
		} else if (rank == 5) {
			return "inoffizieller Admin";
		} else if (rank == 6 || rank == 7) {
			return "Admin";
                } else if (rank == 8) {
			return "inoffizieller Sysadmin";
		}

		return "Sysadmin";
	}
        
        
        public String getForenRankLabel(byte rank) {
		if (rank == 0) {
			return "Forumsuser";
		} else if (rank == 1) {
			return "Forumsmoderator";
		} else if (rank == 2) {
			return "Supermoderator";
		} else if (rank == 3) {
			return "Forumssprecher";
		} else if (rank == 5) {
			return "Forumsadmin";
                } else if (rank == 6) {
			return "Forumssysadmin";
		}

		return "Forumssysadmin";
	}

	public int getAge() {
		return age;
	}

	public String getRealName() {
		return realname;
	}

	public String getStadt() {
		return stadt;
	}

	public String getLand() {
		return land;
	}

	public String getHobbys() {
		return hobbys;
	}

	public String getJob() {
		return job;
	}

	public String getMotto() {
		return motto;
	}

	public void setRealName(String realname) {
		this.realname = realname;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public byte getGender() {
		return gender;
	}

	public int getSmileyCount() {
		return 3;
	}

	public int getKisses() {
		return kisses;
	}

	public void increaseKisses() {
		kisses++;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
                timeOut += 600;
                if(this.checkCode(84))
                {
                    timeOut += 1200;
                }
                this.timeOut = timeOut;
                }

	public boolean checkTeam(String team) {
		return teams.toLowerCase().contains(
				String.format("|%s~", team.toLowerCase()));
	}

	public boolean checkTeam(String team, int rank) {
		return teams.toLowerCase().contains(
				String.format("|%s~%s|", team.toLowerCase(), rank));
	}

	public boolean checkTeamLeader(String team) {
		return teams.toLowerCase().contains(
				String.format("|%s~1|", team.toLowerCase()));
	}


	public String getLastOnlineDate() {
		return timeStampToDate(lastOnline);
	}

	public String getLastOnlineTime() {
		return timeStampToTime(lastOnline);
	}

	public String getLastOnlineChannel() {
		return lastOnlineChannel;
	}

	public void setLastOnlineChannel(String lastOnlineChannel) {
		this.lastOnlineChannel = lastOnlineChannel;
	}

	public void increaseKnuddels(float knuddels) {
		this.knuddels += knuddels;
		Server.get().increaseAllKnuddels(knuddels);
	}

	public void deseaseKnuddels(int knuddels) {
		this.knuddels -= knuddels;
		Server.get().deseaseAllKnuddels(knuddels);
	}

	public int getTimeDay() {
		return timeDay
				+ (int) ((System.currentTimeMillis() - loginTimestamp) / 1000);
	}

	public int getTimeMonth() {
		return timeMonth
				+ (int) ((System.currentTimeMillis() - loginTimestamp) / 1000);
	}

	public int getOnlineTime() {
		return onlineTime
				+ (int) ((System.currentTimeMillis() - loginTimestamp) / 1000);
	}

	public int getAdminTime() {
		return adminTime
				+ (int) ((System.currentTimeMillis() - loginTimestamp) / 1000);
	}

	public String getLoginTime() {
		int bla = (int) ((System.currentTimeMillis() - loginTimestamp) / 1000 / 60);
		String what = "";

		if (bla < 1) {
			what = "gerade eben";
		} else {
			what = bla == 1 ? "einer Minute" : String.format("%s Minuten", bla);
		}

		return what;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}
        
        public String getRegistrationTime2() {
		return registrationTime2;
	}

	public String getGenderLabel() {
		if (gender == 1) {
			return "Er";
		}

		if (gender == 2) {
			return "Sie";
		}

		return name;
	}

	public String getIPAddress() {
		return ipAddress;
	}

	public boolean isAway() {
		return away;
	}

	public void setAway(boolean away) {
		this.away = away;

		if (away) {
			if (rank > 7) {
				addIcon("pics/icon_away_ani_new.gif", 18);
			} else {
				addIcon("pics/away.png", 20);
			}
		} else {
			if (rank > 7) {
				removeIcon("pics/icon_away_ani_new.gif");
			} else {
				removeIcon("pics/away.png");
			}
		}
	}

        
        
        /* Jörns altes Game */
    
        
        public int getDranDicen()
  {
    int lol = 0;
    PoolConnection pcon = ConnectionPool.getConnection();
    PreparedStatement ps = null;
    try
    {
      Connection con = pcon.connect();
      ps = con.prepareStatement(new StringBuilder().append("SELECT * FROM game_dicen where  aktuplayer='").append(this.name).append("'").toString());
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        lol = 1;
      }
    }
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
    return lol;
  }
  public int getDranDarten() {
    int lol = 0;
    PoolConnection pcon = ConnectionPool.getConnection();
    PreparedStatement ps = null;
    try
    {
      Connection con = pcon.connect();
      ps = con.prepareStatement(new StringBuilder().append("SELECT * FROM game_darten where  aktuplayer='").append(this.name).append("'").toString());
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        lol = 1;
      }
    }
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
    return lol;
  }

  public int getDranFreidiffen() {
    int lol = 0;
    PoolConnection pcon = ConnectionPool.getConnection();
    PreparedStatement ps = null;
    try
    {
      Connection con = pcon.connect();
      ps = con.prepareStatement(new StringBuilder().append("SELECT * FROM game_freidiffen where  aktuplayer='").append(this.name).append("'").toString());
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        lol = 1;
      }
    }
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
    return lol;
  }
        
        
 /* ENDE */
        
        
        
        
        public int getDiceID() {
return kdice_id;
}

public String getDice() {
return kdice_dice;
}

public Client getDiceUser() {
return kdice_user;
}

public int getDiceCount() {
return kdice_count;
}

public void setDiceUser(Client user, int count, String dice) {
kdice_user = user;
kdice_id = IntegerUtil.rand(100000, 999999);
kdice_count = count;
kdice_dice = dice;
}

public void increaseKnuddels(int num) {
knuddels += num;
}

public void decreaseKnuddels(int num) {
knuddels -= num;
} 





        
        
        
	public boolean checkSpam(boolean kick, Channel channel) {
		long now = System.currentTimeMillis();

		if (rank < 3) {
			if (lastAction > now - channel.butlerMute()) {
				spam++;

				if (kick) {
					if (spam == 5) {
						disconnect();
					}
				} else {
					if (!Server.get().checkCcm(name, channel, 3)) {
						if (spam == 4 || spam == 8) {
							sendButlerMessage(channel.getName(),
									"Bitte _NICHT spammen_ und fluten.");
							increaseWarnings();
							return true;
						}

						if (spam == 10 || warnings == 3) {
							Long time = System.currentTimeMillis() / 1000;
							sendButlerMessage(
									channel.getName(),
									String.format(
											"Du wurdest soeben von °>_h%s|/serverpp \"|/w \"<° im Channel %s ge_°BB>mutet|/h mute<r°_.",
											Server.get().getButler().getName(),
											channel.getName()));
							channel.setMutes(String.format("%s|%s|",
									channel.getMutes(), name));

							if (channel.isVisible()) {
								setComment(time, channel.getName(), "Mute!",
										Server.get().getButler().getName(),
										"Spamming");
							}

							setWarnings((byte)0);

							return true;
						}
					}
				}
			} else {
				spam -= (now - lastAction) / channel.butlerMute();

				if (spam < 0) {
					spam = 0;
				}
			}
		}

		lastAction = now;
		return false;
	}

	public void login(String nickname) throws SQLException {
		if (name != null && !name.equals(nickname)) {
			logout("Ausgeloggt");
		}

		synchronized (channels) {
			if (channels.isEmpty()) {
				Client client = Server.get().getClient(nickname);

				if (client != null) {
					client.disconnect();
				}

				loadStats(nickname);
			}
		}
	}

	public void logout(String message) {
		synchronized (channels) {
			if (!channels.isEmpty()) { 
				for (Channel channel : channels) {
					channel.leave(this);

					if (message != null) {
						send(PacketCreator.kick(channel.getName(), message));
					}
				}

				saveStats();
				channels.clear();
				Server.get().removeClient(name);
				sendHello();
				setTimeOut(0);
				Logger.handle(null, String.format("%s logged out", name));
			}
		}
	}
// ArrayIndexOutOfBoundsException     Channel.size (-1) 
	public Channel getChannel() {
		if (name.equals(Server.get().getButler().getName())) {
			return null;
		}

		synchronized (channels) {
			return channels.get(channels.size() - 1);
		}
	}

	public List<Channel> getChannels() {
		synchronized (channels) {
			return channels;
		}
	}

        public int getFans() {
            return fans;
        }
        public void setFans(int a) {
            fans = a;
        }
	public void joinChannel(Channel channel) {
		synchronized (channels) {
			if (channels.isEmpty()) { // logged in
				Server.get().addClient(this);
			}

			channels.add(channel);
		}
	}

	public void leaveChannel(Channel channel) {
		synchronized (channels) {
			channels.remove(channel);

			if (channels.isEmpty()) { // logged out
                                setLastOnline(System.currentTimeMillis()/1000);
				setLastOnlineChannel(channel.getName());
				saveStats();
				Server.get().removeClient(name);
				sendHello();
				setTimeOut(0);
				Logger.handle(null, String.format("%s logged out", name));
			}
		}
	}

        
        
        
        
        
        
        public void setSyscomments(long time, String channel, String zusatz,
			String von, String text) {
		Client v = Server.get().getClient(von);
		Client t = Server.get().getClient(name);
		boolean online = true;
		if (t == null) {
			online = false;
		}
		StringBuilder cm = new StringBuilder();

		if (v == null) {
			v = new Client(null);
			v.loadStats(von);
		}

		text = KCodeParser.parse(v, text, 5, 10, 20);
		text = Server.get().parseSmileys(v, text);

		cm.append("_°>_h");
		cm.append(von.replace("<", "\\<"));
		cm.append("|/serverpp \"|/w \"<°_");
		if (channel != null) {
			cm.append(" im °>_h");
			cm.append(channel).append("|/go \"|/go +\"<°");
		}
		cm.append(": ");
		if (zusatz != null) {
			cm.append("_\"").append(zusatz).append("_\" ");
		}
		cm.append(text).append(" (").append(Server.get().timeStampToDate(time))
				.append(" ").append(Server.get().timeStampToTime(time))
				.append(")#");

		if (channel == null) {
			if (online) {
				setSyscomments(String.format("%s%s", getSyscomments(), cm.toString()));
			} else {
				Server.get().query(String.format("UPDATE accounts SET syscomments = '%s%s' WHERE name = '%s'", getSyscomments(), cm.toString(), name));
			}
		        } else {
			if (online) {
				setCmcomments(String.format("%s%s", getCmcomments(),
						cm.toString()));
			} else {
				Server.get()
						.query(String
								.format("UPDATE accounts SET cmComments = '%s%s' WHERE name = '%s'",
										getCmcomments(), cm.toString(), name));
			}
		}

	}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	public void setComment(long time, String channel, String zusatz,
			String von, String text) {
		Client v = Server.get().getClient(von);
		Client t = Server.get().getClient(name);
		boolean online = true;
		if (t == null) {
			online = false;
		}
		StringBuilder cm = new StringBuilder();

		if (v == null) {
			v = new Client(null);
			v.loadStats(von);
		}

		text = KCodeParser.parse(v, text, 5, 10, 20);
		text = Server.get().parseSmileys(v, text);

		cm.append("_°>_h");
		cm.append(von.replace("<", "\\<"));
		cm.append("|/serverpp \"|/w \"<°_");
		if (channel != null) {
			cm.append(" im °>_h");
			cm.append(channel).append("|/go \"|/go +\"<°");
		}
		cm.append(": ");
		if (zusatz != null) {
			cm.append("_\"").append(zusatz).append("_\" ");
		}
		cm.append(text).append(" (").append(Server.get().timeStampToDate(time))
				.append(" ").append(Server.get().timeStampToTime(time))
				.append(")#");

		if (channel == null) {
			if (online) {
				setComments(String.format("%s%s", getComments(), cm.toString()));
			} else {
				Server.get().query(String.format("UPDATE accounts SET comments = '%s%s' WHERE name = '%s'", getComments(), cm.toString(), name));
			}
		        } else {
			if (online) {
				setCmcomments(String.format("%s%s", getCmcomments(),
						cm.toString()));
			} else {
				Server.get()
						.query(String
								.format("UPDATE accounts SET cmComments = '%s%s' WHERE name = '%s'",
										getCmcomments(), cm.toString(), name));
			}
		}

	}


        
        
	public void loadStats(String name) {
		PoolConnection pcon = ConnectionPool.getConnection();
		Statement ps = null;

		try {
			Connection con = pcon.connect();
			ps = con.createStatement();
			ResultSet rs = ps
					.executeQuery(String
							.format("SELECT * FROM `accounts` WHERE `name` = '%s'",
									name));

			if (rs.next()) {
				this.name = rs.getString("name");
				snp = rs.getByte("snp");
                                level = rs.getInt("level");
                                levelsystem = rs.getString("levelsystem");
				authActive = rs.getByte("authActive");
                                partnerlook = rs.getString("partnerlook");
                                lovepotion = rs.getString("lovepotion");
                                lovewait = rs.getInt("lovewait");
                                loveto = rs.getString("loveto");
                                lovefrom = rs.getString("lovefrom");
                                bad6 = rs.getInt("bad6");
                                hallOfFameMessage = rs.getInt("hallOfFameMessage");
                                amorsarrow = rs.getString("amorsarrow");
                                _blitzPoints = rs.getFloat("blitzpoints");
                                _quessPoints = rs.getFloat("quesspoints");
				authPassword = rs.getString("authPassword");
                                gendersmiley = rs.getInt("gendersmiley");
                                forum_post_counter = rs.getInt("forum_post_counter");				
                                donate  = rs.getInt("donate");
                                logincookie = rs.getString("logincookie");
				emailVerify = rs.getByte("emailVerify");
				holdhands = rs.getString("holdhands");
                                moskitoabgewehrt = rs.getInt("moskitoabgewehrt");
                                moskitogestochen = rs.getInt("moskitogestochen");
                                fans = rs.getInt("photo_fancounter");
                                auctionend = rs.getString("auctionend");
                                lastbieter = rs.getString("lastbieter");
                                auctionto = rs.getString("auctionto");
                                auctionfrom = rs.getString("auctionfrom");
                                rosenick = rs.getString("rosenick");
                                nicktag = rs.getString("nicktag");
                                tinkle = rs.getInt("tinkle");
                                tinklelast = rs.getString("tinklelast");
                                washende = rs.getLong("washende");
                                schutzschild = rs.getInt("schutzschild");
                                sunende = rs.getLong("sunende");
                                moskitoende = rs.getLong("moskitoende");
				smileys = rs.getString("smileys");
				element = rs.getInt("element");
                                elementrechner = rs.getInt("elementrechner");
                                rhapsodyto = rs.getString("rhapsodyto");
                                
                                 album_id = rs.getString("album_id");
                                album_cover_image = rs.getString("album_cover_image");
                                photo_verify_last = rs.getInt("photo_verify_last");
                                album_counter = rs.getString("album_counter");
                                 hp = rs.getInt("hp");
                                hpban= rs.getInt("hpban");
                                gb = rs.getInt("gb");
                                
                                 photo_verify_last = rs.getInt("photo_verify_last");
                               
                                rhapsodyfrom = rs.getString("rhapsodyfrom");
                                lastContact = rs.getString("lastContact");
				readmeEffects = rs.getByte("readmeeffects");
				newPhotoComment = rs.getByte("newPhotoComment");
				zusammen = rs.getString("zusammen");
                                heroToday = rs.getString("heroToday");
                                seeFriends = rs.getString("seeFriends");
                                turnedHeadTo = rs.getString("turnedHeadTo");
                                turnedHeadFrom = rs.getString("turnedHeadFrom");
                                adoreTo = rs.getString("adoreTo");
                                loveyou = rs.getString("loveyou");
                                neveralone = rs.getString("neveralone");
                                adoreFrom = rs.getString("adoreFrom");
				like = rs.getString("like");
				rank = rs.getByte("rank");
                                forenrank = rs.getByte("forenrank");
				agePlus = rs.getByte("agePlus");
				kissed = rs.getByte("kissed");
				weckMessage = rs.getByte("weckMessage");
				sun = rs.getByte("sun");
				sunLock = rs.getByte("sunLock");
				icons = new ArrayList<Pair<String, Integer>>();
				gender = rs.getByte("gender");
				bazookasperre = rs.getByte("bazookasperre");
				missed = rs.getString("missed");
				agent = rs.getString("agent");
				butterflysperre = rs.getByte("butterflysperre");
				immunsperre = rs.getByte("immunsperre");
				flyingbedsperre = rs.getByte("flyingbedsperre");
				winksperre = rs.getByte("winksperre");
				photo_verify = rs.getByte("photo_verify");
				heartMessage = rs.getByte("heartMessage");
				grippestatus = rs.getByte("grippestatus");
                                grippeinfiziert = rs.getInt("grippeinfiziert");
				onlineTime = rs.getInt("onlineTime");
				adminTime = rs.getInt("adminTime");
				heart = rs.getString("heart");
				belohnen = rs.getByte("belohnen");
				timeMonth = rs.getInt("timeMonth");
				timeDay = rs.getInt("timeDay");
				photo = rs.getString("photo");
				kontoPassword = rs.getString("kontoPassword");
				kontoKnuddels = rs.getInt("kontoKnuddels");
				mpChat = rs.getByte("mpChat");
				mpFriendlist = rs.getByte("mpFriendlist");
				knuddels = rs.getFloat("knuddels");
                                effekt = rs.getString("nickeffekt");
                                showeffekt = rs.getInt("shownickeffekt");
                                codes = rs.getString("smileycodes");                             
                                smileys2 = rs.getString("smileyidsgeliehen");
                                codesinfo = rs.getString("codesinfo");
                                smileyvisit = rs.getInt("smileyvisit");                              
                                codee = rs.getInt("codesE");
                                codetradelog = rs.getString("codetradelog");
                                codev = rs.getInt("codesV");
                                allowedFeatures = rs.getString("allowedFeatures");
		
                                tageslogin = rs.getInt("tageslogin");
                                color = rs.getString("color");
				knuddelscentLevel = rs.getByte("knuddelscentLevel");
				knuddelscentLock = rs.getByte("knuddelscentLock");
                                knuddelscent = rs.getInt("knuddelscent");
                                dailybonus = rs.getInt("dailybonus");
                                firstSmiley = rs.getInt("firstSmiley");
                                tutorialsend = rs.getInt("tutorialsend");
                                holrank = rs.getInt("holrank");
                                blitzrank = rs.getInt("blitzrank");
				messageSound = rs.getByte("messageSound");
				thiefGame = rs.getInt("thiefgame");
                                joinSound = rs.getString("joinSound");
                                leftSound = rs.getString("leftSound");
                                quizPoints = rs.getFloat("quizpoints");
                                translatePoints = rs.getFloat("translatepoints");
                                mathePoints = rs.getFloat("mathepoints");
                                mixPoints = rs.getFloat("mixpoints");
                              
                                  if (gender == 1) {
                                    if (gendersmiley == 0) {
					addIcon("pics/male.png", 16);
                                    } else {
                                          addIcon("pics/features/gendericon/ft_11-09_boy...iconoverflow.png", 21);
                                   
                                    }
				} else if (gender == 2) {
                                    if (gendersmiley == 0) {
					addIcon("pics/female.png", 14);
                                    } else {
                                        addIcon("pics/features/gendericon/ft_11-09_girl...iconoverflow.png", 21);
                                    }
				}


				if (photo_verify == 1) {
					addIcon("pics/fv_checked.png", 18);
				}

				if (grippestatus > 0) {
					addIcon("pics/nose.gif", 18);
				}   

				if (Server.sexy.contains(name)) {
					addIcon("pics/sexy.gif", 24);
				}
                                
                               if (Server.cool.contains(name)) {
					addIcon("pics/cool.gif", 29);
				}
                               if (Settings.getTopdayUser().equals(name)) {
                                        if(gender==1) {
                 addIcon("pics/top_online_boy.png", 17);

        } else {
                  addIcon("pics/top_online_girl.png", 17);

        }
                               }

				kisses = rs.getInt("kisses");
				sexysperre = rs.getByte("sexysperre");
                                herosperre = rs.getByte("herosperre");
                                holsperre = rs.getByte("holsperre");
                                bot = rs.getByte("bot");
                                friendask = rs.getString("friendask");
                                coolsperre = rs.getByte("coolsperre");
				comments = rs.getString("comments");
                                syscomments = rs.getString("syscomments");
				cmwhen = rs.getString("cmwhen");
				sperrevon = rs.getString("sperrevon");
                                mutevon = rs.getString("mutevon");
                                channellockvon = rs.getString("channellockvon");
				sperre = rs.getLong("sperre");
                                disable = rs.getLong("disable");
                                wStyle = rs.getInt("wStyle");
                                deletenick = rs.getLong("deletenick");
				sperreinfo = rs.getString("sperreinfo");
                                disableinfo = rs.getString("disableinfo");
				stammiwhen = rs.getString("stammiwhen");
				cmcomments = rs.getString("cmcomments");
				lcmonths = rs.getByte("lcmonths");
				hol = rs.getInt("hol");
				plz = rs.getString("plz");
				plzLand = rs.getString("plzCountry");
				starsFrom = rs.getString("starsFrom");
				scrollspeed = rs.getInt("scrollspeed");
				admincallFirst = rs.getInt("admincallFirst");
				admincallSecond = rs.getInt("admincallSecond");
				admincallThird = rs.getInt("admincallThird");
				emails = rs.getByte("emails");
				kicks = rs.getInt("kicks");
                                locks = rs.getInt("locks");
                                visit = rs.getInt("visit");
				notrufsperre = rs.getLong("notrufsperre");
				spitznamen = rs.getString("spitznamen");
				starsTo = rs.getString("starsTo");
				showEffects = rs.getByte("showEffects");
				kissall = rs.getByte("kissall");
				wahlsperre = rs.getLong("wahlsperre");
				heartsperre = rs.getByte("heartLock");
				verify = rs.getByte("verify");
				password = rs.getString("password");
				friendlist = rs.getString("friendlist");
				faSort = rs.getByte("faSort");
                                fSort = rs.getByte("fSort");
				profile = rs.getString("profile");
				career = rs.getString("career");
                                career2 = rs.getString("career2");
                                highlights = rs.getString("highlights");
				mentor = rs.getString("mentor");
                                userLogin = rs.getFloat("UserLogin");
				newssperre = rs.getByte("newssperre");
				loginTimestamp = System.currentTimeMillis();
				age = rs.getByte("age");
				mentorPoints = rs.getInt("mentorPoints");
				spielsperre = rs.getLong("spielsperre");
				mask = rs.getByte("mask");
				teams = rs.getString("teams");
				lotto = rs.getInt("lotto");
				ignoredNicks = rs.getString("ig");
                                visitNicks = rs.getString("visitnicks");
				id = rs.getInt("id");
				cls = rs.getInt("cls");
				roses = rs.getInt("roses");
				rosesSend = rs.getByte("rosesSend");
				cmutes = rs.getInt("cmutes");
				mutes = rs.getInt("mutes");
				realname = rs.getString("realname");
				friends = rs.getString("friends");
				dream = rs.getString("dream");
				stadt = rs.getString("stadt");
				desc = rs.getString("desc");
				birthday = rs.getString("birthday");
				wordmixPoints = rs.getFloat("wordmixpoints");
                                heroCounter = rs.getFloat("herocounter");
                                luckyCounter = rs.getInt("luckyCounter");
                                regTage = rs.getFloat("regtage");
				aufrufe = rs.getInt("aufrufe");
				land = rs.getString("land");
				signatur = rs.getString("signature");
				hobbys = rs.getString("hobbys");
				job = rs.getString("job");
				registerIP = rs.getString("registerIP");
				zeichen = rs.getInt("zeichen");
				lcsperre = rs.getByte("lcsperre");
				friendssperre = rs.getByte("friendssperre");
				dreamsperre = rs.getByte("dreamsperre");
				silence = rs.getByte("silence");
				starlitesperre = rs.getByte("starlitesperre");
				lc = rs.getString("lc");
				motto = rs.getString("motto");
				healsperre = rs.getByte("healsperre");
				effect = rs.getByte("effect");
				readme = rs.getString("readme");
				showEmail = rs.getByte("showEmail");
				analysedatasperre = rs.getByte("analysedatasperre");
				jumpopunkte = rs.getInt("jumpopunkte");
				searchGender = rs.getString("searchGender");
				searchEntfernung = rs.getString("searchEntfernung");
				flames = rs.getInt("flames");
				searchAgeFrom = rs.getByte("searchAgeFrom");
				searchAgeUntil = rs.getByte("searchAgeUntil");
				searchMotiv = rs.getString("searchMotiv");
				showBirthday = rs.getByte("showBirthday");
				searchActivate = rs.getByte("searchActivate");
				vergeben = rs.getString("vergeben");
				schuetzlinge = rs.getString("schuetzlinge");
				showZodiac = rs.getByte("showZodiac");
				email = rs.getString("email");
				lastOnlineChannel = rs.getString("lastOnlineChannel");
				lastOnline = rs.getLong("lastOnline");
				date = rs.getString("date");
				long registration = rs.getLong("registration");
				registrationDate = Server.get().timeStampToDate(registration);
				registrationTime = Server.get().timeStampToTime(registration);
                                registrationTime2 = Server.get().timeStampToTime2(registration);
				host = rs.getString("host");
				tutOpen = rs.getByte("tutOpen");
				tutAktiv = rs.getByte("tutAktiv");
				aktuTut = rs.getByte("aktuTut");

				if (socket == null) {
					ipAddress = rs.getString("ipAddress");
					host = rs.getString("host");
				} else {
					ipAddress = socket.getInetAddress().getHostAddress();
					host = socket.getInetAddress().getHostName();
				}

				away = false;
				spam = 0;
				lastAction = 0;
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"select text,time from readmes where name='%s'",
					id));
			while (rs.next()) {
				String text = rs.getString("text");
				long time = rs.getLong("time");
				
				readmes.put(time, text);
			}
			rs.close();
			 rs = ps.executeQuery(String.format(
					"SELECT `time`,`an`,`betreff`,`text`,`id` FROM `messages` WHERE `von` = '%s' ORDER BY id DESC limit 15",
					name));
			while (rs.next()) {
				long time = rs.getLong("time");
				String an = rs.getString("an");
				String betreff = rs.getString("betreff");
				String text = rs.getString("text");
				String[] a = new String[]{String.valueOf(time),an,betreff,text,String.valueOf(id)};
				
				if(!sentMessages.contains(a)) {
					sentMessages.add(a);
				}
                                
			}
			rs.close();
			
                       rs = ps.executeQuery(String.format(
					"SELECT `time`,`von`,`betreff`,`text`,`id` FROM `messages` WHERE `archiv` = '1' and `an` = '%s' ORDER BY id DESC limit 20",
					name));
			while (rs.next()) {
				long time = rs.getLong("time");
				String von = rs.getString("von");
				String betreff = rs.getString("betreff");
				String text = rs.getString("text");
				String id = rs.getString("id");
				String[] a = new String[]{String.valueOf(time),von,betreff,text};
				
				if(!archivMessages.contains(a)) {
					archivMessages.add(a);
				}
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"SELECT `time`,`von`,`betreff`,`text`,`id` FROM `messages` WHERE `gelesen` = '1' and `an` = '%s' ORDER BY id DESC limit 15",
					name));
			while (rs.next()) {
				long time = rs.getLong("time");
				String von = rs.getString("von");
				String betreff = rs.getString("betreff");
				String text = rs.getString("text");
				String id = rs.getString("id");
				String[] a = new String[]{String.valueOf(time),von,betreff,text};
				
				if(!oldMessages.contains(a)) {
					oldMessages.add(a);
				}
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"SELECT `time`,`von`,`betreff`,`text`,`id` FROM `messages` WHERE `gelesen` = '0' and `an` = '%s'",
					name));
			while (rs.next()) {
				long time = rs.getLong("time");
				String von = rs.getString("von");
				String betreff = rs.getString("betreff");
				String text = rs.getString("text");
                                
				String[] a = new String[]{String.valueOf(time),von,betreff,text};
				
				if(!newMessages.contains(a)) {
					newMessages.add(a);
				}
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"SELECT `id`, `verstoss`, `abgesetzt` FROM `admincalls` where ergebnis='Notruf berechtigt' and beschuldigter='%s' order by abgesetzt",
					name));
			while (rs.next()) {
				long bla = rs.getLong("abgesetzt");
				admincalls.add(new String[] {rs.getString("verstoss"), rs.getString("id"), String.format("%s %s", Server.get().timeStampToDate(bla), Server.get().timeStampToTime(bla))});
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"SELECT `name` FROM `accounts` WHERE `heart`= '%s'", name));
			while (rs.next()) {
				String nick = rs.getString("name");
				
				if(!receivedHearts.contains(nick)) {
					receivedHearts.add(nick);
				}
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"SELECT `uhrzeit`, `ip` FROM `loginlist` where name = '%s' order by id",
					name));
			while (rs.next()) {
				String[] x = new String[] {rs.getString("uhrzeit"), rs.getString("ip")};
				
				if(!logins.contains(x)) {
					logins.add(x);
				}
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"select von, uhrzeit, text from roses where an = '%s' and erhalten = 1 order by id desc limit 10",
					name));
			while (rs.next()) {
				String[] x = new String[] {rs.getString("von"), rs.getString("text"), rs.getString("uhrzeit")};
				
				if(!rosen.contains(x)) {
					rosen.add(x);
				}
			}
			rs.close();
			rs = ps.executeQuery(String.format(
					"SELECT von, text, id FROM `roses` where erhalten = 0 and an = '%s' order by id",
					name));
			while (rs.next()) {
				String[] x = new String[] {rs.getString("von"), rs.getString("text"), rs.getString("id")};
				
				if(!newRoses.contains(x)) {
					newRoses.add(x);
				}
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
	}

	public void saveStats() {
		long systemtime = System.currentTimeMillis() / 1000;

		PoolConnection pcon = ConnectionPool.getConnection();
		PreparedStatement ps = null;

		try {
			Connection con = pcon.connect();
			ps = con.prepareStatement("UPDATE `accounts` SET `kisses` = ?, `desc` = ?, `onlineTime` = ?, `ipAddress` = ?, `realname` = ?, `stadt` = ?, `land` = ?, `hobbys` = ?, `job` = ?, `motto` = ?, `age` = ?, `password` = ?, `gender` = ?, `readme` = ?, `lastOnline` = ?, `lastOnlineChannel` = ?, `wordmixPoints` = ?, `mask` = ?, `effect` = ?, `signature` = ?, `email` = ?, `showEmail` = ?, `showZodiac` = ?, `showBirthday` = ?, `searchActivate` = ?, `searchAgeUntil` = ?, `searchAgeFrom` = ?, `searchEntfernung` = ?, `searchMotiv` = ?, `searchGender` = ?, `vergeben` = ?, `birthday` = ?, `lcsperre` = ?, `lc` = ?, `zeichen` = ?, `cls` = ?, `cmutes` = ?, `mutes` = ?, `flames` = ?, `friends` = ?, `dream` = ?, `roses` = ?, `rosesSend` = ?, `lotto` = ?, `newssperre` = ?, `date` = ?, `career` = ?, `profile` = ?, `heartLock` = ?, `ig` = ?, `friendlist` = ?, `comments` = ?, `cmcomments` = ?, `plz` = ?, `plzCountry` = ?, `verify` = ?, `starsFrom` = ?, `starsTo` = ?, `snp` = ?, `teams` = ?, `scrollspeed` = ?, `wahlsperre` = ?, `silence` = ?, `dreamsperre` = ?, `friendssperre` = ?, `starlitesperre` = ?, `mentor` = ?, `kissall` = ?, `tutOpen` = ?, `jumpopunkte` = ?, `spitznamen` = ?, `aufrufe` = ?, `hol`=?, `admincallFirst` = ?, `admincallSecond` = ?, `admincallThird` = ?, `faSort` = ?,`zusammen` = ?,`sexysperre` = ?, `rank`=?, `showEffects`=?,`adminTime` = ?,`kicks` = ?,`notrufsperre` = ?,`analysedatasperre`=?,`schuetzlinge`=?,`lcmonths` =?,`tutAktiv`=?,`cmwhen`=?,`timeDay`=?,`timeMonth`=?,`knuddels`=?,`photo`=?,`spielsperre`=?,`sperre`=?,`sperreinfo`=?,`sperrevon`=?,`belohnen`=?,`mentorPoints`=?,`mpChat`=?,`mpFriendlist`=?,`kontoKnuddels`=?, `kontoPassword`=?,`heartMessage`=?,`healsperre`=?,`grippestatus`=?,`immunsperre`=?,`flyingbedsperre`=?,`winksperre`=?,`bazookasperre`=?,`butterflysperre`=?,`weckMessage`=?,`sun`=?,`sunLock`=?,`color` =?,`kissed`=?,`agePlus`=?,`newPhotoComment`=?,`aktuTut`=?,`stammiwhen`=?,`readmeeffects`=?,`smileys`=?,`element`=?,`holdhands` = ?, `like` = ?,`missed`=?,`emails`=?,`emailVerify`=?,`knuddelscentLevel`=?,`knuddelscentLock`=?,`messageSound`=?,`thiefGame`=?,`photo_verify`=?,`disable`=?,`deletenick`=?,`locks`=?,`coolsperre`=?,`fSort` = ?,`heroToday` = ?,`heroCounter` = ?,`userLogin` = ?,`regTage` = ?,`herosperre` = ?,`blitzpoints` = ?,`disableinfo` = ?,`visit` = ?,`syscomments` = ?,`visitnicks` = ?,`mutevon` = ?,`channellockvon` = ?,`authPassword`=?,`authActive`=?, `smileycodes` = ?, `codesV` = ?, `codesE` = ?, `codesinfo` = ?, `codetradelog` = ?, `smileyidsgeliehen` = ?,`allowedFeatures` = ?,`smileyvisit` = ?,`nickeffekt` = ?,`shownickeffekt` = ?,`translatePoints` = ?,`mathePoints` = ?,`mixPoints` = ?,`knuddelscent` = ?,`dailybonus` = ?,`tutorialsend` = ?,`turnedHeadTo` = ?,`turnedHeadFrom` = ?,`adoreTo` = ?,`adoreFrom` = ?,`bot` = ?,`holsperre` = ?,`quizPoints`=?, `lastContact`= ?,`quesspoints` = ?,auctionend=?, lastbieter=?,auctionto=?,auctionfrom=?, seeFriends=?, `friendask` = ?,`rhapsodyto`=?,`rhapsodyfrom`=?,`wStyle`=?, `photo_fancounter` = ?,`photo_verify_last` = ?,`album_cover_image` = ?, `album_id` = ?, `album_counter` = ?, `hp` = ?, `gb` = ?, `hpban` = ?, `elementrechner` = ?, `washende` = ?, `sunende` = ?, `moskitoende` = ?, `moskitoabgewehrt` = ?, `moskitogestochen` = ?, `tinkle` = ?, `tinklelast` = ?, `rosenick` = ?, `nicktag` = ?, `loveyou` = ?, `neveralone` = ?, `gendersmiley` = ?, `partnerlook` = ?, `amorsarrow` = ?, `lovepotion` = ?, `loveto` = ?, `lovefrom` = ?, `lovewait` = ?, `luckyCounter` = ?, `logincookie` = ?, `donate`= ?, `forum_post_counter` = ?, `bad6` = ?, `forenrank` = ?, `hallOfFameMessage` = ?,`level` = ?, `levelsystem` = ?, `schutzschild` = ?, `career2` = ?, `highlights` = ?, `holrank` = ?, `blitzrank` = ?, `firstSmiley` = ?, `grippeinfiziert` = ?  WHERE `name` = ? LIMIT 1");
			ps.setInt(1, kisses);
			ps.setString(2, desc);
			ps.setInt(3, getOnlineTime());
			ps.setString(4, ipAddress);
			ps.setString(5, realname);
			ps.setString(6, stadt);
			ps.setString(7, land);
			ps.setString(8, hobbys);
			ps.setString(9, job);
			ps.setString(10, motto);
			ps.setInt(11, age);
			ps.setString(12, password);
			ps.setInt(13, gender);
			ps.setString(14, readme);
			ps.setLong(15, systemtime);
			ps.setString(16, lastOnlineChannel);
			ps.setFloat(17, wordmixPoints);
			ps.setInt(18, mask);
			ps.setInt(19, effect);
			ps.setString(20, signatur);
			ps.setString(21, email);
			ps.setInt(22, showEmail);
			ps.setInt(23, showZodiac);
			ps.setInt(24, showBirthday);
			ps.setInt(25, searchActivate);
			ps.setInt(26, searchAgeUntil);
			ps.setInt(27, searchAgeFrom);
			ps.setString(28, searchEntfernung);
			ps.setString(29, searchMotiv);
			ps.setString(30, searchGender);
			ps.setString(31, vergeben);
			ps.setString(32, birthday);
			ps.setInt(33, lcsperre);
			ps.setString(34, lc);
			ps.setInt(35, zeichen);
			ps.setInt(36, cls);
			ps.setInt(37, cmutes);
			ps.setInt(38, mutes);
			ps.setInt(39, flames);
			ps.setString(40, friends);
			ps.setString(41, dream);
			ps.setInt(42, roses);
			ps.setInt(43, rosesSend);
			ps.setInt(44, lotto);
			ps.setInt(45, newssperre);
			ps.setString(46, date);
			ps.setString(47, career);
			ps.setString(48, profile);
			ps.setInt(49, heartsperre);
			ps.setString(50, ignoredNicks);
			ps.setString(51, friendlist);
			ps.setString(52, comments);
			ps.setString(53, cmcomments);
			ps.setString(54, plz);
			ps.setString(55, plzLand);
			ps.setInt(56, verify);
			ps.setString(57, starsFrom);
			ps.setString(58, starsTo);
			ps.setInt(59, snp);
			ps.setString(60, teams);
			ps.setInt(61, scrollspeed);
			ps.setLong(62, wahlsperre);
			ps.setInt(63, silence);
			ps.setInt(64, dreamsperre);
			ps.setInt(65, friendssperre);
			ps.setInt(66, starlitesperre);
			ps.setString(67, mentor);
			ps.setInt(68, kissall);
			ps.setInt(69, tutOpen);
			ps.setInt(70, jumpopunkte);
			ps.setString(71, spitznamen);
			ps.setInt(72, aufrufe);
			ps.setInt(73, hol);
			ps.setInt(74, admincallFirst);
			ps.setInt(75, admincallSecond);
			ps.setInt(76, admincallThird);
			ps.setInt(77, faSort);
			ps.setString(78, zusammen);
			ps.setInt(79, sexysperre);
			ps.setByte(80, rank);
			ps.setInt(81, showEffects);
			ps.setInt(82, getAdminTime());
			ps.setInt(83, kicks);
			ps.setLong(84, notrufsperre);
			ps.setInt(85, analysedatasperre);
			ps.setString(86, schuetzlinge);
			ps.setInt(87, lcmonths);
			ps.setInt(88, tutAktiv);
			ps.setString(89, cmwhen);
			ps.setInt(90, getTimeDay());
			ps.setInt(91, getTimeMonth());
			ps.setFloat(92, knuddels);
			ps.setString(93, photo);
			ps.setLong(94, spielsperre);
			ps.setLong(95, sperre);
			ps.setString(96, sperreinfo);
			ps.setString(97, sperrevon);
			ps.setInt(98, belohnen);
			ps.setInt(99, mentorPoints);
			ps.setInt(100, mpChat);
			ps.setInt(101, mpFriendlist);
			ps.setInt(102, kontoKnuddels);
			ps.setString(103, kontoPassword);
			ps.setInt(104, heartMessage);
			ps.setInt(105, healsperre);
			ps.setInt(106, grippestatus);
			ps.setInt(107, immunsperre);
			ps.setInt(108, flyingbedsperre);
			ps.setInt(109, winksperre);
			ps.setInt(110, bazookasperre);
			ps.setInt(111, butterflysperre);
			ps.setInt(112, weckMessage);
			ps.setInt(113, sun);
			ps.setInt(114, sunLock);
			ps.setString(115, color);
			ps.setInt(116, kissed);
			ps.setInt(117, agePlus);
			ps.setInt(118, newPhotoComment);
			ps.setInt(119, aktuTut);
			ps.setString(120, stammiwhen);
			ps.setInt(121, readmeEffects);
			ps.setString(122, smileys);
			ps.setInt(123, element);
			ps.setString(124, holdhands);
			ps.setString(125, like);
			ps.setString(126, missed);
			ps.setInt(127, emails);
			ps.setInt(128, emailVerify);
			ps.setInt(129, knuddelscentLevel);
			ps.setInt(130, knuddelscentLock);
			ps.setInt(131, messageSound);
			ps.setInt(132, thiefGame);
                        ps.setByte(133, photo_verify);
                        ps.setLong(134, disable);
                        ps.setLong(135, deletenick); 
                        ps.setInt(136, locks);
                        ps.setInt(137, coolsperre);
                        ps.setInt(138, fSort);
                        ps.setString(139, heroToday);
                        ps.setFloat(140, heroCounter);
                        ps.setFloat(141, userLogin);
                        ps.setFloat(142, regTage);
                        ps.setInt(143, herosperre);
                        ps.setFloat(144, _blitzPoints);
                        ps.setString(145, disableinfo);
                        ps.setInt(146, visit);
                        ps.setString(147, syscomments);
                        ps.setString(148, visitNicks);
                        ps.setString(149, mutevon);
                        ps.setString(150, channellockvon);
			ps.setString(151, authPassword);
			ps.setByte(152, authActive);
                        ps.setString(153,codes);
                        ps.setInt(154,codev);
                        ps.setInt(155,codee);
                        ps.setString(156,codesinfo);
                        ps.setString(157,codetradelog);
                        ps.setString(158,smileys2);
                        ps.setString(159,allowedFeatures);
                        ps.setInt(160,smileyvisit);
                        ps.setString(161,effekt);
                        ps.setInt(162,showeffekt);
                        ps.setFloat(163,translatePoints);
                        ps.setFloat(164, mathePoints);
                        ps.setFloat(165,mixPoints);
                        ps.setInt(166, knuddelscent);
                        ps.setInt(167, dailybonus);
                        ps.setInt(168, tutorialsend);
                        ps.setString(169, turnedHeadTo);
                        ps.setString(170, turnedHeadFrom);
                        ps.setString(171, adoreTo);
                        ps.setString(172, adoreFrom);
                        ps.setInt(173, bot);
                        ps.setInt(174, holsperre);
                        ps.setFloat(175, quizPoints);
                        ps.setString(176, lastContact);
                        ps.setFloat(177, _quessPoints);
                        ps.setString(178,auctionend);
                        ps.setString(179,lastbieter);
                        ps.setString(180,auctionto);
                        ps.setString(181,auctionfrom);
                        ps.setString(182, seeFriends);
                        ps.setString(183, friendask);
                        ps.setString(184,rhapsodyto);
                        ps.setString(185,rhapsodyfrom);
                        ps.setInt(186, wStyle);
                        ps.setInt(187,fans);
                        ps.setInt(188,photo_verify_last);
                        ps.setString(189,album_cover_image);
                        ps.setString(190,album_id);
                        ps.setString(191,album_counter);
                        ps.setInt(192,hp);
                        ps.setInt(193,gb);
                        ps.setInt(194,hpban);
                        ps.setInt(195,elementrechner);
                        ps.setLong(196,washende);
                        ps.setLong(197, sunende);
                        ps.setLong(198,moskitoende);
                        ps.setInt(199,moskitoabgewehrt);
                        ps.setInt(200,moskitogestochen);
                        ps.setInt(201,tinkle);
                        ps.setString(202, tinklelast);
                        ps.setString(203,rosenick);
                        ps.setString(204,nicktag);
                        ps.setString(205,loveyou);
                        ps.setString(206,neveralone);
                        ps.setInt(207,gendersmiley);
                        ps.setString(208,partnerlook);
                        ps.setString(209,amorsarrow);
                        ps.setString(210,lovepotion);
                        ps.setString(211, loveto);
                        ps.setString(212,lovefrom);
                        ps.setInt(213,lovewait);
                        ps.setFloat(214, luckyCounter);
                        ps.setString(215,logincookie);
                        ps.setInt(216,donate);
                        ps.setInt(217, forum_post_counter);
                        ps.setInt(218,bad6);
                        ps.setByte(219, forenrank);
                        ps.setInt(220,hallOfFameMessage);
                        ps.setInt(221,level);
                        ps.setString(222,levelsystem);
                        ps.setInt(223,schutzschild);
                        ps.setString(224, career2);
                        ps.setString(225, highlights);
                        ps.setInt(226, holrank);
                        ps.setInt(227, blitzrank);
                        ps.setInt(228, firstSmiley);
                        ps.setInt(229,grippeinfiziert);
                        ps.setString(230, name);                        
			ps.executeUpdate();
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

		if (holrunde != 0) {
			Server.get().newMessage(
							Server.get().getButler().getName(),
							name,
							"High or Low",
							String.format(
									"Hallo %s,##da du dich ausgeloggt hast, wurde deine aktuelle High or Low Runde abgebrochen und nicht mehr gewertet.##Liebe Grüße,#"+Server.get().getButler().getName(),
									name),  System.currentTimeMillis()/1000);
		}

		setLastChannel(null);
		setNewChannel(null);
		readmes.clear();
		logins.clear();
		newRoses.clear();
		rosen.clear();
		admincalls.clear();
		
	}

	public void sendHello() {
		Collection<Channel> channelList = Server.get().getChannels();
		send(PacketCreator
				.hello(((Channel) channelList.toArray()[0]).getName()));
		send(PacketCreator.channelList(channelList, getLoginCategory()));
	}
        
        

	public void sendPrivateMessage(List<Client> targets, String message,
		 
                Channel channel, boolean fromGame) {
		if (!fromGame
				&& channel.getGame() != null
				&& !channel.getGame().parsePrivateMessage(targets, message,
						this)) {
			return;
		}
                
                   if (channel.getFeatures() != null && !channel.getFeatures().parsePrivateMessage(targets, message,this)) {
			return;
		}
		 this.setLevelInfo("p",1);
		message = KCodeParser.parse(this, message, 5, 10, 20);
		message = Server.get().parseSmileys(this, message);

		if (message.isEmpty()) {
			return;
		}

		StringBuilder recipients = new StringBuilder();
String an = "";
		for (Client target : targets) {
                    if (!an.isEmpty()) {
                        an += ", ";
                    }
                    an += target.getName();
 			recipients.append(target.getName());
			recipients.append(",");
                                              
                  this.addPrivatHistory(target.getName(),channel.getName(), message);
             
		}

		String recipient = recipients.substring(0, recipients.length() - 1);

		if (!targets.contains(this)) {
			targets.add(this);
		}
                   
		for (Client target : targets) {
                    // letzte dialoge setzen
  
     
             Client dd = Server.get().getClient(name);
                
             if (dd != target) {
                 dd.setLastContact(target.getName());
                 target.setLastContact(name); 
             }
             // ende
                    
			if (channel.getClients().contains(target)) {
				target.send(PacketCreator.privateMessage(name, recipient,
						channel.getName(), message, " "));
			} else {
				for (Channel ch : target.getChannels()) {
					target.send(PacketCreator.privateMessage(name, recipient,
							ch.getName(), message,
							channel.isVisible() ? channel.getName() : "?"));
				}
			}

			if (target.isAway()) {
				sendButlerMessage(channel.getName(), String.format(
						"%s ist gerade _/away_!%s",
						target.getName(),
						target.getAwayReason() == null ? "" : String.format(
								" (%s)", target.getAwayReason())));
			}
		}
	}

	public void sendButlerMessage(String channel, String message) {
		send(PacketCreator.privateMessage(Server.get().getButler().getName(),
				name, channel, message, " "));
	}

	public void send(String message) {
		if (socket != null && socket.isConnected()) {
			try {
			//	out.write(Protocol.encode(message.getBytes("UTF8")));
				out.write(Protocol.encode(Huffman.getEncoder().encode(message,0)));
			} catch (Exception e) {
			}
		}
	}

	public void disconnect() {
		logout(null);

		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
	}

   
   

   
   

}

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

import feature.Features;
import feature.GreedyPig;
import funktionen.heart;
import static funktionen.kiss.time;
import game.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import static starlight.Server.countChars;
import tools.Action;
import tools.KCodeParser;
import tools.ModuleCreator;
import tools.PacketCreator;
import tools.Toolbar;
import tools.VoteBox;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Panel;
import tools.popup.Popup;

public class Channel {
    private final List<Client> clients;
    private List<Client> fiftyPlayers;
	private String name,topic,backgroundImage;
    private Game game;
     private Features features;
  private String featuresname;
    private long pfTime, newVoteEnd;
    private boolean moderated, butlerMuted, pfActive;
    private int holrunde,holjackpot,loginMessage, newVoteUser, butlerMute, maxTochter, juSchu,photos, cmk, visible, minage, minstammimonths, minjumpopoints, minquizpoints, minholpoints, minknuddels, mingender, showGender, showAge, specialEvent, size, id, temp, category, minRank, pfStatus;
	private String holnick,info, cmutes="", mutes="", cls="", tochter = "", hz, vips = "", mods = "", cms = "", mcms = "", gameName ="", bannedFunctions, password, pfStarter, owner, pfSpieler = "";
    private byte backgroundPosition;
    private ChannelStyle style;
	private String timeString = new SimpleDateFormat("dd.MM").format(new Date());
	public Map<Long, String[]> history;
    public HashMap<String, Integer> newVoteAnswers;
    public List<String> newVoteNicks;
    
     private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }
    
    
    
    public Channel(ResultSet rs, ChannelStyle style) throws SQLException {
        history = new HashMap<Long, String[]>();
        newVoteAnswers = new HashMap<String, Integer>();
        clients = new ArrayList<Client>();
        fiftyPlayers = new ArrayList<Client>();
        name = rs.getString("name");
        holnick = rs.getString("holnick");
        holrunde = rs.getInt("holrunde");
        holjackpot = rs.getInt("holjackpot");
        newVoteNicks = new ArrayList<String>();
        butlerMute = rs.getInt("butlerMute");
        maxTochter = rs.getInt("maxTochter");
        loginMessage = rs.getInt("loginMessage");
        tochter = rs.getString("tochter");
        cms = rs.getString("cms");
        mcms = rs.getString("mcms");
        hz = rs.getString("hz");
        juSchu = rs.getInt("juschu");
        cmutes = rs.getString("cmutes");
        cls = rs.getString("cls");
        
        
        // hier sehen wir die methoden die er bereits kennt.. Die wir importiert haben! ALso die DB FELDER werden hier eingetragen die in der DB existieren
        // ergo muss er das Feld Game kennen, wahrscheinlich heißt es nur anders.. wir schauen mal.
        mutes = rs.getString("mutes");
        photos = rs.getInt("photos");
        cmk = rs.getInt("cmk");
        category = rs.getInt("category");
        size = rs.getInt("size");
        id = rs.getInt("id");
        showAge = rs.getInt("showAge");
        bannedFunctions = rs.getString("bannedFunctions");
        showGender = rs.getInt("showGender");
        visible = rs.getInt("visible");
        minage = rs.getInt("minage");
        mingender = rs.getInt("mingender");
        minstammimonths = rs.getInt("minstammimonths");
        minjumpopoints = rs.getInt("minjumpopoints");
        minquizpoints = rs.getInt("minquizpoints");
        minholpoints = rs.getInt("minholpoints");
        minknuddels = rs.getInt("minknuddels");
        topic = rs.getString("topic");
        password = rs.getString("password");
        owner = rs.getString("owner");
        specialEvent = rs.getInt("specialEvent");
        minRank = rs.getInt("minrank");
        temp = rs.getInt("temp");
        info = rs.getString("info");
        gameName = rs.getString("game");
        
        

        if (gameName == null) {
            game = null;
        
        } else if (gameName.equals("MAFIA")) {
            game = new Mafia(this);
        } else if (gameName.equals("FIFTY")) {
            game = new Fifty(this);
        } else if (gameName.equals("QUIZ")) {
            game = new Quiz(this);
        } else if (gameName.equals("HIGH OR LOW")) {
            game = new HighOrLow(this);
        } else if (gameName.equals("MIX")) {
            game = new Mix(this);
        } else if (gameName.equals("TRANSLATE")) {
            game = new Translate(this);
        } else if (gameName.equals("MATHE")) {
            game = new Mathe(this);
        } else if (gameName.equals("WORDMIX")) {
            game = new WordMix(this);
        } else if (gameName.equals("BAD6")) {
            game = new Bad6(this);
        }else if (gameName.equals("WORDMIX NEWBY")) {
            game = new WordMixNewby(this);
        } else if (gameName.equals("WORDMIX ENGLISH")) {
            game = new WordMixEnglish(this);  
        } else if (gameName.equals("JUMPO")) {
            game = new Jumpo(this);
         }else if (gameName.equals("GUESSTHENUMBER")) {
            game = new GuessTheNumber(this);
         }else if (gameName.equals("QUIZ2")) {
            game = new Quiz2(this);
         } else if (gameName.equals("JUMPO PRO")) {
            game = new JumpoPro(this);
      //  } else if (name.equals("Blitz! Free")) {
        //    game = new BlitzFree(this);
        //} else if (name.equals("Blitz!")) {
          //  game = new Blitz(this);
        } else if(name.equals("DiceMaster")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 2Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 5Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 10Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 25Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 50Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 100Kn")) {
            game = new DiceMaster(this);
                                 
        }

        backgroundImage = rs.getString("backgroundImage");
        backgroundPosition = rs.getByte("backgroundPosition");
        this.style = style;
    }
    
    public static void CopyChannel(String name, int tochterid) {
    	int loginMessage = 1;
        String channelname = String.format("%s %s", name, tochterid),
        		backgroundImage = "", 
        		backgroundPosition = "",style = "",info = "",owner = "",size = "",topic = "",password = "",bannedFunctions = "",cms = "",mcms = "",hz = "",temp = "",minrank = "", category = "",specialEvent = "",showGender = "",showAge = "",minage = "",mingender = "",visible = "",minstammimonths = "",minjumpopoints = "",minquizpoints ="",minholpoints = "",minknuddels = "",cmk = "",photos = "",juschu = "",maxTochter = "";
        
        PoolConnection pcon3 = ConnectionPool.getConnection();
        PreparedStatement ps3 = null;
        
        try {
        	Connection con3 = pcon3.connect();
        	ps3 = con3.prepareStatement("SELECT * FROM channels where name = ? limit 1");
        	ps3.setString(1, name);
        	ResultSet rs3 = ps3.executeQuery();
          
        	if(rs3.next()) {
        		backgroundImage = rs3.getString("backgroundImage");
        		backgroundPosition = rs3.getString("backgroundPosition");
        		style = rs3.getString("style");
        		size = rs3.getString("size");
        		topic = rs3.getString("topic");
        		info = rs3.getString("info");
        		owner = rs3.getString("owner");
        		password = rs3.getString("password");
        		bannedFunctions = rs3.getString("bannedFunctions");
        		cms = rs3.getString("cms");
                        mcms = rs3.getString("mcms");
        		hz = rs3.getString("hz");
        		showAge = rs3.getString("showAge");
        		visible = rs3.getString("visible");
        		loginMessage = rs3.getInt("loginMessage");
        		temp = rs3.getString("temp");
        		minrank = rs3.getString("minrank");
        		category = rs3.getString("category");
        		specialEvent = rs3.getString("specialEvent");
        		showGender = rs3.getString("showGender");
        		minage = rs3.getString("minage");
        		mingender = rs3.getString("mingender");
        		minstammimonths = rs3.getString("minstammimonths");
                        minjumpopoints = rs3.getString("minjumpopoints");
                        minquizpoints = rs3.getString("minquizpoints");
                        minholpoints = rs3.getString("minholpoints");
                        minknuddels = rs3.getString("minknuddels");
        		cmk = rs3.getString("cmk");
        		photos = rs3.getString("photos");
        		juschu = rs3.getString("juschu");
        		maxTochter = rs3.getString("maxTochter");
        	}
        } catch (SQLException e) {
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
    
        Server.get().query(String.format("INSERT INTO `channels` set `topic`=%s,`size`='%s',`name`='%s', `backgroundImage` = '%s', `backgroundPosition` = '%s', `style` = '%s', `info` = '%s', `owner` = '%s', `password` = '%s', `bannedFunctions` = '%s', `cms` = '%s', `mcms` = '%s', `hz` = '%s', `tochter` = '%s', `game` = NULL, `showAge` = '%s', `visible` = '%s', `temp` = '%s', `minrank` = '%s', `category` = '%s', `specialEvent` = '%s', `showGender` = '%s', `minage` = '%s', `mingender` = '%s', `minstammimonths` = '%s', `minjumpopoints` = '%s', `minquizpoints` = '%s', `minholpoints` = '%s', `minstammimonths` = '%s', `cmk` = '%s', `photos` = '%s', `juschu` = '%s', `maxTochter` = '%s',loginMessage='%s'", topic==null?"NULL":"'"+topic+"'", size, channelname, backgroundImage, backgroundPosition, style,info,owner,password,bannedFunctions,cms,mcms,hz,name,showAge,visible,temp,minrank,category,specialEvent,showGender,minage,minstammimonths,minjumpopoints,minquizpoints,minholpoints,minknuddels,mingender,cmk,juschu,photos,maxTochter,loginMessage));
    
    
        PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;
       
        try {
        	Connection con = pcon.connect();
        	ps = con.prepareStatement("SELECT * FROM channels where name = ?");
        	ps.setString(1, channelname);
        	ResultSet rs = ps.executeQuery();
          
        	if (rs.next()) {
        		Map<Integer, ChannelStyle> channelStyles = new HashMap<Integer, ChannelStyle>();
    
        		PoolConnection pcon2 = ConnectionPool.getConnection();
        		PreparedStatement ps2 = null;
        		
        		try {
        			Connection con2 = pcon2.connect();
        			ps2 = con2.prepareStatement("SELECT * FROM channelstyles");
        			ResultSet rs2 = ps2.executeQuery();
             
        			while (rs2.next())
        				channelStyles.put(Integer.valueOf(rs2.getInt("id")), new ChannelStyle(rs2));
        		} catch (SQLException e)
        		{
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
    
            Channel channel2 = new Channel(rs, (ChannelStyle)channelStyles.get(Integer.valueOf(rs.getInt("style"))));
            Server.get().butler.joinChannel(channel2);
           channel2.addClient(Server.get().butler);
            Server.channels.put(rs.getString("name").toLowerCase(), channel2);
          }
        }
        catch (SQLException e)
       {
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
    }

    
    public String getGameName() {
    	return gameName;
    }
    
    public void setGame(String gameName) {
      
        if (gameName.isEmpty()) {
      this.game = null;
    }  else if (gameName.equals("MAFIA")) {
            game = new Mafia(this);
        } else if (gameName.equals("FIFTY")) {
            game = new Fifty(this);
        } else if (gameName.equals("QUIZ")) {
            game = new Quiz(this);
        } else if (gameName.equals("HIGH OR LOW")) {
            game = new HighOrLow(this);
        } else if (gameName.equals("MIX")) {
            game = new Mix(this);
        } else if (gameName.equals("TRANSLATE")) {
            game = new Translate(this);
        } else if (gameName.equals("MATHE")) {
            game = new Mathe(this);
        } else if (gameName.equals("WORDMIX")) {         
            game = new WordMix(this);
        } else if (gameName.equals("BAD6")) {         
            game = new Bad6(this);
        } else if (gameName.equals("WORDMIX NEWBY")) {
            game = new WordMixNewby(this);
        } else if (gameName.equals("WORDMIX ENGLISH")) {
            game = new WordMixEnglish(this);  
        } else if (gameName.equals("JUMPO")) {
            game = new Jumpo(this);
         }else if (gameName.equals("GUESSTHENUMBER")) {
            game = new GuessTheNumber(this);
         }else if (gameName.equals("QUIZ2")) {
            game = new Quiz2(this);
         } else if (gameName.equals("JUMPO PRO")) {
            game = new JumpoPro(this);
      //  } else if (name.equals("Blitz! Free")) {
        //    game = new BlitzFree(this);
        //} else if (name.equals("Blitz!")) {
          //  game = new Blitz(this);
        } else if(name.equals("DiceMaster")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 2Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 5Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 10Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 25Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 50Kn")) {
            game = new DiceMaster(this);
        } else if (name.equals("DiceMaster 100Kn")) {
            game = new DiceMaster(this);
                                 
        }
        
          Server.get().query(String.format("UPDATE `channels` SET `game` = '%s' WHERE `name` = '%s'", gameName, name));
            this.gameName = gameName;
            
    }
    public boolean getPfActive() {
    	return pfActive;
    }
    
    public String getHZ() {
    	return hz;
    }
    
    public String getTochter() {
    	return tochter;
    }
    
    
        public String getHolnick() {
		return holnick;
	}

	public void setHolnick(String holnick) {
		this.holnick = holnick;
		Server.get().query(String.format("UPDATE `channels` SET `holnick` = '%s' where name='%s'", holnick,name));
	}
        
	public int getHolrunde() {
		return holrunde;
	}

	public void setHolrunde(int holrunde) {
		this.holrunde = holrunde;
		Server.get().query(String.format("UPDATE `channels` SET `holrunde` = '%s' where name='%s'", holrunde,name));
	}

	public int getHoljackpot() {
		return holjackpot;
	}

	public void setHoljackpot(int holjackpot) {
		this.holjackpot = holjackpot;
		Server.get().query(String.format("UPDATE `channels` SET `holjackpot` = '%s' where name='%s'", holjackpot,name));
	}

        public void increaseHoljackpot(int holjackpot) {
		this.holjackpot += holjackpot;
		Server.get().query(String.format("UPDATE channels SET holjackpot=holjackpot+'%s' where name='%s'", holjackpot,name));
	}

        
    public int getMaxTochter() {
    	return maxTochter;
    }

	public void setHZ(String hz) {
    	this.hz = hz;
    	
    	Server.get().query(String.format("UPDATE `channels` SET `hz` = '%s' WHERE `name` = '%s'", hz, name));
    }
    
    public void setPfActive(boolean pfActive) {
    	this.pfActive = pfActive;
    }

    public void addHistory(String nick, String message, boolean doppelpunkt) {
    	long time = System.currentTimeMillis()/1000;
    	String date = Server.get().timeStampToDate(time).replace(".", "-");
    	String tim = Server.get().timeStampToDateLong(time);
    	history.put(time, new String[] {nick, message}); // für /his    
    }
    
    
    public int getSpecialEvent() {
		return specialEvent;
	}
    
    
    
    public void setLoginMessage(int loginMessage) {
		this.loginMessage = loginMessage;
		
		Server.get().query(String.format("UPDATE `channels` SET `loginMessage` = '%s' WHERE `name` = '%s'", loginMessage, name));
	}

	public int getPhotos() {
    	return photos;
    }
    
    public String getCms() {
    	return cms;
    }
    
   public String getMcms() {
    	return mcms;
    }

	public void setShowGender(int showGender) {
		this.showGender = showGender;
		Server.get().query(String.format("UPDATE `channels` SET `showGender` = '%s' WHERE `name` = '%s'", showGender, name));
	}

	public void setShowAge(int showAge) {
		this.showAge = showAge;
		Server.get().query(String.format("UPDATE `channels` SET `showAge` = '%s' WHERE `name` = '%s'", showAge, name));

	}

	public int getCmk() {
    	return cmk;
    }
    
    public int butlerMute() {
    	return butlerMute;
    }
    
   
    
    public boolean checkCm(String name) {
    	return cms.contains(String.format("|%s|", name));
    }
    
   public boolean checkMcm(String name) {
    	return mcms.contains(String.format("|%s|", name));
    }
    
    
    public boolean checkVip(String name) {
    	return vips.contains(String.format("|%s|", name));
    }
    
    public boolean checkMod(String name) {
    	return mods.contains(String.format("|%s|", name));
    }

	public boolean isVisible() {
    	return visible == 1;
    }
    
        
    public void setVisible (int visible) {
    	this.visible = visible;
    	Server.get().query(String.format("UPDATE `channels` SET `visible` = '%s' WHERE `name` = '%s'", visible, name));
    }

	public void setSpecialEvent(int specialEvent) {
		this.specialEvent = specialEvent;
		
		Server.get().query(String.format("UPDATE `channels` SET `specialEvent` = '%s' WHERE `name` = '%s'", specialEvent, name));
	}
        
        public void setJuSchu(int juSchu) {
		this.juSchu = juSchu;
		
		Server.get().query(String.format("UPDATE `channels` SET `juschu` = '%s' WHERE `name` = '%s'", juSchu, name));
	}
        
        public void setPhotos(int photos) {
		this.photos = photos;
		
		Server.get().query(String.format("UPDATE `channels` SET `photos` = '%s' WHERE `name` = '%s'", photos, name));
	}

	public void setCmk(int cmk) {
		this.cmk = cmk;
		
		Server.get().query(String.format("UPDATE `channels` SET `cmk` = '%s' WHERE `name` = '%s'", cmk, name));
	}

	public String getCmutes() {
		return cmutes;
	}

	public void setCmutes(String cmutes) {
		this.cmutes = cmutes;
		
    	Server.get().query(String.format("UPDATE `channels` SET `cmutes` = '%s' WHERE `name` = '%s'", cmutes, name));
	}

	public long getNewVoteEnd() {
		return newVoteEnd;
	}

	public void setNewVoteEnd(long newVoteEnd) {
		this.newVoteEnd = newVoteEnd;
	}

	public int getNewVoteUser() {
		return newVoteUser;
	}

	public void setMinstammimonths(int minstammimonths) {
		this.minstammimonths = minstammimonths;
		Server.get().query(String.format("UPDATE `channels` SET `minstammimonths` = '%s' WHERE `name` = '%s'", minstammimonths, name));
	}
        
        public void setMinjumpopoints(int minjumpopoints) {
		this.minjumpopoints = minjumpopoints;
		Server.get().query(String.format("UPDATE `channels` SET `minjumpopoints` = '%s' WHERE `name` = '%s'", minjumpopoints, name));
	}
        
        public void setMinquizpoints(int minquizpoints) {
		this.minquizpoints = minquizpoints;
		Server.get().query(String.format("UPDATE `channels` SET `minquizpoints` = '%s' WHERE `name` = '%s'", minquizpoints, name));
	}
        
        public void setMinholpoints(int minholpoints) {
		this.minholpoints = minholpoints;
		Server.get().query(String.format("UPDATE `channels` SET `minholpoints` = '%s' WHERE `name` = '%s'", minholpoints, name));
	}
        
        public void setMinknuddels(int minknuddels) {
		this.minknuddels = minknuddels;
		Server.get().query(String.format("UPDATE `channels` SET `minknuddels` = '%s' WHERE `name` = '%s'", minknuddels, name));
	}

	public void setNewVoteUser(int newVoteUser) {
		this.newVoteUser = newVoteUser;
	}

	public String getMutes() {
		return mutes;
	}

	public void setMutes(String mutes) {
		this.mutes = mutes;
		
    	Server.get().query(String.format("UPDATE `channels` SET `mutes` = '%s' WHERE `name` = '%s'", mutes, name));
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
		
    	Server.get().query(String.format("UPDATE `channels` SET `cls` = '%s' WHERE `name` = '%s'", cls, name));
	}

	public void setCms(String cms) {
		this.cms = cms;
		
		Server.get().query(String.format("UPDATE `channels` SET `cms` = '%s' WHERE `name` = '%s'", cms, name));
	}
        
        public void setMcms(String mcms) {
		this.mcms = mcms;
		
		Server.get().query(String.format("UPDATE `channels` SET `mcms` = '%s' WHERE `name` = '%s'", mcms, name));
	}
	
	public int getLcStammis() {
		int lala = 0;
		
		PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;

        try {
            Connection con = pcon.connect();
            ps = con.prepareStatement("select count(name) as x from accounts where lc=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                lala = rs.getInt("x");
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
        
        return lala;
	}

    public int getTemp() {
    	return temp;
    }
    
    public void setTemp(int temp) {
    	this.temp = temp;
    }
    
    public int getLoginMessage() {
		return loginMessage;
	}

	public int getID() {
    	return id;
    }
    
    public void setID(int id) {
    	this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getOwner() {
    	return owner;
    }
    
    public int getShowGender() {
		return showGender;
	}

	public int getShowAge() {
		return showAge;
	}

	public void setOwner(String owner) {
    	this.owner = owner;
    }

    public int getSize() {
        return size;
    }
    
    public void setSize(int i) {
    	this.size = i;
    
    	Server.get().query(String.format("UPDATE `channels` SET `size` = '%s' WHERE `name` = '%s'", i, name));
    }

    public long getPfTime() {
		return pfTime;
	}
    
    public int getMinage() {
    	return minage;
    }

	public void setPfTime(long pfTime) {
		this.pfTime = pfTime;
	}

	public int getPfStatus() {
		return pfStatus;
	}
	
	public boolean checkHz(String nick) {
		if(getHZ().contains(String.format("|%s|", nick))) {
			return true;
		}
			
		return false;
	}

	public void setPfStatus(int pfStatus) {
		this.pfStatus = pfStatus;
	}

	public String getBannedFunctions() {
		return bannedFunctions;
	}

	public void setBannedFunctions(String bannedFunctions) {
		this.bannedFunctions = bannedFunctions;
		
		Server.get().query(String.format("UPDATE `channels` SET `bannedFunctions` = '%s' WHERE `name` = '%s'", bannedFunctions, name));
	}

	public String getPfSpieler() {
		return pfSpieler;
	}

	public int getJuSchu() {
		return juSchu;
	}
        
        public int getVisible() {
		return visible;
	}

	public String getVips() {
		return vips;
	}

	public void setVips(String vips) {
		this.vips = vips;
	}

	public String getMods() {
		return mods;
	}

	public void setMods(String mods) {
		this.mods = mods;
	}

	public void setPfSpieler(String pfSpieler) {
		this.pfSpieler = pfSpieler;
	}

	public String getPfStarter() {
		return pfStarter;
	}

	public void setPfStarter(String pfStarter) {
		this.pfStarter = pfStarter;
	}

	public int getMinRank() {
		return minRank;
	}
    
    public int getCategory() {
    	return category;
    }

	public void setMinRank(int minRank) {
		this.minRank = minRank;
		
		Server.get().query(String.format("UPDATE `channels` SET `minrank` = '%s' WHERE `name` = '%s'", minRank, name));
	}
	
	public String getPassword() {
		return password;
	}

	public String getInfo() {
        return info;
    }
	
	public void setInfo(String info) {
		this.info = info;
		
		Server.get().query(String.format("UPDATE `channels` SET `info` = '%s' WHERE `name` = '%s'", info.replace("'", "\\'"), name));
	}
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        if (topic != null) {
            for (Client client : getClients()) {
            	if(client != Server.get().getButler()) {
            		client.sendButlerMessage(name, String.format("°BB°_Dieser Channel hat folgendes Thema:°r°#%s", topic));
            	}
            }
        }

        this.topic = topic;
        
        if(topic == null) {
        	Server.get().query(String.format("UPDATE `channels` SET `topic` = NULL WHERE `name` = '%s'", name));
        } else {
            Server.get().query(String.format("UPDATE `channels` SET `topic` = '%s' WHERE `name` = '%s'", topic.replace("'", "\\'"), name));
        }
    }

    public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

          public void setFeatures(String a) {
    if (a == null || a.isEmpty()) {
      this.features = null;
    } else if (a.equals("GREEDY")) {
      this.features = new GreedyPig(this);
    }
          }
	public Game getGame() {
        return game;
    }

         public Features getFeatures() {
      return this.features;
  }
 public void setFeaturesname(String a) {
      featuresname= a;
  }
  public String getFeaturesname() {
      return featuresname;
  }
  
    public boolean butlerMuted() {
        return butlerMuted;
    }
    
    public boolean getButlerMuted() {
        return butlerMuted;
    }
    
    public void setButlerMuted(boolean butlerMuted) {
        this.butlerMuted = butlerMuted;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public int getMinstammimonths() {
		return minstammimonths;
	}
    
    public int getMinjumpopoints() {
		return minjumpopoints;
	}
    
    public int getMinquizpoints() {
		return minquizpoints;
	}
    
    public int getMinholpoints() {
		return minholpoints;
	}
    
    public int getMinknuddels() {
		return minknuddels;
	}

	public int getMingender() {
		return mingender;
	}

	public byte getBackgroundPosition() {
        return backgroundPosition;
    }

    public ChannelStyle getStyle() {
        return style;
    }

    
    
    public void broadcastMessage(String message, Client client, boolean fromGame) {
        if (!fromGame && game != null && !game.parsePublicMessage(message, client)) {
            return;
        }
         client.setLevelInfo("a",1);
        message = KCodeParser.parse(client, message, 15, 10, 20);
        message = Server.get().parseSmileys(client, message);

        if (message.isEmpty()) {
            return;
        }

        String msg = PacketCreator.publicMessage(client.getName(), name, message);
        if (!client.getNickTag().isEmpty()) {
     msg = PacketCreator.publicPicMessage(client.getName(), this.name, message,"°>features/nicktag/nicktag-smiley_"+client.getNickTag()+"...b.gif<°");
}
        for (Client c : getClients()) {
        	if(c != Server.get().getButler()) {
        		if(!c.checkIgnored(client.getName())) {
        			c.send(msg);
        		}
        	}
        }
         addHistory(client.getName(), message, true); 
       
    }

    public void broadcastButlerMessage(String message) {
        if(!butlerMuted()) {
           try {
            for (Client c : getClients()) {
            	if(c != Server.get().getButler()) {
            		c.send(PacketCreator.publicMessage(Server.get().getButler().getName(), name, message));
            	}	
            }        
         }  catch (ConcurrentModificationException ex) {
   for (Client c : getClients()) {
            	if(c != Server.get().getButler()) {
            		c.send(PacketCreator.publicMessage(Server.get().getButler().getName(), name, message));
            	}	
            } 
         }
}
    }

    public void broadcastAction(String sender, String message) {
        for (Client c : getClients()) {
        	if(c != Server.get().getButler()) {
        		c.send(PacketCreator.action(sender, name, message));
        	}
        }
         if(!sender.equalsIgnoreCase(Server.get().getButler().getName())) {
        	addHistory(sender, message, false); // his
        }
    
    }
     
       
    public void broadcastPicAction(String sender, String message, String pic) {
        for (Client c : getClients()) {
        	if(c != Server.get().getButler()) {
        		c.send(PacketCreator.picAction(sender, name, message, pic));
        	}
        }
    }

    public int countClients() {
        int count = getClients().size();

        if (count > size) {
            count = size;
        }

        return count;
    }

    public List<Client> getClients() {
        synchronized (clients) {
            return clients;
        }
    }

    public void addClient(Client client) {
        synchronized (clients) {
            clients.add(client);
        }
    }

    public List<Client> getFiftyPlayers() {
        synchronized (clients) {
            return fiftyPlayers;
        }
    }

    public void addFiftyPlayer(Client client) {
        synchronized (clients) {
            fiftyPlayers.add(client);
        }
    }

    public void join(Client client) {
        client.send(PacketCreator.userList(this, client));
        client.send(ModuleCreator.ACTIVATE_FUNCTIONS());
        addClient(client);
  
     
        
        /* KnuddelsCent by Chiller */
         if (client.getDailysend() == 0 && (client.getDailybonus() == 0)) {
         client.setDailysend(1);
         new Action(client.getName(), Action.ActionType.TAGESLOGIN, 10, null, null, 0);
    }
        /* KnuddelsCent ENDE */
        
         if (client.getTutorialsend() == 0) {
         client.setTutorialsend(1);
         new Action(client.getName(), Action.ActionType.TUTORIALLOGIN, 10, null, null, 0);
    }  
         
         
        //Gold
        //client.send(Toolbar.FotoContestStyle(name));
        
        //blau
        //client.send(Toolbar.SmileyWarsBetaStyle(name));
        
        // hellblau
        //client.send(Toolbar.BingoStyle(name));
        
        //rot
        //client.send(Toolbar.FotoContestFlirtStyle(name));
                
           if(name.equals("High or Low")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/hol start"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
            
         } else if(name.equals("Blitz!")) {
            List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            client.send(Toolbar.ToolbarString(name, buttons));
            client.send(Toolbar.BingoStyle(name));
            
        } else if(name.equals("Blitz! Free")) {
            List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "mychannel/ico_dice-6_001.gif", true));  
            client.send(Toolbar.ToolbarString(name, buttons));
            client.send(Toolbar.BingoStyle(name));
            
        } else if(name.equals("High or Low Pro")) {
            List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/hol start"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
            } else if(name.equals("High or Low Champion")) {
            List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/hol start"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
            } else if(name.equals("High or Low Free")) {
            List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/hol start"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
       } else if(name.startsWith("DiceMaster")) {
            List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("1 Wurf", "/dm 1", "sm_classic_00.gif", true));
            buttons.add(new Toolbar.Button("2 Würfe", "/dm 2", "sm_classic_00.gif", true));
            buttons.add(new Toolbar.Button("5 Würfe", "/dm 5", "sm_classic_00.gif", true));
            buttons.add(new Toolbar.Button("10 Würfe", "/dm 10", "sm_classic_00.gif", true));
            buttons.add(new Toolbar.Button("25 Würfe", "/dm 25", "sm_classic_00.gif", true));
            buttons.add(new Toolbar.Button("Spiel-Info", "/h dicemaster", "mychannel/ice_dice-6_001.gif", false));
            client.send(Toolbar.ToolbarString(name, buttons));
        } else if(name.equals("Jumpo")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" jumpo","", false));
            buttons.add(new Toolbar.Button("Hilfe", "/h Jumpo","",false));
            buttons.add(new Toolbar.Button("Links", "/tf-insert links"));
            buttons.add(new Toolbar.Button("Runter", "/tf-insert runter"));
            buttons.add(new Toolbar.Button("Hoch", "/tf-insert hoch "));
            buttons.add(new Toolbar.Button("Rechts", "/tf-insert rechts"));
            
            
            
            
            
            
            client.send(Toolbar.BingoStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
            
            } else if(name.equals("Music")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Play", "/sound sounds/your-love.mp"));
            buttons.add(new Toolbar.Button("Hilfe", "/h music"));
            client.send(Toolbar.BingoStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
       
   /*     } else if(name.equals("Flirt")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
           buttons.add(new Toolbar.Button("Profil", "/w", "w2/edit_menu_grunddaten.png", true));
           buttons.add(new Toolbar.Button("Freunde", "/f", "icon_specialEventChannel.gif", true));
           buttons.add(new Toolbar.Button("Edit", "/e", "edit.gif", true));
           buttons.add(new Toolbar.Button("Shop", "/shop", "present.gif", true));
            client.send(Toolbar.FlirtStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        */
            
            } else if(name.equals("Welcome!")) {
           List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
           buttons.add(new Toolbar.Button("Profil", "/w", "w2/edit_menu_grunddaten.png", true));
           buttons.add(new Toolbar.Button("Freunde", "/f", "icon_specialEventChannel.gif", true));
           buttons.add(new Toolbar.Button("Edit", "/e", "edit.gif", true));
           buttons.add(new Toolbar.Button("Shop", "/shop", "present.gif", true));
            client.send(Toolbar.BingoStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        
            
            
        } else if(name.equals("Jumpo Pro")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" jumpo"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Jumpo"));
            client.send(Toolbar.BingoStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        } else if(name.equals("WordMix")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" mix"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Wordmix"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
             } else if(name.equals("Mix")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" mix"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Mix"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
             } else if(name.equals("Translate")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" translate"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Translate"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
             } else if(name.equals("Mathe")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" mathe"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Mathe"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
             } else if(name.equals("Quiz Solo")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" quiz"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Quiz"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
            } else if(name.equals("Quiz")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Hilfe", "/h Quiz"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
            } else if(name.equals("Guess")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Hilfe", "/h Guess"));
            client.send(Toolbar.SmileyWarsBetaStyle(name));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        } else if(name.equals("WordMix Family")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" mix"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Wordmix"));
            client.send(Toolbar.ToolbarString(name, buttons));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        } else if(name.equals("WordMix Newby")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" mix"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Wordmix"));
            client.send(Toolbar.ToolbarString(name, buttons));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        } else if(name.equals("WordMix English")) {
        	List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
            buttons.add(new Toolbar.Button("Starten", "/a "+Server.get().getButler().getName().toLowerCase()+" mix"));
            buttons.add(new Toolbar.Button("Hilfe", "/h Wordmix"));
            client.send(Toolbar.ToolbarString(name, buttons));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
            client.send(Toolbar.ToolbarString(name, buttons));
        }/* else {
                List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
           buttons.add(new Toolbar.Button("Profil", "/w", "w2/edit_menu_grunddaten.png", true));
           buttons.add(new Toolbar.Button("Freunde", "/f", "icon_specialEventChannel.gif", true));
           buttons.add(new Toolbar.Button("Edit", "/e", "edit.gif", true));
           buttons.add(new Toolbar.Button("Shop", "/shop", "present.gif", true));
           
           client.send(Toolbar.ToolbarString(name, buttons));
            if(checkCm(client.getName()) || checkHz(client.getName()) || client.getRank() > 4)
            {
               buttons.add(new Toolbar.Button("Mutes", "/mute", "cm.png", false));
               buttons.add(new Toolbar.Button("CLs", "/cl", "cm.png", false));
            } else {
               buttons.add(new Toolbar.Button("Infos", "/info", "", false));
            }
               client.send(Toolbar.ToolbarString(name, buttons));
            }
        
        */
        if(newVoteEnd != 0) {
        	client.send(VoteBox.createVoteBox(this, "Befragung", new VoteBox.Button("Vote", "1.gif", String.format("/nvote %s|%s", "Hehe", name), true), newVoteAnswers, 100));
        }
        
        if(name.equals("Fussball")) {
    		client.send(VoteBox.createSoccerBox(name));
    		client.send(VoteBox.setVoteBoxLog(name, "°>{bgimage}pics/soccerbox/soccerbox_ticker-bg.png<12W°#°+9018°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Guten Abend liebe Kicker-Fans! Auf geht's zum letzten Spieltag in der EM-Gruppe B!°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Für gute Unterhaltung ist im Channel \"Fussball\" gesorgt, der von Fans und Moderatoren live \"\"unterhalten\"\" wird.#°!°Die wichtigsten \"Gamefacts\" notieren wir hier in der Soccerbox.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Auch die Jungs und Mädelz im Channel \"\"Fußball\"\" sind wieder startbereit.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°1.°>{tc}<°°>{tc}<°°>LEFT<°°>soccerbox/whistle_shine...w_18.b.my_5.mx_-7.h_10.png<° _Anpfiff_°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°8.°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°In den ersten 8 Minuten lässt sich neben 2 Torchancen von T. Müller erkennen, dass die Deutschen mit Ballbesitz und Spielfluss überlegen sind.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Und wenn hier nichts passiert, dann wenigstens im anderen Gruppenspiel. Die Niederlande gehen 0-1 gegen Portugal in Führung.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°15.°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Bisher hat noch niemand den Zünder von \"Danish Dynamite\" gefunden. Ein Distanzschuß unserer Nachbarn steht in der ersten Viertelstunde zu Buche.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°19.°>{tc}<°°>{tc}<°°>LEFT<°°>soccerbox/soccerball_shine...w_18.b.my_5.mx_-5.h_10.png<°_Tor_ - Für Deutschland zum 0:1 durch Podolski!°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°19.°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Na also! Da haben die Deutschen den Abwehrknoten gesprengt. Einwurf, Müller behauptet den Ball, Gomez verlängert und Poldi drischt das Leder in das Netz.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Und natürlich feiert der ganze NetChat mit einer kräftigen _/laola_ - auf gehts!°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°24.°>{tc}<°°>{tc}<°°>LEFT<°°>soccerbox/soccerball_shine...w_18.b.my_5.mx_-5.h_10.png<°_Tor_ - Für Dänemark zum 1:1 durch Krohn-Dehli!°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°24.°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Da ging das Pulverfass aber schreckhaft hoch! Nach einer Dänen-Ecke steigt Bendtner hoch und in der Mitte köpft Krohn-Dehli zum 1-1 ein.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Die Portugiesen machen es diesem Spiel gleich. Dort steht es nun auch 1-1.°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°34.°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Mehr als 30 Minuten sind um und die Angriffslaune der Deutschen ist nicht gebrochen. Aber auch Dänemark wittert nun Morgenluft...°rbW12°§°>{endtable}<°#°2°#°12°°+9004>{table|40|3|180}<°°>RIGHT<°36.°>{tc}<°°>{tc}<°°>LEFT<°°[211,170,115]°Freistoß für Deutschland... keine 18 Meter vor dem Kasten.°rbW12°§°>{endtable}<°", "°>{globalopacity}100<°°>LEFT<°#°>soccerbox/soccerbox_ticker-bg...w_0.h_0.mx_-5.png<°°>soccerbox/soccerbox_content-bg...w_0.h_0.mx_-5.my_103.jpg<°°>soccerbox/soccerbox_penalty-underlay-notext...w_0.h_0.my_153.mx_-5.png<°°>soccerbox/hbar_white...w_0.h_0.my_26.mx_-5.png<°°>soccerbox/hbar_white...w_0.h_0.my_171.mx_-5.png<°#°14W+9012>CENTER<°_EM 2012 - LiveTicker°bK°#°+9001[211,170,115]12°Moderiert von Ramnip#°12W+9527°_Gruppe B - 3. Spieltag°b°#°14+9508°_Dänemark - Deutschland°b°#°30+9509°°>soccerbox/flag_denmark...w_21.b.my_-3.gif<° _1:1 °>soccerbox/flag_deutschland...w_21.b.my_-3.gif<°°b°#°11+9516°1. Halbzeit#°+9060°"));
        }
        
        Random zufall = new Random();
        long systemtime = System.currentTimeMillis()/1000;
        StringBuilder m = new StringBuilder();
        StringBuilder fo = new StringBuilder();
        StringBuilder so = new StringBuilder();
        int lol = 1, komma = 1, komma2 = 1, xd = 0;
        
        for (Client c : getClients()) {
            
            
            
            c.send(PacketCreator.addUser(this, client, c, c == client));
        }

        client.setTimeOut(systemtime);
        
        if(client.getMentor().isEmpty() && client.getRank() < 1) {
        	List<String> list = new ArrayList<String>();
        	for(Channel s : Server.get().getChannels()) {
        		for(Client x : s.getClients()) {
        			if(x != Server.get().getButler() && x.hasPermission("getmentee") && !x.getIPAddress().equals(client.getIPAddress())) {
        				list.add(x.getName());
        			}
        		}
        	}
        	
    		if(list.size() > 0) {
    			Client men = Server.get().getClient(list.get(zufall.nextInt(list.size())));
    			Popup popup = new Popup("Mentorschaft?", "Mentorschaft?", String.format("Gerade eben hat sich °B°_%s_§ (%s)°>%smale.png<° neu registriert. Du wurdest ausgesucht, um %s als erster Gesprächspartner freundlich zu begrüßen und kennenzulernen.##Alle Informationen zu diesem _Mentorprogramm_, bei dem als höchste Belohnung eine Ehrenmitgliedschaft winkt, erhälst du durch die Eingabe von _/h mentor_.##Möchtest Du nun %s helfen?",  client.getName().replace("<", "\\<"), client.getAge(), client.getGender() == 1 ? "" : "fe", client.getName().replace("<", "\\<"), client.getName().replace("<", "\\<")), 450, 250);
    			popup.setButtonFontSize(16);
    			Panel panel = new Panel();
    			Button buttonMessage2 = new Button("Nein, momentan nicht");
                        buttonMessage2.setStyled(true);
    			panel.addComponent(buttonMessage2);
    			buttonMessage2.enableAction();
    			Button buttonMessage = new Button("Ja");
                        buttonMessage.setStyled(true);
    			panel.addComponent(buttonMessage);
    			buttonMessage.enableAction();
    			popup.addPanel(panel);
    			popup.setOpcode(ReceiveOpcode.MENTOR.getValue(), client.getName());
    			men.send(popup.toString());
    		}
    	}
        
        if (Server.get().getButler().getKnuddels() > 0) {
        	if (zufall.nextInt(100) == 25 && isVisible()) {
        		CommandParser.parse(String.format("/knuddel %s", client.getName()), Server.get().getButler(), this, false);
            	client.increaseKnuddels(1);
            	Server.get().getButler().deseaseKnuddels(1);
        	}
    	}
        
        StringBuilder missed = new StringBuilder();
        
        for(String nick : client.getMissed().split("\\|")) {
        	if(!nick.isEmpty()) {
        		if(FunctionParser.countWords(client.getMissed(), nick) > 2) {
        			if(!missed.toString().contains(nick)) {
        				if(lol != 1) {
        					missed.append(", ");
        				}
        		
        				missed.append(nick);
        				lol++;
        			}
        		}
        	}
        }
        
        ArrayList<String> onlyOneNick = new ArrayList<String>();
        
        for(int i=0;i<client.newMessages.size();i++) {
        	if(!onlyOneNick.contains(client.newMessages.get(i)[1])) {
        		onlyOneNick.add(client.newMessages.get(i)[1]);
        	}
        }
        
        for(String unreadNick : onlyOneNick) {
        	if(xd != 0) {
        		m.append(", ");
        	}
        	
        	m.append("_°>_h").append(unreadNick).append("|/serverpp \"|/w \"<°_");
        	xd++;
        }
        
        if(client.getNewssperre() == 0) {
        	CommandParser.parse("/news first", client, this, false);
        	client.setNewssperre((byte)1);
        }
        
        if(!client.getFriendlist().isEmpty()) {
        	for(String nick : client.getFriendlist().split("\\|", 0)) {
        		if(!nick.isEmpty()) {
        			Client c = Server.get().getClient(nick);
			
					if(c != null) {
						fo.append(komma!=1?", ":"").append("_°BB>_h").append(nick.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_(");
						
						if(!c.getChannel().isVisible()) {
							fo.append("?");
						}else {
							fo.append("°>_h").append(c.getChannel().getName()).append("|/go \"|/go +\"<°");
						}
						
						fo.append(")");
                		komma++;
					}
				}
        	}
        }
        
        if(!client.getSchuetzlinge().isEmpty()) {
        	for(String nick : client.getSchuetzlinge().split("\\|", 0)) {
        		if(!nick.isEmpty()) {
        			Client c = Server.get().getClient(nick);
			
					if(c != null) {
						so.append(komma2!=1?", ":"").append("_°BB>_h").append(nick.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_(");
						
						if(!c.getChannel().isVisible()) {
							so.append("?");
						}else {
							so.append("°>_h").append(c.getChannel().getName()).append("|/go \"|/go +\"<°");
						}
						
						so.append(")");
                		komma2++;
					}
				}
        	}
        }
        
        for(Channel channels : Server.get().getChannels()) {
        	if(channels.countClients() > 0) {
        		for(Client c : channels.getClients()) {
        			if(c != Server.get().getButler()) {
        				if(c.checkFriend(client.getName()) || c.checkSchuetzling(client.getName())) {
        					if(isVisible()) {
        						c.sendButlerMessage(channels.getName(), String.format("_°BB>_h%s|/serverpp \"|/w \"<r°_ hat gerade den _°>_hChannel %s|/go %s|/go +%s<°_ betreten.", client.getName().replace("<", "\\<"), name, name, name));
        					}	
        				}
        			}
        		}
        	}
        }
        
        if(!m.toString().isEmpty()) {
			String an = "";
            int xde = client.newMessages.size();
            if(xde == 1) an = "eine";
            else if(xde == 2) an = "zwei";
            else if(xde == 3) an = "drei";
            else if(xde == 4) an = "vier";
            else if(xde == 5) an = "fünf";
            else if(xde == 6) an = "sechs";
            else if(xde == 7) an = "sieben";
            else if(xde == 8) an = "acht";
            else if(xde == 9) an = "neun";
            else if(xde == 10) an = "zehn";
            else if(xde == 11) an = "elf";
            else if(xde == 12) an = "zwölf";
            else an = String.valueOf(xd); 
            client.send(PacketCreator.postCountChanged(client.newMessages.size()));
            client.sendButlerMessage(name, String.format("°%%-1°Du hast _%s_ Nachricht%s in deinem Briefkasten (von: %s).°#°°>mail_closed-outerglow.png<>--<>|/m<° _°BB>Briefkasten öffnen|/m<r°_", an, xd == 1 ? "":"en", m.toString()));
        }
        
        if(client.getNewPhotoComment() == 1) {
        	client.sendButlerMessage(name, String.format("Jemand hat in deiner Abwesenheit einen Kommentar zu deinem _°>_hFoto|/\" %s<°_ geschrieben.", client.getName()));
        	client.setNewPhotoComment((byte)0);
        }

        if(!fo.toString().isEmpty()) {
            client.sendButlerMessage(name, String.format("%s momentan _°GG>_honline|/f|/mentor<r°_.", fo.toString()));
        }
        
        if(!so.toString().isEmpty()) {
            client.sendButlerMessage(name, String.format("Schützlinge: %s momentan _°GG>_honline|/f|/mentor<r°_.", so.toString()));
        }
 
        
        ArrayList<String> notices = new ArrayList<String>();
        
        
         StringBuilder friends = new StringBuilder("");
        friends.append("Folgende Personen möchten gern mit dir befreundet sein:_#§");
            
                for(String x : client.getFriendask().split("\\|")) {
                    if (!x.isEmpty()) {
                   friends.append("°>py_g.gif<° _°>_h"+x.split("~")[0]+"|/serverpp "+x.split("~")[0]+"|/w "+x.split("~")[0]+"<r°_: ''Hallo _"+client.getName()+"_, ich würde ...'' °BB>_hFreundschaft annehmen|/f +"+x.split("~")[0]+"<r°#") ;    
                }}
                   
            if ((Server.get().countChars(client.getFriendask(),'|')/2) >= 1) {
                friends.append("#Zur °BB°°>_hFreundesanfragen Übersicht|/showfriends<°°r°, "+(Server.get().countChars(client.getFriendask(),'|')/2)+" offene, 0 später#");
        client.sendButlerMessage(name,friends.toString());
    }  
        
        if (client.getPhoto().isEmpty()) {
        	notices.add("#_°>_hDeine Freunde|/f<r°_ würden sich riesig freuen, wenn du jetzt von dir ein _°BB>Foto hochlädst|/foto<r°_.");
        } else {
        	if(client.getPhoto_verify() == 0) {
        		notices.add("#Sichere dir diverse Vorteile, indem du dein Profilfoto _°BB>verifizierst|/foto<r°_ (°>w2/fv_checked...my_3.png<°)!");
            }
        }
        
        int noticesSize = notices.size();
        
        if(noticesSize > 0) {
            StringBuilder notice = new StringBuilder("Hier ein").append(noticesSize == 1 ? " Tipp" : "ige Tipps").append(" für dich:");
            
            for(String not : notices) {
            	notice.append(not);
            }
            
        	client.sendButlerMessage(name, notice.toString());
        }
        
        if(client.getEmailVerify() == 0) {
        	int minutes = 300-client.getOnlineTime()/60;
        	client.sendButlerMessage(name, String.format("°BB°_Wichtig:°r°_ Es ist aus Sicherheitsgründen notwendig, dass du deine _°BB>E-Mail-Adresse verifizierst|/e description E-Mail verifizieren<r°_ (noch %s Online-Minuten).", minutes));
        }
        
        if(!missed.toString().isEmpty()) {
            client.sendButlerMessage(name, String.format("°BB°%s %s dich ziemlich arg vermisst...", missed.toString(), lol == 2 ? "hat":"haben"));
        }
        
        String werb = Server.werbung.get(new Random().nextInt(Server.werbung.size()));
        
        if (topic != null) {
            client.sendButlerMessage(name, String.format("°BB°_Dieser Channel hat folgendes Thema:°r°#%s#§#°BB°°>%s<° °9°-Anzeige-", topic, werb));
        } else {
        	client.sendButlerMessage(name, String.format("#§#°BB°°>%s<° °9°-Anzeige-", werb));
        }
        
        long time = System.currentTimeMillis()/1000;

        
        // Hall of Fame Channel-Message nach Abwahl
        
        
   

        
        if (client.getHallOfFameMessage() == 1 && name.equals(client.getLC())) {
             String msg = "Heidi hat heute leider kein Foto für dich, °BB°_°>_h"+client.getName()+"|/serverpp \"|/w \"<°°r°_... - Dafür haben wir eines in der Hall of Fame des Channels °BB°_"+name+"°r°_ für dich reserviert! Als Dank für deinen redlichen Einsatz als CM!";
              
               broadcastAction(Server.get().getButler().getName(), msg);
            client.setHallOfFameMessage(0);
        }
        
        if(!butlerMuted) {
            if(countClients() <= 5) {

                     String[] l = client.getFeature("James-Begrüßung");
 Feature ft = Server.get().getFeature("James-Begrüßung");
 
 if (ft == null) { return;}
 if (l[0].equals("2")) {
     
     
        String immer = "Servus $TO_NICK, was macht das werte Berufsleben?|Tritt ein, bring Glück herein - willkommen $TO_NICK!|Na wenn das nicht $TO_NICK ist! Willkommen, welch Glanz in unserer Hütte!|";
          String gender = "";
         if (client.getGender() == 1) {
             gender = "Willkommen $TO_NICK, du als starker Mann könntest mir ruhig mal zur Hand gehen und ein paar Möbel verrücken.|Willkommen $TO_NICK! Hier dein Bier, die Pantoffeln und der Sportteil der \"iResort-News\". Was darf ich dir kochen?|Hallo $TO_NICK, verdrehst du den Frauen hier mal wieder den Kopf?|Hallo $TO_NICK! Ein Mann wie du muss auch mal 'ne Runde im Chat entspannen können, nicht wahr?|";
         } else {
             gender = "Ah, $TO_NICK! Gerade habe ich an dich gedacht, ich benötige dringend weiblichen Rat bei der Tischdekoration für Plaxos Bankett morgen Abend.|Hallo $TO_NICK, willst du den Männern hier etwa wieder den Kopf verdrehen?|Hallo $TO_NICK! Eine Frau wie du braucht auch mal ein paar ruhige Minuten im Chat, nicht wahr?|"; 
         }
         
       
          
         String texte = immer+gender;
         int rands = countChars(texte, '|');
        String[] texts = texte.split("\\|");       
        int randomInt = new Random().nextInt(rands);
         String ausgabe = texts[randomInt];
         
     
     
     
                if (client.getSpitznamen() == null || client.getSpitznamen().isEmpty()) {
                ausgabe = ausgabe.replace("$TO_NICK", String.format("°>_h%s|/serverpp \"|/w \"<°", client.getName().replace("<", "\\<")));
                } else {
                    List x = new ArrayList<>();
                    for(String a : client.getSpitznamen().split(",")) {
                        if (!a.trim().isEmpty()) {
                            x.add(a.trim());
                        }
                    }
                    
                    Collections.shuffle(x);
                    String n = (String)x.get(0);
                ausgabe = ausgabe.replace("$TO_NICK", String.format("°>_h%s|/serverpp "+client.getName()+"|/w "+client.getName()+"<°", n.replace("<", "\\<")));      
                    
                    
                }
            	broadcastAction(Server.get().getButler().getName(), ausgabe);  
            	
                
        } else {
            	String[] loginmessages = {"war rechtzeitig an der Tür, um sie [C] zu öffnen.~Hallöchen [C].", "öffnet die Tür und [C] tritt herein.~[C], seien Sie willkommen.", "begrüßt [C] herzlich in unserer Runde.~Juhu [C].", "lässt [C] herein und hängt sogleich die Jacke fein säuberlich auf.~Hallo [C].", "macht die Tür für [C] auf.~Heyho [C]."};
            	String[] msg = loginmessages[zufall.nextInt(loginmessages.length)].replace("[C]", String.format("°>_h%s|/serverpp \"|/w \"<°", client.getName().replace("<", "\\<"))).split("~");
            
                
            	broadcastAction(Server.get().getButler().getName(), msg[0]);
                
            	broadcastButlerMessage(msg[1]);
                }
            } else {
            	if(client.newRoses.size() > 0) {
            		for(String[] info : client.newRoses) {
            			String von = info[0];
            			String text = info[1];
            			String uhrzeit = info[2];
            			
            			broadcastButlerMessage(String.format("Diese Rose soll ein Geschenk von _%s_ sein.", von));
            			broadcastAction(Server.get().getButler().getName(), String.format("hat für _%s_ eine °>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<° von _%s_ mitgebracht. Eine Nachricht von _%s_ habe ich ebenfalls mitgebracht:#%s", client.getName(), von, von, text));
            			Server.get().newMessage(Server.get().getButler().getName(), von, String.format("Rose an %s ausgeliefert", client.getName()), String.format("Deine °>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<° an %s wurde soeben ausgeliefert.", client.getName()), systemtime);
            			client.setRoses(client.getRoses() + 1);
                               
                                /* Highlights Eintrag */    
        int[] roses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 30, 40, 50, 60, 70, 80, 90, 100, 111, 200, 222, 250, 300, 333, 350, 400, 450, 500, 1000, 1500, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 15000, 20000, 25000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000  };
        Arrays.sort(roses);
        
        if(Arrays.binarySearch(roses, client.getRoses()) >= 0) {
         client.setHighlights(String.format("%s|%s _%s_. Rose von °BB°_%s_°12°|", client.getHighlights(), Server.get().timeStampToDate(time), nf.format(client.getRoses()), von));
         
        }
         
                        
                    	if(client.rosen.size() > 7) {
                    		Server.get().query(String.format("delete from roses where an ='%s' and erhalten='1' order by id limit 1", client.getName()));
                    	}
                    	
                		client.rosen.add(new String[] {von, text, uhrzeit});
            		}
            		
            		client.newRoses.clear();
                	Server.get().query(String.format("update roses set erhalten='1', uhrzeit='%s' where `an` = '%s'", systemtime, client.getName())); 
            	}
            
            }
        }
        
        if(!client.getBirthday().isEmpty()) {
        	if(client.getBirthday().substring(0,5).equals(timeString) && client.getAgePlus() == 0) {
        		String[] bday = {"Herzlichen Glückwunsch zum Geburtstag, %s.", "Herzlichen Glückwunsch zum Purzeltag, %s. Man sieht ja schon die ersten Falten!", "Alles Gute von mir zum Geburtstag, %s.", "Hääääääääääppiiiiiiiiiiiiiiiii Bööööööööörssssssssdaaaaaaaaaiiiiiiii, %s!!!", "Herzlichen Glückwunsch zum Geburtstag, %s! Der Sekt steht schon für Sie bereit.", "Schon wieder ein Jahr älter?! Alles Gute, %s!", "Heute gehört die Welt Ihnen, %s. Alles Gute zu Ihrem Geburtstag!"};
        	
        		broadcastButlerMessage(String.format("°BB18°_%s", String.format(bday[zufall.nextInt(bday.length)], client.getName())));
        		client.setAge((byte)(client.getAge()+1));
        		client.setAgePlus((byte)1);
        	}
        }

        client.setMissed("");
        
        ArrayList<String> names = new ArrayList<String>();
        
        for(String lal : client.getSpitznamen().split(",")) {
        	names.add(lal.trim());
        }
        
        client.send(PacketCreator.boldNames(names));
        client.setLastOnline(System.currentTimeMillis()/1000);
        client.setLastOnlineChannel(name);
        
        String js = client.getJoinSound();
        if(js != null && !js.equals("-")) {
            for(Client c : getClients()) {
                if(c != null) {
                    c.send(PacketCreator.playSound(js));
                }
            }
        }
        
        
         if (!client.getNewCode().isEmpty()) {                           
        String[] cd = client.getNewCode().split("\\|~\\|");
        client.setNewCodeLog(client.getNewCode());
        String nick = cd[0];
        String kategorie = cd[1];
        String text = cd[2];
        String nickname = KCodeParser.escape(nick);
         boolean online = true; Client target = Server.get().getClient(nickname); if (target == null) { online = false; target = new Client(null); target.loadStats(nickname); }
         nickname = target.getName();        
           SimpleDateFormat datum2 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
         String dates = datum2.format(new Date());
Kategorie lala = Server.get().getKategorie(kategorie);
           broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:",  Server.get().getButler().getName()), String.format("_Überraschung %s!_ Ein °>present.gif<° wurde für dich hinterlegt:##_Ein °>present.gif<° von %s_:##%s##erhalten am %s", client.getName(), nick, text, dates));
  Server.get().newMessage(Server.get().getButler().getName(), nick, String.format("Code ausgeliefert", client.getName()), String.format("Dein °>present.gif<° an %s wurde soeben ausgeliefert.", client.getName()), systemtime);
            			
           Codes codei = null;
           for(Codes code : Server.get().getCode()) {
              
              codei = code;
            
           }  
         
           codei.aktivateSmileyCode(client,lala.getName());
      
         
                          }
         
         
         String member = "";
		for (Client c : getClients()) {
                   member = member+"|"+c.getName()+"|";
                       if (!c.getEffect().isEmpty() && client.getShowEffects() == 1) {
                       
                           
               String ft = "";
              for(String v : Server.existeffekts.split("\\|")) {
                  if (!v.isEmpty()) {
                      
                      String[] e = v.split("~");
                      if (e[1].equals(c.getEffect())) {
                      ft = e[2];
                  }}
              }
              
              
               if (c.getEffect().startsWith("butterfly:") && !c.getEffect().equals("butterfly")) {
       String a = c.getEffect().split(":")[1];
      Client ac = Server.get().getClient(a);
       if (ac != null && getClients().contains(ac)) {
             client.send(PacketCreator.effect(c.getName(), "butterfly"));   
           client.send(PacketCreator.effect(ac.getName(), "butterflyHeart:"+c.getName())); 
       }      
        
    }
                           
                           String[] l = c.getFeature(ft);
                   if (l[0].equals("2") || c.getEffect().equals("wash") || c.getEffect().equals("moskitoBite")) {
                           client.send(PacketCreator.effect(c.getName(), c.getEffect()));
                       }
                       }}
         
     client.setMentorSmileys();
       sendPrefixTogether();
   
        if (getGame() != null) {
      getGame().onJoin(client);
    }
        if (getFeatures() != null) {
      getFeatures().onJoin(client);
    }   
        int r = new Random().nextInt(20)+1;
        if (r == 10) {
        if (Server.get().getButler().getHeart().isEmpty()) {
            heart.functionMake(Server.get().getButler(), this, "/heart "+client.getName());
        }}
        
                
    }

    public void sendPrefixTogether() {
        
        List<String> prefix = new ArrayList<String>();
      
       List<String> send = new ArrayList<String>();
       List<String[]> together = new ArrayList<String[]>();
       
       try {
       for(Client client : getClients()) {
            
            client.send(PacketCreator.removePrefixIcons(name));
            
            // handle partnerlok
            if(!client.getPartnerlook().isEmpty() && !send.contains(client.getName())) {
                Client target = Server.get().getClient(client.getPartnerlook());
                if (target != null && getClients().contains(target) && !send.contains(target.getName())) {
          prefix.add(String.format("%s~icon_lovetwins_topnick.png", client.getName()));
          prefix.add(String.format("%s~icon_lovetwins_bottomnick.png", target.getName()));
          send.add(client.getName());
          send.add(target.getName());   
          together.add(new String[] {client.getName(),target.getName()});
            }
            } else  if(!client.getAmorsarrow().isEmpty() && !send.contains(client.getName())) {
                Client target = Server.get().getClient(client.getAmorsarrow());
                if (target != null && getClients().contains(target) && !send.contains(target.getName())) {
          prefix.add(String.format("%s~icon_lovetwins_bottomnick.png",target.getName()));
          prefix.add(String.format("%s~ft_amors-arrow_nicklist.png", client.getName()));
          send.add(client.getName());
          send.add(target.getName());   
          together.add(new String[] {client.getName(),target.getName()});
            }
            } else  if(!client.getLovepotion().isEmpty() && !send.contains(client.getName())) {
                Client target = Server.get().getClient(client.getLovepotion());
                if (target != null && getClients().contains(target) && !send.contains(target.getName())) {
          prefix.add(String.format("%s~icon_lovepotioned_bottomnick.gif",target.getName()));
          prefix.add(String.format("%s~icon_lovepotioned_topnick.gif", client.getName()));
          send.add(client.getName());
          send.add(target.getName());   
          together.add(new String[] {client.getName(),target.getName()});
            }
            }
            // handle partnerlook ende.
        
            
        } 
    }  catch (ConcurrentModificationException ex) {
        sendPrefixTogether();
    }
    
      try {
              for(Client alle : getClients()) {
            
                for(String[] a : together) {
    		alle.send(PacketCreator.nicklistSortTogether(name,a[0], a[1]));
                }
                alle.send(PacketCreator.showPrefixIcons(name, prefix,false));
                } 
              
    } catch (ConcurrentModificationException ex) {
       sendPrefixTogether();
    }
    
          prefix.clear();
        send.clear();
        together.clear();
        
    }
    
    
    
    public void leave(Client client) {
         String js = client.getLeftSound();
         
         synchronized (clients) {
            clients.remove(client);
        }

        for (Client c : getClients()) {
             if(js != null && !js.equals("-")) {
                    c.send(PacketCreator.playSound(js));
             }
            
            c.send(PacketCreator.removeUser(client, this));
            sendPrefixTogether();
        }

        if (game != null) {
            game.onLeave(client);
        }
    }

}
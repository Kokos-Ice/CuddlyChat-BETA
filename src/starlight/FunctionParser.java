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

import fifty.fiftyanmeldung;
import fifty.fiftywuerfeln;
import game.Darten;
import game.Dicen;
import game.Freidiffen;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static starlight.CommandParser2.countChars;
import static starlight.Server.replaceLast;
import tools.HexTool;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.ProfileTools;
import tools.Source;
import tools.Zodiac;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.KTab;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;
import tools.popup.PopupWhois2;
import tools.popup.TextField;
import tools.query;
import tools.w2;
import tools.wold;

public class FunctionParser {
    private static NumberFormat nf;
    private static String[] dmNoWin = {"Leider kein Gewinn!", "Das war wohl nichts! Versuch es gleich nochmal...", "Nichts! Das Pech verfolgt dich...", "Du hast einen stinkenden Socken gewonnen, jedoch keine Knuddels!", "Leider nichts! Kopf hoch, das nächste Mal wird besser!"};
    private static String[] dmWin = {"%s Knuddels gehen auf dein Konto!", "Du darfst dich freuen, denn du erhälst %s Knuddels!", "Voll ins Schwarze getroffen! %s Knuddels!"};
    
	static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
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
         
	public static int countWords(String text, String word){
		int count=0;
		for(Matcher m=Pattern.compile(Pattern.quote(word)).matcher(text); m.find(); count++);
		return count;
	}
	
	public static String encode(String input) {
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isUnsafe(ch)) {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            } else {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }
	
	public static boolean URLExist(String link){
	    URL url = null;
	    
	    try {
	        url = new URL(link);
	        url.openStream().close();

	        return true;
	    } catch (Exception e) {
	    	return false;
	    } 
	}
	
	public static void parse(String message, Client client, Channel channel) {
        String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
        String cmd = command.toLowerCase();
        String arg = "";
        Random zufall = new Random();
        Long time = System.currentTimeMillis()/1000; 
        
        if (message.length() > cmd.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1);
        }
        
        int Anzahlspe = Server.countChars(arg, '°');
        
        if (Anzahlspe == 1) {
        	String[] argus = arg.split("°", 2);
        	arg = argus[0];
        }
        
        if(cmd.equals("auth")) {
        funktionen.auth.functionMake(client, channel, arg);        
        } else if(cmd.equals("bugs")) {
        funktionen.bugs.functionMake(client, channel, arg);
        } else if(cmd.equals("tut")) {
        	if(!client.hasPermission("cmd.tut")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if (arg.equals("-")) {
        		/*  23 */       if (client.getTutAktiv() == 1) {
        		/*  24 */         client.sendButlerMessage(channel.getName(), String.format("Das automatische ''\"Tipps und Tricks\"''-System ist nun _abgeschaltet_. Du kannst es mit °BB°_°>_h/tut +|/tut +<°_§ wieder einschalten.", new Object[0]));
        		/*  25 */         client.setTutAktiv((byte)0);
        		/*     */       } else {
        		/*  27 */         client.sendButlerMessage(channel.getName(), String.format("Das ''\"Tipps und Tricks\"''-System ist _bereits ausgeschaltet_. Noch mehr ausschalten geht nicht.", new Object[0]));
        		/*     */       }
        		/*  29 */       return;
        		/*     */     }
        		/*  31 */     if (arg.equals("+")) {
        		/*  32 */       if (client.getTutAktiv() == 0) {
        		/*  33 */         client.sendButlerMessage(channel.getName(), String.format("Das automatische ''\"Tipps und Tricks\"''-System ist nun _aktiv_.", new Object[0]));
        		/*  34 */         client.setTutAktiv((byte)1);
        		/*     */       } else {
        		/*  36 */         client.sendButlerMessage(channel.getName(), String.format("Das ''\"Tipps und Tricks\"''-System ist _bereits eingeschaltet_.", new Object[0]));
        		/*     */       }
        		/*  38 */       return;
        		/*     */     }
        		/*     */ 
        		/*  41 */     if (arg.equals("++")) {
        		/*  42 */       String aktuheader = "";
        		/*  47 */       String nextheader = "";
        		/*  48 */       int tutmins = 0;
        		/*  49 */       int tutrang = 0;
        						for(int tutorial : Server.tutorials.keySet()) {
        							String[] more = Server.tutorials.get(tutorial);
        							
        							if(tutorial == client.getAktuTut()) {
        								aktuheader = more[0];
        								break;
        							} else if(tutorial == client.getAktuTut()+1) {
        								nextheader = more[0];
        								tutmins = Integer.parseInt(more[6]);
        								tutrang = Integer.parseInt(more[5]);
        								break;
        							}
        						}

        		/*  54 */       if ((client.getTutOpen() == 1) || (nextheader.isEmpty()) || (tutmins > client.getOnlineTime() / 60) || (tutrang > client.getRank())) {
        		/*  55 */         client.sendButlerMessage(channel.getName(), String.format("Du hast die aktuelle Lektion schon erfolgreich abgeschlossen. Die nächste Lektion ist noch nicht für dich verfügbar. Bitte gedulde dich noch ein bisschen.", new Object[0]));
        		/*     */       } else {
        		/*  57 */         client.sendButlerMessage(channel.getName(), String.format("Du hast soeben erfolgreich die TuT-Lektion ''" + aktuheader + "'' abgeschlossen. Als nächstes erwartet dich die Lektion ''" + nextheader + "''.", new Object[0]));
        		/*  58 */         int lala = client.getAktuTut() + 1;
        		/*  59 */         client.setAktuTut((byte)lala);
        		/*  60 */         client.setTutOpen((byte)1);
        		/*     */       }
        		/*  62 */       return;
        		/*     */     }
        		/*     */ 
        		/*  65 */     if ((!arg.isEmpty()) && (!arg.equals("index")) && (
        		/*  66 */       (arg.equals("-0")) || (arg.equals("0")) || (!Server.get().isInteger(arg)) || (Integer.parseInt(arg) < 0) || (client.getAktuTut() < Integer.parseInt(arg)))) {
        		/*  67 */       client.sendButlerMessage(channel.getName(), String.format("Diese Lektion ist noch nicht für dich freigegeben.", new Object[0]));
        		/*  68 */       return;
        		/*     */     }
        		/*  70 */     int lol = client.getAktuTut();
        		/*  71 */     if ((!arg.isEmpty()) && (!arg.equals("index"))) {
        		/*  72 */       lol = Integer.parseInt(arg);
        		/*     */     }
        		/*     */ 
        		/*  75 */     String index = "";
        		/*     */ 
        		/*  77 */     PoolConnection pcon99 = ConnectionPool.getConnection();
        		/*  78 */     PreparedStatement ps99 = null;
        		/*     */     try
        		/*     */     {
        		/*  81 */       Connection con99 = pcon99.connect();
        		/*  82 */       ps99 = con99.prepareStatement("SELECT * FROM tutorials where tutid <= '" + client.getAktuTut() + "'");
        		/*     */ 
        		/*  84 */       ResultSet rs99 = ps99.executeQuery();
        		/*  85 */       while (rs99.next())
        		/*  86 */         index = index + "°+0020°°11°  °r°" + rs99.getString("tutid") + ". °+0075BB>_r" + rs99.getString("titel") + "|/tut " + rs99.getString("tutid") + "<%00°°°#";
        		/*     */     }
        		/*     */     catch (SQLException e)
        		/*     */     {
        		/*  90 */       e.printStackTrace();
        		/*     */     } finally {
        		/*  92 */       if (ps99 != null)
        		/*     */         try {
        		/*  94 */           ps99.close();
        		/*     */         }
        		/*     */         catch (SQLException e)
        		/*     */         {
        		/*     */         }
        		/*  99 */       pcon99.close();
        		/*     */     }
        		/*     */ 
        		/* 104 */     String text = "";
        		/* 105 */     String titel = "";
        		/* 106 */     int summe = 0;
        		/* 107 */     int tutid = 0;
        		/* 108 */     if (arg.equals("index")) {
        		/* 109 */       titel = "Alle Lektionen";
        		/* 110 */       text = "°>{switchtab}0<°°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°11>RIGHT<° °+6005° #°+9007°°20>LEFT<+0010°_Alle Lektionen_°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/boxb_bl-f.png<>layout/boxb_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_br-f.png<°°>{endtable}<>LEFT<°#°+8004°°+9005°_°BB12>{table|w7|w4<>layout/line_l.png<°°>layout/line_c...w_0.xrepeat.png<° °+6010>{tc}<>RIGHT<>{noxrep}<°°>layout/tab_a_l...w_8.mx_-1.png<>layout/tab_a_c...w_0.xrepeat.png<>_hAlle Lektionen|/tp-showtab 0<>{noxrep}<>layout/tab_a_r.png<°°>layout/line_r.png<+6010>{endtable}<>LEFT<°_°°# # #°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°16>LEFT<+0010°_Alle Lektionen_°%007°#  §°>{endtable}<>LEFT<°#°1° # °+8003°°1>{table|0|w1|w1<>{tc}<>layout/boxb-h_l.gif<>layout/boxb-h_c...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb-h_r.gif<°°>{endtable}<>LEFT<°#°+8002°°>{table|4|w1,bgimg;layout/box_cl.gif;layout/box_cc.gif;layout/box_cr.gif|4<>{tc}<°°14+0010° #";
        		/* 111 */       text = text + index;
        		/* 112 */       text = text + "#°+9000°#°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/box_bl-f.png<>layout/box_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/box_br-f.png<°°>{endtable}<>LEFT<°#°>{showtab}0<°";
        		/*     */     }
        		/*     */     else
        		/*     */     {
        		/* 116 */       String header = "";
        		/* 117 */       String erklarung = "";
        		/* 118 */       String hintergrund = "";
        		/* 119 */       String merke = "";
        		/*     */ 
        		/* 122 */       PoolConnection pcon = ConnectionPool.getConnection();
        		/* 123 */       PreparedStatement ps = null;
        		/*     */       try
        		/*     */       {
        		/* 126 */         Connection con = pcon.connect();
        		/* 127 */         ps = con.prepareStatement("SELECT * FROM tutorials where tutid='" + lol + "'");
        		/* 128 */         ResultSet rs = ps.executeQuery();
        		/*     */ 
        		/* 131 */         while (rs.next()) {
        		/* 132 */           titel = rs.getString("titel");
        		/* 133 */           header = rs.getString("header");
        		/* 134 */           erklarung = rs.getString("erklarung");
        		/* 135 */           hintergrund = rs.getString("hintergrund");
        		/* 136 */           merke = rs.getString("merke");
        		/* 137 */           tutid = rs.getInt("tutid");
        		/*     */ 
        		/* 139 */           merke = merke.replace("[MAINURL]", Server.get().getURL());
        		/* 140 */           hintergrund = hintergrund.replace("[MAINURL]", Server.get().getURL());
        		/* 141 */           erklarung = erklarung.replace("[MAINURL]", Server.get().getURL());
        		/*     */         }
        		/*     */       } catch (SQLException e) {
        		/* 144 */         e.printStackTrace();
        		/*     */       } finally {
        		/* 146 */         if (ps != null)
        		/*     */           try {
        		/* 148 */             ps.close();
        		/*     */           }
        		/*     */           catch (SQLException e)
        		/*     */           {
        		/*     */           }
        		/* 153 */         pcon.close();
        		/*     */       }
        		/*     */ 
        		/* 158 */       PoolConnection pcon3 = ConnectionPool.getConnection();
        		/* 159 */       PreparedStatement ps3 = null;
        		/*     */       try {
        		/* 161 */         Connection con = pcon3.connect();
        		/* 162 */         ps3 = con.prepareStatement("SELECT COUNT(id) AS summe FROM `tutorials`");
        		/* 163 */         ResultSet rs3 = ps3.executeQuery();
        		/* 164 */         while (rs3.next())
        		/* 165 */           summe = rs3.getInt("summe");
        		/*     */       }
        		/*     */       catch (SQLException e) {
        		/* 168 */         e.printStackTrace();
        		/*     */       } finally {
        		/* 170 */         if (ps3 != null)
        		/*     */           try {
        		/* 172 */             ps3.close();
        		/*     */           }
        		/*     */           catch (SQLException e)
        		/*     */           {
        		/*     */           }
        		/* 177 */         pcon3.close();
        		/*     */       }
        		/*     */ 
        		/* 180 */       text = "°>{switchtab}0<°°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°11>RIGHT<°Lektion _";
        		/* 181 */       text = text + tutid + "/" + summe;
        		/* 182 */       text = text + "_°+6005° #°+9007°°20>LEFT<+0010°";
        		/* 183 */       text = text + "_" + titel + "_";
        		/* 184 */       text = text + "°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/boxb_bl-f.png<>layout/boxb_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_br-f.png<°°>{endtable}<>LEFT<°#°+8004°°+9005°_°BB12>{table|w7|w4<>layout/line_l.png<°°>layout/tab_a_l...w_8.mx_-1.png<>layout/tab_a_c...w_0.xrepeat.png<>_hErklärung|/tp-showtab 0<>{noxrep}<°";
        		/* 185 */       if (!hintergrund.isEmpty()) {
        		/* 186 */         text = text + "°>layout/tab_a_r.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hHintergrund|/tp-showtab 2<>{noxrep}<°";
        		/*     */       }
        		/* 188 */       String zwei = "i";
        		/* 189 */       if ((hintergrund.isEmpty()) && (!merke.isEmpty())) {
        		/* 190 */         zwei = "a";
        		/*     */       }
        		/* 192 */       if (!merke.isEmpty()) {
        		/* 193 */         text = text + "°>layout/tab_" + zwei + "_r.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hMerke|/tp-showtab 3<>{noxrep}<°";
        		/*     */       }
        		/* 195 */       String last = "i";
        		/* 196 */       if ((merke.isEmpty()) && (hintergrund.isEmpty())) {
        		/* 197 */         last = "a";
        		/*     */       }
        		/* 199 */       text = text + "°>layout/tab_" + last + "_r.png<°°>layout/line_c...w_0.xrepeat.png<° °+6010>{tc}<>RIGHT<>{noxrep}<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hAlle Lektionen|/tp-showtab 1<>{noxrep}<>layout/tab_i_r.png<°°>layout/line_r.png<+6010>{endtable}<>LEFT<°_°°# # #°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°16>LEFT<+0010°_";
        		/* 200 */       text = text + header;
        		/* 201 */       text = text + "_°%007°#  §°>{endtable}<>LEFT<°#°1° # °+8003°°1>{table|0|w1|w1<>{tc}<>layout/boxb-h_l.gif<>layout/boxb-h_c...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb-h_r.gif<°°>{endtable}<>LEFT<°#°+8002°°>{table|4|w1,bgimg;layout/box_cl.gif;layout/box_cc.gif;layout/box_cr.gif|4<>{tc}<°°14+0010° #######################°+8022°°14°°>JUSTIFY<°";
        		/* 202 */       text = text + erklarung;
        		/* 203 */       text = text + "#°+9000°#°+9000°#°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/box_bl-f.png<>layout/box_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/box_br-f.png<°°>{endtable}<>LEFT<°#§°>°";
        		/*     */ 
        		/* 209 */       text = text + "°>{switchtab}2<°°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°11>RIGHT<°Lektion _";
        		/* 210 */       text = text + tutid + "/" + summe;
        		/* 211 */       text = text + "_°+6005° #°+9007°°20>LEFT<+0010°";
        		/* 212 */       text = text + "_" + titel + "_";
        		/* 213 */       text = text + "°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/boxb_bl-f.png<>layout/boxb_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_br-f.png<°°>{endtable}<>LEFT<°#°+8004°°+9005°_°BB12>{table|w7|w4<>layout/line_l.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hErklärung|/tp-showtab 0<>{noxrep}<°";
        		/*     */ 
        		/* 215 */       text = text + "°>layout/tab_i_r.png<°°>layout/tab_a_l...w_8.mx_-1.png<>layout/tab_a_c...w_0.xrepeat.png<>_hHintergrund|/tp-showtab 2<>{noxrep}<°";
        		/*     */ 
        		/* 218 */       if (!merke.isEmpty()) {
        		/* 219 */         text = text + "°>layout/tab_a_r.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hMerke|/tp-showtab 3<>{noxrep}<°";
        		/*     */       }
        		/*     */ 
        		/* 222 */       text = text + "°>layout/tab_i_r.png<°°>layout/line_c...w_0.xrepeat.png<° °+6010>{tc}<>RIGHT<>{noxrep}<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hAlle Lektionen|/tp-showtab 1<>{noxrep}<>layout/tab_i_r.png<°°>layout/line_r.png<+6010>{endtable}<>LEFT<°_°°# # #°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°16>LEFT<+0010°_";
        		/* 223 */       text = text + header;
        		/* 224 */       text = text + "_°%007°#  §°>{endtable}<>LEFT<°#°1° # °+8003°°1>{table|0|w1|w1<>{tc}<>layout/boxb-h_l.gif<>layout/boxb-h_c...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb-h_r.gif<°°>{endtable}<>LEFT<°#°+8002°°>{table|4|w1,bgimg;layout/box_cl.gif;layout/box_cc.gif;layout/box_cr.gif|4<>{tc}<°°14+0010° #######################°+8022°°14°°>JUSTIFY<°";
        		/* 225 */       text = text + hintergrund;
        		/* 226 */       text = text + "#°+9000°#°+9000°#°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/box_bl-f.png<>layout/box_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/box_br-f.png<°°>{endtable}<>LEFT<°#§°>°";
        		/*     */ 
        		/* 230 */       text = text + "°>{switchtab}3<°°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°11>RIGHT<°Lektion _";
        		/* 231 */       text = text + tutid + "/" + summe;
        		/* 232 */       text = text + "_°+6005° #°+9007°°20>LEFT<+0010°";
        		/* 233 */       text = text + "_" + titel + "_";
        		/* 234 */       text = text + "°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/boxb_bl-f.png<>layout/boxb_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_br-f.png<°°>{endtable}<>LEFT<°#°+8004°°+9005°_°BB12>{table|w7|w4<>layout/line_l.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hErklärung|/tp-showtab 0<>{noxrep}<°";
        		/* 235 */       if (!hintergrund.isEmpty()) {
        		/* 236 */         text = text + "°>layout/tab_i_r.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hHintergrund|/tp-showtab 2<>{noxrep}<°";
        		/*     */       }
        		/*     */ 
        		/* 240 */       text = text + "°>layout/tab_i_r.png<°°>layout/tab_a_l...w_8.mx_-1.png<>layout/tab_a_c...w_0.xrepeat.png<>_hMerke|/tp-showtab 3<>{noxrep}<°";
        		/*     */ 
        		/* 242 */       text = text + "°>layout/tab_a_r.png<°°>layout/line_c...w_0.xrepeat.png<° °+6010>{tc}<>RIGHT<>{noxrep}<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hAlle Lektionen|/tp-showtab 1<>{noxrep}<>layout/tab_i_r.png<°°>layout/line_r.png<+6010>{endtable}<>LEFT<°_°°# # #°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°16>LEFT<+0010°_";
        		/* 243 */       text = text + header;
        		/* 244 */       text = text + "_°%007°#  §°>{endtable}<>LEFT<°#°1° # °+8003°°1>{table|0|w1|w1<>{tc}<>layout/boxb-h_l.gif<>layout/boxb-h_c...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb-h_r.gif<°°>{endtable}<>LEFT<°#°+8002°°>{table|4|w1,bgimg;layout/box_cl.gif;layout/box_cc.gif;layout/box_cr.gif|4<>{tc}<°°14+0010° #######################°+8022°°14°°>JUSTIFY<°";
        		/* 245 */       text = text + merke;
        		/* 246 */       text = text + "#°+9000°#°+9000°#°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/box_bl-f.png<>layout/box_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/box_br-f.png<°°>{endtable}<>LEFT<°#§°>°";
        		/*     */ 
        		/* 251 */       text = text + "°>{switchtab}1<°°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°11>RIGHT<°Lektion _";
        		/* 252 */       text = text + tutid + "/" + summe;
        		/* 253 */       text = text + "_°+6005° #°+9007°°20>LEFT<+0010°";
        		/* 254 */       text = text + "_" + titel + "_";
        		/* 255 */       text = text + "°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/boxb_bl-f.png<>layout/boxb_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_br-f.png<°°>{endtable}<>LEFT<°#°+8004°°+9005°_°BB12>{table|w7|w4<>layout/line_l.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hErklärung|/tp-showtab 0<>{noxrep}<°";
        		/* 256 */       if (!hintergrund.isEmpty()) {
        		/* 257 */         text = text + "°>layout/tab_i_r.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hHintergrund|/tp-showtab 2<>{noxrep}<°";
        		/*     */       }
        		/* 259 */       if (!merke.isEmpty()) {
        		/* 260 */         text = text + "°>layout/tab_i_r.png<°°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_hMerke|/tp-showtab 3<>{noxrep}<°";
        		/*     */       }
        		/*     */ 
        		/* 263 */       text = text + "°>layout/tab_i_r.png<°°>layout/line_c...w_0.xrepeat.png<° °+6010>{tc}<>RIGHT<>{noxrep}<°°>layout/tab_a_l...w_8.mx_-1.png<>layout/tab_a_c...w_0.xrepeat.png<>_hAlle Lektionen|/tp-showtab 1<>{noxrep}<>layout/tab_a_r.png<°°>layout/line_r.png<+6010>{endtable}<>LEFT<°_°°# # #°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°°>{endtable}<>LEFT<°#°+8003°°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°°16>LEFT<+0010°_";
        		/* 264 */       text = text + "Alle Lektionen";
        		/* 265 */       text = text + "_°%007°#  §°>{endtable}<>LEFT<°#°1° # °+8003°°1>{table|0|w1|w1<>{tc}<>layout/boxb-h_l.gif<>layout/boxb-h_c...w_0.xrepeat.gif<° °>{tc}<>RIGHT<>{noxrep}<>layout/boxb-h_r.gif<°°>{endtable}<>LEFT<°#°+8002°°>{table|4|w1,bgimg;layout/box_cl.gif;layout/box_cc.gif;layout/box_cr.gif|4<>{tc}<°°14+0010° #######################°+8022°°14°°>JUSTIFY<°";
        		/* 266 */       text = text + index;
        		/* 267 */       text = text + "#°+9000°#°+9000°#°%00° §°>{endtable}<>LEFT<°#°+8002°°1>{table|0|w1|w1<>{tc}<>layout/box_bl-f.png<>layout/box_bc-f...w_0.xrepeat.png<° °>{tc}<>RIGHT<>{noxrep}<>layout/box_br-f.png<°°>{endtable}<>LEFT<°";
        		/*     */ 
        		/* 271 */       text = text + "#°+8002°°>LEFT<°#°>{showtab}0<°";
        		/*     */     }
        		/*     */ 
        		/* 274 */     Popup popup2 = new Popup("TuT: " + titel,null, text, 450, 600);
        		/* 275 */     Panel panel = new Panel();
        		/* 276 */     Button button2 = new Button("    <    ");
        		/* 277 */     button2.setStyled(true);
        		/* 278 */     if ((tutid == 1) || (arg.equals("index")))
        		/* 279 */       button2.disableClose();
        		/*     */     else {
        		/* 281 */       button2.setCommand(String.format("/tut %s", new Object[] { Integer.valueOf(tutid - 1) }));
        		/*     */     }
        		/* 283 */     panel.addComponent(button2);
        		/* 284 */     Button button = new Button("        OK        ");
        		/* 285 */     button.setStyled(true);
        		/* 286 */     panel.addComponent(button);
        		/* 287 */     Button button3 = new Button("    >    ");
        		/* 288 */     button3.setStyled(true);
        		/* 289 */     if ((tutid == summe) || (arg.equals("index")) || (client.getAktuTut() < tutid + 1))
        		/* 290 */       button3.disableClose();
        		/*     */     else {
        		/* 292 */       button3.setCommand(String.format("/tut %s", new Object[] { Integer.valueOf(tutid + 1) }));
        		/*     */     }
        		/* 294 */     panel.addComponent(button3);
        		/* 295 */     popup2.addPanel(panel);
        		/*     */ 
        		/* 297 */     client.send(popup2.toString());
        
        } else 
        if(cmd.equals("stat")) {
        	if(!client.hasPermission("cmd.stat")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
                Server.get().newSysLogEntry(client.getName(), String.format("Statistiken zu "+Server.get().getSettings("CHAT_NAME")+" abgerufen", client.getName()));
            
        	
        	String title = "Statistiken zu "+Server.get().getSettings("CHAT_NAME")+"";
        	StringBuilder stat = new StringBuilder();
        	PoolConnection pcon = ConnectionPool.getConnection();
            Statement ps = null;

            try {
                Connection con = pcon.connect();
                ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts`");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _User insgesamt_:°%42°").append(nf.format(rs.getInt("a")));
                }

                rs.close();
                rs = ps.executeQuery("SELECT `id` FROM `accounts` ORDER BY `id` DESC");
                
                if(rs.next()) {
                	stat.append(" [").append(rs.getInt("id")).append("]°%00°#");
                }
                
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `sperre` != 0");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Gesperrte User_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }
                
                stat.append("°>cc/bullet_blue_outlined.png<° _Butler_:°%42>_h").append(Server.get().getButler().getName()).append("|/serverpp \"|/w \"<° (").append(Server.get().getButler().getAge()).append(")°>").append(Server.get().getButler().getGender() == 1 ? "male":"female").append(".png<%00°#");

                rs.close();
                rs = ps.executeQuery("SELECT `name`, `registerIP`, `gender`, `age` FROM `accounts` ORDER BY `id` DESC");
                
                if(rs.next()) {
                	byte gender = rs.getByte("gender");
                	
                	stat.append("°>cc/bullet_blue_outlined.png<° _Neuster User_:°%42>_h").append(rs.getString("name")).append("|/serverpp \"|/w \"<° (").append(rs.getInt("age")).append("°>").append(gender == 1 ? "male":"female").append(".png<°, IP: °>_h").append(rs.getString("registerIP")).append("|/checkip \"<°)°%00°#");
                }
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` = 0");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _User_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` = 1");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Familymitglieder_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` = 2");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Stammis_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` = 3");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Ehrenmitglieder_:°%42°").append(rs.getInt("a")).append(" (1 Bot)°%00°#");
                }
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` = 5");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _inoffizielle Admins_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` > 5 AND `rank` < 8");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Admins_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }         
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` = 8");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _inoffizielle Sysadmins_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }  
                rs.close();
                rs = ps.executeQuery("SELECT COUNT(`id`) AS `a` FROM `accounts` WHERE `rank` > 8");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Sysadmins_:°%42°").append(rs.getInt("a")).append(" (1 Bot)°%00°#");
                }
                if(Settings.getStammi() != null) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Stammi des Monats_:°%42°").append(Settings.getStammi()).append("°%00°#");
                }
                rs.close();
                stat.append("#°>cc/bullet_blue_outlined.png<° _Knuddels im Umlauf_:°%42°").append(nf.format(Server.get().getAllKnuddels()+Server.get().getAllKontoKnuddels())).append(" (").append(nf.format(Server.get().getAllKontoKnuddels())).append(" auf Schweizer Konten)°%00°#");
            	
                rs = ps.executeQuery("SELECT SUM(`roses`) AS `a` FROM `accounts`");
                
                if(rs.next()) {
                	stat.append("°>cc/bullet_blue_outlined.png<° _Rosen im Umlauf_:°%42°").append(rs.getInt("a")).append("°%00°#");
                }
                for(Channel x : Server.get().getChannels()) {
                  if (x.getGameName().equals("HIGH OR LOW")) {
                stat.append("°>cc/bullet_blue_outlined.png<° _JP ("+x.getName()+")_:°%42°").append(x.getHoljackpot());
                if (!x.getHolnick().isEmpty()) {
                stat.append(" (°>_h").append(x.getHolnick()).append("|/serverpp \"|/w \"<° mit Runde ").append(x.getHolrunde()).append(")");
                  }
                stat.append("°%00°#");
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
        	
        	
                 Popup popup = new Popup(title, title, stat.toString(), 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setNewspopup(1);
                 client.send(popup.toString());
                 return;
                 
        } else if(cmd.equals("cm")) {
        	if(!client.hasPermission("cmd.cm")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung");
        		return;
        	}
        	
        	if(arg.equalsIgnoreCase("stat")) {
        		StringBuilder stat = new StringBuilder();
        		String title = "Bla";
        		
        		for(String wahl : Server.elections.keySet()) {
        			String[] more = Server.elections.get(wahl);
        			int active = Integer.parseInt(more[0]);
        			
        			if(active == 1) {
        				String voted = "Blubb";
        				//TODO
        				stat.append("_").append(wahl).append("_#");
        				stat.append(voted);
            			stat.append("#");
        			}
        		}
        		
        		
                        Popup popup = new Popup(title, title, stat.toString(), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setModern(1);
                        client.send(popup.toString());
                        return;
        		
        	}
        } else if(cmd.equals("uptime")) {
       	 	if(!client.hasPermission("cmd.uptime")) {
       	 		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
       	 		return;
       	 	}
    	        Server.get().newSysLogEntry(client.getName(), String.format("Uptime und Revision des Servers abgerufen", client.getName()));
       	 	StringBuilder uptime = new StringBuilder();
       	 	uptime.append("#Uptimes und Revisionen einzelner Server:#");
    	 
       	 	uptime.append("#Chatserver ("+Server.get().getSettings("CHAT_NAME")+")#Uptime: ").append(Server.get().timeStampToDate(Server.serverOnline)).append(" ").append(Server.get().timeStampToTime(Server.serverOnline)).append("#Revision: ").append(Settings.revision/2);
    	 
       	 	uptime.append("##--------------------##OnlineUser (Max Today): ").append(Server.get().getClients().size());
       	 	uptime.append(" (").append(Server.onlineUsers.size()).append(")#Aktuell keine akuten Bugs bekannt.");
    	 
       	 	client.sendButlerMessage(channel.getName(), uptime.toString());
        }else if(cmd.equals("img")) {
        	if(!client.hasPermission("cmd.img")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte diese Funktion folgendermaßen benutzen:#/img URL#(Externes Bild der URL anzeigen)");
        		return;
        	}
        	
        	if(!arg.startsWith("http://") && !arg.startsWith("https://")) {
        		arg = String.format("%s%s%s", Server.get().getURL(), arg.startsWith("pics/")?"":"pics/", arg);
        	}
        	
        	String argLow = arg.toLowerCase();
        	
        	if(!argLow.endsWith(".jpg") && !argLow.endsWith(".png") && !argLow.endsWith(".jpeg") && !arg.endsWith(".gif") || !URLExist(arg)) {
        		client.sendButlerMessage(channel.getName(), String.format("Das Bild _%s ist ungültig_.", KCodeParser.escape(arg)));
        		return;
        	}

        	channel.broadcastAction(">", String.format("%s sendet folgendes Bild:#°>pic.php?url=%s<°", client.getName(), arg));
                Server.get().newSysLogEntry(client.getName(), String.format("Sendet folgendes Bild: %s", arg, client.getName()));
            
        } else if(cmd.equals("rights")) {
        funktionen.rights.functionMake(client, channel, arg);
        } else if(cmd.equals("syslog")) {
        funktionen.syslog.functionMake(client, channel, arg);
        	
        } else if(cmd.equals("server")) {
        	if(!client.hasPermission("cmd.stat") && !client.hasPermission("cmd.update") && !client.hasPermission("cmd.server.werbung") && !client.hasPermission("cmd.server.butlertipps") && !client.hasPermission("cmd.server.umfragen") && !client.hasPermission("cmd.server.quiz")  && !client.hasPermission("cmd.register") && !client.hasPermission("cmd.shutdown") && !client.hasPermission("cmd.wm") && !client.hasPermission("cmd.server.badwords") && !client.hasPermission("cmd.server.butler") && !client.hasPermission("cmd.server.functions") && !client.hasPermission("cmd.server.help") && !client.hasPermission("cmd.server.syslog") && !client.hasPermission("cmd.server.macros") && !client.hasPermission("cmd.server.rights") && !client.hasPermission("cmd.server.teams") && !client.hasPermission("cmd.server.todo") && !client.hasPermission("cmd.server.toplists") && !client.hasPermission("cmd.server.elections") && !client.hasPermission("cmd.server.wordmix") && !client.hasPermission("cmd.server.mix")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	String module = "";
        	String type = "";
            String more = "";
        	String[] split = arg.split(":", 3);
        	String[] modules = new String[] {"News","BadWords","Butler", "Funktionen", "Hilfe", "Syslog", "Makros", "Rechte", "Teams", "ToDo", "Toplisten", "Umfragen", "Quiz", "Mathe", "Translate", "Wahlen", "Werbung", "ButlerTipps", "WordMix", "Mix"};
            
            try {
            	module = split[0].trim();
            	type = split[1].trim();
            	more = split[2].trim();
            } catch(Exception ex) {
            }
            
            if(module.isEmpty()) {
                StringBuilder server = new StringBuilder();
                server.append("Der Server läuft seit _").append(Server.get().getButler().getLoginTime()).append("_.##");
                
                List modulesright = new ArrayList<String>();
                
                for(int i=0;i<modules.length;i++) {
                	if(client.hasPermission(String.format("cmd.server.%s", modules[i].toLowerCase().replace("funktionen", "functions").replace("hilfe", "help").replace("makros", "macros").replace("rechte", "rights").replace("toplisten", "toplists").replace("wahlen", "elections")))) {
                	modulesright.add(modules[i]);
                        }}
                
                 for(int i=0;i<modulesright.size();i++) {
                	String modulename = (String)modulesright.get(i);
             	if(i%2 == 1) {
                			server.append("°%60°");
                		}
if (modules[i].equalsIgnoreCase("news")) {
                		server.append("°>cc/bullet_blue_outlined.png<° _°B>").append(modulename).append("|/newsedit<r°_");
    
} else {
                		server.append("°>cc/bullet_blue_outlined.png<° _°B>").append(modulename).append("|/server ").append(modulename).append(":overview<r°_");
}
                		if(i%2 == 1) {
                			server.append("°%00°#");
                		}
                	
                }
                
                server.append("##°-°");
                
                if(client.hasPermission("cmd.stat")) {
                	server.append("#°>cc/bullet_blue_outlined.png<° _°B>Chat-Statistiken anzeigen|/stat<r°_");
                }
                
                if(client.hasPermission("cmd.shutdown")) {
                	server.append("#°>cc/bullet_blue_outlined.png<° _°B>Chatserver ausschalten|/shutdown<r°_");
                }
                
                if(client.hasPermission("cmd.update")) {
                	server.append("#°>cc/bullet_blue_outlined.png<° _°B>Chatserver updaten|/update<r°_");
                }
                
                if(client.hasPermission("cmd.register")) {
                	server.append("#°>cc/bullet_blue_outlined.png<° _°B>Registration ").append(Settings.getRegistration() == 1 ? "aus":"ein").append("schalten|/register ").append(Settings.getRegistration() == 1 ? "off":"on").append("<r°_");
                }
                
                if(client.hasPermission("cmd.wm")) {
                	server.append("#°>cc/bullet_blue_outlined.png<° _°B>Wartungsmodus ").append(Settings.getMaintenance() == 1 ? "aus":"ein").append("schalten|/wm ").append(Settings.getMaintenance() == 1 ? "off":"on").append("<r°_");
                }
                
                 PopupNewStyle popup = new PopupNewStyle("Server - Übersicht", "Server - Übersicht", server.toString(), 400, 425);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
                
            }
            
            if(!module.equalsIgnoreCase("wordmix") && !module.equalsIgnoreCase("mix") && !module.equalsIgnoreCase("umfragen") && !module.equalsIgnoreCase("quiz") && !module.equalsIgnoreCase("mathe") && !module.equalsIgnoreCase("translate") && !module.equalsIgnoreCase("syslog") && !module.equalsIgnoreCase("werbung") && !module.equalsIgnoreCase("butlertipps") && !module.equalsIgnoreCase("wahlen") && !module.equalsIgnoreCase("todo") && !module.equalsIgnoreCase("teams") && !module.equalsIgnoreCase("toplisten") && !module.equalsIgnoreCase("makros") && !module.equalsIgnoreCase("hilfe") && !module.equalsIgnoreCase("funktionen") && !module.equalsIgnoreCase("badwords") && !module.equalsIgnoreCase("rechte") && !module.equalsIgnoreCase("syslog") && !module.equalsIgnoreCase("butler")) {
            	client.sendButlerMessage(channel.getName(), "Modules: BadWords, Butler, Funktionen, Hilfe, Syslog, Makros, Rechte, Teams, ToDo, Toplisten, Wahlen, Werbung, WordMix, Mix");
            	return;
            }

            if(type.equalsIgnoreCase("overview")) {
            	if(!client.hasPermission(String.format("cmd.server.%s", module.toLowerCase().replace("funktionen", "functions").replace("hilfe", "help").replace("makros", "macros").replace("rechte", "rights").replace("toplisten", "toplists").replace("wahlen", "elections")))) {
            		return;
            	}
            	
                StringBuilder server = new StringBuilder();
                server.append("_Modul_:°%30°").append(module).append("°%00°#_Einträge_:°%30°");
                
                int entries = 0;
                
                if(module.equalsIgnoreCase("wahlen")) {
                	entries = Server.elections.size();
                } else if(module.equalsIgnoreCase("werbung")) {
                	entries = Server.werbung.size();
                } else if(module.equalsIgnoreCase("butlertipps")) {
                	entries = Server.butlertips.size();
                } else if(module.equalsIgnoreCase("toplisten")) {
                	entries = Server.toplisten.size();
                } else if(module.equalsIgnoreCase("todo")) {
                	entries = Server.todo.size();
                } else if(module.equalsIgnoreCase("wordmix")) {
                	entries = Server.wordmix.size();
                } else if(module.equalsIgnoreCase("mix")) {
                	entries = Server.mix.size();
                } else if(module.equalsIgnoreCase("rechte")) {
                	entries = Server.permissions.size();
                } else if(module.equalsIgnoreCase("badwords")) {
                	entries = Server.badwords.size();
                } else if(module.equalsIgnoreCase("funktionen")) {
                	entries = Server.functions.size();
                } else if(module.equalsIgnoreCase("hilfe")) {
                	entries = Server.help.size();
                } else if(module.equalsIgnoreCase("teams")) {
                	entries = CommandParser.countChars(Settings.getTeams(), '|')/2;
                } else if(module.equalsIgnoreCase("butler")) {
                	entries = Server.james.size();
                } else if(module.equalsIgnoreCase("makros")) { 
                	entries = Server.macros.size();
                } else if(module.equalsIgnoreCase("umfragen")) { 
                	entries = Server.umfragen.size();
                } else if(module.equalsIgnoreCase("quiz")) { 
                	entries = Server.quiz.size();
                } else if(module.equalsIgnoreCase("mathe")) { 
                	entries = Server.mathe.size();
                } else if(module.equalsIgnoreCase("translate")) { 
                	entries = Server.translate.size();
                } else if(module.equalsIgnoreCase("syslog")) { 
                	entries = Server.syslog.size();
                }
                
                server.append(nf.format(entries)).append("°%00°#");

                server.append("#°>cc/bullet_blue_outlined.png<° _°B>Neuer Eintrage|/server ").append(module).append("<r°_");
                server.append("#°>cc/bullet_blue_outlined.png<° _°B>Vorhandene Eintrage|/server ").append(module).append(":entries<r°_");
            	
                String title = String.format("Server - %s - Übersicht", module);
                
              
                 Popup popup = new Popup(title, title, server.toString(), 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString());
                 return;
                	
               
            }
            
            if(type.equalsIgnoreCase("edit")) {
            	if(more.isEmpty()) {
            		return;
            	}
            	
            	if(!client.hasPermission(String.format("cmd.server.%s", module.toLowerCase().replace("funktionen", "functions").replace("hilfe", "help").replace("makros", "macros").replace("rechte", "rights").replace("toplisten", "toplists").replace("wahlen", "elections")))) {
            		return;
            	}

            	if(module.equalsIgnoreCase("wordmix")) {
            		if(Server.wordmix.contains(more)) {
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more }));
            		}
                } else if(module.equalsIgnoreCase("mix")) {
            		if(Server.mix.contains(more)) {
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more }));
            		}
                } else if(module.equalsIgnoreCase("werbung")) {
                   String text = Server.werbung.get(Integer.parseInt(more));
            		if(Server.werbung.containsKey(Integer.parseInt(more))) {
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more,text }));
            		}
                } else if(module.equalsIgnoreCase("butlertipps")) {
                   String text = Server.butlertips.get(Integer.parseInt(more));
            		if(Server.butlertips.contains(text)) {
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more,text }));
            		}
                } else if(module.equalsIgnoreCase("toplisten")) {
            		if(Server.toplisten.containsKey(more)) {
            			String[] info = Server.toplisten.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, info[0], info[1] }));
            		}
                } else if(module.equalsIgnoreCase("rechte")) {
            		if(Server.permissions.containsKey(more)) {
            			String info = Server.permissions.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, info }));
            		}
                } else if(module.equalsIgnoreCase("makros")) {
            		if(Server.macros.containsKey(more)) {
            			String info = Server.macros.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, info }));
            		}
                } else if(module.equalsIgnoreCase("funktionen")) {
            		if(Server.functions.containsKey(more)) {
            			String[] info = Server.functions.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, info[0], info[1] }));
            		}
                } else if(module.equalsIgnoreCase("butler")) {
            		if(Server.james.containsKey(more)) {
            			String info = Server.james.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, info }));
            		}
                } else if(module.equalsIgnoreCase("hilfe")) {
            		if(Server.help.containsKey(more)) {
            			String[] help = Server.help.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, help[0], help[1] }));
            		}
                } else if(module.equalsIgnoreCase("badwords")) {
                	if(Server.badwords.containsKey(more)) {
                		int juschu = Server.badwords.get(more);
            		
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more, String.valueOf(juschu) }));
                	}
                } else if(module.equalsIgnoreCase("todo")) {
                	if(Server.todo.containsKey(more)) {
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more }));
                	}
                } else if(module.equalsIgnoreCase("teams")) {
                	if(Settings.getTeams().contains(String.format("|%s|", more))) {
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more }));
                	}
                } else if(module.equalsIgnoreCase("wahlen")) {
                	if(Server.elections.containsKey(more)) {
                		String[] infos = Server.elections.get(more);
            		
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more, infos[0], infos[1], infos[2], infos[3] }));
                	}
                } else if(module.equalsIgnoreCase("syslog")) {
            		if(Server.syslog.containsKey(more)) {
            			String[] syslog = Server.syslog.get(more);
            		
                    	client.send(PacketCreator.createServerPopup(true, module, new String[] { more, syslog[0], syslog[1] }));
            		}
                } else if(module.equalsIgnoreCase("umfragen")) {
                	if(Server.umfragen.containsKey(more)) {
                		String infos = Server.umfragen.get(more);
            		
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more, infos }));
                	}
                         } else if(module.equalsIgnoreCase("quiz")) {
                	if(Server.quiz.containsKey(more)) {
                		String infos = Server.quiz.get(more);
            		
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more, infos }));
                	}
                         } else if(module.equalsIgnoreCase("mathe")) {
                	if(Server.mathe.containsKey(more)) {
                		String infos = Server.mathe.get(more);
            		
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more, infos }));
                	}
                         } else if(module.equalsIgnoreCase("translate")) {
                	if(Server.translate.containsKey(more)) {
                		String infos = Server.translate.get(more);
            		
                		client.send(PacketCreator.createServerPopup(true, module, new String[] { more, infos }));
                	}
                        
                }
            	
            	return;
            }
            
            if(type.equalsIgnoreCase("entries")) {
            	if(!client.hasPermission(String.format("cmd.server.%s", module.toLowerCase().replace("funktionen", "functions").replace("hilfe", "help").replace("makros", "macros").replace("rechte", "rights").replace("toplisten", "toplists").replace("wahlen", "elections")))) {
            		return;
            	}
            	
                StringBuilder server = new StringBuilder();
                
                if(module.equalsIgnoreCase("toplisten")) {
                	for(String name : Server.toplisten.keySet()) {
                		server.append(name).append("°%86°°>edit.gif<>|/server Toplisten:edit:").append(name).append("<° °>del.png<>|/server Toplisten:delete:").append(name).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("werbung")) {
                	for(int id : Server.werbung.keySet()) {
                            String text = Server.werbung.get(id);                           
                		server.append(text).append("°%86°°>edit.gif<>|/server Werbung:edit:").append(id).append("<° °>del.png<>|/server Werbung:delete:").append(id).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("butlertipps")) {
                	 for (int i=0; i<Server.butlertips.size(); i++) {
                            String text = Server.butlertips.get(i);                           
                		server.append(text).append("°%86°°>edit.gif<>|/server ButlerTipps:edit:").append(i).append("<° °>del.png<>|/server ButlerTipps:delete:").append(i).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("wahlen")) {
                	for(String wahlname : Server.elections.keySet()) {
                		server.append(wahlname).append("°%86°°>edit.gif<>|/server Wahlen:edit:").append(wahlname).append("<° °>del.png<>|/server Wahlen:delete:").append(wahlname).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("wordmix")) {
                	for(String sentence : Server.wordmix) {
                		server.append(sentence).append("°%86°°>edit.gif<>|/server WordMix:edit:").append(sentence).append("<° °>del.png<>|/server WordMix:delete:").append(sentence).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("mix")) {
                	for(String sentence : Server.mix) {
                		server.append(sentence).append("°%86°°>edit.gif<>|/server Mix:edit:").append(sentence).append("<° °>del.png<>|/server Mix:delete:").append(sentence).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("rechte")) {
                	for(String permission : Server.permissions.keySet()) {
                		server.append(permission).append("°%86°°>edit.gif<>|/server Rechte:edit:").append(permission).append("<° °>del.png<>|/server Rechte:delete:").append(permission).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("teams")) {
                	for(String team : Settings.getTeams().split("\\|")) {
                		if(!team.isEmpty()) {
                    		server.append(team).append("°%86°°>edit.gif<>|/server Teams:edit:").append(team).append("<° °>del.png<>|/server Teams:delete:").append(team).append("<°°%00°#");
                		}
                	}
                } else if(module.equalsIgnoreCase("todo")) {
                	for(String todo : Server.todo.keySet()) {
                		server.append(todo).append("°%86°°>edit.gif<>|/server ToDo:edit:").append(todo).append("<° °>del.png<>|/server ToDo:delete:").append(todo).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("badwords")) {
                	for(String badword : Server.badwords.keySet()) {
                		server.append(badword).append("°%86°°>edit.gif<>|/server BadWords:edit:").append(badword).append("<° °>del.png<>|/server BadWords:delete:").append(badword).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("funktionen")) {
                	for(String function : Server.functions.keySet()) {
                		server.append(function).append("°%86°°>edit.gif<>|/server Funktionen:edit:").append(function).append("<° °>del.png<>|/server Funktionen:delete:").append(function).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("hilfe")) {
                	for(String help : Server.help.keySet()) {
                		server.append(help).append("°%86°°>edit.gif<>|/server Hilfe:edit:").append(help).append("<° °>del.png<>|/server Hilfe:delete:").append(help).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("butler")) {
                	for(String word : Server.james.keySet()) {
                		server.append(word).append("°%86°°>edit.gif<>|/server Butler:edit:").append(word).append("<° °>del.png<>|/server Butler:delete:").append(word).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("makros")) {
                	for(String macro : Server.macros.keySet()) {
                		server.append(macro).append("°%86°°>edit.gif<>|/server Makros:edit:").append(macro).append("<° °>del.png<>|/server Makros:delete:").append(macro).append("<°°%00°#");
                	}
                }else if(module.equalsIgnoreCase("syslog")) {
                        for(String syslog : Server.syslog.keySet()) {
                            String[] infos = Server.syslog.get(syslog);
                                server.append(infos[1]).append("°%86°°>edit.gif<>|/server Syslog:edit:").append(syslog).append("<° °>del.png<>|/server Syslog:delete:").append(syslog).append("<°°%00°#");
                        }
                } else if(module.equalsIgnoreCase("umfragen")) {
                	for(String macro : Server.umfragen.keySet()) {
                		server.append(macro).append("°%86°°>edit.gif<>|/server Umfragen:edit:").append(macro).append("<° °>del.png<>|/server Umfragen:delete:").append(macro).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("quiz")) {
                	for(String macro : Server.quiz.keySet()) {
                		server.append(macro).append("°%86°°>edit.gif<>|/server Quiz:edit:").append(macro).append("<° °>del.png<>|/server Quiz:delete:").append(macro).append("<°°%00°#");
                	}
                } else if(module.equalsIgnoreCase("mathe")) {
                	for(String macro : Server.mathe.keySet()) {
                		server.append(macro).append("°%86°°>edit.gif<>|/server Mathe:edit:").append(macro).append("<° °>del.png<>|/server Mathe:delete:").append(macro).append("<°°%00°#");
                	}
                 } else if(module.equalsIgnoreCase("translate")) {
                	for(String macro : Server.translate.keySet()) {
                		server.append(macro).append("°%86°°>edit.gif<>|/server Translate:edit:").append(macro).append("<° °>del.png<>|/server Translate:delete:").append(macro).append("<°°%00°#");
                	}
                }
                
                String title = String.format("Server - %s - Vorhandene Einträge", module);
                
                
                 Popup popup = new Popup(title, title, server.toString(), 500, 350);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setNicknotexist(1);
                 client.send(popup.toString());
                 return;
               
            }
            
            if(type.equalsIgnoreCase("delete")) {
            	if(!client.hasPermission(String.format("cmd.server.%s", module.toLowerCase().replace("funktionen", "functions").replace("hilfe", "help").replace("makros", "macros").replace("rechte", "rights").replace("toplisten", "toplists").replace("wahlen", "elections")))) {
            		return;
            	}
            	
            	if(module.equalsIgnoreCase("rechte")) {
            		if(Server.permissions.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.permissions.remove(more);
            			Server.get().query(String.format("DELETE FROM `permissions` WHERE `permission` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("werbung")) {
            		if(Server.werbung.containsKey(Integer.parseInt(more))) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.werbung.remove(Integer.parseInt(more));
            			Server.get().query(String.format("DELETE FROM `werbung` WHERE `id` = '%s'", more));
            		}
                } else if(module.equalsIgnoreCase("butlertipps")) {
            		if(Server.butlertips.contains(Integer.parseInt(more))) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.butlertips.remove(Integer.parseInt(more));
            			Server.get().query(String.format("DELETE FROM `butler_tipps` WHERE `id` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("toplisten")) {
            		if(Server.toplisten.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.toplisten.remove(more);
            			Server.get().query(String.format("DELETE FROM `toplisten` WHERE `word` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("hilfe")) {
            		if(Server.help.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.help.remove(more);
            			Server.get().query(String.format("DELETE FROM `help` WHERE `word` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("badwords")) {
            		if(Server.badwords.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.badwords.remove(more);
            			Server.get().query(String.format("DELETE FROM `badword` WHERE `text` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("teams")) {
            		if(Settings.getTeams().contains(String.format("|%s|", more))) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Settings.removeTeam(more);
            		}
            	} else if(module.equalsIgnoreCase("todo")) {
            		if(Server.todo.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.todo.remove(more);
            			Server.get().query(String.format("DELETE FROM `todo` WHERE `text` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("wahlen")) {
            		if(Server.elections.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.elections.remove(more);
            			Server.get().query(String.format("DELETE FROM `wahlen` WHERE `name` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("funktionen")) {
            		if(Server.functions.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.functions.remove(more);
            			Server.get().query(String.format("DELETE FROM `functions` WHERE `function` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("wordmix")) {
            		if(Server.wordmix.contains(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.wordmix.remove(more);
            			Server.get().query(String.format("DELETE FROM `wordmix` WHERE `SENTENCE` = '%s'", more));
            		}
                        } else if(module.equalsIgnoreCase("mix")) {
            		if(Server.mix.contains(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.mix.remove(more);
            			Server.get().query(String.format("DELETE FROM `game_mix` WHERE `SENTENCE` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("makros")) {
            		if(Server.macros.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.macros.remove(more);
            			Server.get().query(String.format("DELETE FROM `macros` WHERE `macro` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("butler")) {
            		if(Server.james.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.james.remove(more);
            			Server.get().query(String.format("DELETE FROM `butler` WHERE `word` = '%s'", more));
            		}
                } else if(module.equalsIgnoreCase("syslog")) {
            		if(Server.syslog.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.syslog.remove(more);
            			Server.get().query(String.format("DELETE FROM `syslog` WHERE `text` = '%s'", more));
            		}
            	} else if(module.equalsIgnoreCase("umfragen")) {
            		if(Server.umfragen.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.umfragen.remove(more);
            			Server.get().query(String.format("DELETE FROM `umfragen` WHERE `frage` = '%s'", more));
            		}
                        } else if(module.equalsIgnoreCase("quiz")) {
            		if(Server.quiz.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.quiz.remove(more);
            			Server.get().query(String.format("DELETE FROM `quiz` WHERE `sentence` = '%s'", more));
            		}
                        } else if(module.equalsIgnoreCase("mathe")) {
            		if(Server.mathe.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.mathe.remove(more);
            			Server.get().query(String.format("DELETE FROM `mathe` WHERE `sentence` = '%s'", more));
            		}
                        } else if(module.equalsIgnoreCase("translate")) {
            		if(Server.translate.containsKey(more)) {
            			String title = String.format("Server - %s - Eintrag gelöscht", module, more);
            			client.send(PopupNewStyle.create(title, title, String.format("Der Eintrag %s wurde gelöscht.", more), 450, 275));
            			Server.translate.remove(more);
            			Server.get().query(String.format("DELETE FROM `translate` WHERE `sentence` = '%s'", more));
            		}
            	}
            	
            	return;
            }

        	if(!client.hasPermission(String.format("cmd.server.%s", module.toLowerCase().replace("funktionen", "functions").replace("hilfe", "help").replace("makros", "macros").replace("rechte", "rights").replace("toplisten", "toplists").replace("wahlen", "elections")))) {
        		return;
        	}
        	
            client.send(PacketCreator.createServerPopup(false, module, new String[] { "", "", "", "", "", "", "", "" }));
        } else if(cmd.equals("clearcomments")) {
        funktionen.clearcomments.functionMake(client, channel, arg);
        } else if(cmd.equals("clearcmcomments")) {
        funktionen.clearcmcomments.functionMake(client, channel, arg);	
       
                
                
                
                
                
                
                }  else if (cmd.equals("w")){
                if (!client.hasPermission("cmd.w")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }
                 client.setLevelInfo("w",1);
                String tab = "Start";
             
                if (arg.indexOf(":") != -1 && !arg.trim().endsWith(":")) {
                tab = arg.split(":")[1];
                arg = arg.split(":")[0];
                }
               
                if (tab.isEmpty()) {
                    tab = "Start";
                }
               
                Client target;
                String nickname = KCodeParser.escape(arg);
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
                
                if (target.checkIgnored(client.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("Du wirst von %s ignoriert und kannst deshalb die Whois nicht einsehen.", target.getName()));
                    return;
                }
             
                
                 if  (client.getWStyle() == 1) {               
  w2.showPopup(client, target, channel,online,tab);                
                }
                
                
                
                
                
             if  (client.getWStyle() == 2) {
       
             
                int eE = 1, eE2 = 1, dp = 1, dp2 = 1, spaces = 0;
                NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
                nickname = target.getName();
                String charNick = nickname.replace("<", "\\<");
                StringBuilder whois = new StringBuilder();
                KTab content = new KTab(0);
                String extended = "";
     
                if (target.getGender() == 1) {
                    extended += "°>male5.png<°";
                } else if (target.getGender() == 2) {
                    extended += "°>female5.png<°";
                }
     
                if (target.getGender() >= 1 && target.getGender() <= 2 && target.getAge() != 0) {
                    extended += " ";
                }
     
                if (target.getAge() != 0) {
                    extended += "§_°20°(" + target.getAge() + ")§";
                }
     
                String title = "°>iwhois_" + (online ? "on" : "off") + "_button.png<° §_°20°" + nickname + extended + "§#";
                String readme = target.getReadme();
                StringBuilder cls = new StringBuilder();
                StringBuilder mutes = new StringBuilder();
                StringBuilder beschwerden = new StringBuilder();
                String photo = target.getPhoto();
                int knuddels = (int) target.getKnuddels();
                int luftlinie = 0;
     
                if (readme == null) {
                    readme = "";
                } else if (!readme.equals("")) {
                                      readme = "_°>_hReadme|/readmehis " + nickname + "<°:_ " + readme + "#";
 
 
                }
     
                // Profil
                StringBuilder profile = new StringBuilder();
     
                // Short Admin Info
                if (channel.checkCm(client.getName()) || channel.checkHz(client.getName()) || client.getRank() > 2 || client.getTeams().contains("~1|") || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz")) {
                    for (Channel s : Server.get().getChannels()) {
                        if (target.checkCl(s)) {
                            cls.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_ von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
     
                        if (Server.get().checkCcm(target.getName(), s, 2)) {
                            mutes.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" (Color) von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
     
                        if (Server.get().checkCcm(target.getName(), s, 3)) {
                            mutes.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
                    }
     
                    if (target.getDisable() != 0) {
                        if (client.getRank() > 6 && !client.checkTeam("Vertrauensadmin")) {
                            profile.append("°R°_°12°Nick ist derzeit deaktiviert_##");
                        }
                    }
     
                    if (target.getDeletenick() != 0) {
                        if (client.getRank() > 3) {
                            profile.append("°R°_°12°Nick ist zur Löschung freigegeben!_##");
                        }
                    }
     
                    if (target.getSpielsperre() != 0) {
                        profile.append("°R°_°12°Momentan für alle Spiele GESPERRT_##");
                    }
     
                    if (target.getWahlsperre() != 0) {
                        profile.append("°R°_°12°Bis zum ").append(Server.get().timeStampToDate(target.getWahlsperre())).append(" °12°für alle Wahlen GESPERRT_##");
                    }
                   
                     if(target.getSperre() == 1) {
                             if(client.getRank() > 3 || channel.checkHz(client.getName()) || client.hasPermission("cmd.append.header.admin")) {
                        profile.append("°R°_").append(target.getName());
                                    profile.append("°R° ist von ").append(target.getSperrevon()).append(" °B°Permanent°R° gesperrt_");          
                               profile.append("#_Begründung:#").append(target.getSperreinfo()).append("_");
                            } else {
                               profile.append("°R°_").append(target.getName());
                                    profile.append("°R° ist derzeit gesperrt_");          
                             }
                           
                           
                            whois.append("##");
                    }
     
              if(target.getSperre() > 1) {
                        if(client.getRank() > 3 || channel.checkHz(client.getName()) || client.hasPermission("cmd.append.header.admin")) {
                        profile.append("°R°_").append(target.getName());
                                    profile.append("°R° ist von ").append(target.getSperrevon()).append(" bis zum °B°").append(Server.get().timeStampToDate(target.getSperre())).append("°R° gesperrt_");
                    profile.append("#_Begründung:#").append(target.getSperreinfo()).append("_##");            
                        } else {
                           profile.append("°R°_").append(target.getName());
                                    profile.append("°R° ist derzeit gesperrt_");
                                   
                    }
                       
                    }
                         
                       
                   
     
                    if (!cls.toString().isEmpty()) {
                        profile.append("°B°_°12°Channellocks_:").append(cls.toString()).append("##");
                    }
     
                    if (!mutes.toString().isEmpty()) {
                        profile.append("°B°_°12°Gemutet_:").append(mutes.toString()).append("##");
                    }
     
                    profile.append("°r°_");
                }
     
                profile.append("°>LEFT<>{table|150|w1}<°");
     
                profile.append("#°>{imageboxstart}boxS.my_-4.mh_30<>{table|150|w1}<°°>center<°#");
         
               // Channel-ON/OFF-Anzeige über Foto
               profile.append("#°>center<°");
               if (online) {
                    profile.append("°>w2/w2_group_on.png<12° ");
                 if (target.getChannel() == null || !target.getChannel().isVisible()) {
                        profile.append("?");
                    } else {
                        profile.append("°BB°°>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<r°#");
                    }
                 } else {
                    if (target.getLastOnlineChannel() == null) {
                        profile.append("°>w2/w2_group_off.png<12°").append("Niemals online.°%00°##");
                    } else {
                        profile.append("°>w2/w2_group_off.png<12°");
                        Channel lastchannel = Server.get().getChannel(target.getLastOnlineChannel());
     
                        if (lastchannel == null || !lastchannel.isVisible()) {
                            profile.append("?##");
                         } else {
                            profile.append("°BB°°>_h").append(target.getLastOnlineChannel()).append("|/go \"|/go +\"<°§°12°_#");
                        }
                }
                 profile.append(target.getLastOnlineDate()).append(" ").append(target.getLastOnlineTime()).append("#");
                    }
               if (target.isAway()) {
                        if (target.hasPermission("popup.awayaniicon")) {
                            profile.append(" °>icon_away_ani_new.gif<°");
                        } else {
                            profile.append(" °>away.png<°");
                        }
                    }
               profile.append("#");
    // Picture
                if (!photo.isEmpty()) {
                    // PIC
                    String[] ext = photo.split(".");
                    String e;
                    if (ext.length >= 1 && ext[1] != null) {
                        e = "." + ext[1];
                    } else {
                        e = ".jpg";
                    }
                       
                    profile.append("_°B>photos/photo/getPicture.php?m&img=").append(photo).append("...center_140.border_3.shadow_4.jpg<>--<>|").append(Server.get().getURL()).append("index.php?page=photo_user&n=").append(charNick).append("&photo<°");
                   // profile.append("_°B>photos/m/").append(photo.replace(e, "")).append("...center_140.border_3.shadow_4" + e + "<>--<>|").append(Server.get().getURL()).append("photos-profile.php?id=").append(charNick).append("<°");
                } else {
                    profile.append("_°B>nopic_79x79_" + (target.getGender() == 2 ? "f" : "m") + "...center_140.border_3.shadow_4.jpg<°");
                }
     
                if (!photo.isEmpty() && target.getPhoto_verify() == 1) {
                    profile.append("°>w2/fv_checked...w_0.h_0.mx_-40.my_-10.vtop.png<°");
                }
     
               if (target.getName() == client.getName()) {
                   profile.append("°>left<°###°BB°°12°°>w2/actionmenu_edit.png<° _°>Profil bearbeiten|/e<°_");
               profile.append("#°>w2/actionmenu_foto.png<° _°>Foto hochladen|/foto<°_");
              profile.append("#°>w2/actionmenu_smiley.png<° _°>Smiley kaufen|/shop<°");
               } else {
                  profile.append("###°BB°°12°°>left<°°>w2/actionmenu_message.png<° _°>Anflüstern|/m ").append(target.getName()).append("<°_");
               profile.append("#°>left<°°>w2/actionmenu_add.png<° _°>Freund hinzufügen|/f ").append(target.getName()).append("<°_");
              profile.append("#°>w2/actionmenu_smiley.png<° _°>Smiley schenken|/code send<°#°>left<°");
               }
     
            profile.append("°>left<°##§°12°_ °>gt.gif<°°BB° ").append(target.getAge()).append(" Jahre");
               if (target.getGender() == 1) {
                    profile.append(" °>male.png<°#");
                } else if (target.getGender() == 2) {
                    profile.append(" °>female.png<°#");
                }
               profile.append("°>left<°");
                profile.append("§°12°_ °>gt.gif<°°BB° ").append(target.getStadt()).append("_#");
               profile.append("§°12°_ °>gt.gif<°°>BB° ").append(target.getVergeben()).append("_#");
              profile.append("°-° °>gt.gif<° ");
              profile.append(target.getRankLabel(target.getRank()).replace("Ehrenmitglied", "_°BB°°>_hEhrenmitglied|/h ehrenmitglied<°§_").replace("inoffizieller Admin", "°BB°_°>_hEhrenmitglied|/h ehrenmitglied<°_").replace("Familymitglied", "_Familymitglied_").replace("Mitglied", "_Mitglied_").replace("Sysadmin", "_°R°°>_hSysadmin|/h sysadmin<°_§").replace("Stammi", "_°BB°°>_hStammi|/h stammi<°_§").replace("Admin", "_°R°°>_hAdmin|/h admin<°_§"));
              profile.append("§#_°12°°[128,128,128]° Reg.:§°10° ");
               if (target == Server.get().getButler()) {
                    profile.append("21.05.1935_ um 11:11:11");
                } else {
                    profile.append(target.getRegistrationDate()).append(" ").append(target.getRegistrationTime());
                }
               profile.append("§#_°12°°[128,128,128]° Online:§°12°°BB°_ ");
               profile.append(nf.format(target.getOnlineTime() / 60));
               profile.append("#§_°12°°[128,128,128]° Knuddels:§°12°°BB°_ ");
               if(knuddels<1) {
                    profile.append("0");
                } else {
                    profile.append("°>_h").append(nf.format(knuddels)).append("|/h knuddels<°");
                }
               profile.append("#    °>{imageboxend}<#");
            // Shortinfo
                profile.append("§°>{tc}<r12°");
     
               
     // Main Info
          if (!target.getAuctionEnd().isEmpty()) {              
                    profile.append("°[229,141,15,179]°°>{colorboxstart}<°°bir°°12°#°+9502°°>CENTER<°°W°_"+target.getName()+" wird versteigert! (noch "+target.getRestdauerAuction()+")_");
                  if (!target.getLastBieter().isEmpty()) {
                    profile.append("#_Höchstgebot: "+target.getLastBieter().split("~")[0]+"°>sm_classic_yellow...h_0.gif<° von _°[0,53,217]°_°>_h"+target.getLastBieter().split("~")[1]+"|/w "+target.getLastBieter().split("~")[1]+"<°°b°°K°");
                  }
                    if (target != client) {
                    profile.append("#_°[0,53,217]°°>Jetzt mitbieten!|/auctionme "+target.getName()+"<°°°_");
                  }
                   profile.append("#°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°#°r°°>left<°##°13°");
                }  
                   
               
               
               // Wildspace-Bereich
            /*    profile.append("°>left<°");
                if(target.getWildspace() != null) {
                    profile.append(target.getWildspace());
                profile.append("°>left<°");
                profile.append("#°>left<°°%00°##°>cc/bar_greyStrong...clipw_385.w_0.mx_-1.png<°#");
                } */
               
                // Wildspace-Ende
               
                profile.append("°%00°");
                   
                    // Teams Anzeige wenn Online
                if(target.getRank() > 0) {
                 if (countWords(target.getCareer(), target.getRankLabel(target.getRank())) > 1) {
                        profile.append("§°%00°°>left<°°13°Wieder°13° ");
                    }
     
                    profile.append("§°%00°°>left<°°13°").append(target.getRankLabel(target.getRank()).replace("Mitglied", "_Mitglied_").replace("Ehrenmitglied", "_Ehrenmitglied_").replace("Sysadmin", "_°R°Sysadmin_§").replace("Stammi", "_Stammi_§").replace("inoffizieller Admin", "_Ehrenmitglied_").replace("Admin", "_°R°Admin_§")).append(" °13°seit ").append(target.getDate() == null ? "?" : target.getDate()).append(".#°13°");    
                }
                    StringBuilder hz = new StringBuilder();
               
                for(Channel c : Server.get().getChannels()) {
                    if(c.isVisible()) {
                            if(c.checkHz(target.getName())) {
                                    hz.append(eE2!=1?", ":"").append("°>_h").append(c.getName()).append("|/go \"|/go +\"<°");
                                    eE2++;
                            }
                    }
                }
                   
                    StringBuilder cm2 = new StringBuilder();
               
                for(Channel c : Server.get().getChannels()) {
                    if(c.isVisible()) {
                            if(c.checkCm(target.getName())) {
                                    cm2.append(eE!=1?", ":"").append("°BB>_h").append(c.getName()).append("|/go \"|/go +\"<°");
                                    eE++;
                            }
                    }
                }
                   
                 if (!target.getTeams().isEmpty() || !cm2.toString().isEmpty() || !hz.toString().isEmpty()) {
                       
     profile.append("°[176,226,255]°°>{colorboxstart}<°°bir°°10°°+9502°°>CENTER<°°W°_°12°°[128,128,128]°");
                     }
                   
                   
                   
               
                if(!cm2.toString().isEmpty()) {
                    profile.append("°W°°12°°[128,128,128]°°>left<°°%00°°%00°°>bullet.b.png<° ").append("°>cm.png<° im Channel ").append(cm2.toString());
                    }
               
                if(!cm2.toString().isEmpty()) {
                if(!target.getCmwhen().isEmpty()) {
                    byte months = (byte) (CommandParser.countChars(target.getCmwhen(), ',')+1);
                    profile.append("°W°°12°°[128,128,128]° (");
                   
                    if(months == 1) {
                            profile.append("°W°°12°°[128,128,128]°Ein Monat)");
                    } else {
                            profile.append(months).append(" Monate)");
                    }
                }
                }
                   
                   
    if(target.getTeams().contains("~1") || target.getTeams().contains("~2") || target.getTeams().contains("~4") || target.getTeams().contains("~5")) {
        profile.append("#§°[128,128,128]°°>left<°°>bullet.b.png<°°12° _Teamleiter:°%18°");
        String eingabe = target.getTeams().replace("||", "<").replace("|", "");
                        String[] strarr = eingabe.split("<");
     
                        Arrays.sort(strarr);
                        for (int i = 0; i < strarr.length; i++) {
                            String[] x = strarr[i].split("~");
                            String team = x[0];
                            String extra = x[1];
     
                            if (extra.equals("1") || extra.equals("2") || extra.equals("4") || extra.equals("5")) {
                            profile.append(dp != 1 ? ", " : "").append("°BB>_h").append(team);
                            profile.append("|/h ").append(team).append("-team<°");
                            dp++;
                        }
                        }
    }
     
     
     
    if(target.getTeams().contains("~0") || target.getTeams().contains("~3") || target.getTeams().contains("~6")) {
        profile.append("°W°°12°°[128,128,128]°°>left<°°>left<°°%00°#°>bullet.b.png<° Teams:°%18°");
    String eingabe = target.getTeams().replace("||", "<").replace("|", "");    
    String[] strarr = eingabe.split("<");
     
                    Arrays.sort(strarr);
                       for (int i = 0; i < strarr.length; i++) {
                            String[] x = strarr[i].split("~");
                            String team = x[0];
                            String extra = x[1];
                         
     
                            if (extra.equals("0") || extra.equals("3") || extra.equals("6")) {
                          profile.append(dp2 != 1 ? ", " : "").append("°BB>_h").append(team);
                            profile.append("|/h ").append(team).append("-team<°");
                            dp2++;
                        }
                        }
    }
     
     
               
               
                if(!hz.toString().isEmpty()) {
                    profile.append("§_°W°°12°°[128,128,128]°°>left<°°%00°#°>bullet.b.png<° ").append(target.getRank()>5?"HZA":"HZE").append(":°%18°°BB°").append(hz.toString()).append("°%00°");
                    }
               
                profile.append("#°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°#°r°°>left<°_");
                   profile.append("°%00°°>left<°#°12°");
     
             // ---------- FEATURE-BEREICH ----------------
                   
                      StringBuilder holdhands = new StringBuilder();
                int h = 1;
               
                    for(String hold : target.getHoldHands().split("\\|")) {
                            if(!hold.isEmpty()){
                                    if(h != 1){
                                            holdhands.append(", ");
                                    }
                                   
                                    holdhands.append("_°BB>_h").append(hold.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_");
                                    h++;
                            }  
                    }
                   
                String holdhandsToString = holdhands.toString();
               
                if(!holdhandsToString.isEmpty()) {
                    char gend = 'm';
                   
                    if(target.getGender() == 2){
                            gend = 'f';
                    }
                   
                    profile.append("°>holdhands_").append(gend).append("...b.my_7.h_21.png<° _°R°Händchen gehalten°r°°12°_ mit ").append(holdhandsToString).append("#");
                }
               
               
                 if (target.getTurnedHeadFrom().equals(target.getTurnedHeadTo().replace("|","")) && !target.getTurnedHeadFrom().isEmpty()) {
                  Client target2 = Server.get().getClient(target.getTurnedHeadFrom());
                        if (target2 == null) {
                    target2 = new Client(null);
                    target2.loadStats(target.getTurnedHeadFrom());                
                }
                      int lastgender = target2.getGender();
                    String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                     
                     
                    profile.append("°12°°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° und °[0,53,217]°_°>_h"+target.getTurnedHeadFrom()+"|/w "+target.getTurnedHeadFrom()+"<°°b°°K° haben sich gegenseitig den Kopf verdreht.#");
                } else {
               
                if (!target.getTurnedHeadFrom().isEmpty()) {
                   
                    Client target2 = Server.get().getClient(target.getTurnedHeadFrom());
                        if (target2 == null) {
                    target2 = new Client(null);
                    target2.loadStats(target.getTurnedHeadFrom());                
                }
                      int lastgender = target2.getGender();
                     String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                     profile.append("°12°°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° wurde von °[0,53,217]°_°>_h"+target.getTurnedHeadFrom()+"|/w "+target.getTurnedHeadFrom()+"<°°b°°K°  der Kopf verdreht.#");
                   
                }
                if (!target.getTurnedHeadTo().isEmpty()) {
                    int lastgender = 0;
                    int i3 = 0;
                   StringBuilder head = new StringBuilder();              
                for(String v : target.getTurnedHeadTo().split("\\|")) {
                    if (!v.isEmpty()) {
                        if (i3 != 0) {
                            head.append(", ");
                        }
                        head.append("°[0,53,217]°_°>_h"+v+"|/w "+v+"<°°b°°K°");
                        Client target2 = Server.get().getClient(v);
                        if (target2 == null) {
                    target2 = new Client(null);
                    target2.loadStats(v);                
                }
                        lastgender = target2.getGender(); // fehlt
                        i3++;
                       
                    }
                }
                   
                         String image = ""; if (target.getGender() == 2) { if (lastgender == 2) { image = "ff"; } else { image = "mf"; }} else { if (lastgender == 2) { image = "fm"; } else { image = "mm"; }}
                       
                     
                   
                    profile.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° hat "+replaceLast(head.toString(),", "," und ")+" den Kopf verdreht.#");
                }}
               
               
                if (!target.getZusammen().isEmpty()) {
                    profile.append("°12°°>loveletter_whois...b.my_4.h_20.png<°").append(charNick).append(" ist fest zusammen mit °>_h").append(target.getZusammen().replace("<", "\\<")).append("|/serverpp \"|/w \"<°.#");
                }
               
               
               
               
                 if (!target.getHeroToday().isEmpty()) {
                    profile.append(charNick).append(" ist der Held von °>_h").append(target.getHeroToday().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°#");
                }
                 
                   
             /*      if (!target.getHeroToday2().isEmpty()) {
                      if(target == client) {
                            whois.append("Du hast");
                    } else {
                            if(target.getGender() == 1) {
                                    whois.append("Er hat");
                            } else {
                                    whois.append("Sie hat");
                            }
                    }
                     
                      whois.append(" °>_h").append(target.getHeroToday2().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append(" zu ");
                     
                     
                     
                      if(target == client) {
                            whois.append("deinem ").append("Held des Tages ").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°").append(" ernannt.#");
                 
                    } else {
                            if(target.getGender() == 1) {
                                    whois.append("seinem ").append("Held des Tages ").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°").append(" ernannt.#");
                 
                            } else {
                                    whois.append("ihrem ").append("Held des Tages ").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°").append(" ernannt.#");
               
                            }
                    }
                     
                  }
                */
                if(target.getStarsFrom() != null) {
                    if(target == client) {
                            profile.append("Dir");
                    } else {
                            if(target.getGender() == 1) {
                                    profile.append("Ihm");
                            } else {
                                    profile.append("Ihr");
                            }
                    }
                   
                    profile.append(" wurden von °>_h").append(target.getStarsFrom().replace("<", "\\<")).append("|/serverpp \"|/w \"<° die Sterne vom Himmel geholt! °>starlite_whois-bg...b.my_18.h_10.mx_-10.w_38.png<>starlite_whois-ani...b.my_18.h_10.mx_-48.w_0.gif<°#");
                }
               
                if(target.getStarsTo() != null) {
                    if(target == client) {
                            profile.append("Du hast ");
                    } else {
                            profile.append(target.getGenderLabel()).append(" hat ");
                    }
                   
                    profile.append("°>_h").append(target.getStarsTo().replace("<", "\\<")).append("|/serverpp \"|/w \"<° die Sterne vom Himmel geholt! °>starlite_whois-bg...b.my_18.h_10.mx_-10.w_38.png<>starlite_whois-ani...b.my_18.h_10.mx_-48.w_0.gif<°#");
                }
               
                if(target.getDream() != null) {
                    if(target == client) {
                            profile.append("Du");
                    } else {
                            profile.append(target.getGenderLabel());
                    }
                   
                    profile.append("°>icon_dreamof...b.mx_-3.w_15.my_4.h_21.gif<° träum");
                   
                    if(target == client) {
                            profile.append("s");
                    }
                   
                    profile.append("t von °>_h").append(target.getDream().replace("<", "\\<")).append("|/serverpp \"|/w \"<°.#");
                }
                   
                if(target.getFriends() != null) {
                    if(target == client) {
                            profile.append("Du");
                    } else {
                            profile.append(target.getGenderLabel());
                    }
                   
                    profile.append(" und °>_h").append(target.getFriends().replace("<", "\\<")).append("|/serverpp \"|/w \"<° - _Freunde für immer_. °>icon_friendsforever...b.my_6.mx_-4.w_29.h_14.gif<°#");
                }
               
               
               
                if (!target.getAuctionTo().isEmpty()) {
       
            int i = 0;
                   StringBuilder auction = new StringBuilder();              
                for(String v : target.getAuctionTo().split("\\|")) {
                    if (!v.isEmpty()) {
                        if (i != 0) {
                           auction.append(", ");
                        }
                        auction.append("°[0,53,217]°_°>_h"+v+"|/w "+v+"<°°b°°r°");
                        i++;
                       
                    }}
                   
    profile.append("°>features/auctionme/auction_profile-start...h_16.png<° °[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°r° hat "+replaceLast(auction.toString(),", ", " und ")+" ersteigert.#");
           
            }
    if (!target.getAuctionFrom().isEmpty()) {
    profile.append("°>features/auctionme/auction_profile-start...h_16.png<° Ersteigert von °[0,53,217]°_°>_h"+target.getAuctionFrom()+"|/w "+target.getAuctionFrom()+"<°°b°°r°.#");
    }
               
                 
                    // ------------- FEATURE-ENDE ---------------
                   
                  profile.append("°>left<°°%00°##°>cc/bar_greyStrong...clipw_385.w_0.mx_-1.png<°#");
                 if(!target.getProfile().isEmpty()) {
                for(String out : target.getProfile().split("\\|")) {
                        if(!out.isEmpty()) {
                                String datum = out.split(" ")[0];
                                String text = out.split(" ", 2)[1];
                       
                                profile.append(text).append(" (").append(datum).append(")#");
                        }
                }
                }
               
                  String getheart = target.getHeart();
                if(!getheart.isEmpty()) {
                    if(target.receivedHearts.contains(target.getHeart())) {
                    profile.append("°13°°>fullheart.png<° _Herztausch_ mit °>_h").append(getheart.replace("<", "\\<")).append("|/serverpp \"|/w \"<°.°>left<°#");
                    } else {
                       if(target == client) {
                            profile.append("°13°°>fullheart.png<° Du hast dein");
                    } else {
                            profile.append("°>fullheart.png<° ").append(target.getGenderLabel()).append(" hat ");
                           
                            if(target.getGender() == 1) {
                                    profile.append("sein");
                            } else {
                                    profile.append("ihr");
                            }
                       }
                   profile.append(" _Herz_ an °>_h").append(getheart.replace("<", "\\<")).append("|/serverpp \"|/w \"<° vergeben.#");
                }  
                }
               
                profile.append("°[176,226,255]°°>{colorboxstart}<°°bir°°10°°+9502°°>CENTER<°°W°_°12°°[128,128,128]°°>left<°");
               
                if(!target.getLC().isEmpty() && target.getRank() > 1) {
                    Channel lc = Server.get().getChannel(target.getLC());
                   
                    profile.append("°>bullet.b.png<° LieblingsChannel:°%30°").append("°BB>_h").append(target.getLC()).append("|/go \"|/wc \"<°");
                   
                    if(lc.checkCm(target.getName())) {
                            profile.append("°[128,128,128]° (°>cm.png<°)");
                    }
                }
                   
                     if(target.getRoses() > 0) {
                    profile.append("°%00°#°>bullet.b.png<° °[128,128,128]°Rosen:°%30°°13°");
                   
                    if(target == client) {
                            profile.append("°13°°BB>_h").append(nf.format(target.getRoses())).append(" |/rose old<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose old<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<>--<>|/rose old<r°");
                    } else {
                            profile.append("°13°°BB>_h").append(nf.format(target.getRoses())).append(" |/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<>--<>|/rose colors:").append(charNick).append("<r°");
                    }
                 
                            spaces++;
                }
                     
                    /*  if(target.getCodeE() > 0) {
                    profile.append("#°>bullet.b.png<°°[128,128,128]°°13° ");
                   
                    if(target == client) {
                            profile.append("°[128,128,128]°°>_h").append(nf.format(target.getCodeE())).append(" |/code old<>--<>present.gif|present_ani.gif<>|/shop<>--<>|/code old<°");
                    } else {
                            profile.append("").append(nf.format(target.getCodeE())).append(" °>present.gif|present_ani.gif<>|/shop<>--<°");
                    }
                   
                    profile.append(" erhalten");
                   
                    if(target.getCodeV() > 0) {
                            profile.append("°[128,128,128]°, ").append(target.getCodeV()).append(" °>present.gif|present_ani.gif<>|/shop<>--<r° verschenkt");
                    }
                   
                    profile.append("#");
                            spaces++;
                } else {
                    if(target.getCodeV() > 0) {
                            profile.append("°>bullet.b.png<° ").append(target.getCodeV()).append(" °>present.gif|present_ani.gif<>|/shop<>--<r° verschenkt#");
                            spaces++;
                    }
                }*/
                   
                      profile.append("°%00°#°>bullet.b.png<° °13°°[128,128,128]°Knutschflecken:°%30°°BB°").append(target.getKisses()<1?"Keine":nf.format(target.getKisses()));
               
                profile.append("#°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°#°r°°>left<°_");
                   profile.append("°%00°");
                   profile.append("#    °>{imageboxend}<°°>left<°#");
               
                            // Start Gestaltung
                   
     
                    if (!ent) {
                        Server.get().query(String.format("update accounts set missed='%s|%s|' where name='%s'", target.getMissed(), client.getName(), target.getName()));
                    }
             
               
                profile.append("°>{endtable}<°");
               
     
                content.newTab(title, readme, "Start", null, profile.toString());
     
                // Statistiken
                StringBuilder person = new StringBuilder();
                if(!target.getBirthday().isEmpty()) {
                    if(target.getShowBirthday() == 1) {
                        person.append("°>bullet.b.png<° _Geburtstag_:°%15°").append(target.getBirthday()).append("°%00°#");
                    }
               
                    if(target.getShowZodiac() == 1) {
                        person.append("°>bullet.b.png<° _Sternzeichen_:°%15°").append(Zodiac.getZodiacSign(target.getBirthday())).append("°%00°#");
                    }
                }
               
                if(!target.getVergeben().isEmpty()) {
                    person.append("°>bullet.b.png<° _Vergeben_?°%15°").append(target.getVergeben()).append("°%00°#");
                }
               
                 if(!target.getRealName().isEmpty()) {
                    String realname = target.getRealName();
                    realname = Server.get().parseSmileys(target, realname);
                     
                    person.append("°>bullet.b.png<° _Real Name_:°%15°").append(realname.replace("#°!°", "°#°")).append(" §°%00°#");
                }
               
                 if(!target.getLand().isEmpty()) {
                    String land = target.getLand();
                  land = Server.get().parseSmileys(target, land);
                     
                    person.append("°>bullet.b.png<° _Land_:°%15°").append(land.replace("#°!°", "°#°")).append(" §°%00°#");
                }
               
                if(!target.getHobbys().isEmpty()) {
                    String hobbys = target.getHobbys();
                    hobbys = Server.get().parseSmileys(target, hobbys);
                   
                    person.append("°>bullet.b.png<° _Hobbys_:°%15°").append(hobbys.replace("#°!°", "°#°")).append(" §°%00°#");
                }
               
                if(!target.getJob().isEmpty()) {
                    String job = target.getJob();
                    job = Server.get().parseSmileys(target, job);
                   
                    person.append("°>bullet.b.png<° _Job_:°%15°").append(job.replace("#°!°", "°#°")).append(" §°%00°#");
                }
               
                if(!target.getEmail().isEmpty()) {
                    String email = KCodeParser.escape(target.getEmail());
                   
                    if(target.getShowEmail() == 1) {
                            person.append("°>bullet.b.png<° _E-Mail_:°%15").append(email).append(" §°%00°#");
                    } else {
                            person.append("°>bullet.b.png<° _E-Mail_:°%15°");
                           
                            if(client.hasPermission("profile.seeemail")) {
                                    person.append("[").append(email).append("]");
                            } else {
                                    person.append("(vorhanden)");
                            }
                           
                            person.append("§°%00°#");
                    }
                }
               
                if(!target.getMotto().isEmpty()) {
                    String motto = target.getMotto();
                   motto = Server.get().parseSmileys(target, motto);
                   
                    whois.append("_Motto_:#").append(motto.replace("#°!°", "°#°")).append(" §#");
                }
                content.newTab(title, readme, "Person", null, person.toString());
     
                // Einstellungen
              StringBuilder infected = new StringBuilder();
    infected.append("°[128,128,128]°°15°_Allgemein_##");
    infected.append("°>bullet.b.png<° °[128,128,128]°°13°_Registriert:_°%30°°[0,0.0]°_");
    if(target==Server.get().getButler()) {
                    infected.append("28.08.1998 _um_ 00:03:01_#");
                } else {
                  infected.append(target.getRegistrationDate()).append(" _um_ ").append(target.getRegistrationTime()).append("_");
                }
    infected.append("°%00°#°>bullet.b.png<° °[128,128,128]°°13°_Stammi-Monate:°%30°°BB°");
    if(!target.getStammiwhen().isEmpty()) {
                    byte months = (byte) (CommandParser.countChars(target.getStammiwhen(), ',')+1);
                   
                    if(months == 1) {
                            infected.append("1");
                    } else {
                            infected.append(months);
                    }
                   
                    infected.append(" _°[128,128,128]°(").append(target.getStammiwhen()).append(")#");
                }
    infected.append("°%00°#§°>bullet.b.png<° °[128,128,128]°°13°_CM-Monate:°%30°°BB°");
    if(!target.getCmwhen().isEmpty()) {
                    byte months = (byte) (CommandParser.countChars(target.getCmwhen(), ',')+1);
                   
                if(months == 1) {
                            infected.append("1");
                    } else {
                            infected.append(months);
                    }
                   
                    infected.append("_ °[128,128,128]°(").append(target.getCmwhen()).append(")");
                }
              if(!target.getLC().isEmpty() && target.getRank() > 1) {
                    Channel lc = Server.get().getChannel(target.getLC());
                   
                    infected.append("°%00°#§°13°_°[128,128,128]°°>bullet.b.png<° LieblingsChannel:°%30°°BB°").append("°>_h").append(target.getLC()).append("|/go \"|/wc \"<°_°[0,0,0]");
                   
                   
                   
                    if(client.getLC().equals(target.getLC())) {
                            infected.append(" (seit ");
                           
                            if(target.getLcmonths() == 0) {
                                    infected.append("diesem Monat");
                            } else if(target.getLcmonths() == 1) {
                            infected.append("einem Monat");
                        } else {
                            infected.append(target.getLcmonths()).append(" Monaten");
                        }
                           
                            infected.append(")");
                    }
              }
              infected.append("§°%00°##°>cc/bar_greyStrong...clipw_385.w_0.mx_-1.png<°");
              infected.append("°%00°###§°[128,128,128]°°15°_Community_##");
         infected.append("°>bullet.b.png<°°[128,128,128]°°13° _Knutschflecken:°%30°°BB°").append(target.getKisses()<1?"Keine":nf.format(target.getKisses())).append("_");
         if(target.getRoses() > 0) {
                    infected.append("°%00°#°>bullet.b.png<°°[128,128,128]°°13° _Rosen:_°%30°");
                   
                    if(target == client) {
                            infected.append("_°BB>_h").append(nf.format(target.getRoses())).append(" |/rose old<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose old<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<>--<>|/rose old<r°_");
                    } else {
                            infected.append("_°BB>_h").append(nf.format(target.getRoses())).append(" |/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<>--<>|/rose colors:").append(charNick).append("<r°_");
                    }
                            spaces++;
                }
          if(target.getCodeE() > 0) {
                    infected.append("§°%00°#°>bullet.b.png<°°13°°[128,128,128]° _Geschenke:°%30°");
                   
                    if(target == client) {
                            infected.append("°BB>_h").append(nf.format(target.getCodeE())).append(" |/code old<>--<>present.gif|present_ani.gif<>|/shop<>--<>|/code old<°°[128,128,128]° _erhalten");
                    } else {
                            infected.append("°BB°").append(nf.format(target.getCodeE())).append(" °>present.gif|present_ani.gif<>|/shop<>--<°°[128,128,128]° _erhalten");
                    }
                   
                 if(target.getCodeV() > 0) {
                            infected.append(",°BB° _").append(target.getCodeV()).append(" °>present.gif|present_ani.gif<>|/shop<>--<r°°[128,128,128]° °13°_verschenkt");
                    }
                            spaces++;
                } else {
                    if(target.getCodeV() > 0) {
                            infected.append("°%00°#°>bullet.b.png<°°13°°[128,128,128]° _Geschenke: °BB°").append(target.getCodeV()).append(" °>present.gif|present_ani.gif<>|/shop<>--<r° °[128,128,128]° °13°verschenkt#");
                            spaces++;
                    }
                }
        if(target.getMentorPoints() > 0) {
          infected.append("°%00°#°>bullet.b.png<°°[128,128,128]°°13° _Mentorpunkte:°%30°°BB°").append(target.getMentorPoints());
        }
         if(target.getHeroCounter() > 0) {
            infected.append("§°%00°#°>bullet.b.png<°°[128,128,128]°°13° _Held des Tages:°%30°°BB°").append(target.getHeroCounter()).append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°");  
         }
         
         if(target.receivedHearts.size() > 0) {
              infected.append("°%00°#§°>bullet.b.png<°°[128,128,128]°°13° _Herzen erhalten:°%30°°BB°");  
         }
         if(target.receivedHearts.size() > 0) {
                    int herzzahl = 1;
                    String herz = "";
                    StringBuilder herzen = new StringBuilder();
                    StringBuilder herznicks = new StringBuilder();
                         
                    for(String name : target.receivedHearts) {
                            if(herzzahl != 1) {
                                    herznicks.append(", ");
                                    herz = "";
                        } else {
                            herz = "";
                        }
             
                        herznicks.append("°>_h").append(name.replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                        herzzahl++;
                    }
                   
                String hN = herznicks.toString();
                   
                    infected.append("").append(herz);
                   
                    if(hN.contains(getheart.replace("<", "\\<")) && !getheart.isEmpty()) {
                            infected.append(" °>fullheart.png<°");
                            for(int i=1;i<=herzzahl-2;i++) {
                                    infected.append(" °>halfheart.png<°");
                            }
                    } else {
                            for(int i=1;i<=herzzahl-1;i++) {
                                    infected.append(" °>halfheart.png<°");
                            }
                            infected.append(herzen.toString());
                    }
                   
                    infected.append("");
                   
                    if(hN.contains(getheart.replace("<", "\\<")) && !getheart.isEmpty()) {
                            infected.append("°>_h").append(getheart.replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                           
                            if(herz.equals("")) {
                                    infected.append(", ").append(hN.replace(String.format("°>_h%s|/serverpp \"|/w \"<°, ", getheart.replace("<", "\\<")), "").replace(String.format(", °>_h%s|/serverpp \"|/w \"<°", getheart.replace("<", "\\<")), ""));
                            }
                    } else {
                            infected.append(hN);
                    }
         }
         if (target.getKontoKnuddels() > 0) {
                    //int cl = Server.count(String.format("select count(smiley) as a from usersmileys where smiley='53' and name='%s'", client.getName()));
                    //int ta = Server.count(String.format("select count(smiley) as a from usersmileys where smiley='53' and name='%s'", target.getName()));
                   
                    //if(target == client || client.hasPermission("profile.seekontoknuddels") || ta < cl) {
                    if(target == client || client.hasPermission("profile.seekontoknuddels")) {
                            infected.append("°>gt.gif<° _°R°").append(nf.format(target.getKontoKnuddels())).append("°r°_ Knuddels auf dem °>_hKonto|/\"<° °>kroesus...b.my_7.h_22.gif<°#");
                            spaces++;
                    }
                }
         infected.append("§°%00°##°>cc/bar_greyStrong...clipw_385.w_0.mx_-1.png<°");
            infected.append("°%00°###§°[128,128,128]°°15°_Games_##");  
            if (target.getWordMixPoints() > 99) {
                    infected.append("°>bullet.b.png<°§°[128,128,128]°°13° _Wordmix:°%30°°BB°").append(nf.format((int)target.getWordMixPoints())).append("°[128,128,128]° _Punkte");
                            spaces++;
                }
             if (target.getJumpopunkte() > 99) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Jumpo:°%30°°BB°").append(nf.format(target.getJumpopunkte())).append("°[128,128,128]° _Punkte");
                            spaces++;
                }
             if (target.getMixPoints() > 99) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Mix:°%30°°BB°").append(nf.format(target.getMixPoints())).append("°[128,128,128]° _Punkte");
                            spaces++;
                }
             if (target.getTranslatePoints() > 99) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Translate:°%30°°BB°").append(nf.format(target.getTranslatePoints())).append("°[128,128,128]° _Punkte");
                            spaces++;
                }
               if (target.getHol() > 99) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _High or Low:°%30°°BB°").append(nf.format(target.getHol())).append("°[128,128,128]° _Punkte");
                            spaces++;
                }  
               if (target.getBlitzPoints() >= 99) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Blitz:°%30°°BB°").append(nf.format((int) target.getBlitzPoints())).append("°[128,128,128]° _Punkte");
                                    spaces++;
                           }
               if (target.getDonate() > 0) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Donate:°%30°°BB°").append(nf.format(target.getDonate())).append("°[128,128,128]° _Punkte");
                            spaces++;
                }
            /*   String mathe = "";
                    if (target.getMathePoints() > 64) {
                    mathe = "Grundschüler";    
                    }
                    if (target.getMathePoints() > 128) {
                    mathe = "Besserwisser";    
                    }
                    if (target.getMathePoints() > 256) {
                    mathe = "Schlaumeier";    
                    }
                    if (target.getMathePoints() > 512) {
                    mathe = "Gymnasiast";    
                    }
                    if (target.getMathePoints() > 1024) {
                    mathe = "Matheleher";    
                    }
                    if (target.getMathePoints() > 2048) {
                    mathe = "Blitzmerker";    
                    }
                    if (target.getMathePoints() > 4096) {
                    mathe = "Schnelldenker";    
                    }
                    if (target.getMathePoints() > 8129) {
                    mathe = "Pfiffkus";    
                    }
                    if (target.getMathePoints() > 16384) {
                    mathe = "Champion";    
                    }
                    if (target.getMathePoints() > 32768) {
                    mathe = "Superhirn";    
                    }
                    if (target.getMathePoints() > 65536) {
                    mathe = "Genie";    
                    }
                    if (target.getMathePoints() > 131072) {
                    mathe = "Einstein";    
                    }
                    if (target.getMathePoints() > 262144) {
                    mathe = "Data";    
                    }
                   
                if (target.getMathePoints() > 64) {
                    infected.append(String.format("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Mathe:°%30°°BB°%s", mathe));
                    spaces++;
                } */
                 if (target.getQuessPoints() >= 99) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Quess:°%30°°BB°").append(nf.format((int) target.getQuessPoints())).append("°[128,128,128]° _Punkte");
                                    spaces++;
                           }
               
               
                 /*  
                    if (target.getQuizPoints() > 64) {
                    quiz = "Newcomer";    
                    }
                    if (target.getQuizPoints() > 128) {
                    quiz = "Warmduscher";    
                    }
                    if (target.getQuizPoints() > 256) {
                    quiz = "Besserwisser";    
                    }
                    if (target.getQuizPoints() > 512) {
                    quiz = "Schlaumeier";    
                    }
                    if (target.getQuizPoints() > 1024) {
                    quiz = "Streber";    
                    }
                    if (target.getQuizPoints() > 2048) {
                    quiz = "Blitzmerker";    
                    }
                    if (target.getQuizPoints() > 4096) {
                    quiz = "Schnelldenker";    
                    }
                    if (target.getQuizPoints() > 8129) {
                    quiz = "Pfiffkus";    
                    }
                    if (target.getQuizPoints() > 16384) {
                    quiz = "Champion";    
                    }
                    if (target.getQuizPoints() > 32768) {
                    quiz = "Superhirn";    
                    }
                    if (target.getQuizPoints() > 65536) {
                    quiz = "Genie";    
                    }
                    if (target.getQuizPoints() > 131072) {
                    quiz = "Einstein";    
                    }
                    if (target.getQuizPoints() > 262144) {
                    quiz = "Data";    
                    } */
                 
                 
                 
                 
                    if (target.getQuizPoints() > 64) {
                    infected.append("°%00°#°>bullet.b.png<°§°[128,128,128]°°13° _Quiz:°%30°°BB°");
                    if (target.getQuizPoints() > 64) {
                    infected.append("Newcomer");    
                    }
                    if (target.getQuizPoints() > 128) {
                    infected.append("Warmduscher");    
                    }
                    if (target.getQuizPoints() > 256) {
                    infected.append("Besserwisser");    
                    }
                    if (target.getQuizPoints() > 512) {
                    infected.append("Schlaumeier");    
                    }
                    if (target.getQuizPoints() > 1024) {
                    infected.append("Streber");    
                    }
                    if (target.getQuizPoints() > 2048) {
                    infected.append("Blitzmerker");    
                    }
                    if (target.getQuizPoints() > 4096) {
                    infected.append("Schnelldenker");    
                    }
                    if (target.getQuizPoints() > 8129) {
                    infected.append("Pfiffkus");    
                    }
                    if (target.getQuizPoints() > 16384) {
                    infected.append("Champion");    
                    }
                    if (target.getQuizPoints() > 32768) {
                    infected.append("Superhirn");    
                    }
                    if (target.getQuizPoints() > 65536) {
                        
                    infected.append("Genie");    
                    }
                    if (target.getQuizPoints() > 131072) {
                    infected.append("Einstein");    
                    }
                    if (target.getQuizPoints() > 262144) {
                    infected.append("Data");    
                    }
                }
                   
              infected.append("°%00°°>left<°###°[176,226,255]°°>{colorboxstart}<°°bir°°10°°+9502°°>left<°°W°°[0,0,0]°°14°#_Career_#°12°°[0,0,0]°");
                if (!target.getCareer().isEmpty()) {
                    for (String out : target.getCareer().split("\\|")) {
                        if (!out.isEmpty()) {
                            String datum = out.split(" ")[0];
                            String text = out.split(" ", 2)[1];
     
                            infected.append("#°>bullet.b.png<° ").append(datum).append("°%15°").append(text).append("°%00°");
                        }
                    }
    infected.append("#°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°#°r°°>left<°##");
                    content.newTab(title, readme, "Infected", null, infected.toString());
                }
     
               
               
                // CM
                if (channel.checkCm(client.getName())) {
                    StringBuilder cm = new StringBuilder();
                    cm.append("°>CENTER<R°_Die Channelmoderator-Info ist vertraulich!_:##°B>LEFT<°");
                    cm.append("_Channellocks_:°%45°").append(target.getCls()).append("°%00°#");
                    cm.append("_Colormutes_:°%45°").append(target.getCmutes()).append("°%00°#");
                    cm.append("_Mutes_:°%45°").append(target.getMutes()).append("°%00°#");
                    cm.append("##_CM-Comments_:#");
     
                    if (target.getCmcomments().equals("")) {
                        cm.append("Keine Einträge vorhanden");
                    } else {
                        cm.append(target.getCmcomments());
                    }
     
                    content.newTab(title, readme, "CM", null, cm.toString());
                }
     
                // Admin
                 StringBuilder admin = new StringBuilder();
               
                 if (client.getRank() > 2 || client.getTeams().contains("~1|") || channel.checkCm(client.getName()) || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz")) {
                    admin.append("°>CENTER<R°_Die Channelmoderator-Info ist vertraulich!_:##°B>LEFT<°");
                    admin.append("_Channellocks_:°%45°").append(target.getCls()).append("°%00°#");
                    admin.append("_Colormutes_:°%45°").append(target.getCmutes()).append("°%00°#");
                    admin.append("_Mutes_:°%45°").append(target.getMutes()).append("°%00°#");
                    admin.append("##_CM-Comments_:#");
     
                    if (target.getCmcomments().equals("")) {
                        admin.append("Keine Einträge vorhanden##");
                    } else {
                        admin.append(target.getCmcomments());
                    }
                }
               
     if(client.hasPermission("cmd.admininfo") && target.hasPermission("cmd.append.ainfo.hide") && !client.hasPermission("cmd.append.ainfo.alltime")&& client != target)  {
                                admin.append("#°B°_").append(target.getRank() > 6 ?  "Sysa":"A").append("dmininfo_:##");
                                admin.append("°%06°");
                                admin.append("#°R°_- Du hast keine Berechtigung diese Info einzusehen -");
                                admin.append("#§");
                    }else
                   
               
               
     
                    if (client.hasPermission("cmd.admininfo") || client.getTeams().contains("~1|") || channel.checkHz(client.getName()) || client.hasPermission("cmd.admininfo")) {
                            admin.append("#°B°_").append(target.getRank() > 6 ?  "Sysa":"A").append("dmininfo_:##");
                              //ehemals < 6 && target.getRank() > 6) {
                    if(client.getRank() < 5 && target.getRank() > 3 || client.getRank() < 7 && target.getRank() > 7) {
                            }else{
                        admin.append("°%70°");
                           
                            admin.append("°B°°%00°");
                           
                           
                           
                            admin.append("##_Last Host_:°%43°");
                   
                            if(client.getRank() > 6) {
                                    admin.append("°>_h");
                            }
                            if (target.hasPermission("cmd.append.ainfo.bot")) {
                                admin.append("127.0.0.1");
                           
                            }else
                            admin.append(target.getIPAddress().replace(".", "-"));
                           
                           
                           
                            if(client.getRank() > 6) {
                                    admin.append("|/checkip \"<°");
                                   
                                 
                                   
                                    if(!target.getHost().isEmpty()) {
                                            admin.append(" (").append(ProfileTools.getCountry(target.getHost())).append(")");
                                    }
                            }
                   
                    }
                   
                    if(target.hasPermission("cmd.append.ainfo.bot")) {
                        {
                           admin.append(String.format(", (°>_hWhois|http://www.utrace.de/?query=%s<°)", new Object[] { ("127.0.0.1") }));
                    }
                    }else
                   
                   
                   
                   
                    if(client.getRank() >= target.getRank())      
                    {
                           admin.append(String.format(", (°>_hWhois|http://www.utrace.de/?query=%s<°)", new Object[] { target.getIPAddress() }));
                    }
                   
                           admin.append("°%00°#_User Reg-No._:°%43°").append(nf.format(target.getID())).append("°%00°#_Status_:°%43°").append(target.getRank()).append(" (").append(target.getRankLabel(target.getRank()));
                   
                    if(target == Server.get().getNRS() || target == Server.get().getButler() || target.hasPermission("cmd.append.ainfo.bot") || target.getBot() == 1) {
                            admin.append(", Bot");
                    }
                   
                    admin.append(")°%00°");
                   
                    if(target.getMutes() > 0) {
                        admin.append("#_Mutes_:°%43°").append(target.getMutes()).append("°%00°");
                    }
                   
                    if(target.getCmutes() > 0) {
                        admin.append("#_Colormutes_:°%43°").append(target.getCmutes()).append("°%00°");
                    }
                   
                    if(target.getKicks() > 0) {
                        admin.append("#_Kicks_:°%43°").append(target.getKicks()).append("°%00°");
                    }
                   
                     if(target.getLocks() > 0) {
                        admin.append("#_Locks_:°%43°").append(target.getLocks()).append("°%00°");
                    }
                   
                    if(target.getCls() > 0) {
                        admin.append("#_Channellocks_:°%43°").append(target.getCls()).append("°%00°");
                    }
                   
                    if(target.getFlames() > 0) {
                        admin.append("#_Flames_:°%43°").append(target.getFlames()).append("°%00°");
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
                     //   whois.append("#_Nicks mit gleicher IP_:°%43°").append("").append("°%00°");
       
                    }else
                       
                   // Ende
                       
                     if(target.getName().equals("Domi")) {
                         
                     }else
                   
                     // Ende
                         
                    if(!nn.toString().isEmpty()) {
                            admin.append("#_Nicks mit gleicher IP_:°%43°").append(nn.toString()).append("°%00°");
                    }
                   
                    admin.append("#_Notrufe (\\# / Ok / Missbr.)_:°%43°").append(target.getAdmincallFirst()).append(" / ").append(target.getAdmincallSecond()).append(" / ").append(target.getAdmincallThird()).append("°%00°");
     
                    if(target.getNotrufsperre() != 0) {
                            admin.append("#_Notrufsperre bis_:°%43°").append(Server.get().timeStampToDate(target.getNotrufsperre())).append("°%00°");
                    }
                   
                    if(client.getRank() > 6 || client.getName().equals("Toby") || client.hasPermission("cmd.admininfo.full") || client.checkTeam("Vertrauensadmin")) {
                            if(target.getRegisterIP() != null) {
                                    admin.append("#_Register-IP_:°%43>_h").append(target.getRegisterIP().replace(".", "-")).append("|/checkip \"<%00°");
                                    admin.append("#_E-Mail_:°%43>_h").append(target.getEmail().replace(".", "-")).append("|/checkmail \"<%00°");
                            }
                           
                           
                            // Für Nicknamen die das Permission Bot haben
                            if(target.hasPermission("cmd.append.ainfo.bot")) {
                              //  whois.append("#_Betriebssystem_:°%43°").append("???").append("°%00°");
                              //  whois.append("#_Browser_:°%43°").append("???").append("°%00°");
                            }else
                            // Ende    
                           
                           
                            if(target.getAgent() != null) {
                                    admin.append("#_Betriebssystem_:°%43°").append(ProfileTools.getOS(target.getAgent())).append("°%00°");
                                    admin.append("#_Browser_:°%43°").append(ProfileTools.getBrowser(target.getAgent())).append("°%00°");
                            }
                           
                            // Für Nicknamen die das Permission Bot haben
                            if(target.hasPermission("cmd.append.ainfo.bot")) {
                            //    whois.append("#_Host_:°%43°").append("localhost").append("°%00°");
                            }else
                            // Ende
                           
                           
                            if(!target.getHost().isEmpty()) {
                                    admin.append("#_Host_:°%43°").append(ProfileTools.getHost(target.getHost())).append("°%00°");
                            }
                   
                            admin.append("#_Zeichenzahl_:°%43°").append(nf.format(target.getZeichen())).append("°%00°");
                            admin.append("#_Whois Style_:°%43°").append(target.getWStyle() == 2 ? "???":target.getVisit()==1?"W2 BETA":"Classic").append("°%00°");
                            admin.append("#_Whois Sichtbar?_:°%43°").append(target.getVisit() == 2 ? "???":target.getVisit()==1?"false":"true").append("°%00°");
                          
                            if(target.getUserLogin() > 0) {
                            admin.append("#_Logins_:°%43°").append(nf.format(target.getUserLogin())).append("°%00°");
                            }
                           
                            // Für Nicknamen die das Permission Bot haben
                            if(target.hasPermission("cmd.append.ainfo.bot")) {
                             //  whois.append("#_Appletversion_:°%43°").append("???").append("°%00°");
                              //    whois.append("#_Javaversion_:°%43°").append("???").append("°%00°");
                           
                            }else
                             // Ende  
                               
                           
                            if(target.getAppletVersion() != null) {
                                    admin.append("#_Appletversion_:°%43°").append(target.getAppletVersion()).append("°%00°");
                                    admin.append("#_Javaversion_:°%43°").append(KCodeParser.escape(target.getJavaVersion())).append("°%00°");
                                    admin.append("#_LastLoginURL_:°%43°").append(target.getLoginUrl()).append("°%00°");
                            }
                           
                            admin.append("#_E-Mail-Verify_:°%43°").append(target.getEmailVerify() == 2 ? "Verifiziert":target.getEmailVerify()==1?"Verifizierung läuft...":"Nicht verifiziert").append("°%00°");
                           
                            if(online) {
                                    admin.append("#_Online seit_:°%43°").append(target.getLoginTime()).append("°%00°");
                            }
                   
                            if(target != Server.get().getButler()) {
                                    if(online && !target.getChannel().isVisible()) {
                                            admin.append("#_Online in_:°%43>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<%00°");
                                    }
                            }
                           
                            if(!target.getMentor().isEmpty()) {
                                    admin.append("#_Mentor_:°%43°").append("°>_h").append(target.getMentor().replace("<", "\\<")).append("|/serverpp \"|/w \"<°°%00°");
                            }
                    }
     
                    if(!target.getComments().isEmpty()) {
                        admin.append("##_Comments_:#");
                        admin.append(target.getComments());
                    }
                    if(client.getRank() > 6 || client.hasPermission("cmd.admininfo.syscomments")) {
                            if(!target.getSyscomments().isEmpty()) {
                                admin.append("##°-°#_Sys-Comments_:#");
                                admin.append(target.getSyscomments());
                            }
                        }
                   
     
                    content.newTab(title, readme, "Admin", null, admin.toString());
                }
     
                whois.append(content.getSwitchTab());
     
                // Whois wird gesendet
               
                PopupWhois2 popup2 = new PopupWhois2("Profil von " + nickname,"", whois.toString(), 650, 500);
                /*
                 * 1 = old blue
                 * 2 = new blue
                 * 3 = admin red
                 */
                //popup2.setDesign(3);
               // popup2.setDesign(2);
                Panel panel2 = new Panel();
                Button button = new Button("        OK        ");
     
                button.setStyled(true);
                panel2.addComponent(button);
                popup2.addPanel(panel2);
                client.send(popup2.toString());
           
       
             }     
                 if  (client.getWStyle() == 0) {  
           
      
           
           // ALTE WHOIS
           
           
                String nick = target.getName();
                String charNick = nick.replace("<", "\\<");
                String title = String.format("Who is %s ?", nick);
              //  Server.get().newSysLogEntry(client.getName(), String.format("Whois von %s aufgerufen", nick));    
              // client.sendButlerMessage(target.getName(), String.format("%s Hat dein Profil angeschaut.", target.getName()));
             
               
               
                StringBuilder whois = new StringBuilder("#");
                int eE = 1, dp = 1, spaces=0;
                StringBuilder cls = new StringBuilder();
                StringBuilder mutes = new StringBuilder();
                StringBuilder beschwerden = new StringBuilder();
                String photo = target.getPhoto();
                int knuddels = (int) target.getKnuddels();
                int luftlinie = 0;
               
                if(target.getBot() != 0)
                    if(client.getRank() > 4 || client.hasPermission("cmd.append.header.admin")) {
                            whois.append("°B°_Hinweis_: Nickname ist derzeit als Bot in Verwendung.##");
                    }
               
           
            if(channel.checkCm(client.getName()) || channel.checkHz(client.getName()) || client.getRank() > 2 || client.getTeams().contains("~1|") || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz") || client.hasPermission("cmd.append.header.cm") || client.hasPermission("cmd.append.header.admin") || client.hasPermission("cmd.append.header.sysadmin")) {
                for(Channel s : Server.get().getChannels()) {
                        if(target.checkCl(s)) {
                                cls.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_ von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
                       
                        if(Server.get().checkCcm(target.getName(), s, 2)) {
                                mutes.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" (Color) von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
                       
                        if(Server.get().checkCcm(target.getName(), s, 3)) {
                                mutes.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
                }
           
                if(target.getDisable() != 0)
                    if(client.getRank() > 7 || client.hasPermission("cmd.append.header.sysadmin")) {
                        whois.append("°R°_Nick ist derzeit deaktiviert_##");
                }
               
               
                 if(target.getDeletenick() != 0)
                    if(client.getRank() > 4 || client.hasPermission("cmd.append.header.sysadmin")) {
                        whois.append("°R°_Nick ist zur Löschung freigegeben!_##");
                }
               
                if(target.getSpielsperre() != 0) {
                        whois.append("°R°_Momentan für alle Spiele GESPERRT_##");
                }
               
               
             
                if(target.getWahlsperre() != 0) {
                        whois.append("°R°_Bis zum ").append(Server.get().timeStampToDate(target.getWahlsperre())).append(" für alle Wahlen GESPERRT_##");
                }
               
                if(target.getSperre() > 1) {
                    whois.append("°R°_").append(target.getName());
                                whois.append("°R° ist von ").append(target.getSperrevon()).append(" gesperrt_");
               
                               
                    if(client.getRank() > 2 || client.hasPermission("cmd.append.header.admin")) {
                            whois.append("#_Begründung:#").append(target.getSperreinfo()).append("_##");            
                               
                               
                }
                   
                }
                     if(target.getSperre() == 1) {
                    whois.append("°R°_").append(target.getName());
                                whois.append("°R° ist von ").append(target.getSperrevon()).append(" (permanent) gesperrt_");          
                               
                     
                               
                        if(client.getRank() > 2 || client.hasPermission("cmd.append.header.admin")) {
                            whois.append("#_Begründung:#").append(target.getSperreinfo()).append("_");
                       
                               
                        }
                       
                        whois.append("##");
                }
               
                if(!cls.toString().isEmpty()) {
                        whois.append("°B°_Channellocks_:").append(cls.toString()).append("##");
                }
           
                if(!mutes.toString().isEmpty()) {
                        whois.append("°B°_Gemutet_:").append(mutes.toString()).append("##");
                }
               
                whois.append("°r°");
            }
               
              String test = target.getRestdauerAuction();
            if (!target.getAuctionEnd().isEmpty()) {              
                whois.append("°[229,141,15,179]°°>{colorboxstart}<°°bir°°12°#°+9502°°>CENTER<°°W°_"+target.getName()+" wird versteigert! (noch "+target.getRestdauerAuction()+")_");
              if (!target.getLastBieter().isEmpty()) {
                whois.append("#_Höchstgebot: "+target.getLastBieter().split("~")[0]+"°>sm_classic_yellow...h_0.gif<° von _°[0,53,217]°_°>_h"+target.getLastBieter().split("~")[1]+"|/w "+target.getLastBieter().split("~")[1]+"<°°b°°K°");
              }
                if (target != client) {
                whois.append("#_°[0,53,217]°°>Jetzt mitbieten!|/auctionme "+target.getName()+"<°°°_");
              }
               whois.append("#°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°#°r°°>left<°##");
               
               
            }
           
           
           
                if(target == client) {
                whois.append("Du (Level: °>levels/"+target.getLevel()+"...h_10.w_17.my_1.png<°) hast Dich");
            } else {
                String grippefarbe = "K";
            
            if(target.getGrippeStatus() == 1) {
            	grippefarbe = "B";
            } else if(target.getGrippeStatus() == 2 || target.getGrippeStatus() == 3 || target.getGrippeStatus() == 4) {
            	grippefarbe = "R";
            } else if(target.getGrippeStatus() == 5) {
            	grippefarbe = "G";
            }
               
                whois.append("_°").append(grippefarbe).append(">_h").append(charNick).append("|/m \"<r°_ (Level: °>levels/"+target.getLevel()+"...h_10.w_17.my_1.png<°) hat sich");
            }
           
            whois.append(" am _");
           
            if(target==Server.get().getButler()) {
                whois.append("28.08.1998_ um 00:03:01");
            } else {
                try {
                    // (vor X Tagen) Anzeige im Profil - Reg Tage
                               
                             
                               
                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    long longValue=0;
                    String sIndate = target.getRegistrationDate();
                    Date d = df.parse(sIndate);
                    longValue = d.getTime();
                    String uz6x = df.format(new Date().getTime());
                    long longValuex=0;
                    String sIndatex = uz6x;
                    Date dx = df.parse(sIndatex);
                    longValuex = dx.getTime();
                    long ras=longValuex;
                    ras-=longValue;
                    ras/=1000;
                    ras/=86400;
                    String days="Tage";
                    if(ras==0)
                    {
                     days="heute";
                    }
                    if(ras==1)
                    {
                     days="gestern";
                    }
                    if(days.equals("Tage"))
                    {
                        days="vor " + ras + " Tagen";
                    }
                   
              whois.append(target.getRegistrationDate()).append("_ (").append(days).append(")").append(" um ").append(target.getRegistrationTime());
                } catch (ParseException ex) {
                    Logger.getLogger(FunctionParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
            whois.append(" bei "+Server.get().getSettings("CHAT_NAME")+" registriert und seitdem schon _").append(nf.format(target.getOnlineTime()/60)).append("_ Minuten hier verbracht.##");
 
            if(online) {
                        whois.append("°>py_g.gif<° °%05°");
                       
                        if(client == target) {
                        whois.append("Du bist");
                        } else {
                                whois.append(target.getGenderLabel()).append(" ist");
                        }
                       
                        whois.append(" im Moment im Channel _");
                       
                        if(target.getChannel() == null || !target.getChannel().isVisible()) {
                                whois.append("?");
                        } else {
                                whois.append("°>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<r°");
                        }
               
                whois.append(" °E°ONLINE_°r°!");
             if (target.getTinkle() == 1 && client != target) {
whois.append(" °>features/twinkle/ft_twinkle_bell...h_15.w_15.mx_-28.my_-12.mousew_73.mouseh_20.mousex_-19.mousey_25.png|features/twinkle/ft_twinkle_bell-hover...h_15.w_15.mx_-28.my_-12.mousew_73.mouseh_20.mousex_-19.mousey_25.png<>--<>|/tinkle "+target.getName()+"<°");
}
                if(target.isAway()) {
                        if(target.hasPermission("popup.awayaniicon")) {
                                whois.append(" °>icon_away_ani_new.gif<°");
                        }else {
                                whois.append(" °>away.png<°");
                        }
                }
               
                whois.append("°%00°##");
                } else {
                        if(target.getLastOnlineChannel() == null) {
                                whois.append("°>py_b.gif<° °%05°").append(target.getGenderLabel()).append(" war niemals im Chat online°%00°##");
                        } else {
                                whois.append("°>py_r.gif<° °%05°").append(target.getGenderLabel()).append(" war zuletzt am _").append(target.getLastOnlineDate()).append("_ ").append(target.getLastOnlineTime()).append(" im Channel _");
                                Channel lastchannel = Server.get().getChannel(target.getLastOnlineChannel());
                               
                                if(lastchannel == null || !lastchannel.isVisible()) {
                                        whois.append("?");
                                } else {
                                        whois.append("°>_h").append(target.getLastOnlineChannel()).append("|/go \"|/go +\"<°");
                                }
                               
                                whois.append("_##");
                        }
                       
                if(!ent) {
                        Server.get().query(String.format("update accounts set missed='%s|%s|' where name='%s'", target.getMissed(), client.getName(), target.getName()));
                }
                }
           
                        int readmeZahl = target.readmes.size();
                       
                String readme = target.getReadme();
 
                if(client.getReadmeEffects() == 0 && readme != null) {
                        readme = KCodeParser.noKCode(readme);
                }
               
            if(readmeZahl > 1 || readme == null && readmeZahl == 1) {
                whois.append("°%05°");
               
                if(readme != null) {
                        whois.append("\\\"").append(readme).append("§\\\" ");
                }
               
                whois.append("°>_h[His]|/readmehis ").append(charNick).append("<°°%00°##");
            } else {
                if(readme!=null) {
                        whois.append("°%05°\\\"").append(readme).append("§\\\"°%00°##");
                }
            }
           
       
                               
         
                if (!photo.isEmpty()) {
                if(target.getPhoto_verify() == 1) {
                        whois.append("°>{table|140|w1}<°°>{tr}<>CENTER<°");
                }
                whois.append("_°B>photos/photo/getPicture.php?m&img=").append(photo).append("...center_140.border_3.shadow_4.jpg<>--<>|").append(Server.get().getURL()).append("index.php?page=photo_user&n=").append(charNick).append("&photo<");
                if(target.getPhoto_verify() == 1) {
                        whois.append(">w2/fv_checked...w_0.h_0.mx_-25.my_-10.vtop.png<");
                 whois.append(">{endtable}<>LEFT<");
                }
               
                whois.append("#26°");
                whois.append("   °>--<>lup...my_5.gif<13>--<>_hVergrößern|").append(Server.get().getURL()).append("index.php?page=photo_user&n=").append(charNick).append("&photo<r°");
               
                   if (!target.getFotoAlbum().isEmpty()) {
                           int dabei = query.count("select count(id) as a from fotoalbenpics where von='"+target.getName()+"'");
             //   whois.append("°#°     °13>_h"+dabei+" weitere Fotos|").append(Server.get().getURL()).append("index.php?page=album_user&n="+target.getName()+"&photo<r°");
                   }
                whois.append("_");
                 whois.append("°##° °+800");
               
                if(target.getPhoto_verify() == 1) {
                        whois.append("7");
                }else{
                        whois.append("4");
                }
               
                whois.append("+9005°°#°");
            }
               
               
                // album
               
           /*     if (!target.getFotoAlbum().isEmpty()) {
                whois.append("_°B>photos/album/getPicture.php?s&img=").append(target.getFotoAlbum()).append("...center_140.border_3.shadow_4.jpg<>--<>|").append(Server.get().getURL()).append("index.php?page=album_user&n="+target.getName()+"&photo<>--<18°##");
                 int dabei = query.count("select count(id) as a from fotoalbenpics where von='"+target.getName()+"'");
                whois.append("     °13>_h"+dabei+" weitere Fotos|").append(Server.get().getURL()).append("index.php?page=album_user&n="+target.getName()+"&photo<r°_°16#°°#°");
                // rest
                 whois.append(String.format("°+8008+9005° °#° "));
                } else {
                 whois.append(String.format("°+8005+9005°  °#°"));    
                }
               
               
               */
             
           
                   
            if(!photo.isEmpty()) {
                whois.append("°%36°");
               
                if(target == client) {
                        whois.append("Du hast");
                } else {
                        whois.append(nick).append(" hat");
                }
               
                whois.append("...#");
            } else {
                if(target == client) {
                        whois.append("Du hast");
                } else {
                        whois.append(nick).append(" hat");
                }
               
                whois.append("...°%36°");
               
                if(nick.length() > 15 && target != client) {
                        whois.append("#");
                }
            }
           
         
 
            whois.append("°>gt.gif<° _°R°").append(target.getKisses()<1?"Keine":nf.format(target.getKisses())).append("°r°_ Knutschflecken...#");
           
       
            
           
            if(target.getCodeE() > 0) {
                whois.append("°>gt.gif<° ");
               
                if(target == client) {
                        whois.append("_°R>_h").append(nf.format(target.getCodeE())).append(" |/code old<>--<>present.gif|present_ani.gif<>|/shop<>--<>|/code old<r°_");
                } else {
                        whois.append("_°R°").append(nf.format(target.getCodeE())).append("_ °>present.gif|present_ani.gif<>|/shop<>--<r°");
                }
               
                whois.append(" erhalten");
               
                
                
                
                if(target.getCodeV() > 0) {
                        whois.append(",°R°_ ").append(target.getCodeV()).append("_ °>present.gif|present_ani.gif<>|/shop<>--<r° verschenkt");
                }
               
                whois.append("#");
                        spaces++;
            } else {
                if(target.getCodeV() > 0) {
                        whois.append("°>gt.gif<R°_ ").append(target.getCodeV()).append("_ °>present.gif|present_ani.gif<>|/shop<>--<r° verschenkt#");
                        spaces++;
                }
            }
           
            if(target.getRoses() > 0) {
                whois.append("°>gt.gif<° ");
               
                if(target == client) {
                        whois.append("_°R>_h").append(nf.format(target.getRoses())).append(" |/rose old<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose old<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<>--<>|/rose old<r°_");
                } else {
                        whois.append("_°R>_h").append(nf.format(target.getRoses())).append(" |/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_head_01...h_20.w_0.mx_-98.my_5.png<>--<>|/rose colors:").append(charNick).append("<r°_");
                }
               
                whois.append(" erhalten#");
                        spaces++;
            }
 
            if(target.getMentorPoints() > 0) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getMentorPoints())).append("°r°_ Mentor Punkte#");
                        spaces++;
            }
           
           
            if(target.getFans() > 0) {
                whois.append("°>gt.gif<° _°R>_h").append(target.getFans()).append("|").append(Server.get().getURL()).append("index.php?page=photo_fans&n=").append(nick).append("&photo<r°_°>--<°°>_h Fans|").append(Server.get().getURL()).append("index.php?page=photo_fans&n=").append(nick).append("&photo<° °>icons/thumbup.png<°#");
                spaces++;
            }
           
             
              int friends = countChars(target.getFriendlist(),'|')/2;
                 
                 if (friends > 0 && target.getSeeFriends().equals("all") || friends >= 1 && target.getSeeFriends().equals("onlyfriends") && target.checkFriend(client.getName()) || friends > 0 && client == target) {
                whois.append("°>gt.gif<° _°R°°>_h"+friends+"|/showfriends "+target.getName()+"<°°r°_ ");
               
                if (friends > 1) {
                whois.append("Freunde#");
                } else {
                whois.append("Freund#");    
                }
                spaces++;
                 }
       
           
            if(target.getHeroCounter() > 0) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getHeroCounter())).append("x°r°_ °>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°Held des Tages#");
                        spaces++;
            }
            
           if (target.getLuckyCounter() > 0) {    
                 whois.append("°>gt.gif<° Glückwünsche: _°R°"+target.getLuckyCounter()+"_°r° °>features/goodluck/piggyicon...h_15.w_29.png<°#");
                    spaces++;
           }
            
            if (target.getMoskitoGestochen() >= 1 || target.getMoskitoAbgewehrt() >= 1){
                 whois.append(String.format("°>gt.gif<° Moskito: _°R°"+target.getMoskitoAbgewehrt()+"x°r°_ abgewehrt/ _°R°"+target.getMoskitoGestochen()+"x°r°_ gestochen #"));
           
               }
           
 
            if (target.getWordMixPoints() > 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format((int)target.getWordMixPoints())).append("°r°_ WordMix Punkte#");
                        spaces++;
            }
           
                     
            if (target.getJumpopunkte() > 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getJumpopunkte())).append("°r°_ Jumpo Punkte#");
                        spaces++;
            }
           
             if (target.getMixPoints() > 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getMixPoints())).append("°r°_ Mix Punkte#");
                        spaces++;
            }
           
             if (target.getTranslatePoints() > 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getTranslatePoints())).append("°r°_ Translate Punkte#");
                        spaces++;
            }
           
            if (target.getHol() > 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getHol())).append("°r°_ High or Low Punkte#");
                        spaces++;
            }
            
            if (target.getBad6() > 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getBad6())).append("°r°_ Bad6 Punkte#");
                        spaces++;
            }
           
            if (target.getBlitzPoints() >= 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format((int) target.getBlitzPoints())).append("°r°_ Blitz! Punkte#");
                                spaces++;
                       }
           
            if (target.getDonate() > 0) {
                whois.append("°>gt.gif<° _°R°").append(nf.format(target.getDonate())).append("°r°_ Donate Punkte#");
                        spaces++;
            }
           
           
           
           
                String mathe = "";
                if (target.getMathePoints() > 64) {
                mathe = "Grundschüler";    
                }
                if (target.getMathePoints() > 128) {
                mathe = "Besserwisser";    
                }
                if (target.getMathePoints() > 256) {
                mathe = "Schlaumeier";    
                }
                if (target.getMathePoints() > 512) {
                mathe = "Gymnasiast";    
                }
                if (target.getMathePoints() > 1024) {
                mathe = "Matheleher";    
                }
                if (target.getMathePoints() > 2048) {
                mathe = "Blitzmerker";    
                }
                if (target.getMathePoints() > 4096) {
                mathe = "Schnelldenker";    
                }
                if (target.getMathePoints() > 8129) {
                mathe = "Pfiffkus";    
                }
                if (target.getMathePoints() > 16384) {
                mathe = "Champion";    
                }
                if (target.getMathePoints() > 32768) {
                mathe = "Superhirn";    
                }
                if (target.getMathePoints() > 65536) {
                mathe = "Genie";    
                }
                if (target.getMathePoints() > 131072) {
                mathe = "Einstein";    
                }
                if (target.getMathePoints() > 262144) {
                mathe = "Data";    
                }
               
            if (target.getMathePoints() > 64) {
                whois.append(String.format("°>gt.gif<° Matherang: _°R°%s°r°_#", mathe));
                spaces++;
            }
           
            if (target.getQuessPoints() >= 99) {
                whois.append("°>gt.gif<° _°R°").append(nf.format((int) target.getQuessPoints())).append("°r°_ Quess Punkte#");
                                spaces++;
                       }
           
           
                  String quiz = "";
                if (target.getQuizPoints() > 64) {
                quiz = "Newcomer";    
                }
                if (target.getQuizPoints() > 128) {
                quiz = "Warmduscher";    
                }
                if (target.getQuizPoints() > 256) {
                quiz = "Besserwisser";    
                }
                if (target.getQuizPoints() > 512) {
                quiz = "Schlaumeier";    
                }
                if (target.getQuizPoints() > 1024) {
                quiz = "Streber";    
                }
                if (target.getQuizPoints() > 2048) {
                quiz = "Blitzmerker";    
                }
                if (target.getQuizPoints() > 4096) {
                quiz = "Schnelldenker";    
                }
                if (target.getQuizPoints() > 8129) {
                quiz = "Pfiffkus";    
                }
                if (target.getQuizPoints() > 16384) {
                quiz = "Champion";    
                }
                if (target.getQuizPoints() > 32768) {
                quiz = "Superhirn";    
                }
                if (target.getQuizPoints() > 65536) {
                quiz = "Genie";    
                }
                if (target.getQuizPoints() > 131072) {
                quiz = "Einstein";    
                }
                if (target.getQuizPoints() > 262144) {
                quiz = "Data";    
                }
               
            if (target.getQuizPoints() > 64) {
                whois.append(String.format("°>gt.gif<° Quizrang: _°R°%s°r°_#", quiz));
                spaces++;
            }
           
           
           
            if (target.getKontoKnuddels() > 0) {
                //int cl = Server.count(String.format("select count(smiley) as a from usersmileys where smiley='53' and name='%s'", client.getName()));
                //int ta = Server.count(String.format("select count(smiley) as a from usersmileys where smiley='53' and name='%s'", target.getName()));
               
                //if(target == client || client.hasPermission("profile.seekontoknuddels") || ta < cl) {
                if(target == client || client.hasPermission("profile.seekontoknuddels")) {
                        whois.append("°>gt.gif<° _°R°").append(nf.format(target.getKontoKnuddels())).append("°r°_ Knuddels auf dem °>_hKonto|/\"<° °>kroesus...b.my_7.h_22.gif<°#");
                        spaces++;
                }
            }
 
            if(target.getForumpostcounter() > 0) {
                whois.append("°>gt.gif<° °R°_").append(target.getForumpostcounter()).append("°r°_ Beitr");
               
                if(target.getForumpostcounter() == 1) {
                        whois.append("ag");
                } else {
                        whois.append("äge");
                }
               
                whois.append(" im °>_hForum|").append(Server.get().getURL()).append("index.php?page=forum<° °>linkicons/link-icon_forum.png<#°");
            }
            
            
            
           
            whois.append("°>gt.gif<° und kann");
           
            if(target==client) {
                whois.append("st");
            }
           
            whois.append(" noch _°R°");
           
            if(knuddels<1) {
                whois.append("nicht");
            } else {
                whois.append(nf.format(knuddels)).append("x");
            }
           
            whois.append("°r° knuddeln_!°%00°###");
           
          if(!photo.isEmpty()) {
                if(spaces < 4) {
                        whois.append("###");
                         if (!target.getFotoAlbum().isEmpty()) {
              whois.append("#");
          }
                }
            }
         
          	

     if(target.getNeveralone() != null) {
                        whois.append("§°>features/neveralone/neveralone_mf...b.my_10.w_40.h_27.png<°§ °BB°_°>_h").append(target.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append("§ ist immer für °BB°_°>_h").append(target.getNeveralone().replace("<", "\\<")).append("|/serverpp \"|/w \"<°§ da.#");
                    }

             
          
          String getloveyou = target.getLoveyou(); 
        
         
                if(target.getLoveyou() != null) {
                     Client loveyouto = Server.get().getClient(getloveyou);
         if (loveyouto == null) {
             loveyouto = new Client(null);
             loveyouto.loadStats(getloveyou);
         }
                    if(target.getName().equals(loveyouto.getLoveyou())) {
                    whois.append("§°>features/love-you/whois_loves-mf...b.h_15.gif<° °[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° und °[0,53,217]°_°>_h"+target.getLoveyou()+"|/w "+target.getLoveyou()+"<°°b°°K° lieben sich. #");
                    } else {
                       whois.append("§°>features/love-you/whois_loves-mf...b.h_15.gif<° °[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° liebt °[0,53,217]°_°>_h"+target.getLoveyou()+"|/w "+target.getLoveyou()+"<°°b°°K°.#");
                     }
                }
         
                 
      /*      if (!target.getFotoAlbum().isEmpty() && !photo.isEmpty()) {
                        if(spaces < 4) {
                        whois.append("#######");er
                }
            }
               if (!target.getFotoAlbum().isEmpty() && photo.isEmpty()) {
                        if(spaces < 4) {
                        whois.append("###");
                }
            }*/
           
           
            StringBuilder holdhands = new StringBuilder();
            int h = 1;
           
                for(String hold : target.getHoldHands().split("\\|")) {
                        if(!hold.isEmpty()){
                                if(h != 1){
                                        holdhands.append(", ");
                                }
                               
                                holdhands.append("_°BB>_h").append(hold.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_");
                                h++;
                        }  
                }
               
            String holdhandsToString = holdhands.toString();
           
            if(!holdhandsToString.isEmpty()) {
                char gend = 'm';
               
                if(target.getGender() == 2){
                        gend = 'f';
                }
               
                whois.append("°>holdhands_").append(gend).append("...b.my_7.h_21.png<° _°R°Händchen gehalten°r°_ mit ").append(holdhandsToString).append("#");
            }
           
           
             if (target.getTurnedHeadFrom().equals(target.getTurnedHeadTo().replace("|","")) && !target.getTurnedHeadFrom().isEmpty()) {
              Client target2 = Server.get().getClient(target.getTurnedHeadFrom());
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(target.getTurnedHeadFrom());                
            }
                  int lastgender = target2.getGender();
                String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                 
                 
                whois.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° und °[0,53,217]°_°>_h"+target.getTurnedHeadFrom()+"|/w "+target.getTurnedHeadFrom()+"<°°b°°K° haben sich gegenseitig den Kopf verdreht.#");
            } else {
           
            if (!target.getTurnedHeadFrom().isEmpty()) {
               
                Client target2 = Server.get().getClient(target.getTurnedHeadFrom());
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(target.getTurnedHeadFrom());                
            }
                  int lastgender = target2.getGender();
                 String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                 whois.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° wurde von °[0,53,217]°_°>_h"+target.getTurnedHeadFrom()+"|/w "+target.getTurnedHeadFrom()+"<°°b°°K°  der Kopf verdreht.#");
               
            }
            if (!target.getTurnedHeadTo().isEmpty()) {
                int lastgender = 0;
                int i3 = 0;
               StringBuilder head = new StringBuilder();              
            for(String v : target.getTurnedHeadTo().split("\\|")) {
                if (!v.isEmpty()) {
                    if (i3 != 0) {
                        head.append(", ");
                    }
                    head.append("°[0,53,217]°_°>_h"+v+"|/w "+v+"<°°b°°K°");
                    Client target2 = Server.get().getClient(v);
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(v);                
            }
                    lastgender = target2.getGender();
                    i3++;
                   
                }
            }
               
                     String image = ""; if (target.getGender() == 2) { if (lastgender == 2) { image = "ff"; } else { image = "mf"; }} else { if (lastgender == 2) { image = "fm"; } else { image = "mm"; }}
                   
                 
               
                whois.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° hat "+replaceLast(head.toString(),", "," und ")+" den Kopf verdreht.#");
            }}
           
           
       
             
             
   /*            if (target.getAdoreFrom().equals(target.getAdoreTo().replace("|","")) && !target.getAdoreFrom().isEmpty()) {
              Client target2 = Server.get().getClient(target.getAdoreFrom());
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(target.getAdoreFrom());                
            }
                  int lastgender = target2.getGender();
                String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                 
                 
            //   whois.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° und °[0,53,217]°_°>_h"+target.getAdoreFrom()+"|/w "+target.getAdoreFrom()+"<°°b°°K° haben sich gegenseitig den Kopf verdreht.#");
            } else {
           
            if (!target.getAdoreFrom().isEmpty()) {
               
                Client target2 = Server.get().getClient(target.getAdoreFrom());
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(target.getAdoreFrom());                
            }
                  int lastgender = target2.getGender();
                 String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                 whois.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° wurde von °[0,53,217]°_°>_h"+target.getAdoreFrom()+"|/w "+target.getAdoreFrom()+"<°°b°°K°  der Kopf verdreht.#");
               
            }
            if (!target.getAdoreTo().isEmpty()) {
                int lastgender = 0;
                int i3 = 0;
               StringBuilder head = new StringBuilder();              
            for(String v : target.getAdoreTo().split("\\|")) {
                if (!v.isEmpty()) {
                    if (i3 != 0) {
                        head.append(", ");
                    }
                    head.append("°[0,53,217]°_°>_h"+v+"|/w "+v+"<°°b°°K°");
                    Client target2 = Server.get().getClient(v);
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(v);                
            }
                    lastgender = target2.getGender(); // fehlt
                    i3++;
                   
                }
            }
               
                     String image = ""; if (target.getGender() == 2) { if (lastgender == 2) { image = "ff"; } else { image = "mf"; }} else { if (lastgender == 2) { image = "fm"; } else { image = "mm"; }}
                   
                 
               
                whois.append("°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° hat "+replaceLast(head.toString(),", "," und ")+" den Kopf verdreht.#");
            }}
           
           
             
             
             */
             
             
           
            if (!target.getZusammen().isEmpty()) {
                whois.append("°>loveletter_whois...b.my_4.h_20.png<°").append(charNick).append(" ist fest zusammen mit °>_h").append(target.getZusammen().replace("<", "\\<")).append("|/serverpp \"|/w \"<°.#");
            }
           
           
           
           
             if (!target.getHeroToday().isEmpty()) {
                whois.append(charNick).append(" ist der Held von °>_h").append(target.getHeroToday().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°#");
            }
             
               
         /*      if (!target.getHeroToday2().isEmpty()) {
                  if(target == client) {
                        whois.append("Du hast");
                } else {
                        if(target.getGender() == 1) {
                                whois.append("Er hat");
                        } else {
                                whois.append("Sie hat");
                        }
                }
                 
                  whois.append(" °>_h").append(target.getHeroToday2().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append(" zu ");
                 
                 
                 
                  if(target == client) {
                        whois.append("deinem ").append("Held des Tages ").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°").append(" ernannt.#");
             
                } else {
                        if(target.getGender() == 1) {
                                whois.append("seinem ").append("Held des Tages ").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°").append(" ernannt.#");
             
                        } else {
                                whois.append("ihrem ").append("Held des Tages ").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°").append(" ernannt.#");
           
                        }
                }
                 
              }
            */
            if(target.getStarsFrom() != null) {
                if(target == client) {
                        whois.append("Dir");
                } else {
                        if(target.getGender() == 1) {
                                whois.append("Ihm");
                        } else {
                                whois.append("Ihr");
                        }
                }
               
                whois.append(" wurden von °>_h").append(target.getStarsFrom().replace("<", "\\<")).append("|/serverpp \"|/w \"<° die Sterne vom Himmel geholt! °>starlite_whois-bg...b.my_18.h_10.mx_-10.w_38.png<>starlite_whois-ani...b.my_18.h_10.mx_-48.w_0.gif<°#");
            }
           
            if(target.getStarsTo() != null) {
                if(target == client) {
                        whois.append("Du hast ");
                } else {
                        whois.append(target.getGenderLabel()).append(" hat ");
                }
               
                whois.append("°>_h").append(target.getStarsTo().replace("<", "\\<")).append("|/serverpp \"|/w \"<° die Sterne vom Himmel geholt! °>starlite_whois-bg...b.my_18.h_10.mx_-10.w_38.png<>starlite_whois-ani...b.my_18.h_10.mx_-48.w_0.gif<°#");
            }
           
            if(target.getDream() != null) {
                if(target == client) {
                        whois.append("Du");
                } else {
                        whois.append(target.getGenderLabel());
                }
               
                whois.append("°>icon_dreamof...b.mx_-3.w_15.my_4.h_21.gif<° träum");
               
                if(target == client) {
                        whois.append("s");
                }
               
                whois.append("t von °>_h").append(target.getDream().replace("<", "\\<")).append("|/serverpp \"|/w \"<°.#");
            }
               
            if(target.getFriends() != null) {
                if(target == client) {
                        whois.append("Du");
                } else {
                        whois.append(target.getGenderLabel());
                }
               
                whois.append(" und °>_h").append(target.getFriends().replace("<", "\\<")).append("|/serverpp \"|/w \"<° - _Freunde für immer_. °>icon_friendsforever...b.my_6.mx_-4.w_29.h_14.gif<°#");
            }
           
           
           
            if (!target.getAuctionTo().isEmpty()) {
   
        int i = 0;
               StringBuilder auction = new StringBuilder();              
            for(String v : target.getAuctionTo().split("\\|")) {
                if (!v.isEmpty()) {
                    if (i != 0) {
                       auction.append(", ");
                    }
                    auction.append("°[0,53,217]°_°>_h"+v+"|/w "+v+"<°°b°°r°");
                    i++;
                   
                }}
               
whois.append("°>features/auctionme/auction_profile-start...h_16.png<° °[0,53,217]°_°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°r° hat "+replaceLast(auction.toString(),", ", " und ")+" ersteigert.#");
       
        }
if (!target.getAuctionFrom().isEmpty()) {
whois.append("°>features/auctionme/auction_profile-start...h_16.png<° Ersteigert von °[0,53,217]°_°>_h"+target.getAuctionFrom()+"|/w "+target.getAuctionFrom()+"<°°b°°r°.#");
}
           
           
           
           
            if(target.getLike() != null) {
                if(target == client) {
                        whois.append("Du magst");
                } else {
                        whois.append(target.getGenderLabel()).append(" mag");
                }
               
                whois.append(" °RR°_").append(target.getLike()).append("°r°_ °>icons/like...h_10.w_38.my_1.png<°#");
            }
 
                String getheart = target.getHeart();
           
            if(!getheart.isEmpty()) {
                if(target == client) {
                        whois.append("Du hast dein");
                } else {
                        whois.append(target.getGenderLabel()).append(" hat ");
                       
                        if(target.getGender() == 1) {
                                whois.append("sein");
                        } else {
                                whois.append("ihr");
                        }
                }
               
                whois.append(" °>fullheart.png<° an °>_h").append(getheart.replace("<", "\\<")).append("|/serverpp \"|/w \"<° vergeben.#");
            }
                   
            if(target.receivedHearts.size() > 0) {
                int herzzahl = 1;
                String herz = "";
                StringBuilder herzen = new StringBuilder();
                StringBuilder herznicks = new StringBuilder();
                     
                for(String name : target.receivedHearts) {
                        if(herzzahl != 1) {
                                herznicks.append(", ");
                                herz = "die";
                    } else {
                        herz = "das";
                    }
         
                    herznicks.append("°>_h").append(name.replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                    herzzahl++;
                }
               
            String hN = herznicks.toString();
               
                whois.append("Und ").append(herz);
               
                if(hN.contains(getheart.replace("<", "\\<")) && !getheart.isEmpty()) {
                        whois.append(" °>fullheart.png<°");
                        for(int i=1;i<=herzzahl-2;i++) {
                                whois.append(" °>halfheart.png<°");
                        }
                } else {
                        for(int i=1;i<=herzzahl-1;i++) {
                                whois.append(" °>halfheart.png<°");
                        }
                        whois.append(herzen.toString());
                }
               
                whois.append(" von ");
               
                if(hN.contains(getheart.replace("<", "\\<")) && !getheart.isEmpty()) {
                        whois.append("°>_h").append(getheart.replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
                       
                        if(herz.equals("die")) {
                                whois.append(", ").append(hN.replace(String.format("°>_h%s|/serverpp \"|/w \"<°, ", getheart.replace("<", "\\<")), "").replace(String.format(", °>_h%s|/serverpp \"|/w \"<°", getheart.replace("<", "\\<")), ""));
                        }
                } else {
                        whois.append(hN);
                }
               
                whois.append(" erhalten.#");
           
            }
           
            if(target == Server.get().getButler()) {
                whois.append("der Butler, meistens etwas genervt...#");
            }
   
           
            if(!target.getProfile().isEmpty()) {
                for(String out : target.getProfile().split("\\|")) {
                        if(!out.isEmpty()) {
                                String datum = out.split(" ")[0];
                                String text = out.split(" ", 2)[1];
                       
                                whois.append(text).append(" (").append(datum).append(")#");
                        }
                }
                }
           
            if(target.getRank() > 0) {
                if(target.getRank() == 6 || target.getRank() == 7 || target.getRank() == 8) {
                        whois.append("Offiziell gewählter °G°_Admin§ - hilft gerne bei allen Problemen.#");
                }
               
               
               
                if(countWords(target.getCareer(), target.getRankLabel(target.getRank())) > 1) {
                        whois.append("Wieder ");
                }
               
                whois.append(target.getRankLabel(target.getRank()).replace("inoffizieller Admin", "Ehrenmitglied").replace("inoffizieller Sysadmin", "Admin")).append(" seit dem ").append(target.getDate() == null ?"?":target.getDate()).append(".#");
                    }
           
            if(!target.getCmwhen().isEmpty()) {
                byte months = (byte) (CommandParser.countChars(target.getCmwhen(), ',')+1);
                whois.append("_");
               
                if(months == 1) {
                        whois.append("Ein Monat");
                } else {
                        whois.append(months).append(" Monate");
                }
               
                whois.append(" Channelmoderator_: ").append(target.getCmwhen()).append("#");
            }
           
            if(!target.getStammiwhen().isEmpty()) {
                byte months = (byte) (CommandParser.countChars(target.getStammiwhen(), ',')+1);
                whois.append("_");
               
                if(months == 1) {
                        whois.append("Ein Monat");
                } else {
                        whois.append(months).append(" Monate");
                }
               
                whois.append(" Stammchatter_: ").append(target.getStammiwhen()).append("#");
            }
           
            if(!target.getTeams().isEmpty()) {
                whois.append("_Teams_:°%15°");
               
                String eingabe = target.getTeams().replace("||", "<").replace("|", "");
                String[] strarr = eingabe.split("<");
               
                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                        String[] x = strarr[i].split("~");
                        String team = x[0];
                        String extra = x[1];
                       
                        whois.append(dp!=1?", ":"").append("°>_h").append(team);
                       
                        if(extra.equals("1")) {
                                whois.append(" (Teamleiter)");
                        } else if(extra.equals("2")) {
                                whois.append(" (Vorsitz)");
                        } else if(extra.equals("3")) {
                                whois.append(" (Forumsmoderator)");
                        } else if(extra.equals("4")) {
                                whois.append(" (Forumsadmin)");
                        } else if(extra.equals("5")) {
                                whois.append(" (Forums-Sysadmin)");
                        } else if(extra.equals("6")) {
                                whois.append(" (Teamnick)");
                        }
                       
                        whois.append("|/h ").append(team).append("-team<°");
                        dp++;
                }
 
                whois.append("°%00°#");
                }
           
            StringBuilder hz = new StringBuilder();
           
            for(Channel c : Server.get().getChannels()) {
                if(c.isVisible()) {
                        if(c.checkHz(target.getName())) {
                                hz.append(eE!=1?", ":"").append("°>_h").append(c.getName()).append("|/go \"|/go +\"<°");
                                eE++;
                        }
                }
            }
           
             // Original Abfrage von NetChat deaktiviert. Da man bei dieser ab unter Status 3 noch die HZE Channels angezeigt werden.
           
            if(!hz.toString().isEmpty()) {
                whois.append("_").append(target.getRank()>5?"HZA":"HZE").append("_:°%15°").append(hz.toString()).append("°%00°#");
                }
           
           
           /* if(!hz.toString().isEmpty() && target.getRank() >5) {
                whois.append("_").append("HZA").append("_:°%15°").append(hz.toString()).append("°%00°#");
                }
            if(!hz.toString().isEmpty() && target.getRank() ==5) {
            whois.append("_").append("HZE").append("_:°%15°").append(hz.toString()).append("°%00°#");              
            }
            if(!hz.toString().isEmpty() && target.getRank() ==4) {
            whois.append("_").append("HZE").append("_:°%15°").append(hz.toString()).append("°%00°#");              
            }
            if(!hz.toString().isEmpty() && target.getRank() ==3) {
            whois.append("_").append("HZE").append("_:°%15°").append(hz.toString()).append("°%00°#");              
            }*/
           
            whois.append("°-°");
           
            int abstand = 30;
           
            if(!target.getLC().isEmpty()) {
                abstand += 5;
            }
           
            if (target.getGender() == 1) {
                whois.append("_Geschlecht_:°%").append(abstand).append("°männl. °>male.png<°°%00°#");
            } else if (target.getGender() == 2) {
                whois.append("_Geschlecht_:°%").append(abstand).append("°weibl. °>female.png<°°%00°#");
            }
           
            if(target.getAge() != 0) {
                whois.append("_Alter_:°%").append(abstand).append("°").append(target.getAge()).append(target.getVerify()==1?" (verifiziert)":"").append("°%00°#");
            }
           
            if(!target.getBirthday().isEmpty()) {
                if(target.getShowBirthday() == 1) {
                    whois.append("_Geburtstag_:°%").append(abstand).append("°").append(target.getBirthday()).append("°%00°#");
                }
           
                if(target.getShowZodiac() == 1) {
                    whois.append("_Sternzeichen_:°%").append(abstand).append("°").append(Zodiac.getZodiacSign(target.getBirthday())).append("°%00°#");
                }
            }
           
            if(!target.getVergeben().isEmpty()) {
                whois.append("_Vergeben_?°%").append(abstand).append("°").append(target.getVergeben()).append("°%00°#");
            }
           
            if(target != client) {
                if(!client.getPlz().isEmpty() && !target.getPlz().isEmpty()) {
                        for(String plz : Server.luftlinie.keySet()) {
                                if(plz.contains(client.getPlz()) && plz.contains(target.getPlz())) {
                                        try {
                                                luftlinie = Server.luftlinie.get(String.format("%s~%s", client.getPlz(), target.getPlz()));
                                                luftlinie = Server.luftlinie.get(String.format("%s~%s", target.getPlz(), client.getPlz()));
                                        } catch(Exception ex) {
                                        }
                                       
                                        continue;
                                }
                        }
                       
                        ///// Entfernung START /////
                     
                        /*  
                        whois.append("_Entfernung_:°%").append(abstand).append("°ca. ");
                       
                       
                       if(luftlinie != 0) {
                                whois.append(luftlinie);
                        } else {
                                String url = String.format("http://onlinestreet.de/plz/entfernung-%s-%s.html", client.getPlz(), target.getPlz());
                                String source = Source.get(url);
                                int kim = Integer.parseInt(source.split("gt ca. <strong>")[1].split(",")[0])+1;
                                whois.append(nf.format(kim));
                                Server.luftlinie.put(String.format("%s~%s", client.getPlz(), target.getPlz()), kim);
                                Server.get().query(String.format("INSERT INTO `luftlinie` SET `a` = '%s', `b` = '%s', `number` = '%s'", client.getPlz(), target.getPlz(), kim));
                        }
                       
                       
                        whois.append(" km (Luftlinie)°%00°#");
                       
                        */
                       
                        ///// Entfernung ENDE /////
               
                }    
            }
           
            if(!target.getLC().isEmpty() && target.getRank() > 1) {
                Channel lc = Server.get().getChannel(target.getLC());
               
                whois.append("_LieblingsChannel_:°%").append(abstand).append("°").append("°>_h").append(target.getLC()).append("|/go \"|/wc \"<°");
               
                if(lc.checkCm(nick)) {
                        whois.append(" (°>cm.png<°)");
                }
               
                if(lc.checkMcm(nick)) {
                        whois.append(" (°>mcm.png<°)");
                }
               
                if(client.getLC().equals(target.getLC()) || client.hasPermission("profile.seefavoritechannel")) {
                        whois.append(" (seit ");
                       
                        if(target.getLcmonths() == 0) {
                                whois.append("diesem Monat");
                        } else if(target.getLcmonths() == 1) {
                        whois.append("einem Monat");
                    } else {
                        whois.append(target.getLcmonths()).append(" Monaten");
                    }
                       
                        whois.append(")");
                }
                 
                whois.append("°%00°#");
            }
           
            if(!target.getRealName().isEmpty()) {
                String realname = target.getRealName();
                realname = KCodeParser.parse(target, realname, 15, 10, 20);
                realname = Server.get().parseSmileys(target, realname);
                 
                whois.append("_Real Name_:°%").append(abstand).append("°").append(realname.replace("#°!°", "°#°")).append(" §°%00°#");
            }
           
            if(!target.getStadt().isEmpty()) {
                String stadt = target.getStadt();
                stadt = KCodeParser.parse(target, stadt, 15, 10, 20);
                stadt = Server.get().parseSmileys(target, stadt);
                 
                whois.append("_Stadt_:°%").append(abstand).append("°").append(stadt.replace("#°!°", "°#°")).append(" §°%00°#");
            }
           
            if(!target.getLand().isEmpty()) {
                String land = target.getLand();
                land = KCodeParser.parse(target, land, 15, 10, 20);
                land = Server.get().parseSmileys(target, land);
                 
                whois.append("_Land_:°%").append(abstand).append("°").append(land.replace("#°!°", "°#°")).append(" §°%00°#");
            }
           
            if(!target.getHobbys().isEmpty()) {
                String hobbys = target.getHobbys();
                hobbys = KCodeParser.parse(target, hobbys, 15, 10, 20);
                hobbys = Server.get().parseSmileys(target, hobbys);
               
                whois.append("_Hobbys_:°%").append(abstand).append("°").append(hobbys.replace("#°!°", "°#°")).append(" §°%00°#");
            }
           
            if(!target.getJob().isEmpty()) {
                String job = target.getJob();
                job = KCodeParser.parse(target, job, 15, 10, 20);
                job = Server.get().parseSmileys(target, job);
               
                whois.append("_Job_:°%").append(abstand).append("°").append(job.replace("#°!°", "°#°")).append(" §°%00°#");
            }
           
            if(!target.getEmail().isEmpty()) {
                String email = KCodeParser.escape(target.getEmail());
               
                if(target.getShowEmail() == 1) {
                        whois.append("_E-Mail_:°%").append(abstand).append("°").append(email).append(" §°%00°#");
                } else {
                        whois.append("_E-Mail_:°%").append(abstand).append("°");
                       
                        if(client.hasPermission("profile.seeemail")) {
                                whois.append("[").append(email).append("]");
                        } else {
                                whois.append("(vorhanden)");
                        }
                       
                        whois.append("§°%00°#");
                }
            }
           
            if(!target.getMotto().isEmpty()) {
                String motto = target.getMotto();
                motto = KCodeParser.parse(target, motto, 15, 10, 20);
                motto = Server.get().parseSmileys(target, motto);
               
                whois.append("_Motto_:#").append(motto.replace("#°!°", "°#°")).append(" §#");
            }
 
            whois.append("°-°");
     
            
            if(client.hasPermission("cmd.append.werbung")) {
            String werb = Server.werbung.get(new Random().nextInt(Server.werbung.size()));
            whois.append(String.format("#°BB°°>%s<° °9°-Anzeige-§", werb));
            whois.append("°-°");
            }
            
             if(client.hasPermission("cmd.admininfo")) {
               
               whois.append("°%20°°>gg.png<° _°BB16>_hAdmininfo|/ainfo "+target.getName()+"<°_§°r°°%00°");
               whois.append("             °>gg.png<° _°BB16>_hSperren|/sperre "+target.getName()+"<°_§°%00°");
               whois.append("°-°");
           }
             
            // Whois-Old wird nochmal gesendet
           
                Popup popup = new Popup(title, title, whois.toString(), 460, 350);
                popup.setButtonFontSize(16);
                popup.setHeaderFontSize(15);
                Panel panel = new Panel();
                Button buttonMessage3 = new Button("   OK   ");
                buttonMessage3.setStyled(true);
                panel.addComponent(buttonMessage3);
                panel.addComponent(new Label(" "));

            if (target.getRank() > 0) {
                Button buttonMessage = new Button("Career");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand(String.format("/showcareer %s", nickname));
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
            }
           
            if (target == client || client.hasPermission("edit.nickname")) {
                Button buttonMessage = new Button("Edit");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand(String.format("/edit %s", nickname));
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
            }
           
            if(client != target && target.getRank() < 5 && target != Server.get().getButler()) {
                if (!client.checkIgnored(nickname)) {
                    Button buttonMessage = new Button("Ignore");
                    buttonMessage.setStyled(true);
                    buttonMessage.setCommand(String.format("/ig %s", nickname));
                    buttonMessage.disableClose();
                    panel.addComponent(buttonMessage);
                } else {
                    Button buttonMessage = new Button("Unignore");
                    buttonMessage.setStyled(true);
                    buttonMessage.setCommand(String.format("/ig !%s", nickname));
                    buttonMessage.disableClose();
                    panel.addComponent(buttonMessage);
                }
            }
 
            if (!target.getPhoto().isEmpty()) {
                Button buttonMessage = new Button(" Foto ");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand(String.format("/foto %s", nickname));
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
            } else {
                if(target == client) {
                        Button buttonMessage = new Button("Foto hochladen");
                        buttonMessage.setStyled(true);
                        buttonMessage.setCommand("/foto");
                        buttonMessage.disableClose();
                        panel.addComponent(buttonMessage);
                }
            }
           
           
             if (target.getHP() == 1) {
                 if (target.getHPBan() == 0) {
                Button buttonMessage = new Button(" HP ");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand(String.format("/hp %s", nickname));
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
                
                Button buttonMessageh = new Button(" GB ");
                buttonMessageh.setStyled(true);
                buttonMessageh.setCommand(String.format("/gb %s", nickname));
                buttonMessageh.disableClose();
                panel.addComponent(buttonMessageh);
                
                 }
            } else {
                if(target == client && target.getRank() >= 1) {
                        Button buttonMessage = new Button("HP anlegen");
                        buttonMessage.setStyled(true);
                        buttonMessage.setCommand("/hp");
                        buttonMessage.disableClose();
                        panel.addComponent(buttonMessage);
                }
            }
           
           
           
            if (target != client) {
                Button buttonMessage = new Button("/m");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand(String.format("/serverpp %s", nickname));
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
            }
           
            if (target == client && client.getRank() < 5) {
                Button buttonMessage = new Button("Logins");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand("/loginlist");
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
            }else if(client.getRank() > 4) {
                Button buttonMessage = new Button("IP Liste");
                buttonMessage.setStyled(true);
                buttonMessage.setCommand(String.format("/iplist %s", nickname));
                buttonMessage.disableClose();
                panel.addComponent(buttonMessage);
            }
            
            target.increaseAufrufe();
            popup.addPanel(panel);
            popup.setOpcode(ReceiveOpcode.WHOIS.getValue(), nickname);
            client.send(popup.toString());
       
        // Whois-Old Ende
           
           
              }
                
                
                
                
                
                
                
        } else if (cmd.equals("m")) {
        funktionen.m.functionMake(client, channel, arg);
   

      
        } else if(cmd.equals("dm")) {

            
        }  else if (cmd.equals("info")) {
           funktionen.info.functionMake(client, channel, arg);      
        } else if(cmd.equals("lock")) {
           funktionen.lock.functionMake(client, channel, arg);     
        } else if(cmd.equals("gamelock")) {
           funktionen.gamelock.functionMake(client, channel, arg);    
        
        
 
        
        } else if (cmd.equals("cc") || cmd.equals("go")) {
            if(!client.hasPermission(String.format("cmd.%s", cmd))) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
            }
            
        	arg = KCodeParser.escape(arg);
            String ch = arg;
            boolean change = true;

            if (!ch.isEmpty() && arg.charAt(0) == '+') {
                change = false;
                ch = ch.substring(1).trim();
            }

            if (ch.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/go CHANNEL oder NICK#(Wechselt falls möglich in diesen CHANNEL, oder wechselt in den Channel dieses Nicks)");
                return;
            }
            
            if(ch.toLowerCase().equals("suche")) {
            	parse("/e", client, channel);
            	return;
            }

            Channel channelTo = Server.get().getChannel(ch);
            
            Client target = Server.get().getClient(ch);
            if(target != null) {
            	channelTo = target.getChannel();
            }
            
            if (channelTo == null) {
            /*	if(ch.startsWith("/") && client.getRank() > 0 || client.getRank() > 4) {
            		if (ch.length() > 20) {
                		client.sendButlerMessage(channel.getName(), String.format("Der Channel %s kann nicht neu angelegt werden, da er aus zu vielen Zeichen besteht. Maximal sind _20 Zeichen_ erlaubt.", new Object[] { arg }));
                		return;
                	}

                	if ((ch.indexOf(">") >= 0) || (ch.indexOf("}") >= 0) || (arg.indexOf("{") >= 0) || (arg.indexOf("[") >= 0) || (arg.indexOf("]") >= 0) || (arg.indexOf("_") >= 0) || (arg.indexOf("`") >= 0) || (arg.indexOf("´") >= 0) || (arg.indexOf("<") >= 0) || (arg.indexOf(";") >= 0) || (arg.indexOf(",") >= 0) || (arg.indexOf(".") >= 0) || (arg.indexOf("?") >= 0) || (arg.indexOf("!") >= 0) || (arg.indexOf("%") >= 0) || (arg.indexOf("°") >= 0) || (arg.indexOf("\"") >= 0) || (arg.indexOf("(") >= 0) || (arg.indexOf(")") >= 0) || (arg.indexOf("=") >= 0) || (arg.indexOf("#") >= 0) || (arg.indexOf("~") >= 0) || (arg.indexOf("'") >= 0 || (arg.indexOf("§") >= 0) || (arg.indexOf("|") >= 0))) {
                		client.sendButlerMessage(channel.getName(), String.format("Der Channel %s kann nicht neu angelegt werden, da er ein ungültiges Zeichen enthielt. Alle _Buchstaben_ und _Zahlen_ sowie folgende Zeichen sind im Channelnamen erlaubt: ##_+, -, @, ß,  , /, *_", new Object[] { arg }));
                		return;
                	}
                	
                	Server.get().createChannel(ch, 1, client.getName(), 1, null, null);
                	channelTo = Server.get().getChannel(ch);

                    client.joinChannel(channelTo);
                    
                    if (change) {
                        channel.leave(client);
                        client.leaveChannel(channel);
                        client.send(PacketCreator.switchChannel(channel.getName(), channelTo.getName()));
                        client.send(PacketCreator.updateChannelSettings(channelTo));
                        client.send(PacketCreator.updateChannelBackground(channelTo));
                    } else {
                        client.send(PacketCreator.channelFrame(channelTo, client.getName()));
                    }

                    channelTo.join(client);   
            	} else {*/
            		client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", ch));
            	//}
            	
            	return;
            }
            

            
            if (channelTo.getClients().contains(client)) {
            	client.sendButlerMessage(channel.getName(), String.format("Du bist doch schon im Channel %s.", channelTo.getName()));
            	return;
           	}
            
            if(client.checkCl(channelTo)) {
           		client.sendButlerMessage(channel.getName(), String.format("Für den Channel %s wurden Sie leider bis morgen früh gesperrt.", channelTo.getName()));
           		return;
           	}
            
            if(client.getRank() < channelTo.getMinRank() && !channelTo.getName().equals("Knuddelsmillionär") && !client.checkTeam("Knuddelsmillionär")) {
            	String minrank = "";
                	
                if(channelTo.getMinRank() == 1) {
                	minrank = "Familymitglieder";
                } else if(channelTo.getMinRank() == 2) {
                	minrank = "Stammis";
                } else if(channelTo.getMinRank() == 3) {
                	minrank = "Ehrenmitglieder";
                } else if(channelTo.getMinRank() > 3 && channelTo.getMinRank() < 8) {
                	minrank = "Admins";
                } else {
                	minrank = "Sysadmins";
                }
                	
                client.sendButlerMessage(channel.getName(), String.format("Nur %s können den Channel %s betreten.", minrank, channelTo.getName()));
            	return;
            }

            if(channelTo.countClients() >= channelTo.getSize()) {
            if (channelTo.getMaxTochter() == 0) {
            	client.sendButlerMessage(channel.getName(), String.format("Dieser Channel ist auf _maximal %s_ Leute beschränkt, bitte wähl einen anderen Channel.", channelTo.getSize()));
            } else  {
            	String hauptchannel = "";
            			 
            	if(channelTo.getTochter().trim().isEmpty()) {
            		hauptchannel = channelTo.getName();
            	} else {
                 	hauptchannel = channelTo.getTochter();
            	}	

            	Channel haupt = Server.get().getChannel(hauptchannel);
                 
                int lastid = 1;
                PoolConnection pcon9 = ConnectionPool.getConnection();
                PreparedStatement ps9 = null;
                
                try {
                	Connection con = pcon9.connect();
                	ps9 = con.prepareStatement("SELECT * FROM channels where name = ? or tochter = ? and tochter != '' order by id desc limit 1");
                	ps9.setString(1, channelTo.getName());
                	ps9.setString(2, channelTo.getTochter());
                	ResultSet rs9 = ps9.executeQuery();
                
                	while (rs9.next()) {
                			String nam = rs9.getString("name");
                			String[] lols = nam.split(" ");
                			
                			if(lols.length > 0) {
                				try {
                					lastid = Integer.parseInt(lols[lols.length-1]);
                				}catch(NumberFormatException ex) {
                				}
                			}
                		}
                } catch (SQLException e) {
                	e.printStackTrace();
                } finally {
                	if (ps9 != null) {
                		try {
                			ps9.close();
                		}catch (SQLException e) {
                		}
                	}
                	
                	pcon9.close();
                }
                 
                int nextid = 0;
                for (int start = 1; start <= lastid; start++) {
                	String name = haupt.getName() + " " + start;
                	name = name.replace(" 1", "");
                	Channel next = Server.get().getChannel(name);
                
                	if ((nextid != 0) || next != null && next.countClients() >= next.getSize()) continue;
                		
                	nextid = start;
                }
                
                if (nextid == 0) {
                	nextid = lastid + 1;
                }
                 
                String nextchannelname = haupt.getName() + " " + nextid;
                nextchannelname = nextchannelname.replace(" 1", "");
                Channel exist = Server.get().getChannel(nextchannelname);
               
                 if(client.getDm() > 0 && channelTo.getName().startsWith("DiceMaster")) {
                        client.sendButlerMessage(channel.getName(), "Du musst erst deine aktuellen Würfel zu Ende Spielen, um diesen Channel betreten zu können.");
                	return;
            	}
                
                if (!client.hasPermission("channeljoin")) 
                if (nextid > channelTo.getMaxTochter()) {
                	client.sendButlerMessage(channel.getName(), String.format("Dieser Channel ist auf _maximal %s_ Leute beschränkt, bitte wähl einen anderen Channel.", channelTo.getSize()));
                	return;
                }
                if (!client.hasPermission("channeljoin"))
                if (exist == null) {
                	Channel.CopyChannel(haupt.getName(), nextid);
                }
                client.sendButlerMessage(channel.getName(), String.format("Dieser Channel ist auf _maximal %s_ Leute beschränkt, bitte wähl einen anderen Channel. Allerdings wäre im Channel _°>_2" + nextchannelname + "|/go \"|/go +\"<°_ noch ein Platz frei.",channelTo.getSize()));
                return;
            }
            }
            
            if(!client.hasPermission("channeljoin")) {
            	if(channelTo.getMinage() != 0 && client.getAge() > channelTo.getMinage()) {
            		client.sendButlerMessage(channel.getName(), "Du kannst diesen Channel nicht betreten, da er für jüngere Nutzer reserviert ist.");
                    return;
            	}
            	
            	if(channelTo.getMingender() != 0) {
            		if(client.getGender() != channelTo.getMingender()) {
            			client.sendButlerMessage(channel.getName(), String.format("Dieser Channel kann nur von %s Mitgliedern betreten werden.", channel.getMingender() == 1 ? "männlichen":"weiblichen"));
                    	return;
            		}
            	}
            	
            	if(client.getStammiMonths() < channelTo.getMinstammimonths()) {
            		client.sendButlerMessage(channel.getName(), "Du hast nicht genug Stammimonate, um diesen Channel betreten zu können.");
                	return;
            	}
                
               if(client.getHol() < channelTo.getMinholpoints()) {
            		client.sendButlerMessage(channel.getName(), String.format("Du brauchst mindestens %s High or Low Punkte, um diesen Channel betreten zu können.##Oder logge dich im Alternativchannel °>High or Low|/go High or Low<° ein.", nf.format(channelTo.getMinholpoints())));
                	return;
            	} 
                
                if(client.getJumpopunkte() < channelTo.getMinjumpopoints()) {
            		client.sendButlerMessage(channel.getName(), String.format("Du brauchst mindestens %s Jumpo Punkte, um diesen Channel betreten zu können.##Oder logge dich im Alternativchannel °>Jumpo|/go Jumpo<° ein.", nf.format(channelTo.getMinjumpopoints())));
                	return;
            	}
                
                
               if(client.getKnuddels() < channelTo.getMinknuddels()) {
            		client.sendButlerMessage(channel.getName(), "Du besitzt leider nicht genug Knuddels, um diesen Channel betreten zu können.");
                	return;
            	}
               
              if (client.getDeletenick() != 0) {
                       Server.get().newMessage(Server.get().getButler().getName(), client.getName(),"Löschvorgang abgebrochen!", "Am Datum-XYZ wurde mit der IP-Adresse XYZ im Channel XYZ die Löschung deines Nicknamen aktiviert. Zur Sicherheit wurde der Löschvorgang durch die erneut Verwendung von "+client.getName()+" abgebrochen.#Wenn du selber die Löschung nicht beantragt hast, kontrolliere bitte ob jemand Fremdes Zugang zu deinem Passwort, deinem Computer oder zu deiner E-Mailadresse hatte.#Die letzten Logins deines Nicknamen kannst du °>hier|/loginlist<° einsehen.", (System.currentTimeMillis()/1000));      
                       client.setDeletenick((byte)0);  
                       
                }
            	
            	if(channelTo.getName().equals("Foto Verified") && client.getPhoto_verify() == 0) {
            		client.sendButlerMessage(channel.getName(), "Du kannst den Channel Foto Verified nur betreten, wenn du ein verifiziertes Foto hast.");
            		return;
            	}
                
                if(channelTo.getName().equals("iPhone") && client.getRank() < 11) {
            		client.sendButlerMessage(channel.getName(), "Du kannst den Channel iPhone nur mit einem iPhone betreten.");
            		return;
            	}
                
                if(channelTo.getName().equals("Android") && client.getRank() < 11) {
            		client.sendButlerMessage(channel.getName(), "Du kannst den Channel Android nur mit einem Android-Smartphone betreten.");
            		return;
            	}
                
            	
            	if(channelTo.getName().equals("Smileys")) {
            		if(!client.checkCode(47) && !client.checkCode(48) && !client.checkCode(68)) {
            			client.sendButlerMessage(channel.getName(), "Den Channel Smileys können Sie aus geheimnisvollen Gründen nicht betreten.");
            			return;
            		}
            	}
            	
            	if(channelTo.getName().equals("CMs")) {
            		boolean cm = false;

                    for(Channel cha : Server.get().getChannels()) {
                    	if(cha.checkCm(client.getName())) {
                    		cm = true;
                    		continue;
                    	}
                    }
            		
            		if(!cm) {
            			client.sendButlerMessage(channel.getName(), "Um diesen Channel zu betreten, musst du CM oder Admin sein.");
            			return;
            		}
            	}
            	
            	if(!channelTo.getPassword().isEmpty()) {
            		Popup popup = new Popup("Passwort notwendig", "Passwort notwendig", String.format("Für den Channel %s ist ein Passwort notwendig.", channelTo.getName()), 350, 130);
            		Panel panel8 = new Panel();
            		panel8.addComponent(new Label(String.format("Passwort: ")));
            		panel8.addComponent(new TextField(20));
            		popup.addPanel(panel8);
            		Panel panel2 = new Panel();
            		Button button = new Button("OK");
                        button.setStyled(true);
            		panel2.addComponent(new Label("  ")); 
            		button.enableAction();
            		panel2.addComponent(button);
            		Button button2 = new Button ("Abbrechen");
                        button2.setStyled(true);
                        panel2.addComponent(button);
                        
                        
            		popup.addPanel(panel2);

                	String newWindow = "";
                
                	if(change) {
                    	newWindow = "no";
                	}else{
                    	newWindow = "yes";
                	}
                
                	popup.setOpcode(ReceiveOpcode.PASSWORD.getValue(), String.format("%s:%s:%s", channelTo.getName(), channel.getName(), newWindow));
                	client.send(popup.toString());
                	return;
            	}
            }

            if(channel.isVisible()) {
                client.setLastChannel(channel.getName());
                client.setNewChannel(channelTo.getName());
            } else {
                client.setNewChannel(null);
                client.setLastChannel(null);
            }
            
            client.joinChannel(channelTo);

             client.setLevelInfo("go",1);
             
            if (change) {
            	if(channel.getName().equals("Minecraft")) {
            		client.send(PacketCreator.closeBillardList(channel.getName()));
            	}
            	
                channel.leave(client);
                client.leaveChannel(channel);
                client.send(PacketCreator.switchChannel(channel.getName(), channelTo.getName()));
                client.send(PacketCreator.updateChannelSettings(channelTo));
                client.send(PacketCreator.updateChannelBackground(channelTo));
            } else {
                client.send(PacketCreator.channelFrame(channelTo, client.getName(), client.newMessages.size()));
            }

            channelTo.join(client);        
       } else if (cmd.equals("blinddate")) {
                if(!client.hasPermission("cmd.blinddate")) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
                }
        		
                if (arg.equals("!")) {
                       if (client.existBlindDate() == 1) {
                    client.sendButlerMessage(channel.getName(), "Ich habe dich aus der Blinddate Datenbank entfernt.");
                     Server.get().query("delete from blinddate where user = '"+client.getName()+"'");
                     Server.get().query("update blinddate set zuteilung='', time='' where zuteilung='"+client.getName()+"'");
                       } else {
                    client.sendButlerMessage(channel.getName(), "Du stehst nicht in der Blinddate Datenbank.");
                       }
                } else if (arg.equals("edit") || client.getSearchGender().isEmpty() || client.getSearchGender().isEmpty() || client.getSearchAgeUntil() == 0 || client.getSearchAgeFrom() == 0 || client.getSearchMotiv().isEmpty()) {
                  StringBuilder builder = new StringBuilder();
                  builder.append("k\0Blinddate Einstellungenõsblinddateõblinddateõf\0\0\0h¾¼ûãEl         õcgFh¾¼ûãWl         õcgFh¾¼ûãCpBNpBNl õgIh¾¼ûãClBlinddate EinstellungenõblgSf\0\0\0hååÿãSl õgQh¾¼ûããCpBCcIn diesem Fenster musst du Daten über deinen _Wunschblinddatepartner_ angeben. Dabei ist darauf zu achten, dass die erste Altersangabe _höchstens so groß_ gewählt wird, wie die zweite Altersangabe.#Bei _Motiv_ kannst du zwischen _Partnerschaft_, _Abenteuer_ oder _gutes Gespräch_ wählen - je nachdem, was du dir von dem Blinddate versprichst.#°13°Hinweis: Du kannst diese Einstellungen jederzeit durch Eingabe von _/blinddate edit_ ändern.õs@f\0\0\0h¾¼ûipics/cloudsblue.gifõ\0ãSpGEBDDl õgUh¾¼ûãpBWl");
                  builder.append("Geschlecht: õgOh¾¼ûãCoc");
                  if (client.getSearchGender().equals("weibl.")) {
                  builder.append("B");
                  } else {
                  builder.append("A");   
                  }
                  builder.append("ãmännl.õweibl.õããpBWl");
                  builder.append("Alter:õgOh¾¼ûãCpGACDDpBWl von õgOh¾¼ûãCoc");
                  if (client.getSearchAgeFrom() == 14) {
                  builder.append("A");
                  } else if (client.getSearchAgeFrom() == 15) {
                  builder.append("B");
                  }  else if (client.getSearchAgeFrom() == 16) {
                  builder.append("C");
                  } else if (client.getSearchAgeFrom() == 17) {
                  builder.append("D");
                  } else if (client.getSearchAgeFrom() == 18) {
                  builder.append("E");
                  } else if (client.getSearchAgeFrom() == 19) {
                  builder.append("F");
                  } else if (client.getSearchAgeFrom() == 20) {
                  builder.append("G");
                  }  else if (client.getSearchAgeFrom() == 21) {
                  builder.append("H");
                  } else if (client.getSearchAgeFrom() == 22) {
                  builder.append("I");
                  } else if (client.getSearchAgeFrom() == 23) {
                  builder.append("J");
                  } else if (client.getSearchAgeFrom() == 24) {
                  builder.append("K");
                  } else if (client.getSearchAgeFrom() == 25) {
                  builder.append("L");
                  } else if (client.getSearchAgeFrom() == 26) {
                  builder.append("M");
                  } else if (client.getSearchAgeFrom() == 28) {
                  builder.append("N");
                  } else if (client.getSearchAgeFrom() == 30) {
                  builder.append("O");
                  } else if (client.getSearchAgeFrom() == 32) {
                  builder.append("P");
                  } else if (client.getSearchAgeFrom() == 34) {
                  builder.append("Q");
                  } else if (client.getSearchAgeFrom() == 36) {
                  builder.append("R");
                  } else if (client.getSearchAgeFrom() == 40) {
                  builder.append("S");
                  } else if (client.getSearchAgeFrom() == 45) {
                  builder.append("T");
                  } else if (client.getSearchAgeFrom() == 50) {
                  builder.append("U");
                  } else if (client.getSearchAgeFrom() == 55) {
                  builder.append("V");
                  } else if (client.getSearchAgeFrom() == 60) {
                  builder.append("W");
                  } else if (client.getSearchAgeFrom() == 70) {
                  builder.append("X");
                  } else if (client.getSearchAgeFrom() == 100) {
                  builder.append("Y");
                  }
                  builder.append("ã14õ15õ16õ17õ18õ19õ20õ21õ22õ23õ24õ25õ26õ28õ30õ32õ34õ36õ40õ45õ50õ55õ60õ70õ100õããpBWl bis õgOh¾¼ûãCoc");
                  if (client.getSearchAgeUntil() == 14) {
                  builder.append("A");
                  } else if (client.getSearchAgeUntil() == 15) {
                  builder.append("B");
                  }  else if (client.getSearchAgeUntil() == 16) {
                  builder.append("C");
                  } else if (client.getSearchAgeUntil() == 17) {
                  builder.append("D");
                  } else if (client.getSearchAgeUntil() == 18) {
                  builder.append("E");
                  } else if (client.getSearchAgeUntil() == 19) {
                  builder.append("F");
                  } else if (client.getSearchAgeUntil() == 20) {
                  builder.append("G");
                  }  else if (client.getSearchAgeUntil() == 21) {
                  builder.append("H");
                  } else if (client.getSearchAgeUntil() == 22) {
                  builder.append("I");
                  } else if (client.getSearchAgeUntil() == 23) {
                  builder.append("J");
                  } else if (client.getSearchAgeUntil() == 24) {
                  builder.append("K");
                  } else if (client.getSearchAgeUntil() == 25) {
                  builder.append("L");
                  } else if (client.getSearchAgeUntil() == 26) {
                  builder.append("M");
                  } else if (client.getSearchAgeUntil() == 28) {
                  builder.append("N");
                  } else if (client.getSearchAgeUntil() == 30) {
                  builder.append("O");
                  } else if (client.getSearchAgeUntil() == 32) {
                  builder.append("P");
                  } else if (client.getSearchAgeUntil() == 34) {
                  builder.append("Q");
                  } else if (client.getSearchAgeUntil() == 36) {
                  builder.append("R");
                  } else if (client.getSearchAgeUntil() == 40) {
                  builder.append("S");
                  } else if (client.getSearchAgeUntil() == 45) {
                  builder.append("T");
                  } else if (client.getSearchAgeUntil() == 50) {
                  builder.append("U");
                  } else if (client.getSearchAgeUntil() == 55) {
                  builder.append("V");
                  } else if (client.getSearchAgeUntil() == 60) {
                  builder.append("W");
                  } else if (client.getSearchAgeUntil() == 70) {
                  builder.append("X");
                  } else if (client.getSearchAgeUntil() == 100) {
                  builder.append("Y");
                  }
                  builder.append("ã14õ15õ16õ17õ18õ19õ20õ21õ22õ23õ24õ25õ26õ28õ30õ32õ34õ36õ40õ45õ50õ55õ60õ70õ100õããããpBWl");
                  builder.append("Motiv: õgOh¾¼ûãCo");
                  String motive;
                  if (client.getSearchMotiv().equals("Abenteuer")) {
                   motive = "ãAbenteuerõgutes GesprächõPartnerschaft";
                  } else if (client.getSearchMotiv().equals("Partnerschaft")) {
                  motive = "ãPartnerschaftõAbenteuerõgutes Gespräch";
                  } else {
                      motive = "ãgutes GesprächõPartnerschaftõAbenteuer";
                  }
                  builder.append(motive);
                  builder.append("õããããSpFb  OK  õsdpgSf\0\0\0h¾¼ûãb  Abbrechen  õdpgSf\0\0\0h¾¼ûãããã");
                  client.send(builder.toString());
             } else {
                    if (client.existBlindDate() == 1) {
                     client.send(PopupNewStyle.create("Blinddate läuft bereits","Blinddate läuft bereits", "Du bist schon in der Blinddatedatenbank. Sobald jemand für dich gefunden wurde, erhältst du eine Nachricht.", 400, 300));
                    } else {
                        String gender2;
                        if (client.getGender() == 2) {
                            gender2 = "weibl.";
                        } else {
                        gender2 = "männl.";
                        }
                        client.sendButlerMessage(channel.getName(), "Ich habe Sie soeben in die _Blinddate-Datenbank aufgenommen_. Sie werden hieraus automatisch entfernt, wenn Sie den Chat verlassen. Mit _/blinddate edit_ können Sie jederzeit ihre Blinddate-Einstellungen ändern.");
                         Server.get().query("insert into blinddate set time='',gender2='"+gender2+"',age='"+client.getAge()+"',zuteilung='',user='"+client.getName()+"', gender='"+client.getSearchGender()+"', agefrom='"+client.getSearchAgeFrom()+"', ageuntil='"+client.getSearchAgeUntil()+"', motiv='"+client.getSearchMotiv()+"'");
                    }
                
                }
            
            }
            // ende blinddate 
            else if (cmd.equals("test")) {

                Server.get().query("update blinddate set zuteilung='', time='' where time <= '"+((System.currentTimeMillis()/1000)-180)+"'");
                
                  PoolConnection pcon = ConnectionPool.getConnection();
            PreparedStatement ps = null;
           try {
                Connection con = pcon.connect();
                ps = con.prepareStatement("SELECT * FROM `blinddate` WHERE `zuteilung` = ''");
               
                ResultSet rs = ps.executeQuery();
    while(rs.next()) {
        
        String zuteilung = "";
        String ids = "";
        PoolConnection pcon2 = ConnectionPool.getConnection(); PreparedStatement ps2 = null; try {Connection con2 = pcon2.connect();
        ps2 = con2.prepareStatement("select * from blinddate where `age` >= '"+rs.getString("agefrom")+"' and `user` != '"+rs.getString("user")+"' and `age` <= '"+rs.getString("ageuntil")+"' and `motiv` = '"+rs.getString("motiv")+"' and `gender2` = '"+rs.getString("gender")+"' and zuteilung=''");
        ResultSet rs2 = ps2.executeQuery(); if(rs2.next()) {
        zuteilung = rs2.getString("user");
        ids = rs2.getString("id");
    }} catch (SQLException e) {  e.printStackTrace();  } finally {  if (ps2 != null) {  try {   ps2.close(); } catch (SQLException e) {  }  }  pcon2.close();   } 
        
        if (!zuteilung.isEmpty()) {
       
          Client target = Server.get().getClient(rs.getString("user"));
        
          StringBuilder builder = new StringBuilder();
         builder.append("k\0Blinddate?õsblinddateõ"+rs.getString("id")+"õf\0\0\0h¾¼ûãEl         õcgFh¾¼ûãWl         õcgFh¾¼ûãCpBNpBNl õcgFh¾¼ûãClBlinddate?õblgSf\0\0\0hååÿãSl õcgFh¾¼ûããCcSoeben hat die Blinddate-Datenbank ein Blinddate für dich gefunden. Nun musst du das Blinddate nur noch annehmen und wirst dann automatisch in einen geheimen Blinddate-Channel mit deinem Wunschpartner gebracht.õs�\0úf\0\0\0h¾¼ûipics/cloudsblue.gifõ\0ãSpFbAnnehmenõsdpkAnnehmenõgMf\0\0\0h¾¼ûãbAblehnenõsdpkAblehnenõgMf\0\0\0h¾¼ûãbCloseõdpgMf\0\0\0h¾¼ûãããã");
          client.send(builder.toString());
          target.send(builder.toString());
      
                 Server.get().query("update blinddate set time='"+System.currentTimeMillis()/1000+"', `zuteilung` = '"+zuteilung+"'  where id='"+rs.getString("id")+"'");
                 Server.get().query("update blinddate set time='"+System.currentTimeMillis()/1000+"', `zuteilung` = '"+rs.getString("user")+"'  where id='"+ids+"'");
           
          
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
	}
}

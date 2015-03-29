package tools;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import java.awt.Color;
import java.text.*;
import starlight.*;
import java.awt.Font;
import handler.*;
import java.awt.Graphics;
import java.awt.FontMetrics;
public class ModuleCreator {
  
  
   public static Module main;
static {
try { 
 main = Module.startUp(Server.moduletree);
     } catch (Exception e) {e.printStackTrace(); }}    
    

public static Module getMainModule() {
    return main;
}

// testmodule
public static String ACTIVATE_FUNCTIONS() {
        StringBuilder builder = new StringBuilder();
                builder.append(":");
                builder.append((char)0x00);
             Module SB_TAB = main.createModule("ACTIVATE_FUNCTIONS");
             List modules = new ArrayList();            
             modules.add("PostMessagePrivate");          
             List modules2 = new ArrayList();
             modules2.add("Text + Enter = private Nachricht an $NICKS schreiben.");
             SB_TAB.add("FUNCTIONS", modules);
             SB_TAB.add("PARAMS", modules2);    
  byte[] bytes = main.writeBytes(SB_TAB);
            if (bytes != null)  {
                for (byte b : bytes) {                 
                    char bs=(char)b;
                     if (b < 0) { bs = (char)(b & 0xFF); }                                  
                    builder.append((char)bs);
                }
            }
            return builder.toString();
            }  


public static String SET_VOTEBOX_LOG(String CHANNEL_NAME, String MODULE_ID, String LOG_LINE, String STATUS_BAR) {
     StringBuilder builder = new StringBuilder();
            builder.append(":");
            builder.append((char)0x00);
        Module SET_VOTEBOX_LOG = main.createModule("SET_VOTEBOX_LOG");


        SET_VOTEBOX_LOG.add("CHANNEL_NAME", CHANNEL_NAME);
        SET_VOTEBOX_LOG.add("LOG_LINE", LOG_LINE);
        SET_VOTEBOX_LOG.add("STATUS_BAR", STATUS_BAR);
            byte[] bytes = main.writeBytes(SET_VOTEBOX_LOG);
                    if (bytes != null)  {
                for (byte b : bytes) {                 
                    char bs=(char)b;
                     if (b < 0) { bs = (char)(b & 0xFF); }                                  
                    builder.append((char)bs);
                }
            }
            return builder.toString();
        }


public static String FINALIZE_VOTEBOX(String CHANNEL_NAME) {
     StringBuilder builder = new StringBuilder();
            builder.append(":");
            builder.append((char)0x00);
        Module SB_TAB = main.createModule("FINALIZE_VOTEBOX");
        SB_TAB.add("CHANNEL_NAME", CHANNEL_NAME);
            byte[] bytes = main.writeBytes(SB_TAB);
                        if (bytes != null)  {
                for (byte b : bytes) {                 
                    char bs=(char)b;
                     if (b < 0) { bs = (char)(b & 0xFF); }                                  
                    builder.append((char)bs);
                }
            }
            return builder.toString();
        }

public static String REMOVE_VOTEBOX(String CHANNEL_NAME) {
     StringBuilder builder = new StringBuilder();
            builder.append(":");
            builder.append((char)0x00);
        Module SB_TAB = main.createModule("REMOVE_VOTEBOX");
        SB_TAB.add("CHANNEL_NAME", CHANNEL_NAME);
            byte[] bytes = main.writeBytes(SB_TAB);
                     if (bytes != null)  {
                for (byte b : bytes) {                 
                    char bs=(char)b;
                     if (b < 0) { bs = (char)(b & 0xFF); }                                  
                    builder.append((char)bs);
                }
            }
            return builder.toString();
        }

public static String IMAGE_PREFETCH(String typ) {
 StringBuilder builder = new StringBuilder();
                builder.append(":");
                builder.append((char)0x00);
            Module TB = main.createModule("IMAGE_PREFETCH");
            
           List lol = new ArrayList<String>();
           String[] images = null;
            if (typ.equals("TOR")) {
          images = new String[] { "pics/features/torwandschiessen/ball_00.gif","pics/features/torwandschiessen/ball-ready_ani.gif","pics/features/torwandschiessen/kn_ready_ani.gif","pics/features/torwandschiessen/hole_hover.png","pics/features/torwandschiessen/hole-1_active.gif","pics/features/torwandschiessen/hole-2_active.gif","pics/features/torwandschiessen/hole-3_active.gif","pics/features/torwandschiessen/hole-4_active.gif","pics/features/torwandschiessen/hole-5_active.gif","pics/features/torwandschiessen/tw_end-ani.gif","pics/features/torwandschiessen/btn_inactive.png" };      
            } else
           if (typ.equals("STAR")) {
            images = new String[] { "pics/features/starlight/photo_overlay.png","pics/features/starlight/photo_border_hover.png","pics/features/starlight/burst-rotator2.png","pics/features/starlight/star_final.png","pics/features/starlight/burst-rotator1.png", "pics/features/starlight/photo_border.png" };
            } else if (typ.equals("COUNTMASTER")) {
            images = new String[] { "pics/countmaster/apfel.png","pics/countmaster/bowl.png","pics/countmaster/burger.png","pics/countmaster/cake.png","pics/countmaster/cola.png","pics/countmaster/donut.png","pics/countmaster/eis.png","pics/countmaster/grill.png","pics/countmaster/haxe.png","pics/countmaster/pizza.png","pics/countmaster/saw.png","pics/countmaster/schirm.png","pics/countmaster/tele.png","pics/countmaster/trompete.png" };
            } else if (typ.equals("GREEDY")) {
            images = new String[] { "pics/greedy_pig/gpig_explode_0.gif","pics/greedy_pig/gpig_explode_1.gif","pics/greedy_pig/gpig_explode_2.gif","pics/greedy_pig/gpig_explode_3.gif","pics/greedy_pig/gpig_explode_4.gif","pics/greedy_pig/gpig_explode_5.gif","pics/greedy_pig/gpig_explode_6.gif","pics/greedy_pig/gpig_explode_7.gif","pics/greedy_pig/gpig_explode_8.gif","pics/greedy_pig/gpig_laugh_0.gif","pics/greedy_pig/gpig_laugh_1.gif","pics/greedy_pig/gpig_laugh_2.gif","pics/greedy_pig/gpig_laugh_3.gif","pics/greedy_pig/gpig_laugh_4.gif","pics/greedy_pig/gpig_laugh_5.gif","pics/greedy_pig/gpig_laugh_6.gif","pics/greedy_pig/gpig_laugh_7.gif","pics/greedy_pig/gpig_laugh_8.gifpics/greedy_pig/gpig_idle_0.gifpics/greedy_pig/gpig_idle_1.gifpics/greedy_pig/gpig_idle_2.gifpics/greedy_pig/gpig_idle_3.gifpics/greedy_pig/gpig_idle_4.gifpics/greedy_pig/gpig_idle_5.gifpics/greedy_pig/gpig_idle_6.gifpics/greedy_pig/gpig_idle_7.gifpics/greedy_pig/gpig_idle_8.gif","pics/greedy_pig/gpig_munch_0.gif","pics/greedy_pig/gpig_munch_1.gif","pics/greedy_pig/gpig_munch_2.gif","pics/greedy_pig/gpig_munch_3.gif","pics/greedy_pig/gpig_munch_4.gif","pics/greedy_pig/gpig_munch_5.gif","pics/greedy_pig/gpig_munch_6.gif","pics/greedy_pig/gpig_munch_7.gif","pics/greedy_pig/gpig_munch_8.gif" };
            } else if (typ.equals("SCREAM")) {
            images = new String[] { "pics/features/scaryscream/followpic_bg.png","pics/features/scaryscream/followpic_front.gif" };
            }
           for(String v : images) {
               lol.add(v);
           }
TB.add("IMAGE", lol);


     byte[] bytes = main.writeBytes(TB);
            if (bytes != null)  {
                for (byte b : bytes) {
                    
                   
                    char bs=(char)b;
                     if (b < 0) { bs = (char)(b & 0xFF); }
                                  
                    builder.append((char)bs);
                }
            }
            return builder.toString();
}

public static String SHOW_VOTEBOX(String CHANNEL_NAME,String MODULE_ID,String TITLE, int TIMEOUT_SECONDS, int TIMEOUT_SECONDS_TOTAL, int TIMEOUT_SECONDS_HURRY_UP, Module BUTTON, int MAX_VOTES, int MIN_VOTE_TOKEN_LINES, ArrayList VOTE_TOKEN, int MAX_VISIBLE_LOG_LINES, ArrayList LOG_LINE, boolean SHOW_BLOOD_BAR, String TOKEN_CLICK_TEMPLATE, int NR_STATUS_LINES, String STATUS_BAR,boolean STATUS_ABOVE_LOG, boolean NO_TEXT_SNIPPING) {
 StringBuilder builder = new StringBuilder();
            builder.append(":");
            builder.append((char)0x00);

            Module SHOW_VOTEBOX = main.createModule("SHOW_VOTEBOX");
           SHOW_VOTEBOX.add("CHANNEL_NAME",CHANNEL_NAME);          
           
                   SHOW_VOTEBOX.add("MODULE_ID",MODULE_ID);
             SHOW_VOTEBOX.add("TITLE",TITLE);
              SHOW_VOTEBOX.add("TIMEOUT_SECONDS",TIMEOUT_SECONDS);
               SHOW_VOTEBOX.add("TIMEOUT_SECONDS_TOTAL",TIMEOUT_SECONDS_TOTAL);
                SHOW_VOTEBOX.add("TIMEOUT_SECONDS_HURRY_UP",TIMEOUT_SECONDS_HURRY_UP);
                SHOW_VOTEBOX.add("BUTTON",BUTTON);
                 SHOW_VOTEBOX.add("MAX_VOTES",MAX_VOTES);
                  SHOW_VOTEBOX.add("MIN_VOTE_TOKEN_LINES",MIN_VOTE_TOKEN_LINES);
                   SHOW_VOTEBOX.add("VOTE_TOKEN",VOTE_TOKEN); 
                    SHOW_VOTEBOX.add("MAX_VISIBLE_LOG_LINES",MAX_VISIBLE_LOG_LINES);
                    SHOW_VOTEBOX.add("LOG_LINE",LOG_LINE);
                    SHOW_VOTEBOX.add("SHOW_BLOOD_BAR",SHOW_BLOOD_BAR);
                     SHOW_VOTEBOX.add("TOKEN_CLICK_TEMPLATE",TOKEN_CLICK_TEMPLATE);
                      SHOW_VOTEBOX.add("NR_STATUS_LINES",NR_STATUS_LINES);
                       SHOW_VOTEBOX.add("STATUS_BAR",STATUS_BAR);
                        SHOW_VOTEBOX.add("STATUS_ABOVE_LOG",STATUS_ABOVE_LOG);
                         SHOW_VOTEBOX.add("NO_TEXT_SNIPPING",NO_TEXT_SNIPPING);
                
           
           
          
             byte[] bytes = main.writeBytes(SHOW_VOTEBOX);
            if (bytes != null)  {
                for (byte b : bytes) {                 
                    char bs=(char)b;
                     if (b < 0) { bs = (char)(b & 0xFF); }                                  
                    builder.append((char)bs);
                }
            }
            return builder.toString();
            
        }

}
package feature;

import java.util.List;
import java.util.Random;
import java.util.*;
import tools.popup.*;
import tools.*;
import java.text.*;
import starlight.*;
import static tools.ModuleCreator.main;


public class GreedyPig implements Features, Runnable {
    private Channel channel;
    private Thread thread;
    private int einsatz;
    private boolean timeout;
    private Long endtime;
    private int eingezahlt;
    private boolean geplatzt;
    private String type =  "idle";
    private String lasttype = "idle";
    private long lastfeed = 0;
    private String wurf1;
     private static NumberFormat nf;
    private String wurf2;
    private String wurf3;
    private String starter;
    private boolean playing;  
    Random random = new Random();

    public GreedyPig(Channel channel) {        
        this.channel = channel;
    }
      static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        
	}

   public void setPlayer(String nick,String[] values) {}
   
    private String shortText(String text) {
        if (text.length() > 12) {
           text = text.trim().substring(0,12)+"...";
        } 
        
        return text;
    }
         
     private String getGreedyField() {
         
         int zahl = 0;
         if (eingezahlt <= 25) {
             zahl = 0;
         } else  if (eingezahlt <= 50) {
             zahl = 1;
         } else  if (eingezahlt <= 75) {
             zahl = 2;
         } else  if (eingezahlt <= 100) {
             zahl = 3;
         } else  if (eingezahlt <= 125) {
             zahl = 4;
         } else  if (eingezahlt <= 150) {
             zahl = 5;
         } else  if (eingezahlt <= 175) {
             zahl = 6;
         } else  if (eingezahlt <= 200) {
             zahl = 7;
         } else  if (eingezahlt > 200) {
             zahl = 8;
         }
        
        
         String img = "gpig_"+type+"_"+zahl+"...my_-27.gif<°";
         if (type.equals("explode")) {
           String[] style = new String[] {"mx_3.my_-42","mx_3.my_-40","mx_4.my_-40","mx_4.my_-39","mx_2.my_-35","my_-34","mx_3.my_-31","mx_2.my_-30","mx_2.my_-28" }; 
            
             img = "gpig_"+type+"_"+zahl+"..."+style[zahl]+".alwayscopy.gif<°°>gpig_explode_001.mp<°";
         }
         String text = "°>{-scroll}<°°>{-scrollbar}<°°>{globalopacity}100<°°+9011>greedy_pig/gpig_bg_001...mx_-5.png<°#°+9223>RIGHT<12°°[100,164,255]>_hHilfe|/h greedypig<r°_ #°>CENTER<°#°+9505° °>transparent1x1...h_110.w_0.gif<>greedy_pig/"+img;
         
         
         if (!type.equals("explode") && !type.equals("laugh")) {
         text += "#°+9055K12>{button} ||images|bluebutton-s.ending_511~bluebutton-s_hover.ending_511~bluebutton-s_hover.ending_511.mx_1.my_1|textcolor|255,255,255|call|/sfc "+channel.getName()+":/greedypig feed|width|110|noborder|1|heigth|35|disabledTimeout|0|icon|greedy_pig/gpig_buttontext_DE.png<W°§";
         } else {
          text += " ##°+9030°";   
         }
         text += "#°+9514°_Bereits gefüttert: _"+nf.format(einsatz)+" Knuddel_#°12>LEFT<+9514+0003°°b°#";
         
         if (!wurf1.isEmpty()) {
             String[] a = wurf1.split("~");
         text += "°+9502°_°>_h"+shortText(a[0])+"|/serverpp "+a[0]+"|/w "+a[0]+"<°°b° würfelt "+nf.format(Integer.parseInt(a[1]))+".#";         
         }
          if (!wurf2.isEmpty()) {
               String[] a = wurf2.split("~");
            text += "°+9502°_°>_h"+shortText(a[0])+"|/serverpp "+a[0]+"|/w "+a[0]+"<°°b° würfelt "+nf.format(Integer.parseInt(a[1]))+".#";  
          }
           if (!wurf3.isEmpty()) {
                String[] a = wurf3.split("~");
        text += "°+9502°_°>_h"+shortText(a[0])+"|/serverpp "+a[0]+"|/w "+a[0]+"<°°b° würfelt "+nf.format(Integer.parseInt(a[1]))+".#";  
           }
         return text;
     }
   
    
     private void sleep(long pMillis) {
        try {
            Thread.sleep(pMillis*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
       
     
     public void start() {  
         playing = true;   
         eingezahlt = 0;
        thread = new Thread(this);        
        thread.start();      
       
    }
     
   private void ende() {  
      channel.setFeatures("");
      playing = false;
      timeout = false;
       channel.setFeaturesname("");
   }
    
     @Override
     public void run() {
         
         
         
       while(playing) {  
         
           wurf1 = "";
           geplatzt = false;
           wurf2 = "";
           wurf3 = ""; 
         
channel.broadcastMessage("°BB°°>_h"+starter+"|/serverpp \"|/w \"<° hat ein Greedy-Pig-Spiel gestartet. Fütter den Vielfraß!", Server.get().getButler(), false);
   
  Module button = ModuleCreator.main.createModule("BUTTON");
          button.add("TEXT", null);
         button.add("IMAGE", null);
          button.add("BUTTON_ALIGN", (byte)1); 
          button.add("CHAT_FUNCTION",null);
                  ArrayList logline = new ArrayList();
            logline.add(getGreedyField());
    try {
         for(Client a : channel.getClients()) {
             
             endtime = (System.currentTimeMillis()/1000)+1800;
      a.send(ModuleCreator.SHOW_VOTEBOX(channel.getName(),null,"",1800,1800,60,button,0,0,null,10,logline,false,null,0,null,false,false));
     a.send(ModuleCreator.IMAGE_PREFETCH("GREEDY"));
      a.send(VOTEBOX_SETTINGS(channel.getName()));
         }
          } catch (ConcurrentModificationException ex) {}
         
     int h = 0;
 do {
      if (!geplatzt) {
      sleep(1);
      h++;
     
      if (lastfeed <  ((System.currentTimeMillis()/1000)-2)) {
          if (!geplatzt && !timeout) {
          lasttype = type;
          type = "idle";
      }}
      
      if (lasttype.equals(type)) {
          try {
          for(Client x : channel.getClients()) {
              x.send(ModuleCreator.SET_VOTEBOX_LOG(channel.getName(), null, getGreedyField(), ""));
          }
           } catch(ConcurrentModificationException ex) {}
      }
      
      
      
      if (h == 300) {
           channel.broadcastMessage("Nur noch 25 Minuten, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
       
      }
        if (h == 900) {
          channel.broadcastMessage("Nur noch 15 Minuten, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
         if (h == 1500) {
          channel.broadcastMessage("Nur noch 5 Minuten, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
          if (h == 1680) {
          channel.broadcastMessage("Nur noch 2 Minuten, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
           if (h == 1770) {
          channel.broadcastMessage("Nur noch 30 Sekunden, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
               if (h == 1785) {
          channel.broadcastMessage("Nur noch 15 Sekunden, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
                  if (h == 1790) {
          channel.broadcastMessage("Nur noch 10 Sekunden, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
                  if (h == 1795) {
          channel.broadcastMessage("Nur noch 5 Sekunden, bis der Vielfraß gewinnt!", Server.get().getButler(), false);
      }
      } else {
          h = 1800;  // 1800
      }
  } while (h != 1800); 
   
 
 if (!geplatzt) {
     
      timeout = true;
      boolean online = true;
     Client starts = Server.get().getClient(starter);
     if (starts == null) {
         online = false;
         starts = new Client(null);
         starts.loadStats(starter);
     }
    
       channel.broadcastAction(">","Wow, der Vielfraß hat sich satt gegessen und °>_h"+starter+"|/serverpp \"|/w \"<° bekommt die gesammelten _"+einsatz+" Knuddels_!"); 
    starts.setKnuddels((int)starts.getKnuddels()+einsatz);  
    
     String txt = "Dein Greedy-Pig im Channel _"+channel.getName()+"_ wurde nicht geknackt! Die _"+einsatz+" Knuddel_ wandern auf deinen Nick!";
    if (!online) {
 
    Server.get().newMessage(Server.get().getButler().getName(),starts.getName(), "Greedy-Pig", txt, System.currentTimeMillis()/1000); 
         
    }
    starts.saveStats();
    lasttype = type;
    type = "laugh";
    
    for(Client x : channel.getClients()) {
              x.send(ModuleCreator.SET_VOTEBOX_LOG(channel.getName(), null, getGreedyField(), ""));
          
              x.send(ModuleCreator.FINALIZE_VOTEBOX(channel.getName()));
    }
 }
 
 sleep(10);
 
 ende();
 for(Client a : channel.getClients()) {
     a.send(ModuleCreator.REMOVE_VOTEBOX(channel.getName()));
 }
       
     }}
     
     
     
  
    public static int countChars(String input, char toCount) {
		int counter = 0;

		for (char c : input.toCharArray()) {
			if (c != toCount)
				continue;
			counter++;
		}

		return counter;
	}
    
    public boolean parsePublicMessage(String message, Client client) {
       return true;
    }

    public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
	       
        return true;
    }

          public void onLeave(Client client) {
    }

      
	public void onJoin(Client client) {            
      if (playing && endtime != null) {
          
          Long rest = endtime-(System.currentTimeMillis()/1000);
           Module button = ModuleCreator.main.createModule("BUTTON");
          button.add("TEXT", null);
         button.add("IMAGE", null);
          button.add("BUTTON_ALIGN", (byte)1); 
          button.add("CHAT_FUNCTION",null);
                  ArrayList logline = new ArrayList();
            logline.add(getGreedyField());
            client.send(ModuleCreator.SHOW_VOTEBOX(channel.getName(),null,"",Integer.parseInt(String.valueOf(rest)),1800,60,button,0,0,null,10,logline,false,null,0,null,false,false));
     client.send(ModuleCreator.IMAGE_PREFETCH("GREEDY"));
     client.send(VOTEBOX_SETTINGS(channel.getName()));
      }
            
        }

	public boolean parseCommand(String message, Client client, String[] array) {
        String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
        String cmd = command.toLowerCase();
        String arg = "";
        Random zufall = new Random();
        
        if (message.length() > cmd.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1);
        }

        int Anzahlspe = countChars(arg, '°');
        
        if (Anzahlspe == 1) {
        	String[] argus = arg.split("°", 2);
        	arg = argus[0];
        }
        
        if (cmd.equals("start") && client == Server.get().getButler()) {
            einsatz = Integer.parseInt(array[0]);
            starter = array[1];
            start();
            return false;
        }
        if(!cmd.equals("greedypig")) {            
        	return true;
        }
        
        if (timeout == false && geplatzt == false && arg.equals("feed") && channel.getFeaturesname().equals("Greedy-Pig")) {
            
            
             if (client.getKnuddels() == 0) {
               client.sendButlerMessage(channel.getName(),"Du hast keine Knuddels.");
               return false;
           } 
            
            int rand = zufall.nextInt(einsatz)+1;
         
            eingezahlt++;
          client.setKnuddels((int)client.getKnuddels()-1);
            client.sendButlerMessage(channel.getName(),"_°>_h"+client.getName()+"|/serverpp \"|/w \"<°_ rollt einen Würfel...#W"+einsatz+": "+rand+" = _"+rand+"_");
            this.einsatz++;
           lastfeed = System.currentTimeMillis()/1000;
           lasttype = type;
           type = "munch";
           wurf1 = wurf2;
           wurf2 = wurf3;
           wurf3 = client.getName()+"~"+rand;
           if (rand == 1) {
               geplatzt = true;
               lasttype = type;
               type = "explode";
                   channel.broadcastAction(">","Peng! Es gibt einen lauten Knall und °>_h"+client.getName()+"|/serverpp \"|/w \"<° wird von einem Schauer aus _"+einsatz+" Knuddel_ überschüttet!"); 
       client.setKnuddels((int)client.getKnuddels()+einsatz);
       
      String txt = "Dein Greedy-Pig im Channel _"+channel.getName()+"_  wurde von °>_h"+client.getName()+"|/serverpp \"|/w \"<° bei _"+einsatz+" Knuddel_ geknackt!";
     Client st = Server.get().getClient(starter);
     boolean ons = true;
     if (st == null) {
         ons = false;
         st = new Client(null);
         st.loadStats(starter);      
     
     }
     if (!ons) {
  Server.get().newMessage(Server.get().getButler().getName(),st.getName(), "Greedy-Pig", txt, System.currentTimeMillis()/1000); 
  
     }
           }
           try {
           for(Client a : channel.getClients()) {
               a.send(ModuleCreator.SET_VOTEBOX_LOG(channel.getName(), null, getGreedyField(), ""));
           if (geplatzt) {
                 a.send(ModuleCreator.FINALIZE_VOTEBOX(channel.getName()));
           }  
           }
           } catch(ConcurrentModificationException ex) {for(Client a : channel.getClients()) {
               a.send(ModuleCreator.SET_VOTEBOX_LOG(channel.getName(), null, getGreedyField(), ""));
           if (geplatzt) {
                 a.send(ModuleCreator.FINALIZE_VOTEBOX(channel.getName()));
           }  
           }}
            return false;
        }
        
        
        
      return false;
        }
        
        
        
         public static String VOTEBOX_SETTINGS(String CHANNEL_NAME) {
StringBuilder builder = new StringBuilder();
builder.append(":");
builder.append((char)0x00);
Module VOTEBOX_SETTINGS = main.createModule("VOTEBOX_SETTINGS");
VOTEBOX_SETTINGS.add("CHANNEL_NAME", CHANNEL_NAME);
VOTEBOX_SETTINGS.add("MODULE_ID", null);
Module BORDER_COLOR = main.createModule("BORDER_COLOR");
Module COLOR = main.createModule("COLOR_RGB");
COLOR.add("RED", (byte)-1);
COLOR.add("GREEN", (byte)-1);
COLOR.add("BLUE", (byte)-1);
BORDER_COLOR.add("COLOR_RGB", COLOR);
VOTEBOX_SETTINGS.add("BORDER_COLOR",BORDER_COLOR);

Module VOTE_BARS_SETTING = main.createModule("VOTE_BARS_SETTING");
Module VOTE_BAR_WIN = main.createModule("VOTE_BAR_WIN");
VOTE_BAR_WIN.add("IMAGE","mafia/bloodbar.gif");
VOTE_BARS_SETTING.add("VOTE_BAR_WIN",VOTE_BAR_WIN);

Module VOTE_BAR_FIRST = main.createModule("VOTE_BAR_FIRST");
Module FOUR_COLOR_BAR = main.createModule("FOUR_COLOR_BAR");
Module COLOR_TOP_TOP = main.createModule("COLOR_TOP_TOP");
Module COLOR2 = main.createModule("COLOR_RGB");
COLOR2.add("RED", (byte)-30);
COLOR2.add("GREEN", (byte)0);
COLOR2.add("BLUE", (byte)24);
COLOR_TOP_TOP.add("COLOR_RGB",COLOR2);
FOUR_COLOR_BAR.add("COLOR_TOP_TOP", COLOR_TOP_TOP);

Module COLOR_TOP = main.createModule("COLOR_TOP");
Module COLOR3 = main.createModule("COLOR_RGB");
COLOR3.add("RED", (byte)-1);
COLOR3.add("GREEN", (byte)-1);
COLOR3.add("BLUE", (byte)-1);
COLOR_TOP.add("COLOR_RGB",COLOR3);
FOUR_COLOR_BAR.add("COLOR_TOP", COLOR_TOP);

Module COLOR_BOT = main.createModule("COLOR_BOT");
Module COLOR4 = main.createModule("COLOR_RGB");
COLOR4.add("RED", (byte)-10);
COLOR4.add("GREEN", (byte)-73);
COLOR4.add("BLUE", (byte)-65);
COLOR_BOT.add("COLOR_RGB",COLOR4);
FOUR_COLOR_BAR.add("COLOR_BOT", COLOR_BOT);

Module COLOR_BOT_BOT = main.createModule("COLOR_BOT_BOT");
Module COLOR5 = main.createModule("COLOR_RGB");
COLOR5.add("RED", (byte)-26);
COLOR5.add("GREEN", (byte)44);
COLOR5.add("BLUE", (byte)65);
COLOR_BOT_BOT.add("COLOR_RGB",COLOR5);
FOUR_COLOR_BAR.add("COLOR_BOT_BOT", COLOR_BOT_BOT);

VOTE_BAR_FIRST.add("FOUR_COLOR_BAR",FOUR_COLOR_BAR);
VOTE_BARS_SETTING.add("VOTE_BAR_FIRST",VOTE_BAR_FIRST);





Module VOTE_BAR_DRAW_FIRST = main.createModule("VOTE_BAR_DRAW_FIRST");
Module FOUR_COLOR_BAR2 = main.createModule("FOUR_COLOR_BAR");
Module COLOR_TOP_TOP2 = main.createModule("COLOR_TOP_TOP");
Module COLOR6 = main.createModule("COLOR_RGB");
COLOR6.add("RED", (byte)-51);
COLOR6.add("GREEN", (byte)110);
COLOR6.add("BLUE", (byte)2);
COLOR_TOP_TOP2.add("COLOR_RGB",COLOR6);
FOUR_COLOR_BAR2.add("COLOR_TOP_TOP", COLOR_TOP_TOP2);

Module COLOR_TOP2 = main.createModule("COLOR_TOP");
Module COLOR7 = main.createModule("COLOR_RGB");
COLOR7.add("RED", (byte)-1);
COLOR7.add("GREEN", (byte)-1);
COLOR7.add("BLUE", (byte)-1);
COLOR_TOP2.add("COLOR_RGB",COLOR7);
FOUR_COLOR_BAR2.add("COLOR_TOP", COLOR_TOP2);

Module COLOR_BOT2 = main.createModule("COLOR_BOT");
Module COLOR8 = main.createModule("COLOR_RGB");
COLOR8.add("RED", (byte)-1);
COLOR8.add("GREEN", (byte)-73);
COLOR8.add("BLUE", (byte)3);
COLOR_BOT2.add("COLOR_RGB",COLOR8);
FOUR_COLOR_BAR2.add("COLOR_BOT", COLOR_BOT2);

Module COLOR_BOT_BOT2 = main.createModule("COLOR_BOT_BOT");
Module COLOR9 = main.createModule("COLOR_RGB");
COLOR9.add("RED", (byte)-30);
COLOR9.add("GREEN", (byte)120);
COLOR9.add("BLUE", (byte)0);
COLOR_BOT_BOT2.add("COLOR_RGB",COLOR9);
FOUR_COLOR_BAR2.add("COLOR_BOT_BOT", COLOR_BOT_BOT2);

VOTE_BAR_DRAW_FIRST.add("FOUR_COLOR_BAR",FOUR_COLOR_BAR2);
VOTE_BARS_SETTING.add("VOTE_BAR_DRAW_FIRST",VOTE_BAR_DRAW_FIRST);





Module VOTE_BAR_REG = main.createModule("VOTE_BAR_REG");
Module FOUR_COLOR_BAR3 = main.createModule("FOUR_COLOR_BAR");
Module COLOR_TOP_TOP3 = main.createModule("COLOR_TOP_TOP");
Module COLOR10 = main.createModule("COLOR_RGB");
COLOR10.add("RED", (byte)-26);
COLOR10.add("GREEN", (byte)-71);
COLOR10.add("BLUE", (byte)1);
COLOR_TOP_TOP3.add("COLOR_RGB",COLOR10);
FOUR_COLOR_BAR3.add("COLOR_TOP_TOP", COLOR_TOP_TOP3);

Module COLOR_TOP3 = main.createModule("COLOR_TOP");
Module COLOR11 = main.createModule("COLOR_RGB");
COLOR11.add("RED", (byte)-2);
COLOR11.add("GREEN", (byte)-1);
COLOR11.add("BLUE", (byte)-19);
COLOR_TOP3.add("COLOR_RGB",COLOR11);
FOUR_COLOR_BAR3.add("COLOR_TOP", COLOR_TOP3);

Module COLOR_BOT3 = main.createModule("COLOR_BOT");
Module COLOR12 = main.createModule("COLOR_RGB");
COLOR12.add("RED", (byte)-23);
COLOR12.add("GREEN", (byte)-49);
COLOR12.add("BLUE", (byte)0);
COLOR_BOT3.add("COLOR_RGB",COLOR12);
FOUR_COLOR_BAR3.add("COLOR_BOT", COLOR_BOT3);

Module COLOR_BOT_BOT3 = main.createModule("COLOR_BOT_BOT");
Module COLOR13 = main.createModule("COLOR_RGB");
COLOR13.add("RED", (byte)-66);
COLOR13.add("GREEN", (byte)-118);
COLOR13.add("BLUE", (byte)0);
COLOR_BOT_BOT3.add("COLOR_RGB",COLOR13);
FOUR_COLOR_BAR3.add("COLOR_BOT_BOT", COLOR_BOT_BOT3);

VOTE_BAR_REG.add("FOUR_COLOR_BAR",FOUR_COLOR_BAR3);
VOTE_BARS_SETTING.add("VOTE_BAR_REG",VOTE_BAR_REG);





Module VOTE_BAR_LOSE = main.createModule("VOTE_BAR_LOSE");
Module FOUR_COLOR_BAR4 = main.createModule("FOUR_COLOR_BAR");
Module COLOR_TOP_TOP4 = main.createModule("COLOR_TOP_TOP");
Module COLOR14 = main.createModule("COLOR_RGB");
COLOR14.add("RED", (byte)24);
COLOR14.add("GREEN", (byte)100);
COLOR14.add("BLUE", (byte)0);
COLOR_TOP_TOP4.add("COLOR_RGB",COLOR14);
FOUR_COLOR_BAR4.add("COLOR_TOP_TOP", COLOR_TOP_TOP4);

Module COLOR_TOP4 = main.createModule("COLOR_TOP");
Module COLOR15 = main.createModule("COLOR_RGB");
COLOR15.add("RED", (byte)-1);
COLOR15.add("GREEN", (byte)-1);
COLOR15.add("BLUE", (byte)-1);
COLOR_TOP4.add("COLOR_RGB",COLOR15);
FOUR_COLOR_BAR4.add("COLOR_TOP", COLOR_TOP4);

Module COLOR_BOT4 = main.createModule("COLOR_BOT");
Module COLOR16 = main.createModule("COLOR_RGB");
COLOR16.add("RED", (byte)-66);
COLOR16.add("GREEN", (byte)-46);
COLOR16.add("BLUE", (byte)-73);
COLOR_BOT4.add("COLOR_RGB",COLOR16);
FOUR_COLOR_BAR4.add("COLOR_BOT", COLOR_BOT4);

Module COLOR_BOT_BOT4 = main.createModule("COLOR_BOT_BOT");
Module COLOR17 = main.createModule("COLOR_RGB");
COLOR17.add("RED", (byte)-64);
COLOR17.add("GREEN", (byte)-128);
COLOR17.add("BLUE", (byte)44);
COLOR_BOT_BOT4.add("COLOR_RGB",COLOR17);
FOUR_COLOR_BAR4.add("COLOR_BOT_BOT", COLOR_BOT_BOT4);

VOTE_BAR_LOSE.add("FOUR_COLOR_BAR",FOUR_COLOR_BAR4);
VOTE_BARS_SETTING.add("VOTE_BAR_LOSE",VOTE_BAR_LOSE);
VOTEBOX_SETTINGS.add("VOTE_BARS_SETTING",VOTE_BARS_SETTING);


Module VOTEBOX_BOX_SETTING = main.createModule("VOTEBOX_BOX_SETTING");

Module TEXT_COLOR = main.createModule("TEXT_COLOR");

Module COLOR18 = main.createModule("COLOR_RGB");
COLOR18.add("RED", (byte)-1);
COLOR18.add("GREEN", (byte)-1);
COLOR18.add("BLUE", (byte)-1);

TEXT_COLOR.add("COLOR_RGB",COLOR18);

VOTEBOX_BOX_SETTING.add("TEXT_COLOR",TEXT_COLOR);

Module BACKGROUND_COLOR = main.createModule("BACKGROUND_COLOR");

Module COLOR19 = main.createModule("COLOR_RGB");
COLOR19.add("RED", (byte)95);
COLOR19.add("GREEN", (byte)-89);
COLOR19.add("BLUE", (byte)-39);

BACKGROUND_COLOR.add("COLOR_RGB",COLOR19);
VOTEBOX_BOX_SETTING.add("BACKGROUND_COLOR",BACKGROUND_COLOR);


Module TOP_BOX_BACKGROUND_COLOR = main.createModule("TOP_BOX_BACKGROUND_COLOR");

Module COLOR20 = main.createModule("COLOR_RGB");
COLOR20.add("RED", (byte)95);
COLOR20.add("GREEN", (byte)-98);
COLOR20.add("BLUE", (byte)-39);

TOP_BOX_BACKGROUND_COLOR.add("COLOR_RGB",COLOR20);

VOTEBOX_BOX_SETTING.add("TOP_BOX_BACKGROUND_COLOR",TOP_BOX_BACKGROUND_COLOR);

Module TOP_BOX_TEXT_COLOR = main.createModule("TOP_BOX_TEXT_COLOR");

Module COLOR21 = main.createModule("COLOR_RGB");
COLOR21.add("RED", (byte)-1);
COLOR21.add("GREEN", (byte)-1);
COLOR21.add("BLUE", (byte)-1);

TOP_BOX_TEXT_COLOR.add("COLOR_RGB",COLOR21);

VOTEBOX_BOX_SETTING.add("TOP_BOX_TEXT_COLOR",TOP_BOX_TEXT_COLOR);


Module MIDDLE_BOX_BACKGROUND_IMAGE = main.createModule("MIDDLE_BOX_BACKGROUND_IMAGE");
MIDDLE_BOX_BACKGROUND_IMAGE.add("IMAGE",null);
VOTEBOX_BOX_SETTING.add("MIDDLE_BOX_BACKGROUND_IMAGE",MIDDLE_BOX_BACKGROUND_IMAGE);
VOTEBOX_BOX_SETTING.add("USE_BOLD_VOTE_TOKENS",false);
VOTEBOX_BOX_SETTING.add("CONTENT_AREA_PIXEL_WIDTH",(short)240);
VOTEBOX_SETTINGS.add("VOTEBOX_BOX_SETTING",VOTEBOX_BOX_SETTING);

Module VOTEBOX_BUTTON_SETTING = main.createModule("VOTEBOX_BUTTON_SETTING");



Module TEXT_COLOR2 = main.createModule("TEXT_COLOR");

Module COLOR22 = main.createModule("COLOR_RGB");
COLOR22.add("RED", (byte)-1);
COLOR22.add("GREEN", (byte)-1);
COLOR22.add("BLUE", (byte)-1);

TEXT_COLOR2.add("COLOR_RGB",COLOR22);

VOTEBOX_BUTTON_SETTING.add("TEXT_COLOR",TEXT_COLOR2);

Module BORDER_COLOR2 = main.createModule("BORDER_COLOR");
Module COLOR23 = main.createModule("COLOR_RGB");
COLOR23.add("RED", (byte)-1);
COLOR23.add("GREEN", (byte)-1);
COLOR23.add("BLUE", (byte)-1);
BORDER_COLOR2.add("COLOR_RGB", COLOR23);
VOTEBOX_BUTTON_SETTING.add("BORDER_COLOR",BORDER_COLOR2);

Module SPECULAR_SHADING = main.createModule("SPECULAR_SHADING");

Module TOP_FADE_FROM = main.createModule("TOP_FADE_FROM");


Module COLOR24 = main.createModule("COLOR_RGB");
COLOR24.add("RED", (byte)-96);
COLOR24.add("GREEN", (byte)-96);
COLOR24.add("BLUE", (byte)-96);

TOP_FADE_FROM.add("COLOR_RGB",COLOR24);

SPECULAR_SHADING.add("TOP_FADE_FROM", TOP_FADE_FROM);

Module MIDDLE_FADE_TO = main.createModule("MIDDLE_FADE_TO");

Module COLOR25 = main.createModule("COLOR_RGB");
COLOR25.add("RED", (byte)55);
COLOR25.add("GREEN", (byte)55);
COLOR25.add("BLUE", (byte)55);

MIDDLE_FADE_TO.add("COLOR_RGB",COLOR25);

SPECULAR_SHADING.add("MIDDLE_FADE_TO", MIDDLE_FADE_TO);

Module BOTTOM_SOLID = main.createModule("BOTTOM_SOLID");
Module COLOR26 = main.createModule("COLOR_RGB");
COLOR26.add("RED", (byte)0);
COLOR26.add("GREEN", (byte)0);
COLOR26.add("BLUE", (byte)0);

BOTTOM_SOLID.add("COLOR_RGB",COLOR26);

SPECULAR_SHADING.add("BOTTOM_SOLID", BOTTOM_SOLID);
VOTEBOX_BUTTON_SETTING.add("SPECULAR_SHADING",SPECULAR_SHADING);
VOTEBOX_SETTINGS.add("VOTEBOX_BUTTON_SETTING",VOTEBOX_BUTTON_SETTING);


Module TIMOUT_BAR_SETTING = main.createModule("TIMOUT_BAR_SETTING");

Module BACKGROUND_COLOR2 = main.createModule("BACKGROUND_COLOR");


Module COLOR27 = main.createModule("COLOR_RGB");
COLOR27.add("RED", (byte)-104);
COLOR27.add("GREEN", (byte)-58);
COLOR27.add("BLUE", (byte)-26);

BACKGROUND_COLOR2.add("COLOR_RGB",COLOR27);



TIMOUT_BAR_SETTING.add("BACKGROUND_COLOR",BACKGROUND_COLOR2);

Module TIMEOUT_BAR = main.createModule("TIMEOUT_BAR");




Module SPECULAR_SHADING2 = main.createModule("SPECULAR_SHADING");

Module TOP_FADE_FROM2 = main.createModule("TOP_FADE_FROM");


Module COLOR28 = main.createModule("COLOR_RGB");
COLOR28.add("RED", (byte)-2);
COLOR28.add("GREEN", (byte)-47);
COLOR28.add("BLUE", (byte)-1);

TOP_FADE_FROM2.add("COLOR_RGB",COLOR28);

SPECULAR_SHADING2.add("TOP_FADE_FROM", TOP_FADE_FROM2);

Module MIDDLE_FADE_TO2 = main.createModule("MIDDLE_FADE_TO");

Module COLOR29 = main.createModule("COLOR_RGB");
COLOR29.add("RED", (byte)-3);
COLOR29.add("GREEN", (byte)96);
COLOR29.add("BLUE", (byte)-1);

MIDDLE_FADE_TO2.add("COLOR_RGB",COLOR29);

SPECULAR_SHADING2.add("MIDDLE_FADE_TO", MIDDLE_FADE_TO2);

Module BOTTOM_SOLID2 = main.createModule("BOTTOM_SOLID");
Module COLOR30 = main.createModule("COLOR_RGB");
COLOR30.add("RED", (byte)-21);
COLOR30.add("GREEN", (byte)55);
COLOR30.add("BLUE", (byte)-19);

BOTTOM_SOLID2.add("COLOR_RGB",COLOR30);

SPECULAR_SHADING2.add("BOTTOM_SOLID", BOTTOM_SOLID2);
TIMEOUT_BAR.add("SPECULAR_SHADING",SPECULAR_SHADING2);




TIMOUT_BAR_SETTING.add("TIMEOUT_BAR",TIMEOUT_BAR);


VOTEBOX_SETTINGS.add("TIMOUT_BAR_SETTING",TIMOUT_BAR_SETTING);

 byte[] bytes = main.writeBytes(VOTEBOX_SETTINGS);
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

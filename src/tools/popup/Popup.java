package tools.popup;

import java.util.ArrayList;
import java.util.List;

import starlight.SendOpcode;
import tools.PacketBuilder;

public class Popup {
    
    public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
    
    private String title, subtitle, message;
    private int modern = 0; // 1 wenn alle standardmäßig das haben sollen
    private int newspopup = 0; 
    private int gullideckel = 0;
    private int laufbahn = 0;
    private int trade = 0;
    private int messageproblem = 0;
    private int watchlistpopup =0;
    private int loginfailed = 0;
    private int chhistory = 0;
    private int shoppopup = 0;
    private int mentordetlef = 0;
    private int readmehis = 0;
    private int codeactivate = 0;
    private int sperrmeldung = 0;
    private int testi = 0;
    private int nicknotexist = 0;
    private int channelinfo = 0;
    private int meinesmileys = 0;
    private int design = 1,width, height, headerFontSize = 16, buttonFontSize = 16;
    private List<Panel> panels;
    private String opcode, parameter;

    public Popup(String title, String subtitle, String message, int width, int height) {
    	this.title = title;
        this.subtitle = subtitle;
        this.message = message;
        this.width = width;
        this.height = height;
        panels = new ArrayList<Panel>();
    }

    public void addPanel(Panel panel) {
        panels.add(panel);
    }

    public void setOpcode(String opcode, String parameter) {
        this.opcode = opcode;
        this.parameter = parameter;
    }
    
    public void setHeight(int height) {
    	this.height = height;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
    
    public void setDesign(int design) {
    	this.design = design;
    }
    
    public void setChannelinfo(int channelinfo) {
    	this.channelinfo = channelinfo;
    }
    
    public void setModern(int modern) {
        this.modern = modern;
    } 
    
    public void setTrade(int trade) {
        this.trade = trade;
    }
    
    public void setMeinesmileys (int meinesmileys) {
        this.meinesmileys = meinesmileys;
    }
    
    public void setMentordetlef(int mentordetlef) {
        this.mentordetlef = mentordetlef;
    }
    
    public void setReadmehis(int readmehis) {
        this.readmehis = readmehis;
    }
    
    public void setLaufbahn(int laufbahn) {
        this.laufbahn = laufbahn;
    } 
    
    public void setSperrmeldung(int sperrmeldung) {
        this.sperrmeldung = sperrmeldung;
    } 
    
    public void setMessageproblem(int messageproblem) {
        this.messageproblem = messageproblem;
    } 
    
    public void setShoppopup(int shoppopup) {
        this.shoppopup = shoppopup;
    }  
    public void setChhistory(int chhistory) {
        this.chhistory = chhistory;
    }
    
    public void setTesti(int testi) {
        this.testi = testi;
    }
    public void setNewspopup(int newspopup) {
        this.newspopup = newspopup; 
    }  
     public void setCodeactivate(int codeactivate) {
        this.codeactivate = codeactivate; 
    }  
    public void setLoginfailed(int loginfailed) {
        this.loginfailed = loginfailed;
    } 
    
    public void setGullideckel(int gullideckel) {
        this.gullideckel = gullideckel;
    }
    
     public void setNicknotexist(int nicknotexist) {
        this.nicknotexist = nicknotexist;
    }  
     
     public void setWatchlistpopup(int watchlistpopup) {
        this.watchlistpopup = watchlistpopup;
    }
    public void setHeaderFontSize(int headerFontSize) {
    	this.headerFontSize = headerFontSize;
    }    
    public void setButtonFontSize(int buttonFontSize) {
    	this.buttonFontSize = buttonFontSize;
    }

    @Override
    public String toString() {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.POPUP.getValue());

        if (modern == 1) {
               return "k\0"+title+"õf\0\0\0hÿÿÿãp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCbSchließenõcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<K°"+message+"õsÂ,f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
         
        }
         
        
        if (trade == 1) {
              return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs,f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
        
        if (newspopup == 1) {
               return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°#"+message+"õ~tpõs?f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        
        }
        
        if (readmehis ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõsÂf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
        
        
        
        if (meinesmileys ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿCãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc"+message+"õ~tpõsàf\\0\\0\\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
        
        
        
        if (watchlistpopup == 1) {
            StringBuilder bla = new StringBuilder();  
            bla.append(hexToString("6B 00"));
               bla.append(title);
               bla.append(hexToString("F5 73 66 72 69 65 6E 64 F5 2D F5 66 00 00 00 68 FF FF FF E3 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 0A 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 0A 42 E3 43 70 7E F5 42 53 70 7E F5 62 EB EB FF 47 42 42 42 42 70 7E F5 62 EB EB FF 46 70 7E F5 47 41 44 42 42 70 7E F5 62 EB EB FF 42 43 62 20 20 20 20 20 4F 4B 20 20 20 20 20 F5 63 65 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 70 7E F5 62 EB EB FF 42 43 62 73 6F 72 74 69 65 72 74 20 6E 61 63 68 20 4E 69 63 6B 6E 61 6D 65 F5 63 65 73 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 70 7E F5 62 EB EB FF 42 43 62 6C 65 74 7A 74 65 20 44 69 61 6C 6F 67 65 F5 63 65 73 64 62 67 4F 68 FF FF FF E3 45 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 57 70 7E F5 62 EB EB FF 55 2D F5 55 00 03 00 00 42 E3 E3 E3 E3 E3 43 70 7E F5 42 4E 70 42 4E 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 74 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 63 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 53 70 42 57 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 6C 2E 70 6E 67 F5 55 00 10 00 10 42 E3 43 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 63 2E 70 6E 67 F5 55 00 10 00 10 42 E3 45 70 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 6F 78 53 5F 62 72 2E 70 6E 67 F5 55 00 10 00 10 42 E3 E3 43 70 7E F5 47 41 42 42 42 70 7E F5 42 43 6C"));
               bla.append(subtitle);
               bla.append(hexToString("F5 62 67 55 66 00 00 00 68 DE DE FF E3 E3 E3 E3 43 70 7E F5 42 43 70 7E F5 42 4E 70 7E F5 55 2D F5 55 00 01 00 04 42 E3 53 70 7E F5 62 EB EB FF 55 2D F5 55 00 01 00 04 42 E3 43 70 7E F5 42 43 63"));
              bla.append(message);
               bla.append(hexToString("F5 7E 74 70 F5 73 02 4E 01 40 66 00 00 00 68 FF FF FF 69 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 FF FF E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 04 00 01 42 E3 E3 E3 E3 E3 45 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 57 70 7E F5 55 70 69 63 73 2F 6C 61 79 6F 75 74 2F 62 67 5F 74 72 65 6E 64 2E 70 6E 67 F5 55 00 0A 00 01 42 E3 E3 E3"));
       return bla.toString();
        }
        if (messageproblem == 1) {
               return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs\0úf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
        
        if (laufbahn == 1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs\0úf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }    
        
        if  (mentordetlef == 1) {
               return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõsôf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
           
        if (gullideckel ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs\0Èf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
          
        }     
   
        if (loginfailed == 1) {
              return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°#"+message+"°õ~tpõs,f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
        
        if (chhistory == 1) {
              return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs Xf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        
        }
      /* if (messagesend == 1) {
              return "k\0"+title+"õf\0\0\0hÿÿÿocloseButtonõãEl  õcgPhÿÿÿãWl  õcgPhÿÿÿãSl õcgPhÿÿÿãCpBNpBNl           õgIf\0\0\0hÿÿÿãCpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãCpBNpBNpBNc°20°_Gesendete Nachrichtenõs\0\0(f\0\0\0hÞÞÿoãããCc°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>nopic_79x79_f...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=lischen_ea94<° #°+0075+8002°°>iwhois_off_button...my_1.png<° °14°_°BB>_hlischen@94|/serverpp "|/w "<°°°_#Donnerstag, 22.08.2013 00:28:16#_ _§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°Was sollte das letztens? Einfach so zu gehen und vorallem wie du geschrieben hast, war ja wohl ein Witz was du da an dem Kopf geknallt hast oder?#°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1560405871<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-11khzbr91zr5@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>fotos/knuddels.de?n=eisblumen+kind&cnt=102&sq=&...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=eisblumen_-kind<° #°+0075+8002°°>iwhois_off_button...my_1.png<° °14°_°BB>_heisblumen kind|/serverpp "|/w "<°°°_#Mittwoch, 21.08.2013 22:50:05#_ _§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°Marvin?#°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1566297009<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-1e95twn1j1qg8@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>nopic_79x79_f...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=lisa99<° #°+0075+8002°°>iwhois_on_button...my_1.png<° °14°_°BB>_hlisa99|/serverpp "|/w "<°°°_#Mittwoch, 21.08.2013 18:50:10#_Re:_§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°geht.## #§°%07°"lisa99 schrieb am 21.08.2013 18:45:42 an x Your Feeling <3:#supi und dir?### x Your Feeling <3 schrieb am 21.08.2013 18:36:27 an lisa99:#wie geht es dir Hasi?### lisa99 schrieb am 21.08.2013 18:36:08 an x Your Feeling <3:#<3°° §# # ####Antworten mit Zitat!!!#  °° §#       Antworten mit Zitat!!!  °%00°"§##°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1580691704<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-ucrep2qg47m0@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>nopic_79x79_f...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=lisa99<° #°+0075+8002°°>iwhois_on_button...my_1.png<° °14°_°BB>_hlisa99|/serverpp "|/w "<°°°_#Mittwoch, 21.08.2013 18:36:27#_Re:_§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°wie geht es dir Hasi?### #§°%07°"lisa99 schrieb am 21.08.2013 18:36:08 an x Your Feeling <3:#<3°° §# # ####Antworten mit Zitat!!!# °%00°"§##°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1581514886<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-1189eah2j7kd1@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>nopic_79x79_f...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=lisa99<° #°+0075+8002°°>iwhois_on_button...my_1.png<° °14°_°BB>_hlisa99|/serverpp "|/w "<°°°_#Mittwoch, 21.08.2013 18:36:19#_Re:_§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°<3### #§°%07°"lisa99 schrieb am 21.08.2013 18:36:08 an x Your Feeling <3:#<3°° §# # ####Antworten mit Zitat!!!# °%00°"§##°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1581522905<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-11ao08ufbq4oi@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>fotos/knuddels.de?n=xJennchen&cnt=23&sq=&...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=xjennchen<° #°+0075+8002°°>iwhois_off_button...my_1.png<° °14°_°BB>_hxJennchen|/serverpp "|/w "<°°°_#Dienstag, 20.08.2013 18:48:35#_Re:_§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°auch etwas^^## #§°%07°"xJennchen schrieb am 20.08.2013 18:48:01 an x Your Feeling <3:#Lw du  °%00°"§##°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1667186924<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-1g5c6iao3zywo@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>fotos/knuddels.de?n=xJennchen&cnt=23&sq=&...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=xjennchen<° #°+0075+8002°°>iwhois_off_button...my_1.png<° °14°_°BB>_hxJennchen|/serverpp "|/w "<°°°_#Dienstag, 20.08.2013 15:46:34#_Re:_§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°auch.. was machst du feines?### #§°%07°"xJennchen schrieb am 20.08.2013 15:42:17 an x Your Feeling <3:#Gut und dir  °%00°"§##°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1678107901<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-387fg7blszcy@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>fotos/knuddels.de?n=Dave20009&cnt=109&sq=&...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=dave20009<° #°+0075+8002°°>iwhois_off_button...my_1.png<° °14°_°BB>_hDave20009|/serverpp "|/w "<°°°_#Dienstag, 20.08.2013 02:42:04#_ _§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°Ich werde gar nichts loeschen kannst du ihr sagen#°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1725177967<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;o0nxenpptz85@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>nopic_79x79_f...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=lisa99<° #°+0075+8002°°>iwhois_on_button...my_1.png<° °14°_°BB>_hlisa99|/serverpp "|/w "<°°°_#Montag, 19.08.2013 21:34:07#_ _§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°Hab dir auch eine geschickt#°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1743654989<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;9tho7iegie46@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#°>nopic_79x79_f...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|http://photo.knuddels.de/photos-profile.html?d=knuddels.de&id=lisa99<° #°+0075+8002°°>iwhois_on_button...my_1.png<° °14°_°BB>_hlisa99|/serverpp "|/w "<°°°_#Montag, 19.08.2013 19:02:39#_ _§#°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°Danke für die Rose#°+5000°°r°°>center<°°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m fwd;-1752742998<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m save;-1u3j4dqp489jx@$[ID]$@$[CID]$<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<° #°r°#°BB>textlinks/fiesta_banner_100x25_03.png<> Spiele das kostenlose Fantasy-MMORPG noch heute!|http://www3.knuddels.de:8080/txtl/click?d=knuddels.de&id=654&tl=2<°  °9°-Anzeige-õ~1377250480786õsDtsendbackõf\0\0\0hÞÞÿzMããEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããããSpBWl                                                           õgPf\0\0\0hÿÿÿãCbSchließenõ~closeButtonõcedbgPf\0\0\0hÿÿÿãEl                                                           õgPf\0\0\0hÿÿÿãããã";
        */
        if (shoppopup ==1) {  
              return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbkõgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<K°#"+message+"õsf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }

        if (codeactivate ==1) {
              return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<K°#"+message+"õsÖÂf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        
        }
        
        if (sperrmeldung ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs\0úf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
            
        if (nicknotexist ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõsf\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
       
        if (channelinfo ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿCãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGADBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0Bããp~õbëëÿBCbHall of Fameõcedbk/hof channel:SmileyWars:allõgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0Bããp~õbëëÿBCbModeratorenwahlõcesdbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs?f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        }
         if (testi ==1) {
            return "k\0"+title+"õf\0\0\0hÿÿÿCãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGADBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0Bããp~õbëëÿBCbMitgliederõcedbk/server rechteõgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0Bããp~õbëëÿBCbModeratorenwahlõcesdbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBCl"+subtitle+"õbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"õ~tpõs?f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã";
        
        
        } else {
        
        buffer.writeByte(0x00);
        addString(buffer, title);

        if (opcode != null) {
            buffer.writeByte('s');
            addString(buffer, opcode);
            addString(buffer, parameter);
        }

        addForeground(buffer, new int[] { 0x00, 0x00, 0x00 });
        addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
        buffer.writeByte(0xE3);

        // border right
        buffer.writeByte('E');
        buffer.writeByte('l');
        addString(buffer, "         ");
        addFontStyle(buffer, 'p', 5);
        addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
        buffer.writeByte(0xE3);

        // border left
        buffer.writeByte('W');
        buffer.writeByte('l');
        addString(buffer, "         ");
        addFontStyle(buffer, 'p', 5);
        addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
        buffer.writeByte(0xE3);

        buffer.writeByte('C');
        addLayout(buffer, 'B');

        buffer.writeByte('N');
        addLayout(buffer, 'B');

        // border top
        buffer.writeByte('N');
        buffer.writeByte('l');
        addString(buffer, " ");
        addFontStyle(buffer, 'p', 5);
        addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
        buffer.writeByte(0xE3);

        if (subtitle != null) {
            buffer.writeByte('C');
            buffer.writeByte('l');
            addString(buffer, subtitle);
            addFontStyle(buffer, 'b', headerFontSize);
            addForeground(buffer, new int[] { 0x00, 0x00, 0x00 });
            addBackground(buffer, new int[] { 0xE5, 0xE5, 0xFF });
            buffer.writeByte(0xE3);

            buffer.writeByte('S');
            buffer.writeByte('l');
            addString(buffer, " ");
            addFontStyle(buffer, 'p', 5);
            addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
            buffer.writeByte(0xE3);
        }

        buffer.writeByte(0xE3);

        if (message != null) {
            buffer.writeByte('C');
            buffer.writeByte('c');
            addString(buffer, message);
            addFrameSize(buffer, width, height);
            addForeground(buffer, new int[] { 0x00, 0x00, 0x00 });
        	addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
            if(design == 1) {
                addBackgroundImage(buffer, "pics/nc.png", 17);
            }
            buffer.writeByte(0xE3);
        }

        boolean useBorderLayouts = panels.size() > 1;
        int borderLayouts = 0;

        for (Panel panel : panels) {
            buffer.writeByte('S');

            if (useBorderLayouts) {
                borderLayouts++;
                addLayout(buffer, 'B');
                buffer.writeByte('N');
            }

            addLayout(buffer, 'F');

            for (Component component : panel.getComponents()) {
                ComponentType type = component.getType();
                buffer.writeByte(type.getValue());

                if (type == ComponentType.CHECKBOX) {
                    if (component.getText() != null) {
                        buffer.writeByte('l');
                        addString(buffer, component.getText());
                    }

                    addFontStyle(buffer, 'p', 16);
                    Checkbox checkbox = (Checkbox) component;

                    if (checkbox.isDisabled()) {
                        buffer.writeByte('d');
                    }

                    if (checkbox.isChecked()) {
                        buffer.writeByte('s');
                        buffer.writeByte('t');
                    }

                    if (checkbox.getGroup() != 0) {
                        buffer.writeByte('r');
                        addSize(buffer, checkbox.getGroup());
                    }
                } else {
                	if(type != ComponentType.CHOICE) addString(buffer, component.getText());

                    if (type == ComponentType.BUTTON) {
                        addFontStyle(buffer, 'p', buttonFontSize);
                        Button button = (Button) component;

                        if (button.isStyled()) {
                            buffer.writeByte('c');

                            if (button.isColored()) {
                                buffer.writeByte('e');
                            }
                        }

                        if (button.isClose()) {
                            buffer.writeByte('d');
                        }

                        if (button.isAction()) {
                            buffer.writeByte('s');
                        }

                        if (button.getCommand() != null) {
                            buffer.writeByte('u');
                            addString(buffer, button.getCommand());
                        }
                    } else if (type == ComponentType.TEXT_FIELD) {
                        addSize(buffer, ((TextField) component).getWidth());
                    } else if (type == ComponentType.LABEL) {
                        addFontStyle(buffer, 'p', 16);
                    } else if (type == ComponentType.CHOICE) {
                        Choice choice = (Choice) component;

                        if(choice.useIndex()) {
                            buffer.writeByte('c');
                            buffer.writeByte(choice.getSelectedIndex());
                        } else {
                            buffer.writeByte('C');
                            addString(buffer, choice.getSelected());
                        }

                        if(choice.getFontsize() > 0)
                            addFontStyle(buffer, 'p', choice.getFontsize());

                        if(choice.isDisabled()) {
                            buffer.writeByte('d');
                        }

                        addForeground(buffer, component.getForeground());
                        addBackground(buffer, component.getBackground());

                        buffer.writeByte(0xE3);
                        for(String item : choice.getItems()) {
                            addString(buffer, item);
                        }
                    } else if (type == ComponentType.TEXT_AREA) {
                        TextArea textarea = (TextArea) component;
                        addSize(buffer, textarea.getRows());
                        addSize(buffer, textarea.getColumns());

                        switch (textarea.getScrollbars()) {
                            case 0:
                                buffer.writeByte('b');
                                break;
                            case 1:
                                buffer.writeByte('s');
                                break;
                            case 2:
                                buffer.writeByte('w');
                                break;
                        }

                        if (textarea.isEditable()) {
                            buffer.writeByte('e');
                        }
                    }
                }


                if(type != ComponentType.CHOICE) {
                    addForeground(buffer, component.getForeground());
                    addBackground(buffer, component.getBackground());
                }
                buffer.writeByte(0xE3);
            }

            buffer.writeByte(0xE3);
        }

        for (int i = 0; i < borderLayouts; i++) {
            buffer.writeByte(0xE3);
        }

        buffer.writeByte(0xE3);
        buffer.writeByte(0xE3);

        return buffer.toString();
    }
    }

    private static void addSize(PacketBuilder buffer, int size) {
        buffer.writeByte('A' + size);
    }

    private static void addString(PacketBuilder buffer, String str) {
        buffer.writeString(str);
        buffer.writeByte(0xF5);
    }

    private static void addFontStyle(PacketBuilder buffer, char weight, int size) {
        if (weight != 'p') {
            buffer.writeByte(weight);
        }

        buffer.writeByte('g');
        addSize(buffer, size);
    }

    private static void addLayout(PacketBuilder buffer, char layout) {
        buffer.writeByte('p');
        buffer.writeByte(layout);
    }

    private static void addFrameSize(PacketBuilder buffer, int width, int height) {
        buffer.writeByte('s');
        buffer.writeShort(width);
        buffer.writeShort(height);
    }

    private static void addForeground(PacketBuilder buffer, int[] color) {
        buffer.writeByte('f');
        buffer.write(color);
    }

    private static void addBackground(PacketBuilder buffer, int[] color) {
        buffer.writeByte('h');
        buffer.write(color);
    }

    private static void addBackgroundImage(PacketBuilder buffer, String image, int position) {
        buffer.writeByte('i');
        addString(buffer, image);
        buffer.writeShort(position);
    }

    public static String create(String title, String subtitle, String message, int width, int height) {
        Popup popup = new Popup(title, subtitle, message, width, height);
        Panel panel = new Panel();
        Button button = new Button("   OK   ");
        popup.setButtonFontSize(16);
        button.setStyled(true);
        panel.addComponent(button);
        popup.addPanel(panel);
        return popup.toString();
    }

    public static String createClose(String title, String subtitle, String message, int width, int height) {
        Popup popup = new Popup(title, subtitle, message, width, height);
        Panel panel = new Panel();
        Button button = new Button(" Schließen ");
        popup.setButtonFontSize(16);
        button.setStyled(true);
        panel.addComponent(button);
        popup.addPanel(panel);
        return popup.toString();
    }
}

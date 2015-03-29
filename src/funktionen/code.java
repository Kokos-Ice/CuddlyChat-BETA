package funktionen;

import handler.CodeHandler;
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
import tools.*;
import tools.popup.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;




public class code {
    
       public static String timeStampToDate(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String zeit = uhrzeits.format(da);

		return zeit;
	}

	
   public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
     public static int countWords(String text, String word){
        int count=0;
        Pattern pat = Pattern.compile(Pattern.quote(word));
        Matcher m;
        for(m = pat.matcher(text); m.find(); count++);
        return count;
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
    public static void functionMake(Client client,Channel channel, String arg) {
        
           if(!client.hasPermission("cmd.code")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
if (arg.isEmpty()) {
    int basic = 0;
    String[] sms = client.getSmileys().split("%%");
    for(String id : sms) {
        if (!id.isEmpty()) {
      Usersmiley sm = Server.get().getUsersmiley(id);
      Smiley smi = Server.get().getSmiley(String.valueOf(sm.getSMID()));
      if (!sm.getBasic().isEmpty()) {
          basic++;
      }}}
    
    int code = 0;
    String[] c = client.getCodes().split("%%");

for(String s : c) {
 if (!s.isEmpty()) {  
code++;
}}


int anzahl = 0;
  String[] smss = client.getSmileys().split("%%");
    for(String id : smss) {
        if (!id.isEmpty()) {
          anzahl++;  
        }}
    StringBuilder builder = new StringBuilder();
    String dazu2 = "";
    if (anzahl >= 1) {
        dazu2 = " ("+anzahl+")";
    }
              builder.append("k\0Code Menüõscodeõmenuõf\0\0\0h¾¼ûqcodeMenuõãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClCode Menü");
            builder.append("õlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBNpGABBBbMeine Smileys"+dazu2);
          if (basic >= 1) {
            builder.append("õspgPf\0\0\0hÎÍüãbMeine Basics ("+basic+")");
          }
          String dazu = "";
          if (code >= 1) {
              dazu = " ("+code+")";
          }
            builder.append("õspgPf\0\0\0hÎÍüãbCode registrieren"+dazu);
           
            builder.append("õspgPf\0\0\0hÎÍüããClõgFh¾¼ûãSpBNpGABBBbTausch-Fenster öffnen");
            builder.append("õspgPf\0\0\0hÎÍüãbSmileys verleihen");           
            builder.append("õspgPf\0\0\0hÎÍüãbCode aktivieren");
            builder.append("õspgPf\0\0\0hÎÍüãbCode verschenken");
            builder.append("õspgPf\0\0\0hÎÍüãbLetzte Tausch-Aktionen");
            
            builder.append("õspgPf\0\0\0hÎÍüããClõgFh¾¼ûãSpBNpGABBBbSmiley-Shop");
            builder.append("õspgPf\0\0\0hÎÍüãbCode-ID prüfen");
            builder.append("õspgPf\0\0\0hÎÍüãbSuche");
            //builder.append("õspgPf\0\0\0hÎÍüããClõgFh¾¼ûãSpBNpGABBBbSmileyabo abschließen");
            //builder.append("õspbgPf\0\0\0hÎÍüãbSmiley-Codes einzeln kaufen");
            //builder.append("õspbgPf\0\0\0hÎÍüãbÜbersicht über die Abonnements");
            builder.append("õspgPf\0\0\0hÎÍüããClõgFh¾¼ûãSpBNpGABBBbSchließenõsdpgPf\0\0\0hÎÍüãããããããããããããããã");
       client.send(builder.toString());
    return;
}
if (arg.trim().equals("search")) {
    
        Popup popup = new Popup("Smiley-Suche", "Smiley-Suche", "Gebe hier deine Suchbegriffe mit Komma getrennt ein, um nach Smileys zu suchen.", 400, 100);

               
               
        Panel panel6 = new Panel();
        panel6.addComponent(new Label("Suchbegriffe: "));
        TextField lol = new TextField(50);
        panel6.addComponent(lol);        
        popup.addPanel(panel6);
           Panel panel20 = new Panel();
        Button button = new Button("Suchen");
       
         Button button2 = new Button("Abbrechen");
         panel20.addComponent(button);
        panel20.addComponent(button2);
         button.enableAction();
        popup.addPanel(panel20);
         popup.setOpcode(ReceiveOpcode.SMEDIT.getValue(), "search~code");
         client.send(popup.toString());
        
    return;
}
if (arg.trim().equals("lend")) {
    
    
    
    String sms = "";
    
    if (!client.getSmileys().isEmpty()) {
         String[] smileys  = client.getSmileys().split("%%");
       for(String d : smileys) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sm = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getVerliehen().isEmpty() && sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sm.getTauschbar()) {
           if (!sms.isEmpty()) {
               sms += ",";
           }
         
           sms += d;
       }
       }}
         
    
    if (sms.isEmpty()) {
       
        Popup popup = new Popup("Problem", "Problem", "#Derzeit verfügst du über keine tauschbaren Smileys oder Codes.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
      }
    
    
    client.send(PacketCreator.createLeihenWindow(client,sms.split(",")));
         return;
}
if (arg.trim().equals("lasttrade")) {
    String text = "";
   if (client.getCodeTradeLog().isEmpty()) {
       text = "- Keine Einträge vorhanden -";
   } else {
       for(String v : client.getCodeTradeLog().split("\\|")) {
           if (!v.isEmpty()) {
               
               
     
            String[] h = v.split("~");
     int anzahl = countChars(h[1], ',')+1;
     int anzahl2 = countChars(h[4], ',')+1;
     
     String smimages = "";
for(String id : h[1].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
   if (bla != null) {
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}
           }

 String smimages2 = "";
for(String id : h[4].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
  if (bla != null) {
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (sss != null) {
    if (!smimages2.isEmpty()) {
        smimages2 += ", ";
    }
    smimages2 += sss.getKCode();
}}}
     text += "_"+timeStampToDate(Long.parseLong(h[6]))+"_:##_°BB>_h"+h[0]+"|/serverpp \"|/w \"<r°_ tauschte:##°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";

if (!h[2].equals("0")) {
    text += "#°>bullet2...b.png<° "+h[2]+" Knuddels";
}
text += "##_°BB>_h"+h[3]+"|/serverpp \"|/w \"<r°_ tauschte:##°>bullet2...b.png<° _"+anzahl2+" Smileys_ ("+smimages2+")#";

if (!h[5].equals("0")) {
    text += "#°>bullet2...b.png<° "+h[5]+" Knuddels";
}

text += "°-°";
           }
       }
       
       
   }
    
      Popup popup = new Popup("Letzte Tausch-Aktionen von "+client.getName(), "Letzte Tausch-Aktionen von "+client.getName(), text, 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setTrade(1);
                        client.send(popup.toString());
                        return;
}
if (arg.trim().equals("trade")) {
   
    
    String sms = "";
    
    if (!client.getSmileys().isEmpty()) {
         String[] smileys  = client.getSmileys().split("%%");
       for(String d : smileys) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sm = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getVerliehen().isEmpty() && sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sm.getTauschbar()) {
           if (!sms.isEmpty()) {
               sms += ",";
           }
         
           sms += d;
       }
       }}
         
    
    if (sms.isEmpty()) {
    
        Popup popup = new Popup("Problem", "Problem", "#Derzeit verfügst du über keine tauschbaren Smileys oder Codes.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
      }
  
    
     client.send(tools.PacketCreator.tauschFenster(client,sms.split(",")));
    
    return;
} 
if (arg.equals("id")) {
    client.send("k\0Code-ID prüfenõscodeõCode-ID prüfenõf\0\0\0h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClCode-ID prüfenõlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBNcGib weiter unten die zu überprüfende ID ein.õs\0xf\0\0\0h¾¼ûi-õ\0\0oãClõgDh¾¼ûãSpFlID: õcgPf\0\0\0h¾¼ûãfõMf\0\0\0hÿÿÿãbCode PrüfenõspgPf\0\0\0h¾¼ûããããã");
    return;
}
if (arg.equals("aktiv") || arg.split(":")[0].equals("aktiv")) {
    
    
     String code1 = "";
    String code2 = "";
    String code3 = "";
    String code4 = "";
    if (!arg.equals("aktiv")) {
        String[] c = arg.split(":")[1].split("-");
        code1 = c[0];
        code2 = c[1];
        code3 = c[2];
        code4 = c[3];
    }
    
     Popup popup = new Popup("Code aktivieren", "Code aktivieren", "Gib weiter unten die vier Buchstabenblöcke ein, die du in deiner Nachricht findest. Durch einen anschließenden Klick auf _Aktivieren_ wird dein Code für deinen Nicknamen aktiviert.#Beachte: Nach Aktivierung kann der Code nicht mehr verschenkt werden.", 400, 170);

  Panel panel7 = new Panel();
         panel7.addComponent(new Label("Code: "));
          TextField lol = new TextField(5);
        lol.setText(code1);
        panel7.addComponent(lol);          
      
        panel7.addComponent(new Label(" - "));
          TextField lol2 = new TextField(5);
        lol2.setText(code2);
        panel7.addComponent(lol2);
        panel7.addComponent(new Label(" - "));
           TextField lol3 = new TextField(5);
        lol3.setText(code3);
        panel7.addComponent(lol3);
         panel7.addComponent(new Label(" - "));
           TextField lol4 = new TextField(5);
        lol4.setText(code4);
        panel7.addComponent(lol4);
         popup.addPanel(panel7);
       Panel panel20 = new Panel();
      Button button = new Button("Aktivieren");
       Button button2 = new Button("Schließen");
      panel20.addComponent(button);
      panel20.addComponent(button2);
       button.enableAction();
      button.disableClose();
       popup.addPanel(panel20);
       popup.setOpcode(ReceiveOpcode.CODE.getValue(), "aktivieren");
       client.send(popup.toString());
    
    return;
}

if (arg.equals("reg")) {
    if (client.getCodes().isEmpty()) {
    client.send("k\0Code registrierenõscodeõCode registrierenõf\0\0\0h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClCode registrierenõlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBNcGib deinen _Smiley-Aktivierungs-Code_ ein und klicke auf _Registrieren_ um ihn für deinen Nick zu registrieren.##_Nach_ der Registrierung kannst du den Code entweder für dich selbst _aktivieren_, an jemand anderen _verschenken_ oder ihn mit anderen _tauschen_.õs\0f\0\0\0h¾¼ûi-õ\0\0oãClõgDh¾¼ûãSpFlCode: õcgPf\0\0\0h¾¼ûãfõSf\0\0\0hÿÿÿãããSpFbRegistrierenõsbgPf\0\0\0h¾¼ûãbSchließenõdpgPf\0\0\0h¾¼ûãããã");
    } else {
    // |NAME-ID|
    String text = "°+0000°°14>left<[120,120,120]°_Codes aktivieren und verschenken_°°°12°#°+9505°_Tauschen von registrierten Codes_ über den üblichen °BB>_hTausch-Dialog|/code trade<r12° oder _°BB>_halle Codes|/code activateall<r12°_ auf einmal aktivieren.###";
String[] c = client.getCodes().split("%%");
String kats = "";

for(String s : c) {
    String[] v = s.split("\\|");
    Codes code = Server.get().getCode(v[0]);
    if (code != null) {
    kats +="|"+code.getKategorie()+";"+code.getCodeid()+"|";
    }
    }
String versch = "";
int buttonid = 0;
for(String lol : kats.split("\\|")) {
    
    
    if (!lol.isEmpty()) {
        String[] xd = lol.split(";",2);
  if (!versch.contains("|"+xd[0]+"|")) {
      versch += "|"+xd[0]+"|";
  
    Codes x = Server.get().getCode(xd[1]);
    String codei = x.getCodecode();
   
  int zahl = countWords(kats,"|"+xd[0]+";"); 
text += "°+9505°°>{imageboxstart}hr-line_w128..repeat.hover.textHover_0.cursor_0.loadimages_128.my_0.mx_10.png<°°+0020°°>left<15°_°B>_h"+xd[0]+"|/code showserie:"+xd[0]+"<°_#°12°°K°°+0025+9005°##_°>bullet2...b.png<°   "+zahl+"x_ registriert#°+9020°°+0228°_°>{button}  Aktivieren  |"+(buttonid+1)+"|call|_c/code ask:"+codei+"|height|34|noborder|1|cursor|12|images|button-blue~hoverbutton-blue~button-blue.mx_1.my_1<°_°+0303°_°>{button} Verschenken |"+(buttonid+2)+"|call|_c/code send2:"+codei+"|height|34|noborder|1|cursor|12|images|button-blue~hoverbutton-blue~button-blue.mx_1.my_1<°_#°+9515°#°>{imageboxend}<°#";
}}
    buttonid = buttonid+3;
}



Popup popup = new Popup("Registrierte Codes", "Registrierte Codes", text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setCodeactivate(1);
                 client.send(popup.toString());
                 return;
    }
    
    return;
}
if (arg.equals("?")) {
  if (client.getSmileys().isEmpty() && client.getSmileys2().isEmpty()) {
    
      Popup popup = new Popup("Keine Smileys vorhanden", "Keine Smileys vorhanden", "#Du besitzt aktuell keine Smileys.##Vielleicht hast du schon °>Codes|/code reg<°, die du aktivieren könntest.##Oder besuche unseren °BB°_°>_2Smiley-Shop|/shop<°_°r°, um Codes zu erwerben.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
  }
  String text = "";
  if(!client.getSmileys().isEmpty()) {
      text = "°>center<°°BB°_Eigene Smileys_°r°##°>left<°";
  
    String ss = ""; 
    String[] sms = client.getSmileys().split("%%");
    for(String id : sms) {
        if (!id.isEmpty()) {
            Usersmiley usm = Server.get().getUsersmiley(id);
            Smiley sm = Server.get().getSmiley(String.valueOf(usm.getSMID()));
       // System.out.println(sm.getName());
            if (usm.getVerliehen().isEmpty()) {
            if (ss.contains("%%"+sm.getID()+"|")) {
             
               for(String v : ss.split("%%")) {
                  v = "%%"+v+"%%";
                   if (!v.isEmpty()) {
                       if (v.startsWith("%%"+sm.getID()+"|")) {
                           String[] haha = v.replace("%%","").split("\\|");
                           
                         String neu = "%%"+sm.getID()+"|"+(Integer.parseInt(haha[1])+1)+"|"+haha[2]+","+id+"%%";
                     ss = ss.replace(v,neu);
                       } 
               }}
                
            } else {
            ss += "%%"+sm.getID()+"|1|"+id+"%%";
            }
           }
    }}
    
 
   for(String val : Server.get().getSortSmileyoverviewbyKategorie(ss).split("%%")) {
   if (!val.isEmpty()) {
   Smiley sm = Server.get().getSmiley(val.split("\\|")[0]);
   String sec = "";
   if (sm.getSelten() == 10) {
        sec = " °RR°_(Secret-Smiley!)_°r°";
    } else if (sm.getSelten() == 11) {
        sec = " °RR°_(TOP-SECRET-SMILEY!)_°r°";
   }
   
   String blasen = "";
   
   int anzahl = Integer.parseInt(val.split("\\|")[1]);
   if (!sm.getSpez()) {   
    if (sm.getSelten() == 0) {
               if (anzahl >= 2) {
                   blasen = " (" + anzahl + "x, Übergangssmiley)";
               } else {
                  blasen = " (Übergangssmiley)";
               }}  else if (sm.getTauschbar()) {
                if (anzahl >= 2) {
                  blasen = " (" + anzahl + "x, tauschbar)";
               } else {
                   blasen = " (tauschbar)";
                 }
              }else if (anzahl >= 2) {
                blasen = " (" + anzahl + "x)";
               }
   }
   
    text += String.format("#°>{table|w1|min200<°_%s_§%s%s°>{tc}<°°>right<°_%s_°>{endtable}<°°>left<°", sm.getName2(), sec, blasen, sm.getSeltenString());
  int b = 0;
  String dddd = "";
    for(String idd : val.split("\\|")[2].split(",")) {
      Usersmiley dd = Server.get().getUsersmiley(idd);
      
      
      if (!dd.getBan1().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan1())) {
                  dd.setBan1("");
//                   ModuleCreator.UPDATE_SB(client);
                  
              }}          
              if (!dd.getBan2().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan2())) {
                  dd.setBan2("");
        //    ModuleCreator.UPDATE_SB(client); 
              }}
               if (!dd.getBan3().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan3())) {
                  dd.setBan3("");
           //     ModuleCreator.UPDATE_SB(client);
              }}
              dd = Server.get().getUsersmiley(idd);
              
      if (!dd.getBan1().isEmpty()) {
       b = 1;
       dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan1()))+"#°+0000°";
      }
      if (!dd.getBan2().isEmpty()) {
           b = 1;
                dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan2()))+"#°+0000°";
            }
      if (!dd.getBan3().isEmpty()) {
           b = 1;
                dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan3()))+"#°+0000°";
            }
      
  }
   if (b == 1) {
   text += "##°RR°_Feature wieder einsetzbar:_°r° ";
   text += dddd+"##";
   }
  
    text += sm.getKCode();
  if (!sm.getSyntax().isEmpty()) {
       text += " = "+sm.getSyntax();
  } 
 if (!sm.getText().isEmpty()) {
    text += "#"+sm.getText();
 }
  
       text += sm.getFeature();
       
      // Nach dem Smiley der Abstand zum nächsten Smiley
       text +="###";
       text += "°-°";
}}}
   
  
  
  
  // feature kacke
  
  
  
   if(!client.getAllowedFeatures().isEmpty()) {
      text += "°>center<°°BB°_Einmalige Featurenutzung_°r°##°>left<°";
  
    String ss = ""; 
    String[] sms = client.getAllowedFeatures().split("\\|");
    for(String id : sms) {
        if (!id.isEmpty()) {
         Feature ft = Server.get().getFeature(id);
          
            if (ss.contains("%%"+ft.getName()+"|")) {
             
               for(String v : ss.split("%%")) {
                  v = "%%"+v+"%%";
                   if (!v.isEmpty()) {
                       if (v.startsWith("%%"+ft.getName()+"|")) {
                           String[] haha = v.replace("%%","").split("\\|");
                           
                         String neu = "%%"+ft.getName()+"|"+(Integer.parseInt(haha[1])+1)+"%%";
                     ss = ss.replace(v,neu);
                       } 
               }}
                
            } else {
            ss += "%%"+ft.getName()+"|1%%";
            }
           
    }}
    
 
   for(String val : ss.split("%%")) {
  if (!val.isEmpty()) {
      String[] a = val.split("\\|");
      Feature sss = Server.get().getFeature(a[0]);
       text += "##_"+a[1]+"x Feature: "+sss.getName()+"_#°>features/converter/chunk_1xfeature.gif<°##°RR°_FEATURE:_°r° "+sss.getText()+"###";
       
 
  
       text += "°-°";
}}}
  
  // ende
  
  
   
   if (!client.getSmileys2().isEmpty()) {
    String ss = ""; 
    String[] sms = client.getSmileys2().split("%%");
    for(String id : sms) {
    
          if (!id.isEmpty()) {
            Usersmiley usm = Server.get().getUsersmiley(id);
            if (usm != null) {
            Smiley sm = Server.get().getSmiley(String.valueOf(usm.getSMID()));
           if (!usm.getVerliehen().isEmpty()) {
            if (ss.contains("%%"+sm.getID()+"|")) {
             
               for(String v : ss.split("%%")) {
                  v = "%%"+v+"%%";
                   if (!v.isEmpty()) {
                       if (v.startsWith("%%"+sm.getID()+"|")) {
                           String[] haha = v.replace("%%","").split("\\|");
                           
                         String neu = "%%"+sm.getID()+"|"+(Integer.parseInt(haha[1])+1)+"|"+haha[2]+","+id+"%%";
                     ss = ss.replace(v,neu);
                       } 
               }}
                
            } else {
            ss += "%%"+sm.getID()+"|1|"+id+"%%";
            }
           }}
    }}
   if (!ss.isEmpty()) { 
      
       
   text += "°BB>CENTER<°_Geliehene Smileys_#°r>LEFT<°##";
    
   
    for(String val : ss.split("%%")) {
   if (!val.isEmpty()) {
   Smiley sm = Server.get().getSmiley(val.split("\\|")[0]);
   String sec = "";
   if (sm.getSelten() == 10) {
        sec = " °RR°_(Secret-Smiley!)_°r°";
    } else if (sm.getSelten() == 11) {
        sec = " °RR°_(TOP-SECRET-SMILEY!)_°r°";
   }
   
   String blasen = "";
   
   int anzahl = Integer.parseInt(val.split("\\|")[1]);
   if (!sm.getSpez()) { 
    if (sm.getSelten() == 0) {
               if (anzahl >= 2) {
                   blasen = " (" + anzahl + "x, Übergangssmiley)";
               } else {
                  blasen = " (Übergangssmiley)";
               }}  else if (sm.getTauschbar()) {
                if (anzahl >= 2) {
                  blasen = " (" + anzahl + "x, tauschbar)";
               } else {
                   blasen = " (tauschbar)";
                 }
              }else if (anzahl >= 2) {
                blasen = " (" + anzahl + "x)";
               }
   }
      for(String idd : val.split("\\|")[2].split(",")) {
      Usersmiley dd = Server.get().getUsersmiley(idd);
     String[] vals = dd.getVerliehen().split("\\|");
 
     
   text += "Von: °>_h"+dd.getUser()+"|/serverpp \"|/w \"<°  (bis "+timeStampToDate(Long.parseLong(vals[1]))+")#";
      }
   text += "#";
   text += String.format("°>{table|w1|min200<°_%s_§%s%s°>{tc}<°°>right<°_%s_°>{endtable}<°°>left<°", sm.getName2(), sec, blasen, sm.getSeltenString());
  int b = 0;
  String dddd = "";
    for(String idd : val.split("\\|")[2].split(",")) {
      Usersmiley dd = Server.get().getUsersmiley(idd);
      
      
      if (!dd.getBan1().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan1())) {
                  dd.setBan1("");
              }}          
              if (!dd.getBan2().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan2())) {
                  dd.setBan2("");
              }}
               if (!dd.getBan3().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan3())) {
                  dd.setBan3("");
              }}
              dd = Server.get().getUsersmiley(idd);
              
      if (!dd.getBan1().isEmpty()) {
       b = 1;
       dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan1()))+"#°+0000°";
      }
      if (!dd.getBan2().isEmpty()) {
           b = 1;
                dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan2()))+"#°+0000°";
            }
      if (!dd.getBan3().isEmpty()) {
           b = 1;
                dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan3()))+"#°+0000°";
            }
      
  }
   if (b == 1) {
   text += "##°RR°_Feature wieder einsetzbar:_°r° ";
   text += dddd+"##";
   }
  
    text += sm.getKCode();
  if (!sm.getSyntax().isEmpty()) {
       text += " = "+sm.getSyntax();
  } 
 if (!sm.getText().isEmpty()) {
    text += "#"+sm.getText();
 }
  
       text += sm.getFeature();
       
       
 
  
       text += "°-°";
}}
    
   }
   }
   
   
   if (!client.getSmileys().isEmpty()) {
    String ss = ""; 
    String[] sms = client.getSmileys().split("%%");
    for(String id : sms) {
        if (!id.isEmpty()) {
            Usersmiley usm = Server.get().getUsersmiley(id);
            Smiley sm = Server.get().getSmiley(String.valueOf(usm.getSMID()));
           if (!usm.getVerliehen().isEmpty()) {
            if (ss.contains("%%"+sm.getID()+"|")) {
             
               for(String v : ss.split("%%")) {
                  v = "%%"+v+"%%";
                   if (!v.isEmpty()) {
                       if (v.startsWith("%%"+sm.getID()+"|")) {
                           String[] haha = v.replace("%%","").split("\\|");
                           
                         String neu = "%%"+sm.getID()+"|"+(Integer.parseInt(haha[1])+1)+"|"+haha[2]+","+id+"%%";
                     ss = ss.replace(v,neu);
                       } 
               }}
                
            } else {
            ss += "%%"+sm.getID()+"|1|"+id+"%%";
            }
           }
    }}
   if (!ss.isEmpty()) { 
      
       
   text += "°BB>CENTER<°_Verliehene Smileys_#°r>LEFT<°##";
    
   
    for(String val : ss.split("%%")) {
   if (!val.isEmpty()) {
   Smiley sm = Server.get().getSmiley(val.split("\\|")[0]);
   String sec = "";
   if (sm.getSelten() == 10) {
        sec = " °RR°_(Secret-Smiley!)_°r°";
    } else if (sm.getSelten() == 11) {
        sec = " °RR°_(TOP-SECRET-SMILEY!)_°r°";
   }
   
   String blasen = "";
   
   int anzahl = Integer.parseInt(val.split("\\|")[1]);
   if (!sm.getSpez()) { 
    if (sm.getSelten() == 0) {
               if (anzahl >= 2) {
                   blasen = " (" + anzahl + "x, Übergangssmiley)";
               } else {
                  blasen = " (Übergangssmiley)";
               }}  else if (sm.getTauschbar()) {
                if (anzahl >= 2) {
                  blasen = " (" + anzahl + "x, tauschbar)";
               } else {
                   blasen = " (tauschbar)";
                 }
              }else if (anzahl >= 2) {
                blasen = " (" + anzahl + "x)";
               }
   }
      for(String idd : val.split("\\|")[2].split(",")) {
      Usersmiley dd = Server.get().getUsersmiley(idd);
      String[] vals = dd.getVerliehen().split("\\|");
   text += "An: °>_h"+vals[0]+"|/serverpp \"|/w \"<°  (bis "+timeStampToDate(Long.parseLong(vals[1]))+")#";
      }
   text += "#";
   text += String.format("°>{table|w1|min200<°_%s_§%s%s°>{tc}<°°>right<°_%s_°>{endtable}<°°>left<°", sm.getName2(), sec, blasen, sm.getSeltenString());
  int b = 0;
  String dddd = "";
    for(String idd : val.split("\\|")[2].split(",")) {
      Usersmiley dd = Server.get().getUsersmiley(idd);
      
      
      if (!dd.getBan1().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan1())) {
                  dd.setBan1("");
              }}          
              if (!dd.getBan2().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan2())) {
                  dd.setBan2("");
              }}
               if (!dd.getBan3().isEmpty()) {
              if ((System.currentTimeMillis()/1000) > Long.parseLong(dd.getBan3())) {
                  dd.setBan3("");
              }}
              dd = Server.get().getUsersmiley(idd);
              
      if (!dd.getBan1().isEmpty()) {
       b = 1;
       dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan1()))+"#°+0000°";
      }
      if (!dd.getBan2().isEmpty()) {
           b = 1;
                dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan2()))+"#°+0000°";
            }
      if (!dd.getBan3().isEmpty()) {
           b = 1;
                dddd += "°+7215°°>bullet2.png<° "+timeStampToDate(Long.parseLong(dd.getBan3()))+"#°+0000°";
            }
      
  }
   if (b == 1) {
   text += "##°RR°_Feature wieder einsetzbar:_°r° ";
   text += dddd+"##";
   }
  
    text += sm.getKCode();
  if (!sm.getSyntax().isEmpty()) {
       text += " = "+sm.getSyntax();
  } 
 if (!sm.getText().isEmpty()) {
    text += "#"+sm.getText();
 } 
  
      text += sm.getFeature();
       
       
 
  
       text += "°-°";
}}
    
   }
   }
   
    Popup popup = new Popup("Smiley-Überischt " +client.getName(), "Smiley-Übersicht " +client.getName(), text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setNewspopup(1);
                 client.send(popup.toString());
                 return;
   
    

}
if (arg.equals("serie")) {
    
        String[] c = client.getCodes().split("%%");
String kats = "";

for(String s : c) {
    if (!s.isEmpty()) {
    String[] v = s.split("\\|");
    Codes code = Server.get().getCode(v[0]);
    kats +="|"+code.getKategorie()+";"+code.getCodeid()+"|";
    }
    }
    


    String text = "°>center<°";
    for (String value : Server.get().getSortKategorie().split("\\|")) {
          if (!value.isEmpty()) {
              Kategorie kat = Server.get().getKategorie(value);
              String[] im = kat.getSmileysWithoutBasic().split(",");
          int anzahl = countChars(kat.getSmileys(),',')+1;
           Smiley sm1s = null;
            Smiley sm2s = null;
            Smiley sm3s = null;
                try {
           sm1s = Server.get().getSmiley(im[0]);
           sm2s = Server.get().getSmiley(im[1]);
           sm3s = Server.get().getSmiley(im[2]);
               } catch(Exception ex) {
                }
            String sm1 = "";
            String sm2 = "";
            String sm3 = "";
            if (sm1s != null) {
                sm1 = sm1s.getKCode();
            }
              if (sm2s != null) {
                sm2 = sm2s.getKCode();
            }
                if (sm3s != null) {
                sm3 = sm3s.getKCode();
            }
        text += " ##_°16K>"+kat.getName()+"|/code showserie:"+kat.getName()+"<14°_#"+kat.getCreate()+", Smileys: "+anzahl+"#";
        text += sm1+sm2+sm3+"#";
        if (countWords(kats,"|"+kat.getName()+";") >= 1) {
        text += "#_°[0,0,220]°"+countWords(kats,"|"+kat.getName()+";")+"x registriert°°_#";
       // HIER MÜSSTE HIN!
        
        }
        
    }
    }
    Popup popup = new Popup("Serien-Übersicht","Serien-Übersicht", text, 450, 450);

      client.send(popup.toString());
    
    return;
}
if (arg.equals("basic")) {
    String text = "";
    String[] sms = client.getSmileys().split("%%");
    for(String id : sms) {
        if (!id.isEmpty()) {
      Usersmiley sm = Server.get().getUsersmiley(id);
      Smiley smi = Server.get().getSmiley(String.valueOf(sm.getSMID()));
      if (!sm.getBasic().isEmpty()) {
          text += smi.getKCode()+" _"+smi.getName2()+"_: "+sm.getBasic().split("\\|")[0]+"°-°";
      }
    }}
    
    if (text.isEmpty()) {
     String popup = Popup.create("Problem", "Problem", "Du hast keine Basic-Smileys.", 400, 200);
         client.send(popup);
         return;
    }
    
    String popup = Popup.create("Basic-Smiley von "+client.getName(), "Basic-Smiley von "+client.getName(), "Folgende Basic-Smileys entwickeln sich in den nächsten Tagen:##"+text, 400, 200);
         client.send(popup);
         return;
}  if (arg.equals("old")) {
    if (client.getCodeinfo().equals(" |~~~| ") || client.getCodeinfo().isEmpty()) {
      client.sendButlerMessage(channel.getName(), "Du hast bisher keine Geschenke erhalten.");
    return;
    }
     StringBuilder p = new StringBuilder("Folgende Geschenke wurden in letzter Zeit für Dich hinterlegt:##");
            
        for(String info : client.getCodeLog()) {
                if (!info.isEmpty()) {
            	String[] haha = info.split("\\|~\\|");
               
                String nick = haha[0];
                String[] infos = haha[1].split(":");
                String text = haha[2];
            	String time = haha[3];
                          
                p.append("_Ein °>present.gif<° von °>_2"+nick+"|/serverpp \"|/serverpp +\"<°_:##"+text+"§#erhalten am "+timeStampToDate(Long.parseLong(time))+"#°-°");

             }}
           Popup popup = new Popup("Erhaltene Geschenke", "Erhaltene Geschenke", p.toString(), 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return; 
    
  
}

if (arg.contains("send2:")) {
    
    int found = 0;
        String id = "";
        for(Codes d : Server.get().getCode()) {
        if (d.getCodecode().equals(arg.split(":")[1])) {
            found = 1;
            id = d.getCodeid();
        }
        }
        if (found == 0) {
            return;
        }
        Codes c = Server.get().getCode(id);
        Kategorie k = Server.get().getKategorie(c.getKategorie());
    StringBuilder lala = new StringBuilder();
   lala.append(hexToString("6B 00 43 6F 64 65 20 76 65 72 73 63 68 65 6E 6B 65 6E F5 73"));
   lala.append("code");
   lala.append(hexToString("F5"));
   lala.append("send2~"+c.getCodecode());
   lala.append(hexToString("F5 66 00 00 00 68 BE BC FB E3 57 6C 20 20 F5 67 4D 68 BE BC FB E3 45 6C 20 20 F5 67 4D 68 BE BC FB E3 4E 6C F5 67 46 68 BE BC FB E3 53 6C F5 67 46 68 BE BC FB E3 43 70 42 4E 70 42 43 6C 43 6F 64 65 20 76 65 72 73 63 68 65 6E 6B 65 6E F5 6C 67 53 66 00 00 00 68 E5 E5 FF E3 53 6C F5 67 46 68 BE BC FB E3 E3 43 70 42 4E 63 42 69 73 74 20 64 75 20 73 69 63 68 65 72 2C 20 64 61 73 73 20 64 75 20 65 69 6E 65 6E 20 72 65 67 69 73 74 72 69 65 72 74 65 6E 20 43 6F 64 65 20 64 65 72 20 53 65 72 69 65 23 B0 3E 63 65 6E 74 65 72 3C B0 5F B0 52 3E 7B 6C 69 6E 6B 68 6F 76 65 72 63 6F 6C 6F 72 7D 3C 42 3E"));
   lala.append(c.getKategorie());
   lala.append(hexToString("7C"));
   lala.append("/code showserie:"+k.getName());
   lala.append(hexToString("3C 4B B0 5F 23 B0 3E 6C 65 66 74 3C B0 6A 65 74 7A 74 20 5F 76 65 72 73 63 68 65 6E 6B 65 6E 5F 20 6D F6 63 68 74 65 73 74 3F 23 23 5F 48 69 6E 77 65 69 73 3A 5F 20 44 69 65 20 43 6F 64 65 73 20 77 65 72 64 65 6E 20 62 65 69 6D 20 56 65 72 73 63 68 65 6E 6B 65 6E 20 66 FC 72 20 64 65 6E 20 45 6D 70 66 E4 6E 67 65 72 20 5F 73 6F 66 6F 72 74 20 61 6B 74 69 76 69 65 72 74 5F 2E F5 73 01 3F 00 A0 66 00 00 00 68 BE BC FB 69 2D F5 00 00 6F E3 43 6C F5 67 44 68 BE BC FB E3 53 70 42 57 70 47 42 42 44 44 6C 46 FC 72 20 28 4E 69 63 6B 29 3A 20 F5 63 67 50 66 00 00 00 68 BE BC FB E3 E3 43 70 42 57 70 47 42 42 44 44 70 46 66 F5 62 66 00 00 00 68 FF FF FF E3 E3 E3 43 6C F5 63 67 46 68 BE BC FB E3 E3 53 6C F5 63 67 46 68 BE BC FB E3 E3 E3 53 70 42 4E 70 42 57 6C 47 65 73 63 68 65 6E 6B 6B 61 72 74 65 6E 74 65 78 74 3A F5 63 67 50 66 00 00 00 68 BE BC FB E3 43 6C F5 63 67 46 68 BE BC FB E3 53 74 F5 46 5F 73 65 66 00 00 00 68 FF FF FF 67 51 E3 E3 53 70 46 62 56 65 72 73 63 68 65 6E 6B 65 6E F5 73 64 62 67 50 66 00 00 00 68 BE BC FB E3 62 53 63 68 6C 69 65 DF 65 6E F5 64 70 67 50 66 00 00 00 68 BE BC FB E3 E3 E3 E3 E3"));
    
    
    client.send(lala.toString());
    return;
}
if (arg.equals("send") || arg.split(":")[0].equals("send")) {
    String code1 = "";
    String code2 = "";
    String code3 = "";
    String code4 = "";
    if (!arg.equals("send")) {
        String[] c = arg.split(":")[1].split("-");
        code1 = c[0];
        code2 = c[1];
        code3 = c[2];
        code4 = c[3];
    }
     Popup popup = new Popup("Code verschenken", "Code verschenken", "Gib weiter unten die vier Buchstabenblöcke ein, die du in der Nachricht findest. Anschließend gibst du den Nicknamen des Glücklichen an, der von dir beschenkt werden soll und schreibst einen netten Kartentext.", 150, 100);

         Panel panel7 = new Panel();
         panel7.addComponent(new Label("Code: "));
          TextField lol = new TextField(5);
        lol.setText(code1);
        panel7.addComponent(lol);          
      
        panel7.addComponent(new Label(" - "));
          TextField lol2 = new TextField(5);
        lol2.setText(code2);
        panel7.addComponent(lol2);
        panel7.addComponent(new Label(" - "));
           TextField lol3 = new TextField(5);
        lol3.setText(code3);
        panel7.addComponent(lol3);
         panel7.addComponent(new Label(" - "));
           TextField lol4 = new TextField(5);
        lol4.setText(code4);
        panel7.addComponent(lol4);
         popup.addPanel(panel7);

        Panel panel8 = new Panel();
         panel8.addComponent(new Label("Für (Nick): "));
         panel8.addComponent(new TextField(38, ""));
        popup.addPanel(panel8);
         Panel panel9 = new Panel();
         panel9.addComponent(new Label("Geschenkkartentext:                                                             "));
         popup.addPanel(panel9);
 
        Panel panel10 = new Panel();
         panel10.addComponent(new TextArea(6, 55, ""));
         popup.addPanel(panel10);
         Panel panel20 = new Panel();
        Button button = new Button("Verschenken");
         Button button2 = new Button("Schließen");
         panel20.addComponent(button);
         panel20.addComponent(button2);
         button.enableAction();
         button.disableClose();
         popup.addPanel(panel20);
         popup.setOpcode(ReceiveOpcode.CODE.getValue(), "sent");
         client.send(popup.toString());
    return;
}
 String[] val = arg.split(":");

 String[] sync = arg.split(" ",2);
 if (sync[0].equals("-SB") || sync[0].equals("!SB") || sync[0].equals("!SBD")) {
     
     
       String fid = "";
     String sms = "";
   String syncs = sync[1];
  for(String sy : syncs.split(" ")) {
      int gefunden = 0;
      for(String id : client.getSmileys().split("%%")) {
    if (!id.isEmpty()) {  
     Usersmiley s = Server.get().getUsersmiley(id);
     Smiley ss = Server.get().getSmiley(String.valueOf(s.getSMID()));
     
     int nochnetdrauf = 0;
     
     for(Favs f : Server.get().getFavs()) {
         if (f.getUser().equals(client.getName()) && f.getSMID().equals(String.valueOf(ss.getID()))) {
             nochnetdrauf = 1;
             fid = f.getID();
         }
     }
     
     if (nochnetdrauf == 1) {     
     
     if (ss.getSyntax().equals(sy) && !sms.contains("|"+ss.getID()+"|")) {     
        gefunden = 1;
        sms += "|"+ss.getID()+"~"+fid+"|";
  }
     } else {
         gefunden = 1;
     }
    
    
    
    } }
  
   if (gefunden == 0) {
        client.sendButlerMessage(channel.getName(),"Du hast diesen Smiley nicht: "+sy);
    }
    
  }
     
  
   for(String hh : sms.split("\\|")) {
      if (!hh.isEmpty()) {
          String[] sd = hh.split("~");
    Smiley sm = Server.get().getSmiley(sd[0]);
   
    
    Server.get().query("delete from sm_favs where id='"+sd[1]+"'");
    Server.get().removeFav(sd[1]);
    // client.send(ModuleCreator.SB_FAVORITE(client, sm.getSyntax(),false));
   // ModuleCreator.UPDATE_SB(client);
  if (sync[0].equals("!SBD")) {
    //          client.send(ModuleCreator.SB_DETAILS(client,sm.getSyntax()));
     }
      }}
     
 
  
   return;  
 }
 if (sync[0].equals("+SB") || sync[0].equals("+SBD")) {
     
     
     String sms = "";
   String syncs = sync[1];
  for(String sy : syncs.split(" ")) {
      int gefunden = 0;
      for(String id : client.getSmileys().split("%%")) {
    if (!id.isEmpty()) {  
     Usersmiley s = Server.get().getUsersmiley(id);
     Smiley ss = Server.get().getSmiley(String.valueOf(s.getSMID()));
     
     int nochnetdrauf = 0;
     
     for(Favs f : Server.get().getFavs()) {
         if (f.getUser().equals(client.getName()) && f.getSMID().equals(String.valueOf(ss.getID()))) {
             nochnetdrauf = 1;
         }
     }
     
     if (nochnetdrauf == 0) {     
     
     if (ss.getSyntax().equals(sy) && !sms.contains("|"+ss.getID()+"|")) {     
        gefunden = 1;
        sms += "|"+ss.getID()+"|";
  }
     } else {
         gefunden = 1;
     }
    
    
    
    } }
  
   if (gefunden == 0) {
        client.sendButlerMessage(channel.getName(),"Du hast diesen Smiley nicht: "+sy);
    }
    
  }
  for(String hh : sms.split("\\|")) {
      if (!hh.isEmpty()) {
    Smiley sm = Server.get().getSmiley(hh);
    
    
    
      Server.get().query("insert into `sm_favs` set smid='"+sm.getID()+"',user='"+client.getName()+"',syntax='"+sm.getSyntax()+"'");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_favs` WHERE smid='"+sm.getID()+"' and `user` = '"+client.getName()+"' and syntax='"+sm.getSyntax()+"' order by id desc limit 1");
            
            if (rs.next()) {
               Favs put = new Favs(rs);
                synchronized (Server.fav) {
             Server.fav.put(rs.getString("id"), put);
                }
          
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
       
  //client.send(ModuleCreator.SB_FAVORITE(client, sm.getSyntax(),true));
    //ModuleCreator.UPDATE_SB(client); 
  
    if (sync[0].equals("+SBD")) {
     //         client.send(ModuleCreator.SB_DETAILS(client,sm.getSyntax()));
     }
  
  }}
  
  
  
  
  return;   
 } 
 if (sync[0].equals("lend")) {
     
     String syncs = sync[1];
  String sms = "";
     
  int found = 0;  
  
  for(String sy : syncs.split(" ")) {
      for(String id : client.getSmileys().split("%%")) {
    if (!id.isEmpty()) {  
     Usersmiley s = Server.get().getUsersmiley(id);
     Smiley ss = Server.get().getSmiley(String.valueOf(s.getSMID()));
      if (s.getVerliehen().isEmpty() && s.getBan1().isEmpty() && s.getBan2().isEmpty() && s.getBan3().isEmpty() && ss.getTauschbar()) {
         
     if (ss.getSyntax().equals(sy) && !sms.contains("|"+id+"|")) {     
        sms += "|"+id+"|"; 
        found = 1;
  }}}
  }}
  
      
        if (found == 0) {
               
            Popup popup = new Popup("Problem", "Problem", "#Keiner dieser Smileys ist tauschbar/verleihbar.#Achte darauf, dass sie _tauschbar_ sind.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
        }
        String smile = "";
        for(String ss : sms.split("\\|")) {
    if (!ss.isEmpty()) {
        if (!smile.isEmpty()) {
            smile += ",";
        }
        smile += ss;
    }
        }
    client.send(tools.PacketCreator.createLeihenWindow(client,smile.split(",")));
    
     
     return;
 }
 if (sync[0].equals("trade")) {
     
  
       
     String syncs = sync[1];
  String sms = "";
     
  int found = 0;  
  
  for(String sy : syncs.split(" ")) {
      for(String id : client.getSmileys().split("%%")) {
    if (!id.isEmpty()) {  
     Usersmiley s = Server.get().getUsersmiley(id);
     Smiley ss = Server.get().getSmiley(String.valueOf(s.getSMID()));
      if (s.getVerliehen().isEmpty() && s.getBan1().isEmpty() && s.getBan2().isEmpty() && s.getBan3().isEmpty() && ss.getTauschbar()) {
         
     if (ss.getSyntax().equals(sy) && !sms.contains("|"+id+"|")) {     
        sms += "|"+id+"|"; 
        found = 1;
  }}}
  }}
  
      
        if (found == 0) {
                 Popup popup = new Popup("Problem", "Problem", "#Keiner dieser Smileys ist tauschbar/verleihbar.#Achte darauf, dass sie _tauschbar_ sind.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
           
        }
        String smile = "";
        for(String ss : sms.split("\\|")) {
    if (!ss.isEmpty()) {
        if (!smile.isEmpty()) {
            smile += ",";
        }
        smile += ss;
    }
        }
    client.send(tools.PacketCreator.tauschFenster(client,smile.split(",")));
    
     
     return;
 }
 
 
 if (val[0].equals("lend")) {
     
     
     
     
     if (val[1].equals("ok")) {
         
         String[] d = Server.get().getLeihen(client.getName());
         if (d == null || d[4].isEmpty() || !d[0].equals(client.getName())) {
            client.sendButlerMessage(channel.getName(), "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.");
         return;
         }
         if (d[9].equals("1")) {
             return;
         }
          boolean online2 = true;  Client target2 = Server.get().getClient(d[0]);
   if (target2 == null) {   online2 = false;   target2 = new Client(null);  target2.loadStats(d[0]); }
            
boolean online3 = true;  Client target3 = Server.get().getClient(d[3]);
    if (target3 == null) {   online3 = false;   target3 = new Client(null);  target3.loadStats(d[3]); }
         
    int anzahl = countChars(d[1], ',')+1;
     int anzahl2 = countChars(d[4], ',')+1;
    
     String smimages = "";
     
for(String id : d[1].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}


 String smimages2 = "";
 if (!d[4].equals("NIX")) {
for(String id : d[4].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages2.isEmpty()) {
        smimages2 += ", ";
    }
    smimages2 += sss.getKCode();
}}
    
           String text = "Stimmst du folgendem _°R°Verleih°°_ zu?##";
            text += "_°>"+target2.getName()+"|/serverpp \"|/w \"<°_ bietet folgendes:##";
          
            text += "°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";
           
            if (!d[2].equals("0")) {
text += "#"+d[2]+" Knuddels";
}
text += "##_°>"+target3.getName()+"|/serverpp \"|/w \"<°_ bietet dafür folgendes:##";
if (d[4].equals("NIX")) {
 text += "°>bullet2...b.png<° *nichts*#";   
} else {
text += "°>bullet2...b.png<° _"+anzahl2+" Smileys_ ("+smimages2+")#";
}
if (!d[5].equals("0")) {
text += "#"+d[5]+" Knuddels";
}

text += "##Verleihdauer: "+d[10];
  Popup popup2 = new Popup("Smileys verleihen", "Smileys verleihen", text, 400, 300);


     Panel panel20 = new Panel();
     Button button = new Button("Verleihen ablehnen");
            Button button3 = new Button("Verleihen annehmen");
     Button button2 = new Button("Close");
     panel20.addComponent(button);
panel20.addComponent(button3);
     panel20.addComponent(button2);
     button.enableAction();
    
              button3.enableAction();
     popup2.addPanel(panel20);
 
     popup2.setOpcode(ReceiveOpcode.CODE.getValue(), "ok2~"+client.getName());
     target2.send(popup2.toString());
     target3.send(popup2.toString());
     
      Server.get().removeLeihen(client.getName());
      Server.leihen.put(client.getName(), new String[] { d[0],d[1], d[2], d[3],d[4],d[5],"0","0",(System.currentTimeMillis()/1000)+"","1",d[10]} );
     
         return;
     }
    int f = 0;
    String[] as = null;
      for(String[] a : Server.get().getLeihen()) {
          if (a[3].equals(client.getName())) {
          f = 1;  
          as = a;
          }
          
      }
      if (f == 0) {
            client.sendButlerMessage(channel.getName(), "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.");
            return;
      }
      if (!as[4].isEmpty()) {
          return;
      }
      String smids = "";
      for(String id : as[1].split(",")) {
          Usersmiley sss = Server.get().getUsersmiley(id);
          Smiley aa = Server.get().getSmiley(String.valueOf(sss.getSMID()));
          if (!smids.isEmpty()) {
              smids += ", ";
          }
          smids += aa.getKCode();
      }
     String text = "_°>"+as[0]+"|/serverpp \"|/w \"<° _möchte dir folgendes für _"+as[10]+" leihen_:##°>bullet2...b.png<° "+smids;
if (!as[2].equals("0")) {
text += "#°>bullet2...b.png<° "+as[2]+" Knuddels";
}

   String sms = "";
    
    
         String[] smileys  = client.getSmileys().split("%%");
       for(String d : smileys) {
           if (!d.isEmpty()) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sm = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getVerliehen().isEmpty() && sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sm.getTauschbar()) {
           if (!sms.isEmpty()) {
               sms += ",";
           }
         
           sms += d;
       }}
       }
       
     if (sms.isEmpty()) {
         String t = "code,leihen2~"+as[0]+",Verleihen,0, ,";
     CodeHandler.handle(t.split(","), client);
     } else {
       
    client.send(tools.PacketCreator.createLeihenWindow2(client,text,as[0],sms.split(",")));
     }
     
     return;
 }
 
 
 
 
 
 if (val[0].equals("trade")) {
     

     
     if (val[1].equals("ok")) {
         
         String[] d = Server.get().getTausch(client.getName());
         if (d == null || d[4].isEmpty() || !d[0].equals(client.getName())) {
            client.sendButlerMessage(channel.getName(), "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.");
         return;
         }
         if (d[9].equals("1")) {
             return;
         }
          boolean online2 = true;  Client target2 = Server.get().getClient(d[0]);
   if (target2 == null) {   online2 = false;   target2 = new Client(null);  target2.loadStats(d[0]); }
            
boolean online3 = true;  Client target3 = Server.get().getClient(d[3]);
    if (target3 == null) {   online3 = false;   target3 = new Client(null);  target3.loadStats(d[3]); }
         
    int anzahl = countChars(d[1], ',')+1;
     int anzahl2 = countChars(d[4], ',')+1;
    
     String smimages = "";
for(String id : d[1].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages.isEmpty()) {
        smimages += ", ";
    }
    smimages += sss.getKCode();
}


 String smimages2 = "";
for(String id : d[4].split(",")) {
    Usersmiley bla = Server.get().getUsersmiley(id);
    Smiley sss = Server.get().getSmiley(String.valueOf(bla.getSMID()));
    if (!smimages2.isEmpty()) {
        smimages2 += ", ";
    }
    smimages2 += sss.getKCode();
}
    
           String text = "Stimmst du folgendem _°R°Tausch°°_ zu?##";
            text += "_°>"+target2.getName()+"|/serverpp \"|/w \"<°_ bietet folgendes:##";
            text += "°>bullet2...b.png<° _"+anzahl+" Smileys_ ("+smimages+")#";
            if (!d[2].equals("0")) {
text += "#"+d[2]+" Knuddels";
}
text += "##_°>"+target3.getName()+"|/serverpp \"|/w \"<°_ bietet dafür folgendes:##";
text += "°>bullet2...b.png<° _"+anzahl2+" Smileys_ ("+smimages2+")#";
if (!d[5].equals("0")) {
text += "#"+d[5]+" Knuddels";
}


text += "##_Hinweis_: Der Tausch kann nach Abschluss nur mit Zustimmung beider wieder rückgängig gemacht werden!";
   Popup popup2 = new Popup("Tausch-Fenster öffnen", "Tausch-Fenster öffnen", text, 400, 300);


     Panel panel20 = new Panel();
     Button button = new Button("Tausch ablehnen");
            Button button3 = new Button("Tausch annehmen");
     Button button2 = new Button("Close");
     panel20.addComponent(button);
panel20.addComponent(button3);
     panel20.addComponent(button2);
     button.enableAction();
    
              button3.enableAction();
     popup2.addPanel(panel20);
 
     popup2.setOpcode(ReceiveOpcode.CODE.getValue(), "ok~"+client.getName());
     target2.send(popup2.toString());
     target3.send(popup2.toString());
     
      Server.get().removeTauschen(client.getName());
      Server.tauschen.put(client.getName(), new String[] { d[0],d[1], d[2], d[3],d[4],d[5],"0","0",(System.currentTimeMillis()/1000)+"","1"} );
     
         return;
     }
    int f = 0;
    String[] as = null;
      for(String[] a : Server.get().getTauschen()) {
          if (a[3].equals(client.getName())) {
          f = 1;  
          as = a;
          }
          
      }
      if (f == 0) {
            client.sendButlerMessage(channel.getName(), "Du hast dir zu lange Zeit gelassen, um auf diesen Link zu klicken.");
            return;
      }
      if (!as[4].isEmpty()) {
          return;
      }
      String smids = "";
      for(String id : as[1].split(",")) {
          Usersmiley sss = Server.get().getUsersmiley(id);
          Smiley aa = Server.get().getSmiley(String.valueOf(sss.getSMID()));
          if (!smids.isEmpty()) {
              smids += ", ";
          }
          smids += aa.getKCode();
      }
     String text = "_°>"+as[0]+"|/serverpp \"|/w \"<° _bietet dir folgendes zum _°R°Tausch°°_ an:##°>bullet2...b.png<° "+smids;
if (!as[2].equals("0")) {
text += "#°>bullet2...b.png<° "+as[2]+" Knuddels";
}

   String sms = "";
    
    
         String[] smileys  = client.getSmileys().split("%%");
       for(String d : smileys) {
           Usersmiley sd = Server.get().getUsersmiley(d);
           Smiley sm = Server.get().getSmiley(String.valueOf(sd.getSMID()));
          if (sd.getVerliehen().isEmpty() && sd.getBan1().isEmpty() && sd.getBan2().isEmpty() && sd.getBan3().isEmpty() && sm.getTauschbar()) {
           if (!sms.isEmpty()) {
               sms += ",";
           }
         
           sms += d;
       }
       }
    client.send(tools.PacketCreator.tauschFensterakzept(client,text,as[0],sms.split(",")));
     
     
     return;
 }
   if (val[0].equals("?")) { 
       Smiley sm = Server.get().getSmiley(val[1]);
       if (sm == null || !sm.getLink()) {
                      
          Popup popup = new Popup("Smiley nicht gefunden", "Smiley nicht gefunden", "#Smiley nicht gefunden. Überprüfe deine Eingabe.", 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
       }
      
       
        String sec = "";
   if (sm.getSelten() == 10) {
        sec = " °RR°_(Secret-Smiley!)_°r°";
    } else if (sm.getSelten() == 11) {
        sec = " °RR°_(TOP-SECRET-SMILEY!)_°r°";
   }
   
   
    
       
       String text = "°>{table|w1|min200<°_"+sm.getName2()+"_"+sec;
     if (!sm.getSpez()) { 
       if (sm.getTauschbar()) {
           text += " (tauschbar)";
       } else  if (sm.getSelten() == 0) {
           text += " (Übergangssmiley)";
       }}
       text += "°>{tc}<°°>right<°";
       
       text += "_"+sm.getSeltenString()+"_";
       text += "°>{endtable}<°°>left<°#"+sm.getKCode();
       if (!sm.getPrev() && !sm.getSyntax().isEmpty()) {
           text += " = "+sm.getSyntax();
       }
       
       
       text += "###_Beschreibung_:#"+sm.getText();
       text += sm.getFeature();
      
       
       if (Integer.parseInt(sm.getCounter(client.getName(),true)[1]) == 0) {
           text += "##°-°°>finger.b.gif<° _Derzeit °BB°"+sm.getCounter(client.getName(),true)[1]+"°r°x im Chat vorhanden!_";
       }else {
      
               
       text += "##°-°°>finger.b.gif<° _Derzeit °BB°"+sm.getCounter(client.getName(),true)[1]+"°r°x im Chat vorhanden:_##";
       String users = "";
       for(String nicks : sm.getCounter(client.getName(),true)[2].split("\\|")) {
       if (!nicks.isEmpty()) {
           String[] a = nicks.split("~");
           if (!users.isEmpty()) {
               users += ", ";
           }
           users +=  "_°BB°°12°°>_h"+a[0]+"|/w "+a[0]+"<° °K°("+a[1]+"x)_";
       }
       }
       text += users;
       }
                 PopupNewStyle popup = new PopupNewStyle(sm.getName2(), sm.getName2(), text, 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 client.send(popup.toString());
                 return;
       
       
   }
        if (val[0].equals("showserie")) {
            
            
              String[] c = client.getCodes().split("%%");
String kats = "";

for(String s : c) {
    if (!s.isEmpty()) {
    String[] v = s.split("\\|");
    Codes code = Server.get().getCode(v[0]);
    if (code != null) {
    kats +="|"+code.getKategorie()+";"+code.getCodeid()+"|";
    }
    }}
    

            
            
          Kategorie lala = Server.get().getKategorie(val[1]);
        if (lala == null) {
             client.sendButlerMessage(channel.getName(),"Diese Kategorie gibt es nicht.");
             return;
        }
            
            
            String title = lala.getName()+"-Serie - Überblick"; 
            String texts = "Bei Aktivierung bekommst du den Basic-Smiley sofort. Dieser entwickelt sich dann nach "+lala.getDauer()+" Tagen zu einem der anderen Smileys dieser Serie.";
            if (lala.getSofort()) {
                texts = "Die folgenden Smileys sind in dieser Serie enthalten. Bei Aktivierung entwickelt sich der Code sofort in einen dieser Smileys.";
            } 
            
            String text = "°>{table|5|w1|5}<°°>{tr}<>{tc}<°°K°°12>left<°"+texts+" °>{tc}<>{endtable}<°#°>center<12°";
            int aus = 0;  
            text += "°12+0000>{table|w1|100|100|100|w1}<°°>{tr}<>{tc}<°";
             
              for(String v : lala.getSmileys().split(",")) {
                  if (!v.isEmpty()) {
                Smiley sm = Server.get().getSmiley(v);
                
                      aus++;
                      String selten = "";
                    if (sm.getSelten() == 10) {
                        selten = "Secret";
                    }
                    if (sm.getSelten() == 11) {
                        selten = "Top Secret";
                    }
                    String fts = "";
                    if (!sm.getFT1().isEmpty()) {
                        fts += sm.getFT1();
                    }
                    if (!sm.getFT2().isEmpty()) {
                        
                    if (!fts.isEmpty()) {
                        fts += ", ";
                    }
                        fts += sm.getFT2();
                    }
                    if (!sm.getFT3().isEmpty()) {
                     if (!fts.isEmpty()) {
                        fts += ", ";
                    }
                        fts += sm.getFT3();
                    }
                    
                    String kcode = sm.getKCode().substring(0, sm.getKCode().length()-1);
                    
                      // feature null + top nix
                      if (sm.getFT1().isEmpty() && sm.getFT2().isEmpty() && sm.getFT3().isEmpty() && sm.getSelten() != 10 && sm.getSelten() != 11) {
                      text += kcode+">--<>_h|/code ?:"+v+"<°°>--<>transparent1x1...h_54.w_0.gif<°#_°[126,126,126]>--<>_h"+sm.getName().split("°")[0]+"|/code ?:"+v+"<°_°>--<>transparent1x1...h_20.w_0.gif<°°B°";
                      } 
                    //  feature null + top
                       if (sm.getFT1().isEmpty() && sm.getFT2().isEmpty() && sm.getFT3().isEmpty() && sm.getSelten() == 10 || sm.getFT1().isEmpty() && sm.getFT2().isEmpty() && sm.getFT3().isEmpty() && sm.getSelten() == 11) {
                      text += kcode+">--<>_h|/code ?:"+v+"<°°>--<>transparent1x1...h_54.w_0.gif<°#_°[126,126,126]>--<>_h"+sm.getName().split("°")[0]+"|/code ?:"+v+"<°_°>--<>transparent1x1...h_20.w_0.gif<°_°#R>--<>_h"+selten+"|/code ?:"+v+"<°_°>--<>transparent1x1...h_15.w_0.gif<°°B°";
                      } 
                       
                         if (!sm.getFT1().isEmpty() || !sm.getFT2().isEmpty() || !sm.getFT3().isEmpty()) {
                     if (sm.getSelten() != 10 && sm.getSelten() != 11) {
                      
                      text += kcode+">--<>_h|/code ?:"+v+"<°°>--<>transparent1x1...h_54.w_0.gif<°#_°[126,126,126]>--<>_h"+sm.getName().split("°")[0]+"|/code ?:"+v+"<°_°>--<>transparent1x1...h_20.w_0.gif<°°B°#°>--<>_h"+fts+"|/code ?:"+v+"<°°>--<>transparent1x1...h_15.w_0.gif<°";
                     } else {
                      text += kcode+">--<>_h|/code ?:"+v+"<°°>--<>transparent1x1...h_54.w_0.gif<°#_°[126,126,126]>--<>_h"+sm.getName().split("°")[0]+"|/code ?:"+v+"<°_°>--<>transparent1x1...h_20.w_0.gif<°_°#R>--<>_h"+selten+"|/code ?:"+v+"<°_°>--<>transparent1x1...h_15.w_0.gif<°°B°#°>--<>_h"+fts+"|/code ?:"+v+"<°°>--<>transparent1x1...h_15.w_0.gif<°";
                     }}
                    
                      if (aus != 3) {
                        text += "°>{tc}<°";
                    } else {
                        aus = 0;
                        text += "°>{endtable}<°°12+0000>{table|w1|100|100|100|w1}<°°>{tr}<>{tc}<°";
                    } 
                      
                      
              }}
         
              text += "°>{endtable}<°";
              
              if (countWords(kats,"|"+lala.getName()+";") >= 1) {
                  
                  String code = "";
                  for(String v : kats.split("\\|")) {
                      if (!v.isEmpty()) {
    if (v.split(";")[0].equals(lala.getName())) {
        Codes codedd = Server.get().getCode(v.split(";")[1]);
        code = codedd.getCodecode();
    }
                      }
}
                  
              text += "##°>left<[208,208,255]>{colorboxstart}<°##°12°°K°°+0010°°+9510°_°>bullet2...b.png<°   "+countWords(kats,"|"+lala.getName()+";")+"x_ registriert#°>right<°°+0000°°+9020°_°>{button} Aktivieren |1000|call|_c/code ask:"+code+"|height|26|images|edit_bn_blue.~edit_bn_blue_hover~edit_bn_blue.mx_1.my_1<°_  _°>{button}Verschenken|2000|call|_c/code send2:"+code+"|height|26|images|edit_bn_blue.~edit_bn_blue_hover~edit_bn_blue.mx_1.my_1<°_    #°+9520°°[208,208,255]>{colorboxend}<°";
              }
              
              if (lala.getAnzeigen()) {
                  if (countWords(kats,"|"+lala.getName()+";") >= 1) {
              
                      
              text += "##°>left<°°>layout/buy_series_line...mx_10.png<°##°12°°K°°+0010°°+9510°_°>bullet2...b.png<°   Weitere Smileys dieser Serie erwerben:_#°>right<°°+0000°°+9020°_°>{button}      Kaufen      ||call|/shop buy:"+lala.getName()+"|height|26|images|edit_bn_blue.~edit_bn_blue_hover~edit_bn_blue.mx_1.my_1<°##";
              
                  } else {
              text += "##°>left<°°>layout/buy_series_line...mx_10.png<°##°12°°K°°+0010°°+9510°_°>bullet2...b.png<°   Smileys dieser Serie erwerben:_#°>right<°°+0000°°+9020°_°>{button}      Kaufen      ||call|/shop buy:"+lala.getName()+"|height|26|images|edit_bn_blue.~edit_bn_blue_hover~edit_bn_blue.mx_1.my_1<°##";
                  }} else { 
              text += "##°>left<°°>layout/buy_series_line...mx_10.png<°##°12°°K°°+0010°°+9510°_°>bullet2...b.png<°   Diese Serie kannst du nicht online erwerben._#°>right<°°+0000°°+9020°_##";
              }
           
              
              Popup popup = new Popup(title, title, text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setShoppopup(1);
                 client.send(popup.toString());
                 return;

    }

        
        
        if (arg.contains("ask:")) {
            
            
              int found = 0;
        String id = "";
        for(Codes d : Server.get().getCode()) {
        if (d.getCodecode().equals(arg.split(":")[1])) {
            found = 1;
            id = d.getCodeid();
        }
        }
        
       if (found == 0) { return; }
      Codes c = Server.get().getCode(id);

            Popup popup = new Popup("Aktivierung bestätigen", "Aktivierung bestätigen", "Bist du sicher, dass du einen registrierten Code der Serie#°>center<°_°R>{linkhovercolor}<B>"+c.getKategorie()+"|/code showserie:"+c.getKategorie()+"<K°_##°>left<°jetzt _aktivieren_ möchtest?###°>center<°_ °>{button}Aktivieren||call|_c/code "+c.getCodecode()+"|images|edit_bn_blue.~edit_bn_blue_hover~edit_bn_blue.mx_1.my_1<° °>{button}Abbrechen||call|_c/code reg|images|edit_bn_blue.~edit_bn_blue_hover~edit_bn_blue.mx_1.my_1<°", 400, 170);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setGullideckel(1);
                        client.send(popup.toString());
                        return;
        }
        
        
        int found = 0;
        String id = "";
        for(Codes d : Server.get().getCode()) {
        if (d.getCodecode().equals(arg)) {
            found = 1;
            id = d.getCodeid();
        }
        }
            
        
          String effektname = arg.substring(1).toLowerCase();
        
          
          
                String[] lgs = client.getFeature("Gendersmiley");
 Feature ftgs = Server.get().getFeature("Gendersmiley"); 
 if (ftgs == null) {  return; }
          
           if (arg.equals("gendersmiley:on") && lgs[0].equals("2")) {
               
            client.setGendersmiley(1);
           client.sendButlerMessage(channel.getName(),"Das Feature gendersmiley wurde aktiviert.");
            return;
        }
        
         if (arg.equals("gendersmiley:off")  && lgs[0].equals("2")) {
            client.setGendersmiley(0); 
               client.sendButlerMessage(channel.getName(),"Das Feature gendersmiley wurde deaktiviert.");
           
            return;
        }
        
          
          
          if (arg.charAt(0) == '+') {
              if (client.getEffect().equals("wash") || client.getEffect().equals("moskitoBite")) {
                  return;
              }
              
           if (effektname.equals("element") && client.getElementrechner() != 0 && client.getElementrechner() != 5) {
                  
                  if(client.getElementrechner()==1) {
client.sendButlerMessage(channel.getName(), "Der Feuer-Effekt bei öffentlichen Nachrichten wurde aktiviert.");

 }  
 if(client.getElementrechner()==2) {
  client.sendButlerMessage(channel.getName(), "Der Luft-Effekt bei öffentlichen Nachrichten wurde aktiviert.");

 }   
  if(client.getElementrechner()==3) {  
 client.sendButlerMessage(channel.getName(), "Der Wasser-Effekt bei öffentlichen Nachrichten wurde aktiviert.");
 
 }  
   if(client.getElementrechner()==4) { 
 client.sendButlerMessage(channel.getName(), "Der Erde-Effekt bei öffentlichen Nachrichten wurde aktiviert.");

 }  
                  
                  client.setElement(client.getElementrechner());
                  return;
           }
              
              
          if (effektname.equals("effects")) {              
        client.sendButlerMessage(channel.getName(), "Effekte in der Nickliste aktiviert.");
        client.setShowEffects(1);
        for(Client target : channel.getClients()) {
        			   if (!target.getEffect().isEmpty()) {
        				client.send(PacketCreator.effect(target.getName(), target.getEffect()));
                                }}
          return;
          }
          
          
         
           if (Server.existeffekts.contains("|"+effektname.split(":")[0]+"~")) {
            
              String send = "";
              String ft = "";
              for(String v : Server.existeffekts.split("\\|")) {
                  if (!v.isEmpty()) {
                      
                      String[] e = v.split("~");
                      if (e[0].equals(effektname.split(":")[0])) {
                      send = e[1];
                      ft = e[2];
                  }}
              }
                
              String old = client.getEffect();             
            String[] l = client.getFeature(ft);
             if (!l[0].equals("2")) {
                 return;
             }
               int gender = (int)client.getGender();
          
                send = send.replace("GENDER",String.valueOf(gender));
               String noch = "";               

                if (send.equals("butterfly")) {
                    if (!client.getHeart().isEmpty()) {
                    String verschenktan = client.getHeart();
                    Client a = Server.get().getClient(verschenktan);
                    if (a == null) {
                        a = new Client(null);
                        a.loadStats(verschenktan);
                    }
                    if (a.getName() != null && !a.getHeart().isEmpty()) {
                            String verschenktan2 = a.getHeart();
                        if (verschenktan2.equals(client.getName())) {
                        noch = verschenktan+"~butterflyHeart:"+client.getName();
                    send = "butterfly:"+verschenktan;
                        }
                    
                    }   }
                    
              } else if (send.equals("roseNick:COLOR")) {
               int edit = 0;
                  if (effektname.split(":").length >= 2) {
                  if (!effektname.split(":")[1].equals(client.getRosenick())) {
                      String to = "";
                      
                      String nor = "R,G,B,Y,W,O,A,C,E,K,L,P,M,";
                    String rgb = "";
                    
                    String[] a = effektname.split(":")[1].split(",");
                    if (a.length == 3) {
                       
                               if (Server.get().isInteger(a[0]) && Server.get().isInteger(a[1]) && Server.get().isInteger(a[2])) {
                                if (Integer.parseInt(a[0]) >= 0 && Integer.parseInt(a[0]) <= 255 && Integer.parseInt(a[1]) >= 0 && Integer.parseInt(a[1]) <= 255 && Integer.parseInt(a[2]) >= 0 && Integer.parseInt(a[2]) <= 255) {
                                rgb = effektname.split(":")[1].replace(" ","");
                             edit = 1;
                        }}
                        
                    }
                    
                    
                      if (!rgb.isEmpty()) {
                        to = rgb;  
                         client.sendButlerMessage(channel.getName(),"Die Farbe deiner Rose wurde zu _"+to+"_ geändert.");
                       client.setRosenick(to);
                      } else if (nor.toLowerCase().contains(effektname.split(":")[1]+",")) {
                          to = effektname.split(":")[1].toUpperCase();
                      client.sendButlerMessage(channel.getName(),"Die Farbe deiner Rose wurde zu _"+to+"_ geändert.");
                       client.setRosenick(to);
                       edit = 1;
                      } else {
                      client.sendButlerMessage(channel.getName(),"Deine gewünschte Farbe konnte leider nicht gesetzt werden. Die Rose wird nun _rot_ angezeigt.");
                      client.setRosenick("R");
                     edit = 1;
                      }
                      }}
                  
                  if (!client.getRosenick().isEmpty()) {
                   send = send.replace("COLOR",client.getRosenick());
                  client.setRosenick(client.getRosenick());
                  } else {
                  send = send.replace("COLOR","R");
                  client.setRosenick("R");
                  }
                  if (edit == 0) {
                  client.sendButlerMessage(channel.getName(),"Das Feature roseNick wurde aktiviert.");
                  }
              }
               
                
                client.setEffect(send);
                for (Client target : channel.getClients()) {                  
                 target.send(PacketCreator.removeEffect(client.getName(), old));                
                     if (target.getShowEffects() == 1) {
                         if (send.startsWith("butterfly:")) {
                             String[] d = noch.split("~");
                         
                          target.send(PacketCreator.effect(d[0], d[1]));
                         }
                target.send(PacketCreator.effect(client.getName(), send));
                     }
                }
              
              return;
          }
          
          
              return;
          }
          
          if (arg.charAt(0) == '-') {
              
            
                if (effektname.equals("element") && client.getElementrechner() != 0 && client.getElementrechner() != 5) {
                  
                  if(client.getElementrechner()==1) {
client.sendButlerMessage(channel.getName(), "Der Feuer-Effekt bei öffentlichen Nachrichten wurde deaktiviert.");
 }  
 if(client.getElementrechner()==2) {
  client.sendButlerMessage(channel.getName(), "Der Luft-Effekt bei öffentlichen Nachrichten wurde deaktiviert.");

 }   
  if(client.getElementrechner()==3) {  
 client.sendButlerMessage(channel.getName(), "Der Wasser-Effekt bei öffentlichen Nachrichten wurde deaktiviert.");
 
 }  
   if(client.getElementrechner()==4) { 
 client.sendButlerMessage(channel.getName(), "Der Erde-Effekt bei öffentlichen Nachrichten wurde deaktiviert.");

 }  
                  
                  client.setElement(0);
                  return;
           }
                
              if (effektname.equals("effects")) {
                  client.sendButlerMessage(channel.getName(), "Effekte in der Nickliste deaktiviert.");
                  client.setShowEffects(0);
                    for(Client target : channel.getClients()) {
                 if (!target.getEffect().isEmpty()) {
                    client.send(PacketCreator.removeEffect(target.getName(), target.getEffect()));
                }}
                    return;
          }
             
              if (Server.existeffekts.contains("|"+effektname+"~")) {
              String send = "";
              for(String v : Server.existeffekts.split("\\|")) {
                  if (!v.isEmpty()) {
                      String[] e = v.split("~");
                         if (e[0].equals(effektname.split(":")[0])) {
                      send = e[1];
                         }
                  }
              }
              
              if (send.contains("roseNick")) {
                  client.sendButlerMessage(channel.getName(),"Das Feature roseNick wurde deaktiviert.");
             }
              
               if (client.getEffect().split(":")[0].equals(send.split(":")[0])) {
                client.setEffect("");
        	for (Client target : channel.getClients()) {
                target.send(PacketCreator.removeEffect(client.getName(), send.split(":")[0]));
                }
              
                }
              
              return;
                }
              
              return;
          }
          
        
          
          if (arg.equals("activateall")) {
              if (client.getCodes().isEmpty()) {
              client.sendButlerMessage(channel.getName(),"Du hast keine unaktivieren Codes.");
              return;
          }
             for(String c : client.getCodes().split("%%"))  {
                 String code = c.split("\\|")[1];
                 functionMake(client,channel,code);
             }
              client.sendButlerMessage(channel.getName(),"Alle unaktivieren Codes wurden aktiviert.");
              return;
          }
          
          
         if (found == 0) {
        client.sendButlerMessage(channel.getName(),"Code ungültig."); // prüfen ob bereits aktiviert
      return;
       }
         
         
         
      
       Codes code = Server.get().getCode(id);
       
       if (code.getAktiviert()) {
        client.sendButlerMessage(channel.getName(),"Code ungültig."); // prüfen ob bereits aktiviert
      return;   
       }
       
       
       
       if (!code.getUser().equals(client.getName())) {
         client.sendButlerMessage(channel.getName(),"Dieser Code ist nicht auf deinem Nicknamen registriert.");
          return;
       }
     
        Kategorie lala = Server.get().getKategorie(code.getKategorie());
        client.sendButlerMessage(channel.getName(),"Du hast soeben einen _"+lala.getName()+"-Code aktiviert_.");
        
       code.aktivateSmileyCode(client,"");
      
       client.setCodeE(client.getCodeE()+1);
       client.removeCode(code.getCodeid());
       code.setAktiviert(client.getName());
       
        
        
     
        
}
}

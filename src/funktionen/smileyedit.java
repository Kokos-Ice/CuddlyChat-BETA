package funktionen;

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




public class smileyedit {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
        
     if(!client.hasPermission("cmd.smileyedit")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
    
        
        
    
    if (arg.isEmpty()) {
       String text = "Willkommen im Smileyeditor _"+client.getName()+"_!##Hier kannst du Kategorien, Smileys und Features erstellen, bearbeiten und löschen.#°-°°BB°°>finger.b.gif<° _°>Kategorien verwalten|/smileyedit kategorien<°_°-°°BB°°>finger.b.gif<° _°>Smileys verwalten|/smileyedit smileys<°_°-°°BB°°>finger.b.gif<° _°>Feature verwalten|/smileyedit feature<°_°-°°BB°°>finger.b.gif<° _°>Smileys suchen|/smileyedit search<°_";
       
       Popup popup = new Popup("Smileyeditor - Übersicht", "Smileyeditor - Übersicht", text, 400, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLoginfailed(1);
                 client.send(popup.toString());
                 return;
       
     
       } else if (arg.equals("search")) {
           
           
           
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
         popup.setOpcode(ReceiveOpcode.SMEDIT.getValue(), "search~acp");
         client.send(popup.toString());
         
       } else if (arg.split(":")[0].equals("showkat")) {
           
           Kategorie ss = Server.get().getKategorie(arg.split(":")[1]);
           if (ss != null) {
           String text = "";
                  
          for(String id : ss.getSmileys().split(",")) {
              if (!id.isEmpty()) {
              Smiley sm = Server.get().getSmiley(id);
           text += "°>finger.b.gif<° _°BB>"+sm.getName2().replace("°","\\°")+"|/smileyedit smileys:"+sm.getID()+"<°_#";  
          }}
           
      Popup popup = new Popup("Smileyeditor - Smileys der Kategorie "+ss.getName(), "Smileyeditor - Smileys der Kategorie "+ss.getName(), text, 500, 300);       
                 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setShoppopup(1);
                 client.send(popup.toString());
                 return;
       
       
        
           }
           
           
           
       } else if (arg.equals("smileys") || arg.split(":")[0].equals("smileys")) {
          if (arg.equals("smileys")) {
           String text = "°>finger.b.gif<° _°BB>Neuen Smiley erstellen|/smileyedit smileys:create<°_#°-°#";
          
         for(String value : Server.get().getSortSmileys().split("\\|")) {
             if (!value.isEmpty()) {
     
                 Smiley sm = Server.get().getSmiley2(value);
               if (sm != null) {
              text += "°>finger.b.gif<° _°BB>"+sm.getName2().replace("°","\\°")+"|/smileyedit smileys:"+sm.getID()+"<°_#";  
          }}}
           
      Popup popup = new Popup("Smileyeditor - Smileys", "Smileyeditor - Smileys", text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setShoppopup(1);
                 client.send(popup.toString());
                 return;
       
       
          } else {
              // kat edit oder neu
              
       String title = "Smiley erstellen";
       String id = "";
       String edit = " ";
       String name = "";
       String syntax = "";
       String kcode = "";
       String splits = "";
       String kat = "";
       boolean linkable = true;
       boolean prev = false;
       String text = "";
      boolean tausch = true;
      String selten = "";
      String ft1 = "";
      int ft1ban = 0;
      String ft2 = "";
      int ft2ban = 0;
      String ft3 = "";
      int ft3ban = 0;
      int knuddels = 0;
      String tags = "";
      boolean spez = false;
       
              if (!arg.split(":")[1].equals("create")) {
                 Smiley sm = Server.get().getSmiley(arg.split(":")[1]);
                 if (sm == null) {
                     return;
                 }
                 edit = String.valueOf(sm.getID());
                 title = "Smiley "+sm.getName()+" (#"+sm.getID()+") bearbeiten";
                 ft1 = sm.getFT1();
                 ft2 = sm.getFT2();
                 ft3 = sm.getFT3();
                 ft1ban = sm.getBanTime1();
             ft2ban = sm.getBanTime2();                 
             ft3ban = sm.getBanTime3();
                 knuddels = sm.getKnuddel();
                 spez = sm.getSpez();
                 name = sm.getName();
                 syntax = sm.getSyntax();
                 kcode = sm.getKCode();
                 splits = sm.getSplit();
                 kat = sm.getKategorie();
                 linkable = sm.getLink();
                 prev = sm.getPrev();
                 text = sm.getText();
                 tausch = sm.getTauschbar();
                 tags = sm.getTags();
                 selten = sm.getSeltenString();
                         
                 
                 
              }
               Popup popup = new Popup(title, title, "", 400, 0);

               
               
        Panel panel6 = new Panel();
        panel6.addComponent(new Label("Name:   "));
        TextField lol = new TextField(50);
        lol.setText(name);
        panel6.addComponent(lol);        
        popup.addPanel(panel6);
        
         Panel panel6s = new Panel();
        panel6s.addComponent(new Label("Syntax:   "));
        TextField lsol = new TextField(50);
        lsol.setText(syntax);
        panel6s.addComponent(lsol);        
        popup.addPanel(panel6s);
        
          Panel panel6iu = new Panel();
        panel6iu.addComponent(new Label("KCode:   "));
        TextField lolsu = new TextField(50);
        lolsu.setText(kcode);
        panel6iu.addComponent(lolsu);        
        popup.addPanel(panel6iu);
        
         Panel panel6i = new Panel();
        panel6i.addComponent(new Label("Text:   "));
        TextField lols = new TextField(50);
        lols.setText(text);
        panel6i.addComponent(lols);        
        popup.addPanel(panel6i);
        
        
        
        
         Panel panel6iz = new Panel();
        panel6iz.addComponent(new Label("Splitsmiley:   "));
        TextField lolzs = new TextField(50);
        lolzs.setText(splits);
        panel6iz.addComponent(lolzs);        
        popup.addPanel(panel6iz);
        
          Panel panel6izz = new Panel();
        panel6izz.addComponent(new Label("Knuddels:   "));
        TextField lolzzs = new TextField(50);
        lolzzs.setText(String.valueOf(knuddels));
        panel6izz.addComponent(lolzzs);        
        popup.addPanel(panel6izz);
        
        
          Panel panel5 = new Panel();
      panel5.addComponent(new Label("Kategorie:      "));
        List<String> where = new ArrayList<String>();
where.add(""); 
for(Kategorie s : Server.get().getKategorien()) {
       where.add(s.getName());
      }
String[] simpleArray = new String[ where.size() ];
where.toArray( simpleArray );
       Choice lol5 = new Choice(simpleArray,"");
      lol5.setSelected((!kat.isEmpty()) ? kat : "");
      panel5.addComponent(lol5);
      
      
       panel5.addComponent(new Label("Seltenheit:      "));
           Choice lolk5 = new Choice(new String[] { "","extreeem häufig","extrem häufig","seeehr häufig","sehr häufig","häufig","manchmal","selten","sehr selten","seeehr selten","extrem selten","extreeem selten" },"");
       lolk5.setSelected(selten);
      panel5.addComponent(lolk5);
      
       popup.addPanel(panel5);
       
      
       
       
       Panel panel5ia = new Panel();
      panel5ia.addComponent(new Label("Tauschbar:      "));
           Choice lolk5a = new Choice(new String[] { "Ja","Nein" },"");
       lolk5a.setSelected((tausch) ? "Ja" : "Nein");
      panel5ia.addComponent(lolk5a);
      
      
      panel5ia.addComponent(new Label("Vorschau:      "));
           Choice lolk5b = new Choice(new String[] { "Ja","Nein" },"");
       lolk5b.setSelected((prev) ? "Ja" : "Nein");
      panel5ia.addComponent(lolk5b);
      
       popup.addPanel(panel5ia);
       
       
       
        Panel panel5ic = new Panel();
      panel5ic.addComponent(new Label("Verlinkt:      "));
           Choice lolk5c = new Choice(new String[] { "Ja","Nein" },"");
       lolk5c.setSelected((linkable) ? "Ja" : "Nein");
      panel5ic.addComponent(lolk5c);
      
      
       panel5ic.addComponent(new Label("Sondersmiley:      "));
           Choice lolk5d = new Choice(new String[] { "Ja","Nein" },"");
       lolk5d.setSelected((spez) ? "Ja" : "Nein");
      panel5ic.addComponent(lolk5d);
      
       popup.addPanel(panel5ic);
       
        List<String> where2 = new ArrayList<String>();
where2.add(""); 
       for(String v : Server.get().getSortFeatures().split("\\|"))  {
          if (!v.isEmpty()) { 
             where2.add(v);
       }}
       

String[] simpleArray2 = new String[ where2.size() ];
where2.toArray( simpleArray2 );


       
          Panel panel5e = new Panel();
      panel5e.addComponent(new Label("Feature 1:      "));      
       Choice lol5e = new Choice(simpleArray2,"");
      lol5e.setSelected(ft1);
      panel5e.addComponent(lol5e);
      
       panel5e.addComponent(new Label("Sperrzeit 1:   "));
        TextField lolzsa = new TextField(10);
        lolzsa.setText(String.valueOf(ft1ban));
        panel5e.addComponent(lolzsa);  
        
       popup.addPanel(panel5e);
       
       
        
          Panel panel5f = new Panel();
      panel5f.addComponent(new Label("Feature 2:      "));      
       Choice lol5f = new Choice(simpleArray2,"");
      lol5f.setSelected(ft2);
      panel5f.addComponent(lol5f);
      
      
      panel5f.addComponent(new Label("Sperrzeit 2:   "));
        TextField lolzsb = new TextField(10);
        lolzsb.setText(String.valueOf(ft2ban));
        panel5f.addComponent(lolzsb); 
        
       popup.addPanel(panel5f);
       
        
        
       
          Panel panel5g = new Panel();
      panel5g.addComponent(new Label("Feature 3:      "));      
       Choice lol5g = new Choice(simpleArray2,"");
      lol5g.setSelected(ft3);
      panel5g.addComponent(lol5g);
      
      
      panel5g.addComponent(new Label("Sperrzeit 3:   "));
        TextField lolzsc = new TextField(10);
        lolzsc.setText(String.valueOf(ft3ban));
        panel5g.addComponent(lolzsc);        
      
       popup.addPanel(panel5g);
       
       
       
         Panel panel6izzz = new Panel();
        panel6izzz.addComponent(new Label("Search-Tags:   "));
        TextField lolzzzs = new TextField(50);
        lolzzzs.setText(String.valueOf(tags));
        panel6izzz.addComponent(lolzzzs);        
        popup.addPanel(panel6izzz);
          
        
        
         Panel panel20 = new Panel();
        Button button = new Button("Speichern");
        if (!edit.trim().isEmpty()) {
         Button button2 = new Button("Löschen");
         panel20.addComponent(button2);
           button2.enableAction();
        }
         Button button2 = new Button("Abbrechen");
         panel20.addComponent(button);
        panel20.addComponent(button2);
         button.enableAction();
        popup.addPanel(panel20);
         popup.setOpcode(ReceiveOpcode.SMEDIT.getValue(), "smiley~" + edit);
         client.send(popup.toString());
         
          }
       } else if (arg.equals("feature") || arg.split(":")[0].equals("feature")) {
          if (arg.equals("feature")) {
           String text = "°>finger.b.gif<° _°BB>Neues Feature erstellen|/smileyedit feature:create<°_#°-°#";
          
              for (String value : Server.get().getSortFeatures().split("\\|")) {
         if (!value.isEmpty()) {
             Feature ft = Server.get().getFeature(value);
                  text += "°>finger.b.gif<° _°BB>"+ft.getName()+"|/smileyedit feature:"+ft.getName()+"<°_#";  
         }}
              Popup popup = new Popup("Smileyeditor - Features", "Smileyeditor - Features", text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setShoppopup(1);
                 client.send(popup.toString());
                 return;
    
          } else {
              // kat edit oder neu
              
       String title = "Feature erstellen";
       String id = "";
       String edit = " ";
       String name = "";
       String text = "";
       String makro = "";
       int show = 1;
              if (!arg.split(":")[1].equals("create")) {
                 Feature ft = Server.get().getFeature(arg.split(":")[1]);
                 if (ft == null) {
                     return;
                 }
                 edit = ft.getName();
                 title = "Feature "+ft.getName()+" bearbeiten";
                 name = ft.getName();
                 text = ft.getText();
                 makro = ft.getMakro();
                 show = ft.getShow();
                
                  
              }
               Popup popup = new Popup(title, title, "", 400, 0);

        Panel panel6 = new Panel();
        panel6.addComponent(new Label("Name:   "));
        TextField lol = new TextField(50);
        lol.setText(name);
        panel6.addComponent(lol);        
        popup.addPanel(panel6);
        
         Panel panel6i = new Panel();
        panel6i.addComponent(new Label("Text:   "));
        TextField lols = new TextField(50);
        lols.setText(text);
        panel6i.addComponent(lols);        
        popup.addPanel(panel6i);
        
         Panel panel6io = new Panel();
        panel6io.addComponent(new Label("Makro:   "));
        TextField lolsz = new TextField(50);
        lolsz.setText(makro);
        panel6io.addComponent(lolsz);        
        popup.addPanel(panel6io);

        Panel panel7 = new Panel();
        panel7.addComponent(new Label("Anzeigen:                                                                        "));
        Choice lol2 = new Choice(new String[] { "Ja","Nein" }, "");
        lol2.setSelected((show == 1) ? "Ja" : "Nein");
        panel7.addComponent(lol2);
        popup.addPanel(panel7);
        
         Panel panel20 = new Panel();
        Button button = new Button("Speichern");
        if (!edit.trim().isEmpty()) {
         Button button2 = new Button("Löschen");
         panel20.addComponent(button2);
           button2.enableAction();
        }
         Button button2 = new Button("Abbrechen");
         panel20.addComponent(button);
        panel20.addComponent(button2);
         button.enableAction();
        popup.addPanel(panel20);
         popup.setOpcode(ReceiveOpcode.SMEDIT.getValue(), "feature~" + edit);
         client.send(popup.toString());
         
          }
       } else if (arg.equals("kategorien") || arg.split(":")[0].equals("kategorien")) {
          if (arg.equals("kategorien")) {
           String text = "°>finger.b.gif<° _°BB>Neue Kategorie erstellen|/smileyedit kategorien:create<°_#°-°#";
          
        for (String value : Server.get().getSortKategorie().split("\\|")) {
          if (!value.isEmpty()) {
              Kategorie kat = Server.get().getKategorie(value);
            text += "°>{table|250|150<°°>finger.b.gif<° _°BB>"+kat.getName()+"|/smileyedit kategorien:"+kat.getName()+"<°_°>{tc}<°°>py_b.gif<° _°BB>Smileys zeigen|/smileyedit showkat:"+kat.getName()+"<°_°>{endtable}<°#";  
          }}
        Popup popup = new Popup("Smileyeditor - Kategorien", "Smileyeditor - Kategorien", text, 500, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setShoppopup(1);
                 client.send(popup.toString());
                 return;
           
          } else {
              // kat edit oder neu
              
       String title = "Kategorie erstellen";
       String id = "";
       String edit = " ";
       String name = "";
       String preis = "200";
       String dauer = "7";
       boolean sofort = false;
       boolean anzeigen = true;
              if (!arg.split(":")[1].equals("create")) {
                  Kategorie kats = Server.get().getKategorie(arg.split(":")[1]);
                  if (kats == null) {
                      return;
                  }
                 edit = kats.getName();
                 title = "Kategorie "+kats.getName()+" bearbeiten";
                 name = kats.getName();
                 preis = String.valueOf(kats.getPreis());
                 dauer = String.valueOf(kats.getDauer());
                  sofort = kats.getSofort();
                  anzeigen = kats.getAnzeigen();
                  
              }
               Popup popup = new Popup(title, title, "", 400, 0);

        Panel panel6 = new Panel();
        panel6.addComponent(new Label("Name:   "));
        TextField lol = new TextField(50);
        lol.setText(name);
        panel6.addComponent(lol);        
        popup.addPanel(panel6);
        
         Panel panel6i = new Panel();
        panel6i.addComponent(new Label("Preis:   "));
        TextField lols = new TextField(50);
        lols.setText(preis);
        panel6i.addComponent(lols);        
        popup.addPanel(panel6i);

       Panel panel7 = new Panel();
        panel7.addComponent(new Label("Anzeigen:                                                                        "));
        Choice lol2 = new Choice(new String[] { "Ja","Nein" }, "");
        lol2.setSelected((anzeigen) ? "Ja" : "Nein");
        panel7.addComponent(lol2);
        popup.addPanel(panel7);
  
        Panel panel9 = new Panel();
        panel9.addComponent(new Label("Sofortentwickler:                                                                        "));
        Choice lol9 = new Choice(new String[] { "Nein","Ja" }, "");
        lol9.setSelected((sofort) ? "Ja" : "Nein");
        panel9.addComponent(lol9);
        popup.addPanel(panel9);
        
          Panel panel6iz = new Panel();
        panel6iz.addComponent(new Label("Entwicklungszeit:   "));
        TextField lolss = new TextField(50);
        lolss.setText(dauer);
        panel6iz.addComponent(lolss);        
        popup.addPanel(panel6iz);
        
         Panel panel20 = new Panel();
        Button button = new Button("Speichern");
        if (!edit.trim().isEmpty()) {
         Button button2 = new Button("Löschen");
         panel20.addComponent(button2);
           button2.enableAction();
        }
         Button button2 = new Button("Abbrechen");
         panel20.addComponent(button);
        panel20.addComponent(button2);
         button.enableAction();
        popup.addPanel(panel20);
         popup.setOpcode(ReceiveOpcode.SMEDIT.getValue(), "kategorie~" + edit);
         client.send(popup.toString());
         
          }
       }
    
}
}

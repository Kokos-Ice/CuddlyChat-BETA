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




public class shop {
    
   
    public static void functionMake(Client client,Channel channel, String arg) {
             if(!client.hasPermission("cmd.shop")) { client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");  return; }
   
  
         if (arg.isEmpty()) {
        String t = "";
       for (String value : Server.get().getSortKategorie().split("\\|")) {
          if (!value.isEmpty()) {
              Kategorie kats = Server.get().getKategorie(value);
              if (kats != null) {
        String more = kats.getDauer()+" Tage";
        if (kats.getSofort()) {
            more = "Sofort";
        }
        if (kats.getAnzeigen()) {
            String[] im = kats.getSmileysWithoutBasic().split(",");
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
    String ftnames = kats.getFeature();
    
                
     //  t += "##°[0,0,196]>left<>{+textborder}<15+0020°_°>_h"+kats.getName()+"|/code showserie:"+kats.getName()+"<r°_°>{textborder}<° ("+kats.getPreis()+" Knuddels - "+more+")#°+0000°°>right<12+9025°_°>{button}Kaufen||call|/shop buy:"+kats.getName()+"|height|34|width|106|ty|-1|noborder|true|cursor|12|images|button-blue~hoverbutton-blue~button-blue.mx_1.my_1<°_ #°>center<+0000°°>{table|30|w1|w1|w1|30}<°°>{tr}<°°>transparent1x1...h_60.w_0.gif<°°>{tc}<°"+sm1+"°>transparent1x1...h_60.w_0.gif<°°>{tc}<°"+sm2+"°>transparent1x1...h_60.w_0.gif<°°>{tc}<°"+sm3+"°>transparent1x1...h_60.w_0.gif<°°>{tc}<°°>{endtable}<°###°>left<>{-textborder}<K12+0020°";
     //  Text im Shop (ob Sofortentwickler oder X TAGE) entfernt.
    t += "##°[0,0,196]>left<>{+textborder}<15+0020°_°>_h"+kats.getName()+"|/code showserie:"+kats.getName()+"<r°_°>{textborder}<° ("+kats.getPreis()+" Knuddels)#°+0000°°>right<12+9025°_°>{button}Kaufen||call|/shop buy:"+kats.getName()+"|height|34|width|106|ty|-1|noborder|true|cursor|12|images|button-blue~hoverbutton-blue~button-blue.mx_1.my_1<°_ #°>center<+0000°°>{table|30|w1|w1|w1|30}<°°>{tr}<°°>transparent1x1...h_60.w_0.gif<°°>{tc}<°"+sm1+"°>transparent1x1...h_60.w_0.gif<°°>{tc}<°"+sm2+"°>transparent1x1...h_60.w_0.gif<°°>{tc}<°"+sm3+"°>transparent1x1...h_60.w_0.gif<°°>{tc}<°°>{endtable}<°###°>left<>{-textborder}<K12+0020°";
      
       if (!ftnames.isEmpty()) {
       t += "_Features:_°B° °>_h"+ftnames+"|/code showserie:"+kats.getName()+"<°";
       }
       t += "#°>{imageboxend}<°#°+9510°°>{imageboxstart}hr-greyscale_w128...repeat.loadimages_128.my_10.mx_15.png<°°>{imageboxend}<°#";
        }}}}
 
       Popup popup = new Popup("Shop", "Shop", t, 400, 250);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setShoppopup(1);
                        client.send(popup.toString());
                        return;
        }
        String[] val = arg.split(":");
        if (val[0].equals("buy")) {
              Kategorie lala = Server.get().getKategorie(val[1]);
        if (lala == null) {
             client.sendButlerMessage(channel.getName(),"Diese Kategorie gibt es nicht.");
             return;
        }
        if (!lala.getAnzeigen()) {
            client.sendButlerMessage(channel.getName(),"Diese Kategorie ist derzeit nicht kaufbar.");
             return;
        }
         if (lala.getPreis() > client.getKnuddels()) {
            client.sendButlerMessage(channel.getName(),"Dir fehlen die benötigen Knuddel, um diese Smileyserie kaufen zu können.");
             return; 
         }      
        
         String[] aus = lala.createSmileyCode(client);
          
         client.setKnuddels((int)client.getKnuddels()-lala.getPreis());
       //  client.sendButlerMessage(channel.getName(),"Du hast soeben einen Code der _°BB>"+lala.getName()+"-Serie|/code showserie:"+lala.getName()+"|\"<r°_ erworben.##_Code_: "+aus[1]+"#_ID_: "+aus[0]+"##Dieser Code wurde automatisch _°BB>registriert|/code reg<r°_.");
         String betreff = "Einkauf";
         String text = "Du hast soeben einen Code der _°BB>"+lala.getName()+"-Serie|/code showserie:"+lala.getName()+"|\"<r°_ erworben.##_Code_: "+aus[1]+"#_ID_: "+aus[0]+"##Dieser Code wurde automatisch _°BB>registriert|/code reg<r°_.";
         Server.get().newMessage(Server.get().getButler().getName(), client.getName(), betreff, text, System.currentTimeMillis()/1000);
          
         return;
        } 
          client.sendButlerMessage(channel.getName(),"???");
        
    }}
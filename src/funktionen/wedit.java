
package funktionen;

import starlight.Channel;
import starlight.Client;
import starlight.ReceiveOpcode;
import starlight.Server;
import tools.popup.Button;
import tools.popup.Checkbox;
import tools.popup.Choice;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.TextField;


public class wedit {
    
      public static String getChoiceValueYesNo(int i) {
          if (i == 1) {
              return "Ja";
          }
          
          return "Nein";
      }
      
       public static String getChoiceValueVisit(int i) {
          if (i == 1) {
              return "Nein";
          }
          
          return "Ja";
      }
      
        public static String getChoiceValueGender(int i) {
          if (i == 1) {
              return "männlich";
          }
           if (i == 2) {
              return "weiblich";
         
          }
           return "Keine Angabe";  
          
        }
        
        
        
       /*  public static String getChoiceValueRank(int i) {
          if (i == 1) {
              return "Family";
          }
           if (i == 2) {
              return "Stammi";
          }
           if (i == 3) {
              return "Ehrenz";
          }
           if (i == 5) {
              return "inof. Admin";
          }
           if (i == 6) {
              return "Admin";
               }
           if (i == 7) {
              return "Admin (Status 7)";
               }
           if (i == 8) {
              return "inof. Sysadmin";
               }
           if (i == 10) {
              return "Sysadmin (Status 10)";
               }
           if (i == 11) {
              return "Sysadmin (Status 11)";
               }
           if (i == 12) {
              return "Sysadmin (Status 12)";
         
           }
           return "Mitglied";  
          
        }
         */
 
      
       public static String getChoiceValueEmailYesNo(int i) {
          if (i == 2) {
              return "Ja";
          }
          
          return "Nein";
      }
      
       public static String getChoiceValueWStyle(int i) {
          if (i == 1) {
              return "w2 BETA";
          }
          
          return "Classic";
      }
       
      public static void make(String arg, Client client, Channel channel)   {
          
          
             if(!client.hasPermission("cmd.wedit")) {
                   client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        	   return;
               }
             
               if(!client.hasPermission("cmd.wedit.nick")) {
                   arg = "";
             }
                   
                   if (arg.isEmpty()) {
                       arg = client.getName();
                   }
                   Client target = Server.get().getClient(arg);
                   if (target == null) {
                       target = new Client(null);
                       target.loadStats(arg);
                   }
                   if (target.getName() == null) {
                       client.sendButlerMessage(channel.getName(),"Der Nickname "+arg+" existiert nicht.");
                       return;
                   }
                   
                   
                      Popup popup = new Popup("Profil von "+target.getName()+" bearbeiten","Profil von "+target.getName()+ " bearbeiten","", 474, 0);
       
   
     String useable = "";
     
     
      if(client.hasPermission("wedit.age")) {
     Panel panel2 = new Panel(); // neue zeile !
     panel2.addComponent(new Label("Alter:")); // text
     Choice lol2 = new Choice(new String[] { "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65" },"");
     lol2.setSelected(String.valueOf(target.getAge()));
     panel2.addComponent(lol2); // fügt das zur zeile hinzu
     popup.addPanel(panel2); // ende der zeile!    
     useable += "age,";
    
      }
      
      
        if(client.hasPermission("wedit.gender")) {
     Panel panel2 = new Panel(); // neue zeile !
     panel2.addComponent(new Label("Geschlecht:")); // text
     Choice lol2 = new Choice(new String[] { "Keine Angabe","männlich","weiblich" },"");
     lol2.setSelected(getChoiceValueGender(target.getGender()));
     panel2.addComponent(lol2); // fügt das zur zeile hinzu
     popup.addPanel(panel2); // ende der zeile!    
     useable += "gender,";
        }
        
  /*      if(client.hasPermission("wedit.rank")) {
     Panel panel2 = new Panel(); // neue zeile !
     panel2.addComponent(new Label("Status:")); // text
     Choice lol2 = new Choice(new String[] { "Mitglied","Family","Stammi","Ehrenz","inof. Admin","Admin","Admin (Status 7)","inof. Sysadmin","Sysadmin (Status 10)","Sysadmin (Status 11)","Sysadmin (Status 12)" },"");
     lol2.setSelected(getChoiceValueRank(target.getRank()));
     panel2.addComponent(lol2); // fügt das zur zeile hinzu
     popup.addPanel(panel2); // ende der zeile!    
     useable += "rank,";
        }
*/
     if(client.hasPermission("wedit.onlinetime")) {
     Panel panel6 = new Panel(); // neue zeile !
     panel6.addComponent(new Label("Onlinezeit:               ")); // text
     TextField lol6 = new TextField(54); // größe der input
     lol6.setText(String.valueOf(target.getOnlineTime())); // aktuelle Topic in der input
     panel6.addComponent(lol6); // fügt das zur zeile hinzu
     popup.addPanel(panel6); // ende der zeile! 
     useable += "onlinetime,";
      }   
     
     
         
     
     if(client.hasPermission("wedit.knuddels")) {
     Panel panel7 = new Panel(); // neue zeile !
     panel7.addComponent(new Label("Knuddels:                ")); // text
     TextField lol7 = new TextField(54); // größe der input
     lol7.setText(String.valueOf(target.getKnuddels()).replace(".0","")); // aktuelle Topic in der input
     panel7.addComponent(lol7); // fügt das zur zeile hinzu
     popup.addPanel(panel7); // ende der zeile! 
     useable += "knuddels,";
      }       
      
     if(client.hasPermission("wedit.kisses")) {
     Panel panel8 = new Panel(); // neue zeile !
     panel8.addComponent(new Label("Knutschflecken:       ")); // text
     TextField lol8 = new TextField(54); // größe der input
     lol8.setText(String.valueOf(target.getKisses())); // aktuelle Topic in der input
     panel8.addComponent(lol8); // fügt das zur zeile hinzu
     popup.addPanel(panel8); // ende der zeile! 
     useable += "kisses,";
      }    
     
      if(client.hasPermission("wedit.mentorpoints")) {
     Panel panel8 = new Panel(); // neue zeile !
     panel8.addComponent(new Label("Mentorenpunkte:     ")); // text
     TextField lol8 = new TextField(54); // größe der input
     lol8.setText(String.valueOf(target.getMentorPoints())); // aktuelle Topic in der input
     panel8.addComponent(lol8); // fügt das zur zeile hinzu
     popup.addPanel(panel8); // ende der zeile! 
     useable += "mentorpoints,";
      }    
     
     
     
      if(client.hasPermission("wedit.emailverify")) {
     Panel panel10 = new Panel(); // neue zeile !
     panel10.addComponent(new Label("E-Mail verifiziert? ")); // text
     Checkbox checkbox1 = new Checkbox("     ");
  if (target.getEmailVerify() == 2) {
      checkbox1.check();
  }
     useable += "emailverify,";
     panel10.addComponent(checkbox1); // fügt das zur zeile hinzu
     popup.addPanel(panel10); // ende der zeile!
       }
      
     if(client.hasPermission("wedit.alterverify")) {
     Panel panel11 = new Panel(); // neue zeile !
     panel11.addComponent(new Label("Alter verifiziert? ")); // text
     Choice lol11 = new Choice(new String[] { "Ja","Nein" },"");
     lol11.setSelected(getChoiceValueYesNo(target.getVerify()));
     useable += "alterverify,";
     panel11.addComponent(lol11); // fügt das zur zeile hinzu
     popup.addPanel(panel11); // ende der zeile!
       }
     
     
      if(client.hasPermission("wedit.visit")) {
     Panel panel12 = new Panel(); // neue zeile !
     panel12.addComponent(new Label("Profil ist sichtbar? ")); // text
     Choice lol12 = new Choice(new String[] { "Nein","Ja" },"");
     lol12.setSelected(getChoiceValueVisit(target.getVisit()));
     useable += "visit,";
     panel12.addComponent(lol12); // fügt das zur zeile hinzu
     popup.addPanel(panel12); // ende der zeile!
       }
     
     if(client.hasPermission("wedit.wstyle")) {
     Panel panel13 = new Panel(); // neue zeile !
     panel13.addComponent(new Label("Whois Style: ")); // text
     Choice lol13 = new Choice(new String[] { "Classic","w2 BETA"},"");
     lol13.setSelected(getChoiceValueWStyle(target.getWStyle()));
     useable += "wstyle,";
     panel13.addComponent(lol13); // fügt das zur zeile hinzu
     popup.addPanel(panel13); // ende der zeile!
       }
       
     Panel panel20 = new Panel();
     Button button = new Button("Speichern");   
     Button button2 = new Button("Schließen");
     panel20.addComponent(button);
     panel20.addComponent(button2);
     button.enableAction(); 
     popup.addPanel(panel20);

     popup.setOpcode(ReceiveOpcode.WHOISEDIT.getValue(), "senden~"+target.getName()+"~"+useable);
     client.send(popup.toString());
     
          
          
}
}
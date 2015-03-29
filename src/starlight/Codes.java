
package starlight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;       
import tools.KCodeParser;
import java.util.Random;
import tools.*;
import tools.popup.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;


public class Codes {
	private String kategorie,codeid,user,codecode,aktiviertfrom;
        private Boolean aktiviert;

        
          public static String timeStampToDate(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String zeit = uhrzeits.format(da);

		return zeit;
	}
            public static String timeStampToDates(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy");
		String zeit = uhrzeits.format(da);

		return zeit;
	}
          
	public Codes(ResultSet rs) throws SQLException {
		
		kategorie = rs.getString("kategorie");
                codeid = rs.getString("codeId");
                aktiviert = rs.getBoolean("aktiviert");
                aktiviertfrom = rs.getString("aktiviertFrom");
                codecode = rs.getString("codeCode");
                user = rs.getString("user");
                
                
		

		
	}

	
	public String getKategorie() {
		return kategorie;
	}

        public String getCodeid() {
            return codeid;
        }
        public String getCodecode() {
            return codecode;
        }
        public String getUser() {
            return user;
        }
        public String getUserAktiviert() {
            return aktiviertfrom;
        }
        public Boolean getAktiviert() {
            return aktiviert;
        }
	
        
        public void setKategorie(String a) {
            kategorie = a;
              Server.get().query("update `sm_codes` set `kategorie` = '"+a.replace("\'","\\\'")+"' where `codeId`='"+codeid+"'");
          
        }
       
        public void setAktiviert(String von) {
            
        
            
            Server.get().query("update `sm_codes` set `aktiviert` = '1', `aktiviertFrom`= '"+von+"|"+timeStampToDate(System.currentTimeMillis()/1000)+"' where `codeId`='"+codeid+"'");
            aktiviert = true;
            aktiviertfrom = von+"|"+timeStampToDate(System.currentTimeMillis()/1000);
        }
    
         public static String entwickeln(Map<String, Integer> smileyList) {
                List<Integer> indexList = new ArrayList<Integer>();
                List<Integer> amountList = new ArrayList<Integer>();
               
                int totalAmount = 0;
                int amount = 0;
                for (int i = 0; i < smileyList.keySet().size(); i++) {
                        for (int j = 0; j < smileyList.get(smileyList.keySet().toArray()[i]); j++) {
                                indexList.add(i);
                                amount++;
                                totalAmount++;
                        }
                       
                        amountList.add(amount);
                        amount = 0;
                }
               
                             
                return String.valueOf(smileyList.keySet().toArray()[indexList.get(new Random().nextInt(indexList.size()))]);
        }
         
         
        
        
        public void aktivateSmileyCode(Client nick, String kat) {
            String use = kategorie;
            if (!kat.isEmpty()) {
                use = kat;
            }
            int anzahl = 0;
           Kategorie lala = Server.get().getKategorie(use);
           
        
           if (lala.getSofort()) {
             
               
                 Map<String, Integer> smileys = new HashMap<String, Integer>();
                for(String id : lala.getSmileysWithoutBasic().split(",")) {
                     if (!id.isEmpty()) {
                     Smiley sm = Server.get().getSmiley(id);
                         smileys.put(String.valueOf(sm.getID()), sm.getWahrscheinlichkeit());
                 }}   
                String entwickelt = entwickeln(smileys);
               
               Smiley sms = Server.get().getSmiley(entwickelt);
               String text = "";
                String betreff = "Code aktiviert";
               if (!sms.getSyntax().isEmpty()) {
                String smileytext = sms.getText();
               
                
                  smileytext += sms.getFeature();
                
               anzahl = sms.getKnuddel();
              text = String.format("Hallo %s,##die Würfel sind gefallen, Dein %s-Code hat sich in folgenden Code verwandelt:##Soeben wurde der Smiley %s (_%s_) aktiviert. Ab jetzt wird die Eingabe _%s_ durch diesen Smiley ersetzt.#%s = %s##%s##Bei Eingabe von °BB°_/code ?_°r° erhältst Du eine Übersicht deiner Codes.##Ich wünsche viel Vergnügen mit dem Code,##Deine %s", nick.getName(), lala.getName(), sms.getKCode(), sms.getName2(), sms.getSyntax(),sms.getSyntax(), sms.getKCode(), smileytext, Server.get().getButler().getName());
               } else {
               String smileytext = "";
               
             smileytext += sms.getFeature();
                  
           anzahl = sms.getKnuddel();
        
                  
              text =  String.format("Hallo %s,##die Würfel sind gefallen, dein soeben aktivierter Code hat sich in folgenden Code verwandelt:##Soeben wurde das Feature %s (_%s_) aktiviert.##%s##Bei Eingabe von °BB°_/code ?_°r° erhälst Du eine Übersicht Deiner Codes.#Ich wünsche viel Vergnügen mit dem Code,#deine %s", nick.getName(),sms.getKCode(),sms.getName2(),smileytext,Server.get().getButler().getName());
               
               }
               
                       Server.get().query("insert into `sm_usersmileys` set smid='"+sms.getID()+"',name='"+sms.getName().replace("\'","\\\'")+"',kategorie='"+sms.getKategorie().replace("\'","\\\'")+"',user='"+nick.getName()+"', `basic`='',feature1ban='', feature2ban='', feature3ban='',verliehen=''");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_usersmileys` WHERE smid='"+sms.getID()+"' and `name` = '"+sms.getName().replace("\'","\\\'")+"' and user='"+nick.getName()+"' and `basic`='' order by id desc limit 1");
            
            if (rs.next()) {
               Usersmiley put = new Usersmiley(rs);
             Server.usersmileys.put(rs.getString("id"), put);
             nick.setSmileys(rs.getString("id")+"%%"+nick.getSmileys());
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
            //   nick.sendButlerMessage(nick.getChannel().getName(),text); // eig als /m
         Server.get().newMessage(Server.get().getButler().getName(), nick.getName(), betreff, text, System.currentTimeMillis()/1000);
        
        nick.CodeWithKnuddel(anzahl);
           } else {
               String[] basic =  lala.getBasics().split(",");
               Random zufall = new Random();
                String bas = basic[zufall.nextInt(basic.length)];
               Smiley sm = Server.get().getSmiley(bas);
               Long t = (System.currentTimeMillis()/1000)+(86400*lala.getDauer());
               Server.get().query("insert into `sm_usersmileys` set smid='"+sm.getID()+"',name='"+sm.getName().replace("\'","\\\'")+"',kategorie='"+sm.getKategorie().replace("\'","\\\'")+"',user='"+nick.getName()+"', `basic`='"+timeStampToDates(t)+"|"+(System.currentTimeMillis()/1000)+"',feature1ban='', verliehen='',feature2ban='', feature3ban=''");
           
                 PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_usersmileys` WHERE smid='"+sm.getID()+"' and `name` = '"+sm.getName().replace("\'","\\\'")+"' and user='"+nick.getName()+"' and `basic`='"+timeStampToDates(t)+"|"+(System.currentTimeMillis()/1000)+"' order by id desc limit 1");
            
            if (rs.next()) {
               Usersmiley put = new Usersmiley(rs);
                synchronized (Server.usersmileys) {
             Server.usersmileys.put(rs.getString("id"), put);
                }
             nick.setSmileys(rs.getString("id")+"%%"+nick.getSmileys());
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
       
        String codes = "";
        String[] d = lala.getSmileysWithoutBasic().split(",");
       for(String b : d) {
           if (!b.isEmpty()) {
               Smiley sms = Server.get().getSmiley(b);
         codes += sms.getKCode()+" ";
       }}
       
       String ft = "";
       
        ft += sm.getFeature();
         String betreff = "Code aktiviert";

        String text = String.format("Hallo %s,##meinen herzlichen Glückwunsch, du hast deinen Code erfolgreich aktiviert:##Soeben wurde der Smiley %s (_%s_) aktiviert. Ab jetzt wird die Eingabe _%s_ durch diesen Smiley ersetzt.#%s = %s##%s%s##Bei Eingabe von °BB°_/code ?_°r° erhältst du eine Übersicht deiner Codes.##Am _%s_ wird es spannend, dein Code wird sich in einen der folgenden Codes verwandeln:##%s##Bis dahin wünsche ich dir viel Vergnügen mit dem aktuellen Code,##deine %s", nick.getName(), sm.getKCode(), sm.getName2(), sm.getSyntax(),sm.getSyntax(), sm.getKCode(), sm.getText(), ft,timeStampToDates(t), codes, Server.get().getButler().getName());

        
        Server.get().newMessage(Server.get().getButler().getName(), nick.getName(), betreff, text, System.currentTimeMillis()/1000);
                		
        // HIER FÜR DICH!
        
      nick.sendButlerMessage(nick.getChannel().getName(),text); // eig als /m
 
                		
       nick.CodeWithKnuddel(sm.getKnuddel());
               
          } 
//          ModuleCreator.UPDATE_SB(nick);    
       
               
        }
      
}

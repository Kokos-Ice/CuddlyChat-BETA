
package starlight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;


public class Usersmiley {
	private String verliehen,name,user,basic,ban1,ban2,ban3,kategorie;
        private int smid,id;

	public Usersmiley(ResultSet rs) throws SQLException {
		
		name = rs.getString("name");
                id = rs.getInt("id");
                smid = rs.getInt("smid");
                 kategorie = rs.getString("kategorie");
                user = rs.getString("user");
                basic = rs.getString("basic");
                ban1 = rs.getString("feature1ban");
                ban2 = rs.getString("feature2ban");
                ban3 = rs.getString("feature3ban");
                verliehen = rs.getString("verliehen");
		

		
	}
          public static String timeStampToDates(long timestamp) {
		Date da = new Date(timestamp * 1000);
		SimpleDateFormat uhrzeits = new SimpleDateFormat("dd.MM.yyyy");
		String zeit = uhrzeits.format(da);

		return zeit;
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
           
        public void SmileyEntwicklung(boolean all) {
           if (!basic.isEmpty()) {              
           if (basic.split("\\|")[0].equals(timeStampToDates(System.currentTimeMillis()/1000)) || all) {
            Kategorie lala = Server.get().getKategorie(kategorie);
            Smiley old = Server.get().getSmiley(String.valueOf(smid));     
            Map<String, Integer> smileys = new HashMap<String, Integer>();
                for(String id : lala.getSmileysWithoutBasic().split(",")) {
                     if (!id.isEmpty()) {
                     Smiley sm = Server.get().getSmiley(id);
                         smileys.put(String.valueOf(sm.getID()), sm.getWahrscheinlichkeit());
                 }}   
                String entwickelt = entwickeln(smileys);
                Smiley sms = Server.get().getSmiley(entwickelt);
                
                  String text = "";
                String betreff = "Code Verwandlung...";
              
                String smileytext = sms.getText();
                
                    smileytext += sms.getFeature();
                   
              text = String.format("Hallo %s,##die Würfel sind gefallen, dein %s hat sich in folgenden Code verwandelt:##Soeben wurde der Smiley %s (_%s_) aktiviert. Ab jetzt wird die Eingabe _%s_ durch diesen Smiley ersetzt.#%s = %s##%s##Bei Eingabe von °BB°_/code ?_°r° erhältst Du eine Übersicht deiner Codes.##Ich wünsche viel Vergnügen mit dem Code,##deine %s", user, old.getKCode(), sms.getKCode(), sms.getName2(), sms.getSyntax(),sms.getSyntax(), sms.getKCode(), smileytext, Server.get().getButler().getName());
             Client nick = Server.get().getClient(user);
            boolean online = true;
             if (nick == null) {
                 online = false;
                  nick = new Client(null);
                    nick.loadStats(user);
             }
             Server.get().newMessage(Server.get().getButler().getName(), nick.getName(), betreff, text, System.currentTimeMillis()/1000);
        
                nick.CodeWithKnuddel(sms.getKnuddel());
                if (online) {
              // nick.sendButlerMessage(nick.getChannel().getName(),text); // /m
                }
                setBasic("");
               setName(sms.getName());
               setSMID(String.valueOf(sms.getID()));
//                 ModuleCreator.UPDATE_SB(nick);
           }
           
           }
           
        }
      
	
        public void setFeatureban(String pos,Long bis) {
             if (pos.equals("1")) {
          setBan1(String.valueOf(bis));
            } else  if (pos.equals("2")) {
             setBan2(String.valueOf(bis));
            } else  if (pos.equals("3")) {
            setBan3(String.valueOf(bis));
            }
             
           
            
        }
        public String getKategorie() {
            return kategorie;
        }
        public void setUser(String v) {
            user = v;
            Server.get().query("update sm_usersmileys set `user`='"+v+"' where id = '"+id+"'");
               }
         public void setKategorie(String v) {
            kategorie = v;
            Server.get().query("update sm_usersmileys set `kategorie`='"+v.replace("\'","\\\'")+"' where id = '"+id+"'");
               }
        public void setBasic(String v) {
            basic = v;
            Server.get().query("update sm_usersmileys set `basic`='"+v+"' where id = '"+id+"'");
               }
        public void setName(String v) {
            name = v;
            Server.get().query("update sm_usersmileys set `name`='"+v.replace("\'","\\\'")+"' where id = '"+id+"'");
               }
        public void setSMID(String v) {
            smid = Integer.parseInt(v);
            Server.get().query("update sm_usersmileys set `smid`='"+v+"' where id = '"+id+"'");
               }
        public String getVerliehen() {
            return verliehen;
        }
        public void setVerliehen(String v) {
            verliehen = v;
            Server.get().query("update sm_usersmileys set verliehen='"+v+"' where id = '"+id+"'");
        }
        public void setBan1(String ban) {
            ban1 = ban;
            Server.get().query("update sm_usersmileys set feature1ban='"+ban+"' where id = '"+id+"'");
        }
         public void setBan2(String ban) {
            ban2 = ban;
            Server.get().query("update sm_usersmileys set feature2ban='"+ban+"' where id = '"+id+"'");
        }
          public void setBan3(String ban) {
            ban3 = ban;
            Server.get().query("update sm_usersmileys set feature3ban='"+ban+"' where id = '"+id+"'");
        }
        public int getID() {
            return id;
        }
	public String getName() {
		return name;
	}
public String getUser() {
		return user;
	}
  public int getSMID() {
            return smid;
        }
public String getBasic() {
    return basic;
}
public String getBan1() {
    return ban1;
}
public String getBan2() {
    return ban2;
}
public String getBan3() {
    return ban3;
}
	
}


package starlight;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.Toolbar;
import java.util.Random;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;


public class Kategorie {
    
	private String name,smileys,creates;
        private int price,entwicklungsdauer,id;
        private boolean direct,visable;
        
              public static boolean getBoolean(int v) {
    if (v == 1) {
        return true;
    }
    return false;
}
        
        public static String erzeugePIN()
	{
	    int pin = (int) (Math.random()*1000);
	    if (pin < 1) return "000";
	    if (pin < 10) return "00"+pin;
	    if (pin < 100) return "0"+pin;
		    return ""+pin;
	}
        
         public static String getzufalstring(int size){
        Random rGen=new java.util.Random();
             char[] passArray=new char[size];
        
        for(int i=0;i<size;i++) {
            passArray[i]=(char)(rGen.nextInt(26)+97);
        }
        
        String password=new String(passArray);
        return password;
    }

	public Kategorie(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		name = rs.getString("name");
                price = rs.getInt("price");
                direct = rs.getBoolean("direct");       
                smileys = rs.getString("smileys");
		visable = rs.getBoolean("visable");
                creates = rs.getString("creates");
               
                entwicklungsdauer = rs.getInt("entwicklungsdauer");
		
	}

	
	public String getName() {
		return name;
	}
        public int getID() {
            return id;
        }
        public String getSmileys() {
            
            // sortieren nach seltenheit
            return Server.get().getSortSmileylistbySeltenheit(smileys);
        }
        
         public void setSmileys(String v) {
            smileys = v;
            Server.get().query("update sm_kategorien set `smileys`='"+v+"' where id = '"+id+"'");
               }
  
        public void setAnzeigen(int v) {
            visable = getBoolean(v);
            Server.get().query("update sm_kategorien set `visable`='"+v+"' where id = '"+id+"'");
               }
        
         public void setSofort(int v) {
            direct = getBoolean(v);
            Server.get().query("update sm_kategorien set `direct`='"+v+"' where id = '"+id+"'");
               }
         
        public void setDauer(int v) {
            entwicklungsdauer = v;
            Server.get().query("update sm_kategorien set `entwicklungsdauer`='"+v+"' where id = '"+id+"'");
               }
        
         public void setPreis(int v) {
            price = v;
            Server.get().query("update sm_kategorien set `price`='"+v+"' where id = '"+id+"'");
               }
                

        public int getPreis() {
            return price;
         }
        public int getDauer() {
            return entwicklungsdauer;
        }
	
        public boolean getSofort() {
            return direct;
        }
        public String getCreate() {
            return creates;
        }
        public boolean getAnzeigen() {
            return visable;
        }
       
        public String getFeature() {
            String la = "";
            String aus = "";
                for(String basic : smileys.split(",")) {
              if (!basic.isEmpty()) {
                  Smiley sm = Server.get().getSmiley(basic);
                if (sm != null) {
                  if (!sm.getFT1().isEmpty()) {
                 if (!la.contains("|"+sm.getFT1()+"|")) {
                      la += "|"+sm.getFT1()+"|";
                  }}
                   if (!sm.getFT2().isEmpty()) {
                 if (!la.contains("|"+sm.getFT2()+"|")) {
                      la += "|"+sm.getFT2()+"|";
                  }}
                    if (!sm.getFT3().isEmpty()) {
                 if (!la.contains("|"+sm.getFT3()+"|")) {
                      la += "|"+sm.getFT3()+"|";
                  }}
                  
              }}}
            
                for(String d : la.split("\\|")) {
                    if (!d.isEmpty()) {
                      if(!aus.isEmpty()) {
                          aus += ", ";
                      }  
                      aus += d;
                    }
                }
                
                
            return aus;
        }
        
      public String getBasics() {
          String aus = "";
          for(String basic : smileys.split(",")) {
              if (!basic.isEmpty()) {
                  Smiley sm = Server.get().getSmiley(basic);
                  if (sm.getSelten() == 0) {
                      if (!aus.isEmpty()) {
                          aus += ",";
                      }
                      aus += basic;
                  }
              }
          }
         return aus;
          
      }  
      
    
      
        public String getSmileysWithoutBasic() {
          String aus = "";
          for(String basic : smileys.split(",")) {
              if (!basic.isEmpty()) {
                  Smiley sm = Server.get().getSmiley(basic);
                  if (sm != null) {
                  if (sm.getSelten() != 0) {
                      if (!aus.isEmpty()) {
                          aus += ","; // müssen noch nach seltenheit sortiert werden.
                      }
                      aus += basic;
                  }
              }
          }}
         
         
         return Server.get().getSortSmileylistbySeltenheit(aus);
          
      }  
        
       public String[] createSmileyCode(Client nick) {
           
           String id = erzeugePIN()+" "+erzeugePIN()+" "+erzeugePIN()+" "+getzufalstring(1).toUpperCase();
           String code = getzufalstring(4).toUpperCase()+"-"+getzufalstring(4).toUpperCase()+"-"+getzufalstring(4).toUpperCase()+"-"+getzufalstring(4).toUpperCase();
           Server.get().query("insert into `sm_codes` set codeId='"+id+"',kategorie='"+name.replace("\'","\\\'")+"', aktiviertFrom='',codeCode='"+code+"', user='"+nick.getName()+"'");
           
           PoolConnection pcon = ConnectionPool.getConnection();
        Statement ps = null;
        try {
            Connection con = pcon.connect();
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM `sm_codes` WHERE `codeId` = '"+id+"'");
            
            if (rs.next()) {
               Codes put = new Codes(rs);
             Server.codes.put(rs.getString("codeId"), put);
             nick.setCodes(id+"|"+code+"%%"+nick.getCodes());
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

          
           return new String[] { id,code};
       }
}

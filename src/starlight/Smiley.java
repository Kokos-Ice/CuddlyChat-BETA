
package starlight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.KCodeParser;
import tools.PacketCreator;
import tools.Toolbar;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;


public class Smiley {
	private String name,kategorie,split,kcode,syntax,text,ft1,ft2,ft3,tags;
        private Boolean spez,linkable,prev,tauschbar;
        private int selten,ft1b,ft2b,ft3b,knuddels,id;
	public Smiley(ResultSet rs) throws SQLException {
		 id = rs.getInt("id");
		name = rs.getString("name");
                kategorie = rs.getString("category");
                tags = rs.getString("tags");
                split = rs.getString("splits");
                kcode = rs.getString("kcode");
                syntax = rs.getString("syntax");
               spez = rs.getBoolean("spez");
		linkable = rs.getBoolean("linkable");
                prev = rs.getBoolean("onlyPreview");
                text = rs.getString("desc");
                tauschbar = rs.getBoolean("tauschbar");
                selten = rs.getInt("selten");
                ft1 = rs.getString("feature1");
                ft2 = rs.getString("feature2");
                ft3 = rs.getString("feature3");
                 ft1b = rs.getInt("feature1_bantime");
                ft2b = rs.getInt("feature2_bantime");
                ft3b = rs.getInt("feature3_bantime");
                knuddels = rs.getInt("knuddels");

		
	}
        
           public static boolean getBoolean(int v) {
    if (v == 1) {
        return true;
    }
    return false;
}

        public int getKnuddel() {
            return knuddels;
        }
        public String getTags() {
            return tags;
        }
        public String getSyntax() {
            return syntax;
        }
	public String getFT1() {
            return ft1;
        }
        public String getFT2() {
            return ft2;
        }
        public int getID() {
            return id;
        }
        public boolean getSpez() {
            return spez;
        }
        public String getFT3() {
          return ft3;
        }
        
        public String getReplacement(String dauer) {
            String replacement = "";
            if (dauer.equals("0")) {
replacement = "immer";
} else if (dauer.equals("60")) {
replacement = "einmal pro Stunde";
} else if (dauer.equals("1440")) {
replacement = "einmal pro Tag";
} else if (dauer.equals("10080")) {
replacement = "einmal in der Woche";
} else if (dauer.equals("43200")) {
replacement = "einmal im Monat";
} else if (dauer.equals("2880")) {
replacement = "einmal alle 2 Tage";
} else if (dauer.equals("4320")) {
replacement = "einmal alle 3 Tage";
} else if (dauer.equals("5760")) {
replacement = "einmal alle 4 Tage";
}else if (dauer.equals("7200")) {
replacement = "einmal alle 5 Tage";
}else if (dauer.equals("8640")) {
replacement = "einmal alle 6 Tage";
} else if (dauer.equals("20160")) {
replacement = "einmal alle 2 Wochen";
} else if (dauer.equals("30240")) {
replacement = "einmal alle 3 Wochen";
} else if (dauer.equals("120")) {
replacement = "einmal alle 2 Stunden";
} else if (dauer.equals("180")) {
replacement = "einmal alle 3 Stunden";
} else if (dauer.equals("240")) {
replacement = "einmal alle 4 Stunden";
} else if (dauer.equals("300")) {
replacement = "einmal alle 5 Stunden";
} else if (dauer.equals("360")) {
replacement = "einmal alle 6 Stunden";
} else if (dauer.equals("420")) {
replacement = "einmal alle 7 Stunden";
} else if (dauer.equals("480")) {
replacement = "einmal alle 8 Stunden";
} else if (dauer.equals("540")) {
replacement = "einmal alle 9 Stunden";
} else if (dauer.equals("600")) {
replacement = "einmal alle 10 Stunden";
} else if (dauer.equals("660")) {
replacement = "einmal alle 11 Stunden";
} else if (dauer.equals("720")) {
replacement = "einmal alle 12 Stunden";
} else {
replacement = "alle "+dauer+" Minuten";
}
            return replacement;
        }
        public String getFeature() {
            String text = "";
               if (!getFT1().isEmpty() || !getFT2().isEmpty() || !getFT2().isEmpty()) {
           text += "##_°RR°FEATURE:_°r° ";
           
           if (!getFT1().isEmpty()) {
           Feature ft1 = Server.get().getFeature(getFT1());
           text += ft1.getText().replace("$GET_TIMEBAN",getReplacement(String.valueOf(getBanTime1())));
           }
            if (!getFT2().isEmpty()) {
           Feature ft2 = Server.get().getFeature(getFT2());
           text += "##"+ft2.getText().replace("$GET_TIMEBAN",getReplacement(String.valueOf(getBanTime2())));
           }
             if (!getFT3().isEmpty()) {
           Feature ft3 = Server.get().getFeature(getFT3());
           text += "##"+ft3.getText().replace("$GET_TIMEBAN",getReplacement(String.valueOf(getBanTime3())));
           }
         
       } 
               return text;
        }
        
        public boolean getPrev() {
            return prev;
        }
	public String getName() {
		return name;
	}
        public String getName2() {
          String r = "";
            if (!kategorie.isEmpty()) {
               
              r += kategorie; 
               if  (!name.startsWith("+")) {
                    r += "-";
                }
            }
            r += name;
            return r;
        }
        
        public String getSplit() {
            return split;
        }
        public boolean getTauschbar() {
            return tauschbar;
        }
        
        public int getBanTime1() {
            return ft1b;
        }
         public int getBanTime2() {
            return ft2b;
        }
          public int getBanTime3() {
            return ft3b;
        }
         
          public boolean getLink() {
              return linkable;
          }
          public String getKategorie() {
              return kategorie;
          }
        public String[] getCounter(String user,boolean lend) {
              int own = 0;
              int all = 0;
              String allnicks = "";
              
              for(Usersmiley ss : Server.get().getUsersmileys()) {
                  Smiley sm = Server.get().getSmiley(String.valueOf(ss.getSMID()));       
                 
                 
             
               if (sm != null) {
                  if (sm.getName2().equals(getName2())) {
                      all++;
                      if (ss.getUser().equals(user)) {
                          if (!lend && ss.getVerliehen().isEmpty() || lend) {
                       own++;
                      }}
                      if (allnicks.contains("|"+ss.getUser()+"~")) {
                          
                          for(String x : allnicks.split("\\|")) {
                              if  (!x.isEmpty()) {
                              if (x.contains(ss.getUser()+"~")) {
                                  String[] la = x.split("~");
                                  allnicks = allnicks.replace(x,"|"+la[0]+"~"+(Integer.parseInt(la[1])+1)+"|");
                                  
                              }
                                  
                                  
                          }}
                          
                      } else {
                      
                      allnicks += "|"+ss.getUser()+"~1|";
                      }
                  }
              }
        }
              return new String[] { String.valueOf(own),String.valueOf(all),allnicks };
          }
         public int getWahrscheinlichkeit() {
            if (getSelten() == 0) {
           return 0;
       } else  if (getSelten() == 1) {
           return 70;
       }else  if (getSelten() == 2) {
           return 60;
       }else  if (getSelten() == 3) {
           return 50;
       }else  if (getSelten() == 4) {
           return 40;
       }else  if (getSelten() == 5) {
           return 30;
       }else  if (getSelten() == 6) {
           return 20;
       }else  if (getSelten() == 7) {
           return 10;
       }else  if (getSelten() == 8) {
           return 7;
       }else  if (getSelten() == 9) {
           return 5;
       }else  if (getSelten() == 10) {
           return 2;
       }else  if (getSelten() == 11) {
           return 1;
       }
            
            return 0;
        }
        public int getSeltenPixel() {
            if (getSelten() == 0) {
           return 61; 
       } else  if (getSelten() == 1) {
           return 59;
       }else  if (getSelten() == 2) {
           return 53;          
       }else  if (getSelten() == 3) {
           return 48;
       }else  if (getSelten() == 4) {
           return 42;
       }else  if (getSelten() == 5) {
           return 37;
       }else  if (getSelten() == 6) {
          return 32;
       }else  if (getSelten() == 7) {
           return 26;
       }else  if (getSelten() == 8) {
           return 21;
       }else  if (getSelten() == 9) {
           return 15;
       }else  if (getSelten() == 10) {
           return 10;
       }else  if (getSelten() == 11) {
           return 5;
       }
            
            return 61;
        }
        
         
        public String getSeltenString() {
            if (getSelten() == 0) {
           return " "; // leerzeichen wegen _-Fail
       } else  if (getSelten() == 1) {
           return "extreeem häufig";
       }else  if (getSelten() == 2) {
           return "extrem häufig";
       }else  if (getSelten() == 3) {
           return "seeehr häufig";
       }else  if (getSelten() == 4) {
           return "sehr häufig";
       }else  if (getSelten() == 5) {
           return "häufig";
       }else  if (getSelten() == 6) {
           return "manchmal";
       }else  if (getSelten() == 7) {
           return "selten";
       }else  if (getSelten() == 8) {
           return "sehr selten";
       }else  if (getSelten() == 9) {
           return "seeehr selten";
       }else  if (getSelten() == 10) {
           return "extrem selten";
       }else  if (getSelten() == 11) {
           return "extreeem selten";
       }
            
            return "";
        }
     
        public String getText() {
            return text;
        }
        public int getSelten() {
            return selten;
        }
        public String getKCode() {
            return kcode;
        }
	
         public void setKategorie(String v) {
            kategorie = v;
            Server.get().query("update sm_smileys set `category`='"+v.replace("\'","\\\'")+"' where id = '"+id+"'");
               }
         
          public void setFT1Ban(String v) {
           ft1b = Integer.parseInt(v);
            Server.get().query("update sm_smileys set `feature1_bantime`='"+v+"' where id = '"+id+"'");
               }
           public void setFT2Ban(String v) {
           ft2b = Integer.parseInt(v);
            Server.get().query("update sm_smileys set `feature2_bantime`='"+v+"' where id = '"+id+"'");
               }
            public void setSplit(String v) {
           split = v;
            Server.get().query("update sm_smileys set `splits`='"+v+"' where id = '"+id+"'");
               }
             public void setTags(String v) {
           tags = v;
            Server.get().query("update sm_smileys set `tags`='"+v+"' where id = '"+id+"'");
               }
            
             
             public void setKCode(String v) {
           kcode = v;
            Server.get().query("update sm_smileys set `kcode`='"+v+"' where id = '"+id+"'");
               }
              public void setSyntax(String v) {
           syntax = v;
            Server.get().query("update sm_smileys set `syntax`='"+v+"' where id = '"+id+"'");
               }
              
               public void setName(String v) {
           name = v;
            Server.get().query("update sm_smileys set `name`='"+v.replace("\'","\\\'")+"' where id = '"+id+"'");
               }
               
              
               public void setPreview(int v) {
           prev = getBoolean(v);
            Server.get().query("update sm_smileys set `onlyPreview`='"+v+"' where id = '"+id+"'");
               }
                 public void setKnuddels(int v) {
           knuddels = v;
            Server.get().query("update sm_smileys set `knuddels`='"+v+"' where id = '"+id+"'");
               }
                 
                        public void setTauschbar(int v) {
           tauschbar = getBoolean(v);
            Server.get().query("update sm_smileys set `tauschbar`='"+v+"' where id = '"+id+"'");
               }
                 
                 public void setSelten(int v) {
           selten = v;
            Server.get().query("update sm_smileys set `selten`='"+v+"' where id = '"+id+"'");
               }
                 
                 public void setSpez(int v) {
           spez = getBoolean(v);
            Server.get().query("update sm_smileys set `spez`='"+v+"' where id = '"+id+"'");
               }
                 
                    public void setLinkable(int v) {
           linkable = getBoolean(v);
            Server.get().query("update sm_smileys set `linkable`='"+v+"' where id = '"+id+"'");
               }
              
                         
                public void setText(String v) {
           text = v;
            Server.get().query("update sm_smileys set `desc`='"+v.replace("\'","\\\'")+"' where id = '"+id+"'");
               }
            
             
            public void setFT3Ban(String v) {
           ft3b = Integer.parseInt(v);
            Server.get().query("update sm_smileys set `feature3_bantime`='"+v+"' where id = '"+id+"'");
               }
            
            
          public void setFT1(String v) {
           ft1 = v;
            Server.get().query("update sm_smileys set `feature1`='"+v+"' where id = '"+id+"'");
               }
           public void setFT2(String v) {
           ft2 = v;
            Server.get().query("update sm_smileys set `feature2`='"+v+"' where id = '"+id+"'");
               }
            public void setFT3(String v) {
           ft3 = v;
            Server.get().query("update sm_smileys set `feature3`='"+v+"' where id = '"+id+"'");
               }
        
}

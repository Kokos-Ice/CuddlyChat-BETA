
package starlight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.*;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;


public class Feature {
	private String name,text,makro;
        private int show;

	public Feature(ResultSet rs) throws SQLException {
		
		name = rs.getString("name");
                text = rs.getString("text");
                show = rs.getInt("sb_show");
                makro = rs.getString("makro");
		

		
	}

        public void setBan(String id, String pos, String biss,Client client) {
          
            if (!id.isEmpty()) {
            if (id.equals("own")) {
                int found = 0;
                String neu = "";
                for (String value : client.getAllowedFeatures().split("\\|")) {
                    if (!value.isEmpty()) {
                        if (value.equals(name) && found == 0) {
                            neu += "";
                            found = 1;
                        } else {
                            neu += "|"+value+"|";
                        }
                        
                    }
                }
                
              client.setAllowedFeatures(neu);  
            } else {
        Usersmiley sm = Server.get().getUsersmiley(id);
       Long bis = (System.currentTimeMillis()/1000)+Integer.parseInt(biss)*60;
        sm.setFeatureban(pos,bis);
  //      ModuleCreator.UPDATE_SB(client);
        }}
            
        }
	
	public String getName() {
		return name;
	}
        public String getText() {
            return text;
        }
        public String getMakro() {
            return makro;
        }
        public int getShow() {
            return show;
        }
        public void setShow(int v) {
            show = v;
            Server.get().query("update sm_features set `sb_show`='"+v+"' where name = '"+name+"'");
               }
         public void setText(String v) {
            text = v;
            Server.get().query("update sm_features set `text`='"+v.replace("\'","\\\'")+"' where name = '"+name+"'");
               }
            public void setMakro(String v) {
            makro = v;
            Server.get().query("update sm_features set `makro`='"+v+"' where name = '"+name+"'");
               }

	
}


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


public class Verliehen {
	private String id,usersmileyid,from,to,bis;
  
	public Verliehen(ResultSet rs) throws SQLException {
		 id = rs.getString("id");
		usersmileyid = rs.getString("usersmileyid");
                from = rs.getString("from");
                to = rs.getString("to");
                bis = rs.getString("bis");
                

		
	}

       
        public String getID() {
            return id;
        }
        public String getUserSmileyID() {
            return usersmileyid;
        }
        public String getFrom() {
            return from;
        }
        public String getTo() {
            return to;
        }
        public String getBis() {
            return bis;
        }
	
}

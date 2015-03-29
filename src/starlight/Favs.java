
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

public class Favs {
	private String user,syntax,smid,id;

	public Favs(ResultSet rs) throws SQLException {
		
		syntax = rs.getString("syntax");
                id = rs.getString("id");
                user = rs.getString("user");
                smid = rs.getString("smid");
	

		
	}

      
	public String getSyntax() {
		return syntax;
	}
        public String getUser() {
		return user;
	}
        public String getID() {
            return id;
        }
        public String getSMID() {
		return smid;
	}
      
	
}

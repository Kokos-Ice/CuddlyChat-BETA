
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

public class Gratis {
	private String name,sm,text,grafik,buttonsettings,prozent;

	public Gratis(ResultSet rs) throws SQLException {
		
		name = rs.getString("name");
                sm = rs.getString("sm");
                text = rs.getString("text");
                buttonsettings = rs.getString("buttonsettings");
                prozent = rs.getString("prozent");
                grafik = rs.getString("grafik");

		
	}

      public String getGrafik() {
          return grafik;
      }
	public String getName() {
		return name;
	}
        public String getText() {
            return text;
        }
        public String getSM() {
            return sm;
        }
        public String getButton() {
            return buttonsettings;
        }
        public String getProzent() {
            return prozent;
        }
        
      
	
}

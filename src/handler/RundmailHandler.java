package handler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import starlight.Client;
import starlight.Server;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

/**
 *
 * @author Basti
 */
public class RundmailHandler {
    
    public static void handle(String[] tokens, Client client) {
        
        
        
    	String drei = "";
    	
    	
    	try {
    		drei = tokens[3].trim();
    		
    	}catch(Exception ex) {
    		
    	}
        
   	PoolConnection pcona = ConnectionPool.getConnection();
            PreparedStatement psa = null;
        
            try {
                Connection cona = pcona.connect();
                psa = cona.prepareStatement("SELECT `name` FROM `accounts` WHERE name != ''");
                ResultSet rsa = psa.executeQuery();
            
                while(rsa.next()) {
                    
                    String name = rsa.getString("name");
                    Server.get().newMessage(Server.get().getButler().getName(), name, String.format("Rundmail an alle Chatter von %s", client.getName()), String.format("%s", drei), System.currentTimeMillis()/1000);
                    
                }    
            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (psa != null) {
                    try {
                        psa.close();
                    } catch (SQLException e) {
                    }
                }

                pcona.close();
            }     
        
        
        
        
        
    }
    
}

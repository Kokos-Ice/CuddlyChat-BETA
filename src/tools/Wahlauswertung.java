package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;


public class Wahlauswertung {
  
    public static String Auswerten(String name) {
      
        Map<String, Integer> inputMap = new HashMap<String, Integer>(); 
        
              PoolConnection pcon2 = ConnectionPool.getConnection();
        PreparedStatement ps2 = null;
      
        try {
           Connection con = pcon2.connect();
           ps2 = con.prepareStatement("SELECT * FROM `wahlenvoted` where wahl='"+name+"'");
          ResultSet rs = ps2.executeQuery();
          while (rs.next()) {            
          String a = rs.getString("what");
          String[] b = a.split("\\|");
          for(String c : b) {
              if (!c.isEmpty()) {
                  String[] vals = c.split(";;;");
                  String an = vals[0];
                  String zahl = vals[1].trim();
                  
                  if (inputMap.containsKey(an)) {
                      // update
                      int old = inputMap.get(an);
                       inputMap.put(an, old+Integer.parseInt(zahl));
                  } else {
                       inputMap.put(an, Integer.parseInt(zahl));
                  }
                  
              }
          }
             
              
         }
        }
        catch (SQLException e) {
         e.printStackTrace();
       } finally {
          if (ps2 != null)
            try {
             ps2.close();
            }
            catch (SQLException e)
            {
            }
           pcon2.close();
        }
        
          SortedMapUtils.createMapSortedByValuesAndKeys(inputMap);
    Comparator<Integer> customComparator = 
    Collections.reverseOrder(SortedMapUtils.<Integer>comparableComparator());
     Map<String, Integer> customSorted =  SortedMapUtils.createMapSortedByValuesAndKeys(inputMap,customComparator,SortedMapUtils.<String>comparableComparator());    
  String aus = "";
 
          for (Object key : customSorted.keySet())
        {
       aus += key.toString()+"~.~"+inputMap.get(key.toString())+"#";               
        }
          if (aus.isEmpty()) {
              aus = " ";
          }
    return aus;
    
        
    }
          
}

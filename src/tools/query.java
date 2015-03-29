package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class query
{
  public static void truncate(String query)
  {
    PoolConnection pcon8 = ConnectionPool.getConnection();
    PreparedStatement ps8 = null;
    try
    {
      Connection con8 = pcon8.connect();
      ps8 = con8.prepareStatement(query);
      ps8.execute();
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps8 != null)
        try {
          ps8.close();
        }
        catch (SQLException e)
        {
        }
      pcon8.close();
    }
  }

  public static void delete(String query)
  {
    PoolConnection pcon2E = ConnectionPool.getConnection();
    PreparedStatement ps2E = null;
    try {
      Connection con2E = pcon2E.connect();
      ps2E = con2E.prepareStatement(query);
      ps2E.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps2E != null)
        try {
          ps2E.close();
        }
        catch (SQLException e) {
        }
      pcon2E.close();
    }
  }

  public static void insert(String query) {
    PoolConnection pcon2E = ConnectionPool.getConnection();
    PreparedStatement ps2E = null;
    try {
      Connection con2E = pcon2E.connect();
      ps2E = con2E.prepareStatement(query);
      ps2E.execute();
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps2E != null)
        try {
          ps2E.close();
        }
        catch (SQLException e) {
        }
      pcon2E.close();
    }
  }

  public static int count(String query)
  {
    int anzahl = 0;
    PoolConnection pcon = ConnectionPool.getConnection();
    PreparedStatement ps = null;
    try
    {
      Connection con = pcon.connect();
      ps = con.prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
        anzahl = rs.getInt("a");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps != null)
        try {
          ps.close();
        }
        catch (SQLException e)
        {
        }
      pcon.close();
    }
    return anzahl;
  }

  public static List<String> whiles(String table, String where)
  {
    List lol = new ArrayList();
    PoolConnection pcon = ConnectionPool.getConnection();
    PreparedStatement ps = null;
    String more = "";
    if (!where.isEmpty())
      more = "where " + where;
    try
    {
      Connection con = pcon.connect();
      ps = con.prepareStatement("SELECT * FROM " + table + " " + more);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        lol.add(rs.getString("id"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps != null)
        try {
          ps.close();
        }
        catch (SQLException e)
        {
        }
      pcon.close();
    }
    return lol;
  }

  public static List<String> select(String fields, String table, String where)
  {
    List lol = new ArrayList();
    PoolConnection pcon = ConnectionPool.getConnection();
    PreparedStatement ps = null;
    String more = "";
    if (!where.isEmpty())
      more = "where " + where;
    try
    {
      Connection con = pcon.connect();
      ps = con.prepareStatement("SELECT " + fields + " FROM " + table + " " + more);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        String[] haha = fields.split(",");
        for (String item : haha)
          lol.add(rs.getString(item));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      if (ps != null)
        try {
          ps.close();
        }
        catch (SQLException e)
        {
        }
      pcon.close();
    }
    return lol;
  }
  public static void update(String query) {
    PoolConnection pcon2E = ConnectionPool.getConnection();
    PreparedStatement ps2E = null;
    try {
      Connection con2E = pcon2E.connect();
      ps2E = con2E.prepareStatement(query);
      ps2E.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps2E != null)
        try {
          ps2E.close();
        }
        catch (SQLException e) {
        }
      pcon2E.close();
    }
  }
}

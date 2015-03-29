/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (C) 2011 - 2012 Flav <http://banana-coding.com>
 * 
 * Diese Dateien unterliegen dem Coprytight von Banana-Coding und
 * darf verändert, aber weder in andere Projekte eingefügt noch
 * reproduziert werden.
 * 
 * Der Emulator dient - sofern der Client nicht aus Eigenproduktion
 * stammt - nur zu Lernzwecken, das Hosten des originalen Knuddels Clients
 * ist auf eigene Gefahr und verstößt möglicherweise gegen Schutzrechte
 * der Knuddels.de GmbH & Co KG
 * 
 * Autoren: Flav (Grundversion), Localhost (Erweiterte Version), Kokos-Ice (Erweiterte Version)
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

/**
 *
 * @author Brainy
 */
public class KarteUtility {
    private static List<Character> listedKeys = new ArrayList<Character>();

    public static String getRandomSentence() {
        PoolConnection pcon = ConnectionPool.getConnection();
        Statement stmt = null;
        String sentence = null;

        try {
            Connection con = pcon.connect();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `SENTENCE` FROM `karte` ORDER BY RAND() LIMIT 1");

            rs.next();
            sentence = rs.getString("SENTENCE");
            rs.next();
            rs.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {

                }
            }

            pcon.close();
            return sentence;
        }
    }
    public static String makeSentence(String sentence, String solution) {
        StringBuilder builder = new StringBuilder();
        Map<Character, String> map = new HashMap<Character, String>();

        // ein(°Y°1°°) Du(°Y°2°°) Noob.(°Y°3°°) bist(°Y°4°°)
        sentence = sentence.replace("°Y°", "").replace("°°", "");

        Matcher sentenceMatcher = Pattern.compile("\\s*([^\\)]+)\\((.)\\)\\s*").matcher(sentence);
        while (sentenceMatcher.find()) {
            String value = sentenceMatcher.group(1).trim();
            Character key = sentenceMatcher.group(2).charAt(0);

            if (!map.containsKey(key)) {
                map.put(key, value);
            }
        }

        for (int i = 0; i < solution.length(); i++) {
            Character temp = solution.charAt(i);

            if (map.containsKey(temp)) {
                builder.append(map.get(temp));

                if (i < solution.length() - 1) {
                    builder.append(' ');
                }
            }
        }


        return builder.toString();
    }

    public static String mixSentence(String rawSentence) {
        listedKeys.clear();

        StringBuilder builder = new StringBuilder();
        Character[] keys = new Character[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

        if (rawSentence == null) {
            return null;
        }

        String[] rawSentenceParams = rawSentence.split(" ");
        Collections.shuffle(Arrays.asList(rawSentenceParams));

        if (rawSentenceParams.length > keys.length || rawSentenceParams.length < 3) {
            return null;
        }

        for (int i = 0; i < rawSentenceParams.length; i++) {
            String param = rawSentenceParams[i];
            Character paramKey = keys[i];

            builder.append(String.format("%s(°B°%s°°)", param, paramKey));
            listedKeys.add(paramKey);

            if (i < rawSentenceParams.length - 1) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    public static boolean almostCorrect(String raw, String userSentence) {
        String[] rawParams = raw.split(" ");
        String[] userParams = userSentence.split(" ");

        if (rawParams.length != userParams.length) {
            return false;
        }

        int falseCounter = 0;
        for (int i = 0; i < rawParams.length; i++) {
            if (rawParams[i].equals(userParams[i])) {
                continue;
            }

            falseCounter++;
        }

        return (falseCounter < 3);
    }

    public static float totalTime(long start) {
        return (float)((float)(System.currentTimeMillis() - start) / 1000);
    }

    public static boolean isNumberic(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static List<Character> getTwiceChar(String input) {
        List<Character> twiceChar = new ArrayList<Character>();

        char[] arr = input.toCharArray();
        List<Character> list = new ArrayList<Character>();

        for (int i = 0; i < arr.length; i++) {
            if (list.contains(arr[i]) && !twiceChar.contains(arr[i])) {
                twiceChar.add(arr[i]);
                continue;
            }

            list.add(arr[i]);
        }

        return twiceChar;
    }

    public static List<Character> falseKeys(String solution) {
        List<Character> falseList = new ArrayList<Character>();
        for (int i = 0; i < solution.length(); i++) {
            if (!listedKeys.contains(solution.charAt(i))) {
                falseList.add(solution.charAt(i));
            }
        }
        return falseList;
    }

    public static void Reset() {
         PoolConnection pcon = ConnectionPool.getConnection();
         PreparedStatement ps = null;

        try {
            Connection con = pcon.connect();

            ps = con.prepareStatement("UPDATE `karterekord` SET `nick` = ?, `points` = ?, `sentence` = ?, `seconds` = ? WHERE 1");
            ps.setString(1, "");
            ps.setFloat(2, 0.00f);
            ps.setString(3, "");
            ps.setFloat(4, 0.00f);

            ps.executeUpdate();
        } catch (SQLException e) {
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
    }

    public static void Add(String nick, float points, String sentence, float seconds) {
        PoolConnection pcon = ConnectionPool.getConnection();
        PreparedStatement ps = null;

        try {
            Connection con = pcon.connect();
            ps = con.prepareStatement("UPDATE `karterekord` SET `nick` = ?, `points` = ?, `sentence` = ?, `seconds` = ? WHERE 1");
            ps.setString(1, nick);
            ps.setFloat(2, points);
            ps.setString(3, sentence);
            ps.setFloat(4, seconds);
            ps.executeUpdate();
        } catch (SQLException e) {
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
    }

    public static Object[] Read() {
        Object[] data = new Object[4];
        // NICK|POINTS|TIME

        PoolConnection pcon = ConnectionPool.getConnection();
        Statement stmt = null;

        try {
            Connection con = pcon.connect();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `karterekord` WHERE 1");

            if (rs.next()) {
                data[0] = rs.getString("nick");
                data[1] = rs.getFloat("points");
                data[2] = rs.getString("sentence");
                data[3] = rs.getFloat("seconds");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {

                }
            }

            pcon.close();
            for (int i = 0; i < data.length; i++) {
                if (data[i] == null) {
                    return (Object[])null;
                }
            }

            return data;
        }
    }
}
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



package starlight;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChannelStyle {
    private byte fontSize, userListFontSize, lineSpace;
    private String foreground, background, red, blue, fam, stammi, admin;
    private int id;

    public ChannelStyle(ResultSet rs) throws SQLException {
    	id = rs.getInt("id");
        fontSize = rs.getByte("fontSize");
        userListFontSize = rs.getByte("userListFontSize");
        lineSpace = rs.getByte("lineSpace");
        foreground = rs.getString("foreground");
        background = rs.getString("background");
        red = rs.getString("red");
        blue = rs.getString("blue");
        fam = rs.getString("fam");
        stammi = rs.getString("stammi");
        admin = rs.getString("admin");
    }

    public byte getFontSize() {
        return fontSize;
    }
    
    public int getID() {
    	return id;
    }

    public byte getUserListFontSize() {
        return userListFontSize;
    }

    public byte getLineSpace() {
        return lineSpace;
    }

    public String getForeground() {
        return foreground;
    }

    public String getBackground() {
        return background;
    }

    public String getRed() {
        return red;
    }

    public String getBlue() {
        return blue;
    }

    public String getRankColor(Client client) {
        if (client.getRank() == 1) {
            return fam;
        }

        if (client.getRank() > 1 && client.getRank() < 6) {
            return stammi;
        }
        
        if(client.getRank() > 5) {
            if(client.getMask() == 1) {
                return stammi;
            }
            
            return admin;
        }

        return foreground;
    }
}
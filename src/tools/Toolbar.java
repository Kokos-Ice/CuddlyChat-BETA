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



package tools;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import starlight.Client;
import starlight.SendOpcode;
import starlight.Server;
import tools.PacketWriter;

public class Toolbar {
    
    private final static char EMPTY_TEXTVALUE = 0x00;

    public static String ToolbarString(String channel, List<Button> buttons) {
        return ToolbarString(channel, buttons, null);
    }

    public static String ToolbarString(String channel, List<Button> buttons, List<AnalogButton> analogButtons) {
        PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        //System.out.println(Server.get().getModuleTree().getIndex("SHOW_BUTTONS"));
        packet.writeShort(Server.get().getModuleTree().getIndex("SHOW_BUTTONS"));
        packet.write((byte) channel.length());
        packet.writeString(channel);

        for (Button button : buttons) {
            packet.write(0x0B);
            writeButton(packet, button);
        }
        packet.write(0x0C);

        if (analogButtons != null) {
            for (AnalogButton button : analogButtons) {
                packet.write(0x0B);
                writeButton(packet, button.normalButton);
                packet.writeInt(button.analogSegments);
                packet.writeInt(button.sendIntervalMillis);
                packet.writeInt(button.cooldownIntervalMillis);
            }
        }

        packet.write(0x0C);
        return packet.toString();
    }

    private static void writeButton(PacketWriter packet, Button button) {
        if (!isNullOrEmpty(button.text)) {
            packet.write((byte) button.text.length());
            packet.writeString(button.text);
        } else {
            packet.write(EMPTY_TEXTVALUE);
        }

        if (!isNullOrEmpty(button.image)) {
            packet.write((byte) button.image.length());
            packet.writeString(button.image);
        } else {
            packet.write(EMPTY_TEXTVALUE);
        }

        packet.writeBoolean(button.showOnTheLeftSide);

        if (!isNullOrEmpty(button.chatFunction)) {
            packet.write((byte) button.chatFunction.length());
            packet.writeString(button.chatFunction);
        } else {
            packet.write(EMPTY_TEXTVALUE);
        }
    }

    public static String Settings(String channel, Shading barStyle, Shading buttonStyle, Shading analogButtonStyle) {
        PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        packet.writeShort(Server.get().getModuleTree().getIndex("BUTTON_BAR_SETTINGS"));
        packet.write((byte) channel.length());
        packet.writeString(channel);

        //Write the bar style
        writeByteArray(packet, getByteArrayFromColor(barStyle.topFadeFrom));
        writeByteArray(packet, getByteArrayFromColor(barStyle.middleFadeTo));
        writeByteArray(packet, getByteArrayFromColor(barStyle.bottomSolid));

        //Write the button Style
        writeByteArray(packet, getByteArrayFromColor(buttonStyle.topFadeFrom));
        writeByteArray(packet, getByteArrayFromColor(buttonStyle.middleFadeTo));
        writeByteArray(packet, getByteArrayFromColor(buttonStyle.bottomSolid));

        //Write the analogButtonStyle
        writeByteArray(packet, getByteArrayFromColor(analogButtonStyle.topFadeFrom));
        writeByteArray(packet, getByteArrayFromColor(analogButtonStyle.middleFadeTo));
        writeByteArray(packet, getByteArrayFromColor(analogButtonStyle.bottomSolid));

        return packet.toString();

    }

    private static void writeByteArray(PacketWriter writer, byte[] array) {

        for (int i = 0; i < array.length; i++) {
            writer.write(array[i]);
        }
    }

    private static byte[] getByteArrayFromColor(Color color) {
        return new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()};
    }

    private static boolean isNullOrEmpty(String str) {
        return (str == null || str.length() == 0); //Ja, Flav, man kann auch .isEmpty benutzen!
    }

    public static class Shading {

        private Color topFadeFrom;
        private Color middleFadeTo;
        private Color bottomSolid;

        public Shading(Color topFadeFrom, Color middleFadeTo, Color bottomSolid) {
            this.topFadeFrom = topFadeFrom;
            this.middleFadeTo = middleFadeTo;
            this.bottomSolid = bottomSolid;
        }
    }

    public static class Button {

        private String text;
        private String image;
        private String chatFunction;
        private boolean showOnTheLeftSide;

        public Button(String text, String chatFunction) {
            this(text, chatFunction, "", true);
        }

        public Button(String text, String chatFunction, String image, boolean showOnTheLeftSide) {
            this.text = text;
            this.image = image;
            this.chatFunction = chatFunction;
            this.showOnTheLeftSide = showOnTheLeftSide;
        }
    }

    public static class AnalogButton {

        private Button normalButton;
        private int analogSegments;
        private int sendIntervalMillis;
        private int cooldownIntervalMillis;

        public AnalogButton(Button normalButton, int analogSegments, int sendIntervalMillis, int cooldownIntervalMillis) {
            this.normalButton = normalButton;
            this.analogSegments = analogSegments;
            this.sendIntervalMillis = sendIntervalMillis;
            this.cooldownIntervalMillis = cooldownIntervalMillis;
        }
    }

    public static String FotoContestStyle(String channel) {
        return Settings(channel,
          /* 1*/   new Shading(new Color(0xFF, 0xDC, 0xBA), new Color(0xEB, 0x97, 0x00), new Color(0xBF, 0x64, 0x14)),
          /* 2*/   new Shading(new Color(0xE1, 0xEF, 0xF9), new Color(0xB1, 0xC8, 0xD8), new Color(0x4D, 0x93, 0xBF)),
          /* 3*/   new Shading(new Color(0xEF, 0xCA, 0xB4), new Color(0xE3, 0xCA, 0xB4), new Color(0xDD, 0x4A, 0x3F)));
    }

    public static String FotoContestFlirtStyle(String channel) {
        return Settings(channel,
          /* 1*/   new Shading(new Color(0xFF, 0x5E, 0x60), new Color(0x9B, 0x01, 0x03), new Color(0x5F, 0x01, 0x02)),
          /* 2*/   new Shading(new Color(0xC6, 0xF3, 0xFF), new Color(0x2F, 0x61, 0xFF), new Color(0x01, 0x2A, 0xAE)),
          /* 3*/   new Shading(new Color(0xEF, 0xCA, 0xB4), new Color(0xE3, 0x75, 0x67), new Color(0xDD, 0x4A, 0x3F)));
    }
    
    public static String HighOrLowStyle(String channel) {
        return Settings(channel,
          /* 1*/   new Shading(new Color(0x37, 0x00, 0x2A), new Color(0x37, 0x00, 0x2A), new Color(0x37, 0x00, 0x2A)),
          /* 2*/   new Shading(new Color(0xC6, 0xF3, 0xFF), new Color(0x2F, 0x61, 0xFF), new Color(0x01, 0x2A, 0xAE)),
          /* 3*/   new Shading(new Color(0xEF, 0xCA, 0xB4), new Color(0xE3, 0x75, 0x67), new Color(0xDD, 0x4A, 0x3F)));
   }
    
    
    public static String FlirtStyle(String channel) {
        return Settings(channel,
          /* 1*/   new Shading(new Color(0x00, 0x00, 0x00), new Color(0x00, 0x00, 0x00), new Color(0x00, 0x00, 0x00)),
          /* 2*/   new Shading(new Color(0xC6, 0xF3, 0xFF), new Color(0x2F, 0x61, 0xFF), new Color(0x01, 0x2A, 0xAE)),
          /* 3*/   new Shading(new Color(0xEF, 0xCA, 0xB4), new Color(0xE3, 0x75, 0x67), new Color(0xDD, 0x4A, 0x3F)));
    }
    
    /*
     * 1 Farbe der Toolbar 
     * 2 Farbe der Toolbar Buttons
     * 3 Farbe der Analog Buttons
     
     */

    public static String BingoStyle(String channel) {
        return Settings(channel,
          /* 1*/   new Shading(new Color(0x67, 0x9F, 0xFF), new Color(0x4E, 0x69, 0xFF), new Color(0x26, 0x3E, 0xFF)),
          /* 2*/   new Shading(new Color(0x63, 0x9F, 0xFF), new Color(0x4E, 0x69, 0xFF), new Color(0x32, 0x4B, 0xFF)),
          /* 3*/   new Shading(new Color(0x63, 0x9F, 0xFF), new Color(0x4E, 0x69, 0xFF), new Color(0x32, 0x4B, 0xFF)));
    }

    public static String SmileyWarsBetaStyle(String channel) {
        return Settings(channel,
         /* 1*/    new Shading(new Color(0xAE, 0xB7, 0xFC), new Color(0x76, 0x84, 0xE4), new Color(0x26, 0x37, 0xB9)),
         /* 2*/    new Shading(new Color(0xAE, 0xB7, 0xFC), new Color(0x76, 0x84, 0xE4), new Color(0x26, 0x37, 0xB9)),
         /* 3*/    new Shading(new Color(0xEF, 0xCA, 0xB4), new Color(0xE3, 0x75, 0x67), new Color(0xDD, 0x4A, 0x3F)));
    }
    
    
    
        public static void sendButtons(Client client,String channel, String button) {
     
        if (!button.isEmpty()) {
        List<Toolbar.Button> buttons = new ArrayList<Toolbar.Button>();
         String[] lala = button.split("~");
         
         for (String lol : lala) {
              String[] haha = lol.split("\\|");
              
              boolean pos;
              if (haha[3].equals("left")) {
                  pos = true;
              } else {
                  pos = false;
              }
 buttons.add(new Toolbar.Button(haha[0],haha[1],haha[2],pos));
      }
         
   client.send(ToolbarString(channel, buttons));
        }
    }
        
       
    public static void sendStyle(String channel, Client client, String color) {
      
if (color.equals("orange")) {
  client.send(FotoContestStyle(channel));
} else if (color.equals("rot")) {
client.send(FotoContestFlirtStyle(channel));
} else if (color.equals("blau1")) {
client.send(BingoStyle(channel));
}else if (color.equals("blau2")) {
client.send(SmileyWarsBetaStyle(channel));
}
}
    
}

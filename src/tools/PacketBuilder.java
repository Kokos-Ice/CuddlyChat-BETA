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

public class PacketBuilder {
    private StringBuilder buffer;

    public PacketBuilder() {
        buffer = new StringBuilder();
    }

    public PacketBuilder(String opcode) {
        buffer = new StringBuilder(opcode);
    }

    public void write(int[] b) {
        for (int i = 0; i < b.length; i++) {
            writeByte(b[i]);
        }
    }

    public void writeByte(int b) {
        buffer.append((char) b);
    }

    public void writeShort(int s) {
        writeByte((s >> 8) & 0xFF);
        writeByte(s & 0xFF);
    }

    public void writeString(String str) {
        writeString(str, false, false);
    }

    public void writeString(String str, boolean appendLength, boolean isShort) {
        if (appendLength) {
            int length = str.length();

            if (isShort) {
                writeShort(length);
            } else {
                writeByte(length);
            }
        }

        buffer.append(str);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}

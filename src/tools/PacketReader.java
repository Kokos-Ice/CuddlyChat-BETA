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

public class PacketReader {
	private String buffer;

	public PacketReader(String buffer) {
		this.buffer = buffer;
	}

	public byte read() {
		byte v = (byte) buffer.charAt(0);
		buffer = buffer.substring(1);
		return v;
	}

	public boolean readBoolean() {
		return read() != 0;
	}

	public short readShort() {
		return (short) (((read() & 0xFF) << 8)
				+ (read() & 0xFF));
	}

	public int readInt() {
		return ((read() & 0xFF) << 24)
				+ ((read() & 0xFF) << 16)
				+ ((read() & 0xFF) << 8)
				+ (read() & 0xFF);
	}

	public String readString(int len) {
		String v = buffer.substring(0, len);
		buffer = buffer.substring(len);
		return v;
	}
}
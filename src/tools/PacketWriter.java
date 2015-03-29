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

/**
 *
 * @author Flav
 */
public class PacketWriter {
	private StringBuilder buffer;

	public PacketWriter() {
		buffer = new StringBuilder();
	}

	public void write(int v) {
		buffer.append((char) v);
	}

	public void writeBoolean(boolean v) {
		write(v ? 1 : 0);
	}

	public void writeShort(int v) {
		write(v >> 8);
		write(v);
	}

	public void writeInt(int v) {
		write(v >> 24);
		write(v >> 16);
		write(v >> 8);
		write(v);
	}

	public void writeString(String v) {
		buffer.append(v);
	}

	public String toString() {
		return buffer.toString();
	}
}
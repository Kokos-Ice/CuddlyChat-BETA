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



package game;

public class WordMixRecord {
	private static float points;
	private static float seconds;
	private static String sentence;
	private static String nick;
	
	public static float getPoints() {
		return points;
	}
	
	public static void setPoints(float points) {
		WordMixRecord.points = points;
	}
	
	public static float getSeconds() {
		return seconds;
	}
	
	public static void setSeconds(float seconds) {
		WordMixRecord.seconds = seconds;
	}
	
	public static String getSentence() {
		return sentence;
	}
	
	public static void setSentence(String sentence) {
		WordMixRecord.sentence = sentence;
	}
	
	public static String getNick() {
		return nick;
	}
	
	public static void setNick(String nick) {
		WordMixRecord.nick = nick;
	}
}

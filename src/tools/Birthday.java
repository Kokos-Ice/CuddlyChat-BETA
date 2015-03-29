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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Birthday {
	
	Date birthdate;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

	public Birthday (String date) throws Exception {
		setBirthDate(date);
	}

	private void setBirthDate (Date date) {
		birthdate = date;
	}

	private void setBirthDate (String date) throws Exception {
		setBirthDate(sdf.parse(date));
	}

	public Date birthDate () {
		return (birthdate);
	}

	public int age () {
		GregorianCalendar cal = new GregorianCalendar();
		int y, d, a;

		y = cal.get(Calendar.YEAR);
		d = cal.get(Calendar.DAY_OF_YEAR);
		cal.setTime(birthdate);
		a = y - cal.get(Calendar.YEAR);
		if (d < cal.get(Calendar.DAY_OF_YEAR)) {
			--a;
		}
		return (a);
	}

	public String birthDateString () {
		return (sdf.format(birthdate));
	}

	public String toString () {
		return birthDateString();
	}

	public static int toAge(String birthday) throws Exception {
		Birthday h = new Birthday(birthday);
		return h.age();
	}
}

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

import starlight.Server;

public class Settings {
	private static String codes="",stammi;
	public static String teams;
	private static String ban = "";
        private static String topdayuser = "";
	private static int maintenance, registration;
	private static long cronjob;
	static int revision;

	public Settings(ResultSet rs) throws SQLException {
        teams = rs.getString("teams");
        ban = rs.getString("banlist");
        revision = rs.getInt("revision");
        codes = rs.getString("codes");
        topdayuser = rs.getString("topdayuser");
        registration = rs.getInt("registration");
        cronjob = rs.getLong("cronjob");
        maintenance = rs.getInt("maintenance");       
        stammi = rs.getString("stammi");
    }
	
	public static boolean checkTeam(String team) {
		return teams.toLowerCase().contains(String.format("|%s|", team.toLowerCase()));
	}
	
        public static String getTeamname(String arg) {
            String name = "";
            for(String a : teams.split("\\|")) {
                if (!a.isEmpty()) {
                    if (arg.toLowerCase().equals(a.toLowerCase())) {
                        name = a;
                    }
                }
                
            }
            return name;
        }
	public static String getTeam(String arg) {
		return String.format("%s%s", arg.toUpperCase().substring(0, 1), arg.substring(1).replace("-team", "-Team").replace("etchat", "etChat"));
	}
	
	public static String getTeams() {
		return teams;
	}
	
	public static int getRevision() {
		return revision;
	}
	
        public static String getTopdayUser() {
            return topdayuser;
        }
	public static void addTeam(String team) {
		String newTeams = String.format("%s|%s|", Settings.teams, team);
		Settings.teams = newTeams;
		Server.get().query(String.format("UPDATE `settings` SET `teams` = '%s'", newTeams));
	}
	
	public static void increaseRevision(int revision) {
		Settings.revision +=revision;
		Server.get().query(String.format("UPDATE `settings` SET `revision` = '%s'", Settings.revision+=revision));
	}
	
	public static void removeTeam(String team) {
		String newTeams = Settings.teams.replace(String.format("|%s|", team), "");
		Settings.teams = newTeams;
		Server.get().query(String.format("UPDATE `settings` SET `teams`='%s'", newTeams));
	}
	
	public static String getStammi() {
		return stammi;
	}
	
	public static String getBan() {
		return ban;
	}
	
	public static int getRegistration() {
		return registration;
	}
	
	public static int getMaintenance() {
		return maintenance;
	}

	public static long getCronjob() {
		return cronjob;
	}

	

	public static void setRegistration(int registration) {
		Settings.registration = registration;
		Server.get().query(String.format("UPDATE `globalsettings` SET `value`='%s' where `option`= 'REGI_AKTIV'", registration));
	}

public static void setTopdayuser(String a) {
		Settings.topdayuser = a;
		Server.get().query("UPDATE `settings` SET `topdayuser` = '"+a+"'");
	}
        
	public static String getCodes() {
		return codes;
	}

	public static void setCodes(String codes) {
		Settings.codes = codes;
		Server.get().query(String.format("UPDATE `settings` SET `codes` = '%s'", codes));
	}

	
	public static void setBan(String ban) {
		Settings.ban = ban;
		Server.get().query(String.format("UPDATE `settings` SET `banlist` = '%s'", ban));
	}

	public static void setMaintenance(int maintenance) {
		Settings.maintenance = maintenance;
		Server.get().query(String.format("UPDATE `settings` SET `maintenance` = '%s'", maintenance));
	}

	public static void setCronjob(long cronjob) {
		Settings.cronjob = cronjob;
		Server.get().query(String.format("UPDATE `settings` SET `cronjob` = '%s'", cronjob));
	}
}

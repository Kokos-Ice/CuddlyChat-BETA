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



package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.PacketCreator;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.*;

public class BlindDateHandler {
	public static void handle(String[] tokens, Client client) {

		if (tokens[2].contains("OK")) {

			if (Integer.parseInt(tokens[4]) > Integer.parseInt(tokens[5])) {
			    client.send(PopupNewStyle.create("Blinddate Problem", "Blinddate Problem", "Die _erste Altersangabe muss kleiner oder gleich groß_ der zweiten gewählt werden.", 400, 300));
			
                        
                        } else {
				client.send(PopupNewStyle.create("Blinddate Edit erfolgreich", "Blinddate Edit erfolgreich", "Alle Einstellungen wurden erfolgreich aktualisiert.", 400, 300));

				// client.setSearchGender(tokens[3]);
				// client.setSearchAgeFrom(tokens[4]);
				// client.setSearchAgeUntil(tokens[5]);
				// client.setsearchMotiv(tokens[6]);
				if (client.existBlindDate() == 1) {
					Server.get()
							.query("update blinddate set gender='" + tokens[3]
									+ "', agefrom='" + tokens[4]
									+ "', ageuntil='" + tokens[5]
									+ "', motiv='" + tokens[6]
									+ "' where user='" + client.getName() + "'");
				}
			}
		} else {

			String akzept = "";
			String zugewiesen = "";
			String user = "";
			PoolConnection pcon = ConnectionPool.getConnection();
			PreparedStatement ps = null;
			try {
				Connection con = pcon.connect();
				ps = con.prepareStatement("SELECT * FROM `blinddate` WHERE `id` = ?");
				ps.setString(1, tokens[1]);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					akzept = rs.getString("akzept");
					zugewiesen = rs.getString("zuteilung");
					user = rs.getString("user");

				}
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
			if (!akzept.isEmpty()) {
				if (tokens[2].contains("Annehmen")) {

					if (akzept.equals("1")) {
						String channelName = String.format("/Blinddate %s", new Random().nextInt(111));
						Server.get().createChannel(channelName, 1, client.getName(), 1, null, null);
						Channel channelTo = Server.get().getChannel(channelName);
						Client client1 = Server.get().getClient(user);
						Client client2 = Server.get().getClient(zugewiesen);

						client1.joinChannel(channelTo);
			            client1.send(PacketCreator.channelFrame(channelTo, client.getName(), client.newMessages.size()));
			            channelTo.join(client1); 
						client2.joinChannel(channelTo);
			            client2.send(PacketCreator.channelFrame(channelTo, client.getName(), client.newMessages.size()));
			            channelTo.join(client2); 
						
						Server.get().query(
								"delete from blinddate where user='" + user
										+ "'");
						Server.get().query(
								"delete from blinddate where user='"
										+ zugewiesen + "'");
					}
					Server.get().query(
							"update blinddate set akzept='1' where id='"
									+ tokens[1] + "'");
				} else {
					Server.get().query(
							"update blinddate set akzept='0', zuteilung='', time='' where user='"
									+ user + "'");
					Server.get().query(
							"update blinddate set akzept='0', zuteilung='', time='' where user='"
									+ zugewiesen + "'");

				}
			}
		}

	}
}
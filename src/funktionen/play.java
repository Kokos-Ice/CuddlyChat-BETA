package funktionen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.PacketCreator;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

/*public class play {
	//rebootalarm.mp3
	public static void aktion(String arg, final Client client, final Channel channel) {
           if(client.hasPermission("cmd.play")) {
        		
			String[] data	= arg.split(":");
			
			if(arg.equals("stopall")) {
				for(Client clients : channel.getClients()) {
					clients.send(PacketCreator.playMp3("-"));
				}
                        } else if(arg.equals("toggle")) {
                            if(client.getSoundDisabled()) {
                                client.sendButlerMessage(channel.getName(), "Die Sounds wurden _eingeschaltet_. Wenn du keine Musik hören möchtest, gebe °>/play toggle|/play toggle<° ein.");
                            } else {
                                client.sendButlerMessage(channel.getName(), "Die Sounds wurden _ausgeschaltet_. Wenn du wieder Musik hören möchtest, gebe °>/play toggle|/play toggle<° ein.");
                            }
                            client.send(PacketCreator.playMp3("-"));
                            client.setSoundDisabled();
                        } else if(arg.equals("stop")) {
                            client.send(PacketCreator.playMp3("-"));
			} else if(data.length < 2) {
				client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/play TITLE:FILE");
                        } else if(arg.contains("list:")) {
                            final String id               = arg.replace("list:", "");
                            PoolConnection pcon     = ConnectionPool.getConnection();
                            PreparedStatement ps    = null;
                            String temp_name = "";
                            
                            // Fetch Main-Playlist
                            try {
                                Connection con = pcon.connect();
                                ps = con.prepareStatement("SELECT * FROM `playlist` WHERE `id`=? LIMIT 1");
                                ps.setString(1, id);
                                ResultSet rs = ps.executeQuery();
                                
                                while(rs.next()) {
                                    temp_name = rs.getString("album");
                                }
                           } catch (SQLException e) {
                               e.printStackTrace();
                           } finally {
                                if (ps != null) {
                                    try {
                                        ps.close();
                                    } catch (SQLException e) {
                                        /* Do Nothing */ 
                            /*        }
                                }
                                
                                pcon.close();
                           }
                            
                            final String playlist_name = temp_name;
                           
                           if(playlist_name.isEmpty()) {
                               client.sendButlerMessage(channel.getName(), "Die Playlist existiert nicht.");
                           } else {
                               String titles = "";
                               final LinkedHashMap<String, String> play_queue = new LinkedHashMap<String, String>();
                               // Fetch Tracks of Playlist
                               try {
                                    Connection con = pcon.connect();
                                    ps = con.prepareStatement("SELECT * FROM `playlist_files` WHERE `playlist`=?");
                                    ps.setString(1, id);
                                    ResultSet rs = ps.executeQuery();

                                    while(rs.next()) {
                                        titles += rs.getString("title") + ", ";
                                        play_queue.put(rs.getString("title"), rs.getString("file_url") + "~" + rs.getString("length"));
                                    }
                               } catch (SQLException e) {
                                   e.printStackTrace();
                               } finally {
                                    if(ps != null) {
                                        try {
                                            ps.close();
                                        } catch (SQLException e) {
                                            /* Do Nothing */
                                  /*      }
                                    }

                                    pcon.close();
                               }

                               client.sendButlerMessage(channel.getName(), "Die Playlist _" + playlist_name + "_ wurde gestartet. Folgende Titel werden nacheinander abgespielt:#" + titles);
                               channel.broadcastAction(client.getName(), " °>" + client.getName() + "|/w \"<° hat eine neue Playlist gestartet. Ab sofort läuft das Album _" + playlist_name + "_°r°§ (Keine Lust auf Musik? °R10>cancel.png<>--<>Musik bis zum nächsten Login stoppen|/play toggle<°°r°§).");
                               
                               new Thread() {
                                   @Override
                                   public void run() {
                                       for(String entry : play_queue.keySet()) {
                                           String play_title   = entry;
                                           String[] data       = play_queue.get(entry).split("~");
                                           String play_file    = data[0];
                                           int play_length     = Integer.parseInt(data[1]);
                                           
                                           channel.broadcastAction(client.getName(), " Es läuft gerade _(" + playlist_name + ") " + play_title + "_°r°§ (°R10>cancel.png<>--<>Musik stoppen|/play stop<°°r°§).");
				
                                           for(Client clients : channel.getClients()) {
                                                if(!clients.getSoundDisabled()) {
                                                    clients.send(PacketCreator.playSound("http://bobby.flirtchat10.de/playlists/" + id + "/" + play_file));
                                                }
                                           }
                                            
                                           try {
                                                this.sleep(play_length * 0x3E8);
                                           } catch (InterruptedException ex) {
                                                ex.printStackTrace();
                                           }
                                       }
                                       
                                       channel.broadcastAction(client.getName(), " Die Playlist _" + playlist_name + "_ wurde erfolgreich beendet.°r°§");
                                   }  
                               }.start();
                           }
                        } else {
				String title		= (data[0] != null ? data[0] : "");
				StringBuilder sound = new StringBuilder();
				int i				= 0;
				for(String e : data) {
					if(i > 0) {
						sound.append(e + (i < data.length - 1 ? ":" : ""));
					}
					i++;
				}
				
				channel.broadcastAction(client.getName(), " °>" + client.getName() + "|/w \"<° lässt gerade _" + title + "_ abspielen°r°§ (°R10>cancel.png<>--<>Musik stoppen|/play stop<°°r°§).");
				
				for(Client clients : channel.getClients()) {
                                    if(!clients.getSoundDisabled()) {
					clients.send(PacketCreator.playSound(sound.toString()));
                                    }
				}
			}
		} else if(arg.equals("stop")) {
                        client.send(PacketCreator.playSound("-"));
                } else if(arg.equals("toggle")) {
                    if(client.getSoundDisabled()) {
                        client.sendButlerMessage(channel.getName(), "Die Sounds wurden _eingeschaltet_. Wenn du keine Musik hören möchtest, gebe °>/play toggle|/play toggle<° ein.");
                    } else {
                        client.sendButlerMessage(channel.getName(), "Die Sounds wurden _ausgeschaltet_. Wenn du wieder Musik hören möchtest, gebe °>/play toggle|/play toggle<° ein.");
                    }
                    client.send(PacketCreator.playSound("-"));
                    client.setSoundDisabled();
                } else {
			client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir leider nicht zur verfügung.");
		}
	}
}
*/
package funktionen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintStream;
import java.text.*;
import java.util.*;
import starlight.*;
import java.util.regex.*;
import tools.*;
import tools.popup.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;




public class top {
    
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

    
    public static void functionMake(Client client,Channel channel, String arg) {
  if(!client.hasPermission("cmd.top")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	arg = arg.toLowerCase();
        	
        	if(arg.equals("age")) {
        		StringBuilder top = new StringBuilder();
        		StringBuilder nicks = new StringBuilder();
        		ArrayList<String> unsortiert = new ArrayList<String>(); 
        		int clientsWithoutButler = channel.countClients()-1;
        		String title = String.format("Top-%s-age", clientsWithoutButler);
        		
        		int lala = 0;
        		int lala2 = 1;
        		float male = 0;
        		float female = 0;
        		
        		for(Client x : channel.getClients()) {
        			if(x != Server.get().getButler()) {
        				lala+=x.getAge();
                                        
                                  
                                        
                                        
        			
                                        
                                          
                                        
                                        
                                        unsortiert.add(String.format("%s~°>_h%s|/w %s<°~%s", x.getAge(), x.getName(), x.getName(), x.getGender()));
        				
        				if(x.getGender() == 2) {
        					female++;
        				} else if(x.getGender() == 1) {
        					male++;
        				}
        			}
        		}
        		
        		Collections.sort(unsortiert);
        		
        		female = female/clientsWithoutButler*100;
        		male = male/clientsWithoutButler*100;
        		
        		top.append("Durchschnitt: ").append(lala/clientsWithoutButler).append(" Jahre, ").append(df.format(female).replace(",00", "")).append("% °>female.png<°  ").append(df.format(male).replace(",00", "")).append("% °>male.png<°#");
        		
        		for(String x : unsortiert) {
        			String[] split = x.split("~");
        			nicks.append("#").append(lala2).append(". ").append(split[1]).append(" ");
        			int gender = Integer.parseInt(split[2]);
        			
        			if(gender == 1) {
        				nicks.append("°>male.png<°");
        			} else if(gender == 2) {
        				nicks.append("°>female.png<°");
        			}
        			
    				nicks.append(" (").append(split[0]).append(")");
    				lala2++;
        		}
        		
                        
        		top.append(nicks);
        		
                         Popup popup = new Popup(title, title, top.toString(), 350, 250);
        	         Panel panel = new Panel();
                         Button buttonMessage3 = new Button("   OK   ");
                         buttonMessage3.setStyled(true);
                         panel.addComponent(buttonMessage3);
                         popup.addPanel(panel);
                         popup.setModern(1);
                         client.send(popup.toString());
                         return;
                        
        		
        	}
        	
        	if(arg.equals("online")) {
        		StringBuilder top = new StringBuilder();
        		StringBuilder nicks = new StringBuilder();
        		int clientsWithoutButler = channel.countClients()-1;
        		String title = String.format("Top-%s-online", clientsWithoutButler);
        		int lala = 0;
        		int lala2 = 1;
        		
        		for(Client x : channel.getClients()) {
        			if(x != Server.get().getButler()) {
        				lala+=x.getOnlineTime();
        				nicks.append("#").append(lala2).append(". °>_h").append(x.getName()).append("|/w ").append(x.getName()).append("<°").append(" ");
        				
        				lala2++;
        				
        				nicks.append(" (").append(nf.format(x.getOnlineTime()/60)).append(")");
        			}
        		}
        		
        		
        		top.append("Durchschnitt: ").append(nf.format((lala/clientsWithoutButler)/60)).append(" Minuten#").append(nicks);
        		
        		 Popup popup = new Popup(title, title, top.toString(), 350, 250);
        	         Panel panel = new Panel();
                         Button buttonMessage3 = new Button("   OK   ");
                         buttonMessage3.setStyled(true);
                         panel.addComponent(buttonMessage3);
                         popup.addPanel(panel);
                         popup.setModern(1);
                         client.send(popup.toString());
                         return;
        	}
        	
        	if(Server.toplisten.containsKey(arg)) {
        		String[] split = Server.toplisten.get(arg);
        		String name = split[0];
        		String text = split[1];
        		String word = arg;
        		
                int what, zahl = 1;
                boolean showPoints = true;
                
                if(word.equals("wordmix")) {
                    what = 100;
                } else {
                    if(word.equals("quasselig") || word.equals("knuddelig")) {
                        showPoints = false;
                    }
                    
                    what = 0;
                }
                
                StringBuilder top = new StringBuilder();
                top.append(text);
                StringBuilder nicks = new StringBuilder();
                
        		PoolConnection pcon = ConnectionPool.getConnection();
                PreparedStatement ps = null;

                try {
                    Connection con = pcon.connect();
                    ps = con.prepareStatement(String.format("SELECT  name, %s from accounts where name != '%s' and %s > '%s' order by %s desc limit 30", name, Server.get().getButler().getName(), name, what, name));
                    ResultSet rs = ps.executeQuery();
                        
                        while(rs.next()) {
                        	String nam = rs.getString("name");
                        	int x = rs.getInt(name);
                            nicks.append("#").append(zahl).append(". ").append(nam).append(" ");
                            
                            if(showPoints){   
                            	nicks.append("(");
                            	
                            	if(word.startsWith("online ")) {
                            		nicks.append(x/60);
                            	} else {
                            		nicks.append(x);
                            	}
                            	
                            	nicks.append(")");
                            }
                            
                            zahl++;
                        }
                        
                        top.append("#").append(nicks.toString());
                        
                         Popup popup = new Popup(String.format("Top-%s-%s", zahl-1, word), String.format("Top-%s-%s", zahl-1, word), top.toString(), 400, 250);
        	         Panel panel = new Panel();
                         Button buttonMessage3 = new Button("   OK   ");
                         buttonMessage3.setStyled(true);
                         panel.addComponent(buttonMessage3);
                         popup.addPanel(panel);
                         popup.setModern(1);
                         client.send(popup.toString());
                         return;
                        
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
        		
        		return;
        	}
        	
        	StringBuilder toplisten = new StringBuilder();
            
            for(String top : Server.toplisten.keySet()) {
            	toplisten.append(", ").append(top);
            }
            
            client.sendButlerMessage(channel.getName(), String.format("%sEs gibt folgende Listen: Age, Online%s", arg.isEmpty() ? "" : String.format("Die Topliste %s gibt's hier leider nicht. ", arg), toplisten.toString()));
      
        
    }
}

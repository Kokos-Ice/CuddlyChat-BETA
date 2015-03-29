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

import blitz.*;
import fifty.*;
import fifty.free.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import starlight.Channel;
import starlight.Client;

import tools.Source;
import tools.KCodeParser;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;

public class Butler {

	private static String[] noCalculate = {"Ich glaube, die Mathematik ist nicht ganz Ihr Metier.", "Ich kenne da so einen Mathematikstudenten... Vielleicht kann der Ihnen ja weiterhelfen, ich jedenfalls nicht.", "Bei aller Liebe, Deine Mathehausaufgaben musst Du schon selber machen!", "Frag doch mal deinen alten Mathelehrer.", "Das ergibt keinen Sinn und schon gar kein Ergebnis.", "Hat ein des Rechnens unkundiger Mensch Anrecht auf Glück, beim Fund eines vierblättrigen Kleeblattes?", "°>_h%s|/serverpp \"|/w \"<°, Du solltest dich lieber nicht mit Mathematik beschäftigen.", "°>_h%s|/serverpp \"|/w \"<°, ich denke, das Ergebnis liegt auf der Hand.", "Pardon, aber das übersteigt leider all meine Rechenkünste.", "Mein Taschenrechner ist bei der Rechnung abgestürzt!", "Rechne doch selbst.", "So etwas rechne ich nicht."};
	private static String[] calculate = {"Platon, Thales und Euklid würden mir da sicher zustimmen, wenn ich Ihnen sage, dass das Ergebnis %s ist.", "Wenn ich mich nicht irre, macht das genau %s.", "%s!", "%s ist das Ergebnis.", "Ich komme auf %s.", "Mathematik ist mein Steckenpferd. Das Ergebnis lautet %s.", "Das ist doch leicht: %s!", "Ich habe von Archimedes und Aristoteles gelernt, dass dabei nur %s herauskommen kann!", "Uno Momento... es kommt genau %s heraus.", "Laut meines Taschenrechners ist das %s.", "Das macht nach Adam Riese genau %s.", "Kopfrechnen ist meine Stärke. Das Ergebnis ist %s.", "Das macht %s."};
	public static String[] butlerT = {"Was geht ab?", "Paranoia?", "Ich würde damit mal zum Arzt gehen!", "Hast Du kein eigenes Zuhause?", "Meintest du mich?", "Mir ist total langweilig!", "kennst du einen guten Film?", "Kann ich Dir helfen, °>_h%s|/serverpp \"|/w \"<°?", "So nennt man mich wohl.", "Was ist?", "Ich hab gerade keine Zeit für dich", "°>_h%s|/serverpp \"|/w \"<°, Du nervst.", "°>_h%s|/serverpp \"|/w \"<°, was willst du eigentlich von mir?", "Heute ist nicht mein Tag!", "Jaaaa?", "lasst mich einfach heute alle in Ruhe.", "°>_h%s|/serverpp \"|/w \"<°?", "°>_h%s|/serverpp \"|/w \"<°!", "Bereit.", "Das bin ich.", "Yolooo was los?", "Schwärmst du von mir, °>_h%s|/serverpp \"|/w \"<°?"};
	
	public static String getXml(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        InputStream is = null;
        connection.setUseCaches(false);
        
        try {
        	is = connection.getInputStream();
        } catch (IOException e) {
        }

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        StringBuilder sb = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
        	sb.append(line);
        }
        
        reader.close();
        return sb.toString();
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static void parse(Client client, Channel channel, String message) {
          
            
       /* DAS SORGT dafür das james net mehr reagiert, sonder nur auf james mix, james wordmix usw */
            
           
          
              
              if (channel.getName().startsWith("WordMix")) {
           return;  
          }
              
              if (channel.getName().startsWith("WordMix Newby")) {
           return;  
          }
                  
              if (channel.getName().startsWith("WordMix Family")) {
           return;  
          }
              
              if (channel.getName().startsWith("WordMix English")) {
           return;  
          }
                
              if (channel.getName().startsWith("WordMix Oldies")) {
           return;  
          }
              
         //      if (channel.getName().startsWith("Quiz")) {
          // return;  
          //} 
               
               if (channel.getName().startsWith("Quiz Solo")) {
           return;  
          } 
               
               if (channel.getName().startsWith("Mix")) {
           return;  
          } 
              
               if (channel.getName().startsWith("Translate")) {
           return;  
          } 
                
               if (channel.getName().startsWith("Mathe")) {
           return;  
          } 
               
          //     if (channel.getName().startsWith("Blitz!")) {
           //return;  
         // } 
           
         //      if (channel.getName().startsWith("Blitz! Free")) {
        //   return;  
        //  } 
                 
         //      if (channel.getName().startsWith("Fifty!")) {
         //  return;  
         // } 
                    
          //     if (channel.getName().startsWith("Fifty! Free")) {
          // return;  
         // } 
               
             
               
             /* ENDE */
            
            
		String butler = Server.get().getButler().getName();
		String msg = message.toLowerCase();
		Random zufall = new Random();
		long time = System.currentTimeMillis()/1000;
        String num = msg.replace(butler.toLowerCase(), "").replace("°RR18°", "").replace("°BB18°", "").replace("rechne", "").trim();
        String numTV = msg.toLowerCase().replace(butler.toLowerCase(), "").replace("°RR18°", "").replace("°BB18°", "").replace("tv", "").replace("kabel1","k1").replace("kabel 1", "k1").trim().toLowerCase();
        String numCity = msg.replace(butler.toLowerCase(), "").replace("wetter", "").trim().replace(" ", "%20");
        
        String noCalculateText = String.format(noCalculate[zufall.nextInt(noCalculate.length)], client.getName());
        String butlerText = String.format(butlerT[zufall.nextInt(butlerT.length)], client.getName());
       
            
        
        if (msg.contains("blitz")) {         
              blitz.start(Server.get().getButler().getName()+" "+message, client, channel);
            return;
        }
          
            if (msg.contains("fifty")) {
         
              fiftyfree.start(Server.get().getButler().getName()+" "+message, client, channel);
            return;
        }
            
           
        if(msg.contains("rechne")) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
   
            try {
            	Random random = new Random();
                Object object = engine.eval(String.format("eval(%s)", num.replace(",", ".")));
        
                if ((object != null) && (object instanceof Number)) {
                    String number = "";
                    double value = ((Number)(object)).doubleValue();
                    
                    if(value == 1.0) number = "eins";
                    else if(value == 2.0) number = "zwei";
                    else if(value == 3.0) number = "drei";
                    else if(value == 4.0) number = "vier";
                    else if(value == 5.0) number = "fünf";
                    else if(value == 6.0) number = "sechs";
                    else if(value == 7.0) number = "sieben";
                    else if(value == 8.0) number = "acht";
                    else if(value == 9.0) number = "neun";
                    else if(value == 10.0) number = "zehn";
                    else if(value == 11.0) number = "elf";
                    else if(value == 12.0) number = "zwölf";
                    else number = String.valueOf(value);
                    
                    channel.broadcastButlerMessage(String.format(calculate[zufall.nextInt(calculate.length)], number));
                } else {
                    channel.broadcastButlerMessage(noCalculateText);
                }
            } catch (ScriptException e) {
                channel.broadcastButlerMessage(noCalculateText);
            }
            
            
          
            
        } else if(msg.contains("tv")) {
        	if(numTV.isEmpty()) {
        		channel.broadcastButlerMessage(String.format("Von welchem Sender möchtest du die Programmübersicht wissen, %s?", client.getName()));
        		return;
        	}
        	
        	int kind = 0;
        	
        	for(Client c : channel.getClients()) {
        		if(c.getAge() < 16) {
        			kind++;
        		}
        	}
        	
        	if(msg.contains("butv") || msg.contains("pboy")) {
        		if(kind > 0) {
        			channel.broadcastButlerMessage("Aufgrund der _°BB>Jugendschutzbestimmungen|/h jugendschutz<r°_ kann die Programmübersicht dieses Senders nicht bekanntgegeben werden.");
        			return;
        		}
        	}

    		try {
    			String source = Source.get(String.format("http://www.tvtoday.de/programm/?format=list&offset=0&date=today&slotIndex=now&genre=&channel=%s", numTV));
    			String[] split = source.split(" Min.");
    			int actual = 0;
    			boolean getSender = false;
    			StringBuilder tv = new StringBuilder();
    		
    			for(String infos : split) {
    				if(actual != split.length - 1) {
    					int length = infos.length();
    					String dauer = infos.substring(length-2, length).trim();
    					String sendung = infos.substring(infos.indexOf("titel\">")+7).split("<")[0].replace("Ã¼", "ü").replace("Ã¤", "ä").replace("ÃŸ", "ß").replace("Ã©", "é").replace("Ã–", "Ö").replace("Ã¶", "ö");
    					String uhrzeiten = infos.substring(infos.indexOf("uhrzeit\">")+9).split("</span>")[0].replace("<br/>", " ").replace(".", ":");
    				
    					if(!getSender) {
    						getSender = true;
    						tv.append("°>tvchannels/").append(numTV.toLowerCase()).append("...b.h_-0.b_0.mx_-0.my_15.png<°°%-4°Programmübersicht für den _Sender ").append(infos.substring(infos.indexOf("channelId=")+10).split("&")[0]).append("_:#");
    					}
    				
    					tv.append("#°>py_").append(actual == 0 ? "g":"b").append(".gif<° ").append(uhrzeiten).append(" Uhr (").append(dauer).append(" min): _").append(sendung).append("_");
    				}
    			
    				actual++;
    			}
    			
    			String tvString = tv.toString();
    			
    			if(tvString.isEmpty()) {
    				channel.broadcastButlerMessage(String.format("Für den Sender _'%s'_ wurde _keine Programmübersicht gefunden_.", KCodeParser.noKCode(numTV)));
    				return;
    			}
    			
            	channel.broadcastButlerMessage(tvString);
    		} catch(Exception ex) {
            	channel.broadcastButlerMessage(String.format("Für den Sender _'%s'_ wurde _keine Programmübersicht gefunden_.", KCodeParser.noKCode(numTV)));
    		}
        } else if(msg.contains("wetter") && !numCity.isEmpty()) {
        	StringBuilder w = new StringBuilder();
        	String weather;
			try {
				weather = getXml(String.format("http://www.google.com/ig/api?weather=%s&hl=de", numCity));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
    		int i = 1;
    		
    		for(String x : weather.split("day_of_week data=\"")) {
    			if(i == 1) {
    				String city = x.substring(x.indexOf("city data=\"")+11).split("\"")[0];
    				
    				if(city.startsWith("ion=")) {
    					channel.broadcastButlerMessage("Der Ort für die Wettervorhersage wurde _nicht gefunden_.");
    					return;
    				}
    				
    				String temp_c = x.substring(x.indexOf("temp_c data=\"")+13).split("\"")[0];
    				String icon = x.substring(x.indexOf("/ig/images/")+11).split("\"")[0];
    				w.append("Wettervorhersage für _").append(city
    						.replace("Rhineland-Palatinate", "Rheinland-Pfalz")
    						.replace("North Rhine-Westphalia", "Nordrhein-Westfalen")
    						.replace("Lower Saxony", "Niedersachsen")
    						).append("_:##");
    				w.append("°>{table|188|188}<°°>{tr}<°°>{tc}<°°20BB<°_");
    				
    				w.append(temp_c).append("\\°_#°r>");
    				w.append(icon.replace(".", "...border_1.")).append("<°#°>left<°°>{tc}<°°>{tr}<°°>{endtable}<°##°>{table|15|100|15|100|15|100|15|400}<°°>{tr}<°");
    			} else {
    				String day = x.split("\"")[0];
    				String low = x.substring(x.indexOf("low data=\"")+10).split("\"")[0];
    				String high = x.substring(x.indexOf("high data=\"")+11).split("\"")[0];
    				String icon = x.substring(x.indexOf("/ig/images/")+11).split("\"")[0];
    				
    				w.append("°>{tc}<°_  ").append(day).append("_#°>");
    				w.append(icon.replace(".", "...border_1.")).append("<°#°12°");
    				w.append(low).append("\\°/");
    				w.append(high).append("\\°°>{tc}<r°");
    			}
    			
    			i++;
    		}
    		
    		if(!w.toString().isEmpty()) {
    			w.append("°>{tr}<°°>{endtable}<°");
    			channel.broadcastButlerMessage(w.toString());
    		}
        } else {
        	for(String word : Server.james.keySet()) {
        		if(msg.contains(word)) {
        			String text = Server.james.get(word);
        			List<String> texte = new ArrayList<String>();
        			
                    String[] days = { "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
                    
                    text = text.replace("{FROM_NICK}", client.getName());
            		text = text.replace("{YEAR}", new SimpleDateFormat("yyyy").format(new Date()));
            		text = text.replace("{DAY}", new SimpleDateFormat("dd").format(new Date()));
            		text = text.replace("{MONTH}", new SimpleDateFormat("MM").format(new Date()));
            		text = text.replace("{SECONDS}", new SimpleDateFormat("ss").format(new Date()));
            		text = text.replace("{MINUTES}", new SimpleDateFormat("mm").format(new Date()));
            		text = text.replace("{HOURS}", new SimpleDateFormat("HH").format(new Date()));
            		text = text.replace("{MILISECONDS}", new SimpleDateFormat("S").format(new Date()));
            		text = text.replace("{WEEKDAY}", days[new Date().getDay()]);
            		
            		for(String t : text.split("\\|")) {
            			if(!t.isEmpty()) {
            				texte.add(t);
            			}
            		}
            		
            		Random random = new Random();
            		String actualText = texte.get(random.nextInt(texte.size()));
            		String[] split = actualText.split("~");
            		String endText = split[0];
            		int doText = Integer.parseInt(split[1]);
            		
            		if(doText == 1) {
            			channel.broadcastAction(butler, endText);
            		} else {
            			channel.broadcastButlerMessage(endText);
            		}
            		
                        
        			return;
        		}
        	}
        	
        	channel.broadcastButlerMessage(String.format(butlerText, client.getName()));
        }
    }
}
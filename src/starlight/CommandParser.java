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
import blitz.blitz;
import static features.hero.timeStampToDate;
import funktionen.mute;
import tools.PacketWriter;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.KCodeParser;
import game.WordMixRecord;
import fifty.fiftyanmeldung;
import fifty.fiftywuerfeln;
import funktionen.*;
import features.*;
import game.Darten;
import game.Dicen;
import game.Freidiffen;
import handler.CodeHandler;
import tools.DiceCreator;
import tools.IntegerUtil;
import tools.Logger;
import tools.PacketCreator;
import tools.Pair;
import tools.Password;
import tools.VoteBox;
import tools.popup.Button;
import tools.popup.Label;
import tools.popup.Choice;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;
import tools.popup.TextArea;
import tools.popup.TextField;
public class CommandParser {

    static Socket socket;
    static DataInputStream in;
    public static PrintStream out;
    private static Random zufall = new Random();
    private static NumberFormat nf;
    private static DecimalFormat df;
    private static String[] afk = {"verschwindet für einen kurzen Moment.", "ist gleich wieder da."};
    private static String[] unknownUser = {"Wer soll denn bitte %s sein?", "%s ist bestimmt dein Bester Freund, nicht wahr?", "%s hast Du dir nur eingebildet!", "Keine Ahnung, wen Du meinst.", "Wer ist %s???", "Du kuckst wohl zu viele Horror Filme mh?.", "%s gibt es nur in deinen Träumen!.", "Wer zum Teufel ist %s?"};
    private static String[] noKnuddels = {"Willst Du mich eigentlich verarschen?, Du hast doch gar keine Knuddels!", "Du Träumer, knuddeln ohne Knuddels. Dass erzähl mal deinen Psychiater!", "Wer arbeiten geht, der kann auch knuddeln.", "Pech gehabt! Letztes mal hast du mir nicht mal einen Knuddel gegeben, und jetzt hast du gar keine Knuddels mehr! ;)", "Ich bin zwar erst 15, aber in Mathematik bist du wohl nicht auf meiner Höhe!", "Auf welcher Schule warst du nochmal? Es muss die Sonderschule gewesen sein."};
    private static String[] help = {"%s ist ein sehr gefährlicher Tag.", "Ja, mit %s ist das genau wie mit dem Yeti...", "Mit %s sollte man es aber nicht übertreiben...", "Was ist %s?", "%s ist mir unbekannt."};
    private static String[] messageSent = {"Ich gebe das mal so an _[N] weiter_.","Ich hab's mal an [N] weitergegeben .","Bin ich hier etwa das Mädchen für alles?, Schon gut... [N] wird schon informiert.","Sobald ich [N] wiedersehe, werde ich es ausrichten.","[N] wird es bald von mir erfahren.","Hoffentlich freut sich [N] über die Nachricht.","Ich werde es [N] ausrichten.","[N] wird es erfahren, sobald ich etwas mehr Zeit habe.","Wie interessant, ich werde es [N] ausrichten.","Ich werde es schon nicht vergessen und [N] schnellstmöglich ausrichten."};
    public static  String[] cheersbier = {"_[C]_ hat heute die Spendierhosen an und lädt °BB°_[T]_ °r°auf ein kühles Blondes ein.", "_[C]_ hat gerade extrem gute Laune, gibt eine Runde Bier aus und startet mit °BB°_[T]_ °r°eine Polonaise.", "_[C]_ gibt eine Runde Bier für °BB°_[T]_ °r°aus: ''Und zisch, und klack, und weg!''"};
    public static  String[] cheerssekt = {" _[C]_ lässt heute mal so richtig die Korken knallen und schenkt °BB°_[T]_ °r°prickelnden Sekt ein.", "_[C]_ öffnet etwas angeheitert eine Flasche Champagner und schenkt °BB°_[T]_°r°, ohne über die Kosten nachzudenken, ordentlich was ein...", "_[C]_ drückt °BB°_[T]_ °r°einen schmackhaften Cocktailsekt in die Hand und ruft laut:''PROST!''"};
    public static  String[] cheerscola = {"_[C]_ öffnet eine Flasche Cola und schenkt °BB°_[T]_ °r°bereitwillig ein.", " _[C]_ ist sich nicht sicher, ob alle Anwesenden wirklich schon Alkohol trinken dürfen, und gibt daher lieber eine Runde Cola für °BB°_[T]_ °r°aus.", "_[C]_ findet, dass °BB°_[T]_ °r°eine Erfrischung gerade ganz gut tun würde, und spendiert eine Runde eiskalte Cola."};
    public static  String[] highfive = {"[T] schlägt in die Hand von [T] ein!"};
    public static  String[] wash = {"_[C]_ konnte den [E]-Effekt von _[T]_ nicht mehr mit ansehen und hat den Nick mit einer kräftigen Wasserdusche abgewaschen.", "_[C]_ tut etwas gegen den Augenkrebs, den der [E]-Effekt von [T] bei einigen Anwesenden verursachen könnte, und wäscht diesen mit viel Seife und Wasser schleunigst ab.","_[C]_ findet dass _[T]_ mit dem [E]-Effekt viel zu dick aufträgt, und wäscht diesen daher mit desinfizierendem WC-Reiniger einfach ab.","_[C]_ ist der Meinung, dass _[T]_ mit dem [E]-Effekt nur Aufmerksamkeit erregen will, und erteilt _[T]_ eine Lektion. Mit viel Wasser und etwas Spucke verschwindet der Effekt ganz schnell."};
    public static  String[] washfailed = {"Da wollte _[C]_ doch tatsächlich den [E]-Effekt von _[T]_ abwaschen... - Dieser Plan ist jedoch nicht aufgegangen.", "Der perfide Plan von _[C]_, den [E]-Effekt von _[T]_ abzuwaschen, ist kläglich gescheitert."};
    public static  String[] partnerlook = {"Heute geht's rund! [C] und [T] waren scheinbar gemeinsam shoppen. _Den feschen Partnerlook_ erkennt man sofort.", "[C], [T] um den _neuen Partnerlook_ beneide ich euch. Jameline möchte nie die Klamotten tragen, die ich trage.", "[C] und [T] tragen beide Blue Jeans. _Im neuen Partnerlook_ kleben sie scheinbar fast aneinander.", "Der Unterschied zwischen [C] und [T] ? Mit dem _neuen Partnerlook_ gibt es scheinbar keinen mehr."};
    public static  String[] coin = {"Heute möchte [C] nicht selbst nachdenken und lässt die Münze entscheiden...", "Kopf, ich gewinne. Zahl, du verlierst.., denkt [C] während die Münze fliegt..."};
    

    
    //   public static String[] clink = {""};
    
    
    
    public static int countChars(String text, char c) {
    
        
        
        
        
        int counter = 0;
       
       for (int i=0;i<text.length();i++) {
            if (text.charAt(i) == c) {
            	counter++;
            }
       }

       return counter;
    }
    
    static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }

    public static String unknownUser(String nick) {
        return String.format(unknownUser[zufall.nextInt(unknownUser.length)], nick);
    }
    
    public static String messageSent(String nick) {
        return messageSent[zufall.nextInt(messageSent.length)].replace("[N]",String.format("°>_h%s|/serverpp \"|/w \"<°", nick.replace("<","\\<")));
    }
    
    public static void highOrLowWrong(Client client, Channel channel, int runde, int points, int zahl) {
		client.sendButlerMessage(channel.getName(), String.format("°RR°_Leider falsch!§ Die neue Zahl ist _°18RR>{textborder}<°%s§.#Du hast es bis zur _%s. Runde_ geschafft (Gewinn: _%s_ Punkte%s).", zahl, client.getHighOrLowRound(), points, client.getHolknuddelsrunde()==1?String.format(" und °BB°_%s Knuddels§", runde>1?runde:0):""));
		
		if(channel.getHolrunde() < runde) {
			channel.increaseHoljackpot(1);
			
			channel.broadcastButlerMessage(new StringBuilder().append("°%-1>firework...b.mx_-9.w_52.h_40.gif<° °RR°_").append(client.getName()).append(" hat einen neuen High or Low Rekord aufgestellt! °>firework...b.mx_-9.w_52.h_40.gif<#°").append(client.getGenderLabel()).append(" hat es bis Runde ").append(runde).append(" geschafft. Im Jackpot befinden sich ").append(channel.getHoljackpot()).append(" Knuddels.").toString());
			channel.setTopic(String.format("In diesem Channel kann High or Low gespielt werden. °>gt.gif<° °BB>Jetzt mitspielen!|/hol start<r°##%s hält den momentanen Rekord mit Runde %s (Jackpot: %s Knuddels).", client.getName(), runde, channel.getHoljackpot()));

			if(!channel.getHolnick().equals(client.getName())) {
				Server.get().newMessage(Server.get().getButler().getName(), channel.getHolnick(), "High or Low Rekord geknackt", String.format("Dein High or Low Rekord wurde soeben von %s mit %s Runden geknacht.", client.getName(), runde), System.currentTimeMillis()/1000);
			}
			
			channel.setHolrunde(runde);
			channel.setHolnick(client.getName());
		}
		
		client.setHighOrLowRound((byte)0);
		client.increaseHol(points);

		if(client.getHolknuddelsrunde()==1 && runde >1) {
			client.increaseKnuddels(runde);
			channel.increaseHoljackpot(1);
		}
		
		client.setHolknuddelsrunde((byte)0);
    }
    
    public static String userIsOffline(Client client) {
    	int seconds = (int) ((System.currentTimeMillis()/1000)-client.getLastOnline());
    	int days = seconds/86400;
    	int hours = seconds/3600;
    	int minutes = seconds/60;
    	String what;
    	
    	if(days > 1) {
    		what = String.format("%s Tagen", days);
    	} else if(days == 1) {
    		what = "einem Tag";
    	} else if(hours > 1) {
    		what = String.format("%s Stunden", hours);
    	} else if(hours == 1) {
    		what = "einer Stunde";
    	} else if(minutes > 1) {
    		what = String.format("%s Minuten", minutes);
    	} else if(minutes == 1) {
    		what = "einer Minute";
    	} else if(seconds > 1) {
    		what = String.format("%s Sekunden", seconds);
    	} else {
    		what = "einer Sekunde";
    	}
    	
        return String.format("°>_h%s|/serverpp \"|/w \"<° ist _seit %s offline_.", client.getName().replace("<", "\\<"), what); 
    }
    
    public static String image(Client client, Client target) {
        if(target.getGender() == 2 && client.getGender() == 2) return "ff";
        else if(target.getGender() == 2 && client.getGender() == 1) return "mf";
        else if(target.getGender() == 1 && client.getGender() == 2) return "fm";
        else return "mm";    
    }

    private static List<Pair<String, Integer>> icons;

    public static void addIcon(String icon, int size, Client nick, Channel channel) {
        Pair<String, Integer> pair = new Pair<String, Integer>(icon, size);

        icons = new ArrayList<Pair<String, Integer>>();
        icons.add(pair);
        
        if(channel == null) {
        	for (Channel channelz : nick.getChannels()) {
            	for (Client target : channelz.getClients()) {
            		if(target != Server.get().getButler()) {
            			target.send(PacketCreator.addIcon(channelz.getName(), nick.getName(), pair));
            		}
            	}
        	}
        } else {
            for (Client target : channel.getClients()) {
            	if(target != Server.get().getButler()) {
            		target.send(PacketCreator.addIcon(channel.getName(), nick.getName(), pair));
            	}
            }
        }
    }
    
    public static void removeIcon(String icon, String name, Channel channel) {
    	Client nick = Server.get().getClient(name);
    	
    	if(channel == null) {
    		for (Channel channelz : nick.getChannels()) {
    			for (Client target : channelz.getClients()) {
    				if(target != Server.get().getButler()) {
            			target.send(PacketCreator.removeIcon(channelz.getName(), nick.getName(), icon));
            		}
            	}
        	}
        } else {
    		for (Client target : channel.getClients()) {
    			if(target != Server.get().getButler() && nick != null) {
            		target.send(PacketCreator.removeIcon(channel.getName(), nick.getName(), icon));
            	}
        	}
        }
    }
    
    public static boolean muted(Client client, Channel channel) {
    	return Server.get().checkCcm(client.getName(), channel, 3);
    }
    
    public static boolean moderated(Client client, Channel channel) {
    	return channel.isModerated() && !channel.checkMod(client.getName()) && !channel.checkVip(client.getName()) && !client.hasPermission("eventwrite");
    }
    
    
    @SuppressWarnings({ "deprecation" })
	public static void parse(String message, Client client, Channel channel, boolean fromGame) {
        String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
        String cmd = command.toLowerCase();
        String arg = "";
        Long time = System.currentTimeMillis()/1000; 
        
        if (message.length() > cmd.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1);
        }

        int Anzahlspe = Server.countChars(arg, '°');
        
        if (Anzahlspe == 1) {
        	String[] argus = arg.split("°", 2);
        	arg = argus[0];
        }
               
             
             
             if(cmd.equals("diebspiel")) {

                 String[] l = client.getFeature("Diebspiel");
                 Feature ft = Server.get().getFeature("Diebspiel");

                if (ft == null) {
                    return;
                }

                if (l[0].equals("0")) {
                      client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
                return;  
                } 
                if (l[0].equals("1")) {
                      client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
               return;
                  } 
               
        	
        	Popup popup = new Popup("Diebspiel", null, String.format("°>{imageboxstart}boxS.my_-4.mh_30<°#°20+0015+9515°_Diebspiel°b° °12+0000°##°+5040°°+9505°°+0020°Punkte: %s##°>%sthiefgame?id=%s|\"<°##°>{imageboxend}<°##°>LEFT<°##°>center<°°16>{button}OK||call|_c|width|70|heigth|28<°", client.getThiefGame(), Server.get().getURL(), client.getName()), 450, 300);
        	popup.setDesign(2);
        	client.send(popup.toString());
       
        	
    } else if(cmd.equals("like")) {
           
                String[] l = client.getFeature("Like");
               Feature ft = Server.get().getFeature("Like");

               if (ft == null) {
                   return;
               }

               if (l[0].equals("0")) {
                     client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
               return;  
               } 
               if (l[0].equals("1")) {
                     client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
              return;
                 } 
    
        	
        	arg = KCodeParser.noKCode(arg);
        	int argLength = arg.length();
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/like TEXT oder !#(TEXT mögen oder TEXT aus dem Profil löschen.)");
        		return;
        	}
        	
        	if(arg.equals("!")) {
        		client.sendButlerMessage(channel.getName(), "Profileintrag gelöscht.");
        		client.setLike(null);
        		return;
        	}
        	
        	if(argLength > 50) {
        		client.sendButlerMessage(channel.getName(), "Der Text darf höchstens 50 Zeichen lang sein.");
        		return;
        	}
        	
        	arg = Server.get().parseSmileys(client, arg);
        	
        	channel.broadcastPicAction(">", String.format("%s mag °RR°_%s°r°_.", client.getName(), arg), "icons/like...h_10.w_38.my_1.png");
        	client.setLike(arg);
                ft.setBan(l[1],l[3],l[4],client);
       
        	    
        
        } else if(cmd.equals("ok")) {
        	if(!client.hasPermission("cmd.ok")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
                
                if(Server.get().getTausch(arg) != null) {
			String[] data = {"-", "swapstart", arg};
			CodeHandler.handle(data, client);
			return;
		}
        	
        	arg = KCodeParser.escape(arg);
        	
        	for(int x : Server.invite.keySet()) {
        		String[] split = Server.invite.get(x);
        		String an = split[0];
        		String vo = split[1];
        		Client von = Server.get().getClient(vo);
        		String design = split[2];
        		//String test = split[3];
        		int butler = Integer.parseInt(split[4]);
        		String name = split[5];
        		String topic = split[6];
        		boolean online = true;
        		
        		if(von == null) {
        			online = false;
        			von = new Client(null);
        			von.loadStats(vo);
        		}
        		
        		if(an.equals(client.getName())) {
        			if(!online) {
        				client.sendButlerMessage(channel.getName(), String.format("%s ist offline.", von.getName()));
        			} else {
        				String channelName = String.format("/Separee %s - %s", x, name);
        				Server.get().createChannel(channelName, 1, client.getName(), butler, design, topic);
        				Channel channelTo = Server.get().getChannel(channelName);

        				client.joinChannel(channelTo);
        	            channel.leave(client);
        	            client.leaveChannel(channel);
        	            client.send(PacketCreator.switchChannel(channel.getName(), channelTo.getName()));
        	            client.send(PacketCreator.updateChannelSettings(channelTo));
        	            client.send(PacketCreator.updateChannelBackground(channelTo));
        	            channelTo.join(client);    
        	            
        	            von.joinChannel(channelTo);
        	            von.send(PacketCreator.channelFrame(channelTo, von.getName(), von.newMessages.size()));
        	            channelTo.join(von);  
        			}
        			
        			channel.broadcastAction(">", String.format("%s und %s ziehen sich in ihre Privatgemächer zurück.", von.getName(), client.getName()));
        			
        			Server.invite.remove(x);
        			return;
        		}
        		
        		return;
        	}
        	
        	client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
        } else if(cmd.equals("nvote")) {
        	if(!client.hasPermission("cmd.nvote")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	if (arg.indexOf("|") == -1) { return; }
        	String[] split = arg.split("\\|");
        	Channel c = Server.get().getChannel(split[1]);
        	
        	if(c == null) {
        		return;
        	}
        	
        	StringBuilder vote = new StringBuilder();
        	vote.append("k\0");
        	vote.append("Befragung");
        	vote.append("õfÿÿÿh\0\0\0ãEl  õcgMh\0\0\0ãWl  õcgMh\0\0\0ãSl õcgFh\0\0\0ãNl õcgFh\0\0\0ãCpBNpBCl");
        	vote.append("Befragung");
        	vote.append("õblgSfÀÀÀh@@@ãSl õcgFh\0\0\0ããCc");
        	vote.append(split[0]);
        	vote.append("õs^\0dfÿÿÿh\0\0\0ãSpBNpFpGDBFFb");
        	int bla = 0;
        	for(String answer : channel.newVoteAnswers.keySet()) {
        		vote.append(answer);
        		vote.append("õcdba:fct:/sfc ");
        		vote.append(split[1]);
        		vote.append(":/fw ");
        		vote.append(answer);
            	vote.append("õgRfÿÿÿh\0\0\0ä");
            	vote.append(bla==channel.newVoteAnswers.size()-1?"ã":"b");
            	bla++;
        	}
        	
        	vote.append("ãCl õcgIh\0\0\0ãSpFbAbbrechenõcdbgRfÿÿÿh\0\0\0ããããã");
        	
        	// Ende: ã statt b
        	client.send(vote.toString());
       
       
        } else if(cmd.equals("invite")) {
        	if(!client.hasPermission("cmd.invite")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/invite NICK#(Mit dieser Funktion läd man NICK in ein Separee ein)");
        		return;
        	}	        
        	
        	Client target = Server.get().getClient(arg);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}
        	
        	if(target == Server.get().getButler() || target == client) {
        		client.sendButlerMessage(channel.getName(), String.format("%s kann nicht in ein Separee eingeladen werden.", target.getName()));
        		return;
        	}
        	
        	client.send(PacketCreator.createInviteWindow(target.getName(), "", String.format("%s & %s", target.getName(), client.getName())));
       
        
                
                } else if(cmd.equals("ok")) {
                
                
                    
                }  
              
    
            else if(cmd.equals("holdhands")) {
        	    if (arg.equals("okay")) {
        	    	for (long x : Server.actions.keySet()) {
                    	String[] split = Server.actions.get(x);
                    	Channel cha = Server.get().getChannel(split[3]);
                    	String typ = split[0];
                    	String v = split[1];
                    	Client von = Server.get().getClient(v);
                    	Client an = Server.get().getClient(split[2]);
                    	
                    	if(cha == channel && an == client && typ.equals("holdhands")) {
                    		channel.broadcastPicAction(">>", String.format("°>_h%s|/serverpp \"<° hält nun mit °>_h%s|/serverpp \"|/w \"<° Händchen!", von.getName().replace("<", "\\<"), client.getName().replace("<", "\\<")), String.format("actKizz_ani-bg...h_20.my_3.png<>icons/actKizz_%s_ani...h_20.mx_-40.w_0.my_3.gif<>transparent1x1...w_0.gif", image(von, client)));
                           
                    		if(von != null) {
                    			von.setHoldHands(String.format("%s|%s|", von.getHoldHands(), client.getName()));
                    		}
                    		
                    		an.setHoldHands(String.format("%s|%s|", an.getHoldHands(), v));
                    		
                    		Server.actions.remove(x);
                    		break;
                    	}
                    }
        	    	
        	    	return;
        	    }
      
                String[] l = client.getFeature("Holdhands");
               Feature ft = Server.get().getFeature("Holdhands");

               if (ft == null) {
                   return;
               }

               if (l[0].equals("0")) {
                     client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
               return;  
               } 
               if (l[0].equals("1")) {
                     client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
              return;
                 } 

        	    
            	if (arg.isEmpty()) {
            	      client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/holdhands NICK#(Mit NICK Händchen halten.)");
            	      return;
            	} 
            	
        	      String nicknamen = KCodeParser.escape(arg);
        	      boolean online = true;
        	      Client target = Server.get().getClient(nicknamen);
        	      if (target == null) {
        	        online = false;
        	        target = new Client(null);
        	        target.loadStats(nicknamen);
        	      }
        	      nicknamen = target.getName();

        	      if (arg.isEmpty()) {
        	        client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/holdhands NICK#(Mit NICK händchenhalten)");
        	        return;
        	      }

        	      if (nicknamen == null) {
        	        client.sendButlerMessage(channel.getName(), unknownUser(arg));
        	        return;
        	      }

        	      if (target.getName().equals(client.getName())) {
        	        client.sendButlerMessage(channel.getName(), "Du kannst doch nicht mit dir selbst Händchenhalten");
        	        return;
        	      }

        	      if (!online) {
        	        client.sendButlerMessage(channel.getName(), userIsOffline(target));
        	        return;
        	      }
        	      if (client.getHoldHands().contains(target.getName())) {
        	        client.sendButlerMessage(channel.getName(), "Mit " + target.getName() + " hast du bereits Händchen gehalten.");
        	        return;
        	      }
        	      if (target.getName().equals(Server.get().getButler().getName())) {
        	        client.sendButlerMessage(channel.getName(), "Mit " + Server.get().getButler().getName() + " kannst du nicht Händchenhalten.");
        	        return;
        	      }

        	      if (!channel.getClients().contains(target)) {
        	        client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName()));
        	        return;
        	      }

        	      client.sendButlerMessage(channel.getName(), String.format("%s wird sich jetzt entscheiden, deine Hand zu halten oder es bleiben zu lassen...",target.getName() ));
        	      target.sendButlerMessage(channel.getName(), String.format("_°BB20°_Herzklopfen angesagt:_°r°#_°>_h" + client.getName() + "|/serverpp " + client.getName() + "|/w " + client.getName() + "<°_ möchte mit dir _Händchenhalten_... reichst du deine Hand? _°>{button} Bestätigen ||call|/holdhands okay<° _", new Object[0]));
        	      
        	      Server.actions.put(time, new String[] {"holdhands", client.getName(), target.getName(), channel.getName(), "60"});
                      ft.setBan(l[1],l[3],l[4],client); // setz sperre
       
        
        
        
        } else if(cmd.equals("highfive")) {
        	if (arg.trim().equalsIgnoreCase("ok")) {
        		for (long x : Server.actions.keySet()) {
                	String[] split = Server.actions.get(x);
                	Channel cha = Server.get().getChannel(split[3]);
                	String typ = split[0];
                	String v = split[2];
                        
                        
                   
                	Client von = Server.get().getClient(v);
                	Client an = Server.get().getClient(split[1]);
                	if(cha == channel && an == client && typ.equals("highfive")) {
 String text = highfive[zufall.nextInt(highfive.length)].replace("[C]", client.getName().replace("<", "\\<")).replace("[T]", von.getName().replace("<", "\\<"));
                            	
	channel.broadcastPicAction(">>", text, String.format("actHighFive_%s.png", image(von, client)));
                        
                                
                		Server.actions.remove(x);
                		return;
                	}
                }
        	}
        	          
                    String[] l = client.getFeature("Highfive");
                   Feature ft = Server.get().getFeature("Highfive");

                   if (ft == null) {
                       return;
                   }

                   if (l[0].equals("0")) {
                         client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
                   return;  
                   } 
                   if (l[0].equals("1")) {
                         client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
                  return;
                   }
        	
        	if (arg.isEmpty()) {
        	      client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/highfive NICK#(Mit NICK einschlagen)");
        	      return;
        	}
        	
        	      String nicknamen = KCodeParser.escape(arg);
        	      boolean online = true;
        	      Client target = Server.get().getClient(nicknamen);
        	      if (target == null) {
        	        online = false;
        	        target = new Client(null);
        	        target.loadStats(nicknamen);
        	      }
        	      nicknamen = target.getName();

        	      if (arg.isEmpty()) {
        	        client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/highfive NICK#(Mit NICK einschlagen)");
        	        return;
        	      }

        	      if (nicknamen == null) {
        	        client.sendButlerMessage(channel.getName(), unknownUser(arg));
        	        return;
        	      }

        	      if (target.getName().equals(client.getName())) {
        	        client.sendButlerMessage(channel.getName(), "Du kannst doch nicht mit dir selbst einschlagen");
        	        return;
        	      }

        	      if (!online) {
        	        client.sendButlerMessage(channel.getName(), userIsOffline(target));
        	        return;
        	      }

        	      if (target.getName().equals(Server.get().getButler().getName())) {
        	        client.sendButlerMessage(channel.getName(), "Mit " + Server.get().getButler().getName() + " kannst du nicht einschlagen.");
        	        return;
        	      }

        	      if (!channel.getClients().contains(target)) {
        	        client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.",  target.getName()));
        	        return;
        	      }

        	      client.sendButlerMessage(channel.getName(),"Ich habe °BB°_"+target.getName()+"§ gefragt, ob "+target.getGenderLabel()+" mit dir einschlagen möchte.");
        	
                      
                      
                      target.sendButlerMessage(channel.getName(), String.format("%s hält dir die erhobene Hand hin und wartet darauf, dass du einschlägst... °>finger.b.gif<°_°BB>_hEinschlagen|/highfive ok<°_",  client.getName()));
        	      Server.actions.put(time, new String[] {"highfive",target.getName(), client.getName(), channel.getName(), "30"});
                      ft.setBan(l[1],l[3],l[4],client); // setz sperre
                   
       
                      
                      } else if(cmd.equals("hof")) {
        	if(!client.hasPermission("cmd.hof")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion ist derzeit nicht verfügbar.");
        		return;
        	        
                }
                
                 } else if(cmd.equals("notice")) {
        	if(!client.hasPermission("cmd.notice")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion ist derzeit nicht verfügbar.");
        		return;
        	}
                
                 } else if(cmd.equals("admc")) {
        	if(!client.hasPermission("cmd.admc")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion ist derzeit nicht verfügbar.");
        		return;
        	}
                      
                      
                      
                      
       
         } else if(cmd.equals("macro")) {
        	if(!client.hasPermission("cmd.macro")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/macro ? oder TYP:PARAMETER#(Ruft die Macro Hilfe auf oder führt das Macro TYP mit den Parametern PARAMETER aus.)");
        		return;
        	}
        	
        	if(arg.equals("?")) {
            	client.sendButlerMessage(channel.getName(), "Es gibt bisher keine Macros.");
        		return;
        	}

        	client.sendButlerMessage(channel.getName(), String.format("Das Macro _%s existiert nicht_.", arg));
  	
            
        } else if(cmd.equals("kickall")) {
        	if(!client.hasPermission("cmd.kickall")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	Server.get().newSysLogEntry(client.getName(), String.format("Globaler Kick aller Mitglieder"));
        	for(Client cl : channel.getClients()) {
        		if(!cl.getName().equals(client.getName()) && !cl.getName().equals(Server.get().getButler().getName())) {
        			cl.disconnect();
        		}
        	}

        	Server.get().newSysLogEntry(client.getName(), String.format("KickAll Channel %s", channel.getName()));
        } else if(cmd.equals("helpeditor")) {
        	if(!client.hasPermission("cmd.helpeditor")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	client.sendButlerMessage(channel.getName(), "°RR°_Funktion deaktiviert.");
        } else if(cmd.equals("botcontrol")) {
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.equals("no") && client.getBotkontrolleVon() != null) {
        		Client von = Server.get().getClient(client.getBotkontrolleVon());
        		
        		client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wurde darüber informiert, dass du am PC sitzt und keinen Bot verwendest.", client.getBotkontrolleVon()));
        		Server.get().newMessage(Server.get().getButler().getName(), client.getBotkontrolleVon(), String.format("Nicht bestätigt: Botkontrolle %s", client.getName()), String.format("%s hat auf eine Kontroll-Nachricht reagiert, damit hat sich der Bot-Verdacht nicht bestätigt.", client.getName()), time);
        		client.setBotkontrolleVon(null);
        		von.setBotkontrolleAn(null);
        		von.setBotkontrolleZeit(0);
        		return;
        	}
        	
        	if(!client.hasPermission("cmd.botcontrol")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/botcontrol NICK#(Eine Botkontrolle bei NICK durchführen.)");
        		return;
        	}
            
            Client target = Server.get().getClient(arg);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}

            if(target == client || target == Server.get().getButler() || target.getRank() > 4) {
                client.sendButlerMessage(channel.getName(), String.format("Du kannst bei °>_h%s|/serverpp \"|/w \"<° keine Botkontrolle durchführen.", target.getName().replace("<", "\\<")));
                return;
            }
            
            if(target.getBotkontrolleAn() != null) {
            	client.sendButlerMessage(channel.getName(), "Du überprüfst momentan bereits eine Person auf Botnutzung. Geduldige dich bitte einen Augenblick.");
            	return;
            }
            
            if(target.getBotkontrolleVon() != null) {
            	client.sendButlerMessage(channel.getName(), String.format("%s wird momentan bereits auf Botbotnutzung überprüft.", target.getName()));
            	return;
            }
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}
        	
        	client.sendButlerMessage(channel.getName(), String.format("%s erhält jetzt eine Kontroll-Nachricht. Du erhältst eine Info, sobald das Ergebnis der Kontrolle feststeht.", target.getName()));
        		
        	for(Channel s : target.getChannels()) {
        		target.sendButlerMessage(s.getName(), String.format("°20°_Überprüfung auf Bot-Benutzung_°r°##°>_h%s|/serverpp \"|/w \"<° möchte überprüfen, ob du selbst am PC sitzt.##°RR°_WICHTIG_°r°: Klick _°>{button} Bestätigen ||call|/botcontrol no<° _, um zu bestätigen, dass du kein Bot-Programm verwendest.", client.getName()));
        	}
        	target.setBotkontrolleVon(client.getName());
        	client.setBotkontrolleAn(target.getName());
        	client.setBotkontrolleZeit(time);
 
                
                
        } else if(cmd.equals("buzzer")) {      
            
            String[] l = client.getFeature("Buzzer");
           Feature ft = Server.get().getFeature("Buzzer");

           if (ft == null) {
               return;
           }

           if (l[0].equals("0")) {
                 client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
           return;  
           } 
           if (l[0].equals("1")) {
                 client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
          return;
             } 
  
            
        	String nick = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nick.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(nick.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/buzzer +NICK:TEXT#(NICK eine Weck-Botschaft mit der Nachricht TEXT übermitteln.)");
            	return;
            }
            
            if(nick.startsWith("+")) {
            	nick = nick.substring(1);
            }
            
            Client target = Server.get().getClient(nick);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(nick);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(nick));
        			return;
        		}
        	}

            if(target == client) {
                client.sendButlerMessage(channel.getName(), "Du kannst dir selbst keine Weck-Botschaft schicken.");
                return;
            }
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}

        	client.sendButlerMessage(channel.getName(), String.format("Du hast _°BB>_h%s|/serverpp \"|/w \"<r°_ jetzt folgende Weck-Botschaft gesendet...°>|{backgroundad|buzzerAd_bright.jpg|buzzerAd_bright.jpg|buzzerAd_dark.jpg|buzzerAd_dark.jpg|0|0|0|0|1|20|http://www.knuddels.de| %s|255,255,255|255,255,255|28|0|-22<>alarmring_buzzer.mp<°", target.getName().replace("<", "\\<"), msg));
        	
        	for(Channel s : target.getChannels()) {
            	target.sendButlerMessage(s.getName(), String.format("_°BB>_h%s|/serverpp \"|/w \"<r°_ sendet dir diese Weck-Botschaft...°>|{backgroundad|buzzerAd_bright.jpg|buzzerAd_bright.jpg|buzzerAd_dark.jpg|buzzerAd_dark.jpg|0|0|0|0|1|20|http://www.knuddels.de| %s|255,255,255|255,255,255|28|0|-22<>alarmring_buzzer.mp<°", client.getName(), msg));
        	}
        	
        	client.setWeckMessage((byte)1);
                ft.setBan(l[1],l[3],l[4],client); 
       
        
        } else if(cmd.equals("color")) {
        	if(!client.hasPermission("cmd.color")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String nick = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nick.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(nick.isEmpty() || msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/color NICK:RGB-Code oder !#(Farbe von NICK auf RGB-Code setzen oder löschen.)");
            	return;
            }
            
            Client target = Server.get().getClient(nick);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(nick);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(nick));
        			return;
        		}
        	}
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}
        	if(!msg.equals("!")) { 
        		if(!msg.contains(",") || msg.split(",").length < 2) {
        			client.sendButlerMessage(channel.getName(), "Ungültige Farbe! _Beispiel_: 0,0,0 für Schwarz oder 255,255,255 für Weiß.");
        			return;
        		}
        	
        		for(String x : msg.split(",")) {
        			x = x.trim();
        		
        			try {
        				Integer.parseInt(x);
        			} catch (Exception ex) {
        				client.sendButlerMessage(channel.getName(), "Ungültige Farbe! _Beispiel_: 0,0,0 für Schwarz oder 255,255,255 für Weiß.");
        				return;
        			}
        		
        			int zahl = Integer.parseInt(x);
        		
        			if(zahl < 0 || zahl > 255) {
            			client.sendButlerMessage(channel.getName(), "Ungültige Farbe! _Beispiel_: 0,0,0 für Schwarz oder 255,255,255 für Weiß.");
            			return;
        			}
        		}
        	}
        	client.sendButlerMessage(channel.getName(), String.format("_°[%s]°Farbe von %s %s._", msg, target.getName(), msg.equals("!")?"gelöscht":"geändert"));
        	target.setColor(msg.equals("!")?"":msg);
        
                
                
           } else if(cmd.equals("sendheart")) {    
               
              String[] l = client.getFeature("Sendheart");
             Feature ft = Server.get().getFeature("Sendheart");

             if (ft == null) {
                 return;
             }

             if (l[0].equals("0")) {
                   client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
             return;  
             } 
             if (l[0].equals("1")) {
                   client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
            return;
               } 

        	
        	arg = KCodeParser.escape(arg);
        	Client target = Server.get().getClient(arg);
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(arg);
        		
        		if(target.getName() == null) {
        			client.sendButlerMessage(channel.getName(), unknownUser(arg));
        			return;
        		}
        	}
        	
        	if(!online) {
        		client.sendButlerMessage(channel.getName(), userIsOffline(target));
        		return;
        	}
        	
        	if(target == client || target == Server.get().getButler()) {
        		client.sendButlerMessage(channel.getName(), String.format("Du kannst kein Herz an %s senden.", target.getName()));
        		return;
        	}

            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
        	
        	channel.broadcastButlerMessage(String.format("%s schickt ein Herz °>{sprite}type:1|nickname:%s|img:pics/abo/flying_heart_002.png<° an eine ganz besondere Person...", client.getName(), target.getName().replace("<", "\\<")));
                ft.setBan(l[1],l[3],l[4],client); 
       
                
         } else if(cmd.equals("flyingbed")) {       
             
          String[] l = client.getFeature("Flyingbed");
         Feature ft = Server.get().getFeature("Flyingbed");

         if (ft == null) {
             return;
         }

         if (l[0].equals("0")) {
               client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
         return;  
         } 
         if (l[0].equals("1")) {
               client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
        return;
           } 


        	List<String> list = new ArrayList<String>();
        	
        	for(Channel ch : Server.get().getChannels()) {
        		if(ch.countClients() > 1) {
        			list.add(ch.getName());
        		}
        	}
        	
    		Collections.shuffle(list);
			Channel c = Server.get().getChannel(list.get(0));
        	
			client.sendButlerMessage(channel.getName(), String.format("Du bist im Channel %s geflogen und hast %s anwesende Chatter geknuddelt.", c.getName(), c.countClients()));
			c.broadcastButlerMessage(String.format("°>flyingbed...b.my_16.h_57.gif<° °18BB°_%s kommt hereingeflogen und knuddelt alle Chatter mal so richtig durch...", client.getName()));
			ft.setBan(l[1],l[3],l[4],client); // setz sperre
			for(Client s : c.getClients()) {
				s.increaseKnuddels(1);
			}
			
        	
                        
        } else if(cmd.equals("wink")) {       
            
                String[] l = client.getFeature("Wink");
               Feature ft = Server.get().getFeature("Wink");

               if (ft == null) {
                   return;
               }

               if (l[0].equals("0")) {
                     client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
               return;  
               } 
               if (l[0].equals("1")) {
                     client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
              return;
                 } 
 
            String ch = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > ch.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(msg.isEmpty() || ch.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/wink CHANNEL:NACHRICHT#(Im Channel CHANNEL mit der Nachricht TEXT herüberwinken.)");
            	return;
            }
            
            
            
            Channel target = Server.get().getChannel(ch);
            
            if(target == null) {
            	client.sendButlerMessage(channel.getName(), String.format("Der Channel _%s existiert nicht_.", ch));
            	return;
            }
            
            if(client.getChannels().contains(target)) {
            	client.sendButlerMessage(channel.getName(), String.format("Um im Channel %s herüberwinken zu können, darfst du dich nicht in dem Channel aufhalten.", target.getName()));
            	return;
            }

            msg = KCodeParser.parse(client, msg, 0, 10, 20);
            msg = Server.get().parseSmileys(client, msg);
            
            client.sendButlerMessage(channel.getName(), String.format("Du hast im Channel %s herübergewunken.", target.getName()));
            target.broadcastButlerMessage(String.format("%s winkt aus dem Channel %s herüber: °RR°_\"\"%s§°RR°_\"\"§.", client.getName(), channel.getName(), msg));
            ft.setBan(l[1],l[3],l[4],client); // setz sperre
       
        
        } else if(cmd.equals("grippe")) {
            
        String[] l1 = client.getFeature("Grippe-Analyze");
        Feature ft1 = Server.get().getFeature("Grippe-Analyze");
        String[] l2 = client.getFeature("Grippe-Heal");
        Feature ft2 = Server.get().getFeature("Grippe-Heal");   
        String[] l3 = client.getFeature("Grippe-Immun");
        Feature ft3 = Server.get().getFeature("Grippe-Immun");
        String[] l4 = client.getFeature("Grippe-Infect");
        Feature ft4 = Server.get().getFeature("Grippe-Infect");      
        String[] l5 = client.getFeature("Grippe-Infect (Channel)");
        Feature ft5 = Server.get().getFeature("Grippe-Infect (Channel)");
     
            if (ft1 == null || ft2 == null || ft3 == null || ft4 == null || ft5 == null) {
               
                return;
            }
            if (!l1[0].equals("2") && !l3[0].equals("2") && !l4[0].equals("2") && !l2[0].equals("2") && !l5[0].equals("2")) {
            client.sendButlerMessage(channel.getName(), "Du hast das Grippe-Feature nicht");
                                   return;
                           }
        	
            String typ = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String nick = "";
            
            if (arg.length() > typ.length()) {
                nick = KCodeParser.escape(arg.substring(arg.indexOf(':') + 1));
            }
            if (!arg.equals("+infect")) {
            if(nick.isEmpty() || typ.isEmpty()) {
               return;
            }
            }
            
            Client target = Server.get().getClient(nick);
            boolean online = true;
            
            if(target == null) {
            	online = false;
            	target = new Client(null);
            	target.loadStats(nick);
            	 if (!arg.equals("+infect")) {
            	if(target.getName() == null) {
            		client.sendButlerMessage(channel.getName(), unknownUser(nick));
            		return;
            	}}
            }
             if (!arg.equals("+infect")) {
            if(!online) {
            	client.sendButlerMessage(channel.getName(), userIsOffline(target));
            	return;
            }
            
            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
            }
            
            if(typ.equals("analyze")) {
           
                String[] l = client.getFeature("Grippe-Analyze");
                Feature ft = Server.get().getFeature("Grippe-Analyze");

                if (ft == null) {
                    return;
                }

                if (l[0].equals("0")) {
                      client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
                return;  
                } 
                if (l[0].equals("1")) {
                      client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
               return;
                  } 

            	client.sendButlerMessage(channel.getName(), target.getGrippeStatus()<1?String.format("%s fühlt sich momentan den Umständen entsprechend gut.", target.getName()):String.format("%s fühlt sich elend und ist bereits an der Grippe erkrankt.", target.getName()));
            	for(Channel c : target.getChannels()) {
                
            		target.sendButlerMessage(c.getName(), String.format("Doktor %s untersucht dich gerade auf Herz und Nieren...", client.getName()));
            	
                }
                    ft.setBan(l[1],l[3],l[4],client); 
            	return;
            }
            
            if(typ.equals("heal")) {
                
            String[] l = client.getFeature("Grippe-Heal");
           Feature ft = Server.get().getFeature("Grippe-Heal");

           if (ft == null) {
               return;
           }

           if (l[0].equals("0")) {
                 client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
           return;  
           } 
           if (l[0].equals("1")) {
                 client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
          return;
             } 

            	
            	if(target.getGrippeStatus() < 1) {
            		client.sendButlerMessage(channel.getName(), String.format("%s ist nicht an der Grippe erkrankt.", target.getName()));
            		return;
            	}

            	channel.broadcastButlerMessage(String.format("°>hands_ani...b.my_6.h_21.gif<° %s hat %s erfolgreich von der Grippe befreit.", client.getName(), target.getName()));
            	removeIcon("pics/nose.gif", target.getName(), channel);
            	target.setGrippeStatus((byte)0);
                ft.setBan(l[1],l[3],l[4],client); 
            	return;
            }
            
            
            if (arg.equals("+infect")) {
                
            String[] l = client.getFeature("Grippe-Infect (Channel)");
           Feature ft = Server.get().getFeature("Grippe-Infect (Channel)");      
                
            if (ft == null) {
               return;
           }

           if (l[0].equals("0")) {
                 client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
           return;  
           } 
           if (l[0].equals("1")) {
                 client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
          return;
             }
           
           for(Client c : channel.getClients()) {
               if (c != client && c != Server.get().getButler()) {
               if(target.getGrippeStatus() == 0) { // && target.getGrippeImmun() == 0
            	
               c.setGrippeInfiziert(1);
               }
           }}
           
           
             ft.setBan(l[1],l[3],l[4],client); 
           
           client.sendButlerMessage(channel.getName(),"Du hast den Channel "+channel.getName()+" erfolgreich angesteckt!");
                return;
            }
            if(typ.equals("infect")) {
                
            String[] l = client.getFeature("Grippe-Infect");
           Feature ft = Server.get().getFeature("Grippe-Infect");

           if (ft == null) {
               return;
           }

           if (l[0].equals("0")) {
                 client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
           return;  
           } 
           if (l[0].equals("1")) {
                 client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
          return;
             } 

            	
            	if(target.getGrippeStatus() > 0) {
            		client.sendButlerMessage(channel.getName(), String.format("Bei diesem Opfer scheint dein Infizierungsversuch nichts zu bewirken, vielleicht ist _%s_ schon _krank_ oder vielleicht sogar _immun_.", target.getName()));
            		return;
            	}
                
                
               /* if(target.getGrippeImmun() > 0) {
            		client.sendButlerMessage(channel.getName(), String.format("Bei diesem Opfer scheint dein Infizierungsversuch nichts zu bewirken, vielleicht ist _%s_ schon _krank_ oder vielleicht sogar _immun_.", target.getName()));
            		return;
            	}*/

                if (target.getGrippeInfiziert() == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("Bei diesem Opfer scheint dein Infizierungsversuch nichts zu bewirken, vielleicht ist _%s_ schon _krank_ oder vielleicht sogar _immun_.", target.getName()));
            		return;
                }
                client.sendButlerMessage(channel.getName(), String.format("Du hast _%s_ erfolgreich mit deiner gefährlichen und hochansteckenden _Grippe infiziert_.", target.getName()));
            		
            	target.setGrippeInfiziert(1);
                ft.setBan(l[1],l[3],l[4],client); 
            	return;
            }
            
            
            
            if(typ.equals("immun")) {
         
                String[] l = client.getFeature("Grippe-Immun");
               Feature ft = Server.get().getFeature("Grippe-Immun");

               if (ft == null) {
                   return;
               }

               if (l[0].equals("0")) {
                     client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
               return;  
               } 
               if (l[0].equals("1")) {
                     client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
              return;
                 } 
           	
            	if(target.getGrippeStatus() > 0) {
            		client.sendButlerMessage(channel.getName(), String.format("%s ist bereits an der Grippe erkrankt.", target.getName()));
            		return;
            	}

            	channel.broadcastButlerMessage(String.format("°>spritze_ani_green...b.mx_-8.my_14.w_28.h_21.gif<° %s hat %s erfolgreich geimpft. Nun muss sich %s erstmal ziemlich lange keine Sorgen wegen einer neuen Grippe machen.", client.getName(), target.getName(), target.getName()));
            	ft.setBan(l[1],l[3],l[4],client);
            	return;
            }
        }  else if(cmd.equals("set")) {
             
         }

        else if(cmd.equals("deny")) {
        	if(!client.hasPermission("cmd.deny")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung");
        		return;
        	}
                
        	
        	if(client.getDeny() == null) {
        		client.sendButlerMessage(channel.getName(), "Du kannst momentan kein Herz zurückgeben.");
        		return;
        	}
        	
        	Client target = Server.get().getClient(client.getDeny());
        	boolean online = true;
        	
        	if(target == null) {
        		online = false;
        		target = new Client(null);
        		target.loadStats(client.getDeny());
        	}
        	
        	if(!target.getHeart().equals(client.getName())) {
        		client.sendButlerMessage(channel.getName(), "Du kannst momentan kein Herz zurückgeben.");
        		return;
        	}
        	
        	channel.broadcastPicAction(">", String.format("%s hat sein Herz wohl umsonst verschenkt, denn %s möchte es nicht haben!", target.getName(), client.getName()), String.format("actDeny_%s.png", image(client, target))); 
        	Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Herz zurückgegeben", String.format("%s hat dir dein Herz zurückgegeben.", client.getName()), time);
        	
        	if(online) {
        		target.setHeart("");
        		target.receivedHearts.remove(client.getName());
        	} else {
        		Server.get().query(String.format("update accounts set heart='' where name='%s'", target.getName()));
        	}
        	client.setDeny(null);
    
                
        } else if(cmd.equals("analysedata")) {

            
                String[] l = client.getFeature("Analysedata");
               Feature ft = Server.get().getFeature("Analysedata");

               if (ft == null) {
                   return;
               }

               if (l[0].equals("0")) {
                     client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
               return;  
               } 
               if (l[0].equals("1")) {
                     client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
              return;
                 } 
 
        	int a = Server.get().getAllKnuddels();
        	int b = Server.get().getAllKontoKnuddels();
        	int knuddels = a+b;
        	StringBuilder ad = new StringBuilder();
        	ad.append("k\0Statistiken zu "+Server.get().getSettings("CHAT_NAME")+"õhÿÿÿãNl õgFhÿÿÿãSl õgFhÿÿÿãEl         õgFhÿÿÿãWl         õgFhÿÿÿãCpBCpBCc");
        	ad.append("°>{imageboxstart}boxS.my_-4.mh_30<°#°20+0015+9515°_Statistiken zu "+Server.get().getSettings("CHAT_NAME")+"°b° °>features/analyse-data/ft_statistik...my_10.h_0.png<°°12+0000°##°+5040°#°+9505°°+0020>layout/bullet2.png<+0040°_").append(knuddels).append(" Knuddels_ im Umlauf#°+0000°");
        	//ad.append("#°+9505°°+0020>layout/bullet2.png<+0040°Am häufigsten geklickter _Wiki-Link°b°: _°BB>_hWikipedia-Link|http://de.wikipedia.org/wiki/SMS-Gateway<bK°#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°Am häufigsten geklickter _Youtube-Link°b°: _°BB>_hYouTube-Link|http://www.youtube.com/watch?v=75R2__kCp8KA<bK°#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_°BB>_hSuper TT Player|/serverpp \"|/w \"<°°°_ besitzt _die meisten Rosen_ (_16.060°b°)#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_°BB>_hPfirsichgeschmack|/serverpp "|/w "<°°°_ besitzt _die meisten Geschenke_ (_1.445°b°)#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_17_ verschickte _Herzbotschaften_ diese Woche#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_10.197_ verschickte _Rosen_ diese Woche#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_2,7 Nutzer im Schnitt_ hatte der Channel _Worms°b° in den letzten 3 Tagen#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_5.193 Billard-Spiele_ heute#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_6.634 öffentliche MyChannels_ gestern#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_144.337 Küsse_ heute#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°Um _21.03 Uhr°b° war der _Chat gestern am vollsten°b°. Es waren _21.734 User online°b°.#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_Stammchatter des Monats_ ist _°BB>_hbirdem|/serverpp "|/w "<°°°_°b°.#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_2.645 CMs_#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_558 Teamler_#°+0000°#°+9505°°+0020>layout/bullet2.png<+0040°_31.198 MCMs_#°+0000°");
        	ad.append("## °+0000+5000>CENTER<°_°r>{button}OK||call|_c|width|70|heigth|28<°_°>{imageboxend}<°##°>LEFT<°");
        	ad.append("õs\0ÌtsendbackõhÿÿÿãSl õgFhÿÿÿãããã");
        	client.send(ad.toString());
        	channel.broadcastPicAction(">", String.format("°>_h%s|/serverpp \"|/w \"<° weiß Dank eines Blicks in die geheime Server-Datenbank offensichtlich mehr als andere:#_%s Knuddels_ sind im Umlauf.°+0000°§", client.getName(), knuddels), "../features/analyse-data/ft_statistik.png");
                ft.setBan(l[1],l[3],l[4],client); 
       
        
        } else if(cmd.equals("bet")) {
        	if(!client.hasPermission("cmd.bet")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Derzeit laufen keine Wetten.");
        		return;
        	}
        	
        	client.sendButlerMessage(channel.getName(), String.format("Die Wette %s gibt's nicht!", arg));
       
                 
               } else if(cmd.equals("channel")) {       
               } else if(cmd.equals("login")) { 
               } else if(cmd.equals("logout")) { 
               } else if(cmd.equals("changelog")) { 
               } else if(cmd.equals("mentor")) { 
               } else if(cmd.equals("mymentor")) {  
               } else if(cmd.equals("cminfo")) {  
               } else if(cmd.equals("fw")) {          
               } else if(cmd.equals("game")) {
               } else if(cmd.equals("dice")) {
               } else if(cmd.equals("sunshine")) {    
               } else if(cmd.equals("butterfly")) {                 
               } else if(cmd.equals("imitate")) {               
               } else if(cmd.equals("deletenick")) {                     
               } else if(cmd.equals("b")) {   
               } else if(cmd.equals("newvote")) { 
               } else if(cmd.equals("cheers")) {     
               } else if(cmd.equals("clink")) { 
               } else if(cmd.equals("profil")) {                     
                               
 
                   
                   
                   } else if(cmd.equals("gmute")) {
           funktionen.gmute.functionMake(client, channel, arg); 
                   
               } else if(cmd.equals("channeledit")) {
               channeledit.make(arg,client,channel);
               } else if(cmd.equals("wedit")) {
               wedit.make(arg, client, channel);   
               } else if(cmd.equals("sug")) {
               sug.functionMake(client,channel, arg);        	
               } else if(cmd.equals("vote")) {
               vote.functionMake(client,channel, arg);        	
               } else if(cmd.equals("showwahl")) {
               showwahl.functionMake(client,channel, arg);        	
               } else if(cmd.equals("desc")) {
               desc.functionMake(client,channel, arg);        	
               } else if(cmd.equals("fotowhois")) {
               fotowhois.functionMake(client,channel, arg);  
               } else if(cmd.equals("kissen")) {
               kissen.functionMake(client,channel, arg);  
               } else if(cmd.equals("sexy")) {
               sexy.functionMake(client,channel, arg);  
               } else if(cmd.equals("sound")) {
               sound.functionMake(client,channel, arg,cmd);  
               } else if(cmd.equals("bart")) {
               bart.functionMake(client,channel, arg);  
               } else if(cmd.equals("konfettibombe")) {
               konfettibombe.functionMake(client,channel, arg);  
               } else if(cmd.equals("discolight")) {
               discolight.functionMake(client,channel, arg);  
               } else if(cmd.equals("montgolfier")) {
               montgolfier.functionMake(client,channel, arg);  
               } else if(cmd.equals("fireflies")) {
               fireflies.functionMake(client,channel, arg);  
               } else if(cmd.equals("snowball")) {
               snowball.functionMake(client,channel, arg);  
               } else if(cmd.equals("cool")) {
               cool.functionMake(client,channel, arg);                  
               } else if (cmd.equals("dj")) {
               dj.functionMake(client,channel, arg);    
               } else if(cmd.equals("albenfoto")) {
               client.send(PacketCreator.openURL(String.format("%sindex.php?page=album_foto&id="+arg+"&photo", Server.get().getURL()), "_blank"));     


               
        } else if(cmd.equals("s")) { 
        	if(!client.hasPermission("cmd.s")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/s TEXT#(TEXT bei Google suchen.)");
        		return;
        	}
        	
        	arg = arg.replace(" ", "+");
        	client.send(PacketCreator.openURL(String.format("http://www.google.de/#sclient=psy-ab&hl=de&q=%s", arg), "_blank"));
      
        
        } else if(cmd.equals("yt")) { 
            
        	if(!client.hasPermission("cmd.yt")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        	
        	if(arg.isEmpty()) {
        		client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/yt TEXT#(TEXT bei YouTube suchen.)");
        		return;
        	}
        	
        	arg = arg.replace(" ", "+");
        	client.send(PacketCreator.openURL(String.format("http://www.youtube.com/results?search_query=%s", arg), "_blank"));
        } else if (cmd.equals("a") || cmd.equals("postmsg")) {
        	if(!client.hasPermission(String.format("cmd.%s", cmd))) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
            if(muted(client, channel)) { 
            	return;
            }
            
            if(moderated(client, channel)) {
            	client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
            	return;
            }
            
            channel.broadcastMessage(arg, client, false);
            
            if (arg.toLowerCase().contains(Server.get().getButler().getName().toLowerCase()) && arg.toLowerCase().contains("blitz")) {
                   blitz.start(Server.get().getButler().getName()+" blitz", client, channel);
            }
            
        } else if(cmd.equals("admin")) {
            
            
            
            if (arg.equals("stat")) {
                
                StringBuilder nicks = new StringBuilder();
                    List x = Server.get().getAllVoter("admin");
            for(Object a : x) {
             String nickname = (String)a; 
             if (!nicks.toString().isEmpty()) {
                 nicks.append(", ");
             }
             nicks.append("°>_h"+nickname+"|/serverpp \"|/w \"<°");
            }
                
                String text = "Insgesamt haben schon _"+Server.get().getAllVoter("admin").size()+" Mitglieder_ ihre Stimme abgegeben:##"+nicks.toString();
                //client.send(Popup.create("Admin-Wahl - Wer hat gewählt?","Admin-Wahl - Wer hat gewählt?",text,400,300));
                Popup popup = new Popup("Admin-Wahl - Wer hat gewählt?", "Admin-Wahl - Wer hat gewählt?",text, 450, 275);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setLaufbahn(1);
                 client.send(popup.toString());
                 return;     
               
            }
            
            
            
            if(arg.isEmpty() || !client.hasPermission("cmd.admin.time") && !client.hasPermission("cmd.admin.activatemask") && !client.hasPermission("cmd.admin.deactivatemask")) {
        		client.send(PacketCreator.createAdmincallWindow("", "", ""));
            	return;
            }
            
            arg = arg.toLowerCase();
            
            if(arg.equals("mask")) {
            	if(!client.hasPermission("cmd.admin.activatemask")) {
            		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            		return;
            	}
            	
                client.sendButlerMessage(channel.getName(), "Tarnmodus _aktiviert_.");
                client.setMask((byte)1);
                return;
            }
            
            if(arg.equals("!mask")) {
            	if(!client.hasPermission("cmd.admin.deactivatemask")) {
            		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            		return;
            	}
            	
                client.sendButlerMessage(channel.getName(), "Tarnmodus _deaktiviert_.");
                client.setMask((byte)0);
                return;
            }
            
            if(arg.equals("?time")) {
            	if(!client.hasPermission("cmd.admin.time")) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
                // ((160/60)/100)*(12/2);
                
                // (   (Sekunden/60)   /100   )        *      (   TEAMS/2   );
                
                //((rs.getInt("adminTime")/60)/60)*(CommandParser.countChars(teams, '|')/2);
                int knuddels = ((client.getAdminTime()/60)/6)*(countChars(client.getTeams(), '|')/2)/10;
            	
                client.sendButlerMessage(channel.getName(), String.format("#°>bullet.b.png<° Online-Minuten heute (gesamt - ohne Abzüge): %s#°>bullet.b.png<° Online-Minuten heute (beschäftigt - afk, mod/vip): 0#°>bullet.b.png<° Online-Minuten heute (netto - abzgl. aller Abzüge): %s (Insgesamt °BB°_%s_°r° °>sm_classic_00.gif<°)#-----------------------------#°>bullet.b.png<° Online-Minuten diesen Monat (gesamt - ohne Abzüge): %s", nf.format(client.getAdminTime()/60), nf.format(client.getAdminTime()/60), knuddels, nf.format(client.getTimeMonth()/60)));
                return;
            }
        } else if (cmd.equals("afk") || cmd.equals("away")) {
        	if(!client.hasPermission(String.format("cmd.%s", cmd))) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
            if(muted(client, channel)) { 
            	return;
            }
            
            if(moderated(client, channel)) {
            	client.sendButlerMessage(channel.getName(), "Aufgrund einer _moderierten Veranstaltung_ kannst du nicht öffentlich sprechen. Privates Flüstern ist natürlich erlaubt.");
            	return;
            }
            
        	StringBuilder away = new StringBuilder(afk[zufall.nextInt(afk.length)]);
            arg = KCodeParser.noKCode(arg);
            arg = Server.get().parseSmileys(client, arg);
        	
            if (!arg.isEmpty()) {
                away.append(" (").append(arg).append(")");
                client.setAwayReason(arg);
            }
            
            if(client.hasPermission("popup.awayaniicon")) {
            	away.append(" °>icon_away_ani_new.gif<°");
            }

            channel.broadcastAction(client.getName(), away.toString());
            client.setAway(true);
        } else if(cmd.equals("mod")) { 
        	if(!client.hasPermission("cmd.mod.channel") && !client.hasPermission("cmd.mod.mod") && !client.hasPermission("cmd.mod.vip")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if(nickname.isEmpty() || msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
            	return;
            }
            
            String n = nickname.toLowerCase();
            String m = msg.toLowerCase();
            
            if(n.equals("channel")) {
            	if(!client.hasPermission("cmd.mod.channel")) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
            	
        		if(msg.toLowerCase().equals("on".toLowerCase())) {
        			client.sendButlerMessage(channel.getName(), "Channel wird nun _moderiert_. Du wurdest automatisch zum Moderator ernannt.");
        			
                	addIcon("pics/icon_moderator.png", 24, client,channel);
        			channel.setModerated(true);
        			channel.setMods(String.format("|%s|", client.getName()));
        			return;
        		}
        		
        		if(m.equals("off")) {
        			client.sendButlerMessage(channel.getName(), "Channel wird nicht mehr moderiert. MOD- und VIP-Rechte aller Mitglieder entzogen.");

        			for(String vip : channel.getVips().split("\\|")) {
        				if(!vip.isEmpty()) {
            				removeIcon("pics/icon_vip.png", vip,channel);
        				}
        			}
        			
        			for(String mod : channel.getMods().split("\\|")) {
        				if(!mod.isEmpty()) {
            				removeIcon("pics/icon_moderator.png", mod,channel);
        				}
        			}
        			
        			channel.setModerated(false);
        			channel.setMods("");
        			channel.setVips("");
        			return;
        		}
        		
    			client.sendButlerMessage(channel.getName(), "Falscher Parameter");
        		return;
        	}
            
            if(n.equals("vip") || n.equals("mod")) {
            	if(!client.hasPermission(String.format("cmd.%s", n))) {
                	
            	}
            	
            	if(!channel.isModerated()) {
            		client.sendButlerMessage(channel.getName(), "Der Channel ist _nicht moderiert_.");
            		return;
            	}
            	
            	msg = KCodeParser.escape(msg);
            	boolean entziehen = false;
            	
            	if(msg.startsWith("!")) {
            		msg = msg.substring(1);
            		entziehen = true;
            	}
            	
            	if(msg.isEmpty()) {
            		client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
            		return;
            	}
            	
            	Client target = Server.get().getClient(msg);
            	boolean online = true;
            	
            	if(target == null) {
            		online = false;
            		target = new Client(null);
            		target.loadStats(msg);
            		
            		if(target.getName() == null) {
            			client.sendButlerMessage(channel.getName(), unknownUser(msg));
            			return;
            		}
            	}
            	
            	if(entziehen) {
                	if(!channel.checkVip(target.getName()) && n.equals("vip")) {
                		client.sendButlerMessage(channel.getName(), String.format("%s ist kein VIP!", target.getName()));
                		return;
                	}
                	
                	if(!channel.checkMod(target.getName()) && n.equals("mod")) {
                		client.sendButlerMessage(channel.getName(), String.format("%s ist kein Moderator!", target.getName()));
                		return;
                	}
                	
                	if(n.equals("vip")) {
                		client.sendButlerMessage(channel.getName(), String.format("Die VIP-Rechte von %s wurden entzogen.", target.getName()));
                    	removeIcon("pics/icon_vip.png", target.getName(),channel);
                    	if(online) {
                    		for(Channel x : target.getChannels()) {
                    			target.sendButlerMessage(x.getName(), String.format("Deine VIP-Rechte im Channel %s wurden soeben von %s entzogen.", channel.getName(), client.getName()));
                    		}
                    	}
                    	channel.setVips(channel.getVips().replace(String.format("|%s|", target.getName()), ""));
                	} else if(n.equals("mod")) {
                		client.sendButlerMessage(channel.getName(), String.format("Die MOD-Rechte von %s wurden entzogen.", target.getName()));
                		removeIcon("pics/icon_moderator.png", target.getName(),channel);
                		if(online) {
                			for(Channel x : target.getChannels()) {
                				target.sendButlerMessage(x.getName(), String.format("Deine MOD-Rechte im Channel %s wurden soeben von %s entzogen.", channel.getName(), client.getName()));
                			}
                		}
                		
                		channel.setMods(channel.getVips().replace(String.format("|%s|", target.getName()), ""));
                	}
                	
                	return;
            	}
            	
            	if(!online) {
            		client.sendButlerMessage(channel.getName(), userIsOffline(target));
            		return;
            	}
            	
            	if(channel.checkMod(target.getName())) {
            		client.sendButlerMessage(channel.getName(), String.format("%s ist bereits Moderator!", target.getName()));
            		return;
            	}
            	
            	if(channel.checkVip(target.getName())) {
            		client.sendButlerMessage(channel.getName(), String.format("%s ist bereits VIP!", target.getName()));
            		return;
            	}
            	
            	icons = new ArrayList<Pair<String, Integer>>();
            	if(n.equals("vip")) {
            		client.sendButlerMessage(channel.getName(), String.format("%s wurde als VIP gesetzt.", target.getName()));
            		addIcon("pics/icon_vip.png", 22, target,channel);
            		for(Channel x : target.getChannels()) {
            			target.sendButlerMessage(x.getName(), String.format("Du hast soeben VIP-Rechte von %s im Channel %s erhalten.", client.getName(), channel.getName()));
            		}
            		channel.setVips(String.format("%s|%s|", channel.getVips(), target.getName()));
            	} else if(n.equals("mod")) {
            		client.sendButlerMessage(channel.getName(), String.format("%s wurde als Moderator gesetzt.", target.getName()));
            		addIcon("pics/icon_moderator.png", 22, target,channel);
            		for(Channel x : target.getChannels()) {
            			target.sendButlerMessage(x.getName(), String.format("Du hast soeben MOD-Rechte von %s im Channel %s erhalten.", client.getName(), channel.getName()));
            		}
            		channel.setMods(String.format("%s|%s|", channel.getMods(), target.getName()));
            		
            	}
            	
            	return;
            }
            
            client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
      
        
            
        } else if (cmd.equals("career")) {
        	if(!client.hasPermission("cmd.career")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
            
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            Client target = Server.get().getClient(nickname);
            boolean online = true;
            
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(nickname.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/career NICK:TEXT#(Fügt einen Career-Eintrag bei NICK hinzu.)"); 
                return;
            }
            
            if(msg.length() > 200) {
                client.sendButlerMessage(channel.getName(), "Der Text darf höchstens 200 Zeichen lang sein!"); 
                return;
            }
            
        
            
            client.sendButlerMessage(channel.getName(), String.format("Der Career-Eintrag bei %s ist erfolgt.", target.getName()));
            
            if(online) {
            	target.setCareer(String.format("%s|%s %s|", target.getCareer(), Server.get().timeStampToDate(time), msg));
            	
                target.setProfile(String.format("%s|%s %s|", target.getProfile(), Server.get().timeStampToDate(time), msg));
            } else {
            	Server.get().query(String.format("update accounts set profile='%s|%s %s|', career='%s|%s %s|' where name='%s'", target.getProfile(), Server.get().timeStampToDate(time), msg, target.getCareer(), Server.get().timeStampToDate(time), msg, target.getName()));
               
            }
            
            
            
            } else if (cmd.equals("career2")) {
        	if(!client.hasPermission("cmd.career2")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
            
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            Client target = Server.get().getClient(nickname);
            boolean online = true;
            
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(nickname.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/career2 NICK:TEXT#(Fügt einen Career-Eintrag bei NICK hinzu.)"); 
                return;
            }
            
            if(msg.length() > 200) {
                client.sendButlerMessage(channel.getName(), "Der Text darf höchstens 200 Zeichen lang sein!"); 
                return;
            }
            
        
            
            client.sendButlerMessage(channel.getName(), String.format("Der Career2-Eintrag bei %s ist erfolgt.", target.getName()));
            
            if(online) {
            	target.setCareer2(String.format("%s|%s %s|", target.getCareer2(), Server.get().timeStampToDate(time), msg));
                 } else {
            	 Server.get().query(String.format("update accounts set career2='%s|%s %s|' where name='%s'", target.getCareer2(), Server.get().timeStampToDate(time), msg, target.getName()));
            
            }
            
            
            
            } else if (cmd.equals("highlights")) {
        	if(!client.hasPermission("cmd.highlights")) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
            
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            Client target = Server.get().getClient(nickname);
            boolean online = true;
            
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(nickname.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/highlights NICK:TEXT#(Fügt einen Highlight-Eintrag bei NICK hinzu.)"); 
                return;
            }
            
            if(msg.length() > 200) {
                client.sendButlerMessage(channel.getName(), "Der Text darf höchstens 200 Zeichen lang sein!"); 
                return;
            }
            
        
            
            client.sendButlerMessage(channel.getName(), String.format("Der Highlight-Eintrag bei %s ist erfolgt.", target.getName()));
            
            if(online) {
            	target.setHighlights(String.format("%s|%s %s|", target.getHighlights(), Server.get().timeStampToDate(time), msg));
            	
                } else {
            	 Server.get().query(String.format("update accounts set highlights='%s|%s %s|' where name='%s'", target.getHighlights(), Server.get().timeStampToDate(time), msg, target.getName()));
            
            }
            
            
            
            
            
            
            
            
        }  else if(cmd.equals("cl")) {
        	if(!client.hasPermission("cmd.cl") && !channel.checkCm(client.getName()) && !channel.checkMcm(client.getName()) && !channel.checkHz(client.getName())) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
        	
        	String nick = KCodeParser.escape(arg).split(":", 2)[0].trim();
        	String msg = "";

        	if(nick.isEmpty()) {
                if(channel.getCls().isEmpty()) {
                	client.sendButlerMessage(channel.getName(), "Momentan ist niemand in diesem Channel gesperrt.");
                } else {
                	client.sendButlerMessage(channel.getName(), String.format("Gesperrt sind:#%s", channel.getCls().replace("||", ", ").replace("|", "")));
                }
                
        		return;
        	}
        	
        	if (arg.length() > nick.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
        	
        	if (!nick.isEmpty() && arg.charAt(0) == '!') {
                nick = nick.substring(1).trim();
            }
        	
        	Client target = Server.get().getClient(nick);
        	boolean online = true;
        	
            if (target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(nick);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nick));
                    return;
                }
            }
            
            if(arg.charAt(0) != '!' && !online) {
            	client.sendButlerMessage(channel.getName(), userIsOffline(target));
            	return;
            }
        	
            if(arg.charAt(0) == '!') {
				if(target.checkCl(channel)) {
            		client.sendButlerMessage(channel.getName(), String.format("%s wurde entsperrt.", target.getName()));
            		channel.setCls(channel.getCls().replace(String.format("|%s|", target.getName()), ""));
            	} else {
            		client.sendButlerMessage(channel.getName(), String.format("%s ist für diesen Channel nicht gesperrt.", target.getName())); //not the right text
            	}
            	
            	return;
            }
            
            if(!target.getChannels().contains(channel)) {
            	client.sendButlerMessage(channel.getName(), String.format("%s hält sich in einem _anderen Channel_ auf.", target.getName())); //not the right text
            	return;
            }
            
            if(target.getRank() > 4) {
            	client.sendButlerMessage(channel.getName(), String.format("Sie haben nicht genügend Rechte um %s in diesem Channel zu sperren.", target.getName()));
            	return;
            }
            
            if(msg.isEmpty()) {
            	client.sendButlerMessage(channel.getName(), String.format("Bitte geben Sie die Begründung für Ihre Sperre mit an: _/cl %s:GRUND", target.getName()));
            	return;
            }

            if(msg.length() < 10) {
            	client.sendButlerMessage(channel.getName(), "Ihre Begründung muss mindestens 10 Zeichen lang sein.");
            	return;
            }
           
            if(msg.length() > 350) {
            	client.sendButlerMessage(channel.getName(), "Ihre Begründung darf höchstens 350 Zeichen lang sein.");
            	return;
            }
            
            if(target.checkCl(channel)) {
            	client.sendButlerMessage(channel.getName(), String.format("%s ist bereits in diesem Channel gesperrt.", target.getName()));
            	return;
            }
            
            msg = KCodeParser.parse(client, msg, 5, 10, 20);
            msg = Server.get().parseSmileys(client, msg);
            String title = String.format("Gesperrt im %s", channel.getName());
            
            target.setComment(time, channel.getName(), "Channellock!", client.getName(), msg);
            target.setCls(target.getCls()+1);
            target.send(Popup.create(title, title, String.format("#Sie wurden von %s für den Channel %s _gesperrt_. Diese Sperre gilt bis morgen früh.##Begründung von %s:##%s", client.getName(), channel.getName(), client.getName(), msg), 450, 275));
            target.logout("Sie wurden rausgeworfen.");
            channel.broadcastAction(Server.get().getButler().getName(), String.format("war genervt und hat %s unsanft entfernt.", target.getName()));
            channel.setCls(String.format("%s|%s|", channel.getCls(), target.getName()));
            
        } else if(cmd.equals("cmc"))  {
        	if(!client.hasPermission("cmd.cmc") && !client.checkTeam("Spiele") && !client.checkTeam("Jugendschutz") && !channel.checkCm(client.getName())  && !channel.checkMcm(client.getName()) && !channel.checkHz(client.getName())) {
            	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            	return;
        	}
            
            String recipients = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/cmc NICK:TEXT#(Fügt einen CM-Kommentar bei NICK hinzu.)";
            
            if (arg.length() > recipients.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if (recipients.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            String nickname = recipients;
            Client target = Server.get().getClient(recipients);

            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            if(msg.length() > 200) {
                client.sendButlerMessage(channel.getName(), "Der CM-Kommentar darf höchstens 200 Zeichen lang sein!"); 
                return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Der CM-Kommentar wurde°>sounds/comment.mp<° bei %s eingetragen.", target.getName()));
            target.setComment(time, channel.getName(), null, client.getName(), msg);
        
        } else if (cmd.equals("comment")) {
        	if(!client.getTeams().contains("~1|")) {
        		if(client.getRank() < 3) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
        	}
            
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/comment NICK:TEXT#(Fügt einen Adminkommentar bei NICK hinzu.)";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if (nickname.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            Client target = Server.get().getClient(nickname);

            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            if(msg.length() > 200) {
                client.sendButlerMessage(channel.getName(), "Der Adminkommentar darf höchstens 200 Zeichen lang sein!"); 
                return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Der Adminkommentar wurde bei %s eingetragen.", target.getName()));
            target.setComment(time, null, null, client.getName(), msg);
            
            

            } else if (cmd.equals("syscomment")) {
        	if(!client.getTeams().contains("~1|")) {
        		if(client.getRank() < 8) {
                	client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                	return;
            	}
        	}
            
            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/syscomment NICK:TEXT#(Fügt einen Sys-Adminkommentar bei NICK hinzu.)";
            
            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            
            if (nickname.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            Client target = Server.get().getClient(nickname);

            if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickname));
                    return;
                }
            }
            
            if(msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info); 
                return;
            }
            
            if(msg.length() > 200) {
                client.sendButlerMessage(channel.getName(), "Der Sys-Adminkommentar darf höchstens 200 Zeichen lang sein!"); 
                return;
            }
            
            client.sendButlerMessage(channel.getName(), String.format("Der Sys-Adminkommentar wurde bei %s eingetragen.", target.getName()));
            target.setSyscomments(time, null, null, client.getName(), msg);
            
            
   
            
        
        	
        } else if(cmd.equals("dream")) {
                 
            String[] l = client.getFeature("Dream");
           Feature ft = Server.get().getFeature("Dream");

           if (ft == null) {
               return;
           }

           if (l[0].equals("0")) {
                 client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
           return;  
           } 
           if (l[0].equals("1")) {
                 client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
          return;
             } 
           
        	
            arg = KCodeParser.escape(arg);
            Client target = Server.get().getClient(arg);
            
            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/dream NICK#(Von NICK träumen.)");
                return;
            }
            
            if(arg.equals("-")) {
                client.sendButlerMessage(channel.getName(), "Profileintrag gelöscht.");
                client.setDream(null);
                return;
            }
            
            
            
            if(target == null) {
                target = new Client(null);
                target.loadStats(arg);
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }
            }
            
            if(target.getName().equals(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du kannst nicht von dir selbst träumen!");
                return;
            }
            
            channel.broadcastAction("°BB°", String.format("> _°RR>_h%s|/serverpp \"|/w \"<BB>icon_dreamof...b.mx_-3.w_15.my_4.h_21.gif<°_ hat nur noch einen Traum: _°RR>_h%s|/serverpp \"|/w \"<BB°_. %s", client.getName().replace("<", "\\<"), target.getName().replace("<", "\\<"), target.getPhoto().isEmpty() ? "" : String.format("°>photos/m/%s...center_140.shadow_4.border_3.jpg<°", target.getPhoto())));
            client.setDream(target.getName());
            ft.setBan(l[1],l[3],l[4],client); // setz sperre
        } else if(cmd.equals("starlite")) {
        	      
                String[] l = client.getFeature("Starlite");
               Feature ft = Server.get().getFeature("Starlite");

               if (ft == null) {  
                  return;
               }

               if (l[0].equals("0")) {
                     client.sendButlerMessage(channel.getName(),"Du hast das "+ft.getName()+" Feature nicht.");
               return;  
               } 
               if (l[0].equals("1")) {
                     client.sendButlerMessage(channel.getName(),"Du kannst das Feature "+ft.getName()+" erst am "+timeStampToDate(Long.parseLong(l[5]))+" Uhr wieder nutzen.");
              return;
                 } 
            
        	
            arg = KCodeParser.escape(arg);
            
            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/starlite NICK#(NICK die Sterne vom Himmel holen.)");
                return;
            }
            
            if(arg.equals("!")) {
                client.sendButlerMessage(channel.getName(), "Profileintrag gelöscht.");
                
                if(client.getStarsTo() != null) {
                	Client c = Server.get().getClient(client.getStarsTo());
                
                	if(c != null) {
                		c.setStarsFrom(null);
                	} else {
                		Server.get().query(String.format("UPDATE accounts SET starsFrom=null where name='%s'", client.getStarsTo()));
                	}
                }

                client.setStarsTo(null);
                return;
            }
            
            

            Client target  = Server.get().getClient(arg);
            boolean online = true;
            
            if(target == null) {
            	online = false;
                target = new Client(null);
                target.loadStats(arg);
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }
            }
            
            if(target == client) {
                client.sendButlerMessage(channel.getName(), "Du kannst dir nicht selbst die Sterne vom Himmel holen!");
                return;
            }
            
            if(client.getStarsTo() != null) {
            	Client c = Server.get().getClient(client.getStarsTo());
            
            	if(c != null) {
            		c.setStarsFrom(null);
            	} else {
            		Server.get().query(String.format("UPDATE accounts SET starsFrom=null where name='%s'", client.getStarsTo()));
            	}
            }
            
            channel.broadcastAction("°BB°", String.format("> _°RR>_h%s|/serverpp \"|/w \"<BB°_ holt für _°RR>_h%s|/serverpp \"|/w \"<BB°_ die Sterne vom Himmel! °>abo/icon_starlite_%s.gif<°", client.getName().replace("<", "\\<"), target.getName().replace("<", "\\<"), image(client, target)));
            
            if(online) {
            	target.sendButlerMessage(channel.getName(), "Um die erhaltenen Sterne wieder zurückzugeben, kannst du _/starlite !_ eingeben.");
                target.setStarsFrom(client.getName());
            } else {
            	Server.get().query(String.format("UPDATE accounts SET starsFrom='%s' where name='%s'", client.getName(), target.getName()));
            }
            
        	client.setStarsTo(target.getName());
        	ft.setBan(l[1],l[3],l[4],client); // setz sperre
        
        	
        } else if (cmd.equals("update")) {
            update.functionMake(client, channel, arg);
        	
    	   
        }else if (cmd.equals("edit") || cmd.equals("e")) {
        	if(arg.toLowerCase().equals("setlmc") && client.hasPermission("cmd.edit.setlmc")) {
        		client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht als deinen Lieblings-MyChannel setzen.", channel.getName()));
        		return;
        	}
        	
        	if(!client.hasPermission("cmd.edit")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
            String[] lala = arg.split(" ", 2);
            String a = lala[0].trim();
    		
            if (a.equals("description")) {
                String text = "";
                String b = lala[1].trim();

                if(b.equals("E-Mail verifizieren")) {
                	client.send(PacketCreator.createVerifyWindow(client.getName(), client.getEmail(), 1));
                	return;
                }
                
                if(b.equals("changever")) {
                	client.send(PacketCreator.createVerifyWindow(client.getName(), "", 1));
                	return;
                }
                
                if(b.equals("cancelver")) {
                	Popup popup = new Popup("Verifizierung abgebrochen", "Verifizierung abgebrochen", String.format("Du hast die Verifizierung der E-Mail-Adresse %s erfolgreich abgebrochen.", client.getEmail()), 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                    
                    client.setEmailVerify((byte) 0);
                	return;
                }
                
                if(b.equals("Verifizierung läuft...")) {
                	Popup popup = new Popup("Laufende E-Mail-Verifizierung", "Laufende E-Mail-Verifizierung", String.format("Aktuell läuft die Verifizierung der E-Mail-Adresse %s für deinen Nick. Verwende die Buttons weiter unten, um die Verifizierung zu ändern oder abzubrechen.", client.getEmail()), 400,250);
                    Panel panel = new Panel();
                    panel.addComponent(new Button("   OK   "));
                    Button button1 = new Button("Verifizierung abbrechen");
                    button1.setCommand("/edit description cancelver");
                    Button button2 = new Button("Verifizierung ändern");
                    button2.setCommand("/edit description changever");
                    panel.addComponent(button1);
                    panel.addComponent(button2);
                    popup.addPanel(panel);
                    client.send(popup.toString());
                	return;
                }
                
                if (b.equals("Passwort")) {
                    text = "Um deine Änderungen zu speichern, _musst_ du unter Passwort dein bisheriges _Passwort_ angeben.";
                } else if (lala[1].contains("Neues Passwort")) {
                    text = "Wenn du dein Passwort ändern willst, gib unter _Neues Passwort_ und unter _Neues Passwort (2x)_ das gewünsche Passwort ein.";
                } else if (b.equals("E-Mail verifiziert?")) {
                    text = "Unter E-Mail verifiziert findest du heraus, ob deine aktuell eingetragene E-Mail-Adresse bereits verifiziert ist. Sollte das nicht der Fall sein, so erscheint ein Button E-Mail verifizieren, mit dessen Hilfe du die Verifizierung deiner eigenen E-Mail-Adresse einleiten kannst.##Über die verifizierte E-Mail-Adresse kannst du im Falle eines Passwortverlustes ein neues Passwort für den eigenen Nick beantragen.";
                }else if (b.equals("Geschlecht")) {
                    text = "Hier kannst du ein _Suchprofil_ angeben. Alle Mitglieder in einem Channel, die auf dein Suchprofil passen, werden in der Liste mit einem °>mentor_01.gif<° angezeigt.##Trag unter _Geschlecht_ das von dir dazu bevorzugte Geschlecht ein.";
                } else if (b.equals("Alter       von")) {
                    text = "Hier kannst du ein _Suchprofil_ angeben. Alle Mitglieder in einem Channel, die auf dein Suchprofil passen, werden in der Liste mit einem °>mentor_01.gif<° angezeigt.##Trag unter _Alter von - bis_ zwei Altersgrenzen ein, in deren Bereich du Bekanntschaften suchst.";
                } else if (b.equals("Motiv")) {
                    text = "Hier kannst du ein _Suchprofil_ angeben. Alle Mitglieder in einem Channel, die auf dein Suchprofil passen, werden in der Liste mit einem °>mentor_01.gif<° angezeigt.##Trag unter _Motiv_ ein, was du dir von einer Bekanntschaft versprichst.";
                } else if (b.equals("Entfernung bis")) {
                    text = "Hier kannst du ein _Suchprofil_ angeben. Alle Mitglieder in einem Channel, die auf dein Suchprofil passen, werden in der Liste mit einem °>mentor_01.gif<° angezeigt.##Trag unter _Entfernung bis_ ein, wie weit eine neue Bekanntschaft höchstens von dir entfernt wohnen soll. Diese Einstellung wird nur dann aktiv, wenn du selbst eine _gültige Postleitzahl_ angegeben hast.";
                } else if (b.equals("Alter")) {
                    text = "Gib hier dein _Alter_ an. Sofern du ein Geburtsdatum angibst, wird diese Angabe ignoriert und dein Alter anhand des Geburtsdatums bestimmt.##Wenn du dein Alter angibst, dann sollte der Eintrag immer deinem wirklichen Alter entsprechen, der Eintrag kann später über natürliche Alterung hinaus nicht mehr korrigiert werden.";
                } else if (b.equals("Dein Geschlecht")) {
                    text = "Trag unter _Geschlecht_ ein, ob du weiblich oder männlich bist.";
                } else if (b.equals("Vergeben")) {
                    text = "Unter _Vergeben_ kannst du z.B. angeben, ob du Single, verliebt, vergeben oder sogar verheiratet bist. Diese Information ist meistens _sehr interessant_ für die anderen Mitglieder.";
                } else if (b.equals("Stadt")) {
                    text = "Trag unter _Stadt_ ein, in welcher Stadt bzw. in welchem Ort du wohnst.";
                } else if (b.equals("Land")) {
                    text = "Trag unter _Land_ ein, in welchem Land du lebst.";
                } else if (b.equals("Hobbys")) {
                    text = "Deine wichtigsten Hobbys solltest du unter _Hobbys_ angeben.";
                } else if (b.equals("Motto")) {
                    text = "Unter _Motto_ kannst du noch einen persönlichen Text setzen, der nicht in die anderen Feldern passte.";
                } else if (b.equals("Realname")) {
                    text = "Wenn du möchtest, kannst du unter _Realname_ deinen echten Vornamen angeben.";
                } else if (b.equals("Geburtstag")) {
                    text = "Hier solltest du deinen _Geburtstag_ angeben.#Wenn dein Geburtstag angegeben ist, hat das folgende Auswirkungen:#- Dein Alter wird automatisch bestimmt#- Du kannst dein Sternzeichen anzeigen lassen (unter Privatsphäre)##Falls du nicht möchtest, dass dein Geburtsdatum angezeigt wird, kannst du die Anzeige unter Privatsphäre ausstellen.#Sobald dein Alter verifiziert ist, kann es nicht mehr geändert werden.";
                } else if (lala[1].contains("Postleitzahl")) {
                    text = "Trag dein _Land_ und deine _Postleitzahl_ ein.#Deine Postleitzahl ist für _niemanden_ einsehbar. Wenn du eine Postleitzahl angegeben hast, dann kannst du sehen (mit der /w Funktion oder Rechtsklick), wie weit andere Mitglieder, die auch eine Postleitzahl angegeben haben, von dir entfernt wohnen (in Kilometern).##Es ist nicht notwendig, dass du deine ganze Postleitzahl angibst - _es reichen die ersten drei Ziffern._";
                } else if (b.equals("Job")) {
                    text = "Unter _Job_ kannst du eintragen, was du arbeitest.";
                } else if (b.equals("Email")) {
                    text = "Trage hier deine E-Mail Adresse ein, um zu einen späteren Moment das Passwort an diese E-Mail Adresse schicken zu lassen.";
                } else if (b.contains("Spitznamen")) {
                    text = "Trag hier mit Komma getrennt alle Namen ein, unter denen du im Chat angesprochen werden willst.##Das Chatfenster wird diese Namen in allen Chattexten _fett hervorheben_.";
                } else if (b.equals("Lieblingschannel")) {
                    text = "Hier kannst du deinen _Lieblingschannel_ angeben.#Dein Lieblingschannel wird zum einen in _deiner Info_ (/w) angezeigt.#Der größte Vorteil ist allerdings, dass du deinen Lieblingschannel auch immer noch dann betreten kannst, wenn er _eigentlich überfüllt_ ist.##_Beachte_:#Deinen Lieblingschannel kannst du nur einmal im Monat ändern.";
                } else if (b.equals("Eigener Channel")) {
                    text = "Klick auf 'Editieren', um deinen eigenen Channel einzurichten und die Einstellungen daran zu ändern.";
                } else if (b.equals("?")) {
                    text = "In diesem Fenster kannst du die Daten und Einstellungen zu deinem Nick verändern. Nach deiner Änderung musst du auf _Speichern_ klicken, um die Daten abzuspeichern.##Um _weitere Hilfe_ zu den einzelnen Feldern zu erhalten, klick einfach auf den _Text links neben einem Eingabefeld_.";
                } else if (b.contains("verifizieren")) {
                    CommandParser.parse("/verify", client,client.getChannel(), false);
                }
                	
              //  client.send(PopupNewStyle.create("Info", "Info", text, 450, 275));

            PopupNewStyle popup = new PopupNewStyle("Info", "Info", text, 400, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        client.send(popup.toString());
                          
       
                
                
                
            } else if(a.equals("verify")) {
                client.send(PopupNewStyle.create("Info", "Info", "Unter E-Mail verifiziert findest du heraus, ob deine aktuell eingetragene E-Mail-Adresse bereits verifiziert ist. Sollte das nicht der Fall sein, so erscheint ein Button E-Mail verifizieren, mit dessen Hilfe du die Verifizierung deiner eigenen E-Mail-Adresse einleiten kannst.##Über die verifizierte E-Mail-Adresse kannst du im Falle eines Passwortverlustes ein neues Passwort für den eigenen Nick beantragen.", 450, 275));
            } else {
            	arg = KCodeParser.escape(arg);
            	Client target = arg.isEmpty() || client.getRank() < 11 ? client:Server.get().getClient(arg);
            	
            	if(target == null) {
            		target = new Client(null);
            		target.loadStats(arg);
            		
            		if(target.getName() == null) {
            			client.sendButlerMessage(channel.getName(), unknownUser(arg));
            			return;
            		}
            	}
            	
            	client.send(tools.PacketCreator.createEditWindow(target)); 
            }
        

        	
       } else if(cmd.equals("foreverfriends")) {

            String[] l = client.getFeature("Foreverfriends");
           Feature ft = Server.get().getFeature("Foreverfriends");

           if (ft == null) {
               return;
           }
        	
            arg = KCodeParser.escape(arg);
            Client target;
            target = Server.get().getClient(arg);
            
            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/foreverfriends NICK#(Ewige Freundschaft mit NICK schließen.)");
                return;
            }
            
            if(arg.equals("-")) {
                client.sendButlerMessage(channel.getName(), "Profileintrag gelöscht.");
                client.setFriends(null);
                return;
            }
            
          
            	
            if(target == null) {
                target = new Client(null);
                target.loadStats(arg);
                
                if(target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }
            }
            
            if(target.getName().equals(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du willst ewige Freundschaft mit dir selbst schließen?!");
                return;
            }
            
            channel.broadcastAction("°BB°", String.format("> _°RR>_h%s|/serverpp \"|/w \"<BB°_ und _°RR>_h%s|/serverpp \"|/w \"<BB°_ schließen ewige Freundschaft. °>icon_friendsforever...b.my_6.mx_-4.w_29.h_14.gif<°", client.getName().replace("<", "\\<"), target.getName().replace("<", "\\<")));
            ft.setBan(l[1],l[3],l[4],client); 
            client.setFriends(target.getName());
        	
        } else if (cmd.equals("h") || cmd.equals("?") || cmd.equals("help") || cmd.equals("hilfe")) {
        	if(!client.hasPermission("cmd.help")) {
        		client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
        		return;
        	}
        	
        	arg = KCodeParser.escape(arg);
        
            if(arg.isEmpty() || arg.equals("help")) {
                StringBuilder h = new StringBuilder();
                int dD = 0;
                StringBuilder anicks = new StringBuilder();
                h.append("Du bist _").append(client.getRankLabel(client.getRank())).append("_ und hast folgende Rechte:##");
                
                for(String function : Server.functions.keySet()) {
                	String[] split = Server.functions.get(function);
                	byte rank = Byte.parseByte(split[0]);
                	String text = split[1];
                	
                	if(client.getRank() >= rank) {	
                		h.append("_/").append(function).append("_#(").append(text).append(")##");
                	}
                }
                
                PoolConnection pcon = ConnectionPool.getConnection();
                Statement ps = null;
                
                try {
                    Connection con = pcon.connect();
                    ps = con.createStatement();
                    ResultSet rs = ps.executeQuery(String.format("SELECT name FROM `accounts` where `rank` >= 6 and `rank` <= 7 and `name` != '%s'", Server.get().getButler().getName()));
                    
                    while(rs.next()) {
                        anicks.append(dD!=0?", ":"").append("°>_h").append(rs.getString("name")).append("|/serverpp \"|/w \"<°");
                        dD++;
                    }
            
                    h.append("Admins sind derzeit:#").append(anicks.toString());
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
                
                h.append("##(insgesamt ").append(dD).append(" Admins)");
                
               
                 Popup popup = new Popup("Hilfe", "Hilfe",h.toString(), 450, 300);
        	 Panel panel = new Panel();
                 Button buttonMessage3 = new Button("   OK   ");
                 buttonMessage3.setStyled(true);
                 panel.addComponent(buttonMessage3);
                 popup.addPanel(panel);
                 popup.setModern(1);
                 client.send(popup.toString());
                 return;
                
               
            }
            
            String argt = arg.toLowerCase();

            if(!Server.help.containsKey(argt)) {
            	client.sendButlerMessage(channel.getName(), String.format(help[zufall.nextInt(help.length)], arg));
                return;
            }
            
            String[] split = Server.help.get(argt);
            String title = split[0];
            String text = split[1];
            
            if(arg.toLowerCase().contains("-team".toLowerCase())) {
            	String team = arg.replace("-team", "").toLowerCase();

            	if(Settings.getTeams().toLowerCase().contains(String.format("|%s|", team))) {
            		StringBuilder tl = new StringBuilder();
            		StringBuilder tm = new StringBuilder();
            		int num1 = 1, num2 = 1;
            		
            		PoolConnection pcon1 = ConnectionPool.getConnection();
                    PreparedStatement ps1 = null;
                    
                    try {
                        Connection con1 = pcon1.connect();
                        ps1 = con1.prepareStatement("SELECT `name`, `teams` FROM `accounts` WHERE `teams` != ''");
                        ResultSet rs1 = ps1.executeQuery();
                        
                        while(rs1.next()) {
                        	String name = rs1.getString("name");
                        	String tea = rs1.getString("teams");
                        	
                        	if(tea.toLowerCase().contains(String.format("|%s~0|", team))) {
                        		if(num1 != 1) {
                            		tm.append(", ");
                            	}

                            	tm.append("°>_h");
                        		tm.append(name.replace("<", "\\<"));
                        		tm.append("|/serverpp \"|/w \"<°");
                        		num1++;
                        	} else if(tea.toLowerCase().contains(String.format("|%s~1|", team))) {
                            	if(num2 != 1) {
                            		tl.append(", ");
                            	}
                            	
                            	tl.append("°>_h");
                        		tl.append(name.replace("<", "\\<"));
                        		tl.append("|/serverpp \"|/w \"<°");
                        		num2++;
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (ps1 != null) {
                            try {
                                ps1.close();
                            } catch (SQLException e) {
                            }
                        }

                        pcon1.close();
                    }

                    String tlstring = tl.toString();
                    String tmstring = tm.toString();

                    text = text.replace("[TL]", tlstring.isEmpty()?"Keine":tlstring);
                    text = text.replace("[TM]", tmstring.isEmpty()?"Keine":tmstring);
            	}
            }
            
            
            Popup popup = new Popup(title,title,text, 450, 310);
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   Schließen   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            popup.setModern(1);
            client.send(popup.toString());
            return;
       
            
            
           /*
      } else if(cmd.equals("mychannel")) {
      funktionen.mychannel.functionMake(client,channel,arg);
         */    
            
      } else if(cmd.equals("heart")) {
      funktionen.heart.functionMake(client,channel,arg);
      } else if (cmd.equals("his")) {
      funktionen.his.functionMake(client,channel,arg);
      } else if (cmd.equals("ig") || cmd.equals("ignore")) {
      funktionen.ig.functionMake(client,channel,arg);
      } else if(cmd.equals("iplist")) {        
      funktionen.iplist.functionMake(client,channel,arg);
      } else if(cmd.equals("statistik")) {
      funktionen.statistik.functionMake(client,channel,arg);	
      } else if (cmd.equals("kiss")) {
      funktionen.kiss.functionMake(client,channel,arg);	
      } else if (cmd.equals("kizz")) {
      funktionen.kizz.functionMake(client,channel,arg);	
      } else if (cmd.equals("knuddel")) {
      funktionen.knuddel.functionMake(client,channel,arg);   
      } else if(cmd.equals("knuddelz")) {
      funktionen.knuddelz.functionMake(client,channel,arg);   
      }else if (cmd.startsWith("nicktag")) {
      features.nicktag.functionMake(client,channel,arg,cmd);
      } else if(cmd.equals("kissall")) {
      features.kissall.functionMake(client,channel,arg);            
      } else if(cmd.equals("loginlist")) {
      funktionen.loginlist.functionMake(client,channel,arg);   
      } else if(cmd.equals("lotto")) {
      funktionen.lotto.functionMake(client,channel,arg);   
      } else if(cmd.equals("drew")) {
      funktionen.drew.functionMake(client,channel,arg);
      } else if(cmd.equals("mute") || cmd.equals("cmute")) {
      funktionen.mute.functionMake(client,channel,arg,cmd);
      }else if (cmd.equals("me")) {
      funktionen.me.functionMake(client,channel,arg);	
      } else if(cmd.equals("news")) {
      funktionen.news.functionMake(client,channel,arg);	
      } else if (cmd.equals("p")) {
      funktionen.p.functionMake(client,channel,arg);
      } else if(cmd.equals("ping")) {
      funktionen.ping.functionMake(client,channel,arg);
      } else if (cmd.equals("points")) {        	
      funktionen.points.functionMake(client,channel,arg);
      } else if (cmd.equals("poll")) {
      funktionen.poll.functionMake(client,channel,arg);
      } else if(cmd.equals("readme")) {
      funktionen.readme.functionMake(client,channel,arg);
      } else if(cmd.equals("readmehis")) {
      funktionen.readmehis.functionMake(client,channel,arg);
      } else if(cmd.equals("register")) {
      funktionen.register.functionMake(client,channel,arg);
      } else if(cmd.equals("wm")) {        	
      funktionen.wm.functionMake(client,channel,arg);
      } else if (cmd.equals("rose")) {
      funktionen.rose.functionMake(client,channel,arg);
      } else if (cmd.equals("save")) {
      funktionen.save.functionMake(client,channel,arg);
      } else if (cmd.equals("serverpp")) {
      funktionen.serverpp.functionMake(client,channel,arg);
      } else if (cmd.equals("smileyedit")) {
      funktionen.smileyedit.functionMake(client,channel,arg);
      } else  if (cmd.equals("fset")) {
      funktionen.fset.functionMake(client,channel,arg);  
      } else  if (cmd.equals("entwicklung")) {
      funktionen.entwicklung.functionMake(client,channel,arg);  
      } else if (cmd.equals("shop")) {
      funktionen.shop.functionMake(client,channel,arg);     
      } else if (cmd.equals("code")) {
       funktionen.code.functionMake(client,channel,arg);   
      } else if (cmd.equals("hero")) {
      features.hero.functionMake(client,channel,arg); 
      } else if (cmd.equals("skymsg")) {
      features.skymsg.functionMake(client,channel,arg); 
      } else if (cmd.equals("tinkle")) {
      features.tinkle.functionMake(client,channel,arg);  
      } else if (cmd.equals("wash")) {
      features.wash.functionMake(client,channel,arg);  
      }else if (cmd.equals("moskito")) {
      features.moskito.functionMake(client,channel,arg);  
      } else if(cmd.equals("kissall")) {
      features.kissall.functionMake(client,channel,arg);    
      } else if (cmd.equals("smileyvisit")) {
      funktionen.smileyvisit.functionMake(client,channel,arg);
      } else if (cmd.equals("smileypress")) {
      features.smileypress.functionMake(client,channel,arg);
      }else if (cmd.equals("elementrechner")) {
      features.elementrechner.functionMake(client,channel,arg);
      } else if (cmd.equals("auctionme")) {
      features.auctionme.functionMake(client,channel,arg);
      } else if (cmd.equals("fotoalbum")) {
      funktionen.fotoalbum.functionMake(client,channel,arg);
      } else if (cmd.equals("forum")) {
      funktionen.forum.functionMake(client,channel,arg);
      } else if (cmd.equals("dailylogin")) {
      funktionen.dailylogin.functionMake(client,channel,arg);         
      } else if(cmd.equals("devlog")) {
      funktionen.devlog.functionMake(client,channel,arg);                             
      } else if (cmd.equals("chatlogs")) {         
      funktionen.chatlogs.functionMake(client,channel,arg);      
      } else if (cmd.equals("butlerm")) {
      funktionen.butlerm.functionMake(client,channel,arg);
      } else if(cmd.equals("fa")) {
      funktionen.fa.functionMake(client,channel,arg);
      }else if(cmd.equals("ban")) {
      funktionen.ban.functionMake(client,channel,arg);
     } else if (cmd.equals("sperre")) {
    sperre.aktion(arg, client, channel); 
      } else if (cmd.equals("channelrules")) {
      funktionen.channelrules.functionMake(client,channel,arg);
      }else if(cmd.equals("admincall") || cmd.equals("notruf")) {
      funktionen.admincall.functionMake(client,channel,arg,cmd);        	
      } else if(cmd.equals("clearw")) {
      funktionen.clearw.functionMake(client,channel,arg);   
      } else if(cmd.equals("clearsyscomments")) {
        funktionen.clearsyscomments.functionMake(client, channel, arg);
      } else if(cmd.equals("cmtest")) {
      funktionen.cmtest.functionMake(client,channel,arg);  
      } else if(cmd.equals("email")) {
      funktionen.email.functionMake(client,channel,arg);
      } else if(cmd.equals("action")) {
      funktionen.action.functionMake(client,channel,arg);
      } else if(cmd.equals("beta")) {
      funktionen.beta.functionMake(client, channel, arg);
      } else if(cmd.equals("cronjob")) {
      funktionen.cronjob.functionMake(client, channel, arg);
      } else if(cmd.equals("newsedit")) {
      funktionen.newsedit.functionMake(client, channel, arg);
      } else if(cmd.equals("snp")) {
      funktionen.snp.functionMake(client, channel, arg);
      } else if(cmd.equals("checkmail")) {
      funktionen.checkmail.functionMake(client, channel, arg);
      } else if(cmd.equals("checkip")) {
      funktionen.checkip.functionMake(client, channel, arg);
      } else if(cmd.equals("km")) {
      funktionen.km.functionMake(client, channel, arg);
      } else if(cmd.equals("ainfo")) { 
      funktionen.ainfo.functionMake(client, channel, arg);
      } else if(cmd.equals("showfriends")) { 
      funktionen.showfriends.functionMake(client, channel, arg);
      } else if(cmd.equals("adminhelp")) { 
      funktionen.adminhelp.functionMake(client, channel, arg);
      } else if(cmd.equals("showcareer2")) { 
      funktionen.showcareer2.functionMake(client, channel, arg);
      } else if(cmd.equals("showhighlights")) { 
      funktionen.showhighlights.functionMake(client, channel, arg);
      } else  if (cmd.equals("profilvisit")) {
      funktionen.profilvisit.functionMake(client, channel, arg);
      } else if(cmd.equals("hz")) {
      funktionen.hz.functionMake(client, channel, arg);
      } else if (cmd.equals("deletemynick")) {
      funktionen.deletemynick.functionMake(client, channel, arg);
      } else if(cmd.equals("openurl")) {
      funktionen.openurl.functionMake(client, channel, arg);
      } else if (cmd.equals("diceo")) {
      funktionen.diceo.functionMake(client, channel, arg);
      } else if (cmd.equals("broadcast")) {
      funktionen.broadcast.functionMake(client, channel, arg);
      } else if(cmd.equals("l")) {
      funktionen.l.functionMake(client, channel, arg);
      } else if (cmd.equals("todo")) {
      funktionen.todo.functionMake(client, channel, arg);
      } else if(cmd.equals("flame")) {
      funktionen.flame.functionMake(client, channel, arg);
      } else if(cmd.equals("kick") || cmd.equals("k")) {
      funktionen.kick.functionMake(client, channel, arg);
      } else if (cmd.equals("gb")) {
      funktionen.gb.functionMake(client, channel, arg);
      }  else if (cmd.equals("hp")) {
      funktionen.hp.functionMake(client,channel,arg);
      } else if (cmd.equals("foto")) {
      funktionen.foto.functionMake(client,channel,arg);
      } else if (cmd.equals("turnedhead")) {       
      features.turnedhead.functionMake(client,channel,arg);
      } else if (cmd.equals("rhapsody")) {        
      features.rhapsody.functionMake(client,channel,arg);
      } else if (cmd.equals("f")) {        
      funktionen.f.functionMake(client,channel,arg);
      } else if (cmd.equals("adore")) {
      features.adore.functionMake(client,channel,arg);
      } else if (cmd.equals("loveyou")) {
      features.loveyou.functionMake(client,channel,arg);
      } else if (cmd.equals("neveralone")) {
      features.neveralone.functionMake(client,channel,arg);
      } else if (cmd.equals("loveletter")) {
      features.loveletter.functionMake(client,channel,arg);
      } else if (cmd.equals("bomb")) {
      features.bomb.functionMake(client,channel,arg);
      } else if (cmd.equals("schutzschild")) {
      features.schutzschild.functionMake(client,channel,arg);
      } else if (cmd.equals("exit")) {
      features.exit.functionMake(client,channel,arg);
      } else if (cmd.equals("coin")) {
      features.coin.functionMake(client,channel,arg);
      }else if (cmd.equals("greedypig")) {
      features.greedypig.functionMake(client,channel,arg);
      } else if (cmd.equals("konto")) {
      features.konto.functionMake(client,channel,arg);
      } else if(cmd.equals("partnerlook")) {
      features.partnerlook.functionMake(client, channel, arg);
       } else if(cmd.equals("think")) {
      features.think.functionMake(client, channel, arg);
      } else if(cmd.equals("fanifybutler")) {
      features.fanifybutler.functionMake(client, channel, arg);
      } else if(cmd.equals("lovepotion")) {
      features.lovepotion.functionMake(client, channel, arg);
       } else if(cmd.equals("evil")) {
      features.evil.functionMake(client, channel, arg);
       } else if(cmd.equals("blume")) {
      features.blume.functionMake(client, channel, arg);
      } else if(cmd.equals("ring")) {
      features.ring.functionMake(client, channel, arg);
        } else if(cmd.equals("reissack")) {
      features.reissack.functionMake(client, channel, arg);
       } else if(cmd.equals("secretkiss")) {
      features.secretkiss.functionMake(client, channel, arg);
       } else if(cmd.equals("luckywish")) {
      features.luckywish.functionMake(client, channel, arg);
      } else if(cmd.equals("amorsarrow")) {
      features.amorsarrow.functionMake(client, channel, arg);
      } else if (cmd.equals("unset")) {
      funktionen.unset.functionMake(client,channel,arg);       
      }else if (cmd.equals("smileyset")) {
      funktionen.set.functionMake(client,channel,arg);       
      } else if (cmd.equals("showcareer")) {
      funktionen.showcareer.functionMake(client,channel,arg); 
      } else if (cmd.equals("shutdown")) {
      funktionen.shutdown.functionMake(client,channel,arg); 
      } else if(cmd.equals("sql")) {
      funktionen.sql.functionMake(client,channel,arg); 
      } else if (cmd.equals("time")) {
      funktionen.time.functionMake(client,channel,arg); 
      } else if (cmd.equals("top")) {
      funktionen.top.functionMake(client,channel,arg); 
      } else if (cmd.equals("topic")) {
      funktionen.topic.functionMake(client,channel,arg); 
      } else if(cmd.equals("verify")) {
      funktionen.verify.functionMake(client,channel,arg); 
      } else if (cmd.equals("wc")) {
      funktionen.wc.functionMake(client,channel,arg); 
      } else if (cmd.equals("wordmix")) {
      funktionen.wordmix.functionMake(client,channel,arg); 
        } else {
        	if(!fromGame && channel.getGame() != null && !channel.getGame().parseCommand(message, client) || cmd.equals("po") || cmd.equals("io") || cmd.equals("importantonly") || cmd.equals("privateonly")) {
        		return;
        	}
           //     client.sendButlerMessage(channel.getName(), String.format("°RR°_Es ist ein Fehler aufgetreten_§.#Bitte melde den Fehler im °BB°_°>Forum|/forum<°§ oder wende dich an einen Administrator.##Wir entschuldigen uns für die Unannehmlichkeiten.", cmd));
                  client.sendButlerMessage(channel.getName(), String.format("Die Funktion /%s gibt's hier leider nicht.", cmd));
        }
    }
}

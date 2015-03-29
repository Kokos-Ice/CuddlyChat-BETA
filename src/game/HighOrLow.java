	

    package game;
     
import static funktionen.f.time;
    import java.util.List;
    import java.util.Random;
    import starlight.*;
    import tools.*;
     
    public class HighOrLow implements Game {
        private Channel channel;
     
        public HighOrLow(Channel channel) {
            this.channel = channel;
        }
     
       
        public static void highOrLowWrong(Client client, Channel channel, int runde, int points, int zahl, int win) {
     
            if(client.getHolsperre() ==2) {
                client.sendButlerMessage(channel.getName(), String.format("°RR°_Leider falsch!§ Die neue Zahl ist _°18RR>{textborder}<°%s§.#Du hast es bis zur _%s. Runde_ geschafft (Gewinn: _%s_ Punkte%s).", zahl, client.getHighOrLowRound(), points, client.getHolknuddelsrunde()==1?String.format(" und °BB°_%s Knuddels§", win):""));
                client.sendButlerMessage(channel.getName(), String.format("Du hast diese Woche bereits 2x den High or Low Jackpot geknackt, weswegen dein Ergebnis (Rekord) offiziell nicht gewertet wird."));
            }else
            
         if(client.getHolsperre() ==0 || client.getHolsperre() ==1) {   
    client.sendButlerMessage(channel.getName(), String.format("°RR°_Leider falsch!§ Die neue Zahl ist _°18RR>{textborder}<°%s§.#Du hast es bis zur _%s. Runde_ geschafft (Gewinn: _%s_ Punkte%s).", zahl, client.getHighOrLowRound(), points, client.getHolknuddelsrunde()==1?String.format(" und °BB°_%s Knuddels§", win):""));
     
    if(channel.getHolrunde() < runde) {
    String knuddelwin = "";
    if("High or Low Free".equals(channel.getName()) && runde > 2) {
    knuddelwin = "";
    } else
    if("High or Low".equals(channel.getName()) && runde > 2) {
    knuddelwin = "(Einsatz: 1 Knuddel)";
    } else
    if("High or Low Pro".equals(channel.getName()) && runde > 2) {
    knuddelwin = "(Einsatz: 5 Knuddels)";
    } else
    if("High or Low Champion".equals(channel.getName()) && runde > 2) {
    knuddelwin = "(Einsatz: 10 Knuddels)";
    }
    
    if(client.getHolsperre() == 2) {
        
    }
    
    
      if(client.getHolsperre() == 0 || client.getHolsperre() ==1) {
       
        channel.increaseHoljackpot(1);
        
    channel.broadcastButlerMessage(new StringBuilder().append("°%-1>firework...b.mx_-9.w_52.h_40.gif<° °RR°_").append(client.getName()).append(" hat einen neuen High or Low Rekord aufgestellt! °>firework...b.mx_-9.w_52.h_40.gif<#°").append(client.getGenderLabel()).append(" hat es bis Runde ").append(runde).append(" geschafft. Im Jackpot befinden sich ").append(channel.getHoljackpot()).append(" Knuddels.").toString());
    channel.setTopic(String.format("In diesem Channel kann High or Low gespielt werden.%s °>gt.gif<° °BB>Jetzt mitspielen!|/hol start<r°##%s hält den momentanen Rekord mit Runde %s (Jackpot: %s Knuddels).",knuddelwin, client.getName(), runde, channel.getHoljackpot()));
    
    if(!channel.getHolnick().equals(client.getName())) {
    Server.get().newMessage(Server.get().getButler().getName(), channel.getHolnick(), "High or Low Rekord geknackt", String.format("Dein High or Low Rekord wurde soeben von %s mit %s Runden geknacht.", client.getName(), runde), System.currentTimeMillis()/1000);
    }
    channel.setHolrunde(runde);
    channel.setHolnick(client.getName());
    }}}
     
    client.setHighOrLowChannel(null);
    client.setHighOrLowRound((byte)0);
    client.increaseHol(points);
    if (client.getHol() > 99 && client.getHolrank() ==0) {
                     client.setHighlights(String.format("%s|%s Erreichte 100 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)1);
                 }
    if (client.getHol() > 299 && client.getHolrank() ==1) {
                     client.setHighlights(String.format("%s|%s Erreichte 300 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)2);
                 }
    if (client.getHol() > 499 && client.getHolrank() ==2) {
                     client.setHighlights(String.format("%s|%s Erreichte 500 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)3);
                 }
    if (client.getHol() > 999 && client.getHolrank() ==3) {
                     client.setHighlights(String.format("%s|%s Erreichte 1.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)4);
                 }
    if (client.getHol() > 2999 && client.getHolrank() ==4) {
                     client.setHighlights(String.format("%s|%s Erreichte 3.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)5);
                 }
    if (client.getHol() > 4999 && client.getHolrank() ==5) {
                     client.setHighlights(String.format("%s|%s Erreichte 5.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)6);
                 }
    if (client.getHol() > 9999 && client.getHolrank() ==6) {
                     client.setHighlights(String.format("%s|%s Erreichte 10.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)7);
                 }
    if (client.getHol() > 19999 && client.getHolrank() ==6) {
                     client.setHighlights(String.format("%s|%s Erreichte 20.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)8);
                 }
    if (client.getHol() > 29999 && client.getHolrank() ==6) {
                     client.setHighlights(String.format("%s|%s Erreichte 30.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)9);
                 }
    if (client.getHol() > 49999 && client.getHolrank() ==6) {
                     client.setHighlights(String.format("%s|%s Erreichte 50.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)10);
                 }
    if (client.getHol() > 99999 && client.getHolrank() ==6) {
                     client.setHighlights(String.format("%s|%s Erreichte 100.000 Punkte im Spiel High or Low|", client.getHighlights(), Server.get().timeStampToDate(time)));
                     client.setHolrank((byte)11);
                 }
     
    if(client.getHolknuddelsrunde()==1 && runde >1) {
     
    client.increaseKnuddels(win);
    channel.increaseHoljackpot(1);
    }
     
    client.setHolknuddelsrunde((byte)0);
    }
           
        public boolean parsePublicMessage(String message, Client client) {
                    return false;
        }
     
        public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
                    return true;
        }
     
        public void onLeave(Client client) {
        }
     
            public void onJoin(Client client) {
            }
     
            public boolean parseCommand(String message, Client client) {
            String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
            String cmd = command.toLowerCase();
            String arg = "";
            Random zufall = new Random();
           
            if (message.length() > cmd.length() + 1) {
                arg = message.substring(message.indexOf(' ') + 1);
            }
     
            int Anzahlspe = Server.countChars(arg, '°');
           
            if (Anzahlspe == 1) {
                    String[] argus = arg.split("°", 2);
                    arg = argus[0];
            }
           
            if(!cmd.equals("hol")) {
                    return true;
            }
           
            if(!client.hasPermission("cmd.hol")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return false;
            } else if(client.getSpielsperre() != 0) {
                    client.sendButlerMessage(channel.getName(), "Du bist momentan für alle Spiele gesperrt.");
                    return false;
          /*  } else if(client.getHolsperre() >= 2) {
                    client.sendButlerMessage(channel.getName(), "Du hast diese Woche bereits zwei mal den High or Low Jackpot geknackt. Aus diesem Grund kannst du erst am Sonntag wieder spielen.");
                    return false;*/
            } else {
                 boolean havejp = false;  
               String jpchannel = "";
                for(Channel x : Server.get().getChannels()) {
                    if (x != channel) {
                      if (x.getHolnick().equals(client.getName())) {
                          havejp = true;
                          jpchannel = x.getName();
                      }  
                        
                    }
                    
                }
                if (havejp) {
                    client.sendButlerMessage(channel.getName(),"Du hast bereits den Rekord im Channel "+jpchannel+" aufgestellt. Daher ist das spielen nurnoch in diesem Channel für den heutigen Tag möglich.");
                   return false;
                }
            
                
                
                
                    Random xd = new Random();
    int zahl = xd.nextInt(100)+1;
     
    if(zahl == client.getHighOrLowNumber()) {
    if(zahl == 100) {
    zahl--;
    } else {
    zahl++;
    }
    }
     
    int krzahl = xd.nextInt(50)+1;
    int runde = client.getHighOrLowRound();
     
    int win = 0;  
    int points = 0;
    int minknuddels = 0;
            switch (channel.getName()) {
                case "High or Low Free":
                    win = runde;
                    points = 1*runde;
                    break;
                case "High or Low":
                    if (runde > 3) {
                    win = runde*1;
                    points = 2*runde;
                    }
                    minknuddels = 1;
                    break;
                case "High or Low Pro":
                    if (runde > 4) {
                    win = runde*3;
                    points = runde*3;
                        }
                    minknuddels = 5;
                    break;         
                case "High or Low Champion":
                    if (runde > 8) {
                    win = runde*5;
                    points = runde*4;
                        }
                    minknuddels = 10;
                    break;
            }
                   
                    if(arg.equals("start")) {
    if (client.getKnuddels() < minknuddels) {
    client.sendButlerMessage(channel.getName(), String.format("Du brauchst mindestens _%s Knuddels_ um zu Spielen.", minknuddels));
    return false;
    }    
     
    if (client.getHighOrLowChannel() != null && !client.getHighOrLowChannel().equals(channel.getName())) {
    client.sendButlerMessage(channel.getName(), String.format("Du hast bereits im _°>_hChannel %s|/go %s|/go +%s<°_ ein Spiel offen. Beende dieses Spiel zuerst im hier Spielen zu können.", client.getHighOrLowChannel(),client.getHighOrLowChannel(),client.getHighOrLowChannel()));
    return false;
    }
     
    if(runde != 0) {
    client.sendButlerMessage(channel.getName(), String.format("Du spielst bereits High or Low. Deine Zahl ist _°18RR>{textborder}<°%s§.#Denkst du die nächste Zahl wird °>{button} höher ||call|/hol high<° oder °>{button} niedriger ||call|/hol low<°?", client.getHighOrLowNumber()));
    } else {
    client.sendButlerMessage(channel.getName(), String.format("%s Deine Startzahl ist _°18RR>{textborder}<°%s§.#Denkst du die nächste Zahl wird °>{button} höher ||call|/hol high<° oder °>{button} niedriger ||call|/hol low<°?", krzahl==5?"°BB°_Knuddelsrunde!§":"Neue Runde, neues Glück!",zahl));
    client.increaseHighOrLowRound();
    client.setHighOrLowChannel(channel.getName());
    client.setHighOrLowNumber((byte)zahl);
    client.deseaseKnuddels(minknuddels);
     
    if(krzahl==5 || "High or Low Pro".equals(channel.getName()) || "High or Low Champion".equals(channel.getName()) || "High or Low".equals(channel.getName())) {
    client.setHolknuddelsrunde((byte)1);
    }
    }
     
    return false;
    }
                   
    if(arg.equals("high")) {
        if (client.getHighOrLowChannel() != null && !client.getHighOrLowChannel().equals(channel.getName())) {
    client.sendButlerMessage(channel.getName(), String.format("Du hast bereits im _°>_hChannel %s|/go %s|/go +%s<°_ ein Spiel offen. Beende dieses Spiel zuerst im hier Spielen zu können.", client.getHighOrLowChannel(),client.getHighOrLowChannel(),client.getHighOrLowChannel()));
    return false;
    }
    if(runde == 0) {
    client.sendButlerMessage(channel.getName(), "Du spielst kein High or Low. _°BB>Jetzt mitspielen!|/hol start<°");
    return false;
    }
     
    if(client.getHighOrLowNumber() > zahl) {
    highOrLowWrong(client, channel, client.getHighOrLowRound(), points, zahl, win);
    } else {
    client.sendButlerMessage(channel.getName(), String.format("°G°_Vollkommen richtig!§ _Runde %s_: Deine neue Zahl ist _°18RR>{textborder}<°%s§.#Denkst du die nächste Zahl wird °>{button} höher ||call|/hol high<° oder °>{button} niedriger ||call|/hol low<°?", runde+1, zahl));
    client.setHighOrLowNumber((byte)zahl);
    client.increaseHighOrLowRound();
    }
     
    return false;
    }
                   
    if(arg.equals("low")) {
        if (client.getHighOrLowChannel() != null && !client.getHighOrLowChannel().equals(channel.getName())) {
    client.sendButlerMessage(channel.getName(), String.format("Du hast bereits im _°>_hChannel %s|/go %s|/go +%s<°_ ein Spiel offen. Beende dieses Spiel zuerst im hier Spielen zu können.", client.getHighOrLowChannel(),client.getHighOrLowChannel(),client.getHighOrLowChannel()));
    return false;
    }
    if(client.getHighOrLowRound() == 0) {
    client.sendButlerMessage(channel.getName(), "Du spielst kein High or Low. _°BB>Jetzt mitspielen!|/hol start<°");
    return false;
    }
     
    if(client.getHighOrLowNumber() < zahl) {
    highOrLowWrong(client, channel, client.getHighOrLowRound(), points, zahl, win);
    } else {
    client.sendButlerMessage(channel.getName(), String.format("°G°_Vollkommen richtig!§ _Runde %s_: Deine neue Zahl ist _°18RR>{textborder}<°%s§.#Denkst du die nächste Zahl wird °>{button} höher ||call|/hol high<° oder °>{button} niedriger ||call|/hol low<°?", runde+1, zahl));
    client.setHighOrLowNumber((byte)zahl);
    client.increaseHighOrLowRound();
    }
    return false;
    }
                   
                    client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/hol PARAMETER");
                return false;
            }
            }
    }


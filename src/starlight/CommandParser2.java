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

import static features.hero.timeStampToDate;
import fifty.fiftyanmeldung;
import fifty.fiftywuerfeln;
import static funktionen.f.time;
import funktionen.game;
import funktionen.ping;
import game.Darten;
import game.Dicen;
import game.Freidiffen;
import game.WordMixRecord;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import static starlight.CommandParser.countChars;
import static starlight.CommandParser.parse;
import static starlight.CommandParser.userIsOffline;
import static starlight.FunctionParser.countChars;
import static starlight.FunctionParser.countWords;
import starlight.Settings;
import tools.KCodeParser;
import tools.Logger;
import tools.PacketCreator;
import tools.Pair;
import tools.Password;
import tools.ProfileTools;
import tools.VoteBox;
import tools.Zodiac;
import tools.database.ConnectionPool;
import tools.database.PoolConnection;
import tools.popup.Button;
import tools.popup.Choice;
import tools.popup.KTab;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;
import tools.popup.TextArea;
import tools.popup.TextField;

public class CommandParser2 {

    static Socket socket;
    static DataInputStream in;
    public static PrintStream out;
    private static Random zufall = new Random();
    private static NumberFormat nf;
    private static DecimalFormat df;
    private static String[] afk = {"verschwindet für einen kurzen Moment.", "ist gleich wieder da."};
    private static String[] kiss = {"[C] kann einfach nicht anders und gibt [T] einen langen Kuss.", "[T] kann sich dem Küssen von [C] einfach nicht entziehen.", "[T] wird von [C] ganz fest geknutscht. Wow, jetzt ist aber ein Knutschfleck zurückgeblieben.", "[T] bekommt von [C] einen liebevollen, kleinen Kuss...", "[T] kriegt von [C] einen leichten Bussi.", "[T] wird von [C] herzlich geküsst.", "[T] wird von [C] zärtlich geküsst.", "[C] beugt sich rüber zu [T] und küsst [T] sanft.", "[T] bekommt von [C] einen Bussi aufgedrückt.", "[C] küsst [T] sehr herzlich, sodass [T] nun auf [K] Knutschflecken kommt."};
    private static String[] knuddel = {"[C] °RR°knuddelt°BB° [T] sehr herzlich."};
    private static String[] knuddelz = {"[C] °RR°knuddelt°BB° mal ganz spontan [T] durch... WOW!", "[T] werden von [C] so richtig °RR°durchgeknuddelt°BB°.", "[C] nimmt [T] in den Arm und °RR°knuddelt°BB° [T] mal so richtig.", "Es wird wieder °RR°geknuddelt°BB°! [C] scheint [T] sehr gerne zu haben...", "[T] werden Opfer einer °RR°Massenknuddelichung°BB° von [C].", "[C] gibt eine ganze Runde °RR°Knuddels°BB° an [T].", "[C] °RR°knuddelt°BB° [T] mal so richtig durch."};
    private static String[] unknownUser = {"Wer soll denn bitte %s sein?", "%s ist mir unbekannt.", "%s habe ich hier noch niemals gesehen.", "Keine Ahnung, wen Sie meinen.", "Wer ist %s???", "Tut mir leid, dieser Gast existiert nicht.", "%s gibt's hier leider nicht.", "Ich weiß über %s nichts."};
    private static String[] noKnuddels = {"Knuddeln kann man nur, wenn man auch Knuddels hat, und die fehlen dir leider."};
    private static String[] help = {"%s ist ein sehr gefährlicher Tag.", "Ja, mit %s ist das genau wie mit dem Yeti...", "Mit %s sollte man es aber nicht übertreiben...", "Was ist %s?", "%s ist mir unbekannt."};
    private static String[] messageSent = {"Deine Nachricht wurde an _[N] gesendet_.", "[N] wird sich über die Nachricht bestimmt freuen.", "Gute Idee, [N] wird sich bestimmt darüber freuen.", "Sobald ich [N] wiedersehe, werde ich es ausrichten.", "[N] wird es erfahren.", "Hoffentlich freut sich [N] über die Nachricht.", "Null Problemo, ich werde es [N] ausrichten.", "Ich werde es [N] ausrichten.", "Wie interessant, ich werde es [N] schnellstmöglich ausrichten.", "Ich werde es nicht vergessen und [N] schnellstmöglich ausrichten."};

    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }

    public static int countChars(String text, char c) {
        int counter = 0;

        for (int i = 0; i < text.length(); i++) {
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
        return messageSent[zufall.nextInt(messageSent.length)].replace("[N]", String.format("°>_h%s|/serverpp \"|/w \"<°", nick.replace("<", "\\<")));
    }

    public static void highOrLowWrong(Client client, Channel channel, int runde, int points, int zahl) {
        client.sendButlerMessage(channel.getName(), String.format("°RR°_Leider falsch!§ Die neue Zahl ist _°18RR>{textborder}<°%s§.#Du hast es bis zur _%s. Runde_ geschafft (Gewinn: _%s_ Punkte%s).", zahl, client.getHighOrLowRound(), points, client.getHolknuddelsrunde() == 1 ? String.format(" und °BB°_%s Knuddels§", runde > 1 ? runde : 0) : ""));

        if (channel.getHolrunde() < runde) {
            channel.increaseHoljackpot(1);

            channel.broadcastButlerMessage(new StringBuilder().append("°%-1>firework...b.mx_-9.w_52.h_40.gif<° °RR°_").append(client.getName()).append(" hat einen neuen High or Low Rekord aufgestellt! °>firework...b.mx_-9.w_52.h_40.gif<#°").append(client.getGenderLabel()).append(" hat es bis Runde ").append(runde).append(" geschafft. Im Jackpot befinden sich ").append(channel.getHoljackpot()).append(" Knuddels.").toString());
            channel.setTopic(String.format("In diesem Channel kann High or Low gespielt werden. °>gt.gif<° °BB>Jetzt mitspielen!|/hol start<r°##%s hält den momentanen Rekord mit Runde %s (Jackpot: %s Knuddels).", client.getName(), runde, channel.getHoljackpot()));

            if (!channel.getHolnick().equals(client.getName())) {
                Server.get().newMessage(Server.get().getButler().getName(), channel.getHolnick(), "High or Low Rekord geknackt", String.format("Dein High or Low Rekord wurde soeben von %s mit %s Runden geknacht.", client.getName(), runde), System.currentTimeMillis() / 1000);
            }

            channel.setHolrunde(runde);
            channel.setHolnick(client.getName());
        }

        client.setHighOrLowRound((byte) 0);
        client.increaseHol(points);

        if (client.getHolknuddelsrunde() == 1 && runde > 1) {
            client.increaseKnuddels(runde);
            channel.increaseHoljackpot(1);
        }

        client.setHolknuddelsrunde((byte) 0);
    }

    public static String userIsOffline(Client client) {
        int seconds = (int) ((System.currentTimeMillis() / 1000) - client.getLastOnline());
        int days = seconds / 86400;
        int hours = seconds / 3600;
        int minutes = seconds / 60;
        String what;

        if (days > 1) {
            what = String.format("%s Tagen", days);
        } else if (days == 1) {
            what = "einem Tag";
        } else if (hours > 1) {
            what = String.format("%s Stunden", hours);
        } else if (hours == 1) {
            what = "einer Stunde";
        } else if (minutes > 1) {
            what = String.format("%s Minuten", minutes);
        } else if (minutes == 1) {
            what = "einer Minute";
        } else if (seconds > 1) {
            what = String.format("%s Sekunden", seconds);
        } else {
            what = "einer Sekunde";
        }

        return String.format("°>_h%s|/serverpp \"|/w \"<° ist _seit %s offline_.", client.getName().replace("<", "\\<"), what);
    }

    public static String image(Client client, Client target) {
        if (target.getGender() == 2 && client.getGender() == 2) {
            return "ff";
        } else if (target.getGender() == 2 && client.getGender() == 1) {
            return "mf";
        } else if (target.getGender() == 1 && client.getGender() == 2) {
            return "fm";
        } else {
            return "mm";
        }
    }

    private static List<Pair<String, Integer>> icons;

    public static void addIcon(String icon, int size, Client nick, Channel channel) {
        Pair<String, Integer> pair = new Pair<String, Integer>(icon, size);

        icons = new ArrayList<Pair<String, Integer>>();
        icons.add(pair);

        if (channel == null) {
            for (Channel channelz : nick.getChannels()) {
                for (Client target : channelz.getClients()) {
                    if (target != Server.get().getButler()) {
                        target.send(PacketCreator.addIcon(channelz.getName(), nick.getName(), pair));
                    }
                }
            }
        } else {
            for (Client target : channel.getClients()) {
                if (target != Server.get().getButler()) {
                    target.send(PacketCreator.addIcon(channel.getName(), nick.getName(), pair));
                }
            }
        }
    }

    public static void removeIcon(String icon, String name, Channel channel) {
        Client nick = Server.get().getClient(name);

        if (channel == null) {
            for (Channel channelz : nick.getChannels()) {
                for (Client target : channelz.getClients()) {
                    if (target != Server.get().getButler()) {
                        target.send(PacketCreator.removeIcon(channelz.getName(), nick.getName(), icon));
                    }
                }
            }
        } else {
            for (Client target : channel.getClients()) {
                if (target != Server.get().getButler() && nick != null) {
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

    public static void parse2(String message, Client client, Channel channel, boolean fromGame) {

        String command = KCodeParser.escape(message.substring(1).split(" ")[0]);
        String cmd = command.toLowerCase();
        String arg = "";
        Long time = System.currentTimeMillis() / 1000;

        if (message.length() > cmd.length() + 1) {
            arg = message.substring(message.indexOf(' ') + 1);
        }

        int Anzahlspe = Server.countChars(arg, '°');

        if (Anzahlspe == 1) {
            String[] argus = arg.split("°", 2);
            arg = argus[0];
        }

        if (cmd.equals("set")) {
            if (!client.hasPermission("cmd.set.points") && !client.hasPermission("cmd.set.age") && !client.hasPermission("cmd.set.gmute") && !client.hasPermission("cmd.set.lcban") && !client.hasPermission("cmd.set.disable") && !client.hasPermission("cmd.set.deletenick") && !client.hasPermission("cmd.set.gender") && !client.hasPermission("cmd.set.+knuddels") && !client.hasPermission("cmd.set.rosenban") && !client.hasPermission("cmd.set.knuddels") && !client.hasPermission("cmd.set.rank") && !client.hasPermission("cmd.set.silence") && !client.hasPermission("cmd.set.feature") && !client.hasPermission("cmd.set.element") && !client.hasPermission("cmd.set.kisses") && !client.hasPermission("cmd.set.heartban") && !client.hasPermission("cmd.set.onlinetime") && !client.hasPermission("cmd.set.photoverify") && !client.hasPermission("cmd.set.emailverify") && !client.hasPermission("cmd.set.verify")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String[] pieces = arg.split(":", 5);
            String typ = "";
            String nick = "";
            String idRight = "";
            String four = "";
            int id = 0;
            String anzahl = "";

            try {
                typ = pieces[0].toLowerCase();
                nick = pieces[1];
                id = Integer.parseInt(pieces[2]);
            } catch (Exception ex) {
            }

            if (typ.equals("points")) {
                try {
                    idRight = pieces[2];
                    four = pieces[3];
                    anzahl = pieces[4];
                } catch (Exception ex) {
                }
            }

            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/set PARAMETER:NICK:ID");
                return;
            }

            if (!typ.equals("age") && !typ.equals("points")  && !typ.equals("+knuddels") && !typ.equals("feature") && !typ.equals("lcban") && !typ.equals("kisses") && !typ.equals("element") && !typ.equals("onlinetime") && !typ.equals("silence") && !typ.equals("photoverify") && !typ.equals("emailverify") && !typ.equals("verify") && !typ.equals("heartban") && !typ.equals("disable") && !typ.equals("deletenick") && !typ.equals("gender") && !typ.equals("rosenban") && !typ.equals("rank") && !typ.equals("knuddels")) {
                client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
                return;
            }

            Client target = Server.get().getClient(nick);
            boolean online = true;

            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nick);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nick));
                    return;
                }
            }

            if (typ.equals("points")) {
                if (!client.hasPermission("cmd.set.points")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (four.isEmpty() || !Server.get().isInteger(anzahl) || Integer.parseInt(anzahl) < 0 || !idRight.equalsIgnoreCase("Jumpo") && !idRight.equalsIgnoreCase("WordMix") && !idRight.equalsIgnoreCase("Quiz") && !idRight.equalsIgnoreCase("Mathe") && !idRight.equalsIgnoreCase("Translate")&& !idRight.equalsIgnoreCase("Blitz") && !idRight.equalsIgnoreCase("Diebspiel") && !idRight.equalsIgnoreCase("High or Low")) {
                    client.sendButlerMessage(channel.getName(), "Falsche Anwendung.");
                    return;
                }

                int punkte = Integer.parseInt(anzahl);

                if (idRight.equalsIgnoreCase("Jumpo")) {
                    if (target.getJumpopunkte() < punkte) {
                        client.sendButlerMessage(channel.getName(), String.format("%s hat nicht so viele %s Punkte!", target.getName(), idRight));
                    } else {
                        if (online) {
                            target.deseaseJumpoPoints(punkte);
                        } else {
                            Server.get().query(String.format("UPDATE `accounts` SET `jumpopunkte` = '%s' WHERE `name` = '%s'", target.getJumpopunkte() - punkte, target.getName()));
                        }
                    }
                } else if (idRight.equalsIgnoreCase("High or Low")) {
                    if (target.getHol() < punkte) {
                        client.sendButlerMessage(channel.getName(), String.format("%s hat nicht so viele %s Punkte!", target.getName(), idRight));
                    } else {
                        if (online) {
                            target.setHol(target.getHol() - punkte);
                        } else {
                            Server.get().query(String.format("UPDATE `accounts` SET `hol` = '%s' WHERE `name` = '%s'", target.getHol() - punkte, target.getName()));
                        }
                    }
                } else if (idRight.equalsIgnoreCase("Diebspiel")) {
                    if (target.getThiefGame() < punkte) {
                        client.sendButlerMessage(channel.getName(), String.format("%s hat nicht so viele %s Punkte!", target.getName(), idRight));
                    } else {
                        if (online) {
                            target.setThiefGame(target.getThiefGame() - punkte);
                        } else {
                            Server.get().query(String.format("UPDATE `accounts` SET `thiefGame` = '%s' WHERE `name` = '%s'", target.getThiefGame() - punkte, target.getName()));
                        }
                    }
                } else if (idRight.equalsIgnoreCase("WordMix")) {
                    if (target.getWordMixPoints() < punkte) {
                        client.sendButlerMessage(channel.getName(), String.format("%s hat nicht so viele %s Punkte!", target.getName(), idRight));
                    } else {
                        if (online) {
                            target.setWordMixPoints(target.getWordMixPoints() - punkte);
                        } else {
                            Server.get().query(String.format("UPDATE `accounts` SET `wordmixPoints` = '%s' WHERE `name` = '%s'", target.getWordMixPoints() - punkte, target.getName()));
                        }
                    }
                } else if (idRight.equalsIgnoreCase("Blitz")) {
                    if (target.getBlitzPoints() < punkte) {
                        client.sendButlerMessage(channel.getName(), String.format("%s hat nicht so viele %s Punkte!", target.getName(), idRight));
                    } else {
                        if (online) {
                            target.setBlitzPoints(target.getBlitzPoints() - punkte);
                        } else {
                            Server.get().query(String.format("UPDATE `accounts` SET `blitzPoints` = '%s' WHERE `name` = '%s'", target.getBlitzPoints() - punkte, target.getName()));
                        }
                    }
                }

                client.sendButlerMessage(channel.getName(), String.format("%s wurden %s %s Punkte abgezogen.", nick, anzahl, idRight));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Sanktion %s", idRight), String.format("Der Administrator %s hat gegen dich eine _Punkte-Sanktion_ verhängt.#_Art:_ %s#_ANZAHL:_ %s#_Begründung:_ %s", client.getName(), idRight, anzahl, four), time);
                Server.get().newSysLogEntry(client.getName(), String.format("Sanktion - %s %s Punkte %s  abgezogen", anzahl, idRight, target.getName()));

                return;
            }

            if (typ.equals("age")) {
                if (!client.hasPermission("cmd.set.age")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 10 || id > 90) {
                    client.sendButlerMessage(channel.getName(), "Das Alter muss eine Zahl von 10-90 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Das Alter von %s wurde auf %s gesetzt.", target.getName(), id));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Alter auf %s gesetzt", id), String.format("Hallo %s,##dein Alter wurde soeben von %s auf %s gesetzt.##Liebe Grüße,#%s", target.getName(), client.getName(), id, Server.get().getButler().getName()), time);
                target.setComment(time, null, null, client.getName(), String.format("Alter auf _%s_ gesetzt", id));
                Server.get().newSysLogEntry(client.getName(), String.format("Alter von %s auf %s gesetzt", target.getName(), id));

                if (online) {
                    target.setAge((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set age='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            

            if (typ.equals("element")) {
                if (!client.hasPermission("cmd.set.element")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 4) {
                    client.sendButlerMessage(channel.getName(), "Der Wert muss eine Zahl von 0-4 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Das Element von %s wurde auf %s gesetzt.", target.getName(), id));
                Server.get().newSysLogEntry(client.getName(), String.format("%s wurde das Element mit der ID %s gesetzt", target.getName(), id));

                if (online) {
                    target.setElement((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set element='%s' where name='%s'", id, target.getName()));
                }

                return;

            }

            if (typ.equals("feature")) {
                if (!client.hasPermission("cmd.set.feature")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 7) {
                    client.sendButlerMessage(channel.getName(), "Der Wert muss eine Zahl von 0-7 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Das Feature von %s wurde auf %s gesetzt.", target.getName(), id));
                Server.get().newSysLogEntry(client.getName(), String.format("%s wurde das Nick-Feature mit der ID %s gesetzt", target.getName(), id));

                if (online) {
                    //	target.setEffect((byte)id);
                } else {
                    Server.get().query(String.format("update accounts set effect='%s' where name='%s'", id, target.getName()));
                }

                return;

            }

            if (typ.equals("+knuddels")) {
                if (!client.hasPermission("cmd.set.+knuddels")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 100000) {
                    client.sendButlerMessage(channel.getName(), "Die Knuddels müssen eine Zahl von 1-100000 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Die Knuddels von %s wurden um %s erhöht.", target.getName(), id));
                Server.get().newSysLogEntry(client.getName(), String.format("Knuddels von %s um %s erhöht", target.getName(), id));

                if (online) {
                    target.increaseKnuddels(id);
                } else {
                    Server.get().query(String.format("update accounts set knuddels=knuddels+'%s' where name = '%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("knuddels")) {
                if (!client.hasPermission("cmd.set.knuddels")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 500000) {
                    client.sendButlerMessage(channel.getName(), "Die Knuddels müssen eine Zahl von 1-100000 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Die Knuddels von %s wurden auf %s gesetzt.", target.getName(), id));
                Server.get().newSysLogEntry(client.getName(), String.format("Knuddels von %s auf %s gesetzt", target.getName(), id));

                if (online) {
                    target.setKnuddels(id);
                } else {
                    Server.get().query(String.format("update accounts set knuddels='%s' where name='%s'", id, target.getName()));

                }

                return;
            }

            if (typ.equals("kisses")) {
                if (!client.hasPermission("cmd.set.kisses")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 500000) {
                    client.sendButlerMessage(channel.getName(), "Die Kisses müssen eine Zahl von 1-100000 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Die Kisses von %s wurden auf %s gesetzt.", target.getName(), id));
                Server.get().newSysLogEntry(client.getName(), String.format("Kisses von %s auf %s gesetzt", target.getName(), id));

                if (online) {
                    target.setKisses(id);
                } else {
                    Server.get().query(String.format("update accounts set kisses='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("onlinetime")) {
                if (!client.hasPermission("cmd.set.onlinetime")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 999999999) {
                    client.sendButlerMessage(channel.getName(), "Die Knuddels müssen eine Zahl von 0-999999999 sein.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Die Onlinezeit von %s wurde auf %s gesetzt.", target.getName(), id));
                Server.get().newSysLogEntry(client.getName(), String.format("Onlinezeit von %s auf %s gesetzt", target.getName(), id));

                if (online) {
                    target.setonlineTime(id);
                } else {
                    Server.get().query(String.format("update accounts set onlinetime='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("gender")) {
                if (!client.hasPermission("cmd.set.gender")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 1 || id > 2) {
                    client.sendButlerMessage(channel.getName(), "Das Geschlecht muss die Zahl 1 (männlich) oder 2 (weiblich) sein.");
                    return;
                }

                String newgender = "";
                if (id == 1) {
                    newgender = "männlich";
                } else {
                    newgender = "weiblich";
                }
                client.sendButlerMessage(channel.getName(), String.format("Das Geschlecht von %s wurde auf %s (%s) gesetzt.", target.getName(), id, newgender));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Geschlecht auf %s gesetzt", newgender), String.format("Hallo %s,##dein Geschlecht wurde soeben von %s auf %s gesetzt.##Liebe Grüße,#%s", target.getName(), client.getName(), newgender, Server.get().getButler().getName()), time);
                target.setComment(time, null, null, client.getName(), String.format("Geschlecht auf _%s_ gesetzt", newgender));
                Server.get().newSysLogEntry(client.getName(), String.format("Geschlecht von %s auf %s gesetzt", target.getName(), id));

                if (online) {
                    target.setGender(Byte.parseByte(String.valueOf(id)));
                } else {
                    Server.get().query(String.format("update accounts set gender='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("verify")) {
                if (!client.hasPermission("cmd.set.verify")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Das Zahl muss die 1 (verifiziert) oder 0 (unverifiziert) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("Das Alter von %s wurde verifiziert.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##dein Alter wurde soeben von %s verifiziert.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "Alter verifiziert");
                    Server.get().newSysLogEntry(client.getName(), String.format("Altersverifikation von %s auf verifiziert gesetzt", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("Die Altersverifikation von %s wurde gelöcht.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine Altersverifikation wurde soeben von %s gelöscht.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "Altersverifikation gelöscht");
                    Server.get().newSysLogEntry(client.getName(), String.format("Altersverifikation von %s gelöscht", target.getName(), id));

                }

                if (online) {
                    target.setVerify((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set verify='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("photoverify")) {
                if (!client.hasPermission("cmd.set.photoverify")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }
                    client.sendButlerMessage(channel.getName(), "Leider kannst du diesen Befehl nicht mehr nutzen!##_Grund_: Fotos können nur noch über die Fotoadministration auf der Homepage, nach einsenden eines Verifizierungsphotos, verifiziert-/ bestätigt werden.#Sofern du im Foto-Team bist, steht dir die FotoAdministration auf der Homepage unter ''Fotogalerie' zur Verfügung.");
               /* if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Das Zahl muss die 1 (verifiziert) oder 0 (unverifiziert) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("Das Foto von %s wurde verifiziert.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##dein Foto wurde soeben von %s verifiziert.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "Foto verifiziert");
                    Server.get().newSysLogEntry(client.getName(), String.format("Fotoverifikation von %s auf verifiziert gesetzt", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("Die Fotoverifikation von %s wurde gelöcht.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine Fotoverifikation wurde soeben von %s gelöscht.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "Fotoverifikation gelöscht");
                    Server.get().newSysLogEntry(client.getName(), String.format("Fotoverifikation von %s gelöscht", target.getName(), id));

                }

                if (online) {
                    target.setPhoto_verify((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set photoverify='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("emailverify")) {
                if (!client.hasPermission("cmd.set.emailverify")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 2) {
                    client.sendButlerMessage(channel.getName(), "Das Zahl muss 0 (nicht verifiziert) oder 1 (verifikation läuft) oder 2 (verifiziert)sein.");
                    return;
                }

                if (id == 2) {
                    client.sendButlerMessage(channel.getName(), String.format("Die E-Mail-Adresse von %s wurde verifiziert.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine E-Mail-Adresse wurde soeben von %s verifiziert.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "E-Mail verifiziert");
                    Server.get().newSysLogEntry(client.getName(), String.format("E-Mail von %s auf verifiziert gesetzt", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("Die E-Mail-Verifikation von %s wurde gelöcht.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Verifizierung", String.format("Hallo %s,##deine E-Mail-Verifikation wurde soeben von %s gelöscht.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    target.setComment(time, null, null, client.getName(), "E-Mail-Verifikation gelöscht");
                    Server.get().newSysLogEntry(client.getName(), String.format("E-Mail-Verifikation von %s gelöscht", target.getName(), id));

                }

                if (online) {
                    target.setEmailVerify((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set emailverify='%s' where name='%s'", id, target.getName()));
                }

                return;
           */ }
              
            if (typ.equals("heartban")) {
                if (!client.hasPermission("cmd.set.heartban")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Die Zahl muss 0 (nicht gesperrt) oder 1 (gesperrt) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("%s kann ab jetzt das eigene Herz _nicht mehr_ vergeben.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Herzvergabe bei %s gesperrt", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("%s kann ab jetzt das eigene Herz _wieder_ vergeben.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Herzvergabe bei %s entsperrt", target.getName(), id));

                }

                if (online) {
                    target.setHeartsperre((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set heartsperre='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("disable")) {
                if (!client.hasPermission("cmd.set.disable")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Die Zahl muss 0 (nicht deaktiviert) oder 1 (deaktiviert) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("%s wurde erfolgreich deaktiviert.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Nick Deaktiviert - %s", target.getName(), id));
                    target.logout("Ausgeloggt.");

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("%s wurde wieder aktiviert.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Nick wieder aktiviert - %s", target.getName(), id));

                }

                {
                    target.setDisable((byte) id);

                    Server.get().query(String.format("update accounts set disable='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("deletenick")) {
                if (!client.hasPermission("cmd.set.disable")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Die Zahl muss 0 (nicht löschen) oder 1 (löschen) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("%s wurde zur Löschung freigegeben.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Nick-Löschung vorgemerkt - %s", target.getName(), id));
                    target.logout("Ausgeloggt.");

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("Löschung des Nicks %s wurde abgebrochen.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Nick-Löschung abgebrochen - %s", target.getName(), id));

                }

                {
                    target.setDeletenick((byte) id);

                    Server.get().query(String.format("update accounts set deletenick='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("rosenban")) {
                if (!client.hasPermission("cmd.set.rosenban")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Die Zahl muss 0 (gesperrt) oder 1 (nicht gesperrt) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("%s kann ab jetzt wieder _eine_ Rose versenden.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Rosenvergabe bei %s entsperrt", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("%s kann ab jetzt _keine_ Rose mehr versenden.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("Rosenvergabe bei %s gesperrt", target.getName(), id));

                }

                if (online) {
                    target.setRosensperre((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set rosensperre='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("lcban")) {
                if (!client.hasPermission("cmd.set.lcban")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Die Zahl muss 1 (gesperrt) oder 0 (nicht gesperrt) sein.");
                    return;
                }

                if (id == 0) {
                    client.sendButlerMessage(channel.getName(), String.format("%s kann ab jetzt _wieder_ den LieblingsChannel ändern.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("LieblingsChannel bei %s entsperrt", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("%s kann ab jetzt den LieblingsChannel _nicht_ mehr ändern.", target.getName()));
                    Server.get().newSysLogEntry(client.getName(), String.format("LieblingsChannel bei %s gesperrt", target.getName(), id));

                }

                if (online) {
                    target.setLCSperre((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set lcsperre='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("silence")) {
                if (!client.hasPermission("cmd.set.silence")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > 1) {
                    client.sendButlerMessage(channel.getName(), "Das Zahl muss die 1 (an) oder 0 (aus) sein.");
                    return;
                }

                if (id == 1) {
                    client.sendButlerMessage(channel.getName(), String.format("%s befindet sich jetzt im Ruhemodus.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Ruhemodus", String.format("Hallo %s,##du wurdest soeben von %s im Ruhemodus gesetzt.#Ab jetzt können dich nur noch Mitglieder ab Status (inofizieller Admin) oder höher anschreiben.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    Server.get().newSysLogEntry(client.getName(), String.format("Ruhemodus bei %s eingeschaltet", target.getName(), id));

                } else {
                    client.sendButlerMessage(channel.getName(), String.format("%s befindet sich jetzt nicht mehr im Ruhemodus.", target.getName()));
                    Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Ruhemodus", String.format("Hallo %s,##der Ruhemodus wurde soeben von %s ausgechaltet.#Ab jetzt kannst du wieder von jedem Mitglied angeschrieben werden.##Liebe Grüße,#%s", target.getName(), client.getName(), Server.get().getButler().getName()), time);
                    Server.get().newSysLogEntry(client.getName(), String.format("Ruhemodus bei %s ausgeschaltet", target.getName(), id));

                }

                if (online) {
                    target.setSilence((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set silence='%s' where name='%s'", id, target.getName()));
                }

                return;
            }

            if (typ.equals("rank")) {
                if (!client.hasPermission("cmd.set.rank")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (id < 0 || id > client.getRank()) {
                    client.sendButlerMessage(channel.getName(), "Der Rang muss eine Zahl von 0 und deinem Rang sein.");
                    return;
                }

                if (target == Server.get().getButler() || target == client || target.getRank() >= client.getRank() && !client.getName().equals("Localhost")) {
                    client.sendButlerMessage(channel.getName(), String.format("Der Rang von %s kann nicht geändert werden.", target.getName()));
                    return;
                }

                String rank = target.getRankLabel((byte) id);
                String datum = Server.get().timeStampToDate(time);

                client.sendButlerMessage(channel.getName(), String.format("%s wurde auf '%s' gesetzt.", target.getName(), rank));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), rank, String.format("Hallo %s,##Du wurdest von %s auf den Rang %s gesetzt.##Liebe Grüße, %s", target.getName(), client.getName(), rank, Server.get().getButler().getName()), time);
                target.setSyscomments(time, null, null, client.getName(), String.format("Rang auf _%s_ gesetzt", rank));
                Server.get().newSysLogEntry(client.getName(), String.format("Status von %s auf %s gesetzt", target.getName(), rank));

                if (online) {
                    target.setDate(datum);
                    target.setCareer(String.format("%s|%s %s|", target.getCareer(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin")));
                    target.setCareer2(String.format("%s|%s %s|", target.getCareer2(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin")));
                   // target.setHighlights(String.format("%s|%s %s|", target.getHighlights(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin")));
                    
                    target.setRank((byte) id);
                } else {
                    Server.get().query(String.format("update accounts set rank='%s',date='%s',career='%s|%s %s|' where name = '%s'", id, datum, target.getCareer(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin"), target.getName()));
                    Server.get().query(String.format("update accounts set rank='%s',date='%s',career2='%s|%s %s|' where name = '%s'", id, datum, target.getCareer2(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin"), target.getName()));
                   // Server.get().query(String.format("update accounts set rank='%s',date='%s',highlights='%s|%s %s|' where name = '%s'", id, datum, target.getHighlights(), datum, rank.replace("inoffizieller Admin", "Ehrenmitglied").replace("Vertrauensadmin", "Admin").replace("inoffizieller Sysadmin", "Admin"), target.getName()));
               
                }

                return;
            }

        } else if (cmd.equals("channel")) {
            if (!client.hasPermission("cmd.channel.minstatus") && !client.hasPermission("cmd.channel.size") && !client.hasPermission("cmd.channel.visible") && !client.hasPermission("cmd.channel.photos") && !client.hasPermission("cmd.channel.gender") && !client.hasPermission("cmd.channel.age") && !client.hasPermission("cmd.channel.event") && !client.hasPermission("cmd.channel.addcm") && !client.hasPermission("cmd.channel.removecm") && !client.hasPermission("cmd.channel.lockfunction") && !client.hasPermission("cmd.channel.overview") && !client.hasPermission("cmd.channel.unlockfunction") && !channel.checkHz(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg).split(":", 2)[0].toLowerCase();
            String msg = "";
            String info = "";

            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

            if (nickname.isEmpty()) {
                if (!client.hasPermission("cmd.channel.overview")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                String title = String.format("Informationen Channel %s", channel.getName());
                StringBuilder c = new StringBuilder();

                if (!channel.getOwner().isEmpty()) {
                    c.append("#_Besitzer_:°%40°").append(channel.getOwner()).append("°%00°");
                }

                if (channel.getTopic() != null) {
                    c.append("#_Topic_:°%40°").append(channel.getTopic()).append("°%00°§");
                }
                c.append("#_Size_:°%40°").append(channel.getSize()).append("°%00°");
                c.append("#_Butler muted_:°%40°").append(channel.butlerMuted() ? "Yes" : "No").append("°%00°");

                c.append("#_Youth Protection_:°%40°").append(channel.getJuSchu() == 1 ? "On" : "Off").append("°%00°");
                if (channel.getMinage() != 0) {
                    c.append("#_Age Restriction_:°%40°").append(channel.getMinage()).append("°%00°");
                }

                c.append("#_Minstatus_:°%40°").append(channel.getMinRank()).append("°%00°");
                c.append("#_Visible_:°%40°").append(channel.isVisible() ? "Sichtbar" : "Unsichtbar").append("°%00°");
                c.append("#_Special Event_:°%40°").append(channel.getSpecialEvent() == 1 ? "Aktiviert" : "Deaktiviert").append("°%00°");
                c.append("#_Ever_:°%40°").append(!channel.isVisible() && channel.getTemp() == 1 ? "Aktiviert" : "Deaktiviert").append("°%00°");
                c.append("#_Moderiert_:°%40°").append(channel.isModerated() ? "Aktiviert" : "Deaktiviert").append("°%00°");
                c.append("#_Edit Status_:°%40°6°%00°");

                if (!channel.getBannedFunctions().isEmpty()) {
                    c.append("#_Locked Functions_:°%40°").append(channel.getBannedFunctions().replace("||", ", ").replace("|", "")).append("°%00°");
                }

                if (!channel.getCms().isEmpty()) {
                    c.append("#_CMs_:°%40°").append(channel.getCms().replace("||", ", ").replace("|", "")).append("°%00°");
                }

                if (!channel.getMcms().isEmpty()) {
                    c.append("#_MCMs_:°%40°").append(channel.getMcms().replace("||", ", ").replace("|", "")).append("°%00°");
                }

                if (!channel.getHZ().isEmpty()) {
                    c.append("#_HZA/E_:°%40°").append(channel.getHZ().replace("||", ", ").replace("|", "")).append("°%00°");
                }

                c.append("##_°BB>Channelinfo bearbeiten|/channelrules ").append(channel.getName()).append("<r°_");

                Popup popup = new Popup(title, title, c.toString(), 450, 275);
                Panel panel = new Panel();
                Button buttonMessage3 = new Button("   OK   ");
                buttonMessage3.setStyled(true);
                panel.addComponent(buttonMessage3);
                popup.addPanel(panel);
                popup.setModern(1);
                client.send(popup.toString());
                return;

            }

            if (nickname.startsWith("-")) {
                if (!nickname.startsWith("-cm") && !nickname.startsWith("-mcm")) {
                    if (!client.hasPermission("cmd.channel.unlockfunction") && !channel.checkHz(client.getName())) {
                        client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                        return;
                    }

                    String function = nickname.substring(1).trim();

                    if (function.isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                    } else if (channel.getBannedFunctions().contains(String.format("|%s|", function))) {
                        client.sendButlerMessage(channel.getName(), String.format("Die Funktion %s ist bereits gesperrt.", function));
                    } else {
                        client.sendButlerMessage(channel.getName(), String.format("Die _Funktion %s_ wurde für den Channel %s _gesperrt_.", function, channel.getName()));
                        channel.setBannedFunctions(String.format("%s|%s|", channel.getBannedFunctions(), function));
                    }

                    return;
                }
            }

            if (nickname.startsWith("+")) {
                if (!nickname.startsWith("+cm") && !nickname.startsWith("+mcm")) {
                    if (!client.hasPermission("cmd.channel.lockfunction")) {
                        client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                        return;
                    }

                    String function = nickname.substring(1).trim().toLowerCase();

                    if (function.isEmpty()) {
                        client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                    } else if (!channel.getBannedFunctions().contains(String.format("|%s|", function))) {
                        client.sendButlerMessage(channel.getName(), String.format("Die Funktion %s ist nicht gesperrt.", function));
                    } else if (function.equals("io") || function.equals("po")) {
                        client.sendButlerMessage(channel.getName(), String.format("Die Funktion %s kann nicht gesperrt werden.", function));
                    } else {
                        client.sendButlerMessage(channel.getName(), String.format("Die _Funktion %s_ wurde _entsperrt_.", function));
                        channel.setBannedFunctions(channel.getBannedFunctions().replace(String.format("|%s|", function), ""));
                    }

                    return;
                }
            }

            if (msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info);
                return;
            }

            String timeString = new SimpleDateFormat("M/yy").format(new Date());

            if (nickname.equals("+cm")) {
                if (!client.hasPermission("cmd.channel.addcm")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                Client target = Server.get().getClient(msg);
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(msg);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), unknownUser(msg));
                        return;
                    }
                }

                if (channel.checkCm(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("%s ist bereits im Channel %s CM!", target.getName(), channel.getName()));
                    return;
                }

                if (!target.hasPermission("becomecm")) {
                    client.sendButlerMessage(channel.getName(), "Du kannst den User nicht zum CM ernennen.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("%s wurde als CM im Channel %s gesetzt.", target.getName(), channel.getName()));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Channelmoderator im %s", channel.getName()), String.format("Hallo %s,##dir wurden soeben im Channel %s von %s Channelmoderatorenrechte gesetzt.##Liebe Grüße,#%s", target.getName(), channel.getName(), client.getName(), Server.get().getButler().getName()), time);
                target.setHighlights(String.format("%s|%s _Wahl_ zum CM im Channel %s|", target.getHighlights(), Server.get().timeStampToDate(time), channel.getName()));
      
                addIcon("pics/cm.png", 20, target, channel);

                channel.setCms(String.format("%s|%s|", channel.getCms(), target.getName()));
                target.setComment(time, null, null, client.getName(), String.format("CM-Rechte im Channel %s gesetzt", channel.getName()));
                

                if (!target.getCmwhen().contains(timeString) && channel.isVisible()) {
                    if (online) {
                        target.setCmwhen(String.format("%s%s%s", target.getCmwhen(), target.getCmwhen().isEmpty() ? "" : ", ", timeString));
                    } else {
                        Server.get().query(String.format("update accounts set cmMonths=cmMonths+'1', cmwhen='%s%s%s' where name='%s'", target.getCmwhen(), target.getCmwhen().isEmpty() ? "" : ", ", timeString, target.getName()));
                    }
                }

                return;
            }

            if (nickname.equals("-cm")) {
                if (!client.hasPermission("cmd.channel.removecm") && !channel.checkHz(client.getName())) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                Client target = Server.get().getClient(msg);
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(msg);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), unknownUser(msg));
                        return;
                    }
                }

                if (!channel.checkCm(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("%s ist kein Channelmoderator!", target.getName()));
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("%s wurden die Channelmoderatorenrechte entzogen.", target.getName(), channel.getName()));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("Channelmoderatorenrechte entzogen", channel.getName()), String.format("Hallo %s,##dir wurden soeben im Channel %s von %s die Channelmoderatorenrechte entzogen.##Liebe Grüße,#%s", target.getName(), channel.getName(), client.getName(), Server.get().getButler().getName()), time);

                if (online) {
                    removeIcon("pics/cm.png", target.getName(), channel);
                }

                if (target.getCmwhen().contains(timeString)) {
                    if (online) {
                        target.setCmwhen(target.getCmwhen().replace(String.format(", %s", timeString), "").replace(timeString, ""));
                    } else {
                        Server.get().query(String.format("update accounts set cmMonths=cmMonths-'1',cmwhen='%s' where name='%s'", target.getCmwhen().replace(String.format(", %s", timeString), "").replace(timeString, ""), target.getName()));
                    }
                }

                channel.setCms(channel.getCms().replace(String.format("|%s|", target.getName()), ""));
                target.setComment(time, null, null, client.getName(), String.format("CM für %s _entzogen!_", channel.getName()));
                return;
            }

            if (nickname.equals("+mcm")) {
                if (!client.hasPermission("cmd.channel.addmcm")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                Client target = Server.get().getClient(msg);
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(msg);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), unknownUser(msg));
                        return;
                    }
                }

                if (channel.checkMcm(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("%s ist bereits im Channel %s MCM!", target.getName(), channel.getName()));
                    return;
                }

                if (!target.hasPermission("becomemcm")) {
                    client.sendButlerMessage(channel.getName(), "Du kannst den User nicht zum MCM ernennen.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("%s wurde als MCM im Channel %s gesetzt.", target.getName(), channel.getName()));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("MCM im %s", channel.getName()), String.format("Hallo %s,##dir wurden soeben im Channel %s von %s MCM-Rechte gesetzt.##Liebe Grüße,#%s", target.getName(), channel.getName(), client.getName(), Server.get().getButler().getName()), time);
                target.setHighlights(String.format("%s|%s Ernannt zum _MCM_ im Channel %s|", target.getHighlights(), Server.get().timeStampToDate(time), channel.getName()));
      
                addIcon("pics/mcm.png", 25, target, channel);

                channel.setMcms(String.format("%s|%s|", channel.getMcms(), target.getName()));

                if (!target.getCmwhen().contains(timeString) && channel.isVisible()) {
                    if (online) {

                    }
                }

                return;
            }

            if (nickname.equals("-mcm")) {
                if (!client.hasPermission("cmd.channel.removemcm") && !channel.checkHz(client.getName())) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                Client target = Server.get().getClient(msg);
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(msg);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), unknownUser(msg));
                        return;
                    }
                }

                if (!channel.checkMcm(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("%s ist kein MCM!", target.getName()));
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("%s wurden die MCM-Rechte entzogen.", target.getName(), channel.getName()));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), String.format("MCM entzogen", channel.getName()), String.format("Hallo %s,##dir wurden soeben im Channel %s von %s die Channelmoderatorenrechte entzogen.##Liebe Grüße,#%s", target.getName(), channel.getName(), client.getName(), Server.get().getButler().getName()), time);

                if (online) {
                    removeIcon("pics/mcm.png", target.getName(), channel);
                }

                if (target.getCmwhen().contains(timeString)) {
                    if (online) {

                    }
                }

                channel.setMcms(channel.getMcms().replace(String.format("|%s|", target.getName()), ""));
                return;
            }

            if (nickname.equals("minstatus")) {
                if (!client.hasPermission("cmd.channel.minstatus")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                try {
                    Integer.parseInt(msg);
                } catch (Exception ex) {
                    client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
                    return;
                }

                int msgInt = Integer.parseInt(msg);

                if (msgInt < 0 || msgInt > 11) {
                    client.sendButlerMessage(channel.getName(), "Falscher Parameter.");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Minstatus geändert auf %s (%s).", msg, client.getRankLabel(Byte.parseByte(msg))));
                channel.setMinRank(msgInt);
                return;
            }

            if (nickname.equals("size")) {
                if (!client.hasPermission("cmd.channel.size")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                try {
                    Integer.parseInt(msg);
                } catch (Exception ex) {
                    client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Size geändert auf %s.", msg));
                channel.setSize(Integer.parseInt(msg));
                return;
            }

            if (nickname.equals("event")) {
                if (!client.hasPermission("cmd.channel.event")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.toLowerCase().equals("on".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Special Event aktiviert.");
                    channel.setSpecialEvent(1);
                    return;
                }

                if (msg.toLowerCase().equals("off".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Special Event deaktiviert.");
                    channel.setSpecialEvent(0);
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                return;
            }

            if (nickname.equals("foto")) {
                if (!client.hasPermission("cmd.channel.photos")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.toLowerCase().equals("on".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Foto-Anzeige aktiviert.");
                    channel.setPhotos(1);
                    return;
                }

                if (msg.toLowerCase().equals("off".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Foto-Anzeige deaktiviert.");
                    channel.setPhotos(0);
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                return;
            }

            if (nickname.equals("age")) {
                if (!client.hasPermission("cmd.channel.age")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.toLowerCase().equals("on".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Alters-Anzeige aktiviert.");
                    channel.setShowAge(1);
                    return;
                }

                if (msg.toLowerCase().equals("off".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Alters-Anzeige deaktiviert.");
                    channel.setShowAge(0);
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                return;
            }

            if (nickname.equals("gender")) {
                if (!client.hasPermission("cmd.channel.gender")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.toLowerCase().equals("on".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Geschlechts-Anzeige aktiviert.");
                    channel.setShowGender(1);
                    return;
                }

                if (msg.toLowerCase().equals("off".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Geschlechts-Anzeige deaktiviert.");
                    channel.setShowGender(0);
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                return;
            }

            if (nickname.equals("juschu")) {
                if (!client.hasPermission("cmd.channel.juschu")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.toLowerCase().equals("on".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Der Jugendschutz-Filter wurde aktiviert.");
                    channel.setJuSchu(1);
                    return;
                }

                if (msg.toLowerCase().equals("off".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Der Jugendschutz-Filter wurde deaktiviert.");
                    channel.setJuSchu(0);
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                return;
            }

            if (nickname.equals("visible")) {
                if (!client.hasPermission("cmd.channel.visible")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.toLowerCase().equals("on".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Der Channel ist nun _sichtbar_.");
                    channel.setVisible(1);
                    return;
                }

                if (msg.toLowerCase().equals("off".toLowerCase())) {
                    client.sendButlerMessage(channel.getName(), "Der Channel ist nun _unsichtbar_.");
                    channel.setVisible(0);
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                return;
            }

            client.sendButlerMessage(channel.getName(), info);

        } else if (cmd.equals("profil")) {
            if (!client.hasPermission("cmd.profil")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg).split(":", 2)[0];
            String msg = "";

            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

            if (nickname.equals("-")) {
                client.send(PacketCreator.createMessageWindow("", "", ""));
                return;
            }

            String n = nickname.toLowerCase();

            if (nickname.equals("?")) {
                client.send(PacketCreator.createSignatureWindow(client));
                return;
            }

            if (n.isEmpty() || n.equals("visit")) {
                StringBuilder messages = new StringBuilder();

                if (n.equals("visit")) {
                    if (!client.hasPermission("cmd.profil.visit")) {
                        client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                        return;
                    }

                    if (msg.toLowerCase().equals("on".toLowerCase())) {
                        client.sendButlerMessage(channel.getName(), "Profil _sichtbar geschaltet_.");
                        client.setVisit((byte) 0);
                        return;
                    }

                    if (msg.toLowerCase().equals("off".toLowerCase())) {
                        client.sendButlerMessage(channel.getName(), "Profil _unsichtbar geschaltet_.#(Wichtig! - Mitglieder ab dem Status _Ehrenmitglied_ oder höher, können nach wie vor dein Profil einsehen.)");
                        client.setVisit((byte) 1);
                        return;
                    }

                    client.sendButlerMessage(channel.getName(), "Falscher Parameter");
                    return;
                }
            }

        } else if (cmd.equals("b")) {
            if (!client.hasPermission("cmd.b.mute") && !client.hasPermission("cmd.b.say") && !client.hasPermission("cmd.b.do")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            String info = "Bitte die Funktion folgendermaßen benutzen:#/b say/do:TEXT oder mute#(Lässt den Butler etwas schreiben/tun oder mutet/entmutet ihn.)";

            if (arg.length() > nickname.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

            if (nickname.equals("mute")) {
                if (!client.hasPermission("cmd.b.mute")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (channel.butlerMuted()) {
                    channel.broadcastAction(Server.get().getButler().getName(), "hat ihre Pause beendet und ist wieder voll einsatzbereit.");
                    channel.setButlerMuted(false);
                    return;
                }

                channel.broadcastAction(Server.get().getButler().getName(), "macht jetzt Pause.");
                channel.setButlerMuted(true);
                return;
            }

            if (nickname.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), info);
                return;
            }

            if (nickname.equals("say")) {
                if (!client.hasPermission("cmd.b.say")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.charAt(0) == '/') {
                    if (!client.hasPermission("cmd.b.functions")) {
                        client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                        return;
                    }

                    client.sendButlerMessage(channel.getName(), String.format("Funktion als _Butler ausgeführt_: %s",Server.get().parseSmileys(client, msg)));
                    parse(msg, Server.get().getButler(), channel, false);
                    FunctionParser.parse(msg, Server.get().getButler(), channel);
                    return;
                }

                channel.broadcastButlerMessage(Server.get().parseSmileys(client, msg));
                return;
            }

            if (nickname.equals("do")) {
                if (!client.hasPermission("cmd.b.do")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (msg.charAt(0) == '/') {
                    if (!client.hasPermission("cmd.b.functions")) {
                        client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                        return;
                    }

                    client.sendButlerMessage(channel.getName(), String.format("Funktion als _Butler ausgeführt_: %s", msg));
                    parse(msg, Server.get().getButler(), channel, false);
                    FunctionParser.parse(msg, Server.get().getButler(), channel);
                    return;
                }

                channel.broadcastAction(Server.get().getButler().getName(), Server.get().parseSmileys(client, msg));
                return;
            }

            client.sendButlerMessage(channel.getName(), info);
            	

        

    
        } else if (cmd.equals("cheers")) {
            
            String[] l = client.getFeature("Cheers");
            Feature ft = Server.get().getFeature("Cheers");

            if (ft == null) {
                return;
            }

            if (l[0].equals("0")) {
                client.sendButlerMessage(channel.getName(), "Du hast das " + ft.getName() + " Feature nicht.");
                return;
            }
            if (l[0].equals("1")) {
                client.sendButlerMessage(channel.getName(), "Du kannst das Feature " + ft.getName() + " erst am " + timeStampToDate(Long.parseLong(l[5])) + " Uhr wieder nutzen.");
                return;
            }
   
            String getraenke = "|bier||sekt||cola|";
            String name = "beer,sparklingwine,cola";
            String namennormal = "Bier,Sekt,Cola";
            String typ = KCodeParser.escape(arg).split(":", 2)[0].trim();

            String nicks = "";
            if (arg.length() > typ.length()) {
                nicks = KCodeParser.escape(arg.substring(arg.indexOf(":") + 1));
            }

            if (nicks.isEmpty() || typ.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Verwende _/cheers sekt/bier/cola:Nick1,Nick2,Nick3,..._, um mit den angegebenen Nicks anzustoßen."); // wegen test
                return;
            }

            String check = "|" + typ.toLowerCase() + "|";
            String aktu = typ;

            if (!getraenke.contains(check)) {
                client.sendButlerMessage(channel.getName(), "Dieses Getränkt gibt es nicht.");
                return;
            }

            String[] all = getraenke.split("\\|");
            int pos = 0;
            int use = 0;

            for (String fund : all) {

                if (!fund.isEmpty()) {

                    if (fund.trim().equals(aktu.toLowerCase().trim())) {
                        use = pos;
                    }
                    pos++;
                }
            }

            String imagename = name.split(",")[use];
            String namenorm = namennormal.split(",")[use];
            int fail = 0;
            String nicksokay = "";
            String[] nickss = nicks.split(",");
            for (String nick : nickss) {

                Client target = Server.get().getClient(nick.trim());
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nick.trim());

                }

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nick));
                    fail = 1;
                } else if (!online) {
                    client.sendButlerMessage(channel.getName(), userIsOffline(target));
                    fail = 1;
                } else if (!channel.getClients().contains(target)) {
                    client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                    fail = 1;
                } else if (client == target) {
                    client.sendButlerMessage(channel.getName(), "Du kannst nicht mit dir selbst anstoßen."); // oder so
                    fail = 1;
                } else {
                    if (!nicksokay.contains(target.getName())) {
                        if (!nicksokay.isEmpty()) {
                            nicksokay += ", ";
                        }

                        nicksokay += "°>_h" + target.getName() + "|/w " + target.getName() + "|/serverpp " + target.getName() + "<°";
                    }
                }

            }

            if (fail == 1) {
                return;
            }

            nicksokay = replaceLast(nicksokay, ", ", " und ");

            String text = "";
            if (namenorm.equals("Bier")) {
                text = CommandParser.cheersbier[zufall.nextInt(CommandParser.cheersbier.length)].replace("[C]", client.getName()).replace("[T]", nicksokay);
            } else if (namenorm.equals("Sekt")) {
                text = CommandParser.cheerssekt[zufall.nextInt(CommandParser.cheerssekt.length)].replace("[C]", client.getName()).replace("[T]", nicksokay);
            } else if (namenorm.equals("Cola")) {
                text = CommandParser.cheerscola[zufall.nextInt(CommandParser.cheerscola.length)].replace("[C]", client.getName()).replace("[T]", nicksokay);
            }
            channel.broadcastPicAction("°r°", "°>pics/features/cheers/sound_cheers_" + imagename + ".mp<°" + text, String.format("../features/cheers/ico_cheers_" + imagename + "_bg...w_40.h_20.my_0.png<>features/cheers/ico_cheers_" + imagename + "_ani...w_0.h_20.my_-2.mx_-40.alwayscopy.gif<>trans_1x1.png"));
            ft.setBan(l[1], l[3], l[4], client); // setz sperre            

            // removeIcon("pics/features/cheers/ico_cheers_beer_ani.gif", target.getName(), channel);
            // Wird später eingebaut die Feature-Sperre
            // client.setCheerssperre(byte)1);
            // Clink-Funktion START
            // Derzeit fehlt die Privat Abfrage von James bei Target, ob er wirklich auch Anstoßen möchte, in dieser steht auch der Grund den der andere User angegeben hat, zum Anstoßen
            // Grund angabe für das Ergeignis zum Anstoßen (Siehe auch obendrüber)
            // Zufalls-Texte bei den jeweiligen Statements wie Cola oder Sekt oder Tee, etc
            // Profileintrag für 24 Stunden bei beiden im Profil
        } else if (cmd.equals("clink")) {

            if (arg.equals("ok")) {
                if (client.getClink().isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Dafür ist es bereits zu Spät.");
                    return;
                }

                String[] values = client.getClink().split("~");

                Client target = Server.get().getClient(values[0].trim());
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(values[0].trim());
                }

                if (!online) {
                    client.sendButlerMessage(channel.getName(), target.getName() + " ist bereits offline.");
                    client.setClink("");
                    return;
                }

                String typ = values[2];
                String reason = values[1];

                String name = "beer,sparklingwine,cola,fehlt,fehlt,fehlt,fehlt"; // nur das musste dann gucken wie grafien heißen
                String getraenke = "|bier||sekt||cola||tee||bubbletea||cocktail||orangensaft|";
                String namennormal = "Bier,Sekt,Cola,Tee,BubbleTea,Cocktail,Orangensaft";

                // nachricht im channel senden
                String[] all = getraenke.split("\\|");
                int pos = 0;
                int use = 0;

                for (String fund : all) {

                    if (!fund.isEmpty()) {

                        if (fund.trim().equals(typ.toLowerCase().trim())) {
                            use = pos;
                        }
                        pos++;
                    }
                }

                String imagename = name.split(",")[use];
                String namenorm = namennormal.split(",")[use];

                String text = "" + target.getName() + " und " + client.getName() + " stoßen miteinander mit einem Glas " + namenorm + " an. Als Grund für dieses feuchtfröhliche Ereignis nennen beide ''" + Server.get().parseSmileys(target, reason) + "§°BB°''§";
                channel.broadcastPicAction(">>", "" + text, String.format("../features/cheers/ico_cheers_" + imagename + "_bg...w_40.h_20.my_0.png<>features/cheers/ico_cheers_" + imagename + "_ani...w_0.h_20.my_-2.mx_-40.alwayscopy.gif<>trans_1x1.png"));

                client.setClink("");
                return;
            } else if (arg.equals("fail")) {
                if (client.getClink().isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Dafür ist es bereits zu Spät.");
                    return;
                }

                String[] values = client.getClink().split("~");

                Client target = Server.get().getClient(values[0].trim());
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(values[0].trim());
                }

                if (!online) {
                    client.sendButlerMessage(channel.getName(), target.getName() + " ist bereits offline.");
                    client.setClink("");
                    return;
                }

                client.sendButlerMessage(channel.getName(), "Du hast abgelehnt.");
                target.sendButlerMessage(channel.getName(), client.getName() + " möchte gerade nicht mit dir anstoßen.");
                client.setClink("");
                return;
            }

                  
            String[] l = client.getFeature("Clink");
            Feature ft = Server.get().getFeature("Clink");

            if (ft == null) {
                return;
            }

            if (l[0].equals("0")) {
                client.sendButlerMessage(channel.getName(), "Du hast das " + ft.getName() + " Feature nicht.");
                return;
            }
            if (l[0].equals("1")) {
                client.sendButlerMessage(channel.getName(), "Du kannst das Feature " + ft.getName() + " erst am " + timeStampToDate(Long.parseLong(l[5])) + " Uhr wieder nutzen.");
                return;
            }
            // Cola, Sekt, Bier, Tee, BubbleTea, Cocktail, Orangensaft
            String getraenke = "|bier||sekt||cola||tee||bubbletea||cocktail||orangensaft|";
            String namennormal = "Bier,Sekt,Cola,Tee,BubbleTea,Cocktail,Orangensaft";

            String[] split = arg.split(":", 3);
            String typ = "";
            String nick = "";
            String reason = "";

            try {
                typ = split[0].trim();
                nick = split[1].trim();
                reason = split[2].trim();

            } catch (Exception ex) {
            }

            if (nick.isEmpty() || typ.isEmpty() || reason.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Verwende _/clink[GETRÄNK]:[NICK]:[GRUND]_, um mit Nick anzustoßen.");
                return;
            }

            String check = "|" + typ.toLowerCase() + "|";
            String aktu = typ;

            if (!getraenke.contains(check)) {
                client.sendButlerMessage(channel.getName(), "Dieses Getränkt gibt es nicht.");
                return;
            }

            String[] all = getraenke.split("\\|");
            int pos = 0;
            int use = 0;

            for (String fund : all) {

                if (!fund.isEmpty()) {

                    if (fund.trim().equals(aktu.toLowerCase().trim())) {
                        use = pos;
                    }
                    pos++;
                }
            }

            String namenorm = namennormal.split(",")[use];

            Client target = Server.get().getClient(nick.trim());
            boolean online = true;

            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nick.trim());

            }

            if (target.getName() == null) {
                client.sendButlerMessage(channel.getName(), unknownUser(nick));
                return;
            }
            if (!online) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }

            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }
            if (client == target) {
                client.sendButlerMessage(channel.getName(), "Du kannst nicht mit dir selbst anstoßen."); // oder so
                return;
            }

            client.sendButlerMessage(channel.getName(), "Ich habe °BB°_" + target.getName() + "§ gefragt, ob " + target.getGenderLabel() + " mit dir anstoßen möchte.");

            String text = "°>CENTER<+9508°Möchtest du mit _°BB>_h" + client.getName() + "|/serverpp \"|/w \"<°°°_ auf#''" + Server.get().parseSmileys(client, reason) + "''##";

            if (namenorm.equals("Sekt")) {
                text += "mit einem Glas Sekt anstoßen? °>features/anstossen/ft_anstossen_prefix_01-sekt_" + image(client, target) + ".gif?.png<°";
            } else if (namenorm.equals("Cocktail")) {
                text += "mit einem leckeren Cocktail anstoßen? °>features/anstossen/ft_anstossen_prefix_01-cocktail_" + image(client, target) + ".gif?.png<°";  // ka ob url stimmt 
            } else if (namenorm.equals("Tee")) { 
                text += "Text für Tee";
            } else if (namenorm.equals("Cola")) { 
                text += "Text für Cola";
            } else if (namenorm.equals("Bier")) { 
                text += "Text für Bier";
            } else if (namenorm.equals("BubbleTea")) { 
                text += "Text für BubbleTea";
            } else if (namenorm.equals("Orangensaft")) { 
                text += "Text für Orangensaft";
            }
            String title = "Anstoßen";

            Popup popup = new Popup(title, title, text, 330, 200); 
            Panel panel = new Panel();
            Button buttonMessage = new Button("Ja");
            buttonMessage.setStyled(true);
            buttonMessage.setCommand("/clink ok");
            panel.addComponent(buttonMessage);
            Button buttonMessage2 = new Button("Nein");
            buttonMessage2.setStyled(true);
            buttonMessage2.setCommand("/clink fail");
            panel.addComponent(buttonMessage2);
            popup.addPanel(panel);
            popup.setModern(0);
            target.send(popup.toString());

            target.setClink(client.getName() + "~" + reason + "~" + typ);
            ft.setBan(l[1], l[3], l[4], client);

       

       
        } else if (cmd.equals("newvote")) {
            if (!client.hasPermission("cmd.newvote")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String[] split = arg.split("\\|");
            String error = "Verwende den Befehl wie folgt:#/newvote BESCHREIBUNG|SEKUNDEN|WAHL 1|WAHL2|...|WAHL N";

            if (arg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), error);
                return;
            }

            try {
                Integer.parseInt(split[1]);
            } catch (Exception ex) {
                client.sendButlerMessage(channel.getName(), error);
                return;
            }

            int length = split.length;
            int zeit = Integer.parseInt(split[1]);

            if (length < 4 || length > 12) {
                client.sendButlerMessage(channel.getName(), "Du musst zwischen 2 und 10 Wahlmöglichkeiten angeben.");
                return;
            }

            if (zeit > 300) {
                client.sendButlerMessage(channel.getName(), "Die Befragungszeit kann _maximal 300 Sekunden_ betragen.");
                return;
            }

            if (zeit < 1) {
                zeit = 30;
            }

            /*if(channel.getNewVoteEnd() != 0) {
             client.sendButlerMessage(channel.getName(), "In diesem Channel wird die Votebox leider schon verwendet.");
             return;
             }*/
            int min = 0;
            String question = KCodeParser.noKCode(split[0]);

            for (String answer : split) {
                if (min > 1) {
                    channel.newVoteAnswers.put(answer, 0);
                }

                min++;
            }

            for (Client c : channel.getClients()) {
                c.send(VoteBox.createVoteBox(channel, "Befragung", new VoteBox.Button("Vote", "1.gif", String.format("/nvote %s|%s", question, channel.getName()), true), channel.newVoteAnswers, zeit));
            }
            Server.get().newSysLogEntry(client.getName(), String.format("Startet eine Befragung: %s", question, client.getName()));
            channel.broadcastButlerMessage(String.format("°20°_Es findet eine Befragung statt:_°r°##°+0080°_%s_°%%00°##Gib jetzt deine Meinung ab.", question));
            channel.setNewVoteEnd(time + zeit);
            channel.setNewVoteUser(channel.countClients() - 1);

        } else if (cmd.equals("imitate")) {
            if (!client.hasPermission("cmd.imitate")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            int split = arg.indexOf(':');

            if (split < 1 || split == arg.length() - 1) {
                client.sendButlerMessage(channel.getName(), "Die Funktion bitte wie folgt benutzen: /imitate NICK:TEXT");
                return;

            }
            {

                String nickname = KCodeParser.escape(arg.substring(0, split));
                Client target = Server.get().getClient(nickname);

                String m = arg.substring(split + 1);

                channel.broadcastAction("°>_h" + nickname + "|/serverpp \"|/w \"<°" + ":", Server.get().parseSmileys(client, m));

            }

        } else if (cmd.equals("butterfly")) {

                
            String[] l = client.getFeature("Butterfly");
            Feature ft = Server.get().getFeature("Butterfly");

            if (ft == null) {
                return;
            }

            if (l[0].equals("0")) {
                client.sendButlerMessage(channel.getName(), "Du hast das " + ft.getName() + " Feature nicht.");
                return;
            }
            if (l[0].equals("1")) {
                client.sendButlerMessage(channel.getName(), "Du kannst das Feature " + ft.getName() + " erst am " + timeStampToDate(Long.parseLong(l[5])) + " Uhr wieder nutzen.");
                return;
            }  

            String nick = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";

            if (arg.length() > nick.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

            if (nick.isEmpty() || msg.isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Bitte die Funktion folgendermaßen benutzen:#/butterfly NICK:NACHRICHT#(Einen Schmetterling mit der Nachricht NACHRICHT an NICK senden.)");
                return;
            }

            Client target = Server.get().getClient(nick);
            boolean online = true;

            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(nick);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nick));
                    return;
                }
            }

            if (!online) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }

            if (target == client || target == Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), String.format("Du kannst kein Schmetterling an %s senden.", target.getName()));
                return;
            }

            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }

            msg = KCodeParser.parse(client, msg, 0, 10, 20);
            msg = Server.get().parseSmileys(client, msg);

            channel.broadcastButlerMessage(String.format("%s lässt einen Schmetterling °>{sprite}type:0|startpause:10000|flytime:10000|restinlist:120000|imgfly:pics/abo/butterfly_fly_010.gif|imgrest:pics/abo/butterfly_rest_010.gif|imgshadow:pics/abo/butterfly_rest_010.gif|imgshadow:pics/abo/butterfly_shadow.png|nickname:%s<° fliegen und gibt ihm diese Nachricht mit: \"\"%s§\"\". Wo er wohl landen wird?", client.getName(), target.getName().replace("<", "\\<"), msg));
            ft.setBan(l[1], l[3], l[4], client); // setz sperre        

        } else if (cmd.equals("sunshine")) {
      
            String[] l = client.getFeature("Sunshine");
            Feature ft = Server.get().getFeature("Sunshine");

            if (ft == null) {
                return;
            }

            if (l[0].equals("0")) {
                client.sendButlerMessage(channel.getName(), "Du hast das " + ft.getName() + " Feature nicht.");
                return;
            }
            if (l[0].equals("1")) {
                client.sendButlerMessage(channel.getName(), "Du kannst das Feature " + ft.getName() + " erst am " + timeStampToDate(Long.parseLong(l[5])) + " Uhr wieder nutzen.");
                return;
            }

            arg = KCodeParser.escape(arg);
            Client target = Server.get().getClient(arg);
            boolean online = true;

            if (target == null) {
                online = false;
                target = new Client(null);
                target.loadStats(arg);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(arg));
                    return;
                }
            }

            if (!online) {
                client.sendButlerMessage(channel.getName(), userIsOffline(target));
                return;
            }

            if (target == client || target == Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), String.format("Du kannst keine Sonne an %s senden.", target.getName()));
                return;
            }

            if (!channel.getClients().contains(target)) {
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hält sich im Moment _in einem anderen Channel_ auf.", target.getName().replace("<", "\\<")));
                return;
            }

            channel.broadcastAction(client.getName(), String.format("sendet eine °>{sprite}type:0|startpause:10000|flytime:60000|listxoffs:120|timetolist:10000|restinlist:15000|imgfly:pics/sunshinechat...h0.png|imgflylist:pics/sunshinenick.png|imgrest:pics/sunshinechat...h0.png|h:85|w:85|maxspeed:150|imgshadow:pics/transparent1x1.gif|imgrestinlist:pics/sunshinenick.png|nickname:%s<° los. Zu wem die wohl fliegen wird?", target.getName().replace("<", "\\<")));
            ft.setBan(l[1], l[3], l[4], client); // setz sperre
            target.setSun((byte) 1);
            target.setWashEnde(((System.currentTimeMillis()/1000)+3600));

            
            
        } else if (cmd.equals("fw")) {
            if (!client.hasPermission("cmd.fw")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            if (!channel.newVoteAnswers.containsKey(arg)) {
                return;
            }

            if (channel.newVoteNicks.contains(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Du hast bereits gevotet.");
                return;
            }

            channel.newVoteNicks.add(client.getName());
            channel.newVoteAnswers.put(arg, channel.newVoteAnswers.get(arg) + 1);

            for (Client c : channel.getClients()) {
                c.send(VoteBox.updateVoteBox(channel.getName(), arg, 1, String.format("%s wählt %s", client.getName(), arg)));
            }

            if (channel.getNewVoteUser() == channel.newVoteNicks.size()) {
                VoteBox.newVoteResult(channel);
            }

        } else if (cmd.equals("dice") || cmd.equals("d")) {
            if (!client.hasPermission(String.format("cmd.%s", cmd))) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            if (arg.trim().equals("+")) {
                fiftyanmeldung.make(client, channel);
                return;
            }
            if (channel.getName().equals("Fifty!")) {
                fiftywuerfeln.make(arg, client, channel, "free");
                return;
            }
/*
            if ((client.getDranDicen() == 1)) {
                Dicen.make(arg, client, channel);
                return;
            }
            if ((client.getDranDarten() == 1)) {
                Darten.make(arg, client, channel);
                return;
            }
            if ((client.getDranFreidiffen() == 1)) {
                Freidiffen.make(arg, client, channel);
                return;
            }
            */

            Random r = new Random();
            int zufall = r.nextInt(7) + 1;
            String image = "";
            if (zufall == 1) {
                image = "blue";
            } else if (zufall == 2) {
                image = "brightgreen";
            } else if (zufall == 3) {
                image = "darkblue";
            } else if (zufall == 4) {
                image = "green";
            } else if (zufall == 5) {
                image = "orange";
            } else if (zufall == 6) {
                image = "pink";
            } else if (zufall == 7) {
                image = "red";
            }
            Client target = Server.get().getClient(client.getDartenGegner());

            if (!client.getDartenGegner().isEmpty()) {

                if (client.getDartenDran() == 0) {
                    client.sendButlerMessage(channel.getName(), "Du bist noch nicht dran.");
                } else {
                    int ir1 = r.nextInt(client.getDartenWurf()) + 1;
                    String wurf = String.valueOf(client.getDartenWurf());
                    String variablen = tools.DiceCreator.dice(wurf, 0);
                    String[] teil = variablen.split("\\|");

                    client.setDartenDran(0);

                    target.setDartenDran(1);

                    if (target.getDartenPrivat() == 0) {
                      
                        channel.broadcastAction("°BB°", String.format("> %s rollt einen Würfel...#W%s: %s = _%s_", client.getName(), client.getDartenWurf(), ir1, ir1));
                        client.setDartenWurf(ir1);
                        client.setDartenWurfLast(ir1);
                    } else {
                       
                        client.sendButlerMessage(channel.getName(), String.format("°BB°#_Du_ rollst einen Würfel...#W%s: %s = _%s_", client.getDartenWurf(), ir1, ir1));
                        client.setDartenWurf(ir1);
                        client.setDartenWurfLast(ir1);
                    }
                    if (ir1 != 1) {
                        client.sendButlerMessage(channel.getName(), String.format("Du hast eine _%s_ gewürfelt. °>_h%s|/serverpp \"|/w \"<° wird nun mit einem %s-seitigen Würfel würfeln.", ir1, client.getDartenGegner(), target.getDartenWurf()));
                        target.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hat gerade eine _%s_ gewürfelt. Du bist nun an der Reihe _°BB>_h/dice 1w%s|/dice 1w%s<°°°_ zu würfeln.", client.getName(), ir1, target.getDartenWurf(), client.getDartenWurf()));
                   Long key = null;
                          for (long x : Server.actions.keySet()) {
                              String[] val = Server.actions.get(x);
                              if (val[0].equals("gameWurf") && val[1].equals(client.getName()))  {
                                  key = x;
                              }
                          }
                          if (key != null) {
                        
                        Server.actions.remove(key);
                          }
                        Server.actions.put(time, new String[]{"gameWurf", target.getName(), target.getName(), channel.getName(), "30"});

                    } else {
                        String einsatzArt = "";
                        if (target.getDartenEinsatzArt() == 1) {
                            einsatzArt = "Knuddels";
                        }
                        int win = client.getDartenEinsatz() + client.getDartenEinsatz();
                        channel.broadcastPicAction("°BB°", String.format("Das Spiel Darten gewann _°>_h%s|/serverpp \"|/w \"<°_. Der Gewinn in Höhe von _%s %s_ wurde soeben ausgezahlt.", client.getName(), win, einsatzArt), "../mychannel/cubes_prefix_" + image + ".png");
                        client.increaseKnuddels(win);
                        client.setDartenOpen(0);
                        client.setDartenGegner("");
                        client.setDartenWurf(0);
                        client.setDartenDran(0);
                        client.setDartenPrivat(0);
                        client.setDartenArt(0);
                        client.setDartenEinsatz(0);
                        client.setDartenEinsatzArt(0);

                        target.setDartenOpen(0);
                        target.setDartenGegner("");
                        target.setDartenWurf(0);
                        target.setDartenDran(0);
                        target.setDartenPrivat(0);
                        target.setDartenArt(0);
                        target.setDartenEinsatz(0);
                        target.setDartenEinsatzArt(0);

                    }
                }
            } else {

                String variablen = tools.DiceCreator.dice(arg, 0);
                String[] teil = variablen.split("\\|");
                if (teil[5].equals("0")) {
                    channel.broadcastAction("°BB°", String.format("> %s rollt %s Würfel%s...#%s: %s = _%s_", client.getName(), teil[0], teil[1], teil[2], teil[3], teil[4]));
                    return;
                }
                client.sendButlerMessage(channel.getName(), String.format("%s", teil[2]));
            }

        } else if (cmd.equals("game")) {
            if (!client.hasPermission("cmd.game")) {
                client.sendButlerMessage(channel.getName(), "Die Funktion _/game_ ist für dich leider nicht freigeschaltet.");
                return;
            }

            String nickn = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";
            if (arg.length() > nickn.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }
            String regeln = "";
            String nick = "";
            String gegner = "";
            String du = client.getName();
            int art = 0;
            int wurf = 0;
            int einsatz = 0;

            Random r = new Random();
            int zufall = r.nextInt(7) + 1;
            String image = "";
            if (zufall == 1) {
                image = "blue";
            } else if (zufall == 2) {
                image = "brightgreen";
            } else if (zufall == 3) {
                image = "darkblue";
            } else if (zufall == 4) {
                image = "green";
            } else if (zufall == 5) {
                image = "orange";
            } else if (zufall == 6) {
                image = "pink";
            } else if (zufall == 7) {
                image = "red";
            }

            Client target = Server.get().getClient(msg);
            if (client.getDartenOpen() == 1) {
                art = client.getDartenEinsatzArt();
            }

            if (nickn.toLowerCase().equals("join") && !msg.isEmpty()) {

                gegner = target.getDartenGegner();
                wurf = target.getDartenWurf();
                einsatz = target.getDartenEinsatz();
                if (target.getDartenOpen() == 0) {
                    client.sendButlerMessage(channel.getName(), "Dieses Spiel existiert _nicht mehr_.");
                    return;
                }
                if (!gegner.isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Dieses Spiel existiert _nicht mehr_.");
                    return;
                }
                if (client.getDartenOpen() == 1) {
                    client.sendButlerMessage(channel.getName(), "Du bist bereits in ein Spiel.");
                    return;
                }
                if (einsatz > client.getKnuddels()) {
                    client.sendButlerMessage(channel.getName(), "Soviele Knuddels hast du nicht.");
                    return;
                }

                 Long key = null;
                          for (long x : Server.actions.keySet()) {
                              String[] val = Server.actions.get(x);
                              if (val[0].equals("game") && val[1].equals(client.getName()))  {
                                  key = x;
                              }
                          }
                          if (key != null) {
                        Server.actions.remove(key);
                          }
                client.sendButlerMessage(channel.getName(), String.format("Du bist dem Spiel von _%s beigetreten_. %s wird nun anfangen zu Würfeln.", target.getName(), target.getName()));
                target.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° hat deine Herausforderung angenommen. Du beginnst mit _°BB>_h/dice 1w%s|/dice 1w%s<°°°_.", client.getName(), wurf, wurf));

                Server.actions.put(time, new String[]{"gameWurf", target.getName(), target.getName(), channel.getName(), "30"});

                target.setDartenDran(1);
                target.setDartenGegner(client.getName());
                client.setDartenEinsatzArt(target.getDartenEinsatzArt());
                client.setDartenEinsatz(target.getDartenEinsatz());
                client.setDartenPrivat(target.getDartenPrivat());
                client.setDartenGegner(target.getName());
                client.setDartenOpen(1);
                client.deseaseKnuddels(einsatz);
                client.setDartenWurf(target.getDartenWurf());
                return;
            }

            if (nickn.toLowerCase().equals("details") && !msg.isEmpty()) {

                if (target.getDartenOpen() == 0) {
                    client.sendButlerMessage(channel.getName(), "Dieses Spiel existiert _nicht mehr_.");
                    return;
                }
                if (!target.getDartenGegner().isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Dieses Spiel existiert _nicht mehr_.");
                    return;
                }
                if (target == null) {
                    client.sendButlerMessage(channel.getName(), "Dieses Spiel existiert _nicht mehr_.");
                    return;
                }

                if (target.getDartenArt() == 1) {
                    String dartenArt = "";
                    String dartenEinsatzArt = "";
                    if (target.getDartenArt() == 1) {
                        dartenArt = "Darten";
                    }
                    if (target.getDartenEinsatzArt() == 1) {
                        dartenEinsatzArt = "Knuddels";
                    }
                    regeln = "°>_h" + target.getName() + "|/serverpp |/w <° bietet momentan dieses Spiel an: _" + dartenArt + "_##_Regeln:_ #Du und dein Gegner werfen nacheinander einen _Wurf 1w" + target.getDartenWurf() + "_. Die jeweils erwürfelte Zahl ist die nächste Zahl mit der gewürfelt wird. Der Spieler, der zuerst _die Eins_ wirft gewinnt das Spiel. Solltest du zuerst _eine Eins_ würfeln, so hat dein Gegner noch die Chance ein Unentschieden zu erreichen.##_Einsatz:_ " + target.getDartenEinsatz() + " " + dartenEinsatzArt + "##°-°##_Beispiel:_ CokaColaBoy und HandZup spielen gegeneinander Darten. Das Spiel startet mit 1w10.##°>bullet.b.png<° HandZup wirft 1w10 und trift eine _7_.#°>bullet.b.png<° CokaColaBoy wirft 1w10 und trift eine _3_.#°>bullet.b.png<° HandZup wirft 1w_7_ und trift eine _4_.#°>bullet.b.png<° CokaColaBoy wirft 1w_3_ und trift eine _2_.#°>bullet.b.png<° HandZup wirft 1w_4_ und trift eine _1_.##Wenn in diesem Spiel Nachwürfe aktiv sind, so darf CokaColaBoy noch versuchen mit dem nächsten Wurf ein Unentschieden zu erreichen. Falls Nachwürfe nicht eingeschaltet sind, so hat HandZup nun gewonnen.";
                }
                Popup popup = new Popup("Erklärung des Spielangebotes", null, String.format("°>{imageboxstart}boxS.my_-4.mh_30<°#°20+0015+9515°_Erklärung des Spielangebotes°b° °12+0000°##°+5040°°+9505°°+0020°%s##°>{imageboxend}<°##°>LEFT<°##°>center<°°16>{button}OK||call|_c|width|70|heigth|28<°", regeln), 450, 400);
                popup.setDesign(2);
                client.send(popup.toString());

                return;
            }

            if (arg.equals("stop") && client.getDartenOpen() == 1 && client.getDartenGegner().isEmpty()) {

                client.sendButlerMessage(channel.getName(), String.format("Dein Angebot zum Spielen von _Darten_ wurde soeben gestoppt.", art));
                client.increaseKnuddels(client.getDartenEinsatz());
                client.setDartenOpen(0);
                client.setDartenGegner("");
                client.setDartenWurf(0);
                client.setDartenDran(0);
                client.setDartenPrivat(0);
                client.setDartenArt(0);
                client.setDartenEinsatz(0);
                client.setDartenEinsatzArt(0);
                return;
            }
            if (arg.equals("stop") && client.getDartenOpen() == 1 && !client.getDartenGegner().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Du kannst dein Spiel nicht beenden.");
                return;
            }
            if (arg.equals("stop") && client.getDartenOpen() == 0) {
                client.sendButlerMessage(channel.getName(), "Du hattest kein offenes Spielangebot.");
                return;
            }
            if (arg.equals("?")) {
                client.sendButlerMessage(channel.getName(), "In diesem Channel kannst du diese Spiele starten:#°>bullet.b.png<° Freidiffen °>mychannel/question-mark.png<>--<>|/h Freidiffen<°#°>bullet.b.png<° Diffen °>mychannel/question-mark.png<>--<>|/h Diffen<°#°>bullet.b.png<° Darten °>mychannel/question-mark.png<>--<>|/h Darten<°#°>bullet.b.png<° Dicen °>mychannel/question-mark.png<>--<>|/h Dicen<°");
                return;
            }
            if (arg.equals("again")) {
                if (client.getDartenLastWurf() == 0) {
                    client.sendButlerMessage(channel.getName(), "Die Einstellungen deines letzten Spiels kenne ich leider nicht mehr.");
                    return;
                }
                Client target2 = Server.get().getClient(client.getDartenLastGegner());
                boolean online = true;

                if (target2 == null) {
                    online = false;
                    target2 = new Client(null);
                    target2.loadStats(client.getDartenLastGegner());

                    if (target2.getName() == null && !client.getDartenLastGegner().isEmpty()) {
                        client.send(PacketCreator.game(client.getName()));
                        Popup popup = new Popup("Game - Problem", "Game Problem", "#Dieser User _existiert_ nicht.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;
                    }

                    if ((!online) && !client.getDartenLastGegner().isEmpty()) {
                        client.send(PacketCreator.game(client.getName()));
                        Popup popup = new Popup("Game - Problem", "Game Problem", "#Dieser User ist derzeit _nicht_ online.", 450, 275);
                        Panel panel = new Panel();
                        Button buttonMessage3 = new Button("   OK   ");
                        buttonMessage3.setStyled(true);
                        panel.addComponent(buttonMessage3);
                        popup.addPanel(panel);
                        popup.setLaufbahn(1);
                        client.send(popup.toString());
                        return;

                    }
                }
                if (client.getDartenOpen() == 1) {
                    client.sendButlerMessage(channel.getName(), "Du bist derzeit in ein Spiel!");
                    return;
                }
                if (client.getDartenLastEinsatz() > client.getKnuddels()) {
                    client.sendButlerMessage(channel.getName(), "Soviele Knuddels hast du nicht.");
                    return;
                }
                if (client.getDartenLastEinsatz() > target2.getKnuddels() && !client.getDartenLastGegner().isEmpty()) {
                    client.sendButlerMessage(channel.getName(), String.format("Soviele Knuddels hat _%s_ nicht.", target2.getName()));
                    return;
                }

                String einsatzart = "";
                String privatison = "";
                if (client.getDartenLastEinsatz() > 0) {
                    einsatzart = " Knuddels";

                }

                if (client.getDartenLastPrivat() == 1) {
                    privatison = "Privates würfeln, ";
                }

                if (client.getDartenLastGegner().isEmpty()) {
                    channel.broadcastPicAction(">", String.format("°>_h%s|/serverpp \"|/w \"<° bietet eine Runde °RR22°_Darten_°BB° §an. _°>{button}Mitspielen||call|/game join:%s<°_ °BB°(_°>Startzahl: %s, %s %sEinsatz: %s %s|/game details:%s<°_)", client.getName(), client.getName(), client.getDartenLastWurf(), "Nachwurf Aktiv, ", privatison, client.getDartenLastEinsatz(), einsatzart, client.getName()), "../mychannel/cubes_prefix_" + image + ".png");
                    client.deseaseKnuddels(client.getDartenLastEinsatz());
                    client.setDartenWurfLast(client.getDartenLastWurf());
                    client.setDartenOpen(1);
                    client.setDartenPrivat(client.getDartenLastPrivat());
                    client.setDartenWurf(client.getDartenLastWurf());
                    client.setDartenArt(1);
                    client.setDartenEinsatz(client.getDartenLastEinsatz());
                    client.setDartenEinsatzArt(1);
                } else {
                    target2.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° bietet dir eine Runde °RR22°_Darten_°BB° §an. _°>{button}Mitspielen||call|/game join:%s<°_ °BB°(_°>Startzahl: %s,%s %sEinsatz: %s %s|/game details:%s<°_)", client.getName(), client.getName(), client.getDartenLastWurf(), "Nachwurf Aktiv, ", privatison, client.getDartenLastEinsatz(), einsatzart, client.getName()));
                    client.sendButlerMessage(channel.getName(), String.format("Du hast _%s_ soebend ein Angebot gemacht.", client.getDartenLastGegner()));
                    client.setDartenOpen(1);
                    client.setDartenWurfLast(client.getDartenLastWurf());
                    client.setDartenPrivat(client.getDartenLastPrivat());
                    client.setDartenWurf(client.getDartenLastWurf());
                    client.setDartenArt(1);
                    client.setDartenEinsatz(client.getDartenLastEinsatz());
                    client.setDartenEinsatzArt(1);
                    client.setDartenGegner(client.getDartenLastGegner());
                }
                return;
            }

            if (arg.equals("list")) {
                StringBuilder list = new StringBuilder();

                for (Client user : channel.getClients()) {
                    if (user.getDartenOpen() == 1) {

                        String einsatzArt = "";
                        if (user.getDartenArt() == 1) {
                            einsatzArt = "Darten";
                        }
                        if (user.getDartenOpen() == 1 & user.getDartenPrivat() == 0) {
                            list.append("°>bullet.b.png<° °>_h").append(user.getName()).append("|/serverpp |/w <° bietet eine Runde °RR22°_").append(einsatzArt).append("_§°° an. _°>{button}Mitspielen||call|/game join:").append(user.getName()).append("<°_ (_°>Startzahl: ").append(user.getDartenWurf()).append(", Nachwurf aktiv, Einsatz: ").append(user.getDartenEinsatz()).append(" Knuddels|/game details:").append(user.getName()).append("<°_)#");
                        } else {
                            list.append("Momentan gibt es kein offenes Spielangebot. _°>finger.b.gif<>--<>|/game<>--<° °>--<BB>Eröffne ein Spiel!|/game<°_");
                        }
                    }
                }
                client.sendButlerMessage(channel.getName(), String.format("Folgende Spiele werden momentan angeboten:#%s", list));

                return;
            }

            client.send(PacketCreator.game(client.getName()));
        }

        if (cmd.equals("gameold")) {
            game.make(arg, client, channel);

        } /*
         else if (cmd.equals("ping")) {
         ping.make(arg, client, channel);

         }*/ else if (cmd.equals("login")) {
            if (!client.hasPermission("cmd.login")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            if (arg.equals("all")) {
                arg = "";
                PoolConnection pcon5 = ConnectionPool.getConnection();
                PreparedStatement ps5 = null;
                try {
                    Connection con5 = pcon5.connect();
                    ps5 = con5.prepareStatement("SELECT * FROM accounts");
                    ResultSet rs5 = ps5.executeQuery();
                    while (rs5.next()) {

                        arg += rs5.getString("name") + ",";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (ps5 != null) {
                        try {
                            ps5.close();
                        } catch (SQLException e) {
                        }
                    }
                    pcon5.close();
                }

            }

            for (String args : arg.split(",")) {
                if (!args.isEmpty()) {
                    int fail = 0;
                    Client t = Server.get().getClient(args);
                    for (Client eNick : channel.getClients()) {
                        if (eNick == t) {
                            fail = 1;
                        }
                    }
                    if (fail == 0) {
                        if (t == null) {

                            
                            t = new Client(null);

                            t.loadStats(args);
                        }
                        if (t != null) {
                            
                           if(t.getSperre() == 0) {
                            
                            t.joinChannel(channel);
                            channel.join(t);
                            t.setBot((byte) 1);
                        }}
                    }

                }
            }
        } else if (cmd.equals("logout")) {
             if (!client.hasPermission("cmd.logout")) {
                client.sendButlerMessage(channel.getName(), "Das lassen wir mal schön bleiben "+client.getName());
                return;
            }
            for (String args : arg.split(",")) {
                if (!args.isEmpty()) {
                    Client t = Server.get().getClient(args);
                    if (t == null) {

                        t = new Client(null);

                        t.loadStats(args);
                    }
                    if (t != null) {
                        t.setBot((byte) 0);
                        t.logout("Ausgeloggt!");

                    }
                }
            }
            

        } else if (cmd.equals("mentor")) {
            if (!client.hasPermission("cmd.mentor") && !client.hasPermission("cmd.mentor.reward")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickn = KCodeParser.escape(arg).split(":", 2)[0].trim();
            String msg = "";

            if (arg.length() > nickn.length()) {
                msg = arg.substring(arg.indexOf(':') + 1);
            }

            if (nickn.isEmpty()) {
                if (!client.hasPermission("cmd.mentor")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (client.getSchuetzlinge().isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Du hast noch keine Schützlinge.");
                    return;
                }

                StringBuilder f = new StringBuilder();
                StringBuilder on = new StringBuilder();
                StringBuilder off = new StringBuilder();
                String eingabe = client.getSchuetzlinge().replace("||", "~").replace("|", "");
                String[] strarr = eingabe.split("~");

                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                    String nick = strarr[i];
                    Client c = Server.get().getClient(nick);

                    if (c == null) {
                        c = new Client(null);
                        c.loadStats(nick);

                        Channel n = null;

                        if (c.getLastOnlineChannel() == null) {
                            n = Server.get().getChannel("Flirt");
                        } else {
                            n = Server.get().getChannel(c.getLastOnlineChannel());
                        }

                        off.append("#°>schaf.png<°°%06°_°>_h").append(nick.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°_ war zuletzt am _").append(c.getLastOnlineDate()).append("_ ").append(c.getLastOnlineTime()).append(" im Channel _");

                        if (!n.isVisible()) {
                            off.append("?");
                        } else {
                            off.append("°>_h").append(n.getName()).append("|/go \"|/go +\"<°");
                        }

                        off.append("_");

                        if (c.getReadme() != null) {
                            off.append("#°%06°\"\"").append(Server.get().parseSmileys(c, c.getReadme())).append("§\"\"");
                        }

                        off.append("°%00°");

                    } else {
                        on.append("#°>schaf.png<°°%06°_°>_h").append(nick.replace("<", "\\<")).append("|/serverpp \"|/w \"<°_ ist im Channel _");

                        if (c.getChannel().isVisible()) {
                            on.append("°>_h").append(c.getChannel().getName()).append("|/go \"|/go +\"<°");
                        } else {
                            on.append("?");
                        }

                        on.append("°E° ONLINE_°r°!");

                        if (c.isAway()) {
                            if (client.hasPermission("popup.awayaniicon")) {
                                on.append(" °>icon_away_ani_new.gif<°");
                            } else {
                                on.append(" °>away.png<°");
                            }
                        }

                        if (c.getReadme() != null) {
                            on.append("#°%06°\"\"").append(Server.get().parseSmileys(c, c.getReadme())).append("§\"\"");
                        }

                        on.append("°%00°");
                    }
                }

                f.append(on.toString()).append(on.toString().isEmpty() ? "" : "#").append(off.toString());
                String title = String.format("Deine Schützlinge (%s/%s)", strarr.length, 20);

                Popup popup = new Popup(title, title, f.toString(), 450, 275);
                Panel panel = new Panel();
                Button buttonMessage3 = new Button("   OK   ");
                buttonMessage3.setStyled(true);
                panel.addComponent(buttonMessage3);
                popup.addPanel(panel);
                popup.setMentordetlef(1);
                client.send(popup.toString());
                return;

            }

            if (nickn.toLowerCase().equals("reward") && !msg.isEmpty()) {
                if (!client.hasPermission("cmd.mentor.reward")) {
                    client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                    return;
                }

                if (client.getBelohnen() == 1) {
                    client.sendButlerMessage(channel.getName(), "Du kannst momentan niemanden belohnen.");
                    return;
                }

                Client target = Server.get().getClient(msg);
                boolean online = true;

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(msg);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), unknownUser(msg));
                        return;
                    }
                }

                if (target.getIPAddress().equals(client.getIPAddress())) {
                    client.sendButlerMessage(channel.getName(), String.format("Du kannst %s nicht für die Belohnung auswählen.", target.getName()));
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Du hast %s für die Belohnung ausgewählt.", target.getName()));
                Server.get().newMessage(Server.get().getButler().getName(), target.getName(), "Ein Mentorpunkt", String.format("Frage: Wer hat dir bisher am meisten bei "+Server.get().getSettings("CHAT_NAME")+" geholfen?#Antwort von %s: Ganz klar %s!##Glückwunsch! Aus diesem Grund erhälst du _ein Mentorpunkt_.", client.getName(), target.getName()), time);

                if (online) {
                    target.increaseMentorPoints(1);
                } else {
                    Server.get().query(String.format("update accounts set mentorPoints=mentorPoints+'1' where name='%s'", target.getName()));
                }

                client.setBelohnen((byte) 1);
                return;
            }

            if (!client.hasPermission("cmd.mentor")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            boolean delete = false;

            if (nickn.startsWith("!")) {
                delete = true;
                nickn = nickn.replace("!", "");
            }

            Client target = Server.get().getClient(nickn);

            if (target == null) {
                target = new Client(null);
                target.loadStats(nickn);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), unknownUser(nickn));
                    return;
                }
            }

            if (delete) {
                if (!client.checkSchuetzling(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("%s steht nicht auf deiner Mentor List.", target.getName()));
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("%s wurde von deiner Mentor List entfernt.", target.getName()));
                client.setSchuetzlinge(client.getSchuetzlinge().replace(String.format("|%s|", target.getName()), ""));
                return;
            }

            if (!target.getMentor().equals(client.getName())) {
                client.sendButlerMessage(channel.getName(), String.format("%s ist nicht dein Schützling.", target.getName()));
                return;
            }

            if (client.checkSchuetzling(target.getName())) {
                client.sendButlerMessage(channel.getName(), String.format("%s steht bereits auf deiner Mentor List.", target.getName()));
                return;
            }

            client.sendButlerMessage(channel.getName(), String.format("%s wurde deiner Mentor List wieder hinzugefügt.", target.getName()));
            client.setSchuetzlinge(String.format("%s|%s|", client.getSchuetzlinge(), target.getName()));
        } else if (cmd.equals("mymentor")) {
            if (!client.hasPermission("cmd.mymentor")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            if (client.getMentor().isEmpty()) {
                client.sendButlerMessage(channel.getName(), "Sie haben keinen Mentor.");
                return;
            }

            client.sendButlerMessage(channel.getName(), String.format("Der Nick Ihres Mentors lautet _°>_h%s|/serverpp \"|/w \"<°_.", client.getMentor().replace("<", "\\<")));

        } else if (cmd.equals("changelog")) {
            if (!client.hasPermission("cmd.changelog")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            String nickname = KCodeParser.escape(arg);

            Client target;
            boolean online = true;
            boolean ent = false;

            if (!nickname.isEmpty() && arg.startsWith("+") && client.hasPermission("cmd.changelog")) {
                ent = true;
                nickname = nickname.substring(1).trim();
            }

            if (nickname.isEmpty() || nickname.equalsIgnoreCase(client.getName())) {
                target = client;
            } else {
                target = Server.get().getClient(nickname);

                if (target == null) {
                    online = false;
                    target = new Client(null);
                    target.loadStats(nickname);

                    if (target.getName() == null) {
                        client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                        return;
                    }
                }
            }

            String nick = target.getName();
            String charNick = nick.replace("<", "\\<");
            //holt er von hier!!!
            String title = String.format(""+Server.get().getSettings("CHAT_NAME")+" Changelog");
            StringBuilder changelog = new StringBuilder("");
            int eE = 1, dp = 1, spaces = 0;

            // Header von W2 Beginnend
            //  ahelp.append("°[208,207,255]°°>{colorboxstart}<°°bir°°12°#°+9502°°>LEFT<°°W°_CokaColaBoy wird versteigert! (noch 2Wochen 0Tage)_#_Höchstgebot: 100°>sm_classic_yellow...h_0.gif<° von _°[0,53,217]°_°>_hJames|/w James<°°b°°K°#_°[0,53,217]°°>Jetzt mitbieten!|/auctionme CokaColaBoy<°°°_#");
            changelog.append("§Eingetragen am _08.11.2013_ von _°BB>_hRainbowslide|/w Rainbowslide<°_°r°");
            changelog.append("###Wir haben heute einige kleinere Bugs behoben und Verbesserungen durchgeführt. So werden nun Stammimonate in denen man Stammi ist automatisch im Profil eingetragen.##Neues Smileyfeature: '_TurnedHead_' wurde hinzugefügt.");
            changelog.append("###°-°");
            changelog.append("§Eingetragen am _05.11.2013_ von _°BB>_hRainbowslide|/w Rainbowslide<°_°r°");
            changelog.append("###Neues Smileyfeature: '_Smileypress_', sowie Ein neuer Nickeffekt: 'Neon' wurden hinzugefügt.");

            // W2 Header ENFDE! 
            //  ahelp.append("°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°°bir°°12°###§°bir°°12°°>LEFT<°");
            // Ausgabe der Adminfunktionen möchte ich aber über Datenbank einbauen, daher erstmal hier PAUSE. Joern FRAGEN!
            // Popup wird erzeugt mit title des Names welches er aus String title holt und Größe des Fensters
            Popup popup = new Popup(title, title, changelog.toString(), 500, 400); // Fenster Größe Breite und Höhe.
            Panel panel = new Panel();
            Button buttonMessage3 = new Button("   OK   ");
            buttonMessage3.setStyled(true);
            panel.addComponent(buttonMessage3);
            popup.addPanel(panel);
            popup.setNewspopup(1);
            client.send(popup.toString());

        }

    }
}

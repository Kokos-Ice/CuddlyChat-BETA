package funktionen;

import java.text.*;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.countChars;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class profilvisit {


    public static void functionMake(Client client,Channel channel, String arg) {


if (!client.hasPermission("cmd.profil.visit")) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
                return;
            }

            arg = KCodeParser.escape(arg);

            if (arg.isEmpty()) {
                if (client.getVisitNicks().isEmpty()) {
                    client.sendButlerMessage(channel.getName(), "Momentan steht niemand auf deiner Visit-Liste.");
                } else {
                    StringBuilder visitNicks = new StringBuilder();
                    int countNicks = 1;

                    for (String nick : client.getVisitNicks().split("\\|")) {
                        if (!nick.isEmpty()) {
                            String name = nick.replace("<", "\\<");

                            if (countNicks != 1) {
                                visitNicks.append(", ");
                            }

                            visitNicks.append("°>_h").append(name).append("|/serverpp \"|/w \"<°");
                            countNicks++;
                        }
                    }

                    client.sendButlerMessage(channel.getName(), String.format("Folgende Mitglieder stehen derzeit auf deiner Visit-Liste:#%s", visitNicks.toString()));
                }

                return;
            }

            Client target = Server.get().getClient(arg);
            boolean remove = false;

            if (arg.startsWith("!")) {
                remove = true;
                arg = arg.substring(1);
            }

            if (target == null) {
                target = new Client(null);
                target.loadStats(arg);

                if (target.getName() == null) {
                    client.sendButlerMessage(channel.getName(), String.format("Wer ist %s?", arg));
                    return;
                }
            }

            String nick = target.getName();
            String name = nick.replace("<", "\\<");

            if (remove) {
                if (!client.checkVisit(target.getName())) {

                    client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° steht nicht auf deiner Visit-Liste", name));
                    return;
                }

                Logger.handle(null, String.format("%s wurde auf der Visit-Liste von %s entfernt!", target.getName(), client.getName()));
                Server.get().newSysLogEntry(client.getName(), String.format("%s wurde auf der Visit-Liste entfernt!", name));
                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wurde auf deiner Visit-Liste entfernt!", name));
                client.removeVisitNick(target.getName());
            } else {
                int maxVisitNicks = client.getRank() * 5 + 10;
                int currentVisitNicks = countChars(client.getVisitNicks(), '|') / 2;
                String lastVisitNick = "";

                if (!client.getVisitNicks().isEmpty()) {
                    lastVisitNick = client.getVisitNicks().substring(1).split("\\|")[0];
                }

                if (target == client) {
                    client.sendButlerMessage(channel.getName(), "Du kannst dich nicht selber auf der Visit-Liste eintragen!");
                    return;
                }

                if (client.checkVisit(target.getName())) {
                    client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° steht bereits auf deiner Visit-Liste.", name));
                    return;
                }

                client.sendButlerMessage(channel.getName(), String.format("°>_h%s|/serverpp \"|/w \"<° wurde auf deiner Visit-Liste hinzugefügt!", name, nick, currentVisitNicks == maxVisitNicks ? String.format("#Da deine Visit List voll war, wurde %s wieder von deiner Visit List entfernt.", lastVisitNick) : "", name, name));

                if (currentVisitNicks == maxVisitNicks) {
                    client.removeVisitNick(lastVisitNick);
                }

                client.addVisitNick(target.getName());

            }
    }
}

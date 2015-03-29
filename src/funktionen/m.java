package funktionen;

import static funktionen.his.time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import starlight.*;
import tools.*;
import tools.database.*;
import tools.popup.*;

public class m {


    public static void functionMake(Client client,Channel channel, String arg) {
 
        
         if(!client.hasPermission("cmd.m")) {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur Verfügung.");
            return;
            } 

            String nickname = KCodeParser.escape(arg).split(":", 2)[0];
            String msg = "";

            if (arg.length() > nickname.length()) {
            msg = arg.substring(arg.indexOf(':') + 1);
            }

            if(nickname.equals("-")) {
            client.send(PacketCreator.createMessage2Window("", "", ""));
            return;
            }

            String n = nickname.toLowerCase();

            if(nickname.equals("sig")) {
            client.send(PacketCreator.createSignatureWindow(client));
            return;
            }




            Client target = Server.get().getClient(nickname);
            if(n.isEmpty() || n.equals("get") || n.equals("delete") || n.equals("archiv") || n.equals("lock") || n.equals("+") || n.equals("old") || n.equals("sent")) {
            StringBuilder messages = new StringBuilder();

            if(n.equals("lock")) {
            if(!client.hasPermission("cmd.m.lock")) {
            client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur VerfÃ¼gung.");
            return;
            }

            if(msg.toLowerCase().equals("on".toLowerCase())) {
            client.sendButlerMessage(channel.getName(), "Briefkasten _ausgeschaltet_.");
            client.setSilence((byte)1);
            return;
            }

            if(msg.toLowerCase().equals("off".toLowerCase())) {
            client.sendButlerMessage(channel.getName(), "Briefkasten _eingeschaltet_.");	
            client.setSilence((byte)0);
            return;
            }

            client.sendButlerMessage(channel.getName(), "Falscher Parameter");
            return;
            }

            if (n.equals("delete")) {
            Server.get().query(String.format("update messages set archiv='0' where time='%s' and an = '%s'", msg,client.getName()));
            client.sendButlerMessage(channel.getName(), "Nachricht wurde aus dem Archiv gelöscht.");
            client.archivMessages.clear();


            return;
            } 


            String titel = "";
            if (n.isEmpty()) {
            titel = "Dein Briefkasten";    
            } else
            if (n.equals("get")) {
            titel = "Dein Briefkasten";    
            } else
            if (n.equals("old")) {
            titel = "Alte Nachrichten";    
            } else
            if (n.equals("+")) {
            titel = "Dein Briefkasten";    
            } else    
            if (n.equals("sent")) {
            titel = "Gesendete Nachrichten";    
            } else
            if (n.equals("archiv")) {
            titel = "Gespeicherte Nachrichten";    
            }    

            messages.append("k\0").append(titel).append("õf\0\0\0hÿÿÿocloseButtonõãEl  õcgPhÿÿÿãWl  õcgPhÿÿÿãSl õcgPhÿÿÿãCpBNpBNl           õgIf\0\0\0hÿÿÿãCpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãCpBNpBNpBNc");
            messages.append("°20°_").append(titel).append("õs\0\0(f\0\0\0hÞÞÿoãããCc°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#°+9022+0013°#");



            if (n.equals("archiv")) {

            if(msg.isEmpty()) {

            if(client.archivMessages.size() < 1) {
            client.sendButlerMessage(channel.getName(), "Du hast keine alten Nachrichten in deinem Briefkasten.");
            return;
            }

            for(String[] old : client.archivMessages) {
            String von = old[1];
            String betreff = old[2];
            String text = old[3];

            long tim = Long.parseLong(old[0]);
            String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(tim), Server.get().timeStampToTime(tim));
            text = text.replace("[q]","§");
            text = text.replace("[/q]","");

            Client who = Server.get().getClient(von);
            boolean online = true;

            if(who == null) {
            online = false;
            who = new Client(null);
            who.loadStats(von);
            }

            String whoChar = who.getName().replace("<", "\\<");


            text = Server.get().parseSmileys(who, text);
            if(who.getPhoto().isEmpty()) {
            messages.append("°>nopic_79x79_").append(who.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<°");
            } else {
            messages.append("°>photos/photo/getPicture.php?l&img=").append(who.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(whoChar).append("<°");
            }
            String sig = "";
            String butlerm = "";

            if (!who.getSignatur().isEmpty()) {
            sig = String.format("°>{signaturestart}<°°° §#°05° °>layout/hr_over-sg.png<°#°05° #°+701012°°>{globalopacity}50<°%s°>{globalopacity}100<°#", who.getSignatur());    
            }
            if (who == Server.get().getButler()) {
            butlerm = "°>posts/sig_systemmsg...h_28.my_4.mx_-5.png<°#";  
            } 
            String rang = "";
            if(who.getRank() ==0) { 
            rang = " °K°Mitglied°K°";
            }  
            if(who.getRank() ==1) { 
            rang = "°[0,0,150]°Familymitglied°K°";
            } 
            if(who.getRank() ==2) { 
            rang ="°[0,0,150]°Stammi°K°";
            }
            if(who.getRank() ==3) { 
            rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
            }
            if(who.getRank() ==5) { 
            rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
            }
            if(who.getRank() ==6) { 
            rang = "°R°Admin°K° °>ct/sm_ehren_02.gif<°";
            }
            if(who.getRank() ==7) { 
            rang ="°R°Admin°K° °>ct/sm_ehren_02.gif<°";
            }
            if(who.getRank() >7) { 
            rang = "°R°Sysadmin°K° °>ct/sm_ehren_02.gif<°";
            }
            text = Server.get().parseSmileys(who, text);
            messages.append("#°+0075+8002°°>").append(!online?"off":who==Server.get().getButler() || who.isAway()?"ono":"ong").append("...my_1.png<° °14°_°BB>_h").append(whoChar).append("|/serverpp \"|/w \"<°°°_(_").append(rang).append("_)#").append(uhrzeit).append("#_").append(betreff.isEmpty()?"°r°_":betreff+"§#").append(!betreff.isEmpty()?"°r°_":betreff+"§##").append("§°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°").append(text).append(sig).append("##°+5000°°r°°>center<°").append(butlerm).append("°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|137|5|104|5|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort an ").append(whoChar).append("|/m ").append(whoChar).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort mit Zitat|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archivedel...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archivedel_hover...clickchange.clicknotoggle.useIdInLink.mx_-40.png<>--<>|/m delete:").append(tim).append("<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<°#°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#");

            }

            messages.append("#õsD?tsendbackõf\0\0\0hÞÞÿzMããEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããããSpBWl                                                           õgPf\0\0\0hÿÿÿãCbSchließenõ~closeButtonõcedbgPf\0\0\0hÿÿÿãEl                                                           õgPf\0\0\0hÿÿÿãããã");
            client.send(messages.toString());

            return;
            }    


            String text = "";
            String betreff = "";
            String von = "";
            String an = "";
            int archiv = 0;


            PoolConnection pcon2 = ConnectionPool.getConnection();
                   PreparedStatement ps2 = null;
                try {
                Connection con2 = pcon2.connect();
                ps2 = con2.prepareStatement("SELECT * FROM messages WHERE time  = '"+msg+"' and an = '"+client.getName()+"' order by id limit 1");
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next())  {           
                  an = rs2.getString("an");
                  long tim = Long.parseLong(rs2.getString("time"));
                  text = rs2.getString("text");
                  archiv = rs2.getInt("archiv");

       
       
                if (!text.isEmpty() && archiv == 0) {
                Server.get().query(String.format("update messages set archiv='1' where time='%s' and an = '%s'", msg,client.getName()));
                client.sendButlerMessage(channel.getName(), "Nachricht wurde erfolgreich ins Archiv abgelegt.");
                client.archivMessages.clear();
                PoolConnection pcon0 = ConnectionPool.getConnection();
                PreparedStatement ps0 = null;
                try {
                Connection con0 = pcon0.connect();
                ps0 = con0.prepareStatement("SELECT * FROM messages WHERE archiv  = '1' and an = '"+client.getName()+"' order by id");
                ResultSet rs0 = ps0.executeQuery();
                while (rs0.next())  { 
                von = rs0.getString("von");
                an = rs0.getString("an");
                text = rs0.getString("text");
                betreff = rs0.getString("betreff");  

                client.archivMessages.add(new String[] {String.valueOf(tim), von, betreff, text});

                }}  catch (SQLException e) {} finally {  if (ps0 != null) {try {  ps0.close();  } catch (SQLException e) {}} pcon0.close(); }

                } else {
                 client.sendButlerMessage(channel.getName(), "Diese Nachricht ist schon in deinem Archiv.");

                }}}  catch (SQLException e) { e.printStackTrace();  } finally {  if (ps2 != null) try {  ps2.close();  } catch (SQLException e) {  } pcon2.close(); }


                } else

                if(n.equals("old")) {
                if(client.oldMessages.size() < 1) {
                client.sendButlerMessage(channel.getName(), "Du hast keine alten Nachrichten in deinem Briefkasten.");
                return;
                }

                for(String[] old : client.oldMessages) {
                String von = old[1];
                String betreff = old[2];
                String text = old[3];
                long tim = Long.parseLong(old[0]);
                String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(tim), Server.get().timeStampToTime(tim));
                text = text.replace("[q]","§");
                text = text.replace("[/q]","");
                Client who = Server.get().getClient(von);
                boolean online = true;
                if(who == null) {
                online = false;
                who = new Client(null);
                who.loadStats(von);
                }

                String whoChar = who.getName().replace("<", "\\<");

                if(who.getPhoto().isEmpty()) {
                messages.append("°>nopic_79x79_").append(who.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<°");
                } else {
                messages.append("°>photos/photo/getPicture.php?l&img=").append(who.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(whoChar).append("<°");
                }
                String sig = "";
                String butlerm = "";
                if (!who.getSignatur().isEmpty()) {
                sig = String.format("°>{signaturestart}<°°° §#°05° °>layout/hr_over-sg.png<°#°05° #°+701012°°>{globalopacity}50<°%s°>{globalopacity}100<°#", who.getSignatur());    
                }
                if (who == Server.get().getButler()) {
                butlerm = "°>posts/sig_systemmsg...h_28.my_4.mx_-5.png<°#";  
                } 
                String rang = "";
                if(who.getRank() ==0) { 
                rang = " °K°Mitglied°K°";
                }  
                if(who.getRank() ==1) { 
                rang = "°[0,0,150]°Familymitglied°K°";
                } 
                if(who.getRank() ==2) { 
                rang ="°[0,0,150]°Stammi°K°";
                }
                if(who.getRank() ==3) { 
                rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
                }
                if(who.getRank() ==5) { 
                rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
                }
                if(who.getRank() ==6) { 
                rang = "°R°Admin°K° °>ct/sm_ehren_02.gif<°";
                }
                if(who.getRank() ==7) { 
                rang ="°R°Admin°K° °>ct/sm_ehren_02.gif<°";
                }
                if(who.getRank() >7) { 
                rang = "°R°Sysadmin°K° °>ct/sm_ehren_02.gif<°";
                }

                messages.append("#°+0075+8002°°>").append(!online?"off":who==Server.get().getButler() || who.isAway()?"ono":"ong").append("...my_1.png<° °14°_°BB>_h").append(whoChar).append("|/serverpp \"|/w \"<°°°_(_").append(rang).append("_)#").append(uhrzeit).append("#_").append(betreff.isEmpty()?"°r°_":betreff+"§#").append(!betreff.isEmpty()?"°r°_":betreff+"§##").append("§°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°").append(text).append(sig).append("##°+5000°°r°°>center<°").append(butlerm).append("°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|137|5|104|5|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort an ").append(whoChar).append("|/m ").append(whoChar).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort mit Zitat|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m archiv:").append(tim).append("<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<°#°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#");
                }
                messages.append("#õsD?tsendbackõf\0\0\0hÞÞÿzMããEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããããSpBWl                                                           õgPf\0\0\0hÿÿÿãCbSchließenõ~closeButtonõcedbgPf\0\0\0hÿÿÿãEl                                                           õgPf\0\0\0hÿÿÿãããã");
                client.send(messages.toString());
                } else
                if(n.equals("sent")) {
                if(client.sentMessages.size() == 0) {
                client.sendButlerMessage(channel.getName(), "Es sind keine von dir gesendeten Nachrichten gespeichert.");
                return;
                }

                int aus = 0;
                for(String[] old : client.sentMessages) {
                  if (aus <= 15) {
                String an = old[1];
                String betreff = old[2];
                String text = old[3];

                aus++;
                long tim = Long.parseLong(old[0]);
                String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(tim), Server.get().timeStampToTime(tim));
                text = text.replace("[q]","§");
                text = text.replace("[/q]","");

                Client who = Server.get().getClient(an);
                boolean online = true;

                if(who == null) {
                online = false;
                who = new Client(null);
                who.loadStats(an);
                }

                String whoChar = who.getName().replace("<", "\\<");


                if(who.getPhoto().isEmpty()) {
                messages.append("°>nopic_79x79_").append(who.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<°");
                } else {
                messages.append("°>photos/photo/getPicture.php?l&img=").append(who.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(whoChar).append("<°");
                }
                String sig = "";
                String butlerm = "";                          
                if (!who.getSignatur().isEmpty()) {
                sig = String.format("°>{signaturestart}<°°° §#°05° °>layout/hr_over-sg.png<°#°05° #°+701012°°>{globalopacity}50<°%s°>{globalopacity}100<°#", who.getSignatur());    
                }
                if (who == Server.get().getButler()) {
                butlerm = "°>posts/sig_systemmsg...h_28.my_4.mx_-5.png<°#";    
                }
                String rang = "";
                if(who.getRank() ==0) { 
                rang = " °K°Mitglied°K°";
                }  
                if(who.getRank() ==1) { 
                rang = "°[0,0,150]°Familymitglied°K°";
                } 
                if(who.getRank() ==2) { 
                rang ="°[0,0,150]°Stammi°K°";
                }
                if(who.getRank() ==3) { 
                rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
                }
                if(who.getRank() ==5) { 
                rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
                }
                if(who.getRank() ==6) { 
                rang = "°R°Admin°K° °>ct/sm_ehren_02.gif<°";
                }
                if(who.getRank() ==7) { 
                rang ="°R°Admin°K° °>ct/sm_ehren_02.gif<°";
                }
                if(who.getRank() >7) { 
                rang = "°R°Sysadmin°K° °>ct/sm_ehren_02.gif<°";
                }
                text = Server.get().parseSmileys(who, text);
                messages.append("#°+0075+8002°°>").append(!online?"off":who==Server.get().getButler() || who.isAway()?"ono":"ong").append("...my_1.png<° °14°_°BB>_h").append(whoChar).append("|/serverpp \"|/w \"<°°°_(_").append(rang).append("_)#").append(uhrzeit).append("#_").append(betreff.isEmpty()?"°r°_":betreff+"§#").append(!betreff.isEmpty()?"°r°_":betreff+"§##").append("§°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°").append(text).append(sig).append("##°+5000°°r°°>center<°").append(butlerm).append("°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|137|5|104|5|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort an ").append(whoChar).append("|/m ").append(whoChar).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort mit Zitat|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m archiv:").append(tim).append("<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<°#°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#");

                }}
                messages.append("#õsD?tsendbackõf\0\0\0hÞÞÿzMããEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããããSpBWl                                                           õgPf\0\0\0hÿÿÿãCbSchließenõ~closeButtonõcedbgPf\0\0\0hÿÿÿãEl                                                           õgPf\0\0\0hÿÿÿãããã");
                client.send(messages.toString());
                } else {
                  if(client.newMessages.size() == 0) {
                client.sendButlerMessage(channel.getName(), "Es liegen leider _keine Nachrichten_ für dich vor.");
                return;
                }

                for(String[] old : client.newMessages) {
                long tim = Long.parseLong(old[0]);
                String von = old[1];
                String betreff = old[2];
                String text = old[3];

                String uhrzeit = String.format("%s %s", Server.get().timeStampToDate(tim), Server.get().timeStampToTime(tim));
                text = text.replace("[q]","§");
                text = text.replace("[/q]","");

                Client who = Server.get().getClient(von);
                boolean online = true;

                if(who == null) {
                online = false;
                who = new Client(null);
                who.loadStats(von);
                }

                String whoChar = who.getName().replace("<", "\\<");
                text = Server.get().parseSmileys(who, text);
                if(who.getPhoto().isEmpty()) {
                messages.append("°>nopic_79x79_").append(who.getGender()==2?"f":"m").append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<°");
                } else {
                messages.append("°>photos/photo/getPicture.php?l&img=").append(who.getPhoto()).append("...bordercol_164,164,255.hbordercol_3,0,227.quadcut_49.border_2.jpg<>--<>_h|").append(Server.get().getURL()).append("photo?n=").append(whoChar).append("<°");
                }
                String sig = "";
                String butlerm = "";                          
                if (!who.getSignatur().isEmpty()) {
                sig = String.format("°>{signaturestart}<°°° §#°05° °>layout/hr_over-sg.png<°#°05° #°+701012°°>{globalopacity}50<°%s°>{globalopacity}100<°#", who.getSignatur());    
                }
                if (who == Server.get().getButler()) {
                butlerm = "°>posts/sig_systemmsg...h_28.my_4.mx_-5.png<°#";    
                }
                String rang = "";
                if(who.getRank() ==0) { 
                rang = "°K°Mitglied°K°";
                }  
                if(who.getRank() ==1) { 
                rang = "°[0,0,150]°Familymitglied°K°";
                } 
                if(who.getRank() ==2) { 
                rang ="°[0,0,150]°Stammi°K°";
                }
                if(who.getRank() ==3) { 
                rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
                }
                if(who.getRank() ==5) { 
                rang ="°[0,0,150]°Ehrenmitglied°K° °>ct/sm_ehren_01.gif<°";
                }
                if(who.getRank() ==6) { 
                rang = "°R°Admin°K° °>ct/sm_ehren_02.gif<°";
                }
                if(who.getRank() ==7) { 
                rang ="°R°Admin°K° °>ct/sm_ehren_02.gif<°";
                }
                if(who.getRank() >7) { 
                rang = "°R°Sysadmin°K° °>ct/sm_ehren_02.gif<°";
                }
                messages.append("#°+0075+8002°°>").append(!online?"off":who==Server.get().getButler() || who.isAway()?"ono":"ong").append("...my_1.png<° °14°_°BB>_h").append(whoChar).append("|/serverpp \"|/w \"<°°°_(_").append(rang).append("_)#").append(uhrzeit).append("#_").append(betreff.isEmpty()?"°r°_":betreff+"§#").append(!betreff.isEmpty()?"°r°_":betreff+"§##").append("§°+9515°°>center<°°>/posts/hr_inmsg.png<°# #°+9003°°>left<°°+0011r+509°°12°").append(text).append(sig).append("##°+5000°°r°°>center<°").append(butlerm).append("°>/posts/fade_endmsg.png<°°>{imageboxend}<°# °+9048°°>left<°#°r°#°>/posts/hr_inmsg.png<°§#°+9010° #°>{table|min5|min34|min5|min34|w1|137|5|104|5|79|w1|min34|min5|min34|min5}<>center<16° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort an ").append(whoChar).append("|/m ").append(whoChar).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hAntwort mit Zitat|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°4° #°12°_°BB>_hWeiterleiten|/m quote:").append(tim).append("<r°_°4°# °[193,193,255]>{colorboxend}<° °>{tc}<° °>{tc}<° °>{tc}<° °>{tc}<°°[193,193,255]>{colorboxstart}<°°3° #°>posts/archive...clickchange.clicknotoggle.useIdInLink.w_24.h_17.png|posts/archive_hover...clickchange.clicknotoggle.useIdInLink.mx_-42.my_1.png<>--<>|/m archiv:").append(tim).append("<°#°3° °[193,193,255]>{colorboxend}<° °>{tc}<° °>{endtable}<°#°1° °>left<°#°+950412>/posts/hr_nextmsg.png<°#°r°°>{imageboxforeground}scroll-shadow.ending_511.loadimages_130<°#°>{imageboxstart}bgpattern_msg.loadimages_16.repeat.fileending2_jpg<°°>posts/fade_startmsg...h_0.png<°#");
                if(!client.oldMessages.contains(old)) {
                client.oldMessages.add(old);
                }}
                messages.append("#õsD?tsendbackõf\0\0\0hÞÞÿzMããEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããããSpBWl                                                           õgPf\0\0\0hÿÿÿãCbSchließenõ~closeButtonõcedbgPf\0\0\0hÿÿÿãEl                                                           õgPf\0\0\0hÿÿÿãããã");
                Server.get().query(String.format("UPDATE `messages` SET `gelesen` = '1' WHERE `gelesen` = '0' AND `an` = '%s'", client.getName()));
                client.send(messages.toString());
                client.send(PacketCreator.postCountChanged(0));  
                client.newMessages.clear();
                }

                return;
                }



                if (nickname.toLowerCase().equals("quote")) {

                if(msg.isEmpty()) {
                return;
                }    
                String text = "";
                String betreff = "";
                String von = "";
                String an = "";

                PoolConnection pcon2 = ConnectionPool.getConnection();
                PreparedStatement ps2 = null;
                try {
                Connection con2 = pcon2.connect();
                ps2 = con2.prepareStatement("SELECT * FROM messages WHERE time  = '"+msg+"' and an = '"+client.getName()+"' order by id limit 1");
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next())  {           
                von = rs2.getString("von");
                an = rs2.getString("an");
                long tim = Long.parseLong(rs2.getString("time"));

                    text = String.format("[q]\"%s schrieb am %s %s an %s:#%s[/q]",von,Server.get().timeStampToDate(tim),Server.get().timeStampToTime(tim), an, rs2.getString("text"));
                    betreff = rs2.getString("betreff");   
                    }}  catch (SQLException e) { e.printStackTrace();  } finally {  if (ps2 != null) try {  ps2.close();  } catch (SQLException e) {  } pcon2.close(); }
                    betreff = betreff.replace("°>posts/msg_ico_xmark...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_qmark...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_warning...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_clover...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_bulb...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_thumbdown...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_thumbup...w_20.h_8.my_3.mx_-10.png<°","").replace("°>posts/msg_ico_sun...w_20.h_8.my_3.mx_-10.png<°","");
                    client.send(PacketCreator.createMessage3Window(von, betreff, text));

                    return;
                    }

                if(nickname.toLowerCase().equals("*cm")) {
                if(!channel.checkHz(client.getName()) && !channel.checkCm(client.getName())) {
                client.sendButlerMessage(channel.getName(), "Diese Funktion steht dir hier nicht zur VerfÃ¼gung.");
                return;
                }

                if(msg.isEmpty()) {
                return;
                }

                client.sendButlerMessage(channel.getName(), String.format("Deine Nachricht wurde an alle CMs und HZA/E _gesendet_."));
                String betreff = String.format("Rundmail von %s an alle CMs / HZA des Channels %s", client.getName(), channel.getName());

                for(String cm : channel.getCms().split("\\|")) {
                if(!cm.isEmpty()) {
                Server.get().newMessage(Server.get().getButler().getName(), cm, betreff, msg, time);
                }

                }

                PoolConnection pcon1 = ConnectionPool.getConnection();
                Statement ps1 = null;

                try {
                Connection con1 = pcon1.connect();
                ps1 = con1.createStatement();
                ResultSet rs1 = ps1.executeQuery("SELECT `name`, `hz` FROM `accounts` WHERE  `hz` != ''");

                while(rs1.next()) {
                String hz = rs1.getString("hz");
                String name = rs1.getString("name");

                if(hz.contains(String.format("|%s|", channel.getName()))) {
                Server.get().newMessage(Server.get().getButler().getName(), name, betreff, msg, time);
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

                return;
                }

                if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                return;
                }
                }



                if (target == null) {
                target = new Client(null);
                target.loadStats(nickname);

                if(target.getName() == null) {
                client.sendButlerMessage(channel.getName(), CommandParser.unknownUser(nickname));
                return;
                }
                }
                if(msg.isEmpty()) {
                client.send(PacketCreator.createMessageWindow(target.getName(), "", ""));
                return;
                } 
                if (msg.length() > 2000) {
                client.sendButlerMessage(channel.getName(), "Deine Nachricht konnte nicht gesendet werden:##Ihre _Nachricht ist zu lang_, sie darf höchstens 2000 Zeichen lang sein.");
                return;
                }
                if(target == Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), String.format("Deine Nachricht konnte nicht gesendet werden:##An %s können keine Nachrichten geschickt werden.", Server.get().getButler().getName()));
                return;
                }

                String betreff = "";

                if(msg.contains("§")) {
                String[] split = msg.split("\\§");

                msg = split[1]; 
                betreff = split[0];

                if(betreff.length() > 50) {
                client.sendButlerMessage(channel.getName(), "Deine Nachricht konnte nicht gesendet werden:##Der _Betreff Ihrer Nachricht ist zu lang_, dieser darf höchstens 50 Zeichen lang sein.");
                return;
                }
                }
                betreff = KCodeParser.noKCode(betreff);
                msg = KCodeParser.noKCode(msg);
                if(client != Server.get().getButler()) {
                client.sendButlerMessage(channel.getName(), CommandParser.messageSent(target.getName()));
                }
                Server.get().newMessage(client.getName(), target.getName(), betreff, msg, time);

        
    }
}

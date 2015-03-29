package tools;

import static funktionen.his.time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import starlight.*;
import static starlight.CommandParser.parse;
import static starlight.CommandParser.unknownUser;
import static starlight.CommandParser.userIsOffline;
import static starlight.CommandParser2.replaceLast;
import static starlight.FunctionParser.countChars;
import tools.*;
import tools.database.*;
import tools.popup.*;


public class w2 {

        
    private static NumberFormat nf;
    private static DecimalFormat df;
     static {
        nf = NumberFormat.getInstance(Locale.GERMAN);
        df = new DecimalFormat("0.00");
    }
     

    public static void showPopup(Client client,Client target,Channel channel,boolean online,String tab) {
          
          
        if(target.checkIgnored(client.getName())) {
            client.sendButlerMessage(channel.getName(), String.format("Du wirst von %s ignoriert und kannst deshalb die Whois nicht einsehen.", target.getName()));
            return;
        }
        
        
        boolean ent = false;

 
            String nick = target.getName();
            String charNick = nick.replace("<", "\\<");
            String title = String.format("Profil von "+target.getName());
         
            StringBuilder left = new StringBuilder("");          
            StringBuilder center = new StringBuilder("");
            StringBuilder right = new StringBuilder("");
            StringBuilder rightheight = new StringBuilder("");
            int eE = 1, dp = 1,dp2 = 1, spaces = 0;

            int friends = countChars(target.getFriendlist(),'|')/2;          
            
                    left.append("#°>{+scroll}<°°>{-scrollbar}<°°>{table|10|153|0|430|10|155}<°#°>{tr}<°##°>{imageboxstart}boxS.my_-15.mh_30<°°>{tc}<°#°12°#");      
          
           if(online) {
               
                 if(client == target) {
                    left.append("");
               } else {
                    left.append("");
               }        
                 if(target.getChannel() == null || !target.getChannel().isVisible()) {
                    left.append("°+0001°°>w2/w2_group_on...mx_0.my_0.h_0.w_0.png<° °+0022° _°[0,53,217]°?_#°+0000°#°+9508°");  
               } else { 
                    left.append("°+0001°°>w2/w2_group_on...mx_0.my_0.h_0.w_0.png<° °+0022° _°[0,53,217]°°>_h").append(target.getChannel().getName()).append("|/go \"<°_#°+0000°#°+9508°");
               }
               } else {
                 if(target.getLastOnlineChannel() == null) {
                    left.append("°+0001>w2/w2_group_off...mx_0.my_0.h_0.w_0.png<° °+0022°Niemals online!#°+0000°#°+9508°");    
              
               } else {
                     Channel lastchannel = Server.get().getChannel(target.getLastOnlineChannel());
                    if(lastchannel == null || !lastchannel.isVisible()) {
                    left.append("°+0001>w2/w2_group_off...mx_0.my_0.h_0.w_0.png<° °+0022°_°[0,53,217]°?_#°+0000°#°+9508°");
               } else {
                    left.append("°+0001>w2/w2_group_off...mx_0.my_0.h_0.w_0.png<° °+0022°_°[0,53,217]°").append(target.getLastOnlineChannel()).append("_#");
               }
                   
                   
                    left.append("°+0000°°K°am _").append(target.getLastOnlineDate()).append("_ ").append(target.getLastOnlineTime()).append("#°+0000°#°+9508°");            
               }
               }
            
                    left.append("°>transparent1x1...quadcut_130.h_130.w_130.gif<>--<>|"+Server.get().getURL()+"index.php?page=photo_user&n="+target.getName()+"<>--<>");
            
   
                    left.append("photos/photo/getPicture.php?l&img=");            
                    left.append(target.getPhotoDetails()[0]);   
                    left.append("...quadcut_128.mx_-130.h_0.w_0.border_3.bordercol_164,164,255.hbordercol_3,0,227.");
                    left.append(target.getPhotoDetails()[1]);  
                    left.append("<°#°+9505°");
                    int anzahlalben = query.count("select count(id) as a from fotoalben where von='"+target.getName()+"'");
                    if (anzahlalben >= 1) {
                    left.append("#°+9502°°[0,53,217]°°>w2/w2_start_morepics.png<°#°+9016+0035°_°>_h");
                    left.append(target.getAlbum_counter());
                    left.append(" weitere Fotos|").append(Server.get().getURL()).append("index.php?page=album_user&n=").append(target.getName()).append("&photo<°");
                    }
                    left.append("°b°§°bir°°12°°>LEFT<°#°W°°>{linkhovercolor}<°°+0005°°+9505°°W°#");
       
                    if (target != client) {
                    left.append("°>{imageboxstart}pullbox-dark_t.ending_511.hover.w_144.mh_1.click__p/m ").append(target.getName()).append("<°#°+9502°_°>w2/actionmenu_message...h_0.mx_-2.png<° Nachricht senden°b°#°>{imageboxend}<°#");
                    }      
                    if(target == client) {
                    left.append("°>{imageboxstart}pullbox-dark_t.ending_511.hover.w_144.mh_1.click__p/e ").append(target.getName()).append("<°#°+9502°_°>w2/actionmenu_edit...h_0.mx_-2.png<° Profil bearbeiten°b°#°>{imageboxend}<°#");   
                    } 
                    if (target == client && client.getPhoto().isEmpty()) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/foto").append("<°#°+9501°_°>w2/actionmenu_foto...h_0.mx_-2.png<° Foto hochladen°b°#°>{imageboxend}<°#");          
                    }   
                    String photo = target.getPhoto();
                    if (target == client && !photo.isEmpty()) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/foto").append("<°#°+9501°_°>w2/actionmenu_foto...h_0.mx_-2.png<° Foto bearbeiten°b°#°>{imageboxend}<°#");     
                    }            
                    if (target == client && anzahlalben == 0)  {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/album").append("<°#°+9501°_°>w2/actionmenu_album...h_0.mx_-2.png<° Fotoalbum anlegen°b°#°>{imageboxend}<°#"); 
                    }  
                    if (target != client) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/missyou").append(target.getName()).append("<°#°+9501°_°>w2/actionmenu_miss...h_0.mx_-2.png<° Miss You°b°#°>{imageboxend}<°#");
                    }
                    if (target != client) {
                
                if (target.checkFriendask(client.getName())) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/w ").append(target.getName()).append("<°#°+9501°_°[206,206,232]°°>w2/actionmenu_added...h_0.mx_-2.png<° Anfrage läuft °>w2/char_check_white.png<°°W°°b°#°>{imageboxend}<°#");
                } else  if (client.getFriendlist().contains("|"+target.getName()+"|")) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/f !").append(target.getName()).append("<°#°+9501°_°[206,206,232]°°>w2/actionmenu_added...h_0.mx_-2.png<° Befreundet °>w2/char_check_white.png<°°W°°b°#°>{imageboxend}<°#");
                } else {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/f ").append(target.getName()).append("<°#°+9501°_°>w2/actionmenu_add...h_0.mx_-2.png<° Freund°W°°b°#°>{imageboxend}<°#");
                     
                }
                }
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.w_144.mh_1.click__p/code buy<°#°+9501°_°>w2/actionmenu_smiley...h_0.mx_-2.png<° Smiley schenken°b°#°>{imageboxend}<°#");
                    left.append("°>{imageboxstart}pullbox-dark_b.ending_511.hover.w_144.mh_1.click_/openpulldown id_1234\\.w_150\\.h_63\\.location_2310<°#°+9502°_Mehr...°b°#°>{imageboxend}<°");
                    left.append("°>{definetext|1234}<°°+0005°°12°°+9002°°W°°>{linkhovercolor}<°°W°°>{imageboxbackground}pullbox_border.ending_0<°");
                    /* ACTION UNTERMENUE */               
                    
                     // Homepage anlegen
                    if (target == client && target.getHP() == 0) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.mh_1.click__p/hp ").append(target.getName()).append("<°#°+9501°_Homepage anlegen°b°#°>{imageboxend}<°#");  
                    }  
                    
                             
                    
                   // if (target.getHP() == 1) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.mh_1.click__p/hp ").append(target.getName()).append("<°#°+9501°_Homepage öffnen°b°#°>{imageboxend}<°#");
                   //  }
                   // Gästebuch öffnen
                   // if (target.getHP() == 1) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.mh_1.click__p/gb ").append(target.getName()).append("<°#°+9501°_Gästebuch öffnen°b°#°>{imageboxend}<°#");
                   //   }
                    /*
                    // Logins öffnen
                    if (target == client) {
                    left.append("°>{imageboxstart}pullbox-dark_m.ending_511.hover.mh_1.click__p/loginlist").append("<°#°+9502°_Login-Liste°b°#°>{imageboxend}<°#");
                    }
                    */
                   // Watchlist öffnen
                    if (target == client) {
                    left.append("°>{imageboxstart}pullbox-dark_b.ending_511.hover.mh_1.click__p/f").append("<°#°+9502°_Watchlist aufrufen°b°#°>{imageboxend}<°#");
                    }
                    
                    
                   // User ignorieren
                    if (target != client) {
                    left.append("°>{imageboxstart}pullbox-dark_b.ending_511.hover.mh_1.click__p/ig ").append(target.getName()).append("<°#°+9502°_Ignore°b°#°>{imageboxend}<°#");
                    }
                    
                    left.append("°+9005°°>{definetext|1234}<°°bir12°#°+9007°#°+0000%0°°>{linkhovercolorreset}<°");
                     // Abfrage für Age einfügen ob Age überhaupt angegeben ist bzw nicht 0 Ist.
             if (target.getAge() >= 1) {
                    left.append("§°bir°°12°°>LEFT<°#°+9512°°>w2/w2_left-split_arrow...mx_-3.w_5.png<°_°[0,53,217]°°>_h"+target.getAge()+" Jahre|/w2g AGEANDGENDER~121<°°K°°b°");
                    }
             if (target.getGender() == 1) {
                    left.append(" °>gender_male...b.my_1.h_0.png<°");
                }
             if (target.getGender() == 2) {
                    left.append(" °>gender_female...b.my_1.h_0.png<°");
                }
               
             if (!target.getVergeben().isEmpty()) {
                  left.append("°[0,53,217]°°b°");
                    left.append("§°bir°°12°°>LEFT<°°bir°°12°#°+9500°°>w2/w2_left-split_arrow...mx_-3.w_5.png<°_°[0,53,217]°°>_h").append(target.getVergeben()).append("|/w2g MOOD~13<°°K°°b°°+9500°");
                     left.append("§°bir°°12°°>LEFT<°§°bir°°12°°>LEFT<°");
                }
             
                    left.append("°+9505°#°>cc/bar_greyStrong...clipw_135.w_0.mx_0.my_0.png<°°+9505°#°+9505°_°R°°>w2/w2_left-split_arrow...mx_-3.w_5.png<°_");
                    left.append(target.getRankLabelWhois2(target.getRank())+"_°12°#°+9502+0005°°[137,138,142]°Reg.: °K°"+target.getRegistrationDate()+" "+target.getRegistrationTime2()+"#°[137,138,142]°Online: °[0,53,217]°°>_h"+nf.format(target.getOnlineTime()/60)+"|/top online all<°°K° Min#°+9508°°[137,138,142]°Knuddel: °K°°[0,53,217]°°>_h"+target.getKnuddels()+"|/h knuddel<° °>sm_classic_yellow...h_0.gif<°°bir°°12°°b°°>{imageboxend}<°°>{tc}<°##°>{tc}<°#");     
        
            
            
               // DEFINE TABS!
                    left.append("_°12>{button}Start||images|btn1.ending_511~btn1_hover.ending_511~btn1_hover.ending_511.mx_1.my_1|textcolor|0,0,0|noborder|1|call|_r/w "+target.getName()+":Start|width|80|heigth|28<b° ");
                    left.append("_°12>{button}Person||images|btn1.ending_511~btn1_hover.ending_511~btn1_hover.ending_511.mx_1.my_1|textcolor|77,77,77|noborder|1|call|_r/w "+target.getName()+":Person|width|80|heigth|28<b° ");
                    left.append("_°12>{button}"+Server.get().getSettings("CHAT_NAME")+"||images|btn1.ending_511~btn1_hover.ending_511~btn1_hover.ending_511.mx_1.my_1|textcolor|77,77,77|noborder|1|call|_r/w "+target.getName()+":"+Server.get().getSettings("CHAT_NAME")+"|width|80|heigth|28<b° ");
                    left.append("_°12>{button}Freunde||images|btn1.ending_511~btn1_hover.ending_511~btn1_hover.ending_511.mx_1.my_1|textcolor|77,77,77|noborder|1|call|_r/w "+target.getName()+":Freunde|width|80|heigth|28<b° ");
                    if(client.hasPermission("cmd.admininfo")) {
                    left.append("_°12>{button}Admin||images|btn1.ending_511~btn1_hover.ending_511~btn1_hover.ending_511.mx_1.my_1|textcolor|77,77,77|noborder|1|call|_r/w "+target.getName()+":Admin|width|80|heigth|28<b°");
                    }
           
           
            
            List friendslist = new ArrayList<String>();
            List alluserlist = new ArrayList<String>();
            for(String x : target.getFriendlist().split("\\|")) {
                if (!x.isEmpty()) {
                  friendslist.add(x);
                }
                
            }
            
               
          PoolConnection pcon2 = ConnectionPool.getConnection();
        PreparedStatement ps2 = null;
      
        try {
           Connection con = pcon2.connect();
           ps2 = con.prepareStatement("SELECT name FROM `accounts` order by rand() desc limit 6");
          ResultSet rs = ps2.executeQuery();
          while (rs.next()) {
              alluserlist.add(rs.getString("name"));
            }
        }
        catch (SQLException e) {
         e.printStackTrace();
       } finally {
          if (ps2 != null)
            try {
             ps2.close();
            }
            catch (SQLException e)
            {
            }
           pcon2.close();
        }
            
            Collections.shuffle(friendslist);
            
            String f1 = "";
            String f2 = "";
            String f3 = "";
            if (friendslist.size() >= 1) {
            f1 = String.valueOf(friendslist.get(0));
            }
              if (friendslist.size() >= 2) {
            f2 = String.valueOf(friendslist.get(1));
              } 
                if (friendslist.size() >= 3) {
            f3 = String.valueOf(friendslist.get(2));
                }
               // int anzahlalben = query.count("select count(id) as a from fotoalben where von='"+target.getName()+"'");
            
            // POPUP RECHTE 
                 right.append("°>{tc}<°##°>{imageboxstart}boxS.my_-15.mh_30<°°>{tc}<°##°bir°°12°");
            // ALBUM
            if (anzahlalben >= 1) {
                 right.append("°>CENTER<°_°[0,53,217]°°>_hFotoalben|"+Server.get().getURL()+"index.php?page=album_user&n="+target.getName()+"&photo<°°b°°K° ("+anzahlalben+")#°>LEFT<°#°>CENTER<°°[0,53,217]°_°>_h"+target.getAlbum_counter()+" weitere Fotos|"+Server.get().getURL()+"index.php?page=album_user&n="+target.getName()+"&photo<°°b°#°>LEFT<°°+9502°°>transparent1x1...h_41.w_0.gif<°°>photos/album/getPicture.php?xl&img="+target.getAlbum_cover_image()+"...quadcut_60.h_64.w_64.border_2.albborder_4.bordercol_164,164,255.hbordercol_3,0,227.jpg<>--<>|"+Server.get().getURL()+"index.php?page=album_user&n="+target.getName()+"&photo<°");
            }else {
                 rightheight.append("########");  
            }
            // TRENNSTRICH
            if (anzahlalben >= 1) {
                 right.append("°+7073°°+7000+0000°#°+9515°#°+9503°°>cc/bar_greyStrong...clipw_142.w_0.mx_-1.png<°#°+9505°#°+9001°");
            }
             
            // FREUNDE
            if (!target.getFriendlist().isEmpty()) {
                right.append("°>CENTER<°_°[0,53,217]°°>_hFreunde|/showfriends "+target.getName()+"<°°b°°K° ("+friends+")#°>LEFT<°#°+9510°");
           
            if (!f1.isEmpty()) {
                Client friend1 = Server.get().getClient(f1); if (friend1 == null) { friend1 = new Client(null); friend1.loadStats(f1); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+f1+"<>--<>photos/photo/getPicture.php?l&img="+friend1.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+friend1.getPhotoDetails()[1]+"<°");
            }
             if (!f2.isEmpty()) {
                   Client friend2 = Server.get().getClient(f2); if (friend2 == null) { friend2 = new Client(null); friend2.loadStats(f2); }
                   
                right.append("°+7049°");            
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+f2+"<>--<>photos/photo/getPicture.php?l&img="+friend2.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+friend2.getPhotoDetails()[1]+"<°");
              }
               if (!f3.isEmpty()) {
                     Client friend3 = Server.get().getClient(f3); if (friend3 == null) { friend3 = new Client(null); friend3.loadStats(f3); }
                right.append("°+7049°");
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+f3+"<>--<>photos/photo/getPicture.php?l&img="+friend3.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+friend3.getPhotoDetails()[1]+"<°");
               }
                right.append("#°+9510°°+7000+0000°°+7000+0000°°>LEFT<°#°+9510°°K°°+9001°");
            } else {
              rightheight.append("######");  
            } 
            // Kennst du schon XYZ?
                right.append("°>CENTER<°_Kennst du schon?°b°#°>LEFT<°#°+9510°");
            Client alluser1 = Server.get().getClient(String.valueOf(alluserlist.get(0))); if (alluser1 == null) { alluser1 = new Client(null); alluser1.loadStats(String.valueOf(alluserlist.get(0))); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+alluser1.getName()+"<>--<>photos/photo/getPicture.php?l&img="+alluser1.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+alluser1.getPhotoDetails()[1]+"<°");
                right.append("°+7049°");
            Client alluser2 = Server.get().getClient(String.valueOf(alluserlist.get(1))); if (alluser2 == null) { alluser2 = new Client(null); alluser2.loadStats(String.valueOf(alluserlist.get(1))); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+alluser2.getName()+"<>--<>photos/photo/getPicture.php?l&img="+alluser2.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+alluser2.getPhotoDetails()[1]+"<°");
                right.append("°+7049°");
            Client alluser3 = Server.get().getClient(String.valueOf(alluserlist.get(2))); if (alluser3 == null) { alluser3 = new Client(null); alluser3.loadStats(String.valueOf(alluserlist.get(2))); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+alluser3.getName()+"<>--<>photos/photo/getPicture.php?l&img="+alluser3.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+alluser3.getPhotoDetails()[1]+"<°");
                right.append("#°+9510°°+7000+0000°");
            Client alluser4 = Server.get().getClient(String.valueOf(alluserlist.get(3))); if (alluser4 == null) { alluser4 = new Client(null); alluser4.loadStats(String.valueOf(alluserlist.get(3))); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+alluser4.getName()+"<>--<>photos/photo/getPicture.php?l&img="+alluser4.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+alluser4.getPhotoDetails()[1]+"<°");
                right.append("°+7049°");
            Client alluser5 = Server.get().getClient(String.valueOf(alluserlist.get(4))); if (alluser5 == null) { alluser5 = new Client(null); alluser5.loadStats(String.valueOf(alluserlist.get(4))); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+alluser5.getName()+"<>--<>photos/photo/getPicture.php?l&img="+alluser5.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+alluser5.getPhotoDetails()[1]+"<°");
                right.append("°+7049°");
            Client alluser6 = Server.get().getClient(String.valueOf(alluserlist.get(5))); if (alluser6 == null) { alluser6 = new Client(null); alluser6.loadStats(String.valueOf(alluserlist.get(5))); }
                right.append("°>transparent1x1...quadcut_40.h_40.w_40.gif<>--<>|/w "+alluser6.getName()+"<>--<>photos/photo/getPicture.php?l&img="+alluser6.getPhotoDetails()[0]+"...quadcut_39.mx_-40.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227."+alluser6.getPhotoDetails()[1]+"<°");
            
                right.append(rightheight.toString());
                right.append("######°>{imageboxend}<°°>{tc}<°");
                right.append("°>{tr}<°");
                right.append("°>{endtable}<°");
      
            
            // POPUP MITTE 
            if (tab.equals("Start")) {
                
            // ADMININFOS Start-TAB
                
                if (channel.checkCm(client.getName()) || channel.checkHz(client.getName()) || client.getRank() > 2 || client.getTeams().contains("~1|") || client.checkTeam("Spiele") || client.checkTeam("Jugendschutz")) {
                    for (Channel s : Server.get().getChannels()) {
                        if (target.checkCl(s)) {
                            center.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_ von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
     
                        if (Server.get().checkCcm(target.getName(), s, 2)) {
                            center.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" (Color) von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
     
                        if (Server.get().checkCcm(target.getName(), s, 3)) {
                            center.append("#- _°>_h").append(s.getName()).append("|/go \"|/go +\"<°_").append(" von °>_h").append("").append("|/serverpp \"|/w \"<°");
                        }
                    }
     
                    if (target.getDisable() != 0) {
                        if (client.getRank() > 6 && !client.checkTeam("Vertrauensadmin")) {
                            center.append("_°12°Nick ist derzeit °R°deaktiviert°r°°12°_#");
                        }
                    }
     
                    if (target.getDeletenick() != 0) {
                        if (client.getRank() > 3) {
                            center.append("_°12°Nick ist zur °R°Löschung°r°°12° freigegeben!_#");
                        }
                    }
     
                    if (target.getSpielsperre() != 0) {
                        center.append("_°12°Momentan für alle Spiele °R°gesperrt_°r°°12°#");
                    }
     
                    if (target.getWahlsperre() != 0) {
                        center.append("_°12°Bis zum ").append(Server.get().timeStampToDate(target.getWahlsperre())).append(" °12°für alle Wahlen °R°gesperrt_°r°°12°#");
                    }
                   
                     if(target.getSperre() == 1) {
                             if(client.getRank() > 3 || channel.checkHz(client.getName()) || client.hasPermission("cmd.append.header.admin")) {
                        center.append("°12°_").append(target.getName());
                                    center.append("°12° ist von ").append(target.getSperrevon()).append(" °12°°R°Permanent gesperrt_°r°°12°");          
                               center.append("°12°#_Begründung:#").append(target.getSperreinfo()).append("°12°_");
                            } else {
                               center.append("°12°_").append(target.getName());
                                    center.append("°12° ist derzeit °R°gesperrt_°r°°12°");          
                             }
                           
                           
                            center.append("##");
                    }
     
              if(target.getSperre() > 1) {
                        if(client.getRank() > 3 || channel.checkHz(client.getName()) || client.hasPermission("cmd.append.header.admin")) {
                        center.append("°12°_").append(target.getName());
                                    center.append("°12° ist von ").append(target.getSperrevon()).append("°12° bis zum ").append(Server.get().timeStampToDate(target.getSperre())).append("°12°°R° gesperrt_°r°°12°");
                    center.append("°12°#_Begründung:#").append(target.getSperreinfo()).append("_##");            
                        } else {
                           center.append("°12°_").append(target.getName());
                                    center.append("°12° ist derzeit °R°gesperrt_°r°°12°");
                                   
                    }
              }
              
                }
                  
     
               /*     if (!cls.toString().isEmpty()) {
                        center.append("°B°_°12°Channellocks_:").append(cls.toString()).append("##");
                    }
     
                    if (!mutes.toString().isEmpty()) {
                        center.append("°B°_°12°Gemutet_:").append(mutes.toString()).append("##");
                    }
                
                */
                
                
  
                // FEATURES START-TAB 
                
                
                
            /* Versteigerung von NICKNAME */
                String test = target.getRestdauerAuction();
            if (!target.getAuctionEnd().isEmpty()) {              
                center.append("##°[229,141,15,179]°°>{colorboxstart}<°°bir°°12°#°+9502°°>CENTER<°°W°_"+target.getName()+" wird versteigert! (noch "+target.getRestdauerAuction()+")_");
              if (!target.getLastBieter().isEmpty()) {
                center.append("#_Höchstgebot: "+target.getLastBieter().split("~")[0]+"°>sm_classic_yellow...h_0.gif<° von _°[0,53,217]°_°>_h"+target.getLastBieter().split("~")[1]+"|/w "+target.getLastBieter().split("~")[1]+"<°°b°°K°");
              }
                if (target != client) {
                center.append("#_°[0,53,217]°°>Jetzt mitbieten!|/auctionme "+target.getName()+"<°°°_");
              }
                center.append("#°+9502°°[229,141,15,179]°#°+9502°°>{colorboxend}<°#°r°°>left<°##");            
              }
            /* Versteigerung von NICKNAME ENDE */
            
            
            
            
            
            
            
            
            //  center.append("#°+9515°°12°                 - Hier wird später deine persönliche Wildspace stehen -     #");         
               center.append("#§°bir°°12°°>LEFT<°°+9510°°>cc/bar_greyPale...clipw_420.w_0.mx_-1.png<°#°+9505°");
            
            
            if (target.getRank() > 0) {
               center.append("°+0000°#°+9504°").append(target.getRankLabelWhois3(target.getRank())).append("°b°°b°°12° seit ").append(target.getDate() == null ?"?":target.getDate()).append("°bir°#°12°°+9515°§°bir°°12°°>LEFT<°");
             }
          
            
            // Hall of Fame / Teams / CM / HZA-E
            
            
             StringBuilder hz = new StringBuilder();

            for (Channel c : Server.get().getChannels()) {
                if (c.isVisible()) {
                    if (c.checkHz(target.getName())) {
                        hz.append(eE != 1 ? ", " : "").append("°[0,53,217]°_°>_h").append(c.getName()).append("|/go \"|/go +\"<°°b°°bir°°12°");
                        eE++;
                    }
                }
            }
            
            boolean isCM = false;
            String cmChannel = "";
            
            for(Channel cha : Server.get().getChannels()) {
                if (cha.checkCm(target.getName())) {
                    isCM = true;
                    cmChannel = cha.getName();
                }
                        
            }
            
            
             boolean isMCM = false;
            String mcmChannel = "";
            
            for(Channel cha2 : Server.get().getChannels()) {
                if (cha2.checkMcm(target.getName())) {
                    isMCM = true;
                    mcmChannel = cha2.getName();
                }
            }
            
            
            if (!target.getTeams().isEmpty() || !hz.toString().isEmpty() || isCM || isMCM) {
            center.append("#°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_°b°#°+0000+9502+5020°"); 
            
            StringBuilder teams = new StringBuilder();
            StringBuilder tl = new StringBuilder();
            
              String eingabe = target.getTeams().replace("||", "<").replace("|", "");
                String[] strarr = eingabe.split("<");
                Arrays.sort(strarr);
                for (int i = 0; i < strarr.length; i++) {
                    String team = "";
                    String extra = "";
                    String[] x = strarr[i].split("~");
                if (strarr[i].contains("~")) {                   
                    team = x[0];            
                 extra = x[1];
                } else {
                    team = strarr[i];
                }
                    
                    
                    if (extra.equals("1")) {
                           tl.append(dp2 != 1 ? ", " : "").append("°[0,53,217]°_°>_h").append(team);
                           tl.append("|/h ").append(team).append("-team<°°b°°bir°°12°");
                    dp2++;
                    } else {
                    teams.append(dp != 1 ? ", " : "").append("°[0,53,217]°_°>_h").append(team);
                    /*if(extra.equals("2")) {
                     pem.append(" (Vorsitz)");
                     } else if(extra.equals("3")) {
                     pem.append(" (Forumsmoderator)");
                     } else if(extra.equals("4")) {
                     pem.append(" (Forumsadmin)");
                     } else if(extra.equals("5")) {
                     pem.append(" (Forums-Sysadmin)");
                     } else if(extra.equals("6")) {
                     pem.append(" (Teamnick)");
                     }
                     */
                     if(extra.equals("6")) {
                     teams.append(" (Teamnick)");
                     }
                    teams.append("|/h ").append(team).append("-team<°°b°°bir°°12°");
                    dp++;
                    }

    
                }
                
                // Hier Später die Hall of Fame-Abfrage
                
               /* if (!target.getHallOfFame() == 1) {
                    center.append("#°+9504°°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Aufgenommen in die_°b° °[0,53,217]°_°>_hHall of Fame|/hof "+target.getName()+"<°_°b°§°bir°°12°°>LEFT<°");
                } */
                
                // Hall of Fame ENDE
                  
                
                 // Abfrage für MCM und CM erweitern dass wenn man in mehreren Channels MCM oder CM ist, dann statt MCM im Channel...  Dann MCM in den Channels CHANNELNAME1, CHANNELNAME2, CHANNELNAME3 kommt
                 if (isMCM) {
                    center.append("#°+9505°°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°>mcm.png<° °+0000+9017+7050°im Channel °[0,53,217]°_°>_h"+mcmChannel+"|/go +\"|/info \"<°°b°§°bir°°12°°>LEFT<°");
                }
                               
               
                if (isCM) {
                     byte months = (byte) (CommandParser.countChars(target.getCmwhen(), ',')+1);
                    center.append("#°+9505°°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°>cm.png<° °+0000+9017+7045°im Channel °[0,53,217]°_°>_h"+cmChannel+"|/go +\"|/info \"<°°b°§°bir°°12° ("+months+" CM-Monate)°>LEFT<°");
                }
                
             
                if (!tl.toString().isEmpty()) {
                center.append("#°+9504°°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Teamleiter:_°b° "+tl.toString());
                 }
                
                if (!teams.toString().isEmpty()) {
                center.append("#°+9504°°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Team");
                if (dp != 2) {
                center.append("s");
                }
                center.append(":_°b° "+teams.toString());
                 }
                
                if (!hz.toString().isEmpty()) {
                center.append("#°+9504°°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_");
                if (target.getRank() >= 6) {
                center.append("HZA");
                }else {
                center.append("HZE");
                }
                center.append(":°b° "+hz.toString());
            }
                
                        
                
                center.append("°b°°[174,174,255,127]°#°+9508°°>{colorboxend}<°#°r°°>left<°#°+9510°");
            }
            // boxend
               
            
               //center.append("°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_§°bir°°12°°>LEFT<°°b°#°+0000+9502°°S18°_ Recht°%50°°S18°Bezeichnung°%00°_#°r°°+9502°°[174,174,255,127]°#°+9502°°>{colorboxend}<°#°r°°>left<°##");
            
            
            /* FEATURE BEREICH */
             
            
            
             /* LOVELETTER */
              if (!target.getZusammen().isEmpty()) {
                center.append("§#°12°°>loveletter_whois...b.my_4.h_20.png<°°[0,53,217]° _").append(charNick).append("_ °K°°12°§°K12°ist fest zusammen mit§ _°12°°[0,53,217]°°>_h").append(target.getZusammen().replace("<", "\\<")).append("|/serverpp \"|/w \"<°°K°_.§");
            }
              
              
              
              
              
            /* HELD DES TAGES */
              if (!target.getHeroToday().isEmpty()) {
                center.append("#°12°").append(charNick).append(" ist der Held von °>_h").append(target.getHeroToday().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append("°>features/hero-of-the-day/ft_hotd_wappen_color...h_20.w_28.my_1.png<°#§");
            }
            
              
              
              
              
              
              
            /* HAT ERSTEIGERT */
        
                if (!target.getAuctionTo().isEmpty()) {
       
            int i = 0;
                   StringBuilder auction = new StringBuilder();              
                for(String v : target.getAuctionTo().split("\\|")) {
                    if (!v.isEmpty()) {
                        if (i != 0) {
                           auction.append("°12°, °12°");
                        }
                        auction.append("°12°°[0,53,217]°_°12°°>_h"+v+"|/w "+v+"<°°12°°b°°r°");
                        i++;
                       
                    }}
                   
            center.append("§#°12°°>features/auctionme/auction_profile-start...h_16.png<°°12° °[0,53,217]°°12°_°>_h"+target.getName()+"|/w "+target.getName()+"<°_°12°°b°°r° °12°hat °12°"+replaceLast(auction.toString(),"°12°§,§°12°§ ", "§°12° und§°12° §°12°")+"§ °12°ersteigert.§");
           
            }
                
                
                
                
           /* WURDE ERSTEIGERT */     
                 
    if (!target.getAuctionFrom().isEmpty()) {
           center.append("§#°12°°>features/auctionme/auction_profile-start...h_16.png<° Ersteigert von °[0,53,217]°_°>_h"+target.getAuctionFrom()+"|/w "+target.getAuctionFrom()+"<°°b°°r°.§");
    }
    
    
    
    
               
          /* IST IMMER FÜR NICK DA */
     if(target.getNeveralone() != null) {
                        center.append("#°12°°>features/neveralone/neveralone_mf...b.my_10.w_40.h_27.png<°§ °BB°°12°_°>_h").append(target.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°").append("_°r°°12° ist immer für °BB°_°>_h").append(target.getNeveralone().replace("<", "\\<")).append("|/serverpp \"|/w \"<°°12°°r°_°12° da.§");
                    }

           
     
     
     
     
     
          /* LOVE YOU / LIEBT XYZ */
          String getloveyou = target.getLoveyou(); 
        
         
                if(target.getLoveyou() != null) {
                     Client loveyouto = Server.get().getClient(getloveyou);
         if (loveyouto == null) {
             loveyouto = new Client(null);
             loveyouto.loadStats(getloveyou);
         }
                    if(target.getName().equals(loveyouto.getLoveyou())) {
                    center.append("#°12°°>features/love-you/whois_loves-mf...b.h_15.gif<° °[0,53,217]°_°12° °>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° °12°und °[0,53,217]°_°>_h"+target.getLoveyou()+"|/w "+target.getLoveyou()+"<°°b°°K°°12° lieben sich. ");
                    } else {
                       center.append("#°12°°>features/love-you/whois_loves-mf...b.h_15.gif<° °[0,53,217]°_°12°°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K°°12° liebt °[0,53,217]°_°>_h"+target.getLoveyou()+"|/w "+target.getLoveyou()+"<°°12°°b°°K°.");
                     }
                }
    
    
                
                  if (target.getTurnedHeadFrom().equals(target.getTurnedHeadTo().replace("|","")) && !target.getTurnedHeadFrom().isEmpty()) {
              Client target2 = Server.get().getClient(target.getTurnedHeadFrom());
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(target.getTurnedHeadFrom());                
            }
                  int lastgender = target2.getGender();
                String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                 
                 
                center.append("#°12°°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°12°°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K°°12° und °[0,53,217]°_°12°°>_h"+target.getTurnedHeadFrom()+"|/w "+target.getTurnedHeadFrom()+"<°°b°°K° °12°haben sich gegenseitig den Kopf verdreht.");
            } else {
           
            if (!target.getTurnedHeadFrom().isEmpty()) {
               
                Client target2 = Server.get().getClient(target.getTurnedHeadFrom());
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(target.getTurnedHeadFrom());                
            }
                  int lastgender = target2.getGender();
                 String image = ""; if (lastgender == 2) { if (target.getGender() == 2) { image = "ff"; } else { image = "mf"; }} else { if (target.getGender() == 2) { image = "fm"; } else { image = "mm"; }}
                 center.append("#°12°°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°12°°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K° °12°wurde von °[0,53,217]°_°12°°>_h"+target.getTurnedHeadFrom()+"|/w "+target.getTurnedHeadFrom()+"<°°b°°K°  °12°der Kopf verdreht.");
               
            }
            if (!target.getTurnedHeadTo().isEmpty()) {
                int lastgender = 0;
                int i3 = 0;
               StringBuilder head = new StringBuilder();              
            for(String v : target.getTurnedHeadTo().split("\\|")) {
                if (!v.isEmpty()) {
                    if (i3 != 0) {
                        head.append(", ");
                    }
                    head.append("°[0,53,217]°_°>_h"+v+"|/w "+v+"<°°b°°K°");
                    Client target2 = Server.get().getClient(v);
                    if (target2 == null) {
                target2 = new Client(null);
                target2.loadStats(v);                
            }
                    lastgender = target2.getGender();
                    i3++;
                   
                }
            }
               
                     String image = ""; if (target.getGender() == 2) { if (lastgender == 2) { image = "ff"; } else { image = "mf"; }} else { if (lastgender == 2) { image = "fm"; } else { image = "mm"; }}
                   
                 
               
                center.append("#°12°°>features/turned-head/turned-head_profil_"+image+"...b.my_2.h_15.w_42.png<°°[0,53,217]°_°12°°>_h"+target.getName()+"|/w "+target.getName()+"<°°b°°K°°12° hat "+replaceLast(head.toString(),", ","°12° und ")+" °12°den Kopf verdreht.");
            }}
           
           
                  
                  
                /* HOLDHANDS */
                  
                  
                 StringBuilder holdhands = new StringBuilder();
            int h = 1;
           
                for(String hold : target.getHoldHands().split("\\|")) {
                        if(!hold.isEmpty()){
                                if(h != 1){
                                        holdhands.append(", ");
                                }
                               
                                holdhands.append("_°12°°BB>_h").append(hold.replace("<", "\\<")).append("|/serverpp \"|/w \"<r°°12°_");
                                h++;
                        }  
                }
               
            String holdhandsToString = holdhands.toString();
           
            if(!holdhandsToString.isEmpty()) {
                char gend = 'm';
               
                if(target.getGender() == 2){
                        gend = 'f';
                }
               
                center.append("#°12°°>holdhands_").append(gend).append("...b.my_7.h_21.png<° _°12°°R°Händchen gehalten°r°_ °12°mit ").append(holdhandsToString);
            }
           
                
                
            
            //left.append("#°K°#°>LEFT<°°[0,53,217]°_°>_hiSpectra|/w2 iSpectra<°°b°°K° ist der Schwarm °>features/rhapsody/rhapsody_profile-bg...b.my_13.h_15.w_31.png<>features/rhapsody/rhapsody_profile-ani...b.mx_-31.my_13.h_15.w_0.gif<° von °[0,53,217]°_°>_hSpectra|/w2 Spectra<°°b°°K°.#°+0000+9503°iSpectra hat _°[0,53,217]°°>_h2|/h secretkisses<>fotomeet/fm-profil_lips...h_0.png<>|/h secretkisses<>_h Secret Kisses|/h secretkisses<K°_ erhalten.#§°bir°°12°°>LEFT<°°+0000+9515°°>transparent1x1...quadcut_15.mx_-13.h_15.w_15.mousey_-13.mousex_15.gif<>--<>|/w2 Spectra<>--<>nopic...quadcut_15.mx_-13.my_-14.h_0.w_0.border_2.bordercol_164,164,255.hbordercol_3,0,227.gif<°#°+0000+0024+9032° °+0024°°>features/burning-hearts/whois_herztausch-normal_01...b.my_5.h_15.gif<° _Herztausch_ mit °[0,53,217]°_°>_hSpectra|/w2 Spectra:Knuddels<°°b°°K°#°+9505°#°+9510°");
            
            
            /* FEATURE BEREICH ENDE */
             
              center.append("#§°bir°°12°°>LEFT<°°+9510°°>cc/bar_greyPale...clipw_420.w_0.mx_-1.png<°#°+9505°");
             
             
             // center.append("##°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_§°bir°°12°°>LEFT<°°b°#°+0000+9502+5020°"); 
              
              
              center.append("#°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_°b°#°+0000+9502+5020°"); 
           
            if (!target.getRealName().isEmpty()) {
              center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Name: °b°°K°°+0135°_"+target.getRealName()+" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°");
            }
            
            if (!target.getBirthday().isEmpty()) {
              center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Geburtstag: °b°°K°°+0135°_°[0,53,217]°").append(target.getBirthday()).append("°K° (°[0,53,217]°").append(Zodiac.getZodiacSign(target.getBirthday())).append("°K°)°b°°K°#°+0000+9503°°>LEFT<°");
            }
            
            
              center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Bei "+Server.get().getSettings("CHAT_NAME")+": °b°°K°°+0135°_°[0,53,217]°°>_h"+Server.get().getSettings("CHAT_NAME")+" ist Kult|/w2g AT_KNUDDELS~14<°°K°°b°°K°#°+0000+9503°°>LEFT<°");

            if (!target.getHobbys().isEmpty()) {
              center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Hobbys: °b°°K°°+0135°_°+0135+5002°_°[0,53,217]°_").append(target.getHobbys()).append(" °b°°K°#°+0000+9503°°>LEFT<°");
            }
            
            if (!target.getStadt().isEmpty()) {
              center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Stadt: °b°°K°°+0135°_").append(target.getStadt()).append("_ °b°°K°#°+0000+9503°°>LEFT<°");
            }
            
             center.append("#°+0330°_°[0,53,217]°°>_hmehr...|/w ").append(target.getName()).append(":Person<°°K°°b°§°bir°°12°°>LEFT<°°b°°[174,174,255,127]°#°+9502°°>{colorboxend}<°°bir°°12°§°bir°°12°");
             
             StringBuilder box = new StringBuilder();
             
             
             int showbox = 0;
              if (!target.getLC().isEmpty()) {
              box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Lieblings-Channel: °b°°K°°+0135°°[0,53,217]°_°>_h").append(target.getLC()).append("|/go +\"|/info \"<°°b° °K°#°+0000+9503°°>LEFT<°");           
            showbox = 1;
              }
              
               if (target.getRoses() > 0) {
              box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Rosen: °b°°K°°+0135°°[0,53,217]°_°>_h"+target.getRoses()+"|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_head_01...h_20.w.0.mx_-98.my_5.png<>--<>|/rose colors:").append(charNick).append("<r°_°12°°b°#°+0000+9503°");
            showbox = 1;
               }
               if (target.getCodeE() > 0) {
              box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Geschenke: °b°°+0135°°[0,53,217]°°[0,53,217]°_°>_h"+target.getCodeE()+"|/shop|/h abo<>--<>present...my_2.h_10.gif|present_ani...my_2.gif<>--<>_h|www.knuddels.de/abo|/h abo<°_°K° erhalten#°+0000+9503°");
               showbox = 1;
               }
               if (target.getKisses() > 0) {
              box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°_Knutschflecken: °b°°K°°+0135°°[0,53,217]°_°>_h"+target.getKisses()+"|/h knutschfleck<°°b°#°+0000+9503°");
              showbox = 1;
               }
               if (showbox == 1) {
                  center.append("°>LEFT<°#°+9510°°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_§°bir°°12°");          
                  center.append(box.toString());
                  center.append("°>LEFT<°#°+0330°_°[0,53,217]°°>_hmehr...|/ww James:Knuddels<°°K°°b°°b°°[174,174,255,127]°#°+9502°");
               center.append("°>{colorboxend}<°°bir°°12°§°bir°°12°°>LEFT<°#°+9505°");
               }
            center.append("°>LEFT<°°b°#°+0000+9502°");
            
            
             // PERSON TAB START //
            
            } else if (tab.equals("Person")) {
                  // Überschrift Personen-Tab
              center.append("##°+0005°°[137,138,142]°°14°_Allgemein & Kontakt_#");
                
                  // Realname
              if (!target.getRealName().isEmpty()) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Name: °b°°K°°+0135°_°[0,53,217]°").append(target.getRealName()).append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
            }
                 // Bei Heaven24:
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Bei "+Server.get().getSettings("CHAT_NAME")+": °b°°K°°+0135°_°[0,53,217]°°>_h"+Server.get().getSettings("CHAT_NAME")+" ist Kult|/w2g AT_KNUDDELS~14<°°K°°b°°K°#°+0000+9503°°>LEFT<°§");
                 // Geburtstag:
            if (!target.getBirthday().isEmpty()) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Geburtstag: °b°°K°°+0135°_°[0,53,217]°").append(target.getBirthday()).append("°K° (°[0,53,217]°").append(Zodiac.getZodiacSign(target.getBirthday())).append("°K°)#°+0000+9503°°>LEFT<°§");
            }
            
                // Elemente 
             
             if(target.getElementrechner()==1) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Element: °b°°K°°+0135°_°[0,53,217]°°K°Feuer °>features/elements/whois-fire...my_2.h_0.png<°").append("°K°°[0,53,217]°").append("°K°#°+0000+9503°°>LEFT<°§");
            }
            
             if(target.getElementrechner()==2) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Element: °b°°K°°+0135°_°[0,53,217]°°K°Luft °>features/elements/whois-air...my_2.h_0.png<°").append("°K°°[0,53,217]°").append("°K°#°+0000+9503°°>LEFT<°§");
            }
             
             if(target.getElementrechner()==3) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Element: °b°°K°°+0135°_°[0,53,217]°°K°Wasser °>features/elements/whois-water...my_2.h_0.png<°").append("°K°°[0,53,217]°").append("°K°#°+0000+9503°°>LEFT<°§");
            }
              
             if(target.getElementrechner()==4) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Element: °b°°K°°+0135°_°[0,53,217]°°K°Erde °>features/elements/whois-earth...my_2.h_0.png<°").append("°K°°[0,53,217]°").append("°K°#°+0000+9503°°>LEFT<°§");
            }
           
            
                 // E-Mail-Adresse
            if(!target.getEmail().isEmpty() && target.getShowEmail() == 1) {
                center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_E-Mail: °b°°K°°+0135°_°[0,53,217]°°>_ ").append(target.getEmail()).append("|/email ").append(target.getName()).append("<°_°K°°b°°K°#°+0000+9503°°>LEFT<°§");
                
            }
            if(!client.getEmail().isEmpty() && target == client && client.getShowEmail() == 0) {
              center.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_E-Mail: °b°°K°°+0135°_°[137,138,142]°°>_ ").append(target.getEmail()).append("|/email ").append(target.getName()).append("<°_°K°°b°°K°#°+0000+9503°°>LEFT<°§");
                    
            }
            
            // PERSON TAB ENDE //
            
            
            
             // HEAVEN 24 TAB START //
            
            } else if (tab.equals("Heaven24")) {
                  // Überschrift Personen-Tab
              center.append("##°+0005°°[137,138,142]°°14°_Allgemein_#");
                
            // Registrations-Datum
           
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Registriert: °b°°K°°+0135°_°[0,53,217]°_°K12°").append(target.getRegistrationDate()).append(" um ").append(target.getRegistrationTime2()).append(" Uhr").append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Erfahrungslevel: °b°°K°°+0135°_°[0,53,217]°_°K12°°>levels/").append(target.getLevel()).append("...h_10.w_17.my_1.png<°");
              if(!target.getStammiwhen().isEmpty()) {
              byte months = (byte) (CommandParser.countChars(target.getStammiwhen(), ',')+1);
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Stammi-Monate: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(months).append("_ §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
              
              if(!target.getCmwhen().isEmpty()) {
              byte months = (byte) (CommandParser.countChars(target.getCmwhen(), ',')+1);
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_CM-Monate: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(months).append("_ §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
               
              if (!target.getLC().isEmpty()) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Lieblings-Channel: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_°>_h").append(target.getLC()).append("|/go +\"|/info \"<°_ §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
              // Trennlinie
              center.append("#°+0005°°+9515°#°>cc/bar_greyStrong...clipw_413.w_0.mx_0.my_0.png<°°+9505°#°+9505°");
              
              center.append("##°+0005°°[137,138,142]°°14°_Community_#");     
              if (target.getKisses() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Knutschflecken: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(target.getKisses()).append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
              if (target.getRoses() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Rosen: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_°>_h"+target.getRoses()+"|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_stem_01...h_20.w_98.my_5.png<>--<>|/rose colors:").append(charNick).append("<>--<>features/colorfulroses/rose_head_01...h_20.w.0.mx_-98.my_5.png<>--<>|/rose colors:").append(charNick).append("<r°_°12°°b°#°+0000+9503°");
              }        
              if(target.getCodeE() > 0) {
                    center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Geschenke: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_");
                   
              if(target == client) {
                            center.append("°BB>_h").append(target.getCodeE()).append(" |/code old<>--<>present...my_2.h_10.gif|present_ani...my_2.gif<>|/shop<>--<>|/code old<°§°°°12°°b°°K° erhalten §°°°12°°b°°K°°+0000+9503°°>LEFT<°§");
                    } else {
                            center.append("°BB°").append(target.getCodeE()).append(" °>present...my_2.h_10.gif|present_ani...my_2.gif<>|/shop<>--<°§°°°12°°b°°K° erhalten §°°°12°°b°°K°°+0000+9503°°>LEFT<°§");
                    }    
              if(target.getCodeV() > 0) {
                            center.append(",°BB12° _").append(target.getCodeV()).append(" °>present...my_2.h_10.gif|present_ani...my_2.gif<>|/shop<>--<r°§°°°12°°b°°K° verschenkt §°°°12°°b°°K°°+0000+9503°°>LEFT<°§");
                    }      
                    } else {
              if(target.getCodeV() > 0) {
                            center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Geschenke: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(target.getCodeV()).append(" °>present...my_2.h_10.gif|present_ani...my_2.gif<>|/shop<>--<r°§°°°12°°b°°K° verschenkt §°°°12°°b°°K°°+0000+9503°°>LEFT<°§");
                    }
              }
              if(target.getForumpostcounter() > 5) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Beiträge im Forum: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(target.getForumpostcounter()).append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }      
              if (target.getMentorPoints() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Mentorpunkte: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(target.getMentorPoints()).append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
               if (target.getFans() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Fotofans: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(target.getFans()).append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
              if (target.getHeroCounter() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Held des Tages: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(nf.format((target.getHeroCounter()))).append(" °>features/hero-of-the-day/ft_hotd_wappen_color...h_10.w_28.my_1.png<°").append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
              if (target.getLuckyCounter() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Glückwünsche: °b°°K°°+0135°_°[0,53,217]°_°K12°°[0,53,217]°_").append(nf.format((target.getLuckyCounter()))).append(" °>features/goodluck/piggyicon...h_10.w_28.png<°").append(" §°°°12°°b°°K°#°+0000+9503°°>LEFT<°§");
              }
              
              if(target.receivedHearts.size() > 0) {
              center.append("#°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°[137,138,142]°°12°°+0023°°[137,138,142]°_Herzen erhalten:°b°°K°_°[0,53,217]°_°K12°°[0,53,217]°");
              
              
               String getheart = target.getHeart();
                       
                int herzzahl = 1;
                String herz = "°>fullheart.png<°";
                StringBuilder herzen = new StringBuilder();
                StringBuilder herznicks = new StringBuilder();
                     
                for(String name : target.receivedHearts) {
                     
                    herznicks.append(" °>fullheart.png<° _°BB12>_h").append(name.replace("<", "\\<")).append("|/serverpp \"|/w \"<°_,");
                    herzzahl++;
                }             
            String hN = herznicks.toString();
              
                if(hN.contains(getheart.replace("<", "\\<"))) {
                                center.append(hN.replace(String.format("°>_h%s|/serverpp \"|/w \"<°, ", getheart.replace("<", "\\<")), "").replace(String.format(", °>_h%s|/serverpp \"|/w \"<°", getheart.replace("<", "\\<")), ""));
                        
                }
            }
              center.append("§°°°12°°b°°K°°+0000+9503°°>LEFT<°§");
              center.append("#°+0000+9503°°>LEFT<°§");
              
              
              
              
              
              
              // Trennlinie
              center.append("#°+0005°°+9515°#°>cc/bar_greyStrong...clipw_413.w_0.mx_0.my_0.png<°°+9505°#°+9505°");
              // Highlights:
              
              
              center.append("##°+0005°°[137,138,142]°°14°_Heaven24-Career_#");   
              int showbox = 0;
              int caraus = 1;
              StringBuilder box = new StringBuilder();
           
              int s = target.getHighlights().split("\\|").length/2;
              int ab = s-10+1;
           
              if (s < 10) {
            //  box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°K°Registriert°b°°K° °[137,138,142]°").append(target == Server.get().getButler() ? "21.05.1935" : target.getRegistrationDate()).append("°K° #°+0000+9502°");
              }
              showbox = 1;
              if(!target.getHighlights().isEmpty()) {
                
            	for(String out : target.getHighlights().split("\\|")) {
            		if(!out.isEmpty()) {
            			String datum = out.split(" ")[0];
            			String text = out.split(" ", 2)[1];
                                if (caraus >= ab) {
                                      String old = box.toString();
                          
          String icon = "";
          if(text.contains("Knutschfleck")) {
                 icon = "°>w2/icons/sm_kissingcouple_blue...my_-3.h_0.w_0.gif<°";
          }
          else if(text.contains("Rose")) {
                 icon = "°>w2/icons/sm_rosecavalier_blue...my_-3.h_0.w_0.gif<°";
          }  
                                      
                                      
                                      box.setLength(0);
    			   box.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°K°"+text+"°b°°K° °[137,138,142]°"+datum+" "+icon+"°K° #°+0000+9502°"+old);
                                }
                             
                                caraus++;
                        }}}
              
              if (showbox == 1) {
                  center.append("°>LEFT<°#°+9510°°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_#°+9505°°+0007°Highlights#°+9505+0000+5005°°b°");          
                  center.append(box.toString());
                  center.append("°+9513°°[0,53,217]°°12°°[0,53,217]°°+9017°°>RIGHT<°");
                  if (caraus > 10) {
                  center.append("_°>_hmehr...|/showhighlights "+target.getName()+"<°");
                  }
                  center.append("°b°#°K°§°bir°°12°°>LEFT<°#°+9503°°b°°[174,174,255,127]°#°+9502°");
               center.append("°>{colorboxend}<°°bir°°12°§°bir°°12°°>LEFT<°#°+9505°");
              }
            
              
              
              
              
              
              
              // Highlights ENDE
              
              // Career:
              //center.append("##°+0005°°[137,138,142]°°14°_Heaven24-Career_#");   
              int showbox2 = 0;
              int caraus2 = 1;
              StringBuilder box2 = new StringBuilder();
           
              int s2 = target.getCareer2().split("\\|").length/2;
              int ab2 = s2-10+1;
           
              if (s2 < 10) {
              box2.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°K°Registriert°b°°K° °[137,138,142]°").append(target == Server.get().getButler() ? "21.05.1935" : target.getRegistrationDate()).append("°K° #°+0000+9502°");
              }
              showbox2 = 1;
              if(!target.getCareer2().isEmpty()) {
                
            	for(String out : target.getCareer2().split("\\|")) {
            		if(!out.isEmpty()) {
            			String datum = out.split(" ")[0];
            			String text = out.split(" ", 2)[1];
                                if (caraus2 >= ab2) {
                                      String old = box2.toString();
                          box2.setLength(0);
    			   box2.append("°+0005°°>cc/bullet_blue_outlined.png<°°+0023°°K°"+text+"°b°°K° °[137,138,142]°"+datum+"°K° #°+0000+9502°"+old);
                                }
                             
                                caraus2++;
                        }}}
              
              if (showbox2 == 1) {
                  center.append("°>LEFT<°#°+9510°°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°#°+9502°_#°+9505°°+0007°Career#°+9505+0000+5005°°b°");          
                  center.append(box2.toString());
                  center.append("°+9513°°[0,53,217]°°12°°[0,53,217]°°+9017°°>RIGHT<°");
                  if (caraus2 > 10) {
                  center.append("_°>_hmehr...|/showcareer2 "+target.getName()+"<°");
                  }
                  center.append("°b°#°K°§°bir°°12°°>LEFT<°#°+9503°°b°°[174,174,255,127]°#°+9502°");
               center.append("°>{colorboxend}<°°bir°°12°§°bir°°12°°>LEFT<°#°+9505°");
               }
            
              
              
              
              
              
              
              
            // HEAVEN 24 TAB ENDE //
            
            
            
            
            
              // ADMIN TAB START //
            
            } else if (tab.equals("Admin")) {
                // Überschrift Admin-Tab
               if(client.hasPermission("cmd.admininfo")) { 
                center.append("°12°°+9504°°+0000>LEFT<°##°>layout/arrow-mini_black.b...w_8.gif<°°K°_Übersicht_ _|_ °[0,53,217]°°>IP-Liste|/iplist ").append(target.getName()).append("<° _|_ °[0,53,217]°°>Notiz|/notice ").append(target.getName()).append("<°°[137,138,142]°°14°");
                center.append("#°+9525°_Allgemeine Informationen°b°°bir°°12°#°");
                center.append("+9515°°[174,174,255,127]°°>{colorboxstart}<°°bir°");
                // Last Host
                center.append("°12°#°+9502°_#°+9505°°b°°+0000°°+0023°°K°_Last Host: °b°°+7102°°K°°+0220°").append(target.getIPAddress().replace(".", "-")).append("°K°#°+0000+9503°°>LEFT<°");
                 // Login Land
             if(!target.getHost().isEmpty()) {
                center.append("°+0000°°+0023°°K°_Login-Land: °b°°+7102°°K°°+0220°(").append(ProfileTools.getCountry(target.getHost())).append(")°K°#°+0000+9503°°>LEFT<°");
                        }
                // Register-IP-Adressse
                 //if(client.getRank() > 6 || client.getName().equals("Sternrainy") || client.hasPermission("cmd.admininfo.full")) {
                	if(target.getRegisterIP() != null) { 
                center.append("°+0000°°+0023°°K°_Register-IP: °b°°+7102°°K°°+0220°°>_h").append(target.getRegisterIP().replace(".", "-")).append("|/checkip \"<°°K°#°+0000+9503°°>LEFT<°");
                        }
                
               // User Reg-No:
                center.append("#°+0000°°+0023°°K°_User Reg-No: °b°°+7102°°K°°+0220°").append(nf.format(target.getID())).append("°K°#°+0000+9503°°>LEFT<°");
               // Status:
                center.append("°+0000°°+0023°°K°_Status: °b°°+7102°°K°°+0220°").append(target.getRank()).append(" (").append(target.getRankLabel(target.getRank())).append(")°K°#°+0000+9503°°>LEFT<°");
               
                 StringBuilder nn = new StringBuilder();
                int xl = 1;

                for (Client ob : Server.get().getClients()) {
               	 	if(ob != target && ob.getIPAddress().equals(target.getIPAddress())) {
               	 		nn.append(xl!=1?", ":"").append("°>_h").append(ob.getName().replace("<", "\\<")).append("|/serverpp \"|/w \"<°");
               	 		xl++;
                        }
                }
                         
               // Nicks mit gleicher IP  
                if(!nn.toString().isEmpty()) {
                center.append("°+0000°°+0023°°K°_Nicks mit gleicher IP: °b°°+7102°°K°°+0220°").append(nn.toString()).append("°K°#°+0000+9503°°>LEFT<°");
                }  
               // Email-Adresse:
                center.append("°+0000°°+0023°°K°_Aktuelle Emailadresse: °b°°+7102°°K°°+0220°°>_h").append(target.getEmail().replace(".", "-")).append("|/checkmail \"<%00°").append("°K°#°+0000+9503°°>LEFT<°");
               // COLORBOX TEIL
                center.append("#");
                center.append("°b°°[174,174,255,127]°#°+9502°°>{colorboxend}<°°bir°°12°");
               // COLORBOX ENDE
                
               // Statistiken
                center.append("#°+9523°°+0000°°[137,138,142]°°14°_Statistiken°b°°bir°°12°");
               // COLORBOX START
                center.append("#°+9515°°[174,174,255,127]°°>{colorboxstart}<°°bir°°12°");
               // FORMATIERUNGEN
                center.append("#°+9502°_#°+9505°°b°");
                // Mutes
                if(target.getMutes() > 0) {
                center.append("°+0000°°+0023°°K°_Mutes: °b°°+7102°°K°°+0220°").append(target.getMutes()).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Colormutes
                if(target.getCmutes() > 0) {
                    center.append("#Colormutes:°%43°").append(target.getCmutes()).append("°%00°");
                 }     
                // CLs
                if(target.getCls() > 0) {
                center.append("°+0000°°+0023°°K°_Channellocks: °b°°+7102°°K°°+0220°").append(target.getMutes()).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Flames
                if(target.getFlames() > 0) {
                center.append("°+0000°°+0023°°K°_Flames: °b°°+7102°°K°°+0220°").append(target.getMutes()).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Kicks
                if(target.getKicks() > 0) {
                center.append("°+0000°°+0023°°K°_Kicks: °b°°+7102°°K°°+0220°").append(target.getMutes()).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Locks
                if(target.getLocks() > 0) {
                center.append("°+0000°°+0023°°K°_Locks: °b°°+7102°°K°°+0220°").append(target.getMutes()).append("°K°#°+0000+9503°°>LEFT<°");
                 }  
                // Notrufe (# / ok / Missbr.)
                center.append("°+0000°°+0023°°K°_Notrufe (\\# / ok / Missbr.): °b°°+7102°°K°°+0220°0 / 0 / 0°K°#°+0000+9503°°>LEFT<°");
                // Agent-Informationen
                if(target.getAgent() != null) {
                center.append("°+0000°°+0023°°K°_Betriebssystem: °b°°+7102°°K°°+0220°").append(ProfileTools.getOS(target.getAgent())).append("°K°#°+0000+9503°°>LEFT<°");
                center.append("°+0000°°+0023°°K°_Browser: °b°°+7102°°K°°+0220°").append(ProfileTools.getBrowser(target.getAgent())).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Provider-ID
                if(!target.getHost().isEmpty()) {
                center.append("°+0000°°+0023°°K°_Provider: °b°°+7102°°K°°+0220°").append(ProfileTools.getHost(target.getHost())).append("°K°#°+0000+9503°°>LEFT<°");
                }
                // Geschriebene Zeichen
                center.append("°+0000°°+0023°°K°_Zeichenzahl: °b°°+7102°°K°°+0220°").append(nf.format(target.getZeichen())).append("°K°#°+0000+9503°°>LEFT<°");
                // Profil Style
                center.append("°+0000°°+0023°°K°_Whois Style: °b°°+7102°°K°°+0220°").append(target.getWStyle() == 2 ? "???":target.getWStyle()==1?"W2 BETA":"Classic").append("°K°#°+0000+9503°°>LEFT<°");           
                // Profil Visit 
                center.append("°+0000°°+0023°°K°_Whois Sichtbar?: °b°°+7102°°K°°+0220°").append(target.getVisit() == 2 ? "???":target.getVisit()==1?"false":"true").append("°K°#°+0000+9503°°>LEFT<°");
                // Logins
                if(target.getUserLogin() > 0) {
                center.append("°+0000°°+0023°°K°_Logins: °b°°+7102°°K°°+0220°").append(nf.format(target.getUserLogin())).append("°K°#°+0000+9503°°>LEFT<°");
                 }    
                // Javaversion
                 if(target.getAppletVersion() != null) {
                center.append("°+0000°°+0023°°K°_Appletversion: °b°°+7102°°K°°+0220°").append(target.getAppletVersion()).append("°K°#°+0000+9503°°>LEFT<°");
                center.append("°+0000°°+0023°°K°_Javaversion: °b°°+7102°°K°°+0220°").append(KCodeParser.escape(target.getJavaVersion())).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Email Verifiziert? 
                center.append("°+0000°°+0023°°K°_E-Mail-Verify: °b°°+7102°°K°°+0220°").append(target.getEmailVerify() == 2 ? "Verifiziert":target.getEmailVerify()==1?"Verifizierung läuft...":"Nicht verifiziert").append("°K°#°+0000+9503°°>LEFT<°");    
                // Online seit?
                if(online) {
                center.append("°+0000°°+0023°°K°_Online seit: °b°°+7102°°K°°+0220°").append(target.getLoginTime()).append("°K°#°+0000+9503°°>LEFT<°");
                 }
                // Online in:
                if(target != Server.get().getButler()) {
                if(online && !target.getChannel().isVisible()) {
                center.append("°+0000°°+0023°°K°_Online in: °b°°+7102°°K°°+0220°°>_h").append(target.getChannel().getName()).append("|/go \"|/go +\"<°°K°#°+0000+9503°°>LEFT<°");
                 }
                 }
                // Mentor
                if(!target.getMentor().isEmpty()) {
               	center.append("°+0000°°+0023°°K°_Mentor: °b°°+7102°°K°°+0220°").append("°>_h").append(target.getMentor().replace("<", "\\<")).append("|/serverpp \"|/w \"<°°K°#°+0000+9503°°>LEFT<°");
                 }
                if(target != Server.get().getButler()) {
                if(online) {
                 // Login-Seite
                center.append("°+0000°°+0023°°K°_LastLoginURL: °b°°+7102°°K°°+040°#°+0023°°>_h").append(target.getLoginUrl()).append("|").append(target.getLoginUrl()).append("<°°K°#°+0000+9503°°>LEFT<°");
                }
                }
                // COLORBOX ENDE
                center.append("#°b°°[174,174,255,127]°#°+9502°°>{colorboxend}<°");
                // FORMATIERUNGEN
                center.append("°bir°°12°#°+9523°");
                if(!target.getComments().isEmpty()) {
                // Überschrift: Admin-Kommentare
                center.append("#°+9523°°+0000°°[137,138,142]°°14°_Admin-Kommentare°b°°bir°°12°");
                // Sortiert nach: IPliste / Notizen
                center.append("#°+9515°°12°°+0010°°+0000°°>LEFT<°_Sortiert nach:°b° Kein Filter°>RIGHT<°°>w2/button_sort...clickchange.hovervalign_center.h_20.png|w2/button_sort_hover...clickchange.hovervalign_center.h_20.png<>--<>|/openpulldown id_3211.w_145.h_242.location_2<°°>{definetext|3211}<°°12°°W°°>{linkhovercolor}<°°[0,53,217]°°>{imageboxbackground}pullbox_border.ending_0<°°+0002°°>{imageboxstart}pullbox_t.ending_511.hover.mh_1.click__p/admc setFilter :html14<°#°+9502°_Kein Filter°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter Admin:html14<°#°+9501°_Admin°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter AntiExtreme:html14<°#°+9501°_AET°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter Photo:html14<°#°+9501°_Foto°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter HZE:html14<°#°+9501°_HZA/E°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter Homepage:html14<°#°+9501°_HP°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter YouthProtection:html14<°#°+9501°_JuSchu°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter MyChannel:html14<°#°+9501°_MyChannel°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter Password:html14<°#°+9501°_Passwort°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter Games:html14<°#°+9501°_Spiele°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter Verify:html14<°#°+9501°_Verify°b°#°>{imageboxend}<°°>{imageboxstart}pullbox_m.ending_511.hover.mh_1.click__p/admc setFilter SecurityLocks:html14<°#°+9501°_SL°b°#°>{imageboxend}<°#°+9015°°>{definetext|3211}<°°>{linkhovercolorreset}<°°+0000°°+9510°§°bir°°12°°>LEFT<°");
                center.append("#°+0008°°+0025°°[0,53,217]°");
                center.append(target.getComments());
                //center.append("_°>_hJames|/ww2 +*\"<°_°r°°12° (23.08.14 00:00) [Admin] [Admin] [Auto]#°+0030°Automatisch \"_entsperrt!_\"§°bir°°12°°>LEFT<°#°+0008°°>cc/bullet_blue_outlined.png<°°+0025°°[0,53,217]°_°>_hSpectra|/ww2 +*\"<°_°r°°12° (17.08.14 19:24) [VA/Sys] [Admin] [Auto]#°+0030°_(°RR°5 Tage°°)_ \"_gesperrt!_\" (Bis 22.08.2014)  nutzte Monatelang die Möglichkeit aus, dass ein Admin ihm über TeamViewer Zugang zu Admininfos usw gab§°bir°°12°°>LEFT<°#°+0008°°>cc/bullet_blue_outlined.png<°°+0025°°[0,53,217]°_°>_hNICKNAME|/ww2 +*\"<°_°r°°12° (23.05.14 22:57) [Admin] [Admin] [Auto]#°+0030°\"_Entsperrt!_\" Versehentlich gesperrt, mein Fehler§°bir°°12°°>LEFT<°#°+0008°°>cc/bullet_blue_outlined.png<°°+0025°°[0,53,217]°_°>_hNICKNAME|/ww2 +*\"<°_°r°°12° (23.05.14 22:47) [Admin] [Verify] [Auto]#°+0030°_°RR°Permanent°°_ \"_gesperrt!_\" Sicherheitslock. Verdacht auf falsche Angaben im Profil§°bir°°12°°>LEFT<°");
                center.append("#°+9510°_°>{button}Kommentar schreiben||textborder|1|call|/admc add html14|width|150|heigth|25|<°°b°°+9523°§°bir°°12°°>LEFT<°##");
                }
                 
                // ADMIN TAB ENDE //
            }
                 
        
            } else {
              center.append("##                                °R18° _Dieses Tab ist gesperrt._");
            }
            
          
            PopupNewStyle popup = new PopupNewStyle(title, title, left.toString()+center.toString()+right.toString(), 755, 475); 
            client.send(popup.toString());
        
           
    }
             }
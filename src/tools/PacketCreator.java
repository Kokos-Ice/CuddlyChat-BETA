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



package tools;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import starlight.*;

import tools.popup.Button;
import tools.popup.Choice;
import tools.popup.Label;
import tools.popup.Panel;
import tools.popup.Popup;
import tools.popup.PopupNewStyle;
import tools.popup.TextArea;
import tools.popup.TextField;

public class PacketCreator {
	
    public static String game(String name) {
	return "k\0Spiel konfigurierenõsgameõDiceõf\0\0\0hÿÿÿãEl  õcgPhÿÿÿãWl  õcgPhÿÿÿãSl õcgPhÿÿÿãpBNpBNl           õgKf\0\0\0hÿÿÿããCpBNpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCpBNpBNpBClSpiel konfigurierenõbgSf\0\0\0hÞÞÿããCohÞÞÿAFreidiffen\0card:switchcard:Freidiffen\0Darten\0card:switchcard:Darten\0õãFreidiffenõDartenõããCpBCp~cardõCp~FreidiffenõBSlõgBf\0\0\0hÞÞÿãClõgBf\0\0\0hÞÞÿãNpBNlõgKf\0\0\0hÞÞÿãCpBNpBãWpGABCCxsflPrivates Würfelnõf\0\0\0hÞÞÿgMãlKnuddelsõgMf\0\0\0hÞÞÿsKnuddelsõãlRosenõgMf\0\0\0hÞÞÿsRosenõãlGegenspielerõgMf\0\0\0hÞÞÿsGegenspielerõãlAnz. WürfelõgMf\0\0\0hÞÞÿsAnz. WürfelõããCpGABCClõgMf\0\0\0hÞÞÿãf0õBhÿÿÿãf0õBhÿÿÿãfõBhÿÿÿãf1õBhÿÿÿãããããp~DiffenõBSlõgBf\0\0\0hÞÞÿãClõgBf\0\0\0hÞÞÿãNpBNl    õgKf\0\0\0hÞÞÿãCpBNpBãWpGABCCxsflPrivates Würfelnõf\0\0\0hÞÞÿgMãxsflOffene Würfeõf\0\0\0hÞÞÿgMãxsflNur hochzählenõf\0\0\0hÞÞÿgMãlKnuddelsõgMf\0\0\0hÞÞÿsKnuddelsõãlRosenõgMf\0\0\0hÞÞÿsRosenõãlGegenspielerõgMf\0\0\0hÞÞÿsGegenspielerõãlSiegpunkteõgMf\0\0\0hÞÞÿsSiegpunkteõãlMin.-WürfelõgMf\0\0\0hÞÞÿsMin.-WürfelõãlMax.-WürfelõgMf\0\0\0hÞÞÿsMax.-WürfelõãlWürfel/RundeõgMf\0\0\0hÞÞÿsWürfel/RundeõããCpGABCClõgMf\0\0\0hÞÞÿãlõgMf\0\0\0hÞÞÿãlõgMf\0\0\0hÞÞÿãf0õBhÿÿÿãf0õBhÿÿÿãfõBhÿÿÿãf10õBhÿÿÿãf3õBhÿÿÿãf10õBhÿÿÿãf1õBhÿÿÿãããããp~DartenõBSlõgBf\0\0\0hÞÞÿãClõgBf\0\0\0hÞÞÿãNpBNl    õgKf\0\0\0hÞÞÿãCpBNpBãWpGABCCxsflPrivates Würfelnõf\0\0\0hÞÞÿgMãxsflOffene Würfeõf\0\0\0hÞÞÿgMãxstlNachwurf aktivõf\0\0\0hÞÞÿgMãlKnuddelsõgMf\0\0\0hÞÞÿsKnuddelsõãlRosenõgMf\0\0\0hÞÞÿsRosenõãlGegenspielerõgMf\0\0\0hÞÞÿsGegenspielerõãlStart-ZahlõgMf\0\0\0hÞÞÿsStart-ZahlõããCpGABCClõgMf\0\0\0hÞÞÿãlõgMf\0\0\0hÞÞÿãlõgMf\0\0\0hÞÞÿãf0õBhÿÿÿãf0õBhÿÿÿãfõBhÿÿÿãf100õBhÿÿÿãããããp~DicenõBSlõgBf\0\0\0hÞÞÿãClõgBf\0\0\0hÞÞÿãNpBNl    õgKf\0\0\0hÞÞÿãCpBNpBãWpGABCCxsflPrivates Würfelnõf\0\0\0hÞÞÿgMãxsflOffene Würfeõf\0\0\0hÞÞÿgMãlKnuddelsõgMf\0\0\0hÞÞÿsKnuddelsõãlRosenõgMf\0\0\0hÞÞÿsRosenõãlGegenspielerõgMf\0\0\0hÞÞÿsGegenspielerõãlSiegrundenõgMf\0\0\0hÞÞÿsSiegrundenõãlSiegvorsprungõgMf\0\0\0hÞÞÿsSiegvorsprungõããCpGABCClõgMf\0\0\0hÞÞÿãlõgMf\0\0\0hÞÞÿãf0õBhÿÿÿãf0õBhÿÿÿãfõBhÿÿÿãf3õBhÿÿÿãf1õBhÿÿÿãããããããSpBCpBWbSpiel startenõcesdbgPf\0\0\0hÞÞÿãCl        õgPf\0\0\0hÞÞÿãEbAbbrechenõcedbgPf\0\0\0hÞÞÿãããããããã";
        
        }
    
    public static String createMessage3Window(String an, String betreff, String text) {
return "k\0Nachricht schreiben an "+an+"õspostõpostõw?@f\0\0\0hÿÿÿcoTextareaõãCp~õbÿÿÿBNp~õbÿÿÿU-õU\0\0\nBãSp~õbÿÿÿU-õU\0\0\nBãCp~õBSp~õGBBBBp~õFp~õGABBBãããCpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBNp~õBSlõgKf\0\0\0hÞÞÿãClNachricht schreibenõbgUf\0\0\0hÞÞÿããCp~õBNpBNpBWpUpics/layout/bluebox-nontrans_tl.pngõU\0\0BãCpUpics/layout/bluebox-nontrans_tc.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_tr.pngõU\0\0BããWpUpics/layout/bluebox-nontrans_cl.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_cr.pngõU\0\0BãSpBWpUpics/layout/bluebox-nontrans_bl.pngõU\0\0BãCpUpics/layout/bluebox-nontrans_bc.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_br.pngõU\0\0BããCp~õGABBBp~õbÊÊÿBNp~õbÊÊÿU-õU\0\0BãSp~õbÊÊÿU-õU\0\0BãCp~õbÊÊÿBWp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBlõbgDf\0\0\0hÊÊÿãããWp~õU-õU\0A\0\0GABBBlõbgDf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBf"+an+"õBhïïÿãããWp~õU-õU\0A\0\0GABBBlAn:õbgMf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBp~õbÊÊÿBCp~õbÊÊÿBNp~õbÊÊÿU-õU\0\0BãSp~õbÊÊÿU-õU\0\t\0\tBãCf"+betreff+"õBhïïÿããWp~iconcardlayoutõCc°>posts/msg_ico_noicon...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-NoIconõs\0#\0(tsendbackãformValueNoIconõf\0\0\0hÊÊÿoãc°>posts/msg_ico_xmark...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Exclamationõs\0#\0\ntsendbackãformValueExclamationõf\0\0\0hÊÊÿoãc°>posts/msg_ico_qmark...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Questionõs\0#\0\ntsendbackãformValueQuestionõf\0\0\0hÊÊÿoãc°>posts/msg_ico_warning...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Warnõs\0#\0\ntsendbackãformValueWarnõf\0\0\0hÊÊÿoãc°>posts/msg_ico_clover...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Cloverõs\0#\0\ntsendbackãformValueCloverõf\0\0\0hÊÊÿoãc°>posts/msg_ico_bulb...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Bulbõs\0#\0\ntsendbackãformValueBulbõf\0\0\0hÊÊÿoãc°>posts/msg_ico_thumbdown...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ThumpDownõs\0#\0\ntsendbackãformValueThumpDownõf\0\0\0hÊÊÿoãc°>posts/msg_ico_thumbup...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ThumpUpõs\0#\0\ntsendbackãformValueThumpUpõf\0\0\0hÊÊÿoãc°>posts/msg_ico_cm...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-CMMsgõs\0#\0\ntsendbackãformValueCMMsgõf\0\0\0hÊÊÿoãc°>posts/msg_ico_butler...my_-3.mx_-9.w_25.gif<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Jamesõs\0#\0\ntsendbackãformValueJamesõf\0\0\0hÊÊÿoãc°>posts/msg_ico_heart...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Heartõs\0#\0\ntsendbackãformValueHeartõf\0\0\0hÊÊÿoãc°>posts/msg_ico_manocornuto...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ManoCornutoõs\0#\0\ntsendbackãformValueManoCornutoõf\0\0\0hÊÊÿoãc°>posts/msg_ico_victory...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Victoryõs\0#\0\ntsendbackãformValueVictoryõf\0\0\0hÊÊÿoãc°>posts/msg_ico_sw...h_8.my_-1.mx_-6.w_30.h_40.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-SmileyWarsõs\0#\0\ntsendbackãformValueSmileyWarsõf\0\0\0hÊÊÿoãc°>posts/msg_ico_knuddelkey...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Internetdayõs\0#\0\ntsendbackãformValueInternetdayõf\0\0\0hÊÊÿoãc°>posts/msg_ico_xmas...h_8.my_-1.mx_-6.w_25.h_40.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-XMASTreeõs\0#\0\ntsendbackãformValueXMASTreeõf\0\0\0hÊÊÿoãc°>posts/msg_ico_sun...w_25.my_1.mx_-6.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-CalendarSummerõs\0#\0\ntsendbackãformValueCalendarSummerõf\0\0\0hÊÊÿoãc°>worldtour/m-icon_01...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour1õs\0#\0\ntsendbackãformValueWorldTour1õf\0\0\0hÊÊÿoãc°>worldtour/m-icon_02...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour2õs\0#\0\ntsendbackãformValueWorldTour2õf\0\0\0hÊÊÿoãc°>worldtour/m-icon_03...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour3õs\0#\0\ntsendbackãformValueWorldTour3õf\0\0\0hÊÊÿoãc°>posts/msg_ico_private2post...w_22.h_8.my_2.mx_0.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-PrivateToPostõs\0#\0\ntsendbackãformValuePrivateToPostõf\0\0\0hÊÊÿoãc°>posts/msg_sold...w_30.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Soldõs\0#\0\ntsendbackãformValueSoldõf\0\0\0hÊÊÿoãc°>posts/msg_bought...w_35.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Boughtõs\0#\0\ntsendbackãformValueBoughtõf\0\0\0hÊÊÿoãc°>posts/msg_rank...w_41.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Rankõs\0#\0\ntsendbackãformValuePrivateToPostõf\0\0\0hÊÊÿoãããããWp~õU-õU\0A\0\0GABBBlBetreff:õbgMf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBp~cardlayoutõCc°12+9503°_°B>_nZeitpunkt der Zustellung festlegen|/togglecheckbox checkbox10<>--<>posts/scheduled...h_0.png<>--<>_n|/togglecheckbox checkbox10<rb°#°+9020°õ~cardlabelõs\0\n\0\nf\0\0\0hÊÊÿoãp~cardtextõBp~õbÊÊÿBWp~õBCp~õBCp~õBCp~õU-õU\0Ï\0\0GABBBl(Bsp: 30.05.2012 10:15:00 oder 5m)õbrgMf\0\0\0hÊÊÿãããWp~õU-õU\0Ò\0\0GABBBfõUhïïÿãããããããããWp~õU-õU\0A\0\0GABBBx~checkbox10õsfS1\0cardlayout:switchcard:cardtext\00\0cardlayout:switchcard:cardlabelõãããCp~õBCp~õBCp~õBCp~õU-õU?\0\0GABBBlõbgDf\0\0\0hÊÊÿãããWp~õU-õU\0A\0\0GABBBlõbgDf\0\0\0hÊÊÿãããããããããEp~õbÊÊÿU-õU\0\0BãWp~õbÊÊÿU-õU\0\0BããããCp~õBNp~õbÞÞÿU-õU\0\0BãCp~õBCp~textcardlayoutõCp~showtextareaõBSp~õBSp~õBNp~õbÞÞüBCc_°[4,36,243]12>_hSignatur anfügen|/m sig<°_õf\0\0\0hÞÞüoãEp~õBCl                                                                           õ~CounterõbrgMf\0\0\0hÞÞüãEc°>{-scroll}<>right<>smileybox/sb_w2-button2...mx_1.gif|smileybox/sb_w2-button2_hover...mx_1.gif<>|/code ?<°õs\0*\0f\0\0\0hÞÞüozKããWxsthÞÞüããSp~õbÞÞüGBBBBp~õbÞÞüFp~õGADBBp~õbÞÞüBCbSendenõcesdbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0Bããp~õbÞÞüBCbVorschauõcesbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0Bããp~õbÞÞüBCbAbbrechenõcedbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0BãããããããCt\n\n\n"+text+"õVB~Textareaõsef\0\0\0hÿÿÿcCounterõÐããc°R>{linkhovercolor}<r°°12°_Icon auswählen:_#°+9014>RIGHT<°°10B>_hIconauswahl abbrechen|/executescriptlet 1textcardlayout:switchcard:showtextarea<r°##°>CENTER<°°>{table|w1|80|80|80|80|80|w1}<°°>{linkhovercolorreset}<°°>{tr}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-NoIcon<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_noicon...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Exclamation<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_xmark...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Question<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_qmark...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Warn<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_warning...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Clover<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_clover...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tr}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Bulb<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_bulb...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-ThumpDown<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_thumbdown...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-ThumpUp<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_thumbup...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-CalendarSummer<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_sun...w_25.my_1.mx_-6.png<°°>{imageboxend}<°°>{endtable}<°õ~iconchooseareaõsJîf\0\0\0hÞÞüãããããããããEp~õbÿÿÿU-õU\0\n\0BãWp~õbÿÿÿU-õU\0\n\0Bããã";

        }
    
    
    
	public static String createMessage2Window(String an, String betreff, String text) {
		
            
return "k\0Nachricht schreibenõspostõpostõw?@f\0\0\0hÿÿÿcoTextareaõãCp~õbÿÿÿBNp~õbÿÿÿU-õU\0\0\nBãSp~õbÿÿÿU-õU\0\0\nBãCp~õBSp~õGBBBBp~õFp~õGABBBãããCpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBNp~õBSlõgKf\0\0\0hÞÞÿãClNachricht schreibenõbgUf\0\0\0hÞÞÿããCp~õBNpBNpBWpUpics/layout/bluebox-nontrans_tl.pngõU\0\0BãCpUpics/layout/bluebox-nontrans_tc.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_tr.pngõU\0\0BããWpUpics/layout/bluebox-nontrans_cl.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_cr.pngõU\0\0BãSpBWpUpics/layout/bluebox-nontrans_bl.pngõU\0\0BãCpUpics/layout/bluebox-nontrans_bc.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_br.pngõU\0\0BããCp~õGABBBp~õbÊÊÿBNp~õbÊÊÿU-õU\0\0BãSp~õbÊÊÿU-õU\0\0BãCp~õbÊÊÿBWp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBl"+an+"õbgDf\0\0\0hÊÊÿãããWp~õU-õU\0A\0\0GABBBlõbgDf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBfõBhïïÿãããWp~õU-õU\0A\0\0GABBBlAn:õbgMf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBp~õbÊÊÿBCp~õbÊÊÿBNp~õbÊÊÿU-õU\0\0BãSp~õbÊÊÿU-õU\0\t\0\tBãCfõBhïïÿããWp~iconcardlayoutõCc°>posts/msg_ico_noicon...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-NoIconõs\0#\0(tsendbackãformValueNoIconõf\0\0\0hÊÊÿoãc°>posts/msg_ico_xmark...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Exclamationõs\0#\0\ntsendbackãformValueExclamationõf\0\0\0hÊÊÿoãc°>posts/msg_ico_qmark...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Questionõs\0#\0\ntsendbackãformValueQuestionõf\0\0\0hÊÊÿoãc°>posts/msg_ico_warning...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Warnõs\0#\0\ntsendbackãformValueWarnõf\0\0\0hÊÊÿoãc°>posts/msg_ico_clover...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Cloverõs\0#\0\ntsendbackãformValueCloverõf\0\0\0hÊÊÿoãc°>posts/msg_ico_bulb...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Bulbõs\0#\0\ntsendbackãformValueBulbõf\0\0\0hÊÊÿoãc°>posts/msg_ico_thumbdown...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ThumpDownõs\0#\0\ntsendbackãformValueThumpDownõf\0\0\0hÊÊÿoãc°>posts/msg_ico_thumbup...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ThumpUpõs\0#\0\ntsendbackãformValueThumpUpõf\0\0\0hÊÊÿoãc°>posts/msg_ico_cm...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-CMMsgõs\0#\0\ntsendbackãformValueCMMsgõf\0\0\0hÊÊÿoãc°>posts/msg_ico_butler...my_-3.mx_-9.w_25.gif<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Jamesõs\0#\0\ntsendbackãformValueJamesõf\0\0\0hÊÊÿoãc°>posts/msg_ico_heart...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Heartõs\0#\0\ntsendbackãformValueHeartõf\0\0\0hÊÊÿoãc°>posts/msg_ico_manocornuto...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ManoCornutoõs\0#\0\ntsendbackãformValueManoCornutoõf\0\0\0hÊÊÿoãc°>posts/msg_ico_victory...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Victoryõs\0#\0\ntsendbackãformValueVictoryõf\0\0\0hÊÊÿoãc°>posts/msg_ico_sw...h_8.my_-1.mx_-6.w_30.h_40.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-SmileyWarsõs\0#\0\ntsendbackãformValueSmileyWarsõf\0\0\0hÊÊÿoãc°>posts/msg_ico_knuddelkey...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Internetdayõs\0#\0\ntsendbackãformValueInternetdayõf\0\0\0hÊÊÿoãc°>posts/msg_ico_xmas...h_8.my_-1.mx_-6.w_25.h_40.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-XMASTreeõs\0#\0\ntsendbackãformValueXMASTreeõf\0\0\0hÊÊÿoãc°>posts/msg_ico_sun...w_25.my_1.mx_-6.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-CalendarSummerõs\0#\0\ntsendbackãformValueCalendarSummerõf\0\0\0hÊÊÿoãc°>worldtour/m-icon_01...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour1õs\0#\0\ntsendbackãformValueWorldTour1õf\0\0\0hÊÊÿoãc°>worldtour/m-icon_02...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour2õs\0#\0\ntsendbackãformValueWorldTour2õf\0\0\0hÊÊÿoãc°>worldtour/m-icon_03...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour3õs\0#\0\ntsendbackãformValueWorldTour3õf\0\0\0hÊÊÿoãc°>posts/msg_ico_private2post...w_22.h_8.my_2.mx_0.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-PrivateToPostõs\0#\0\ntsendbackãformValuePrivateToPostõf\0\0\0hÊÊÿoãc°>posts/msg_sold...w_30.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Soldõs\0#\0\ntsendbackãformValueSoldõf\0\0\0hÊÊÿoãc°>posts/msg_bought...w_35.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Boughtõs\0#\0\ntsendbackãformValueBoughtõf\0\0\0hÊÊÿoãc°>posts/msg_rank...w_41.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Rankõs\0#\0\ntsendbackãformValueRankõf\0\0\0hÊÊÿoãããããWp~õU-õU\0A\0\0GABBBlBetreff:õbgMf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBp~cardlayoutõCc°12+9503°_°B>_nZeitpunkt der Zustellung festlegen|/togglecheckbox checkbox10<>--<>posts/scheduled...h_0.png<>--<>_n|/togglecheckbox checkbox10<rb°#°+9020°õ~cardlabelõs\0\n\0\nf\0\0\0hÊÊÿoãp~cardtextõBCp~õbÊÊÿBWp~õBCp~õBCp~õBCp~õU-õU\0Ï\0\0GABBBl(Bsp: 30.05.2012 10:15:00 oder 5m)õbrgMf\0\0\0hÊÊÿãããWp~õU-õU\0Ò\0\0GABBBfõUhïïÿãããããããããWp~õU-õU\0A\0\0GABBBx~checkbox10õsfS1\0cardlayout:switchcard:cardtext\00\0cardlayout:switchcard:cardlabelõãããCp~õBCp~õBCp~õBCp~õU-õU?\0\0GABBBlõbgDf\0\0\0hÊÊÿãããWp~õU-õU\0A\0\0GABBBlõbgDf\0\0\0hÊÊÿãããããããããEp~õbÊÊÿU-õU\0\0BãWp~õbÊÊÿU-õU\0\0BããããCp~õBNp~õbÞÞÿU-õU\0\0BãCp~õBCp~textcardlayoutõCp~showtextareaõBSp~õBSp~õBNp~õbÞÞüBCc_°[4,36,243]12>_hSignatur anfügen|/m sig<°_õf\0\0\0hÞÞüoãEp~õBCl                                                                           õ~CounterõbrgMf\0\0\0hÞÞüãEc°>{-scroll}<>right<>smileybox/sb_w2-button2...mx_1.gif|smileybox/sb_w2-button2_hover...mx_1.gif<>|/code ?<°õs\0*\0f\0\0\0hÞÞüozKããWxsthÞÞüããSp~õbÞÞüGBBBBp~õbÞÞüFp~õGADBBp~õbÞÞüBCbSendenõcesdbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0Bããp~õbÞÞüBCbVorschauõcesbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0Bããp~õbÞÞüBCbAbbrechenõcedbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0BãããããããCtõVB~Textareaõsef\0\0\0hÿÿÿcCounterõÐããc°R>{linkhovercolor}<r°°12°_Icon auswählen:_#°+9014>RIGHT<°°10B>_hIconauswahl abbrechen|/executescriptlet 1textcardlayout:switchcard:showtextarea<r°##°>CENTER<°°>{table|w1|80|80|80|80|80|w1}<°°>{linkhovercolorreset}<°°>{tr}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-NoIcon<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_noicon...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Exclamation<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_xmark...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Question<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_qmark...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Warn<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_warning...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Clover<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_clover...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tr}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Bulb<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_bulb...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-ThumpDown<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_thumbdown...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-ThumpUp<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_thumbup...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-CalendarSummer<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_sun...w_25.my_1.mx_-6.png<°°>{imageboxend}<°°>{endtable}<°õ~iconchooseareaõsJîf\0\0\0hÞÞüãããããããããEp~õbÿÿÿU-õU\0\n\0BãWp~õbÿÿÿU-õU\0\n\0Bããã";

	}
        
      
        
public static String createMessageWindow(String an, String betreff, String text) {
return "k\0Nachricht schreiben an "+an+"õspostõpostõw?@f\0\0\0hÿÿÿcoTextareaõãCp~õbÿÿÿBNp~õbÿÿÿU-õU\0\0\nBãSp~õbÿÿÿU-õU\0\0\nBãCp~õBSp~õGBBBBp~õFp~õGABBBãããCpBNpBWpUpics/layout/boxS_tl.pngõU\0\0BãCpUpics/layout/boxS_tc.pngõU\0\0BãEpUpics/layout/boxS_tr.pngõU\0\0BããWpUpics/layout/boxS_cl.pngõU\0\0BãEpUpics/layout/boxS_cr.pngõU\0\0BãSpBWpUpics/layout/boxS_bl.pngõU\0\0BãCpUpics/layout/boxS_bc.pngõU\0\0BãEpUpics/layout/boxS_br.pngõU\0\0BããCp~õGABBBp~õBNp~õBSlõgKf\0\0\0hÞÞÿãClNachricht schreibenõbgUf\0\0\0hÞÞÿããCp~õBNpBNpBWpUpics/layout/bluebox-nontrans_tl.pngõU\0\0BãCpUpics/layout/bluebox-nontrans_tc.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_tr.pngõU\0\0BããWpUpics/layout/bluebox-nontrans_cl.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_cr.pngõU\0\0BãSpBWpUpics/layout/bluebox-nontrans_bl.pngõU\0\0BãCpUpics/layout/bluebox-nontrans_bc.pngõU\0\0BãEpUpics/layout/bluebox-nontrans_br.pngõU\0\0BããCp~õGABBBp~õbÊÊÿBNp~õbÊÊÿU-õU\0\0BãSp~õbÊÊÿU-õU\0\0BãCp~õbÊÊÿBWp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBlõbgDf\0\0\0hÊÊÿãããWp~õU-õU\0A\0\0GABBBlõbgDf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBf"+an+"õBhïïÿãããWp~õU-õU\0A\0\0GABBBlAn:õbgMf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBp~õbÊÊÿBCp~õbÊÊÿBNp~õbÊÊÿU-õU\0\0BãSp~õbÊÊÿU-õU\0\t\0\tBãCf"+betreff+"õBhïïÿããWp~iconcardlayoutõCc°>posts/msg_ico_noicon...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-NoIconõs\0#\0(tsendbackãformValueNoIconõf\0\0\0hÊÊÿoãc°>posts/msg_ico_xmark...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Exclamationõs\0#\0\ntsendbackãformValueExclamationõf\0\0\0hÊÊÿoãc°>posts/msg_ico_qmark...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Questionõs\0#\0\ntsendbackãformValueQuestionõf\0\0\0hÊÊÿoãc°>posts/msg_ico_warning...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Warnõs\0#\0\ntsendbackãformValueWarnõf\0\0\0hÊÊÿoãc°>posts/msg_ico_clover...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Cloverõs\0#\0\ntsendbackãformValueCloverõf\0\0\0hÊÊÿoãc°>posts/msg_ico_bulb...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Bulbõs\0#\0\ntsendbackãformValueBulbõf\0\0\0hÊÊÿoãc°>posts/msg_ico_thumbdown...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ThumpDownõs\0#\0\ntsendbackãformValueThumpDownõf\0\0\0hÊÊÿoãc°>posts/msg_ico_thumbup...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ThumpUpõs\0#\0\ntsendbackãformValueThumpUpõf\0\0\0hÊÊÿoãc°>posts/msg_ico_cm...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-CMMsgõs\0#\0\ntsendbackãformValueCMMsgõf\0\0\0hÊÊÿoãc°>posts/msg_ico_butler...my_-3.mx_-9.w_25.gif<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Jamesõs\0#\0\ntsendbackãformValueJamesõf\0\0\0hÊÊÿoãc°>posts/msg_ico_heart...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Heartõs\0#\0\ntsendbackãformValueHeartõf\0\0\0hÊÊÿoãc°>posts/msg_ico_manocornuto...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-ManoCornutoõs\0#\0\ntsendbackãformValueManoCornutoõf\0\0\0hÊÊÿoãc°>posts/msg_ico_victory...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Victoryõs\0#\0\ntsendbackãformValueVictoryõf\0\0\0hÊÊÿoãc°>posts/msg_ico_sw...h_8.my_-1.mx_-6.w_30.h_40.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-SmileyWarsõs\0#\0\ntsendbackãformValueSmileyWarsõf\0\0\0hÊÊÿoãc°>posts/msg_ico_knuddelkey...my_-3.mx_-9.w_25.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Internetdayõs\0#\0\ntsendbackãformValueInternetdayõf\0\0\0hÊÊÿoãc°>posts/msg_ico_xmas...h_8.my_-1.mx_-6.w_25.h_40.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-XMASTreeõs\0#\0\ntsendbackãformValueXMASTreeõf\0\0\0hÊÊÿoãc°>posts/msg_ico_sun...w_25.my_1.mx_-6.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-CalendarSummerõs\0#\0\ntsendbackãformValueCalendarSummerõf\0\0\0hÊÊÿoãc°>worldtour/m-icon_01...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour1õs\0#\0\ntsendbackãformValueWorldTour1õf\0\0\0hÊÊÿoãc°>worldtour/m-icon_02...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour2õs\0#\0\ntsendbackãformValueWorldTour2õf\0\0\0hÊÊÿoãc°>worldtour/m-icon_03...w_25.my_-1.mx_-11.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-WorldTour3õs\0#\0\ntsendbackãformValueWorldTour3õf\0\0\0hÊÊÿoãc°>posts/msg_ico_private2post...w_22.h_8.my_2.mx_0.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-PrivateToPostõs\0#\0\ntsendbackãformValuePrivateToPostõf\0\0\0hÊÊÿoãc°>posts/msg_sold...w_30.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Soldõs\0#\0\ntsendbackãformValueSoldõf\0\0\0hÊÊÿoãc°>posts/msg_bought...w_35.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Boughtõs\0#\0\ntsendbackãformValueBoughtõf\0\0\0hÊÊÿoãc°>posts/msg_rank...w_41.h_8.my_0.mx_-2.png<>--<>_h|/executescriptlet 1textcardlayout:switchcard:iconchoosearea<°õ~icon-Rankõs\0#\0\ntsendbackãformValuePrivateToPostõf\0\0\0hÊÊÿoãããããWp~õU-õU\0A\0\0GABBBlBetreff:õbgMf\0\0\0hÊÊÿãããCp~õBNp~õBCp~õBCp~õU-õU?\0\0GABBBp~cardlayoutõCc°12+9503°_°B>_nZeitpunkt der Zustellung festlegen|/togglecheckbox checkbox10<>--<>posts/scheduled...h_0.png<>--<>_n|/togglecheckbox checkbox10<rb°#°+9020°õ~cardlabelõs\0\n\0\nf\0\0\0hÊÊÿoãp~cardtextõBp~õbÊÊÿBWp~õBCp~õBCp~õBCp~õU-õU\0Ï\0\0GABBBl(Bsp: 30.05.2012 10:15:00 oder 5m)õbrgMf\0\0\0hÊÊÿãããWp~õU-õU\0Ò\0\0GABBBfõUhïïÿãããããããããWp~õU-õU\0A\0\0GABBBx~checkbox10õsfS1\0cardlayout:switchcard:cardtext\00\0cardlayout:switchcard:cardlabelõãããCp~õBCp~õBCp~õBCp~õU-õU?\0\0GABBBlõbgDf\0\0\0hÊÊÿãããWp~õU-õU\0A\0\0GABBBlõbgDf\0\0\0hÊÊÿãããããããããEp~õbÊÊÿU-õU\0\0BãWp~õbÊÊÿU-õU\0\0BããããCp~õBNp~õbÞÞÿU-õU\0\0BãCp~õBCp~textcardlayoutõCp~showtextareaõBSp~õBSp~õBNp~õbÞÞüBCc_°[4,36,243]12>_hSignatur anfügen|/m sig<°_õf\0\0\0hÞÞüoãEp~õBCl                                                                           õ~CounterõbrgMf\0\0\0hÞÞüãEc°>{-scroll}<>right<>smileybox/sb_w2-button2...mx_1.gif|smileybox/sb_w2-button2_hover...mx_1.gif<>|/code ?<°õs\0*\0f\0\0\0hÞÞüozKããWxsthÞÞüããSp~õbÞÞüGBBBBp~õbÞÞüFp~õGADBBp~õbÞÞüBCbSendenõcesdbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0Bããp~õbÞÞüBCbVorschauõcesbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0Bããp~õbÞÞüBCbAbbrechenõcedbgOhÿÿÿãEp~õbÞÞüU-õU\0\0\0BãWp~õbÞÞüU-õU\0\0\0BãããããããCt\n\n\n"+text+"õVB~Textareaõsef\0\0\0hÿÿÿcCounterõÐããc°R>{linkhovercolor}<r°°12°_Icon auswählen:_#°+9014>RIGHT<°°10B>_hIconauswahl abbrechen|/executescriptlet 1textcardlayout:switchcard:showtextarea<r°##°>CENTER<°°>{table|w1|80|80|80|80|80|w1}<°°>{linkhovercolorreset}<°°>{tr}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-NoIcon<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_noicon...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Exclamation<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_xmark...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Question<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_qmark...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Warn<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_warning...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Clover<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_clover...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tr}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-Bulb<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_bulb...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-ThumpDown<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_thumbdown...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-ThumpUp<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_thumbup...my_-3.mx_-9.w_25.png<°°>{imageboxend}<°°>{tc}<°°>{imageboxstart}noImages.hoverid_bluebox.click_/executescriptlet 1textcardlayout:switchcard:showtextarea1iconcardlayout:switchcard:icon-CalendarSummer<°°>transparent1x1...h_70.gif<°°>posts/msg_ico_sun...w_25.my_1.mx_-6.png<°°>{imageboxend}<°°>{endtable}<°õ~iconchooseareaõsJîf\0\0\0hÞÞüãããããããããEp~õbÿÿÿU-õU\0\n\0BãWp~õbÿÿÿU-õU\0\n\0Bããã";

        }
    
	public static String createAdminCallWindow() {
		return "k\0Notruf - Grund Auswählenõscdiaõ805õf\0\0\0h¾¼ûd Abbrechen õãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClNotruf - Grund AuswählenõlgSf\0\0\0hååÿãSlõgFh¾¼ûããCcBitte beachte, dass du einen Notruf nur in Notfällen einsetzen sollten. Bitte wähle den Grund des Notrufs aus.õs\0Kf\0\0\0h¾¼ûãSpBNpBNlNotruf-GrundõbgQf\0\0\0h¾¼ûãCogOã- Auswählen -õAllgemeine Frage / ProblemõBeschwerde über andere Chat-TeilnehmerõãSlõgPh¾¼ûããClõgFh¾¼ûãSpFb    OK    õsdbgQf\0\0\0h¾¼ûãb  ?  õsbgQf\0\0\0h¾¼ûãb Abbrechen õsdbgQf\0\0\0h¾¼ûããããã";
	}
	
	public static String createTeamInfoPopup(String team, String text) {
        Popup popup = new Popup(String.format("Teaminfo %s bearbeiten", team), String.format("Teaminfo %s bearbeiten", team), "", 0, 0);
        Panel panel7 = new Panel();
        TextArea a = new TextArea(15, 50);
        a.setText(text);
        panel7.addComponent(a);
        popup.addPanel(panel7);
        Panel panel2 = new Panel();
        Button button = new Button("Speichern");
        button.setStyled(true);
        button.enableAction();
        button.disableClose();
        panel2.addComponent(button);
        Button button2 = new Button("Abbrechen");
        button2.setStyled(true);
        panel2.addComponent(button2);
        popup.addPanel(panel2);
        popup.setOpcode(ReceiveOpcode.FA.getValue(), team);
        return popup.toString();
	}
	
	public static String createServerPopup(boolean edit, String module, String[] infos) {
		String text = String.format("Server - %s - %s", module, edit ? "Eintrag bearbeiten":"Neuer Eintrag");
        Popup popup = new Popup(text, text, "", 350, 0);
        popup.setButtonFontSize(17);
        Panel panel1 = new Panel();
        
        if(module.equalsIgnoreCase("wordmix")) {
        	panel1.addComponent(new Label("Satz:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);
        }if(module.equalsIgnoreCase("mix")) {
        	panel1.addComponent(new Label("wort:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);
        } if(module.equalsIgnoreCase("werbung")) {
            
        	panel1.addComponent(new Label("Werbetext:"));
        	panel1.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel1);
        } if(module.equalsIgnoreCase("butlertipps")) {
            
        	panel1.addComponent(new Label("ButlerTipp:"));
        	panel1.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel1);
            
        } else if(module.equalsIgnoreCase("todo")) {
        	panel1.addComponent(new Label("Text:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);
        } else if(module.equalsIgnoreCase("teams")) {
        	panel1.addComponent(new Label("Team:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);
        } else if(module.equalsIgnoreCase("rechte")) {
        	panel1.addComponent(new Label("Recht:     "));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Bezeichnungen:"));
        	panel3.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel3);
        } else if(module.equalsIgnoreCase("umfragen")) {
        	popup.setMessage("Hinweis: Antworten getrennt mit einem Semikolon schreiben.");
        	panel1.addComponent(new Label("Frage:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Antworten:"));
        	panel3.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel3);         
        } else if(module.equalsIgnoreCase("quiz")) {
        	popup.setMessage("Hinweis: Es ist nur eine Antwort möglich mit max. 100 Buchstaben.");
        	panel1.addComponent(new Label("Frage:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Lösung:"));
        	panel3.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel3);
            } else if(module.equalsIgnoreCase("mathe")) {
        	popup.setMessage("Hinweis: Es ist nur eine Antwort möglich mit max. 100 Buchstaben.");
        	panel1.addComponent(new Label("Frage:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Lösung:"));
        	panel3.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel3);
            } else if(module.equalsIgnoreCase("translate")) {
        	popup.setMessage("Hinweis: Es ist nur eine Antwort möglich mit max. 100 Buchstaben.");
        	panel1.addComponent(new Label("Frage:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Lösung:"));
        	panel3.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel3);
        } else if(module.equalsIgnoreCase("butler")) {
        	popup.setMessage("Replacements: {FROM__NICK}, {YEAR}, {DAY}, {MONTH}, {SECONDS}, {MINUTES}, {HOURS}, {MILISECONDS}, {WEEKDAY}");
        	popup.setHeight(100);
        	panel1.addComponent(new Label("Wort:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Antworten:"));
        	panel3.addComponent(new TextArea(15,30,  infos[1]));
            popup.addPanel(panel3);
        } else if(module.equalsIgnoreCase("makros")) {
        	popup.setMessage("Replacements: [TEXT]");
        	popup.setHeight(100);
        	panel1.addComponent(new Label("Makro:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel3 = new Panel();
        	panel3.addComponent(new Label("Texte:"));
        	panel3.addComponent(new TextArea(15,30,  infos[1]));
            popup.addPanel(panel3);
        } else if(module.equalsIgnoreCase("wahlen")) {
        	panel1.addComponent(new Label("Name:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel4 = new Panel();
        	panel4.addComponent(new Label("Status:"));
        	String status = infos[1];
        	panel4.addComponent(new Choice(new String[] {"Aktiviert", "Deaktiviert"}, status.isEmpty() || Integer.parseInt(status) == 1 ? "Aktiviert":"Deaktiviert"));
            popup.addPanel(panel4);

            Panel panel5 = new Panel();
        	panel5.addComponent(new Label("Nominierungsphase:"));
        	panel5.addComponent(new TextField(30, infos[2]));
            popup.addPanel(panel5);

            Panel panel6 = new Panel();
        	panel6.addComponent(new Label("Wahlphase:"));
        	panel6.addComponent(new TextField(30, infos[3]));
            popup.addPanel(panel6);

            Panel panel7 = new Panel();
        	panel7.addComponent(new Label("Endphase:"));
        	panel7.addComponent(new TextField(30, infos[4]));
            popup.addPanel(panel7);
        } else if(module.equalsIgnoreCase("badwords")) {
        	panel1.addComponent(new Label("BadWord:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel4 = new Panel();
        	panel4.addComponent(new Label("Jugendschutz?"));
        	String jugendschutz = infos[1].trim();
        	panel4.addComponent(new Choice(new String[] {"Ja", "Nein"}, jugendschutz.isEmpty() || Integer.parseInt(jugendschutz) == 0 ? "Nein":"Ja"));
            popup.addPanel(panel4);
        } else if(module.equalsIgnoreCase("funktionen")) {
        	panel1.addComponent(new Label("Funktion:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel4 = new Panel();
        	panel4.addComponent(new Label("Beschreibung:"));
        	panel4.addComponent(new TextArea(15,30,  infos[1]));
            popup.addPanel(panel4);
            
            Panel panel5 = new Panel();
        	panel5.addComponent(new Label("Rang:"));
        	panel5.addComponent(new Choice(new String[] {"Newbie", "Familymitglied", "Stammi", "Ehrenmitglied", "Status 4", "inoffizieller Admin", "Admin Status 6", "Admin Status 7", "Sysadmin Status 8", "Sysadmin Status 9", "Sysadmin Status 10", "Sysadmin Status 11"}));
            popup.addPanel(panel5);
        } else if(module.equalsIgnoreCase("hilfe")) {
        	panel1.addComponent(new Label("Wort:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel4 = new Panel();
        	panel4.addComponent(new Label("Titel:"));
        	panel4.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel4);

            Panel panel5 = new Panel();
        	panel5.addComponent(new Label("Text:"));
        	panel5.addComponent(new TextArea(15,30,  infos[2]));
            popup.addPanel(panel5);       
            } else if(module.equalsIgnoreCase("syslog")) {
        	panel1.addComponent(new Label("Time:"));
        	panel1.addComponent(new TextField(30, infos[0]));
            popup.addPanel(panel1);

            Panel panel4 = new Panel();
        	panel4.addComponent(new Label("Name:"));
        	panel4.addComponent(new TextField(30, infos[1]));
            popup.addPanel(panel4);

            Panel panel5 = new Panel();
        	panel5.addComponent(new Label("Text:"));
        	panel5.addComponent(new TextArea(15,30,  infos[2]));
            popup.addPanel(panel5);
        } else if(module.equalsIgnoreCase("toplisten")) {
        	panel1.addComponent(new Label("Name:"));
        	panel1.addComponent(new TextField(30, infos[0]));
        	popup.addPanel(panel1);
        	
        	Panel panel4 = new Panel();
        	panel4.addComponent(new Label("Beschreibung:"));
        	panel4.addComponent(new TextArea(15,30,  infos[1]));
        	popup.addPanel(panel4);

        	Panel panel5 = new Panel();
        	panel5.addComponent(new Label("Datenbankfeld:"));
        	panel5.addComponent(new TextField(30, infos[2]));
        	popup.addPanel(panel5);
        }
        
        Panel panel2 = new Panel();
        Button button = new Button("Absenden");
        button.enableAction();
        button.setStyled(true);
        panel2.addComponent(button);
        Button button2 = new Button("Abbrechen");
        button2.setStyled(true);
        panel2.addComponent(button2);
        popup.addPanel(panel2);
                
        String end = String.format("%s%s", edit ? String.format("edit%s~~~", module.equalsIgnoreCase("makros")?infos[0]:infos[0]):"", module);
       if(module.equalsIgnoreCase("werbung")) {
           if (edit) {
           end = "edit"+infos[0]+"~~~"+module;
           } else {
               end = module;
           }
       }
        popup.setOpcode(ReceiveOpcode.SERVER.getValue(), end);
        return popup.toString();
	}
	
	public static String createVerifyWindow(String nick, String email, int id) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("k\0E-Mail-Verifizierung ");
		builder.append(nick);
		builder.append("õsmaillinksõframe|-739543851145446095|"+nick+"õf\0\0\0h¾¼ûãNl õgFh¾¼ûãEl         õgFh¾¼ûãWl         õgFh¾¼ûãSl õgFh¾¼ûãCpBNpBNlE-Mail-Verifizierung ");
		builder.append(nick);
		builder.append("õblgSf\0\0\0hååÿããCpBNpBNl õgMf\0\0\0h¾¼ûãCpGBCIFpBCc°12°Die E-Mail-Verifizierung ist sehr wichtig, um deinen Nicknamen vor Missbrauch durch Dritte zu schützen.##");
		
		if(id == 1) {
			builder.append("_°16°E-Mail-Adresse verifizieren°12°_##_°>number_1.b.my_10.h_14.gif<%15°Trage im Eingabefeld unten die gewünschte E-Mail-Adresse ein°%00°##°>number_2.b.my_10.h_14.gif<%15°Klicke auf \"\"Verifizieren\"\"°%00°_##°>number_3.b.my_10.h_14.gif<%15°Nach einer Bestätigung per /m erhältst du eine E-Mail°%00°##°>number_4.b.my_10.h_14.gif<%15°Klicke in dieser E-Mail auf den Verifizierungslink##Fertig!");
		} else {
			builder.append("#Die eingetragene E-Mail-Adresse kann aus folgendem Grund nicht verifiziert werden: _°R°E-Mail-Adresse fehlerhaft_°r°##Bitte wähle eine andere E-Mail-Adresse!");
		}
		
		builder.append("õs\"f\0\0\0h¾¼ûãSpBCf");
		builder.append(email);
		builder.append("õUf\0\0\0hÿÿÿãEpBWl õgSh¾¼ûãb Verifizieren õcesdpbgMf\0\0\0h¾¼ûããããããããã");
		
		return builder.toString();	
	}
	
	public static String protocolConfirmed() {
		PacketWriter packet = new PacketWriter();
		packet.writeString(SendOpcode.MODULE.getValue());
		packet.write(0x00);
		packet.writeShort(Server.get().getModuleTree().getIndex("PROTOCOL_CONFIRMED"));
		return packet.toString();
	}

	public static String changeProtocol() {
		PacketWriter packet = new PacketWriter();
		packet.writeString(SendOpcode.MODULE.getValue());
		packet.write(0x00);
		packet.writeShort(Server.get().getModuleTree().getIndex("CHANGE_PROTOCOL"));
		packet.writeShort(Server.get().getModuleTree().getTree().length());
		packet.writeString(Server.get().getModuleTree().getTree());
		return packet.toString();
	}
	
	public static String createInviteWindow(String nick, String name, String thema) {
		return "k\0Separee mit "+nick+" eröffnenõsinvõ"+nick+"õf\0\0\0h¾¼ûãEl         õcgFh¾¼ûãWl         õcgFh¾¼ûãCpBNpBNl õgIh¾¼ûãClSeparee mit "+nick+" eröffnenõblgSf\0\0\0hååÿãSl õgQh¾¼ûããCpBCcDu möchtest _"+nick+"_ in ein _Separee_ einladen.##Du musst nun einen _Namen_ für das Separee wählen, ein _Design_, optional einen _Test_ und zu guter letzt, ob du einen anwesenden _Butler_ magst, oder lieber ganz allein mit "+nick+" sein willst.õs\0f\0\0\0h¾¼ûipics/cloudsblue.gifõ\0ãSpGACDDl õgUh¾¼ûãl õgUh¾¼ûãlSeparee Name: õgOh¾¼ûãf"+name+"õUf\0\0\0hÿÿÿãlSeparee Thema: õgOh¾¼ûãf"+thema+"õUf\0\0\0hÿÿÿãlDesign: õgOh¾¼ûãoãBlumentapeteõBlaue WolkenõKerzeõHansi und eine Handvoll HerzenõPferde auf grünõSterneõErdbeerenõKirschenõStifte Radierer und KnöpfeõãlTest: õgOh¾¼ûãoãKein TestõÄhnlichkeitstestõMenschenkenntnistestõãlButler?õgOh¾¼ûãxstãl õgUh¾¼ûãl õgUh¾¼ûãb  OK  õsdpgSf\0\0\0h¾¼ûãb  Abbrechen  õdpgSf\0\0\0h¾¼ûããããã";
	}
	
    public static String createCodeActivateWindow() {
        Popup popup = new Popup("Code aktivieren", "Code aktivieren", "Gib weiter unten die vier Buchstabenblöcke ein, die du in der /m findest. Durch einen anschließenden Klick auf _Aktivieren_ wird dein Code für deinen Nicknamen aktiviert.#Beachte: Nach Aktivierung kann der Code nicht mehr verschenkt werden.", 350, 120);
        popup.setButtonFontSize(17);
        Panel panel1 = new Panel();
        panel1.addComponent(new Label("Code:"));
        panel1.addComponent(new TextField(4, ""));
        panel1.addComponent(new Label("-"));
        panel1.addComponent(new TextField(4, ""));
        panel1.addComponent(new Label("-"));
        panel1.addComponent(new TextField(4, ""));
        panel1.addComponent(new Label("-"));
        panel1.addComponent(new TextField(4, ""));
        popup.addPanel(panel1);
        Panel panel2 = new Panel();
        Button button = new Button("Aktivieren");
        button.setStyled(true);
        button.enableAction();
        button.disableClose();
        panel2.addComponent(button);
        Button button2 = new Button("Abbrechen");
        button2.setStyled(true);
        panel2.addComponent(button2);
        popup.addPanel(panel2);
                
        popup.setOpcode(ReceiveOpcode.CODE.getValue(), "activate");
        return popup.toString();
    }
    
	public static String createRoseWindow(String nick, String text) {
        Popup popup = new Popup("Rose versenden", "Rose versenden", "", 0, 0);
        popup.setHeaderFontSize(18);
        Panel panel1 = new Panel();
        panel1.addComponent(new Label("Empfänger (Nick):"));
        panel1.addComponent(new TextField(41, nick));
        popup.addPanel(panel1);
        Panel panel2 = new Panel();
        panel2.addComponent(new Label("Text zur Rose:                                                                                      "));
        popup.addPanel(panel2);
        Panel panel3 = new Panel();
        panel3.addComponent(new TextArea(15,60, text));
        popup.addPanel(panel3);
        Panel panel4 = new Panel();
        Button button = new Button("Absenden");
        button.setStyled(true);
        button.enableAction();
        panel4.addComponent(button);
        Button button2 = new Button("Schließen");
        button2.setStyled(true);
        panel4.addComponent(button2);
        popup.addPanel(panel4);

        popup.setOpcode(ReceiveOpcode.ROSE.getValue(), "rose");
        return popup.toString();
	}
    
	public static String createCodeGiveAwayWindow(String nick, String text) {
        Popup popup = new Popup("Code verschenken", "Code verschenken", "", 0, 0);
        Panel panel0 = new Panel();
        panel0.addComponent(new Label("Empfänger (Nick):"));
        panel0.addComponent(new TextField(41, nick));
        popup.addPanel(panel0);
        Panel panel1 = new Panel();
        panel1.addComponent(new Label("Code:          "));
        panel1.addComponent(new TextField(4, ""));
        panel1.addComponent(new Label("-"));
        panel1.addComponent(new TextField(4, ""));
        panel1.addComponent(new Label("-"));
        panel1.addComponent(new TextField(4, ""));
        panel1.addComponent(new Label("-"));
        panel1.addComponent(new TextField(4, ""));
        popup.addPanel(panel1);
        Panel panel2 = new Panel();
        panel2.addComponent(new Label("Text zum Geschenk:                                                                            "));
        popup.addPanel(panel2);
        Panel panel3 = new Panel();
        panel3.addComponent(new TextArea(10,60, text));
        popup.addPanel(panel3);
        Panel panel4 = new Panel();
        Button button = new Button("Verschenken");
        button.setStyled(true);
        button.enableAction();
        panel4.addComponent(button);
        Button button2 = new Button("Schließen");
        button2.setStyled(true);
        panel4.addComponent(button2);
        popup.addPanel(panel4);

        popup.setOpcode(ReceiveOpcode.CODE.getValue(), "giveaway");
        return popup.toString();
	}
    
	public static String createHZEditorWindow(String channel, String text) {
		String title = String.format("HZ-Editor - Channel %s", channel);
        Popup popup = new Popup(title, title, "", 0, 0);
        Panel panel2 = new Panel();
        panel2.addComponent(new Label("Hauptzuständige Admins:                                        "));
        popup.addPanel(panel2);
        Panel panel3 = new Panel();
        panel3.addComponent(new TextArea(5,40, text));
        popup.addPanel(panel3);
        Panel panel4 = new Panel();
        Button button = new Button("Speichern");
        button.setStyled(true);
        button.enableAction();
        panel4.addComponent(button);
        Button button2 = new Button("Schließen");
        button2.setStyled(true);
        panel4.addComponent(button2);
        popup.addPanel(panel4);

        popup.setOpcode(ReceiveOpcode.EDITOR.getValue(), String.format("HZA~%s", channel));
        return popup.toString();
	}
    
        public static void createWindowDetailsFreidiffen(Client client, String einsatz)
  {
    String text = new StringBuilder().append("°>_h").append(client.getName()).append("|/serverpp \"|/w \"<° bietet momentan dieses Spiel an: _Freidiffen_##_Regeln:_ #Du und dein Gegner werfen nacheinander _1w2_. Der Gewinner erhält die Differenz der Ergebnisse sofort ausgezahlt. ##_Einsatz:_ ").append(einsatz).append("##°-°##_Beispiel:_ Holgi und Plex spielen gegeneinander Freidiffen. Beide haben _10 Knuddel_ gesetzt. Das Spiel startet mit 1w10.##°>bullet.b.png<° Holgi wirft 1w10 und trift eine _7_.#°>bullet.b.png<° Plex wirft 1w10 und trift eine _3_.##Holgi gewinnt das Spiel und erhält _14 Knuddel_. (= 10 + 7 - 3)#Plex verliert das Spiel und erhält _6 Knuddel_. (= 10 + 3 - 7)").toString();
    client.send(PopupNewStyle.create("Erklärung des Spielangebotes", "Erklärung des Spielangebotes", text, 400, 300));
  }
  public static void createWindowDetailsDarten(Client client, String einsatz) {
    String text = new StringBuilder().append("°>_h").append(client.getName()).append("|/serverpp \"|/w \"<° bietet momentan dieses Spiel an: _Darten_##_Regeln:_ #Du und dein Gegner werfen nacheinander einen _Wurf 1w100_. Die jeweils erwürfelte Zahl ist die nächste Zahl mit der gewürfelt wird. Der Spieler, der zuerst _die Eins_ wirft gewinnt das Spiel. Solltest du zuerst _eine Eins_ würfeln, so hat dein Gegner noch die Chance ein Unentschieden zu erreichen.##_Einsatz:_ ").append(einsatz).append("##°-°##_Beispiel:_ Holgi und Plex spielen gegeneinander Dart. Das Spiel startet mit 1w10.##°>bullet.b.png<° Holgi wirft 1w10 und trift eine _7_.#°>bullet.b.png<° Plex wirft 1w10 und trift eine _3_.#°>bullet.b.png<° Holgi wirft 1w_7_ und trift eine _4_.#°>bullet.b.png<° Plex wirft 1w_3_ und trift eine _2_.#°>bullet.b.png<° Holgi wirft 1w_4_ und trift eine _1_.##Wenn in diesem Spiel Nachwürfe aktiv sind, so darf Plex noch versuchen mit dem nächsten Wurf ein Unentschieden zu erreichen. Falls Nachwürfe nicht eingeschaltet sind, so hat Holgi nun gewonnen.").toString();
    client.send(PopupNewStyle.create("Erklärung des Spielangebotes", "Erklärung des Spielangebotes", text, 400, 300));
  }
  public static void createWindowDetailsDicen(Client client, String einsatz) {
    String text = new StringBuilder().append("°>_h").append(client.getName()).append("|/serverpp \"|/w \"<° bietet momentan dieses Spiel an: _Dicen_##_Regeln:_ #Du entscheidest, welche Würfel geworfen werden sollen. Danach würfeln Du und dein Gegner diese Würfel. Die Person, die die höhere Zahl würfelt erhält einen Punkt. Das Spiel endet, sobald ein Spieler 3 Punkte erreicht hat°_° (Mindestabstand 2 Punkte).##_Einsatz:_ ").append(einsatz).append("##°-°##_Beispiel:_ Holgi und Plex spielen gegeneinander Dicen. Sie spielen mit _2 Siege_.##°>bullet.b.png<° Holgi entscheidet sich für die Würfel _1w10_.#°>bullet.b.png<° Holgi wirft 1w10 und trift eine _3_.#°>bullet.b.png<° Plex wirft 1w10 und trift eine _5_.##Plex erhält einen Punkt. Somit steht es 1:0 für Plex.##°>bullet.b.png<° Plex entscheidet sich für die Würfel _1w8_.#°>bullet.b.png<° Plex wirft 1w8 und trift eine _2_.#°>bullet.b.png<° Holgi wirft 1w8 und trift eine _1_.##Plex erhält einen Punkt. Somit steht es 2:0 für Plex.#Plex hat das Spiel gewonnen, da er zuerst 2 Siege erlangt hat.").toString();
    client.send(PopupNewStyle.create("Erklärung des Spielangebotes", "Erklärung des Spielangebotes", text, 400, 300));
  }
        
	public static String createRightsEditorWindow(String permission, String rights) {
		String title = String.format("Rights-Editor - Recht %s", permission);
        Popup popup = new Popup(title, title, "", 0, 0);
        Panel panel2 = new Panel();
        panel2.addComponent(new Label("Bezeichnungen:                                                        "));
        popup.addPanel(panel2);
        Panel panel3 = new Panel();
        panel3.addComponent(new TextArea(5,40, rights));
        popup.addPanel(panel3);
        Panel panel4 = new Panel();
        Button button = new Button("Speichern");
        button.enableAction();
        button.setStyled(true);
        panel4.addComponent(button);
        Button button2 = new Button("?");
        button2.setStyled(true);
        button2.enableAction();
        button2.disableClose();
        panel4.addComponent(button2);
        Button button3 = new Button("Schließen");
        button3.setStyled(true);
        panel4.addComponent(button3);
        popup.addPanel(panel4);

        popup.setOpcode(ReceiveOpcode.EDITOR.getValue(), String.format("RIGHTS~%s", permission));
        return popup.toString();
	}
	
    public static String createSnpWindow(String von, String nick, String password) {
        Popup popup = new Popup("Neues Passwort setzen", "Neues Passwort setzen", "", 0, 0);
        Panel panel1 = new Panel();
        panel1.addComponent(new Label(String.format("Nick:                     ")));
        panel1.addComponent(new TextField(25, nick));
        popup.addPanel(panel1);
        Panel panel2 = new Panel();
        panel2.addComponent(new Label("Neues Passwort:"));
        panel2.addComponent(new TextField(25, password));
        popup.addPanel(panel2);
        Panel panel4 = new Panel();
        Button button = new Button("Ändern");
        button.setStyled(true);
        button.enableAction();
        panel4.addComponent(button);
        Button button2 = new Button("Abbrechen");
        button2.setStyled(true);
        panel4.addComponent(button2);
        popup.addPanel(panel4);

        popup.setOpcode(ReceiveOpcode.SNP.getValue(), von);
        return popup.toString();
    }
    
        public static String hexToString(String text) {
     StringBuilder sb = new StringBuilder();
	  
    for(String output : text.split(" ")) {
        if (!output.trim().isEmpty()) {
      int decimal = Integer.parseInt(output, 16);
       sb.append((char)decimal);
    }}
   return sb.toString();
    
}
        public static String createLeihenWindow2(Client client,String text, String nick,String[] smileyid) {
            StringBuilder builder = new StringBuilder();
            builder.append(hexToString("6B 00 53 6D 69 6C 65 79 73 20 76 65 72 6C 65 69 68 65 6E F5 73 63 6F 64 65 F5"));
            
            builder.append("leihen2~"+nick);
            
            builder.append(hexToString("F5 66 00 00 00 68 BE BC FB E3 57 6C 20 20 F5 67 4D 68 BE BC FB E3 45 6C 20 20 F5 67 4D 68 BE BC FB E3 4E 6C F5 67 46 68 BE BC FB E3 53 6C F5 67 46 68 BE BC FB E3 43 70 42 43 70 42 E3 4E 70 42 4E 70 42 43 6C 53 6D 69 6C 65 79 73 20 76 65 72 6C 65 69 68 65 6E F5 6C 67 53 66 00 00 00 68 E5 E5 FF E3 53 6C F5 67 46 68 BE BC FB E3 E3 43 70 42 43 70 42 57 63 23"));
            
          //  B0 3E 69 53 70 65 63 74 72 61 7C 2F 73 65 72 76 65 72 70 70 20 22 7C 2F 77 20 22 3C B0 5F 20 6D F6 63 68 74 65 20 64 69 72 20 66 6F 6C 67 65 6E 64 65 73 20 66 FC 72 20 5F 31 20 4D 69 6E 75 74 65 20 6C 65 69 68 65 6E 5F 3A 23 23 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 5F 31 20 53 6D 69 6C 65 79 5F 20 28 B0 3E 66 65 61 74 75 72 65 73 2F 67 69 66 74 62 6F 78 65 73 2F 67 69 66 74 74 69 63 6B 65 74 5F 70 6F 6B 65 72 2E 2E 2E 62 2E 6D 79 5F 39 2E 70 6E 67 3C 3E 2D 2D 3C 3E 7C 2F 63 6F 64 65 20 3F 3A 31 30 37 30 3C B0 29
            builder.append(text);
            builder.append(hexToString("F5 73 01 AE 00 5A 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 43 63 20 F5 73 00 0A 00 0A 68 BE BC FB E3 45 70 42 4E 63 F5 73 01 4A 00 26 68 BE BC FB E3 43 70 42 4E 63 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 57 69 65 20 76 69 65 6C 65 20 4B 6E 75 64 64 65 6C 20 67 69 62 73 74 20 64 75 3F 5F 20 B0 31 32 B0 28 64 75 20 68 61 73 74 20"));
              builder.append(client.getKnuddels()); 
            builder.append(hexToString("29 F5 73 01 4A 00 1A 68 BE BC FB E3 57 6C 4B 6E 75 64 64 65 6C 3A 20 F5 67 50 68 BE BC FB E3 43 66 F5 46 66 00 00 00 68 FF FF FF E3 45 63 F5 73 00 A5 00 0A 68 BE BC FB E3 E3 E3 E3 53 70 42 4E 63 B0 3E 6C 61 79 6F 75 74 2F 74 72 61 64 65 5F 63 6F 64 65 73 5F 6C 69 6E 65 2E 2E 2E 68 5F 32 30 2E 70 6E 67 3C B0 23 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 4D F6 63 68 74 65 73 74 20 64 75 20 61 75 63 68 20 65 74 77 61 73 20 76 65 72 6C 65 69 68 65 6E 3F 5F 20 B0 31 32 B0 28 6F 70 74 69 6F 6E 61 6C 29 F5 73 02 F8 00 32 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 43 63"));
            builder.append("°40° °r°");
            
            
               int counter = 0;
              for (int i = 0; i < smileyid.length; i++) {
                  counter++;
                  
                  
                  if (counter == 1) {
                      builder.append("°%0°");
                  } else if (counter == 2) {
                      builder.append("°%20°");
                  }else if (counter == 3) {
                      builder.append("°%40°");
                  }else if (counter == 4) {
                      builder.append("°%60°");
                  }else if (counter == 5) {
                      builder.append("°%80°");
                  }
                  
                  
                   builder.append("°>{checkbox");
             builder.append(smileyid[i]);
             builder.append("<>}checkbox<° ");
            
               Usersmiley sm = Server.get().getUsersmiley(smileyid[i]);
             Smiley ss = Server.get().getSmiley(String.valueOf(sm.getSMID()));
             builder.append(ss.getKCode());
             if (counter == 5) {
             builder.append("°%00°#°40° °r°");
             counter = 0;
             }
  }
       
              

           
            builder.append(hexToString("F5 73 02 F8 00 F0 74 73 65 6E 64 62 61 63 6B F5 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 E3 E3 E3 53 70 46 62 56 65 72 6C 65 69 68 65 6E F5 63 65 73 64 62 67 51 66 00 00 00 68 BE BC FB E3 62 53 63 68 6C 69 65 DF 65 6E F5 63 65 64 67 51 66 00 00 00 68 BE BC FB E3 E3 E3 E3"));
            return builder.toString();
        }
         public static String createLeihenWindow(Client client,String[] smileyid) {
   StringBuilder builder = new StringBuilder();
       
            builder.append(hexToString("6B 00 53 6D 69 6C 65 79 73 20 76 65 72 6C 65 69 68 65 6E F5 73 63 6F 64 65 F5"));
            
             builder.append("Tausch-Fenster öffnen");
            builder.append(hexToString("F5 66 00 00 00 68 BE BC FB E3 57 6C 20 20 F5 67 4D 68 BE BC FB E3 45 6C 20 20 F5 67 4D 68 BE BC FB E3 4E 6C F5 67 46 68 BE BC FB E3 53 6C F5 67 46 68 BE BC FB E3 43 70 42 43 70 42 E3 4E 70 42 4E 70 42 43 6C 53 6D 69 6C 65 79 73 20 76 65 72 6C 65 69 68 65 6E F5 6C 67 53 66 00 00 00 68 E5 E5 FF E3 53 6C F5 67 46 68 BE BC FB E3 E3 43 70 42 4E 63 23 47 69 62 20 65 69 6E 2C 20 5F 61 6E 20 77 65 6E 5F 20 75 6E 64 20 5F 77 65 6C 63 68 65 20 53 6D 69 6C 65 79 73 5F 20 64 75 20 5F 76 65 72 6C 65 69 68 65 6E 5F 20 6D F6 63 68 74 65 73 74 20 75 6E 64 20 66 FC 72 20 5F 77 69 65 20 6C 61 6E 67 65 5F 3A F5 73 02 F8 00 32 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 43 70 42 57 70 42 4E 63 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 56 65 72 6C 65 69 68 65 6E 20 61 6E 20 77 65 6E 3F 5F F5 73 01 04 00 1A 68 BE BC FB E3 57 6C 56 65 72 6C 65 69 68 65 6E 20 61 6E 20 28 4E 69 63 6B 29 3A F5 67 50 68 BE BC FB E3 43 66 F5 4D 66 00 00 00 68 FF FF FF E3 45 6C 20 F5 67 4D 68 BE BC FB E3 E3 43 70 42 4E 63 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 57 69 65 20 6C 61 6E 67 65 3F 5F F5 73 00 AA 00 1A 68 BE BC FB E3 57 6C 56 65 72 6C 65 69 68 64 61 75 65 72 3A 20 F5 67 50 68 BE BC FB E3 43 6F 66 00 00 00 68 FF FF FF 67 4C E3 31 20 4D 69 6E 75 74 65 F5 31 30 20 4D 69 6E 75 74 65 6E F5 33 30 20 4D 69 6E 75 74 65 6E F5 31 20 53 74 75 6E 64 65 F5 32 20 53 74 75 6E 64 65 6E F5 36 20 53 74 75 6E 64 65 6E F5 31 32 20 53 74 75 6E 64 65 6E F5 31 20 54 61 67 F5 32 20 54 61 67 65 F5 33 20 54 61 67 65 F5 31 20 57 6F 63 68 65 F5 32 20 57 6F 63 68 65 6E F5 E3 45 6C 20 F5 67 4D 68 BE BC FB E3 E3 45 70 42 43 70 42 4E 63 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 57 69 65 20 76 69 65 6C 65 20 4B 6E 75 64 64 65 6C 20 67 69 62 73 74 20 64 75 3F 5F 20 B0 31 32 B0 28 64 75 20 68 61 73 74 20"));
            builder.append(client.getKnuddels());            
            builder.append(hexToString("29 F5 73 01 4A 00 1A 68 BE BC FB E3 57 6C 4B 6E 75 64 64 65 6C 3A 20 F5 67 50 68 BE BC FB E3 43 66 F5 46 66 00 00 00 68 FF FF FF E3 45 63 F5 73 00 A5 00 0A 68 BE BC FB E3 E3 E3 E3 53 70 42 4E 63 B0 3E 6C 61 79 6F 75 74 2F 74 72 61 64 65 5F 63 6F 64 65 73 5F 6C 69 6E 65 2E 2E 2E 68 5F 32 30 2E 70 6E 67 3C B0 23 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 57 61 73 20 73 6F 6C 6C 20 76 65 72 6C 69 65 68 65 6E 20 77 65 72 64 65 6E 3F 5F F5 73 02 F8 00 32 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 43 63"));
            builder.append("°40° °r°");
         
            int counter = 0;
              for (int i = 0; i < smileyid.length; i++) {
                  counter++;
                  
                  
                  if (counter == 1) {
                      builder.append("°%0°");
                  } else if (counter == 2) {
                      builder.append("°%20°");
                  }else if (counter == 3) {
                      builder.append("°%40°");
                  }else if (counter == 4) {
                      builder.append("°%60°");
                  }else if (counter == 5) {
                      builder.append("°%80°");
                  }
                  
                  
                   builder.append("°>{checkbox");
             builder.append(smileyid[i]);
             builder.append("<>}checkbox<° ");
            
               Usersmiley sm = Server.get().getUsersmiley(smileyid[i]);
             Smiley ss = Server.get().getSmiley(String.valueOf(sm.getSMID()));
             builder.append(ss.getKCode());
             if (counter == 5) {
             builder.append("°%00°#°40° °r°");
             counter = 0;
             }
  }
       
       builder.append(hexToString("F5 73 02 F8 00 F0 74 73 65 6E 64 62 61 63 6B F5 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 E3 E3 E3 53 70 46 62 56 65 72 6C 65 69 68 65 6E F5 63 65 73 64 62 67 51 66 00 00 00 68 BE BC FB E3 62 53 63 68 6C 69 65 DF 65 6E F5 63 65 64 67 51 66 00 00 00 68 BE BC FB E3 E3 E3 E3"));
       return builder.toString();
             // ohne knuddel //  return "6B 00 53 6D 69 6C 65 79 73 20 76 65 72 6C 65 69 68 65 6E F5 73 63 6F 64 65 F5 54 61 75 73 63 68 2D 46 65 6E 73 74 65 72 20 F6 66 66 6E 65 6E 7E 4C 7E 4E 7E 4E 7E 44 7E 3F F5 66 00 00 00 68 BE BC FB E3 57 6C 20 20 F5 67 4D 68 BE BC FB E3 45 6C 20 20 F5 67 4D 68 BE BC FB E3 4E 6C F5 67 46 68 BE BC FB E3 53 6C F5 67 46 68 BE BC FB E3 43 70 42 43 70 42 E3 4E 70 42 4E 70 42 43 6C 53 6D 69 6C 65 79 73 20 76 65 72 6C 65 69 68 65 6E F5 6C 67 53 66 00 00 00 68 E5 E5 FF E3 53 6C F5 67 46 68 BE BC FB E3 E3 43 70 42 4E 63 23 47 69 62 20 65 69 6E 2C 20 5F 61 6E 20 77 65 6E 5F 20 75 6E 64 20 5F 77 65 6C 63 68 65 20 53 6D 69 6C 65 79 73 5F 20 64 75 20 5F 76 65 72 6C 65 69 68 65 6E 5F 20 6D F6 63 68 74 65 73 74 20 75 6E 64 20 66 FC 72 20 5F 77 69 65 20 6C 61 6E 67 65 5F 3A F5 73 02 F8 00 32 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 43 70 42 57 70 42 4E 63 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 56 65 72 6C 65 69 68 65 6E 20 61 6E 20 77 65 6E 3F 5F F5 73 01 04 00 1A 68 BE BC FB E3 57 6C 56 65 72 6C 65 69 68 65 6E 20 61 6E 20 28 4E 69 63 6B 29 3A F5 67 50 68 BE BC FB E3 43 66 F5 4D 66 00 00 00 68 FF FF FF E3 45 6C 20 F5 67 4D 68 BE BC FB E3 E3 43 70 42 4E 63 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 57 69 65 20 6C 61 6E 67 65 3F 5F F5 73 00 AA 00 1A 68 BE BC FB E3 57 6C 56 65 72 6C 65 69 68 64 61 75 65 72 3A 20 F5 67 50 68 BE BC FB E3 43 6F 66 00 00 00 68 FF FF FF 67 4C E3 31 20 4D 69 6E 75 74 65 F5 31 30 20 4D 69 6E 75 74 65 6E F5 33 30 20 4D 69 6E 75 74 65 6E F5 31 20 53 74 75 6E 64 65 F5 32 20 53 74 75 6E 64 65 6E F5 36 20 53 74 75 6E 64 65 6E F5 31 32 20 53 74 75 6E 64 65 6E F5 31 20 54 61 67 F5 32 20 54 61 67 65 F5 33 20 54 61 67 65 F5 31 20 57 6F 63 68 65 F5 32 20 57 6F 63 68 65 6E F5 E3 45 6C 20 F5 67 4D 68 BE BC FB E3 E3 45 70 42 43 70 42 4E 63 F5 73 01 4A 00 1A 68 BE BC FB E3 E3 E3 E3 53 70 42 4E 63 B0 3E 6C 61 79 6F 75 74 2F 74 72 61 64 65 5F 63 6F 64 65 73 5F 6C 69 6E 65 2E 2E 2E 68 5F 32 30 2E 70 6E 67 3C B0 23 B0 31 34 B0 B0 3E 62 75 6C 6C 65 74 32 2E 2E 2E 62 2E 70 6E 67 3C B0 20 20 5F 57 61 73 20 73 6F 6C 6C 20 76 65 72 6C 69 65 68 65 6E 20 77 65 72 64 65 6E 3F 5F F5 73 02 F8 00 32 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 43 63 B0 34 30 B0 20 B0 72 B0 B0 25 30 B0 B0 3E 7B 63 68 65 63 6B 62 6F 78 31 30 37 30 3C 3E 7D 63 68 65 63 6B 62 6F 78 3C B0 20 B0 3E 66 65 61 74 75 72 65 73 2F 67 69 66 74 62 6F 78 65 73 2F 67 69 66 74 74 69 63 6B 65 74 5F 70 6F 6B 65 72 2E 2E 2E 62 2E 6D 79 5F 39 2E 70 6E 67 3C B0 F5 73 02 F8 00 46 74 73 65 6E 64 62 61 63 6B F5 66 00 00 00 68 BE BC FB 69 2D F5 00 00 E3 E3 E3 E3 53 70 46 62 56 65 72 6C 65 69 68 65 6E F5 63 65 73 64 62 67 51 66 00 00 00 68 BE BC FB E3 62 53 63 68 6C 69 65 DF 65 6E F5 63 65 64 67 51 66 00 00 00 68 BE BC FB E3 E3 E3 E3";
     
         } 
      
           public static String tauschFensterakzept(Client client,String text,String nick,String[] smileyid) {
             boolean save = false;
      
         
         StringBuilder builder = new StringBuilder();
         
         builder.append("k\0Codes tauschenõscodeõtauschen2~"+nick+"~");
         builder.append(save ? 'T' : 'F');
         builder.append("õf\0\0\0");
         if (save) {
             builder.append("h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBCpBCc");
             builder.append("°15°#Der Smileytausch ist durch den Smiley \"Plüsch-Devil-Krösus\" geschützt. Bitte gib dein Schweizer Knuddelskonto-Passwort ein.õsÌ\0Kf\0\0\0h¾¼ûi-õ\0\0oãSpGBAFFlKontopasswort: õbgPf\0\0\0h¾¼ûãfõUf\0\0\0hÿÿÿc*");
             builder.append("gOãlõgFh¾¼ûãããSlõgHh¾¼ûããNpBNpBCl");
         } else {
             builder.append("h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBSlõgHh¾¼ûããNpBNpBCl");
         }

         builder.append("Codes tauschen");
         builder.append("õlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBNc"+text+"õs\0Pf\0\0\0h¾¼ûi-õ\0\0ãCpBWpBNl");
         
         builder.append("õgDh¾¼ûãCfõMf\0\0\0");
         
         builder.append("hÿÿÿãSlõgDh¾¼ûãClZusätzliche Knuddels: ");
         builder.append("õgPh¾¼ûãEfõMf\0\0\0");
         builder.append("hÿÿÿãSlõgDh¾¼ûãããSc");
         
         builder.append("°30° °r°");
         int counter = -1;

         for (int i = 0; i < smileyid.length; i++) {
             counter++;

             builder.append("°%");

             switch (counter + 1) {
                 case 1:
                     builder.append("0");
                     break;
                 case 2:
                     builder.append("18");
                     break;
                 case 3:
                     builder.append("37");
                     break;
                 case 4:
                     builder.append("56");
                     break;
                 case 5:
                     builder.append("75");
                     break;
             }

             builder.append("°");

             builder.append("°>{checkbox");
             builder.append(smileyid[i]);
             builder.append("<>}checkbox<° ");
            
               Usersmiley sm = Server.get().getUsersmiley(smileyid[i]);
             Smiley ss = Server.get().getSmiley(String.valueOf(sm.getSMID()));
                 builder.append(ss.getKCode());
             
             
             
             if (i == smileyid.length - 1) {
                 builder.append("°%00°");
                 break;
             }
             
             if ((i + 1) % 5 == 0) {
                 builder.append("°%00°");
                 builder.append("#°30° °r°");
                 counter = -1;
                 continue;
             }

         }

         builder.append("#õs \0×tsendbackõf\0\0\0h¾¼ûi-õ\0\0ãããSpFbTauschenõcesdbgQf\0\0\0h¾¼ûãbSchließenõcedgQf\0\0\0h¾¼ûãããã");
         return builder.toString();
     }
           
     public static String tauschFenster(Client client,String[] smileyid) {
         
     boolean save = false;
      
      
         
         
         StringBuilder builder = new StringBuilder();
         builder.append("k\0Codes tauschenõscodeõCodes tauschen~");
         builder.append(save ? 'T' : 'F');
         builder.append("õf\0\0\0");
         if (save) {
             builder.append("h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBCpBCc");
             builder.append("°15°#Der Smileytausch ist durch den Smiley \"Plüsch-Devil-Krösus\" geschützt. Bitte gib dein Schweizer Knuddelskonto-Passwort ein.õsÌ\0Kf\0\0\0h¾¼ûi-õ\0\0oãSpGBAFFlKontopasswort: õbgPf\0\0\0h¾¼ûãfõUf\0\0\0hÿÿÿc*");
             builder.append("gOãlõgFh¾¼ûãããSlõgHh¾¼ûããNpBNpBCl");
         } else {
             builder.append("h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBSlõgHh¾¼ûããNpBNpBCl");
         }

         builder.append("Codes tauschen");
         builder.append("õlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBNc#Gib den Nicknamen ein, mit dem du °R°_tauschen_°° willst.#Klicke anschließend auf alle Häkchen von den Smileys oder Codes, die du tauschen möchtest.õs\0Pf\0\0\0h¾¼ûi-õ\0\0ãCpBWpBNlõgDh¾¼ûãWlTauschen mit (Nick): õgPh¾¼ûãCfõMf\0\0\0hÿÿÿãSlõgDh¾¼ûããClõgDh¾¼ûãEpBNlõgDh¾¼ûãClZusätzliche Knuddels: õgPh¾¼ûãEfõMf\0\0\0hÿÿÿãSlõgDh¾¼ûãããSc");
         
         builder.append("°30° °r°");
         int counter = -1;

         for (int i = 0; i < smileyid.length; i++) {
             counter++;

             builder.append("°%");

             switch (counter + 1) {
                 case 1:
                     builder.append("0");
                     break;
                 case 2:
                     builder.append("18");
                     break;
                 case 3:
                     builder.append("37");
                     break;
                 case 4:
                     builder.append("56");
                     break;
                 case 5:
                     builder.append("75");
                     break;
             }

             builder.append("°");

             builder.append("°>{checkbox");
             builder.append(smileyid[i]);
             builder.append("<>}checkbox<° ");
            
             Usersmiley sm = Server.get().getUsersmiley(smileyid[i]);
             Smiley ss = Server.get().getSmiley(String.valueOf(sm.getSMID()));
                 builder.append(ss.getKCode());
             
             
             if (i == smileyid.length - 1) {
                 builder.append("°%00°");
                 break;
             }
             
             if ((i + 1) % 5 == 0) {
                 builder.append("°%00°");
                 builder.append("#°30° °r°");
                 counter = -1;
                 continue;
             }

         }

         builder.append("#õs \0×tsendbackõf\0\0\0h¾¼ûi-õ\0\0ãããSpFbTauschenõcesdbgQf\0\0\0h¾¼ûãbSchließenõcedgQf\0\0\0h¾¼ûãããã");
         return builder.toString();
     }

	
	public static String createCodeWindow() {
        Popup popup = new Popup("Code Menu", "Code Menu", "", 0, 0);
        popup.setButtonFontSize(15);
        popup.setHeaderFontSize(17);
        
        Panel panel1 = new Panel();
        Button buttonMessage = new Button("         Codes aktivieren         ");
        buttonMessage.setStyled(true);
        buttonMessage.setCommand("/code activate");
        buttonMessage.disableClose();
        panel1.addComponent(buttonMessage);
        popup.addPanel(panel1);
        Panel panel4 = new Panel();
        Button button = new Button("    Eigene Codes ansehen   ");
        button.setStyled(true);
        button.setCommand("/code ?");
        buttonMessage.setStyled(true);
        button.disableClose();
        panel4.addComponent(button);
        popup.addPanel(panel4);
        Panel panel8 = new Panel();
        Button button8 = new Button("    Letzte Tausch-Aktionen   ");
        buttonMessage.setStyled(true);
        button8.setCommand("/code swapactions");
        button8.setStyled(true);
        button8.disableClose();
        panel8.addComponent(button8);
        popup.addPanel(panel8);
        Panel panel6 = new Panel();
        Button button6 = new Button("         Codes tauschen         ");
        button6.setCommand("/code swap");
        button6.setStyled(true);
        button6.disableClose();
        panel6.addComponent(button6);
        popup.addPanel(panel6);
        Panel panel0 = new Panel();
        Button button0 = new Button("      Codes verschenken       ");
        button0.setStyled(true);
        button0.setCommand("/code giveaway");
        button0.disableClose();
        panel0.addComponent(button0);
        popup.addPanel(panel0);
        Panel panel5 = new Panel();
        Button butto = new Button("            Smiley-Shop            ");
        butto.setCommand("/shop");
        butto.setStyled(true);
        butto.disableClose();
        panel5.addComponent(butto);
        popup.addPanel(panel5);
        Panel panel2 = new Panel();
        panel2.addComponent(new Label(" "));
        popup.addPanel(panel2);
        Panel panel3 = new Panel();
        Button button11 = new Button("             Schließen               ");
        button11.setStyled(true);
        panel3.addComponent(button11);
        popup.addPanel(panel3);

        return popup.toString();
	}
	
	public static String createSignatureWindow(Client client) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("k\0Einstellungen - Briefkastenõspostõmowõf47VhÜÜþãEl  õcgPhÜÜþãWl  õcgPhÜÜþãSl õcgPhÜÜþãNl õcgPhÜÜþãCpBNpBNpBNlEinstellungen deines BriefkastensõgMfÜÜþh47VãClõgBhÜÜþããWpGABCCxs");
        builder.append(client.getMessageSound() == 1 ? "t":"f");
        builder.append("lSound bei neuer Nachrichtõf47VhÜÜþgMããCpGABCClõgMf47VhÜÜþãlõgMf47VhÜÜþãlõgMf47VhÜÜþãããCpBNlSignatur unter deinen NachrichtenõgPf\0\0\0hÜÜþãCt");
        builder.append(client.getSignatur());
        builder.append("õIB~signatureõef47VhÿÿÿgOãSpBNl            õgPf47VhÜÜþãWb     Speichern     õcesdbãEb     Abbrechen     õcedbããããã");
       
        return builder.toString();
	}
	
	public static String createPollWindow(String channel) {
        Popup popup = new Popup("Umfrage erstellen", "Umfrage erstellen", "Gib unten eine Frage und mindestens zwei verschiedene Antwortmöglichkeiten - durch Semikolons getrennt - ein. Jeder Chatter hat dann die Möglichkeit die Umfrage innerhalb von einer Minute zu beantworten und dabei 25 Knuddels abzusahnen, welche unter allen Teilnehmern verlost werden.", 400, 200);

        Panel panel1 = new Panel();
        panel1.addComponent(new Label("Frage:"));
        panel1.addComponent(new TextField(15, ""));
        panel1.addComponent(new Label("Antworten:"));
        panel1.addComponent(new TextField(15, ""));
        popup.addPanel(panel1);

        Panel panel2 = new Panel();
        Button button = new Button("Erstellen");
        button.setStyled(true);
        button.enableAction();
        panel2.addComponent(button);
        popup.addPanel(panel2);
        
        popup.setOpcode(ReceiveOpcode.POLL.getValue(), String.format("new~%s", channel));
        return popup.toString();
	}
	
	public static String createAdmincallWindow (String beschuldigter, String verstoß, String begründung) {
		String title = "Beschwerde über andere Chat-Teilnehmer";
        Popup popup = new Popup(title, title, "", 0, 0);
        
        Panel panel1 = new Panel();
        panel1.addComponent(new Label(String.format("Beschuldigter:")));
        panel1.addComponent(new TextField(30, beschuldigter));
        popup.addPanel(panel1);
        Panel panel2 = new Panel();
        String[] lala = {"- Auswählen -                                            ", "Spamming / Werbung / Knigge", "Botnutzung / Faken von Nachrichten", "Schwere Beleidigung / Bedrohung", "Foto-Verstoß", "Sexuelle Belästigung", "Verbotene Äußerung", "Profil-Verstoß", "HP-Verstoß", "Nickname unzulässig", "Spiel-Verstoß / Cheating", "Sonstiger Verstoß"};
        panel2.addComponent(new Label(String.format("Verstoß:          ")));
        panel2.addComponent(new Choice(lala, verstoß.trim()));
        popup.addPanel(panel2);
        Panel panel3 = new Panel();
        panel3.addComponent(new Label("Begründung:  "));
        panel3.addComponent(new TextArea(10,30, begründung));
        popup.addPanel(panel3);
        Panel panel5 = new Panel();
        Button button = new Button("Notruf absenden");
        button.enableAction();
        button.setStyled(true);
        panel5.addComponent(button);
        Button button2 = new Button("Abbrechen");
        button2.setStyled(true);
        panel5.addComponent(button2);
        popup.addPanel(panel5);
        
        Random random = new Random();
    	int randomInt = random.nextInt(9999999);

        popup.setOpcode(ReceiveOpcode.ADMINCALL.getValue(), String.valueOf(randomInt+1));
        return popup.toString();
	}
	
	public static String createEmailWindow(String an, String text) {
		Popup popup = new Popup("E-Mail versenden", "E-Mail versenden", "", 0, 0);
		popup.setButtonFontSize(18);
        
        Panel panel1 = new Panel();
        panel1.addComponent(new Label(String.format("An (Nick):")));
        panel1.addComponent(new TextField(48, an));
        popup.addPanel(panel1);
        Panel panel3 = new Panel();
        panel3.addComponent(new Label("Nachricht:                                                                                            "));
        popup.addPanel(panel3);
        Panel panel4 = new Panel();
        panel4.addComponent(new TextArea(15,60, text));
        popup.addPanel(panel4);
        Panel panel5 = new Panel();
        Button button = new Button("Absenden");
        button.setStyled(true);
        button.enableAction();
        panel5.addComponent(button);
        Button button2 = new Button("Schließen");
        button2.setStyled(true);
        panel5.addComponent(button2);
        popup.addPanel(panel5);

        popup.setOpcode(ReceiveOpcode.EMAIL.getValue(), "email");
        return popup.toString();
	}
	
	public static String boldNames(List<String> names) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.BOLD_NAMES.getValue());

        for(String name : names) {
        	buffer.writeByte(0x00);
        	buffer.writeString(name.toLowerCase());
        }

        return buffer.toString();
    }

    public static String kontoPw(String name) {
		return "k\0Schweizer Knuddelskonto - Kontopasswort setzenõsknuddelsaõ"+name+"õf\0\0\0h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClKontopasswort setzenõblgSf\0\0\0hååÿãSlõgFh¾¼ûããCc°15°Bevor Du Knuddels auf Deinem Knuddelskonto in der Schweiz bunkern kannst, musst Du ein Kontopasswort festlegen.##_Beachte:_##  °>py_g.gif<%05°_Merke_ Dir Dein Kontopasswort gut!°%00°#  °>py_g.gif<%05°Das Kontopasswort _wird zum Codes/Smileys tauschen_ benötigt.°%00°#  °>py_g.gif<%05°Bei Vergessen/Verlust ist eine Neusetzung durch Admins oder das System etc. _nicht möglich_.õsô\0¾f\0\0\0h¾¼ûãSpBNpBWpGABCClKontopasswort:   õbgPf\0\0\0h¾¼ûãlKontopasswort wdh.:   õbgPf\0\0\0h¾¼ûããCpGABCCfõMf\0\0\0hÿÿÿc*ãfõMf\0\0\0hÿÿÿc*ãããCl õgMh¾¼ûãSpFbKontopasswort speichernõsdbgQf\0\0\0h¾¼ûãb  Abbrechen  õdbgQf\0\0\0h¾¼ûããããã";
	}
	
	public static String kontoPwNew(String name) {
		return "k\0Schweizer Knuddelskonto - Kontopasswort setzenõsknuddelsaõ"+name+"õf\0\0\0h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClKontopasswort setzenõblgSf\0\0\0hååÿãSlõgFh¾¼ûããCc°15°Gib unten zuerst Dein altes Kontopasswort an, um ein neues zu setzen.##_Beachte:_##  °>py_g.gif<%05°_Merke_ Dir Dein Kontopasswort gut!°%00°#  °>py_g.gif<%05°Das Kontopasswort _wird zum Codes/Smileys tauschen_ benötigt.°%00°#  °>py_g.gif<%05°Bei Vergessen/Verlust ist eine Neusetzung durch Admins oder das System etc. _nicht möglich_.õsô\0¾f\0\0\0h¾¼ûãSpBNpBWpGABCClAltes Kontopasswort:   õbgPf\0\0\0h¾¼ûãl õgFh¾¼ûãlNeues Kontopasswort:   õbgPf\0\0\0h¾¼ûãlNeues Kontopasswort wdh.:   õbgPf\0\0\0h¾¼ûããCpGABCCfõMf\0\0\0hÿÿÿc*ãl õgFh¾¼ûãfõMf\0\0\0hÿÿÿc*ãfõMf\0\0\0hÿÿÿc*ãããCl õgMh¾¼ûãSpFbKontopasswort setzenõsdbgQf\0\0\0h¾¼ûãb  Abbrechen  õdbgQf\0\0\0h¾¼ûããããã";
	}
	
	public static String konto(String name, int knuddels) {
              Long time = System.currentTimeMillis(); 
              Date da = new Date(time);
              SimpleDateFormat uhrzeits = new SimpleDateFormat("dd");
                SimpleDateFormat uhrzeits2 = new SimpleDateFormat("MM");
                  SimpleDateFormat uhrzeits3 = new SimpleDateFormat("yyyy");
                String tag = uhrzeits.format(da);
               int monat = Integer.parseInt(uhrzeits2.format(da));
               int jahr = Integer.parseInt(uhrzeits3.format(da));
                  monat++;
              if (monat == 12) {
                  monat = 1;
                  jahr++;
              }
           
              // jetzt wieder denken -.-*
             double pw = (double)knuddels*10/100;
             
            int knuddelsneu = knuddels+(int)pw;
            String monatsstring = monat+"";
            if (String.valueOf(monat).length() == 1) {
                monatsstring = "0"+monat;
            }
            String datum = "01."+monatsstring+"."+jahr;
		return "k\0Schweizer Knuddelskontoõsknuddelsaõ"+name+"õf\0\0\0h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBNpBClSchweizer KnuddelskontoõblgSf\0\0\0hååÿãSlõgIh¾¼ûããCc°15°  °>py_g.gif<%05°Aktuell maximal _verfügbarer Knuddels-Betrag: "+knuddels+"_°%00°#  °>py_g.gif<%05°Neue Knuddels durch _Vermehrung zum "+datum+":  "+knuddelsneu+"_ (10% pro Jahr)_°%00°##°-°õs:\0nf\0\0\0h¾¼ûãSpBWpBWpGABCClKnuddels:   õbgPf\0\0\0h¾¼ûãlAktion:   õbgPf\0\0\0h¾¼ûãlKontopasswort:   õbgPf\0\0\0h¾¼ûãl õgFh¾¼ûãl õgFh¾¼ûããCpGABCCfõMf\0\0\0hÿÿÿãogOãAktion wählen... õEinzahlenõAbhebenõãfõMf\0\0\0hÿÿÿc*ãl õgFh¾¼ûãpBWb  OK  õsdbgPf\0\0\0h¾¼ûãCl   õgIh¾¼ûãEb  Abbrechen  õdbgPf\0\0\0h¾¼ûããããCpGABCCl õgFh¾¼ûãl õgFh¾¼ûãpBWl  õgFh¾¼ûãClKontopasswort ändernõbgPf\0\0ÿh¾¼ûsKontopasswort ändernõuããl õgFh¾¼ûãl õgFh¾¼ûããããã";
	}
    
	public static String updateBillardFunctions(String channel) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.UPDATE_BILLARD_FUNCTIONS.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString("l");
        buffer.writeByte(0x00);
        buffer.writeString("/billard j:"); // join
        buffer.writeByte(0x00);
        buffer.writeString("L");
        buffer.writeByte(0x00);
        buffer.writeString("/billard j:"); // join
        buffer.writeByte(0x00);
        buffer.writeString("r");
        buffer.writeByte(0x00);
        buffer.writeString("/billard i:"); // info
        buffer.writeByte(0x00);
        buffer.writeString("R");
        buffer.writeByte(0x00);
        buffer.writeString("/billard i:"); // info
        buffer.writeByte(0x00);
        buffer.writeString("m");
        buffer.writeByte(0x00);
        buffer.writeString("/billard j:"); // join

        return buffer.toString();
    }

    public static String billardListAddNick(Channel channel, Client nick) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.BILLARD_LIST.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString("l");
        buffer.writeByte(0x00);
        buffer.writeString(channel.getName());
        buffer.writeByte(0x00);
        buffer.writeString(nick.getName());
        buffer.writeByte(0x00);
        buffer.writeString("p");
        buffer.writeByte(0x00);
        buffer.writeString(channel.getStyle().getRankColor(nick));
        buffer.writeByte(0x00);
        buffer.writeString("-");

        return buffer.toString();
    }
    
    
     public static String exit(String channel) { // vllt an der falschen stelle hier ? die stelle is egal
        PacketBuilder buffer = new PacketBuilder(SendOpcode.EXIT.getValue());
        buffer.writeByte(0x00);
        buffer.writeByte(0x00);
        buffer.writeString(channel, true, false);
        return buffer.toString();
    }

    public static String moduleClass(String channel, String module) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.MODULECLASS.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString("0");
        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(module);

        return buffer.toString();
    }
    


    public static String billardListRemoveNick(String channel, String nick) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.BILLARD_LIST.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString("w");
        buffer.writeByte(0x00);
        buffer.writeString(nick);
        buffer.writeByte(0x00);
        buffer.writeString(channel);

        return buffer.toString();
    }
    
    public static String billardList(String channel, String text) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.BILLARD_LIST.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString("u");
        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(text); // Der Text, der in dem Button steht
        buffer.writeByte(0x00);
        buffer.writeString("p");
        buffer.writeByte(0x00);
        buffer.writeString("-");
        buffer.writeByte(0x00);
        buffer.writeString("-");

        return buffer.toString();
    }
    
    public static String closeBillardList(String channel) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.CLOSE_BILLARD_LIST.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString(channel);

        return buffer.toString();
    }
	
	
    public static String votePopup(String ownnickname, String election, List<String> params) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.VOTE.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString(election);
        buffer.writeByte(0x00);
        buffer.writeByte('5'); //positive Stimmen insgesamt
        buffer.writeByte(0x00);
        buffer.writeByte('3'); //negative Stimmen insgesamt
        buffer.writeByte(0x00);
        buffer.writeByte('2'); //maximale positive Stimmen pro Nick
        buffer.writeByte(0x00);
        buffer.writeByte('2'); //negative positive Stimmen pro Nick
        buffer.writeByte(0x00);
        
        int is = 0;
          for (int i = 0; i < params.size(); i++) {
            String temp = params.get(i);
            String[] splitted = temp.split("~");
            String nick = splitted[0];
            String info = splitted[1];
if (!nick.equals(ownnickname)) {
    is++;
}}
       
        buffer.writeString(String.valueOf(is)); //Wahlteilnehmer
        
        for (int i = 0; i < params.size(); i++) {
            String temp = params.get(i);
            String[] splitted = temp.split("~");
            String nick = splitted[0];
            String info = splitted[1];
if (!nick.equals(ownnickname)) {
            buffer.writeByte(0x00);
            buffer.writeByte(0x00);
            buffer.writeString(nick);
            buffer.writeByte(0x00);
            buffer.writeByte(0x00);
            buffer.writeString(info);
            buffer.writeByte(0x00);
            buffer.writeByte(0x00);
        }}
        
        return buffer.toString();
    }
    
    public static String effect(String sender, String effect) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.EFFECT.getValue());
        buffer.writeByte(0x00);
        buffer.writeString("a");
        buffer.writeByte(0x00);
        buffer.writeString(sender);
        buffer.writeByte(0x00);
        buffer.writeString(effect);

        return buffer.toString();
    }

    public static String showPrefixIcons(String channelName, List<String> params,boolean sorttotop) {
      PacketBuilder buffer = new PacketBuilder(SendOpcode.MODULE.getValue());

           
        buffer.writeByte(0);
    
        buffer.writeShort(Server.get().getModuleTree().getIndex("SHOW_PREFIXICONS"));  //.asq        
        buffer.writeString(channelName, true, false); // Channelname
        buffer.writeByte(11);
 
        for (int i = 0; i < params.size(); i++) {
            String[] temp = params.get(i).split("~");
            String nick = temp[0];

            buffer.writeString(nick, true, false); // Nick
 
            for (int j = 1; j < temp.length; j++) {
                String icon = temp[j];
                buffer.writeByte(11);
                buffer.writeString(icon, true, false); // Icon
            }
 
            if (i < params.size() - 1) {
                buffer.writeByte(12);
                buffer.writeByte(11);
            }
        }
        
        buffer.writeByte(12);
        buffer.writeByte(12);
        if (sorttotop) {
             buffer.writeByte(1);
        } else {
        buffer.writeByte(0);
        }
        return buffer.toString();
    }

    public static String removePrefixIcons(String channelName) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.MODULE.getValue());

       
        buffer.writeByte(0);
      //buffer.writeByte(74);   // Opcode für Client afi
        buffer.writeShort(Server.get().getModuleTree().getIndex("REMOVE_ALL_PREFIXICONS"));  // .asq
        buffer.writeString(channelName, true, false);

        return buffer.toString();
    }
    
    public static String removeEffect(String sender, String effect) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.EFFECT.getValue());
        buffer.writeByte(0x00);
        buffer.writeString("r");
        buffer.writeByte(0x00);
        buffer.writeString(sender);
        buffer.writeByte(0x00);
        buffer.writeString(effect);

        return buffer.toString();
    }
    
    public static String tauschFensterZwei(String nickname, int Smileyzahl, String smileycodes, String knuddelsHaben, List<String> smileys, String ok) {
    	StringBuilder builder = new StringBuilder();
    	builder.append("k\0Tausch-Fenster öffnenõscodeõswapped~" + ok + "õf\0\0\0h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBãNpBNpBClTausch-Fenster öffnenõlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBCpBWc#_");
    	builder.append("°>" +  nickname + "|/serverpp \"|/w \"<°");
    	builder.append("_ bietet dir folgendes zum _°R°Tausch°°_ an:##°>bullet2...b.png<° _");
    	
    	if(Smileyzahl == 1) {
    		builder.append("1 Smiley");
    	} else {
    		builder.append(Smileyzahl + " Smileys");
    	}
    	
    	builder.append("_ (");
    	builder.append(smileycodes);
    	builder.append(")õs®\0Zf\0\0\0h¾¼ûi-õ\0\0ãCc õs\0\n\0\nh¾¼ûãEpBNcõsJ\0&h¾¼ûãCpBNc°14°°>bullet2...b.png<°  _Wieviele Knuddels gibst du?_ °12°(du hast ");
    	builder.append(knuddelsHaben);
    	builder.append(")õsJ\0h¾¼ûãWlKnuddels: õgPh¾¼ûãCfõFf\0\0\0hÿÿÿãEcõs\0¥\0\nh¾¼ûããããSpBNc°>layout/trade_codes_line...h_20.png<°#°14°°>bullet2...b.png<°  _Welchen Smiley bietest du zum Tausch dafür an?_ °12°(Du musst _genau ");

    	if(Smileyzahl == 1) {
    		builder.append("1 Smiley");
    	} else {
    		builder.append(Smileyzahl + " Smileys");
    	}
    	
    	builder.append("_ auswählen.)#");
    	
    	int counter = -1;

        for (int i = 0; i < smileys.size(); i++) {
            counter++;

            builder.append("°%");

            switch (counter + 1) {
                case 1:
                    builder.append("0");
                    break;
                case 2:
                    builder.append("18");
                    break;
                case 3:
                    builder.append("37");
                    break;
                case 4:
                    builder.append("56");
                    break;
                case 5:
                    builder.append("75");
                    break;
            }

            builder.append("°");

            builder.append("°>{checkbox");
            builder.append(i + 1);
            builder.append("<>}checkbox<° ");
            
            if (!smileys.get(i).startsWith("°>")) {
                builder.append("°>");
                builder.append(smileys.get(i));
                builder.append("<°");
            } else {
                builder.append(smileys.get(i));
            }
            
            if (i == smileys.size() - 1) {
                builder.append("°%00°");
                break;
            }
            
            if ((i + 1) % 5 == 0) {
                builder.append("°%00°");
                builder.append("#°30° °r°");
                counter = -1;
                continue;
           }

       }
    	
    	builder.append("õsø\0ðtsendbackõf\0\0\0h¾¼ûi-õ\0\0ããããSpFbTauschenõcesdbgQf\0\0\0h¾¼ûãbSchließenõcedgQf\0\0\0h¾¼ûãããã");
    	return builder.toString();
    }
    
  
    public static String tauschFenster(boolean save, List<String> smileys) {
    	StringBuilder builder = new StringBuilder();
        builder.append("k\0Codes tauschenõscodeõCodes tauschen~");
        builder.append(save ? 'T' : 'F');
        builder.append("õf\0\0\0");
        if (save) {
            builder.append("h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBCpBCc");
            builder.append("°15°#Der Smileytausch ist durch den Smiley \"Plüsch-Devil-Krösus\" geschützt. Bitte gib dein Schweizer Knuddelskonto-Passwort ein.õsÌ\0Kf\0\0\0h¾¼ûi-õ\0\0oãSpGBAFFlKontopasswort: õbgPf\0\0\0h¾¼ûãfõUf\0\0\0hÿÿÿc*");
            builder.append("gOãlõgFh¾¼ûãããSlõgHh¾¼ûããNpBNpBCl");
        } else {
            builder.append("h¾¼ûãWl  õgMh¾¼ûãEl  õgMh¾¼ûãNlõgFh¾¼ûãSlõgFh¾¼ûãCpBCpBSlõgHh¾¼ûããNpBNpBCl");
        }

        builder.append("Codes tauschen");
        builder.append("õlgSf\0\0\0hååÿãSlõgFh¾¼ûããCpBNc#Gib den Nicknamen, mit dem du tauschen willst, ein. Klicke anschließend auf alle Häkchen von den Codes, die du tauschen möchtest.õs\0Pf\0\0\0h¾¼ûi-õ\0\0ãCpBWpBNlõgDh¾¼ûãWlTauschen mit (Nick): õgPh¾¼ûãCfõMf\0\0\0hÿÿÿãSlõgDh¾¼ûããClõgDh¾¼ûãEpBNlõgDh¾¼ûãClZusätzliche Knuddels: õgPh¾¼ûãEfõMf\0\0\0hÿÿÿãSlõgDh¾¼ûãããSc");
        
        builder.append("°30° °r°");
        int counter = -1;

        for (int i = 0; i < smileys.size(); i++) {
            counter++;

            builder.append("°%");

            switch (counter + 1) {
                case 1:
                    builder.append("0");
                    break;
                case 2:
                    builder.append("18");
                    break;
                case 3:
                    builder.append("37");
                    break;
                case 4:
                    builder.append("56");
                    break;
                case 5:
                    builder.append("75");
                    break;
            }

            builder.append("°");

            builder.append("°>{checkbox");
            builder.append(i + 1);
            builder.append("<>}checkbox<° ");
            
            if (!smileys.get(i).startsWith("°>")) {
                builder.append("°>");
                builder.append(smileys.get(i));
                builder.append("<°");
            } else {
                builder.append(smileys.get(i));
            }
            
            if (i == smileys.size() - 1) {
                builder.append("°%00°");
                break;
            }
            
            if ((i + 1) % 5 == 0) {
                builder.append("°%00°");
                builder.append("#°30° °r°");
                counter = -1;
                continue;
           }

       }

       builder.append("#õs \0×tsendbackõf\0\0\0h¾¼ûi-õ\0\0ãããSpFbTauschenõcesdbgQf\0\0\0h¾¼ûãbSchließenõcedgQf\0\0\0h¾¼ûãããã");
       return builder.toString();
   }

    public static String createEditWindow(Client client) {   
        StringBuilder channels = new StringBuilder();
            
        boolean havelc = false;
        if (!client.getLC().isEmpty()) {
            havelc = true;
            channels.append(client.getLC());
        }
        
        for (Channel name : Server.get().getChannels()) {
        	if(name.isVisible()) {
                    if (!havelc || havelc && !name.getName().equals(client.getLC())) {
        		channels.append("õ").append(name.getName());          
        	}}
        }
        
        char relationChar = 'A';
        
        if(client.getVergeben().equals("Single")) {
            relationChar = 'B';
        } else if(client.getVergeben().equals("Glücklicher Single")) {
            relationChar = 'C';
        } else if(client.getVergeben().equals("Verliebt")) {
            relationChar = 'D';
        } else if(client.getVergeben().equals("Unglücklich Verliebt")) {
            relationChar = 'E';
        } else if(client.getVergeben().equals("Vergeben")) {
            relationChar = 'F';
        } else if(client.getVergeben().equals("Glücklich Vergeben")) {
            relationChar = 'G';
        } else if(client.getVergeben().equals("Verlobt")) {
            relationChar = 'H';
        } else if(client.getVergeben().equals("Verheiratet")) {
            relationChar = 'I';
        } else if(client.getVergeben().equals("Glücklich Verheiratet")) {
            relationChar = 'J';
        } else if(client.getVergeben().equals("Geschieden")) {
            relationChar = 'K';
        } else if(client.getVergeben().equals("Glücklich Geschieden")) {
            relationChar = 'L';
        } else if(client.getVergeben().equals("Beziehungsgestört")) {
            relationChar = 'M';
        } else if(client.getVergeben().equals("Ich geh ins Kloster")) {
            relationChar = 'N';
        } else if(client.getVergeben().equals("offene Beziehung")) {
            relationChar = 'O';
        }
        
        char searchingGender = 'A';
        
        if(client.getSearchGender().equals("männl.")) {
            searchingGender = 'B';
        } else if(client.getSearchGender().equals("weibl.")) {
            searchingGender = 'C';
        }
        
        char searchingForBlinddate = 'A';
        
        if(client.getSearchMotiv().equals("Partnerschaft")) {
            searchingForBlinddate = 'B';
        } else if(client.getSearchMotiv().equals("Abenteuer")) {
            searchingForBlinddate = 'C';
        } else if(client.getSearchMotiv().equals("gutes Gespräcb")) {
            searchingForBlinddate = 'D';
        }
        
        char searchingForDistance = 'A';
        
        if(client.getSearchEntfernung().equals("20 km")) {
            searchingForDistance = 'B';
        } else if(client.getSearchEntfernung().equals("50 km")) {
            searchingForDistance = 'C';
        } else if(client.getSearchEntfernung().equals("150 km")) {
            searchingForDistance = 'D';
        } else if(client.getSearchEntfernung().equals("300 km")) {
            searchingForDistance = 'E';
        } else if(client.getSearchEntfernung().equals("egal")) {
            searchingForDistance = 'F';
        } 
        
        StringBuilder builder = new StringBuilder();

        builder.append("k\0Editõsgõ");
        builder.append(client.getName()); // Nickname
        builder.append('|');
        builder.append(client.getID()); // damit wird später gecheckt ob der Nick auch eingeloggt ist, Beispiel: 100101
        builder.append("õp\0ð\0\0fUhUãWl  õgMhUãEl  õgMhUãNlõgFhUãCpBWpBNpBNpBNpBNpBNl");
        builder.append("PflichtangabenõgMfUhÈÈúãClõgBhUããWpGABCClPasswortõgMfÈÈúhUsPasswortõãlNeues PasswortõgMfÈÈúhUsNeues PasswortõãlNeues Passwort (2x) õgMfÈÈúhUsNeues Passwort (2x) õãlEmailõgMfÈÈúhUsEmailõãlE-Mail verifiziert?õgMfÈÈúhUsE-Mail verifiziert?õããCpGABCCfõBhÿÿÿc*ãfõBhÿÿÿc*ãfõBhÿÿÿc*ãf");
        builder.append(client.getEmail()); // Email
        builder.append("õBhÿÿÿã");
        
        if(client.getEmailVerify() < 2) {
            builder.append("b");
            builder.append(client.getEmailVerify()==0?"E-Mail verifizieren":"Verifizierung läuft...");
            builder.append("õo");
        } else {
        	builder.append("l");
        	builder.append("Verifiziert!"); // Verifiziert? (Status)
        	builder.append("õc");
        }
        
        builder.append("gMfÈÈúhUããããCl õgIhUããCpBNpBNpBNlPrivatsphäreõgMfUhÈÈúãClõgBhUããCpGABCCxs");
        builder.append(client.getShowEmail() == 1 ? "t" : "f"); // Email anzeigen (t für checked, f für unchecked)
        builder.append("lE-Mail anzeigenõfÈÈúgMãxs");
        builder.append(client.getEmails() == 1 ? "t" : "f"); // Empfang von /email (t für checked, f für unchecked)
        builder.append("lEmpfang von /emailõfÈÈúgMãxs");
        builder.append(client.getShowBirthday() == 1 ? "t" : "f"); // Geburtsdatum anzeigen (s.o.)
        builder.append("lGeburtsdatum anzeigenõfÈÈúgMãxs");
        builder.append(client.getShowZodiac() == 1 ? "t" : "f"); // Sternzeichen anzeigen
        builder.append("lSternzeichen anzeigenõfÈÈúgMãxs");
        builder.append(client.getSearchActivate() == 1 ? "t" : "f"); // Suche aktiviert
        builder.append("lSuche aktiviertõfÈÈúgMãããCl õgIhUããSpBNpBNl");
        builder.append("Suche & Blinddate (Du suchst ...)õgMfUhÈÈúãClõgBhUãSpGBAAApBWlNicks werden mit õgMfÈÈúhUãCpBWcõs\0\0hUipics/1.gifõ\0oãCl angezeigt.õgMfÈÈúhUãããããWpGABCClGeschlechtõgMfÈÈúhUsGeschlechtõãlAlter      von: õgMfÈÈúhUsAlter      von: õãlMotivõgMfÈÈúhUsMotivõãlEntfernung bis õgMfÈÈúhUsEntfernung bis õããCpGABCCo");
        builder.append("c");
        builder.append(searchingGender); // A = 1. Eintrag (hier: nichts), B = 2. Eintrag (hier: männl.) ...
        builder.append("hÿÿÿã");
        builder.append(" õmännl.õweibl.õãpBWohÿÿÿã");
        builder.append(client.getSearchAgeFrom()==0?"":client.getSearchAgeFrom()); // Value (Alter von)
        builder.append("õ14õ15õ16õ17õ18õ19õ20õ21õ22õ23õ24õ25õ26õ28õ30õ32õ34õ36õ40õ45õ50õ55õ60õ70õ100");
        builder.append("õãCl     bis     õcgMfÈÈúhUãEohÿÿÿã");
        builder.append(client.getSearchAgeUntil()==0?"":client.getSearchAgeUntil()); // Value (Alter bis)
        builder.append("õ14õ15õ16õ17õ18õ19õ20õ21õ22õ23õ24õ25õ26õ28õ30õ32õ34õ36õ40õ45õ50õ55õ60õ70õ100");
        builder.append("õãão");
        builder.append("c");
        builder.append(searchingForBlinddate); // A = 1. Eintrag (empty), B = 2. Eintrag (Partnerschaft), C = 3. Eintrag (Abenteuer) etc.
        builder.append("hÿÿÿã õPartnerschaftõAbenteuerõgutes Gesprächõão");
        builder.append("c");
        builder.append(searchingForDistance); // Entfernung bis (A = 1. Eintrag, empty, C = 3. Eintrag, 50 km usw.)
        builder.append("hÿÿÿã õ20 kmõ50 kmõ150 kmõ300 kmõegalõããããCl  õgMhUãEpBNpBNpBNl");
        builder.append("Persönliches (freiwillig)õgMfUhÈÈúãClõgBhUããWpGABCClAlterõgMfÈÈúhUsAlterõãlDein GeschlechtõgMfÈÈúhUsDein GeschlechtõãlVergebenõgMfÈÈúhUsVergebenõãlStadtõgMfÈÈúhUsStadtõãlLandõgMfÈÈúhUsLandõãlHobbysõgMfÈÈúhUsHobbysõãlMottoõgMfÈÈúhUsMottoõãlRealnameõgMfÈÈúhUsRealnameõãlGeburtstagõgMfÈÈúhUsGeburtstagõãlLand / Postleitzahl (nie angezeigt) õgMfÈÈúhUsLand / Postleitzahl (nie angezeigt) õãlJobõgMfÈÈúhUsJobõãlSpitznamen (Komma getrennt)õgMfÈÈúhUsSpitznamen (Komma getrennt)õãl");
             
        if (client.getRank() > 1) {
            builder.append("LieblingschannelõgMfÈÈúhUsLieblingschannelõãl");
        }
             
        builder.append("Eigener ChannelõgMfÈÈúhUsEigener Channelõãl");
        builder.append("õgMfÈÈúhUããCpGABCCf");
        builder.append(client.getAge() > 0 ? client.getAge() : ""); // Alter Value
           
        if (client.getVerify() == 1) {
            builder.append("õBefhÀÀÀãoc");   
        } else {
            builder.append("õBhÿÿÿãoc");
        }
        
        char genderValueChar = 'A';
            
        if (client.getGender() == 1) {
            genderValueChar = 'B';
        } else if (client.getGender() == 2) {
            genderValueChar = 'C';
        } else {
            genderValueChar = 'A';
        }
            
        builder.append(genderValueChar); // Value Gender, A = empty, B = männl., C = weibl., ...
        builder.append("hÿÿÿã ");
        builder.append("õmännl.õweibl.õ");
        builder.append("ãoc");
        builder.append(relationChar); // A = empty, B = Single, ...
        builder.append("hÿÿÿã ");
        builder.append("õauf der Sucheõsingleõglücklicher Singleõverliebtõglücklich verliebtõunglücklich verliebtõvergebenõglücklich vergebenõIm 7. Himmelõverlobtõverheiratetõfrisch getrenntõgetrennt lebendõglücklich verheiratetõgeschiedenõglücklich geschiedenõalleinerziehendõbeziehungsgestörtõich geh ins Klosterõoffene Beziehungõfür alles offenões ist kompliziertõin gute Hände abzugebenõich mag Kekseõjackpotõpflegefallõflexibelõverwirrtõverwitwetõsexbeziehungõfamilienzerstörer"); // Auswahl Beziehungsstatus
        builder.append("õãf");
        builder.append(client.getStadt()); // Stadt
        builder.append("õBhÿÿÿãf");
        builder.append(client.getLand()); // Land
        builder.append("õBhÿÿÿãf");
        builder.append(client.getHobbys()); // Hobbies
        builder.append("õBhÿÿÿãf");
        builder.append(client.getMotto()); // Motto
        builder.append("õBhÿÿÿãf");
        builder.append(client.getRealName()); // Realname  
        builder.append("õBhÿÿÿãf");
        builder.append(client.getBirthday()); // Geburtsdatum
            
        if (client.getVerify() == 1) {
            builder.append("õBefhÀÀÀãpBWo");
        } else {
            builder.append("õBhÿÿÿãpBWoc");
        }
        
        //Scheint voll verbuggt zu sein... 
        builder.append('B'); // Value (Country, PLZ); A = empty, B = Deutschl., C = Öster. usw.
        builder.append("hÿÿÿã õDeutschl.õãCl      /      õcgMfÈÈúhUãEf");
        builder.append(client.getPlz()); // PLZ
        builder.append("õDhÿÿÿããf");
        builder.append(client.getJob()); // Job
        builder.append("õBhÿÿÿãf");
        builder.append(client.getSpitznamen()); // Spitzname(n)
           
        if (client.getRank() < 2) {
            builder.append("õBhÿÿÿãb");     // ohne lc  
        } else {
            builder.append("õBhÿÿÿão"); 
            builder.append("hÿÿÿã");
            
            String lala = "";
            if(client.getLCSperre() == 1) { // == sperre
                if(!client.getLC().isEmpty()) {
                    lala = "õ"+client.getLC();
                }
            } else if(client.getLCSperre() == 0) { // keine sperre
                  lala = channels.toString();
               
            }
            
            builder.append(lala); // Auswahl lc
            builder.append("õãb");
        }
        
        builder.append("Editieren"); // Mychannel-Button Text
        builder.append("õogMfÈÈúhUãlõcgMfÈÈúhUãããSpFb ");
        builder.append("Speichern"); // Button-List, 1. Button Text
        builder.append(" õsgSfÈÈúhUãb ");
            
        if (client.getVerify() == 0) {
            builder.append("Alter verifizieren"); // Button-List, 2. Button Text
            builder.append(" õogSfÈÈúhUãb ");
        }
        
        builder.append('?'); // Button-List, 3. Button Text
        builder.append(" õogSfÈÈúhUããCpFpGACEEb");
            
        if (client.getRank() > 0) {
            builder.append("HP bearbeiten"); // 1. Button Text (HP Edit & GB Edit)
            builder.append("õgOfÈÈúhUu");
            builder.append(String.format("/openurl %sindex.php?page=hp_edit&hp&accesscookie="+Functions.saveLoginCookie(client), Server.get().getURL().substring(7))); // URL (öffnen bei Klick, 1. Button)
            builder.append("õãb");
        }
            
        builder.append("Foto bearbeiten"); // 2. Button Text (HP Edit & GB Edit)
        builder.append("õgOfÈÈúhUu");
        builder.append(String.format("/openurl %sindex.php?page=photo_user&n=&photo&accesscookie="+Functions.saveLoginCookie(client), Server.get().getURL().substring(7))); // URL (öffnen bei Klick, 2. Button)
        builder.append("õãããããSlõgFhUãã");

        return builder.toString();
     }
    
    public static void createMyChannelWindow(Client client) {
        StringBuilder builder = new StringBuilder();
        
        Channel channel = client.getMyChannel();

        char minStatus = 'A';
        
        if (channel != null) {
if(channel.getMinRank() == 1) {
        	minStatus = 'B';
        } else if(channel.getMinRank() == 2) {
        	minStatus = 'C';
        } else if(channel.getMinRank() == 3) {
        	minStatus = 'D';
        } else if(channel.getMinRank() > 3) {
        	minStatus = 'E';
        }}
        
        char gender = 'A';
        if (channel != null) {
        if(channel.getMingender() == 1) {
        	gender = 'B';
        } else if(channel.getMingender() == 2) {
        	gender = 'C';
        }}
        
        char stammiMonths = 'A';
        if (channel != null) {
        if(channel.getMinstammimonths() == 1) {
        	stammiMonths = 'B';
        } else if(channel.getMinstammimonths() == 2) {
        	stammiMonths = 'C';
        } else if(channel.getMinstammimonths() == 3) {
        	stammiMonths = 'D';
        } else if(channel.getMinstammimonths() == 4) {
        	stammiMonths = 'E';
        } else if(channel.getMinstammimonths() == 5) {
        	stammiMonths = 'F';
        } else if(channel.getMinstammimonths() == 6) {
        	stammiMonths = 'G';
        } else if(channel.getMinstammimonths() == 9) {
        	stammiMonths = 'H';
        } else if(channel.getMinstammimonths() == 12) {
        	stammiMonths = 'I';
        } else if(channel.getMinstammimonths() == 18) {
        	stammiMonths = 'J';
        }}
        
        builder.append("k\0Eigener Channel - GrundeinstellungenõsucõmainMyChannelõp\0ð\0\0fUhUãWl  õgMhUãEl  õgMhUãNlõgFhUãSlõgFhUãCpBWpBWpBNpBNpBNlGrundeinstellungenõgMfUhÈÈúãClõgBhUããWpGABCClNameõgMfÈÈúhUsNameõãlThemaõgMfÈÈúhUsThemaõãlZusatzinfoõgMfÈÈúhUsZusatzinfoõãlõgMfÈÈúhUãxs");
        builder.append(channel != null && channel.getLoginMessage() == 1 ? 't' : 'f'); // L // Loginmitteilung checked
        builder.append("lLogin-MitteilungõfÈÈúgMãxs");
        builder.append(channel != null && channel.getShowGender() == 1 ? 't' : 'f'); // Geschlecht anzeigen
        builder.append("lGeschlecht anzeigenõfÈÈúgMãxs");
        builder.append(channel != null && channel.getShowAge() == 1 ? 't' : 'f'); // Alter anzeigen
        builder.append("lAlter anzeigenõfÈÈúgMãlõgMfÈÈúhUããCpGABCCf");
        builder.append(channel != null ? channel.getName() : "");
        builder.append("õBhÿÿÿãf");
        builder.append(channel != null ? channel.getTopic() : "");
        builder.append("õBhÿÿÿãf");
        builder.append(channel != null ? channel.getInfo() : "");
        builder.append("õBhÿÿÿãlõcgMfÈÈúhUãlõgMfÈÈúhUãlõgMfÈÈúhUãlõgMfÈÈúhUãlõcgMfÈÈúhUãããCpBNpBNlDesignõgMfUhÈÈúãClõgBhUããWpGABCClDesignõgMfÈÈúhUsDesignõãlEigenes HintergrundbildõgMfÈÈúhUsEigenes HintergrundbildõãlDetaildesigneinstellungenõgMfÈÈúhUsDetaildesigneinstellungenõãlõgMfÈÈúhUããCpGABCCoc");
        builder.append('A'); // Eintrag (A = 1. usw.), Design Choice Value
        builder.append("Ùhÿÿÿã");
        builder.append("0815õ70erõ80erõAachenõAlienõAlternativeõAsiaõAugsburgõBadewanneõBasketballõBaWüõBayernõBeauty FarmõBerlinõBielefeldõBierõBikerõBlaue WolkenõBlubberõBlueõBlumentapeteõBochumõBodenseeõBonnõBrandenburgõBraunschweigõBremenõBroken HeartsõBücherõCanyonõCarsõChemnitzõChillenõChinatownõCocktailbarõComputerõCoolõDancingõDiscoõDortmundõDresdenõDuisburgõDüsseldorfõEastsideõElectronicõEngelõErdbeerenõEssenõEuropeõF1õFamilyõFantasiesõFarbverlaufõFlirtõFlirtextaseõFragezeichenõFrankfurtõFunõFußballõGelsenkirchenõGirls&BoysõGraue WolkenõHamburgõHandballõHandyõHannoverõHausaufgabenõHelp4YouõHerzenõHerzigõHessenõHimmelõHip HopõHitzefreiõIrrenhausõJamaicaõKaffeeõKaminzimmerõKaribikõKarlsruheõKekseõKerzenlichtõKielõKindergartenõKinoõKirschenõKleine HerzenõKnuddelsõKnutschende KnuddelsõKonsolenõKuscheleckeõKölnõLadiesõLeipzigõLeuchtturmõLiebeõLiebesengelõLiebeskummerõLiebesnestõLila WolkenõLokschuppenõLondonõLonely HeartsõLooolõLovetalkõMadridõMafiaõMafia2õMagdeburgõMagicõMamas&PapasõManga&AnimeõMannheimõMeckPommõMenõMetalõMusicõMönchengladbachõMünchenõMünsterõNachtcafeõNew YorkõNiedersachsenõNightclubõNirvanaõNRWõNürnbergõOrangeõOstfrieslandõParisõPferdeõPubõPunkõPunk 2õPuschelhasisõRapõRnBõRomõRostockõRuhrpottõRügenõSaarlandõSachsenõSachsen-AnhaltõSauerlandõSchleswig-HolsteinõSchulutensilienõSkateboardõSmileysõSmokingõSoulõSportõStandardõStarsõSternenhimmelõStuttgartõSummer FeelingõSunriseõSunshine KnuddelõTastaturõTearsõTechnoõThüringenõTierfreundeõTrauminselõTVõVampireõVerknuddelichungsinselõWeinrebeõWeiße BlütenõWestenõWestsideõWiesbadenõWolke7õWrestlingõWuppertalõ"); // Design Channels
        builder.append("ãbHochladenõsgMfÈÈúhUãbErweitertõsgMfÈÈúhUãlõcgMfÈÈúhUãããSpBNpBNpBNlMyChannel veröffentlichenõgMfUhÈÈúãClõgBhUããWpGABCCãCpGABCCããWpBNpBãWpGABCCxs");
        builder.append('t'); // LC Möglich checked
        builder.append("lLC möglichõfÈÈúgMãxs");
        builder.append('t'); // in der Whois anzeigen checked
        builder.append("lin der Whois anzeigenõfÈÈúgMããCpGABCClõgMfÈÈúhUãlõgMfÈÈúhUãããEpBNpBãWpGABCClTochterchannelsõgMfÈÈúhUsTochterchannelsõãlNotrufe EinsehenõgMfÈÈúhUsNotrufe EinsehenõããCpGABCCf");
        builder.append('9'); // Tochterchannels, TextBox Value
        builder.append("õBhÿÿÿãlõcgMfÈÈúhUãããSpFbVeröffentlichenõsgNfÈÈúhUããããCl  õgKfÈÈúhUãEpBNpBNlAdministrativesõgMfUhÈÈúãClõgBhUããWpGABCClChannelgrößeõgMfÈÈúhUsChannelgrößeõãxs");
        builder.append('t'); // Password, checked
        builder.append("lPasswortõfÈÈúgMãlChannelmoderatorenõgMfÈÈúhUsChannelmoderatorenõãlMindeststatusõgMfÈÈúhUsMindeststatusõãlGeschlechtsbeschränkungõgMfÈÈúhUsGeschlechtsbeschränkungõãlAltersbeschränkungõgMfÈÈúhUsAltersbeschränkungõãlStammimonateõgMfÈÈúhUsStammimonateõãlRegistriertageõgMfÈÈúhUsRegistriertageõãlMuten durch ButlerõgMfÈÈúhUsMuten durch Butlerõãxs");
        builder.append('t'); // Wordcheck checked
        builder.append("lWordcheckõfÈÈúgMãxs");
        builder.append('t'); // Textkügelchen checked
        builder.append("lTextkügelchenõfÈÈúgMãxs");
        builder.append('t'); // Fett & Kursiv checked
        builder.append("lFett & KursivõfÈÈúgMãxs");
        builder.append('t'); // Farbwechsel checked
        builder.append("lFarbwechselõfÈÈúgMãlSpiele: õgMfÈÈúhUsSpiele: õããCpGABCCoc");
        builder.append('L'); // 25, A = 1. Eintrag (3, Channelgröße) usw.
        builder.append("hÿÿÿã");
        builder.append("3õ4õ5õ6õ7õ8õ9õ10õ12õ15õ20õ25õ30õ35õ40õ45õ50õ60õ75õ100õ125õ150õ200õ"); // Channelgröße Auswahl
        builder.append("ãfPWõBhÿÿÿc*ãf");
        if (channel != null) {
        builder.append(channel.getMcms().replace("||", ", ").replace("|", "")); // CMs, TextBox Value
        } else {
        builder.append(""); // CMs, TextBox Value = leer
        }
       // builder.append(channel.getMcms().replace("||", ", ").replace("|", "")); // MCMs, TextBox Value
        builder.append("õBhÿÿÿão");
        builder.append('c');
        builder.append(minStatus); // 1. Eintrag, hier: Mitglied
        builder.append("hÿÿÿãMitgliedõFamilymitgliedõStammiõEhrenmitgliedõAdminõão");
        builder.append('c');
        builder.append(gender); // 1. Eintrag, hier: Alle
        builder.append("hÿÿÿãAlleõNur männlichõNur weiblichõão");
        builder.append('c');
        builder.append('A'); // 1. Eintrag, hier: Keine Beschränkung
        builder.append("hÿÿÿãKeine BeschränkungõAb 10õAb 12õAb 14õAb 16õAb 18õAb 21õAb 25õAb 30õAb 40õAb 50õAb 60õão");
        builder.append('c');
        builder.append(stammiMonths); // 1. Eintrag, hier: 1 (Stammimonate)
        builder.append("hÿÿÿã0õ1õ2õ3õ4õ5õ6õ9õ12õ18õãohÿÿÿã0õ5õ10õ30õ60õ120õ240õ365õão");
        builder.append('c');
        builder.append('A'); // 1. Eintrag, hier: Streng
        builder.append("hÿÿÿãStrengõNormalõMildõãlõgMfÈÈúhUãlõgMfÈÈúhUãlõgMfÈÈúhUãlõgMfÈÈúhUãbEinstellungenõogMfÈÈúhUãããSpFb Speichern õsgSfÈÈúhUãlõgFhUãb Schließen õdgSfÈÈúhUããããã");

        client.send(builder.toString());
    }

     
  

    public static String hello(String channel) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.HELLO.getValue());

        buffer.writeByte(0x00);
        buffer.writeString("xyz"); // password key
        buffer.writeByte(0x00);
        buffer.writeString(channel);

        return buffer.toString();
    }

    public static String pong() {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.PONG.getValue());

        return buffer.toString();
    }

    private static void addChannelStyle(PacketBuilder buffer, ChannelStyle style, boolean doNotLogJames) {
        buffer.writeByte(0x00);
        buffer.writeString(style.getForeground());
        buffer.writeByte(0x00);
        buffer.writeString(style.getBackground());
        buffer.writeByte(0x00);
        buffer.writeString(style.getRed());
        buffer.writeByte(0x00);
        buffer.writeString(style.getBlue());
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(style.getFontSize()));
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(style.getLineSpace()));
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(style.getUserListFontSize()));
        buffer.writeByte(0x00);
        buffer.writeString(style.getBackground()); // user list
        buffer.writeByte(0x00);
        buffer.writeString(doNotLogJames?"F":"T");
        buffer.writeByte(0x00);
        buffer.writeString("-");
    }

    public static String updateChannelSettings(Channel channel) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.UPDATE_CHANNEL_SETTINGS.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channel.getName());
        addChannelStyle(buffer, channel.getStyle(), channel.getGame()!=null||channel.getSpecialEvent()==1);

        return buffer.toString();
    }

    public static String butler() {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.BUTLER.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(Server.get().getButler().getName());

        return buffer.toString();
    }

    public static String kick(String channel, String reason) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.KICK.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(reason);

        return buffer.toString();
    }

    public static String privateChat(String channel, String nickname) {
		PacketWriter packet = new PacketWriter();
		packet.writeString(SendOpcode.MODULE.getValue());
		packet.write(0x00);
		packet.writeShort(Server.get().getModuleTree().getIndex("CLIENT_PP"));
		packet.write(channel.length());
		packet.writeString(channel);
		packet.write(nickname.length());
		packet.writeString(nickname);
		packet.write(0xFF);
		return packet.toString();
	}
    
    public static String nicklistSortTogether(String channel, String sender, String nick) {
		PacketWriter packet = new PacketWriter();
		packet.writeString(SendOpcode.MODULE.getValue());
		packet.write(0x00);
		packet.writeShort(Server.get().getModuleTree().getIndex("NICKLIST_SORT_TOGETHER"));
		packet.write((byte) channel.length());
		packet.writeString(channel);
		packet.write(0x0B);
		packet.write((byte) sender.length());
		packet.writeString(sender);
		packet.write(0x0B);
		packet.write((byte) nick.length());
		packet.writeString(nick);
		packet.write(0x0C);
		return packet.toString();
                
	}

    public static String channelFrame(Channel channel, String nickname, int unread) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.CHANNEL_FRAME.getValue());
        Client client = Server.get().getClient(nickname);
        buffer.writeByte(0x00);
        buffer.writeString(channel.getName());
        buffer.writeByte(0x00);
        buffer.writeString(nickname);
        buffer.writeByte(0x00);
        buffer.writeString("3"); // scrollspeed
        buffer.writeByte(0x00);
        buffer.writeString("800"); // width
        buffer.writeByte(0x00);
        buffer.writeString("600"); // height
        buffer.writeByte(0x00);
        buffer.writeString("0"); // position x
        buffer.writeByte(0x00);
        buffer.writeString("0"); // position y
        buffer.writeByte(0x00);
        buffer.writeString(channel.getBackgroundImage());
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(channel.getBackgroundPosition()));
        buffer.writeByte(0x00);
        buffer.writeString(!client.getLC().isEmpty()?client.getLC():"-");
        addChannelStyle(buffer, channel.getStyle(), channel.getGame()!=null||channel.getSpecialEvent()==1);
        buffer.writeByte(0x00);
        buffer.writeString("T"); // channel combobox
        buffer.writeByte(0x00);
        buffer.writeString("3000"); // max. message length
        buffer.writeByte(0x00);
        buffer.writeString("T"); // help button
        buffer.writeByte(0x00);
        buffer.writeString("T"); // report button
        buffer.writeByte(0x00);
        buffer.writeString("F"); // feedback button
        buffer.writeByte(0x00);
        buffer.writeString("F"); // search button
        buffer.writeByte(0x2C);
        buffer.writeString("T"); // mail button
        buffer.writeByte(0x3A);
        buffer.writeString(String.valueOf(client.getPostCountChanged()));
        buffer.writeByte(0x00);
        buffer.writeString("|y"); // kp
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(Server.get().getModuleTree().getVersion()));
        
        return buffer.toString();
    }
    
    public static String postCountChanged(int number) {
		PacketWriter packet = new PacketWriter();
		packet.writeString(SendOpcode.MODULE.getValue());
		packet.write(0x00);
		packet.writeShort(Server.get().getModuleTree().getIndex("POST_COUNT_CHANGED"));
		packet.write(0x00);
		packet.write(0x00);
		packet.write(0x00);
		packet.write((byte) number);
		return packet.toString();
	}
    
    public static String channelList(Collection<Channel> channels, int category) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.CHANNEL_LIST.getValue());

        for (Channel channel : channels) {
        	if(channel.isVisible()) {
        		if(channel.getCategory() == category || category == 0) {
        			int count = channel.countClients();
        			buffer.writeByte(0x00);
        			buffer.writeString(channel.getName());
        			buffer.writeByte(0x0A);
        			buffer.writeString(String.valueOf(count));
        			buffer.writeByte(0x00);
        			buffer.writeByte('p');
        			buffer.writeByte(0x00);
        			buffer.writeString("K"); // schriftfarbe applet

        			if (count == channel.getSize()) {
        				buffer.writeByte(0x00);
        				buffer.writeString("pics/icon_fullChannel.gif");
        			}
                                
                           /*     if (channel.getMingender() == 0) {
        				buffer.writeByte(0x00);
        				buffer.writeString("pics/icon_starlite_mf.gif");
        			}
                                
                                if (channel.getMingender() == 1) {
        				buffer.writeByte(0x00);
        				buffer.writeString("pics/icon_gender_male.gif");
        			}
                                
                                if (channel.getMingender() == 2) {
        				buffer.writeByte(0x00);
        				buffer.writeString("pics/icon_gender_female.gif");
        			}
                                */
        			if (channel.getSpecialEvent() == 1) {
        				buffer.writeByte(0x00);
        				buffer.writeString("pics/icon_specialEventChannel.gif");
        			}

        			buffer.writeByte(0x00);
        			buffer.writeString("-");
        		}
        	}
        }

        return buffer.toString();
    }

    public static String switchChannel(String channelFrom, String channelTo) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.SWITCH_CHANNEL.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channelFrom);
        buffer.writeByte(0x00);
        buffer.writeString(channelTo);

        return buffer.toString();
    }
    
    
    
       public static String  paketfake(String fake) {
       PacketBuilder buffer = new PacketBuilder(SendOpcode.paket.getValue());
        buffer.writeString(fake);
        return buffer.toString();
     }
    

    public static String publicMessage(String sender, String channel, String message) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.PUBLIC_MESSAGE.getValue());

        Client client = Server.get().getClient(sender);
        
           Client his = Server.get().getClient(sender);
                if (his != null) {                    
               his.addPublicHistory(channel, message, true);
                }
                
      
        
           String pic = "";
                  if (Server.get().getChannel(channel).getPhotos() == 1) {
       if (client.getPhoto().isEmpty()) {
       if (client.getGender() == 1) {
           pic = "msgMale...quadcut_36.border_2.h_39.png";
       } else if (client.getGender() == 2) {
           pic = "msgFemale...quadcut_36.border_2.h_39.png";
       } else {
            pic = "msgUnknownGender...quadcut_36.border_2.h_39.png";
       }} else {
        
           pic = "photos/m/"+client.getPhoto()+"...quadcut_36.border_2.h_39.jpg";
       }}
               
       
       String send1 = "";
       String send2 = "";
       
       if (!Functions.getNickUmrandung(client,Server.get().getChannel(channel)).split("~~")[0].equals("null")) {
           send1 += Functions.getNickUmrandung(client,Server.get().getChannel(channel)).split("~~")[0];
       }
       
        if (client.getSun()==1) {
           send2 += "°>features/sunshine/sunshine_chattext...b.my_37.mx_-20.w_47.h_0.png<°";
       }
        
         if (!Functions.getNickUmrandung(client,Server.get().getChannel(channel)).split("~~")[1].equals("null")) {
           send2 += Functions.getNickUmrandung(client,Server.get().getChannel(channel)).split("~~")[1];
       }
         
         
         buffer.writeByte(0x00);
		buffer.writeString(sender);
                
           
		buffer.writeByte(0x00);
		buffer.writeString(channel);
		buffer.writeByte(0x00);
                
                buffer.writeString(send1);
               
                buffer.writeString(message);
          
                 buffer.writeByte(0x00);
                 
                       if (!pic.isEmpty()) {
      
        buffer.writeString(pic);
          buffer.writeByte(0x00);
          buffer.writeString(sender);
          buffer.writeByte(0x00);
           buffer.writeString("0");
           buffer.writeByte(0x00);
            buffer.writeString("0");
           buffer.writeByte(0x00);
        
       } else {
                
                 
        	 buffer.writeString(" ");
           	 buffer.writeByte(0x00);    
                       }
                 buffer.writeString(send2);
                       
                 
        
        return buffer.toString();
    }

    public static String updateChannelBackground(Channel channel) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.UPDATE_CHANNEL_BACKGROUND.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channel.getName());
        buffer.writeByte(0x00);
        buffer.writeString(channel.getBackgroundImage());
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(channel.getBackgroundPosition()));

        return buffer.toString();
    }

    public static String addUser(Channel channel, Client client, Client c, boolean self) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.ADD_USER.getValue());
        Byte weight = 'p';
         for (Client c2 : channel.getClients())  {
             
               if (!client.getEffect().isEmpty() && c2.getShowEffects() == 1) {
                   
                     String ft = "";
              for(String v : Server.existeffekts.split("\\|")) {
                  if (!v.isEmpty()) {
                      
                      String[] e = v.split("~");
                      if (e[1].equals(client.getEffect())) {
                      ft = e[2];
                  }}
              }
                           
                           String[] l = client.getFeature(ft);
                   if (l[0].equals("2") || client.getEffect().equals("wash") || client.getEffect().equals("moskitoBite")) {
                               c2.send(PacketCreator.effect(client.getName(), client.getEffect()));
                   }          
                       }
                       
    }
        if (self || c.checkFriend(client.getName())) {
            weight = 'b'; 
        }
        
        buffer.writeByte(0x00);
        buffer.writeString(channel.getName());
        buffer.writeByte(0x00);
        buffer.writeString(client.getName());
        
        if(client.getAge() != 0 && channel.getShowAge() == 1) {
            buffer.writeByte(0x0A);
            buffer.writeString(String.valueOf(client.getAge()));
        }
        
        buffer.writeByte(0x00);
        buffer.writeByte(weight);
        buffer.writeByte(0x00);
        buffer.writeString(channel.getStyle().getRankColor(client));

        for (Pair<String, Integer> icon : client.getIcons()) {
        	if(icon.getLeft().equals("pics/male.png") || icon.getLeft().equals("pics/female.png") || icon.getLeft().equals("pics/features/gendericon/ft_11-09_boy...iconoverflow.png") || icon.getLeft().equals("pics/features/gendericon/ft_11-09_girl...iconoverflow.png")) {
        		if(channel.getShowGender() == 1) {
        			buffer.writeByte(0x00);
            		buffer.writeString(icon.getLeft());
            		buffer.writeByte(0x00);
            		buffer.writeString(String.valueOf(icon.getRight()));
        		}
        	} else {
        		buffer.writeByte(0x00);
            	buffer.writeString(icon.getLeft());
            	buffer.writeByte(0x00);
            	buffer.writeString(String.valueOf(icon.getRight()));
        	}
        }
        
        if(channel.checkCm(client.getName())) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/cm.png");
            buffer.writeByte(0x00);
            buffer.writeString("20");
            buffer.writeByte(0x00);
        }
        
        if(channel.checkMcm(client.getName())) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/mcm.png");
            buffer.writeByte(0x00);
            buffer.writeString("25");
            buffer.writeByte(0x00);
        }
        
        
        if(channel.checkVip(client.getName())) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/icon_vip.png");
            buffer.writeByte(0x00);
            buffer.writeString("22");
            buffer.writeByte(0x00);
        }
        
        if(channel.checkMod(client.getName())) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/icon_moderator.png");
            buffer.writeByte(0x00);
            buffer.writeString("24");
            buffer.writeByte(0x00);
        }
        
        if(c.getRank() > 1) {
        	if(client.getOnlineTime() < 6000) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/newUser.png");
                buffer.writeByte(0x00);
                buffer.writeString("17");
                buffer.writeByte(0x00);
        	}
        }
        
        if(client.hasPermission("cmd.globalcm") && client.getTeams().contains("|Global-CMs~0|")) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/gcm.png");
            buffer.writeByte(0x00);
            buffer.writeString("26");
            buffer.writeByte(0x00);
        }
    
        
        if(client.getMentor().equals(c.getName())) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/schaf.png");
            buffer.writeByte(0x00);
            buffer.writeString("20");
            buffer.writeByte(0x00);
        }
        if (client.getDevilsbomb() == 1) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/devilbomb_nicklist.gif");
            buffer.writeByte(0x00);
            buffer.writeString(String.valueOf(18));
        }
        
        if(c != client && client.getSearchActivate() == 1 && c.getSearchActivate() == 1) {
        	if(c.getSearchGender().equals("männl.") && client.getGender() == 1 || c.getSearchGender().equals("weibl.") && client.getGender() == 2) {
        		buffer.writeByte(0x00);
            	buffer.writeString("pics/1.gif");
            	buffer.writeByte(0x00);
            	buffer.writeString("18");
            	buffer.writeByte(0x00);
            }
        }

        buffer.writeByte(0x00);
        buffer.writeString("-"); // login message
        buffer.writeByte(0x00);
    	buffer.writeString(client.getLastChannel() != null?client.getLastChannel():"-");
        buffer.writeByte(0x00);
        buffer.writeString(" "); 
        buffer.writeByte(0x00);
        
        if(channel.getPhotos() == 1) {
        	buffer.writeString(String.format("photos/m/%s...quadcut_36.border_2.h_39.jpg", client.getPhoto()));
        	buffer.writeByte(0x00);
        	buffer.writeString(client.getName());
        	buffer.writeByte(0x00);
        	buffer.writeString("0");
        	buffer.writeByte(0x00);
        	buffer.writeString("2");
        }
        
        return buffer.toString();
    }

    public static String addIcon(String channel, String nickname, Pair<String, Integer> icon) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.ADD_ICON.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(nickname);
        buffer.writeByte(0x00);
        buffer.writeString(icon.getLeft());
        buffer.writeByte(0x00);
        buffer.writeString(String.valueOf(icon.getRight()));

        return buffer.toString();
    }

      public static String playMp3(String stream) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.PLAY_MP3.getValue());
        buffer.writeByte(0x00);
        buffer.writeString(stream);
        return buffer.toString();
    }
      
    public static String playSound(String file) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.PLAY_SOUND.getValue());
        buffer.writeByte(0x00);
        buffer.writeString(file);
        return buffer.toString();
    }

    public static String privateMessage(String sender, String recipient, String channelTo, String message, String channelFrom) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.PRIVATE_MESSAGE.getValue());

        
        buffer.writeByte(0x00);
        buffer.writeString(sender);
        buffer.writeByte(0x00);
        buffer.writeString(recipient);
        buffer.writeByte(0x00);
        buffer.writeString(channelTo);
        buffer.writeByte(0x00);
        buffer.writeString(message);
        buffer.writeByte(0x00);
        buffer.writeString(channelFrom);
        
        if(Server.get().getChannel(channelTo).getPhotos() == 1) {
            Client client = Server.get().getClient(sender);
            buffer.writeByte(0x00);
        
            if(!client.getPhoto().isEmpty()) {
            	buffer.writeString(String.format("photos/m/%s...quadcut_36.border_2.h_39.jpg", client.getPhoto()));
            } else {
            	if(client.getGender() == 1) {
                	buffer.writeString("msgMale...quadcut_40.mx_-2.h_39.png");
            	} else if(client.getGender() == 2) {
                	buffer.writeString("msgFemale...quadcut_40.mx_-2.h_39.png");
            	}
            }
            
            buffer.writeByte(0x00);
            buffer.writeString(sender);
            buffer.writeByte(0x00);
            buffer.writeString("0");
            buffer.writeByte(0x00);
            buffer.writeString("2");
        }

        return buffer.toString();
    }

    public static String action(String sender, String channel, String message) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.ACTION.getValue());
  Client his = Server.get().getClient(sender);
                if (his != null) {                    
               his.addPublicHistory(channel, message, false);
                }
            
        buffer.writeByte(0x00);
        buffer.writeString(sender);
        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(message);
        
        if(Server.get().getChannel(channel).getPhotos() == 1) {
        	if(!sender.equals("°BB°") && !sender.equals("°BB°_>>>")) {
            Client client = Server.get().getClient(sender);
            buffer.writeByte(0x00);
        
            if(!client.getPhoto().isEmpty() && !sender.isEmpty()) {
            	buffer.writeString(String.format("photos/m/%s...quadcut_36.border_2.h_39.jpg", client.getPhoto()));
            } else {
            	if(client.getGender() == 1) {
                	buffer.writeString("msgMale...quadcut_40.mx_-2.h_39.png");
            	} else if(client.getGender() == 2) {
                	buffer.writeString("msgFemale...quadcut_40.mx_-2.h_39.png");
            	}
            }
            
            buffer.writeByte(0x00);
            buffer.writeString(sender);
            buffer.writeByte(0x00);
            buffer.writeString("0");
            buffer.writeByte(0x00);
            buffer.writeString("2");
        	}
        }

        return buffer.toString();
    }
    
      public static String publicPicMessage(String sender, String channel,
			String message,String pics) {
		PacketBuilder buffer = new PacketBuilder(SendOpcode.PUBLIC_MESSAGE.getValue());
                
                Client his = Server.get().getClient(sender);
                if (his != null) {                    
               his.addPublicHistory(channel, message, true);
                }
                
                
		buffer.writeByte(0x00);
		buffer.writeString(sender);
		buffer.writeByte(0x00);
		buffer.writeString(channel);
		buffer.writeByte(0x00);
		buffer.writeString(message);
                buffer.writeByte(0x00);
                buffer.writeString(" ");
                buffer.writeByte(0x00);
               buffer.writeString(pics);
              	return buffer.toString();
	}
    
    public static String picAction(String sender, String channel, String message, String pic) {
        if (!pic.startsWith("icons/") && !pic.isEmpty()) {
            pic = String.format("icons/%s", pic);
        }
        
        PacketBuilder buffer = new PacketBuilder(SendOpcode.ACTION.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(sender);
        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(message);
        buffer.writeByte(0x00);
        buffer.writeString(pic);
        buffer.writeByte(0x00);
        buffer.writeString(" ");
        buffer.writeByte(0x00);
        buffer.writeString("1");
        buffer.writeByte(0x00);
        buffer.writeString("0");

        return buffer.toString();
    }

    public static String userList(Channel channel, Client c) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.USER_LIST.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channel.getName());

        for (Client client : channel.getClients()) {
        	String drauf = "0";
        	
            if(c.checkFriend(client.getName())) {
                drauf = "1";
            }
            
            buffer.writeByte(0x00);
            buffer.writeString(client.getName());
            
            if(client.getAge() != 0 && channel.getShowAge() == 1) {
                buffer.writeByte(0x0A);
                buffer.writeString(String.valueOf(client.getAge()));
            }
            
            buffer.writeByte(0x00);
            if (drauf.equals("1")) {
                buffer.writeByte('b');
            } else {
                buffer.writeByte('p');
            }
            buffer.writeByte(0x00);
            buffer.writeString(channel.getStyle().getRankColor(client));

            for (Pair<String, Integer> icon : client.getIcons()) {
            	if(icon.getLeft().equals("pics/male.png") || icon.getLeft().equals("pics/female.png") || icon.getLeft().equals("pics/features/gendericon/ft_11-09_boy...iconoverflow.png") || icon.getLeft().equals("pics/features/gendericon/ft_11-09_girl...iconoverflow.png")) {
            		if(channel.getShowGender() == 1) {
            		
                            buffer.writeByte(0x00);
                		buffer.writeString(icon.getLeft());
                		buffer.writeByte(0x00);
                		buffer.writeString(String.valueOf(icon.getRight()));
            		}
            	} else {
            		buffer.writeByte(0x00);
                	buffer.writeString(icon.getLeft());
                	buffer.writeByte(0x00);
                	buffer.writeString(String.valueOf(icon.getRight()));
            	}
            }
            
            if(channel.checkCm(client.getName())) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/cm.png");
                buffer.writeByte(0x00);
                buffer.writeString("20");
                buffer.writeByte(0x00);
            }
            
             if(channel.checkMcm(client.getName())) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/mcm.png");
                buffer.writeByte(0x00);
                buffer.writeString("25");
                buffer.writeByte(0x00);
            }
            
            if(channel.checkVip(client.getName())) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/icon_vip.png");
                buffer.writeByte(0x00);
                buffer.writeString("22");
                buffer.writeByte(0x00);
            }
            
            if(channel.checkMod(client.getName())) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/icon_moderator.png");
                buffer.writeByte(0x00);
                buffer.writeString("24");
                buffer.writeByte(0x00);
            }
            
            if(c.getRank() > 1) {
            	if(client.getOnlineTime() < 6000) {
                    buffer.writeByte(0x00);
                    buffer.writeString("pics/newUser.png");
                    buffer.writeByte(0x00);
                    buffer.writeString("17");
                    buffer.writeByte(0x00);
            	}
            }
            
             if (client.getDevilsbomb() == 1) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/devilbomb_nicklist.gif");
                buffer.writeByte(0x00);
                buffer.writeString(String.valueOf(18));
            }
             
             
            if(client.hasPermission("cmd.globalcm") && client.getTeams().contains("|Global-CMs~0|")) {
            buffer.writeByte(0x00);
            buffer.writeString("pics/gcm.png");
            buffer.writeByte(0x00);
            buffer.writeString("26");
            buffer.writeByte(0x00);
        }
             
             
            if(client.getMentor().equals(c.getName())) {
                buffer.writeByte(0x00);
                buffer.writeString("pics/schaf.png");
                buffer.writeByte(0x00);
                buffer.writeString("20");
                buffer.writeByte(0x00);
            }
            
            if(client.getSearchActivate() == 1 && c.getSearchActivate() == 1) {
            	if(c.getSearchGender().equals("männl.") && client.getGender() == 1 || c.getSearchGender().equals("weibl.") && client.getGender() == 2) {
            		buffer.writeByte(0x00);
                	buffer.writeString("pics/1.gif");
                	buffer.writeByte(0x00);
                	buffer.writeString("18");
                	buffer.writeByte(0x00);
                }
            }

            buffer.writeByte(0x00);
            buffer.writeString("-");
        }

        return buffer.toString();
    }

    public static String removeUser(Client client, Channel c) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.REMOVE_USER.getValue());
        
        buffer.writeByte(0x00);
        buffer.writeString(client.getName());
        buffer.writeByte(0x00);
        buffer.writeString(c.getName());
    	buffer.writeByte(0x00);
        buffer.writeString(client.getNewChannel() != null && !client.getChannel().getName().equals(client.getNewChannel()) && client.getChannels().size() < 2?client.getNewChannel():"-");
        
        if(c.getPhotos() == 1) {
        	buffer.writeByte(0x00);
        	buffer.writeString(" ");
        	buffer.writeString(String.format("photos/m/%s...quadcut_36.border_2.h_39.jpg", client.getPhoto()));
        	buffer.writeByte(0x00);
        	buffer.writeString(client.getName());
        	buffer.writeByte(0x00);
        	buffer.writeString("0");
        	buffer.writeByte(0x00);
        	buffer.writeString("2");
        }
        
        if(!c.isVisible() && c.getTemp() == 1 && c.countClients() < 2 || !c.getTochter().isEmpty() && c.countClients() < 2) {
			Server.removeChannel(c.getName());
        	Server.get().query(String.format("delete from channels where name='%s'", c.getName()));
        }

        return buffer.toString();
    }

    public static String openURL(String url, String target) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.OPEN_URL.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(url);
        buffer.writeByte(0x00);
        buffer.writeString(target);

        return buffer.toString();
    }

    public static String removeIcon(String channel, String nickname, String icon) {
        PacketBuilder buffer = new PacketBuilder(SendOpcode.REMOVE_ICON.getValue());

        buffer.writeByte(0x00);
        buffer.writeString(channel);
        buffer.writeByte(0x00);
        buffer.writeString(nickname);
        buffer.writeByte(0x00);
        buffer.writeString(icon);

        return buffer.toString();
    }
}
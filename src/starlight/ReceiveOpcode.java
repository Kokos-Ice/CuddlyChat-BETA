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

public enum ReceiveOpcode {
    FA("fa"),
    NEWSEDIT("newsedit"),
    FEEDBACK("feedback"),
    EXCEPTION("1"),
    ADMINCALL("cdia"),
    DISCONNECT("d"),
    CHATLOGS("chatlogs"),
    CHAT("e"),
    EDIT("g"),
    GAME("game"),
    AUCTIONME("auctionme"),  
    RHAPSODY("rhapsody"),
    PING("h"),
    INFO("info"),
    POLL("ip"),
    SNP("snp"),
    DL("dl"),
    INVITE("inv"),
    EDITOR("editor"), 
    FRIEND("friend"),
    AUTH("auth"),
    LINK_CLICKED("j"),
    CATEGORIES("k"),
    EMAIL("email"),
    MAIL("maillinks"),
    MENTOR("mentor"),
    RUNDMAIL("rundmail"),
    BLINDDATE("blinddate"),
    KONTO("knuddelsa"),
    MESSAGE("post"),
    MYCHANNEL("uc"),
    JOIN_CHANNEL("n"),
    Q_TOKEN("q"),
    ROSE("rose"),
    CMTEST("iv"),
    PASSWORD("password"),
    VOTEHANDLER("s"),
    KNUDDEL("knuddel"),
    REQUEST_USER_LIST("r"),
    HANDSHAKE("t"),
    SERVER("server"),
    CODE("code"),
    SMEDIT("smedit"),
    PRESSE("sud"),
    CHANNELEDIT("channeledit"),
    WHOISEDIT("whoisedit"),
    CHANNELRULES("channelrules"),
    REQUEST_HELP("u"),
    VERIFY("verify"),
    LEAVE_CHANNEL("w"),
    WHOIS("whois"),
    MODULE("q");

    private String opcode;

    private ReceiveOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getValue() {
        return opcode;
    }
}
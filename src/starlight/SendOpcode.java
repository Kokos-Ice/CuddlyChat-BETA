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

public enum SendOpcode {
    PLAY_MP3("#"),
    MODULECLASS("?"),
    HELLO("("),
    PONG(","),
    UPDATE_CHANNEL_SETTINGS("1"),
    EFFECT("4"),
    BUTLER("5"),
    KICK("6"),
    MODULE(":"),
    CHANNEL_FRAME("a"),
    CHANNEL_LIST("b"),
    BOLD_NAMES("."),
    SWITCH_CHANNEL("d"),
    PUBLIC_MESSAGE("e"),
    VOTE("p"),
    UPDATE_CHANNEL_BACKGROUND("j"),
    POPUP("k"),
    CLOSE_BILLARD_LIST("9"),
    BILLARD_LIST("0"),
    UPDATE_BILLARD_FUNCTIONS("8"),
    ADD_USER("l"),
    ADD_ICON("m"),
    PLAY_SOUND("o"),
    PRIVATE_MESSAGE("r"),
    ACTION("t"),
    USER_LIST("u"),
    REMOVE_USER("w"),
    OPEN_URL("x"),
    REMOVE_ICON("z"),
    paket(""),
    EXIT("!");

    private String opcode;

    private SendOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getValue() {
        return opcode;
    }
}
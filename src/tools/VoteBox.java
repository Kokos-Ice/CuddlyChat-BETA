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

import java.util.HashMap;
import java.util.Map;

import starlight.Channel;
import starlight.Client;
import starlight.SendOpcode;
import starlight.Server;
import tools.PacketWriter;

public class VoteBox {
    
    public static String createVoteBox(Channel channel, String voteBoxTitle, Button voteButton, HashMap<String, Integer> votes, int timeOut) {
        return createVoteBox(channel.getName(), voteBoxTitle, timeOut, timeOut, 0, voteButton, channel.countClients()-1, 2, votes, 4, true, "fw $TOKEN", 0, null, false);
    }
    
    public static String createSoccerBox(Channel channel, String voteBoxTitle, Button voteButton, HashMap<String, Integer> votes, int timeOut) {
        return createVoteBox(channel.getName(), voteBoxTitle, timeOut, timeOut, 0, voteButton, 0, 2, votes, 4, false, "fw $TOKEN", 0, null, false);
    }
    
	public static void newVoteResult(Channel channel) {
    	StringBuilder result = new StringBuilder("°20°_Das Ergebnis der Befragung:_°r°#");
    	int number = 1;
    	
    	for(String answer : channel.newVoteAnswers.keySet()) {
    		int stimmen = channel.newVoteAnswers.get(answer);
    		
    		if(stimmen > 0) {
    			result.append("#°+0080°").append(number).append(". °+7040°").append(answer).append("  (").append(stimmen).append(" Stimme");
    			
    			if(stimmen != 0) {
    				result.append("n");
    			}
    			
    			result.append(")");
    			number++;
    		}
    	}
    	
    	channel.broadcastButlerMessage(result.toString());
		
		for (Client c : channel.getClients()) {
			c.send(VoteBox.removeVoteBox(channel.getName()));
		}
	
		channel.setNewVoteEnd(0);
		channel.newVoteNicks.clear();
		channel.newVoteAnswers.clear();
    }
	
	public static String createSoccerBox(String channelName) {
		PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        packet.writeShort(Server.get().getModuleTree().getIndex("SHOW_VOTEBOX"));
        packet.write((byte) channelName.length());
        packet.writeString(channelName);
        packet.write(0xFF); //MODULE_ID
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0xFF); //MODULE_ID
        packet.write(0xFF); //MODULE_ID
        packet.write(0x01);
        packet.write(0xFF); //MODULE_ID
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x0C);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x07);
        packet.write(0x0C);
        packet.write(0x00);
        packet.write(0xFF);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x00);
        packet.write(0x08);
        packet.write(0xFF);
        packet.write(0x01);
        return packet.toString();
	}
    
    public static String createVoteBox(String channelName, String voteBoxTitle, int timeoutSeconds, int timeoutSecondsTotal,
            int timeoutSecondsHurryUp, Button voteButton, int maxVotes, int minVoteLines, HashMap<String, Integer> votes,
            int maxLogLines, boolean showBloodBar, String tokenClickTemplate, int statusLines, String statusBar, boolean logStatusAbove){
        
        PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        packet.writeShort(Server.get().getModuleTree().getIndex("SHOW_VOTEBOX"));
        packet.write((byte) channelName.length());
        packet.writeString(channelName);
        packet.write(0xFF); //MODULE_ID
        packet.write((byte) voteBoxTitle.length());
        packet.writeString(voteBoxTitle);
        
        //Settings I
        packet.writeInt(timeoutSeconds);
        packet.writeInt(timeoutSecondsTotal);
        packet.writeInt(timeoutSecondsHurryUp);
        
        //Button
        packet.write((byte) voteButton.buttonText.length());
        packet.writeString(voteButton.buttonText);
        packet.write((byte) voteButton.image.length());
        packet.writeString(voteButton.image);
        packet.writeBoolean(voteButton.alignLeft);
        packet.write((byte) voteButton.chatFunktion.length());
        packet.writeString(voteButton.chatFunktion);
        
        //Settings II
        packet.writeInt(maxVotes);
        packet.writeInt(minVoteLines);
        
        //Vote-Possibilities
        for(Map.Entry<String, Integer> entry : votes.entrySet()){
            packet.write(0x0B); //New List Item
            packet.write((byte) entry.getKey().length());
            packet.writeString(entry.getKey());
            packet.writeInt(entry.getValue());
        }
        
        packet.write(0x0C); //ListEnd!
        packet.writeInt(maxLogLines);
        packet.write(0x0C); //ListEnd! [->Unvollständiges Packet!]
        
        //Settings III
        packet.writeBoolean(showBloodBar);
        packet.write((byte) tokenClickTemplate.length());
        packet.writeString(tokenClickTemplate);
        packet.writeInt(statusLines);
        
        
        if(statusBar == null){
            packet.write(0xFF);
        }else{
            packet.write((byte) statusBar.length());
            packet.writeString(statusBar);
        }
        packet.writeBoolean(logStatusAbove);
        packet.writeBoolean(false);
        return packet.toString();
        
    }

    public static String updateVoteBox(String channelName, String voteFor, int votes, String logText){
        PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        packet.writeShort(Server.get().getModuleTree().getIndex("UPDATE_VOTEBOX"));
        packet.write((byte) channelName.length());
        packet.writeString(channelName);
        packet.write(0xFF); //MODULE_ID
        
        packet.write((byte) voteFor.length());
        packet.writeString(voteFor);
        packet.writeInt(votes);
        
        packet.write((byte) logText.length());
        packet.writeString(logText);
        packet.write(0xFF);
        
        return packet.toString();
    }
    
    public static String removeVoteBox(String channelName){
        PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        packet.writeShort(Server.get().getModuleTree().getIndex("REMOVE_VOTEBOX"));
        packet.write((byte) channelName.length());
        packet.writeString(channelName);
        packet.write(0xFF); //MODULE_ID
        return packet.toString();
    }
    
    public static String setVoteBoxLog(String channelName, String textFooter, String textHeader){
        PacketWriter packet = new PacketWriter();
        packet.writeString(SendOpcode.MODULE.getValue());
        packet.write(0x00);
        packet.writeShort(Server.get().getModuleTree().getIndex("SET_VOTEBOX_LOG"));
        packet.write((byte) channelName.length());
        packet.writeString(channelName);
        packet.write(0xFF); //MODULE_ID
        packet.write(0x80);
        packet.write(0x0C);
        packet.write(0xA3);
        packet.writeString(textFooter);
        packet.write(0x80);
        packet.write(0x02);
        packet.write(0x6E);
        packet.writeString(textHeader);
        return packet.toString();
    }
    
    public static class Button{
        private String buttonText;
        private String image;
        private String chatFunktion;
        private boolean alignLeft;
        
        public Button(String buttonText, String image, String chatFunktion, boolean leftSideAlign){
            this.buttonText = buttonText;
            this.image = image;
            this.chatFunktion = chatFunktion;
            this.alignLeft = leftSideAlign;
        }
    }
    
}
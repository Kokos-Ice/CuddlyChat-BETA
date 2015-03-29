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

import handler.*;
import java.io.DataOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import tools.huffman.*;

import tools.PacketReader;

public class SessionHandler extends Thread {
    private Socket socket;

    public SessionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Client client = new Client(socket);

        try {
            InputStream in = socket.getInputStream();
            byte type = (byte) in.read();

            if (type == 0x00) {
                while (true) {
                	int first = in.read();

                    if (first == -1) {
                            break;
                    }

                    int len;
                    byte b = (byte) first;

                    if (b >= 0) {
                        len = b + 1;
                    } else {
                        len = (b & 0x1F) + 1;
                        int count = (b & 0x60) >>> 5;

                        for (int i = 0; i < count; i++) {
                            len += in.read() << (i << 3) + 5;
                        }
                    }

                    byte[] buffer = new byte[len];
                   String packet = Huffman.getDecoder().decode(buffer);
                                      
                   //  String packet = new String(buffer);
                    
                    for (int i = 0; i < len; i++) {
                        buffer[i] = (byte) in.read();
                    }
                    
                 //   String[] tokens = new String(buffer, "UTF8").split("\u0000");
                   String[] tokens = Huffman.getDecoder().decode(buffer).split("\0");
					
                   
                    try {
						@SuppressWarnings("unused")
						String opcode = tokens[0];
                    }catch(ArrayIndexOutOfBoundsException e) {
                    	break;
                    }

                    String opcode = tokens[0];
                    
                    if (opcode.equals(ReceiveOpcode.FA.getValue())) {
                        FaHandler.handle(tokens, client);
                    } else if(opcode.equals(ReceiveOpcode.RUNDMAIL.getValue())) {
                      RundmailHandler.handle(tokens, client);  
                    } else if (opcode.equals(ReceiveOpcode.EXCEPTION.getValue())) {
                    } else if (opcode.equals(ReceiveOpcode.REQUEST_USER_LIST.getValue())) {
                    	RequestUserListHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.SMEDIT.getValue())) {
                    	SmileyEditorHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.NEWSEDIT.getValue())) {
                    	NewsEditHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.PRESSE.getValue())) {
                        PresseHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.CHANNELEDIT.getValue())) {
                         ChannelEditHandler.handle(tokens, client);        
                    }else if (opcode.equals(ReceiveOpcode.WHOISEDIT.getValue())) {
                         WhoisEditHandler.handle(tokens, client);        
                    } else if (opcode.equals(ReceiveOpcode.FRIEND.getValue())) {
                         FriendListHandler.handle(tokens, client);        
                    } else if (opcode.equals(ReceiveOpcode.GAME.getValue())) {
                        GameHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.AUCTIONME.getValue())) {
                        AuctionMeHandler.handle(tokens, client);
                    }   else if (opcode.equals(ReceiveOpcode.RHAPSODY.getValue())) {
                        RhapsodyHandler.handle(tokens, client);                  
                    } else if (opcode.equals(ReceiveOpcode.GAME.getValue())) {
                        JörnGameHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.DISCONNECT.getValue())) {
                    } else if (opcode.equals(ReceiveOpcode.CHAT.getValue())) {
                        ChatHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.AUTH.getValue())) {
                    	AuthenticationHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.EDIT.getValue())) {
                        EditHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.CHATLOGS.getValue())) {
                        ChatlogsHandler.handle(tokens, client);
                    }else if (opcode.equals(ReceiveOpcode.FEEDBACK.getValue())) {
                        FeedBackHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.KONTO.getValue())) {
                        KontoHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.PING.getValue())) {
                        PingHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.SERVER.getValue())) {
                        ServerHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.ROSE.getValue())) {
                        RoseHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.SNP.getValue())) {
                        SetNewPasswordHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.POLL.getValue())) {
                        PollHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.PASSWORD.getValue())) {
                        PasswordHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.INVITE.getValue())) {
                        InviteHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.CHANNELRULES.getValue())) {
                        ChannelrulesHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.EDITOR.getValue())) {
                        EditorHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.KNUDDEL.getValue())) {
                        KnuddelHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.VOTEHANDLER.getValue())) {
                        VoteHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.MYCHANNEL.getValue())) {
                        MyChannelHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.CMTEST.getValue())) {
                        CMTestHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.ADMINCALL.getValue())) {
                        AdmincallHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.LINK_CLICKED.getValue())) {
                    } else if (opcode.equals(ReceiveOpcode.JOIN_CHANNEL.getValue())) {
                        JoinChannelHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.VERIFY.getValue())) {
                        VerifyHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.INFO.getValue())) {
                        InfoHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.Q_TOKEN.getValue())) {
                    } else if (opcode.equals(ReceiveOpcode.CODE.getValue())) {
                        CodeHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.MESSAGE.getValue())) {
                        MessageHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.MENTOR.getValue())) {
                        MentorHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.HANDSHAKE.getValue())) {
                        HandshakeHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.CATEGORIES.getValue())) {
                        CategoryHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.BLINDDATE.getValue())) {
                        BlindDateHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.MAIL.getValue())) {
                        MailHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.EMAIL.getValue())) {
                        EmailHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.REQUEST_HELP.getValue())) {
                        RequestHelpHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.LEAVE_CHANNEL.getValue())) {
                        LeaveChannelHandler.handle(tokens, client);
                    } else if (opcode.equals(ReceiveOpcode.WHOIS.getValue())) {
                    } else if (opcode.equals(ReceiveOpcode.MODULE.getValue())) {
						packet = packet.substring(2);
						PacketReader reader = new PacketReader(packet);
						ModuleHandler.handle(reader, client);
					} else {
                        System.out.println(String.format("Unhandled opcode: %s", opcode));
                    }
                }
            } else if (type == 0x01) { // bridge
                          byte[] password  = new byte[in.read()];
                            in.read (password);                            
                            String passwordStr = new String(password);
                            if (passwordStr.equals("z7rrQUHZjUDz")) {
                            byte typeInfo = (byte)in.read();
                            byte[] extraInfo = new byte[in.read()];
                            in.read(extraInfo);
                            String nick = new String(extraInfo);
                            byte[] extraInfo2 = new byte[in.read()];
                            in.read(extraInfo2);
                            String info1 = new String(extraInfo2);
                            byte[] extraInfo4 = new byte[in.read()];
                            in.read(extraInfo4);
                            String info2 = new String(extraInfo4);
                            byte[] extraInfo5 = new byte[in.read()];
                            in.read(extraInfo5);
                            String info3 = new String(extraInfo5);
                            
                            byte[] extraInfo3 = new byte[in.read()];
                            in.read(extraInfo3);
                            String extraInfoStr3 = new String(extraInfo3);
                            BridgeHandler.handleCommand(nick, info1,info2,info3, extraInfoStr3);
                              
                            }
                        }else if (type == 0x02) {
                // Regi Kram
            }else if (type == 0x03) { 

                                 byte[] password  = new byte[in.read()];
                            in.read (password);                            
                            String passwordStr = new String(password);
                                if (passwordStr.equals("z7rrQUHZjUDz")) {
                            byte typeInfo = (byte)in.read();
                            byte[] nams = new byte[in.read()];
                            in.read(nams);
                            String value = new String(nams);
                             byte[] nams2 = new byte[in.read()];
                            in.read(nams2);
                            String typ = new String(nams2);
                            String lol = PHPHandler.handleCommand(value,typ);
                            byte[] result = lol.getBytes(); 
                            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                            dout.write(result); 
                                }

}
        } catch (IOException e) {
        } finally {
            client.disconnect();
        }
    }
}
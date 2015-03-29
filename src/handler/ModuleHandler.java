package handler;

import starlight.*;
import tools.*;


public class ModuleHandler {
	private static String protocolConfirmed;
	private static String changeProtocol;

	static {
		protocolConfirmed = PacketCreator.protocolConfirmed();
		changeProtocol = PacketCreator.changeProtocol();
	}

	public static void handle(PacketReader reader, Client client) {
		
            
            String module = Server.get().getModuleTree().getModule(reader.readShort());
		
           if (module.equals("REQUEST_INSTANT_WHOIS")) {
               
           } else if (module.equals("SEND_CHANGED_CONFIGURE_APPLET")) {
                
            } else  if (module.equals("STAT_REPORT")) {
                
            } else  if (module.equals("APPSTATS")) {
               
           } else if (module.equals("REQUEST_SB_TABS")) {
               
                
            } else if (module.equals("COCONUT_COLLECT")) {
              // der scheiß ging noch nie
             } else  if (module.equals("CONFIRM_PROTOCOL_HASH")) {
			reader.readInt();
			int version = reader.readInt();

			if (Server.get().getModuleTree().getVersion() == version) {
				client.send(protocolConfirmed);
			} else {
				client.send(changeProtocol);
			}
		} 
        }
}
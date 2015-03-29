package feature;

import java.util.List;
import starlight.*;

public interface Features {
    public boolean parsePublicMessage(String message, Client client);
    public boolean parsePrivateMessage(List<Client> targets, String message, Client client);
    public void onLeave(Client client);
    public abstract void onJoin(Client client);
    public abstract boolean parseCommand(String command, Client client,String[] array);
    public abstract void setPlayer(String nick, String[] values);
}


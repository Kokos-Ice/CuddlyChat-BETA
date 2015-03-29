package fifty.free;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.query;

class thread5 extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
    if (!fiftyfree.nextuser) {
      for (int i = 0; i < 70; i++) {
        try {
          if (fiftyfree.activ8 == true)
            sleep(50L);
          else
            return;
        }
        catch (InterruptedException e)
        {
        }
      }
      fiftyfree.stechuser = 0;
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_Stechen um Platz " + fiftyfree.stechplace + "._°r°§##" + fiftyfree.getStechenUser() + " treten im Stechen gegeneinander an und müssen versuchen, möglichst nahe an °20RR°_- " + fiftyfree.stechzahl + " -_°r°§ zu würfeln, aber nicht darüber.", new Object[0]));
    }

    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ8 == true)
          sleep(100L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }

    }

    int aktuplayer = fiftyfree.stechuser;
    fiftyfree.stechuser += 1;
    String aktu = fiftyfree.getAktuPlayer(aktuplayer);
    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20BB°_" + aktu + "°RR° ist an der Reihe...°r°§##" + aktu + " muss zwischen _" + fiftyfree.stechenwurf + " und " + fiftyfree.stechzahl + "_ würfeln (3W4 + 2W6 + 2W8 + W10 + W12 + W20 können dabei verwendet werden).", new Object[0]));
    fiftyfree.aktuuser = aktu;
    query.update("update fiftyfreedabei set wurfel='4,4,4,6,6,8,8,10,12,20' where user='" + aktu + "'");

    fiftyfree.activ8 = false;

    fiftyfree.activ9 = true;

    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ9 == true)
          sleep(200L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }
    }

    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format(aktu + " hat verschlafen und scheidet aus dem Stechen aus.", new Object[0]));

    fiftyfree.WurfOut(aktu, channel, client);
  }
}
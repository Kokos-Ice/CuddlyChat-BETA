package fifty.free;

import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.query;

class thread2 extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ3 == true)
          sleep(50L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }
    }
    fiftyfree.activ3 = false;
    fiftyfree.activ4 = true;

    int zahl = 0;
    if (fiftyfree.runde == 1)
      zahl = fiftyfree.wurf1;
    else if (fiftyfree.runde == 2)
      zahl = fiftyfree.wurf2;
    else if (fiftyfree.runde == 3) {
      zahl = fiftyfree.wurf3;
    }
    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_Achtung, °>sm_runde" + fiftyfree.runde + ".b.h_21.gif<° beginnt!_§##Wer über °20RR°_- " + zahl + " -_§°r° würfelt, scheidet aus... Zeit zum Würfeln: 25 Sekunden.", new Object[0]));
    query.update("update fiftyfreedabei set gewurfelt='0'");
    fiftyfree.sentWuerfel();
    fiftyfree.activ5 = true;

    thread3.client = client;
    thread3.channel = channel;
    Thread mixThread = new thread3();
    mixThread.start();
  }
}
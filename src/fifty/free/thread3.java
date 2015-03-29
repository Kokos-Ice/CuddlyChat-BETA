package fifty.free;

import fifty.settings;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.query;

class thread3 extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ5 == true)
          sleep(200L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }
    }
    fiftyfree.activ5 = false;
    channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_Noch 5 Sekunden..._°r°§", new Object[0]));
    fiftyfree.activ6 = true;

    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ6 == true)
          sleep(50L);
        else {
          return;
        }
      }
      catch (InterruptedException e)
      {
      }

    }

    fiftyfree.restwuerfe();

    if (fiftyfree.runde == 3) {
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_Damit ist die letzte Runde beendet!_°r°##" + fiftyfree.getPlaces2() + "", new Object[0]));

      Object[] lol = settings.getFiftyPoints("free", fiftyfree.getDabei());
      String place1 = lol[0].toString();
      String place2 = lol[1].toString();
      String place3 = lol[2].toString();
      int first = 1;
      int stechen = 0;
      int second = 1;
      int exist2 = 0;
      int third = 1;
      int exist3 = 0;
      if ((!place1.equals("0.0")) && (fiftyfree.getDabei2() > 1)) {
        first = query.count("select count(id) as a from fiftyfreedabei where place='1' and ingame='1'");
      }
      if ((!place2.equals("0.0")) && (fiftyfree.getDabei2() > 2)) {
        second = query.count("select count(id) as a from fiftyfreedabei where place='2' and ingame='1'");
        exist2 = 1;
      }
      if ((!place3.equals("0.0")) && (fiftyfree.getDabei2() > 3)) {
        third = query.count("select count(id) as a from fiftyfreedabei where place='3' and ingame='1'");
        exist3 = 1;
      }
      if (first >= 2)
      {
        stechen = 1;
        fiftyfree.stechplace = 1;
      } else if ((second >= 2) && (exist2 == 1)) {
        stechen = 1;
        fiftyfree.stechplace = 2;
      } else if ((third >= 2) && (exist3 == 1)) {
        stechen = 1;
        fiftyfree.stechplace = 3;
      }
      if (stechen == 1) {
        fiftyfree.setStechen();

        fiftyfree.activ6 = false;
        fiftyfree.activ4 = false;
        fiftyfree.activ8 = true;
        thread5.client = client;
        thread5.channel = channel;
        Thread mixThread = new thread5();
        mixThread.start();
      }
      else {
        fiftyfree.activ6 = false;
        fiftyfree.activ4 = false;
        fiftyfree.activ7 = true;
        thread4.client = client;
        thread4.channel = channel;
        Thread mixThread = new thread4();
        mixThread.start();
      }
    } else {
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_Runde " + fiftyfree.runde + " ist beendet!_§°r°##" + fiftyfree.getPlaces() + "#(noch " + fiftyfree.getDabei2() + " Spieler)", new Object[0]));
      fiftyfree.sentPlace();
      fiftyfree.activ6 = false;
      fiftyfree.activ4 = false;
      fiftyfree.runde += 1;
      fiftyfree.activ3 = true;

      thread2.client = client;
      thread2.channel = channel;
      Thread mixThread = new thread2();
      mixThread.start();
    }
  }
}
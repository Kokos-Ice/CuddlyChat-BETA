package fifty.free;

import fifty.settings;
import starlight.Channel;
import starlight.Client;
import starlight.Server;
import tools.query;

class thread extends Thread
{
  public static Channel channel;
  public static Client client;

  public void run()
  {
    for (int i = 0; i < 70; i++) {
      try {
        if (fiftyfree.activ == true)
          sleep(200L);
        else
          return;
      }
      catch (InterruptedException e)
      {
      }
    }
    if (fiftyfree.getDabei() < 2)
    {
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format("°20RR°_Fifty! findet leider nicht statt._°r°#Es haben sich lediglich " + fiftyfree.getDabei() + " Spieler angemeldet (5 Spieler wären mindestens notwendig).", new Object[0]));

      query.truncate("truncate table fiftyfreedabei");
      fiftyfree.activ = false;
    } else {
      Object[] lol = settings.getFiftyPoints("free", fiftyfree.getDabei());
      String place1 = lol[0].toString();
      String place2 = lol[1].toString();
      String place3 = lol[2].toString();
      String points = "1. Platz: " + place1.replace(".0", "") + " Fifty! Punkte#";
      points = points + "2. Platz: " + place2.replace(".0", "") + " Fifty! Punkte#";
      if (!place3.equals("0.0")) {
        points = points + "3. Platz: " + place3.replace(".0", "") + " Fifty! Punkte#";
      }
      channel.broadcastAction(String.format("°>_h%s|/serverpp \"|/w \"<°:", new Object[] { Server.get().getButler().getName() }), String.format(fiftyfree.getPlayer() + " (insgesamt " + fiftyfree.getDabei() + " Spieler) würfeln jetzt um den Sieg:#%s#(Aktuell im Jackpot: " + settings.getJP("free") + " Fifty! Punkte)", new Object[] { points }));

      fiftyfree.activ2 = true;
      fiftyfree.activ3 = true;

      thread2.client = client;
      thread2.channel = channel;
      Thread mixThread = new thread2();
      mixThread.start();
    }
  }
}
package game;

import handler.GameHandler;
import starlight.Channel;
import starlight.Client;

public class shourtFreidiffen
{
  private static int countChars(String input, char toCount)
  {
    int counter = 0; for (char c : input.toCharArray()) if (c == toCount) counter++; 
    return counter;
  }

  public static void make(String arg, Client client, Channel channel)
  {
    String[] teile = arg.split(":", 3);
    String knuddels = "0";
    String würfel = "0";
    String gegner = "";
    if (countChars(arg, ':') >= 2) {
      knuddels = teile[0];
      würfel = teile[1];
      gegner = teile[2];
    } else if (countChars(arg, ':') == 1) {
      knuddels = teile[0];
      würfel = teile[1];
    }

    if (!würfel.isEmpty())
    {
      String lol = "FAIL|freidiffen||0|" + knuddels + "|0|" + gegner + "|" + würfel;

      String[] tokens = lol.split("\\|");
      GameHandler.handle(tokens, client);
    }
  }
}
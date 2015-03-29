package game;

import handler.JörnGameHandler;
import starlight.Channel;
import starlight.Client;

public class shourtDicen
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
    String runden = "0";
    String gegner = "";
    if (countChars(arg, ':') >= 2) {
      knuddels = teile[0];
      runden = teile[1];
      gegner = teile[2];
    } else if (countChars(arg, ':') == 1) {
      knuddels = teile[0];
      runden = teile[1];
    }

    if (!runden.isEmpty())
    {
      String lol = "FAIL|dicen||0|0|" + knuddels + "|0|" + gegner + "|" + runden + "|1";

      String[] tokens = lol.split("\\|");
      JörnGameHandler.handle(tokens, client);
    }
  }
}
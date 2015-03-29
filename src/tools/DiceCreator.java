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

import java.util.Random;

public class DiceCreator {
    
    private static boolean isInteger(String s) {
         try {
             Integer.parseInt(s);
             return true;
         } catch(NumberFormatException e) {
             return false;
         }
    }
    
    public static String Würfeln(String inhalt, int isDiceo)
  {
    String returns = "";
    String error1 = "Bitte die Funktion folgendermaßen benutzen:#/dice [AUGENZAHL] oder [ANZAHL]w[AUGENZAHL]+[AUGENZAHL]+[ANZAHL]w[AUGENZAHL]+...#(/dice bzw. /diceo (für offene Würfe) rollt einmal einen sechsseitigen Würfel. Anzahl legt die Anzahl der Würfel (mindestens eins) fest und Augenzahl die Anzahl der Augen des Würfels. z.B.: /dice 5w4+2w20 würfelt fünfmal mit einem vierseitigen und zweimal mit einem zwanzigseitigem Würfel.)";
    String error2 = "Sie können maximal mit 25 Würfel würfeln.";
    String error3 = "Sie können maximal mit 100000-seitigen Würfeln würfeln.";
    String error4 = "Sie können nicht mit W0 Würfel würfeln.";
    String output = "";
    String erg = "";
    int gesamt = 0;
    int Anzahl = 0;
    String returner = "";
    if (inhalt.isEmpty()) {
      inhalt = "6";
    }
    if (inhalt.trim().equals("+")) {
      returns = error1;
    }

    if (inhalt.indexOf(":") >= 0) {
      returns = error1;
    }
    String[] würfeln = inhalt.split("\\+");
    for (String item : würfeln) {
      if (item.isEmpty()) {
        returns = error1;
      }

      String[] items = item.split(" ", 2);
      item = items[0];
      String zahl = item;
      if ((zahl.startsWith("w")) || (zahl.startsWith("W"))) {
        zahl = item.substring(1);
      }

      if ((!isInteger(zahl)) && (zahl.indexOf("w") < 0) && (zahl.indexOf("W") < 0)) {
        returns = error1;
      }
      if ((isInteger(zahl) == true) && (Integer.parseInt(zahl) <= 1) && (zahl.indexOf("w") < 0) && (zahl.indexOf("W") < 0)) {
        returns = error4;
      }
      if (returns.isEmpty()) {
        int i7 = 0;
        if (zahl.toLowerCase().indexOf("w") >= 0)
        {
          String[] split = zahl.toLowerCase().split("w");
          int index = 0;
          int index2 = 0;

          for (index = 0; index < split.length; index++) {
            index2 = index;
          }

          if (index2 == 0) {
            returns = error1;
          }
          if (index2 == 0) {
            String text = "1|1|" + error1 + "|1|1|1";

            return text;
          }

          if ((!isInteger(split[0])) || (!isInteger(split[1]))) {
            returns = error1;
          }

          i7 = Integer.parseInt(split[1]);
          zahl = zahl.toUpperCase();
          int i = Integer.parseInt(split[0]);

          if ((i <= 0) || (i7 <= 1)) {
            returns = error4;
          }
          else if (isDiceo == 0) {
            for (int i3 = 1; i3 <= i; i3++)
            {
              Random random = new Random();
              int randomInt = random.nextInt(i7);
              int zahli = randomInt + 1;
              erg = erg + zahli + ", ";
              gesamt += zahli;
            }
          }
          else {
            int zahl9 = 0;

            for (int i0 = 1; i0 <= i; i0++)
            {
              Random random = new Random();
              do {
                int randomInt2 = random.nextInt(i7);
                zahl9 = randomInt2 + 1;
                if (zahl9 == i7)
                  erg = erg + " " + zahl9 + "> ";
                else {
                  erg = erg + " " + zahl9 + ", ";
                }
                gesamt += zahl9;
              }while (zahl9 == i7);
            }

          }

          Anzahl += i;
        } else {
          Anzahl++;

          i7 = Integer.parseInt(zahl);
          if (i7 <= 1) {
            returns = error4;
          }
          else if (isDiceo == 0)
          {
            Random random = new Random();
            int randomInt = random.nextInt(i7);
            int randi = randomInt + 1;
            erg = erg + randi + ", ";
            gesamt += randi;
          }
          else {
            int rand2 = 0;
            do {
              Random random2 = new Random();
              int randomInt2 = random2.nextInt(i7);
              rand2 = randomInt2 + 1;
              if (rand2 == i7)
                erg = erg + " " + rand2 + "> ";
              else {
                erg = erg + " " + rand2 + ", ";
              }
              gesamt += rand2;
            }while (rand2 == i7);
          }

          zahl = "W" + zahl;
        }
        if (i7 > 100000) {
          returns = error3;
        }

        output = output + zahl + " + ";
      }

      if (Anzahl > 25) {
        returns = error2;
      }
    }
    String privi = "0";
    if (returns.isEmpty()) {
      returner = output.substring(0, output.length() - 3);
    } else {
      returner = returns;
      privi = "1";
      erg = "FAIL :D";
    }

    String text = "";
    String dooffen = "";
    if (isDiceo == 1) {
      dooffen = " (offener Wurf)";
    }
    String einzahl = "einen";
    if (Anzahl >= 2) {
      einzahl = "die";
    }

    returner = returner.replace("1W", "W");
    text = einzahl + "|" + dooffen + "|" + returner + "|" + erg.substring(0, erg.length() - 2) + "|" + gesamt + "|" + privi;

    return text;
  }
    
    public static String dice(String inhalt, int isDiceo) {
        String returns = "";
        String error1 = "Bitte die Funktion folgenderma�en benutzen:#/dice [AUGENZAHL] oder [ANZAHL]w[AUGENZAHL]+[AUGENZAHL]+[ANZAHL]w[AUGENZAHL]+...#(/dice bzw. /diceo (f�r offene W�rfe) rollt einmal einen sechsseitigen W�rfel. Anzahl legt die Anzahl der W�rfel (mindestens eins) fest und Augenzahl die Anzahl der Augen des W�rfels. z.B.: /dice 5w4+2w20 w�rfelt f�nfmal mit einem vierseitigen und zweimal mit einem zwanzigseitigem W�rfel.)";
        String error2 = "Sie k�nnen maximal mit 25 W�rfel w�rfeln.";
        String error3 = "Sie k�nnen maximal mit 100000-seitigen Würfeln w�rfeln.";
        String error4 = "Sie k�nnen nicht mit W0 W�rfel w�rfeln.";
        String output = "";
        String erg = ""; // fehlt
        int gesamt = 0; // fehlt
        int Anzahl = 0;
        String returner = "";

        if (inhalt.isEmpty()) {
            inhalt = "6";
        }

        if (inhalt.trim().equals("+")) {
            returns = error1;
        }
        
        if (inhalt.indexOf(":") >= 0) {
            returns = error1;
        }
        
        String[] würfeln = inhalt.split("\\+"); 

        for (String item : würfeln) {
            if (item.isEmpty()) {
                returns = error1;
            }

            String[] items = item.split(" ",2);
            item = items[0];
            String zahl = item;

            if (zahl.startsWith("w") || zahl.startsWith("W")) {
                zahl = item.substring(1);
            }

            if (isInteger(zahl) == false && zahl.indexOf("w") < 0 && zahl.indexOf("W") < 0) {
                returns = error1;       
            }
            
            if (isInteger(zahl) == true && Integer.parseInt(zahl) <= 1 && zahl.indexOf("w") < 0 && zahl.indexOf("W") < 0) {
                returns = error4;       
            }

            if (returns.isEmpty()) {
                int i7 = 0;

                if (zahl.indexOf("w") >= 0 || zahl.indexOf("W") >= 0) {
                    String[] split = zahl.toLowerCase().split("w"); 
                    
                    if (isInteger(split[0]) == false || isInteger(split[1]) == false) {
                        returns = error1;
                    }
                    
                    i7 =  Integer.parseInt(split[1]); 
                    zahl = zahl.toUpperCase();
                    int i =  Integer.parseInt(split[0]);
                    // mit mehreren und diceo == 0 
   
                    if (i <= 0 || i7 <= 1) {
                        returns = error4;   
                    } else {
                        if (isDiceo == 0) {
                            for (int i3 = 1; i3 <= i; i3++) {
                                Random random = new Random();
                                int randomInt = random.nextInt(i7);
                                int zahli = randomInt+1;
                                erg += zahli+", ";
                                gesamt = gesamt+zahli;
                            }
                        } else {
                            int zahl9 = 0;
        
                            for (int i0 = 1; i0 <= i; i0++) {
                                Random random = new Random();
                                
                                do {
                                    int randomInt2 = random.nextInt(i7);
                                    zahl9 = randomInt2+1;
                                
                                    if (zahl9 == i7) {
                                        erg = erg+" "+zahl9+"> ";
                                    } else {
                                        erg = erg+" "+zahl9+", ";        
                                    }
                                    
                                    gesamt = gesamt+zahl9;
                                } while(zahl9 == i7);
                            }
                        }
                    }
  
                    Anzahl = Anzahl+i;
                } else {
                    Anzahl++;
                    // einzelwurf
                    i7 =  Integer.parseInt(zahl);
  
                    if (i7 <= 1) {
                        returns = error4;   
                    } else {
                        if (isDiceo == 0) {
                            // dice und einem
                            Random random = new Random();
                            int randomInt = random.nextInt(i7);
                            int randi = randomInt+1;
                            erg += randi+", ";
                            gesamt = gesamt+randi;
                        } else {
                            int rand2 = 0;
                    
                            do {
                                Random random2 = new Random();
                                int randomInt2 = random2.nextInt(i7);
                                rand2 = randomInt2+1;
    
                                if (rand2 == i7) {
                                    erg = erg+" "+rand2+"> ";        
                                } else {
                                    erg = erg+" "+rand2+", ";
                                }
                    
                                gesamt = gesamt+rand2;
                            } while (rand2 == i7);
                            }
                        }
  
                        zahl = "W"+zahl;
                    }

                if (i7 > 100000) {
                    returns = error3;    
                }

                output += zahl+" + "; 
            }

            if (Anzahl > 25) {
                returns = error2;    
            }
        }

        String privi = "0";

        if (returns.isEmpty()) {
            returner = output.substring(0, output.length()-3);
        } else {
            returner = returns;
            privi = "1";
            erg = "FAIL :D";
        }

        String text = "";
        String dooffen = "";

        if (isDiceo == 1) {
            dooffen = " (offener Wurf)";   
        }
        
        String einzahl = "einen";

        if (Anzahl >= 2) {
            einzahl = "die";
        }
        
        returner = returner.replace("1W", "W");
        text = einzahl+"|"+dooffen+"|"+returner+"|"+erg.substring(0, erg.length()-2)+"|"+gesamt+"|"+privi;

        return text;
    }
}
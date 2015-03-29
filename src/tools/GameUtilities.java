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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import starlight.Server;
import game.WordMixRecord;

public class GameUtilities {
   private static List<Character> listedKeys = new ArrayList<Character>();

	public static String getRandomWordMixSentence() {
            return Server.wordmix.get(new Random().nextInt(Server.wordmix.size()));
    }
	
	public static String getRandomMixSentence() {
        return Server.mix.get(new Random().nextInt(Server.mix.size()));
	}
        
    public static char[] mixSentence(String pSentence) {
		
		char[] cText = pSentence.replace(" ", "").toUpperCase().toCharArray();
				
		ArrayList<Character> cListText = new ArrayList<Character>();
		for (int i=0; i<cText.length; i++) {
			cListText.add(cText[i]);
		}
		
		Collections.shuffle(cListText);
		
		char[] mcText = new char[cListText.size()];
		
		for (int i=0; i<cListText.size(); i++) {
			mcText[i] = cListText.get(i);
		}
			
		return mcText;
	}    
        
    public static String readableMixSentence(char[] pMixed, int pStartpos) {
			
		StringBuilder strBuilder = new StringBuilder(pMixed.length * 2);
				
		for (int i=0; i<pStartpos; i++) {
			strBuilder.append('_').append('_');
		}
		
		for (int i=pStartpos; i<pMixed.length; i++) {
			strBuilder.append(Character.toUpperCase(pMixed[i])).append(' ');
		}
		
		return strBuilder.toString();
	}
	
	public static String createReadableMixSentence(String pMixedSentence, String pAnswer, char[] pMixed, int pStartpos) {
				
		pMixedSentence = pMixedSentence.toUpperCase();
		
		StringBuilder strBuilder = new StringBuilder(pAnswer);
						
		int index = pMixedSentence.indexOf(pMixed[pStartpos]);
		while (pAnswer.charAt(index) != '_') {
			index = pMixedSentence.indexOf(pMixed[pStartpos], index + 1);
		}

		strBuilder.setCharAt(index, pMixed[pStartpos]);
		
		
		return strBuilder.toString();
	}
	
	public static String readableMixSentence(String pMixedSentence) {
		
		pMixedSentence = pMixedSentence.toUpperCase();
		
		StringBuilder strBuilder = new StringBuilder(pMixedSentence.length() * 2);
				
		for (int i=0; i<pMixedSentence.length(); i++) {
			strBuilder.append(pMixedSentence.charAt(i)).append(' ');
		}
		
		return strBuilder.toString().replace("_", "__");
	}
    
     // Folgender Teil gehört zu Blitz!
    
   public static int getRandomNumber(int x1, int x2) {  int ia = (int)Math.floor((Math.random() * x2) + x1); return ia;  }
    
    // Ende - nochmal Prüfen, bringt Fehler in Blitz.java bei RandomNumber

	
    public static String makeSentence(String sentence, String solution) {
        StringBuilder builder = new StringBuilder();
        Map<Character, String> map = new HashMap<Character, String>();
        sentence = sentence.replace("°B°", "").replace("°°", "");

        Matcher sentenceMatcher = Pattern.compile("\\s*([^\\)]+)\\((.)\\)\\s*").matcher(sentence);
        while (sentenceMatcher.find()) {
            String value = sentenceMatcher.group(1).trim();
            Character key = sentenceMatcher.group(2).charAt(0);

            if (!map.containsKey(key)) {
                map.put(key, value);
            }
        }

        for (int i = 0; i < solution.length(); i++) {
            Character temp = solution.charAt(i);

            if (map.containsKey(temp)) {
                builder.append(map.get(temp));

                if (i < solution.length() - 1) {
                    builder.append(' ');
                }
            }
        }


        return builder.toString();
    }

    
    
    public static String mixSentence2(String rawSentence) {
        listedKeys.clear();

        StringBuilder builder = new StringBuilder();
        Character[] keys = new Character[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

        if (rawSentence == null) {
            return null;
        }

        String[] rawSentenceParams = rawSentence.split(" ");
        Collections.shuffle(Arrays.asList(rawSentenceParams));

        if (rawSentenceParams.length > keys.length || rawSentenceParams.length < 3) {
            return null;
        }

        for (int i = 0; i < rawSentenceParams.length; i++) {
            String param = rawSentenceParams[i];
            Character paramKey = keys[i];

            builder.append(String.format("%s(°B°%s°°)", param, paramKey));
            listedKeys.add(paramKey);

            if (i < rawSentenceParams.length - 1) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    public static boolean almostCorrect(String raw, String userSentence) {
        String[] rawParams = raw.split(" ");
        String[] userParams = userSentence.split(" ");

        if (rawParams.length != userParams.length) {
            return false;
        }

        int falseCounter = 0;
        for (int i = 0; i < rawParams.length; i++) {
            if (rawParams[i].equals(userParams[i])) {
                continue;
            }

            falseCounter++;
        }

        return (falseCounter < 3);
    }

    public static float totalTime(long start) {
        return (float)((float)(System.currentTimeMillis() - start) / 1000);
    }

    public static boolean isNumberic(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static List<Character> getTwiceChar(String input) {
        List<Character> twiceChar = new ArrayList<Character>();

        char[] arr = input.toCharArray();
        List<Character> list = new ArrayList<Character>();

        for (int i = 0; i < arr.length; i++) {
            if (list.contains(arr[i]) && !twiceChar.contains(arr[i])) {
                twiceChar.add(arr[i]);
                continue;
            }

            list.add(arr[i]);
        }

        return twiceChar;
    }

    public static List<Character> falseKeys(String solution) {
        List<Character> falseList = new ArrayList<Character>();
        for (int i = 0; i < solution.length(); i++) {
            if (!listedKeys.contains(solution.charAt(i))) {
                falseList.add(solution.charAt(i));
            }
        }
        return falseList;
    }

    public static void Add(String nick, float points, String sentence, float seconds) {
    	WordMixRecord.setNick(nick);
    	WordMixRecord.setPoints(points);
    	WordMixRecord.setSeconds(seconds);
    	WordMixRecord.setSentence(sentence);
    	
    	Server.get().query(String.format("UPDATE `wordmixrekord` SET `nick` = '%s', `points` = '%s', `sentence` = '%s', `seconds` = '%s'", nick, points, sentence.replace("'", "\\'"), seconds));
    }
}
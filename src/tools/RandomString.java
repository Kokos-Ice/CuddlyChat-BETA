package tools;

import java.util.Random;
     
    public class RandomString {
     
    private static final String numbers = "0123456789";
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char[] alphabet = (numbers + characters).toCharArray();
     
    public static String getRandomString(int length) {
    char[] randomString = new char[length];
    Random random = new Random();
     
    for (int i = 0; i < randomString.length; i++) {
    randomString[i] = alphabet[random.nextInt(alphabet.length)];
    }
     
    boolean containsDigits = false;
    boolean containsCharacters = false;
    for (Character c : randomString) {
    containsDigits |= numbers.contains(c.toString());
    containsCharacters |= characters.contains(c.toString());
    }
     
    if (length < 2 || (containsDigits && containsCharacters)) {
    return new String(randomString);
    } else {   
    return getRandomString(length);
    }
    }
     
   
    }
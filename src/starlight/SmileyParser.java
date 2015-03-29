package starlight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tools.*;

/**
 *
 * @author Brainy
 */
/**
 *
 * Eine Klasse, die die Nachricht eines Users auf Smileys untersucht und sie
 * dann setzt. Nicht sehr sauber geschrieben, aber es sollte funktionieren.
 */
public class SmileyParser {

    private Map<String, String> chatSmileys;
    private Map<String, Integer> multipleSmileys;
    private List<String> keys;
    private List<String> userSmileys;

    public SmileyParser(Map<String, String> chatSmileys, List<String> userSmileys) {
        this.chatSmileys = chatSmileys;
        this.userSmileys = userSmileys;
        this.multipleSmileys = getMultipleSmileys(this.userSmileys);
        this.keys = new ArrayList<String>(chatSmileys.keySet());
        Collections.sort(keys, new MyComparator());
    }

    // wenn ein User z.B. ein Basic kauft etc.
    public void setUserSmileys(List<String> userSmileys) {
        this.userSmileys = userSmileys;
        this.multipleSmileys = getMultipleSmileys(this.userSmileys);
    }
     public void setChatSmileys(Map<String, String> chatSmileys) {
        this.chatSmileys = chatSmileys;
         this.keys = new ArrayList<String>(chatSmileys.keySet());
         Collections.sort(keys, new MyComparator());
    }

    private Map<String, Integer> getMultipleSmileys(List<String> userSmileys) {
        Map<String, Integer> multiple = new HashMap<String, Integer>();

        for (String smiley : userSmileys) {
            if (!multiple.containsKey(smiley)) {
                multiple.put(smiley, Collections.frequency(userSmileys, smiley));
            }
        }

        return multiple;
    }

    private Map<String, Integer> getMultipleSmileys(Map<String, Integer> multipleSmileys) {
        Map<String, Integer> m = new HashMap<String, Integer>();

        for (String key : multipleSmileys.keySet()) {
            m.put(this.chatSmileys.get(key), multipleSmileys.get(key));
        }

        return m;
    }

    private String parseMultiple(String input, Map<String, String> util) {
        StringBuilder sb = new StringBuilder();

        for (String key : this.chatSmileys.keySet()) {
            if (input.contains(key) && this.userSmileys.contains(key)) {
                input = input.replace(key, this.chatSmileys.get(key).replace(".gif", "{multiple?}.gif").replace(".png", "{multiple?}.png").replace(".jpg", "{multiple?}.jpg"));
            }
        }

        Map<String, Integer> temp = getMultipleSmileys(this.multipleSmileys);
        Map<String, Integer> helper = temp;
        
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            for (String m : temp.keySet()) {
                String s = m.replace(".gif", "{multiple?}.gif").replace(".png", "{multiple?}.png").replace(".jpg", "{multiple?}.jpg");

                if (sb.indexOf(s) >= 0) {
                    int count = temp.get(m);
                    count--;
                    
                    helper.put(m, count);
                    
                    int index = sb.length() - s.length();
                    int length = s.length();

                    sb.delete(index, index + length);

                    if (count > 0) {
                        sb.append(m);
                    } else {
                        sb.append(util.get(m));
                    }
                } else {
                    helper.put(m, temp.get(m));
                }
            }
            
            temp = helper;
        }

        return sb.toString();
    }

    private String replace(String input, String replacement, int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.indexOf(replacement) >= 0) {
                count--;

                if (count > 0) {
                    int index = sb.length() - replacement.length();
                    int length = replacement.length();

                    try {
                        if (input.substring(index).indexOf(replacement) >= 0) {

                            sb.delete(index, index + length);
                            sb.append(replacement.replace(".gif", "{multiple}.gif").replace(".png", "{multiple}.png").replace(".jpg", "{multiple}.jpg"));
                            // falls es .jpg oder .png gibt, auch das beachten!
                        }
                    } catch (Exception e) {
                        
                    }
                }
            }
        }

        return sb.toString();
    }

    private String parse(String input) {
        StringBuilder parsed = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        List<String> parsedList = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            for (String key : multipleSmileys.keySet()) {
                String replacement = this.chatSmileys.get(key);
                if (sb.indexOf(replacement) >= 0 && !parsedList.contains(replacement)) {
                    parsedList.add(replacement);

                    int counter = multipleSmileys.get(key);

                    parsed.append(sb);
                    sb.delete(0, sb.length());

                    String rest = replace(input.substring(i + 1), replacement, counter);
                    input = rest;
                    i = input.length() - rest.length() - 1;
                }
            }
        }

        return parsed.append(sb).toString();
    }

    public String parse(String input, int extraCount) {
        extraCount++;

        Map<String, String> util = new HashMap<String, String>();

        for (String smiley : this.keys) {
            if (userSmileys.contains(smiley)) {
                String replacement = chatSmileys.get(smiley);
                input = input.replace(smiley, replacement);

                if (!util.containsKey(replacement)) {
                    util.put(replacement, smiley);
                }
            }
        }

        input = parse(input);

        StringBuilder sb = new StringBuilder();
        StringBuilder chatText = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            for (String replacement : chatSmileys.values()) {
                String multiple = replacement.replace(".gif", "{multiple}.gif");

                if (sb.indexOf(replacement) >= 0) {
                    extraCount--;

                    String key = util.get(replacement);

                    if (extraCount < 0) {
                        int index = sb.length() - replacement.length();
                        int length = replacement.length();
                        sb.delete(index, index + length);
                        sb.append(key);
                    } else {
                        chatText.append(sb);
                        sb.delete(0, sb.length());
                    }
                } else if (sb.indexOf(multiple) >= 0 && chatText.indexOf(replacement) == -1) {
                    int index = sb.length() - multiple.length();
                    int length = multiple.length();

                    sb.delete(index, index + length);
                    sb.append(util.get(replacement));
                }
            }

        }

        // falls es .png oder .jpg gibt, ergänzen
        return parseMultiple(chatText.append(sb).toString().replace("{multiple}.gif", ".gif").replace("{multiple}.png", ".png").replace("{multiple}.jpg", ".jpg"), util);
    }
}
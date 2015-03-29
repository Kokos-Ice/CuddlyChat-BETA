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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import starlight.Channel;
import starlight.Client;
import starlight.Server;

public class KCodeParser {
	
	public static String clearKCode(String text) {
		return text.replace("§", "").replace("\\", "");
	}
	
    public static String parse(Client client, String str, int maxLineBreaks, int minSize, int maxSize) {
        StringBuilder ret = new StringBuilder();
        StringBuilder code = null;
        boolean isCode = false;
        boolean escape = false;
        byte lineBreaks = 0;

        
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);

            if (current == '°' && !escape) {
                isCode = !isCode;

                if (isCode) {
                    if (str.lastIndexOf('°') == i) {
                        break;
                    }

                    code = new StringBuilder();
                } else {
                	if (!code.toString().isEmpty()) {
                    	if(!client.hasPermission("cmd.parse.all")) {
                    		StringBuilder filtered = new StringBuilder();
                    		StringBuilder rgb = null;
                    		boolean isRGB = false;
                    		String size;

                    		for (int j = 0; j < code.length(); j++) {
                    			char c = code.charAt(j);

                       String neu = specialReplacement(code.toString(),client);
                           if (!neu.equals(code.toString())) {
                           filtered.append(neu);
                        }
                    			if (isRGB) {
                    				if (c == ']') {
                    					if (!validateRGB(rgb.toString().split(","))) {
                    						break;
                    					}

                    					isRGB = false;
                    					filtered.append('[');
                    					filtered.append(rgb);
                    					filtered.append(']');
                    				} else if (isNumber(c) || c == ',') {
                    					rgb.append(c);
                    				} else {
                    					break;
                    				}
                    			} else {
                    				if (isNumber(c)) {
                    					size = "";

                    					while (j < code.length()) {
                    						c = code.charAt(j);

                    						if (!isNumber(c)) {
                    							j--;
                    							break;
                    						}

                    						size += c;
                    						j++;
                    					}

                    					if (Integer.parseInt(size) < minSize) {
                    						size = String.valueOf(minSize);
                    					} else if (Integer.parseInt(size) > maxSize) {
                    						size = String.valueOf(maxSize);
                    					}

                    					filtered.append(size);
                    				} else if (isColor(c) || c == 'r') {
                    					filtered.append(c);
                    				} else if (c == '[') {
                    					isRGB = true;
                    					rgb = new StringBuilder();
                    				} else {
                    					break;
                    				}
                    			}
                    		}

                    		if (filtered.toString().isEmpty()) {
                    			continue;
                    		}

                    		code = filtered;
                    	} else {
                            
                              StringBuilder filtered = new StringBuilder();
                  
                             String neu = specialReplacement(code.toString(),client);
                               filtered.append(neu);
                       
                            if (filtered.toString().isEmpty()) {
                            continue;
                        }

                        code = filtered;
                            
                            
                        }
                            

                    	ret.append('°');
                    	ret.append(code);
                    	ret.append('°');
                    }
                }

                continue;
            }

            if (isCode) {
                code.append(current);
            } else {
                if (current == '#' && !escape) {
                    if (lineBreaks < maxLineBreaks) { // !filter || vor lineBreaks
                        ret.append("#°!°");
                        lineBreaks++;
                    } else {
                        ret.append(' ');
                    }
                } else {
                    ret.append(current);
                }
            }

           
            if (current == '\\') {
                escape = !escape;
            } else {
                escape = false;
            }
        }

     
         
        return ret.toString().trim();
    }
    
     public static String noKCode(String message) {
    	return message.replaceAll("°[^°]+°", "").replace("°", "").replace("\"", "").replace("_", "").replace("#", "").replace("§", "").replace("\\", "");
    }

    public static String escape(String message) {
        return message
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("#", "\\#")
                .replace("_", "\\_")
                .replace("§", "\\§")
                .replace("°", "\\")
                .trim();
    }

    private static boolean isNumber(int character) {
        return character >= '0' && character <= '9';
    }

    private static boolean isColor(int character) {
        switch (character) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'G':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'R':
            case 'W':
            case 'Y':
                return true;
            default:
                return false;
        }
    }

    private static String specialReplacement(String code, Client client) {
    // /m-Replacer
        if (client.hasPermission("cmd._parseFunction.mail")) {
        code = code.replace(">/m<",">linkicons/link-icon_post...b.png<>--<>|/m "+client.getName()+"<");
        }
        if (client.hasPermission("cmd.parseFunction.forum")) {
        code = code.replace(">/forum<",">linkicons/link-icon_forum2...b.png<>--<>|/forum<");
        }
        if (client.hasPermission("cmd.parseFunction.whois")) {
        code = code.replace(">/w<",">linkicons/link-icon_whois...b.png<>--<>|/w "+client.getName()+"<");
        }
         if (client.hasPermission("cmd.parseFunction.gb")) {
        code = code.replace(">/gb<",">linkicons/link-icon_gb...b.png<>--<>|/gb "+client.getName()+"<");
        }
         if (client.hasPermission("cmd.parseFunction.foto")) {
             if (!client.getPhoto().isEmpty()) {
        code = code.replace(">/foto<",">linkicons/link-icon_foto...b.png<>--<>|/foto "+client.getName()+"<");
        }}
          // /gb-Replacer
        if (client.hasPermission("cmd.parseFunction.gb")) {
        Pattern pattern6 = Pattern.compile(">\\/gb (.+?)<");
      Matcher matcher6 = pattern6.matcher(code);
      while (matcher6.find()) {
      String var = matcher6.group();
      String nick = var.substring(var.indexOf(">/gb ")+5).split("<")[0];
      Client target = Server.get().getClient(nick);
      if (target != null) {
      code = code.replace(">/gb "+nick+"<",String.format(">linkicons/link-icon_gb...b.png<>--<>%s|/gb %s<", target.getName(),target.getName()));
      }
      }
    }   
         // /foto-Replacer
        if (client.hasPermission("cmd.parseFunction.foto")) {
        Pattern pattern5 = Pattern.compile(">\\/foto (.+?)<");
      Matcher matcher5 = pattern5.matcher(code);
      while (matcher5.find()) {
      String var = matcher5.group();
      String nick = var.substring(var.indexOf(">/foto ")+7).split("<")[0];
      Client target = Server.get().getClient(nick);
      if (target != null && !target.getPhoto().isEmpty()) {
      code = code.replace(">/foto "+nick+"<",String.format(">linkicons/link-icon_foto...b.png<>--<>%s|/foto %s<", target.getName(),target.getName()));
      }
      }
    }  
        
        
        // /m-Replacer
        if (client.hasPermission("cmd.parseFunction.mail")) {
        Pattern pattern4 = Pattern.compile(">\\/m (.+?)<");
      Matcher matcher4 = pattern4.matcher(code);
      while (matcher4.find()) {
      String var = matcher4.group();
      String nick = var.substring(var.indexOf(">/m ")+4).split("<")[0];
      Client target = Server.get().getClient(nick);
      if (target != null) {
      code = code.replace(">/m "+nick+"<",String.format(">linkicons/link-icon_post...b.png<>--<>%s|/m %s<", target.getName(),target.getName()));
      }
      }
    }
        // /w-Replacer
        if (client.hasPermission("cmd.parseFunction.whois")) {
        Pattern pattern1 = Pattern.compile(">\\/w (.+?)<");
      Matcher matcher1 = pattern1.matcher(code);
      while (matcher1.find()) {
      String var = matcher1.group();
      String nick = var.substring(var.indexOf(">/w ")+4).split("<")[0];
      Client target = Server.get().getClient(nick);
      if (target != null) {
      code = code.replace(">/w "+nick+"<",String.format(">linkicons/link-icon_whois...b.png<>--<>%s|/w %s<", target.getName(),target.getName()));
      }
      }
    }
    // /h-Replacer
      if (client.hasPermission("cmd.parseFunction.help")) {
        Pattern pattern2 = Pattern.compile(">\\/h (.+?)<");
        Matcher matcher2 = pattern2.matcher(code);
        while(matcher2.find()) {
        String var = matcher2.group();
        String help = var.substring(var.indexOf(">/h ")+4).split("<")[0];
        if (Server.help.containsKey(help)) {
       code = code.replace(">/h "+help+"<",String.format(">linkicons/link-icon_hilfe...b.png<>--<>%s|/h %s<", help,help));
        } 
        }
    }
    // /info-Replacer
      if (client.hasPermission("cmd.parseFunction.info")) {
      Pattern pattern3 = Pattern.compile(">\\/info (.+?)<");
      Matcher matcher3 = pattern3.matcher(code);
      while (matcher3.find()) {
      String var = matcher3.group();
      String channels = var.substring(var.indexOf(">/info ")+7).split("<")[0];
      Channel channelTo = Server.get().getChannel(channels);
      if (channelTo != null) {
      code = code.replace(">/info "+channels+"<",String.format(">linkicons/link-icon_channelinfo...b.png<>--<>%s|/info %s<", channelTo.getName(),channelTo.getName()));
      }
      }
        }
      // Link-Replacer
    
      if (code.startsWith(">http://") || code.startsWith(">https://") || code.startsWith(">www.")) {
      int adminlink = code.indexOf("|");
      if (adminlink < 0) {
        String link = "";
       String start = "";
    if (code.startsWith(">http://")) {
        link = code.substring(code.indexOf(">http://")+8).split("<")[0];
        start = "http://";
    } else if (code.startsWith(">https://")) {
        link = code.substring(code.indexOf(">https://")+9).split("<")[0];
        start = "https://";
    } else if (code.startsWith(">www.")) {
        link = code.substring(code.indexOf(">www.")+5).split("<")[0];
        start = "www.";
    }
     String icon = "";
        			 
      if (link.contains("youtube.com") && client.hasPermission("cmd.parseLink.youtube")) {
         icon = "youtube";
        } else if (link.contains("facebook.com") && client.hasPermission("cmd.parseLink.facebook")) {
       	 icon = "facebook";
       	 }  else if (link.contains("de.wikipedia.org") && client.hasPermission("cmd.parseLink.wikipedia")) {
       	 icon = "wikipedia";
       	 }else if (link.contains("google.de") && client.hasPermission("cmd.parseLink.google")) {
       	 icon = "google";
       	 }else if (link.contains("knuddels-wiki.de") && client.hasPermission("cmd.parseLink.wiki")) {
       	 icon = "wiki";
       	 } else if (link.contains(String.format("%sshop", Server.get().getURL())) && client.hasPermission("cmd.parseLink.shop")) {
        icon = "shop";
        } else  if (link.contains(String.format("%sforum", Server.get().getURL())) && client.hasPermission("cmd.parseLink.forum")) {
       	 icon = "forum";
        } else if (client.hasPermission("cmd.parseLink.allwebsites")) {
        icon = "default";
        }
     if (!icon.isEmpty()) {
        code = code.replace(code,String.format(">linkicons/link-icon_%s...b.png<>--<>%s%s|\"<", icon,start,KCodeParser.escape(link).replace("\\°!\\°", "")));
     }

      }}
      
      return code;
    }
    
    private static boolean validateRGB(String[] rgb) {
        if (rgb.length < 3) {
            return false;
        }

        for (String color : rgb) {
            if (color.isEmpty() || color.length() > 3) {
                return false;
            }

            short value = Short.parseShort(color);

            if (value < 0 || value > 255) {
                return false;
            }
        }

        return true;
    }
}

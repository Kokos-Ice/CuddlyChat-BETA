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
 
public class ProfileTools {
    
   
    
    
    
    
 
        public static String getOS(String agent) {
                agent = agent.toLowerCase();
               
                  //Windows XP, Vista, 7, 8, 8.1, 9, 10
                if(agent.contains("windows xp") || agent.contains("nt 5.1")) {
                        return "Windows XP";
                } else if(agent.contains("windows vista") || agent.contains("nt 6.0")) {
                        return "Windows Vista";
                } else if(agent.contains("windows 7") || agent.contains("nt 6.1")) {
                        return "Windows 7";
                } else if(agent.contains("windows 8") || agent.contains("nt 6.2")) {
                        return "Windows 8";
                } else if(agent.contains("windows 9") || agent.contains("nt 6.3")) {
                        return "Windows 9";
                } else if(agent.contains("windows") || agent.contains("windows")) {
                        return "Windows";
                       
                     //Mac OS und MacOS X  
                } else if(agent.contains("intel mac os x 10.8") || agent.contains("Intel Mac OS X 10.8")) {
                        return "MacOS X 10.8";        
                } else if(agent.contains("mac os x") || agent.contains("Mac OS X")) {
                        return "MacOS X";
                } else if(agent.contains("mac os") || agent.contains("Mac OS")) {
                        return "MacOS";
                } else if(agent.contains("unix") || agent.contains("Unix")) {
                        return "Unix";
                       
                     //Alte Windows
                } else if(agent.contains("windows 95") || agent.contains("95")) {
                        return "Windows 95";        
                } else if(agent.contains("windows 98") || agent.contains("win98")) {
                        return "Windows 98";        
                } else if(agent.contains("windows me") || agent.contains("4x 4.90")) {
                        return "Windows ME";        
                } else if(agent.contains("windows nt 4.0") || agent.contains("nt 4.0")) {
                        return "Windows NT 4.0";        
                } else if(agent.contains("windows 2000") || agent.contains("nt 5.0")) {
                        return "Windows 2000";
                       
                   //Linux etc.
                } else if(agent.contains("gentoo linux") || agent.contains("[Gg]entoo")) {
                        return "Gentoo Linux";        
                } else if(agent.contains("ubuntu linux") || agent.contains("[Uu]buntu")) {
                        return "Ubuntu Linux";  
                } else if(agent.contains("redhat linux") || agent.contains("RedHat")) {
                        return "RedHat Linux";
                } else if(agent.contains("debian linux") || agent.contains("Debian")) {
                        return "Debian Linux";
                } else if(agent.contains("fedora linux") || agent.contains("Fedora")) {
                        return "Fedora Linux";
                } else if(agent.contains("mandriva linux") || agent.contains("Mandriva")) {
                        return "Mandriva Linux";
                } else if(agent.contains("suse linux") || agent.contains("SUSE")) {
                        return "SuSe Linux";  
                } else if(agent.contains("linux") || agent.contains("Linux")) {
                        return "Linux";
                } else if(agent.contains("symbian") || agent.contains("SymbianOS")) {
                        return "Symbian";
                } else { return null; }
                
        }
                
        
       
       
        
        
        public static String getCountry(String host) {
                if(host.endsWith("de") || host.endsWith("net") || host.startsWith("VPS")) {
                        return "Germany";
                } else if(host.endsWith("at")) {
                        return "Austria";
                } else if(host.endsWith("ch")) {
                        return "Switzerland";        
                } else if(host.endsWith("pl")) {
                        return "Poland";
                } else if(host.endsWith("ru")) {
                        return "Russia";
                } else if(host.endsWith("cz")) {
                        return "Czech Republik";
                } else if(host.endsWith("nl")) {
                        return "Netherlands";
                } else if(host.endsWith("it")) {
                        return "Italy";
                } else if(host.endsWith("es")) {
                        return "Spain";
                } else if(host.endsWith("com")) {
                        return "WorldWide";
                } else if(host.endsWith("se")) {
                        return "Sweden";
                } else if(host.endsWith("gr")) {
                        return "Greece";        
                }
 
                return KCodeParser.escape(host);
        }
 
        
        	

     public static String getHost(String host) {
                    host = host.toLowerCase();
            
            // p4fd4520f.dip0.t-ipconnect.de
                    
                    
                     if(host.contains("p5dd4106e.dip0")) {
                  return "Deutsche Telekom AG (RP)"; 
              } else if(host.contains("p5dc54c65.dip0")) {
                  return "Deutsche Telekom AG (RP)"; 
              } else if(host.contains("p5dd42ab3.dip0")) {
                  return "Deutsche Telekom AG (RP)";              
              } else if(host.contains("p578F4C69.dip0")) {
                  return "Deutsche Telekom AG (BW)";
              } else if(host.contains("p5b0d6256.dip0")) {
                  return "Deutsche Telekom AG (BW)";                 
              } else if(host.contains("p4fd4693c.dip0")) {
                  return "Deutsche Telekom AG (NRW)";  
              } else if(host.contains("p4fd4520f.dip0")) {
                  return "Deutsche Telekom AG (NRW)"; 
              } else if(host.contains("p4fd462a2.dip0")) {
                  return "Deutsche Telekom AG (NRW)"; 
              } else if(host.contains("p5b34c5bf.dip0")) {
                  return "Deutsche Telekom AG (NRW)";
              } else if(host.contains("p4fd46367.dip0")) {
                  return "Deutsche Telekom AG (NRW)";
              } else if(host.contains("p57976686.dip0")) {
                  return "Deutsche Telekom AG (NI)";    
              } else if(host.contains("p57b4cebe.dip0")) {
                  return "Deutsche Telekom AG (SH)";
              } else if(host.contains("p4fd979a0.dip0")) {
                  return "Deutsche Telekom AG (SH)";
              } else if(host.contains("p5489da2d.dip0")) {
                  return "Deutsche Telekom AG (BY)";
              } else if(host.contains("p5490CDC5.dip0")) {
                  return "Deutsche Telekom AG (BY)";
              } else if(host.contains("p5489dbfe.dip0")) {
                  return "Deutsche Telekom AG (BY)";            
              } else if(host.contains("p4FE16DFC.dip0")) {
                  return "Deutsche Telekom AG (BY)";
              } else if(host.contains("d1-online.com")) {
                  return "T-Mobile Web (HSPA/UMTS)";
              } else if(host.contains("unitymediagroup.de")) {
                  return "UnityMedia (NRW)"; 	
              } else if(host.contains("e181177229.adsl")) {
                  return "Alice DSL (DE)";
              } else if(host.contains("alicedsl.de")) {
                  return "Alice DSL (DE)";
              } else if(host.contains("arcor-ip.net")) {
                  return "Vodafone DSL"; 
              } else if(host.contains("vodafone.de")) {
                  return "Vodafone Web (HSPA/UMTS)"; 
              } else if(host.contains("bkg-neuruppin.de")) {
                  return "RFT kabel Nord"; 
              } else if(host.contains("superkabel.de")) {
                  return "Kabel Deutschland";  
              } else if(host.contains("mediaways.net")) {
                  return "o2 Germany";  
              } else if(host.contains("t-ipconnect.de")) {
                  return "Deutsche Telekom AG";  
              } else if(host.contains("skylogicnet.com")) {
                  return "Skylogic.com (Eutelsat)";  
              }
    
                    
               return KCodeParser.escape(host);
     
              }	
        
        
        public static String getBrowser(String agent) {
                agent = agent.toLowerCase();
               
                if(agent.contains("opera/16")) {
                        return "Opera 16";
                } else if(agent.contains("opera/15")) {
                        return "Opera 15";
                } else if(agent.contains("opera/14")) {
                        return "Opera 14";
                } else if(agent.contains("opera/13")) {
                        return "Opera 13";
                } else if(agent.contains("opera/12")) {
                        return "Opera 12";
                } else if(agent.contains("opera/11.62")) {
                        return "Opera 11.62";
                } else if(agent.contains("opera/11.52")) {
                        return "Opera 11.52";
                } else if(agent.contains("opera/11.51")) {
                        return "Opera 11.51";
                } else if(agent.contains("opera/11.50")) {
                        return "Opera 11.50";
                } else if(agent.contains("opera/11.11")) {
                        return "Opera 11.11";
                } else if(agent.contains("opera/11.10")) {
                        return "Opera 11.10";
                } else if(agent.contains("opera/11.01")) {
                        return "Opera 11.01";
                } else if(agent.contains("opera/11.00")) {
                        return "Opera 11";
                } else if(agent.contains("opera/10.70")) {
                        return "Opera 10.70";
                } else if(agent.contains("opera/10.63")) {
                        return "Opera 10.63";
                } else if(agent.contains("opera/10.62")) {
                        return "Opera 10.62";
                } else if(agent.contains("opera/10.61")) {
                        return "Opera 10.61";
                } else if(agent.contains("opera/10.60")) {
                        return "Opera 10.60";
                } else if(agent.contains("opera/10.54")) {
                        return "Opera 10.54";  
                } else if(agent.contains("opera/10.53")) {
                        return "Opera 10.53";
                } else if(agent.contains("opera/10.52")) {
                        return "Opera 10.52";
                } else if(agent.contains("opera/10.51")) {
                        return "Opera 10.51";
                } else if(agent.contains("opera/10.50")) {
                        return "Opera 10.50";
                } else if(agent.contains("opera/10.10")) {
                        return "Opera 10.10";        
                } else if(agent.contains("opera/10.01")) {
                        return "Opera 10.01";
                } else if(agent.contains("opera/10.00")) {
                        return "Opera 10";  
                } else if(agent.contains("opera/9.99")) {
                        return "Opera 9.99";    
                } else if(agent.contains("opera/9.80")) {
                        return "Opera 9.80";
                } else if(agent.contains("opera/9.70")) {
                        return "Opera 9.70";
                } else if(agent.contains("opera/9.64")) {
                        return "Opera 9.64";
                } else if(agent.contains("opera/9.63")) {
                        return "Opera 9.63";
                } else if(agent.contains("opera/9.62")) {
                        return "Opera 9.62";        
                } else if(agent.contains("opera/9.61")) {
                        return "Opera 9.61";        
                } else if(agent.contains("opera/9.60")) {
                        return "Opera 9.60";
                } else if(agent.contains("opera/9.52")) {
                        return "Opera 9.52";
                } else if(agent.contains("opera/9.51")) {
                        return "Opera 9.51";        
                } else if(agent.contains("opera/9.50")) {
                        return "Opera 9.50";
                } else if(agent.contains("opera/9.4")) {
                        return "Opera 9.4";
                } else if(agent.contains("opera/9.30")) {
                        return "Opera 9.30";        
                } else if(agent.contains("opera/9.27")) {
                        return "Opera 9.27";
                } else if(agent.contains("opera/9.26")) {
                        return "Opera 9.26";
                } else if(agent.contains("opera/9.25")) {
                        return "Opera 9.25";
                } else if(agent.contains("opera/9.24")) {
                        return "Opera 9.24";
                } else if(agent.contains("opera/9.23")) {
                        return "Opera 9.23";
                } else if(agent.contains("opera/9.22")) {
                        return "Opera 9.22";
                } else if(agent.contains("opera/9.21")) {
                        return "Opera 9.21";
                } else if(agent.contains("opera/9.20")) {
                        return "Opera 9.20";
                } else if(agent.contains("opera/9.12")) {
                        return "Opera 9.12";
                } else if(agent.contains("opera/9.10")) {
                        return "Opera 9.10";
                } else if(agent.contains("opera/9.02")) {
                        return "Opera 9.02";
                } else if(agent.contains("opera/9.01")) {
                        return "Opera 9.01";
                } else if(agent.contains("opera/9.00")) {
                        return "Opera 9";
                } else if(agent.contains("opera/8.65")) {
                        return "Opera 8.65";
                } else if(agent.contains("opera/8.60")) {
                        return "Opera 8.60";
                } else if(agent.contains("opera/8.54")) {
                        return "Opera 8.54";  
                } else if(agent.contains("opera/8.53")) {
                        return "Opera 8.53";
                } else if(agent.contains("opera/8.52")) {
                        return "Opera 8.52";
                } else if(agent.contains("opera/8.51")) {
                        return "Opera 8.51";
                } else if(agent.contains("opera/8.50")) {
                        return "Opera 8.50";
                } else if(agent.contains("opera/8.10")) {
                        return "Opera 8.10";
                } else if(agent.contains("opera/8.02")) {
                        return "Opera 8.02";
                } else if(agent.contains("opera/8.01")) {
                        return "Opera 8.01";
                } else if(agent.contains("opera/8.00")) {
                        return "Opera 8";  
                } else if(agent.contains("opera/7.60")) {
                        return "Opera 7.60";
                } else if(agent.contains("opera/7.54u1")) {
                        return "Opera 7.54";
                } else if(agent.contains("opera/7.54")) {
                        return "Opera 7.54";
                } else if(agent.contains("opera/7.53")) {
                        return "Opera 7.53";
                } else if(agent.contains("opera/7.52")) {
                        return "Opera 7.52";
                } else if(agent.contains("opera/7.51")) {
                        return "Opera 7.51";
                } else if(agent.contains("opera/7.50")) {
                        return "Opera 7.50";
                } else if(agent.contains("opera/7.23")) {
                        return "Opera 7.23";
                } else if(agent.contains("opera/7.22")) {
                        return "Opera 7.22";
                } else if(agent.contains("opera/7.21")) {
                        return "Opera 7.21";
                } else if(agent.contains("opera/7.20")) {
                        return "Opera 7.20";
                } else if(agent.contains("opera/7.11")) {
                        return "Opera 7.11";
                } else if(agent.contains("opera/7.10")) {
                        return "Opera 7.10";
                } else if(agent.contains("opera/7.03")) {
                        return "Opera 7.03";
                } else if(agent.contains("opera/7.02")) {
                        return "Opera 7.02";
                } else if(agent.contains("opera/7.01")) {
                        return "Opera 7.01";
                } else if(agent.contains("opera/7.0")) {
                        return "Opera 7";
                } else if(agent.contains("opera/6.12")) {
                        return "Opera 6.12";
                } else if(agent.contains("opera/6.11")) {
                        return "Opera 6.11";
                } else if(agent.contains("opera/6.1")) {
                        return "Opera 6.1";
                } else if(agent.contains("opera/6.06")) {
                        return "Opera 6.06";
                } else if(agent.contains("opera/6.05")) {
                        return "Opera 6.05";        
                } else if(agent.contains("opera/6.04")) {
                        return "Opera 6.04";    
                } else if(agent.contains("opera/6.03")) {
                        return "Opera 6.03";
                } else if(agent.contains("opera/6.02")) {
                        return "Opera 6.02";
                } else if(agent.contains("opera/6.01")) {
                        return "Opera 6.01";
                } else if(agent.contains("opera/6.0")) {
                        return "Opera 6";  
                } else if(agent.contains("opera/5.12")) {
                        return "Opera 5.12";
                } else if(agent.contains("opera/5.11")) {
                        return "Opera 5.11";
                } else if(agent.contains("opera/5.02")) {
                        return "Opera 5.02";
                } else if(agent.contains("opera/5.0")) {
                        return "Opera 5";        
                } else if(agent.contains("opera/4.02")) {
                        return "Opera 4.02";        
                       
                      //Google Chrome  
                } else if(agent.contains("chrome/33")) {
                        return "Google Chrome 33";
                } else if(agent.contains("chrome/32")) {
                        return "Google Chrome 32";
                } else if(agent.contains("chrome/31")) {
                        return "Google Chrome 31";
                } else if(agent.contains("chrome/30")) {
                        return "Google Chrome 30";
                } else if(agent.contains("chrome/29")) {
                        return "Google Chrome 29";
                } else if(agent.contains("chrome/28.0.1500.95")) {
                        return "Google Chrome 28";
                } else if(agent.contains("chrome/28.0.1468.0")) {
                        return "Google Chrome 28";
                } else if(agent.contains("chrome/28.0.1467.0")) {
                        return "Google Chrome 28";
                } else if(agent.contains("chrome/28.0.1464.0")) {
                        return "Google Chrome 28";
                } else if(agent.contains("chrome/27.0.1453.93")) {
                        return "Google Chrome 27";
                } else if(agent.contains("chrome/27.0.1453.116")) {
                        return "Google Chrome 27";
                } else if(agent.contains("chrome/24.0.1312.60")) {
                        return "Google Chrome 24";  
                } else if(agent.contains("chrome/24.0.1309.0")) {
                        return "Google Chrome 24";
                } else if(agent.contains("chrome/24.0.1295.0")) {
                        return "Google Chrome 24";        
                } else if(agent.contains("chrome/24.0.1292.0")) {
                        return "Google Chrome 24";
                } else if(agent.contains("chrome/24.0.1290.1")) {
                        return "Google Chrome 24";  
                } else if(agent.contains("chrome/24.0.1284.0")) {
                        return "Google Chrome 24";
                } else if(agent.contains("chrome/23.0.1271.6")) {
                        return "Google Chrome 23";
                } else if(agent.contains("chrome/23.0.1271.26")) {
                        return "Google Chrome 23";  
                } else if(agent.contains("chrome/23.0.1271.26")) {
                        return "Google Chrome 23";
                } else if(agent.contains("chrome/22.0.1216.0")) {
                        return "Google Chrome 22";
                } else if(agent.contains("chrome/22.0.1207.1")) {
                        return "Google Chrome 22";
                } else if(agent.contains("chrome/20.0.1092.0")) {
                        return "Google Chrome 20";  
                } else if(agent.contains("chrome/20.0.1090.0")) {
                        return "Google Chrome 20";        
                       
                //} else if(agent.contains("mozilla/5.0") || agent.contains("netscape") || agent.contains("mozilla/6.0") || agent.contains("mozilla/4.0") || agent.contains("mozilla/3.0") || agent.contains("mozilla/4.5") || agent.contains("mozilla/4.8") || agent.contains("firefox") || agent.contains("firebird")) {
                        //return "Firefox";
                       
                     //Mozilla Firefox  
                } else if(agent.contains("firefox/31")) {
                        return "Mozilla Firefox 31";
                } else if(agent.contains("firefox/30")) {
                        return "Mozilla Firefox 30";
                } else if(agent.contains("firefox/29")) {
                        return "Mozilla Firefox 29";
                } else if(agent.contains("firefox/28")) {
                        return "Mozilla Firefox 28";
                } else if(agent.contains("firefox/27.0")) {
                        return "Mozilla Firefox 27";
                } else if(agent.contains("firefox/26.0")) {
                        return "Mozilla Firefox 26";
                } else if(agent.contains("firefox/25.0")) {
                        return "Mozilla Firefox 25";
                } else if(agent.contains("firefox/24.0")) {
                        return "Mozilla Firefox 24";
                } else if(agent.contains("firefox/23.0")) {
                        return "Mozilla Firefox 23";
                } else if(agent.contains("firefox/22.0")) {
                        return "Mozilla Firefox 22";
                } else if(agent.contains("firefox/21.0.1")) {
                        return "Mozilla Firefox 21.0.1";
                } else if(agent.contains("firefox/21.0")) {
                        return "Mozilla Firefox 21";
                } else if(agent.contains("firefox/20.0")) {
                        return "Mozilla Firefox 20";  
                } else if(agent.contains("firefox/19.0")) {
                        return "Mozilla Firefox 19";
                } else if(agent.contains("firefox/18.0.1")) {
                        return "Mozilla Firefox 18.0.1";
                } else if(agent.contains("firefox/18.0")) {
                        return "Mozilla Firefox 18";
                } else if(agent.contains("firefox/17.0")) {
                        return "Mozilla Firefox 17";
                } else if(agent.contains("firefox/16.0.1")) {
                        return "Mozilla Firefox 16.0.1";  
                } else if(agent.contains("firefox/15.0a2")) {
                        return "Mozilla Firefox 15.0a2";
                } else if(agent.contains("firefox/15.0a1")) {
                        return "Mozilla Firefox 15.0a2";
                } else if(agent.contains("firefox/15.0.2")) {
                        return "Mozilla Firefox 15.2";
                } else if(agent.contains("firefox/15.0.1")) {
                        return "Mozilla Firefox 15.1";
                } else if(agent.contains("firefox/14.0a1")) {
                        return "Mozilla Firefox 14.0a1";
                } else if(agent.contains("firefox/14.0.1")) {
                        return "Mozilla Firefox 14.1";
                } else if(agent.contains("firefox/13.0.1")) {
                        return "Mozilla Firefox 13.1";
                } else if(agent.contains("firefox/12.0")) {
                        return "Mozilla Firefox 12";  
                } else if(agent.contains("firefox/11.0")) {
                        return "Mozilla Firefox 11";  
                } else if(agent.contains("firefox/10.0a4")) {
                        return "Mozilla Firefox 10.0a4";
                } else if(agent.contains("firefox/10.0.9")) {
                        return "Mozilla Firefox 10.9";
                } else if(agent.contains("firefox/9.0a2")) {
                        return "Mozilla Firefox 9.0a2";
                } else if(agent.contains("firefox/9.0.1")) {
                        return "Mozilla Firefox 9.1";
                } else if(agent.contains("firefox/9.0")) {
                        return "Mozilla Firefox 9";
                } else if(agent.contains("firefox/8.0")) {
                        return "Mozilla Firefox 8";
                } else if(agent.contains("firefox/6.0a2")) {
                        return "Mozilla Firefox 6.0a2";
                } else if(agent.contains("firefox/6.0")) {
                        return "Mozilla Firefox 6";
                } else if(agent.contains("firefox/5.0a2")) {
                        return "Mozilla Firefox 5.0a2";
                } else if(agent.contains("firefox/5.0.1")) {
                        return "Mozilla Firefox 5.1";
                } else if(agent.contains("firefox/5.0")) {
                        return "Mozilla Firefox 5";
                } else if(agent.contains("firefox/4.0.1")) {
                        return "Mozilla Firefox 4.1";
                } else if(agent.contains("firefox/4.0")) {
                        return "Mozilla Firefox 4";        
                } else if(agent.contains("firefox/3.0")) {
                        return "Mozilla Firefox 3";
                } else if(agent.contains("firefox/2.0")) {
                        return "Mozilla Firefox 2";
                } else if(agent.contains("firefox/1.0")) {
                        return "Mozilla Firefox 1";  
                       
                     //Microsoft Internet Explorer  
                } else if(agent.contains("msie") && agent.contains(".0")) {
                        return "Internet Explorer";
                } else if(agent.contains("msie 4") && agent.contains(".0")) {
                        return "Internet Explorer 4";
                } else if(agent.contains("msie 5") && agent.contains(".0")) {
                        return "Internet Explorer 5";
                } else if(agent.contains("msie 6") && agent.contains(".0")) {
                        return "Internet Explorer 6";
                } else if(agent.contains("msie 7") && agent.contains(".0")) {
                        return "Internet Explorer 7";  
                } else if(agent.contains("msie 8") && agent.contains(".0")) {
                        return "Internet Explorer 8";
                } else if(agent.contains("msie 9") && agent.contains(".0")) {
                        return "Internet Explorer 9";
                } else if(agent.contains("msie 10") && agent.contains(".0")) {
                        return "Internet Explorer 10";
                } else if(agent.contains("msie 11") && agent.contains(".0")) {
                        return "Internet Explorer 11";  
                       
             /*         //Safari  
                } else if(agent.contains("safari")) {
                        return "Safari";
                */}
               
                return KCodeParser.escape(agent);
        }
}

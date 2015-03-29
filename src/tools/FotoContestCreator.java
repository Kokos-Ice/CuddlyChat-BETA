	

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
     
    package tools;
     
    import java.util.List;
import starlight.Server;
     
    /**
     *
     * @author Brainy
     */
    public class FotoContestCreator {
       
        public static String configureApplet() {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(1));
            builder.append(getChar(9));
            builder.append(getChar(11));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(11));
            builder.append(getChar(2));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(100));
            builder.append(getChar(11));
            builder.append(getChar(4));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(1));
            builder.append(getChar(44));
            builder.append(getChar(11));
            builder.append(getChar(3));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(250));
            builder.append(getChar(11));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(12));
            builder.append(getChar(12));
     
            return builder.toString();
        }
    public static String showCountingBox(String channelName, String text, int value, int totalValue) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58)); // -> :
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(87)); // -> W
            builder.append(getChar(channelName.length())); // -> 
            builder.append(channelName);
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(text.length())); // -> 
            builder.append(text);
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(value)); // -> (
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(totalValue)); // -> (
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(1)); // -> 
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(12)); // -> 
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(12)); // -> 
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(255)); // -> ÿ
     
            return builder.toString();
        }
        public static String buttonBarSettings(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(122));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(220));
            builder.append(getChar(186));
            builder.append(getChar(235));
            builder.append(getChar(151));
            builder.append(getChar(0));
            builder.append(getChar(191));
            builder.append(getChar(100));
            builder.append(getChar(20));
            builder.append(getChar(225));
            builder.append(getChar(239));
            builder.append(getChar(249));
            builder.append(getChar(177));
            builder.append(getChar(200));
            builder.append(getChar(216));
            builder.append(getChar(77));
            builder.append(getChar(147));
            builder.append(getChar(191));
            builder.append(getChar(239));
            builder.append(getChar(202));
            builder.append(getChar(180));
            builder.append(getChar(227));
            builder.append(getChar(117));
            builder.append(getChar(103));
            builder.append(getChar(221));
            builder.append(getChar(74));
            builder.append(getChar(63));
     
            return builder.toString();
        }
       public static String buttonBarSettings3(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(122));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(105));
            builder.append(getChar(38));
            builder.append(getChar(30));
            builder.append(getChar(98));
            builder.append(getChar(25));
            builder.append(getChar(28));
            builder.append(getChar(87));
            builder.append(getChar(7));
            builder.append(getChar(25));
            builder.append(getChar(105));
            builder.append(getChar(38));
            builder.append(getChar(30));
            builder.append(getChar(98));
            builder.append(getChar(25));
            builder.append(getChar(28));
            builder.append(getChar(87));
            builder.append(getChar(7));
            builder.append(getChar(25));
            builder.append(getChar(239));
            builder.append(getChar(202));
            builder.append(getChar(180));
            builder.append(getChar(227));
            builder.append(getChar(117));
            builder.append(getChar(103));
            builder.append(getChar(221));
            builder.append(getChar(74));
            builder.append(getChar(9));
     
            return builder.toString();
        }
       public static String buttonBarSettings4(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(122));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(232));
            builder.append(getChar(217));
            builder.append(getChar(132));
            builder.append(getChar(255));
            builder.append(getChar(250));
            builder.append(getChar(200));
            builder.append(getChar(232));
            builder.append(getChar(217));
            builder.append(getChar(132));
            builder.append(getChar(205));/**/
            builder.append(getChar(170));
            builder.append(getChar(125));
            builder.append(getChar(205));/**/
            builder.append(getChar(170));
            builder.append(getChar(125));
             builder.append(getChar(205));/**/
            builder.append(getChar(170));
            builder.append(getChar(125));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
     
            return builder.toString();
        }
        public static String buttonBarSettings15(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(122));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(99));
            builder.append(getChar(159));
            builder.append(getChar(255));
            builder.append(getChar(119));
            builder.append(getChar(141));
            builder.append(getChar(234));
            builder.append(getChar(58));/**/
            builder.append(getChar(95));
            builder.append(getChar(205));
          builder.append(getChar(99));
            builder.append(getChar(159));
            builder.append(getChar(255));
            builder.append(getChar(119));
            builder.append(getChar(141));
            builder.append(getChar(234));
            builder.append(getChar(58));/**/
            builder.append(getChar(95));
            builder.append(getChar(205));
            builder.append(getChar(239));
            builder.append(getChar(202));
            builder.append(getChar(180));
            builder.append(getChar(227));
            builder.append(getChar(117));
            builder.append(getChar(103));
            builder.append(getChar(221));
            builder.append(getChar(74));
            builder.append(getChar(9));
     
            return builder.toString();
        }
      public static String buttonBarSettings2(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(122));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(99));
            builder.append(getChar(159));
            builder.append(getChar(255));
            builder.append(getChar(80));
            builder.append(getChar(112));
            builder.append(getChar(255));
            builder.append(getChar(51));
            builder.append(getChar(76));
            builder.append(getChar(255));
        builder.append(getChar(99));
            builder.append(getChar(159));
            builder.append(getChar(255));
            builder.append(getChar(80));
            builder.append(getChar(112));
            builder.append(getChar(255));
            builder.append(getChar(51));
            builder.append(getChar(76));
            builder.append(getChar(255));
            builder.append(getChar(239));
            builder.append(getChar(202));
            builder.append(getChar(180));
            builder.append(getChar(227));
            builder.append(getChar(117));
            builder.append(getChar(103));
            builder.append(getChar(221));
            builder.append(getChar(74));
            builder.append(getChar(9));
     
            return builder.toString();
        }
       public static String showButtons2(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(11));
     
            builder.append(getChar(5));
     
     
            builder.append(getChar(72));
     
            builder.append(getChar(105));
            builder.append(getChar(108));
            builder.append(getChar(102));
            builder.append(getChar(101));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(14));
            builder.append(getChar(47));
            builder.append(getChar(104));
            builder.append(getChar(32));
            builder.append(getChar(102));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(111));
            builder.append(getChar(99));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(115));
            builder.append(getChar(111));
           
            builder.append(getChar(12));
            builder.append(getChar(12));
     
            return builder.toString();
        }
        public static String showButtons(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar(5));
            builder.append(getChar(72));
            builder.append(getChar(105));
            builder.append(getChar(108));
            builder.append(getChar(102));
            builder.append(getChar(101));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(14));
            builder.append(getChar(47));
            builder.append(getChar(104));
            builder.append(getChar(32));
            builder.append(getChar(102));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(111));
            builder.append(getChar(99));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(115));
            builder.append(getChar(116));
            builder.append(getChar(12));
            builder.append(getChar(12));
     
            return builder.toString();
        }
            public static String showblitz1(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Start".length()));
            builder.append("Start");
        builder.append(getChar(8));
    builder.append(getChar(105));
     builder.append(getChar(99));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/a "+Server.get().getButler().getName().toLowerCase()+" blitz");
            builder.append(getChar(append1.length()));
            builder.append(append1);
         
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
       public static String showtv1(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Start".length()));
            builder.append("Start");
        builder.append(getChar(9));
    builder.append(getChar(105));
    builder.append(getChar(105));
     builder.append(getChar(99));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/a "+Server.get().getButler().getName().toLowerCase()+" show");
            builder.append(getChar(append1.length()));
            builder.append(append1);
     
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
    public static String showtv2(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Hilfe".length()));
            builder.append("Hilfe");
        builder.append(getChar(9));
    builder.append(getChar(105));
    builder.append(getChar(105));
     builder.append(getChar(99));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/h tv-show king");
            builder.append(getChar(append1.length()));
            builder.append(append1);
     
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
     
                          public static String showblitz2(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Anmelden".length()));
            builder.append("Anmelden");
               builder.append(getChar(8));
    builder.append(getChar(105));
     builder.append(getChar(99));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/blitz ok");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
            public static String showblitz3(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Würfeln".length()));
            builder.append("Würfeln");
              builder.append(getChar(8));
    builder.append(getChar(105));
     builder.append(getChar(99));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/blitz wurf");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
            public static String showtvshow(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Hilfe".length()));
            builder.append("Hilfe");
            builder.append(getChar(0));
            builder.append(getChar(1));
            String append1 = String.format("/h tv-show king");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
    public static String showsoccer(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Start".length()));
            builder.append("Start");
        builder.append(getChar(8));
    builder.append(getChar(105));
    builder.append(getChar(105));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/a "+Server.get().getButler().getName().toLowerCase()+" soccer");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
       public static String showsoccer2(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Anmelden".length()));
            builder.append("Anmelden");
      builder.append(getChar(8));
    builder.append(getChar(105));
    builder.append(getChar(105));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/soccer +");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
         public static String showsoccer3(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Schuss!".length()));
            builder.append("Schuss!");
      builder.append(getChar(8));
    builder.append(getChar(105));
    builder.append(getChar(105));
     builder.append(getChar(111));
    builder.append(getChar(102));
     builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/soccer schuß");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
            public static String showhigh(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Start".length()));
            builder.append("Start");
            builder.append(getChar(0));
            builder.append(getChar(1));
            String append1 = String.format("/high new");
            builder.append(getChar(append1.length()));
            builder.append(append1);
     
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
                  public static String showhigh2(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("Höher".length()));
            builder.append("Höher");
            builder.append(getChar(0));
            builder.append(getChar(1));
            String append1 = String.format("/high gro");
            builder.append(getChar(append1.length()));
            builder.append(append1);
              builder.append(getChar(11));
            builder.append(getChar("Niederiger".length()));
            builder.append("Niederiger");
            builder.append(getChar(0));
            builder.append(getChar(1));
            String append12 = String.format("/high nie");
            builder.append(getChar(append12.length()));
            builder.append(append12);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
       public static String showdicemaster(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar("1x würfeln".length()));
            builder.append("1x würfeln");
            builder.append(getChar(0));
            builder.append(getChar(1));
            String append1 = String.format("/dicemaster start:1");
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(11));
            builder.append(getChar("1x würfeln".length()));
            builder.append("3x würfeln");
       builder.append(getChar(0));
            builder.append(getChar(1));
            String append2 = String.format("/dicemaster start:3");
            builder.append(getChar(append2.length()));
            builder.append(append2);
            builder.append(getChar(11));
            builder.append(getChar("10x würfeln".length()));
            builder.append("10x würfeln");
       builder.append(getChar(0));
            builder.append(getChar(1));
            String append3 = String.format("/dicemaster start:10");
            builder.append(getChar(append3.length()));
            builder.append(append3);
            builder.append(getChar(12));
            builder.append(getChar(12));
            return builder.toString();
        }
        public static String showVoteButtons(String channelName, String nick1, String nick2) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(11));
            builder.append(getChar(nick1.length()));
            builder.append(nick1);
            builder.append(getChar(18));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(105));
            builder.append(getChar(99));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(45));
            builder.append(getChar(112));
            builder.append(getChar(108));
            builder.append(getChar(97));
            builder.append(getChar(121));
            builder.append(getChar(101));
            builder.append(getChar(114));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append1 = String.format("/sfc %s:/v %s", channelName, nick1);
            builder.append(getChar(append1.length()));
            builder.append(append1);
            builder.append(getChar(11));
            builder.append(getChar(nick2.length()));
            builder.append(nick2);
            builder.append(getChar(18));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(105));
            builder.append(getChar(99));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(45));
            builder.append(getChar(112));
            builder.append(getChar(108));
            builder.append(getChar(97));
            builder.append(getChar(121));
            builder.append(getChar(101));
            builder.append(getChar(114));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String append2 = String.format("/sfc %s:/v %s", channelName, nick2);
            builder.append(getChar(append2.length()));
            builder.append(append2);
            builder.append(getChar(12));
            builder.append(getChar(12));
     
            return builder.toString();
        }
     
        public static String showVoteButtonInVotebox(String channelName, String nick1, String nick2) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(87));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append(getChar(70));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(111));
            builder.append(getChar(67));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(115));
            builder.append(getChar(116));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(4));
            builder.append(getChar(86));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(18));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(105));
            builder.append(getChar(99));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(45));
            builder.append(getChar(112));
            builder.append(getChar(108));
            builder.append(getChar(97));
            builder.append(getChar(121));
            builder.append(getChar(101));
            builder.append(getChar(114));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String command = String.format("/sfc %s:/v", channelName);
            builder.append(getChar(command.length()));
            builder.append(command);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(26));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(2));
            builder.append(getChar(11));
            builder.append(getChar(nick1.length()));
            builder.append(nick1);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(11));
            builder.append(getChar(nick2.length()));
            builder.append(nick2);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(12));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(4));
            builder.append(getChar(12));
            builder.append(getChar(1));
            builder.append(getChar(9));
            builder.append(getChar(102));
            builder.append(getChar(119));
            builder.append(getChar(32));
            builder.append(getChar(36));
            builder.append(getChar(84));
            builder.append(getChar(79));
            builder.append(getChar(75));
            builder.append(getChar(69));
            builder.append(getChar(78));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
     
            return builder.toString();
        }
       public static String voteboxSettings(String channelName) {
            StringBuilder builder = new StringBuilder();
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(99));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(25));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(119));
            builder.append(getChar(105));
            builder.append(getChar(110));
            builder.append(getChar(98));
            builder.append(getChar(97));
            builder.append(getChar(114));
            builder.append(getChar(45));
            builder.append(getChar(97));
            builder.append(getChar(110));
            builder.append(getChar(105));
            builder.append(getChar(46));
            builder.append(getChar(46));
            builder.append(getChar(46));
            builder.append(getChar(109));
            builder.append(getChar(121));
            builder.append(getChar(95));
            builder.append(getChar(45));
            builder.append(getChar(55));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(18));
            builder.append(getChar(89));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(183));
            builder.append(getChar(205));
            builder.append(getChar(176));
            builder.append(getChar(18));
            builder.append(getChar(89));
            builder.append(getChar(0));
            builder.append(getChar(162));
            builder.append(getChar(122));
            builder.append(getChar(39));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(244));
            builder.append(getChar(184));
            builder.append(getChar(59));
            builder.append(getChar(144));
            builder.append(getChar(108));
            builder.append(getChar(35));
            builder.append(getChar(162));
            builder.append(getChar(122));
            builder.append(getChar(39));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(244));
            builder.append(getChar(184));
            builder.append(getChar(59));
            builder.append(getChar(144));
            builder.append(getChar(108));
            builder.append(getChar(35));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(246));
            builder.append(getChar(183));
            builder.append(getChar(191));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(206));
            builder.append(getChar(126));
            builder.append(getChar(247));
            builder.append(getChar(169));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append(getChar(118));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(98));
            builder.append(getChar(111));
            builder.append(getChar(120));
            builder.append(getChar(45));
            builder.append(getChar(98));
            builder.append(getChar(103));
            builder.append(getChar(45));
            builder.append(getChar(48));
            builder.append(getChar(48));
            builder.append(getChar(49));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(225));
            builder.append(getChar(239));
            builder.append(getChar(249));
            builder.append(getChar(177));
            builder.append(getChar(200));
            builder.append(getChar(216));
            builder.append(getChar(77));
            builder.append(getChar(147));
            builder.append(getChar(191));
            builder.append(getChar(224));
            builder.append(getChar(149));
            builder.append(getChar(41));
            builder.append(getChar(255));
            builder.append(getChar(220));
            builder.append(getChar(186));
            builder.append(getChar(235));
            builder.append(getChar(151));
            builder.append(getChar(0));
            builder.append(getChar(191));
            builder.append(getChar(100));
            builder.append(getChar(20));
     
            return builder.toString();
        }
        public static String voteboxSettings2(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(99));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(25));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(119));
            builder.append(getChar(105));
            builder.append(getChar(110));
            builder.append(getChar(98));
            builder.append(getChar(97));
            builder.append(getChar(114));
            builder.append(getChar(45));
            builder.append(getChar(97));
            builder.append(getChar(110));
            builder.append(getChar(105));
            builder.append(getChar(46));
            builder.append(getChar(46));
            builder.append(getChar(46));
            builder.append(getChar(109));
            builder.append(getChar(121));
            builder.append(getChar(95));
            builder.append(getChar(45));
            builder.append(getChar(55));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(18));
            builder.append(getChar(89));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(183));
            builder.append(getChar(205));
            builder.append(getChar(176));
            builder.append(getChar(18));
            builder.append(getChar(89));
            builder.append(getChar(0));
            builder.append(getChar(162));
            builder.append(getChar(122));
            builder.append(getChar(39));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(244));
            builder.append(getChar(184));
            builder.append(getChar(59));
            builder.append(getChar(144));
            builder.append(getChar(108));
            builder.append(getChar(35));
            builder.append(getChar(162));
            builder.append(getChar(122));
            builder.append(getChar(39));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(244));
            builder.append(getChar(184));
            builder.append(getChar(59));
            builder.append(getChar(144));
            builder.append(getChar(108));
            builder.append(getChar(35));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(246));
            builder.append(getChar(183));
            builder.append(getChar(191));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(206));
            builder.append(getChar(126));
            builder.append(getChar(100));
            builder.append(getChar(149));
            builder.append(getChar(237));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append(getChar(118));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(98));
            builder.append(getChar(111));
            builder.append(getChar(120));
            builder.append(getChar(45));
            builder.append(getChar(98));
            builder.append(getChar(103));
            builder.append(getChar(45));
            builder.append(getChar(48));
            builder.append(getChar(48));
            builder.append(getChar(49));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(225));
            builder.append(getChar(239));
            builder.append(getChar(249));
            builder.append(getChar(177));
            builder.append(getChar(200));
            builder.append(getChar(216));
            builder.append(getChar(77));
            builder.append(getChar(147));
            builder.append(getChar(191));
              /**//**//**//**//**//**//**//**/
      builder.append(getChar(72));
            builder.append(getChar(118));
            builder.append(getChar(255));
            /**//**//**//**//**//**//**//**/
      builder.append(getChar(99));
            builder.append(getChar(159));
            builder.append(getChar(255));
            builder.append(getChar(119));
            builder.append(getChar(141));
            builder.append(getChar(234));
            builder.append(getChar(58));
            builder.append(getChar(95));
            builder.append(getChar(205));
     
            return builder.toString();
        }
     public static String voteboxSettings3(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(99));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(25));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(119));
            builder.append(getChar(105));
            builder.append(getChar(110));
            builder.append(getChar(98));
            builder.append(getChar(97));
            builder.append(getChar(114));
            builder.append(getChar(45));
            builder.append(getChar(97));
            builder.append(getChar(110));
            builder.append(getChar(105));
            builder.append(getChar(46));
            builder.append(getChar(46));
            builder.append(getChar(46));
            builder.append(getChar(109));
            builder.append(getChar(121));
            builder.append(getChar(95));
            builder.append(getChar(45));
            builder.append(getChar(55));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(18));
            builder.append(getChar(89));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(183));
            builder.append(getChar(205));
            builder.append(getChar(176));
            builder.append(getChar(18));
            builder.append(getChar(89));
            builder.append(getChar(0));
            builder.append(getChar(162));
            builder.append(getChar(122));
            builder.append(getChar(39));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(244));
            builder.append(getChar(184));
            builder.append(getChar(59));
            builder.append(getChar(144));
            builder.append(getChar(108));
            builder.append(getChar(35));
            builder.append(getChar(162));
            builder.append(getChar(122));
            builder.append(getChar(39));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(244));
            builder.append(getChar(184));
            builder.append(getChar(59));
            builder.append(getChar(144));
            builder.append(getChar(108));
            builder.append(getChar(35));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(246));
            builder.append(getChar(183));
            builder.append(getChar(191));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(206));
            builder.append(getChar(126));
            builder.append(getChar(220));/*TV*/
            builder.append(getChar(226));
            builder.append(getChar(72));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append(getChar(118));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(98));
            builder.append(getChar(111));
            builder.append(getChar(120));
            builder.append(getChar(45));
            builder.append(getChar(98));
            builder.append(getChar(103));
            builder.append(getChar(45));
            builder.append(getChar(48));
            builder.append(getChar(48));
            builder.append(getChar(49));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(225));
            builder.append(getChar(239));
            builder.append(getChar(249));
            builder.append(getChar(177));
            builder.append(getChar(200));
            builder.append(getChar(216));
            builder.append(getChar(77));
            builder.append(getChar(147));
            builder.append(getChar(191));
            builder.append(getChar(224));
            builder.append(getChar(149));
            builder.append(getChar(41));
            builder.append(getChar(255));
            builder.append(getChar(220));
            builder.append(getChar(186));
            builder.append(getChar(235));
            builder.append(getChar(151));
            builder.append(getChar(0));
            builder.append(getChar(191));
            builder.append(getChar(100));
            builder.append(getChar(20));
     
            return builder.toString();
        }
        public static String showVotebox(String channelName, String nick1, String nick2, int count) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(87));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append("FotoContest"); // IMPORTANT: DO NOT CHANGE!!!!!!!!
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(count));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(2));
            builder.append(getChar(11));
            builder.append(getChar(getLength(nick1)));
            builder.append(nick1);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(11));
            builder.append(getChar(getLength(nick2)));
            builder.append(nick2);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(12));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(4));
            builder.append(getChar(12));
            builder.append(getChar(1));
            builder.append(getChar(9));
            builder.append(getChar(102));
            builder.append(getChar(119));
            builder.append(getChar(32));
            builder.append(getChar(36));
            builder.append(getChar(84));
            builder.append(getChar(79));
            builder.append(getChar(75));
            builder.append(getChar(69));
            builder.append(getChar(78));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
     
            return builder.toString();
        }
     
        public static String updateVotebox(String channelName, String voted, boolean votedIsMale, String voter, int voterAge, int count) {
            StringBuilder builder = new StringBuilder();
     
            if (!votedIsMale) {
                builder.append(getChar(58));
                builder.append(getChar(0));
                builder.append(getChar(0));
                builder.append(getChar(89));
                builder.append(getChar(getLength(channelName)));
                builder.append(channelName);
                builder.append(getChar(255));
                builder.append(getChar(getLength(voted)));
                builder.append(voted);
                builder.append(getChar(0));
                builder.append(getChar(0));
                builder.append(getChar(0));
                builder.append(getChar(count));
                builder.append(getChar(128));
                builder.append(getChar(0));
                String votedEscape = voted.replace("<", "\\<");
                String voterEscape = voter.replace("<", "\\<");
                String knFormat = String.format("°>_h%s|/fotowhois \"|/w \"<° (%s) °>icon-gender-female-small.b.mx_1.my_2.gif<° wählt °>_h%s|/serverpp \"|/w \"<°", voterEscape, voterAge, votedEscape);
                builder.append(getChar(getLength(knFormat)));
                builder.append(knFormat);
                builder.append(getChar(255));
            } else {
     
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(89));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(voted.length()));
            builder.append(voted);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(count));
            String appendText = String.format("°>_h%s|/fotowhois \"|/w \"<° (%s) °>icon-gender-male.b.mx_1.my_2.gif<° wählt °>_h%s|/serverpp \"|/w \"<°", voter.replace("<", "\\<"), voterAge, voted.replace("<", "\\<"));
            builder.append(getChar(appendText.length()));
            builder.append(appendText);
            builder.append(getChar(255));
            }
     
            return builder.toString();
        }
     
        public static String finalizeVotebox(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(92));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
     
            return builder.toString();
        }
     
        public static String showPrefixIcons(String channelName, List<String> params) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58)); // :
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(73));
            /* die 73 ist sozusagen unser Opcode, denn bei FotoContest (updateVotebox) lautet's hier nicht 73, sondern 89 */
            builder.append(getChar(channelName.length())); // Anzahl der Buchstaben vom Channelnamen
            builder.append(channelName); // Channelname
            builder.append(getChar(11));
            for (int i = 0; i < params.size(); i++) {
                String[] temp = params.get(i).split("~");
                String nick = temp[0];
     
                builder.append(getChar(nick.length())); // Anzahl der Buchstaben vom Nick
                builder.append(nick); // Nick
                for (int j = 1; j < temp.length; j++) {
                    String icon = temp[j];
                    builder.append(getChar(11));
                    builder.append(getChar(icon.length())); // Anzahl der Buchstaben vom Icon
                    builder.append(icon); // Icon
                }
                if (i < params.size() - 1) {
                    builder.append(getChar(12));
                    builder.append(getChar(11));
                }
            }
            builder.append(getChar(12));
            builder.append(getChar(12));
            builder.append(getChar(1));
     
            return builder.toString();
        }
      public static String showVotebox2(String channelName, String nick1, String nick2, int count) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(87));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append("FotoContest"); // IMPORTANT: DO NOT CHANGE!!!!!!!!
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(count));
     
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(2));
            builder.append(getChar(11));
            builder.append(getChar(getLength(nick1)));
            builder.append(nick1);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(11));
            builder.append(getChar(getLength(nick2)));
            builder.append(nick2);
            builder.append(getChar(0));
     
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
     
            builder.append(getChar(12));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(4));
            builder.append(getChar(12));
            builder.append(getChar(1));
            builder.append(getChar(9));
            builder.append(getChar(102));
            builder.append(getChar(119));
            builder.append(getChar(32));
            builder.append(getChar(36));
            builder.append(getChar(84));
            builder.append(getChar(79));
            builder.append(getChar(75));
            builder.append(getChar(69));
            builder.append(getChar(78));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
     
            return builder.toString();
        }
     public static String showVoteButtonInVotebox2(String channelName, String nick1, String nick2) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(87));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append(getChar(70));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(111));
            builder.append(getChar(67));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(115));
            builder.append(getChar(116));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(32));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(20));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(4));
            builder.append(getChar(86));
            builder.append(getChar(111));
            builder.append(getChar(116));
            builder.append(getChar(101));
            builder.append(getChar(18));
            builder.append(getChar(102));
            builder.append(getChar(99));
            builder.append(getChar(45));
            builder.append(getChar(105));
            builder.append(getChar(99));
            builder.append(getChar(111));
            builder.append(getChar(110));
            builder.append(getChar(45));
            builder.append(getChar(112));
            builder.append(getChar(108));
            builder.append(getChar(97));
            builder.append(getChar(121));
            builder.append(getChar(101));
            builder.append(getChar(114));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(1));
            String command = String.format("/sfc %s:/v", channelName);
            builder.append(getChar(command.length()));
            builder.append(command);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(26));
       builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(2));
            builder.append(getChar(11));
            builder.append(getChar(nick1.length()));
            builder.append(nick1);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(2));
            builder.append(getChar(11));
            builder.append(getChar(nick1.length()));
            builder.append(nick1);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(11));
            builder.append(getChar(nick2.length()));
            builder.append(nick2);
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(12));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(4));
            builder.append(getChar(12));
            builder.append(getChar(1));
            builder.append(getChar(9));
            builder.append(getChar(102));
            builder.append(getChar(119));
            builder.append(getChar(32));
            builder.append(getChar(36));
            builder.append(getChar(84));
            builder.append(getChar(79));
            builder.append(getChar(75));
            builder.append(getChar(69));
            builder.append(getChar(78));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
     
            return builder.toString();
        }
        public static String removeVotebox(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(91));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            builder.append(getChar(255));
     
            return builder.toString();
        }
     
        public static String removeAllPrefixIcons(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(74));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
     
            return builder.toString();
        }
     
        public static String showButton(String channelName, List<String> params) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(65));
            builder.append(getChar(getLength(channelName)));
            builder.append(channelName);
            for (int i = 0; i < params.size(); i++) {
                String temp = params.get(i);
                String[] splitted = temp.split("~");
                String buttonText = splitted[0];
                String command = splitted[1];
     
                builder.append(getChar(11));
                builder.append(getChar(getLength(buttonText)));
                builder.append(buttonText);
                builder.append(getChar(255));
                builder.append(getChar(1));
                builder.append(getChar(getLength(command)));
                builder.append(command);
            }
            builder.append(getChar(12));
            builder.append(getChar(12));
     
            return builder.toString();
        }
     
        private static char getChar(int code) {
            return (char)code;
        }
     
        private static int getLength(String paramString) {
            return paramString.length();
        }
    }


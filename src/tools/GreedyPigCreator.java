	

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
     
    package tools;
     
    import java.text.NumberFormat;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Locale;
    import java.util.Map;
     
    /**
     *
     * @author Brainy
     */
    public class GreedyPigCreator {
        private static NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
     
        private static char getChar(int code) {
            return (char)code;
        }
     
        public static String setPictures() {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(179));
     
            List<String> pictures = new ArrayList<String>();
            for (int i = 0; i <= 8; i++) {
                pictures.add(String.format("pics/greedy-pig/gpig-explode-%s.gif", i));
            }
            for (int i = 0; i <= 8; i++) {
                pictures.add(String.format("pics/greedy-pig/gpig-laugh-%s.gif", i));
            }
            for (int i = 0; i <= 8; i++) {
                pictures.add(String.format("pics/greedy-pig/gpig-idle-%s.gif", i));
            }
            for (int i = 0; i <= 8; i++) {
                pictures.add(String.format("pics/greedy-pig/gpig-munch-%s.gif", i));
            }
            for (String p : pictures) {
                builder.append(getChar(11));
                builder.append(getChar(p.length()));
                builder.append(p);
            }
            builder.append(getChar(12));
     
            return builder.toString();
        }
     
        public static String init(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(99));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(18));
            builder.append(getChar(109));
            builder.append(getChar(97));
            builder.append(getChar(102));
            builder.append(getChar(105));
            builder.append(getChar(97));
            builder.append(getChar(47));
            builder.append(getChar(98));
            builder.append(getChar(108));
            builder.append(getChar(111));
            builder.append(getChar(111));
            builder.append(getChar(100));
            builder.append(getChar(98));
            builder.append(getChar(97));
            builder.append(getChar(114));
            builder.append(getChar(46));
            builder.append(getChar(103));
            builder.append(getChar(105));
            builder.append(getChar(102));
            builder.append(getChar(226));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(246));
            builder.append(getChar(183));
            builder.append(getChar(191));
            builder.append(getChar(230));
            builder.append(getChar(44));
            builder.append(getChar(65));
            builder.append(getChar(205));
            builder.append(getChar(110));
            builder.append(getChar(2));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(183));
            builder.append(getChar(3));
            builder.append(getChar(226));
            builder.append(getChar(120));
            builder.append(getChar(0));
            builder.append(getChar(230));
            builder.append(getChar(185));
            builder.append(getChar(1));
            builder.append(getChar(254));
            builder.append(getChar(255));
            builder.append(getChar(237));
            builder.append(getChar(233));
            builder.append(getChar(207));
            builder.append(getChar(0));
            builder.append(getChar(190));
            builder.append(getChar(138));
            builder.append(getChar(0));
            builder.append(getChar(24));
            builder.append(getChar(100));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(190));
            builder.append(getChar(210));
            builder.append(getChar(183));
            builder.append(getChar(64));
            builder.append(getChar(128));
            builder.append(getChar(44));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(95));
            builder.append(getChar(167));
            builder.append(getChar(217));
            builder.append(getChar(95));
            builder.append(getChar(167));
            builder.append(getChar(217));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(160));
            builder.append(getChar(160));
            builder.append(getChar(160));
            builder.append(getChar(55));
            builder.append(getChar(55));
            builder.append(getChar(55));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(152));
            builder.append(getChar(198));
            builder.append(getChar(230));
            builder.append(getChar(254));
            builder.append(getChar(209));
            builder.append(getChar(255));
            builder.append(getChar(253));
            builder.append(getChar(96));
            builder.append(getChar(255));
            builder.append(getChar(235));
            builder.append(getChar(55));
            builder.append(getChar(237));
     
            return builder.toString();
        }
     
        public static String show(String channelName) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(87));
            builder.append(getChar(channelName.length()));
            builder.append(channelName);
            builder.append(getChar(255));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(7));
            builder.append(getChar(2));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(7));
            builder.append(getChar(2));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(60));
            builder.append(getChar(255));
            builder.append(getChar(255));
            builder.append(getChar(1));
            builder.append(getChar(255));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(12));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(10));
            builder.append(getChar(12));
            builder.append(getChar(0));
            builder.append(getChar(255));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(0));
            builder.append(getChar(255));
     
            return builder.toString();
        }
     
        public static String initShow(String channelName, int knCount) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58)); // -> :
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(90)); // -> Z
            builder.append(getChar(channelName.length())); // -> 
            builder.append(channelName);
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(128)); // -> 
            builder.append(getChar(2)); // -> 
            if (channelName.length() < 6) {
                builder.append(getChar(String.format("%s Knuddels", nf.format(knCount)).length() - (6 - channelName.length()))); // -> 
            } else if (channelName.length() == 6) {
                builder.append(getChar(String.format("%s Knuddels", nf.format(knCount)).length())); // -> 
            } else {
                builder.append(getChar(String.format("%s Knuddels", nf.format(knCount)).length() + (channelName.length() - 6))); // -> 
            }
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(62)); // -> >
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(45)); // -> _
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(98)); // -> b
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(120)); // -> x
            builder.append(getChar(95)); // -> _
            builder.append(getChar(45)); // -> -
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(46)); // -> .
            builder.append(getChar(112)); // -> p
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(60)); // -> <
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(51)); // -> 3
            builder.append(getChar(62)); // -> >
            builder.append(getChar(82)); // -> R
            builder.append(getChar(73)); // -> I
            builder.append(getChar(71)); // -> G
            builder.append(getChar(72)); // -> H
            builder.append(getChar(84)); // -> T
            builder.append(getChar(60)); // -> <
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(176)); // -> °
            builder.append(getChar(176)); // -> °
            builder.append(getChar(91)); // -> [
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(54)); // -> 6
            builder.append(getChar(52)); // -> 4
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(93)); // -> ]
            builder.append(getChar(62)); // -> >
            builder.append(getChar(95)); // -> _
            builder.append(getChar(104)); // -> h
            builder.append(getChar(72)); // -> H
            builder.append(getChar(105)); // -> i
            builder.append(getChar(108)); // -> l
            builder.append(getChar(102)); // -> f
            builder.append(getChar(101)); // -> e
            builder.append(getChar(124)); // -> |
            builder.append(getChar(47)); // -> /
            builder.append(getChar(104)); // -> h
            builder.append(getChar(32)); // ->
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(60)); // -> <
            builder.append(getChar(114)); // -> r
            builder.append(getChar(176)); // -> °
            builder.append(getChar(95)); // -> _
            builder.append(getChar(32)); // ->
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(62)); // -> >
            builder.append(getChar(67)); // -> C
            builder.append(getChar(69)); // -> E
            builder.append(getChar(78)); // -> N
            builder.append(getChar(84)); // -> T
            builder.append(getChar(69)); // -> E
            builder.append(getChar(82)); // -> R
            builder.append(getChar(60)); // -> <
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(176)); // -> °
            builder.append(getChar(32)); // ->
            builder.append(getChar(176)); // -> °
            builder.append(getChar(62)); // -> >
            builder.append(getChar(116)); // -> t
            builder.append(getChar(114)); // -> r
            builder.append(getChar(97)); // -> a
            builder.append(getChar(110)); // -> n
            builder.append(getChar(115)); // -> s
            builder.append(getChar(112)); // -> p
            builder.append(getChar(97)); // -> a
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(116)); // -> t
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(120)); // -> x
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(104)); // -> h
            builder.append(getChar(95)); // -> _
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(46)); // -> .
            builder.append(getChar(119)); // -> w
            builder.append(getChar(95)); // -> _
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(46)); // -> .
            builder.append(getChar(103)); // -> g
            builder.append(getChar(105)); // -> i
            builder.append(getChar(102)); // -> f
            builder.append(getChar(60)); // -> <
            builder.append(getChar(62)); // -> >
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(45)); // -> _
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(105)); // -> i
            builder.append(getChar(100)); // -> d
            builder.append(getChar(108)); // -> l
            builder.append(getChar(101)); // -> e
            builder.append(getChar(45)); // -> _
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(121)); // -> y
            builder.append(getChar(95)); // -> _
            builder.append(getChar(45)); // -> -
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(55)); // -> 7
            builder.append(getChar(46)); // -> .
            builder.append(getChar(103)); // -> g
            builder.append(getChar(105)); // -> i
            builder.append(getChar(102)); // -> f
            builder.append(getChar(60)); // -> <
            builder.append(getChar(176)); // -> °
            builder.append(getChar(32)); // ->
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(75)); // -> K
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(62)); // -> >
            builder.append(getChar(123)); // -> {
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(125)); // -> }
            builder.append(getChar(32)); // ->
            builder.append(getChar(124)); // -> |
            builder.append(getChar(124)); // -> |
            builder.append(getChar(105)); // -> i
            builder.append(getChar(109)); // -> m
            builder.append(getChar(97)); // -> a
            builder.append(getChar(103)); // -> g
            builder.append(getChar(101)); // -> e
            builder.append(getChar(115)); // -> s
            builder.append(getChar(124)); // -> |
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(117)); // -> u
            builder.append(getChar(101)); // -> e
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(45)); // -> -
            builder.append(getChar(115)); // -> s
            builder.append(getChar(46)); // -> .
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(95)); // -> _
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(126)); // -> ~
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(117)); // -> u
            builder.append(getChar(101)); // -> e
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(45)); // -> -
            builder.append(getChar(115)); // -> s
            builder.append(getChar(95)); // -> _
            builder.append(getChar(104)); // -> h
            builder.append(getChar(111)); // -> o
            builder.append(getChar(118)); // -> v
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(46)); // -> .
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(95)); // -> _
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(126)); // -> ~
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(117)); // -> u
            builder.append(getChar(101)); // -> e
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(45)); // -> -
            builder.append(getChar(115)); // -> s
            builder.append(getChar(95)); // -> _
            builder.append(getChar(104)); // -> h
            builder.append(getChar(111)); // -> o
            builder.append(getChar(118)); // -> v
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(46)); // -> .
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(95)); // -> _
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(120)); // -> x
            builder.append(getChar(95)); // -> _
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(121)); // -> y
            builder.append(getChar(95)); // -> _
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(124)); // -> |
            builder.append(getChar(116)); // -> t
            builder.append(getChar(101)); // -> e
            builder.append(getChar(120)); // -> x
            builder.append(getChar(116)); // -> t
            builder.append(getChar(99)); // -> c
            builder.append(getChar(111)); // -> o
            builder.append(getChar(108)); // -> l
            builder.append(getChar(111)); // -> o
            builder.append(getChar(114)); // -> r
            builder.append(getChar(124)); // -> |
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(124)); // -> |
            builder.append(getChar(99)); // -> c
            builder.append(getChar(97)); // -> a
            builder.append(getChar(108)); // -> l
            builder.append(getChar(108)); // -> l
            builder.append(getChar(124)); // -> |
            builder.append(getChar(47)); // -> /
            builder.append(getChar(115)); // -> s
            builder.append(getChar(102)); // -> f
            builder.append(getChar(99)); // -> c
            builder.append(getChar(32)); // ->
            builder.append(channelName);
            builder.append(getChar(58)); // -> :
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(32)); // ->
            builder.append(getChar(102)); // -> f
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(124)); // -> |
            builder.append(getChar(119)); // -> w
            builder.append(getChar(105)); // -> i
            builder.append(getChar(100)); // -> d
            builder.append(getChar(116)); // -> t
            builder.append(getChar(104)); // -> h
            builder.append(getChar(124)); // -> |
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(124)); // -> |
            builder.append(getChar(110)); // -> n
            builder.append(getChar(111)); // -> o
            builder.append(getChar(98)); // -> b
            builder.append(getChar(111)); // -> o
            builder.append(getChar(114)); // -> r
            builder.append(getChar(100)); // -> d
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(124)); // -> |
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(124)); // -> |
            builder.append(getChar(104)); // -> h
            builder.append(getChar(101)); // -> e
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(116)); // -> t
            builder.append(getChar(104)); // -> h
            builder.append(getChar(124)); // -> |
            builder.append(getChar(51)); // -> 3
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(124)); // -> |
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(115)); // -> s
            builder.append(getChar(97)); // -> a
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(84)); // -> T
            builder.append(getChar(105)); // -> i
            builder.append(getChar(109)); // -> m
            builder.append(getChar(101)); // -> e
            builder.append(getChar(111)); // -> o
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(124)); // -> |
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(124)); // -> |
            builder.append(getChar(105)); // -> i
            builder.append(getChar(99)); // -> c
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(124)); // -> |
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(45)); // -> _
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(116)); // -> t
            builder.append(getChar(101)); // -> e
            builder.append(getChar(120)); // -> x
            builder.append(getChar(116)); // -> t
            builder.append(getChar(45)); // -> _
            builder.append(getChar(68)); // -> D
            builder.append(getChar(69)); // -> E
            builder.append(getChar(46)); // -> .
            builder.append(getChar(112)); // -> p
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(60)); // -> <
            builder.append(getChar(87)); // -> W
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(52)); // -> 4
            builder.append(getChar(176)); // -> °
            builder.append(getChar(95)); // -> _
            builder.append(getChar(66)); // -> B
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(105)); // -> i
            builder.append(getChar(116)); // -> t
            builder.append(getChar(115)); // -> s
            builder.append(getChar(32)); // ->
            builder.append(getChar(103)); // -> g
            builder.append(getChar(101)); // -> e
            builder.append(getChar(102)); // -> f
            builder.append(getChar(252)); // -> ü
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(116)); // -> t
            builder.append(getChar(58)); // -> :
            builder.append(getChar(32)); // ->
            builder.append(getChar(95)); // -> _
            builder.append(nf.format(knCount));
            builder.append(getChar(32)); // ->
            builder.append(getChar(75)); // -> K
            builder.append(getChar(110)); // -> n
            builder.append(getChar(117)); // -> u
            builder.append(getChar(100)); // -> d
            builder.append(getChar(100)); // -> d
            builder.append(getChar(101)); // -> e
            builder.append(getChar(108)); // -> l
            builder.append(getChar(115)); // -> s
            builder.append(getChar(95)); // -> _
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(62)); // -> >
            builder.append(getChar(76)); // -> L
            builder.append(getChar(69)); // -> E
            builder.append(getChar(70)); // -> F
            builder.append(getChar(84)); // -> T
            builder.append(getChar(60)); // -> <
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(52)); // -> 4
            builder.append(getChar(43)); // -> +
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(51)); // -> 3
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(255)); // -> ÿ
     
            return builder.toString();
        }
     
        public static String append(String channelName, int knCount, String greedyPic, List<String> greedy) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58)); // -> :
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(90)); // -> Z
            builder.append(getChar(channelName.length())); // -> 
            builder.append(channelName);
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(128)); // -> 
            StringBuilder dicedBuilder = new StringBuilder();
                try {
                    for (int i = 0; i < greedy.size(); i++) {
                        String[] param = greedy.get(i).split("\\|");
                        String user = param[0].replace("<", "\\<");
                        Integer dicedNum = Integer.parseInt(param[1]);
                        dicedBuilder.append(String.format("°+9502°_°>_h%s|/serverpp %s|/w %s<°°b° würfelt %s.#", user, user, user, nf.format(dicedNum)));
                    }
                } catch (Exception e) {
                }
     
            String appendText = dicedBuilder.toString();
            int knLength = nf.format(knCount).length();
            int appendLength = appendText.length();
            int all = knLength + appendLength + channelName.length() + 514;
            if (greedyPic.length() < 23) {
                all = all - (23 - greedyPic.length());
            }  else if (greedyPic.length() > 23) {
                all = all + (greedyPic.length() - 23);
            }
            int n = (int)(all / 255);
            int o = all - (n * 255);
            builder.append(getChar(n)); // -> 
            builder.append(getChar(o));
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(62)); // -> >
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(45)); // -> _
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(98)); // -> b
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(120)); // -> x
            builder.append(getChar(95)); // -> _
            builder.append(getChar(45)); // -> -
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(46)); // -> .
            builder.append(getChar(112)); // -> p
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(60)); // -> <
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(51)); // -> 3
            builder.append(getChar(62)); // -> >
            builder.append(getChar(82)); // -> R
            builder.append(getChar(73)); // -> I
            builder.append(getChar(71)); // -> G
            builder.append(getChar(72)); // -> H
            builder.append(getChar(84)); // -> T
            builder.append(getChar(60)); // -> <
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(176)); // -> °
            builder.append(getChar(176)); // -> °
            builder.append(getChar(91)); // -> [
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(54)); // -> 6
            builder.append(getChar(52)); // -> 4
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(93)); // -> ]
            builder.append(getChar(62)); // -> >
            builder.append(getChar(95)); // -> _
            builder.append(getChar(104)); // -> h
            builder.append(getChar(72)); // -> H
            builder.append(getChar(105)); // -> i
            builder.append(getChar(108)); // -> l
            builder.append(getChar(102)); // -> f
            builder.append(getChar(101)); // -> e
            builder.append(getChar(124)); // -> |
            builder.append(getChar(47)); // -> /
            builder.append(getChar(104)); // -> h
            builder.append(getChar(32)); // ->
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(60)); // -> <
            builder.append(getChar(114)); // -> r
            builder.append(getChar(176)); // -> °
            builder.append(getChar(95)); // -> _
            builder.append(getChar(32)); // ->
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(62)); // -> >
            builder.append(getChar(67)); // -> C
            builder.append(getChar(69)); // -> E
            builder.append(getChar(78)); // -> N
            builder.append(getChar(84)); // -> T
            builder.append(getChar(69)); // -> E
            builder.append(getChar(82)); // -> R
            builder.append(getChar(60)); // -> <
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(176)); // -> °
            builder.append(getChar(32)); // ->
            builder.append(getChar(176)); // -> °
            builder.append(getChar(62)); // -> >
            builder.append(getChar(116)); // -> t
            builder.append(getChar(114)); // -> r
            builder.append(getChar(97)); // -> a
            builder.append(getChar(110)); // -> n
            builder.append(getChar(115)); // -> s
            builder.append(getChar(112)); // -> p
            builder.append(getChar(97)); // -> a
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(116)); // -> t
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(120)); // -> x
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(104)); // -> h
            builder.append(getChar(95)); // -> _
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(46)); // -> .
            builder.append(getChar(119)); // -> w
            builder.append(getChar(95)); // -> _
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(46)); // -> .
            builder.append(getChar(103)); // -> g
            builder.append(getChar(105)); // -> i
            builder.append(getChar(102)); // -> f
            builder.append(getChar(60)); // -> <
            builder.append(getChar(62)); // -> >
            builder.append(greedyPic);
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(121)); // -> y
            builder.append(getChar(95)); // -> _
            builder.append(getChar(45)); // -> -
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(55)); // -> 7
            builder.append(getChar(46)); // -> .
            builder.append(getChar(103)); // -> g
            builder.append(getChar(105)); // -> i
            builder.append(getChar(102)); // -> f
            builder.append(getChar(60)); // -> <
            builder.append(getChar(176)); // -> °
            builder.append(getChar(32)); // ->
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(75)); // -> K
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(62)); // -> >
            builder.append(getChar(123)); // -> {
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(125)); // -> }
            builder.append(getChar(32)); // ->
            builder.append(getChar(124)); // -> |
            builder.append(getChar(124)); // -> |
            builder.append(getChar(105)); // -> i
            builder.append(getChar(109)); // -> m
            builder.append(getChar(97)); // -> a
            builder.append(getChar(103)); // -> g
            builder.append(getChar(101)); // -> e
            builder.append(getChar(115)); // -> s
            builder.append(getChar(124)); // -> |
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(117)); // -> u
            builder.append(getChar(101)); // -> e
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(45)); // -> -
            builder.append(getChar(115)); // -> s
            builder.append(getChar(46)); // -> .
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(95)); // -> _
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(126)); // -> ~
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(117)); // -> u
            builder.append(getChar(101)); // -> e
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(45)); // -> -
            builder.append(getChar(115)); // -> s
            builder.append(getChar(95)); // -> _
            builder.append(getChar(104)); // -> h
            builder.append(getChar(111)); // -> o
            builder.append(getChar(118)); // -> v
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(46)); // -> .
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(95)); // -> _
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(126)); // -> ~
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(117)); // -> u
            builder.append(getChar(101)); // -> e
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(45)); // -> -
            builder.append(getChar(115)); // -> s
            builder.append(getChar(95)); // -> _
            builder.append(getChar(104)); // -> h
            builder.append(getChar(111)); // -> o
            builder.append(getChar(118)); // -> v
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(46)); // -> .
            builder.append(getChar(101)); // -> e
            builder.append(getChar(110)); // -> n
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(95)); // -> _
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(120)); // -> x
            builder.append(getChar(95)); // -> _
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(46)); // -> .
            builder.append(getChar(109)); // -> m
            builder.append(getChar(121)); // -> y
            builder.append(getChar(95)); // -> _
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(124)); // -> |
            builder.append(getChar(116)); // -> t
            builder.append(getChar(101)); // -> e
            builder.append(getChar(120)); // -> x
            builder.append(getChar(116)); // -> t
            builder.append(getChar(99)); // -> c
            builder.append(getChar(111)); // -> o
            builder.append(getChar(108)); // -> l
            builder.append(getChar(111)); // -> o
            builder.append(getChar(114)); // -> r
            builder.append(getChar(124)); // -> |
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(44)); // -> ,
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(124)); // -> |
            builder.append(getChar(99)); // -> c
            builder.append(getChar(97)); // -> a
            builder.append(getChar(108)); // -> l
            builder.append(getChar(108)); // -> l
            builder.append(getChar(124)); // -> |
            builder.append(getChar(47)); // -> /
            builder.append(getChar(115)); // -> s
            builder.append(getChar(102)); // -> f
            builder.append(getChar(99)); // -> c
            builder.append(getChar(32)); // ->
            builder.append(channelName);
            builder.append(getChar(58)); // -> :
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(32)); // ->
            builder.append(getChar(102)); // -> f
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(124)); // -> |
            builder.append(getChar(119)); // -> w
            builder.append(getChar(105)); // -> i
            builder.append(getChar(100)); // -> d
            builder.append(getChar(116)); // -> t
            builder.append(getChar(104)); // -> h
            builder.append(getChar(124)); // -> |
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(124)); // -> |
            builder.append(getChar(110)); // -> n
            builder.append(getChar(111)); // -> o
            builder.append(getChar(98)); // -> b
            builder.append(getChar(111)); // -> o
            builder.append(getChar(114)); // -> r
            builder.append(getChar(100)); // -> d
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(124)); // -> |
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(124)); // -> |
            builder.append(getChar(104)); // -> h
            builder.append(getChar(101)); // -> e
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(116)); // -> t
            builder.append(getChar(104)); // -> h
            builder.append(getChar(124)); // -> |
            builder.append(getChar(51)); // -> 3
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(124)); // -> |
            builder.append(getChar(100)); // -> d
            builder.append(getChar(105)); // -> i
            builder.append(getChar(115)); // -> s
            builder.append(getChar(97)); // -> a
            builder.append(getChar(98)); // -> b
            builder.append(getChar(108)); // -> l
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(84)); // -> T
            builder.append(getChar(105)); // -> i
            builder.append(getChar(109)); // -> m
            builder.append(getChar(101)); // -> e
            builder.append(getChar(111)); // -> o
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(124)); // -> |
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(124)); // -> |
            builder.append(getChar(105)); // -> i
            builder.append(getChar(99)); // -> c
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(124)); // -> |
            builder.append(getChar(103)); // -> g
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(101)); // -> e
            builder.append(getChar(100)); // -> d
            builder.append(getChar(121)); // -> y
            builder.append(getChar(45)); // -> _
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(47)); // -> /
            builder.append(getChar(103)); // -> g
            builder.append(getChar(112)); // -> p
            builder.append(getChar(105)); // -> i
            builder.append(getChar(103)); // -> g
            builder.append(getChar(45)); // -> _
            builder.append(getChar(98)); // -> b
            builder.append(getChar(117)); // -> u
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(111)); // -> o
            builder.append(getChar(110)); // -> n
            builder.append(getChar(116)); // -> t
            builder.append(getChar(101)); // -> e
            builder.append(getChar(120)); // -> x
            builder.append(getChar(116)); // -> t
            builder.append(getChar(45)); // -> _
            builder.append(getChar(68)); // -> D
            builder.append(getChar(69)); // -> E
            builder.append(getChar(46)); // -> .
            builder.append(getChar(112)); // -> p
            builder.append(getChar(110)); // -> n
            builder.append(getChar(103)); // -> g
            builder.append(getChar(60)); // -> <
            builder.append(getChar(87)); // -> W
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(52)); // -> 4
            builder.append(getChar(176)); // -> °
            builder.append(getChar(95)); // -> _
            builder.append(getChar(66)); // -> B
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(101)); // -> e
            builder.append(getChar(105)); // -> i
            builder.append(getChar(116)); // -> t
            builder.append(getChar(115)); // -> s
            builder.append(getChar(32)); // ->
            builder.append(getChar(103)); // -> g
            builder.append(getChar(101)); // -> e
            builder.append(getChar(102)); // -> f
            builder.append(getChar(252)); // -> ü
            builder.append(getChar(116)); // -> t
            builder.append(getChar(116)); // -> t
            builder.append(getChar(101)); // -> e
            builder.append(getChar(114)); // -> r
            builder.append(getChar(116)); // -> t
            builder.append(getChar(58)); // -> :
            builder.append(getChar(32)); // ->
            builder.append(getChar(95)); // -> _
            builder.append(nf.format(knCount));
            builder.append(getChar(32)); // ->
            builder.append(getChar(75)); // -> K
            builder.append(getChar(110)); // -> n
            builder.append(getChar(117)); // -> u
            builder.append(getChar(100)); // -> d
            builder.append(getChar(100)); // -> d
            builder.append(getChar(101)); // -> e
            builder.append(getChar(108)); // -> l
            builder.append(getChar(115)); // -> s
            builder.append(getChar(95)); // -> _
            builder.append(getChar(35)); // -> #
            builder.append(getChar(176)); // -> °
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(50)); // -> 2
            builder.append(getChar(62)); // -> >
            builder.append(getChar(76)); // -> L
            builder.append(getChar(69)); // -> E
            builder.append(getChar(70)); // -> F
            builder.append(getChar(84)); // -> T
            builder.append(getChar(60)); // -> <
            builder.append(getChar(43)); // -> +
            builder.append(getChar(57)); // -> 9
            builder.append(getChar(53)); // -> 5
            builder.append(getChar(49)); // -> 1
            builder.append(getChar(52)); // -> 4
            builder.append(getChar(43)); // -> +
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(48)); // -> 0
            builder.append(getChar(51)); // -> 3
            builder.append(getChar(176)); // -> °
            builder.append(getChar(35)); // -> #
            builder.append(appendText);
            builder.append(getChar(255)); // -> ÿ
     
            return builder.toString();
        }
     
        public static String finalDice(String channelName, int knCount, List<String> greedy) {
            StringBuilder builder = new StringBuilder();
     
            builder.append(getChar(58)); // -> :
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(0)); // -> 0
            builder.append(getChar(90)); // -> Z
            builder.append(getChar(channelName.length())); // -> 
            builder.append(channelName);
            builder.append(getChar(255)); // -> ÿ
            builder.append(getChar(128)); // -> 
            StringBuilder dicedBuilder = new StringBuilder();
            try {
                    for (int i = 0; i < greedy.size(); i++) {
                        String[] params = greedy.get(i).split("\\|");
                        String user = params[0].replace("<", "\\<");
                        Integer dicedNum = Integer.parseInt(params[1]);
                       
                         dicedBuilder.append(String.format("°+9502°_°>_h%s|/serverpp %s|/w %s<°°b° würfelt %s.#", user, user, user, nf.format(dicedNum)));
                    }
                } catch (Exception e) {
                }
     
     
            String appendText = dicedBuilder.toString();
            String kCode = String.format("°+9011>greedy-pig/gpig-bg-001...mx_-5.png<°#°+9223>RIGHT<12°°[100,164,255]>_hHilfe|/h greedypig<r°_ #°>CENTER<°#°+9505° °>transparent1x1...h_110.w_0.gif<>greedy-pig/gpig-explode-8...my_-28.alwayscopy.gif<°°>gpig_explode_001.mp<°°>.gif<° ##°+9030°#°+9514°_Bereits gefüttert: _%s Knuddels_#°12>LEFT<+9514+0003°#%s", nf.format(knCount), appendText);
            int all = kCode.length() - 1;
            int n = (int)(all / 255);
            int o = all - (n * 255);
            builder.append(getChar(n)); // -> 
            builder.append(getChar(o)); // -> 
            builder.append(kCode);
            builder.append(getChar(255)); // -> ÿ
     
            return builder.toString();
        }
    }


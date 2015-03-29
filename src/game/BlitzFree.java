package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tools.GameUtilities;
import tools.PacketCreator;
import tools.Toolbar;
import starlight.Channel;
import starlight.Client;
import starlight.Server;

public class BlitzFree implements Game, Runnable {

    /* TODO
     * # Gewürfelte Augen Verlauf zum Überprüfen ob jemand ein Auge schon geworfen hatte.
     * Letzte Runde
     * 
     */
    
    private final int     MIN_PLAYER_COUNT         =     2;
    private final int     MAX_PLAYER_COUNT         =     20;
    private final int     ROUND_LOGON_TIME         =     15000; // Zeit für die Anmeldung. (15sek)
    private final int     ROUND_LOGON_TIME_ADD     =     2000; // Zeit die pro neu-angemeldeten Spieler addiert wird. (2sek)
    private final int    ROUND_DICE_TIME            =    15000; // Gibt die Zeit an, in der die Spieler Zeit haben zu würfeln. (15sek)
    private final String ICON_PLAYER             =     "nicklistblitz.gif";
    private final int    WIN_POINTS                 =     5; // Die Punkte die pro Runde vergeben werden.
    
    private Channel _channel;
    private Thread _thread;
    /* -----------------------------------*/
    private boolean _request; // Gibt an ob ein Spieler eine neue Runde starten möchte.
    private boolean _playing; // Gibt an ob ein Spiel läuft.
    private ArrayList<Client> _activeClients; // Gibt alle Spieler an.    
    private ArrayList<Client> _players;
    private long _waitTime;
    private long _startTime; // Gibt die Zeit an, an der die Runden-Anmeldung/eine neue Spielrunde erfolgte.
    private int _roundNumber; // Gibt die aktuell laufende Spielrunde an.
    private int _loseNumber; // Gibt die Wurfzahl an, bei der ein Spieler rausfliegt.
    private int _jackpot;
    private int _smiley;
    /* -----------------------------------*/    
    
    private final String MSG_GAME_START = "°+0065>mychannel/ico_dice-6_001.gif<°°+0100°°20°_Mögen die Spiele beginnen...!_";
    
    private final String MSG_ROUND_START = 
        "°+0065°°20°_Die Anmeldung°°°20° für eine neue Runde °BB20°Blitz\"!\"°20°°°°20° läuft..._"
        + "°r°#(gestartet von %s)##"
        + "°+0065°Wer bei unserem Würfelklassiker _Blitz!_ (%d - %d Spieler) mitspielen möchte, gibt bitte jetzt_ °BB>/blitz join|/blitz join<°_°r° ein.";
    
    private final String MSG_ROUND_NEW = "°+065°°20°_Achtung, Runde %d beginnt!_°r°##"
                      //  + "°+075°(alle haben nun %d Sekunden Zeit zu °BB>würfeln|/blitz dice<r°.)#"
                        + "°+075°Wer eine °20BB°_- %d -_°r° würfelt oder dieselbe Zahl würfelt wie ein vorheriger Spieler, scheidet aus... Zeit zum Würfeln: %d Sekunden.";
    
    private final String MSG_ROUND_END = "°20°_Runde %d ist beendet!_#°16°#"
            + "°+0045°%s"
            + "#°+0000°(Rausgeflogen sind: %s)";
    
    private final String MSG_GAME_HELP = "Folgende Befehle stehen für _°B°°>blitz!<°_:#"
            + "_/blitz join_ - Startet eine Runde oder meldet sich für eine an.#"
            + "_/help blitz_ - Blendet eine Hilfe ein.";
    
    private final String MSG_GAME_NOT =  "°+0065°°BB20°_Blitz\"!\"°°°20°  findet leider nicht statt°°°20°._°r°#"
            + "°+0065°%s";
    
    
    /* -----------------------------------*/    
    public BlitzFree(Channel pChannel) {
        _channel = pChannel;
    }
    
    private void start() {        
        _thread = new Thread(this);
        _thread.start();
    }
    
    private void startRound(Client pClient) {    
        
        _activeClients = new ArrayList<Client>();
        _players = new ArrayList<Client>();    
        
        addClient(pClient, false);    
        start();            
        pClient.send(Toolbar.ToolbarString(_channel.getName(), getToolbar(3)));        
    }
    
    private void stop() {
        _request = false;
        _playing = false;
        _activeClients = null;
        _players = null;
        _waitTime = 0L;
        _startTime = 0L;
        _roundNumber = 0;
        _loseNumber = 0;
                
        for (Client client : _channel.getClients()) {
            client.setBlitzCan(false);
            client.setBlitzDice(0);
            client.resetBlitzHistory();
            client.send(PacketCreator.removePrefixIcons(_channel.getName()));
            client.send(Toolbar.ToolbarString(_channel.getName(), getToolbar(0)));
        }
                
    }
    
    @Override
    public void run() {
        _sleep(50);
        
        _request = true;    
        
        _startTime = System.currentTimeMillis();        
        _waitTime = ROUND_LOGON_TIME;
        
        _jackpot = 0;
        _smiley = 0;
        
        _channel.broadcastButlerMessage(
                String.format(MSG_ROUND_START, 
                        getClickableNickString(_activeClients.get(0).getName()), MIN_PLAYER_COUNT, MAX_PLAYER_COUNT));    
        
        String toolbarWuerfel = Toolbar.ToolbarString(_channel.getName(), getToolbar(1));
        for (Client client : _channel.getClients()) {
            if (!_activeClients.contains(client)) {
                client.send(toolbarWuerfel);
            }
        }

        /** -- ------------------- -- **/
        while (_request || _playing) {
            _sleep(2000);
            if (_playing) {
                            
                if (_activeClients.size() == 1) {
                    
                    Client client = _activeClients.get(0);
                    
                    int winPoints = _roundNumber * WIN_POINTS;
                    client.increaseBlitzPoints(winPoints);
                    _channel.broadcastButlerMessage(String.format(
                              "°+0065>mychannel/ico_dice-6_001.gif<°°+0100°°18°°BB°_%s hat das Spiel gewonnen_.°r°#"
                              + "%s bekommt für den Sieg °16BB°_%d_°r° Punkte."
                            , getClickableNickString(_activeClients.get(0).getName()), client.getGenderLabel(),winPoints));
                    
                    _activeClients.clear();
                    
                    continue;
                    
                } else if (_activeClients.isEmpty()) {
                    _channel.broadcastButlerMessage(
                              "°+0065>mychannel/ico_dice-6_001.gif<°°+0100°°20°_Das Spiel ist zu Ende._"
                            );
                    break;
                }
                                
                /* -- True, wenn eine neue Runde gestartet wurde -- */
                if (_startTime > 0) {
                    
                    boolean all_played = false;
                    int count = 0;
                    for (Client player : _activeClients) {
                        if (player.getBlitzDice() > 0) {
                            count ++;
                        }
                    }
                    if (count == _activeClients.size()) {
                        all_played = true;
                    }
                    
                    long time = System.currentTimeMillis() - _startTime;
                    /* -- True, wenn die Zeit umgelaufen ist für eine Runde -- */
                    if (all_played || time >= ROUND_DICE_TIME) {    
                        
                        if (all_played) {
                            _sleep(300);
                        }
                        
                        /* =============================================
                         *    Rundenzeit ist um. Werte Ergebnisse aus.
                         * =============================================
                         */
                                        
                        
                        // Kein Spieler wurde rausgeworfen
                        if (_players.size() == _activeClients.size()) {
                                
                            _players.clear();
                            
                            for (Client client : _activeClients) {
                                client.setBlitzDice(0);
                                client.setBlitzCan(true);
                            }
                            
                            _startTime = System.currentTimeMillis();
                            
                            _channel.broadcastButlerMessage("Alle Spieler dürfen nun nocheinmal würfeln.");
                            
                            continue;
                        }
                        
                        
                        
                        StringBuilder playerString = new StringBuilder();                                    
                        // Speicher alle rausgeworfenen Spieler und entferne diese dann aus der Liste der aktiven Spieler.
                        
                        List<String> removedPlayersList = new ArrayList<String>();
                        
                        for (Iterator<Client> activeClientsIterator = _activeClients.iterator(); activeClientsIterator.hasNext();) {                            
                            Client client = activeClientsIterator.next();
                            if (!_players.contains(client)) { // Spieler wurde rausgeworfen
                                playerString.append(getClickableNickString(client.getName()));
                                if (!client.getName().equals(_activeClients.get(_activeClients.size()-1).getName())){
                                    playerString.append(", ");
                                }
                                removedPlayersList.add(client.getName());
                                activeClientsIterator.remove();
                            }
                        }
                                    

                        for (Client client : _channel.getClients()) {
                            client.setBlitzCan(false);
                            client.setBlitzDice(0);
                            client.resetBlitzHistory();
                            client.send(PacketCreator.removePrefixIcons(_channel.getName()));
                            client.send(PacketCreator.showPrefixIcons(_channel.getName(), removedPlayersList,true));
                            client.send(Toolbar.ToolbarString(_channel.getName(), getToolbar(0)));
                        }
                            
                        if (_activeClients.size() > 1) {
                            
                            String removedPlayers = playerString.toString();
                            playerString = new StringBuilder();
                            // Speicher alle weiter-kommenden Spieler
                            for (Client client : _activeClients) {
                                playerString.append("- ").append(getClickableNickString(client.getName())).append("#");
                                client.setBlitzDice(0);
                                client.setBlitzCan(true);
                                client.resetBlitzHistory();
                            }
                            
                            String playersLeft = playerString.toString();
                            playerString = null;
                            
                            _channel.broadcastButlerMessage(String.format(
                                    MSG_ROUND_END
                                    , _roundNumber, playersLeft, removedPlayers));
                                                                        
                        }
                        
                        _startTime = 0L;                                        
                    }
                    continue;
                }
                
                /* =============================================
                 *    Neue Runde
                 * =============================================
                 */
                
                _players.clear();
                _loseNumber = GameUtilities.getRandomNumber(1, 15);                            
                _startTime = System.currentTimeMillis();
                _roundNumber++;        
                
                _channel.broadcastButlerMessage(String.format(MSG_ROUND_NEW
                        , _roundNumber, _loseNumber, ROUND_DICE_TIME / 1000));
                
                toolbarWuerfel = Toolbar.ToolbarString(_channel.getName(), getToolbar(4));
                for (Client client : _activeClients) {
                    client.send(toolbarWuerfel);
                    client.setBlitzDice(0);
                    client.setBlitzCan(true);
                    client.resetBlitzHistory();
                }
                
                
                                        
            } else {
                
                long time = System.currentTimeMillis() - _startTime;
                if (time >= _waitTime) { // Prüfe ob die maximale Anmeldedauer vorbei ist
                    if (_activeClients.size() >= MIN_PLAYER_COUNT) { // Sind mindestens X Spieler dabei?
                        
                        /* =============================================
                         *    Neues Spiel wird gestartet.
                         * =============================================
                         */
                        
                        _request = false;
                        _playing = true;
                                
                        _channel.broadcastButlerMessage(MSG_GAME_START);
                        _startTime = 0L;
                        _roundNumber = 0;    
                        
                        // An alle die NICHT mitspielen
                        toolbarWuerfel = Toolbar.ToolbarString(_channel.getName(), getToolbar(2));
                        for (Client client : _channel.getClients()) {
                            if (!_activeClients.contains(client)) {
                                
                            }
                        }
                        
                        _sleep(1000);
                                                                        
                    } else { // Nicht genügend Spieler vorhanden

                        List<String> activePlayerNames = getPlayerNames();
                        StringBuilder kndNames = new StringBuilder();
                        for (String playername : activePlayerNames) {
                            kndNames.append(getClickableNickString(playername));
                            if (!playername.equals(activePlayerNames.get(activePlayerNames.size()-1))){
                                kndNames.append(", ");
                            }
                        }
                        
                        String playerNames = kndNames.toString();
                        kndNames = null;
                        
                        if (activePlayerNames.size() <= 0) { // Sollte wegen ersten Spieler nie auftreten, aber wer weiß :D
                            playerNames = "Es haben sich _keine Spieler_ angemeldet.";
                        } else {
                          playerNames = "Es haben sich lediglich 1 Spieler angemeldet (2 Spieler wären mindestens notwendig).";
                        }                        
                        activePlayerNames = null;
                        
                        _channel.broadcastButlerMessage(String.format(MSG_GAME_NOT, playerNames));
                        
                        break;                                        
                    }
                    
                }
            }
        }
        // Spiel zu Ende
        stop();
    }
            
    private void addClient(Client pClient) { addClient(pClient, true); }
    
    private void addClient(Client pClient, boolean pOut) {
        _activeClients.add(pClient);
        
        if (_waitTime <= (_waitTime + (ROUND_LOGON_TIME_ADD * 2))) {
            _waitTime += ROUND_LOGON_TIME_ADD;
        }
        
        if (pOut) {
            pClient.sendButlerMessage(_channel.getName(), "Du bist nun _für Blitz! angemeldet_.");        
        }
        
        pClient.send(Toolbar.ToolbarString(_channel.getName(), getToolbar(3)));
        
        List<String> playerNames = new ArrayList<String>();
        playerNames.add(pClient.getName() + "~" + ICON_PLAYER);
        
        String showPrefixIcons = PacketCreator.showPrefixIcons(_channel.getName(), playerNames,true);
            
        for (Client client : _channel.getClients()) {
            client.send(showPrefixIcons);
        }
    }
        
    @Override
    public boolean parseCommand(String pCommand, Client pClient) {        
        String lcCommand = pCommand.toLowerCase();
                
        if (!lcCommand.startsWith("/blitz")){
            return false;
        }
        
        if (lcCommand.length() <= "/blitz ".length()) {
            pClient.sendButlerMessage(_channel.getName(), MSG_GAME_HELP);
        }
        
        String arg = lcCommand.substring("/blitz ".length());
        
        
        /** Befehle filtern
         *     -------
         *     "/blitz join" - An Runde anmelden (falls bereits eine Runde läuft)
         *     -------
         */
        
        if (arg.equalsIgnoreCase("dice") && pClient.getBlitzCan()) {
            
            String result = "";
            boolean out = false;
            pClient.setBlitzCan(false);
            
            if (_activeClients.size() == 1) {
                pClient.setBlitzDice(GameUtilities.getRandomNumber(1, 75));    
                
                _channel.broadcastAction(getClickableNickString(pClient.getName()), 
                        String.format(
                                " rollt einen W75-Würfel...: °16B°_%d_°r° %s", 
                                pClient.getBlitzDice(), 
                                "")
                                );
                
            } else {
                pClient.setBlitzDice(GameUtilities.getRandomNumber(1, 15));                
                
                Client sameNumber = null;
                
                if (pClient.getBlitzDice() == _loseNumber) {
                    
                    result = "- Würfelt die Zahl die zum Rauswurf führt und °RR°_fliegt somit aus der Runde_°°.";
                    out = true;
                                    
                } else { // if (_players.size() > 0) {

                    for (Client player : _activeClients) {
                        if (player == pClient) {
                            continue;
                        }
                        
                        if (player.hasBlitzDice(pClient.getBlitzDice())) {    
                            sameNumber = player;
                            result = " - Würfelt dieselbe Zahl wie %s und °RR°_scheidet kläglich aus_°°.";
                            out = true;
                        }
                    }
                }            
                
                _channel.broadcastAction(getClickableNickString(pClient.getName()), 
                        String.format(
                                " rollt einen W15-Würfel...: °16BB°_%d_°r° %s", 
                                pClient.getBlitzDice(), 
                                String.format(result, getClickableNickString(sameNumber != null ? sameNumber.getName() : "")))
                                );
                
                if (!out) {                    
                    _players.add(pClient);
                }
            
        }
            
        } else if (arg.equalsIgnoreCase("join")){
            if (_request) {
                if (pClient.getSpielsperre() != 0) {
                    pClient.sendButlerMessage(_channel.getName(), "Du bist momentan für alle Spiele _gesperrt_.");
                if(Server.get().checkCcm(pClient.getName(), _channel, 3)) {
                   pClient.sendButlerMessage(_channel.getName(), "Wenn du gemutet bist, kannst du nicht spielen.");
                   return false;
    	        }
                } else if (_activeClients.contains(pClient)) {    
                    pClient.sendButlerMessage(_channel.getName(), "Du bist bereits bei Blitz! angemeldet.");                    
                } else {
                    addClient(pClient);
                }
            } else if (!_playing) {
                startRound(pClient);
            } else {
                pClient.sendButlerMessage(_channel.getName(), "Es _läuft bereits ein Spiel_. _Bitte warte_ bis diese zu Ende ist.");
            }
        }
        
        return false;
    }
    
    @Override
    public boolean parsePublicMessage(String pMessage, Client pClient) { 
        String lcMessage = pMessage.toLowerCase();
        
        /** Nachrichten filtern
         *     -------
         *  "james blitz" - Neue Runde Blitz! starten
         *     -------
         */
        
        if (_request) {
            
            pClient.sendButlerMessage(_channel.getName(), "Es _läuft bereits ein Spiel_. °BB>Melde dich jetzt an!|/blitz join<r°");
            
        } else if (!_playing && !_request && lcMessage.contains(Server.get().getButler().getName().toLowerCase()) && lcMessage.contains("blitz")) {
             
               if(Server.get().checkCcm(pClient.getName(), _channel, 3)) {
                  pClient.sendButlerMessage(_channel.getName(), "Wenn du gemutet bist, kannst du nicht spielen.");
                  return false;
    	}    
            
            startRound(pClient);    
        
            return true;
        } else if (_activeClients != null && !_activeClients.contains(pClient)) {
            
          //  pClient.sendButlerMessage(_channel.getName(), "Nur aktuellen Spielern ist es gestattet öffentliche Nachrichten zu senden.");
            
            return false;            
        }
        
        return true; 
    }
    
    @Override
    public void onJoin(Client pClient) {    
        // Gebe den neu-gejointen Spieler die Möglichkeit sich noch anzumelden.
        if (_request) {
            if ((System.currentTimeMillis() - _startTime) >= (_waitTime - 2000)) {
                _waitTime += ROUND_LOGON_TIME_ADD;
            }
        }
                
        /** TOOLBAR **/
        
        List<Toolbar.Button> toolbar;
        
        if (_request) { // Eine Anfrage zur Start einer Blitz!-Runde wurde bereits gestartet        
            toolbar = getToolbar(1);            
        } else if (_playing) { // Eine Blitz!-Runde ist derzeit am Laufen            
            toolbar = getToolbar(2);        
        } else { // Nichts läuft            
            toolbar = getToolbar(0);            
        }
        
        pClient.send(Toolbar.ToolbarString(_channel.getName(), toolbar));    
        
        /** PREFIX_ICONS **/
        
        if (_activeClients == null || _activeClients.isEmpty()) {
            return;
        }
        
        List<String> playerNames = new ArrayList<String>();
        for (Client player : _activeClients) {
            playerNames.add(player.getName() + "~" + ICON_PLAYER);
        }
    
        pClient.send(PacketCreator.showPrefixIcons(_channel.getName(), playerNames,true));
        
        /** MESSAGE: START ROUND **/
        pClient.sendButlerMessage(_channel.getName(), String.format(MSG_ROUND_START, 
                        getClickableNickString(_activeClients.get(0).getName()), MIN_PLAYER_COUNT, MAX_PLAYER_COUNT));    
    }
    
    @Override
    public void onLeave(Client pClient) {
    }
    
    /*
     * ===============================================
     */
    
    private List<String> getPlayerNames() {
        return getPlayerNames("");    
    }
    
    private List<String> getPlayerNames(String pAdd) {
        List<String> playerNames = new ArrayList<String>();
        
        for (Client client : _activeClients) {
            playerNames.add(client.getName() + pAdd);
        }
        
        return playerNames;
    }
    
    private static String getClickableNickString(String pName) {
        return "°>_h" + pName + "|/m \"|/w \"<°";
    }
    
    private List<Toolbar.Button> getToolbar(int pState) {
        List<Toolbar.Button> toolbar = new ArrayList<Toolbar.Button>();
        switch (pState){
        case 0: 
            toolbar.add(new Toolbar.Button("Neue Runde starten", "/a "+Server.get().getButler().getName().toLowerCase()+" blitz", "loginknuddelscent/knuddel_small.gif", false));  
            break;
        case 1:
            toolbar.add(new Toolbar.Button("An neue Blitz!-Runde anmelden", "/blitz join", "loginknuddelscent/knuddel_small.gif", false));  
            break;
        case 2:
            toolbar.add(new Toolbar.Button("Aktuell läuft ein Spiel.", ""));
            break;
        case 3:
            toolbar.add(new Toolbar.Button("Anmeldung läuft..", ""));
            break;
        case 4:
            toolbar.add(new Toolbar.Button("Würfeln", "/blitz dice", "mychannel/ico_dice-6_001.gif", true));
                        toolbar.add(new Toolbar.Button("Points", "/points", "loginknuddelscent/knuddel_small.gif", false));  
            break;
        }
        toolbar.add(new Toolbar.Button("Hilfe", "/h blitz", "k/13.gif", false));
        return toolbar;
    }
    
    private void _sleep(long pMillis) {
        try {
            Thread.sleep(pMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        
    @Override
    public boolean parsePrivateMessage(List<Client> pTargets, String pMessage, Client pClient) { return false; }
        
} 
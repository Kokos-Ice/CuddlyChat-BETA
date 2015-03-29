package game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tools.GameUtilities;

import starlight.Channel;
import starlight.Client;
import starlight.Server;

/* SQL's:
 * UPDATE  `icenetchat`.`channels` SET  `visible` =  '1' WHERE  `channels`.`id` =383;
 * ALTER TABLE  `channels` ADD  `minmixpoints` INT( 11 ) NULL DEFAULT NULL
 * UPDATE `icenetchat`.`channels` SET `topic` = 'Hier kann mit James Mix gespielt werden. Einfach °B>/h mix|"<r° für Infos zum Spiel eingeben. °>1...b.my_2.gif<°', `game` = 'MIX', `style` = '610', `minrank` = '0' WHERE `channels`.`id` =383;
 * ALTER TABLE `accounts` ADD `mixpoints` FLOAT( 10, 2 ) UNSIGNED NULL DEFAULT NULL 
 * 
 * Änderungen:
 * - Channel.java
 * - ChatHandler.java
 * - GameUtilities.java
 * - Server.java
 * - Client.java
 * - CommandParser.java
 */







public class Mix implements Game, Runnable {
	
	
    private String[] startMixSentences = new String[]
    {
        "Folgende gemixte Buchstaben enthalten die zwei Lösungswörter:#_%s_"
    ,	"Nennt mir die Lösung für das folgende, vermixte Rätsel:#_%s_"
    };
   
    private String[] newTippSentences = new String[]
    {
        "Du bist schon beim %d. Lösungstipp:#_%s_ ===>#%s"
    ,	"%d. Lösungstipp:#_%s_ ===>#%s"
    ,	"Hier mein %d. Lösungstipp:#_%s_ ===>#%s"
    };

    private String[] solvedSentences = new String[]
    {
        "_°>_h%s|/serverpp \"|/w \"<°_ hat gewonnen, °B°_'%s'_°r° war korrekt!"
    ,	"_°>_h%s|/serverpp \"|/w \"<°_, °B°_'%s'_°r° ist die richtige Lösung!"
    };

    private String[] notSolvedSentences = new String[]
    {
        "Welch blamable Leistung! _'%s'_ war die Lösung."
    ,	"Niemand hat die richtige Lösung _'%s'_ erraten. Schwach!"
    };

    private String[] mixPlayingSentences = new String[]
    {
        "Mix läuft bereits."
    ,	"Es läuft bereits Mix."
    ,	"Wir spielen schon Mix."
    };
    
    private String getRandomStartMixSentence() {
        return startMixSentences[new Random().nextInt(startMixSentences.length)];
    }

    private String getRandomNewTippSentence() {
        return newTippSentences[new Random().nextInt(newTippSentences.length)];
    }
    
    private String getRandomSolvedSentence() {
        return solvedSentences[new Random().nextInt(solvedSentences.length)];
    }

    private String getRandomNotSolvedSentence() {
        return notSolvedSentences[new Random().nextInt(notSolvedSentences.length)];
    }

    private String getRandomMixPlayingSentence() {
        return mixPlayingSentences[new Random().nextInt(mixPlayingSentences.length)];
    }
     
    private Channel _channel;
    private Thread _thread;
    private DecimalFormat _decFormat;
    private int _round;
    private String _sentence;
    private String _sentence_out;
    private char[] _sentence_mixed;
    
    private long _start;
    private final int _waitTime = 12000; // 12sek (wie in Knuddels.de)
    
    private boolean _playing;
    private boolean _solved;
    
    public Mix(Channel pChannel) {
    	_channel = pChannel;
    	
    	
    	_decFormat = new DecimalFormat("0.00");
    	init();
    }
	
    private void init() {
    	_solved = false;   	
    	_sentence = null;
    	_sentence_out = null;
         _sentence_mixed = null;
         _round = 0;            
    }
            
	@Override
	public void run() {
		init();

		_sentence = GameUtilities.getRandomMixSentence();	
		_sentence_out = _sentence.replaceAll("\\w", "_");
		_sentence_mixed = GameUtilities.mixSentence(_sentence);
				
		_channel.broadcastButlerMessage(String.format(getRandomStartMixSentence(), GameUtilities.readableMixSentence(_sentence_mixed, _round)));
		_start = System.currentTimeMillis();
		_playing = true;
		while (!_solved) {
			
			_sleep(_waitTime);			
			
			_sleep(100);
			if (_solved || Thread.interrupted() || _round == _sentence_mixed.length) {
				break;
			}
			
			_sentence_out = GameUtilities.createReadableMixSentence(_sentence, _sentence_out, _sentence_mixed, _round);
			_channel.broadcastButlerMessage(String.format(getRandomNewTippSentence(), 
					_round + 1, GameUtilities.readableMixSentence(_sentence_mixed, _round + 1), GameUtilities.readableMixSentence(_sentence_out)));
			_round++;
			
			if (_round >= _sentence_mixed.length) {
				break;
			}			
		}
		_playing = false;
		
		if (_round == _sentence_mixed.length) {
			_channel.broadcastButlerMessage(String.format(getRandomNotSolvedSentence(), _sentence));
		}
		
	}

	@Override
	public boolean parsePublicMessage(String message, Client client) {		
		String msg = message.toLowerCase();

        if (msg.contains(Server.get().getButler().getName().toLowerCase()) && msg.contains("mix")) {
            if (!_playing) {
            	try {          		
            		_thread = new Thread(this);
            		_thread.start();
            	} catch(IllegalThreadStateException ex){
            	}
            } else {
            	_channel.broadcastMessage(message, client, true);
                _channel.broadcastButlerMessage(getRandomMixPlayingSentence());
                return false;
            }
        }
        
        if (_playing) {
            if (msg.contains(_sentence.toLowerCase())) {
            	_thread.interrupt();
            	_solved = true;
            	float time = Float.parseFloat(_decFormat.format(GameUtilities.totalTime(_start)).replace(",", "."));
            	float points = Float.parseFloat(_decFormat.format(((int)_channel.getClients().size() - 5 + (int)_sentence_mixed.length + (time + 8.439F)) / time).replace(",", "."));            	           	
            	
            	 client.increaseMixPoints(points);
            	
            	_channel.broadcastMessage(message, client, true);
            	String solvedSentence = String.format(getRandomSolvedSentence(), client.getName(), _sentence);
            	_channel.broadcastButlerMessage(
            			String.format("%s (_%s_ Sekunden, _%s_ Punkte)", 
            				solvedSentence,
	            			_decFormat.format(time), 
	            			(points < 5 ? _decFormat.format(points) : String.format("°BB°%s°r°", _decFormat.format(points)))
            			)
            	);
            	return false;
            }          
        }
       			
		return true;
	}

	@Override
	public boolean parsePrivateMessage(List<Client> targets, String message, Client client) {
		
        if (targets.contains(Server.get().getButler())) {
            client.sendPrivateMessage(targets, message, _channel, true);
            client.sendButlerMessage(_channel.getName(), "Wegen Problemen mit Bots werden nur noch öffentliche Nachrichten akzeptiert.");
            return false;
        }
		
		return true;
	}

	@Override
	public void onLeave(Client client) {
		
		
	}

	@Override
	public void onJoin(Client client) {
		
		
	}

	@Override
	public boolean parseCommand(String command, Client client) {
		
		return false;
	}
	
    private void _sleep(long pMillis) {
        try {
            Thread.sleep(pMillis);
        } catch (InterruptedException e) { }
    }

	

}

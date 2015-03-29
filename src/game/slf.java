
/*
 * Created on Nov 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author j0351189
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/*
 * SimpleTableDemo.java is a 1.4 application that requires no other files.
 */
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;



public class slf  implements ActionListener {
	  
	JTextArea textSpiel;
	JTextArea textStadt;
	JTextArea textLand;
	JTextArea textFluss;
	JTextArea textArea1;
	JTextArea textArea2;
	JTextArea textFehler;
	JTextArea textFehler2;
	JEditorPane jEditorPane;
	JEditorPane jEditorPane2;
	JEditorPane jEditorPane3;
	JEditorPane jEditorPane4;
	JLabel gesamtPunkte;
	JLabel leerRaum;
	JLabel leerRaum1;
	JLabel leerRaum2;
	JLabel leerRaum3;
	JTable table;
	int punkteZahl =0;	
	int runde = 0;
	boolean buttonListener = false;
	double dPunkte = 0.0;
	
	public slf() {
		JPanel pane = new JPanel(new GridLayout(8, 3));
		JFrame.setDefaultLookAndFeelDecorated(true);
				
			//Create and set up the window.
			JFrame frame = new JFrame("Herzlich Willkommen zum Spiel   STADT - LAND - FLUSS");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(pane);
            
            //Rand im ganzen Fenster einfügen
            pane.setBorder(BorderFactory.createEmptyBorder(
                    30, //top
                    30, //left
                    10, //bottom
                    30) //right
                    );
            
            //Leerraum erzeugen
            leerRaum = new JLabel();
            pane.add(leerRaum);

            //Erzeugt den Button der den Zufallsgenerator aufruft
            JButton b2 = new JButton("Neue Runde starten");
     	    pane.add(b2);
     	    
            //Buttonerzeugung zur Spielanleitung
            JButton b6 = new JButton("Spielanleitung");
            pane.add(b6);
            
			//Leerraum erzeugen
			leerRaum3 = new JLabel();
			pane.add(leerRaum3);

			//Leerraum erzeugen
			leerRaum3 = new JLabel();
			pane.add(leerRaum3);

			//Leerraum erzeugen
			leerRaum3 = new JLabel();
			pane.add(leerRaum3);			
			
            //Anzeige des Buchstaben
			jEditorPane2 = new JEditorPane();
			jEditorPane2.setEditable(false);
			pane.add(jEditorPane2); 
			jEditorPane2.setForeground(java.awt.Color.red);
			jEditorPane2.setFont(new java.awt.Font ("Arial", 0,40));
     	    
        	//Erklärungstext für Buchstabenanzeige und Rundenanzeige
			jEditorPane = new JEditorPane();
			jEditorPane.setEditable(false);
			pane.add(jEditorPane); 
			jEditorPane.setForeground(java.awt.Color.red);
			jEditorPane.setFont(new java.awt.Font ("Arial", 0,13));
			
			
            //Anzeige der Rundenzahl
            jEditorPane3 = new JEditorPane();
			jEditorPane3.setEditable(false);
			pane.add(jEditorPane3); 
			jEditorPane3.setForeground(java.awt.Color.red);
			jEditorPane3.setFont(new java.awt.Font ("Arial", 0,40));
			jEditorPane3.setText("Runde Nr. 1");
			
			//Erzeugt Tabelle mit den Eingabefeldern   
        	String[] columnNames = {"Stadt",
								    "Land",
							     	"Fluss",
							    	
							      	};
		
		    Object [][] data = {
			{"", "",
			 ""},	
		    };

		    table = new JTable(data, columnNames);
	    	table.setPreferredScrollableViewportSize(new Dimension(300, 50));
		
	    	//Mouselistener, damit bei Feldwechsel die Daten aktualisiert werden
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
				 									   }
													  }
									);

			
		    //Scroll Pane erstellen und Tabelle hinzuf?gen
		    JScrollPane scrollPane = new JScrollPane(table);
		    pane.add(scrollPane);
		
		
		
		    //Buttonerzeugung
		    JButton b0 = new JButton("Ueberpruefen");
		    pane.add(b0);
		
		    textArea1 = new JTextArea();
		    pane.add(textArea1);  
		
		    //3 Buttons f?r hinzuf?gen von neuen L?sungen
			JButton b3 = new JButton("Stadt hinzufuegen");
			pane.add(b3);
			
		
			JButton b4 = new JButton("Land hinzufuegen");
			pane.add(b4);
					  
			JButton b5 = new JButton("Fluss hinzufuegen");
        	pane.add(b5);

        	
			//Leerraum erzeugen
			leerRaum3 = new JLabel();
			pane.add(leerRaum3);

			//Leerraum erzeugen
			leerRaum3 = new JLabel();
			pane.add(leerRaum3);
			
			//Leerraum erzeugen
			leerRaum3 = new JLabel();
			pane.add(leerRaum3);
			
			//Buttonerzeugung f?r Gesamtpunkteaddition
			JButton b1 = new JButton("Runde beenden");
			pane.add(b1);

            //Anzeige des Punktestandes
            jEditorPane4 = new JEditorPane();
			jEditorPane4.setEditable(false);
			pane.add(jEditorPane4); 
			jEditorPane4.setForeground(java.awt.Color.red);
			jEditorPane4.setFont(new java.awt.Font ("Arial", 0,20));
			
			
        	//Action-Listener zu Button b0 - b6 hinzufuegen
		
        	b0.addActionListener(this);
        	b1.addActionListener(this);
        	b2.addActionListener(this);
        	b3.addActionListener(this);
        	b4.addActionListener(this);
        	b5.addActionListener(this);
        	b6.addActionListener(this);
        	
        	//Alle Komponenten des Fenster zusammenfassen und Fenster erzeugen
        	frame.pack();
        	frame.setVisible(true);
		
			}
	

	public void actionPerformed(ActionEvent e) {
			 
			//Aktionen darstellen
			System.out.println(e);
						 
			if(e.getActionCommand().equals("Spielanleitung")){
								
			   //JPanel f?r neues Fenster erzeugen								
			   JPanel pane1 = new JPanel(new GridLayout(1, 1));
			   JFrame.setDefaultLookAndFeelDecorated(true);
			   JFrame frame1 = new JFrame("Spielanleitung STADT - LAND - FLUSS");
			   frame1.add(pane1);	
			   
			   //TextArea zu neuem Fenster hinzuf?gen
			   textSpiel = new JTextArea();
			   textSpiel.setEditable(false);
			   pane1.add(textSpiel); 
			   
			   //BufferedReader zum Lesen der Datei "anleitung.txt"
			   String x = "";
			   String zeile;
		       BufferedReader eingabe;
			   FileReader text;
			   File anleitung;
						     
			   try{
			   	   anleitung= new File("text/anleitung.txt");
				   text = new FileReader(anleitung);
				   eingabe = new BufferedReader(text);
				   while(  (zeile = eingabe.readLine()) != null) {
					   		textSpiel.append(zeile);
					   		textSpiel.append("\n");
				   												 }
						    	 eingabe.close(); 
						 }
			   catch (IOException ex) {
			    	  ex.printStackTrace();
			   						  }
			   
			   //Fenster erstellen und anzeigen
			   frame1.pack();
			   frame1.setVisible(true);
						 									 }
				
			if(e.getActionCommand().equals("Runde beenden")){
			   			   
			   if (buttonListener == true){
				   javax.swing.table.TableModel model =table.getModel();
			   
			   
			   //neue Instanz des Lexikons erstellen
			   Lexikon lex = new Lexikon();
			   
			   //textArea1 l?schen
			   textArea1.setText("");
			   
			   //Wort aus der Tabelle holen
			   String feldStadt;
			   feldStadt= ((String)model.getValueAt(0,0));
			   
				//Stadt in Lexikon nachschlagen
				textArea1.append(lex.searchStadt(feldStadt));
				textArea1.setEditable(false);
				textArea1.setCaretPosition(textArea1.getDocument().getLength());
				
				String feldLand;
				feldLand= ((String)model.getValueAt(0,1));
				textArea1.append(lex.searchLand(feldLand));
				textArea1.setCaretPosition(textArea1.getDocument().getLength());
						 	
				String feldFluss;
				feldFluss= ((String)model.getValueAt(0,2));
				textArea1.append(lex.searchFluss(feldFluss));
				textArea1.setCaretPosition(textArea1.getDocument().getLength());
				
				//Punktestand ausgeben
			    punkteZahl = (lex.punkteuebergabe(punkteZahl));
			    dPunkte = punkteZahl/runde;
			    jEditorPane4.setText("Aktueller Punktestand: " + Integer.toString(punkteZahl) + "\nPunkte/Runde: " + dPunkte);
				

				buttonListener = false;
			   
			   }
			   else {
					JPanel pane4 = new JPanel(new GridLayout(1, 1));
					JFrame.setDefaultLookAndFeelDecorated(true);
					JFrame frame4 = new JFrame("Fehler");
					frame4.add(pane4);	
					textFehler2 = new JTextArea();
					textFehler2.setEditable(false);
					textFehler2.append("Nicht Schummeln!!! \n Sie müssen zuerst eine neue Runde starten \n bevor Sie diese dann wieder beenden können.");
					pane4.add(textFehler2); 
					frame4.pack();
					frame4.setVisible(true);				   
			   }
			}
						 
						 
			if(e.getActionCommand().equals("Ueberpruefen")){
			   javax.swing.table.TableModel model =table.getModel();
				
			    //neue Instanz des Lexikons erstellen
				Lexikon lex = new Lexikon();
				
				//Wort aus der Tabelle holen
			    String feldStadt;
				feldStadt= ((String)model.getValueAt(0,0));
				
				//textArea1 l?schen
				textArea1.setText("");
				
				//Stadt in Lexikon nachschlagen
				textArea1.append(lex.searchStadt(feldStadt));
				textArea1.setEditable(false);
				textArea1.setCaretPosition(textArea1.getDocument().getLength());
				
				String feldLand;
				feldLand= ((String)model.getValueAt(0,1));
				textArea1.append(lex.searchLand(feldLand));
				textArea1.setCaretPosition(textArea1.getDocument().getLength());
						 	
				String feldFluss;
				feldFluss= ((String)model.getValueAt(0,2));
				textArea1.append(lex.searchFluss(feldFluss));
				textArea1.setCaretPosition(textArea1.getDocument().getLength());
				
				
														  }
						 
			if(e.getActionCommand().equals("Neue Runde starten")) {
				
				//Wird nur gestartet, wenn Runde beendet wurde
				if (buttonListener == false){ 
				
				//textArea2 l?schen
				jEditorPane.setText("");
				
				//neue Instanz des Zufallsgenerator erstellen und ausf?hren
				Zufallsgenerator zufall = new Zufallsgenerator();
				jEditorPane.setText(" Bitte geben Sie die jeweiligen Begriffe mit dem \n links dargestelltem Anfangsbuchstaben ein! \n Auf der rechten Seite sehen sie die akutelle \n Rundenzahl.");
				jEditorPane2.setText(zufall.createLetter());
				
				//Felder in Tabelle löschen
				javax.swing.table.TableModel model =table.getModel();
				
				model.setValueAt("",0,0);
				model.setValueAt("",0,1);
				model.setValueAt("",0,2);
				
			    //Rundenzahl ausgeben
			    runde++;
		        jEditorPane3.setText("Runde Nr. " + runde);
				
				//setzt ButtonListener auf true, damit neue Runde nicht 2x gestartet werden kann
				buttonListener = true;
	
				}
				//Wird nur gestartet, wenn Runde noch NICHT beendet wurde	
				else{
					JPanel pane3 = new JPanel(new GridLayout(1, 1));
					JFrame.setDefaultLookAndFeelDecorated(true);
					JFrame frame3 = new JFrame("Fehler");
					frame3.add(pane3);	
					textFehler = new JTextArea();
					textFehler.setEditable(false);
					textFehler.append("Sie müssen zuerst die aktuelle Runde beenden \n bevor Sie eine neue Runde starten können.");
					pane3.add(textFehler); 
					frame3.pack();
					frame3.setVisible(true);					
					}
				}
						
			if(e.getActionCommand().equals("Stadt hinzufuegen")) {
				javax.swing.table.TableModel model =table.getModel();
								 
				//Instanz von Lexikon erstellen, Wort holen und abspeichern
				Lexikon lex = new Lexikon();
				String feldStadt;
				feldStadt= ((String)model.getValueAt(0,0));
				lex.addStadt(feldStadt);
				
				//neue Fenster f?r Best?tigungstext erstellen
				JPanel pane2 = new JPanel(new GridLayout(1, 1));
				JFrame.setDefaultLookAndFeelDecorated(true);
				JFrame frame2 = new JFrame("Stadt Hinzuf?gen");
				frame2.add(pane2);	
				textStadt = new JTextArea();
				textStadt.setEditable(false);
				textStadt.append("Die Stadt " + feldStadt + " wurde erfolgreich hinzugef?gt.");
				pane2.add(textStadt); 
				frame2.pack();
				frame2.setVisible(true);
													}
						
			if(e.getActionCommand().equals("Land hinzufuegen")) {
				javax.swing.table.TableModel model =table.getModel();
				
				//Instanz von Lexikon erstellen, Wort holen und abspeichern				 
				Lexikon lex = new Lexikon();
				String feldLand;
				feldLand= ((String)model.getValueAt(0,1));
				lex.addLand(feldLand);
				
				//neue Fenster f?r Best?tigungstext erstellen
				JPanel pane3 = new JPanel(new GridLayout(1, 1));
				JFrame.setDefaultLookAndFeelDecorated(true);
				JFrame frame3 = new JFrame("Land Hinzuf?gen");
				frame3.add(pane3);	
				textLand = new JTextArea();
				textLand.setEditable(false);
				textLand.append("Das Land " + feldLand + " wurde erfolgreich hinzugef?gt.");
				pane3.add(textLand); 
				frame3.pack();
				frame3.setVisible(true);
																}
			
			if(e.getActionCommand().equals("Fluss hinzufuegen")) {
				javax.swing.table.TableModel model =table.getModel();
				
				//Instanz von Lexikon erstellen, Wort holen und abspeichern
				Lexikon lex = new Lexikon();
				String feldFluss;
				feldFluss= ((String)model.getValueAt(0,2));
				lex.addFluss(feldFluss);
				
				//neue Fenster f?r Best?tigungstext erstellen
				JPanel pane4 = new JPanel(new GridLayout(1, 1));
				JFrame.setDefaultLookAndFeelDecorated(true);
				JFrame frame4 = new JFrame("Fluss Hinzuf?gen");
				frame4.add(pane4);	
				textFluss = new JTextArea();
			    textFluss.setEditable(false);
			    textFluss.append("Der Fluss " + feldFluss + " wurde erfolgreich hinzugef?gt.");
			    pane4.add(textFluss); 
			    frame4.pack();
			    frame4.setVisible(true);
																}
											}
	
	//Tabelleninhalt ausgeben
	private void printDebugData(JTable table) {
			int numRows = table.getRowCount();
			int numCols = table.getColumnCount();
			javax.swing.table.TableModel model = table.getModel();

			System.out.println("Value of data: ");
			for (int i=0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j=0; j < numCols; j++) {
				System.out.print("  " + j + " " +  model.getValueAt(i, j));
											}
			System.out.println();
											}
			System.out.println("--------------------------");
			}
		}
	
	
*/
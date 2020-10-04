import java.util.LinkedList;

public class Benutzer {

	private String name;
	private String bild;
	private int id;
	private static int i = 0;
	LinkedList<Aufgabe> aufgabenliste = new LinkedList<Aufgabe>();
	LinkedList<AufgabeMitDatum> aufgabenplan = new LinkedList<AufgabeMitDatum>();
	private static LinkedList<Benutzer> alleBenutzer = new LinkedList<Benutzer>();

	/**
	 * Konstruktor zum erstellen eines neuen Benutzers
	 * @param n Benutzername
	 * @param b Pfad zum Benutzerbild
	 */
	public Benutzer(String n, String b) {
		this.name = n;
		this.bild = b;
		this.id = i;
		alleBenutzer.add(this);
		MainController.updateBenutzer();
		MainController.buildToDoPanel(this);
		i++;
	}

	/**
	 * Ausgabe des Namen des Benutzers
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Ausgabe der ID des Benutzers
	 * @return ID
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Fügt eine Aufgabe der Aufgabenliste einer Person hinzu 
	 * @param a Aufgabe
	 */
	public void aufgabeGeben(Aufgabe a) {
		this.aufgabenliste.add(a);
	}
	
	/**
	 * Fügt eine Datum, an dem eine Aufgabe erledigt werden soll dem Aufgabenplan einer Person hinzu
	 * @param a Aufgabe mit Datum
	 */
	public void datumGeben(AufgabeMitDatum a) {
		aufgabenplan.add(a);
	}

	/**
	 * Ausgabe des Pfades zum Benutzerbild
	 * @return Pfad zum Benutzerbild
	 */
	public String getBild() {
		return this.bild;
	}

	/**
	 * Statische Methode zum ausgeben aller Benutzer
	 * @return Liste aller Benutzer
	 */
	public static LinkedList<Benutzer> getAlleBenutzer() {
		return alleBenutzer;
	}
	
	/**
	 * Ausgabe des Aufgabenplans eines Benutzers
	 * @return Aufgabenplan
	 */
	public LinkedList<AufgabeMitDatum> getAufgabenplan(){
		return this.aufgabenplan;
	}
}

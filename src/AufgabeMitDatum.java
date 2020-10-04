import java.time.LocalDate;

public class AufgabeMitDatum {
	private Aufgabe aufgabe;
	private LocalDate datum;
	
	/**
	 * Konstruktor zum erstellen eines neuen Datums, an dem eine Aufgabe erledigt werden soll
	 * @param a Aufgabe
	 * @param d Datum
	 * @param b Benutzer
	 */
	public AufgabeMitDatum(Aufgabe a, LocalDate d, Benutzer b) {
		this.aufgabe = a;
		this.datum = d;
		b.datumGeben(this);
	}
	
	/**
	 * Ausgabe der Aufgabe
	 * @return Aufgabe
	 */
	public Aufgabe getAufgabe() {
		return this.aufgabe;
	}
	
	/**
	 * Ausgabe des Datums
	 * @return Datum
	 */
	public LocalDate getDatum() {
		return this.datum;
	}
}

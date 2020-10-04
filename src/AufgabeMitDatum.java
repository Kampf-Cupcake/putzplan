import java.time.LocalDate;

public class AufgabeMitDatum {
	private Aufgabe aufgabe;
	private LocalDate datum;
	
	public AufgabeMitDatum(Aufgabe a, LocalDate d, Benutzer b) {
		this.aufgabe = a;
		this.datum = d;
		b.datumGeben(this);
	}
	
	public Aufgabe getAufgabe() {
		return this.aufgabe;
	}
	
	public LocalDate getDatum() {
		return this.datum;
	}
}

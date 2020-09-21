
public class Aufgabenliste {

	protected Aufgabe aufgabe;
	
	
	public void setAufgabe (Aufgabe a) {
		this.aufgabe = a;
		a.addzuListe(this);
	}
	
	
}

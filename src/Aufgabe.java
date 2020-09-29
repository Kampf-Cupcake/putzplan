//Name = Name, Schwierrigkeit von 1-5, Haeufigkeit= wie oft pro Woche

public class Aufgabe {
 
	String name;
	int schwierigkeit;
	int haeufigkeit;

	public Aufgabe(String n, int s, int h) {
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
		Aufgabenliste.getInstanz().hinzufuegen(this);
	}

	public String toString() {
		return this.name;
		//return this.name + " (" + this.schwierigkeit + ") " + this.haeufigkeit;
	}

	public void bearbeiten(String n, int s, int h) {
		//Aufgabenliste.getInstanz().entfernen(this);
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
	}

	
}

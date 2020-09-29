
public class Aufgabe {

	String name;
	int schwierigkeit;
	double haeufigkeit;

	public Aufgabe(String n, int s, double h) {
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
		Aufgabenliste.getInstanz().hinzufuegen(this);
	}

	public String toString() {
		return this.name + " (" + this.schwierigkeit + ") " + this.haeufigkeit;
	}

	public void bearbeiten(String n, int s, double h) {
		//Aufgabenliste.getInstanz().entfernen(this);
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
	}
}

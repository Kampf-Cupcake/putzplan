
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
		return "Die Aufgabe " + name + " hat die Schwierigkeitsstufe " + schwierigkeit
				+ " und soll so oft gemacht werden " + haeufigkeit;
	}

	public void neuErstellenA(String n, int s, double h) {

	}

	public void bearbeitenA(String n, int s, double h) {

	}
}

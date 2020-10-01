import java.util.LinkedList;

public class Aufgabe {

	String name;
	int schwierigkeit;
	int haeufigkeit;

	private static LinkedList<Aufgabe> alleAufgaben = new LinkedList<Aufgabe>();

	public Aufgabe(String n, int s, int h) {
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
		alleAufgaben.add(this);

		LinkedList<Aufgabe> neu = new LinkedList<Aufgabe>();
		/*
		 * System.out.println("Neue Aufgabe: " + n);
		 * System.out.println(alleAufgaben.size()); for (int akt = 0; akt <
		 * alleAufgaben.size(); akt++) { System.out.println("  " + akt + ": " +
		 * alleAufgaben.get(akt)); boolean istHoechste = true; for (int verg = akt + 1;
		 * verg < alleAufgaben.size(); verg++) { System.out.println("    " + verg + ": "
		 * + alleAufgaben.get(verg)); if (alleAufgaben.get(akt).getSchwierigkeit() *
		 * alleAufgaben.get(akt).getHaeufigkeit() < alleAufgaben
		 * .get(verg).getSchwierigkeit() * alleAufgaben.get(verg).getHaeufigkeit()) {
		 * System.out.println("nicht Höchste"); istHoechste = false; } } if
		 * (istHoechste) { System.out.println("ist Höchste");
		 * neu.add(alleAufgaben.get(akt)); } }
		 */

		for (int i = 5; i > 0; i--) {
			for (Aufgabe a : alleAufgaben) {
				if (a.getSchwierigkeit() * a.getHaeufigkeit() == i) {
					neu.add(a);
				}
			}
		}
		alleAufgaben = neu;
	}

	public String toString() {
		return this.name + " S:" + this.schwierigkeit + " H:" + this.haeufigkeit;
	}

	public String getName() {
		return this.name;
	}

	public int getSchwierigkeit() {
		return this.schwierigkeit;
	}

	public int getHaeufigkeit() {
		return this.haeufigkeit;
	}

	public void bearbeiten(String n, int s, int h) {
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
	}

	public static LinkedList<Aufgabe> getAlleAufgaben() {
		return alleAufgaben;
	}

}

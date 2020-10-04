import java.util.LinkedList;

public class Aufgabe {

	private String name;
	private int schwierigkeit;
	private int haeufigkeit;

	private static LinkedList<Aufgabe> alleAufgaben = new LinkedList<Aufgabe>();

	/**
	 * Konstruktor zum erstellen einer neuen Aufgabe
	 * @param n Aufgabenname
	 * @param s Schwierigkeit
	 * @param h Haeufigkeit im separat bestimmbaren Zeitraum
	 */
	public Aufgabe(String n, int s, int h) {
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;

		LinkedList<Aufgabe> neu = (LinkedList<Aufgabe>) alleAufgaben.clone();

		if (!alleAufgaben.isEmpty()) {
			if (s * h < alleAufgaben.get(alleAufgaben.size() - 1).getSchwierigkeit()
					* alleAufgaben.get(alleAufgaben.size() - 1).getHaeufigkeit()) {
				neu.add(this);
			} else {
				for (int i = 0; i < alleAufgaben.size(); i++) {
					if (s * h >= alleAufgaben.get(i).getSchwierigkeit() * alleAufgaben.get(i).getHaeufigkeit()) {
						neu.add(i, this);
						break;
					}
				}
			}
		} else {
			neu.add(this);
		}
		alleAufgaben = neu;
		MainController.updateAufgaben();
	}

	/**
	 * Ausgabe des Namen der Aufgabe
	 * @return Aufgabenname
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Ausgabe der Schwierigkeit der Aufgabe
	 * @return Schwierigkeit
	 */
	public int getSchwierigkeit() {
		return this.schwierigkeit;
	}

	/**
	 * Ausgabe der Haeufigkeit der Aufgabe in einem separat bestimmbaren Zeitraum
	 * @return
	 */
	public int getHaeufigkeit() {
		return this.haeufigkeit;
	}

	/**
	 * Statische Methode zum ausgeben aller Aufgaben
	 * @return Liste mit allen Aufgaben
	 */
	public static LinkedList<Aufgabe> getAlleAufgaben() {
		return alleAufgaben;
	}

}

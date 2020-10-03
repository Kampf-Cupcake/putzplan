import java.util.LinkedList;

public class Aufgabe {

	private String name;
	private int schwierigkeit;
	private int haeufigkeit;

	private static LinkedList<Aufgabe> alleAufgaben = new LinkedList<Aufgabe>();

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

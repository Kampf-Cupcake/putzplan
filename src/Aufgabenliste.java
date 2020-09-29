import java.util.LinkedList;
import java.util.List;

public class Aufgabenliste {

	private static Aufgabenliste instanz;
	private static List<Aufgabe> aufgaben = new LinkedList<Aufgabe>();

	public static Aufgabenliste getInstanz() {
		if (instanz == null) {
			instanz = new Aufgabenliste();
		}
		return instanz;
	}

	public void hinzufuegen(Aufgabe a) {
		aufgaben.add(a);
	}

	public void entfernen(Aufgabe a) {
		for (Aufgabe i : aufgaben) {
			if (i == a) {
				aufgaben.remove(i);
				break;
			}
		}
	}

	public List<Aufgabe> ausgeben() {
		return this.aufgaben;
	}

	public Aufgabe[] arrayAusgeben() {
		Aufgabe[] a = new Aufgabe[aufgaben.size()];
		a = aufgaben.toArray(a);
		return a;
	}

	public String toString() {
		String s = "";
		for (Aufgabe a : aufgaben) {
			s += (a.toString() + "\n");
		}
		return s;
	}
}

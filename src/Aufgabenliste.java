import java.util.LinkedList;
import java.util.List;

public class Aufgabenliste {

	private static Aufgabenliste instanz;
	private List<Aufgabe> aufgaben = new LinkedList<Aufgabe>();

	public static Aufgabenliste getInstanz() {
		if (instanz == null) {
			instanz = new Aufgabenliste();
		}
		return instanz;
	}

	public void hinzufuegen(Aufgabe a) {
		aufgaben.add(a);
	}

	public List<Aufgabe> ausgeben() {
		return this.aufgaben;
	}

	public String toString() {
		String s = "";
		for (Aufgabe a : aufgaben) {
			s += (a.toString() + "\n");
		}
		return s;
	}
}

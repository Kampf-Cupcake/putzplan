import java.util.LinkedList;

public class Benutzer {

	String name;
	String bild; // muss noch angepasst werden
	LinkedList<Aufgabe> aufgabenliste = new LinkedList<Aufgabe>();

	public Benutzer(String n, String b) {
		this.name = n;
		this.bild = b;
	}

	public String toString() {
		return name;
	}

	public void bearbeitenB(String n, String b) {

	}
	
	public void aufgabeGeben(Aufgabe a) {
		this.aufgabenliste.add(a);
	}
}

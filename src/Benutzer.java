import java.util.LinkedList;

public class Benutzer {

	String name;
	String bild; // muss noch angepasst werden
	LinkedList<Aufgabe> aufgabenliste = new LinkedList<Aufgabe>();
	private static LinkedList<Benutzer> alleBenutzer = new LinkedList<Benutzer>();

	public Benutzer(String n, String b) {
		this.name = n;
		this.bild = b;
		alleBenutzer.add(this);
		System.out.println(alleBenutzer);
	}

	public String toString() {
		return name;
	}

	public void bearbeitenB(String n, String b) {

	}
	
	public void aufgabeGeben(Aufgabe a) {
		this.aufgabenliste.add(a);
	}
	
	public String getBild() {
		return this.bild;
	}
	
	public static LinkedList<Benutzer> getAlleBenutzer(){
		return alleBenutzer;
	}
}

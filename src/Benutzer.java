import java.util.LinkedList;

public class Benutzer {

	private String name;
	private String bild; // muss noch angepasst werden
	private int id;
	private static int i = 0;
	LinkedList<Aufgabe> aufgabenliste = new LinkedList<Aufgabe>();
	LinkedList<AufgabeMitDatum> aufgabenplan = new LinkedList<AufgabeMitDatum>();
	private static LinkedList<Benutzer> alleBenutzer = new LinkedList<Benutzer>();

	public Benutzer(String n, String b) {
		this.name = n;
		this.bild = b;
		this.id = i;
		alleBenutzer.add(this);
		MainController.updateBenutzer();
		MainController.buildToDoPanel(this);
		i++;
	}

	public String toString() {
		return this.name;
	}

	public int getID() {
		return this.id;
	}

	public void aufgabeGeben(Aufgabe a) {
		this.aufgabenliste.add(a);
	}
	
	public void datumGeben(AufgabeMitDatum a) {
		aufgabenplan.add(a);
	}

	public String getBild() {
		return this.bild;
	}

	public static LinkedList<Benutzer> getAlleBenutzer() {
		return alleBenutzer;
	}
	
	public LinkedList<AufgabeMitDatum> getAufgabenplan(){
		return this.aufgabenplan;
	}
}

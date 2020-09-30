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
		for (int i = 5; i > 0; i--) {
			for (Aufgabe a : alleAufgaben) {
				if (a.getSchwierigkeit() == i) {
					neu.add(a);
				}
			}
		}
		alleAufgaben = neu;
	}

	public String toString() {
		return this.name + this.schwierigkeit;
		//return this.name + " (" + this.schwierigkeit + ") " + this.haeufigkeit;
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

	public static LinkedList<Aufgabe> getAlleAufgaben(){
		return alleAufgaben;
	}
	
}

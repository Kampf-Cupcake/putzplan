import java.util.LinkedList;
import java.util.List;

public class Aufgabe {
	
	String name;
	int schwierigkeit;
	double haeufigkeit;
	
	protected List<Aufgabenliste> aufgabenliste = new LinkedList();
	
	public Aufgabe(String n, int s, double h) {
		this.name = n;
		this.schwierigkeit = s;
		this.haeufigkeit = h;
	}
	
	public String toString() {
		return "Die Aufgabe " + name + 
				" hat die Schwierigkeitsstufe " + schwierigkeit + 
				" und soll so oft gemacht werden "+ haeufigkeit;
	}
	
	public void neuErstellenA(String n, int s, double h) {
		
	}
	
	public void bearbeitenA(String n, int s, double h) {
		
	}
	
	public void addzuListe(Aufgabenliste l) {
		aufgabenliste.add(l);
	}
}

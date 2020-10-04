import javax.swing.JTextField;

public class NeueAufgabeController {
	private static NeueAufgabeWindow instanz;

	/**
	 * Statische Methode zum erstellen der Instanz des Fensters f�r die Eingabe einer neuen Aufgabe
	 * @return instanz des Fensters
	 */
	public static NeueAufgabeWindow getInstanz() {
		if (instanz == null) {
			instanz = new NeueAufgabeWindow();
		}
		return instanz;
	}
	
	/**
	 * �berpr�ft die Inhalte der Eingabefelder und erstellt eine neue Aufgabe
	 * @param aufgabeField Eingabefeld f�r den Aufgabennamen
	 * @param schwierigkeitField Eingabefeld f�r die Schwierigkeit einer Aufgabe
	 * @param haeufigkeitField Eingabefeld f�r die H�ufigkeit einer Aufgabe
	 */
	public static void confirmAufgabe(JTextField aufgabeField, JTextField schwierigkeitField, JTextField haeufigkeitField) {
		if (!(aufgabeField.getText().equals("Aufgabe")) && !(schwierigkeitField.getText().equals("Schwierigkeit (1-5)"))
				&& !(haeufigkeitField.getText().equals("H�ufigkeit (pro Monat)"))) {
			int s = 0;
			int h = 0;
			try {
				s = Integer.parseInt(schwierigkeitField.getText().trim());
				h = Integer.parseInt(haeufigkeitField.getText().trim());
				new Aufgabe(aufgabeField.getText(), s, h);
				instanz.setVisible(false);
				instanz.dispose();
				MainController.updateAufgaben();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

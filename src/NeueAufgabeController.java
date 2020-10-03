import javax.swing.JTextField;

public class NeueAufgabeController {
	private static NeueAufgabeWindow instanz;

	public static NeueAufgabeWindow getInstanz() {
		if (instanz == null) {
			instanz = new NeueAufgabeWindow();
		}
		return instanz;
	}
	
	public static void confirmAufgabe(JTextField aufgabeField, JTextField schwierigkeitField, JTextField haeufigkeitField) {
		if (!(aufgabeField.getText().equals("Aufgabe")) && !(schwierigkeitField.getText().equals("Schwierigkeit (1-5)"))
				&& !(haeufigkeitField.getText().equals("Häufigkeit (pro Monat)"))) {
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

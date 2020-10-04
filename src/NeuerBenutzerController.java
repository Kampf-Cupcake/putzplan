import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NeuerBenutzerController {
	private static NeuerBenutzerWindow instanz;

	/**
	 * Statische Methode zum erstellen der Instanz des Fensters f�r das erstellen eines neuen Benutzers
	 * @return instanz des Fensters
	 */
	public static NeuerBenutzerWindow getInstanz() {
		if (instanz == null) {
			instanz = new NeuerBenutzerWindow();
		}
		return instanz;
	}

	/**
	 * �berpr�ft die Inhalte der Eingabefelder und erstellt einen neuen Benutzer
	 * @param nameField
	 * @param file
	 */
	public static void confirmUser(JTextField nameField, String file) {
		if (!nameField.getText().equals("Name")) {
			new Benutzer(nameField.getText(), file);
			instanz.setVisible(false);
			instanz.dispose();
			MainController.getInstanz().revalidate();
		}
	}

	/**
	 * �ffnet den Dialog zum ausw�hlen eines Bildes f�r den neuen Benutzer
	 * @param confirmBtn Erstellen-Button, der nach Auswahl des Bildes freigeschaltet wird
	 * @return Pfad zum ausgew�hlten Bild
	 */
	public static String selectFile(JButton confirmBtn) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());
		chooser.addChoosableFileFilter(imageFilter);
		chooser.setFileFilter(imageFilter);
		String file = "";
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile().getAbsolutePath();
			confirmBtn.setEnabled(true);
		}
		return file;
	}
}

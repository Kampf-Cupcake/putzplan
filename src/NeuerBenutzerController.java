import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NeuerBenutzerController {
	private static NeuerBenutzerWindow instanz;

	public static NeuerBenutzerWindow getInstanz() {
		if (instanz == null) {
			instanz = new NeuerBenutzerWindow();
		}
		return instanz;
	}
	
	public static void confirmUser(JTextField nameField, String file) {
		if (!nameField.getText().equals("Name")) {
			new Benutzer(nameField.getText(), file);
			instanz.setVisible(false);
			instanz.dispose();
			MainController.getInstanz().revalidate();
		}
	}

	public static String selectFile(JButton confirmBtn, String file) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());
		chooser.addChoosableFileFilter(imageFilter);
		chooser.setFileFilter(imageFilter);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile().getAbsolutePath();
			confirmBtn.setEnabled(true);
		}
		return file;
	}
}

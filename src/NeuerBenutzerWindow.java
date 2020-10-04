import java.awt.*;
import java.awt.event.*;
import java.io.FileFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NeuerBenutzerWindow extends JFrame {

	JPanel hintergrund = new JPanel();
	JTextField nameField = new JTextField("Name");
	JButton bildBtn = new JButton("Bild auswählen");
	JButton confirmBtn = new JButton("Bestätigen");
	String file;

	/**
	 * Konstruktor zum erstellen eines neuen Fensters zum erstellen eines neuen Benutzers
	 */
	public NeuerBenutzerWindow() {
		setTitle("Benutzer erstellen");
		setResizable(false);

		GridLayout layout = new GridLayout(3, 0);
		layout.setVgap(14);

		hintergrund.setPreferredSize(new Dimension(200, 160));
		hintergrund.setBackground(new Color(1, 83, 82));
		hintergrund.setLayout(layout);
		hintergrund.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		add(hintergrund);

		
		Eingabefenster.platzhalter(nameField, "Name");
		nameField.setHorizontalAlignment(JTextField.CENTER);
		hintergrund.add(nameField);

		bildBtn.addActionListener(e -> {
			file = NeuerBenutzerController.selectFile(confirmBtn);
		});
		hintergrund.add(bildBtn);

		confirmBtn.setEnabled(false);
		confirmBtn.addActionListener(e -> {
			NeuerBenutzerController.confirmUser(nameField, file);
		});
		hintergrund.add(confirmBtn);

		pack();
	}


}

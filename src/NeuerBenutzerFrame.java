import java.awt.*;
import java.awt.event.*;
import java.io.FileFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

// TODO: click auf existierenden Benutzer -> Benutzer bearbeiten
public class NeuerBenutzerFrame extends JFrame {
	private static NeuerBenutzerFrame instanz;

	public static NeuerBenutzerFrame getInstanz() {
		if (instanz == null) {
			instanz = new NeuerBenutzerFrame();
		}
		return instanz;
	}

	JPanel hintergrund = new JPanel();
	JTextField nameField = new JTextField("Name");
	JButton bildBtn = new JButton("Bild auswählen");
	JButton confirmBtn = new JButton("Bestätigen");
	String file;

	public NeuerBenutzerFrame() {
		setTitle("Benutzer erstellen");
		//wollen ja nicht direkt alles schliesen, nur weil wir den frame zu machen wollen
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		GridLayout layout = new GridLayout(3, 0);
		layout.setVgap(14);

		hintergrund.setPreferredSize(new Dimension(200, 160));
		hintergrund.setBackground(new Color(1, 83, 82));
		hintergrund.setLayout(layout);
		hintergrund.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		add(hintergrund);

		nameField.setForeground(Color.GRAY);
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (nameField.getText().equals("Name")) {
					nameField.setForeground(Color.BLACK);
					nameField.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if (nameField.getText().isEmpty()) {
					nameField.setForeground(Color.GRAY);
					nameField.setText("Name");
				}
			}
		});
		nameField.setHorizontalAlignment(JTextField.CENTER);
		hintergrund.add(nameField);

		bildBtn.addActionListener(e -> {
			selectFile();
		});
		hintergrund.add(bildBtn);

		confirmBtn.setEnabled(false);
		confirmBtn.addActionListener(e -> {
			confirmUser();
		});
		hintergrund.add(confirmBtn);

		pack();
	}

	private void confirmUser() {
		if (!nameField.getText().equals("Name")) {
			new Benutzer(nameField.getText(), file);
			setVisible(false);
			dispose();
			PutzplanGUI.getInstanz().updateBenutzer();
		}
	}

	private void selectFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());
		chooser.addChoosableFileFilter(imageFilter);
		chooser.setFileFilter(imageFilter);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.file = chooser.getSelectedFile().getAbsolutePath();
			confirmBtn.setEnabled(true);
		}
	}

}

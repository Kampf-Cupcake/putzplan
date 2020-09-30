import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

// TODO: TextField nur Zahlen
public class NeueAufgabeFrame extends JFrame {
	private static NeueAufgabeFrame instanz;

	public static NeueAufgabeFrame getInstanz() {
		if (instanz == null) {
			instanz = new NeueAufgabeFrame();
		}
		return instanz;
	}

	JPanel hintergrund = new JPanel();
	JTextField aufgabeField = new JTextField("Aufgabe");
	JTextField schwierigkeitField = new JTextField("Schwierigkeit");
	JTextField haufigkeitField = new JTextField("Häufigkeit");
	JButton confirmBtn = new JButton("Bestätigen");
	String aufgabe;
	String schwierigkeit;
	String haufigkeit;

	public NeueAufgabeFrame() {
		setTitle("Aufgabe erstellen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		GridLayout layout = new GridLayout(4, 0);
		layout.setVgap(14);

		hintergrund.setPreferredSize(new Dimension(200, 210));
		hintergrund.setBackground(new Color(99, 0, 0));
		hintergrund.setLayout(layout);
		hintergrund.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		add(hintergrund);

		initializeField(aufgabeField, "Aufgabe");
		initializeField(schwierigkeitField, "Schwierigkeit");
		initializeField(haufigkeitField, "Häufigkeit");

		confirmBtn.setEnabled(false);
		confirmBtn.addActionListener(e -> {
			confirmAufgabe();
		});
		hintergrund.add(confirmBtn);

		pack();
	}

	private void confirmAufgabe() {
		if (!(aufgabeField.getText().equals("Aufgabe")) && !(schwierigkeitField.getText().equals("Schwierigkeit"))
				&& !(haufigkeitField.getText().equals("Häufigkeit"))) {
			//new Aufgabe(aufgabeField.getText(), schwierigkeitField.getText(), haufigkeittextjjjjField.getText());
			setVisible(false);
			dispose();
			PutzplanGUI.getInstanz().updateBenutzer();
		}
	}

	private void initializeField(JTextField field, String name) {
		field.setForeground(Color.GRAY);
		field.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (field.getText().equals(name)) {
					field.setForeground(Color.BLACK);
					field.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (field.getText().isEmpty()) {
					field.setForeground(Color.GRAY);
					field.setText(name);
				}
			}
		});
		field.setHorizontalAlignment(JTextField.CENTER);
		hintergrund.add(field);
	}

}

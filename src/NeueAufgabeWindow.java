import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class NeueAufgabeWindow extends JFrame {

	JPanel hintergrund = new JPanel();
	JTextField aufgabeField = new JTextField("Aufgabe");
	JTextField schwierigkeitField = new JTextField("Schwierigkeit (1-5)", 1);
	JTextField haeufigkeitField = new JTextField("Häufigkeit (pro Monat)", 2);
	JButton confirmBtn = new JButton("Bestätigen");
	String aufgabe;
	String schwierigkeit;
	String haufigkeit;

	public NeueAufgabeWindow() {
		setTitle("Aufgabe erstellen");
		setResizable(false);

		GridLayout layout = new GridLayout(4, 0);
		layout.setVgap(14);

		hintergrund.setPreferredSize(new Dimension(200, 210));
		hintergrund.setBackground(new Color(99, 0, 0));
		hintergrund.setLayout(layout);
		hintergrund.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		add(hintergrund);

		initializeField(aufgabeField, "Aufgabe");
		initializeField(schwierigkeitField, "Schwierigkeit (1-5)");
		initializeField(haeufigkeitField, "Häufigkeit (pro Monat)");

		confirmBtn.setEnabled(true);
		confirmBtn.addActionListener(e -> {
			NeueAufgabeController.confirmAufgabe(aufgabeField, schwierigkeitField, haeufigkeitField);
		});
		hintergrund.add(confirmBtn);

		pack();
	}

	private void initializeField(JTextField field, String name) {
		field.setForeground(Color.GRAY);
		field.setHorizontalAlignment(JTextField.CENTER);
		Eingabefenster.platzhalter(field, name);
		if (field != aufgabeField) {
			field.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE
							|| ke.getKeyChar() == KeyEvent.VK_DELETE) {
						field.setEditable(true);
					} else {
						field.setEditable(false);
					}
				}
			});
		}
		hintergrund.add(field);
	}

}

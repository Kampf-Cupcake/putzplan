import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

// TODO: TextField max. 1/2 Zahlen; confirmBtn enabled/disabled; Aufgabe übergeben
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
	JTextField schwierigkeitField = new JTextField("Schwierigkeit (1-5)", 1);
	JTextField haufigkeitField = new JTextField("Häufigkeit (pro Monat)", 2);
	JButton confirmBtn = new JButton("Bestätigen");
	String aufgabe;
	String schwierigkeit;
	String haufigkeit;

	public NeueAufgabeFrame() {
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
		initializeField(haufigkeitField, "Häufigkeit (pro Monat)");

		confirmBtn.setEnabled(true);
		confirmBtn.addActionListener(e -> {
			confirmAufgabe();
		});
		hintergrund.add(confirmBtn);

		pack();
	}

	private void confirmAufgabe() {
		if (!(aufgabeField.getText().equals("Aufgabe")) && !(schwierigkeitField.getText().equals("Schwierigkeit (1-5)"))
				&& !(haufigkeitField.getText().equals("Häufigkeit (pro Monat)"))) {
			System.out.println("neue Aufgabe");
			// new Aufgabe(aufgabeField.getText(), schwierigkeitField.getText(),
			// haufigkeittextjjjjField.getText());
			setVisible(false);
			dispose();
			PutzplanGUI.getInstanz().updateAufgaben();
		}
	}

	private void initializeField(JTextField field, String name) {
		field.setForeground(Color.GRAY);
		field.setHorizontalAlignment(JTextField.CENTER);
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
		if (field != aufgabeField) {
			field.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE) {
						field.setEditable(true);
						/*
						if(!(aufgabeField.getText().equals("Aufgabe"))
								&& !(schwierigkeitField.getText().equals("Schwierigkeit (1-5)"))
								&& !(haufigkeitField.getText().equals("Häufigkeit (pro Monat)"))
								&& !(aufgabeField.getText().equals(""))
								&& !(schwierigkeitField.getText().equals(""))
								&& !(haufigkeitField.getText().equals(""))) {
							confirmBtn.setEnabled(true);
							
						}*/
					} else {
						field.setEditable(false);
					}
				}
			});
		}
		hintergrund.add(field);
	}

}

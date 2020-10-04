import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public interface Eingabefenster {
	/**
	 * Fügt einem TextField einen Platzhalter hinzu. (Wird bei fokussiertem Feld ausgeblendet)
	 * @param field TextField, das den Platzhalter erhalten soll
	 * @param platzhalter Platzhalter-Text
	 */
	static void platzhalter(JTextField field, String platzhalter) {
		field.setForeground(Color.GRAY);
		FocusListener f = new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (field.getText().equals(platzhalter)) {
					field.setForeground(Color.BLACK);
					field.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (field.getText().isEmpty()) {
					field.setForeground(Color.GRAY);
					field.setText(platzhalter);
				}
			}
		};
		field.addFocusListener(f);
	}
}

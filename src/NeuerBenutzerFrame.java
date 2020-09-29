import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NeuerBenutzerFrame extends JFrame implements ActionListener{
	private static NeuerBenutzerFrame instanz;
	
	public static NeuerBenutzerFrame getInstanz() {
		if (instanz == null) {
			instanz = new NeuerBenutzerFrame();
		}
		return instanz;
	}
	
	JPanel hintergrund = new JPanel();
	JButton bildBtn = new JButton();
	
	public NeuerBenutzerFrame() {
		hintergrund.setPreferredSize(new Dimension(300,300));
		hintergrund.setBackground(Color.BLUE);
		add(hintergrund);
		
		
		
		JFileChooser chooser = new JFileChooser();
		add(chooser);
        int rueckgabeWert = chooser.showOpenDialog(null);
        
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("Die zu öffnende Datei ist: " +
                  chooser.getSelectedFile().getName());
        }
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}

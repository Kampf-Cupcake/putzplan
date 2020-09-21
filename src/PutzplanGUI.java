import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;


public class PutzplanGUI extends JFrame {

	private javax.swing.JPanel hintergrund = new javax.swing.JPanel();
	private javax.swing.JPanel menu = new javax.swing.JPanel();
	private javax.swing.JPanel haupt = new javax.swing.JPanel();
	private javax.swing.JPanel benutzer = new javax.swing.JPanel();
	private javax.swing.JButton benutzerB = new javax.swing.JButton();
	private javax.swing.JPanel aufgaben = new javax.swing.JPanel();
	private javax.swing.JButton aufgabenB = new javax.swing.JButton();
	private javax.swing.JPanel kalendar = new javax.swing.JPanel();
	private javax.swing.JButton kalendarB = new javax.swing.JButton();
	
	JLabel label;
	
	public PutzplanGUI() {
		setTitle("Putzplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,900);
		
		this.setVisible(true);

		
        hintergrund.setLayout( new javax.swing.BoxLayout(
                hintergrund, javax.swing.BoxLayout.X_AXIS ) );
        
		hintergrund.add (menu);
	
			menu.setSize(200, 900);
			menu.setVisible(true);
			menu.setLayout(new javax.swing.BoxLayout(
					menu, javax.swing.BoxLayout.Y_AXIS));
			
			benutzerB.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	benutzerActionPerformed(evt);
	            }
			});
			
			menu.setVisible(true);
			menu.add(benutzer);
			benutzer.setSize(200, 300);
			benutzer.setBackground(Color.BLACK);
			benutzer.setVisible(true);
			//aufgaben.setText("A");
			menu.add(aufgaben);
			aufgaben.setSize(200, 300);
			//kalendar.setText("K");
			menu.add(kalendar);
			kalendar.setSize(200, 300);
		
		
		hintergrund.add (haupt);
		
			
	}
	
	private void benutzerActionPerformed( java.awt.event.ActionEvent evt ) {
		
	}
	

	public static void main(String[] args) {

		PutzplanGUI p = new PutzplanGUI();

		p.setVisible(true);
	}
	
}
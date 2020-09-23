import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionListener;


public class PutzplanGUI extends JFrame implements ActionListener {

	JPanel hintergrund = new JPanel();
		
	JPanel menu = new JPanel();
		JButton benutzerMB = new JButton();	
		JButton aufgabenMB = new JButton();
		JButton kalendarMB = new JButton();
	
	JPanel haupt = new JPanel();
		JLabel willkommen;
		
		JPanel benutzerHin = new JPanel();
			JLabel benutzer;
		JPanel benutzer1 = new JPanel();
		JPanel benutzer2 = new JPanel();
		JButton benutzerAnlegen = new JButton();

		JPanel aufgabenHin = new JPanel();
			JLabel aufgaben;

		JPanel kalendarHin = new JPanel();
			JLabel kalendar;
	

	
	public PutzplanGUI() {
		setTitle("Putzplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,950);
		setResizable(false);
		
		add(hintergrund);

		hintergrund.setLayout(null);
		hintergrund.add(menu);
		hintergrund.add(haupt);
		
		Insets insets = hintergrund.getInsets();
		
	//Menüfläche
		menu.setBounds(0 + insets.left, 0 + insets.top, 200, 900);
			
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
			benutzerMB.setIcon(new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\menu_benutzer2.png"));
			menu.add(benutzerMB);
			benutzerMB.addActionListener(this);
			
			aufgabenMB.setIcon(new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\menu_aufgaben2.png"));
			menu.add(aufgabenMB);
			aufgabenMB.addActionListener(this);
			
			kalendarMB.setIcon(new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\menu_kalendar2.png"));
			menu.add(kalendarMB);
			kalendarMB.addActionListener(this);
		
				
		haupt.setBounds(200 + insets.left, 0 + insets.top, 1000, 900);
		//haupt.setLayout(null);
		haupt.setBackground(Color.white);
		
	//Hauptfläche:Willkommen/Start-Teil
			ImageIcon start= new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\Hintergruende_willkommen.png");
			willkommen = new JLabel(start);
			haupt.add(willkommen);
			willkommen.setVisible(true);
			
			
	//Hauptfläche:Benutzer-Teil
			
			haupt.add(benutzerHin);
				ImageIcon benutzerH= new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\Hintergruende_benutzer.png");
				benutzer = new JLabel(benutzerH);
				benutzerHin.add(benutzer);
				benutzerHin.setVisible(false);
			haupt.add(benutzer1);
			
			benutzer1.setLayout(new BoxLayout(benutzer1, BoxLayout.X_AXIS));
				benutzer1.add(benutzerAnlegen);
				benutzerAnlegen.addActionListener(this);
				benutzer1.setVisible(false);

			
	//Hauptfläche:Aufgaben-Teil
			ImageIcon aufgabenH= new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\Hintergruende_aufgaben.png");
			aufgaben = new JLabel(aufgabenH);
			haupt.add(aufgaben);
			aufgaben.setVisible(false);

			
	//Hauptfläche:Kalendar-Teil
			ImageIcon kalendarH= new ImageIcon("C:\\Users\\inala\\Desktop\\Putzplan\\Hintergruende_kalendar.png");
			kalendar = new JLabel(kalendarH);
			haupt.add(kalendar);
			kalendar.setVisible(false);
		

	}
	

	public static void main(String[] args) {

		PutzplanGUI p = new PutzplanGUI();

		p.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, werden die jeweiligen Sachen ausgefüht
        if(ae.getSource() == this.benutzerMB){
        	willkommen.setVisible(false);
        	//benutzerHin.setVisible(true);
        	benutzer1.setVisible(true);
        	aufgaben.setVisible(false);
        	kalendar.setVisible(false);
        }
        else if(ae.getSource() == this.aufgabenMB){
        	willkommen.setVisible(false);
        	benutzerHin.setVisible(false);
        	benutzer1.setVisible(false);
        	aufgaben.setVisible(true);
        	kalendar.setVisible(false);
        }
        else if (ae.getSource() == this.kalendarMB){
        	willkommen.setVisible(false);
        	benutzerHin.setVisible(false);
        	benutzer1.setVisible(false);
        	aufgaben.setVisible(false);
        	kalendar.setVisible(true);
        }
        else if(ae.getSource() == this.benutzerAnlegen) {
        	
        }
    
	}
}
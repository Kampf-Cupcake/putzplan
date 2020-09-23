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
		//JLabel willkommen;
		
		JPanel benutzerHin = new JPanel();
			JLabel benutzer;
		JPanel benutzer1 = new JPanel();
		JPanel benutzer2 = new JPanel();
		JButton person1 = new JButton();
		JButton person2 = new JButton();
		JButton person3 = new JButton();

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
		
	//Menuefaeche
		menu.setBounds(0 + insets.left, 0 + insets.top, 200, 900);
			
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
			benutzerMB.setIcon(new ImageIcon("bilder\\icons\\menu_benutzer3.png"));
			benutzerMB.setMargin(new Insets(0, 0, 0, 0));
			menu.add(benutzerMB);
			benutzerMB.addActionListener(this);
			
			aufgabenMB.setIcon(new ImageIcon("bilder\\icons\\menu_aufgaben3.png"));
			aufgabenMB.setMargin(new Insets(0, 0, 0, 0));
			menu.add(aufgabenMB);
			aufgabenMB.addActionListener(this);
			
			kalendarMB.setIcon(new ImageIcon("bilder\\icons\\menu_kalendar3.png"));
			kalendarMB.setMargin(new Insets(0, 0, 0, 0));
			menu.add(kalendarMB);
			kalendarMB.addActionListener(this);
		
				
		haupt.setBounds(200 + insets.left, 0 + insets.top, 1000, 900);
		//haupt.setLayout(null);
		//haupt.setBackground(Color.white);
		
	//Hauptflaeche:Kalendar-Teil
		ImageIcon kalendarH= new ImageIcon("bilder\\icons\\Hintergruende_kalendar.png");
		kalendar = new JLabel(kalendarH);
		haupt.add(kalendar);
		//kalendar.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
		kalendar.setVisible(true);
		
	/**Hauptfläche:Willkommen/Start-Teil
			ImageIcon start= new ImageIcon("bilder\\icons\\Hintergruende_willkommen.png");
			willkommen = new JLabel(start);
			haupt.add(willkommen);
			willkommen.setVisible(true);*/
			
			
	//Hauptflaeche:Benutzer-Teil
			
			haupt.add(benutzerHin);
				//benutzerHin.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
				benutzerHin.setLayout(new BoxLayout(benutzerHin, BoxLayout.X_AXIS));
					//ImageIcon benutzerH = new ImageIcon("bilder\\icons\\Hintergruende_benutzer.png");
					//benutzer = new JLabel(benutzerH);
					//benutzerHin.add(benutzer);
					//benutzer.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
				
				
				person1.setIcon(new ImageIcon("bilder\\icons\\plus.png"));
				person1.setMargin(new Insets(0, 0, 0, 0));
				benutzerHin.add(person1);
				//person1.setBounds(0 + insets.left, 333 + insets.top, 333, 900);
				person1.addActionListener(this);
				
				//person1.setVisible(false);
				person2.setIcon(new ImageIcon("bilder\\icons\\plus.png"));
				person2.setMargin(new Insets(0, 0, 0, 0));
				benutzerHin.add(person2);
				person2.addActionListener(this);
				
				person3.setIcon(new ImageIcon("bilder\\icons\\plus.png"));
				person3.setMargin(new Insets(0, 0, 0, 0));
				benutzerHin.add(person3);
				person3.addActionListener(this);
				
				benutzerHin.setVisible(false);
			
			/**haupt.add(benutzer1);
				benutzer1.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
				benutzer1.add(person1);
				person1.addActionListener(this);
				benutzer1.add(person2);
				person2.addActionListener(this);
				benutzer1.add(person3);
				person3.addActionListener(this);
				
				benutzer1.setVisible(false);*/

			
	//Hauptflaeche:Aufgaben-Teil
			ImageIcon aufgabenH= new ImageIcon("bilder\\icons\\Hintergruende_aufgaben.png");
			aufgaben = new JLabel(aufgabenH);
			haupt.add(aufgaben);
			//aufgaben.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
			aufgaben.setVisible(false);


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
        	//willkommen.setVisible(false);
        	benutzerHin.setVisible(true);
        	//person1.setVisible(true);

        	aufgaben.setVisible(false);
        	kalendar.setVisible(false);
        }
        else if(ae.getSource() == this.aufgabenMB){
        	//willkommen.setVisible(false);
        	benutzerHin.setVisible(false);
        	benutzer1.setVisible(false);
        	aufgaben.setVisible(true);
        	kalendar.setVisible(false);
        }
        else if (ae.getSource() == this.kalendarMB){
        	//willkommen.setVisible(false);
        	benutzerHin.setVisible(false);
        	benutzer1.setVisible(false);
        	aufgaben.setVisible(false);
        	kalendar.setVisible(true);
        }
        else if(ae.getSource() == this.person1) {
        	
        }
        else if(ae.getSource() == this.person2) {
        	
        }
        else if(ae.getSource() == this.person3) {
        	
        }
    
	}
}
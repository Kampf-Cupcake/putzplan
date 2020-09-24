import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class PutzplanGUI extends JFrame implements ActionListener {

	JPanel hintergrund = new JPanel();
	JPanel haupt = new JPanel();
	JPanel menu = new JPanel();
	JPanel benutzerHin = new JPanel();
	JPanel benutzer1 = new JPanel();
	JPanel benutzer2 = new JPanel();
	JPanel aufgabenHin = new JPanel();
	JPanel kalendarHin = new JPanel();
	MonthPanel kalender;

	JButton benutzerMB = new JButton();
	JButton aufgabenMB = new JButton();
	JButton kalenderMB = new JButton();
	JButton person1 = new JButton();
	JButton person2 = new JButton();
	JButton person3 = new JButton();

	JLabel benutzer;
	JLabel aufgaben;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int windowHeight = (int) Math.ceil(0.8 * screenSize.getHeight());
	int windowWidth = (int) Math.ceil(1.2 * windowHeight);

	public PutzplanGUI() {
		
		// putzplanGUI
		setTitle("Putzplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(windowWidth, windowHeight);
		//setResizable(false);
		
		// layout
		BoxLayout layout = new BoxLayout(hintergrund, BoxLayout.X_AXIS);
		//layout.minimumLayoutSize(this);	

		// hintergrund
		hintergrund.setLayout(layout);
		hintergrund.add(menu);
		hintergrund.add(haupt);
		Insets insets = hintergrund.getInsets();
		hintergrund.setPreferredSize(new Dimension(windowWidth, windowHeight));
		add(hintergrund);

		// menu
		int menuWidth = (int) (windowWidth/5.6);
		menu.setSize(menuWidth, windowHeight);
		//menu.setBounds(insets.left, insets.top, (int) (windowWidth/6), windowHeight);
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));		
		
		benutzerMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_benutzer3.png"), menuWidth));
		benutzerMB.setMargin(new Insets(-4, 0, -4, 0));
		benutzerMB.addActionListener(this);
		benutzerMB.setBorderPainted(false);
		benutzerMB.setContentAreaFilled(false);
		menu.add(benutzerMB);

		aufgabenMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_aufgaben3.png"), menuWidth));
		aufgabenMB.setMargin(new Insets(-4, 0, -4, 0));
		aufgabenMB.addActionListener(this);
		aufgabenMB.setBorderPainted(false);
		aufgabenMB.setContentAreaFilled(false);
		menu.add(aufgabenMB);
		
		kalenderMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_kalendar3.png"), menuWidth));
		kalenderMB.setMargin(new Insets(-4, 0, -4, 0));
		kalenderMB.addActionListener(this);
		kalenderMB.setBorderPainted(false);
		kalenderMB.setContentAreaFilled(false);
		menu.add(kalenderMB);

		// haupt
		int hauptWidth = windowWidth - (int) (windowWidth/5.6);
		haupt.setPreferredSize(new Dimension(hauptWidth, windowHeight));
		//haupt.setBounds((int) (windowWidth/6), 0, hauptWidth, windowHeight-39);
		// haupt.setLayout(null);
		// haupt.setBackground(Color.white);
		// Hauptflaeche:Kalender-Teil
		// ImageIcon kalendarH = new
		// ImageIcon("bilder\\icons\\Hintergruende_kalendar.png");

		/**
		 * Hauptfläche:Willkommen/Start-Teil ImageIcon start= new
		 * ImageIcon("bilder\\icons\\Hintergruende_willkommen.png"); willkommen = new
		 * JLabel(start); haupt.add(willkommen); willkommen.setVisible(true);
		 */

		// haupt: benutzer
		haupt.add(benutzerHin);
		// benutzerHin.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
		benutzerHin.setLayout(new BoxLayout(benutzerHin, BoxLayout.X_AXIS));
		// ImageIcon benutzerH = new
		// ImageIcon("bilder\\icons\\Hintergruende_benutzer.png");
		// benutzer = new JLabel(benutzerH);
		// benutzerHin.add(benutzer);
		// benutzer.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);

		person1.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth/3));
		person1.setMargin(new Insets(0, 0, 0, 0));
		person1.setBorderPainted(false);
		person1.setContentAreaFilled(false);
		person1.addActionListener(this);
		benutzerHin.add(person1);
		
		person2.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth/3));
		person2.setMargin(new Insets(0, 0, 0, 0));
		person2.setBorderPainted(false);
		person2.setContentAreaFilled(false);
		person2.addActionListener(this);
		benutzerHin.add(person2);

		person3.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth/3));
		person3.setMargin(new Insets(0, 0, 0, 0));
		person3.setBorderPainted(false);
		person3.setContentAreaFilled(false);
		person3.addActionListener(this);
		benutzerHin.add(person3);

		benutzerHin.setVisible(false);

		/**
		 * haupt.add(benutzer1); benutzer1.setBounds(0 + insets.left, 0 + insets.top,
		 * 1000, 900); benutzer1.add(person1); person1.addActionListener(this);
		 * benutzer1.add(person2); person2.addActionListener(this);
		 * benutzer1.add(person3); person3.addActionListener(this);
		 * 
		 * benutzer1.setVisible(false);
		 */

		// haupt: aufgaben
		ImageIcon aufgabenH = new ImageIcon("bilder\\icons\\Hintergruende_aufgaben.png");
		aufgaben = new JLabel(aufgabenH);
		haupt.add(aufgaben);
		// aufgaben.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
		aufgaben.setVisible(false);

		// haupt: kalender
		kalender = new MonthPanel(9, 2020);
		haupt.add(kalender);
		// kalender.setBounds(0 + insets.left, 0 + insets.top, 1000, 900);
		kalender.setVisible(true);
		
		System.out.println("totalWidth: " + windowWidth);
		System.out.println("menuWidth: " + menuWidth);
		System.out.println("hauptWidth: " + hauptWidth);
		System.out.println();
		System.out.println("height: " + windowHeight);
		pack();
	}

	public static void main(String[] args) {

		PutzplanGUI p = new PutzplanGUI();

		p.setVisible(true);
	}
	
	public ImageIcon resize(ImageIcon i, int width) {
		Image image = i.getImage();
		int newH = (int) Math.round(i.getIconHeight() * ((float) width / i.getIconWidth()));
		i = new ImageIcon(image.getScaledInstance(width, newH, java.awt.Image.SCALE_SMOOTH));
		return i;
	}

	public void actionPerformed(ActionEvent ae) {
		// Die Quelle wird mit getSource() abgefragt und mit den
		// Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
		// der Buttons ist, werden die jeweiligen Sachen ausgefüht
		if (ae.getSource() == this.benutzerMB) {
			// willkommen.setVisible(false);
			benutzerHin.setVisible(true);
			// person1.setVisible(true);

			aufgaben.setVisible(false);
			kalender.setVisible(false);
		} else if (ae.getSource() == this.aufgabenMB) {
			// willkommen.setVisible(false);
			benutzerHin.setVisible(false);
			benutzer1.setVisible(false);
			aufgaben.setVisible(true);
			kalender.setVisible(false);
		} else if (ae.getSource() == this.kalenderMB) {
			// willkommen.setVisible(false);
			benutzerHin.setVisible(false);
			benutzer1.setVisible(false);
			aufgaben.setVisible(false);
			kalender.setVisible(true);
			haupt.setBackground(new Color(22, 35, 54));
		} else if (ae.getSource() == this.person1) {

		} else if (ae.getSource() == this.person2) {

		} else if (ae.getSource() == this.person3) {

		}

	}

	// Kalender
	static class MonthPanel extends JPanel {
		int month;
		int year;
		protected String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };

		protected String[] dayNames = { "S", "M", "T", "W", "T", "F", "S" };

		public MonthPanel(int month, int year) {
			this.month = month-1;
			this.year = year;
			JPanel monthPanel = new JPanel(true);
			monthPanel.setLayout(new BorderLayout());
			monthPanel.add(createTitleGUI(), BorderLayout.NORTH);
			monthPanel.add(createDaysGUI(), BorderLayout.SOUTH);
			this.add(monthPanel);
		}

		protected JPanel createTitleGUI() {
			JPanel titlePanel = new JPanel(true);
			titlePanel.setLayout(new FlowLayout());
			titlePanel.setBackground(Color.WHITE);

			JLabel label = new JLabel(monthNames[month] + " " + year);
			label.setForeground(SystemColor.activeCaption);
			titlePanel.add(label, BorderLayout.CENTER);
			return titlePanel;
		}

		protected JPanel createDaysGUI() {
			JPanel dayPanel = new JPanel(true);
			dayPanel.setLayout(new GridLayout(0, dayNames.length));

			Calendar today = Calendar.getInstance();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.DAY_OF_MONTH, 1);

			Calendar iterator = (Calendar) calendar.clone();
			iterator.add(Calendar.DAY_OF_MONTH, -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

			Calendar maximum = (Calendar) calendar.clone();
			maximum.add(Calendar.MONTH, +1);

			for (int i = 0; i < dayNames.length; i++) {
				JPanel dPanel = new JPanel(true);
				dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JLabel dLabel = new JLabel(dayNames[i]);
				dPanel.add(dLabel);
				dayPanel.add(dPanel);
			}

			int count = 0;
			int limit = dayNames.length * 6;

			while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
				int lMonth = iterator.get(Calendar.MONTH);
				int lYear = iterator.get(Calendar.YEAR);

				JPanel dPanel = new JPanel(true);
				dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JLabel dayLabel = new JLabel();

				if ((lMonth == month) && (lYear == year)) {
					int lDay = iterator.get(Calendar.DAY_OF_MONTH);
					dayLabel.setText(Integer.toString(lDay));
				}
				dPanel.add(dayLabel);
				dayPanel.add(dPanel);
				iterator.add(Calendar.DAY_OF_YEAR, +1);
				count++;
			}

			for (int i = count; i < limit; i++) {
				JPanel dPanel = new JPanel(true);
				dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				dPanel.add(new JLabel());
				dayPanel.add(dPanel);
			}
			return dayPanel;
		}
	}
}
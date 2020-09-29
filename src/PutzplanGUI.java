import javax.swing.*;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class PutzplanGUI extends JFrame implements ActionListener {

	JPanel hintergrund = new JPanel();
	JPanel haupt = new JPanel();
	JPanel menu = new JPanel();
	JPanel benutzer = new JPanel();
	JPanel benutzer1 = new JPanel();
	JPanel benutzer2 = new JPanel();
	JPanel aufgaben = new JPanel();
	JPanel kalendarHin = new JPanel();
	MonthPanel kalender;

	JButton benutzerMB = new JButton();
	JButton aufgabenMB = new JButton();
	JButton kalenderMB = new JButton();
	JButton person1 = new JButton();
	JButton person2 = new JButton();
	JButton person3 = new JButton();
	JButton aufgabenplus = new JButton();

	// JLabel benutzer;
	JLabel aufgabenUeberschrift;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int windowHeight = (int) Math.ceil(0.8 * screenSize.getHeight());
	int windowWidth = (int) Math.ceil(1.2 * windowHeight);

	public PutzplanGUI() {

		// putzplanGUI
		setTitle("Putzplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setSize(windowWidth, windowHeight);
		setResizable(false);

		// layout
		BoxLayout layout = new BoxLayout(hintergrund, BoxLayout.X_AXIS);
		// layout.minimumLayoutSize(this);

		// hintergrund
		hintergrund.setLayout(layout);
		hintergrund.add(menu);
		hintergrund.add(haupt);
		Insets insets = hintergrund.getInsets();
		hintergrund.setPreferredSize(new Dimension(windowWidth, windowHeight));
		add(hintergrund);

		// menu
		int menuWidth = (int) (windowWidth / 5.6);
		menu.setPreferredSize(new Dimension(menuWidth, windowHeight));
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
		int hauptWidth = windowWidth - menuWidth;
		haupt.setPreferredSize(new Dimension(hauptWidth, windowHeight));
		haupt.setBackground(new Color(22, 35, 54));

		// haupt: benutzer
		haupt.add(benutzer);
		benutzer.setPreferredSize(new Dimension(hauptWidth, windowHeight));
		benutzer.setLayout(new BoxLayout(benutzer, BoxLayout.X_AXIS));
		benutzer.setBackground(new Color(1, 83, 82));

		person1.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth / 3));
		person1.setMargin(new Insets(0, 0, 0, 0));
		person1.setBorderPainted(false);
		person1.setContentAreaFilled(false);
		person1.addActionListener(this);
		benutzer.add(person1);

		person2.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth / 3));
		person2.setMargin(new Insets(0, 0, 0, 0));
		person2.setBorderPainted(false);
		person2.setContentAreaFilled(false);
		person2.addActionListener(this);
		benutzer.add(person2);

		person3.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth / 3));
		person3.setMargin(new Insets(0, 0, 0, 0));
		person3.setBorderPainted(false);
		person3.setContentAreaFilled(false);
		person3.addActionListener(this);
		benutzer.add(person3);

		benutzer.setVisible(false);

		// haupt: aufgaben
		aufgaben.setBackground(new Color(99, 0, 0));
		haupt.add(aufgaben);
		aufgaben.setLayout(new BorderLayout());
		aufgabenUeberschrift = new JLabel("Aufgaben:");
		aufgabenUeberschrift.setForeground(Color.white);
		aufgabenUeberschrift.setFont(aufgabenUeberschrift.getFont().deriveFont(40f));
		aufgabenUeberschrift.setPreferredSize(new Dimension(200, 100));
		aufgaben.add(aufgabenUeberschrift, BorderLayout.PAGE_START);

		// hier mit AufgabeClasse verbinden
		new Aufgabe("a", 3, 3);
		JList aufgabenauflistung = new JList(Aufgabenliste.getInstanz().arrayAusgeben());
		aufgabenauflistung.setBackground(new Color(99, 0, 0));
		aufgabenauflistung.setForeground(Color.white);
		aufgabenauflistung.setFont(aufgabenauflistung.getFont().deriveFont(20f));
		aufgabenauflistung.setPreferredSize(new Dimension(400, 400));
		aufgaben.add(aufgabenauflistung, BorderLayout.CENTER);

		aufgabenplus.setIcon(resize(new ImageIcon("bilder\\icons\\plusrot.png"), hauptWidth / 3));
		aufgabenplus.setMargin(new Insets(0, 0, 0, 0));
		aufgabenplus.setBorderPainted(false);
		aufgabenplus.setContentAreaFilled(false);
		aufgabenplus.addActionListener(this);

		aufgaben.add(aufgabenplus, BorderLayout.LINE_END);
		aufgaben.setVisible(false);

		// haupt: kalender
		LocalDate datum = LocalDate.now();
		kalender = new MonthPanel(datum.getMonthValue(), datum.getYear());
		haupt.add(kalender);
		kalender.setVisible(true);

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
			benutzer.setVisible(true);
			aufgaben.setVisible(false);
			kalender.setVisible(false);
			haupt.setBackground(new Color(1, 83, 82));
		} else if (ae.getSource() == this.aufgabenMB) {
			benutzer.setVisible(false);
			benutzer1.setVisible(false);
			aufgaben.setVisible(true);
			kalender.setVisible(false);
			haupt.setBackground(new Color(99, 0, 0));
		} else if (ae.getSource() == this.kalenderMB) {
			benutzer.setVisible(false);
			aufgaben.setVisible(false);
			kalender.setVisible(true);
			haupt.setBackground(new Color(22, 35, 54));
		} else if (ae.getSource() == this.person1) {
			NeuerBenutzerFrame.getInstanz();
			NeuerBenutzerFrame.getInstanz().setVisible(true);;
		} else if (ae.getSource() == this.person2) {

		} else if (ae.getSource() == this.person3) {

		} else if (ae.getSource() == this.aufgabenplus) {

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
			this.month = month - 1;
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
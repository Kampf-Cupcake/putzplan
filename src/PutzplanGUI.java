import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

// TODO: Aufgabengenerierung für beliebige Anzahl an Benutzern (Liste statt 3 Variablen?)
public class PutzplanGUI extends JFrame implements ActionListener {

	private static PutzplanGUI instanz;

	JPanel hintergrund = new JPanel();
	JPanel haupt = new JPanel();
	JPanel menu = new JPanel();
	JPanel benutzer = new JPanel();
	JPanel aufgaben = new JPanel();
	JPanel kalendarHin = new JPanel();
	JPanel kalender = new JPanel();

	JButton benutzerMB = new JButton();
	JButton aufgabenMB = new JButton();
	JButton toDoMB = new JButton();
	JButton person1 = new JButton();
	JButton person2 = new JButton();
	JButton person3 = new JButton();
	JButton aufgabenplus = new JButton();
	JButton generierenBtn = new JButton("Generieren");
	JButton exportBtn = new JButton("Exportieren");
	LinkedList<JPanel> aufgabenPanels = new LinkedList<JPanel>();

	String colNames[] = { "Name", "Schwierigkeit", "Haeufigkeit" };
	DefaultTableModel tableModel = new DefaultTableModel(colNames, 0);
	JTable aufgabenauflistung = new JTable(tableModel);

	// JLabel benutzer;
	// JLabel aufgabenUeberschrift;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int windowHeight = (int) Math.ceil(0.8 * screenSize.getHeight());
	int windowWidth = (int) Math.ceil(1.2 * windowHeight);
	int menuWidth = (int) (windowWidth / 5.6);
	int hauptWidth = windowWidth - menuWidth;

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
		menu.setPreferredSize(new Dimension(menuWidth, windowHeight));
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

		benutzerMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_benutzer3.png"), (int) (menuWidth * 1.05f)));
		benutzerMB.setMargin(new Insets(-4, 0, -4, 0));
		benutzerMB.addActionListener(this);
		benutzerMB.setBorderPainted(false);
		benutzerMB.setContentAreaFilled(false);
		menu.add(benutzerMB);

		aufgabenMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_aufgaben3.png"), (int) (menuWidth * 1.05f)));
		aufgabenMB.setMargin(new Insets(-4, 0, -4, 0));
		aufgabenMB.addActionListener(this);
		aufgabenMB.setBorderPainted(false);
		aufgabenMB.setContentAreaFilled(false);
		menu.add(aufgabenMB);

		toDoMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_toDo.png"), (int) (menuWidth * 1.05f)));
		toDoMB.setMargin(new Insets(-4, 0, -4, 0));
		toDoMB.addActionListener(this);
		toDoMB.setBorderPainted(false);
		toDoMB.setContentAreaFilled(false);
		menu.add(toDoMB);

		// haupt
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

		new Benutzer("Andrea", "bilder\\personas\\Persona1.png");
		new Benutzer("Bernd", "bilder\\personas\\Persona2.png");
		new Benutzer("Claire", "bilder\\personas\\Persona3.png");
		updateBenutzer();

		benutzer.setVisible(false);

		// haupt: aufgaben
		aufgaben.setBackground(new Color(99, 0, 0));
		aufgaben.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -40));
		haupt.add(aufgaben);
		aufgaben.setLayout(new BorderLayout());

		new Aufgabe("Bad putzen", 5, 1);
		new Aufgabe("Blumen giessen", 1, 3);
		new Aufgabe("Boden wischen", 3, 1);
		new Aufgabe("Fenster putzen", 3, 1);
		new Aufgabe("Küche putzen", 4, 1);
		new Aufgabe("Müll rausbringen", 1, 7);
		new Aufgabe("Spülmaschine", 2, 3);
		new Aufgabe("Staubsaugen", 1, 4);
		new Aufgabe("Treppenhaus fegen", 2, 1);
		updateAufgaben();

		aufgabenauflistung.setBackground(new Color(99, 0, 0));
		aufgabenauflistung.setForeground(Color.white);
		aufgabenauflistung.setPreferredSize(new Dimension(400, 400));
		aufgaben.add(new JScrollPane(aufgabenauflistung), BorderLayout.CENTER);

		aufgabenplus.setIcon(resize(new ImageIcon("bilder\\icons\\plusrot.png"), hauptWidth / 3));
		aufgabenplus.setMargin(new Insets(0, 0, 0, 0));
		aufgabenplus.setBorderPainted(false);
		aufgabenplus.setContentAreaFilled(false);
		aufgabenplus.addActionListener(this);
		/*
		 * generierenBtn.setMargin(new Insets(0, 0, 0, 0));
		 * generierenBtn.addActionListener(this);
		 */
		JPanel btns = new JPanel();
		btns.setLayout(new BoxLayout(btns, BoxLayout.Y_AXIS));
		// btns.setAlignmentY(Component.CENTER_ALIGNMENT);
		btns.add(aufgabenplus);
		JPanel helperPanel = new JPanel();
		helperPanel.setMaximumSize(new Dimension(hauptWidth / 4, 400));
		helperPanel.add(generierenBtn);
		btns.add(helperPanel);
		btns.setBackground(new Color(99, 0, 0));
		aufgaben.add(btns, BorderLayout.LINE_END);
		aufgaben.setVisible(false);

		// haupt: kalender
		kalender.setBackground(new Color(22, 35, 54));
		LinkedList<JPanel> persPanels = new LinkedList<JPanel>();

		JPanel toDoList = new JPanel();
		toDoList.setLayout(new BoxLayout(toDoList, BoxLayout.Y_AXIS));

		int i = 0;
		for (Benutzer b : Benutzer.getAlleBenutzer()) {
			JPanel personPanel = new JPanel();
			personPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
			personPanel.setBackground(new Color(i * 30 + 80, i * 30 + 80, i * 30 + 80));
			personPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;

			JLabel bild = new JLabel();
			bild.setIcon(resize(new ImageIcon(b.getBild()), hauptWidth / 10));
			personPanel.add(bild);

			JLabel name = new JLabel(b.toString());
			name.setFont(new Font("Arial", 1, 24));
			personPanel.add(name);

			c.gridy = 0;
			c.gridy = i;
			persPanels.add(personPanel);
			toDoList.add(personPanel);

			JPanel aufgaben = new JPanel();
			aufgaben.setBackground(Color.red);
			aufgaben.setLayout(new BoxLayout(aufgaben, BoxLayout.Y_AXIS));
			aufgabenPanels.add(aufgaben);
			toDoList.add(aufgaben);

			i++;
		}

		kalender.add(toDoList, BorderLayout.EAST);

		JPanel toDoBtns = new JPanel();
		toDoBtns.setLayout(new BoxLayout(toDoBtns, BoxLayout.Y_AXIS));

		generierenBtn.addActionListener(this);
		toDoBtns.add(generierenBtn);

		exportBtn.addActionListener(this);
		toDoBtns.add(exportBtn);

		kalender.add(toDoBtns, BorderLayout.WEST);
		haupt.add(kalender);
		kalender.setVisible(true);

		pack();
	}

	public static void main(String[] args) {
		getInstanz();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		getInstanz().setVisible(true);
	}

	public static PutzplanGUI getInstanz() {
		if (instanz == null) {
			instanz = new PutzplanGUI();
		}
		return instanz;
	}

	public ImageIcon resize(ImageIcon i, int width) {
		Image image = i.getImage();
		int newH = (int) Math.round(i.getIconHeight() * ((float) width / i.getIconWidth()));
		i = new ImageIcon(image.getScaledInstance(width, newH, java.awt.Image.SCALE_SMOOTH));
		return i;
	}

	public void updateBenutzer() {
		for (int i = 0; i < 3; i++) {
			if (Benutzer.getAlleBenutzer().size() > i) {
				switch (i) {
				case 0:
					person1.setIcon(resize(new ImageIcon(Benutzer.getAlleBenutzer().get(0).getBild()), hauptWidth / 3));
					break;
				case 1:
					person2.setIcon(resize(new ImageIcon(Benutzer.getAlleBenutzer().get(1).getBild()), hauptWidth / 3));
					break;
				case 2:
					person3.setIcon(resize(new ImageIcon(Benutzer.getAlleBenutzer().get(2).getBild()), hauptWidth / 3));
					break;
				}
			}
		}
	}

	public void updateAufgaben() {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		for (Aufgabe a : Aufgabe.getAlleAufgaben()) {
			tableModel.addRow(new Object[] { a.getName(), a.getSchwierigkeit(), a.getHaeufigkeit() });
		}
	}

	public void planGenerieren() {
		LinkedList<Benutzer> benutzer = (LinkedList<Benutzer>) Benutzer.getAlleBenutzer().clone();
		Collections.shuffle(benutzer);
		LinkedList<Aufgabe> aufgaben = Aufgabe.getAlleAufgaben();
		LinkedList<Integer> schwierigk = new LinkedList<Integer>();

		for (Benutzer b : benutzer) {
			schwierigk.add(0);
		}

		for (int aktuelleAufgabe = 0; aktuelleAufgabe < aufgaben.size(); aktuelleAufgabe++) {
			for (int aktuell = 0; aktuell < schwierigk.size(); aktuell++) {
				boolean istKleinste = true;
				for (int vergleich = aktuell + 1; vergleich < schwierigk.size(); vergleich++) {
					if (schwierigk.get(vergleich) < schwierigk.get(aktuell)) {
						istKleinste = false;
						break;
					}
				}
				if (istKleinste) {
					schwierigk
							.set(aktuell,
									(aufgaben.get(aktuelleAufgabe).getSchwierigkeit()
											* aufgaben.get(aktuelleAufgabe).getHaeufigkeit())
											+ schwierigk.get(aktuell));
					Benutzer.getAlleBenutzer().get(aktuell).aufgabeGeben(aufgaben.get(aktuelleAufgabe));
					aufgabenPanels.get(aktuell).add(new JLabel(aufgaben.get(aktuelleAufgabe).getName()));
					break;
				}
			}
		}
		kalender.revalidate();
	}

	public void planExportieren() throws IOException {
        final PdfWriter pdfWriter = new PdfWriter("hello.pdf");
        final PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        try (final Document document = new Document(pdfDocument)) {
            document.add(new Paragraph("Hello World!"));
        }
	}

	public void actionPerformed(ActionEvent ae) {
		// Die Quelle wird mit getSource() abgefragt und mit den
		// Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
		// der Buttons ist, werden die jeweiligen Sachen ausgefÃ¼ht
		if (ae.getSource() == this.benutzerMB) {
			benutzer.setVisible(true);
			aufgaben.setVisible(false);
			kalender.setVisible(false);
			haupt.setBackground(new Color(1, 83, 82));
		} else if (ae.getSource() == this.aufgabenMB) {
			benutzer.setVisible(false);
//			benutzer1.setVisible(false);
			aufgaben.setVisible(true);
			kalender.setVisible(false);
			haupt.setBackground(new Color(99, 0, 0));
		} else if (ae.getSource() == this.toDoMB) {
			benutzer.setVisible(false);
			aufgaben.setVisible(false);
			kalender.setVisible(true);
			haupt.setBackground(new Color(22, 35, 54));
		} else if (ae.getSource() == this.person1 || ae.getSource() == this.person2 || ae.getSource() == this.person3) {
			NeuerBenutzerFrame.getInstanz().setVisible(true);
		} else if (ae.getSource() == this.aufgabenplus) {
			NeueAufgabeFrame.getInstanz().setVisible(true);
		} else if (ae.getSource() == this.generierenBtn) {
			planGenerieren();
		} else if (ae.getSource() == this.exportBtn) {
			try {
				planExportieren();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
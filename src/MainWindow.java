import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class MainWindow extends JFrame implements ActionListener {

	private JPanel hintergrund = new JPanel();
	private JPanel haupt = new JPanel();
	private JPanel menu = new JPanel();
	private JPanel benutzer = new JPanel();
	private JPanel aufgaben = new JPanel();
	private JPanel toDoPanel = new JPanel();
	private JPanel toDoList = new JPanel();

	private JButton benutzerMB = new JButton();
	private JButton aufgabenMB = new JButton();
	private JButton toDoMB = new JButton();
	private JButton person1 = new JButton();
	private JButton person2 = new JButton();
	private JButton person3 = new JButton();
	private JButton aufgabenplus = new JButton();
	private JButton generierenBtn = new JButton("Generieren");
	private JButton exportBtn = new JButton("Exportieren");

	JButton[] benutzerButtons = { person1, person2, person3 };

	private DefaultTableModel tableModel = new DefaultTableModel(
			new String[] { "Name", "Schwierigkeit", "Haeufigkeit" }, 0);
	private JTable aufgabenauflistung = new JTable(tableModel);

	private LinkedList<JPanel> aufgabenPanels = new LinkedList<JPanel>();

	private JTextField startDate = new JTextField();
	private JTextField endDate = new JTextField();

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int windowHeight = (int) Math.ceil(0.8 * screenSize.getHeight());
	private int windowWidth = (int) Math.ceil(1.35 * windowHeight);
	private int menuWidth = (int) (windowWidth / 6);
	private int hauptWidth = windowWidth - menuWidth;

	/**
	 * Konstruktor zum erstellen des Hauptfensters der Anwendung
	 */
	public MainWindow() {

		// MainWindow
		setTitle("Putzplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Hintergrund
		hintergrund.setLayout(new BoxLayout(hintergrund, BoxLayout.X_AXIS));
		hintergrund.add(menu);
		hintergrund.add(haupt);
		hintergrund.setPreferredSize(new Dimension(windowWidth, windowHeight));
		add(hintergrund);

		// Menu
		menu.setPreferredSize(new Dimension(menuWidth, windowHeight));
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

		// Menu-Buttons
		JButton[] menuButtons = { benutzerMB, aufgabenMB, toDoMB };
		benutzerMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_benutzer3.png"), (int) (menuWidth)));
		aufgabenMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_aufgaben3.png"), (int) (menuWidth)));
		toDoMB.setIcon(resize(new ImageIcon("bilder\\icons\\menu_toDo.png"), (int) (menuWidth)));
		for (JButton b : menuButtons) {
			b.setMargin(new Insets(-4, 0, -4, 0));
			b.addActionListener(this);
			b.setBorderPainted(false);
			b.setContentAreaFilled(false);
			menu.add(b);
		}

		// Haupt
		haupt.setPreferredSize(new Dimension(hauptWidth, windowHeight));
		haupt.setBackground(new Color(1, 83, 82));

		// Haupt: Benutzer
		haupt.add(benutzer);
		benutzer.setPreferredSize(new Dimension(hauptWidth, windowHeight));
		benutzer.setLayout(new BoxLayout(benutzer, BoxLayout.X_AXIS));
		benutzer.setBackground(new Color(1, 83, 82));
		benutzer.setVisible(true);

		for (JButton b : benutzerButtons) {
			b.setIcon(resize(new ImageIcon("bilder\\icons\\plus.png"), hauptWidth / 3));
			b.setBorderPainted(false);
			b.setContentAreaFilled(false);
			b.addActionListener(this);
			b.setMargin(new Insets(0, 0, 0, 0));
			benutzer.add(b);
		}

		// Haupt: Aufgaben
		aufgaben.setBackground(new Color(99, 0, 0));
		aufgaben.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -40));
		aufgaben.setVisible(false);
		haupt.add(aufgaben);

		aufgabenauflistung.setBackground(new Color(99, 0, 0));
		aufgabenauflistung.setForeground(Color.white);
		aufgabenauflistung.setPreferredSize(new Dimension(400, 400));
		aufgaben.add(new JScrollPane(aufgabenauflistung), BorderLayout.CENTER);

		aufgabenplus.setIcon(resize(new ImageIcon("bilder\\icons\\plusrot.png"), hauptWidth / 3));
		aufgabenplus.setBorderPainted(false);
		aufgabenplus.setContentAreaFilled(false);
		aufgabenplus.addActionListener(this);
		aufgaben.add(aufgabenplus, BorderLayout.EAST);

		// Haupt: ToDo
		toDoPanel.setBackground(new Color(22, 35, 54));
		toDoPanel.setLayout(new BorderLayout());
		startDate.setText(LocalDate.now().toString());
		endDate.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().lengthOfMonth()).toString());
		startDate.setMargin(new Insets(5,5,5,5));
		endDate.setMargin(new Insets(5,5,5,5));
		startDate.setHorizontalAlignment(JTextField.CENTER);
		endDate.setHorizontalAlignment(JTextField.CENTER);
		JPanel dates = new JPanel();
		dates.setLayout(new BoxLayout(dates, BoxLayout.X_AXIS));
		dates.add(startDate);
		dates.add(endDate);
		toDoPanel.add(dates, BorderLayout.NORTH);
		
		toDoList.setLayout(new BoxLayout(toDoList, BoxLayout.Y_AXIS));
		toDoList.setBackground(Color.LIGHT_GRAY);
		
		toDoPanel.add(toDoList, BorderLayout.CENTER);

		JPanel toDoBtns = new JPanel();
		toDoBtns.setLayout(new BoxLayout(toDoBtns, BoxLayout.Y_AXIS));
		generierenBtn.addActionListener(this);
		toDoBtns.add(generierenBtn);
		exportBtn.addActionListener(this);
		exportBtn.setEnabled(false);
		toDoBtns.add(exportBtn);
		toDoPanel.add(toDoBtns, BorderLayout.EAST);
		haupt.add(toDoPanel);
		toDoPanel.setVisible(false);
		pack();
		setVisible(true);
	}

	/**
	 * Funktion zum skalieren eines ImageIcons
	 * @param i zu skalierendes ImageIcon
	 * @param width gewünschte Breite
	 * @return skaliertes ImageIcon
	 */
	public ImageIcon resize(ImageIcon i, int width) {
		Image image = i.getImage();
		int newH = (int) Math.round(i.getIconHeight() * ((float) width / i.getIconWidth()));
		i = new ImageIcon(image.getScaledInstance(width, newH, java.awt.Image.SCALE_SMOOTH));
		return i;
	}

	/**
	 * Ausgabe des TableModels
	 * @return TableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Ausgabe des Panels für die ToDoList
	 * @return ToDoList-Panel
	 */
	public JPanel getToDoList() {
		return toDoList;
	}

	/**
	 * Aufruf der Verwaltung von onClick-Events
	 */
	public void actionPerformed(ActionEvent ae) {
		MainController.onClick(ae);
	}

	/**
	 * Ausgabe des Benutzer Menü-Buttons
	 * @return Benutzer Menü-Button
	 */
	public JButton getBenutzerMB() {
		return benutzerMB;
	}

	/**
	 * Ausgabe des Aufgaben Menü-Buttons
	 * @return Aufgaben Menü-Button
	 */
	public JButton getAufgabenMB() {
		return aufgabenMB;
	}
	
	/**
	 * Ausgabe des ToDo Menü-Buttons
	 * @return ToDo Menü-Button
	 */
	public JButton getToDoMB() {
		return toDoMB;
	}

	/**
	 * Ausgabe der Benutzer-Buttons
	 * @return Benutzer-Buttons
	 */
	public JButton[] getBenutzerButtons() {
		return benutzerButtons;
	}

	/**
	 * Ausgabe des Generieren Buttons
	 * @return Generieren Button
	 */
	public JButton getGenerierenButton() {
		return generierenBtn;
	}

	/**
	 * Ausgabe des Aufgabe hinzufügen Buttons
	 * @return Aufgabe hinzufügen Button
	 */
	public JButton getAufgabenplusButton() {
		return aufgabenplus;
	}

	/**
	 * Ausgabe des Exportieren-Buttons
	 * @return Exportieren-Button
	 */
	public JButton getExportButton() {
		return exportBtn;
	}

	/**
	 * Ausgabe des Benutzer-Panels
	 * @return Benutzer-Panel
	 */
	public JPanel getBenutzerPanel() {
		return benutzer;
	}

	/**
	 * Ausgabe des Aufgaben-Panels
	 * @return Aufgaben-Panel
	 */
	public JPanel getAufgabenPanel() {
		return aufgaben;
	}

	/**
	 * Ausgabe des ToDo-Panels
	 * @return ToDo-Panel
	 */
	public JPanel getToDoPanel() {
		return toDoPanel;
	}

	/**
	 * Ausgabe des Haupt-Panels
	 * @return Haupt-Panel
	 */
	public JPanel getHauptPanel() {
		return haupt;
	}

	/**
	 * Ausgabe der Aufgaben-Panels
	 * @return Liste mit Aufgaben-Panels
	 */
	public LinkedList<JPanel> getAufgabenPanels() {
		return aufgabenPanels;
	}

	/**
	 * Ausgabe der Breite des Hauptfensters
	 * @return Breite des Hauptfensters
	 */
	public int getHauptWidth() {
		return hauptWidth;
	}
	
	/**
	 * Ausgabe des Inhaltes des Startdatum-Feldes
	 * @return Inhalt des Startdatum-Feldes
	 */
	public String getStartDate() {
		return startDate.getText();
	}
	
	/**
	 * Ausgabe des Inhaltes des Enddatum-Feldes
	 * @return Inhalt des Enddatum-Feldes
	 */
	public String getEndDate() {
		return endDate.getText();
	}
}
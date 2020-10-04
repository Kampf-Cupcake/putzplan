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

	private static MainWindow instanz;

	private JPanel hintergrund = new JPanel();
	private JPanel haupt = new JPanel();
	private JPanel menu = new JPanel();
	private JPanel benutzer = new JPanel();
	private JPanel aufgaben = new JPanel();
	private JPanel kalendarHin = new JPanel();
	private JPanel kalender = new JPanel();
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

	private LinkedList<JPanel> persPanels = new LinkedList<JPanel>();
	private LinkedList<JPanel> aufgabenPanels = new LinkedList<JPanel>();

	private JTextField startDate = new JTextField();
	private JTextField endDate = new JTextField();

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int windowHeight = (int) Math.ceil(0.8 * screenSize.getHeight());
	private int windowWidth = (int) Math.ceil(1.35 * windowHeight);
	private int menuWidth = (int) (windowWidth / 6);
	private int hauptWidth = windowWidth - menuWidth;

	public MainWindow() {

		// MainWindow
		setTitle("Putzplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Hintergrund
		hintergrund.setLayout(new BoxLayout(hintergrund, BoxLayout.X_AXIS));
		hintergrund.add(menu);
		hintergrund.add(haupt);
		// Insets insets = hintergrund.getInsets();
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
		kalender.setBackground(new Color(22, 35, 54));
		kalender.setLayout(new BorderLayout());
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
		kalender.add(dates, BorderLayout.NORTH);
		
		toDoList.setLayout(new BoxLayout(toDoList, BoxLayout.Y_AXIS));
		toDoList.setBackground(Color.LIGHT_GRAY);

		kalender.add(toDoList, BorderLayout.CENTER);

		JPanel toDoBtns = new JPanel();
		toDoBtns.setLayout(new BoxLayout(toDoBtns, BoxLayout.Y_AXIS));
		generierenBtn.addActionListener(this);
		toDoBtns.add(generierenBtn);
		exportBtn.addActionListener(this);
		toDoBtns.add(exportBtn);
		kalender.add(toDoBtns, BorderLayout.EAST);
		haupt.add(kalender);
		kalender.setVisible(false);
		pack();
	}

	public ImageIcon resize(ImageIcon i, int width) {
		Image image = i.getImage();
		int newH = (int) Math.round(i.getIconHeight() * ((float) width / i.getIconWidth()));
		i = new ImageIcon(image.getScaledInstance(width, newH, java.awt.Image.SCALE_SMOOTH));
		return i;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JPanel getToDoList() {
		return toDoList;
	}

	public void actionPerformed(ActionEvent ae) {
		MainController.onClick(ae);
	}

	public JButton getBenutzerMB() {
		return benutzerMB;
	}

	public JButton getAufgabenMB() {
		return aufgabenMB;
	}

	public JButton getToDoMB() {
		return toDoMB;
	}

	public JButton[] getBenutzerButtons() {
		return benutzerButtons;
	}

	public JButton getGenerierenButton() {
		return generierenBtn;
	}

	public JButton getAufgabenplusButton() {
		return aufgabenplus;
	}

	public JButton getExportButton() {
		return exportBtn;
	}

	public JPanel getBenutzerPanel() {
		return benutzer;
	}

	public JPanel getAufgabenPanel() {
		return aufgaben;
	}

	public JPanel getKalenderPanel() {
		return kalender;
	}

	public JPanel getHauptPanel() {
		return haupt;
	}

	public LinkedList<JPanel> getAufgabenPanels() {
		return aufgabenPanels;
	}

	public int getHauptWidth() {
		return hauptWidth;
	}
	
	public String getStartDate() {
		return startDate.getText();
	}
	
	public String getEndDate() {
		return endDate.getText();
	}
}
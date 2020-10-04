import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

// TODO: ToDo läd nicht richtig, erst bei Aufruf in main
public class MainController {
	private static MainWindow instanz;

	public static MainWindow getInstanz() {
		if (instanz == null) {
			instanz = new MainWindow();
		}
		return instanz;
	}

	public static void main(String[] args) {
		getInstanz();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getInstanz().setVisible(true);

		new Benutzer("Andrea", "bilder\\personas\\Persona1.png");
		new Benutzer("Bernd", "bilder\\personas\\Persona2.png");
		new Benutzer("Claire", "bilder\\personas\\Persona3.png");

		new Aufgabe("Bad putzen", 5, 1);
		new Aufgabe("Blumen giessen", 1, 3);
		new Aufgabe("Boden wischen", 3, 1);
		new Aufgabe("Fenster putzen", 3, 1);
		new Aufgabe("Küche putzen", 4, 1);
		new Aufgabe("Müll rausbringen", 1, 7);
		new Aufgabe("Spülmaschine", 2, 3);
		new Aufgabe("Staubsaugen", 1, 4);
		new Aufgabe("Treppenhaus fegen", 2, 1);

		instanz.revalidate();
	}

	public static void updateAufgaben() {
		while (getInstanz().getTableModel().getRowCount() > 0) {
			getInstanz().getTableModel().removeRow(0);
		}
		for (Aufgabe a : Aufgabe.getAlleAufgaben()) {
			getInstanz().getTableModel().addRow(new Object[] { a.getName(), a.getSchwierigkeit(), a.getHaeufigkeit() });
		}
	}

	public static void updateBenutzer() {
		for (int i = 0; i < Benutzer.getAlleBenutzer().size(); i++) {
			instanz.getBenutzerButtons()[i].setIcon(instanz
					.resize(new ImageIcon(Benutzer.getAlleBenutzer().get(i).getBild()), instanz.getHauptWidth() / 3));
		}
	}

	public static void planExportieren() throws IOException {
		final PdfWriter pdfWriter = new PdfWriter("hello.pdf");
		final PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		try (final Document document = new Document(pdfDocument)) {
			document.add(new Paragraph("Hello World!"));
		}
	}

	public static void onClick(ActionEvent ae) {
		if (ae.getSource() == instanz.getBenutzerMB()) {
			instanz.getBenutzerPanel().setVisible(true);
			instanz.getAufgabenPanel().setVisible(false);
			instanz.getKalenderPanel().setVisible(false);
			instanz.getHauptPanel().setBackground(new Color(1, 83, 82));
		} else if (ae.getSource() == instanz.getAufgabenMB()) {
			instanz.getBenutzerPanel().setVisible(false);
			instanz.getAufgabenPanel().setVisible(true);
			instanz.getKalenderPanel().setVisible(false);
			instanz.getHauptPanel().setBackground(new Color(99, 0, 0));
		} else if (ae.getSource() == instanz.getToDoMB()) {
			instanz.getBenutzerPanel().setVisible(false);
			instanz.getAufgabenPanel().setVisible(false);
			instanz.getKalenderPanel().setVisible(true);
			instanz.getHauptPanel().setBackground(new Color(22, 35, 54));
		} else if (ae.getSource() == instanz.getBenutzerButtons()[0]
				|| ae.getSource() == instanz.getBenutzerButtons()[1]
				|| ae.getSource() == instanz.getBenutzerButtons()[2]) {
			NeuerBenutzerController.getInstanz().setVisible(true);
		} else if (ae.getSource() == instanz.getAufgabenplusButton()) {
			NeueAufgabeController.getInstanz().setVisible(true);
		} else if (ae.getSource() == instanz.getGenerierenButton()) {
			planGenerieren();
		} else if (ae.getSource() == instanz.getExportButton()) {
			try {
				planExportieren();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void planGenerieren() {
		// Datumseingaben prüfen
		LocalDate start = LocalDate.parse(instanz.getStartDate());
		LocalDate end = LocalDate.parse(instanz.getEndDate());
		if (start.isAfter(end)) {
			System.out.println("Startdatum muss vor Enddatum liegen.");
			return;
		}
		int zeitraum = start.until(end).getDays() + 1;
		int haeufigste = 0;
		for (Aufgabe a : Aufgabe.getAlleAufgaben()) {
			if (a.getHaeufigkeit() > haeufigste) {
				haeufigste = a.getHaeufigkeit();
			}
		}
		System.out.println("Zr: " + zeitraum + " H: " + haeufigste);
		if (haeufigste > zeitraum) {
			System.out.println("Zeitraum zu kruz");
			return;
		}

		LinkedList<Benutzer> benutzer = (LinkedList<Benutzer>) Benutzer.getAlleBenutzer().clone();
		Collections.shuffle(benutzer);
		LinkedList<Aufgabe> aufgaben = Aufgabe.getAlleAufgaben();
		LinkedList<Integer> schwierigk = new LinkedList<Integer>();
		Benutzer ben = null;

		for (Benutzer b : benutzer) {
			schwierigk.add(0);
		}

		// Aufgaben durchlaufen
		for (int aktuelleAufgabe = 0; aktuelleAufgabe < aufgaben.size(); aktuelleAufgabe++) {
			// Benutzer durchlaufen
			for (int aktuell = 0; aktuell < schwierigk.size(); aktuell++) {
				boolean istKleinste = true;
				// Benutzer für Aufgabe finden
				for (int vergleich = aktuell + 1; vergleich < schwierigk.size(); vergleich++) {
					if (schwierigk.get(vergleich) < schwierigk.get(aktuell)) {
						istKleinste = false;
						break;
					}
				}
				// Richtiger Benutzer
				if (istKleinste) {
					// Wert für Person erhöhen
					schwierigk
							.set(aktuell,
									(aufgaben.get(aktuelleAufgabe).getSchwierigkeit()
											* aufgaben.get(aktuelleAufgabe).getHaeufigkeit())
											+ schwierigk.get(aktuell));
					
					ben = benutzer.get(aktuell);
					System.out.println(ben + " " + aufgaben.get(aktuelleAufgabe));
					// ben.aufgabeGeben(aufgaben.get(aktuelleAufgabe));
					int h = aufgaben.get(aktuelleAufgabe).getHaeufigkeit();
					int pause = zeitraum / h;
					LocalDate d = start;
					// Daten generieren
					for (int i = 0; i < h; i++) {
						new AufgabeMitDatum(aufgaben.get(aktuelleAufgabe), d, ben);

						// Label für Aufgabe
						JLabel l = new JLabel();
						instanz.getAufgabenPanels().get(Benutzer.getAlleBenutzer().indexOf(ben)).add(l);

						// Typewriter-Animation
						ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
						executorService.scheduleAtFixedRate(new Runnable() {
							JLabel label;
							String str;

							@Override
							public void run() {
								char[] content = str.toCharArray();
								if (l.getText().length() < content.length) {
									l.setText(l.getText() + content[l.getText().length()]);
								}
							}

							public Runnable init(JLabel l, String s) {
								this.str = s;
								this.label = l;
								return (this);
							}
						}.init(l, d + ": " + aufgaben.get(aktuelleAufgabe).getName()), 0, 100, TimeUnit.MILLISECONDS);
						d = d.plusDays(pause);
					}

				}
			}

			// Verteilung auf Tage
			/*
			 * int h = aufgaben.get(aktuelleAufgabe).getHaeufigkeit(); int pause =
			 * zeitraum/h; LocalDate d = start; for(int i = 0; i < h; i++) { new
			 * AufgabeMitDatum(aufgaben.get(aktuelleAufgabe), d, ben); d =
			 * d.plusDays(pause); }
			 */
		}

		// Button disabled
		instanz.getGenerierenButton().setEnabled(false);
	}

	public static void buildToDoPanel(Benutzer b) {

		JPanel personPanel = new JPanel();
		personPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, instanz.getHauptWidth() / 4));
		personPanel.setBackground(Color.LIGHT_GRAY);
		personPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		JLabel bild = new JLabel();
		bild.setIcon(instanz.resize(new ImageIcon(b.getBild()), instanz.getHauptWidth() / 10));
		bild.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		personPanel.add(bild);

		JLabel name = new JLabel(b.toString());
		name.setFont(new Font("Arial", 1, 32));
		personPanel.add(name);

		c.gridy = 0;
		c.gridy = b.getID();
		instanz.getToDoList().add(personPanel);

		JPanel aufgaben = new JPanel();
		aufgaben.setBackground(Color.LIGHT_GRAY);
		aufgaben.setLayout(new BoxLayout(aufgaben, BoxLayout.Y_AXIS));
		instanz.getAufgabenPanels().add(aufgaben);
		instanz.getToDoList().add(aufgaben);
	}
}

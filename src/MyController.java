import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyController implements ActionListener{
	
	private PutzplanGUI window;
	
	public void startGui() {
		window = new PutzplanGUI(this);
		//window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Send")) {
			System.out.println("SEND");
			window.showMessage("Send");
		}
		else if (command.equals("Close")) {
			System.out.println("CLOSE");
			window.showMessage("close");
		}
	}

}
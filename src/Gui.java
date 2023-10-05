import javax.swing.*;
// for the listeners
// AWT = "Abstract Window Toolkit"
import java.awt.event.*;

public class Gui {
	private static final int  WIDTH = 800;
	private static final int LENGTH = 600;

	// menu stuff
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu resvMenu;
	private JMenu helpMenu;
	private JMenuItem filemExit;
	private JMenuItem helpmAbout;
	private JMenuItem resvmCreate;

	// member variables
	private JFrame frame;

	// reservation manager
	ReservationManager rm;

	// default contructor
	public Gui(ReservationManager resvmanager) {
		frame = new JFrame("Four Corners Hotel Reservation System");

		createMenu();
		createButtons(); // MUST CALL BEFORE createListeners
		createListeners(); // MUST CALL AFTER createMenu()

		// add createButtons stuff to frame
		//frame.add(enterNum);
		//frame.add(numTextField);
		//frame.add(oddEvenButton);
		//frame.add(weightMoonButton);
		//frame.add(feet2MeetersButton);
		//frame.add(isPrimeButton);
		//frame.add(output);

		frame.setSize(WIDTH, LENGTH);
		frame.setLayout(null); // telling Java not to arrange anything for us. we will use setBounds to arrange components ourselves
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // MUST HAVE!
		rm = resvmanager;
	}

	private void createMenu() {
		// Menu Bar
		menuBar = new JMenuBar();

		// Menus
		fileMenu = new JMenu("File");
		resvMenu = new JMenu("Reservation");
		helpMenu = new JMenu("Help");

		// Menu Items
		filemExit = new JMenuItem("Quit");
		resvmCreate = new JMenuItem("Create");
		helpmAbout = new JMenuItem("About");

		// Add Menu Items to Menus
		fileMenu.add(filemExit);
		resvMenu.add(resvmCreate);
		helpMenu.add(helpmAbout);

		// Add Menus to Menu Bar
		menuBar.add(fileMenu);
		menuBar.add(resvMenu);
		menuBar.add(helpMenu);

		frame.setJMenuBar(menuBar);
	}

	private void createListeners() {
		// for exit menu item
		filemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// your code goes here...
				frame.dispose(); // exit the app
			}
		});

		// for about menu item
		helpmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// your code goes here...
				JOptionPane.showMessageDialog(null, "This is my first java gui app!");
			}
		});

		resvmCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "test");
			}
		});
	}

	private void createButtons() {}
}

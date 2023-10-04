import javax.swing.*;
// for the listeners
// AWT = "Abstract Window Toolkit"
import java.awt.event.*;

public class Gui {
	private static final int  WIDTH = 800; 
	private static final int LENGTH = 600;
	
	private static int clicked = 0; // number of times user clicked on the enter button
	
	// menu stuff
	private JMenuBar menuBar;
	private JMenu appMenu;
	private JMenu helpMenu;
	private JMenuItem exit;
	private JMenuItem about;
	
	// createOddEvenChecker stuff
	private JLabel enterNum;
	private JLabel output;
	private JTextField numTextField;
	private JButton oddEvenButton;
	private JButton weightMoonButton;
	private JButton feet2MeetersButton;
	private JButton isPrimeButton;

	// member variables
	private JFrame frame;
	
	// default contructor
	public Gui() {
	
		createMenu();
		createOddEvenChecker(); // MUST CALL BEFORE createListeners
		createListeners(); // MUST CALL AFTER createMenu()
		
		frame = new JFrame("My First Jave App :) ");
		
		// add createOddEvenChecker stuff to frame
		frame.add(enterNum);
		frame.add(numTextField);
		frame.add(oddEvenButton);
		frame.add(weightMoonButton);
		frame.add(feet2MeetersButton);
		frame.add(isPrimeButton);
		frame.add(output);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(WIDTH, LENGTH);
		frame.setLayout(null); // telling Java not to arrange anything for us. we will use setBounds to arrange components ourselves
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // MUST HAVE!

	
	}

	private void createMenu() {
		// Menu Bar
		menuBar = new JMenuBar();
		
		// Menus
		appMenu = new JMenu("App");
		helpMenu = new JMenu("Help");
		
		// Menu Items
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");
		
		// Add Menu Items to Menus
		appMenu.add(exit);
		helpMenu.add(about);
		
		// Add Menus to Menu Bar
		menuBar.add(appMenu);
		menuBar.add(helpMenu);
	}

	public static boolean isPrime(int num) {    
		for(int i = 2; i <= num/i; ++i) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}

	private void createListeners() {
		// for exit menu item
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// your code goes here...
				frame.dispose(); // exit the app
			}
		});

		// for about menu item
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// your code goes here...
				JOptionPane.showMessageDialog(null, "This is my first java gui app!");
			}
		});
		
		oddEvenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// your code goes here...
				clicked++;
				String input = numTextField.getText();
				try {
					int num = Integer.parseInt(input);
					if(num % 2 == 0) {
						output.setText("EVEN (clicked: " + clicked + ")");
					}
					else {
						output.setText("ODD (clicked: " + clicked + ")");
					}
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Woah! Bad input, numbers only!");
				}
			}
		});

		weightMoonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = numTextField.getText();
				try {
					double num = Double.parseDouble(input);
					double weight = num*0.165;
					output.setText("Moon weight "+weight+" (lbs)");
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Woah! Bad input, numbers only!");
				}
			}
		});

		feet2MeetersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = numTextField.getText();
				try {
					double num = Double.parseDouble(input);
					double meters = num/3.281;
					output.setText("Distance: "+meters+" (meters)");
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Woah! Bad input, numbers only!");
				}
			}
		});

		isPrimeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = numTextField.getText();
				try {
					int num = Integer.parseInt(input);
					if (isPrime(num))
						output.setText("Number, "+num+" is a prime number.");
					else
						output.setText("Number, "+num+" is not a prime number.");
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Woah! Bad input, numbers only!");
				}
			}
		});
	}

	private void createOddEvenChecker() {
		enterNum = new JLabel("Enter number: ");
		//Note:
		//     x - the new x-coordinate of this component
		//     y - the new y-coordinate of this component
		// width - the new width of this component
		//height - the new height of this component
		
		//       setBounds(x,   y, width, height);
		enterNum.setBounds(10, 10,  150, 50);
		numTextField = new JTextField();
		numTextField.setBounds(130, 20, 200, 25);
		oddEvenButton = new JButton("Is Even?");
		oddEvenButton.setBounds(10, 50, 100, 25);

		weightMoonButton = new JButton("Earth weight to moon (lbs)");
		weightMoonButton.setBounds(110, 50, 250, 25);

		feet2MeetersButton = new JButton("Feet to meters");
		feet2MeetersButton.setBounds(360, 50, 150, 25);

		isPrimeButton = new JButton("Is prime?");
		isPrimeButton.setBounds(510, 50, 100, 25);

		output = new JLabel();
		output.setBounds(480, 20, 500, 25);
	}


    public void run() {
        System.out.println("RUn");

    }
}

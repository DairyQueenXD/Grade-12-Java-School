package Unit3_Assignment5;

// imports
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// Name: Dequan Kong
// Date: December 6, 2024
// Description: A program that finds the original order of cards after a specific order of operations.

public class Problem3 implements ActionListener {

	// Variables initialization
	JPanel panel = new JPanel(), cardsPanel = new JPanel(), topPanel = new JPanel(), leftPanel = new JPanel(), rightPanel = new JPanel();
	JFrame frame = new JFrame("Assignment 5 - Problem 3");
	JButton deckSizeButton = new JButton("Enter size"), exitButton = new JButton("Exit"), proofButton = new JButton("Proof");
	int deckSize = -1;
	final int screenWidth = 1500, screenHeight = 1000, cardWidth = 120, cardHeight = 120, cardsPanelHeight = 800, leftPanelWidth = screenWidth / 2;
	final int rightPanelWidth = screenWidth/2;
	JLabel[] cards = new JLabel[26]; // 1 extra for 1-index based
	Deque<Integer> hand = new LinkedList<Integer>(), table = new LinkedList<Integer>();
	boolean proofClick = false, put = true;

	/*
	 * Description: Constructor method that initializes all Panels and buttons. This is called at the start of the program
	 * Parameters: none
	 * Return: none (Constructor method has no return type)
	 */
	public Problem3() {
		// Set up panels

		topPanel.setLayout(new FlowLayout());
		topPanel.setMaximumSize(new Dimension(screenWidth+500, screenHeight-50 - cardsPanelHeight));
		// Button to input deck size
		deckSizeButton.setActionCommand("inputSize");
		deckSizeButton.addActionListener( this);
		// Button to exit program
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(this);
		// Button to start "Proof"
		proofButton.setActionCommand("proof");
		proofButton.addActionListener(this);
		// Add buttons to top panel
		topPanel.add(deckSizeButton); topPanel.add(exitButton); topPanel.add(proofButton);

		// Main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(leftPanel); panel.add(rightPanel);

		// Left panel
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setMaximumSize(new Dimension(leftPanelWidth, screenHeight));
		leftPanel.add(topPanel); leftPanel.add(cardsPanel);

		// Right panel
		rightPanel.setLayout(new GridLayout(0,5));
		rightPanel.setMaximumSize(new Dimension(leftPanelWidth, screenHeight));

		// Cards Panel
		cardsPanel.setLayout(new GridLayout(0,5)); // set to 0 to let GridLayout automatically adjust size
		// Load all pictures into an array for easy access
		for (int i = 1; i <= 25; ++i) {
			String name = "card" + i + ".png";
			ImageIcon imageIcon = new ImageIcon(name); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
			cards[i] = new JLabel(new ImageIcon(newimg));
		}

		// Frame
		frame.setPreferredSize(new Dimension (screenWidth, screenHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // can close program using "x" on top

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Problem3();
	}

	/*
	 * Description: Handles button click events for the "Enter size", "Exit", and "Proof" buttons.
	 * Parameters: ActionEvent e - triggered when a button is clicked
	 * Return: void 
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// Input the deck size from 1 to 25
		if (command.equalsIgnoreCase("inputSize")) {
			try {
				deckSize = Integer.parseInt(JOptionPane.showInputDialog("Enter deck size: ", "Input"));
				if (deckSize < 1 || deckSize > 25) throw new NumberFormatException();
				// Reset variables & panel
				proofClick = false; proofButton.setText("Proof"); put = true; 
				rightPanel.removeAll(); rightPanel.revalidate(); rightPanel.repaint();
				// Reset the Deque representing our "hand"
				hand = new LinkedList<>();
				hand.add(deckSize);
				// Reverse the process
				for (int i = deckSize-1; i >= 1; --i) {
					hand.addFirst(i);
					if (i != 1) hand.addFirst(hand.pollLast());
				}
				// Paint cardsPanel
				cardsPanel.removeAll();
				for (Integer i: hand) cardsPanel.add(cards[i]); // add cards in the correct order
				cardsPanel.revalidate(); cardsPanel.repaint();
			} catch (NumberFormatException exc) { // If input is invalid
				JOptionPane.showMessageDialog(frame, "Invalid input! Your input must be an integer from 1 to 25.", "Warning", JOptionPane.WARNING_MESSAGE);
			}

		}

		// The user has to continuously click the "Proof/Next" button to continue
		// I tried to use Thread.sleep but it didn't work :/
		if (command.equalsIgnoreCase("proof")) { 
			// if no deckSize has been inputed yet
			if (deckSize == -1) JOptionPane.showMessageDialog(frame, "Input a deck size first!", "Warning", JOptionPane.WARNING_MESSAGE);
			// if "proof" is already complete
			else if (hand.isEmpty()) JOptionPane.showMessageDialog(frame, "Proof already shown!", "Warning", JOptionPane.WARNING_MESSAGE);
			else {
				// Change button text
				if (!proofClick) {
					proofClick = true;
					proofButton.setText("Next");
				}
				// Put is just a boolean that alternates between true and false
				if (put) { // When true, add the top card to the rightPanel
					put = false;
					rightPanel.add(cards[hand.peekFirst()]);
					hand.removeFirst();
				} else { // When false, move the top card to the bottom; add nothing to rightPanel
					put = true;
					cardsPanel.remove(cards[hand.peekFirst()]);
					cardsPanel.add(cards[hand.peekFirst()]);
					hand.addLast(hand.removeFirst());
				}
				cardsPanel.revalidate(); cardsPanel.repaint();
				rightPanel.revalidate(); rightPanel.repaint();
			}
		}

		// Exit button
		if (command.equalsIgnoreCase("exit")) {
			System.exit(0);
		}

	}
}

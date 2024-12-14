package Unit3_Assignment6;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Assignment6 implements ActionListener {

	String fileName = "MOBY";

	BufferedReader inFile = null;
	JFrame frame = new JFrame("Assignment 6");
	JPanel panel = new JPanel(), leftPanel = new JPanel(), rightPanel = new JPanel();
	JButton addFile = new JButton("Add File");
	final int screenWidth = 1500, screenHeight = 1000, leftPanelWidth = 750, rightPanelWidth = screenWidth - leftPanelWidth;

	public Assignment6() {

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(leftPanel); panel.add(rightPanel);

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(addFile);
		leftPanel.setMaximumSize(new Dimension(leftPanelWidth, screenHeight));

		addFile.setActionCommand("addFile");
		addFile.addActionListener(this);

		frame.setPreferredSize(new Dimension (screenWidth, screenHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // can close program using "x" on top

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equalsIgnoreCase("addFile")) {
			FileDialog openDialog = new FileDialog (frame, "Open a new file", FileDialog.LOAD);
			openDialog.setVisible (true);
			fileName = openDialog.getFile ();
//			String dir = openDialog.getDirectory ();
//			System.out.println(fileName + " " + dir);
		}
	}

	public static void main(String[] args)  {
		new Assignment6();
	}

}

package Unit2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFrameTest{
	
	public JFrameTest () {
		JFrame frame = new JFrame("My Google Form");
		JPanel panel =  new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setBackground(Color.green);
		
		frame.addWindowListener(new WindowAdapter() {
	        // Program automatically exits when window is closed
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	System.exit(0);
	        }
	    });
		
		panel.setPreferredSize(new Dimension(500,800)); // set size of window
		panel.setBackground(Color.yellow); // default colors
		panel.setBackground(new Color(239,255,0)); // rgb color
		
		JButton b1 = new JButton("ABCABCABCABCABCABCABC");
		bottomPanel.add(b1); // add to panel!!
		
		JLabel msg = new JLabel("Whatever");
		bottomPanel.add(msg);
		
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		panel.setVisible(true);
		
	}

	public static void main(String[] args) {
		/*
		 * JButtons, JTextFields, JComboBox (dropdowns), JLabel (text,picture) -> JComponents
		 * We put JComponents on JPanel / JFrames -> Border = JFrame, put the content on the JPanel.
		 */ 
		new JFrameTest();
		
	}

	

}

package Unit2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFrameTestNoLayout implements ActionListener{

	JLabel msg;
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("laugh")) {
			msg.setText("ha ha ha");
		} 
		
		if (action.equals("boo")) {
			msg.setText("boo boob oo");
		}
		
	}

	public JFrameTestNoLayout () {
		JFrame frame = new JFrame("My Google Form");
		JPanel panel =  new JPanel();
		panel.setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		panel.setPreferredSize(screenSize); // set size of window

		frame.addWindowListener(new WindowAdapter() {
			// Program automatically exits when window is closed
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		panel.setBackground(Color.yellow); // default colors
		panel.setBackground(new Color(239,255,0)); // rgb color

		JButton b1 = new JButton("ABC");
		b1.setBounds(new Rectangle(10,10,200,100));
		panel.add(b1);
		b1.addActionListener(this);
		b1.setActionCommand("laugh");
		
		JButton b2 = new JButton("ABC");
		b2.setBounds(new Rectangle(200,10,200,100));
		panel.add(b2);
		b2.addActionListener(this);
		b2.setActionCommand("boo");

		msg = new JLabel ("abc");
		msg.setBounds(new Rectangle(400,400,100,100));
		panel.add(msg);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		/*
		 * JButtons, JTextFields, JComboBox (dropdowns), JLabel (text,picture) -> JComponents
		 * We put JComponents on JPanel / JFrames -> Border = JFrame, put the content on the JPanel.
		 */ 
		new JFrameTestNoLayout();

	}

}

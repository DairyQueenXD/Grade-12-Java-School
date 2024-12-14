package Unit3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class HeapSortDemo extends JPanel implements ActionListener {
	// Dimensions for node size and spacing
	private static final int NODE_RADIUS = 60;
	private static final int VERTICAL_SPACING = 150;
	private static final int HORIZONTAL_SPACING = 150;
	private static final int ARRAY_CELL_WIDTH = 80;
	private static final int ARRAY_CELL_HEIGHT = 80;

	private JButton next = new JButton("Next");
	private JLabel text = new JLabel(""), labelA = new JLabel("A"), labelB = new JLabel("B");
	private int actionNum = 0;
	private Color[] colors = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};

	int arrayStartX = 225; // Starting X position for the array
    int arrayY = 650; // Y position for the array
    int labelX = arrayStartX + ARRAY_CELL_WIDTH/2 - 10, labelY = arrayY - 25;
    int aX = labelX, bX = labelX + 4*ARRAY_CELL_WIDTH;
 

	// Node positions
	private final int rootX = 500, rootY = 200;
	private int[] xPos = {
			rootX,
			rootX - HORIZONTAL_SPACING,
			rootX + HORIZONTAL_SPACING,
			rootX - HORIZONTAL_SPACING - HORIZONTAL_SPACING / 2,
			rootX - HORIZONTAL_SPACING + HORIZONTAL_SPACING / 2,
			rootX + HORIZONTAL_SPACING - HORIZONTAL_SPACING / 2,
			rootX + HORIZONTAL_SPACING + HORIZONTAL_SPACING / 2
	};
	private int[] yPos = {
			rootY,
			rootY + VERTICAL_SPACING,
			rootY + VERTICAL_SPACING,
			rootY + 2 * VERTICAL_SPACING,
			rootY + 2 * VERTICAL_SPACING,
			rootY + 2 * VERTICAL_SPACING,
			rootY + 2 * VERTICAL_SPACING
	};

	private int[] nodes = {50,10,30,40,50,60,80};
	public HeapSortDemo() {
		setLayout(null); // Use null layout for manual positioning
		setBackground(Color.black);
		setupUI();
	}

	private void setupUI() {
		// Set up the "Next" button
		next.setBounds(50, 50, 100, 30);
		next.addActionListener(this);
		add(next);

		// Set up the label
		text.setBounds(200, 50, 1500, 60);
		text.setForeground(Color.white);
		text.setFont(new Font("Arial", Font.BOLD, 30));
		add(text);
		
//		labelA = new JLabel("A");
	    labelA.setForeground(new Color(255,51,51)); // Color for better visibility
	    labelA.setFont(new Font("Arial", Font.BOLD, 24));
	    labelA.setBounds(aX, labelY, 20, 20); // Adjust X and Y positions as needed
	    add(labelA);

	    // Create and configure JLabel for "B"
//	    labelB = new JLabel("B");
	    labelB.setForeground(new Color(102,255,255)); // Color for better visibility
	    labelB.setFont(new Font("Arial", Font.BOLD, 24));
	    labelB.setBounds(bX, labelY, 20, 20); // Adjust X and Y positions as needed
	    add(labelB);
	    
	}

	public void swapText(int swap1, int swap2) {
		text.setText("Heapify: Swap " + swap1 + " and " + swap2);
	}
	
	public void white(int ind) {
		colors[ind] = Color.white;
	}
	
	public void yellow(int ind) {
		colors[ind] = Color.yellow;
	}
	
	public void gray(int ind) {
		colors[ind] = Color.gray;
	}
	
	public void heapify(int prev1, int prev2, int new1, int new2) {
		swapText(nodes[new1], nodes[new2]);
		white(prev1); white(prev2);
		yellow(new1); yellow(new2);
	}
	
	public void delete(int prev1, int prev2, int small, int big) {
		text.setText("Heapify Complete. Deleting node " + nodes[big] + " by swapping " + nodes[small] + " with " + nodes[big]);
		white(prev1); white(prev2);
		yellow(small); yellow(big);
		
	}
	
	public void updateHeap(int prev1, int prev2, int new1, int new2) {
		white(prev1); white(prev2); yellow(new1); yellow(new2); swapText(nodes[new1], nodes[new2]);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Next")) {
			switch(actionNum) {
			case 0:
				colors[2] = Color.yellow; colors[6] = Color.yellow; text.setText("Heapify: Swap 30 and 80");
				break;
			case 1:
				nodes[2] = 80; nodes[6] = 30;
				break;
			case 2: 
				white(2); white(6); 
				swapText(10,50); yellow(1); yellow(4);
				break;
			case 3:
				nodes[1] = 50; nodes[4] = 10;
				bX = labelX + ARRAY_CELL_WIDTH;
				break;
			case 4:
				updateHeap(1,4,0,2);
				break;
			case 5:
				aX = labelX + 2*ARRAY_CELL_WIDTH;
				nodes[0] = 80; nodes[2] = 50;
				break;
			case 6:
				heapify(0,2,2,5);
				break;
			case 7:
				nodes[5] = 50; nodes[2] = 60;
				aX = labelX + 5*ARRAY_CELL_WIDTH;
				break;
			case 8:
				delete(5,2,6,0);
				break;
			case 9:
				nodes[6] = 80; nodes[0] = 30; gray(6);
				break;
			case 10:
				heapify(0,0,0,2);
				break;
			case 11:
				nodes[2] = 30; nodes[0] = 60;
				break;
			case 12:
				heapify(0,2,2,5);
				break;
			case 13:
				nodes[5] = 30; nodes[2] = 50;
				aX = labelX + 2*ARRAY_CELL_WIDTH;
				break;
			case 14:
				delete(5,2,5,0);
				break;
			case 15:
				nodes[5] = 60; nodes[0] = 30; gray(5);
				break;
			case 16:
				heapify(0,0,0,2);
				break;
			case 17:
				nodes[2] = 30; nodes[0] = 50;
				aX = labelX;
				break;
			case 18:
				delete(0,2,4,0);
				break;
			case 19:
				nodes[4] = 50; nodes[0] = 10; gray(4);
				aX = labelX + 4*ARRAY_CELL_WIDTH;
				break;
			case 20:
				heapify(0,0,0,1);
				break;
			case 21:
				nodes[1] = 10; nodes[0] = 50;
				bX = labelX;
				break;
			case 22:
				heapify(0,1,1,3);
				break;
			case 23:
				nodes[3] = 10; nodes[1] = 40;
				break;
			case 24:
				delete(1,3,3,0);
				break;
			case 25:
				nodes[0] = 10; nodes[3] = 50; gray(3);
				bX = labelX + 3*ARRAY_CELL_WIDTH;
				break;
			case 26:
				heapify(0,0,0,1);
				break;
			case 27:
				nodes[1] = 10; nodes[0] = 40;
				break;
			case 28:
				delete(0,1,2,0);
				break;
			case 29:
				nodes[2] = 40; nodes[0] = 30; gray(2);
				break;
			case 30:
				delete(0,0,1,0);
				break;
			case 31:
				nodes[1] = 30; nodes[0] = 10; gray(1);
				break;
			case 32:
				gray(0); text.setText("Last node left.");
				break;
			
			}
			repaint();
			actionNum++;

		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Set anti-aliasing for smoother circles and lines
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw nodes
		g2d.setFont(new Font("Arial", Font.BOLD, 24));
		for (int i = 0; i < 7; ++i) {
			g2d.setColor(colors[i]);
			g2d.fillOval(xPos[i] - NODE_RADIUS, yPos[i] - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

			// Draw node numbers inside the circles
			g2d.setColor(Color.BLACK);
			String value = String.valueOf(nodes[i]);
			FontMetrics fm = g2d.getFontMetrics();
			int textWidth = fm.stringWidth(value);
			int textHeight = fm.getAscent();
			g2d.drawString(value, xPos[i] - textWidth / 2, yPos[i] + textHeight / 4);
		}

		// Draw edges between nodes
		g2d.setColor(Color.ORANGE);
		drawLine(g2d, 0, 1); // Root to left child
		drawLine(g2d, 0, 2); // Root to right child
		drawLine(g2d, 1, 3); // Left child to left grandchild
		drawLine(g2d, 1, 4); // Left child to right grandchild
		drawLine(g2d, 2, 5); // Right child to left grandchild
		drawLine(g2d, 2, 6); // Right child to right grandchild
		
		labelA.setBounds(aX, labelY, 20, 20);
		labelB.setBounds(bX, labelY, 20, 20);
//		
	    for (int i = 0; i < 7; ++i) {
	    	if (colors[i] == Color.gray) g2d.setColor(Color.green);
	    	else g2d.setColor(colors[i]);
	        g2d.fillRect(arrayStartX + i * ARRAY_CELL_WIDTH, arrayY, ARRAY_CELL_WIDTH, ARRAY_CELL_HEIGHT);
	        g2d.setColor(Color.BLACK);
	        g2d.drawRect(arrayStartX + i * ARRAY_CELL_WIDTH, arrayY, ARRAY_CELL_WIDTH, ARRAY_CELL_HEIGHT);

	        // Draw array value inside the square
	        String value = String.valueOf(nodes[i]);
	        FontMetrics fm = g2d.getFontMetrics();
	        int textWidth = fm.stringWidth(value);
	        int textHeight = fm.getAscent();
	        g2d.drawString(value, arrayStartX + i * ARRAY_CELL_WIDTH + (ARRAY_CELL_WIDTH - textWidth) / 2,
	                       arrayY + (ARRAY_CELL_HEIGHT + textHeight) / 2);
	    }
	}

	private void drawLine(Graphics2D g2d, int parentIndex, int childIndex) {
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(xPos[parentIndex], yPos[parentIndex], xPos[childIndex], yPos[childIndex]);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Heap Sort Demo");
		HeapSortDemo panel = new HeapSortDemo();

		frame.add(panel);
		frame.setSize(1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

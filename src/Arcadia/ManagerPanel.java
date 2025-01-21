package Arcadia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerPanel extends JPanel {
	private JFrame parentFrame;
	private JPanel gridPanel;
	private JButton[][] gridButtons;
	private int rows = 10;
	private int cols = 10;
	private Machine selectedMachine;

	public ManagerPanel(JFrame frame) {
		this.parentFrame = frame;

		setLayout(new BorderLayout());

		// Initialize grid
		gridPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
		gridButtons = new JButton[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				JButton cell = new JButton();
				cell.setBackground(Color.LIGHT_GRAY);
				cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				cell.setFocusPainted(false);

				int finalI = i;
				int finalJ = j;

				cell.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						if (selectedMachine != null && isPlacementValid(finalI, finalJ)) {
							highlightPlacement(finalI, finalJ, true);
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						if (selectedMachine != null) {
							highlightPlacement(finalI, finalJ, false);
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
						if (selectedMachine != null) {
							System.out.println("Attempting to place Machine: " + selectedMachine.getName());
							if (isPlacementValid(finalI, finalJ)) {
								placeMachine(finalI, finalJ);
							} else {
								JOptionPane.showMessageDialog(gridPanel, "Invalid placement. Try again.", "Placement Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							System.out.println("No machine selected at placement time.");
						}
					}
				});

				gridButtons[i][j] = cell;
				gridPanel.add(cell);
			}
		}
		add(gridPanel, BorderLayout.CENTER);

		// Add bottom buttons
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton inventoryButton = new JButton("Inventory");
		JButton mainMenuButton = new JButton("Main Menu");

		inventoryButton.addActionListener(e -> showInventoryScreen());
		mainMenuButton.addActionListener(e -> goToMainMenu());

		buttonPanel.add(inventoryButton);
		buttonPanel.add(mainMenuButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	//    private boolean isPlacementValid(int x, int y) {
	//        if (selectedMachine == null) return false;
	//
	//        // Validate if the machine fits within grid boundaries
	//        if (x + selectedMachine.getHeight() > rows || y + selectedMachine.getWidth() > cols) {
	//            return false;
	//        }
	//
	//        // Check for overlap with existing machines
	//        for (int i = x; i < x + selectedMachine.getHeight(); i++) {
	//            for (int j = y; j < y + selectedMachine.getWidth(); j++) {
	//                if (!gridButtons[i][j].getBackground().equals(Color.LIGHT_GRAY)) {
	//                    return false;
	//                }
	//            }
	//        }
	//        return true;
	//    }

	private boolean isPlacementValid(int x, int y) {
		if (selectedMachine == null) {
			System.out.println("No machine selected in isPlacementValid.");
			return false;
		}

		System.out.println("Checking placement for Machine: " + selectedMachine.getName());
		if (x + selectedMachine.getHeight() > rows || y + selectedMachine.getWidth() > cols) {
			System.out.println("Placement exceeds grid boundaries.");
			return false;
		}

		for (int i = x; i < x + selectedMachine.getHeight(); i++) {
			for (int j = y; j < y + selectedMachine.getWidth(); j++) {
				if (!gridButtons[i][j].getBackground().equals(Color.LIGHT_GRAY)) {
					System.out.println("Overlap detected at cell (" + i + ", " + j + ").");
					return false;
				}
			}
		}

		System.out.println("Placement is valid.");
		return true;
	}



	private void placeMachine(int x, int y) {
		for (int i = x; i < x + selectedMachine.getHeight(); i++) {
			for (int j = y; j < y + selectedMachine.getWidth(); j++) {
				gridButtons[i][j].setBackground(Color.CYAN);
			}
		}

		selectedMachine.setPosition(x, y);
		selectedMachine = null;
	}

	private void highlightPlacement(int x, int y, boolean highlight) {
		Color color = highlight ? Color.GREEN : Color.LIGHT_GRAY;

		for (int i = x; i < x + selectedMachine.getHeight(); i++) {
			for (int j = y; j < y + selectedMachine.getWidth(); j++) {
				if (i < rows && j < cols) {
					gridButtons[i][j].setBackground(color);
				}
			}
		}
	}

	private void goToMainMenu() {
		parentFrame.getContentPane().removeAll();
		parentFrame.getContentPane().add(new Main().createMainPanel());
		parentFrame.revalidate();
		parentFrame.repaint();
	}

	private void showInventoryScreen() {
	    parentFrame.getContentPane().removeAll();
	    InventoryPanel inventoryPanel = new InventoryPanel(parentFrame, this); // Pass current ManagerPanel

	    parentFrame.getContentPane().add(inventoryPanel);
	    parentFrame.revalidate();
	    parentFrame.repaint();
	}

	public void setSelectedMachine(Machine machine) {
	    this.selectedMachine = machine;
	    System.out.println("Machine selected for placement: " + machine.getName());
	}

//	private void showInventoryScreen() {
//		parentFrame.getContentPane().removeAll();
//		InventoryPanel inventoryPanel = new InventoryPanel(parentFrame);
//
//		// Ensure selectedMachine in ManagerPanel is updated
//		inventoryPanel.setMachineSelectionCallback(machine -> {
//			selectedMachine = machine; // Assign selected machine to ManagerPanel's selectedMachine
//			System.out.println("Machine selected for placement: " + machine.getName());
//			JOptionPane.showMessageDialog(gridPanel, "Selected Machine: " + machine.getName());
//		});
//
//		parentFrame.getContentPane().add(inventoryPanel);
//		parentFrame.revalidate();
//		parentFrame.repaint();
//	}

}
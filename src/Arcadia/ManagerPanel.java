package Arcadia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerPanel extends JPanel {

    private JFrame parentFrame;
    private JPanel gridPanel;
    private JButton[][] gridButtons;
    private int rows = 10, cols = 10;
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

                // Add drag-and-drop mechanics
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (selectedMachine != null && isPlacementValid(finalI, finalJ)) {
                            placeMachine(finalI, finalJ);
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

    private boolean isPlacementValid(int x, int y) {
        if (selectedMachine == null) return false;

        // Check if the machine fits in the grid and doesn't overlap
        for (int i = x; i < x + selectedMachine.getHeight(); i++) {
            for (int j = y; j < y + selectedMachine.getWidth(); j++) {
                if (i >= rows || j >= cols || !gridButtons[i][j].getBackground().equals(Color.LIGHT_GRAY)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeMachine(int x, int y) {
        // Mark the cells as occupied
        for (int i = x; i < x + selectedMachine.getHeight(); i++) {
            for (int j = y; j < y + selectedMachine.getWidth(); j++) {
                gridButtons[i][j].setBackground(Color.CYAN);
            }
        }

        selectedMachine.setPosition(x, y);
        selectedMachine = null; // Reset after placing
    }

    private void goToMainMenu() {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(new Main().createMainPanel());
        parentFrame.revalidate();
        parentFrame.repaint();
    }
    
    private void showInventoryScreen() {
        parentFrame.getContentPane().removeAll();
        
        InventoryPanel inventoryPanel = new InventoryPanel(parentFrame);
        parentFrame.getContentPane().add(inventoryPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }


}

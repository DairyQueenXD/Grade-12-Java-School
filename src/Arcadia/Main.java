package Arcadia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Dequan Kong
 * @date December 27, 2024
 * 
 * A program that simulates managing an arcade shop by keeping track of an inventory of arcade machines
 * and games available for play. Allows the user to manage bookings, layout, and offers loyalty discounts.
 */

public class Main {

    private JFrame frame;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        frame = new JFrame("Arcade Shop Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.getContentPane().add(createMainPanel());
        frame.setVisible(true);
    }

    public JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Title
        JLabel title = new JLabel("Arcade Shop Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        mainPanel.add(title, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));

        JButton managerButton = new JButton("Manage Arcade");
        JButton customerButton = new JButton("Customer Bookings");
        JButton exitButton = new JButton("Exit");

        // Add button actions
        managerButton.addActionListener(evt -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new ManagerPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        customerButton.addActionListener(evt -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new CustomerPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        exitButton.addActionListener(evt -> System.exit(0));

        // Add buttons to panel
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(managerButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(customerButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        return mainPanel;
    }
}

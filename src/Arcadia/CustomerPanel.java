package Arcadia;

import javax.swing.*;
import java.awt.*;

/**
 * Customer class to handle customer bookings and loyalty points.
 */
public class CustomerPanel extends JPanel {

    private JFrame parentFrame;

    public CustomerPanel(JFrame frame) {
        this.parentFrame = frame;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Customer Bookings", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JTextArea bookingsDisplay = new JTextArea();
        bookingsDisplay.setEditable(false);
        bookingsDisplay.setText("No bookings yet.");
        add(new JScrollPane(bookingsDisplay), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));

        JButton addBookingButton = new JButton("Add Booking");
        JButton backButton = new JButton("Back to Main Menu");

        addBookingButton.addActionListener(e -> {
            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            String bookingDetails = JOptionPane.showInputDialog("Enter Booking Details:");
            if (customerName != null && bookingDetails != null) {
                bookingsDisplay.append("Customer: " + customerName + "\nBooking: " + bookingDetails + "\n\n");
            }
        });

        backButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            
            // Rebuild the main menu
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(3, 1));

            JLabel title2 = new JLabel("Arcade Shop Management System", SwingConstants.CENTER);
            title2.setFont(new Font("Arial", Font.BOLD, 24));
            mainPanel.add(title2);

            JButton managerButton = new JButton("Manage Arcade");
            JButton customerButton = new JButton("Customer Bookings");
            JButton exitButton = new JButton("Exit");

            managerButton.addActionListener(evt -> {
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new ManagerPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            });

            customerButton.addActionListener(evt -> {
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new CustomerPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            });

            exitButton.addActionListener(evt -> System.exit(0));

            mainPanel.add(managerButton);
            mainPanel.add(customerButton);
            mainPanel.add(exitButton);

            parentFrame.getContentPane().add(mainPanel);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        controlPanel.add(addBookingButton);
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.SOUTH);
    }
}

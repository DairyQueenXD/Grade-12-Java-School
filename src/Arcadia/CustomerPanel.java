package Arcadia;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Customer class to handle customer bookings and loyalty points.
 */
public class CustomerPanel extends JPanel {

    private JFrame parentFrame;
    private JTextArea bookingsDisplay;
    private Map<String, Integer> loyaltyPoints;

    public CustomerPanel(JFrame frame) {
        this.parentFrame = frame;
        this.loyaltyPoints = new HashMap<>();

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Customer Bookings", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        bookingsDisplay = new JTextArea();
        bookingsDisplay.setEditable(false);
        bookingsDisplay.setText("No bookings yet.");
        add(new JScrollPane(bookingsDisplay), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));

        JButton addBookingButton = new JButton("Add Booking");
        JButton applyDiscountButton = new JButton("Apply Discount");
        JButton backButton = new JButton("Back to Main Menu");

        addBookingButton.addActionListener(e -> {
            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            String bookingDetails = JOptionPane.showInputDialog("Enter Booking Details:");
            if (customerName != null && bookingDetails != null) {
                bookingsDisplay.append("Customer: " + customerName + "\nBooking: " + bookingDetails + "\n\n");
                updateLoyaltyPoints(customerName, 10); // Example: Add 10 points per booking
            }
        });

        applyDiscountButton.addActionListener(e -> {
            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            if (customerName != null && loyaltyPoints.containsKey(customerName)) {
                int points = loyaltyPoints.get(customerName);
                if (points >= 50) { // Example: 50 points for discount
                    JOptionPane.showMessageDialog(this, "Discount applied for " + customerName);
                    loyaltyPoints.put(customerName, points - 50);
                } else {
                    JOptionPane.showMessageDialog(this, customerName + " does not have enough points.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found.");
            }
        });

        backButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new Main().createMainPanel());
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        controlPanel.add(addBookingButton);
        controlPanel.add(applyDiscountButton);
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void updateLoyaltyPoints(String customerName, int points) {
        loyaltyPoints.put(customerName, loyaltyPoints.getOrDefault(customerName, 0) + points);
        JOptionPane.showMessageDialog(this, customerName + " now has " + loyaltyPoints.get(customerName) + " points.");
    }
}
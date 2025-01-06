package Arcadia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryPanel extends JPanel {

    private JFrame parentFrame;
    private JPanel itemsGrid;
    private ArrayList<Machine> inventoryMachines; // Change from Item to Machine
    private int currentPage = 0;
    private int itemsPerPage = 6;

    public InventoryPanel(JFrame parent) {
        this.parentFrame = parent;
        this.inventoryMachines = new ArrayList<>();
        setLayout(new BorderLayout());

        // Add dummy machines for demonstration
        for (int i = 0; i < 20; i++) {
            inventoryMachines.add(new Machine("Machine " + (i + 1), null, 2, 3));
        }

        // Dropdown for filters
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> filterDropdown = new JComboBox<>(new String[]{"All", "Owned", "Not Owned"});
        filterDropdown.addActionListener(e -> filterMachines((String) filterDropdown.getSelectedItem()));
        topPanel.add(new JLabel("Show:"));
        topPanel.add(filterDropdown);

        // Add a "Back" button
        JButton backButton = new JButton("Back to Grid");
        backButton.addActionListener(e -> {
            parent.getContentPane().removeAll();
            parent.getContentPane().add(new ManagerPanel(parent));
            parent.revalidate();
            parent.repaint();
        });
        topPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);

        // Grid for machines
        itemsGrid = new JPanel(new GridLayout(2, 3, 10, 10));
        refreshGrid();
        add(itemsGrid, BorderLayout.CENTER);

        // Bottom navigation
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");
        JLabel pageLabel = new JLabel("Page " + (currentPage + 1));
        
        prevButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                pageLabel.setText("Page " + (currentPage + 1));
                refreshGrid();
            }
        });

        nextButton.addActionListener(e -> {
            if ((currentPage + 1) * itemsPerPage < inventoryMachines.size()) {
                currentPage++;
                pageLabel.setText("Page " + (currentPage + 1));
                refreshGrid();
            }
        });

        bottomPanel.add(prevButton);
        bottomPanel.add(pageLabel);
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }


    public ArrayList<Machine> getMachines() {
        return inventoryMachines; // Return the list of machines
    }
    
    private void refreshGrid() {
        itemsGrid.removeAll();
        int start = currentPage * itemsPerPage;
        int end = Math.min(start + itemsPerPage, inventoryMachines.size());

        for (int i = start; i < end; i++) {
            itemsGrid.add(createMachinePanel(inventoryMachines.get(i)));
        }

        itemsGrid.revalidate();
        itemsGrid.repaint();
    }

    private JPanel createMachinePanel(Machine machine) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(machine.getName(), SwingConstants.CENTER);
        JLabel dimensionLabel = new JLabel("Size: " + machine.getWidth() + "x" + machine.getHeight(), SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Selected " + machine.getName());
        });

        buttonPanel.add(selectButton);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(dimensionLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void filterMachines(String filter) {
        // Placeholder for filtering logic
        JOptionPane.showMessageDialog(this, "Filter applied: " + filter);
    }
    
    
}

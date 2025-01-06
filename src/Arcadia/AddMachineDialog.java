package Arcadia;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;

public class AddMachineDialog extends JDialog {

    public AddMachineDialog(JFrame parent) {
        super(parent, "Add Machine", true);
        setSize(400, 300);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Machine", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JTextField machineNameField = new JTextField(20);
        JButton uploadButton = new JButton("Upload PNG");

        uploadButton.addActionListener(e -> {
            // Use the default file system view for unrestricted browsing
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            // Allow the user to navigate the entire file system
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("Select Machine Image");

            // Optional: Set a file filter for PNG images
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "png"));

            // Open the file chooser
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                java.io.File selectedFile = fileChooser.getSelectedFile();

                // Validate the file
                if (selectedFile != null && selectedFile.exists()) {
                    String fileName = selectedFile.getName().toLowerCase();
                    if (fileName.endsWith(".png")) {
                        JOptionPane.showMessageDialog(this, "Selected file: " + selectedFile.getAbsolutePath());
                        // Save or process the file as needed
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid file type. Please select a PNG image.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid file selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




        JButton selectDimensionButton = new JButton("Select Dimensions");
        selectDimensionButton.addActionListener(e -> showDimensionSelector());

        inputPanel.add(new JLabel("Machine Name:"));
        inputPanel.add(machineNameField);
        inputPanel.add(uploadButton);
        inputPanel.add(selectDimensionButton);

        add(inputPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> dispose());
        add(confirmButton, BorderLayout.SOUTH);
        
        selectDimensionButton.addActionListener(e -> {
        	DimensionSelectorDialog dimensionDialog = new DimensionSelectorDialog(parent);
        	dimensionDialog.setVisible(true);

            if (dimensionDialog.isSelectionConfirmed()) {
                int width = dimensionDialog.getSelectedWidth();
                int height = dimensionDialog.getSelectedHeight();
                JOptionPane.showMessageDialog(this, "Selected dimensions: " + width + "x" + height);
                // Save the dimensions for the new machine
            }
        });

    }

    private void showDimensionSelector() {
        // TODO: Implement a grid dimension selector
    }
}

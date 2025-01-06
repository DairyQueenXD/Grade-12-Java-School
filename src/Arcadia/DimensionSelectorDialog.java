package Arcadia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DimensionSelectorDialog extends JDialog {

	private int rows = 5; // Number of rows in the grid
	private int cols = 5; // Number of columns in the grid
	private boolean[][] selectedCells; // Tracks selected cells
	private JButton[][] gridButtons; // Grid buttons for visual feedback
	private int startX = -1, startY = -1, endX = -1, endY = -1; // Selection coordinates

	private int selectedWidth;
	private int selectedHeight;
	private boolean selectionConfirmed = false;

	public DimensionSelectorDialog(JFrame parent) {
		super(parent, "Select Dimensions", true);
		setSize(400, 400);
		setLayout(new BorderLayout());

		// Create grid panel
		JPanel gridPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
		gridButtons = new JButton[rows][cols];
		selectedCells = new boolean[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				JButton cell = new JButton();
				cell.setBackground(Color.LIGHT_GRAY);
				cell.setFocusPainted(false);

				int finalI = i;
				int finalJ = j;

				// Add mouse listener to track selection
				cell.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						startX = finalI;
						startY = finalJ;
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						endX = finalI;
						endY = finalJ;
						updateSelection();
					}
				});

				gridButtons[i][j] = cell;
				gridPanel.add(cell);
			}
		}

		add(gridPanel, BorderLayout.CENTER);

		// Add confirm button
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(e -> {
			if (validateSelection()) {
				selectedWidth = Math.abs(endX - startX) + 1;
				selectedHeight = Math.abs(endY - startY) + 1;
				selectionConfirmed = true;
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Invalid selection. Please select a valid rectangle.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		add(confirmButton, BorderLayout.SOUTH);
	}

	/**
	 * Returns true if the user confirmed their selection.
	 */
	public boolean isSelectionConfirmed() {
	    return selectionConfirmed;
	}

	/**
	 * Returns the width of the selected rectangle.
	 */
	public int getSelectedWidth() {
	    return selectedWidth;
	}
	
	/**
	 * Returns the height of the selected rectangle.
	 */
	public int getSelectedHeight() {
	    return selectedHeight;
	}

	/**
	 * Validates the selection to ensure it's a valid rectangle.
	 */
	private boolean validateSelection() {
		return startX != -1 && startY != -1 && endX != -1 && endY != -1;
	}

	/**
	 * Updates the visual selection on the grid.
	 */
	private void updateSelection() {
		// Reset grid colors
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				gridButtons[i][j].setBackground(Color.LIGHT_GRAY);
			}
		}
	}
}


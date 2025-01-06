package Unit3_Assignment6;

import java.util.List;
import java.util.Map;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * @author Dequan Kong
 * @date December 18, 2024
 * 
 * A program that allows the user to add files and returns the top 20 words that appear the most
 */

public class Assignment6 implements ActionListener {
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JTextArea textArea;
	private JTextArea outputArea;
	private JComboBox<String> fileComboBox;
	private Map<String, String> openedFiles = new HashMap<>(); // Stores file names and content
	private long runTime;

	private static String[] test = {"should've", "shouldn't", "could've", "there'll", "would've", "couldn't", "there're", "these're", "those're", "wouldn't", 
			"that'll", "they'll", "they've", "doesn't", "haven't", "there's", "they're", "weren't", "she'll", "you'll", 
			"aren't", "didn't", "hadn't", "hasn't", "wasn't", "you're", "you've", "won't", "he'll", "it'll", 
			"we'll", "we've", "can't", "don't", "isn't", "she's", "we're", "I'll", "I've", "he's", 
			"it's", "I'm"};
	private static ArrayList<String> contractions = new ArrayList<String>();

	/**
	 * Main method - calls the constructor
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		for (String word: test) contractions.add(word.toLowerCase());
		new Assignment6();
	}

	/**
	 * Gets command from the add file button and the select file drop down menu
	 * @param ActionEvent e - represents the actionEvent
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equalsIgnoreCase("add File")) {
			addFile();
		}

		if (command.equalsIgnoreCase("Select File")) {
			displayFileContent((String) fileComboBox.getSelectedItem());
		}
	}

	/**
	 * Constructor - calls Setup() and loads the first two files (ALICE.TXT and MOBY.TXT)
	 * @param none
	 * @return none
	 */
	public Assignment6() {
		setUp();
		loadFile("ALICE.TXT"); loadFile("MOBY.TXT");
	}

	/**
	 * set up the main frame, panels, buttons, and drop down menus
	 * @param none
	 * @return void
	 */
	private void setUp() {
		// Main frame
		frame = new JFrame("Word Frequency Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main panel 
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(1500,1000));

		// Top panel: ComboBox and Add File button
		JPanel topPanel = new JPanel(new BorderLayout());
		fileComboBox = new JComboBox<>(openedFiles.keySet().toArray(new String[0]));
		fileComboBox.addActionListener(this);
		fileComboBox.setActionCommand("Select File");

		// Add File Button
		JButton addButton = new JButton("Add File");
		addButton.addActionListener(this);
		addButton.setActionCommand("Add file");

		// Drop Down Menu
		topPanel.add(fileComboBox, BorderLayout.CENTER);
		topPanel.add(addButton, BorderLayout.EAST);

		// Center panel: File content text area
		JPanel centerPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(600, 700)); // Control the size of the center panel
		centerPanel.add(scrollPane, BorderLayout.CENTER);

		// Right panel: Output text area
		JPanel rightPanel = new JPanel(new BorderLayout());
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Ensure consistent alignment
		outputArea.setLineWrap(false); // Avoid wrapping the text unnecessarily
		JScrollPane outputScrollPane = new JScrollPane(outputArea);
		outputScrollPane.setPreferredSize(new Dimension(400, 700)); // Fix right panel size
		rightPanel.add(outputScrollPane, BorderLayout.CENTER);

		// Add all sub-panels to the main panel
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(rightPanel, BorderLayout.EAST);

		// Add main panel to the frame
		frame.add(panel);
		frame.pack(); 
		frame.setVisible(true);
	}

	/**
	 * Reads the file, then add it to the HashMap openedFiles, and add it to the drop down menu
	 * @param String fileName - represents the file to be read
	 * @retun void
	 */
	private void loadFile(String fileName) {

		try (BufferedReader inFile = new BufferedReader(new FileReader(fileName))) {
			StringBuilder content = new StringBuilder();
			String line;

			// Read the file line by line
			while ((line = inFile.readLine()) != null) {
				content.append(line).append("\n");
			}

			// Add content to the openedFiles map and comboBox
			openedFiles.put(fileName, content.toString());
			fileComboBox.addItem(fileName);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error loading file: " + fileName + "\n" + e.getMessage());
		}
	}

	/**
	 * Called when the add file method is clicked - allows user to choose a file from their computer
	 * Also refreshes the drop down menu
	 * @param none
	 * @return void
	 */
	private void addFile() {
		// Ask user to select file
		FileDialog openDialog = new FileDialog(frame, "Open a New File", FileDialog.LOAD);
		openDialog.setVisible(true);

		String directory = openDialog.getDirectory(); // Get the selected directory
		String fileName = openDialog.getFile();       // Get the selected file name

		if (fileName != null) { // If a file is selected
			File file = new File(directory, fileName);
			openedFiles.put(file.getName(), readFile(file));
			fileComboBox.addItem(file.getName());    // Add file to the dropdown
			fileComboBox.revalidate();                // Refresh JComboBox
			fileComboBox.repaint(); 
			JOptionPane.showMessageDialog(frame, "File added successfully: " + fileName);
		} else {
			JOptionPane.showMessageDialog(frame, "No file selected.");
		}
	}

	/**
	 * read the content of a file
	 * @param file
	 * @return a string representing the content of the file
	 */
	private String readFile(File file) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) { // read file
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error reading file: " + e.getMessage());
		}
		return content.toString();
	}

	/**
	 * Display the content of the file on center panel, then calls analyzeWordFrequency()
	 * @param fileName - String representing the file name of the file to display
	 * @return void
	 */
	private void displayFileContent(String fileName) {
		// update left panel with the file's content
		if (openedFiles.get(fileName) == null) textArea.setText("");
		else textArea.setText(openedFiles.get(fileName));
		analyzeWordFrequency();
	}

	/**
	 * Updates the right panel with the top 20 most frequent words
	 * @param none
	 * @return void
	 */
	private void analyzeWordFrequency() {
		String content = textArea.getText();
		if (content.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "No content to analyze.");
			return;
		}

		long startTime = System.currentTimeMillis();
		Map<String, Integer> wordCount = new HashMap<>(); // HashMap for word count

		String[] words = content.toLowerCase().split("\\s+"); // Split by spaces and newline characters

		for (String word : words) {

			// remove trailing and leading whitespace
			word = word.trim();
			while (word.length() != 0) {

				// remove symbols at beginning
				if (!Character.isLetterOrDigit(word.charAt(0))) word = word.substring(1);
				// remove symbols at end
				else if (!Character.isLetterOrDigit(word.charAt(word.length()-1))) word = word.substring(0,word.length()-1);

				// remove 's at the end unless its part of contractions (keep contractions as one word)
				else if (word.length() >= 2 && word.substring(word.length()-2,word.length()).equalsIgnoreCase("\'s")) {
					if (!contractions.contains(word.toLowerCase())) word = word.substring(0,word.length()-2);
					else break;
				}
				else break;
			}

			// remove trailing and leading whitespace
			word = word.trim();
			System.out.println(word);
			if (word.length() == 0) continue;

			if (wordCount.get(word) == null) {
				wordCount.put(word, 1);
			} else {
				wordCount.put(word,wordCount.get(word) + 1);
			}

		}

		// Convert map to a list of Word objects
		List<String> keyList = new ArrayList<>(wordCount.keySet());
		List<Word> wordFrequencyList = new ArrayList<>();
		for (String key: keyList) wordFrequencyList.add(new Word(key, wordCount.get(key)));

		// Sort the list in descending order of frequency (using compareTo method in Word.java)
		Collections.sort(wordFrequencyList);

		long endTime = System.currentTimeMillis();
		runTime = endTime - startTime;

		// Display top 20 words
		StringBuilder output = new StringBuilder();
		output.append("Total Time: ").append(runTime).append(" milliseconds\n\n");
		output.append("20 Most Frequent Words\n\n");
		output.append(String.format("%-15s %s\n", "Word", "Frequency"));
		output.append("----------------------------\n");
		int rank = 1;

		// add the top 20 strings (unless if the file has less than 20 words)
		for (Word wf : wordFrequencyList.subList(0, Math.min(20, wordFrequencyList.size()))) {
			output.append(String.format("%2d) %s\n", rank, wf.toString()));
			rank++;
		}

		// update the output area
		outputArea.setText(output.toString());
	}
}
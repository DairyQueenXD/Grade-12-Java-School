package Unit2_Assignment4;
import java.util.*; import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * Name: Dequan Kong
 * Date: Nov 12, 2024
 * Description: Interactive program that organizes a music collection consisting of playlists and songs, allowing
 * 				the user to add songs, sort songs, add playlists, etc.
 */

public class DriverWithJFrame implements ActionListener {

	// Too many variables initialization...
	ArrayList <Playlist> allPlaylists = new ArrayList<>();
	Map<JButton, Playlist> buttonPlaylistMap = new HashMap<>();
	Map<JButton, Song> songsMap = new HashMap<>();
	Map<Playlist, SortOrder> playlistSortOrderMap = new HashMap<>();

	JPanel panel =  new JPanel(), scrollPanel = new JPanel(), rightPanel = new JPanel(), leftPanel = new JPanel();
	JPanel playlistInfoPanel = new JPanel(), playlistSongsPanel = new JPanel(), playlistTopPanel = new JPanel(), playlistBottomPanel = new JPanel();
	JPanel songsPanel = new JPanel(), playlistTitlePanel = new JPanel();
	JPanel playlistBottomLeftPanel = new JPanel(), playlistBottomMiddlePanel = new JPanel(), playlistBottomRightPanel = new JPanel();
	JScrollPane scrollPane, songsPane, playlistTitlePane, allSongsPane;
	JLabel emptyPlaylist = new JLabel("You have no playlist currently.");
	JLabel numOfSongs = new JLabel(""), totalTime = new JLabel(""), averageTimePerSong = new JLabel("");
	JPanel songsMenuPanel = new JPanel(), allSongsPanel = new JPanel();

	JLabel playlistTitle = new JLabel("No playlist selected");
	JLabel songTitleLabel = new JLabel ("Title"), artistLabel = new JLabel ("Artist"), genreLabel = new JLabel ("Genre");
	JLabel ratingLabel = new JLabel ("Rating"),timeLabel = new JLabel("Time");

	JFrame frame = new JFrame("Assignment 4");
	final int buttonWidth = 275, playlistBottomLength = 290;
	final int buttonHeight = 50, playlistInfoLength = 130, playlistInfoTopLength = 60, playlistInfoBottomLength = playlistInfoLength - playlistInfoTopLength;
	int maxNumOfSongs = -1, curNumOfSongs = 50;
	final int playlistTitlePaneLength = 800, rightPanelLength = 1000;
	Font defaultFont = new Font("Avenir", Font.PLAIN, 20); Font playlistInfoBottomFont = new Font("Avenir", Font.PLAIN, 20);
	private Playlist currentPlaylist = null;

	/**
	 * Description: Sets up the songs menu panel with sorting and song management options.
	 *              The menu includes buttons for sorting songs by title, artist, and time,
	 *              and an option to add a new song to the playlist. The panel also displays 
	 *              labels for song properties such as title, artist, genre, rating, and time.
	 * 
	 * Parameters: None
	 * 
	 * Return: None
	 */

	public void songsMenuSetup() {

		// The "Song Menu" Panel (just below # of songs, total time, and average time per song)
		songsMenuPanel.setLayout(new FlowLayout());
		songsMenuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		JPanel buttonPanel = new JPanel (new FlowLayout());
		JButton threeDotButton = new JButton("...");

		threeDotButton.setPreferredSize(new Dimension(40,40));
		buttonPanel.add(threeDotButton);

		// Options at the leftmost three point
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem option1 = new JMenuItem("Sort by title");
		JMenuItem option2 = new JMenuItem("Sort by artist");
		JMenuItem option3 = new JMenuItem("Sort by time");
		JMenuItem option4 = new JMenuItem("Add a song");

		// Add options to the popup menu
		popupMenu.add(option1);
		popupMenu.add(option2);
		popupMenu.add(option3);
		popupMenu.add(option4);

		// Add action listener to the three-dot button to show the popup menu
		threeDotButton.addActionListener(e -> {
			popupMenu.show(threeDotButton, threeDotButton.getWidth(), 0);
		});

		buttonPanel.add(threeDotButton);
		option1.addActionListener(e -> { // Sort the songs by title
			if (currentPlaylist != null) { // check if playlist is selected
				Collections.sort(currentPlaylist.getSongs());
				// After sorting, update and display the songs
				displaySongs(currentPlaylist);
			} else {
				JOptionPane.showMessageDialog(frame, "No playlist selected.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		option2.addActionListener(e -> { // Sort by artist
			if (currentPlaylist != null) {
				Collections.sort(currentPlaylist.getSongs(), new SortByArtist());
				// After sorting, update and display the songs
				displaySongs(currentPlaylist);
			} else {
				JOptionPane.showMessageDialog(frame, "No playlist selected.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		option3.addActionListener(e -> { // Sort by time
			if (currentPlaylist != null) {
				Collections.sort(currentPlaylist.getSongs(), new SortByTime());
				displaySongs(currentPlaylist);
			} else {
				JOptionPane.showMessageDialog(frame, "No playlist selected.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		option4.addActionListener(e -> { // Add a new song
			if (currentPlaylist != null) {
				try {
					// Get song properties from the user
					String title = JOptionPane.showInputDialog(frame, "Enter song title:", "Add a Song", JOptionPane.QUESTION_MESSAGE);
					String artist = JOptionPane.showInputDialog(frame, "Enter artist name:", "Add a Song", JOptionPane.QUESTION_MESSAGE);
					String genre = JOptionPane.showInputDialog(frame, "Enter genre:", "Add a Song", JOptionPane.QUESTION_MESSAGE);

					String ratingInput = JOptionPane.showInputDialog(frame, "Enter rating (1-5):", "Add a Song", JOptionPane.QUESTION_MESSAGE);
					int rating = Integer.parseInt(ratingInput); // Parse rating as an integer
					if (rating < 1 || rating > 5) {
						throw new NumberFormatException("Rating must be between 1 and 5.");
					}

					String timeInput = JOptionPane.showInputDialog(frame, "Enter time (mm:ss):", "Add a Song", JOptionPane.QUESTION_MESSAGE);
					if (timeInput == null || !timeInput.contains(":")) {
						throw new NumberFormatException("Invalid time format. Please use mm:ss.");
					}
					String[] timeParts = timeInput.split(":");
					int minutes = Integer.parseInt(timeParts[0]);
					int seconds = Integer.parseInt(timeParts[1]);
					if (seconds < 0 || seconds >= 60) {
						throw new NumberFormatException("Seconds must be between 0 and 59.");
					}
					Time songTime = new Time(minutes, seconds);

					// Create a new Song object based on properties
					Song newSong = new Song(title, artist, genre, rating, songTime);

					// Ask for new sorting option after adding a song
					String[] options = {"Title", "Artist", "Time"};
					int sortChoice = JOptionPane.showOptionDialog(frame, "How would you like to sort the songs?","Sort Songs",JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE,null,options, options[0]);

					// Sort based on user choice
					if (sortChoice == 0) { // Sort by Title
						currentPlaylist.addSong(newSong);
						Collections.sort(currentPlaylist.getSongs());
					} else if (sortChoice == 1) { // Sort by Artist
						currentPlaylist.addSong(newSong);
						Collections.sort(currentPlaylist.getSongs(), new SortByArtist());
					} else if (sortChoice == 2) { // Sort by Time
						currentPlaylist.addSong(newSong);
						Collections.sort(currentPlaylist.getSongs(), new SortByTime());
					}

					// Update and display the songs
					displaySongs(currentPlaylist);

					// Update info labels at the top
					numOfSongs.setText("" + currentPlaylist.getNumOfSongs());
					totalTime.setText(currentPlaylist.getTotalTime().toString());
					averageTimePerSong.setText(currentPlaylist.getAverageTime().toString());

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(frame, "No playlist selected.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		// The rest of the "Song Menu" Panel - there are a lot of extra panels for good display (panel within panel)
		// These panels don't do much
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlePanel.add(songTitleLabel);
		titlePanel.setPreferredSize(new Dimension(400,50));
		songTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel artistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		artistPanel.setPreferredSize(new Dimension(200,50));
		artistPanel.add(artistLabel);
		artistLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel genrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genrePanel.add(genreLabel);
		genrePanel.setPreferredSize(new Dimension(100,50));
		genreLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ratingPanel.add(ratingLabel);
		ratingPanel.setPreferredSize(new Dimension(100,50));
		ratingLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		timePanel.add(timeLabel);
		timePanel.setPreferredSize(new Dimension(100,50));
		timeLabel.setHorizontalAlignment(SwingConstants.LEFT);

		songsMenuPanel.add(buttonPanel);
		songsMenuPanel.add(titlePanel);
		songsMenuPanel.add(artistPanel);
		songsMenuPanel.add(genrePanel);
		songsMenuPanel.add(ratingPanel);
		songsMenuPanel.add(timePanel);

	}

	/**
	 * Description: Sets up the playlist title panel, which displays the title of the current playlist. 
	 *              The panel uses a horizontal BoxLayout and is embedded in a scroll pane to allow 
	 *              horizontal scrolling if the title is too long. 
	 *              
	 * 				(The scrollPane here does NOT work at all, but I'm too scared to delete it. This is the only thing that does nothing in the program)
	 * Parameters: None
	 * 
	 * Return: None
	 */
	public void playlistTitleSetup() {
		// This is the top of the right panel, where you see the title of the current playlist in big
		playlistTitlePanel.setLayout(new BoxLayout(playlistTitlePanel, BoxLayout.X_AXIS));
		playlistTitlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		playlistTitlePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		playlistTitlePanel.setMaximumSize(new Dimension (800, playlistInfoTopLength));

		// JScrollPane for when there are multiple playlists.
		// At least that's what it's supposed to be... but it does not work.
		// To prevent the title from getting to long, some interesting restrictions were implemented. See below...
		playlistTitlePane = new JScrollPane(playlistTitlePanel);
		playlistTitlePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		playlistTitlePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		playlistTitlePane.setBorder(null);
		playlistTitlePane.setMaximumSize(new Dimension (800, playlistInfoTopLength)); // set max width 
		playlistTitlePane.setAlignmentX(Component.LEFT_ALIGNMENT); // align left
		playlistTitlePane.setAlignmentY(Component.TOP_ALIGNMENT);
		playlistTitlePane.getHorizontalScrollBar().setUnitIncrement(16); // smoother scrolling

		// Add to Top Panel (there's a lot of panel within panel)
		playlistTopPanel.add(playlistTitlePane);
		playlistTitlePanel.add(playlistTitle);

		playlistTitle.setFont(new Font("Avenir", Font.PLAIN, 40));
		playlistTitle.setMaximumSize(new Dimension (800, playlistInfoTopLength));
	}

	/**
	 * Description: Sets up the panel for displaying all songs in the playlist. The panel 
	 *              uses a vertical BoxLayout to arrange songs in a list format. It is 
	 *              embedded in a scroll pane to allow vertical scrolling, ensuring all songs 
	 *              are accessible regardless of the number of songs in the playlist.
	 * 				(This actually works)
	 * Parameters: None
	 * 
	 * Return: None
	 */
	public void songs() {
		// BoxLayout setup
		allSongsPanel.setLayout(new BoxLayout(allSongsPanel, BoxLayout.Y_AXIS));
		allSongsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		allSongsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		// Add to scrollPane
		allSongsPane = new JScrollPane(allSongsPanel);

		// Adjust vertical scroll
		allSongsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		allSongsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Set maximum size for the scroll pane to avoid layout conflicts
		allSongsPane.setMaximumSize(new Dimension(1000, Integer.MAX_VALUE));
		allSongsPane.setPreferredSize(new Dimension(1000, 600)); // Adjust as needed
		allSongsPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		allSongsPane.setAlignmentY(Component.TOP_ALIGNMENT);
		allSongsPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

		// Add to panel
		playlistSongsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 900));
		playlistSongsPanel.add(allSongsPane);
	}

	/**
	 * Description: Sets up the scroll pane for displaying a list of playlists. The scroll panel 
	 *              is configured with a vertical BoxLayout to stack playlists vertically and is 
	 *              embedded in a scroll pane to enable vertical scrolling when necessary. 
	 * 
	 * Parameters: None
	 * 
	 * Return: None
	 */

	public void playlists() { 

		// This is similar to songs(), except it's for playlists (on the left panel)
		// BoxLayout Setup
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		scrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		// Add to scrollPane
		scrollPane = new JScrollPane(scrollPanel);

		// Adjust vertical scroll
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Some more properties setup
		scrollPane.setMaximumSize(new Dimension (350, Integer.MAX_VALUE)); // set max width 
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // align left
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

		scrollPanel.setBorder(BorderFactory.createEmptyBorder(20,20,0,0)); // add border to panel to separate playlists
	}

	/**
	 * Description: Configures the main layout and settings for the application's panels. 
	 *              The method sets up the frame, main panel, and sub-panels with appropriate 
	 *              layouts, dimensions, and background colors. Basically, this is just a grouping of the setLayouts of almost all panels
	 * 
	 * Parameters: None
	 * 
	 * Return: None
	 */

	public void panelSetup() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when you click "x" on top of screen

		// Main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // boxlayout
		panel.setPreferredSize(new Dimension(1500,900)); // size
		panel.setBackground(Color.gray); // color d
		panel.setBorder(BorderFactory.createEmptyBorder(50,20,20,0)); // top border

		// The right main panel
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setMaximumSize(new Dimension(rightPanelLength, Integer.MAX_VALUE));
		rightPanel.setBackground(Color.white);

		// The left main panel
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		leftPanel.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));

		playlistInfoPanel.setLayout(new BoxLayout(playlistInfoPanel, BoxLayout.Y_AXIS));
		playlistInfoPanel.setMaximumSize(new Dimension(rightPanelLength, playlistInfoLength));

		// playlistTopPanel and playlistBottomPanel are added to playlistInfoPanel
		playlistTopPanel.setMaximumSize(new Dimension(rightPanelLength, playlistInfoTopLength));
		playlistTopPanel.setLayout(new FlowLayout());

		playlistBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		playlistBottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, playlistInfoBottomLength));

		// These three are added to playlistBottomPanel
		playlistBottomLeftPanel.setPreferredSize(new Dimension (playlistBottomLength, playlistInfoBottomLength));
		playlistBottomLeftPanel.setLayout(new GridBagLayout());

		playlistBottomMiddlePanel.setPreferredSize(new Dimension (playlistBottomLength, playlistInfoBottomLength));
		playlistBottomMiddlePanel.setLayout(new GridBagLayout());

		playlistBottomRightPanel.setPreferredSize(new Dimension (playlistBottomLength, playlistInfoBottomLength));
		playlistBottomRightPanel.setLayout(new GridBagLayout());

	}
	/**
	 * Description: Constructor that contains method calls and more setup (mainly adding panels within panels)
	 * 
	 * Parameters: None
	 * 
	 * Return: None
	 */

	public DriverWithJFrame() throws IOException {
		// Call previously defined methods
		panelSetup();
		playlists();
		playlistTitleSetup();
		songsMenuSetup();
		songs();

		// Add a playlist button
		JButton addPlaylistButton = new JButton ("Add a playlist");
		addPlaylistButton.setActionCommand("AddPlaylist");
		addPlaylistButton.addActionListener(this);

		// The useless scrollpane on top
		playlistTopPanel.add(playlistTitlePane);


		// These are the JLabels just below the title of playlist
		// # of Songs
		JLabel temp = new JLabel("# of Songs: ");
		temp.setFont(playlistInfoBottomFont);
		playlistBottomLeftPanel.add(temp);
		playlistBottomLeftPanel.add(numOfSongs);
		numOfSongs.setFont(playlistInfoBottomFont);

		// Total Time
		JLabel temp2 = new JLabel ("Total time: ");
		temp2.setFont(playlistInfoBottomFont);
		playlistBottomMiddlePanel.add(temp2);
		totalTime.setFont(playlistInfoBottomFont);
		playlistBottomMiddlePanel.add(totalTime);

		// Average time per song
		JLabel temp3 = new JLabel ("Average time per song: ");
		temp3.setFont(playlistInfoBottomFont);
		playlistBottomRightPanel.add(temp3);
		averageTimePerSong.setFont(playlistInfoBottomFont);
		playlistBottomRightPanel.add(averageTimePerSong);

		// Add to playlistBottomPanel
		playlistBottomPanel.add(playlistBottomLeftPanel); 
		playlistBottomPanel.add(playlistBottomMiddlePanel);
		playlistBottomPanel.add(playlistBottomRightPanel);

		// Add to rightPanel
		rightPanel.add(playlistInfoPanel);
		rightPanel.add(songsMenuPanel);
		rightPanel.add(playlistSongsPanel);

		// Add to playlistInfoPanel
		playlistInfoPanel.add(playlistTopPanel);
		playlistInfoPanel.add(playlistBottomPanel);

		// Add to leftPanel
		leftPanel.add(addPlaylistButton);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		leftPanel.add(scrollPane);

		// emptyPlaylist is a JLabel that says "No playlist currently" when there are 0 playlist imported
		scrollPanel.add(emptyPlaylist);

		// Add to main panel
		panel.add(leftPanel);
		panel.add(Box.createRigidArea(new Dimension(100,0))); // space between
		panel.add(rightPanel);

		// Frame
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Description: Handles user actions triggered by buttons in the user interface. 
	 *              Mainly used for two actions: adding a new playlist and displaying an existing playlist.
	 *              For adding a playlist, it prompts the user to input a file name, and if the file is found, it adds the playlist.
	 *              For displaying a playlist, it updates the right panel with the selected playlist's details and songs.
	 * 
	 * Parameters: 
	 * - e: ActionEvent object that contains information about the event that occurred
	 * 
	 * Return: None
	 */

	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();

		if (action.equals("AddPlaylist")) { // Add a new playlist
			String fileName = JOptionPane.showInputDialog("Enter file name (without .txt): ", "Add a playlist");
			if (fileName != null) 
				try { 
					fileName = fileName.trim();
					// Handle input by calling addPlaylist method
					addPlaylist(fileName); 
				} catch (IOException e1) { 
					JOptionPane.showMessageDialog (panel, "File was not found! Please try again.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
		}
		if (action.equals("PlaylistDisplay")) { // display a playlist (when you click the corresponding playlist's button)
			JButton clickedButton = (JButton) e.getSource();

			// I know we didn't learn HashMaps yet, but I didn't really know how to implement otherwise. Sorry!!
			Playlist selectedPlaylist = buttonPlaylistMap.get(clickedButton);

			// Update global variable currentPlaylist (useful when sorting)
			currentPlaylist = buttonPlaylistMap.get(clickedButton);

			if (selectedPlaylist != null) {
				// Call displaySongs method to display all songs in playlist
				displaySongs(selectedPlaylist);

				// Update the right panel with the selected playlist information
				playlistTitle.setText(selectedPlaylist .getTitle());
				numOfSongs.setText(""+selectedPlaylist .getNumOfSongs());
				totalTime.setText(selectedPlaylist .getTotalTime().toString());
				averageTimePerSong.setText(selectedPlaylist.getAverageTime().toString());

				rightPanel.revalidate();
				rightPanel.repaint();
			}
		}
	}

	/**
	 * Description: Main method - creates an instance of the DriverWithJFrame class.
	 * 
	 * Parameters: 
	 * - args: not used
	 * 
	 * Return: None
	 */

	public static void main(String[] args) throws IOException {
		new DriverWithJFrame();
	}

	/**
	 * Description: Adds a new playlist by reading song details from the file (fileName)
	 *              The method reads the file, creates Song objects for each song, and adds them to 
	 *              a new Playlist object. It also updates the screen to display the new playlist.
	 * 
	 * Parameters: 
	 * - fileName: The name of the text file (without .txt) 
	 * 
	 * Return: None
	 */

	public void addPlaylist(String fileName) throws IOException {
		// file reader Scanner
		fileName = fileName.toLowerCase();
		Scanner inFile = new Scanner (new FileReader(fileName + ".txt"));

		// Initialize playlist's variables
		String playlistTitle = inFile.nextLine();
		int numOfSongs = Integer.parseInt(inFile.nextLine());
		maxNumOfSongs = Integer.max(numOfSongs, maxNumOfSongs);

		ArrayList<Song> initialSongs = new ArrayList<>();
		Time totalTime = new Time(0,0);

		// Create variables for reading input
		String title, artist, genre, timeString; int rating;

		for (int i = 0; i < numOfSongs; ++i) {
			// Read input
			title = inFile.nextLine(); artist = inFile.nextLine(); genre = inFile.nextLine();
			rating = Integer.parseInt(inFile.nextLine()); timeString = inFile.nextLine();

			// Create a Time variable
			int index = timeString.indexOf(":");
			Time songLength = new Time (Integer.parseInt(timeString.substring(0,index)), Integer.parseInt(timeString.substring(index+1)));
			totalTime.addNewTime(Integer.parseInt(timeString.substring(0,index)), Integer.parseInt(timeString.substring(index+1)));
			initialSongs.add(new Song(title,artist,genre,rating,songLength));
		}
		// Create new playlist & add to panel by calling createButtonPanel()
		Playlist newPlaylist = new Playlist(playlistTitle, numOfSongs, initialSongs, totalTime, new Time(0, 0));
		JPanel buttonPanel = createButtonPanel(newPlaylist, playlistTitle);

		// Remove the emptyPlaylist JLabel
		if (allPlaylists.size() == 0) {
			scrollPanel.remove(emptyPlaylist);
		}

		// Add to ArrayList
		allPlaylists.add(newPlaylist);
		// Add to panel
		scrollPanel.add(buttonPanel);  

		scrollPane.revalidate();
		scrollPane.repaint();
		scrollPanel.revalidate();
		scrollPanel.repaint();

		inFile.close();
	}

	/**
	 * Description: Displays all the songs in the specified playlist by creating individual panels 
	 *              for each song and adding them to allSongsPanel. Each song panel includes 
	 *              labels for song properties and a three-dot button that opens a popup menu 
	 *              to remove the song. When a song is removed, the playlist is updated.
	 * 
	 * Parameters: 
	 * - playlist: The Playlist object containing the list of songs to be displayed
	 * 
	 * Return: None
	 */

	public void displaySongs(Playlist playlist) {
		// Clear the existing content
		allSongsPanel.removeAll();
		
		// Iterate through the songs in the playlist
		for (int i = 0; i < playlist.getSongs().size(); ++i) {
			Song song = playlist.getSongs().get(i);

			// Create a panel for each song
			JPanel songPanel = new JPanel(new FlowLayout());
			songPanel.setMaximumSize(new Dimension(1000, 50)); // Adjust as needed

			// Create buttonPanel to hold the threeDotButton
			JPanel buttonPanel = new JPanel (new FlowLayout());
			JButton threeDotButton = new JButton("...");

			threeDotButton.setPreferredSize(new Dimension(40,40));
			buttonPanel.add(threeDotButton);

			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem option1 = new JMenuItem("Remove this song");

			// Add options to the popup menu
			popupMenu.add(option1);

			// Add action listener to the three-dot button to show the popup menu
			threeDotButton.addActionListener(e -> {
				popupMenu.show(threeDotButton, threeDotButton.getWidth(), 0);
			});

			// Create and align each label to the left
			JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel titleLabel = new JLabel(song.getTitle());
			titlePanel.add(titleLabel);
			titlePanel.setPreferredSize(new Dimension(400,50));
			titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

			JPanel artistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel artistLabel = new JLabel(song.getArtist());
			artistPanel.setPreferredSize(new Dimension(200,50));
			artistPanel.add(artistLabel);
			artistLabel.setHorizontalAlignment(SwingConstants.LEFT);

			JPanel genrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel genreLabel = new JLabel(song.getGenre());
			genrePanel.add(genreLabel);
			genrePanel.setPreferredSize(new Dimension(100,50));
			genreLabel.setHorizontalAlignment(SwingConstants.LEFT);

			JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel ratingLabel = new JLabel(String.valueOf(song.getRating()));
			ratingPanel.add(ratingLabel);
			ratingPanel.setPreferredSize(new Dimension(100,50));
			ratingLabel.setHorizontalAlignment(SwingConstants.LEFT);

			JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel timeLabel = new JLabel(song.getTime().toString());
			timePanel.add(timeLabel);
			timePanel.setPreferredSize(new Dimension(100,50));
			timeLabel.setHorizontalAlignment(SwingConstants.LEFT);

			// Add the song panel to the allSongsPanel
			songPanel.add(buttonPanel);
			songPanel.add(titlePanel);
			songPanel.add(artistPanel);
			songPanel.add(genrePanel);
			songPanel.add(ratingPanel);
			songPanel.add(timePanel);
			allSongsPanel.add(songPanel);

			// Update map
			songsMap.put(threeDotButton, song);

			// Option 1: Remove this playlist
			option1.addActionListener(e -> {
				// Use an iterator to remove song from playlist of songs (arraylist indexing was too annoying)
				Iterator<Song> iterator = playlist.getSongs().iterator();
				while (iterator.hasNext()) {
					Song currentSong = iterator.next();
					// Check if it corresponds to map
					if (currentSong.equals(songsMap.get(threeDotButton))) {
						iterator.remove(); // Safely remove the song
						break; // Break out of the loop once the song is removed
					}
				}

				// Remove the song panel from the allSongsPanel and update the UI
				allSongsPanel.remove(songPanel);
				songsMap.remove(threeDotButton);
				allSongsPanel.repaint();
				allSongsPanel.revalidate();

				// Update info on rightPanel
				playlist.setNumOfSongs(playlist.getNumOfSongs()-1);
				numOfSongs.setText(playlist.getNumOfSongs() + "");
				playlist.substractTotalTime(song.getTime());
				totalTime.setText(playlist.getTotalTime().toString());
				playlist.calcAverageTime();
				averageTimePerSong.setText(playlist.getAverageTime().toString());
			});
		}

		// Refresh panel
		allSongsPanel.revalidate();
		allSongsPanel.repaint();
	}

	/**
	 * Description: Creates a JPanel containing a button to display the playlist and 
	 *              a three-dot button that opens a popup menu with options to manage the playlist. 
	 *              The options include removing the playlist, creating a copy of the original playlist, and creating a sub-playlist.
	 * 
	 * Parameters: 
	 * - playlist: The Playlist object for which the button panel is created
	 * - title: The title of the playlist
	 * 
	 * Return: 
	 * - JPanel: The button panel containing the playlist button and three-dot button
	 */
	
	public JPanel createButtonPanel(Playlist playlist, String title) {
		// Create the button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonPanel.setMaximumSize(new Dimension(buttonWidth + 50, buttonHeight + 10));

		// Create the playlist button
		JButton button = new JButton(title);
		button.setToolTipText(title);
		button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		button.setFont(defaultFont);

		button.setActionCommand("PlaylistDisplay");
		button.addActionListener(this);

		// Add the button to the button panel
		buttonPanel.add(button);

		// Create the three-dot button and popup menu
		JButton threeDotButton = new JButton("...");
		threeDotButton.setPreferredSize(new Dimension(40, buttonHeight));

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem option1 = new JMenuItem("Remove this playlist");
		JMenuItem option2 = new JMenuItem("Create a copy of this playlist");
		JMenuItem option3 = new JMenuItem("Create a sub-playlist");

		// Option 1: Remove this playlist
		option1.addActionListener(e -> {
			scrollPanel.remove(buttonPanel);
			buttonPlaylistMap.remove(button);
			scrollPanel.repaint();
			scrollPanel.revalidate();
		});

		// Option 2: Create a copy of this playlist
		option2.addActionListener(e -> {
			String newTitle = "Copy of " + title;
			if (newTitle.length() > 38)  { JOptionPane.showMessageDialog(frame, "The title of the playlist is too long, which will affect the display. \nPlease import a playlist with a shorter title. "
					+ "\nWe apologize for the incovenience.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else { 
				// Create a deep copy of the songs in playlist
				ArrayList<Song> copiedSongs = new ArrayList<>();
				for (Song song : playlist.getSongs()) {
					copiedSongs.add(new Song(song.getTitle(), song.getArtist(), song.getGenre(), song.getRating(), song.getTime()));
				}

				// Create a deep copy of the playlist
				Playlist copiedPlaylist = new Playlist(newTitle, playlist.getNumOfSongs(), copiedSongs,
						new Time(playlist.getTotalTime().getMinutes(), playlist.getTotalTime().getSeconds()), // Copy total time
						new Time(playlist.getAverageTime().getMinutes(), playlist.getAverageTime().getSeconds()) // Copy average time
						);

				// Create a button panel for the copied playlist
				JPanel copiedButtonPanel = createButtonPanel(copiedPlaylist, newTitle);

				scrollPanel.add(copiedButtonPanel);
				scrollPanel.repaint();
				scrollPanel.revalidate();
			}
		});

		// Option 3: Create a sub-playlist
		option3.addActionListener(e -> {
			String newTitle = "Sub of " + title;
			if (newTitle.length() > 38)  { JOptionPane.showMessageDialog(frame, "The title of the playlist is too long, which will affect the display. \nPlease import a playlist with a shorter title. "
					+ "\nWe apologize for the incovenience.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				if (playlist.getNumOfSongs() == 0) {
					JOptionPane.showMessageDialog(frame, "Playlist is empty.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					// Ask for the start song number
					String startInput = JOptionPane.showInputDialog(frame, "Enter the start song number (Min: 1):", "Create Sub-Playlist", 
							JOptionPane.QUESTION_MESSAGE);

					int startNumber = Integer.parseInt(startInput);

					// Check if the start number is within valid range
					if (startNumber < 1 || startNumber > playlist.getNumOfSongs()) {
						JOptionPane.showMessageDialog(frame, "Invalid start number. Must be between 1 and " + playlist.getNumOfSongs(), "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Ask for the end song number
					String endInput = JOptionPane.showInputDialog(frame, "Enter the end song number (Min: " + startNumber + ", Max: " + playlist.getNumOfSongs() + "):", 
							"Create Sub-Playlist", JOptionPane.QUESTION_MESSAGE);

					int endNumber = Integer.parseInt(endInput);

					// Check if the end number is within valid range
					if (endNumber < startNumber || endNumber > playlist.getNumOfSongs()) {
						JOptionPane.showMessageDialog(frame, "Invalid end number. Must be between " + startNumber + " and " + playlist.getNumOfSongs(), "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Create a deep copy of all songs within range
					ArrayList<Song> copiedSongs = new ArrayList<>();
					for (int i = startNumber - 1; i < endNumber; i++) { // -1 to adjust for 0-based index
						Song originalSong = playlist.getSongs().get(i);

						// Create a deep copy of the Song object
						Song copiedSong = new Song( originalSong.getTitle(),originalSong.getArtist(),originalSong.getGenre(),
								originalSong.getRating(),new Time(originalSong.getTime().getMinutes(), originalSong.getTime().getSeconds())
								);
						copiedSongs.add(copiedSong);
					}

					// Create a new playlist with the copied songs
					Playlist subPlaylist = new Playlist("Sub of " + title, copiedSongs.size(), copiedSongs, new Time(0, 0), new Time(0, 0));

					// Calculate the total and average time for the sub-playlist
					Time totalSubTime = new Time(0, 0);
					for (Song song : copiedSongs) {
						totalSubTime.addNewTime(song.getTime().getMinutes(), song.getTime().getSeconds());
					}
					subPlaylist.setTotalTime(totalSubTime);
					subPlaylist.calcAverageTime();

					// Add the new sub-playlist to allPlaylists and create its button panel
					allPlaylists.add(subPlaylist);
					JPanel subPlaylistButtonPanel = createButtonPanel(subPlaylist, subPlaylist.getTitle());
					scrollPanel.add(subPlaylistButtonPanel);

					scrollPanel.repaint();
					scrollPanel.revalidate();

				} catch (NumberFormatException exc) {
					// Handle invalid input
					JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		// Add options to the popup menu
		popupMenu.add(option1);
		popupMenu.add(option2);
		popupMenu.add(option3);

		// Add action listener to the three-dot button to show the popup menu
		threeDotButton.addActionListener(e -> {
			popupMenu.show(threeDotButton, threeDotButton.getWidth(), 0);
		});

		// Add the three-dot button to the button panel
		buttonPanel.add(threeDotButton);

		// Add button and playlist to map
		buttonPlaylistMap.put(button, playlist);

		return buttonPanel;
	}
}

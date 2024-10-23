package Unit2_Assignment4;
import java.util.*; import java.io.*;

public class Driver {

	public static int displayMenu (int menuNum, Scanner stdIn) throws IOException {

		if (menuNum == 0)
		{
			System.out.println ("----------  MAIN MENU  -----------");
			System.out.println ("1) Accessing your playlists");
			System.out.println ("2) Accessing within a particular playlist");
			System.out.println ("3) Exit");
			System.out.println ("----------------------------------");
		}
		else if (menuNum == 1)
		{
			System.out.println ("\n---------  SUB-MENU #1  ----------");
			System.out.println ("1) Display all of your playlists"); 
			System.out.println ("2) Display info on a particular playlist"); 
			System.out.println ("3) Add a playlist"); 
			System.out.println ("4) Remove a playlist");
			System.out.println ("5) Copy a playlist");
			System.out.println ("6) Create a sub-playlist");
			System.out.println ("7) List songs in common between two playlists");
			System.out.println ("8) Return back to main menu.");
			System.out.println ("----------------------------------");
		}
		else
		{
			System.out.println ("\n---------  SUB-MENU #2  ----------");
			System.out.println ("1) Display all songs (in the last sorted order) ");
			System.out.println ("2) Display info on a particular song ");
			System.out.println ("3) Add song");
			System.out.println ("4) Remove Song (4 options)");
			System.out.println ("5) Sort songs (3 options)");
			System.out.println ("6) Return back to main menu");
			System.out.println ("----------------------------------");
		}

		System.out.print ("\n\nPlease enter your choice:  ");

		int choice = Integer.parseInt (stdIn.nextLine ());
		return choice;
	}

	public static void removePlaylist(ArrayList<Playlist> allPlaylists, int playlistNumber) {
		allPlaylists.remove(playlistNumber - 1);
	}

	public static int askForPlaylistNumber(Scanner in) throws IOException{
		System.out.print("Enter the playlist number: ");
		return Integer.parseInt(in.nextLine());
	}

	public static void main(String[] args) throws IOException {

		// input Scanner
		Scanner in = new Scanner (System.in);
		ArrayList <Playlist> allPlaylists = new ArrayList<>();


		int mainMenuChoice = 0, subMenuChoice = 0, playlistNumber = 0, songNumber = 0;
		mainMenuChoice = displayMenu (0, in);

		//		while (mainMenuChoice != 3) {
		displayMenu(mainMenuChoice,in);
		if (mainMenuChoice == 1) {
			while (subMenuChoice != 8) {
				subMenuChoice = displayMenu (1, in);
				if (subMenuChoice == 1) {
					displayAllPlaylists(allPlaylists);
				}
				if (subMenuChoice == 2) {
					playlistNumber = askForPlaylistNumber(in);
					displayPlaylistInfo(allPlaylists.get(playlistNumber-1));
				}

				if (subMenuChoice == 3) {
					addPlaylist(in, allPlaylists);
				}

				if (subMenuChoice == 4) {
					playlistNumber = askForPlaylistNumber(in);
					removePlaylist(allPlaylists, playlistNumber);
				}

				if (subMenuChoice == 5) {
					playlistNumber = askForPlaylistNumber(in);
					copyPlaylist(allPlaylists, playlistNumber);
				}

				if (subMenuChoice == 6) {
					playlistNumber = askForPlaylistNumber(in);
					System.out.print("Enter start index: ");
					int startInd = Integer.parseInt(in.nextLine());
					System.out.print("Enter end index: ");
					int endInd = Integer.parseInt(in.nextLine());
					createSubPlaylist(allPlaylists, playlistNumber, startInd-1, endInd-1);
				}

				if (subMenuChoice == 7) {
					int one = askForPlaylistNumber(in);
					int two = askForPlaylistNumber(in);

					if (allPlaylists.get(one-1).getSongs().size() > allPlaylists.get(two-1).getSongs().size()) 
						commonSongs(allPlaylists.get(one-1).getSongs(), allPlaylists.get(two-1).getSongs());
					else 
						commonSongs(allPlaylists.get(two-1).getSongs(), allPlaylists.get(one-1).getSongs());

				}
				
				if (subMenuChoice == 8) displayMenu(0,in);
			}
		}

		else if (mainMenuChoice == 2) {
			playlistNumber = askForPlaylistNumber(in);
			Playlist current = allPlaylists.get(playlistNumber-1);
			subMenuChoice = displayMenu (2, in);

			if (subMenuChoice == 1) {
				System.out.println(current.displayAllSongTitles());
			}

			if (subMenuChoice == 2) {
				System.out.print("Enter the song number: ");
				songNumber = Integer.parseInt(in.nextLine());
				System.out.println(current.getSongs().get(songNumber-1));
			}

			if (subMenuChoice == 3) {
				String title, artist, genre, timeString; int rating;
				
				System.out.print("Enter a title: ");
				title = in.nextLine();
				System.out.print("Enter the artist name: ");
				artist=  in.nextLine();
				System.out.print("Enter the genre: ");
				genre = in.nextLine();
				System.out.print("Enter the rating: ");
				rating = Integer.parseInt(in.nextLine());
				System.out.print("Enter the time (please follow this format - mm:ss)");
				timeString = in.nextLine();
				// Initialize time variable
				int index = timeString.indexOf(":");
				Time songLength = new Time (Integer.parseInt(timeString.substring(0,index)), Integer.parseInt(timeString.substring(index+1)));
				current.addSong(new Song(title,artist,genre,rating,songLength, 0));
			}
			if (subMenuChoice == 4) {
				System.out.println(current.displayAllSongTitles());
				System.out.print("Enter a choice: ");
				String choice = in.nextLine().trim().toLowerCase();
				if (choice.equals("number")) {
					System.out.print("Enter the number: ");
					int number = Integer.parseInt(in.nextLine());
					current.removeByNumber(new Song ("","","",-1,new Time(-1,-1),number));
				} else if (choice.equals("title")) {
					System.out.print("Enter the title: ");
					String title = in.nextLine();
					current.removeByTitle(new Song(title,"","",-1,new Time(-1,-1),-1));
				} else if (choice.equals("first")) {
					current.removeFirstSong();
				} else if (choice.equals("range")) {
					System.out.print("Enter start index: ");
					int start = Integer.parseInt(in.nextLine());
					System.out.print("Enter end index: ");
					int end = Integer.parseInt(in.nextLine());
					current.removeByRange(start, end);
				}
			}
			if (subMenuChoice == 5) {
				String choice = "";
				
				System.out.println(current.displayAllSongTitles());
			}
		}
		else System.exit(0);
	}
	//	}

	public static void commonSongs(ArrayList<Song> song1, ArrayList<Song> song2) { // assume p1 is always bigger than p2
		Collections.sort(song1);
		System.out.print("Common songs: ");
		String output = "";
		for (int i = 0; i < song2.size(); ++i) {
			int index = Collections.binarySearch(song1, song2.get(i));
			if (index >= 0) output += song2.get(i).getTitle() + ", ";
		}
		System.out.println(output.substring(0,output.length()-2));

	}
	public static void createSubPlaylist(ArrayList<Playlist> allPlaylists, int index, int start, int end) {
		Playlist old = allPlaylists.get(index-1);
		ArrayList<Song> songs = new ArrayList<>();
		int numOfSongs = end - start + 1;
		Time totalTime = new Time(0,0);
		for (int i = start; i <= end; ++i) {
			songs.add(old.getSongs().get(i));
			totalTime.addNewTime(old.getSongs().get(i).getTime().getMinutes(), old.getSongs().get(i).getTime().getSeconds());
		}
		Playlist newPlaylist = new Playlist("sub-" + old.getTitle(), numOfSongs, songs,totalTime, new Time(0,0));
		allPlaylists.add(newPlaylist);
	}

	public static void copyPlaylist(ArrayList<Playlist> allPlaylists, int index) {
		Playlist old = allPlaylists.get(index-1);
		Playlist newPlaylist = new Playlist(old.getTitle() + " Copy", old.getNumOfSongs(), old.getSongs(), old.getTotalTime(), old.getAverageTime());
		allPlaylists.add(newPlaylist);
	}

	public static void displayAllPlaylists(ArrayList <Playlist> allPlaylists) {
		System.out.print("Current playlists: \n");
		for (int i = 0; i < allPlaylists.size(); ++i) {
			Playlist current = allPlaylists.get(i);
			if (i == allPlaylists.size() - 1) System.out.println("#" + current.getNumber() + ": " + current.getTitle() + "\n");
			else System.out.print("#" + current.getNumber() + ": " + current.getTitle() + ", ");
		}
	}

	public static void displayPlaylistInfo (Playlist pl) {
		System.out.println(pl);
	}

	public static void addPlaylist(Scanner in, ArrayList<Playlist> allPlaylists) throws IOException {
		System.out.print("Enter a file name (without .txt): ");
		String fileName = in.nextLine();
		// file reader Scanner
		Scanner inFile = new Scanner (new FileReader(fileName + ".txt"));

		// Initialize first playlist's variables
		String playlistTitle = inFile.nextLine();
		int numOfSongs = Integer.parseInt(inFile.nextLine());
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
			initialSongs.add(new Song(title,artist,genre,rating,songLength, 0));
		}

		allPlaylists.add(new Playlist(playlistTitle, numOfSongs, initialSongs, totalTime, new Time(0,0)));
	}
}

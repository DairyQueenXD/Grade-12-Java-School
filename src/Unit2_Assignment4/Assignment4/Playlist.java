package Unit2_Assignment4;
import java.util.*; 

public class Playlist {
	private String title;
	private int numOfSongs;
	private ArrayList<Song> songs;
	private Time totalTime;
	private Time averageTime;
	private static int totalPlaylists = 0;
	private int number;

	/**
	 * Description: Constructs a new Playlist object and initialize its instance variables
	 * 
	 * Parameters: 
	 * - title: The title of the playlist
	 * - numOfSongs: The number of songs in the playlist
	 * - songs: An ArrayList of Song objects that are part of the playlist
	 * - totalTime: A Time object representing the total duration of all songs in the playlist
	 * - averageTime: A Time object representing the average duration of the songs
	 * 
	 * Return: None
	 */
	public Playlist(String title, int numOfSongs, ArrayList<Song> songs, Time totalTime, Time averageTime) {
		this.title = title;
		this.numOfSongs = numOfSongs;
		this.songs = songs;
		this.totalTime = totalTime;

		this.averageTime = averageTime;
		calcAverageTime();

		totalPlaylists += 1;
		this.number = totalPlaylists;
	}

	// Getter Methods
	public String getTitle() {
		return this.title;
	}

	public int getNumber() {
		return this.number;
	}

	public int getNumOfSongs() {
		return this.numOfSongs;
	}
	
	public ArrayList<Song> getSongs() {
		return this.songs;
	}


	public Time getTotalTime() {
		return this.totalTime;
	}

	public Time getAverageTime() {
		return this.averageTime;
	}

	// Setter Methods
	public void setNumOfSongs(int newNum) {
		this.numOfSongs = newNum;
	}
	
	public void setTotalTime(Time newTime) {
		this.totalTime = newTime;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	/**
	 * Description: Calculates and updates the average time per song in the playlist. 
	 *              If the number of songs is zero, the average time is set to 0 minutes 
	 *              and 0 seconds. 
	 * 
	 * Parameters: None
	 * 
	 * Return: None
	 */
	public void calcAverageTime() {
		if (numOfSongs == 0) {
			this.averageTime.setMinutes(0);
			this.averageTime.setSeconds(0);
		} else {
			int totalSec = (totalTime.getMinutes() * 60 + totalTime.getSeconds()) / numOfSongs;
			this.averageTime.setMinutes(totalSec / 60);
			this.averageTime.setSeconds(totalSec % 60);
		}
	}
	
	/**
	 * Description: Subtracts the Time object from the total time of the playlist 
	 *              and updates the total time accordingly. It calls the substractNewTime method from the Time class
	 * 
	 * Parameters: 
	 * - newTime: A Time object representing the duration to subtract from the total time
	 * 
	 * Return: None
	 */
	public void substractTotalTime (Time newTime) {
		int[] arr;
		arr = totalTime.substractNewTime(newTime);
		totalTime.setMinutes(arr[0]);
		totalTime.setSeconds(arr[1]);
	}

	/**
	 * Description: Adds a new song to the playlist, updates the total number of songs, 
	 *              adds the duration of the new song to the total time, and recalculates 
	 *              the average time per song.
	 * 
	 * Parameters: 
	 * - newSong: A Song object representing the song to be added to the playlist
	 * 
	 * Return: None
	 */
	public void addSong(Song newSong) {
		this.songs.add(newSong);
		this.numOfSongs+=1;
		this.totalTime.addNewTime(newSong.getMinutes(), newSong.getSeconds());
		this.calcAverageTime();
	}

	/**
	 * Description: Returns a string representation of the playlist, including the title, 
	 *              number of songs, total time, and average time per song. 
	 * 
	 * Parameters: None
	 * 
	 * Return: A string describing the playlist's details
	 */
	public String toString() {
		return "Title: " + title + "\n# of songs: " + numOfSongs + "\nTotal time: " + totalTime + "\nAverage time per song: " + averageTime;
	}
}

package Unit2_Assignment4;
import java.util.*; import java.io.*;

public class Playlist {
	private String title;
	private int numOfSongs;
	private ArrayList<Song> songs;
	private Time totalTime;
	private Time averageTime;
	private static int totalPlaylists = 0;
	private int number;
	
	public Playlist(String title, int numOfSongs, ArrayList<Song> songs, Time totalTime, Time averageTime) {
		this.title = title;
		this.numOfSongs = numOfSongs;
		this.songs = songs;
		this.totalTime = totalTime;
		
		// Calculate average time
		this.averageTime = averageTime;
		int averageSec = (totalTime.getMinutes() * 60 + totalTime.getSeconds()) / numOfSongs;
		this.averageTime.setMinutes(averageSec / 60);
		this.averageTime.setSeconds(averageSec % 60);
		
		totalPlaylists += 1;
		this.number = totalPlaylists;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public int getNumOfSongs() {
		return this.numOfSongs;
	}
	
	public Time getTotalTime() {
		return this.totalTime;
	}
	
	public Time getAverageTime() {
		return this.averageTime;
	}
	
	public String displayAllSongTitles() {
		String output = "";
		for (Song s: songs) {
			output += s.getTitle() + ", ";
		}
		return output.substring(0,output.length()-2);
	}
	
	public ArrayList<Song> getSongs() {
		return this.songs;
	}
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public void addSong(Song newSong) {
		this.songs.add(newSong);
	}
	
	public void removeByNumber(Song song) {
		Collections.sort(songs, new SortByNumber());
		int index = Collections.binarySearch(songs, song, new SortByNumber());
		if (index >= 0) songs.remove(index);
	}
	
	public void removeByTitle(Song song) {
		Collections.sort(songs);
		int index = Collections.binarySearch(songs,song);
		if (index >= 0) songs.remove(index);
	}
	
	public void removeFirstSong() {
		songs.remove(0);
	}
	
	public void removeByRange(int start, int end) {
		for (int i = start; i <= end; ++i) {
			songs.remove(start);
		}
	}
	
	public String toString() {
		return "Title: " + title + "\n# of songs: " + numOfSongs + "\nTotal time: " + totalTime + "\nAverage time per song: " + averageTime;
	}
}

package Unit2_Assignment4;

public class Song implements Comparable <Song>{
	private String title, artist, genre;
	private int rating;
	private Time length;

	/**
	 * Description: Constructor that is called when a new Song object is created
	 * 
	 * Parameters: 
	 * - title: The title of the song
	 * - artist: The artist of the song
	 * - genre: The genre of the song
	 * - rating: The rating of the song (1-5)
	 * - length: A Time object representing the duration of the song in minutes and seconds
	 * 
	 * Return: None
	 */
	public Song (String title, String artist, String genre, int rating, Time length) {
		this.title = title; 
		this.artist = artist;
		this.genre = genre;
		this.rating = rating;
		this.length = length;
	}

	// Getter Methods
	public int getRating() {
		return this.rating;
	}
	public String getGenre() {
		return this.genre;
	}
	public Time getTime() {
		return this.length;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getMinutes() {
		return this.length.getMinutes();
	}
	
	public int getSeconds() {
		return this.length.getSeconds();
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	/**
	 * Description: converts the Song to appropriate String format (technically don't need this since I'm displaying everything on JFrame
	 * 
	 * Parameters: None
	 * 
	 * Return: 
	 * - String: A formatted string containing the song's details
	 */
	public String toString() {
		
		return String.format("Title: %s%nArtist: %s%nGenre: %s%nRating: %d%nTime: %02d:%02d", title, artist, genre, rating, length.getMinutes(), 
				length.getSeconds());
	}
	
	/**
	 * Description: Compares the current Song object with another Song object based on their titles (case insensitve)
	 * 
	 * Parameters: 
	 * - song2: The Song object to compare with the current Song object
	 * 
	 * Return: 
	 * - int: A negative integer, zero, or a positive integer if the current Song's title is 
	 *        alphabetically less than, equal to, or greater than the title of song2
	 */
	public int compareTo(Song song2) { // sort by title
		return this.title.toLowerCase().compareTo(song2.title.toLowerCase());
	}
}

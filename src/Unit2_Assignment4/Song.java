package Unit2_Assignment4;

public class Song implements Comparable <Song>{
	private String title, artist, genre;
	private int rating, number;
	private Time length;

	public Song (String title, String artist, String genre, int rating, Time length, int number) {
		this.title = title; 
		this.artist = artist;
		this.genre = genre;
		this.rating = rating;
		this.length = length;
		this.number = 1;
	}

	public Time getTime() {
		return this.length;
	}
	
	public int getNumber() {
		return this.number;
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
	public String toString() {
		
		return String.format("Title: %s%nArtist: %s%nGenre: %s%nRating: %d%nTime: %02d:%02d", title, artist, genre, rating, length.getMinutes(), 
				length.getSeconds());
	}
	
	public int compareTo(Song song2) { // sort by title
		return this.title.compareTo(song2.title);
	}
}

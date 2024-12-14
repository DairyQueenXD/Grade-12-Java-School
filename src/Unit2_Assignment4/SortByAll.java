package Unit2_Assignment4;

import java.util.Comparator;

public class SortByAll implements Comparator<Song> {
	
	/**
	 * Description: Compares two Song objects based on everything (title, artist, genre, rating, time)
	 * 
	 * Parameters:
	 * - s1: The first Song object to be compared
	 * - s2: The second Song object to be compared
	 * 
	 * Return: the return that matters the most here is if the two songs are completely equal, in whicb case the method returns 0
	 */
	public int compare(Song s1, Song s2) {
		if (!s1.getTitle().equalsIgnoreCase(s2.getTitle())) return s1.getTitle().toLowerCase().compareTo(s2.getTitle().toLowerCase());
		if (!s1.getArtist().equalsIgnoreCase(s2.getArtist())) return s1.getArtist().toLowerCase().compareTo(s2.getArtist().toLowerCase());
		if (!s1.getGenre().equalsIgnoreCase(s2.getGenre())) return s1.getGenre().toLowerCase().compareTo(s2.getGenre().toLowerCase());
		if (s1.getRating() - s2.getRating() != 0) return s1.getRating() - s2.getRating();
		if (s1.getMinutes() - s2.getMinutes() != 0) return s1.getMinutes() - s2.getMinutes();
		return s1.getSeconds() - s2.getSeconds();
    }
}

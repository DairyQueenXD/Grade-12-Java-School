package Unit2_Assignment4;
import java.util.*;

public class SortByArtist implements Comparator<Song>{
	
	/**
	 * Description: Compares two Song objects based on the artist of the songs (case insensitive)
	 * 
	 * Parameters:
	 * - s1: The first Song object to be compared
	 * - s2: The second Song object to be compared
	 * 
	 * Return: An integer value that represents which artist is alphabetically smaller/larger/equal to the other
	 **/
	public int compare (Song s1, Song s2) {
		return s1.getArtist().toLowerCase().compareTo( s2.getArtist().toLowerCase());
	}
}

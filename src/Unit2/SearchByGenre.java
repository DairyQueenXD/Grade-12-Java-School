package Unit2;
import java.util.*;

public class SearchByGenre implements Comparator <Movie>{
	/*
	 * Description: This method compares two Movie objects; it is mainly used when sorting or searching for a movie based on their genres' alphabetical order.
	 * Parameters: 2 Movie objects; the two objects that need to be compared with each other
	 * Return: an integer; negative if m1's genre is alphabetically smaller than m2's genre, positive if m1's genre is alphabetically larger than m2's genre, and 0 if they are the same
	 */
	public int compare (Movie m1, Movie m2) {
		return m1.getGenre().toLowerCase().compareTo(m2.getGenre().toLowerCase());
	}
}
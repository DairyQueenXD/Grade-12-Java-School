package Unit2;
import java.util.*;

public class SearchByMovie implements Comparator <Movie> {
	/*
	 * Description: This method compares two Movie objects; it is mainly used when sorting or searching for a movie based on their titles' alphabetical order.
	 * Parameters: 2 Movie objects; the two objects that need to be compared with each other
	 * Return: an integer; negative if m1's title is alphabetically smaller than m2's title, positive if m1's title is alphabetically larger than m2's title, and 0 if the same
	 */
	public int compare (Movie m1, Movie m2) {
		return m1.getTitle().toLowerCase().compareTo(m2.getTitle().toLowerCase());
	}
}

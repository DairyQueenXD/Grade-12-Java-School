package Unit2_Assignment4;
import java.util.*;

public class SortByTime implements Comparator<Song>{
	/**
	 * Description: Compares two Song objects based on their length, first by minutes and then by seconds.
	 * 
	 * Parameters:
	 * - s1: The first Song object to be compared
	 * - s2: The second Song object to be compared
	 * 
	 * Return: An integer value:
	 *         - A negative integer if s1 is shorter than s2
	 *         - Zero if s1 and s2 have the same length
	 *         - A positive integer if s1 is longer than s2
	 */
	public int compare (Song s1, Song s2) {
		if (s1.getMinutes() == s2.getMinutes()) return s1.getSeconds() - s2.getSeconds();
		return s1.getMinutes() - s2.getMinutes();
	}
}

package Unit2;
import java.util.*;
public class SearchByRanking implements Comparator <Movie>{

	/*
	 * Description: This method compares two Movie objects; it is mainly used when sorting or searching for a movie based on their rankings (best ranking first)
	 * Parameters: 2 Movie objects; the two objects that need to be compared with each other
	 * Return: an integer; negative if m1's ranking is better than m2's ranking, positive if m2's ranking is better than m1's ranking, and 0 if the rankings are the same
	 */
	public int compare(Movie m1, Movie m2) {
		return m1.getRanking() - m2.getRanking();
	}
}

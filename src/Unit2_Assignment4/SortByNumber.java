package Unit2_Assignment4;
import java.util.*;

public class SortByNumber implements Comparator<Song>{
	
	public int compare(Song s1, Song s2) {
		if (s1.getNumber() < s2.getNumber()) return -1;
		if (s1.getNumber() > s2.getNumber()) return 1;
		return 0;
	}
}

package Unit2_Assignment4;
import java.util.*;

public class SortByTime implements Comparator<Song>{
	public int compare (Song s1, Song s2) {
		if (s1.getMinutes() == s2.getMinutes()) return s1.getSeconds() - s2.getSeconds();
		return s1.getMinutes() - s2.getMinutes();
	}
}

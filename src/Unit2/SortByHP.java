package Unit2;

import java.util.*;

public class SortByHP implements Comparator <Pokemon> {
	
	// We are in a different class now, so we have to do everything more complicated
	public int compare(Pokemon p1, Pokemon p2) {
		if (p1.getHp() < p2.getHp()) return -1;
		if (p1.getHp() > p2.getHp()) return 1;
		return 0;
	}
}

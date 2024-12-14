package Unit3;

import java.util.*;

public class ListIteratorTest {

	public static void main(String[] args) {
		
		LinkedList<Integer> lst = new LinkedList<Integer>();
		for (int i = 1; i <= 6; ++i) lst.add(i);
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 1; i <= 6; ++i) arr.add(i);

		List<Integer> lst2 = new LinkedList<Integer>(arr);
		
		System.out.println("test");
		for (Integer i: lst2) System.out.println(i);
		ListIterator<Integer> itr = lst.listIterator();
		lst.addFirst(10);
		lst.removeLast();
		itr = lst.listIterator();
		while (itr.hasNext()) System.out.println(itr.next());
	
	}

}

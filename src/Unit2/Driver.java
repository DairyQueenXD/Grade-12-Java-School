package Unit2;
import java.util.*;

public class Driver {

	public static void main(String[] args) {

		Pokemon p1 = new Pokemon("jigglypuff",100);
//		Pokemon p2 = new Pokemon("Charizard",100);

		ArrayList <Pokemon> list = new ArrayList <>();
		
//		int[] arr = new int[5];
		ArrayList <Double> list2 = new ArrayList <>();
		list2.add( 5*1.0);
		System.out.println(list2.get(0));
		list.add(p1); 
		list.add(new Pokemon("Charizard",50));
		list.add(new Pokemon("Pikachu", 80));
		list.add(new Pokemon("Lickitung",100));
		
		Collections.sort(list);
		System.out.println(list);
		// Slightly different: in the 2nd way, when we eventually want to delete the object, we won't be able to find it ever again!
		// It is somewhere in the memory, but since the arrayList is the only thing "pointing" at the 2nd object, we won't be able to find it.
		// In the first way, if we delete the object in the arrayList, we will still be able to access the object because p1 is also 
		// "pointing" at the object.
		
//		System.out.println(list.size());
//		System.out.println(list.get(0));
//		System.out.println(list.get(0).getName());
//		
//		System.out.println(list.contains(p1));
//		System.out.println(list.contains(new Pokemon("Charizard",100))); // returns false, because we created a new address!
//		// however, we override the equals method, so now it returns true.
//		
//		System.out.println(list.indexOf(p1));
//		System.out.println(list.indexOf(new Pokemon("Charizard",100)));
		
		// By default, "contains" and "indexOf" call "equals"
		// We modify the equals method for "custom comparator"!
		
		// ArrayList<Pokemon> list = new ArrayList<> (list); <- CORRECT WAY of creating a duplicate of an arrayList.
		// However, if we modify the list in the new arrayList, the original arrayList will also be modified (they share the same reference)!
		
		Collections.sort(list, new SortByHP());
		System.out.println(list);
	}

}

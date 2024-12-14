package Unit3;
import java.util.*;

public class ArrayListDemo
{
	public static void main (String[] args)
	{
		System.out.println ("ArrayList Demo");

		List <String>myList = new ArrayList <String>();

		myList.add ("apple");
		myList.add ("peach");
		myList.add ("banana");
		System.out.println (myList); // [apple,peach,banana]

		myList.add (1, "lemon");
		System.out.println (myList); // [apple,lemon,peach,banana]

		System.out.println (myList.size ()); // 4

		String fruit = myList.get (2);
		System.out.println (fruit); // peach

		String removedFruit = myList.set (2, "apple");
		System.out.println (removedFruit); // peach (returns the replaced element)
		System.out.println (myList); // [apple, lemon, apple, banana]

		int index = myList.indexOf ("apple"); 
		System.out.println (index);// 0
		index = myList.lastIndexOf ("apple"); 
		System.out.println (index);// 2

		System.out.println (myList.contains ("lemon")); // true
		System.out.println (myList.contains ("peach")); // false

		removedFruit = myList.remove (2); // returns the removed element
		System.out.println (removedFruit); // apple
		
//			myList.add (new Integer (1)); // <- error
		System.out.println (myList); // [apple, lemon, banana]

		ArrayList <Integer>newList = new ArrayList <Integer>();
		for (int number = 1 ; number <= 10 ; number++)
			newList.add (number);
		System.out.println (newList);

		ArrayList <Integer> newList2 = new ArrayList <Integer> (); 
		newList2.addAll (0, newList);
		newList2.add (1);
		System.out.println (newList2);

		List <Integer>subList = newList2.subList (0, 10);
		System.out.println (subList);

		System.out.println (subList.equals (newList));
		System.out.println (subList.equals (newList2));

		newList2.removeAll (newList);
		System.out.println (newList2);
		System.out.println (newList);
//		System.out.println (subList); <- error because newList2 got modified

		Collections.sort (myList);
		System.out.println (myList);

		System.out.println (newList.isEmpty ());
		newList.clear ();
		System.out.println (newList.isEmpty ());

		System.out.println (myList);

		for (int i = 0 ; i < myList.size () ; i++)
			System.out.println (myList.get (i));


		ListIterator <String>scan = myList.listIterator ();
		while (scan.hasNext ())
			System.out.println (scan.next ());


		Object[] myArray = myList.toArray ();
		for (int i = 0 ; i < myArray.length ; i++)
			System.out.println (myArray [i]);



		String[] strArray = new String [myList.size ()];
		myList.toArray (strArray);
		for (int i = 0 ; i < strArray.length ; i++)
			System.out.println (strArray [i]);

	} // main method
} // ArrayListDemo class

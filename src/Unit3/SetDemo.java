package Unit3;

import java.util.*;
 
public class SetDemo
{
   public static void main (String [] args)
   {
	System.out.println ("Set Demo");

	Set <String> mySet = new HashSet <String> ();

	mySet.add ("pear");
	mySet.add ("kiwi");
	mySet.add ("banana");
	mySet.add ("strawberry");
	System.out.println (mySet);

	mySet.add ("pear");
	mySet.add ("banana");
	mySet.add ("strawberry");
	System.out.println (mySet);

	Set <String> secondSet = new HashSet <String> ();
	secondSet.add ("pear");
	secondSet.add ("banana");
	secondSet.add ("apple");
	secondSet.add ("strawberry");
	secondSet.add ("raspberry");
	secondSet.add ("peach");
	System.out.println (secondSet);


	System.out.println ("\nFind the intersection of the sets");
	Set <String >intersection = new HashSet  
                                   <String >(mySet);
	intersection.retainAll (secondSet);
	System.out.println (intersection);
	System.out.println ("Number of elements: " + 
                            intersection.size ());

	System.out.println ("\nFind the union of the sets");
	Set <String> union = new HashSet <String> (mySet);
	union.addAll (secondSet);
	System.out.println (union);

	System.out.println ("Number of elements: " + 
                           union.size ());

	System.out.println ("\nUsing removeAll");
	System.out.println (mySet);
	System.out.println (secondSet);
	
Set <String> newSet = new HashSet <String> (mySet);
	newSet.removeAll (secondSet);
	System.out.println (newSet);

	newSet.remove ("kiwi");
	System.out.println (newSet.isEmpty ());

	
System.out.println ("\nChecking for elements or set within sets");
	System.out.println (intersection.contains ("apple"));
	System.out.println (intersection.contains ("pear"));
	System.out.println (union.containsAll (mySet));
	System.out.println (union.containsAll (secondSet));
	System.out.println (intersection.containsAll  
                           (secondSet));




	System.out.println ("\nUsing an Iterator to look at the set");
	Iterator <String> iter = mySet.iterator ();
	while (iter.hasNext ())
	    System.out.println (iter.next ());




	System.out.println ("\nTree Set commands");
	TreeSet <String> myTreeSet = new TreeSet 
                                    <String>(union);
	System.out.println (myTreeSet);

	System.out.println (myTreeSet.first ());
	System.out.println (myTreeSet.last ());
	System.out.println (myTreeSet.headSet ("peach"));
	System.out.println (myTreeSet.tailSet ("peach"));

	System.out.println (myTreeSet.subSet ("kiwi", 
                                             "raspberry"));
    } // main method
} // SetDemo class

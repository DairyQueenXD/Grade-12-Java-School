package Unit3;

import java.util.*;
/** The "MapDemo" class.
 * Purpose: To demo some Map methods
 */
public class MapDemo
{
	public static void main (String[] args)
	{
		System.out.println ("A demo of Java HashMaps and HashTree | methods");

		Map <String, String> animalClasses = new HashMap <String, 
				String> ();

		animalClasses.put ("dog", "mammal");
		animalClasses.put ("cat", "mammal");
		animalClasses.put ("horse", "mammal");
		animalClasses.put ("snake", "reptile");
		animalClasses.put ("lizard", "reptile");
		animalClasses.put ("robin", "bird");
		animalClasses.put ("goldfinch", "bird");
		animalClasses.put ("starling", "bird");

		System.out.println ("\nThe complete map and its size");
		System.out.println (animalClasses);
		
		System.out.println (animalClasses.size ());

		String v = animalClasses.put ("dog", "canine");
		System.out.println (v);
		System.out.println ("Updated map");
		System.out.println (animalClasses);

		String matchingValue = animalClasses.get ("goldfinch");
		System.out.println (matchingValue);

		matchingValue = animalClasses.get ("monkey");
		System.out.println (matchingValue);

		Set <String> setOfKeys = animalClasses.keySet (); // VIEW PORTION
		//	Set<String> setOfKeys2 = new HashSet<String>(animalClasses.keySet()); <- to create a new one unaffected by original
		System.out.println (setOfKeys);

		Iterator <String> keyIter = setOfKeys.iterator ();
		while (keyIter.hasNext ())
		{
			String key = keyIter.next ();
			String value =  animalClasses.get (key);
			System.out.println ("A " + key + " is a " + value);
		}

		matchingValue =  animalClasses.remove ("horse");
		System.out.println (matchingValue);
		System.out.println (animalClasses);



		System.out.println (animalClasses.containsKey ("robin"));
		System.out.println (animalClasses.containsKey ("bird"));
		System.out.println (animalClasses.containsValue ("robin"));
		System.out.println (animalClasses.containsValue ("bird"));


		Collection <String> collectionOfValues = animalClasses.values ();
		//	Collection <String> collectionOfValues2 = new HashSet<String> (animalClasses.values ()); <- if we actually want the keys to be without duplicates
		// HashSet has no methods
		System.out.println (collectionOfValues);

		System.out.println ("\nEnd of Map Demo");
	} // main method
} // MapDemo class

package Unit3;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListDemo
{
   public static void main (String[] args)
   {
      System.out.println ("LinkedList Demo");

      LinkedList <String >myList = new LinkedList <String>();

      myList.addLast ("pear");
      myList.addLast ("kiwi");
      myList.addFirst ("banana");
      System.out.println (myList);

      myList.addFirst ("apple");
      myList.addFirst ("strawberry");
      System.out.println (myList);
      myList.addLast ("raspberry");
      myList.addFirst ("peach");
      System.out.println (myList);

      String firstFruit = myList.getFirst ();
      System.out.println ("\nFirst: " + firstFruit);
      String lastFruit = myList.getLast ();
      System.out.println ("Last: " + lastFruit);

      String removedFruit = myList.removeFirst ();
      System.out.println ("\nRemoved First: " + removedFruit);
      System.out.println (myList);

      removedFruit = myList.removeLast ();
      System.out.println ("Removed Last: " + removedFruit);
      System.out.println (myList);

      for (int rotate = 1 ; rotate <= 3 ; rotate++)
          myList.addFirst (myList.removeLast ());
      System.out.println (myList);

      ListIterator <String> iter = myList.listIterator ();
      while (iter.hasNext ())
          System.out.println (iter.next ());

      iter = myList.listIterator (myList.size ());
      while (iter.hasPrevious ())
          System.out.println (iter.previous ());
      
      iter = myList.listIterator ();
      while (iter.hasNext ())
      {
          String nextFruit = iter.next ();
          if (nextFruit.length () < 5)
              iter.remove ();
      }
      System.out.println (myList);

      

      iter = myList.listIterator ();
      while (iter.hasNext ())
      {
          String nextFruit = iter.next ();
          nextFruit = nextFruit.toUpperCase ();
      }
      System.out.println (myList);

    } // main method
}

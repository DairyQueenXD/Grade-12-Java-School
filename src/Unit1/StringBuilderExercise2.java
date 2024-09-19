package Unit1;
import java.util.*;
public class StringBuilderExercise2 {

	public static void main(String[] args) {
		// Using the StringBuilder class, write a program that takes a word and it allows the user to shift the letters n number of times, 
		//in a specified direction.  (0 = left, 1 = right).
		
		Scanner in = new Scanner (System.in);
		System.out.print("Enter a word: ");
		String word = in.nextLine();
		System.out.print("Enter a number of times for the letters to be shifted; ");
		int n = Integer.parseInt(in.nextLine());
		System.out.print("Enter a direction (0 for left, 1 for right): ");
		int dir = Integer.parseInt(in.nextLine());
		
		StringBuilder sb = new StringBuilder(word);
		sb.ensureCapacity(word.length()*2+5);
		n %= word.length();
		if (dir == 0) {
			sb.append(sb.substring(0,n));
			sb.delete(0, n);
			
		} else {
			sb.append(sb.substring(0,word.length()-n));
			sb.delete(0, word.length()-n);
		}
		System.out.println(sb);
		
		in.close();
	}

}

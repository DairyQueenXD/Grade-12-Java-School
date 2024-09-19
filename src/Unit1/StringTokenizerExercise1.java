package Unit1;
import java.util.*;
public class StringTokenizerExercise1 {

	public static void main(String[] args) {
		// Write a program that finds the longest word in a sentence.  
		// (Keep in mind that punctuation should not be included as part of a word.)
		Scanner in = new Scanner (System.in);
		String s = in.nextLine();
		StringTokenizer st = new StringTokenizer(s, " ,.!?:;'");
		int mx = 0;
		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			if (word.length() > mx) mx = word.length();
		}
		System.out.println(mx);
		in.close();
	}

}

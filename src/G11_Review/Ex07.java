package G11_Review;

import java.util.*;

public class Ex07 {

	public static void main(String[] args) {
		/*
		Write a method to determine if a string has all unique characters. You can assume that the string is
		an ASCII string, meaning there are at most 128 possible characters in the string (unicodes from 0 to 127).
		 */
				Scanner in = new Scanner(System.in);
				
				System.out.print("Enter a string: ");
				String s = in.nextLine();
				in.close();
				int[] characters = new int[128];
				for (int i = 0; i < s.length(); ++i) {
					characters[s.charAt(i)]++;
					if (characters[s.charAt(i)] > 1) {
						System.out.println("The string does NOT have all unique characters."); return;
					}
				}
				System.out.println("The string has all unique characters.");
	}
}

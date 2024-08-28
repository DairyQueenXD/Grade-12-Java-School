package G11_Review;

import java.util.*;

public class Ex04 {

	public static void main(String[] args) {
//		Write a program that checks if a word is a palindrome. A palindrome is a word that is the same
//		forward and backward. (e.g. radar, Mom, MADam are all palindromes.)
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String s = in.nextLine().toLowerCase(); boolean check = true;
		for (int i = 0; i < s.length()/2; ++i) {
			if (s.charAt(i) != s.charAt(s.length()-i-1)) {
				check = false;
			}
		}
		if(check) System.out.println("Your string is a palindrome.");
		else System.out.println("Your string is NOT a palindrome.");
		in.close();
	}
}

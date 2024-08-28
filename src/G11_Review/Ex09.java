package G11_Review;

import java.util.*;

public class Ex09 {
	
	
	
	public static void commonLetters(String str1, String str2,int[]chara1,int[]chara2) {
		for (int i = 0; i < str1.length(); ++i) {
			chara1[str1.charAt(i)]++;
		}
		for (int i = 0; i < str2.length(); ++i) chara2[str2.charAt(i)]++;
		for (int i = 0; i < 128; ++i) {
			if (((65<=i && i<=90) || (97 <= i && i <= 122)) && chara1[i] > 0 && chara2[i] > 0) System.out.print((char)i);
		}
	}
	
	public static void main(String[] args) {
/*
9. Write a method called commonLetters that finds and displays all the letters ('a' to 'z') that
are common to two strings. (ie. It takes in two parameters.)
 These two strings will be read in by the user, and at the end, the program will display the
string that is made up of all of letters common to both strings.
 The string of common letters should be in lower case and should not contain any
duplicate letters.
 If the two strings have no letters in common, you should return an empty string.
 Your program should ignore case (i.e. 'A' and 'a' should be considered the same letter).
 */
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter first string: ");
		String s1 = in.nextLine().toLowerCase();
		System.out.print("Enter second string: ");
		String s2 = in.nextLine().toLowerCase();
		int[] chara1 = new int[128]; int[] chara2 = new int[128];
		System.out.print("The common letters are: ");
		commonLetters(s1,s2,chara1,chara2);
		in.close();
	}
}

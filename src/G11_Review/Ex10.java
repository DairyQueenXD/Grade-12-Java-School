package G11_Review;

import java.util.*;

public class Ex10 {
	
	
	
	public static boolean commonLetters(String str1, String str2,int[]chara1,int[]chara2) {
		for (int i = 0; i < str1.length(); ++i) {
			chara1[str1.charAt(i)]++;
		}
		for (int i = 0; i < str2.length(); ++i) chara2[str2.charAt(i)]++;
		for (int i = 0; i < 128; ++i) {
			if (((65<=i && i<=90) || (97 <= i && i <= 122)) && (chara1[i] > 0 && chara2[i] == chara1[i])) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
/*
10. Given two strings, write a method that checks if one is a permutation / rearrangement of the other
string’s letters.
(ie. SIGN and SING, BRAGS and GRABS)
 */
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter first string: ");
		String s1 = in.nextLine().toLowerCase();
		System.out.print("Enter second string: ");
		String s2 = in.nextLine().toLowerCase();
		int[] chara1 = new int[128]; int[] chara2 = new int[128];
		if (commonLetters(s1,s2,chara1,chara2)) System.out.println("These two words are permutations of each other.");
		else System.out.println("These two words are not permutations of each other.");
		in.close();
	}
}

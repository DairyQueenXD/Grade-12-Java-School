package G11_Review;

import java.util.*;

public class Ex03 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String s = in.nextLine();
		if (s.length()%2==1) {
			for (int i = 0; i <= s.length(); i+=2) {System.out.print(s.charAt(i));}
		} else {
			for (int i = 1; i <= s.length(); i+=2) System.out.print(s.charAt(i));
		}
		in.close();
	}
}

package G11_Review;

import java.util.*;

public class Ex05 {

	public static void main(String[] args) {
//		Write a method that lets you enter in a number and then it will translate it to its spoken form from
// right to left. (ie. 32578 = eight seven five two three)
		Scanner in = new Scanner(System.in);
		
		int num = in.nextInt();
		String[] names = {"one", "two", "three","four","five","six","seven","eight","nine","ten"};
		while (num >=1) {
			int digit = num - num/10*10;
			System.out.print(names[digit-1] + " ");
			num/=10;
		}
		in.close();
	}
}

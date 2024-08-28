package G11_Review;

import java.util.*;

public class Ex02a {

	public static void main(String[] args) {
//		Write a program that prints all the letters in the ODD positions of a string if it has an odd number of
//		letters. Otherwise, print all the letters in the EVEN positions of the string.
		
		Scanner in = new Scanner(System.in);
		double sum = 0;
		for (int i = 1; i <=10; ++i) {
			System.out.print("Enter number " + i + ": ");
			sum+=in.nextDouble();
		}
		System.out.printf("The average is %.2f" , sum/10);
		in.close();
	}
}

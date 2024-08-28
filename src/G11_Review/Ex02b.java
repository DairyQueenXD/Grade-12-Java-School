package G11_Review;

import java.util.*;

public class Ex02b {

	public static void main(String[] args) {
//		Write a program that finds the average between 10 numbers that are entered by the user.
//		Round the result to two decimal places.
//		b) Adding to the above program, find and display the highest AND lowest of the 10 numbers entered.
		Scanner in = new Scanner(System.in);
		double sum = 0; double mx = 0, mn = 0;
		for (int i = 1; i <=10; ++i) {
			System.out.print("Enter number " + i + ": ");
			double input = in.nextDouble();
			sum+=input;
			if (i == 1) {
				mx = input; mn = input;
			} else {
				mx = Math.max(mx, input);
				mn = Math.min(mn, input);
			}
		}
		System.out.printf("The average is %.2f" , sum/10);
		System.out.print("\nMax: " + mx + "\tMin: " + mn);
		in.close();
	}
}

package Unit3_Assignment5;

import java.util.*;

// Name: Dequan Kong
// Date: December 6, 2024
// Description: A program that finds the number of all reduced fractions between 0 and 1 (inclusive) with denominators <= N.
// The user can input a lower and upper limit, and the program will output the total number of fractions and the number of fractions within the range.

public class Problem2 {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int n = -1;
		
		// Read N from console
		while (!(n >= 1 && n <= 1000)) {
			try { 
				System.out.print("Enter the maximum denominator: ");
				n = Integer.parseInt(in.nextLine());
				if (!(n >= 1 && n <= 1000)) throw new NumberFormatException();
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// This TreeSet uses the compareTo method in the Fraction class
		TreeSet<Fraction> allFractions = new TreeSet<Fraction>();

		// Add all fractions to TreeSet
		// The compareTo method in the Fraction class will detect fractions that are not reduced
		for (int denom = 1; denom <= n; ++denom) {
			for (int num = 0; num <= denom; ++num) {
				allFractions.add(new Fraction(num, denom));
			}
		}

		String input = "";
		boolean check = true;

		Fraction lower = null, upper = null;
		// Read lower limit from console
		while (check) {
			try {
				System.out.print("Enter the lower limit: ");
				input = in.nextLine();

				int index = input.indexOf("/");
				if ( index == -1) throw new NumberFormatException();

				int num = Integer.parseInt(input.substring(0,index)), denom = Integer.parseInt(input.substring(index+1));
				lower = new Fraction(num, denom);

				// Lower limit: invalid if smaller than 0 or larger than 1 or denominator is 0
				if ((num < 0 && denom > 0) || (num > 0 && denom < 0 ) || denom == 0 || Math.abs(num) > Math.abs(denom)) throw new NumberFormatException();
				check = false;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please try again.");
				check = true;
			}
		}

		// Read upper limit from console
		check = true;
		while (check) {
			try {
				System.out.print("Enter the upper limit: ");
				input = in.nextLine();

				int index = input.indexOf("/");
				if (index == -1) throw new NumberFormatException();

				int num = Integer.parseInt(input.substring(0,index)), denom = Integer.parseInt(input.substring(index+1));
				upper = new Fraction(num, denom);

				// Upper limit: invalid if smaller than 0 or larger than 1 or upper is smaller than lower or denominator is 0
				if ((num < 0 && denom > 0) || (num > 0 && denom < 0 ) || denom == 0 || Math.abs(num) > Math.abs(denom) || 
						upper.compareTo(lower) < 0) throw new NumberFormatException();
				check = false;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please try again.");
				check = true;
			}
		}

		// Output the total # of fractions
		System.out.println("Total number of fractions: " + allFractions.size());
		
		// Use a subset to determine the # of fractions between lower and upper limit
		System.out.println("Number of Fractions between " + lower + " and " + upper + " inclusive: " + 
				allFractions.subSet(lower, true,upper,true).size());

		in.close();
	}
}

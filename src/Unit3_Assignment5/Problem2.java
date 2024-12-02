package Unit3_Assignment5;

import java.util.*;

public class Problem2 {

	public static void invalid() {
		System.out.println("Invalid input. Please try again.");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int n = -1;
		while (!(n >= 1 && n <= 1000)) {
			try { 
				System.out.print("Enter the maximum denominator: ");
				n = Integer.parseInt(in.nextLine());
				if (!(n >= 1 && n <= 1000)) throw new NumberFormatException();
			} catch (NumberFormatException e) {
				invalid();
			}
		}

		TreeSet<Fraction> allFractions = new TreeSet<Fraction>();

		for (int denom = 1; denom <= n; ++denom) {
			for (int num = 0; num <= denom; ++num) {
				allFractions.add(new Fraction(num, denom));
			}
		}

		String input = "";
		boolean check = true;

		Fraction lower = null, upper = null;
		// input lower limit
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
				invalid();
				check = true;
			}
		}

		// input upper limit
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
				invalid();
				check = true;
			}
		}

		System.out.println("Total number of fractions: " + allFractions.size());
		System.out.println("Number of Fractions between " + lower + " and " + upper + " inclusive: " + 
				allFractions.subSet(lower, true,upper,true).size());

		in.close();

	}
}

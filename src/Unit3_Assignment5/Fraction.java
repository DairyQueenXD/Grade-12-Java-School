package Unit3_Assignment5;

public class Fraction implements Comparable<Fraction>{

	private int numerator, denominator;

	/*
	 * Description: Constructor method that initializes all Fraction Objects
	 * Parameters: int num: numerator; int denom: denominator
	 * Return: none (Constructor method has no return type)
	 */
	public Fraction(int num, int denom) {

		this.numerator = num; 
		this.denominator = denom;
	}

	/*
	 * Description: compareTo method that determine if the current fraction is less than, equal to, or larger than another fraction
	 * This is automatically called for the TreeSet in Problem2.java
	 * The method used here is to put both fractions on the same denominator by cross-multiplying, then comparing the two numerators.
	 * 
	 * Parameters: Fraction fraction2: the fraction that we are comparing to
	 * Return: an integer representing whether the current fraction is smaller (<0), equal to(0), or larger (>0) than another fraction
	 */
	public int compareTo(Fraction fraction2) {
		if (Math.abs(this.numerator) * 1.0 / Math.abs(this.denominator) == Math.abs(fraction2.numerator) * 1.0 / Math.abs(fraction2.denominator)) return 0; 
		if (Math.abs(this.denominator) == Math.abs(fraction2.denominator)) return Math.abs(this.numerator) - Math.abs(fraction2.numerator);
		return Math.abs(this.numerator) * Math.abs(fraction2.denominator) - Math.abs(fraction2.numerator) * Math.abs(this.denominator);
	}

	/*
	 * Description: method that turns the current fraction into a desirable string
	 * This is automatically called in the final output of the program
	 * 
	 * Parameters: None
	 * Return: A string in the format "num/denom"
	 */
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
}

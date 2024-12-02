package Unit3_Assignment5;

public class Fraction implements Comparable<Fraction>{

	private int numerator, denominator;

	public Fraction(int num, int denom) {

		this.numerator = num; 
		this.denominator = denom;
	}

	public int compareTo(Fraction fraction2) {
		if (Math.abs(this.numerator) * 1.0 / Math.abs(this.denominator) == Math.abs(fraction2.numerator) * 1.0 / Math.abs(fraction2.denominator)) return 0; 
		if (Math.abs(this.denominator) == Math.abs(fraction2.denominator)) return Math.abs(this.numerator) - Math.abs(fraction2.numerator);
		return Math.abs(this.numerator) * Math.abs(fraction2.denominator) - Math.abs(fraction2.numerator) * Math.abs(this.denominator);
	}

	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
}

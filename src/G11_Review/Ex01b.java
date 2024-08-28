package G11_Review;

import java.util.*;

public class Ex01b {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		double a=0, b=0; 
		System.out.print("Enter side 1: ");
		a = in.nextDouble();
		while (a<0) {
			System.out.print("Invalid input! Please enter another number: ");
			a = in.nextDouble();
		}
		System.out.print("Enter side 2: ");
		b = in.nextDouble();
		while (b<0) {
			System.out.print("Invalid input! Please enter another number: ");
			b = in.nextDouble();
		}
		System.out.println("The hypotenuse is: " + Math.sqrt(a*a+b*b));
		in.close();
	}

}

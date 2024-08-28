package G11_Review;

import java.util.*;

public class Ex01a {
    
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double a, b; 
		a = in.nextDouble(); b = in.nextDouble();
		System.out.println(Math.sqrt(a*a+b*b));
		in.close();
	}
}

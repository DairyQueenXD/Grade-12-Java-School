package Unit1;
import java.util.*;

public class StringTokenizerExercise2 {

	public static void main(String[] args) {
		// Write a program that reads in a mathematical expression (of any length).  
		// The expression only consists of number and these operations:  +, -, +, /, % (no brackets).  Evaluate the expression.
		
		// Note: For multiply please input "x" and not "*" !!
		Scanner in = new Scanner (System.in);
		String s = in.nextLine();
		StringTokenizer st = new StringTokenizer(s,"+-x/%", true);
		
		String[] arr = new String[st.countTokens()]; int len = st.countTokens();
		for (int i = 0; i < st.countTokens()+i; ++i) {
			arr[i] = st.nextToken();
			if (arr[i].equals("x") || arr[i].equals("%") || arr[i].equals("/")) len -= 2;
		}
		String[] newArr = new String[len];
		
		int index = 0;
		for (int i = 0; i < arr.length; ++i) {
			if (i == arr.length - 1) {
				if (arr[i-1].equals("x") || arr[i-1].equals("%") || arr[i-1].equals("/")) {
					continue;
				} else {
					newArr[index] = arr[i]; continue;
				}
			}
			if ((arr[i+1].equals("x") || arr[i+1].equals("%") || arr[i+1].equals("/"))) {
				continue;
			}
			
			if (arr[i].equals("x") || arr[i].equals("%") || arr[i].equals("/")) {
				if (arr[i].equals("x")) {
					newArr[index] = ""+ Double.parseDouble(arr[i-1]) * Double.parseDouble(arr[i+1]);
				}
				if (arr[i].equals("/")) {
					newArr[index] = ""+ Double.parseDouble(arr[i-1]) / Double.parseDouble(arr[i+1]);
				}
				if (arr[i].equals("%")) {
					newArr[index] = ""+ Double.parseDouble(arr[i-1]) % Double.parseDouble(arr[i+1]);
				}
				i++; index++;
			} else {
				newArr[index] = arr[i]; index++;
			}
		}
//		for (int i = 0; i < newArr.length; ++i) System.out.println(newArr[i]);
		double ans = 0; int multiplier = 1; boolean num = true;
		for (int i = 0; i < newArr.length; ++i) {
			if (num) { ans += Double.parseDouble(newArr[i]) * multiplier; num = false;}
			else {
				if (newArr[i].equals("+")) multiplier = 1;
				if (newArr[i].equals("-")) multiplier = -1;
				num = true;
			}
		}
		System.out.println(ans);
		in.close();
	}

}

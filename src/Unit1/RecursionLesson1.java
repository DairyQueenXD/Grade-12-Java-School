package Unit1;

public class RecursionLesson1 {

	public static int fact (int n) {
		if (n == 1 || n == 0) return 1;
		return (fact (n-1)*n);
	}
	
	public static String reverseStr(String s) {
		if (s.length() <= 1) return s;
		return (s.charAt(s.length()-1) + "*" + reverseStr(s.substring(0,s.length()-1)));
	}
	// StackOverflowError
	public static void main(String[] args) {
		System.out.println(fact(0));
		System.out.println(reverseStr("ab"));
	}

}

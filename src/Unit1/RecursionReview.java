package Unit1;


public class RecursionReview {

	
	public static int convertToBase10 (String num, int digit) {
		if (digit == num.length()) return 0;
		if (num.charAt(num.length() - 1 - digit) == '1') return  (int) (Math.pow(2, digit)) + convertToBase10(num,digit+1);
		return convertToBase10(num, digit+1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try {
//			
//		} catch (NumberFormatException) {
//			
//		}
		System.out.println( convertToBase10 ("101", 0));
		System.out.println( convertToBase10 ("110111101", 0)) ;
	}

}

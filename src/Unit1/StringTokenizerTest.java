package Unit1;
import java.util.*;
public class StringTokenizerTest {

	public static void main(String[] args) {

		String s  = "I am 10 years old";
		StringTokenizer st = new StringTokenizer(s);
		
		// While loop
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());	
		}
		
		s  = "I am 10 years old";
		st = new StringTokenizer(s);
		// For loop (way 1)
		for (int i = 0; i < st.countTokens()+i; ++i) {
			System.out.println(st.nextToken());
		}
		s  = "I am 10 years old";
		st = new StringTokenizer(s);  int length = st.countTokens();
		// For loop (way 2 - store in a variable)
		for (int i = 0; i < length; ++i) {
			System.out.println(st.nextToken());
		}
		s  = "I am 10 years old";
		st = new StringTokenizer(s); 
		// For loop (way 3 - reverse)
		for (int i = st.countTokens(); i >1 ; --i) {
			System.out.println(st.nextToken());
		}
	}

}

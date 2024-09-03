package G11_Review;
import java.util.*;

public class stringalgo1 {
    
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
        System.out.print("Enter a sentence: ");
        String s = in.nextLine().trim().toLowerCase();
        System.out.print("Enter a word to be deleted: ");
        String w = in.nextLine().trim().toLowerCase();
        System.out.print("Enter a word to replace the deleted word with: ");
        String replace = in.nextLine().trim().toLowerCase();
        
        int start = 0, delete = s.indexOf(w);
        while (delete != -1) {
        	s = s.substring(0,delete)+replace+s.substring(delete+w.length());
        	start = delete + replace.length();
        	delete = s.indexOf(w,start);
        }

        // Output the updated sentence
        System.out.println(s);

		in.close();
	}
}
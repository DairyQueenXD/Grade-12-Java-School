import java.util.*;

public class stringalgo {
    
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
        System.out.print("Enter a sentence: ");
        String s = in.nextLine().toLowerCase();
        System.out.print("Enter a word to be deleted: ");
        String w = in.nextLine().toLowerCase();
        String cont = ""; int ind = 0, begin = 0;
        String s2 = s;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == w.charAt(ind)) {
                ind++; cont+=c; begin = i;
            }
            if (ind == w.length()) {
                cont = ""; ind = 0; begin = 0;
                s2 = s2.substring(0,begin) + s2.substring(begin+w.length()-1);
            }
        }
        System.out.println(s2);
		in.close();
	}
}
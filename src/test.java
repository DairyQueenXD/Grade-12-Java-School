
import java.util.*; import java.math.*;
import java.io.*;
public class test {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static int test(int n) {
		System.out.println("abc");
		if (n > 100) return test(n/100);
		System.out.println("def");
		if (n > 10) return test(n/10);
		return 0;
	}
	public static void main(String[] args) throws NumberFormatException{

		Scanner in = new Scanner (System.in);
//
//		BigInteger b1 = new BigInteger("99999999999999");
//		BigInteger b3 = new BigInteger ("999"); 
//		System.out.println(b1.min(b3));
//		BigInteger b2 = BigInteger.valueOf(2);
//		if (b1.compareTo(b2) >= 0) System.out.println("sdfkdsjfldsk");
		test(1234); in.close();
//		fun2("brawl"); fun3(4321);
	}
	public static void fun3 (int k) {
		System.out.println (k);
		if (k > 0)
			fun3 (k / 10);
		if (k > 9)
			fun3 (k % 10);
	}
	public static void fun2 (String str)
	{
		if (str.length() <= 1) {
			System.out.println (str.toUpperCase());
			return;
		}
		System.out.println(str);
		fun2 (str.substring (0, str.length()-1));
		fun2 (str.substring (2));
	}
	static String next () throws IOException {
		while (st == null || ! st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}  
	static String readLine () throws IOException {
		return br.readLine().trim();
	}

}

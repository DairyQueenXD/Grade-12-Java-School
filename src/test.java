
import java.util.*;
import java.io.*;
public class test {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
//		int n = readInt();
		
		String s = "A R AO+";
		String[] str = s.split(" ");
		for (String s1: str) System.out.println(s1);

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

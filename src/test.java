
import java.util.*;

import Unit2.Movie;

import java.math.*;
import java.io.*;
public class test {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException{

		int arr[] = new int[300];
		int nums[] = {2,3,4,5,6,7,8};
		int counts[] = new int[7];
		for (int i = 0; i < 300; ++i) {
			int x = (int) (Math.random()*4) + 1, y = (int) (Math.random()*4)+1;
			arr[i] = x + y;
			for (int j = 0;j < 7; ++j ) {
				if (nums[j] == arr[i]) { counts[j]++; break;}
			}
		}
		
		ArrayList<Integer> old = new ArrayList<>();
		old.add(5); old.add(7);
		ArrayList<Integer> newArr = new ArrayList<Integer>(old);
		newArr.get(0);
//		newArr.get(1) = 8;
	
//		System.out.println(Double.parseDouble(num));
//		for (int i = 0; i < 7; ++i) System.out.println(counts[i]);
		// for (Movie mov: moviesSortedByGenre) System.out.println(mov);
		
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

package Unit3_Assignment5;

import java.io.*;
import java.util.*;

// Name: Dequan Kong
// Date: December 6, 2024
// Description: A program that finds the number of distinct substrings in a string (including the empty substring)

public class Problem1 {

	public static void main(String[] args) throws IOException {
		
		// Scanners
		Scanner in = new Scanner(System.in);
		BufferedReader inFile = null; String filename; boolean check = true;
		
		// Read file name from console
		while (check) {
			try {
				System.out.print("Enter file name (without .txt): ");
				filename = in.nextLine();
				inFile = new BufferedReader (new FileReader(filename + ".txt"));
				check = false;
			} catch (FileNotFoundException e) {
				System.out.println("File not found! Please try again.");
				check = true;
			}
		}
		
		// Read the number of strings from file
		int n = Integer.parseInt(inFile.readLine());	
		System.out.println("Finding the number of Substrings\n");
		
		for (int i = 0; i < n; ++i) {
			// For each string, create a HashSet to store all substrings
			// HashSet elements are all distinct, so we just need to print the size of the HashSet at the end
			Set <String> allSubstrings = new HashSet <String> ();
			
			// read string
			String str = inFile.readLine(); 
			System.out.println("String: " + str);
			
			allSubstrings.add(""); // add empty substring
			// if string is not empty string, loop through all substrings (otherwise we do nothing since we already added the empty substring)
			if (str.length() != 0) { 
				for (int first = 0; first < str.length(); ++first) {
					for (int last = first+1; last <= str.length(); ++last) {
						allSubstrings.add(str.substring(first, last));
					}
				}
			}
			System.out.println("No. of Substrings: " + allSubstrings.size() + "\n");
			
		}
		in.close();
		inFile.close();
	}

}

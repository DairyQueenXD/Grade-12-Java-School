package Unit1;
import java.io.*; import java.util.*;

/* Name: Dequan Kong
Date: September 13, 2024
Description: Program that finds the largest palindromic substring.
The program prints the largest palindrome's starting position, its length, and the string itself.

--- Note --- Please remove the first line of this program - the package is for organization on my device.
 */
public class Assignment1C {

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner (System.in); boolean check = true; BufferedReader inFile = null;

		// Ask for the file to read from
		do {
			try {
				System.out.print("Enter the file name you wish to read input from (without .txt): ");
				String file = in.nextLine().trim();
				inFile = new BufferedReader (new FileReader (file + ".txt"));
				check = true;
			} catch (FileNotFoundException e) {
				System.out.println("File not found! Please try again.");
				check = false;
			}

		} while (!check);

		// Variables initialization
		String line, maxStr, odd, even; int startPosOdd = 0, startPosEven = 0, startPos = 0;
		System.out.println();
		try {
			while ((line = inFile.readLine()) != null) { // Read each line
				// If the line of input is only 1 character, the largest and only palindrome is the character itself.
				if (line.length() <= 1) { maxStr = line;} 
				else {
					maxStr = line.substring(0,1);
					// The idea is to look for palindromes CENTERED at position i
					for (int i = 0; i < line.length() - 1; ++i) {
						// Look for the largest odd length palindrome centered at i
						int left = i, right = i;
						// The approach is to expand from center i
						while (left >= 0 && right < line.length() && (line.charAt(left)+"").equalsIgnoreCase(line.charAt(right)+"")) {
							left --; right ++;
						}
						// Update variables
						odd = line.substring(left+1,right);
						startPosOdd = left+2;

						// We do the same for the largest even length palindrome centered at i
						left = i; right = i+1;
						while (left >= 0 && right < line.length() && (line.charAt(left)+"").equalsIgnoreCase(line.charAt(right)+"")) {
							left --; right ++;
						}
						even = line.substring(left+1, right);
						startPosEven = left+2;

						// Determine the palindrome with longest length centered at i
						if (odd.length() > maxStr.length()) {
							maxStr = odd;
							startPos = startPosOdd;
						}
						if (even.length() > maxStr.length()) {
							maxStr = even;
							startPos = startPosEven;
						}
					}
				}
				// Output
				System.out.println("    Finding the largest palindrome\n    Largest palindrome: " + maxStr + "\n    Starting position: "
						+ startPos + "\n    Length: " + maxStr.length() + "\n");
			}
		} catch (NullPointerException e) { 
			System.out.println("Error. Please run the program again.");
		}
		System.out.println("Program is complete");

		in.close();
		inFile.close();
	}

}

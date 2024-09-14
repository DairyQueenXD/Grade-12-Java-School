package Unit1;
import java.io.*; import java.util.*;

/*
 Name: Dequan Kong
 Date: September 13, 2024
 Description: Program that translates a string with 2-digit ASCII encoded characters
 into the original string
 
 --- Note --- Please remove the first line of this program - the package is for organization on my device.
 */

public class Assignment1B {

	public static void main(String[] args) throws IOException {
		
		// Variables
		Scanner in = new Scanner (System.in); boolean check = true; BufferedReader inFile = null;
		
		// Ask the user for a valid file name to read input from
		do {
			try {
				System.out.print("Enter the file name you wish to read input from (without .txt): ");
				String file = in.nextLine();
				inFile = new BufferedReader (new FileReader (file + ".txt"));
				check = true;
			} catch (FileNotFoundException e) {
				System.out.println("File not found! Please try again.");
				check = false;
			}

		} while (!check);

		String line;
		while ((line = inFile.readLine()) != null) { // Read each line of input file, until no lines are left
			String output = line; int index = -2;
			
			while (output.indexOf("%", index) != -1) {
				index = output.indexOf("%",index); 
				
				try { // When the two characters after a '%' is valid (ex. %42, %57)
					output = output.substring(0,index) + (char) Integer.parseInt(output.substring(index+1, index+3), 16) + 
							output.substring(index+3);
				} catch (NumberFormatException e) { 
					// When there are two consecutive %, we increase index by 1
					// so that we continue searching with the second %
					index++;
				}
			}
			System.out.println(output);
		}
		
		// Close Scanner / BufferedReader
		in.close();
		inFile.close();
	}

}

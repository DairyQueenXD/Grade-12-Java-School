package Unit1;


import java.util.*;
import java.io.*;
public class ReadFileScanner {

	public static void main(String[] args) {
		try {
			Scanner inFile = new Scanner (new File ("highScore.txt"));

			while (inFile.hasNextLine()) {
				String line = inFile.nextLine();
				System.out.println(line);
				
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("orz");
		}



	}

}

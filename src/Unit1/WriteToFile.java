package Unit1;
import java.io.*;
public class WriteToFile {

	public static void main(String[] args) {
		try {
			PrintWriter outFile = new PrintWriter (new FileWriter ("output.txt", true));
			// By default PrintWriter overwrites the file
			// To make it so that it doesn't overwrite, add a boolean with a true value.
			outFile.println("sdfdsfd");

			outFile.close();
			
		} catch (IOException e) {

		}
	}

}

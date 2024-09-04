package Unit1;
import java.io.*;

public class ReadFileBufferedReader {

	public static void main (String[] args) {
		try {
			BufferedReader inFile = new BufferedReader (new FileReader ("highScore.txt"));
			String line = "";
			while (line != null) {
				line = inFile.readLine();
				System.out.println(line);
			}
			
			inFile.close();
			
		} catch (IOException e) { // IOException includes FileNotFoundException
			// Note that if you put FileNotFoundException after an IOException
			// the FileNotFoundException will not be reachable as IOException already handles it.
		}
	}
}

package Unit1;
import java.io.*;

public class StringBuilderExercise1 {

	public static void main(String[] args) throws IOException {
		BufferedReader inFile = new BufferedReader (new FileReader ("input.txt"));
		String line; StringBuilder sb = new StringBuilder();
		while ((line = inFile.readLine()) != null) {
			String[] arr = line.split(" ");
			sb.ensureCapacity(line.length());
			for (int i = 0; i < arr.length; ++i) {
				sb.append((arr[i].charAt(0)+"").toUpperCase());
			}
		}
		System.out.println(sb);
		inFile.close();
	}

}

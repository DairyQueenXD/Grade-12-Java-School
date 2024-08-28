package G11_Review;

import java.util.*;

public class Ex08b {

	public static String decryptMessage(String original, int position) {
		String newString = "";
		for (int i = 0; i < original.length(); ++i) {
			if (65 <= original.charAt(i) && original.charAt(i) <= 90) {
				if (original.charAt(i)-position < 65) newString += (char) (original.charAt(i)-position+26);
				else newString += (char) (original.charAt(i)-position);

			} else if (97 <= original.charAt(i) && original.charAt(i) <= 122){
				if (original.charAt(i)-position < 97) newString += (char) (original.charAt(i)-position+26);
				else newString += (char) (original.charAt(i)-position);
			} else newString += original.charAt(i);
		}
		return newString;
	}
	
	public static void main(String[] args) {
/*
One way to “encrypt” a message is to shift each letter in the message a fixed number of positions.
For example, given “GOOD LUCK” if we shift each character 3 positions we would get “JRRG OXFN”.
In this case, the non-letters (spaces etc.) were not shifted. This is known as a Caesar shift since it
was used by Julius Caesar to send messages to his generals. To “decrypt” this message we just shift
the encrypted message 3 positions back.
a) Write a method called encryptMessage that will shift each letter in a String a given
number of positions. (ie. You will need two parameters.)
b) Write a similar method called decryptMessage that “unshifts” each letter in a String a
given number of positions.
 */
		
		// A to Z: 65 to 90 | a to z: 97 to 122
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter a string: ");
		String s = in.nextLine();
		System.out.print("Enter the number of positions to shift: ");
		int pos = in.nextInt();
		pos%=26;

		System.out.println("Your decrypted message is: " + decryptMessage(s,pos));
		in.close();
	}
}

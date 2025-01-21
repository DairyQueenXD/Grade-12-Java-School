package Unit1;
import java.util.*;

// Please remove the first line of the program !! It's for organization on my device.
public class RecursionExercises {

	public static void main(String[] args) {

		int num = 0;
		Scanner in = new Scanner (System.in);
		while (num != -1) {
			System.out.print("Choose an exercise to run (Enter -1 to quit): ");
			num = Integer.parseInt(in.nextLine());

			if (num == -1) {in.close(); return; }
			if (num == 1) {
				int customers = 0;
				do {
					System.out.print("Enter the number of customers: ");
					customers = Integer.parseInt(in.nextLine());
					if (customers < 0 ) System.out.print("Invalid! ");
				} while (customers < 0);
				System.out.println(bubbles(customers) + " scoops of bubbles were given out that day.");

			} else if (num == 2) {			
				System.out.print("Enter an integer: ");
				int rec = Integer.parseInt(in.nextLine());
				System.out.println("The answer is " + sumDiff(rec) + ".");

			} else if (num == 3) {
				System.out.print("Enter integer 1: ");
				int num1 = Integer.parseInt(in.nextLine());

				System.out.print("Enter integer 2: ");
				int num2 = Integer.parseInt(in.nextLine());
				System.out.println("The product is " + multiply (num1,num2) + ".");

			} else if (num == 4) {
				System.out.print("Enter a string: ");
				String str = in.nextLine();
				System.out.println("There are " + find(str) + " capital letters and non-alphabetial letters.");

			} else if (num == 5) {
				System.out.print("Enter a string: ");
				String str = in.nextLine();

				System.out.print("Enter a character: ");
				char c = in.nextLine().charAt(0);
				System.out.println("Your final word is " + insert(str,c));
			} else if (num == 6) {
				System.out.print("Enter an integer: ");
				int reformat = Integer.parseInt(in.nextLine());
				System.out.println("Your reformated integer is " + commas(reformat) + ".");
			} else if (num == 7) {
				String[] arr = {}; // Please change array here!
				System.out.println("The average of all numbers is " + Math.round(calculateAverage(arr,0,0,0) * 100) / 100.0 + ".");
			}
			System.out.println();

		}
		in.close();
	}

	public static int bubbles(int customers) {
		if (customers == 0) return 0;
		int add = 0;
		if (customers % 10 == 0) add+=3;
		else {
			if (customers % 2 == 0) add+=1;
			if (customers % 2 == 1) add += 2;
		}

		customers--;
		return add + bubbles(customers);
	}
	
	public static double sumDiff (int num) {
		if (num == 0 || num == 1 || num == -1) return num;
		if (num > 0) {
			if (num % 2 == 1) return  Math.round((1.0/num + sumDiff(num-2))*100000) / 100000.0;
			else return Math.round((1.0/-num + sumDiff(num-2))*100000) / 100000.0;
		} else {
			if (num % 2 == -1) return Math.round((1.0/num + sumDiff(num+2))*100000) / 100000.0;
			else return Math.round((1.0/-num + sumDiff(num+2))*100000) / 100000.0;
		}
	}

	public static int multiply (int n1, int n2) {
		if (n2 == 0 || n1 == 0) return 0;
		if (n2 < 0 && n1 > 0) return n2 + multiply(n2, n1-1);
		if (n2 < 0 && n1 < 0) return multiply(-n1,-n2);
		return n1 + multiply(n1, n2-1);
	}

	public static int find(String str) {
		if (str.length() == 0) return 0;
		if (!(97 <= str.charAt(0) && str.charAt(0) <= 122)) return 1 + find(str.substring(1));
		return find(str.substring(1));
	}

	public static String insert(String str, char c) {
		if (str.length() == 0) return "";
		if (str.length() == 1) {
			if (Character.isLetter(str.charAt(0))) return str;
			return "";
		}
		if (!Character.isLetter(str.charAt(0))) return insert(str.substring(1),c);	
		if (!Character.isLetter(str.charAt(1))) return insert(str.charAt(0) + str.substring(2),c);
		if (("" + str.charAt(0)).toLowerCase().equals (("" + str.charAt(1)).toLowerCase())) 
			return "" + str.charAt(0) + c + insert(str.substring(1), c);
		return "" + str.charAt(0) + insert(str.substring(1),c);
	}

	public static String commas(int num) {
		String str = num+"";
		if (num > 0) {
			if (str.length() <= 3) return "+" + str;
			return commas(Integer.parseInt(str.substring(0,str.length()-3))) + "," + str.substring(str.length()-3);
		}
		if (num < 0) {
			if (str.length() <= 4) return str;
			return commas(Integer.parseInt(str.substring(0,str.length()-3))) + "," + str.substring(str.length()-3);
		}
		return "0";

	}

	public static double calculateAverage(String[] array, int index, double sum, int count) {
		if (index == array.length) {
			if (count == 0) return 0;
			return sum / count;
		}

		try {
			double number = Double.parseDouble(array[index]);
			sum += number;  
			count++;        
		} catch (NumberFormatException e) {
			
		}
		return calculateAverage(array, index + 1, sum, count);
	}
}

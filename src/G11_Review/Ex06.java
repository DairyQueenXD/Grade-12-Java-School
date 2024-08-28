package G11_Review;

public class Ex06 {

	public static int[] insertElement(int[] original, int num) {
		int N = original.length;
		int[] newArray = new int[original.length+1]; int index = 0;
		for (int i = 0; i < N; ++i) {
			if (original[i] >= num) {newArray[i] = num;index = i+1; break;}
			else newArray[i] = original[i];
		}
		if (index == 0) newArray[N] = num;
		else { 
			for (int i = index; i < N+1; ++i) {
				newArray[i] = original[i-1];
			}
		}

		return newArray;
	}
	public static void main(String[] args) {
		/*
Write a Java method called insertElement() that inserts an integer number into a sorted
array of integers in the correct position. The method will have two parameters, the original sorted
array and the number you wish to insert. The method should return the new array (with the
number inserted in the correct position).
For example:
int [] originalNumbers = {10, 20, 30, 40, 50, 60, 70};
int [] newNumbers = insertElement (originalNumbers, 45);
 This would set newNumbers to {10, 20, 30, 40, 45, 50, 60, 70}
		 */
		int [] originalNumbers = {10, 20, 30, 40, 50, 60, 70};
		int [] newNumbers = insertElement (originalNumbers, 1);
		for (int i = 0; i < newNumbers.length; ++i) {
			System.out.print(newNumbers[i] + " ");
		}
	}
}

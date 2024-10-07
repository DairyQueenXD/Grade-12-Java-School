package Unit2;

public class ArrayCopyTest {

	public static void main(String[] args) {

		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = new int[10];
		
		System.arraycopy(arr1, 0, arr2, 1, 5); // Original arr, start index of original, new arr, start index of new, length
		for (int i = 1; i < 6; ++i) System.out.println(arr2[i]);
	}

}

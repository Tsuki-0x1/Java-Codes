/**
 * 
 * Quick Sort Algorithm Demonstration in Java
 *
 * ------------------------------------
 * [ Recursive Algorithm ]
 *
 * Algorithm QuickSort(A, n)
 * In: Array A with n values
 * Out: {sort A in increasing order}
 * 
 * if n > 1 then {
 *   
 *   smaller, equal, larger = new array of size n
 *   n_smaller, n_equal, n_larger = 0
 *   pivot = A[0]
 *   
 *   for i=0 to n-1 do {
 *     if A[i] == pivot then equal[n_equal++] = A[i]
 *     else if A[i] < pivot then smaller[n_smaller++] = A[i]
 *     else larger[n_l++] = A[i]
 *   }
 *   
 *   quickSort(smaller, n_smaller)
 *   quickSort(larger, n_larger)
 *   
 *   i=0
 *   for j=0 to n_smaller do A[i++] = smaller[j]
 *   for j=0 to n_equal do A[i++] = equal[j]
 *   for j=0 to n_larger do A[i++] = larger[j]
 *   
 * }
 *
 * ------------------------------------
 * Order of Time Complexity:
 *
 * Best:		O(n log2(n))
 * Worst:	  O(n^2)
 * Average:	O(n log2(n))
 *
 */
public class QuickSort {

  
	  /* Two Stack Algorithm for Insertion Sort
	 * @param A Unordered Array
	 * @param n Number of items
	 * @return Sorted Array
	 */
	public static int[] quickSort(int[] A, int n) {
		
		if (n > 1) {
			
			int[] smaller, equal, larger;
			smaller = new int[n];
			equal = new int[n];
			larger = new int[n];
			int n_smaller, n_equal, n_larger;
			n_smaller = n_equal = n_larger = 0;
			int pivot = A[0];
			
			for (int i = 0; i < n; i++) {
				if (A[i] == pivot) equal[n_equal++] = A[i];
				else if (A[i] < pivot) smaller[n_smaller++] = A[i];
				else larger[n_l++] = A[i];
			}
			
			quickSort(smaller, n_smaller);
			quickSort(larger, n_larger);
			
			int i = 0;
			for (int j = 0; j < n_s; j++) A[i++] = smaller[j];
			for (int j = 0; j < n_e; j++) A[i++] = equal[j];
			for (int j = 0; j < n_l; j++) A[i++] = larger[j];
			
		}
		
		return A;
	}

}

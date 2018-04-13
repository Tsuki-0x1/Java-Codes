import Helpers.ArrayStack;  // ArrayStack Data Structure
/**
 * 
 * Insertion Sort Algorithms Demonstration in Java
 *
 * ------------------------------------
 * [ Two Stack Algorithm ]
 *
 * Algorithm InsertionSort(A, n)
 * In: Array A storing n values
 * Out: {Sort A in increasing order}
 *
 * sorted = empty stack
 * temp = empty stack
 * 
 * for i=0 to n-1 do {
 *   while (sorted is not empty) and (sorted.peek() < A[i]) do
 *   	temp.push(sorted.pop())
 *   sorted.push(A[i])
 *   while (temp is not empty) do
 *   	sorted.push(temp.pop())
 * }
 * 
 * for i=0 to n-1 do
 *   A[i] = sorted.pop()
 *   
 * ------------------------------------
 * [ In-Place Algorithm ]
 *
 * Algorithm InsertionSort(A, n)
 * In: Array A storing n values
 * Out: {Sort A in increasing order}
 
 * for i=1 to n-1 do {
 *   temp = A[i]
 *   j = i-1
 *   while (j>=0) and (A[j] > temp) do {
 *   	A[j+1] = A[j]
 *   	j = j-1
 *   }
 *   A[j+1] = temp
 * }
 *   
 * ------------------------------------
 * 
 * Order of Time Complexity: 
 *
 * Best:	O(n)
 * Worst: 	O(n^2)
 * Average: 	O(n^2)
 *
 */
public class InsertionSort {

	// Note: Below is only an implementation of a specific algorithm (above). It is NOT recommended to use the code.
	//	 This is only uploaded for demonstration purpose for above algorithm.
	
  	/**
	 * Two Stack Algorithm for Insertion Sort
	 * @param A Unordered Array
	 * @param n Number of items
	 * @return Sorted Array
	 */
	public int[] Sort_UsingStacks(int[] A, int n) {
		
		ArrayStack<Integer> sorted = new ArrayStack<Integer>();
		ArrayStack<Integer> temp = new ArrayStack<Integer>();
		
		for (int i = 0; i < n; i++) {
			while (!sorted.isEmpty() && sorted.peek() < A[i])
				temp.push(sorted.pop());
			sorted.push(A[i]);
			while (!temp.isEmpty())
				sorted.push(temp.pop());
		}
		
		for (int i = 0; i < n; i++)
			A[i] = sorted.pop();
		
		return A;
	}
	
	// Note: Below is only an implementation of a specific algorithm (above). It is NOT recommended to use the code.
	//	 This is only uploaded for demonstration purpose for above algorithm.
	
	/**
	 * In-Place Algorithm for Insertion Sort
	 * @param A Unordered Array
	 * @param n Number of items
	 * @return Sorted Array
	 */
	public static int[] Sort_InPlace(int[] A, int n) {
		
		for (int i = 1; i < n; i++) {	
			int temp = A[i];
			int j = i - 1;
			
			while (j >= 0 && A[j] > temp) {
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = temp;
		}
		return A;
	}
  
}

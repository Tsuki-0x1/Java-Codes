import Helpers.ArrayStack;  // ArrayStack Data Structure
/**
 * 
 * Insertion Sort using : Two Stacks
 *
 * ------------------------------------
 * 
 * Algorithm insertionSort(A, n)
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
 * 
 * Order of Time Complexity: 
 * Best:		O(n)
 * Worst: 	O(n^2)
 * Average: O(n^2)
 *
 */
public class InsertionSort {

  /**
	 * Two Stack Algorithm - Insertion Sort
	 * @param A Unordered Array
	 * @param n Number of items
	 * @return Sorted Array
	 */
	public int[] sort(int[] A, int n) {
		
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
  
}

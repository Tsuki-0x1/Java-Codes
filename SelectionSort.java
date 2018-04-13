import Helpers.LinkedQueue; // LinkedQueue Data Structure
/**
 * 
 * Selection Sort Algorithms Demonstration in Java
 *
 * ------------------------------------
 * [ Two Queues Algorithm ]
 *
 * Algorithm SelectionSort(list)
 * 
 * sorted = empty queue
 * temp = empty queue
 * 
 * while list is not empty do {
 *   smallest = list.removeFirst()
 *   while list is not empty do {
 *   	item = list.removeFirst()
 *   	if item < smallest then {
 *   		temp.enqueue(smallest)
 *   		smallest = item
 *   	}
 *   	else
 *   		temp.enqueue(item)
 *   }
 *   sorted.enqueue(smallest)
 *   while temp is not empty do
 *   	list.addToRear(temp.dequeue())
 * }
 * 
 * while sorted is not empty do
 *   list.addToRear(sorted.dequeue())
 *   
 * ------------------------------------
 * [ In-Place Algorithm ]
 *
 * Algorithm SelectionSort(A, n)
 * In: Array A storing n values
 * Out: {Sort A in increasing order}
 * 
 * for i=0 to n-2 do {
 *   smallest = i
 *   
 *   for j=i+1 to n-1 do {
 *   	if A[j] < A[smallest] then
 *   		smallest = j
 *   }
 *   
 *   temp = A[smallest]
 *   A[smallest] = A[i]
 *   A[i] = temp
 * }
 *   
 * ------------------------------------
 * 
 * Order of Time Complexity:
 *
 * Always:	O(n^2)
 *
 */
public class SelectionSort {

  	/**
	 * Two Queue Algorithm for Selection Sort
	 * @param list Unordered List
	 * @return Sorted List
	 */
	public static ArrayUnorderedList<Integer> Sort_UsingQueues(ArrayUnorderedList<Integer> list) {
		
		LinkedQueue<Integer> sorted = new LinkedQueue<Integer>();
		LinkedQueue<Integer> temp = new LinkedQueue<Integer>();
		
		while (!list.isEmpty()) {
			int smallest = list.removeFirst();
			
			while (!list.isEmpty()) {
				int item = list.removeFirst();
				
				if (item < smallest) {
					temp.enqueue(smallest);
					smallest = item;
				}
				else
					temp.enqueue(item);
			}
			sorted.enqueue(smallest);
			
			while (!temp.isEmpty())
				list.addToRear(temp.dequeue());
		}
		
		while (!sorted.isEmpty())
			list.addToRear(sorted.dequeue());

		return list;
	}
  
 	/**
	 * In-Place Algorithm for Insertion Sort
	 * @param A Unordered Array
	 * @param n Number of items
	 * @return Sorted Array
	 */
	public static int[] Sort_InPlace(int[] A, int n) {
		
		for (int i = 0; i < n - 1; i++) {
			int smallest = i;
			
			for (int j = i + 1; j < n; j++) {
				if (A[j] < A[smallest])
					smallest = j;
			}
			
			int temp = A[smallest];
			A[smallest] = A[i];
			A[i] = temp;
		}
		
		return A;
	}
  
}

import java.text.NumberFormat;
import java.util.Arrays;

public class QuickSort {
	
	// keeps the num of basic op's executions
	public static int count = 0;
	
	public static void quicksort(int array[]){
		quicksort(array,0,array.length-1);
	}
	
	public static void quicksort(int array[], int start, int end){
		
		int i = start;
		int k = end;
		if (end-start >= 1){
			// chooses pivot as the leftmost element
			int pivot = array[start];
			
			// partition part
			while (k > i){
				while (array[i] <= pivot && i <= end && k > i){
					i++;
					++count;
				}
				while (array[k] > pivot && k >= start && k>=i){
					k--;
					++count;
				}
				if (k > i)
					swap(array, i, k);
			}
			swap(array, start, k);
			
			quicksort(array, start, k-1);
			quicksort(array, k+1, end);
			
		} else {
				return;
		}
	}
	
	// swaps two elements of an array
	public static void swap (int array[], int index1, int index2) {
		
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		
	}
	
	// used for generating third type of inputs
	public static void generate(int[] arr, int begin, int end) {
		
	    int count = end - begin;
	    if(count < 3)
	      return;

	    //Find a middle element index
	    //This will be the pivot element for the part of the array [begin; end)
	    int middle = begin + (count - 1) / 2;

	    //Make the left part best-case first: [begin; middle)
	    generate(arr, begin, middle);

	    //Swap the pivot and the start element
	    swap(arr, begin, middle);

	    //Make the right part best-case, too: (middle; end)
	    generate(arr, ++middle, end);
	}
	
	// fills an array in ascending order
	private static void fillArray(int[] arr) {
	    for(int i = 0; i != arr.length; ++i)
	      arr[i] = i + 1;
	}

	// fills an array in descending order
	private static void fillArrayReversed(int[] arr) {
		int j=0;
	    for(int i =arr.length-1 ; i != -1; --i){
	      arr[j] = i + 1;
	      j++;
	    }
	}
	
	// prints an array
	private static void printArray(int[] arr) {
	    for(int item : arr)
	      System.out.print(item + " ");
	}

	public static void main(String[] args) {
		
		// change the size to 1000, 2000, 5000, 10000, and 12000
		int[] t = new int[12000];
		
		/*
		 * 	comment out or uncomment to generate different types of inputs
		 * */
		
		//	generates input in ascending order
		fillArray(t);	
		
		//	generates input such that the pivot divides both sides equally
		//	best case for quicksort algorithm
		//generate(t, 0, t.length);
		
		//	generates input in descending order
		//fillArrayReversed(t);
		
		printArray(t);
		System.out.println();
		
		// starts timer, and executes the algorithm on given array
		long startTime = System.nanoTime();
		quicksort(t);
		
		// ends the timer, and computes the running time
		long endTime = System.nanoTime();
		long execTime = endTime - startTime;
		
		// prints the results
        System.out.println("Sorted array : " + Arrays.toString(t));
		System.out.println();
		System.out.println("Number of basic operation's executions: " + count);
		System.out.println("Elapsed time: " + (double)execTime/1000000000.0);
		
	}

}

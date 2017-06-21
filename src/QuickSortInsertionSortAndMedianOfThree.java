import java.util.Arrays;

public class QuickSortInsertionSortAndMedianOfThree {
	  
	public static int count = 0;
	
	public static void quickSort(int[] intArray) {
		recQuickSort(intArray, 0, intArray.length - 1);
	    insertionSort(intArray, 0, intArray.length - 1);
	}

	public static void recQuickSort(int[] intArray, int left, int right) {
		int size = right - left + 1;
		if (size < 100)
	      insertionSort(intArray, left, right);
	    else {
	      double median = medianOf3(intArray, left, right);
	      int partition = partitionIt(intArray, left, right, median);
	      recQuickSort(intArray, left, partition - 1);
	      recQuickSort(intArray, partition + 1, right);
	    }
	}

	public static double medianOf3(int[] intArray, int left, int right) {
		int center = (left + right) / 2;

	    if (intArray[left] > intArray[center])
	      swap(intArray, left, center);

	    if (intArray[left] > intArray[right])
	      swap(intArray, left, right);

	    if (intArray[center] > intArray[right])
	      swap(intArray, center, right);

	    swap(intArray, center, right - 1);
	    return intArray[right - 1];
	}

	public static void swap(int[] intArray, int dex1, int dex2) {
	    int temp = intArray[dex1];
	    intArray[dex1] = intArray[dex2];
	    intArray[dex2] = temp;
	}

	public static int partitionIt(int[] intArray, int left, int right, double pivot) {
	    int leftPtr = left;
	    int rightPtr = right - 1;
	    while (true) {
	      while (intArray[++leftPtr] < pivot)
	    	  ++count;
	      while (intArray[--rightPtr] > pivot)
	    	  ++count;
	      if (leftPtr >= rightPtr)
	        break;
	      else
	        swap(intArray, leftPtr, rightPtr);
	    }
	    swap(intArray, leftPtr, right - 1);
	    return leftPtr;
	}

	public static void insertionSort(int[] intArray, int left, int right) {
	    int in, out;

	    for (out = left + 1; out <= right; out++) {
	      int temp = intArray[out];
	      in = out;

	      while (in > left && intArray[in - 1] >= temp) {
	        intArray[in] = intArray[in - 1];
	        --in;
	        ++count;
	      }
	      intArray[in] = temp;
	    }
	}
	
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
	
	private static void fillArray(int[] arr) {
	    for(int i = 0; i != arr.length; ++i)
	      arr[i] = i + 1;
	}
	
	private static void fillArrayReversed(int[] arr) {
	    for(int i =arr.length-1 ; i != -1; --i)
	      arr[i] = i + 1;
	}
	
	private static void printArray(int[] arr) {
	    for(int item : arr)
	      System.out.print(item + " ");
	}
	
	public static void main(String[] args) {
		// change to 1000, 2000, 5000, 10000, and 12000
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
		long startTime = System.nanoTime();
		quickSort(t);
		long endTime = System.nanoTime();
		long execTime = endTime - startTime;
		
        System.out.println("Sorted array : " + Arrays.toString(t));
		System.out.println();
		System.out.println("Number of basic operation's executions: " + count);
		System.out.println("Elapsed time: " + (double)execTime/1000000000.0);
	  }
}

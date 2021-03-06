import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class QuickSortMedianInsertionIterative {
	
	public static int count = 0;
	
    public static void iterativeQsort(int[] numbers) {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(numbers.length);

        while (!stack.isEmpty()) {
            int end = (int) stack.pop();
            int start = (int) stack.pop();
            if (end - start < 100) {
                insertionSort(numbers,start,end);
            }
            else {
            	int p = medianOf3(numbers, start, end-1);
            	p = partition(numbers,start, end-1);

            	stack.push(p + 1);
            	stack.push(end);

            	stack.push(start);
            	stack.push(p);
            }

        }
    }
    
    
	public static int medianOf3(int[] intArray, int left, int right) {
		int center = (left + right) / 2;

		if (intArray[left] > intArray[center])
			swap(intArray, left, center);

		if (intArray[left] > intArray[right])
			swap(intArray, left, right);

		if (intArray[center] > intArray[right])
			swap(intArray, center, right);

		swap(intArray, center, right - 1);
		return right - 1;
	}
	
	public static void insertionSort(int[] intArray, int left, int right) {
	    int in, out;

	    for (out = left + 1; out < right; out++) {
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

    /*
     * Utility method to partition the array into smaller array, and
     * comparing numbers to rearrange them as per quicksort algorithm.
     */
    public static int partition (int arr[], int l, int h)
    {
        int x = arr[h];
        int i = (l - 1);
 
        for (int j = l; j <= h- 1; j++)
        {
            if (arr[j] <= x)
            {
                i++;
                // swap arr[i] and arr[j]
                swap(arr,i,j);
            }
            ++count;
        }
        // swap arr[i+1] and arr[h]
        swap(arr,i+1,h);
        return (i + 1);
    }

    /**
     * Utility method to swap two numbers in given array
     *
     * @param arr - array on which swap will happen
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
		int[] t = new int[1000];
		
		/*
		 * 	comment out or uncomment to generate different types of inputs
		 * */
		
		//	generates input in ascending order
		fillArray(t);	
		
		//	generates input such that the pivot divides both sides equally
		//	best case for quicksort algorithm
		generate(t, 0, t.length);
		
		//	generates input in descending order
		//fillArrayReversed(t);
		
		printArray(t);
		System.out.println();
		
		long startTime = System.nanoTime();
        iterativeQsort(t);
		long endTime = System.nanoTime();
		long execTime = endTime - startTime;
		
        System.out.println("Sorted array : " + Arrays.toString(t));
		System.out.println();
		System.out.println("Number of basic operation's executions: " + count);
		System.out.println("Elapsed time: " + (double)execTime/1000000000.0);
	}

}

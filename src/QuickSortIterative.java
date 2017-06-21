import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class QuickSortIterative {
	
	public static int count = 0;
	
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
 
    // Sorts arr[l..h] using iterative QuickSort
    public static void iterativeQsort(int arr[], int l, int h)
    {
        // create auxiliary stack
        int stack[] = new int[h-l+1];
 
        // initialize top of stack
        int top = -1;
 
        // push initial values in the stack
        stack[++top] = l;
        stack[++top] = h;
 
        // keep popping elements until stack is not empty
        while (top >= 0)
        {
            // pop h and l
            h = stack[top--];
            l = stack[top--];
 
            // set pivot element at it's proper position
            int p = partition(arr, l, h);
 
            // If there are elements on left side of pivot,
            // then push left side to stack
            if ( p-1 > l )
            {
                stack[ ++top ] = l;
                stack[ ++top ] = p - 1;
            }
 
            // If there are elements on right side of pivot,
            // then push right side to stack
            if ( p+1 < h )
            {
                stack[ ++top ] = p + 1;
                stack[ ++top ] = h;
            }
       }
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
		int[] t = new int[12000];
		
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
        iterativeQsort(t,0,t.length-1);
		long endTime = System.nanoTime();
		long execTime = endTime - startTime;
		
        System.out.println("Sorted array : " + Arrays.toString(t));
		System.out.println();
		System.out.println("Number of basic operation's executions: " + count);
		System.out.println("Elapsed time: " + (double)execTime/1000000000.0);
	}

}

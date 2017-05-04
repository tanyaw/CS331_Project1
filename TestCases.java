import java.io.IOException;
import java.io.FileWriter;
import java.util.Random;
import java.lang.Math;
import java.io.File;

public class TestCases {
	private static FileWriter fw1, fw2;

	private static final int EXPONENT = 17;
    private static final int TEST_CASES = 20;
    private static int[] unsortedArr, testArr, sorted;

    private static long start_time = 0;
    private static long end_time = 0;

    private static double totalTimeInsert = 0;
    private static double totalTimeMerge = 0;
    private static double totalTimeQuick1 = 0;
    private static double totalTimeQuick2 = 0;
    private static double totalTimeQuick3 = 0;

	public static void main(String[] args) throws IOException {
		File file1 = new File("SortingAlgs_Output.txt");
        file1.createNewFile();
        fw1 = new FileWriter(file1);

        fw1.write("TEST CASES #1: Unsorted Arrays");
        fw1.flush();
        newLine();

        //Run test cases on unsorted array
		for(double i=1; i < EXPONENT; i++) {
            //Create array of size 2^1 to 2^16
            int arrSize = (int) Math.pow(2.0, i);
            unsortedArr = new int[arrSize];

            generateRandVals(arrSize);

            fw1.write("ARRAY SIZE - " + arrSize + "\n");
            fw1.flush();

            if(arrSize <= 32) {
            	fw1.write("UNSORTED ARRAY - ");
            	fw1.flush();
            	writeArrToFile(unsortedArr);
            	newLine();
            }

            testUnsortedArrays(arrSize);    
    	}

    	fw1.write("TEST CASES #2: Sorted Arrays");
        fw1.flush();
        newLine();

    	//Run test cases on sorted array
    	for(double i=1; i < EXPONENT; i++) {
            //Create array of size 2^1 to 2^16
            int arrSize = (int) Math.pow(2.0, i);
            unsortedArr = new int[arrSize];

            generateRandVals(arrSize);
            
            //Sort Array
            MergeSort ms = new MergeSort();
            ms.sort(unsortedArr);
            int[] sortedArr = ms.getSortedArr();

            fw1.write("ARRAY SIZE - " + arrSize + "\n");
            fw1.flush();

            if(arrSize <= 32) {
            	fw1.write("SORTED ARRAY - ");
            	fw1.flush();
            	writeArrToFile(sortedArr);
            	newLine();
            }

            testSortedArrays(sortedArr, arrSize);
    	}

    	fw1.close();
    }

    //Helper Method - Run test cases on unsorted arrays
    public static void testUnsortedArrays(int arrSize) throws IOException {
    	//TEST 1: Insertion Sort
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(unsortedArr);

            InsertionSort insert = new InsertionSort();
            start_time = System.nanoTime();
            sorted = insert.insertionSort(testArr);
            end_time = System.nanoTime();
            
            postTestCase(sorted, 1);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

        //TEST 2: Merge Sort
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(unsortedArr);

            MergeSort merge = new MergeSort();
            start_time = System.nanoTime();
            merge.sort(testArr);
            end_time = System.nanoTime();
            sorted = merge.getSortedArr();

            postTestCase(sorted, 2);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

        //TEST 3: Quick Sort1
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(unsortedArr);

            QuickSort quick1 = new QuickSort(testArr);
            start_time = System.nanoTime();
            quick1.quickSort1(0, (arrSize-1));
            end_time = System.nanoTime();
      		sorted = quick1.getSortedArr();

            postTestCase(sorted, 3);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

    	//TEST 4: Quick Sort2
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(unsortedArr);

            QuickSort quick2 = new QuickSort(testArr);
            start_time = System.nanoTime();
            quick2.quickSort2(0, (arrSize-1));
            end_time = System.nanoTime();
      		sorted = quick2.getSortedArr();

            postTestCase(sorted, 4);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

    	//TEST 5: Quick Sort3
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(unsortedArr);

            QuickSort quick3 = new QuickSort(testArr);
            start_time = System.nanoTime();
            quick3.quickSort3(0, (arrSize-1));
            end_time = System.nanoTime();
      		sorted = quick3.getSortedArr();

            postTestCase(sorted, 5);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

        calcAvgRuntimes();
    }

    //Helper Method - Run test cases on unsorted arrays
    public static void testSortedArrays(int[] sortedArr, int arrSize) throws IOException {
    	//TEST 1: Insertion Sort
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(sortedArr);

            InsertionSort insert = new InsertionSort();
            start_time = System.nanoTime();
            sorted = insert.insertionSort(testArr);
            end_time = System.nanoTime();
            
            postTestCase(sorted, 1);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

        //TEST 2: Merge Sort
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(sortedArr);

            MergeSort merge = new MergeSort();
            start_time = System.nanoTime();
            merge.sort(testArr);
            end_time = System.nanoTime();
            sorted = merge.getSortedArr();

            postTestCase(sorted, 2);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

        //TEST 3: Quick Sort1
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(sortedArr);

            QuickSort quick1 = new QuickSort(testArr);
            start_time = System.nanoTime();
            quick1.quickSort1(0, (arrSize-1));
            end_time = System.nanoTime();
      		sorted = quick1.getSortedArr();

            postTestCase(sorted, 3);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

    	//TEST 4: Quick Sort2
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(sortedArr);

            QuickSort quick2 = new QuickSort(testArr);
            start_time = System.nanoTime();
            quick2.quickSort2(0, (arrSize-1));
            end_time = System.nanoTime();
      		sorted = quick2.getSortedArr();

            postTestCase(sorted, 4);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

    	//TEST 5: Quick Sort3
        for(int j=0; j < TEST_CASES; j++) {
            testArr = copyArrVals(sortedArr);

            QuickSort quick3 = new QuickSort(testArr);
            start_time = System.nanoTime();
            quick3.quickSort3(0, (arrSize-1));
            end_time = System.nanoTime();
      		sorted = quick3.getSortedArr();

            postTestCase(sorted, 5);

            if(j == (TEST_CASES -1) && arrSize <= 32) {
            	newLine();
            }
        }

        calcAvgRuntimes();
    }

    //Helper Method - Debugging
    public static void printArray(int[]t) {
		for (int i : t) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	//Helper Method - Calculate and Write average runtimes to file
    public static void calcAvgRuntimes() throws IOException { 
        double avgTimeInsert = 0;
        double avgTimeMerge = 0;
        double avgTimeQuick1 = 0;
        double avgTimeQuick2 = 0;
        double avgTimeQuick3 = 0;

        fw1.write("\n--- AVERAGE RUNTIMES ---\n");

        avgTimeInsert = (totalTimeInsert / 20.0);
        fw1.write("IS AVG: " + avgTimeInsert + " ns\n");
        fw1.flush();

        avgTimeMerge = (totalTimeMerge / 20.0);
        fw1.write("MS AVG: " + avgTimeMerge + " ns\n");
        fw1.flush();

        avgTimeQuick1 = (totalTimeQuick1 / 20.0);
        fw1.write("QS1 AVG: " + avgTimeQuick1 + " ns\n");
        fw1.flush();

        avgTimeQuick2 = (totalTimeQuick2 / 20.0);
        fw1.write("QS2 AVG: " + avgTimeQuick2 + " ns\n");
        fw1.flush();

        avgTimeQuick3 = (totalTimeQuick3 / 20.0);
        fw1.write("QS3 AVG: " + avgTimeQuick3 + " ns\n");
        fw1.flush();

        fw1.write("\n\n\n");
        fw1.flush();
    }

    //Helper Method - Initalize unsortedArr with random integers
    public static void generateRandVals(int size) {
        Random rand = new Random();
        for(int i=0; i < size; i++) {
           unsortedArr[i] = rand.nextInt();
        }
    }

    //Helper Method - Clear arrays and update TotalTime values
    public static void postTestCase(int[] arr, int i) throws IOException {
        switch(i) {
        	case 1:
        		totalTimeInsert += (end_time - start_time);
	            if(arr.length <= 32) {
		        	fw1.write("INSERTION SORT: ");
		        	fw1.flush();
		        	writeArrToFile(arr);
	       		 }
        		break;
        	case 2:
        		totalTimeMerge += (end_time - start_time);
        		if(arr.length <= 32) {
	        		fw1.write("MERGE SORT: ");
	        		fw1.flush();
	        		writeArrToFile(arr);
	        	}
        		break;
        	case 3:
        		totalTimeQuick1 += (end_time - start_time);
        		if(arr.length <= 32) {
	        		fw1.write("QUICK1 SORT: ");
	        		fw1.flush();
	        		writeArrToFile(arr);
	        	}
        		break;
        	case 4: 
        		totalTimeQuick2 += (end_time - start_time);
        		if(arr.length <= 32) {
	        		fw1.write("QUICK2 SORT: ");
	        		fw1.flush();
	        		writeArrToFile(arr);
	        	}
        		break;
        	case 5:
        		totalTimeQuick3 += (end_time - start_time);
        		if(arr.length <= 32) {
	        		fw1.write("QUICK3 SORT: ");
	        		fw1.flush();
	        		writeArrToFile(arr);
	        	}
        		break;
        	default:
        		System.out.println("Invalid, post test case");
        		break;
        }
        
        //CLEAR ARRAYS
        testArr = null;
        sorted = null;
    }

    //Helper Method - Writes content of array
    public static void writeArrToFile(int[] arr) throws IOException {
        for(int val: arr) {
            fw1.write(val + " ");
            fw1.flush();
        }
        newLine();
    }

    //Helper Method - Formats file
    public static void newLine() throws IOException {
    	fw1.write("\n");
        fw1.flush();
    }

    //Helper Method - Copies unsorted array to testArr
    public static int[] copyArrVals(int[] arr) {
        testArr = new int[arr.length];

        for(int i=0; i < arr.length; i++) {
            testArr[i] = arr[i];
        }
        return testArr;
    }
}
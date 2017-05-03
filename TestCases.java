import java.util.Random;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestCases {
    private FileWriter fw1, fw2, fw3, fw4, fw5;

    private final int EXPONENT = 17;
    private final int TEST_CASES = 20;
    private int[] unsortedArr, testArr, sorted;

    private long start_time = 0;
    private long end_time = 0;

    private double totalTimeInsert = 0;
    private double totalTimeMerge = 0;
    private double totalTimeQuick1 = 0;
    private double totalTimeQuick2 = 0;
    private double totalTimeQuick3 = 0;

    //Constructor - Initialize FileWriter Objects and output files
    public TestCases() throws IOException {
        File file1 = new File("Insertion_Output.txt");
        file1.createNewFile();
        fw1 = new FileWriter(file1);
        fw1.write("--- INSERTION SORT ---\n\n");
        
        File file2 = new File("Merge_Output.txt");
        file2.createNewFile();
        fw2 = new FileWriter(file2);
        fw2.write("--- MERGE SORT ---\n\n");

        File file3 = new File("Quick1_Output.txt");
        file3.createNewFile();
        fw3 = new FileWriter(file3);
        fw3.write("--- QUICK SORT1 ---\n\n");

        File file4 = new File("Quick2_Output.txt");
        file4.createNewFile();
        fw4 = new FileWriter(file4);
        fw4.write("--- QUICK SORT2 ---\n\n");

        File file5 = new File("Quick3_Output.txt");
        file5.createNewFile();
        fw5 = new FileWriter(file5); 
        fw5.write("--- QUICK SORT3 ---\n\n");
    }

    public void runTestCases() throws IOException {

        for(double i=1; i < EXPONENT; i++) {
            //Create array of size 2^1 to 2^16
            int arrSize = (int) Math.pow(2.0, i);
            unsortedArr = new int[arrSize];

            writeArrSizeToFile(arrSize);
            generateRandVals(arrSize);

            //TEST 1: Insertion Sort
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                InsertionSort insert = new InsertionSort();
                start_time = System.nanoTime();
                sorted = insert.insertionSort(testArr);
                end_time = System.nanoTime();

                writeSortedArrToFile(sorted, j, 1);
                postTestCase(1);
            }

            //TEST 2: Merge Sort
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                MergeSort merge = new MergeSort();
                start_time = System.nanoTime();
                merge.sort(testArr);
                end_time = System.nanoTime();
                sorted = merge.getSortedArr();

                writeSortedArrToFile(sorted, j, 2);
                postTestCase(2);
            }

            //TEST 3: Quick Sort 1
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                QuickSort quick1 = new QuickSort();
                start_time = System.nanoTime();
                quick1.sort(testArr, 1);
                end_time = System.nanoTime();
                sorted = quick1.getSortedArr();

                writeSortedArrToFile(sorted, j, 3);
                postTestCase(3);
            }

            //TEST 4: Quick Sort 2
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                QuickSort quick2 = new QuickSort();
                start_time = System.nanoTime();
                quick2.sort(testArr, 2);
                end_time = System.nanoTime();
                sorted = quick2.getSortedArr();

                writeSortedArrToFile(sorted, j, 4);
                postTestCase(4);
            }

            //TEST 5: Quick Sort 3
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                QuickSort quick3 = new QuickSort();
                start_time = System.nanoTime();
                quick3.sort(testArr, 3);
                end_time = System.nanoTime();
                sorted = quick3.getSortedArr();

                writeSortedArrToFile(sorted, j, 5);
                postTestCase(5);
            }
            calcAvgRuntimes();
        }
        closeFileWriters();
    }

    //Helper Method - Initalize unsortedArr with random integers
    public void generateRandVals(int size) {
        Random rand = new Random();
        for(int i=0; i < size; i++) {
           unsortedArr[i] = rand.nextInt(100) +1;
        }
    }

    //Helper Method - Write array length to file
    public void writeArrSizeToFile(int i) throws IOException {
        fw1.write("Array Length - " + i + " elements\n");
        fw1.flush();

        fw2.write("Array Length - " + i + " elements\n");
        fw2.flush();

        fw3.write("Array Length - " + i + " elements\n");
        fw3.flush();

        fw4.write("Array Length - " + i + " elements\n");
        fw4.flush();

        fw5.write("Array Length - " + i + " elements\n");
        fw5.flush();
    }

    //Helper Method - Write sorting method output to file
    public void writeSortedArrToFile(int[] sorted, int testNum, int i) throws IOException {
        switch(i) {
            case 1:
                fw1.write("Test #" + testNum + ": ");
                fw1.flush();
                writeArrToFile(sorted, fw1);
                break;
            case 2:
                fw2.write("Test #" + testNum + ": ");
                fw2.flush();
                writeArrToFile(sorted, fw2);
                break;
            case 3:
                fw3.write("Test #" + testNum + ": ");
                fw3.flush();
                writeArrToFile(sorted, fw3);
                break;
            case 4:
                fw4.write("Test #" + testNum + ": ");
                fw4.flush();
                writeArrToFile(sorted, fw4);
                break;
            case 5:
                fw5.write("Test #" + testNum + ": ");
                fw5.flush();
                writeArrToFile(sorted, fw5);
                break;
            default: 
                System.out.println("You enetered an invalid integer parameter.");
                break;
        }
    }

    //Helper Method - Clear arrays and update TotalTime values
    public void postTestCase(int i) {
        testArr = null;
        sorted = null;

        switch(i) {
            case 1:
                totalTimeInsert += (end_time - start_time);
                break;
            case 2:
                totalTimeMerge += (end_time - start_time);
                break;
            case 3:
                totalTimeQuick1 += (end_time - start_time);
                break;
            case 4:
                totalTimeQuick2 += (end_time - start_time);
                break;
            case 5:
                totalTimeQuick3 += (end_time - start_time);
                break;
            default:
                System.out.println("You provided an invalid integer parameter.");
                break;
        }
    }

    //Helper Method - Calculate and Write average runtimes to file
    public void calcAvgRuntimes() throws IOException { 
        double avgTimeInsert = 0;
        double avgTimeMerge = 0;
        double avgTimeQuick1 = 0;
        double avgTimeQuick2 = 0;
        double avgTimeQuick3 = 0;

        avgTimeInsert = (totalTimeInsert / 15.0);
        fw1.write("Average Runtime: " + avgTimeInsert + "\n\n");
        fw1.flush();

        avgTimeMerge = (totalTimeMerge / 15.0);
        fw2.write("Average Runtime: " + avgTimeMerge + "\n\n");
        fw2.flush();

        avgTimeQuick1 = (totalTimeQuick1 / 15.0);
        fw3.write("Average Runtime: " + avgTimeQuick1 + "\n\n");
        fw3.flush();

        avgTimeQuick2 = (totalTimeQuick2 / 15.0);
        fw4.write("Average Runtime: " + avgTimeQuick2 + "\n\n");
        fw4.flush();

        avgTimeQuick3 = (totalTimeQuick3 / 15.0);
        fw5.write("Average Runtime: " + avgTimeQuick3 + "\n\n");
        fw5.flush();
    }

    //Helper Method - Closes FileWriter Objects
    public void closeFileWriters() throws IOException {
        fw1.close();
        fw2.close();
        fw3.close();
        fw4.close();
        fw5.close(); 
    }

    //Helper Method - Copies unsorted array to testArr
    public int[] copyArrVals(int[] arr) {
        testArr = new int[arr.length];

        for(int i=0; i < arr.length; i++) {
            testArr[i] = arr[i];
        }
        return testArr;
    }

    //Helper Method - Writes content of array
    public void writeArrToFile(int[] arr, FileWriter fw) throws IOException {
        for(int val: arr) {
            fw.write(val + " ");
            fw.flush();
        }
        fw.write("\n");
        fw.flush();
    }

}
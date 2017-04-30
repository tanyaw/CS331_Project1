// CS 331 Design and Analysis of Algorithms
// Project #1 - Implement and Analyze Sorting Algorithms
// Spring Quarter 2017
// Dr. Daisy Tang
// Author: Tanya Wanwatanakool

import java.util.Random;
import java.lang.Math;
import java.lang.System;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SortingAlgsAnalysis {
    private static final int EXPONENT = 17;
    private static final int TEST_CASES = 15;

    private static Random rand = new Random();
    private static int[] unsortedArr;
    private static int[] testArr;
    private static int[] sorted;

    private static long start_time = 0;
    private static long end_time = 0;

    private static double totalTimeInsert = 0;
    private static double totalTimeMerge = 0;
    private static double totalTimeQuick1 = 0;
    private static double totalTimeQuick2 = 0;
    private static double totalTimeQuick3 = 0;

    private static double avgTimeInsert = 0;
    private static double avgTimeMerge = 0;
    private static double avgTimeQuick1 = 0;
    private static double avgTimeQuick2 = 0;
    private static double avgTimeQuick3 = 0;

    public static void main(String[] args) throws IOException {

        //Create FileWriter Objects and output files
        File file1 = new File("Insertion_Output.txt");
        file1.createNewFile();
        FileWriter fw1 = new FileWriter(file1); 
        
        File file2 = new File("Merge_Output.txt");
        file2.createNewFile();
        FileWriter fw2 = new FileWriter(file2); 

        File file3 = new File("Quick1_Output.txt");
        file3.createNewFile();
        FileWriter fw3 = new FileWriter(file3); 

        File file4 = new File("Quick2_Output.txt");
        file4.createNewFile();
        FileWriter fw4 = new FileWriter(file4);

        File file5 = new File("Quick3_Output.txt");
        file5.createNewFile();
        FileWriter fw5 = new FileWriter(file5); 


        //Create array of size 2^1 to 2^16
        for(double i=1; i < EXPONENT; i++) {
            int arrSize = (int) Math.pow(2.0, i);
            unsortedArr = new int[arrSize];

            //Generate random array values
            for(int j=0; j < arrSize; j++) {
                unsortedArr[j] = rand.nextInt(100) +1;
            }

            //TEST 1: Insertion Sort
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);
                sorted = insertionSort(testArr);

                fw1.write("Insertion Sort Test #" + j + ":");
                fw1.flush();
                writeArrToFile(sorted, fw1);

                testArr = null;
                sorted = null;

                totalTimeInsert += (end_time - start_time);
            }

            //TEST 2: Merge Sort
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                MergeSort merge = new MergeSort();
                start_time = System.nanoTime();
                merge.sort(testArr);
                end_time = System.nanoTime();
                sorted = merge.getSortedArr();

                fw2.write("Merge Sort Test #" + j + ":");
                fw2.flush();
                writeArrToFile(sorted, fw2);

                testArr = null;
                sorted = null;

                totalTimeMerge += (end_time - start_time);
            }

            //TEST 3: Quick Sort 1
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                QuickSort quick1 = new QuickSort();
                start_time = System.nanoTime();
                quick1.sort(testArr, 1);
                end_time = System.nanoTime();
                sorted = quick1.getSortedArr();

                fw3.write("Merge Sort Test #" + j + ":");
                fw3.flush();
                writeArrToFile(sorted, fw3);

                testArr = null;
                sorted = null;

                totalTimeQuick1 += (end_time - start_time);
            }

            //TEST 4: Quick Sort 2
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                QuickSort quick2 = new QuickSort();
                start_time = System.nanoTime();
                quick2.sort(testArr, 2);
                end_time = System.nanoTime();
                sorted = quick2.getSortedArr();

                fw4.write("Merge Sort Test #" + j + ":");
                fw4.flush();
                writeArrToFile(sorted, fw4);

                testArr = null;
                sorted = null;

                totalTimeQuick2 += (end_time - start_time);
            }

            //TEST 5: Quick Sort 3
            for(int j=0; j < TEST_CASES; j++) {
                testArr = copyArrVals(unsortedArr);

                QuickSort quick3 = new QuickSort();
                start_time = System.nanoTime();
                quick3.sort(testArr, 3);
                end_time = System.nanoTime();
                sorted = quick3.getSortedArr();

                fw5.write("Merge Sort Test #" + j + ":");
                fw5.flush();
                writeArrToFile(sorted, fw5);

                testArr = null;
                sorted = null;

                totalTimeQuick3 += (end_time - start_time);
            }

            avgTimeInsert = (totalTimeInsert / 15.0);
            fw1.write("Insertion Sort Average Run-time: " + avgTimeInsert + "\n\n");
            fw1.flush();

            avgTimeMerge = (totalTimeMerge / 15.0);
            fw2.write("Insertion Sort Average Run-time: " + avgTimeMerge + "\n\n");
            fw2.flush();

            avgTimeQuick1 = (totalTimeQuick1 / 15.0);
            fw3.write("Insertion Sort Average Run-time: " + avgTimeQuick1 + "\n\n");
            fw3.flush();

            avgTimeQuick2 = (totalTimeQuick2 / 15.0);
            fw4.write("Insertion Sort Average Run-time: " + avgTimeQuick2 + "\n\n");
            fw4.flush();

            avgTimeQuick3 = (totalTimeQuick3 / 15.0);
            fw5.write("Insertion Sort Average Run-time: " + avgTimeQuick3 + "\n\n");
            fw5.flush();
        }   

        //Close FileWriter Objects
            fw1.close();
            fw2.close();
            fw3.close();
            fw4.close();
            fw5.close(); 
    }

    public static int[] insertionSort(int[] arr) {
        start_time = System.nanoTime();
        int target;
        int j;

        for(int i=0; i < arr.length; i++) {
            target = arr[i];
            j = i-1;

            while(j >= 0 && target < arr[j]) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = target;
        }
        end_time = System.nanoTime();
        return arr;
    }

    //Helper Method - Copies unsorted array to testArr
    public static int[] copyArrVals(int[] arr) {
        testArr = new int[arr.length];

        for(int i=0; i < arr.length; i++) {
            testArr[i] = arr[i];
        }
        return testArr;
    }

    //Helper Method - Writes content of array
    public static void writeArrToFile(int[] arr, FileWriter fw) throws IOException {
        for(int val: arr) {
            fw.write(val + " ");
            fw.flush();
        }
        fw.write("\n");
        fw.flush();
    }

}

class MergeSort {
    private int[] arr;
    private int[] temp;

    public void sort(int[] arr) {
        this.arr = arr;
        temp = new int[arr.length];
        mergeSort(0, (arr.length-1));
    }

    public void mergeSort(int low, int high) {
        int mid;

        if(low < high) {
            mid = (low + high) / 2;
            mergeSort(low, mid);
            mergeSort(mid+1, high);
            merge(low, mid, high);
        }
    }

    public void merge(int low, int mid, int high) {
        for(int i=low; i <= high; i++) {
            temp[i] = arr[i];
        }

        int i = low;
        int j = mid + 1;
        int k = low;       

        while( i <= mid && j <= high) {
            if(temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while(i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }
    }

    public int[] getSortedArr() {
        return arr;
    }
}

class QuickSort {
    private int[] arr;
    private int pivotposition;

    //QuickSort Helper Method
    public void sort(int[] arr, int i) {
        this.arr = arr;

        //Determine QuickSort implementation to use
        switch(i) {
            case 1:
                quickSort(0, (arr.length-1), 1);
                break;
            case 2:
                quickSort(0, (arr.length-1), 2);
                break;
            case 3:
                quickSort(0, (arr.length-1), 3);
                break;
            default:
                System.out.println("You provided an invalid integer parameter.");
                break;
        }
    }

    public void quickSort(int low, int high, int i) {
        if(low < high) {

            //Determine Partition implementation to use
            switch(i) {
                case 1:
                    pivotposition = partition1(low, high);
                    quickSort(low, pivotposition-1, 1);
                    quickSort(pivotposition+1, high, 1);
                    break;
                case 2:
                    pivotposition = partition2(low, high);
                    quickSort(low, pivotposition-1, 2);
                    quickSort(pivotposition+1, high, 2);
                    break;
                case 3:
                    pivotposition = partition3(low, high);
                    quickSort(low, pivotposition-1, 3);
                    quickSort(pivotposition+1, high, 3);
                    break;
                default:
                    System.out.println("You provided an invalid integer parameter.");
                    break;
            }
        }
    }

    //QuickSort Implementation 1:
    //Use the 1st element as the pivot
    public int partition1(int first, int last) {
        int pivot = arr[first];
        int tb = first +1;
        int ts = last;

        while(tb <= ts) {
            while(tb <= last && arr[tb] < pivot) {
                tb++;
            }

            while(ts > first && arr[ts] > pivot) {
                ts--;
            }

            if(tb < ts) {
                int temp = arr[tb];
                arr[tb] = arr[ts];
                arr[ts] = temp;
            } else {
                break;
            }
        }
        arr[first] = arr[ts];
        arr[ts] = pivot;
        return ts;  //This is pivotposition
    }

    //QuickSort Implementation 2:
    //If number of elements in arr is <= 16, then call Insertion Sort
    public int partition2(int first, int last) {
        if( (last - first) <= 16) {
            int[] sortedSubArr = insertionSort(arr, first, last);

            //Append insertion sorted sub-array to arr
            for(int i=first; i < last; i++) {
                arr[i] = sortedSubArr[i];
            }
        }

        int pivot = arr[first];
        int tb = first +1;
        int ts = last;

        while(tb <= ts) {
            while(tb <= last && arr[tb] < pivot) {
                tb++;
            }

            while(ts > first && arr[ts] > pivot) {
                ts--;
            }

            if(tb < ts) {
                int temp = arr[tb];
                arr[tb] = arr[ts];
                arr[ts] = temp;
            } else {
                break;
            }
        }
        arr[first] = arr[ts];
        arr[ts] = pivot;
        return ts;  //This is pivotposition
    }

    //QuickSort Implementation 3:
    //If number of elements >= 16, then use random index as pivotposition
    public int partition3(int first, int last) {
        int randIndex = 0;

        //Select random index
        if((last-first+1) >= 16) {
            Random rand = new Random();
            randIndex = first + rand.nextInt() % (last-first+1);

            if(randIndex < 0) {
                randIndex *= -1;
            }

            int temp = arr[first];
            arr[first] = arr[randIndex];
            arr[randIndex] = temp;
        }

        int pivot = arr[first];
        int tb = first +1;
        int ts = last;
        while(tb <= ts) {
            while(tb <= last && arr[tb] < pivot) {
                tb++;
            }

            while(ts > first && arr[ts] > pivot) {
                ts--;
            }

            if(tb < ts) {
                int temp = arr[tb];
                arr[tb] = arr[ts];
                arr[ts] = temp;
            } else {
                break;
            }
        }
        arr[first] = arr[ts];
        arr[ts] = pivot;
        return ts;  //This is pivotposition
    }

    public static int[] insertionSort(int[] arr, int first, int last) {
        int target;
        int j;

        for(int i=first; i < last; i++) {
            target = arr[i];
            j = i-1;

            while(j >= 0 && target < arr[j]) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = target;
        }
        return arr;
    }

    public int[] getSortedArr() {
        return arr;
    }
}

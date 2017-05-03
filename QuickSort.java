import java.util.Random;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSort {
    private int[] arr;

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
            int pivotposition;
            //Determine Partition implementation to use
            switch(i) {
                case 1:
                    partition1(low, high);
                    //pivotposition = partition1(low, high);
                    // quickSort(low, ts-1, 1);
                    // quickSort(ts+1, high, 1);
                    break;
                case 2:
                    partition2(low, high);
                    //pivotposition = partition2(low, high);
                    // quickSort(low, pivotposition-1, 2);
                    // quickSort(pivotposition+1, high, 2);
                    break;
                case 3:
                    partition3(low, high);
                    // pivotposition = partition3(low, high);
                    // quickSort(low, pivotposition-1, 3);
                    // quickSort(pivotposition+1, high, 3);
                    break;
                default:
                    System.out.println("You provided an invalid integer parameter.");
                    break;
            }
        }
    }

    //QuickSort Implementation 1:
    //Use the 1st element as the pivot
    public void partition1(int first, int last) {
        if(first >= last) {
            return;
        }

        int pivot = arr[first];
        int tb = first +1;
        int ts = last;

        while(tb <= ts) {
            while(tb <= ts && arr[tb] < pivot) {
                tb++;
            }

            while(tb <= ts && arr[ts] > pivot) {
                ts--;
            }

            if(tb <= ts) {
                int temp = arr[tb];
                arr[tb] = arr[ts];
                arr[ts] = temp;
                tb++;
                ts--;
            } else {
                break;
            }
        }
        arr[first] = arr[ts];
        arr[ts] = pivot;

        if(first < ts) {
            partition1(first, ts);
        } 
        else if(last > tb) {
            partition1(tb, last);
        }
        //return ts;  //This is pivotposition
    }

    //QuickSort Implementation 2:
    //If number of elements in arr is <= 16, then call Insertion Sort
    public void partition2(int first, int last) {
        if(first >= last) {
            return;
        }

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
            while(tb <= ts && arr[tb] < pivot) {
                tb++;
            }

            while(tb <= ts && arr[ts] > pivot) {
                ts--;
            }

            if(tb <= ts) {
                int temp = arr[tb];
                arr[tb] = arr[ts];
                arr[ts] = temp;
                tb++;
                ts--;
            } else {
                break;
            }
        }
        arr[first] = arr[ts];
        arr[ts] = pivot;

        if(first < ts) {
            partition2(first, ts);
        } 
        else if(last > tb) {
            partition2(tb, last);
        }
        //return ts;  //This is pivotposition
    }

    //QuickSort Implementation 3:
    //If number of elements >= 16, then use random index as pivotposition
    public void partition3(int first, int last) {
        if(first >= last) {
            return;
        }

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
            while(tb <= ts && arr[tb] < pivot) {
                tb++;
            }

            while(tb <= ts && arr[ts] > pivot) {
                ts--;
            }

            if(tb <= ts) {
                int temp = arr[tb];
                arr[tb] = arr[ts];
                arr[ts] = temp;
                tb++;
                ts--;
            } else {
                break;
            }
        }
        arr[first] = arr[ts];
        arr[ts] = pivot;

        if(first < ts) {
            partition3(first, ts);
        } 
        else if(last > tb) {
            partition3(tb, last);
        }
        //return ts;  //This is pivotposition
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

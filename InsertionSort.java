public class InsertionSort {

    public int[] insertionSort(int[] arr) {
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
        return arr;
    }
}
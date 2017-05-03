public class MergeSort {
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

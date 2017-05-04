import java.util.Random;

public class QuickSort {
	private int[] arr;

	public QuickSort(int[] arr) {
		this.arr = arr;
	}

	public void quickSort1(int left, int right) {
		if (left < right) {
			int pivotIndex = left;
			int pos = partition(left, right, pivotIndex);
			quickSort1(left, pos - 1);
			quickSort1(pos + 1, right);
		}
	}

	public void quickSort2(int left, int right) {
		if((left - right) <= 16) {
			insertionSort(arr);
		} else {
			quickSort1(left, right);
		}
	}

	public void quickSort3(int left, int right) {
		if(left < right) {
			partitionRandom(left, right);
			int pivotIndex = left;
			int pivotposition = partition(left, right, pivotIndex);
			quickSort1(left, (pivotposition-1));
			quickSort1((pivotposition+1), right);
		}
	}
	
	public int partition(int left, int right, int pivotIndex) {
		int pivot = arr[pivotIndex];
        swap(pivotIndex, right);
        int pos = left;

        for(int i = left; i < right; i++) {
            if(arr[i]<= pivot) {
                swap(i, pos);
                pos++;
            }   
        }

        swap(pos,right);
        return pos;
	}

	public void partitionRandom(int first, int last) {
        //Select random index
        if((last- (first+1)) > 16) {
            Random rand = new Random();
            int randIndex = first + rand.nextInt() % (last-first+1);

            if(randIndex < 0) {
                randIndex *= -1;
            }

            int temp = arr[first];
            arr[first] = arr[randIndex];
            arr[randIndex] = temp;
        }
	}

	public int[] insertionSort(int[] arr) {
		for (int i=1; i < arr.length; i++) {
			int value = arr[i];
			int j = i-1;

			while (j >= 0 && arr[j] > value) {
				arr[j+1] = arr[j];
				j = j-1;
			}

			arr[j+1] = value;
		}
		return arr;
	}

    public void swap(int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t; 
    }  

    public int[] getSortedArr() {
        return arr;
    }
    
}
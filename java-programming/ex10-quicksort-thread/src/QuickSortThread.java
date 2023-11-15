public class QuickSortThread extends Thread {
    private int[] array;
    private int low, high;

    public QuickSortThread(int[] array) {
        this(array, 0, array.length - 1);
    }

    public QuickSortThread(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    public void run() {
        quickSort(array, low, high);
    }

    private void quickSort(int[] array, int low, int high) {
        if ( low < high ) {
            int pivotIndex = partition();
            QuickSortThread left = new QuickSortThread(array, low, pivotIndex - 1);
            QuickSortThread right = new QuickSortThread(array, pivotIndex + 1, high);
            left.start();
            right.start();
            try {
                left.join();
                right.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int partition() {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j]= tmp;
    }

    public int[] getArray() {
        return array;
    }

}

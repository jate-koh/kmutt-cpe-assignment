import utils.*;

import java.util.ArrayList;

public class Quicksort {

    private ArrayList<Integer> numbers;
    private int pivot;
    private int low, high;

    public Quicksort(ArrayList<Integer> numbers) {
        this(numbers, 0, numbers.size() - 1);
    }

    public Quicksort(ArrayList<Integer> numbers, int low, int high) {
        this.numbers = numbers;
        this.low = low;
        this.high = high;

        this.pivot = numbers.get(high);
        Logger.logMessage("Pivot = " + pivot + ", Length = " + numbers.size(), this);
    }

    public void sort() {
        SortTools.printArray(numbers, this);
        int pivotIndex = partition();

        Logger.logMessage("Pivot Index = " + pivotIndex, this);

        if (pivotIndex - 1 > low) {
            Logger.logMessage("Sorting Left: ", this);
            Quicksort left = new Quicksort(numbers, low, pivotIndex - 1);
            left.sort();
        }

        if (pivotIndex + 1 < high) {
            Logger.logMessage("Sorting Right: ", this);
            Quicksort right = new Quicksort(numbers, pivotIndex + 1, high);
            right.sort();
        }

        SortTools.printArray(numbers, this);
    }

    private int partition() {
        int i = low - 1;
        for (int j = low; j < high - 1 ; j++) {
            if (numbers.get(j) < pivot) {
                i++;

                Logger.logMessage("Swapping " + numbers.get(i) + " and " + numbers.get(j), this);
                SortTools.swap(numbers, i, j);
            }

            Logger.logMessage("Swapping " + numbers.get(i + 1) + " and " + numbers.get(high), this);
        }
        SortTools.swap(numbers, i + 1, high);
        return i + 1;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }
}

import utils.SortTools;

import java.util.ArrayList;

public class Main {
    static int x = 3;

    public static void main(String[] args) {

        for (; x >= 0; x--) {
            switch(x) {
                case 1:
                    System.out.print(x);
                case 2:
                    System.out.print(x--);
                case 3:
                    System.out.print("many");
            }
        }

//        // Create an array of random integers
//        int[] array = new int[10];
//
//        for (int i = 0; i < array.length; i++) {
//            array[i] = (int)(Math.random() * 1000);
//        }
//
//        // Print the array
//        SortTools.printArray(array, "Main");
//
//        // Start the quicksort thread
//        QuickSortThread thread = new QuickSortThread(array);
//        thread.start();
//
//        // Wait for the thread to finish
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Print the sorted array
//        SortTools.printArray(thread.getArray(), "Main");

    }
}
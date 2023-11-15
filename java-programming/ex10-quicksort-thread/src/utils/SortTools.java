package utils;

import java.util.ArrayList;

public class SortTools {

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + 1];
        for (int i = 0; i < left.length; i++)
            result[i] = left[i];
        for (int i = 0; i < right.length; i++)
            result[i + left.length] = right[i];
        return result;
    }

    public static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < left.size(); i++)
            result.add(left.get(i));
        for (int i = 0; i < right.size(); i++)
            result.add(right.get(i));
        return result;
    }

    public static void printArray(int[] array, Object logger) {
        String arrayString = "Array: { ";
        for(int i = 0; i < array.length; i++) {
            arrayString += array[i] + " ";
        }
        arrayString += "}";
        Logger.logMessage(arrayString, logger);
    }

    public static void printArray(ArrayList<Integer> array, String logger) {
        String arrayString = "Array: { ";
        for(int i = 0; i < array.size(); i++) {
            arrayString += array.get(i) + " ";
        }
        arrayString += "}";
        Logger.logMessage(arrayString, logger);
    }

    public static void printArray(ArrayList<Integer> array, Object logger) {
        String arrayString = "Array: { ";
        for(int i = 0; i < array.size(); i++) {
            arrayString += array.get(i) + " ";
        }
        arrayString += "}";
        Logger.logMessage(arrayString, logger);
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j]= tmp;
    }

    public static void swap(ArrayList<Integer> array, int i, int j) {
        int tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

}

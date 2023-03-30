package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {

    /**
     * Log message with time stamp and name of class that called the method.
     * @param name the name of the class that called the method.
     * @param message the message to log.
     */
    public static void logMessage(String name, String message, boolean newLine) {
        String log = "["+ getTimeStamps() +"] [" + name + "]: " + message;
        if(newLine) System.out.println(log);
        else System.out.print(log);
    }

    /**
     * Log array with time stamp and name of class that called the method.
     * @param name the name of the class that called the method.
     * @param array the array to log.
     * @param printPerLine the number of elements to print per line.
     */
    public static void logArray(String name, ArrayList<Double> array, int printPerLine) {
        int counter = 1;
        StringBuilder log = new StringBuilder("[" + getTimeStamps() + "] [" + name + "]: { ");
        for (Double iteration : array) {
            if( !(counter == array.size()) ) log.append(iteration).append(", ");
            else log.append(iteration).append(" }");
            counter++;
            if( counter % printPerLine == 0) log.append("\n");
        }
        System.out.print(log + "\n");
    }

    /**
     * Get current time in hh:mm:ss format
     * @return time return current time in hh:mm:ss format
     */
    public static String getTimeStamps() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        Date date = new Date(currentTime);
        String time =  simpleDateFormat.format(date);

        return time;
    }

}

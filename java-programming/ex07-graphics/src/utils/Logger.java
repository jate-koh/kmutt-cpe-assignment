package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void logMessage(String message) {
        String log = "GENERAL " + "[" + getTimeStamps() + "]: " + message;
        System.out.println(log);
    }

    public static void logMessage(String message, String logger) {
        String log = logger + " " + "[" + getTimeStamps() + "]: " + message;
        System.out.println(log);
    }

    public static void logMessage(String message, Object object) {
        String log = object.getClass().getSimpleName() + " " + "[" + getTimeStamps() + "]: " + message;
        System.out.println(log);
    }

    public static void logWarning(String message, Object object) {
        String log = object.getClass().getSimpleName() + " (Warning) " + "[" + getTimeStamps() + "]: " + message;
        System.err.println(log);
    }

    public static void logError(String message, Object object) {
        String log = object.getClass().getSimpleName() + " (Error) " + "[" + getTimeStamps() + "]: " + message;
        System.err.println(log);
    }

    private static String getTimeStamps() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        Date date = new Date(currentTime);
        String time =  simpleDateFormat.format(date);

        return time;
    }
}

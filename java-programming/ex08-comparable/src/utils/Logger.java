package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void logMessage(String message) {
        String log = "General " + "[" + getTimeStamps() + "]: " + message;
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

    public static void logError(String message, Object object, Exception exception) {
        String log = "";
        if (message != null) {
            log = object.getClass().getSimpleName() + " " + "[" + getTimeStamps() + "]: " + message;
            System.out.println(log);
        }

        log = "Exception: " + exception.getClass().getSimpleName();
        System.out.println(log);

        log = "Message: " + exception.getMessage();
        System.out.println(log);

        log = "Cause: " + exception.getCause();
        System.out.println(log);

        log = "Stack Trace: " + exception.getStackTrace();
        System.out.println(log);
    }

    private static String getTimeStamps() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        Date date = new Date(currentTime);
        String time =  simpleDateFormat.format(date);

        return time;
    }
}

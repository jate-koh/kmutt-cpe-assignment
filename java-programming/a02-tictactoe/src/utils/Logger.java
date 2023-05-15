package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static StringBuilder backLog = new StringBuilder();
    private static StringBuilder previousLog = new StringBuilder();

    public static StringBuilder getBackLog() {
        return backLog;
    }

    public static StringBuilder getPreviousLog() {
        return previousLog;
    }

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

    public static void logMessage(String message, Object object, boolean saveLog) {
        String log = object.getClass().getSimpleName() + " " + "[" + getTimeStamps() + "]: " + message;
        if(saveLog) {
            previousLog = new StringBuilder();
            previousLog.append(log);
            backLog.append(log);
            backLog.append("\n");
        }
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

        log = "Stack Trace: " + "\n";
        for ( StackTraceElement stackTraceElement : exception.getStackTrace() ) {
            log += stackTraceElement.toString() + "\n";
        }
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

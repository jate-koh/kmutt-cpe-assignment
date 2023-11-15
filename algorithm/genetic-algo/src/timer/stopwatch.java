package timer;

public class stopwatch {
	private static double StartTime;
	private static double StopTime;

    public static void Timestart(){
        StartTime = System.nanoTime();
    }
    public static void TimeStop(){
        StopTime = System.nanoTime();
    }
    public static double Time(){
        return (StopTime-StartTime)/1E6;
    }
}

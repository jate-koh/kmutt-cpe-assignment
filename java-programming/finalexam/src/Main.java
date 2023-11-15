import java.util.ArrayList;

public class Main {
    static void m1(int a) {
        a = 5;
    }

    public static void main(String[] args) {

        int x = 0;
        m1(x);
        System.out.println(x);



//        ArrayList<Thread> threadList = new ArrayList<Thread>();
//
//        Thread t1 = new Thread(new CountingThread());
//        Thread t2 = new Thread(new CountingThread());
//
//        threadList.add(t1);
//        threadList.add(t2);
//
//        // Start threads same time
//        for (Thread t : threadList) {
//            t.start();
//        }
//
//        // Wait for threads to finish
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted!");
//        }
//
//        System.out.println("All Done!");
    }
}
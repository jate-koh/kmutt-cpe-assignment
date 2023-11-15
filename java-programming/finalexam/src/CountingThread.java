public class CountingThread implements Runnable {

    private int counter;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter++;
            System.out.println(counter);
        }

    }
}

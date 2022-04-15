package LocalThreadVariables;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Creates the unsafe task
        UnsafeTask task = new UnsafeTask();

        // Throw ten Threads objects
        for(int i=0; i<10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

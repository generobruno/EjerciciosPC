package ControllingConcurrentAccessToAResource;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    // Creates a lock to control the access to the queue
    private final Semaphore semaphore;

    public PrintQueue() {
        semaphore = new Semaphore(1);
    }

    // Method that prints the job. The printing is divided in 2 phases.
    public void printJob(Object document) {
        try {
            // Get the access to the semaphore. If other job is printing,
            // this thread sleep until get the access to the semaphore.
            semaphore.acquire();

            Long duration = (long) (Math.random()*10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds.\n",
                    Thread.currentThread().getName(),(duration/1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Free the semaphore. If there are other threads waiting for the
            // semaphore, the JVM selects one of this threads and give it the access.
            semaphore.release();
        }

    }

}

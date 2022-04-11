package ControllingConcurrentAccessToMultipleCopiesOfAResource;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    // Creates a lock to control the access to the queue
    private final Semaphore semaphore;
    // Array to control what printer is free
    private boolean freePrinters[];

    // Lock to control the access to the freePrinters array.
    private Lock lockPrinters;

    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        for (int i=0; i<3; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    // Method that prints the job. The printing is divided in 2 phases.
    public void printJob(Object document) {
        try {
            // Get the access to the semaphore. If other job is printing,
            // this thread sleep until get the access to the semaphore.
            semaphore.acquire();

            // Get the number of the free printer
            int assignedPrinter = getPrinter();

            Long duration = (long) (Math.random()*10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds.\n",
                    Thread.currentThread().getName(),(duration/1000));
            Thread.sleep(duration);

            // Free the printer
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Free the semaphore. If there are other threads waiting for the
            // semaphore, the JVM selects one of this threads and give it the access.
            semaphore.release();
        }

    }

    private int getPrinter() {
        int ret = -1;

        try {
            // Get the access to the array
            lockPrinters.lock();
            // Look fot the first free printer
            for(int i = 0; i < freePrinters.length; i++) {
                if(freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }

        return ret;
    }

}

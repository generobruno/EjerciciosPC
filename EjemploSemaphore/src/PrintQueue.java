import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the PrintQueue using a Semaphore
 * to control the access to it.
 */
public class PrintQueue {
    /**
     * Semaphore to control the access to the queue
     */
    private final Semaphore semaphore;

    /**
     * Contructor of the class. Initializes the Semaphore.
     */
    public PrintQueue() {
        semaphore = new Semaphore(1);
    }

    /**
     * Method that simulates printing a document.
     * @param document Document to print.
     */
    public void printJob (Object document) {
        try {
            //Get the acces to the semaphore. If other job is printing,
            // this thread sleeps until get the access to the semaphore
            semaphore.acquire();

            Long duration = (long)(Math.random()*3);
            System.out.printf("%s: PrintQueue: Printing the Job during %d seconds\n",
                    Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Free the semaphore. If there are other threads waiting for this
            //semaphore, the JVM selects one of this threads and give it the access
            semaphore.release();
        }
    }

}

package ControllingConcurrentAccessToAResource;

public class Main {
    public static void main(String[] args) {
        // Creates the print queue
        PrintQueue printQueue = new PrintQueue();

        // Creates 10 jobs and the threads to run them
        Thread thread[] = new Thread[10];
        for(int i=0; i<10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // Launch the threads
        for (int i=0; i<10; i++) {
            thread[i].start();
        }


    }
}

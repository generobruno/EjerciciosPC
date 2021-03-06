public class Main {
    /**
     * Main method of the class. Run ten jobs in parallel that
     * send documents to the print queue at the same time.
     */
    public static void main(String[] args) {

        //Creates the print queue
        PrintQueue printQueue = new PrintQueue();

        //Creates ten threads
        Thread thread[] = new Thread[10];
        for (int i=0; i<10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        //Starts the threads
        for (int i=0; i<10; i++) {
            thread[i].start();
        }
    }

}

package DaemonThreads;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        // Creates the Event Data structure
        Deque<Event> deque = new ConcurrentLinkedDeque<>();
        // Creates the three WriterTask and starts them
        WriterTask writer = new WriterTask(deque);
        for (int i=0; i<Runtime.getRuntime().availableProcessors();i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        // Creates a cleaner task and starts them
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
}

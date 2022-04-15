package DaemonThreads;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Clase WriterTask: Implementa Runnable.
 * Declara la cola que almacena los eventos e implementa el constructor
 * que inicializa la cola.
 * El método run tiene un loop de 100 iteraciones donde se crea un
 * new Event, se guarda en la cola y duerme por 1 segundo.
 */
public class WriterTask implements Runnable {
    Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        //Writes 100 events
        for(int i=0; i<100; i++) {
            // Creates and initializes the Event Objects
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated the event %d event",
                    Thread.currentThread().getId(),i));
            // Add to the data structure
            deque.addFirst(event);
            try {
                // Sleeps during one second
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

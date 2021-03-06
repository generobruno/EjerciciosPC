package Changing_data_between_concurrent_tasks;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {
    // Buffer to save the events produced
    private List<String> buffer;
    // Exchanger to synchronize with the consumer
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;

        for(int i=0;i<10;i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                // Wait for the produced data and send the empty buffer to the producer
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Consumer: %d\n", buffer.size());

            for(int j=0;j<10;j++) {
                String message = buffer.get(0);
                System.out.printf("Consumer: %s\n", message);
                buffer.remove(0);
            }

            cycle++;
        }

    }
}

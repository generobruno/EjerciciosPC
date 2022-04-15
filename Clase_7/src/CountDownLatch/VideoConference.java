package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {
    private final CountDownLatch controller;

    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        // This method uses the countDown method to decrement the internal counter
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n",
                controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n",
                controller.getCount());
        try {
            // Wait for all the participants
            controller.await();
            // Starts the conference
            System.out.print("VideoConference: All the participants have come.\n");
            System.out.print("VideoConference: Let's start...\n");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

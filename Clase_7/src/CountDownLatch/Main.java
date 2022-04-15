package CountDownLatch;

public class Main {
    public static void main(String[] args) {
        // Creates a VideoConference with 10 participants
        VideoConference conference = new VideoConference(10);
        // Creates a thread to run the videoconference and starts it
        Thread threadConference = new Thread(conference);
        threadConference.start();

        // Creates 10 participants, a thread for each one
        for(int i=0; i<10; i++) {
            Participant p = new Participant(conference, "Participant "+i);
            Thread t = new Thread(p);
            t.start();
        }

    }
}

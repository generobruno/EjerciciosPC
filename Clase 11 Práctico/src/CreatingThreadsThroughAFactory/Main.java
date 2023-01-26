public class Main {
    public static void main(String[] args) {
        // Creates the factory
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        // Creates a task
        Task task = new Task();
        Thread thread;

        // Creates and starts 10 Thread objects
        System.out.println("Starting the Threads\n");
        for(int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }

        // Prints the statistics of the ThreadFactory to the console
        System.out.println("Factory stats:\n");
        List<String> stats = factory.getStats();
        stats.forEach(System.out::println);
    }
}
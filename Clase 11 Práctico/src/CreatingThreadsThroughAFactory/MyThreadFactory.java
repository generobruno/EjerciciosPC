public class MyThreadFactory implements ThreadFactory {

    // Atributes to save the necessary data to the factory
    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        counter = 0;
        this.name = name;
        stats = new ArrayList<String>();
    }

    public Thread newThread(Runnable r) {
        // Create the new Thread object
        Thread t = new Thread(r, name + "-Thread_" + counter);
        counter++;
        // Actualize the statistics of the factory
        stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));

        return t;
    }

    public List<String> getStats() {
        return stats;
    }
}
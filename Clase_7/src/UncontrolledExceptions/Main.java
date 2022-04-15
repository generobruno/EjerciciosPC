package UncontrolledExceptions;

/**
 * Clase Main: Crea un objeto de la clase Task y un thread para correrlo.
 * Setea el handler de las excepciones con el método setUncaughtExceptionHandler()
 */
public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Thread has finished.\n");

    }

}

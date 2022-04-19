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

        // El try-catch no recibirá excepción, ya que esta no la lanza el main
        // sino, el hilo de task.
        //try {
            thread.start();
        //} catch (NumberFormatException nfe) {
        //    System.out.println("I got the exception");
        //}

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Thread has finished.\n");

    }

}

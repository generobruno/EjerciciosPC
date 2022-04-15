package UncontrolledExceptions;

/**
 * Clase Task: Lanza una excepción no chequeada en tiempo de ejecución
 * Implementa la interfaz Runnable
 * Fuerza la excepción, tratando de convertir un string a un int
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int number = Integer.parseInt("TTT");
        // This sentence will never be executed
        System.out.printf("Number: %d ", number);
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        // Thread priority information
        System.out.printf("Minimum priority: %s\n",
                Thread.MIN_PRIORITY);
        System.out.printf("Normal priority: %s\n",
                Thread.NORM_PRIORITY);
        System.out.printf("Maximum priority: %s\n",
                Thread.MAX_PRIORITY);


         //La prioridad es un numero que va del 1 al 10.
         //Mientras mayor es el numero, mayor la
         //prioridad que se le da al thread.


        // Inicializamos array para almacenar 10 hilos.
        Thread threads[];
        Thread.State status[];

         //Launch 10 threads to do the operation, 5 with
         //the max priority, 5 with the min.

        threads = new Thread[10];
        status = new Thread.State[10];

        for(int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator());
            if ((i%2) == 0) { //Hilos pares tienen max prioridad
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else { //Hilos impares tienen min prioridad
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("My Thread " + i);
        }


         //Wait for the finalization of the threads. Meanwhile,
         //write the status of those threads in a file.

        try (FileWriter file = new FileWriter(".\\data\\log.txt"); PrintWriter pw = new PrintWriter(file)) {

            // Write the status of thr threads
            for (int i = 0; i < 10; i++) {
                pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            //Start the ten threads
            for (int i = 0; i < 10; i++) {
                threads[i].start();
                //threads[i].run();
            }



             //Wait for the finalization of the threads. We save
             //the status of the threads and only write the status
             //if it changes.

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }

                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Imprime la información de los hilos
     * @param pw Archivo
     * @param thread Hilo
     * @param state Estado del hilo
     */
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : *****************************************\n");
    }

}

package tareas;

public class Tarea {

    public Tarea(){
        System.out.printf("Desde Objeto Clase Tarea, ejecucion hilo %s",
                Thread.currentThread().getName());

        Calculator Calc = new Calculator(4);
        Thread H1 = new Thread(Calc);

        //H1.start();  // .start() ejecuta el Hilo0
        H1.run(); // .run() ejecuta el metodo de una clase, utilizando el hilo Main

        /**
         *  Con start() se asignan todos los recursos para
         *  crear un hilo, a parte de main, que ejecute
         *  las tareas. En este caso hay 2 hilos activos y
         *  podría existir concurrencia.
         *
         *  En cambio, con run(), se esta ejecutando el
         *  metodo de una clase desde el main hilo del main.
         *  No hay interleaving posible, es estrictamente
         *  secuencial.
         */
    }

}

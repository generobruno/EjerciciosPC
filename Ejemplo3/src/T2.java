public class T2 extends Tarea {

    public T2() {}

    public void run() {
        while (true) {
            Tarea.y2 = Tarea.y1 + 1; // "INSTRUCCION ESTRELLA 2"

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Espera activa hasta que se cumpla alguna de las condiciones
            while ((!(Tarea.y1 == 0) && !(Tarea.y2 < Tarea.y1))) { }

            Tarea.critical++;

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            Tarea.critical--;
            Tarea.y2 = 0;

            if(Tarea.critical != 0) {
            System.out.println("Valor CRITICAL desde T2 = " + (Tarea.critical));
            System.out.println("Valor Y1 desde T2 = " + (Tarea.y1));
            System.out.println("Valor Y2 desde T2 = " + (Tarea.y2));
             }

        }
    }
}

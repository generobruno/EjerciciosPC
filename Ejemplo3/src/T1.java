public class T1 extends Tarea{
    public T1() {}

    public void run() {
        while (true) {
            Tarea.y1 = Tarea.y2 + 1;

            while ((!(Tarea.y2 == 0) && !(Tarea.y1 <= Tarea.y2))) {
            }

            //Instruccion de Sleep por 0 milisegundos.
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Tarea.critical++;
            Tarea.critical--;
            Tarea.y1 = 0;

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            if(Tarea.critical != 0) {
            System.out.println("Valor CRITICAL desde T1 = " + (Tarea.critical));
            System.out.println("Valor Y1 desde T1 = " + (Tarea.y1));
            System.out.println("Valor Y2 desde T1 = " + (Tarea.y2));
             }

        }
    }

}

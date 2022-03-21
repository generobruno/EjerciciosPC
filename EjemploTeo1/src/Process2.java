public class Process2 implements Runnable {

    private Variable v;
    private String s;

    public Process2 (Variable variable) {
        this.v = variable;
    }

    public void run() {
        while(true) {
            v.y2 = v.y1 + 1;

            while(!(v.y1 == 0 || v.y2 < v.y1)) {}

            v.inCritical++;
            v.inCritical--;
            v.y2 = 0;

            System.out.print("Process 2 - inCritical   ");
            System.out.println(v.inCritical);
        }
    }

}

public class Process1 implements Runnable {
    private Variable v;

    public Process1(Variable variable) {
        this.v = variable;
    }

    public void run() {
        while (true) {
            v.y1 = v.y2 +1;

            while (!(v.y2 == 0 || v.y1 < v.y2)) { }

            v.inCritical++;
            v.inCritical--;
            v.y1 = 0;

            System.out.print("Process 1 - inCritical   ");
            System.out.println(v.inCritical);
        }
    }

}

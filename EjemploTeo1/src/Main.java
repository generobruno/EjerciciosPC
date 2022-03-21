public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Variable variable = new Variable();

        Process1 p1 = new Process1(variable);
        Process2 p2 = new Process2(variable);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t2.start();

        System.out.println("Bye World!");
    }
}

public class ThreadApp2 {
    public static void main(String[] args) {
        Buffer b = new Buffer();
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        p.start();
        c.start();

        try { //Espera la pulsacion de una tecla y RETURN
            System.in.read();
        } catch (Exception e) { }
    }
}

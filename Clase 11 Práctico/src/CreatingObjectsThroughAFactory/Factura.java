public abstract class Factura {

    protected int id;
    protected double importe;

    public abstract double getImporteIva();

    public Factura(int id, double importe) {
        this.id = id;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

}

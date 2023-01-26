public class FacturaB extends Factura {

    public FacturaB(int id, double importe) {
        super(id, importe);
    }

    public double getImporteIva() {
        return this.importe * 1.21;
    }
}
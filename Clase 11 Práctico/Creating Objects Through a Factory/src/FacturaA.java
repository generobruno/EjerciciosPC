public class FacturaA extends Factura {

    public FacturaA(int id, double importe) {
        super(id, importe);
    }

    public double getImporteIva() {
        return this.importe * 1.07;
    }
}
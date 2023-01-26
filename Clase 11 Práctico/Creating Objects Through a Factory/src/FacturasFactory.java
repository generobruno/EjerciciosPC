public class FacturasFactory {

    private int facturasContador;

    public FacturasFactory() {
        this.facturasContador = 1;
    }

    public Factura getFactura(String tipoCliente, double importe) {
        Factura factura;

        if(tipoCliente.equals("monotributista") || tipoCliente.equals("responsable inscripto")) {
            factura = new FacturaA(facturasContador, importe);
        } else {
            factura = new FacturaB(facturasContador, importe);
        }

        this.facturasContador++;

        return factura;
    }
}
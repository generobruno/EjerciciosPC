public class Main {
    public static void main(String[] args) {
        FacturasFactory facturasFactory = new FacturasFactory();

        Factura f1 = facturasFactory.getFactura("monotributista", 1000);
        Factura f2 = facturasFactory.getFactura("comun", 1000);

        System.out.println(String.format("ID de f1: %d. Importe IVA: %.2f", f1.getId(), f1.getImporteIva()));
        System.out.println(String.format("ID de f2: %d. Importe IVA: %.2f", f2.getId(), f2.getImporteIva()));
    }
}
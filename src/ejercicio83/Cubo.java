package ejercicio83;

public class Cubo extends FiguraGeometrica {

    private final double arista;

    public Cubo(double arista) {
        super("Cubo");
        this.arista = validarMedida("arista", arista);
        actualizarMedidas();
    }

    @Override
    public double calcularVolumen() {
        return Math.pow(arista, 3);
    }

    @Override
    public double calcularSuperficie() {
        return 6 * Math.pow(arista, 2);
    }
}

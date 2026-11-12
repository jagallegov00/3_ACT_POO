package ejercicio83;

public class Esfera extends FiguraGeometrica {

    private final double radio;

    public Esfera(double radio) {
        super("Esfera");
        this.radio = validarMedida("radio", radio);
        actualizarMedidas();
    }

    @Override
    public double calcularVolumen() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radio, 3);
    }

    @Override
    public double calcularSuperficie() {
        return 4 * Math.PI * Math.pow(radio, 2);
    }
}

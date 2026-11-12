package ejercicio83;

public class Piramide extends FiguraGeometrica {

    private final double base;
    private final double altura;
    private final double apotema;

    public Piramide(double base, double altura, double apotema) {
        super("Pirámide");
        this.base = validarMedida("base", base);
        this.altura = validarMedida("altura", altura);
        this.apotema = validarMedida("apotema", apotema);
        actualizarMedidas();
    }

    @Override
    public double calcularVolumen() {
        return Math.pow(base, 2) * altura / 3;
    }

    @Override
    public double calcularSuperficie() {
        double areaBase = Math.pow(base, 2);
        double areaLateral = 2 * base * apotema;
        return areaBase + areaLateral;
    }
}

package ejercicio83;

public class Prisma extends FiguraGeometrica {

    private final double largo;
    private final double ancho;
    private final double altura;

    public Prisma(double largo, double ancho, double altura) {
        super("Prisma rectangular");
        this.largo = validarMedida("largo", largo);
        this.ancho = validarMedida("ancho", ancho);
        this.altura = validarMedida("altura", altura);
        actualizarMedidas();
    }

    @Override
    public double calcularVolumen() {
        return largo * ancho * altura;
    }

    @Override
    public double calcularSuperficie() {
        return 2 * (largo * ancho + largo * altura + ancho * altura);
    }
}

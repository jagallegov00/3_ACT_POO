package ejercicio83;

public class Cilindro extends FiguraGeometrica {

    private final double radio;
    private final double altura;

    public Cilindro(double radio, double altura) {
        super("Cilindro");
        this.radio = validarMedida("radio", radio);
        this.altura = validarMedida("altura", altura);
        actualizarMedidas();
    }

    @Override
    public double calcularVolumen() {
        return Math.PI * altura * Math.pow(radio, 2);
    }

    @Override
    public double calcularSuperficie() {
        double areaLateral = 2 * Math.PI * radio * altura;
        double areaBases = 2 * Math.PI * Math.pow(radio, 2);
        return areaLateral + areaBases;
    }
}

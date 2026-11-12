package ejercicio83;

public abstract class FiguraGeometrica {

    private final String nombre;
    private double volumen;
    private double superficie;

    protected FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public double getVolumen() {
        return volumen;
    }

    public double getSuperficie() {
        return superficie;
    }

    protected void actualizarMedidas() {
        volumen = calcularVolumen();
        superficie = calcularSuperficie();
    }

    protected static double validarMedida(String medida, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(
                    "La medida «" + medida + "» debe ser mayor que cero.");
        }
        return valor;
    }

    public abstract double calcularVolumen();

    public abstract double calcularSuperficie();
}

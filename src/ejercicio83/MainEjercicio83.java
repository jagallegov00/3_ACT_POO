package ejercicio83;

import comun.Estilo;
import javax.swing.SwingUtilities;

public class MainEjercicio83 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Estilo.aplicar();
            new VentanaFiguras().setVisible(true);
        });
    }
}

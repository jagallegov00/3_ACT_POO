package ejercicio82;

import comun.Estilo;
import javax.swing.SwingUtilities;

public class MainEjercicio82 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Estilo.aplicar();
            new VentanaNotas().setVisible(true);
        });
    }
}

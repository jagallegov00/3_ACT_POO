package comun;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelResultado extends JPanel {

    private final JLabel titulo;
    private final JTextArea detalle;

    public PanelResultado() {
        super(new BorderLayout(0, 4));
        setBackground(Estilo.FONDO_SUAVE);
        titulo = new JLabel("Resultado");
        titulo.setFont(Estilo.FUENTE_NEGRITA);
        titulo.setForeground(Estilo.TEXTO);
        detalle = new JTextArea(3, 30);
        detalle.setEditable(false);
        detalle.setLineWrap(true);
        detalle.setWrapStyleWord(true);
        detalle.setOpaque(false);
        detalle.setFont(Estilo.FUENTE);
        detalle.setForeground(Estilo.TEXTO);
        detalle.setBorder(null);
        add(titulo, BorderLayout.NORTH);
        add(detalle, BorderLayout.CENTER);
        setName("panelResultado");
        limpiar();
    }

    private void marcar(Color color) {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(3, 0, 0, 0, color),
                BorderFactory.createEmptyBorder(10, 12, 12, 12)));
    }

    public void limpiar() {
        marcar(Estilo.BORDE);
        titulo.setText("Resultado");
        detalle.setText("Ingrese los datos y presione el botón para ver el resultado.");
        detalle.setForeground(Estilo.TEXTO_TENUE);
    }

    public void mostrarExito(String encabezado, String texto) {
        marcar(Estilo.VERDE);
        titulo.setText(encabezado);
        detalle.setText(texto);
        detalle.setForeground(Estilo.TEXTO);
    }

    public void mostrarExcepcion(Exception excepcion) {
        mostrarExcepcion(excepcion, excepcion.getMessage());
    }

    public void mostrarExcepcion(Exception excepcion, String mensaje) {
        marcar(Estilo.AMARILLO);
        titulo.setText("Excepción capturada: " + excepcion.getClass().getSimpleName());
        detalle.setText(mensaje);
        detalle.setForeground(Estilo.TEXTO);
    }
}

package ejercicio82;

import comun.Estilo;
import comun.Formulario;
import comun.VentanaBase;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaNotas extends VentanaBase implements ActionListener {

    private final Notas notas = new Notas();
    private final JTextField[] camposNota = new JTextField[Notas.CANTIDAD_NOTAS];
    private final JButton botonCalcular =
            Estilo.nombrar(Estilo.botonPrimario("Calcular"), "botonCalcular");
    private final JButton botonLimpiar =
            Estilo.nombrar(Estilo.botonSecundario("Limpiar"), "botonLimpiar");
    private final JLabel valorPromedio = crearValor("valorPromedio");
    private final JLabel valorDesviacion = crearValor("valorDesviacion");
    private final JLabel valorMayor = crearValor("valorMayor");
    private final JLabel valorMenor = crearValor("valorMenor");

    public VentanaNotas() {
        super("Ejercicio 8.2. Notas", "Ejercicio 8.2. Notas",
                "Ingrese las cinco notas y presione «Calcular».");
        Formulario formulario = new Formulario();
        for (int i = 0; i < camposNota.length; i++) {
            camposNota[i] = Estilo.nombrar(Estilo.campo(10), "campoNota" + (i + 1));
            formulario.agregarCampo("Nota " + (i + 1), camposNota[i]);
        }
        botonCalcular.addActionListener(this);
        botonLimpiar.addActionListener(this);
        formulario.agregarBotones(botonCalcular, botonLimpiar);

        JPanel contenido = new JPanel(new BorderLayout(0, 16));
        contenido.add(formulario, BorderLayout.NORTH);
        contenido.add(crearPanelResultados(), BorderLayout.CENTER);
        establecerContenido(contenido);
        ajustarTamano(420);
    }

    private JLabel crearValor(String nombre) {
        JLabel valor = Estilo.nombrar(new JLabel("–"), nombre);
        valor.setFont(Estilo.FUENTE_NEGRITA);
        valor.setForeground(Estilo.TEXTO);
        return valor;
    }

    private JPanel crearPanelResultados() {
        JPanel resultados = new JPanel(new GridLayout(4, 2, 12, 6));
        resultados.setBackground(Estilo.FONDO_SUAVE);
        resultados.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(3, 0, 0, 0, Estilo.VERDE),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));
        String[] etiquetas = {"Promedio", "Desviación estándar", "Nota mayor", "Nota menor"};
        JLabel[] valores = {valorPromedio, valorDesviacion, valorMayor, valorMenor};
        for (int i = 0; i < etiquetas.length; i++) {
            JLabel etiqueta = new JLabel(etiquetas[i]);
            etiqueta.setForeground(Estilo.TEXTO);
            resultados.add(etiqueta);
            resultados.add(valores[i]);
        }
        return resultados;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            calcular();
        } else if (evento.getSource() == botonLimpiar) {
            limpiar();
        }
    }

    private void calcular() {
        for (int i = 0; i < camposNota.length; i++) {
            String texto = camposNota[i].getText().trim();
            if (texto.isEmpty()) {
                alertar("Debe ingresar la nota " + (i + 1) + ".", camposNota[i]);
                return;
            }
            try {
                notas.asignarNota(i, Double.parseDouble(texto.replace(',', '.')));
            } catch (NumberFormatException e) {
                alertar("La nota " + (i + 1) + " («" + texto + "») no es un valor numérico.",
                        camposNota[i]);
                return;
            }
        }
        valorPromedio.setText(formatear(notas.calcularPromedio()));
        valorDesviacion.setText(formatear(notas.calcularDesviacion()));
        valorMayor.setText(formatear(notas.calcularMayor()));
        valorMenor.setText(formatear(notas.calcularMenor()));
    }

    private void alertar(String mensaje, JTextField campo) {
        limpiarResultados();
        JOptionPane.showMessageDialog(this, mensaje, "Dato no válido",
                JOptionPane.WARNING_MESSAGE);
        campo.requestFocusInWindow();
    }

    private String formatear(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }

    private void limpiarResultados() {
        valorPromedio.setText("–");
        valorDesviacion.setText("–");
        valorMayor.setText("–");
        valorMenor.setText("–");
    }

    private void limpiar() {
        for (JTextField campo : camposNota) {
            campo.setText("");
        }
        limpiarResultados();
        camposNota[0].requestFocusInWindow();
    }
}

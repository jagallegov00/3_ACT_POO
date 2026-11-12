package ejercicio83;

import comun.Estilo;
import comun.Formulario;
import comun.PanelResultado;
import comun.VentanaBase;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class VentanaFiguras extends VentanaBase implements ActionListener {

    private final List<FiguraGeometrica> figurasCalculadas = new ArrayList<>();
    private final JLabel etiquetaCalculadas = Estilo.nota("Figuras calculadas en esta sesión: 0");

    private final JTextField campoRadioCilindro = campo("campoRadioCilindro");
    private final JTextField campoAlturaCilindro = campo("campoAlturaCilindro");
    private final JTextField campoRadioEsfera = campo("campoRadioEsfera");
    private final JTextField campoBasePiramide = campo("campoBasePiramide");
    private final JTextField campoAlturaPiramide = campo("campoAlturaPiramide");
    private final JTextField campoApotemaPiramide = campo("campoApotemaPiramide");
    private final JTextField campoAristaCubo = campo("campoAristaCubo");
    private final JTextField campoLargoPrisma = campo("campoLargoPrisma");
    private final JTextField campoAnchoPrisma = campo("campoAnchoPrisma");
    private final JTextField campoAlturaPrisma = campo("campoAlturaPrisma");

    private final JButton botonCilindro = boton("botonCilindro");
    private final JButton botonEsfera = boton("botonEsfera");
    private final JButton botonPiramide = boton("botonPiramide");
    private final JButton botonCubo = boton("botonCubo");
    private final JButton botonPrisma = boton("botonPrisma");

    private final PanelResultado resultadoCilindro = new PanelResultado();
    private final PanelResultado resultadoEsfera = new PanelResultado();
    private final PanelResultado resultadoPiramide = new PanelResultado();
    private final PanelResultado resultadoCubo = new PanelResultado();
    private final PanelResultado resultadoPrisma = new PanelResultado();

    public VentanaFiguras() {
        super("Ejercicio 8.3. Figuras geométricas", "Ejercicio 8.3. Figuras geométricas",
                "Seleccione una figura, ingrese sus medidas en centímetros "
                + "y presione «Calcular».");
        JTabbedPane pestanas = crearPestanas();
        pestanas.addTab("Cilindro", crearPaginaFigura(PanelDibujo.Tipo.CILINDRO, botonCilindro,
                resultadoCilindro, new String[]{"Radio (cm)", "Altura (cm)"},
                campoRadioCilindro, campoAlturaCilindro));
        pestanas.addTab("Esfera", crearPaginaFigura(PanelDibujo.Tipo.ESFERA, botonEsfera,
                resultadoEsfera, new String[]{"Radio (cm)"}, campoRadioEsfera));
        pestanas.addTab("Pirámide", crearPaginaFigura(PanelDibujo.Tipo.PIRAMIDE, botonPiramide,
                resultadoPiramide, new String[]{"Base (cm)", "Altura (cm)", "Apotema (cm)"},
                campoBasePiramide, campoAlturaPiramide, campoApotemaPiramide));
        pestanas.addTab("Cubo", crearPaginaFigura(PanelDibujo.Tipo.CUBO, botonCubo,
                resultadoCubo, new String[]{"Arista (cm)"}, campoAristaCubo));
        pestanas.addTab("Prisma", crearPaginaFigura(PanelDibujo.Tipo.PRISMA, botonPrisma,
                resultadoPrisma, new String[]{"Largo (cm)", "Ancho (cm)", "Altura (cm)"},
                campoLargoPrisma, campoAnchoPrisma, campoAlturaPrisma));
        JPanel contenido = new JPanel(new BorderLayout(0, 10));
        contenido.add(pestanas, BorderLayout.CENTER);
        contenido.add(etiquetaCalculadas, BorderLayout.SOUTH);
        establecerContenido(contenido);
        ajustarTamano(640);
    }

    private JTextField campo(String nombre) {
        return Estilo.nombrar(Estilo.campo(10), nombre);
    }

    private JButton boton(String nombre) {
        JButton boton = Estilo.nombrar(Estilo.botonPrimario("Calcular"), nombre);
        boton.addActionListener(this);
        return boton;
    }

    private JPanel crearPaginaFigura(PanelDibujo.Tipo tipo, JButton boton,
            PanelResultado resultado, String[] etiquetas, JTextField... campos) {
        Formulario formulario = new Formulario();
        for (int i = 0; i < campos.length; i++) {
            formulario.agregarCampo(etiquetas[i], campos[i]);
        }
        formulario.agregarBotones(boton);
        JPanel derecha = new JPanel(new BorderLayout(0, 12));
        derecha.add(formulario, BorderLayout.NORTH);
        derecha.add(resultado, BorderLayout.CENTER);
        JPanel fila = new JPanel(new BorderLayout(20, 0));
        fila.add(new PanelDibujo(tipo), BorderLayout.WEST);
        fila.add(derecha, BorderLayout.CENTER);
        return crearPagina(fila);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origen = evento.getSource();
        try {
            if (origen == botonCilindro) {
                mostrar(new Cilindro(leer(campoRadioCilindro, "radio"),
                        leer(campoAlturaCilindro, "altura")), resultadoCilindro);
            } else if (origen == botonEsfera) {
                mostrar(new Esfera(leer(campoRadioEsfera, "radio")), resultadoEsfera);
            } else if (origen == botonPiramide) {
                mostrar(new Piramide(leer(campoBasePiramide, "base"),
                        leer(campoAlturaPiramide, "altura"),
                        leer(campoApotemaPiramide, "apotema")), resultadoPiramide);
            } else if (origen == botonCubo) {
                mostrar(new Cubo(leer(campoAristaCubo, "arista")), resultadoCubo);
            } else if (origen == botonPrisma) {
                mostrar(new Prisma(leer(campoLargoPrisma, "largo"),
                        leer(campoAnchoPrisma, "ancho"),
                        leer(campoAlturaPrisma, "altura")), resultadoPrisma);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Dato no válido",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private double leer(JTextField campo, String medida) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar la medida «" + medida + "».");
        }
        try {
            return Double.parseDouble(texto.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "La medida «" + medida + "» debe ser un valor numérico.");
        }
    }

    private void mostrar(FiguraGeometrica figura, PanelResultado resultado) {
        figurasCalculadas.add(figura);
        etiquetaCalculadas.setText("Figuras calculadas en esta sesión: "
                + figurasCalculadas.size());
        resultado.mostrarExito(figura.getNombre(), String.format(Locale.US,
                "Volumen = %.2f cm³%nSuperficie = %.2f cm²",
                figura.getVolumen(), figura.getSuperficie()));
    }
}

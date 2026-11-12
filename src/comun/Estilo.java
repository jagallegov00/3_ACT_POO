package comun;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;

public final class Estilo {

    public static final Color FONDO = Color.WHITE;
    public static final Color FONDO_SUAVE = new Color(0xF4F5F1);
    public static final Color TEXTO = new Color(0x1D1D1B);
    public static final Color TEXTO_TENUE = new Color(0x6E6E6E);
    public static final Color BORDE = new Color(0xD3D5CC);
    public static final Color VERDE = new Color(0x94B43B);
    public static final Color AMARILLO = new Color(0xEAAD13);
    public static final Color OSCURO = new Color(0x464646);

    public static final Font FUENTE = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    public static final Font FUENTE_NEGRITA = FUENTE.deriveFont(Font.BOLD);
    public static final Font FUENTE_PEQUENA = FUENTE.deriveFont(12f);
    public static final Font FUENTE_TITULO = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    public static final Font FUENTE_CODIGO = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    private Estilo() {
    }

    public static void aplicar() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo aplicar el aspecto visual: " + e.getMessage());
        }
        String[] claves = {"Label.font", "TextField.font", "PasswordField.font", "TextArea.font",
            "Button.font", "ComboBox.font", "CheckBox.font", "TabbedPane.font", "List.font",
            "ToolTip.font", "OptionPane.messageFont", "FileChooser.listFont"};
        for (String clave : claves) {
            UIManager.put(clave, FUENTE);
        }
        UIManager.put("Panel.background", FONDO);
        UIManager.put("Label.foreground", TEXTO);
        UIManager.put("CheckBox.background", FONDO);
        UIManager.put("CheckBox.foreground", TEXTO);
        UIManager.put("ComboBox.background", FONDO);
        UIManager.put("ComboBox.selectionBackground", FONDO_SUAVE);
        UIManager.put("ComboBox.selectionForeground", TEXTO);
        UIManager.put("List.selectionBackground", FONDO_SUAVE);
        UIManager.put("List.selectionForeground", TEXTO);
        UIManager.put("ScrollPane.background", FONDO);
        UIManager.put("Viewport.background", FONDO);
    }

    public static Border bordeCampo() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(5, 7, 5, 7));
    }

    public static JTextField campo(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setBorder(bordeCampo());
        campo.setForeground(TEXTO);
        return campo;
    }

    public static JPasswordField campoContrasena(int columnas) {
        JPasswordField campo = new JPasswordField(columnas);
        campo.setBorder(bordeCampo());
        return campo;
    }

    public static JTextArea areaTexto(boolean codigo) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(codigo ? FUENTE_CODIGO : FUENTE);
        area.setForeground(TEXTO);
        area.setBackground(codigo ? FONDO_SUAVE : FONDO);
        area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        area.setLineWrap(!codigo);
        area.setWrapStyleWord(true);
        return area;
    }

    public static JButton botonPrimario(String texto) {
        JButton boton = new JButton(texto);
        boton.setUI(new BasicButtonUI());
        boton.setBackground(OSCURO);
        boton.setForeground(Color.WHITE);
        boton.setFont(FUENTE_NEGRITA);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(7, 16, 7, 16));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public static JButton botonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setUI(new BasicButtonUI());
        boton.setBackground(FONDO);
        boton.setForeground(TEXTO);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(6, 15, 6, 15)));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public static JLabel subtitulo(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE_NEGRITA);
        etiqueta.setForeground(TEXTO);
        return etiqueta;
    }

    public static JLabel nota(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE_PEQUENA);
        etiqueta.setForeground(TEXTO_TENUE);
        return etiqueta;
    }

    public static <T extends JComponent> T nombrar(T componente, String nombre) {
        componente.setName(nombre);
        return componente;
    }
}

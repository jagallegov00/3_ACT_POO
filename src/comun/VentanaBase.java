package comun;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public abstract class VentanaBase extends JFrame {

    protected VentanaBase(String tituloVentana, String titulo, String descripcion) {
        super(tituloVentana);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(Estilo.FONDO);
        raiz.add(crearEncabezado(titulo, descripcion), BorderLayout.NORTH);
        setContentPane(raiz);
    }

    private JPanel crearEncabezado(String titulo, String descripcion) {
        JPanel encabezado = new JPanel(new BorderLayout(0, 4));
        encabezado.setBackground(Estilo.FONDO);
        encabezado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Estilo.VERDE),
                BorderFactory.createEmptyBorder(16, 22, 12, 22)));
        JLabel etiquetaTitulo = new JLabel(titulo);
        etiquetaTitulo.setFont(Estilo.FUENTE_TITULO);
        etiquetaTitulo.setForeground(Estilo.TEXTO);
        JLabel etiquetaDescripcion = new JLabel(descripcion);
        etiquetaDescripcion.setForeground(Estilo.TEXTO_TENUE);
        encabezado.add(etiquetaTitulo, BorderLayout.NORTH);
        encabezado.add(etiquetaDescripcion, BorderLayout.CENTER);
        return encabezado;
    }

    protected void establecerContenido(JComponent contenido) {
        JPanel cuerpo = new JPanel(new BorderLayout());
        cuerpo.setBackground(Estilo.FONDO);
        cuerpo.setBorder(BorderFactory.createEmptyBorder(14, 22, 20, 22));
        cuerpo.add(contenido, BorderLayout.CENTER);
        getContentPane().add(cuerpo, BorderLayout.CENTER);
    }

    protected void ajustarTamano(int ancho) {
        pack();
        setSize(ancho, getHeight());
        setLocationRelativeTo(null);
    }

    protected JTabbedPane crearPestanas() {
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.setUI(new PestanasPlanas());
        pestanas.setBackground(Estilo.FONDO);
        pestanas.setForeground(Estilo.TEXTO);
        pestanas.setName("pestanas");
        return pestanas;
    }

    protected JPanel crearPagina(JComponent... componentes) {
        JPanel pagina = new JPanel(new BorderLayout(0, 14));
        pagina.setBackground(Estilo.FONDO);
        pagina.setBorder(BorderFactory.createEmptyBorder(14, 2, 2, 2));
        if (componentes.length > 0) {
            pagina.add(componentes[0], BorderLayout.NORTH);
        }
        if (componentes.length > 1) {
            JPanel envoltura = new JPanel(new BorderLayout());
            envoltura.setBackground(Estilo.FONDO);
            envoltura.add(componentes[1], BorderLayout.NORTH);
            pagina.add(envoltura, BorderLayout.CENTER);
        }
        if (componentes.length > 2) {
            pagina.add(componentes[2], BorderLayout.SOUTH);
        }
        return pagina;
    }

    private static class PestanasPlanas extends BasicTabbedPaneUI {

        @Override
        protected void installDefaults() {
            super.installDefaults();
            tabInsets = new java.awt.Insets(6, 14, 7, 14);
            selectedTabPadInsets = new java.awt.Insets(0, 0, 0, 0);
            contentBorderInsets = new java.awt.Insets(1, 0, 0, 0);
            tabAreaInsets = new java.awt.Insets(0, 0, 0, 0);
        }

        @Override
        protected int getTabLabelShiftY(int placement, int index, boolean selected) {
            return 0;
        }

        @Override
        protected int getTabLabelShiftX(int placement, int index, boolean selected) {
            return 0;
        }

        @Override
        protected void paintTabBackground(Graphics g, int placement, int index,
                int x, int y, int w, int h, boolean selected) {
            g.setColor(selected ? Estilo.FONDO : Estilo.FONDO_SUAVE);
            g.fillRect(x, y, w, h);
        }

        @Override
        protected void paintTabBorder(Graphics g, int placement, int index,
                int x, int y, int w, int h, boolean selected) {
            if (selected) {
                g.setColor(Estilo.VERDE);
                g.fillRect(x, y + h - 3, w, 3);
            }
        }

        @Override
        protected void paintFocusIndicator(Graphics g, int placement, Rectangle[] rects,
                int index, Rectangle iconRect, Rectangle textRect, boolean selected) {
        }

        @Override
        protected void paintContentBorder(Graphics g, int placement, int selectedIndex) {
            int y = calculateTabAreaHeight(placement, runCount, maxTabHeight);
            g.setColor(Estilo.BORDE);
            g.drawLine(0, y, tabPane.getWidth(), y);
        }
    }
}

package comun;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Formulario extends JPanel {

    private int fila = 0;

    public Formulario() {
        super(new GridBagLayout());
        setBackground(Estilo.FONDO);
        setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
    }

    public void agregarCampo(String etiqueta, JComponent campo) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = fila;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(4, 0, 4, 12);
        JLabel texto = new JLabel(etiqueta);
        texto.setForeground(Estilo.TEXTO);
        add(texto, c);

        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 0, 4, 0);
        add(campo, c);
        fila++;
    }

    public void agregarFilaCompleta(JComponent componente, int espacioSuperior) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = fila;
        c.gridwidth = 2;
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(espacioSuperior, 0, 4, 0);
        add(componente, c);
        fila++;
    }

    public void agregarBotones(JComponent... botones) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(Estilo.FONDO);
        for (int i = 0; i < botones.length; i++) {
            if (i > 0) {
                panel.add(javax.swing.Box.createHorizontalStrut(8));
            }
            panel.add(botones[i]);
        }
        agregarFilaCompleta(panel, 10);
    }
}

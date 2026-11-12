package ejercicio83;

import comun.Estilo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelDibujo extends JPanel {

    public enum Tipo {
        CILINDRO, ESFERA, PIRAMIDE, CUBO, PRISMA
    }

    private static final Color RELLENO = new Color(0x94, 0xB4, 0x3B, 45);
    private static final Stroke CONTINUO = new BasicStroke(1.6f);
    private static final Stroke OCULTO = new BasicStroke(1.2f, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 10f, new float[]{5f, 4f}, 0f);

    private final Tipo tipo;

    public PanelDibujo(Tipo tipo) {
        this.tipo = tipo;
        setPreferredSize(new Dimension(190, 190));
        setBackground(Estilo.FONDO_SUAVE);
        setBorder(BorderFactory.createLineBorder(Estilo.BORDE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.translate(getWidth() / 2 - 95, getHeight() / 2 - 95);
        g2.setFont(Estilo.FUENTE_PEQUENA);
        switch (tipo) {
            case CILINDRO -> dibujarCilindro(g2);
            case ESFERA -> dibujarEsfera(g2);
            case PIRAMIDE -> dibujarPiramide(g2);
            case CUBO -> dibujarCaja(g2, 50, 60, 70, 70, 26);
            case PRISMA -> dibujarCaja(g2, 30, 75, 110, 60, 30);
        }
        g2.dispose();
    }

    private void linea(Graphics2D g2, Stroke trazo, int x1, int y1, int x2, int y2) {
        g2.setStroke(trazo);
        g2.drawLine(x1, y1, x2, y2);
    }

    private void texto(Graphics2D g2, String texto, int x, int y) {
        g2.setColor(Estilo.TEXTO_TENUE);
        g2.drawString(texto, x, y);
        g2.setColor(Estilo.OSCURO);
    }

    private void dibujarCilindro(Graphics2D g2) {
        g2.setColor(RELLENO);
        g2.fillRect(50, 45, 90, 100);
        g2.fillOval(50, 130, 90, 30);
        g2.setColor(Estilo.OSCURO);
        g2.setStroke(CONTINUO);
        g2.drawOval(50, 30, 90, 30);
        g2.drawLine(50, 45, 50, 145);
        g2.drawLine(140, 45, 140, 145);
        g2.drawArc(50, 130, 90, 30, 180, 180);
        g2.setStroke(OCULTO);
        g2.drawArc(50, 130, 90, 30, 0, 180);
        linea(g2, OCULTO, 95, 45, 140, 45);
        texto(g2, "r", 112, 41);
        linea(g2, OCULTO, 150, 45, 150, 145);
        texto(g2, "h", 156, 100);
    }

    private void dibujarEsfera(Graphics2D g2) {
        g2.setColor(RELLENO);
        g2.fillOval(40, 40, 110, 110);
        g2.setColor(Estilo.OSCURO);
        g2.setStroke(CONTINUO);
        g2.drawOval(40, 40, 110, 110);
        g2.drawArc(40, 82, 110, 26, 180, 180);
        g2.setStroke(OCULTO);
        g2.drawArc(40, 82, 110, 26, 0, 180);
        linea(g2, OCULTO, 95, 95, 150, 95);
        g2.fillOval(92, 92, 6, 6);
        texto(g2, "r", 118, 90);
    }

    private void dibujarPiramide(Graphics2D g2) {
        int[] base = {40, 125, 150, 65};
        int[] baseY = {140, 140, 115, 115};
        int cimaX = 95;
        int cimaY = 30;
        g2.setColor(RELLENO);
        g2.fillPolygon(new Polygon(new int[]{40, 125, 150, cimaX},
                new int[]{140, 140, 115, cimaY}, 4));
        g2.setColor(Estilo.OSCURO);
        g2.setStroke(CONTINUO);
        g2.drawLine(base[0], baseY[0], base[1], baseY[1]);
        g2.drawLine(base[1], baseY[1], base[2], baseY[2]);
        g2.drawLine(cimaX, cimaY, base[0], baseY[0]);
        g2.drawLine(cimaX, cimaY, base[1], baseY[1]);
        g2.drawLine(cimaX, cimaY, base[2], baseY[2]);
        g2.setStroke(OCULTO);
        g2.drawLine(base[2], baseY[2], base[3], baseY[3]);
        g2.drawLine(base[3], baseY[3], base[0], baseY[0]);
        g2.drawLine(cimaX, cimaY, base[3], baseY[3]);
        g2.drawLine(cimaX, cimaY, cimaX, 127);
        g2.drawLine(cimaX, cimaY, 82, 140);
        texto(g2, "h", 99, 100);
        texto(g2, "ap", 64, 132);
        texto(g2, "b", 80, 156);
    }

    private void dibujarCaja(Graphics2D g2, int x, int y, int ancho, int alto, int fondo) {
        g2.setColor(RELLENO);
        g2.fillPolygon(new Polygon(
                new int[]{x, x + fondo, x + fondo + ancho, x + fondo + ancho, x + ancho, x},
                new int[]{y, y - fondo, y - fondo, y - fondo + alto, y + alto, y + alto}, 6));
        g2.setColor(Estilo.OSCURO);
        g2.setStroke(CONTINUO);
        g2.drawRect(x, y, ancho, alto);
        g2.drawLine(x, y, x + fondo, y - fondo);
        g2.drawLine(x + ancho, y, x + fondo + ancho, y - fondo);
        g2.drawLine(x + ancho, y + alto, x + fondo + ancho, y - fondo + alto);
        g2.drawLine(x + fondo, y - fondo, x + fondo + ancho, y - fondo);
        g2.drawLine(x + fondo + ancho, y - fondo, x + fondo + ancho, y - fondo + alto);
        g2.setStroke(OCULTO);
        g2.drawLine(x, y + alto, x + fondo, y - fondo + alto);
        g2.drawLine(x + fondo, y - fondo, x + fondo, y - fondo + alto);
        g2.drawLine(x + fondo, y - fondo + alto, x + fondo + ancho, y - fondo + alto);
        if (tipo == Tipo.CUBO) {
            texto(g2, "a", x + ancho / 2 - 3, y + alto + 16);
        } else {
            texto(g2, "l", x + ancho / 2 - 2, y + alto + 16);
            texto(g2, "an", x + ancho + fondo / 2 + 4, y + alto - fondo / 2 + 12);
            texto(g2, "h", x - 14, y + alto / 2 + 4);
        }
    }
}

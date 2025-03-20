package raven.menu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Menos extends JButton {

    private static final int BUTTON_SIZE = 50;
    private static final Color ICON_COLOR_DEFAULT = new Color(59, 130, 246); // Azul
    private static final Color ICON_COLOR_HOVER = Color.WHITE; // Blanco en hover
    private static final Color BORDER_COLOR = new Color(59, 130, 246); // Azul borde
    private static final Color BACKGROUND_COLOR_DEFAULT = new Color(0, 0, 0, 0); // Transparente
    private static final Color BACKGROUND_COLOR_HOVER = new Color(59, 130, 246); // Azul hover

    private boolean isHovered = false;

    public Menos() {
        super(""); // Asegurar que no tenga texto
        setFocusable(false); // Evita que reciba foco
        setOpaque(false); // Hace que el fondo sea completamente transparente
        setContentAreaFilled(false); // Elimina el área de contenido
        setBorderPainted(false); // No pinta el borde predeterminado
        setText(""); // Asegurar que no tenga texto
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        setBackground(BACKGROUND_COLOR_DEFAULT);

        // Detectar cuando el mouse entra y sale para cambiar el estado de hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Determinar colores según el estado de hover
        Color backgroundColor = isHovered ? BACKGROUND_COLOR_HOVER : BACKGROUND_COLOR_DEFAULT;
        Color iconColor = isHovered ? ICON_COLOR_HOVER : ICON_COLOR_DEFAULT;

        // Dibujar fondo del botón
        g2.setColor(backgroundColor);
        g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));

        // Dibujar borde azul alrededor del botón
        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(3)); // Grosor del borde
        g2.draw(new Ellipse2D.Double(1.5, 1.5, getWidth() - 3, getHeight() - 3));

        // Dibujar el signo menos (-) manualmente
        g2.setColor(iconColor);
        int barWidth = getWidth() / 4; // Ancho de la línea
        int barHeight = getHeight() / 16; // Grosor de la línea
        int x = (getWidth() - barWidth) / 2;
        int y = (getHeight() - barHeight) / 2;
        g2.fillRoundRect(x, y, barWidth, barHeight, 5, 5); // Linea redondeada para mejor estética

        g2.dispose();
    }
}

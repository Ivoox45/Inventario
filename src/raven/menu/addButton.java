package raven.menu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class addButton extends JButton {

    private static final int BUTTON_SIZE = 50;
    private static final Color BUTTON_COLOR = new Color(59, 130, 246);
    private static final String ICON_PATH = "/icon/plus.png";

    private boolean mousePress;
    private BufferedImage iconImage;

    public addButton() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        setBackground(BUTTON_COLOR);
        setText(null); // Elimina el texto

        // Cargar imagen del icono
        try {
            iconImage = ImageIO.read(getClass().getResourceAsStream(ICON_PATH));
        } catch (IOException | IllegalArgumentException e) {
            iconImage = null; // Asegurarse de que iconImage es null si hay un error
        }

        // Manejo de eventos del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    mousePress = true;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    mousePress = false;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo del botón
        g2.setColor(mousePress ? getBackground().darker() : getBackground());
        g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));

        // Dibujar icono centrado y escalado
        if (iconImage != null) {
            int iconWidth = getWidth() / 2;
            int iconHeight = getHeight() / 2;
            Image scaledIcon = iconImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            int x = (getWidth() - iconWidth) / 2;
            int y = (getHeight() - iconHeight) / 2;
            g2.drawImage(scaledIcon, x, y, this);
        }

        g2.dispose();
    }
}

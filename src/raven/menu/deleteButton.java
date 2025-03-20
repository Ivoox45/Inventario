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

public class deleteButton extends JButton {

    private static final int BUTTON_SIZE = 50;
    private static final Color ICON_COLOR_DEFAULT = new Color(59, 130, 246); // Azul
    private static final Color ICON_COLOR_HOVER = new Color(128, 0, 0); // Rojo vino
    private static final Color BACKGROUND_COLOR_HOVER = new Color(255, 0, 0, 20); // Rojo transparente en hover
    private static final String ICON_PATH = "/icon/delete.png"; // Ruta del ícono del basurero

    private BufferedImage iconImageDefault;
    private BufferedImage iconImageHover;
    private boolean isHovered = false;

    public deleteButton() {
        super(""); // Asegurar que no tenga texto
        setFocusable(false); // Evita que reciba foco
        setOpaque(false); // Hace que el fondo sea completamente transparente
        setContentAreaFilled(false); // Elimina el área de contenido
        setBorderPainted(false); // No pinta el borde predeterminado
        setText(""); // Asegurar que no tenga texto
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));

        // Cargar la imagen y modificar sus colores
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(ICON_PATH));
            iconImageDefault = recolorImage(originalImage, Color.BLACK, ICON_COLOR_DEFAULT); // Basurero azul
            iconImageHover = recolorImage(originalImage, Color.BLACK, ICON_COLOR_HOVER); // Basurero rojo vino
        } catch (IOException | IllegalArgumentException e) {
            iconImageDefault = null;
            iconImageHover = null;
        }

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
        Color backgroundColor = isHovered ? BACKGROUND_COLOR_HOVER : new Color(0, 0, 0, 0); // Transparente por defecto
        BufferedImage icon = isHovered ? iconImageHover : iconImageDefault;

        // Dibujar fondo del botón en hover
        g2.setColor(backgroundColor);
        g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));

        // Dibujar icono centrado y escalado
        if (icon != null) {
            int iconWidth = getWidth() / 2;
            int iconHeight = getHeight() / 2;
            Image scaledIcon = icon.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            int x = (getWidth() - iconWidth) / 2;
            int y = (getHeight() - iconHeight) / 2;
            g2.drawImage(scaledIcon, x, y, this);
        }

        g2.dispose();
    }

    /**
     * Método para cambiar el color de los píxeles negros de una imagen a otro
     * color.
     */
    private BufferedImage recolorImage(BufferedImage image, Color targetColor, Color newColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel, true);

                // Si el píxel es negro (o muy oscuro), lo cambiamos al nuevo color
                if (color.getRed() < 50 && color.getGreen() < 50 && color.getBlue() < 50) {
                    newImage.setRGB(x, y, newColor.getRGB());
                } else {
                    newImage.setRGB(x, y, pixel); // Mantiene la transparencia
                }
            }
        }
        return newImage;
    }
}

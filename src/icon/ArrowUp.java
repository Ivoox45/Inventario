/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package icon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author USER
 */
public class ArrowUp extends JComponent {

    private static final String ICON_PATH = "/icon/arrow-up.png"; // Ruta del PNG
    private static final int SIZE = 60; // Tamaño fijo (circular)
    private static final Color BACKGROUND_COLOR = new Color(34, 197, 94, 80);

    private BufferedImage image;

    public ArrowUp() {
        setPreferredSize(new Dimension(SIZE, SIZE));
        try {
            image = ImageIO.read(getClass().getResource(ICON_PATH));
            if (image != null) {

                image = recolorImage(image, new Color(34, 197, 94));
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            image = null;
        }
    }

    private BufferedImage recolorImage(BufferedImage img, Color newColor) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage recolored = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = img.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xFF; // Extraer canal alfa

                if (alpha > 0) { // Solo cambiar los píxeles visibles
                    recolored.setRGB(x, y, newColor.getRGB() & 0xFFFFFF | (alpha << 24));
                } else {
                    recolored.setRGB(x, y, pixel); // Mantener píxeles transparentes
                }
            }
        }
        return recolored;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo circular azul con transparencia
        g2.setColor(BACKGROUND_COLOR);
        g2.fill(new Ellipse2D.Double(0, 0, SIZE, SIZE));

        // Dibujar imagen PNG centrada
        if (image != null) {
            int iconSize = SIZE / 2;
            Image scaledImage = image.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            int x = (SIZE - iconSize) / 2;
            int y = (SIZE - iconSize) / 2;
            g2.drawImage(scaledImage, x, y, this);
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }
}

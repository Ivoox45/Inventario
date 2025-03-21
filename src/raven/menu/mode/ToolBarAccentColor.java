package raven.menu.mode;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import raven.menu.Menu;

/**
 * @author Raven
 */
public class ToolBarAccentColor extends JPanel {

    private final Menu menu;
    private final JPopupMenu popup = new JPopupMenu();
    private final String[] accentColorKeys = {
        "App.accent.default", "App.accent.blue", "App.accent.purple", "App.accent.red",
        "App.accent.orange", "App.accent.yellow", "App.accent.green"
    };
    private final String[] accentColorNames = {
        "Default", "Blue", "Purple", "Red", "Orange", "Yellow", "Green"
    };

    private boolean menuFull = true;
    private JToolBar toolbar;
    private JToggleButton selectedButton;

    public ToolBarAccentColor(Menu menu) {
        this.menu = menu;
        init();
    }

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        removeAll();
        if (menuFull) {
            add(toolbar);
            popup.remove(toolbar);
        } else {
            add(selectedButton);
            popup.add(toolbar);
        }
        revalidate(); // <- Agregado para actualizar correctamente el layout
        repaint();
    }

    public void show(Component com, int x, int y) {
        if (menu.getComponentOrientation().isLeftToRight()) {
            popup.show(com, x, y);
        } else {
            int px = toolbar.getPreferredSize().width + UIScale.scale(5);
            popup.show(com, -px, y);
        }
        SwingUtilities.updateComponentTreeUI(popup);
    }

    private void init() {
        setLayout(new BorderLayout());
        toolbar = new JToolBar();
        add(toolbar);

        putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background");
        toolbar.putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background");
        popup.putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background; borderColor:$Menu.background;");

        ButtonGroup group = new ButtonGroup();

        // Crear botón de selección
        selectedButton = new JToggleButton(new AccentColorIcon(accentColorKeys[0]));
        selectedButton.addActionListener((ActionEvent e) -> {
            int y = (selectedButton.getPreferredSize().height - (toolbar.getPreferredSize().height + UIScale.scale(10))) / 2;
            show(ToolBarAccentColor.this, getWidth() + UIScale.scale(4), y);
        });

        for (int i = 0; i < accentColorNames.length; i++) {
            String key = accentColorKeys[i];
            JToggleButton tbutton = new JToggleButton(new AccentColorIcon(key));
            tbutton.setSelected(UIManager.getColor("Component.accentColor").equals(UIManager.getColor(key)));
            tbutton.addActionListener((ActionEvent e) -> colorAccentChanged(key));
            group.add(tbutton);
            toolbar.add(tbutton);
        }
    }

    private void colorAccentChanged(String colorKey) {
        if (popup.isVisible()) {
            popup.setVisible(false);
        }
        Color color = UIManager.getColor(colorKey);
        selectedButton.setIcon(new AccentColorIcon(colorKey));

        try {
            Class<? extends LookAndFeel> lafClass = UIManager.getLookAndFeel().getClass();
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", toHexCode(color)));
            FlatLaf.setup(lafClass.getDeclaredConstructor().newInstance()); // ✅ Corrección aquí
            FlatLaf.updateUI();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    private String toHexCode(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private class AccentColorIcon extends FlatAbstractIcon {

        private final String colorKey;

        public AccentColorIcon(String colorKey) {
            super(16, 16, null);
            this.colorKey = colorKey;
        }

        @Override
        protected void paintIcon(Component c, Graphics2D g) {
            Color accColor = UIManager.getColor(colorKey);
            if (accColor == null) {
                accColor = Color.lightGray;
            } else if (!c.isEnabled()) {
                accColor = FlatLaf.isLafDark()
                        ? ColorFunctions.shade(accColor, 0.5f)
                        : ColorFunctions.tint(accColor, 0.6f);
            }
            g.setColor(accColor);
            g.fillRoundRect(1, 1, width - 2, height - 2, 5, 5);
        }
    }
}

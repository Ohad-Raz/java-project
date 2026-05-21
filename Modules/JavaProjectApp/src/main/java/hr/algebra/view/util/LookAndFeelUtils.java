/*
 * Project setup note:
 * Based on Exercise 09-10, Look and Feel menu pattern. FlatLaf default for final project GUI module.
 */

package hr.algebra.view.util;

import com.formdev.flatlaf.FlatLightLaf;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Look and Feel menu setup (Exercise 09-10 Notepad pattern + FlatLaf default).
 */
public final class LookAndFeelUtils {

    private static final Logger LOGGER = Logger.getLogger(LookAndFeelUtils.class.getName());

    private LookAndFeelUtils() {
    }

    public static void installDefaultLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, "FlatLaf install failed, falling back to Nimbus", ex);
            installNimbusLookAndFeel();
        }
    }

    public static void installNimbusLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void populateLookAndFeelMenu(JMenu lookAndFeelMenu, java.awt.Window window) {
        ButtonGroup group = new ButtonGroup();
        Arrays.asList(UIManager.getInstalledLookAndFeels()).forEach(lf -> {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lf.getName());
            group.add(item);
            lookAndFeelMenu.add(item);
            if (lf.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())) {
                item.setSelected(true);
            }
            item.addActionListener(e -> {
                try {
                    UIManager.setLookAndFeel(lf.getClassName());
                    SwingUtilities.updateComponentTreeUI(window);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            });
        });
    }
}

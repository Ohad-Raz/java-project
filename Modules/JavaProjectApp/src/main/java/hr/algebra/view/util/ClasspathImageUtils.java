/*
 * Classpath image loading for Swing panels (JavaProjectApp module-local).
 */

package hr.algebra.view.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class ClasspathImageUtils {

    private ClasspathImageUtils() {
    }

    /** Loads and stretches a classpath image to the label size; uses text fallback on failure. */
    public static void setLabelIconFromResource(
            JLabel label,
            Class<?> anchor,
            String resourcePath,
            int width,
            int height,
            String fallbackText) {
        int targetWidth = width > 0 ? width : 64;
        int targetHeight = height > 0 ? height : 64;
        try {
            label.setIcon(loadFittedIcon(anchor, resourcePath, targetWidth, targetHeight));
            label.setText("");
        } catch (IOException ex) {
            label.setIcon(null);
            label.setText(fallbackText);
        }
    }

    private static ImageIcon loadFittedIcon(Class<?> anchor, String resourcePath, int width, int height)
            throws IOException {
        try (InputStream inputStream = anchor.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Missing classpath resource: " + resourcePath);
            }
            BufferedImage source = ImageIO.read(inputStream);
            if (source == null) {
                throw new IOException("Unreadable image resource: " + resourcePath);
            }
            BufferedImage fitted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = fitted.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.drawImage(source, 0, 0, width, height, null);
            graphics.dispose();
            return new ImageIcon(fitted);
        }
    }
}

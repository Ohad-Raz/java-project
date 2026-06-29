/*
 * Classpath image loading for Swing panels (JavaProjectApp module-local).
 * Avoids depending on Utilities rebuild for banner/logo helpers at runtime.
 */

package hr.algebra.view.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class ClasspathImageUtils {

    private ClasspathImageUtils() {
    }

    /** Loads and scales a classpath image onto a label; uses text fallback on failure. */
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
            label.setIcon(loadScaledIcon(anchor, resourcePath, targetWidth, targetHeight));
            label.setText("");
        } catch (IOException ex) {
            label.setIcon(null);
            label.setText(fallbackText);
        }
    }

    public static ImageIcon loadScaledIcon(Class<?> anchor, String resourcePath, int width, int height)
            throws IOException {
        try (InputStream inputStream = anchor.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Missing classpath resource: " + resourcePath);
            }
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null) {
                throw new IOException("Unreadable image resource: " + resourcePath);
            }
            int targetWidth = width > 0 ? width : bufferedImage.getWidth();
            int targetHeight = height > 0 ? height : bufferedImage.getHeight();
            Image image = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }
    }
}

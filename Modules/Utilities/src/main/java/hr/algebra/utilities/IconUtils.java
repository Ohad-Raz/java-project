/*
 * Project setup note:
 * Based on Exercise 07-08, IconUtils utility pattern. Adapted for the final Java Programming 1 project.
 */

package hr.algebra.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class IconUtils {

    private IconUtils() {
    }

    public static ImageIcon createIcon(File file, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        return createIcon(bufferedImage, width, height);
    }

    /** Loads a classpath image resource and scales it for a JLabel icon. */
    public static ImageIcon createIconFromResource(Class<?> anchor, String resourcePath, int width, int height)
            throws IOException {
        try (InputStream inputStream = anchor.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Missing classpath resource: " + resourcePath);
            }
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return createIcon(bufferedImage, width, height);
        }
    }

    private static ImageIcon createIcon(BufferedImage bufferedImage, int width, int height) {
        int targetWidth = width > 0 ? width : bufferedImage.getWidth();
        int targetHeight = height > 0 ? height : bufferedImage.getHeight();
        Image image = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}

/*
 * Project setup note:
 * Based on Exercise 07-08, IconUtils utility pattern. Adapted for the final Java Programming 1 project.
 */

package hr.algebra.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class IconUtils {

    private IconUtils() {
    }

    public static ImageIcon createIcon(File file, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}

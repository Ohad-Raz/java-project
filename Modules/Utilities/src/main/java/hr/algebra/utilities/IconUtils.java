package hr.algebra.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// Scales image files for JLabel previews (Exercise 07-08 pattern).
public final class IconUtils {

    private IconUtils() {
    }

    public static ImageIcon createIcon(File file, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        return createIcon(bufferedImage, width, height);
    }

    private static ImageIcon createIcon(BufferedImage bufferedImage, int width, int height) {
        int targetWidth = width > 0 ? width : bufferedImage.getWidth();
        int targetHeight = height > 0 ? height : bufferedImage.getHeight();
        Image image = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}

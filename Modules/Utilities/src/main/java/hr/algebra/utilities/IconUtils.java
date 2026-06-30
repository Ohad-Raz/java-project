package hr.algebra.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// Scales image files for JLabel previews (Exercise 07-08 pattern).
public final class IconUtils {

    private static final String SUPPORTED_IMAGE_NAMES = "PNG, JPG, JPEG, or GIF";

    public static final String SUPPORTED_IMAGE_DESCRIPTION = SUPPORTED_IMAGE_NAMES + " images";
    public static final String UNSUPPORTED_IMAGE_MESSAGE =
            "Unsupported or invalid image file. Please choose a real "
            + SUPPORTED_IMAGE_NAMES + " image.";

    public static final String PNG = "png";    
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String GIF = "gif";

    private IconUtils() {
    }

    public static ImageIcon createIcon(File file, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        if (bufferedImage == null) {
            throw new IOException(UNSUPPORTED_IMAGE_MESSAGE);
        }
        return createIcon(bufferedImage, width, height);
    }

    private static ImageIcon createIcon(BufferedImage bufferedImage, int width, int height) throws IOException {
        if (bufferedImage == null) {
            throw new IOException(UNSUPPORTED_IMAGE_MESSAGE);
        }
        int targetWidth = width > 0 ? width : bufferedImage.getWidth();
        int targetHeight = height > 0 ? height : bufferedImage.getHeight();
        Image image = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}

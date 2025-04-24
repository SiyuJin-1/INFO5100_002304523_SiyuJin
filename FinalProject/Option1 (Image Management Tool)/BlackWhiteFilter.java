import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BlackWhiteFilter implements Filter {

    @Override
    public BufferedImage apply(BufferedImage inputImage) {
        // Create a new blank image, inverted, with the same size as the original image
        BufferedImage grayscale = new BufferedImage(
                inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY
        );

        // Draw the original image into the grayscale image
        Graphics g = grayscale.getGraphics();
        g.drawImage(inputImage, 0, 0, null);
        g.dispose();

        return grayscale;
    }
}

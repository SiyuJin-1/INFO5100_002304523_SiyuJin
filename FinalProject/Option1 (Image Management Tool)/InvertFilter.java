import java.awt.image.BufferedImage;

public class InvertFilter implements Filter {

    @Override
    public BufferedImage apply(BufferedImage inputImage) {
        // Create a new blank image, inverted, with the same size as the original image
        BufferedImage inverted = new BufferedImage(
                inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        // Change the color value to (255 - original value)
        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int rgb = inputImage.getRGB(x, y);

                int r = 255 - ((rgb >> 16) & 0xff);
                int g = 255 - ((rgb >> 8) & 0xff);
                int b = 255 - (rgb & 0xff);
                int invertedRGB = (r << 16) | (g << 8) | b;
                inverted.setRGB(x, y, invertedRGB);
            }
        }

        return inverted;
    }
}

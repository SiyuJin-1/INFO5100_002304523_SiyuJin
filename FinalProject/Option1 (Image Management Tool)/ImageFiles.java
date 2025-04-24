import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageFiles {
    File file;
    BufferedImage originalImage;
    Image thumbnail;

    // Constructor
    public ImageFiles(File file, BufferedImage originalImage) {
        this.file = file;
        this.originalImage = originalImage;
        createThumbnail();
    }

    // Getters and setters
    public File getFile() {
        return file;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public int getWidth() {
        return originalImage.getWidth();
    }

    public int getHeight() {
        return originalImage.getHeight();
    }

    public String getFormat() {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase();
    }

    // Create Thumbnail
    public void createThumbnail() {
        try {
            // Thumbnail size 100 * 100
            int thumbWidth = 100;
            int thumbHeight = 100;

            // Create a blank canvas
            BufferedImage thumbnailImg = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = thumbnailImg.createGraphics();

            // Fill the background with white
            g2d.setColor(java.awt.Color.WHITE);
            g2d.fillRect(0, 0, thumbWidth, thumbHeight);

            // Draw image to the canvas
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, thumbWidth, thumbHeight, null);
            g2d.dispose();

            // Change the image type, from BufferedImage to Image
            this.thumbnail = SwingFXUtils.toFXImage(thumbnailImg, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
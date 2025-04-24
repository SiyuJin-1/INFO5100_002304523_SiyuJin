import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

public class Thumbnail {
    HBox thumbnailsContainer;
    VBox propertiesContainer;
    ImageFiles selectedImage;
    Convert convert;

    // Constructor
    public Thumbnail(HBox thumbnailsContainer, VBox propertiesContainer) {
        this.thumbnailsContainer = thumbnailsContainer;
        this.propertiesContainer = propertiesContainer;
    }

    public void setConvert(Convert convert) {
        this.convert = convert;
    }

    // Display Image Thumbnail
    public void displayImageThumbnail(ImageFiles imageModel) {
        // Create a 100x100 ImageView to show thumbnail
        ImageView thumbnailView = new ImageView(imageModel.getThumbnail());
        thumbnailView.setFitWidth(100);
        thumbnailView.setFitHeight(100);
        thumbnailView.setPreserveRatio(true);

        // Add to the thumbnail thumbnailsContainer
        thumbnailsContainer.getChildren().add(thumbnailView);

        // Select image to show different property
        thumbnailView.setOnMouseClicked(e -> {
            selectedImage = imageModel;
            displayImageProperties(selectedImage);

            if (convert != null) {
                convert.setSelectedImage(selectedImage);
            }

            if (e.getClickCount() == 2) {
                showFullImage(imageModel);
            }
        });
    }

    // Display Image Properties
    public void displayImageProperties(ImageFiles imageModel) {
        if (imageModel == null || propertiesContainer == null) return;

        // Clear the old property
        propertiesContainer.getChildren().clear();

        // Show image property
        TextArea propertiesArea = new TextArea();
        propertiesArea.setEditable(false);
        propertiesArea.setText(
                "Filename: " + imageModel.getFile().getName() + "\n" +
                        "Width: " + imageModel.getWidth() + " px\n" +
                        "Height: " + imageModel.getHeight() + " px\n" +
                        "Format: " + imageModel.getFormat() + "\n" +
                        "Size: " + (imageModel.getFile().length() / 1024) + " KB\n" +
                        "Modified: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(imageModel.getFile().lastModified())) + "\n" +
                        "Color Space: " + getColorType(imageModel.getOriginalImage().getType()) + "\n"
        );

        // Add to the propertiesContainer
        propertiesContainer.getChildren().add(propertiesArea);
    }

    // Get image color type
    public String getColorType(int colorType) {
        switch (colorType) {
            case BufferedImage.TYPE_INT_RGB:
                return "RGB";
            case BufferedImage.TYPE_INT_ARGB:
                return "ARGB";
            case BufferedImage.TYPE_BYTE_GRAY:
                return "Grayscale";
            case BufferedImage.TYPE_3BYTE_BGR:
                return "RGB";
            default:
                return "Other Type" +colorType;
        }
    }

    // Show Full Image
    public void showFullImage(ImageFiles imageModel) {
        Stage imageStage = new Stage();
        imageStage.setTitle("Full Image - " + imageModel.getFile().getName());

        // Get the original image
        Image fullImage = SwingFXUtils.toFXImage(imageModel.getOriginalImage(), null);
        ImageView fullImageView = new ImageView(fullImage);
        fullImageView.setPreserveRatio(true);

        // Set the maximum display size
        fullImageView.setFitWidth(1000);
        fullImageView.setFitHeight(800);

        ScrollPane scrollPane = new ScrollPane(fullImageView);

        imageStage.setScene(new Scene(scrollPane));
        imageStage.show();
    }

}
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class Upload {
    Stage mainStage;
    List<ImageFiles> imageList = new ArrayList<>();
    Button uploadButton = new Button("Upload");
    Convert convert;
    Thumbnail thumbnail;

    // Constructor
    public Upload(Stage mainStage) {
        this.mainStage = mainStage;
        setupImageUploadButton();
    }

    // Getters and Setters
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setConvert(Convert convert) {
        this.convert = convert;
    }

    public Button getUploadButton() {
        return uploadButton;
    }

    // Set up Image Upload Button
    public void setupImageUploadButton() {
        // Can only choose images
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));

        // Choose and upload files
        uploadButton.setOnAction(e -> {
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(mainStage);
            if (selectedFiles != null) {
                for (File file : selectedFiles) {
                    try {
                        uploadedImage(file);
                    }
                    catch (Exception ex) {
                        showErrorAlert("Upload Error", ex.getMessage());
                    }
                }
            }
        });
    }

    public void uploadedImage(File file) throws IOException {
        // Read Image
        BufferedImage originalImage = ImageIO.read(file);
        if (originalImage == null) {
            throw new IOException("Could not read image: " + file.getName());
        }

        // Set to the currently selected image
        ImageFiles imageModel = new ImageFiles(file, originalImage);
        imageList.add(imageModel);

        // Show Thumbnail and Properties
        thumbnail.displayImageThumbnail(imageModel);
        thumbnail.displayImageProperties(imageModel);

        convert.setSelectedImage(imageModel);
    }

    // Error pop-up window
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


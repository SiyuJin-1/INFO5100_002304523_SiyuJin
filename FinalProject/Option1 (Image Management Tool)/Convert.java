import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Convert {
    Stage mainStage;
    ImageFiles selectedImage;
    List<File> convertedFiles = new ArrayList<>();
    VBox controlsContainer;
    Download download;

    // Constructor
    public Convert(Stage mainStage) {
        this.mainStage = mainStage;
        this.controlsContainer = new VBox(10);
        setupConversionButtons();
    }

    // Setters and Getters
    public void setDownload(Download download) {
        this.download = download;
    }

    public VBox getControlsContainer() {
        return controlsContainer;
    }

    public void setSelectedImage(ImageFiles image) {
        this.selectedImage = image;
    }

    // Set up some convert buttons, like standard convert button, Black-White convert button, Invert button
    public void setupConversionButtons() {
        // Create format selection checkboxes
        CheckBox pngOption = new CheckBox("PNG");
        CheckBox jpgOption = new CheckBox("JPG");
        CheckBox bmpOption = new CheckBox("BMP");
        CheckBox gifOption = new CheckBox("GIF");

        VBox formatOptions = new VBox(10,
                new Label("Select the conversion format:"),
                pngOption, jpgOption, bmpOption, gifOption
        );

        // Standard Convert button
        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> {
            if (selectedImage == null) {
                showErrorAlert("Error", "Please select the image to be converted first");
                return;
            }

            List<String> selectedFormats = new ArrayList<>();
            if (pngOption.isSelected()) selectedFormats.add("png");
            if (jpgOption.isSelected()) selectedFormats.add("jpg");
            if (bmpOption.isSelected()) selectedFormats.add("bmp");
            if (gifOption.isSelected()) selectedFormats.add("gif");

            if (selectedFormats.isEmpty()) {
                showErrorAlert("Error", "Please select at least one conversion format");
                return;
            }

            convertImage(selectedImage.getOriginalImage(),selectedImage, selectedFormats, "");
        });

        // B/W Convert button
        Button BWButton = new Button("Convert to B/W");
        BWButton.setOnAction(e -> {
            if (selectedImage == null) {
                showErrorAlert("Error", "Please select an image first.");
                return;
            }

            List<String> selectedFormats = new ArrayList<>();
            if (pngOption.isSelected()) selectedFormats.add("png");
            if (jpgOption.isSelected()) selectedFormats.add("jpg");
            if (bmpOption.isSelected()) selectedFormats.add("bmp");
            if (gifOption.isSelected()) selectedFormats.add("gif");

            if (selectedFormats.isEmpty()) {
                showErrorAlert("Error", "Please select at least one format.");
                return;
            }

            convertToBlackWhite(selectedImage, selectedFormats);
        });

        // Invert button
        Button invertButton = new Button("Convert to Invert");
        invertButton.setOnAction(e -> {
            if (selectedImage == null) {
                showErrorAlert("Error", "Please select an image first.");
                return;
            }

            List<String> selectedFormats = new ArrayList<>();
            if (pngOption.isSelected()) selectedFormats.add("png");
            if (jpgOption.isSelected()) selectedFormats.add("jpg");
            if (bmpOption.isSelected()) selectedFormats.add("bmp");
            if (gifOption.isSelected()) selectedFormats.add("gif");

            if (selectedFormats.isEmpty()) {
                showErrorAlert("Error", "Please select at least one format.");
                return;
            }

            convertToInvert(selectedImage, selectedFormats);
        });

        // Add to controls
        controlsContainer.getChildren().addAll(formatOptions, convertButton, BWButton, invertButton);
    }

    // Convert image
    public void convertImage(BufferedImage image, ImageFiles model, List<String> formats, String end) {
        try {
            List<File> newConverted = new ArrayList<>();
            for (String format : formats) {
                File file = generateOutputFile(model.getFile(), format, end);
                ImageIO.write(image, format, file);
                newConverted.add(file);
            }

            convertedFiles = newConverted;
            download.setConvertedFiles(convertedFiles);
            download.downLoadButton();
        } catch (Exception e) {
            showErrorAlert("Error", "Saving failed: " + e.getMessage());
        }
    }

    // Generate black-and-white images using the Filter interface
    public void convertToBlackWhite(ImageFiles imageModel, List<String> formats) {
        Filter bwFilter = FilterFactory.getFilter("bw");
        BufferedImage bwImage = bwFilter.apply(imageModel.getOriginalImage());
        convertImage(bwImage, imageModel, formats, "-BW");
    }

    // Generate invert images using the Filter interface
    public void convertToInvert(ImageFiles imageModel, List<String> formats) {
        Filter invertFilter = FilterFactory.getFilter("invert");
        BufferedImage bwImage = invertFilter.apply(imageModel.getOriginalImage());
        convertImage(bwImage, imageModel, formats, "-Invert");
    }

    // Generate converted file name
    public File generateOutputFile(File originalFile, String format, String end) {
        String originalName = originalFile.getName();
        String baseName = originalName.substring(0, originalName.lastIndexOf('.'));
        return new File(originalFile.getParent(), baseName + "_converted" + end + "." + format);
    }

    // Error pop-up window
    private void showErrorAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
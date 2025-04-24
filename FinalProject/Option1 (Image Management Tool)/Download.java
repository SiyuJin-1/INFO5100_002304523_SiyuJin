import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Download {
    Stage mainStage;
    List<File> convertedFiles = new ArrayList<>();
    VBox downloadContainer;

    // Constructor
    public Download(Stage mainStage) {
        this.mainStage = mainStage;
        this.downloadContainer = new VBox(10);
    }

    public void setConvertedFiles(List<File> files) {
        this.convertedFiles = files;
    }

    public VBox getDownloadContainer() {
        return downloadContainer;
    }

    // Set up download button
    public void downLoadButton() {
        if (convertedFiles.isEmpty()) {
            return;
        }

        // Create dlContainer and title
        VBox dlContainer = new VBox(10);
        Label titleLabel = new Label("Converted files:");
        dlContainer.getChildren().add(titleLabel);

        // Process the converted image file
        for (File file : convertedFiles) {
            HBox fileRow = new HBox(10);
            Label fileLabel = new Label(file.getName());
            Button downloadButton = new Button("Download");

            downloadButton.setOnAction(e -> {
                // Create a file save box
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName(file.getName());
                File saveLocation = fileChooser.showSaveDialog(mainStage);

                // Save files
                if (saveLocation != null) {
                    try {
                        Files.copy(file.toPath(), saveLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        showInfoAlert("Succeed", "The file has been successfully saved to " + saveLocation.getAbsolutePath());
                    }
                    catch (IOException ex) {
                        showErrorAlert("Error", "An error occurred when saving the file: " + ex.getMessage());
                    }
                }
            });

            // Add fileLabel and downloadButton to fileRow，then add fileRow to downloadContainer
            fileRow.getChildren().addAll(fileLabel, downloadButton);
            dlContainer.getChildren().add(fileRow);
        }

        // Clear the old download records and add new
        downloadContainer.getChildren().clear();
        downloadContainer.getChildren().add(dlContainer);
    }

    // Information pop-up window
    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Error pop-up window
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
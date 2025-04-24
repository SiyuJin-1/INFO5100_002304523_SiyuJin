import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;


public class ImageTool extends Application {
    // The main window of the application
    Stage mainStage;

    Thumbnail thumbnailImage;
    Convert convert;
    Upload upload;
    Download download;

    // UI container
    VBox root;
    HBox thumbnailsContainer;
    VBox propertiesContainer;
    VBox controlsContainer;
    HBox uploadContainer;
    VBox downloadContainer;

    @Override
    public void start(Stage mainStage) {
        this.mainStage = mainStage;

        convert = new Convert(mainStage);
        upload = new Upload(mainStage);
        download = new Download(mainStage);

        // Set the window title as "Image Management Tool"
        mainStage.setTitle("Image Management Tool");

        initializeUI();

        // Set the size to 1000x800
        mainStage.setScene(new Scene(root, 700, 800));
        mainStage.show();
    }

    private void initializeUI() {
        // Create the main layout container
        root = new VBox(20);
        root.setPadding(new Insets(20));

        Image bgImage = new Image("file:background.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        root.setBackground(new Background(backgroundImage));

        // Create a thumbnail area
        Label thumbnailLabel = new Label("Thumbnails");
        thumbnailLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2C3E50;");
        thumbnailsContainer = new HBox(10);
        thumbnailsContainer.setPadding(new Insets(20));

        // Wrap the thumbnail area with ScrollPane to support left and right scrolling
        ScrollPane thumbnailsScroll = new ScrollPane(thumbnailsContainer);
        thumbnailsScroll.setPrefHeight(200);
        thumbnailsScroll.setFitToWidth(true);

        VBox thumbnailBox = new VBox(5, thumbnailLabel, thumbnailsScroll);
        thumbnailBox.setAlignment(Pos.CENTER);
        thumbnailBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 10;");

        // Create a property area
        Label propertyLabel = new Label("Properties");
        propertyLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2C3E50;");
        propertiesContainer = new VBox(20);
        propertiesContainer.setPadding(new Insets(20));
        ScrollPane propertyScroll = new ScrollPane(propertiesContainer);
        propertyScroll.setPrefHeight(250);
        propertyScroll.setPrefWidth(300);
        propertyScroll.setFitToWidth(true);
        VBox propertyBox = new VBox(5, propertyLabel, propertyScroll);
        propertyBox.setAlignment(Pos.CENTER);
        propertyBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 10;");


        // Connect the container and convert to the Thumbnail instance
        thumbnailImage = new Thumbnail(thumbnailsContainer, propertiesContainer);
        thumbnailImage.setConvert(convert);

        // Create a control area to place the control button
        Label controllLabel = new Label("Convert Area");
        controllLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2C3E50;");
        controlsContainer = convert.getControlsContainer();
        controlsContainer.setPadding(new Insets(20));
        ScrollPane controlScroll = new ScrollPane(controlsContainer);
        controlScroll.setPrefHeight(250);
        controlScroll.setPrefWidth(300);
        VBox controlBox = new VBox(5, controllLabel, controlScroll);
        controlBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 10;");
        controlBox.setAlignment(Pos.CENTER);

        // Create a Upload button
        upload.setConvert(convert);
        upload.setThumbnail(thumbnailImage);
        upload.getUploadButton().setMinWidth(60);
        uploadContainer = new HBox(upload.getUploadButton());
        uploadContainer.setPadding(new Insets(10));

        // Create the download display area
        Label downloadlLabel = new Label("Download Area");
        downloadlLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2C3E50;");
        downloadContainer = download.getDownloadContainer();
        downloadContainer.setPadding(new Insets(20));
        ScrollPane downloadScroll = new ScrollPane(downloadContainer);
        downloadScroll.setPrefWidth(200);
        downloadScroll.setPrefHeight(200);
        VBox downloadBox = new VBox(5, downloadlLabel, downloadScroll);
        downloadBox.setAlignment(Pos.CENTER);
        downloadBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 10;");
        convert.setDownload(download);


        // Add all the components to the main container
        root.getChildren().addAll(
                thumbnailBox,
                new HBox(20, propertyBox, uploadContainer, controlBox),
                downloadBox
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}

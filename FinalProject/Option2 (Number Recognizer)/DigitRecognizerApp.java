import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class DigitRecognizerApp extends Application {
    int CANVAS_WIDTH = 280;
    int CANVAS_HEIGHT = 280;
    GraphicsContext gc;
    Label resultLabel = new Label("Result：");

    @Override
    public void start(Stage mainStage) {
        // Create the canvas and set the line width of the drawing context as 10
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(10);        
        clearCanvas();

        // Set up mouse drawing
        canvas.setOnMousePressed(e -> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        // Set up clear button, when clicking the button, it will clear the canvas
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearCanvas());

        // Set up recognize button, when clicking the button, it will save the image and recognize the number
        Button recognizeButton = new Button("Recognize");
        recognizeButton.setOnAction(e -> {
            saveCanvasAsImage(canvas, "digit_input.png");
            runPythonPrediction();
        });

        // Arrange layout
        HBox controls = new HBox(10, clearButton, recognizeButton);
        VBox layout = new VBox(10, canvas, controls, resultLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center");

        Scene scene = new Scene(layout);
        mainStage.setScene(scene);
        mainStage.setTitle("Number Recognizer");
        mainStage.show();
    }

    // Clear canvas
    public void clearCanvas() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(30);
    }

    // Save canvas as image
    public void saveCanvasAsImage(Canvas canvas, String filename) {
        WritableImage image = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
        // Take a screenshot of the current canvas and save it to image
        canvas.snapshot(null, image);
        File file = new File(filename);
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run a Python script (predict.py), read the prediction results and display them on the interface
    public void runPythonPrediction() {
        resultLabel.setText("Please wait for a moment...");
    
        new Thread(() -> {
            try {
                String pythonCmd = System.getenv("PYTHON_PATH");
                if (pythonCmd == null || pythonCmd.isEmpty()) {
                    pythonCmd = "/opt/anaconda3/bin/python"; // it can be changed to user Python Path
                }
    
                ProcessBuilder pb = new ProcessBuilder(pythonCmd, "predict.py");
                pb.redirectErrorStream(true);
                Process process = pb.start();

                // Read the output of Python，the last line will be teh result
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String lastLine = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line; 
                }

                // Check the execution status and set the result
                int exitCode = process.waitFor();
                String finalResult = (exitCode == 0)
                    ? "Result：" + lastLine.trim()
                    : "Error" + exitCode + "）：\n" + lastLine;
    
                javafx.application.Platform.runLater(() -> resultLabel.setText(finalResult));
    
            } catch (IOException e) {
                e.printStackTrace();
                javafx.application.Platform.runLater(() ->
                    resultLabel.setText("Please check PYTHON_PATH or use the absolute path")
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
                javafx.application.Platform.runLater(() ->
                    resultLabel.setText("The process was interrupted")
                );
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
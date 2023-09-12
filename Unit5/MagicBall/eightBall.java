package Unit5.MagicBall;

// Files and Random
import java.io.*;
import java.nio.file.*;
import java.lang.Math;
// JavaFX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class eightBall extends Application {
    private String[] outputs;

    // Receive responses from responses.txt
    public void takeResponses() {
        String contents = "";
        // Reading responses
        try {
            Path filePath = Paths.get("C:\\Users\\Ryan Krasinski\\2223\\CS4280_FX\\MagicBall\\responses.txt");
            contents = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }

        this.outputs = contents.split("\n");
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        takeResponses();

        // Set Output
        Label fortune = new Label();
        fortune.setText("What will your prediction be?");
        // Set Input Box
        Label question = new Label();
        question.setText("Enter a question: ");
        // Set Button
        Button btn = new Button();
        btn.setText("Enter");
        // Set TextField
        TextField inputArea = new TextField();

        // When input entered
        inputArea.setOnAction(action -> {
            fortune.setText(outputs[(int) (Math.random() * (outputs.length))]);
        });
        HBox hboxInput = new HBox(question, inputArea);
        hboxInput.setAlignment(Pos.CENTER);

        // When Button Pressed:
        btn.setOnAction(e -> {
            fortune.setText(outputs[(int) (Math.random() * (outputs.length))]);
        });

        // Layout
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(fortune, hboxInput, btn);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 400, 350);

        // Stage
        primaryStage.setTitle("Magic-8-Ball");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }

}

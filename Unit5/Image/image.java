package Unit5.Image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
// Files and Random
import java.io.*;
import java.nio.file.*;

public class image extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("ImageView Experiment 1");

        FileInputStream input = new FileInputStream("./MagicBall/8Ball.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

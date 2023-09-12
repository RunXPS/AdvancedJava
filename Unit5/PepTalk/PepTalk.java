package Unit5.PepTalk;

// Files and Random
import java.io.*;
import java.nio.file.*;
import java.lang.Math;
// JavaFX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class PepTalk extends Application {
    @Override
    public void start(Stage primaryStage) {
        String contents = "";
        String[] options;

        // Take in options from file
        try {
            Path filePath = Paths.get("C:\\Users\\Ryan Krasinski\\2223\\CS4280_FX\\PepTalk\\options.txt");
            contents = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        options = contents.split("\n");

        // Label(s)
        Label first = new Label();
        first.setText("---");
        Label second = new Label();
        second.setText("---");
        Label third = new Label();
        third.setText("---");

        // Initialize menus
        MenuButton intro = new MenuButton("Intro"), middle = new MenuButton("Middle"), end = new MenuButton("End");
        MenuItem item1, item2, item3;
        for (int i = 0; i < 16; i++) {
            int test = i;
            item1 = new MenuItem(options[i]);
            item1.setOnAction(e -> first.setText(options[test]));
            item2 = new MenuItem(options[i + 16]);
            item2.setOnAction(e -> second.setText(options[test + 16]));
            item3 = new MenuItem(options[i + 32]);
            item3.setOnAction(e -> third.setText(options[test + 32]));
            intro.getItems().add(item1);
            middle.getItems().add(item2);
            end.getItems().add(item3);
        }

        // Generation Button
        Button gen = new Button();
        gen.setText("Generate");
        gen.setOnAction(e -> {
            first.setText(options[(int) (Math.random() * (16))]);
            second.setText(options[(int) (Math.random() * (16) + 16)]);
            third.setText(options[(int) (Math.random() * (16) + 32)]);
        });

        // HBoxes
        HBox menus = new HBox(intro, middle, end);
        menus.setAlignment(Pos.CENTER);
        HBox output = new HBox(first, second, third);
        output.setAlignment(Pos.CENTER);

        // Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gen, menus, output);

        // set the scene / stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Pep Talk");

        // create a background fill
        BackgroundFill background_fill = new BackgroundFill(Color.TEAL,
                null, null);
        Background background = new Background(background_fill);
        layout.setBackground(background);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
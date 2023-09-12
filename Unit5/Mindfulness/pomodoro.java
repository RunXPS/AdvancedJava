package Mindfulness;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.concurrent.TimeUnit;

import java.time.LocalDateTime;

import javafx.concurrent.Task;

public class pomodoro extends Application {

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // current time
        int[] currentTime = {
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute(),
                LocalDateTime.now().getSecond() };
        // Visual Label
        Label hours = new Label(String.valueOf(currentTime[0]) + " - ");
        Label mins = new Label(String.valueOf(currentTime[1] + " - "));
        Label secs = new Label(String.valueOf(currentTime[2]));
        hours.setAlignment(Pos.CENTER);
        mins.setAlignment(Pos.CENTER);
        secs.setAlignment(Pos.CENTER);

        GridPane time = new GridPane();

        time.add(hours, 0, 1);
        time.add(mins, 1, 1);
        time.add(secs, 2, 1);
        time.setAlignment(Pos.CENTER);

        // create a layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(time);

        // set the scene / stage
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

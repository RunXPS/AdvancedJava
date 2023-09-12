package diyProj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Font;

import diyProj.MathExpressionEvaluator;

public class DIY_FX extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create a layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        // Visual Label
        Label output = new Label("_________________");
        output.setAlignment(Pos.CENTER);

        String[] equation = { "" };
        final String[] buttons = { "1", "2", "3", "*", "4", "5", "6", "-", "7", "8", "9", "+",
                "0", ".", "/" }; // "^", "(", ")",
        // Button grid
        GridPane numbers = new GridPane();
        int i = 0;
        for (; i < buttons.length; i++) {
            // final String current = Integer.toString(i);
            final int tmp = i;
            Button number = new Button(buttons[i]);
            number.setPrefWidth(45);
            number.setPrefHeight(45);
            number.setFont(new Font(15));
            number.setOnAction(e -> {
                equation[0] += buttons[tmp];
                Label tmpLabel = new Label(equation[0]);
                layout.getChildren().remove(1);
                layout.getChildren().addAll(tmpLabel);
                // output = new Label(equation[0]);
            });
            numbers.add(number, i % 4, i / 4);
        }

        // Entering and Evaluating ==> implementation from ChatGPT
        // Alternative evaluation technique requires JavaScript & ScriptEngines
        Button enter = new Button("GO");
        enter.setPrefWidth(45);
        enter.setPrefHeight(45);
        enter.setFont(new Font(15));
        enter.setOnAction(e -> {
            MathExpressionEvaluator var = new MathExpressionEvaluator();
            double test = var.evaluateMathExpression(equation[0]);
            System.out.println(test);
            equation[0] = "";
            layout.getChildren().remove(1);
            layout.getChildren().addAll(output);
        });
        numbers.add(enter, i % 4, i / 4);

        numbers.setAlignment(Pos.CENTER);

        numbers.getColumnConstraints().add(new ColumnConstraints(50)); // column 0 is 100 wide
        numbers.getColumnConstraints().add(new ColumnConstraints(50)); // column 1 is 200 wide

        layout.getChildren().addAll(numbers, output);

        // set the scene / stage
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class DIY_FX2 extends Application {

    public double evaluate(String[] equation) {

        if (equation[1] == "/") {
            return (Integer.parseInt(equation[0]) / Integer.parseInt(equation[2]));
        } else if (equation[1] == "*") {
            return (Integer.parseInt(equation[0]) * Integer.parseInt(equation[2]));
        } else if (equation[1] == "-") {
            return (Integer.parseInt(equation[0]) - Integer.parseInt(equation[2]));
        } else {
            return (Integer.parseInt(equation[0]) + Integer.parseInt(equation[2]));
        }
    }

    @Override
    public void start(Stage primaryStage) {

        String[] equation = { "0", "+", "0" };
        // create a layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        GridPane orientation = new GridPane();

        // create menu button
        MenuItem menuItem1 = new MenuItem("*");
        MenuItem menuItem2 = new MenuItem("/");
        MenuItem menuItem3 = new MenuItem("-");
        MenuItem menuItem4 = new MenuItem("+");
        menuItem1.setOnAction(e -> equation[1] = "*");
        menuItem2.setOnAction(e -> equation[1] = "/");
        menuItem3.setOnAction(e -> equation[1] = "-");
        menuItem4.setOnAction(e -> equation[1] = "+");
        MenuButton menuButton = new MenuButton("Operation", null, menuItem1, menuItem2, menuItem3, menuItem4);
        orientation.add(menuButton, 1, 0);

        MenuButton number1 = new MenuButton("Choose first number: ");
        MenuButton number2 = new MenuButton("Choose Second number: ");

        // numbers
        int i = 0;
        for (; i <= 9; i++) {
            final String tmp = Integer.toString(i);
            MenuItem first = new MenuItem(tmp);
            MenuItem second = new MenuItem(tmp);
            first.setOnAction(e -> equation[0] = tmp);
            second.setOnAction(e -> equation[2] = tmp);
            number1.getItems().add(first);
            number2.getItems().add(second);
        }
        orientation.add(number1, 0, 0);
        orientation.add(number2, 2, 0);

        // create label
        // Label output = new Label("000.000");
        // orientation.add(output, 3, 0);

        // create button
        Button btn = new Button();
        btn.setText("Go");
        btn.setOnAction(e -> {
            // create label
            Label tmp = new Label((Double.toString(evaluate(equation))));
            orientation.add(tmp, 4, 0);
        });
        orientation.add(btn, 3, 0);

        orientation.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(orientation);

        // set the scene / stage
        Scene scene = new Scene(layout, 700, 300);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
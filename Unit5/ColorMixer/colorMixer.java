package ColorMixer;

import javax.swing.GroupLayout.Alignment;

/*
 * Combo box ==> user chooses RGB or HSB
 * 
 * 3 Sliders for respective values ==> Red, Green, Blue/Hue, Saturation, Brightness
 * Background adjusts to colors
 * Swap text color depending on luminance
 * luminance = 0.2126*R + 0,7152*G + 0.0722*B
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class colorMixer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Beginning variables
        VBox layout = new VBox(20);
        double[] values = { 255, 255, 255 };

        // Combobox
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll("RGB", "HSB");

        // Labels
        Label rgbValue = new Label("RGB:" + values[0] + ", " + values[1] + ", " + values[2]);
        Label hsbValue = new Label("HSB: " + values[0] + ", " + (values[1] / 255) + ", " + (values[2] / 255));
        Label hexcode = new Label("HEX: ffffff");
        rgbValue.setAlignment(Pos.CENTER);
        hsbValue.setAlignment(Pos.CENTER);
        hexcode.setAlignment(Pos.CENTER);

        // Sliders
        Slider r_hSlider = new Slider(0, 255, 255);
        r_hSlider.setMajorTickUnit(5.0); // sets how many units the value changes by
        r_hSlider.setMinorTickCount(4); // how many minor ticks between two major ticks
        r_hSlider.setSnapToTicks(true); // snaps to ticks, no intermediate values
        r_hSlider.setShowTickMarks(true);
        r_hSlider.setShowTickLabels(true);
        r_hSlider.setOnMouseReleased(e -> {
            values[0] = r_hSlider.getValue();
            Color tmpColor = comboBox.getValue() == "HSB"
                    ? Color.hsb(values[0], values[1] / 255, values[2] / 255)
                    : Color.rgb((int) values[0], (int) values[1], (int) values[2]);
            BackgroundFill tmpFill = new BackgroundFill(tmpColor, null, null);
            Background tmpBackground = new Background(tmpFill);
            Label tmpRGB = new Label("RGB:" + values[0] + ", " + values[1] + ", " + values[2]);
            Label tmpHSB = new Label("HSB:" + values[0] + ", " + (values[1] / 255) + ", " + (values[2] / 255));
            Label tmpHEX = new Label("HEX: " + Integer.toHexString((int) values[0])
                    + Integer.toHexString((int) values[1]) + Integer.toHexString((int) values[2]));
            if ((0.2126 * tmpColor.getRed() * 255 + 0.7152 * tmpColor.getGreen() * 255
                    + 0.0722 * tmpColor.getBlue() * 255) > 127) {
                tmpRGB.setTextFill(Color.color(0, 0, 0));
                tmpHSB.setTextFill(Color.color(0, 0, 0));
                tmpHEX.setTextFill(Color.color(0, 0, 0));
            } else {
                tmpRGB.setTextFill(Color.color(1, 1, 1));
                tmpHSB.setTextFill(Color.color(1, 1, 1));
                tmpHEX.setTextFill(Color.color(1, 1, 1));
            }
            layout.getChildren().remove(4, 7);
            layout.getChildren().addAll(tmpRGB, tmpHSB, tmpHEX);
            layout.setBackground(tmpBackground);
        });

        Slider g_sSlider = new Slider(0, 255, 255);
        g_sSlider.setMajorTickUnit(5.0); // sets how many units the value changes by
        g_sSlider.setMinorTickCount(4); // how many minor ticks between two major ticks
        g_sSlider.setSnapToTicks(true); // snaps to ticks, no intermediate values
        g_sSlider.setShowTickMarks(true);
        g_sSlider.setShowTickLabels(true);
        g_sSlider.setOnMouseReleased(e -> {
            values[1] = g_sSlider.getValue();
            Color tmpColor = comboBox.getValue() == "HSB"
                    ? Color.hsb(values[0], values[1] / 255, values[2] / 255)
                    : Color.rgb((int) values[0], (int) values[1], (int) values[2]);
            BackgroundFill tmpFill = new BackgroundFill(tmpColor, null, null);
            Background tmpBackground = new Background(tmpFill);
            Label tmpRGB = new Label("RGB:" + values[0] + ", " + values[1] + ", " + values[2]);
            Label tmpHSB = new Label("HSB:" + values[0] + ", " + (values[1] / 255) + ", " + (values[2] / 255));
            Label tmpHEX = new Label("HEX: " + Integer.toHexString((int) values[0])
                    + Integer.toHexString((int) values[1]) + Integer.toHexString((int) values[2]));
            if ((0.2126 * tmpColor.getRed() * 255 + 0.7152 * tmpColor.getGreen() * 255
                    + 0.0722 * tmpColor.getBlue() * 255) > 127) {
                tmpRGB.setTextFill(Color.color(0, 0, 0));
                tmpHSB.setTextFill(Color.color(0, 0, 0));
                tmpHEX.setTextFill(Color.color(0, 0, 0));
            } else {
                tmpRGB.setTextFill(Color.color(1, 1, 1));
                tmpHSB.setTextFill(Color.color(1, 1, 1));
                tmpHEX.setTextFill(Color.color(1, 1, 1));
            }
            layout.getChildren().remove(4, 7);
            layout.getChildren().addAll(tmpRGB, tmpHSB, tmpHEX);
            layout.setBackground(tmpBackground);
        });

        Slider bl_brSlider = new Slider(0, 255, 255);
        bl_brSlider.setMajorTickUnit(5.0); // sets how many units the value changes by
        bl_brSlider.setMinorTickCount(4); // how many minor ticks between two major ticks
        bl_brSlider.setSnapToTicks(true); // snaps to ticks, no intermediate values
        bl_brSlider.setShowTickMarks(true);
        bl_brSlider.setShowTickLabels(true);
        bl_brSlider.setOnMouseReleased(e -> {
            values[2] = bl_brSlider.getValue();
            Color tmpColor = comboBox.getValue() == "HSB"
                    ? Color.hsb(values[0], values[1] / 255, values[2] / 255)
                    : Color.rgb((int) values[0], (int) values[1], (int) values[2]);
            BackgroundFill tmpFill = new BackgroundFill(tmpColor, null, null);
            Background tmpBackground = new Background(tmpFill);
            Label tmpRGB = new Label("RGB:" + values[0] + ", " + values[1] + ", " + values[2]);
            Label tmpHSB = new Label("HSB:" + values[0] + ", " + (values[1] / 255) + ", " + (values[2] / 255));
            Label tmpHEX = new Label("HEX: " + Integer.toHexString((int) values[0])
                    + Integer.toHexString((int) values[1]) + Integer.toHexString((int) values[2]));
            if ((0.2126 * tmpColor.getRed() * 255 + 0.7152 * tmpColor.getGreen() * 255
                    + 0.0722 * tmpColor.getBlue() * 255) > 127) {
                tmpRGB.setTextFill(Color.color(0, 0, 0));
                tmpHSB.setTextFill(Color.color(0, 0, 0));
                tmpHEX.setTextFill(Color.color(0, 0, 0));
            } else {
                tmpRGB.setTextFill(Color.color(1, 1, 1));
                tmpHSB.setTextFill(Color.color(1, 1, 1));
                tmpHEX.setTextFill(Color.color(1, 1, 1));
            }
            layout.getChildren().remove(4, 7);
            layout.getChildren().addAll(tmpRGB, tmpHSB, tmpHEX);
            layout.setBackground(tmpBackground);
        });

        // VBox
        layout.getChildren().addAll(comboBox, r_hSlider, g_sSlider, bl_brSlider, rgbValue, hsbValue, hexcode);

        layout.setAlignment(Pos.CENTER);

        // set the scene / stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Color Mixer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

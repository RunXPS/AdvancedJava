package Dino;

import javafx.stage.Screen;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.geometry.Rectangle2D;
import javafx.scene.robot.Robot;

public class Dino extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        int xPlace = (int) (primaryScreenBounds.getMaxX()) / 5;
        int yPlace = (int) (primaryScreenBounds.getMaxY() * 3) / 5;
        Robot HAL = new Robot();
        int i = 500;
        // Minimize
        HAL.mouseMove(1250, 15);
        Thread.sleep(i);
        HAL.mouseClick(MouseButton.PRIMARY);
        // Center on Screen
        HAL.mouseMove(primaryScreenBounds.getMaxX() / 2, primaryScreenBounds.getMaxY() / 2);
        while (true) {
            // Fullscreen
            // for cacti (295,440)
            // for dino (95,432)
            // for "O" (715,282)
            // for minimize (1250, 15)
            // 1365 767
            Color isCacti = HAL.getPixelColor(xPlace, yPlace);
            Color isCacti2 = HAL.getPixelColor(xPlace + 5, yPlace);
            Color isCacti3 = HAL.getPixelColor(yPlace - 5, yPlace);
            Color restart = HAL.getPixelColor(primaryScreenBounds.getMaxX() / 2, primaryScreenBounds.getMaxY() / 2);
            if (isCacti.equals(Color.web("535353ff")) || isCacti2.equals(Color.web("535353ff"))
                    || isCacti3.equals(Color.web("535353ff")) || restart.equals(Color.web("535353ff"))) {
                HAL.mouseClick(MouseButton.PRIMARY);
                // HAL.mousePress(MouseButton.PRIMARY);
                // Thread.sleep(i);
                // HAL.mouseRelease(MouseButton.PRIMARY);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
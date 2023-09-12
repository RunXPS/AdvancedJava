package Unit5.UselessJavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.Random;

public class bizzareUseless extends Application {

    private Random random = new Random();

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(400, 400);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        scene.setOnMouseClicked(event -> {
            drawCreepyFace(canvas.getGraphicsContext2D());
        });

        stage.setScene(scene);
        stage.show();
    }

    private void drawCreepyFace(GraphicsContext gc) {
        gc.setFill(randomColor());
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(randomColor());
        gc.fillOval(100, 100, 200, 200);
        gc.setFill(Color.WHITE);
        gc.fillOval(130, 160, 20, 20);
        gc.fillOval(250, 160, 20, 20);
        gc.setFill(randomColor());
        gc.fillArc(140, 220, 120, 80, 180, 180, ArcType.OPEN);
    }

    private Color randomColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

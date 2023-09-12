import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class CreateGUI extends Application {
    private Scene scene;
    private Label timer;
    private Label mineCounter = new Label();;
    private int flagCount;
    private Button reset;
    private HBox controlBar;
    private BorderPane root;
    private long startTime;
    private boolean timerRunning;
    private ChoiceBox<String> themeDropdown;

    public void winGame(GridPane visualField, int[][] intField, int size) {
        int test = 0;
        for (Node node : visualField.getChildren()) {
            Button btn = (Button) node;
            btn.setText(Integer.toString(intField[test / size][test % size]));
            if (intField[test / size][test % size] == -1) {
                btn.setText("X");
                btn.setStyle("-fx-background-color: #ff0000");
            } else {
                btn.setStyle("-fx-background-color: #dbf20f");
            }
            test++;
        }
    }

    public static void displayField(int size, int[][] field) {
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                if (field[i][j] == -1) {
                    System.out.print("X ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println("|");
        }
    }

    private int[][] generateField(int size, int mines) {
        int[][] field = new int[size][size];
        for (int i = 0; i < mines; i++) {
            int x = (int) (Math.random() * size), y = (int) (Math.random() * size);
            // Redo if same place
            if (field[x][y] == -1) {
                i--;
            } else {
                // Set random cell to bomb
                field[x][y] = -1;
                int k = x - 1, l = y - 1;
                // Adjust every cell around it up one
                for (; k <= x + 1; k++) {
                    // if row is an end
                    if (k < size && k >= 0) {
                        for (l = y - 1; l <= y + 1; l++) {
                            // if column is an end
                            if ((l < size && l >= 0)) {
                                if (field[k][l] != -1) {
                                    field[k][l] += 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return field;
    }

    private GridPane generateGrid(int size) {
        int mines = 2 * (size - 3);
        int[] end = { size * size };
        flagCount = mines;
        mineCounter.setText(String.valueOf(mines));
        int[][] intField = generateField(size, mines);
        // Grid
        GridPane visualField = new GridPane();
        visualField.setAlignment(Pos.CENTER);
        // Initialize grid of buttons
        for (int i = 0; i < size * size; i++) {
            final int tmp = i;
            Button square = new Button("#");
            square.setPrefHeight(33);
            square.setPrefWidth(33);
            // Initialize bombs
            if (intField[i / size][i % size] == -1) {
                square.setOnMouseClicked(e -> {
                    animationTimer.stop();
                    if (e.getButton() == MouseButton.PRIMARY && square.getText().equals("#")) {
                        int test = 0;
                        for (Node node : visualField.getChildren()) {
                            Button btn = (Button) node;
                            btn.setText(Integer.toString(intField[test / size][test % size]));
                            if (intField[test / size][test % size] == -1) {
                                btn.setText("X");
                                btn.setStyle("-fx-background-color: #ff0000");
                            }
                            test++;
                        }
                    } else if (square.getText().equals("#")) {
                        flagCount--;
                        mineCounter.setText(String.valueOf(flagCount));
                        square.setText("|>");
                        end[0]--;
                        if (end[0] == 0) {
                            winGame(visualField, intField, size);
                            animationTimer.stop();
                        }
                    } else if (square.getText().equals("|>")) {
                        flagCount++;
                        mineCounter.setText(String.valueOf(flagCount));
                        square.setText("#");
                        end[0]++;
                    }

                });
                // Initialize numbers
            } else if (intField[i / size][i % size] != 0) {
                square.setOnMouseClicked(e -> {
                    if (!timerRunning)
                        animationTimer.start();

                    if (e.getButton() == MouseButton.PRIMARY && square.getText().equals("#")) {
                        square.setText(Integer.toString(intField[tmp / size][tmp % size]));
                        end[0]--;
                        // If all squares clicked & bombs found
                        if (end[0] == 0) {
                            winGame(visualField, intField, size);
                            animationTimer.stop();
                        }
                    } else if (square.getText().equals("#")) {
                        flagCount--;
                        mineCounter.setText(String.valueOf(flagCount));
                        square.setText("|>");
                        end[0]++;
                    } else if (square.getText().equals("|>")) {
                        flagCount++;
                        mineCounter.setText(String.valueOf(flagCount));
                        square.setText("#");
                        end[0]--;
                    }
                });
                // Initialize Empty Spaces
            } else {
                square.setOnMouseClicked(e -> {
                    if (!timerRunning)
                        animationTimer.start();

                    if (e.getButton() == MouseButton.PRIMARY && square.getText().equals("#")) {
                        square.setText(Integer.toString(intField[tmp / size][tmp % size]));
                        end[0]--;
                        // trigger all surrounding numbers
                        int test = 0;
                        for (Node node : visualField.getChildren()) {
                            if (test == tmp) {
                                Button btn = (Button) node;
                                btn.setText(Integer.toString(intField[test / size][test % size]));
                                if (intField[test / size][test % size] == 0) {
                                    btn.setText("X");
                                    btn.setStyle("-fx-background-color: #ff0000");
                                }
                            }
                            test++;
                        }
                        // If all squares clicked & bombs found
                        if (end[0] == 0) {
                            winGame(visualField, intField, size);
                            animationTimer.stop();
                        }
                    } else if (square.getText().equals("#")) {
                        flagCount--;
                        mineCounter.setText(String.valueOf(flagCount));
                        square.setText("|>");
                        end[0]++;
                    } else if (square.getText().equals("|>")) {
                        flagCount++;
                        mineCounter.setText(String.valueOf(flagCount));
                        square.setText("#");
                        end[0]--;
                    }
                });
            }
            visualField.add(square, i / size, i % size);
        }
        return visualField;
    }

    // Controls the clock
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void start() {
            timerRunning = true;
            startTime = System.nanoTime();
            super.start();
        }

        @Override
        public void handle(long now) {
            long elapsedTime = now - startTime;
            long elapsedSeconds = elapsedTime / 1_000_000_000;

            long hours = elapsedSeconds / 3600;
            long mins = (elapsedSeconds % 3600) / 60;
            long secs = elapsedSeconds % 60;

            String showTime = String.format("%02d:%02d:%02d", hours, mins, secs);
            timer.setText(showTime);
        }

        @Override
        public void stop() {
            timerRunning = false;
            startTime = System.nanoTime();
            super.stop();
        }
    };

    @Override
    public void start(Stage primaryStage) {
        final VBox board = new VBox();
        board.setAlignment(Pos.CENTER);
        int[] size = { 7 };
        GridPane visualField = generateGrid(size[0]);
        MenuButton gridSize = new MenuButton("Grid Size", null);
        for (int i = 4; i < 15; i++) {
            final int tmp = i;
            MenuItem item = new MenuItem(Integer.toString(tmp));
            item.setOnAction(e -> {
                size[0] = tmp;
                final GridPane visualField2 = generateGrid(size[0]);
                board.getChildren().remove(0);
                board.getChildren().add(visualField2);
            });
            gridSize.getItems().add(item);

        }

        // Create theme dropdown box
        ObservableList<String> themes = FXCollections.observableArrayList();
        themes.addAll("Default", "Pinks", "Blues", "Warm");
        themeDropdown = new ChoiceBox<>(themes);
        themeDropdown.setValue(themes.get(0));

        // Control Bar Items
        timer = new Label("00:00:00");
        reset = new Button("Reset");
        controlBar = new HBox(mineCounter, gridSize, timer, themeDropdown, reset);

        // Control Bar Formatting
        timer.getStyleClass().add("counter");
        mineCounter.getStyleClass().add("counter");
        timer.setPrefSize(90, 14);
        mineCounter.setPrefSize(35, 12);
        timer.setAlignment(Pos.CENTER);
        mineCounter.setAlignment(Pos.CENTER);
        controlBar.getStyleClass().add("controlBar");
        controlBar.setAlignment(Pos.CENTER);
        controlBar.setSpacing(40);
        controlBar.setPrefHeight(60);

        // Reset Button Functionality
        reset.setOnAction(e -> {
            animationTimer.stop();
            timer.setText("00:00:00");
            final GridPane visualField2 = generateGrid(size[0]);
            board.getChildren().remove(0);
            board.getChildren().add(visualField2);
        });

        // Theme Box Functionality
        themeDropdown.setOnAction(e -> {
            String theme = themeDropdown.getValue();
            String styleFile = theme + ".css";
            scene.getStylesheets().remove(0);
            scene.getStylesheets().add(styleFile);
        });

        // Stage Hierarchy
        board.getChildren().addAll(visualField);
        root = new BorderPane(board, controlBar, null, null, null);
        scene = new Scene(root, 500, 560);
        scene.getStylesheets().add("Default.css");

        // Set and show Stage
        primaryStage.setTitle("Minesweeper v1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
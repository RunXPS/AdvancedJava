package Unit5.UselessJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class personalityTest extends Application {

    private Random random = new Random();
    private List<String> questions = new ArrayList<>(Arrays.asList(
            "Do you enjoy staring into space for long periods of time?",
            "Do you often feel like you're being watched by something unseen?",
            "Have you ever felt a sudden urge to do something destructive or violent?",
            "Do you have recurring nightmares about being trapped in a maze?",
            "Do you enjoy the taste of blood?",
            "Do you believe in the existence of supernatural entities?",
            "Do you find yourself drawn to dark or disturbing imagery?",
            "Have you ever felt like you were being possessed by an outside force?",
            "Do you feel a sense of dread or unease in unfamiliar places?",
            "Do you sometimes hear voices that no one else can hear?",
            "Do you have an affinity for sharp or pointy objects?",
            "Do you have a fascination with death or dying?",
            "Do you often feel like you're being followed by someone or something?",
            "Do you enjoy causing pain to others?",
            "Have you ever had an out-of-body experience?",
            "Do you find comfort in solitude and isolation?"));

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 400, 400);

        Label questionLabel = new Label();
        questionLabel.setFont(new Font(20));
        questionLabel.setWrapText(true);
        root.getChildren().add(questionLabel);

        ToggleGroup answerGroup = new ToggleGroup();
        RadioButton answer1 = new RadioButton();
        RadioButton answer2 = new RadioButton();
        RadioButton answer3 = new RadioButton();
        answer1.setToggleGroup(answerGroup);
        answer2.setToggleGroup(answerGroup);
        answer3.setToggleGroup(answerGroup);
        VBox answerBox = new VBox(answer1, answer2, answer3);
        answerBox.setAlignment(Pos.CENTER);
        answerBox.setSpacing(10);
        root.getChildren().add(answerBox);

        Button nextButton = new Button("Next");
        root.getChildren().add(nextButton);

        nextButton.setOnAction(event -> {
            int index = random.nextInt(questions.size());
            String question = questions.remove(index);
            questionLabel.setText(question);
            answerGroup.selectToggle(null);
            if (questions.isEmpty()) {
                nextButton.setText("Finish");
            }
            if (nextButton.getText().equals("Finish")) {
                showResult(stage);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void showResult(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 400, 400);

        Label resultLabel = new Label();
        resultLabel.setFont(new Font(20));
        root.getChildren().add(resultLabel);

        Button restartButton = new Button("Restart");
        root.getChildren().add(restartButton);

        restartButton.setOnAction(event -> {
            start(stage);
        });

        int score = 0;
    }
}
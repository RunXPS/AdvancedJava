package Mindfulness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application {
    ArrayList<ToDoItem> toDoList;
    VBox list;

    @Override
    public void start(Stage primaryStage) {
        toDoList = new ArrayList<ToDoItem>();
        list = new VBox();

        // ToolBar
        Label newTaskNameLabel = new Label("New Task Name:");
        TextField newTaskName = new TextField();
        Label dueDatePickerLabel = new Label("Due Date:");
        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setValue(LocalDate.now());
        Button createTask = new Button("Create New Task");
        ToolBar toolbar = new ToolBar(newTaskNameLabel, newTaskName, dueDatePickerLabel, dueDatePicker, createTask);

        // Create Task Button
        createTask.setOnAction(e -> {
            String taskName = String.valueOf(newTaskName.getCharacters());
            LocalDate dueDate = dueDatePicker.getValue();
            ToDoItem listItem = new ToDoItem(taskName, dueDate);
            toDoList.add(listItem);
            updateList();

            // List item Functionality:
            listItem.setOnAction(f -> {
                toDoList.remove(listItem);
                updateList();
            });
            newTaskName.clear();
            dueDatePicker.setValue(LocalDate.now());
        });

        // Load the saved list
        loadList();
        updateList();

        BorderPane rootNode = new BorderPane(list, toolbar, null, null, null);
        Scene scene = new Scene(rootNode, 600, 720);
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(scene);

        // CSS things
        // newTaskNameLabel.setStyle("-fx-font: normal bold 20px 'serif' ");
        // newTaskNameLabel.getStyleClass().add("TaskName");

        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        primaryStage.show();
    }

    private void updateList() {
        Collections.sort(toDoList);
        saveList();
        list.getChildren().clear();
        list.getChildren().addAll(toDoList);
    }

    private void saveList() {
        String fileOut = "";
        for (ToDoItem i : toDoList) {
            String taskName = i.getTask();
            String dueDate = i.getDueDate().toString();
            String itemString = String.format("%s∟%s\n", taskName, dueDate);
            fileOut += itemString;
        }
        try {
            Files.writeString(Paths.get("list.dat"), fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {
        // read from a file
        String contents = "";
        try {
            contents = Files.readString(Paths.get("list.dat"));
        } catch (IOException e) {
        }
        if (contents != "") {
            String[] taskStrings = contents.split("\n");
            for (String i : taskStrings) {
                String[] taskDateSplit = i.split("∟");
                String taskName = taskDateSplit[0];
                LocalDate dueDate = LocalDate.parse(taskDateSplit[1]);
                ToDoItem loadedItem = new ToDoItem(taskName, dueDate);
                loadedItem.setOnAction(f -> {
                    toDoList.remove(loadedItem);
                    updateList();
                });
                toDoList.add(loadedItem);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

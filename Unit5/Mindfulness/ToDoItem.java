package Mindfulness;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class ToDoItem extends TitledPane implements Comparable<ToDoItem> {
    private String task;
    private LocalDate dueDate;

    public ToDoItem(String task, LocalDate dueDate) {
        super();
        this.task = task;
        this.dueDate = dueDate;

        CheckBox taskCompleted = new CheckBox();
        taskCompleted.setOnAction(e -> fire());
        String dueDay = dueDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());

        this.setText(task);
        this.setGraphic(taskCompleted);
        this.setContent(
                new Label(String.format("Due %s, %d/%d", dueDay, dueDate.getMonthValue(), dueDate.getDayOfMonth())));
        this.setAlignment(Pos.CENTER);
        this.setContentDisplay(ContentDisplay.RIGHT);
    }

    // ******Below is a direct rip from JavaFX ButtonBase class****** \\
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return onAction;
    }

    public final void setOnAction(EventHandler<ActionEvent> value) {
        onActionProperty().set(value);
    }

    public final EventHandler<ActionEvent> getOnAction() {
        return onActionProperty().get();
    }

    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override
        protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }

        @Override
        public Object getBean() {
            return ToDoItem.this; // <-- Only thing I changed
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

    public void fire() {
        if (!isDisabled()) {
            fireEvent(new ActionEvent());
        }
    }

    public String getTask() {
        return task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        String dueDay = dueDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
        return String.format("Task: %s due %s, %d/%d", task, dueDay, dueDate.getMonthValue(), dueDate.getDayOfMonth());
    }

    public boolean equals(Object o) {
        if (!(o instanceof ToDoItem)) {
            return false;
        }
        ToDoItem obj = (ToDoItem) o;
        if (obj.getTask() != this.getTask() || obj.getDueDate().compareTo(this.getDueDate()) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(ToDoItem o) {
        if (this.equals(o)) {
            return 0;
        } else if (this.getDueDate().compareTo(o.getDueDate()) > 0) {
            return 1;
        } else if (this.getDueDate().compareTo(o.getDueDate()) < 0) {
            return -1;
        }
        return 0;
    }
}

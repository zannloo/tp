package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.taskmanager.ToDo;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class TodoCard extends UiPart<Region> {
    private static final String FXML = "TodoCard.fxml";

    public final ToDo todo;

    @FXML
    private HBox cardPane;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label type;
    @FXML
    private Label index;
    @FXML
    private Label desc;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TodoCard(ToDo todo, int displayedIndex) {
        super(FXML);
        this.todo = todo;
        index.setText(displayedIndex + ". ");
        type.setText("Todo");
        desc.setText(todo.getDesc());
        checkBox.setSelected(todo.getStatus());
    }
}

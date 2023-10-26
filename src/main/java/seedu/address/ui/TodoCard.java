package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.taskmanager.ToDo;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class TodoCard extends ItemCard {
    private static final String FXML = "TodoCard.fxml";

    public final ToDo todo;

    @FXML
    private HBox cardPane;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label type;
    @FXML
    private Label desc;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TodoCard(ToDo todo, int displayedIndex) {
        super(FXML, displayedIndex);
        this.todo = todo;
        type.setText("Todo");
        desc.setText(todo.getDesc());
        checkBox.setSelected(todo.getStatus());
        checkBox.setDisable(true);
        if (displayedIndex % 2 == 0) {
            cardPane.setStyle("-fx-background-color: #534531;");
        } else {
            cardPane.setStyle("-fx-background-color: #866937;");
        }
    }
}

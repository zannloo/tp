package seedu.address.ui;

import seedu.address.model.task.ToDo;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class TodoCard extends TaskCard {
    private static final String FXML = "TodoCard.fxml";

    public final ToDo todo;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TodoCard(ToDo todo, int displayedIndex) {
        super(todo.getDesc(), todo.getStatus(), FXML, displayedIndex);
        this.todo = todo;
        type.setText("Todo");
    }
}

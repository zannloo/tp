package seedu.address.model.task;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.TodoCard;

/**
 * The ToDo class represents a ToDo task for the ProfBook.
 * It extends the Task class and provides additional methods specific to ToDo tasks.
 */
public class ToDo extends Task {
    private static final Logger logger = LogsCenter.getLogger(ToDo.class);

    /**
     * Constructs a {@code ToDo} with given {@code description}.
     */
    public ToDo(String description) {
        super(description);
        logger.info("Created ToDo: " + description);
    }

    /**
     * Costructs a {@code ToDo} with given {@code description} and {@code status}.
     */
    public ToDo(String description, boolean status) {
        super(description, status);
    }

    @Override
    public ToDo mark() {
        return new ToDo(description, true);
    }

    @Override
    public ToDo unmark() {
        return new ToDo(description, false);
    }

    @Override
    public ToDo clone() {
        return new ToDo(description);
    }

    @Override
    public TodoCard getDisplayCard(int displayedIndex) {
        return new TodoCard(this, displayedIndex);
    }

    /**
     * Returns a string representation of the task when it is added to a list.
     * @return a string representing the task when it is added
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Checks if this ToDo is equal to another ToDo object.
     * Two tasks are equal if they have the same description and isDone status.
     * @param o The object to compare this task against.
     * @return true if the given object represents a Task equivalent to this task, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToDo task = (ToDo) o;
        return super.equals(task);
    }
}

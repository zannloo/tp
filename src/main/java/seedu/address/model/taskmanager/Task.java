package seedu.address.model.taskmanager;

import seedu.address.ui.Displayable;

/**
 * Encapsulates logic for Tasks in TaskList
 * The Task class represents a single task for the addressbook.
 * It is an abstract class that provides a common interface for different types of tasks.
 */
public abstract class Task implements Displayable {
    protected String description;
    protected Boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param s The description of the task.
     */
    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    public abstract void mark();

    public abstract void unmark();

    public String getDesc() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String statusString() {
        if(isDone) {
            return "true";
        }
        return "false";
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public abstract Task clone();

}

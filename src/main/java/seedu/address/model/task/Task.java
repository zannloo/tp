package seedu.address.model.task;

import seedu.address.ui.Displayable;

/**
 * Encapsulates logic for Tasks in TaskList
 * The Task class represents a single task for the profbook.
 * It is an abstract class that provides a common interface for different types of tasks.
 */
public abstract class Task implements Displayable {
    protected final String description;
    protected final Boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param s The description of the task.
     */
    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with given description is status.
     */
    public Task(String desc, boolean status) {
        this.description = desc;
        this.isDone = status;
    }

    /**
     * Marks the task as done.
     */
    abstract Task mark();

    /**
     * Marks the task as not done.
     */
    abstract Task unmark();

    /**
     * Returns a clone of the task.
     */
    public abstract Task clone();

    public String getDesc() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns "true" if task is done else "false".
     */
    public String statusString() {
        if (isDone) {
            return "true";
        }
        return "false";
    }

    /**
     * Return "X" if task is done else " ".
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) other;
        return description.equals(otherTask.description);
    }

}

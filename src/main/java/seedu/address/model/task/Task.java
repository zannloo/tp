package seedu.address.model.task;

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

    /**
     * This method checks the status of a task.
     *
     * @return A string representation of the status.
     *         Returns "true" if the task is done, "false" otherwise.
     */
    public String statusString() {
        if (isDone) {
            return "true";
        }
        return "false";
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public abstract Task clone();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) other;
        return description.equals(otherTask.description) && isDone.equals(otherTask.isDone);
    }

}

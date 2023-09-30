package seedu.address.model.taskmanager;

/**
 * Encapsulates logic for Tasks in TaskList
 */
/**
 * The Task class represents a single task for the addressbook.
 * It is an abstract class that provides a common interface for different types of tasks.
 */
public abstract class Task {
    protected  String description;
    protected Boolean isDone;
    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    public abstract void mark();

    public abstract void unmark();
    public String getStatusIcon() {

        return (this.isDone ? "X" : " ");
    }
}
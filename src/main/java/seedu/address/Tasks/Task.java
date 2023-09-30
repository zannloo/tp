package seedu.address.Tasks;

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

    public abstract String mark();

    public abstract String unmark();
    public abstract String listString();

    public abstract String remove ();
    public abstract String toSaveString();
    public String getStatusIcon() {

        return (this.isDone ? "X" : " ");
    }
}

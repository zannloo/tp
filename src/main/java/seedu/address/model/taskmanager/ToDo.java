package seedu.address.model.taskmanager;



/**
 * The ToDo class represents a deadline task for the ProfBook.
 * It extends the Task class and provides additional methods specific to ToDo tasks.
 */
public class ToDo extends Task {
    public ToDo(String s) {
        super(s);
    }

    /**
     * Marks the task as done and displays a message to the user.
     */
    public void mark() {
        this.isDone = true;
        assert this.isDone;
    }


    /**
     * Unmarks the task as done and displays a message to the user.
     */
    public void unmark() {
        this.isDone = false;
        assert !this.isDone;
    }

    /**
     * Returns a string representation of the task when it is added to a list.
     * @return a string representing the task when it is added
     */
    @Override
    public String toString() {
        return "Your ToDo has been added:\n"
                + "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

}

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
        return isDone.equals(task.isDone) && description.equals(task.description);
    }
}

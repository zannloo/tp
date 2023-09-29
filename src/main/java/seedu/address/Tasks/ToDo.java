package seedu.address.Tasks;

/**
 * The ToDo class represents a deadline task for the ProfBook.
 * It extends the Task class and provides additional methods specific to ToDo tasks.
 */
public class ToDo extends Task{
    public ToDo(String s) {
        super(s);
    }

    /**
     * Marks the task as done and displays a message to the user.
     */
    public String mark() {
        this.isDone = true;
        assert this.isDone;
        return "This task has been marked \n" +
                "[" + this.getStatusIcon() + "] " + this.description;
    }


    /**
     * Unmarks the task as done and displays a message to the user.
     */
    public String unmark() {
        this.isDone = false;
        assert !this.isDone;
        return "This task has been unmarked \n" +
                "[" + this.getStatusIcon() +"] " + this.description;
    }

    /**
     * Returns a string representation of the task when it is added to a list.
     * @return a string representing the task when it is added
     */
    @Override
    public String toString() {
        return "Your ToDo has been added:\n" +
                "[T]" + "[" + this.getStatusIcon() + "] " + this.description;

    }

    /**
     * Returns a string representation of the task for displaying in a list.
     * @return a string representing the task in a list
     */
    @Override
    public String listString() {
        return ". [T]" + "[" + this.getStatusIcon() +"] " + this.description +"\n";
    }

    /**
     * Returns a string representation of the task when it is removed from a list.
     * @return a string representing the task when it is removed
     */
    @Override
    public String remove() {
        return "ToDO has been removed:\n" +
                "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * @return a string representing the task in a save file
     */
    @Override
    public String toSaveString() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + description;
    }
}

package seedu.address.model.taskmanager;


import java.time.LocalDateTime;

/**
 * The Deadline class represents a deadline task for the ProfBook.
 * It extends the Task class and provides additional methods specific to deadline tasks.
 */
public class Deadline extends Task {
    private LocalDateTime dueBy;

    /**
     * Constructs a new Deadline object with the given description and deadline.
     * @param description the description of the Deadline task
     * @param deadline LocalDateTime object that stores the date of the deadline for the task
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.dueBy = deadline;

    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
        assert this.isDone;

    }

    /**
     * Unmarks the task as done
     */
    public void unmark() {
        this.isDone = false;
        assert !this.isDone;
    }

    /**
     * Returns a string representation of the task
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return "Deadline has been added:\n"
                + "[D][" + getStatusIcon() + "] " + this.description + "(by: " + this.dueBy + ")";
    }

    /**
     * Checks if this Deadline is equal to another Deadline object.
     * Two tasks are equal if they have the same description, isDone and dueBy status and time.
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
        Deadline deadline = (Deadline) o;
        return dueBy.equals(deadline.dueBy) && isDone.equals(deadline.isDone)
                && description.equals(deadline.description);
    }
}


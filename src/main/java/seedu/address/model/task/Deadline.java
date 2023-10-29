package seedu.address.model.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.ui.DeadlineCard;

/**
 * The Deadline class represents a deadline task for the ProfBook.
 * It extends the Task class and provides additional methods specific to deadline tasks.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER =
        DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mm a");
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

    public String getDeadline() {
        return this.dueBy.format(OUTPUT_DATETIME_FORMATTER);
    }

    @Override
    public Deadline clone() {
        return new Deadline(description, dueBy);
    }

    @Override
    public DeadlineCard getDisplayCard(int displayedIndex) {
        return new DeadlineCard(this, displayedIndex);
    }

    /**
     * Returns a string representation of the task
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + "(by: " + formatDueBy() + ")";
    }

    /**
     * Formats the dueBy LocalDateTime without the 'T' between date and time.
     *
     * @return formatted dueBy as a string
     */
    public String formatDueBy() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dueBy.format(customFormatter);
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


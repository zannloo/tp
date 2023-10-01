package seedu.address.logic.newcommands;

//import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
//import java.util.List;
//import seedu.address.commons.util.ToStringBuilder;
//import seedu.address.logic.Messages;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
//import seedu.address.model.path.element.PathElement;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.Deadline;
//import seedu.address.model.taskmanager.Task;

/**
 * Adds a person to the address book.
 */
public class CreateDeadlineForStudentCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO_TASK =
            "This Deadline task has already been allocated to this student in ProfBook";

    private final RelativePath path;
    private final Deadline deadline;

    /**
     * Creates an CreateDeadlineForStudentCommand to add the Deadline Task for a specified {@code Student}
     */
    public CreateDeadlineForStudentCommand(RelativePath path, Deadline deadline) {
        requireAllNonNull(path, deadline);
        this.path = path;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}

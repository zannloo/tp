package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.Deadline;

/**
 * Represents a command for creating a new deadline task within a specified group in ProfBook.
 * This command is typically used to add a deadline task to a group.
 */
public class CreateDeadlineForGroupCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";

    public static final String MESSAGE_DUPLICATE_DEADLINE_TASK = "This Deadline task has already been allocated to "
            + "this group in ProfBook";

    private final RelativePath path;
    private final Deadline deadline;

    /**
     * Constructs a {@code CreateDeadlineForGroupCommand} with the specified relative path and deadline details.
     *
     * @param path The relative path to the group where the deadline task will be added.
     * @param deadline The details of the deadline task to be created.
     */
    public CreateDeadlineForGroupCommand(RelativePath path, Deadline deadline) {
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

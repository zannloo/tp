package seedu.address.logic.newcommands;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.statemanager.State;

/**
 * Show Task List.
 */
public class ShowChildrenListCommand extends Command {
    public static final String COMMAND_WORD = "ls";
    public static final String MESSAGE_SUCCESS = "Show Children List of %1$s";
    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommand.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    /**
     * Executes the MoveStudentToGroupCommand, moving a student from the source group to the destination group in
     * ProfBook.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        state.showChildrenList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, "Current Directory"));
    }

    /**
     * Checks if this MoveStudentToGroupCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowChildrenListCommand)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of this MoveStudentToGroupCommand.
     *
     * @return A string representation of the MoveStudentToGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
